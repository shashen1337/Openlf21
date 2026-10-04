/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import java.util.Arrays;
import java.util.Optional;
import lf21.event.EventTarget;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.RotationEvent;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.a.b;
import lf21.modules.impl.movement.FireballBlink;
import lf21.modules.impl.movement.HighJump;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.rotation.a;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.world.phys.Vec3;

public class AntiKB
extends Module {
    public final BooleanSetting N;
    public final BooleanSetting I;
    public final ModeSetting J;
    public final BooleanSetting followDirection;
    private boolean V;
    private Vec3 R;
    public final NumberSetting H;
    private int S;
    public final NumberSetting B;
    public final BooleanSetting movementOverride;
    public final NumberSetting vertical;
    public final BooleanSetting rotate;
    public final NumberSetting horizontal;
    public final BooleanSetting P;
    public final BooleanSetting F;
    public final BooleanSetting autoAttackCount;
    private boolean T;
    public final NumberSetting K;
    public final BooleanSetting G;
    public final BooleanSetting autoJump;
    private static final String[] W;
    public final NumberSetting D;
    public final BooleanSetting M;
    public static a k;
    public final BooleanSetting O;
    public final BooleanSetting sprintStateCheck;
    public final BooleanSetting jumpReset;
    public final NumberSetting rotateTicks;
    private int U;
    public final NumberSetting E;
    public static AntiKB j;
    public final BooleanSetting instantAttack;
    public final NumberSetting L;
    public static ModeSetting l;
    public final NumberSetting A;
    public final BooleanSetting tryAttack;
    public final NumberSetting C;
    public final BooleanSetting Q;
    public final BooleanSetting onSwing;
    public final NumberSetting attackAmount;

    public AntiKB() {
        String[] stringArray = W;
        super("AntiKB", Category.COMBAT);
        this.autoJump = new BooleanSetting("Auto Jump", false, () -> {
            if (!this.i()) return false;
            String[] stringArray = W;
            if (l.is("Grim Full")) return true;
            if (!l.is("Grim Fast")) return false;
            return true;
        });
        this.rotate = new BooleanSetting("Rotate", false, () -> {
            if (!this.i()) return false;
            String[] stringArray = W;
            if (l.is("Jump Reset")) return true;
            if (!l.is("Mix")) return false;
            return true;
        });
        this.tryAttack = new BooleanSetting("Try Attack", false, () -> this.i() && l.is("Mix"));
        this.movementOverride = new BooleanSetting("Movement Override", false, () -> this.i() && l.is("Mix"));
        this.followDirection = new BooleanSetting("Follow Direction", false, () -> this.i() && l.is("Jump Reset"));
        this.rotateTicks = new NumberSetting("Rotate Ticks", 12, 3, 20, 1, () -> this.i() && l.is("Jump Reset") && ((Boolean)this.rotate.getValue() != false || (Boolean)this.followDirection.getValue() != false));
        this.autoAttackCount = new BooleanSetting("Auto Attack Count", true, () -> false);
        this.attackAmount = new NumberSetting("Attack amount", 5.0, 1.0, 20.0, 1, () -> false);
        this.instantAttack = new BooleanSetting("Instant Attack", false, () -> false);
        this.sprintStateCheck = new BooleanSetting("Sprint state check", true, () -> false);
        this.jumpReset = new BooleanSetting("Jump Reset", false, () -> false);
        this.onSwing = new BooleanSetting("On Swing", false, this::a);
        this.horizontal = new NumberSetting("Horizontal", 0.0, 0.0, 100.0, 1.0, () -> {
            if (!this.i()) return false;
            String[] stringArray = W;
            if (l.is("Standard")) return true;
            if (!l.is("Buffer Abuse")) return false;
            return true;
        });
        this.vertical = new NumberSetting("Vertical", 0.0, 0.0, 100.0, 1.0, () -> {
            if (!this.i()) return false;
            String[] stringArray = W;
            if (l.is("Standard")) return true;
            if (!l.is("Buffer Abuse")) return false;
            return true;
        });
        this.A = new NumberSetting("Delay (ticks)", 5.0, 0.0, 40.0, 1.0, () -> this.i() && l.is("Delay"));
        this.B = new NumberSetting("Chance", 100.0, 0.0, 100.0, 1.0, () -> {
            if (!this.i()) return false;
            String[] stringArray = W;
            if (l.is("Legit/Jump Reset")) return true;
            if (!l.is("Polar")) return false;
            return true;
        });
        this.C = new NumberSetting("Ground Delay", 1.0, 0.0, 20.0, 1.0, () -> this.i() && l.is("Ground"));
        this.D = new NumberSetting("Tick Velocity", 1.0, 1.0, 6.0, 1.0, () -> this.i() && l.is("Tick"));
        this.E = new NumberSetting("Bounce Tick", 0.0, 0.0, 6.0, 1.0, () -> this.i() && l.is("Bounce"));
        this.F = new BooleanSetting("Bounce Horizontal", true, () -> this.i() && l.is("Bounce"));
        this.G = new BooleanSetting("Bounce Vertical", false, () -> this.i() && l.is("Bounce"));
        this.H = new NumberSetting("Buffer", 2.0, 0.0, 10.0, 1.0, () -> this.i() && l.is("Buffer Abuse"));
        this.I = new BooleanSetting("Explosion Ignore", false, () -> this.i() && l.is("Standard"));
        this.J = new ModeSetting("Air Mode", "Ground", "Air").withDefault("Ground").withVisibility(this::h);
        this.K = new NumberSetting("Attack Reduce", 2.0, 0.0, 8.0, 1.0, this::h);
        this.L = new NumberSetting("Timeout", 5.0, 5.0, 120.0, 1.0, this::h);
        this.M = new BooleanSetting("Movement Fix", true, this::h);
        this.N = new BooleanSetting("Prediction", true, this::h);
        this.O = new BooleanSetting("Debug", true, this::h);
        this.P = new BooleanSetting("Rotate", true, this::h);
        this.Q = new BooleanSetting("Always show ALink", false, this::h);
        j = this;
        b.a();
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(gameTickEvent);
    }

    @Override
    public void onEnable() {
        Optional<b> optional;
        k = null;
        this.R = null;
        this.S = 0;
        this.T = false;
        this.U = 0;
        if (!Arrays.stream((Object[])l.getModes()).toList().contains(l.getValue())) {
            l.withDefault("AttackReduce");
        }
        if ((optional = this.k()).isEmpty()) {
            return;
        }
        optional.get().b();
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String c(char[] cArray) {
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
                    case 0 -> 80;
                    case 1 -> 100;
                    case 2 -> 17;
                    case 3 -> 67;
                    case 4 -> 31;
                    case 5 -> 15;
                    default -> 57;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    public void onDisable() {
        k = null;
        this.R = null;
        this.S = 0;
        this.T = false;
        this.U = 0;
        Optional<b> optional = this.k();
        if (optional.isEmpty()) {
            return;
        }
        optional.get().c();
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (this.V) {
            return;
        }
        if (this.a()) {
            if (AntiKB.mc.player == null) {
                return;
            }
            if (this.R != null && --this.S <= 0) {
                AntiKB.mc.player.setDeltaMovement(this.R);
                this.R = null;
            }
            if (this.T && AntiKB.mc.player.onGround()) {
                AntiKB.mc.player.jumpFromGround();
                this.T = false;
            }
            String[] stringArray = W;
            if (l.is("Tick") && AntiKB.mc.player.hurtTime == 10 - ((Number)this.D.getValue()).intValue()) {
                AntiKB.mc.player.setDeltaMovement(0.0, AntiKB.mc.player.getDeltaMovement().y, 0.0);
            }
            stringArray = W;
            if (l.is("Ground") && AntiKB.mc.player.hurtTime == ((Number)this.C.getValue()).intValue()) {
                AntiKB.mc.player.setOnGround(true);
            }
            if (l.is("Bounce") && AntiKB.mc.player.hurtTime == 9 - ((Number)this.E.getValue()).intValue()) {
                Vec3 vec3 = AntiKB.mc.player.getDeltaMovement();
                double d2 = (Boolean)this.F.getValue() != false ? -vec3.x : vec3.x;
                double d3 = (Boolean)this.G.getValue() != false ? -vec3.y : vec3.y;
                double d4 = (Boolean)this.F.getValue() != false ? -vec3.z : vec3.z;
                AntiKB.mc.player.setDeltaMovement(d2, d3, d4);
            }
            return;
        }
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(tickEvent);
    }

    public void a(boolean bl) {
        if (this.V == bl) {
            return;
        }
        if (bl) {
            if (this.isEnabled()) {
                this.onDisable();
            }
            this.V = true;
            return;
        }
        this.V = false;
        if (this.isEnabled()) {
            this.onEnable();
        }
    }

    private void b(c c2) {
        double d2;
        double d3;
        ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket;
        Vec3 vec3;
        if (AntiKB.mc.player == null || !((vec3 = c2.a()) instanceof ClientboundSetEntityMotionPacket) || (clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)vec3).getId() != AntiKB.mc.player.getId()) {
            return;
        }
        if (((Boolean)this.onSwing.getValue()).booleanValue() && !AntiKB.mc.player.swinging) {
            return;
        }
        vec3 = new Vec3((double)clientboundSetEntityMotionPacket.getXa() / 8000.0, (double)clientboundSetEntityMotionPacket.getYa() / 8000.0, (double)clientboundSetEntityMotionPacket.getZa() / 8000.0);
        String[] stringArray = W;
        if (l.is("Legit/Jump Reset") || l.is("Polar")) {
            if (Math.random() * 100.0 <= ((Number)this.B.getValue()).doubleValue()) {
                this.T = true;
            }
            return;
        }
        stringArray = W;
        if (l.is("Ground") || l.is("Tick") || l.is("Bounce")) {
            return;
        }
        stringArray = W;
        if (l.is("Delay")) {
            c2.a(true);
            this.R = vec3;
            this.S = ((Number)this.A.getValue()).intValue();
            return;
        }
        stringArray = W;
        if (l.is("Buffer Abuse") && this.U++ >= ((Number)this.H.getValue()).intValue()) {
            this.U = 0;
            return;
        }
        c2.a(true);
        stringArray = W;
        if (l.is("Standard") || l.is("Buffer Abuse")) {
            d3 = ((Number)this.horizontal.getValue()).doubleValue() / 100.0;
            d2 = ((Number)this.vertical.getValue()).doubleValue() / 100.0;
        } else {
            stringArray = W;
            if (l.is("Grim Reduce") || l.is("Watchdog Reduce")) {
                d3 = 0.6;
                d2 = 1.0;
            } else {
                d3 = 0.0;
                d2 = 0.0;
            }
        }
        AntiKB.mc.player.setDeltaMovement(vec3.x * d3, vec3.y * d2, vec3.z * d3);
    }

    public boolean h() {
        return l != null && l.is("AttackReduce");
    }

    @EventTarget
    public void a(DisconnectEvent disconnectEvent) {
        Optional<b> optional = this.k();
        if (optional.isEmpty()) {
            return;
        }
        optional.get().a(disconnectEvent);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[74];
                var3_1 = 0;
                var2_2 = "\u0002\u000be\"kj\u0003\u001d\ri\u0006\u0012\u000bd-|j\r\u0011\u0010e\"|d\u0019\u0002\u0001u6|j\u0006\u0017\u0016~6qk\u0011\u0011\bf\"f|\u0019#\f~4?Nu9\nz\b\u0006\u0001c7vlX<\r\u0005\nx5z}J?\u0007c\"y{\u0013\u0007\u0005e wkV7DA1zkP3\u0010x,q\u0010\u001c\u0001v*k s%\tacMjJ5\u0010\u0010\u001c\u0001v*k s%\tacMjJ5\u0010\f\u0017\u0016~6qk\u0019\u0014\u0001}\"f\b\u0003\u0010p-{nK4\u0011\u0012\u000bd-|j\u0019\u0018\u000bc*e`W$\u0005}\u0006\u001d\u0005e1vw\n\u0018\u000bc*e`W$\u0005}\u0006\u0011\ne*TM\u0005\u0014\u0001}\"f\u0005\u0014\u0001s6x\n\u001a\u0011|3?]\\#\u0001e\u0004\u0004\rr(\n\u0000\u0016t'vlM9\u000b\u007f\u0004\u001d\u000bu&\f\u0011\u0010e\"|dk5\u0000d z\t\u0017\u0016x.?IX#\u0010\t\u0011\u0011e,?EL=\u0014\b\u0003\u0010p-{nK4\u0006\u0012\u0011w%z}\u0005\u001b\u0005c+j\u000e\u0019\nb7~aMp%e7~lR\b\u001f\n1\u0010hfW7\u000f\u0007\u0005e wkV7DC&{zZ5\u0005\u0000\u000b}\"m\b\u0007\u0005e wkV7\n\u001a\u0011|3?]\\#\u0001e\u0011\u0011\u0011e,?NM$\u0005r(?LV%\ne\b\u0011\rccR`]5\u0006\u0019\ne\"ij\u0006\u0017\u0016~6qk\f\u0002\u000be\"kj\u0019\u0004\rr(l\u0007\u0015\u0000_\"ijW\r\u0011\u0010e\"|d\u00191\t~6q{\r\u0004\rr(?Y\\<\u000br*kv\u0006\u0002\u000be\"kj\u000f\u0012\u000bd-|j\u0019\u0006\u0001c7vlX<\r\u0014\u0001}\"f/\u0011$\rr(l&\u0004\u0004\rr(\f\u0012\u0011w%z}\u0019\u0011\u0006d0z\n\u0004\u0016hc^{M1\u0007z\u0007\u0004\r|&pzM\u0005\u0014\u0001}\"f\u0011\u001d\u000bg&rjW$D^5z}K9\u0000t\u0003\u001d\ri\u0003\u0011\rc\f\u0011\u0010e\"|dk5\u0000d z\u000b\u0017\u0016x.?]\\4\u0011r&\u0006\u0006\u0011} ~a\t\u0017\u0016x.?[\\#\u0010\u0004\u0017\u0016x.\u0010\u0015\u001ca/p|P?\n1\nxaV\"\u0001\t\u0017\u0016x.?IL<\b\u0006\u0012\u000bd-|j\u0005\u0000\u000b}\"m\u000b\u0017\u0016x.?]\\4\u0011r&\u0003\u001d)R\f\u001d\u000bg&rjW$DW*g\u000f\u0007\u0005e wkV7DC&{zZ5\f\u0012\u0011w%z}\u0019\u0011\u0006d0z\u0010\u0016\u000b}/px\u0019\u0014\rc&|{P?\n\u000b\u0012\u000bd-|j\u0019\u0004\rr(\u0006\u0017\u0016x.?=\u0006\u0013\fp-|j";
                var4_3 = "\u0002\u000be\"kj\u0003\u001d\ri\u0006\u0012\u000bd-|j\r\u0011\u0010e\"|d\u0019\u0002\u0001u6|j\u0006\u0017\u0016~6qk\u0011\u0011\bf\"f|\u0019#\f~4?Nu9\nz\b\u0006\u0001c7vlX<\r\u0005\nx5z}J?\u0007c\"y{\u0013\u0007\u0005e wkV7DA1zkP3\u0010x,q\u0010\u001c\u0001v*k s%\tacMjJ5\u0010\u0010\u001c\u0001v*k s%\tacMjJ5\u0010\f\u0017\u0016~6qk\u0019\u0014\u0001}\"f\b\u0003\u0010p-{nK4\u0011\u0012\u000bd-|j\u0019\u0018\u000bc*e`W$\u0005}\u0006\u001d\u0005e1vw\n\u0018\u000bc*e`W$\u0005}\u0006\u0011\ne*TM\u0005\u0014\u0001}\"f\u0005\u0014\u0001s6x\n\u001a\u0011|3?]\\#\u0001e\u0004\u0004\rr(\n\u0000\u0016t'vlM9\u000b\u007f\u0004\u001d\u000bu&\f\u0011\u0010e\"|dk5\u0000d z\t\u0017\u0016x.?IX#\u0010\t\u0011\u0011e,?EL=\u0014\b\u0003\u0010p-{nK4\u0006\u0012\u0011w%z}\u0005\u001b\u0005c+j\u000e\u0019\nb7~aMp%e7~lR\b\u001f\n1\u0010hfW7\u000f\u0007\u0005e wkV7DC&{zZ5\u0005\u0000\u000b}\"m\b\u0007\u0005e wkV7\n\u001a\u0011|3?]\\#\u0001e\u0011\u0011\u0011e,?NM$\u0005r(?LV%\ne\b\u0011\rccR`]5\u0006\u0019\ne\"ij\u0006\u0017\u0016~6qk\f\u0002\u000be\"kj\u0019\u0004\rr(l\u0007\u0015\u0000_\"ijW\r\u0011\u0010e\"|d\u00191\t~6q{\r\u0004\rr(?Y\\<\u000br*kv\u0006\u0002\u000be\"kj\u000f\u0012\u000bd-|j\u0019\u0006\u0001c7vlX<\r\u0014\u0001}\"f/\u0011$\rr(l&\u0004\u0004\rr(\f\u0012\u0011w%z}\u0019\u0011\u0006d0z\n\u0004\u0016hc^{M1\u0007z\u0007\u0004\r|&pzM\u0005\u0014\u0001}\"f\u0011\u001d\u000bg&rjW$D^5z}K9\u0000t\u0003\u001d\ri\u0003\u0011\rc\f\u0011\u0010e\"|dk5\u0000d z\u000b\u0017\u0016x.?]\\4\u0011r&\u0006\u0006\u0011} ~a\t\u0017\u0016x.?[\\#\u0010\u0004\u0017\u0016x.\u0010\u0015\u001ca/p|P?\n1\nxaV\"\u0001\t\u0017\u0016x.?IL<\b\u0006\u0012\u000bd-|j\u0005\u0000\u000b}\"m\u000b\u0017\u0016x.?]\\4\u0011r&\u0003\u001d)R\f\u001d\u000bg&rjW$DW*g\u000f\u0007\u0005e wkV7DC&{zZ5\f\u0012\u0011w%z}\u0019\u0011\u0006d0z\u0010\u0016\u000b}/px\u0019\u0014\rc&|{P?\n\u000b\u0012\u000bd-|j\u0019\u0004\rr(\u0006\u0017\u0016x.?=\u0006\u0013\fp-|j".length();
                var1_4 = 6;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = AntiKB.c(AntiKB.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0003\u0014c*q{\u0019#\u0010p7z/Z8\u0001r(\u0003\u0011%R";
                    var4_3 = "\u0003\u0014c*q{\u0019#\u0010p7z/Z8\u0001r(\u0003\u0011%R".length();
                    var1_4 = 18;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = AntiKB.c(AntiKB.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    break block10;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl37:
                // 1 sources

                ** continue;
            }
        }
        AntiKB.W = var5;
        var0_6 = AntiKB.W;
        AntiKB.l = new ModeSetting("Mode", new String[]{"AttackReduce", "Jump Reset", "Mix", "Standard", "Buffer Abuse", "Delay", "Legit/Jump Reset", "Polar", "Ground", "Intave", "Matrix", "AAC", "Vulcan", "Tick", "Bounce", "Karhu", "MMC", "Universocraft", "Grim Reduce", "Grim", "Grim 2", "Watchdog", "Watchdog Prediction", "Watchdog Reduce", "Grim Test"}).withDefault("AttackReduce");
    }

    public boolean i() {
        return !this.h();
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(motionEvent);
    }

    @EventTarget
    public void a(PreMotionEvent preMotionEvent) {
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(preMotionEvent);
    }

    public boolean j() {
        return this.V;
    }

    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(render2DEvent);
    }

    @EventTarget
    public void a(RotationEvent rotationEvent) {
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(rotationEvent);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean a() {
        if (this.h()) return false;
        if (l == null) return false;
        String[] stringArray = W;
        if (l.is("Jump Reset")) return false;
        if (l.is("Mix")) return false;
        return true;
    }

    private Optional<b> k() {
        if (this.V) {
            return Optional.empty();
        }
        return b.a(this.h() ? "EdNaven" : (String)l.getValue());
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(renderEvent);
    }

    @EventTarget(a=3)
    public void a(StrafeEvent strafeEvent) {
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(strafeEvent);
    }

    @EventTarget(a=1)
    public void a(c c2) {
        if (this.V) {
            return;
        }
        if (this.a()) {
            this.b(c2);
            return;
        }
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(c2);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x39);
        }
        return cArray;
    }

    @EventTarget
    public void a(SprintEvent sprintEvent) {
        Optional<b> optional = this.k();
        if (FireballBlink.j.isEnabled() || HighJump.j.isEnabled() || optional.isEmpty()) {
            return;
        }
        optional.get().a(sprintEvent);
    }
}

