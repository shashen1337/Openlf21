/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.KeyEvent;
import lf21.exception.ModuleNotFoundException;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.AntiBots;
import lf21.modules.impl.combat.AntiFireball;
import lf21.modules.impl.combat.AntiKB;
import lf21.modules.impl.combat.AutoOffHand;
import lf21.modules.impl.combat.AutoSoup;
import lf21.modules.impl.combat.AutoThrow;
import lf21.modules.impl.combat.Backtrack;
import lf21.modules.impl.combat.BowAim;
import lf21.modules.impl.combat.Critical;
import lf21.modules.impl.combat.CrystalAura;
import lf21.modules.impl.combat.Displace;
import lf21.modules.impl.combat.FakeLag;
import lf21.modules.impl.combat.KeepSprint;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.combat.KillSay;
import lf21.modules.impl.combat.LegitReach;
import lf21.modules.impl.combat.TeleportAura;
import lf21.modules.impl.exploit.Disabler;
import lf21.modules.impl.exploit.FastPlace;
import lf21.modules.impl.exploit.PingSpoof;
import lf21.modules.impl.misc.AimAssist;
import lf21.modules.impl.misc.AutoClicker;
import lf21.modules.impl.misc.AutoRod;
import lf21.modules.impl.misc.BlockHideSeekDetector;
import lf21.modules.impl.misc.IQBoost;
import lf21.modules.impl.misc.MurderDetector;
import lf21.modules.impl.misc.f;
import lf21.modules.impl.movement.Blink;
import lf21.modules.impl.movement.Clip;
import lf21.modules.impl.movement.FireballBlink;
import lf21.modules.impl.movement.Fly;
import lf21.modules.impl.movement.Freeze;
import lf21.modules.impl.movement.GuiMove;
import lf21.modules.impl.movement.HighJump;
import lf21.modules.impl.movement.MovementFix;
import lf21.modules.impl.movement.NoClip;
import lf21.modules.impl.movement.NoDelay;
import lf21.modules.impl.movement.NoSlow;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.movement.TargetStrafe;
import lf21.modules.impl.movement.Teleport;
import lf21.modules.impl.movement.Timer;
import lf21.modules.impl.movement.d;
import lf21.modules.impl.movement.e;
import lf21.modules.impl.movement.l;
import lf21.modules.impl.movement.m;
import lf21.modules.impl.player.AntiTNT;
import lf21.modules.impl.player.AntiVoid;
import lf21.modules.impl.player.AntiWeb;
import lf21.modules.impl.player.AutoChest;
import lf21.modules.impl.player.AutoMLG;
import lf21.modules.impl.player.AutoWeb;
import lf21.modules.impl.player.ChestStealer;
import lf21.modules.impl.player.Helper;
import lf21.modules.impl.player.InventoryManager;
import lf21.modules.impl.player.MidPearl;
import lf21.modules.impl.player.NoFall;
import lf21.modules.impl.player.NoGuiClose;
import lf21.modules.impl.player.ShiftDrag;
import lf21.modules.impl.player.Stuck;
import lf21.modules.impl.player.n;
import lf21.modules.impl.render.AspectRatio;
import lf21.modules.impl.render.BedESP;
import lf21.modules.impl.render.ClickGui;
import lf21.modules.impl.render.Compass;
import lf21.modules.impl.render.DamageGlow;
import lf21.modules.impl.render.ESP;
import lf21.modules.impl.render.FakeFPS;
import lf21.modules.impl.render.Freecam;
import lf21.modules.impl.render.FullBright;
import lf21.modules.impl.render.Interface;
import lf21.modules.impl.render.ItemTags;
import lf21.modules.impl.render.NameTags;
import lf21.modules.impl.render.NoAttackCooldown;
import lf21.modules.impl.render.NoBlinded;
import lf21.modules.impl.render.NoCameraClip;
import lf21.modules.impl.render.NoFov;
import lf21.modules.impl.render.NoHurtCam;
import lf21.modules.impl.render.OldHitting;
import lf21.modules.impl.render.Projectiles;
import lf21.modules.impl.render.ScoreBoard;
import lf21.modules.impl.render.XRay;
import lf21.modules.impl.render.i;
import lf21.modules.impl.world.AntiStaff;
import lf21.modules.impl.world.AutoPlay;
import lf21.modules.impl.world.AutoTools;
import lf21.modules.impl.world.BlockIn;
import lf21.modules.impl.world.Debugger;
import lf21.modules.impl.world.MusicBot;
import lf21.modules.impl.world.Nuker;
import lf21.modules.impl.world.Teams;
import org.apache.commons.lang3.StringUtils;

