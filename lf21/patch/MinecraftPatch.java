/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.Slice;
import asm.patchify.annotation.Transform;
import asm.patchify.annotation.WrapInvoke;
import java.util.ArrayList;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.asm.Bootstrap;
import lf21.asm.Invocation;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.PostMotionEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.impl.combat.Displace;
import lf21.modules.impl.combat.TeleportAura;
import lf21.modules.impl.misc.BlockHideSeekDetector;
import lf21.modules.impl.movement.NoSlow;
import lf21.modules.impl.render.ESP;
import lf21.modules.impl.render.FakeFPS;
import lf21.modules.impl.render.Freecam;
import lf21.modules.impl.world.Nuker;
import lf21.patch.CallbackInfo;
import lf21.render.Renderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

@Patch(value=Minecraft.class)
public class MinecraftPatch {
    public static volatile boolean initialized = false;
    private static HitResult savedHitResult;

    public static void markInitialized() {
        initialized = true;
    }

    @Inject(method="getFps", desc="()I", at=@At(value=At.Type.TAIL))
    public static void onGetFps(Minecraft minecraft, CallbackInfo callbackInfo) {
        FakeFPS fakeFPS = FakeFPS.INSTANCE;
        if (fakeFPS != null && fakeFPS.isEnabled()) {
            int n2 = ((Number)callbackInfo.result).intValue();
            callbackInfo.result = fakeFPS.getDisplayedFps(n2);
        }
    }

    @Transform(method="runTick", desc="(Z)V")
    public static void transformFpsCache(MethodNode methodNode) {
        String string = Type.getInternalName(Minecraft.class);
        String string2 = Bootstrap.remapField(string, "fps");
        String string3 = Type.getInternalName(FakeFPS.class);
        int n2 = 0;
        for (AbstractInsnNode abstractInsnNode : methodNode.instructions.toArray()) {
            FieldInsnNode fieldInsnNode;
            if (!(abstractInsnNode instanceof FieldInsnNode) || (fieldInsnNode = (FieldInsnNode)abstractInsnNode).getOpcode() != 179 || !fieldInsnNode.name.equals(string2) && !fieldInsnNode.name.equals("fps") && !fieldInsnNode.name.equals("f_91021_") || !fieldInsnNode.desc.equals("I")) continue;
            InsnList insnList = new InsnList();
            insnList.add((AbstractInsnNode)new MethodInsnNode(184, string3, "hookFpsValue", "(I)I", false));
            methodNode.instructions.insertBefore((AbstractInsnNode)fieldInsnNode, insnList);
            ++n2;
        }
        ClientBase.logger.info("FakeFPS cache redirect applied to {} fps assignment site(s)", (Object)n2);
    }

    @Inject(method="startAttack", desc="()Z", at=@At(value=At.Type.HEAD))
    public static void onStartAttack(Minecraft minecraft, CallbackInfo callbackInfo) {
        if (!LF21Client.isReady()) {
            return;
        }
        if (Freecam.shouldBlockInteraction()) {
            callbackInfo.result = Boolean.FALSE;
            callbackInfo.cancel();
            return;
        }
        TeleportAura teleportAura = TeleportAura.INSTANCE;
        if (teleportAura != null && teleportAura.shouldInterceptVanillaAttack()) {
            callbackInfo.result = Boolean.FALSE;
            callbackInfo.cancel();
            return;
        }
        Nuker nuker = Nuker.INSTANCE;
        if (nuker != null && nuker.handleBedNukerClick()) {
            callbackInfo.result = Boolean.FALSE;
            callbackInfo.cancel();
            return;
        }
        Displace displace = Displace.INSTANCE;
        if (displace != null && displace.isEnabled() && displace.interceptVanillaAttack()) {
            callbackInfo.result = Boolean.FALSE;
            callbackInfo.cancel();
            return;
        }
        if (Freecam.shouldPrepareInteraction()) {
            Freecam.prepareCameraInteraction();
        }
    }

    @Inject(method="startAttack", desc="()Z", at=@At(value=At.Type.TAIL))
    public static void onStartAttackPost(Minecraft minecraft, CallbackInfo callbackInfo) {
        Freecam.finishCameraInteraction();
    }

    @Inject(method="continueAttack", desc="(Z)V", at=@At(value=At.Type.HEAD))
    public static void onContinueAttack(Minecraft minecraft, boolean bl, CallbackInfo callbackInfo) {
        if (!LF21Client.isReady()) {
            return;
        }
        if (Freecam.shouldBlockInteraction()) {
            callbackInfo.cancel();
            return;
        }
        Nuker nuker = Nuker.INSTANCE;
        if (nuker != null && nuker.handleBedNukerHold(bl)) {
            callbackInfo.cancel();
            return;
        }
        if (bl && Freecam.shouldPrepareInteraction()) {
            Freecam.prepareCameraInteraction();
        }
    }

