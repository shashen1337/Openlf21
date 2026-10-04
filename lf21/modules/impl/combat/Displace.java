/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import lf21.ClientBase;
import lf21.event.EventTarget;
import lf21.event.impl.AttackEntityEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.PositionPacketSentEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.combat.c;
import lf21.modules.impl.combat.v;
import lf21.modules.impl.combat.x;
import lf21.modules.impl.world.Teams;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.f;
import lf21.utils.game.g;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class Displace
extends Module {
    private static final int C;
    public static Displace INSTANCE;
    public final BooleanSetting A;
    public final NumberSetting delay;
    public final BooleanSetting blink;
    private boolean O;
    private long V;
    public final BooleanSetting peaceful;
    public final BooleanSetting ignoreInvisible;
    private a T;
    private boolean P;
    public final NumberSetting yawOffset;
    private x R;
    public final ModeSetting direction;
    private int U;
    public final BooleanSetting hasKnockback;
    public final ModeSetting dynamicAngle;
    public final BooleanSetting mobs;
    public final BooleanSetting ignoreBehindWalls;
    public final ModeSetting mode;
    public final BooleanSetting ignoreTeammates;
    private float Q;
    private Entity J;
    private static final int D;
    private static final double F;
    private static final int G;
    public final NumberSetting legalTurnSpeed;
    public final BooleanSetting players;
    private a S;
    public final BooleanSetting attackInfo;
    private boolean M;
    public final BooleanSetting findVoid;
    private c I;
    private int L;
    private static final int E;
    private final Queue<Packet<?>> H;
    public final BooleanSetting neutral;
    private boolean N;
    private a K;
    private static final String[] W;
    public final MultiSelectSetting B;

    private void b(a a2) {
        if (Displace.mc.player == null || a2 == null) {
            return;
        }
        float f2 = this.a(Displace.mc.player.getYRot(), a2.e());
        a a3 = new a(f2, Mth.clamp((float)a2.f(), (float)-90.0f, (float)90.0f)).a(Float.valueOf(((Double)Displace.mc.options.sensitivity().get()).floatValue()));
        Displace.mc.player.setYRot(a3.e());
        Displace.mc.player.setXRot(a3.f());
        Displace.mc.player.yHeadRot = a3.e();
        this.K = a3;
    }

    private void p() {
        double d2 = ((Number)this.delay.getValue()).doubleValue();
        if (d2 > 10.0) {
            d2 = Math.max(0.1, (double)Math.round(d2 / 100.0) / 10.0);
        }
        d2 = Math.max(0.0, Math.min(10.0, (double)Math.round(d2 * 10.0) / 10.0));
        this.delay.setValue(d2);
    }

    public boolean interceptVanillaAttack() {
        if (this.I != c.IDLE) {
            return true;
        }
        HitResult hitResult = Displace.mc.hitResult;
        if (!(hitResult instanceof EntityHitResult)) {
            return false;
        }
        EntityHitResult entityHitResult = (EntityHitResult)hitResult;
        return this.a(entityHitResult.getEntity());
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x5F);
        }
        return cArray;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private void n() {
        block12: {
            var1_1 = this.R;
            if (var1_1 == null || Displace.mc.level == null) {
                return;
            }
            var2_2 = this.L - var1_1.i;
            var3_3 = Displace.mc.level.getEntity(var1_1.a);
            if (!(var3_3 instanceof LivingEntity)) break block12;
            var5_4 /* !! */  = (LivingEntity)var3_3;
            if (var5_4 /* !! */ .hurtTime > var1_1.f || var5_4 /* !! */ .getHealth() < var1_1.g) ** GOTO lbl-1000
        }
        if (var3_3 != null && !var3_3.isAlive()) lbl-1000:
        // 2 sources

        {
            v0 = true;
        } else {
            v0 = var4_6 = false;
        }
        if (var3_3 != null && var4_6 && var2_2 >= 1 && (var5_5 = var3_3.getX() - var1_1.c) * var5_5 + (var7_7 = var3_3.getZ() - var1_1.d) * var7_7 >= 0.001225) {
            var9_8 = (float)Math.toDegrees(Math.atan2(var7_7, var5_5));
            var10_9 = Mth.wrapDegrees((float)(var9_8 - var1_1.e));
            var11_10 = Math.signum(var10_9) == Math.signum(var1_1.h) || Math.abs(var1_1.h) < 1.0f;
            var12_11 = var11_10 != false && Math.abs(var10_9) >= 5.0f;
            this.R = null;
            if (var12_11) {
                this.q();
            }
            if (((Boolean)this.attackInfo.getValue()).booleanValue()) {
                if (var12_11) {
                    var15_12 = Displace.W;
                    v1 = "\u00a7aSuccess";
                } else {
                    var15_12 = Displace.W;
                    v1 = var13_14 = "\u00a7eNo shift";
                }
                var14_15 = var10_9 < 0.0f ? "L" : (var10_9 > 0.0f ? "R" : "0");
                var15_12 = Displace.W;
                ChatUtil.print(false, String.format("\u00a7b[Displace] %s \u00a77| \u00a7f%s \u00a77| \u00a7f%s %.1f\u00b0 \u00a77| \u00a7fSet %.1f\u00b0", new Object[]{var13_14, var1_1.b, var14_15, Float.valueOf(Math.abs(var10_9)), Float.valueOf(Math.abs(var1_1.h))}));
            }
            return;
        }
        if (var2_2 >= 8) {
            if (((Boolean)this.attackInfo.getValue()).booleanValue()) {
                if (var4_6) {
                    var15_13 = Displace.W;
                    v2 = "\u00a7eHit \u00b7 no sample";
                } else {
                    var15_13 = Displace.W;
                    v2 = "\u00a7cMiss";
                }
                var5_4 /* !! */  = v2;
                ChatUtil.print(false, String.format("\u00a7b[Displace] %s \u00a77| \u00a7f%s \u00a77| \u00a7fSet %.1f\u00b0", new Object[]{var5_4 /* !! */ , var1_1.b, Float.valueOf(Math.abs(var1_1.h))}));
            }
            this.R = null;
        }
    }

    private boolean b(Packet<?> packet) {
        return packet != null && packet.getClass().getSimpleName().startsWith("Serverbound");
    }

    public Displace() {
        String[] stringArray = W;
        super("Displace", Category.COMBAT);
        this.mode = new ModeSetting("Mode", "Legal", "Packet").withDefault("Legal");
        this.dynamicAngle = new ModeSetting("Dynamic Angle", "Static", "Dynamic").withDefault("Static");
        this.yawOffset = new NumberSetting("Yaw Offset", 90.0, 0.0, 180.0, 1.0, () -> this.dynamicAngle.is("Static"));
        this.delay = new NumberSetting("Delay", 0.0, 0.0, 10.0, 0.1);
        this.legalTurnSpeed = new NumberSetting("Legal Turn Speed", 45.0, 10.0, 90.0, 5.0, () -> this.mode.is("Legal"));
        this.direction = new ModeSetting("Direction", "Left", "Right", "Random").withDefault("Left");
        this.findVoid = new BooleanSetting("Find Void", false, () -> this.dynamicAngle.is("Static"));
        this.blink = new BooleanSetting("Blink", false);
        this.ignoreTeammates = new BooleanSetting("Ignore Teammates", true);
        this.hasKnockback = new BooleanSetting("Has Knockback", false);
        this.attackInfo = new BooleanSetting("Attack Info", true);
        this.players = new BooleanSetting("Players", true);
        this.mobs = new BooleanSetting("Mobs", false);
        this.peaceful = new BooleanSetting("Peaceful", false);
        this.neutral = new BooleanSetting("Neutral", false);
        this.ignoreInvisible = new BooleanSetting("Ignore Invisible", false);
        this.ignoreBehindWalls = new BooleanSetting("Ignore Behind Walls", false);
        this.A = new BooleanSetting("Limit To Items", false);
        this.B = new MultiSelectSetting("Allowed Items", "Swords", "Axes").withDefaults("Swords");
        this.H = new ConcurrentLinkedQueue();
        this.I = c.IDLE;
        INSTANCE = this;
    }

    private float a(float f2, float f3, float f4, boolean bl) {
        float f5 = bl ? Mth.wrapDegrees((float)(f3 - f2)) : f3 - f2;
        float f6 = Math.abs(f5);
        if (f6 < 0.75f) {
            return f2 + f5;
        }
        float f7 = Mth.clamp((float)(f6 * 0.78f), (float)4.0f, (float)f4);
        if (f7 >= f6) {
            return f2 + f5;
        }
        return f2 + Math.copySign(f7, f5);
    }

    private void t() {
        if (!this.H.isEmpty()) {
            this.s();
        }
        this.I = c.IDLE;
        this.J = null;
        this.K = null;
        this.M = false;
        this.N = false;
        this.O = false;
        this.S = null;
        this.T = null;
        this.U = 0;
    }

    @Override
    protected void onEnable() {
        this.t();
        this.L = 0;
        this.V = 0L;
        this.p();
    }

    private void s() {
        Packet<?> packet;
        if (mc.getConnection() == null) {
            this.H.clear();
            return;
        }
        while ((packet = this.H.poll()) != null) {
            Packet<?> packet2 = packet;
            PacketUtil.a.add(packet2);
            mc.getConnection().send(packet2);
        }
    }

    private boolean a(Packet<?> packet) {
        return packet.getClass().getName().contains("ServerboundInteractPacket");
    }

    private a a(Entity entity, a a2) {
        if (Displace.mc.player == null || entity == null || a2 == null) {
            return null;
        }
        a a3 = this.c(entity);
        if (a3 == null) {
            return null;
        }
        if (this.a(a2, entity)) {
            return a2;
        }
        if (!this.a(a3, entity)) {
            return null;
        }
        float f2 = Mth.wrapDegrees((float)(a3.e() - a2.e()));
        float f3 = a3.f() - a2.f();
        a a4 = a3;
        float f4 = 0.0f;
        float f5 = 1.0f;
        for (int i2 = 0; i2 < 12; ++i2) {
            float f6 = (f4 + f5) * 0.5f;
            a a5 = new a(a2.e() + f2 * f6, a2.f() + f3 * f6).a(Float.valueOf(((Double)Displace.mc.options.sensitivity().get()).floatValue()));
            if (this.a(a5, entity)) {
                a4 = a5;
                f5 = f6;
                continue;
            }
            f4 = f6;
        }
        return a4;
    }

    private float a(float f2, float f3) {
        return f2 + Mth.wrapDegrees((float)(f3 - f2));
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                Displace.G = 6;
                Displace.F = 0.5;
                Displace.E = 10;
                Displace.D = 12;
                Displace.C = 32;
                var5 = new String[45];
                var3_1 = 0;
                var2_2 = "\u0017)\u0007u)?\n\u001c)\u001e1\t496-\u001d\u000b\u0016-\u001bg# =*=\u0007u\u0013\f/\u0007~47\u007f\u0007-\u0001x(6\u007f\u0012)\u0005}5\u0006\u0016?\u0006c\"!\u000e\t!\u0004x2r\u000b*h e#?,\u000b\u0004<\u001dp%9\u007f\f&\u000f~\n\u00e2-'~f!7,.\u001d\b\u0015-\br#4*)\u0019\u0016-\u001bg# =*=\u0007u\u000f<+ :\br2\u0002>&#\fe(\u00e2*2U/!/))\nt\u001brz6h\u00ce&:r\u00f8#m\u001a1\u00e1e#e\u00ef\u000fB#&\u007f`fXw\u00f6\u0010\f/\u0007~47\u007f\u0011-\b|+3+ ;\u0005\u0001-\u0005p?\u0006\u00e2+$x5!\u0007\u000b-\u001ce433\u0004\u00040\fb\r\r)\u001a1\r<0&#\u000bp%9\u0007\u00011\u0007p+;<\r\u00011\u0007p+;<e\t\u0007v*7\u0007\u0015$\bh# ,\u0011\u00e2-!x2r\u00e8e&\u000615325$\f\u0006\u0016<\be/1\u0005\u0017!\u000ey2\u0010\f/\u0007~47\u007f\f&\u001fx5;=)-\t\u00e2):d%1:6;\u0006\u0015)\nz#&\t\u0003!\u0007uf\u00040,,\u0005\u0007$\u0000\u007f-\u0010\t-\u000ep*r\u000b0:\u00071\u0015\": ,\u0007\u00011\u0007p+;<\u0004\t-\u000fe\u0004\t-\u000fe7\u00e2*2U/!/))\nt\u001brz6h\u00ce&:r\u00f8#m\u001a1\u00e1e#e\u00ef\u000f45rzky\u000f\u00a1f\u00f5h9h\u00cew\u00157+emG  \u00e2\u0006\u0016<\be/1\u0005\t-\u000ep*\u0005\t-\u000ep*\u0004\u00040\fb\b\u0001!\u001aa*3< \u0005\u0017!\u000ey2\u0006\u0017)\u0007u)?\u0004\b'\u000bb\t\u0001!\u001bt%&6*&\u0006\u0016?\u0006c\"!";
                var4_3 = "\u0017)\u0007u)?\n\u001c)\u001e1\t496-\u001d\u000b\u0016-\u001bg# =*=\u0007u\u0013\f/\u0007~47\u007f\u0007-\u0001x(6\u007f\u0012)\u0005}5\u0006\u0016?\u0006c\"!\u000e\t!\u0004x2r\u000b*h e#?,\u000b\u0004<\u001dp%9\u007f\f&\u000f~\n\u00e2-'~f!7,.\u001d\b\u0015-\br#4*)\u0019\u0016-\u001bg# =*=\u0007u\u000f<+ :\br2\u0002>&#\fe(\u00e2*2U/!/))\nt\u001brz6h\u00ce&:r\u00f8#m\u001a1\u00e1e#e\u00ef\u000fB#&\u007f`fXw\u00f6\u0010\f/\u0007~47\u007f\u0011-\b|+3+ ;\u0005\u0001-\u0005p?\u0006\u00e2+$x5!\u0007\u000b-\u001ce433\u0004\u00040\fb\r\r)\u001a1\r<0&#\u000bp%9\u0007\u00011\u0007p+;<\r\u00011\u0007p+;<e\t\u0007v*7\u0007\u0015$\bh# ,\u0011\u00e2-!x2r\u00e8e&\u000615325$\f\u0006\u0016<\be/1\u0005\u0017!\u000ey2\u0010\f/\u0007~47\u007f\f&\u001fx5;=)-\t\u00e2):d%1:6;\u0006\u0015)\nz#&\t\u0003!\u0007uf\u00040,,\u0005\u0007$\u0000\u007f-\u0010\t-\u000ep*r\u000b0:\u00071\u0015\": ,\u0007\u00011\u0007p+;<\u0004\t-\u000fe\u0004\t-\u000fe7\u00e2*2U/!/))\nt\u001brz6h\u00ce&:r\u00f8#m\u001a1\u00e1e#e\u00ef\u000f45rzky\u000f\u00a1f\u00f5h9h\u00cew\u00157+emG  \u00e2\u0006\u0016<\be/1\u0005\t-\u000ep*\u0005\t-\u000ep*\u0004\u00040\fb\b\u0001!\u001aa*3< \u0005\u0017!\u000ey2\u0006\u0017)\u0007u)?\u0004\b'\u000bb\t\u0001!\u001bt%&6*&\u0006\u0016?\u0006c\"!".length();
                var1_4 = 6;
                var0_5 = -1;
lbl12:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl17:
                // 1 sources

                while (true) {
                    var5[var3_1++] = Displace.c(Displace.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\b'\rt\r\u0004$\u0005~17;e\u0001\u001dt+!";
                    var4_3 = "\b'\rt\r\u0004$\u0005~17;e\u0001\u001dt+!".length();
                    var1_4 = 4;
                    var0_5 = -1;
lbl26:
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
lbl31:
                // 1 sources

                while (true) {
                    var5[var3_1++] = Displace.c(Displace.a(v1));
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
lbl42:
                // 1 sources

                ** continue;
            }
        }
        Displace.W = var5;
    }

    private boolean a(a a2, Entity entity) {
        EntityHitResult entityHitResult;
        if (a2 == null || entity == null || Displace.mc.player == null || Displace.mc.gameMode == null) {
            return false;
        }
        double d2 = Displace.mc.player.getEntityReach();
        HitResult hitResult = f.a(a2, d2, 0.0f, (Entity)Displace.mc.player, entity, false);
        return hitResult instanceof EntityHitResult && (entityHitResult = (EntityHitResult)hitResult).getEntity() == entity;
    }

    private void r() {
        this.M = false;
        a a2 = this.S;
        if (a2 == null) {
            this.t();
            return;
        }
        if (this.mode.is("Legal")) {
            this.b(a2);
        } else {
            this.K = a2;
        }
        this.I = c.RESTORE_SENT_WAIT;
    }

    private a c(a a2) {
        float f2;
        Float f3;
        Float f4 = f3 = (Boolean)this.findVoid.getValue() != false || this.dynamicAngle.is("Dynamic") ? this.d(this.J) : null;
        if (f3 != null) {
            f2 = f3.floatValue();
        } else {
            float f5 = ((Number)this.yawOffset.getValue()).floatValue();
            f2 = a2.e() + (this.P ? -f5 : f5);
        }
        a a3 = this.c(this.J);
        float f6 = a3 != null ? a3.f() : Displace.mc.player.getXRot();
        float f7 = Mth.clamp((float)(Math.abs(Mth.wrapDegrees((float)(f2 - a2.e()))) / 90.0f), (float)0.0f, (float)2.0f) * 1.75f;
        float f8 = this.P ? f7 : -f7;
        f2 = this.a(a2.e(), f2);
        return new a(f2, Mth.clamp((float)(f6 += f8), (float)-90.0f, (float)90.0f)).a(Float.valueOf(((Double)Displace.mc.options.sensitivity().get()).floatValue()));
    }

    @EventTarget(a=0)
    public void a(AttackEntityEvent attackEntityEvent) {
        if (this.O) {
            return;
        }
        if (this.I != c.IDLE) {
            attackEntityEvent.a(true);
            return;
        }
        if (this.a(attackEntityEvent.a())) {
            attackEntityEvent.a(true);
        }
    }

    private boolean a(a a2) {
        if (Displace.mc.player == null || a2 == null) {
            return false;
        }
        float f2 = ((Number)this.legalTurnSpeed.getValue()).floatValue();
        float f3 = Mth.clamp((float)(f2 * 2.6f), (float)72.0f, (float)180.0f);
        float f4 = Mth.clamp((float)(f2 * 1.8f), (float)48.0f, (float)120.0f);
        float f5 = this.a(Displace.mc.player.getYRot(), a2.e(), f3, true);
        float f6 = this.a(Displace.mc.player.getXRot(), a2.f(), f4, false);
        a a3 = new a(f5, Mth.clamp((float)f6, (float)-90.0f, (float)90.0f)).a(Float.valueOf(((Double)Displace.mc.options.sensitivity().get()).floatValue()));
        Displace.mc.player.setYRot(a3.e());
        Displace.mc.player.setXRot(a3.f());
        Displace.mc.player.yHeadRot = a3.e();
        return Math.abs(Mth.wrapDegrees((float)(a2.e() - a3.e()))) < 0.75f && Math.abs(a2.f() - a3.f()) < 0.75f;
    }

    private boolean j() {
        KillAura killAura = KillAura.j;
        return killAura != null && killAura.isEnabled();
    }

    @EventTarget(a=0)
    public void a(PositionPacketSentEvent positionPacketSentEvent) {
        if (this.I == c.OUTWARD_SENT_WAIT) {
            this.I = c.RETURNING;
            return;
        }
        if (this.I == c.RESTORE_SENT_WAIT) {
            this.t();
        }
    }

    private a c(Entity entity) {
        if (Displace.mc.player == null || entity == null) {
            return null;
        }
        Vec3 vec3 = Displace.mc.player.getEyePosition();
        a a2 = g.d(vec3, g.a(vec3, entity.getBoundingBox()));
        a2.a(this.a(Displace.mc.player.getYRot(), a2.e()));
        return a2.a(Float.valueOf(((Double)Displace.mc.options.sensitivity().get()).floatValue()));
    }

    private Float d(Entity entity) {
        if (entity == null || Displace.mc.level == null || Displace.mc.player == null) {
            return null;
        }
        double d2 = Double.MAX_VALUE;
        double d3 = 0.0;
        double d4 = 0.0;
        for (int i2 = 1; i2 <= 12; ++i2) {
            double d5 = (double)i2 * 0.5;
            boolean bl = false;
            for (int i3 = 0; i3 < 32; ++i3) {
                double d6;
                double d7;
                double d8;
                double d9 = Math.PI * 2 * (double)i3 / 32.0;
                double d10 = entity.getX() + Math.cos(d9) * d5;
                double d11 = entity.getZ() + Math.sin(d9) * d5;
                if (!this.a(d10, entity.getY(), d11) || !((d8 = d5 * d5 * 1000.0 + (d7 = d10 - Displace.mc.player.getX()) * d7 + (d6 = d11 - Displace.mc.player.getZ()) * d6) < d2)) continue;
                d2 = d8;
                d3 = d10;
                d4 = d11;
                bl = true;
            }
            if (bl) break;
        }
        if (d2 == Double.MAX_VALUE) {
            return null;
        }
        double d12 = entity.getX() - Displace.mc.player.getX();
        double d13 = entity.getZ() - Displace.mc.player.getZ();
        double d14 = d3 - Displace.mc.player.getX();
        double d15 = d4 - Displace.mc.player.getZ();
        this.P = d12 * d15 - d13 * d14 < 0.0;
        return Float.valueOf((float)(Math.toDegrees(Math.atan2(d4 - entity.getZ(), d3 - entity.getX())) - 90.0));
    }

    public a a() {
        return this.I == c.IDLE ? null : this.K;
    }

    @EventTarget(a=0)
    public void a(PacketEvent packetEvent) {
        if (packetEvent.a() || packetEvent.isCancelled() || !this.b(packetEvent.c())) {
            return;
        }
        if (this.I != c.IDLE && this.M && !this.O && this.a(packetEvent.c())) {
            packetEvent.a(true);
            return;
        }
        if (!((Boolean)this.blink.getValue()).booleanValue() || this.I == c.IDLE || this.O) {
            return;
        }
        packetEvent.a(true);
        this.H.offer(packetEvent.c());
        if (this.a(packetEvent.c())) {
            this.s();
        }
    }

    private boolean a(Entity entity) {
        boolean bl;
        if (this.I != c.IDLE || !this.i() || !this.b(entity)) {
            return false;
        }
        boolean bl2 = bl = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.KNOCKBACK, (ItemStack)Displace.mc.player.getMainHandItem()) > 0;
        if (((Boolean)this.hasKnockback.getValue()).booleanValue() && !bl) {
            return false;
        }
        this.J = entity;
        this.M = true;
        this.N = false;
        this.O = false;
        this.Q = 0.0f;
        this.U = this.L;
        this.I = c.DISPLACING;
        String string = (String)this.direction.getValue();
        int n2 = -1;
        switch (string.hashCode()) {
            case 78959100: {
                String[] stringArray = W;
                if (!string.equals("Right")) break;
                n2 = 0;
                break;
            }
            case -1854418717: {
                if (!string.equals("Random")) break;
                n2 = 1;
            }
        }
        this.P = switch (n2) {
            case 0 -> false;
            case 1 -> ThreadLocalRandom.current().nextBoolean();
            default -> true;
        };
        this.K = this.l();
        this.S = new a(Displace.mc.player.getYRot(), Displace.mc.player.getXRot()).a(Float.valueOf(((Double)Displace.mc.options.sensitivity().get()).floatValue()));
        this.T = this.c(this.S);
        this.Q = this.T == null ? 0.0f : Mth.wrapDegrees((float)(this.T.e() - this.S.e()));
        this.H.clear();
        return true;
    }

    private boolean b(Entity entity) {
        if (entity == null || entity == Displace.mc.player || !entity.isAlive() || entity.isSpectator() || !(entity instanceof LivingEntity) || Displace.mc.player.distanceTo(entity) > 9.0f) {
            return false;
        }
        if (((Boolean)this.ignoreInvisible.getValue()).booleanValue() && entity.isInvisible()) {
            return false;
        }
        if (((Boolean)this.ignoreBehindWalls.getValue()).booleanValue() && !Displace.mc.player.hasLineOfSight(entity)) {
            return false;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (!((Boolean)this.players.getValue()).booleanValue()) {
                return false;
            }
            return (Boolean)this.ignoreTeammates.getValue() == false || !Teams.a((Entity)player);
        }
        if (entity instanceof Monster) {
            return (Boolean)this.mobs.getValue();
        }
        if (entity instanceof Animal) {
            return (Boolean)this.peaceful.getValue();
        }
        if (entity instanceof Mob) {
            return (Boolean)this.neutral.getValue();
        }
        return false;
    }

    private float a(float f2, float f3, a a2) {
        float f4 = Mth.wrapDegrees((float)(a2.e() - f2));
        float f5 = a2.f() - f3;
        return (float)Math.sqrt(f4 * f4 + f5 * f5);
    }

    private a l() {
        if (RotationHandler.c != null) {
            return RotationHandler.c.a();
        }
        return new a(Displace.mc.player.getYRot(), Displace.mc.player.getXRot());
    }

    private void q() {
        double d2 = ((Number)this.delay.getValue()).doubleValue();
        if (d2 <= 0.0) {
            return;
        }
        this.V = System.nanoTime() + (long)(d2 * 1.0E9);
        if (this.I != c.IDLE) {
            this.t();
        }
    }

    private boolean m() {
        if (!this.M || this.N || !this.b(this.J) || this.K == null || !this.a(this.K, this.J)) {
            this.M = false;
            return false;
        }
        LivingEntity livingEntity = (LivingEntity)this.J;
        this.R = new x(this.J.getId(), this.J.getName().getString(), this.J.getX(), this.J.getZ(), (float)Math.toDegrees(Math.atan2(this.J.getZ() - Displace.mc.player.getZ(), this.J.getX() - Displace.mc.player.getX())), livingEntity.hurtTime, livingEntity.getHealth(), this.Q, this.L);
        a a2 = this.T;
        if (a2 != null) {
            ClientBase.yaw = a2.e();
            RotationHandler.a(a2);
            RotationHandler.c = a2.a();
        }
        this.O = true;
        try {
            Displace.mc.gameMode.attack((Player)Displace.mc.player, this.J);
            Displace.mc.player.swing(InteractionHand.MAIN_HAND);
        }
        finally {
            this.O = false;
        }
        this.N = true;
        this.M = false;
        return true;
    }

    public boolean h() {
        return this.I != c.IDLE;
    }

    private boolean a(double d2, double d3, double d4) {
        int n2 = Mth.floor((double)d2);
        int n3 = Mth.floor((double)d4);
        int n4 = Mth.floor((double)d3) - 1;
        int n5 = Math.max(Displace.mc.level.getMinBuildHeight(), n4 - 10);
        for (int i2 = n4; i2 >= n5; --i2) {
            if (Displace.mc.level.isEmptyBlock(new BlockPos(n2, i2, n3))) continue;
            return false;
        }
        return true;
    }

    @Override
    protected void onDisable() {
        this.t();
        this.V = 0L;
    }

    private boolean o() {
        return this.V > System.nanoTime();
    }

    private boolean i() {
        return this.isEnabled() && Displace.mc.player != null && Displace.mc.level != null && Displace.mc.gameMode != null && Displace.mc.screen == null && !this.o() && !this.j() && this.k();
    }

    @EventTarget(a=3)
    public void a(TickEvent tickEvent) {
        ++this.L;
        this.n();
        if (this.I == c.IDLE) {
            return;
        }
        if (this.L - this.U > 6) {
            this.t();
            return;
        }
        if (this.j() || Displace.mc.player == null || Displace.mc.level == null || Displace.mc.screen != null || !this.b(this.J)) {
            this.t();
            return;
        }
        switch (v.a[this.I.ordinal()]) {
            case 1: {
                a a2 = this.T;
                if (a2 == null) {
                    this.t();
                    return;
                }
                String[] stringArray = W;
                if (this.mode.is("Legal")) {
                    this.b(a2);
                } else {
                    this.K = a2;
                }
                this.I = c.OUTWARD_SENT_WAIT;
                break;
            }
            case 2: {
                a a3 = this.a(this.J, this.K != null ? this.K : new a(Displace.mc.player.getYRot(), Displace.mc.player.getXRot()));
                if (a3 == null) {
                    this.r();
                    return;
                }
                if (this.mode.is("Legal")) {
                    this.b(a3);
                } else {
                    this.K = a3;
                }
                this.m();
                this.I = c.RESTORE_SENT_WAIT;
                break;
            }
            case 3: 
            case 4: {
                break;
            }
        }
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
                    case 0 -> 69;
                    case 1 -> 72;
                    case 2 -> 105;
                    case 3 -> 17;
                    case 4 -> 70;
                    case 5 -> 82;
                    default -> 95;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean k() {
        if (!((Boolean)this.A.getValue()).booleanValue()) {
            return true;
        }
        Item item = Displace.mc.player.getMainHandItem().getItem();
        String[] stringArray = W;
        return this.B.isSelected("Swords") && item instanceof SwordItem || this.B.isSelected("Axes") && item instanceof AxeItem;
    }
}

