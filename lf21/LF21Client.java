/*
 * Decompiled with CFR 0.152.
 */
package lf21;

import asm.patchify.loader.PatchAgent;
import asm.patchify.loader.PatchRegistry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import lf21.ClientBase;
import lf21.asm.Bootstrap;
import lf21.event.EventBus;
import lf21.event.EventTarget;
import lf21.event.impl.TickEvent;
import lf21.gui.IntroAnimation;
import lf21.manager.CommandManager;
import lf21.manager.ConfigManager;
import lf21.manager.HudManager;
import lf21.manager.LagManager;
import lf21.manager.LookForManager;
import lf21.manager.ModuleManager;
import lf21.manager.NotificationManager;
import lf21.manager.TargetManager;
import lf21.patch.AbstractContainerScreenPatch;
import lf21.patch.BlockOcclusionCachePatch;
import lf21.patch.BlockPatch;
import lf21.patch.CameraPatch;
import lf21.patch.ChatScreenPatch;
import lf21.patch.ClientLevelPatch;
import lf21.patch.ClientPacketListenerPatch;
import lf21.patch.ConnectionPatch;
import lf21.patch.EntityPatch;
import lf21.patch.EntityRendererPatch;
import lf21.patch.ForgeGuiPatch;
import lf21.patch.GameRendererPatch;
import lf21.patch.GlUtilHwidPatch;
import lf21.patch.GuiPatch;
import lf21.patch.HumanoidModelPatch;
import lf21.patch.ItemInHandLayerPatch;
import lf21.patch.ItemInHandRendererPatch;
import lf21.patch.ItemPatch;
import lf21.patch.KeyboardHandlerPatch;
import lf21.patch.KeyboardInputPatch;
import lf21.patch.LevelRendererPatch;
import lf21.patch.LivingEntityPatch;
import lf21.patch.LivingEntityRendererPatch;
import lf21.patch.LocalPlayerPatch;
import lf21.patch.MinecraftPatch;
import lf21.patch.MouseHandlerPatch;
import lf21.patch.MultiPlayerGameModePatch;
import lf21.patch.PacketUtilsPatch;
import lf21.patch.PlayerPatch;
import lf21.patch.PlayerTabOverlayPatch;
import lf21.patch.ScreenPatch;
import lf21.patch.VanillaGuiOverlayPatch;
import lf21.utils.rotation.RotationHandler;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;