public class ModuleManager
extends ClientBase {
    private final Map<Class<? extends Module>, Module> a = new ConcurrentHashMap<Class<? extends Module>, Module>();
    private static final String b = ModuleManager.b(ModuleManager.a("-~:u5\u001e_\u0014a/"));

    public ModuleManager() {
        LF21Client.getInstance().getEventBus().register(this);
    }

    public void initModules() {
        this.a(new AntiBots());
        this.a(new AntiFireball());
        this.a(new AntiKB());
        this.a(new AutoOffHand());
        this.a(new AutoSoup());
        this.a(new AutoThrow());
        this.a(new lf21.modules.impl.combat.i());
        this.a(new Backtrack());
        this.a(new BowAim());
        this.a(new Critical());
        this.a(new CrystalAura());
        this.a(new FakeLag());
        this.a(new Displace());
        this.a(new KillAura());
        KillSay killSay = new KillSay();
        this.a(killSay);
        killSay.setEnabled(true);
        this.a(new KeepSprint());
        this.a(new LegitReach());
        this.a(new TeleportAura());
        this.a(new Disabler());
        this.a(new FastPlace());
        this.a(new PingSpoof());
        this.a(new AimAssist());
        this.a(new AutoClicker());
        this.a(new AutoRod());
        this.a(new f());
        this.a(new MurderDetector());
        this.a(new BlockHideSeekDetector());
        this.a(new lf21.modules.impl.misc.d());
        this.a(new IQBoost());
        this.a(new d());
        this.a(new MovementFix());
        this.a(new NoSlow());
        this.a(new e());
        this.a(new Blink());
        this.a(new Clip());
        this.a(new FireballBlink());
        this.a(new Fly());
        this.a(new Freeze());
        this.a(new GuiMove());
        this.a(new HighJump());
        this.a(new NoDelay());
        this.a(new l());
        this.a(new NoClip());
        this.a(new Scaffold());
        LF21Client.getInstance().getHudManager().b(Scaffold.INSTANCE);
        this.a(new m());
        this.a(new TargetStrafe());
        this.a(new Teleport());
        this.a(new Timer());
        this.a(new AntiTNT());
        this.a(new AntiVoid());
        this.a(new AntiWeb());
        this.a(new AutoMLG());
        this.a(new AutoWeb());
        this.a(new AutoChest());
        this.a(new ChestStealer());
        this.a(new n());
        this.a(new Helper());
        this.a(new InventoryManager());
        this.a(new MidPearl());
        this.a(new NoFall());
        this.a(new NoGuiClose());
        this.a(new Stuck());
        this.a(new ShiftDrag());
        this.a(new AspectRatio());
        this.a(new lf21.modules.impl.render.e());
        this.a(new BedESP());
        this.a(new ClickGui());
        this.a(new Compass());
        this.a(new DamageGlow());
        this.a(new i());
        this.a(new ESP());
        this.a(new FullBright());
        this.a(new Freecam());
        this.a(new FakeFPS());
        this.a(new Interface());
        this.a(new ItemTags());
        this.a(new NameTags());
        this.a(new NoFov());
        this.a(new NoCameraClip());
        this.a(new NoHurtCam());
        this.a(new NoBlinded());
        this.a(new NoAttackCooldown());
        this.a(new OldHitting());
        this.a(new Projectiles());
        ScoreBoard scoreBoard = new ScoreBoard();
        this.a(scoreBoard);
        LF21Client.getInstance().getHudManager().b(scoreBoard);
        this.a(new XRay());
        this.a(new AntiStaff());
        this.a(new AutoPlay());
        this.a(new AutoTools());
        this.a(new BlockIn());
        this.a(new Nuker());
        MusicBot musicBot = new MusicBot();
        this.a(musicBot);
        LF21Client.getInstance().getHudManager().b(musicBot);
        this.a(new Debugger());
        this.a(new Teams());
        Module.d();
    }

    public void a(Module module) {
        block4: {
            if (module != null && !b.equalsIgnoreCase(module.getName())) break block4;
            return;
        }
        this.a.put(module.getClass(), module);
        module.b();
    }

    public Module getModule(String string) {
        Module module = null;
        for (Module module2 : this.a.values()) {
            if (!StringUtils.replace((String)module2.getName(), (String)" ", (String)"").equalsIgnoreCase(string)) {
                continue;
            }
            module = module2;
        }
        if (module == null) {
            throw new ModuleNotFoundException();
        }
        return module;
    }

    public <T extends Module> T getModule(Class<T> clazz) {
        Module module = (Module)clazz.cast(this.a.get(clazz));
        if (module == null) {
            throw new ModuleNotFoundException();
        }
        return (T)module;
    }

    public List<Module> getModules() {
        return this.a.values().stream().toList();
    }

    public List<Module> a(Category category) {
        List<Module> list = this.a.values().stream().filter(module -> module.f().equals((Object)category)).collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }

    @EventTarget
    public void a(KeyEvent keyEvent) {
        if (ModuleManager.mc.screen == null) {
            for (Module module : this.a.values()) {
                if (module.getKey() == 0 || module.getKey() != keyEvent.getKeyCode()) continue;
                if (!keyEvent.isPressed()) continue;
                module.toggle();
            }
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x13);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 125;
                    case 1 -> 18;
                    case 2 -> 91;
                    case 3 -> 12;
                    case 4 -> 80;
                    case 5 -> 108;
                    default -> 19;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