    @Inject(method="continueAttack", desc="(Z)V", at=@At(value=At.Type.TAIL))
    public static void onContinueAttackPost(Minecraft minecraft, boolean bl, CallbackInfo callbackInfo) {
        Freecam.finishCameraInteraction();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Inject(method="tick", desc="()V")
    public static void onTick(Minecraft minecraft, CallbackInfo callbackInfo) throws Throwable {
        if (!initialized) {
            Class<MinecraftPatch> clazz = MinecraftPatch.class;
            // MONITORENTER : lf21.patch.MinecraftPatch.class
            if (!initialized) {
                ClientBase.mc = LF21Client.getMcInstance();
                ClientBase.isLoading = true;
                ModList.get().getMods().removeIf(iModInfo -> iModInfo.getModId().equals("hey"));
                ArrayList<IModFileInfo> arrayList = new ArrayList<IModFileInfo>();
                for (IModFileInfo iModFileInfo : ModList.get().getModFiles()) {
                    for (IModInfo iModInfo2 : iModFileInfo.getMods()) {
                        if (!iModInfo2.getModId().equals("hey")) continue;
                        arrayList.add(iModFileInfo);
                    }
                }
                ModList.get().getModFiles().removeAll(arrayList);
                new LF21Client();
                initialized = true;
            }
            // MONITOREXIT : clazz
        }
        if (!LF21Client.isReady()) return;
        LF21Client.serverTickRate = 1.0f;
        ClientBase.yaw = minecraft.player.getYRot();
        LF21Client.instance.getEventBus().call(new TickEvent());
    }

    @Inject(method="tick", desc="()V", at=@At(value=At.Type.TAIL))
    public static void onTickPost(Minecraft minecraft, CallbackInfo callbackInfo) throws Throwable {
        if (LF21Client.isReady()) {
            LF21Client.instance.getEventBus().call(new PostMotionEvent());
        }
    }

    @Inject(method="close", desc="()V", at=@At(value=At.Type.HEAD))
    public static void onClose(Minecraft minecraft, CallbackInfo callbackInfo) {
        LF21Client.getInstance().shutdown();
    }

    @Inject(method="setLevel", desc="(Lnet/minecraft/client/multiplayer/ClientLevel;)V")
    public static void onSetLevel(ClientLevel clientLevel, CallbackInfo callbackInfo) {
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(new DisconnectEvent());
        }
    }

    @Inject(method="handleKeybinds", desc="()V", at=@At(value=At.Type.BEFORE_INVOKE, method="net/minecraft/client/player/LocalPlayer/isUsingItem", desc="()Z"), slice=@Slice(startIndex=1, endIndex=1))
    public static void onHandleKeybinds(Minecraft minecraft, CallbackInfo callbackInfo) {
        if (LF21Client.isReady()) {
            PreMotionEvent preMotionEvent = new PreMotionEvent();
            LF21Client.getInstance().getEventBus().call(preMotionEvent);
            if (preMotionEvent.isCancelled()) {
                callbackInfo.cancel();
            }
        }
    }

    @Inject(method="startUseItem", desc="()V", at=@At(value=At.Type.HEAD))
    public static void onStartUseItemPre(Minecraft minecraft, CallbackInfo callbackInfo) {
        if (Freecam.shouldBlockInteraction()) {
            callbackInfo.cancel();
            return;
        }
        if (Freecam.shouldPrepareInteraction()) {
            Freecam.prepareCameraInteraction();
        }
        if (NoSlow.shouldCancelEdNavenStartUse(minecraft)) {
            callbackInfo.cancel();
            return;
        }
        if ((NoSlow.isBlocking(minecraft) || NoSlow.shouldBypassBlockInteraction(minecraft)) && (savedHitResult = minecraft.hitResult) != null) {
            Vec3 vec3 = savedHitResult.getLocation();
            minecraft.hitResult = BlockHitResult.miss((Vec3)vec3, (Direction)Direction.DOWN, (BlockPos)BlockPos.containing((Position)vec3));
        }
    }

    @Inject(method="startUseItem", desc="()V", at=@At(value=At.Type.TAIL))
    public static void onStartUseItemPost(Minecraft minecraft, CallbackInfo callbackInfo) {
        if (savedHitResult != null) {
            minecraft.hitResult = savedHitResult;
            savedHitResult = null;
        }
        Freecam.finishCameraInteraction();
    }

    @WrapInvoke(method="shouldEntityAppearGlowing", desc="(Lnet/minecraft/world/entity/Entity;)Z", target="net/minecraft/world/entity/Entity/isCurrentlyGlowing", targetDesc="()Z")
    public static boolean onShouldEntityGlow(Minecraft minecraft, Entity entity, Invocation<Entity, Boolean> invocation) throws Exception {
        if (BlockHideSeekDetector.shouldGlow(entity)) {
            return true;
        }
        if (LF21Client.isReady() && LF21Client.instance.getModuleManager() != null && ESP.INSTANCE != null && ESP.INSTANCE.isGlowing(entity)) {
            return true;
        }
        return (Boolean)invocation.call();
    }

    @Inject(method="resizeDisplay", desc="()V", at=@At(value=At.Type.TAIL))
    public static void onResizeDisplay(Minecraft minecraft, CallbackInfo callbackInfo) {
        Renderer.setGuiScaleVerified((float)minecraft.getWindow().getGuiScale());
    }
}