@Mod(value="hey")
public class LF21Client
extends ClientBase {
    public static LF21Client instance;
    public static final String CLIENT_NAME;
    public static final String VERSION;
    public static float serverTickRate;
    public static boolean isReady;
    public static boolean isMCPMapped;
    public static String configDir;
    public static String username;
    private static final String[] CLOUD_ASSET_NAMES;
    private EventBus eventBus;
    private RotationHandler rotationHandler;
    private ModuleManager moduleManager;
    private NotificationManager notificationManager;
    private CommandManager commandManager;
    private ConfigManager configManager;
    private HudManager hudManager;
    private LagManager lagManager;
    private TargetManager targetManager;
    private LookForManager lookForManager;
    private int reconnectAttempts;
    private boolean moduleInit = false;
    private long lastConfigSave;

    public LF21Client() {
        if (instance == null) {
            instance = this;
            this.init();
        }
    }

    private void init() {
        try {
            username = System.getProperty("user.name", "Player");
            File file = new File(configDir);
            if (!file.exists() && !file.mkdirs()) {
                logger.warn("Failed to create config directory at {}", (Object)configDir);
            }
            mc = LF21Client.getMcInstance();
            this.eventBus = new EventBus();
            this.rotationHandler = new RotationHandler();
            this.eventBus.register(this.rotationHandler);
            this.moduleManager = new ModuleManager();
            this.notificationManager = new NotificationManager();
            this.hudManager = new HudManager();
            this.commandManager = new CommandManager();
            this.configManager = new ConfigManager();
            this.extractCloudAssets();
            this.lagManager = new LagManager();
            this.targetManager = new TargetManager();
            this.lookForManager = new LookForManager();
            this.eventBus.register(this.hudManager);
            this.eventBus.register(this.notificationManager);
            this.eventBus.register(this.lagManager);
            this.eventBus.register(this.targetManager);
            this.eventBus.register(this);
            this.commandManager.initCommands();
            this.eventBus.register(new IntroAnimation());
            Bootstrap.init();
            LF21Client.registerPatches();
            if (PatchAgent.getInstrumentation() != null) {
                PatchAgent.installPatchesAndRetransform();
            } else {
                logger.warn("agent not attached. Launch with `./gradlew runClient0` so the agent jvmArg is set.");
            }
            isReady = true;
            logger.info("{} v{} initialized.", (Object)"lf21", (Object)"1.1");
            logger.info("LF21 v1.0 initialized.");
        }
        catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
        }
    }

    @EventTarget
    public void onTick(TickEvent tickEvent) {
        if (LF21Client.isReady() && !this.moduleInit) {
            this.moduleInit = true;
            this.moduleManager.initModules();
            this.configManager.captureDefaults();
            this.configManager.resetToDefaults();
            this.configManager.loadAll();
            this.configManager.publishRuntimeStatus();
            this.lastConfigSave = System.currentTimeMillis();
            this.publishInjectionReady();
        }
        if (LF21Client.isReady() && this.moduleInit && this.configManager != null) {
            this.configManager.pollExternalCommand();
        }
    }

    public static boolean isReady() {
        return instance != null && LF21Client.instance.eventBus != null && isReady && mc != null && LF21Client.mc.player != null && !username.isEmpty() && LF21Client.mc.player.tickCount > 5;
    }

    public void shutdown() {
        isReady = false;
        this.clearInjectionReady();
        if (this.configManager != null) {
            this.configManager.saveAll();
        }
    }

    private static Path injectionReadyFile() {
        String string = System.getenv("LOCALAPPDATA");
        Path path = string == null || string.isBlank() ? Path.of(System.getProperty("java.io.tmpdir"), new String[0]) : Path.of(string, new String[0]);
        return path.resolve("LF21").resolve("inject-ready-" + ProcessHandle.current().pid() + ".txt");
    }

    private void publishInjectionReady() {
        try {
            Path path = LF21Client.injectionReadyFile();
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            Files.writeString(path, (CharSequence)"ready", StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        catch (IOException iOException) {
            logger.error("Failed to publish LF21 injection readiness", (Throwable)iOException);
        }
    }

    private void clearInjectionReady() {
        try {
            Files.deleteIfExists(LF21Client.injectionReadyFile());
        }
        catch (IOException iOException) {
            logger.warn("Failed to clear LF21 injection readiness", (Throwable)iOException);
        }
    }

    private void extractCloudAssets() {
        File file = ConfigManager.CONFIG_DIR;
        if (!file.exists() && !file.mkdirs()) {
            logger.warn("Failed to create config directory at {}", (Object)file);
            return;
        }
        for (String string : CLOUD_ASSET_NAMES) {
            File file2 = new File(file, string);
            if (file2.exists()) continue;
            try (InputStream inputStream = LF21Client.openCloudAsset(string);){
                if (inputStream == null) {
                    logger.warn("Cloud asset missing on classpath: {}", (Object)string);
                    continue;
                }
                try (FileOutputStream fileOutputStream = new FileOutputStream(file2);){
                    inputStream.transferTo(fileOutputStream);
                }
            }
            catch (IOException iOException) {
                logger.error("Failed to extract cloud asset {}", (Object)string, (Object)iOException);
            }
        }
    }

    private static InputStream openCloudAsset(String string) {
        File file;
        String string2 = "/assets/lf21/cloud_assets/" + string;
        InputStream inputStream = LF21Client.class.getResourceAsStream(string2);
        if (inputStream != null) {
            return inputStream;
        }
        String string3 = System.getProperty("lf21.resources");
        if (string3 != null && (file = new File(string3, "assets/lf21/cloud_assets/" + string)).isFile()) {
            try {
                return new FileInputStream(file);
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        return null;
    }

    public static void registerPatches() {
        PatchRegistry.register(MinecraftPatch.class);
        PatchRegistry.register(MultiPlayerGameModePatch.class);
        PatchRegistry.register(MouseHandlerPatch.class);
        PatchRegistry.register(LocalPlayerPatch.class);
        PatchRegistry.register(LivingEntityPatch.class);
        PatchRegistry.register(EntityPatch.class);
        PatchRegistry.register(PlayerPatch.class);
        PatchRegistry.register(ClientPacketListenerPatch.class);
        PatchRegistry.register(ConnectionPatch.class);
        PatchRegistry.register(PacketUtilsPatch.class);
        PatchRegistry.register(KeyboardHandlerPatch.class);
        PatchRegistry.register(KeyboardInputPatch.class);
        PatchRegistry.register(ClientLevelPatch.class);
        PatchRegistry.register(AbstractContainerScreenPatch.class);
        PatchRegistry.register(ChatScreenPatch.class);
        PatchRegistry.register(ScreenPatch.class);
        PatchRegistry.register(EntityRendererPatch.class);
        PatchRegistry.register(LevelRendererPatch.class);
        PatchRegistry.register(BlockPatch.class);
        PatchRegistry.register(GameRendererPatch.class);
        PatchRegistry.register(CameraPatch.class);
        PatchRegistry.register(GuiPatch.class);
        PatchRegistry.register(ForgeGuiPatch.class);
        PatchRegistry.register(VanillaGuiOverlayPatch.class);
        PatchRegistry.register(ItemInHandRendererPatch.class);
        PatchRegistry.register(ItemInHandLayerPatch.class);
        PatchRegistry.register(HumanoidModelPatch.class);
        PatchRegistry.register(LivingEntityRendererPatch.class);
        PatchRegistry.register(ItemPatch.class);
        PatchRegistry.register(PlayerTabOverlayPatch.class);
        PatchRegistry.register(GlUtilHwidPatch.class);
        PatchRegistry.register(BlockOcclusionCachePatch.class);
    }

    public static Minecraft getMcInstance() {
        Minecraft minecraft = null;
        try {
            Class<Minecraft> clazz = Minecraft.class;
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() != clazz) continue;
                field.setAccessible(true);
                minecraft = (Minecraft)field.get(null);
                field.setAccessible(false);
            }
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return minecraft != null ? minecraft : Minecraft.getInstance();
    }

    @Generated
    public EventBus getEventBus() {
        return this.eventBus;
    }

    @Generated
    public RotationHandler getRotationHandler() {
        return this.rotationHandler;
    }

    @Generated
    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }

    @Generated
    public NotificationManager getNotificationManager() {
        return this.notificationManager;
    }

    @Generated
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Generated
    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    @Generated
    public HudManager getHudManager() {
        return this.hudManager;
    }

    @Generated
    public LagManager getLagManager() {
        return this.lagManager;
    }

    @Generated
    public TargetManager getTargetManager() {
        return this.targetManager;
    }

    @Generated
    public LookForManager getLookForManager() {
        return this.lookForManager;
    }

    @Generated
    public int getReconnectAttempts() {
        return this.reconnectAttempts;
    }

    @Generated
    public boolean isModuleInit() {
        return this.moduleInit;
    }

    @Generated
    public long getLastConfigSave() {
        return this.lastConfigSave;
    }

    @Generated
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Generated
    public void setRotationHandler(RotationHandler rotationHandler) {
        this.rotationHandler = rotationHandler;
    }

    @Generated
    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    @Generated
    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    @Generated
    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Generated
    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Generated
    public void setHudManager(HudManager hudManager) {
        this.hudManager = hudManager;
    }

    @Generated
    public void setLagManager(LagManager lagManager) {
        this.lagManager = lagManager;
    }

    @Generated
    public void setTargetManager(TargetManager targetManager) {
        this.targetManager = targetManager;
    }

    @Generated
    public void setLookForManager(LookForManager lookForManager) {
        this.lookForManager = lookForManager;
    }

    @Generated
    public void setReconnectAttempts(int n2) {
        this.reconnectAttempts = n2;
    }

    @Generated
    public void setModuleInit(boolean bl) {
        this.moduleInit = bl;
    }

    @Generated
    public void setLastConfigSave(long l2) {
        this.lastConfigSave = l2;
    }

    @Generated
    public static LF21Client getInstance() {
        return instance;
    }

    static {
        VERSION = "1.1";
        CLIENT_NAME = "lf21";
        configDir = System.getProperty("user.home") + File.separator + ".lf21shit-forge-1.20.1";
        username = "";
        CLOUD_ASSET_NAMES = new String[]{"panel.png", "ptr.png", "lie.wav", "truth.wav"};
    }
}

