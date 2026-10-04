/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.misc;

import java.util.List;
import lf21.event.EventTarget;
import lf21.event.impl.Render2DEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.g;
import lf21.utils.rotation.a;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class AimAssist
extends Module {
    private static final String[] v;
    private final BooleanSetting mouseDown;
    private final NumberSetting fov;
    private final NumberSetting randomYawOffset;
    private final NumberSetting range;
    private final NumberSetting smoothAmount;
    private final NumberSetting randomPitchOffset;
    private final NumberSetting adaptiveOffset;
    private Vec3 s;
    private Vec3 t;
    private final BooleanSetting adaptive;
    private boolean u;
    private final BooleanSetting breakBlock;

    public boolean a(LivingEntity livingEntity) {
        if (AimAssist.mc.player.distanceTo((Entity)livingEntity) > ((Number)this.range.getValue()).floatValue() || !KillAura.j.e((Entity)livingEntity)) {
            return false;
        }
        if (!AimAssist.mc.player.hasLineOfSight((Entity)livingEntity)) {
            return false;
        }
        return !livingEntity.isDeadOrDying() && !(livingEntity.getHealth() <= 0.0f);
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
                    case 0 -> 75;
                    case 1 -> 29;
                    case 2 -> 46;
                    case 3 -> 53;
                    case 4 -> 2;
                    case 5 -> 66;
                    default -> 107;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean a(a a2, Entity entity) {
        float f2 = AimAssist.a(entity.getYRot(), a2.e());
        return (double)Math.abs(f2) <= ((Number)this.fov.getValue()).doubleValue() / 2.0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[10];
                var3_1 = 0;
                var2_2 = "\rrX\u0011\u0019|@Qm/K\u0012|Y\u0015M$\r8xZ\n\u0006r[Fgb\u000f$j@\t\ntCtq1\u00028i\r\u0018pAZv*K*pA@l6\b\nyOEv+\u001d.\u0013\u0019|@Qm/K\u001btZVjb$-{]Pv\u0005\u0019|@Rg";
                var4_3 = "\rrX\u0011\u0019|@Qm/K\u0012|Y\u0015M$\r8xZ\n\u0006r[Fgb\u000f$j@\t\ntCtq1\u00028i\r\u0018pAZv*K*pA@l6\b\nyOEv+\u001d.\u0013\u0019|@Qm/K\u001btZVjb$-{]Pv\u0005\u0019|@Rg".length();
                var1_4 = 3;
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
                    var5[var3_1++] = AimAssist.c(AimAssist.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\nyOEv+\u001d.=aSd1\u000e?\u000b\toKTib)'rM^";
                    var4_3 = "\nyOEv+\u001d.=aSd1\u000e?\u000b\toKTib)'rM^".length();
                    var1_4 = 15;
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
                    var5[var3_1++] = AimAssist.c(AimAssist.a(v1));
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
        AimAssist.v = var5;
    }

    private void a(a a2) {
        a2.a(Float.valueOf((float)((Double)AimAssist.mc.options.sensitivity().get()).doubleValue()));
        float f2 = a2.e();
        float f3 = a2.f();
        AimAssist.mc.player.setXRot(f3);
        AimAssist.mc.player.setYRot(f2);
        AimAssist.mc.player.setXRot(Mth.clamp((float)AimAssist.mc.player.getXRot(), (float)-90.0f, (float)90.0f));
        AimAssist.mc.player.xRotO = f3;
        AimAssist.mc.player.yRotO = f2;
        AimAssist.mc.player.xRotO = Mth.clamp((float)AimAssist.mc.player.xRotO, (float)-90.0f, (float)90.0f);
    }

    public static float a(double d2, double d3) {
        return Float.parseFloat(Double.valueOf(Mth.wrapDegrees((double)(d2 - d3))).toString());
    }

    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        Object object;
        float f2;
        Player player3;
        if (AimAssist.mc.player == null || AimAssist.mc.level == null) {
            return;
        }
        double d2 = ((Number)this.range.getValue()).doubleValue();
        boolean bl = (Boolean)this.mouseDown.getValue();
        double d3 = ((Number)this.fov.getValue()).doubleValue();
        double d4 = ((Number)this.randomYawOffset.getValue()).doubleValue();
        double d5 = ((Number)this.randomPitchOffset.getValue()).doubleValue();
        boolean bl2 = (Boolean)this.adaptive.getValue();
        double d6 = ((Number)this.adaptiveOffset.getValue()).doubleValue();
        double d7 = ((Number)this.smoothAmount.getValue()).doubleValue();
        boolean bl3 = (Boolean)this.breakBlock.getValue();
        if (bl && !AimAssist.mc.options.keyAttack.isDown()) {
            this.s = new Vec3(0.0, 0.0, 0.0);
            return;
        }
        if (bl3 && AimAssist.mc.options.keyAttack.isDown() && AimAssist.mc.hitResult != null && AimAssist.mc.hitResult.getType() == HitResult.Type.BLOCK) {
            this.s = new Vec3(0.0, 0.0, 0.0);
            return;
        }
        float f3 = mc.getFrameTime();
        List list = AimAssist.mc.level.players();
        LocalPlayer localPlayer = AimAssist.mc.player;
        Vec3 vec3 = localPlayer.position().add(new Vec3(0.0, (double)localPlayer.getEyeHeight(), 0.0));
        a a2 = this.a();
        Player player2 = null;
        float f4 = Float.MAX_VALUE;
        for (Player player3 : list) {
            if (player3 == null || player3.equals((Object)localPlayer) || !this.a((LivingEntity)player3)) continue;
            f2 = player3.getEyeHeight();
            object = g.a(a2.c().a(g.a(vec3, player3.position().add(new Vec3(0.0, (double)f2, 0.0))).c()));
            if (((a)object).e() < 0.0f) {
                ((a)object).a(-((a)object).e());
            }
            if (((a)object).f() < 0.0f) {
                ((a)object).b(-((a)object).f());
            }
            float f5 = (float)localPlayer.position().subtract(player3.position()).length();
            if (!((double)Math.abs(((a)object).e()) <= d3) || !((double)f5 <= d2) || !(f4 > f5)) continue;
            player2 = player3;
            f4 = f5;
        }
        if (player2 == null || !this.a((LivingEntity)player2)) {
            this.s = new Vec3(0.0, 0.0, 0.0);
            return;
        }
        Vec3 vec32 = player2.position();
        player3 = player2.getPosition(f3);
        f2 = player2.getEyeHeight();
        object = vec32.add(new Vec3(0.0, (double)f2, 0.0));
        Vec3 vec33 = player3.add(new Vec3(0.0, (double)f2, 0.0));
        a a3 = g.a(vec3, vec32);
        a a4 = g.a(vec3, (Vec3)object);
        a a5 = g.a(a2.c().a(a4.c()));
        a a6 = g.a(a2.c().a(a3.c()));
        double d8 = lf21.utils.b.a.a(-d4, d4);
        if (bl2) {
            if (AimAssist.mc.options.keyRight.isDown() && !AimAssist.mc.options.keyLeft.isDown()) {
                d8 -= d6;
            }
            if (AimAssist.mc.options.keyLeft.isDown() && !AimAssist.mc.options.keyRight.isDown()) {
                d8 += d6;
            }
        }
        float f6 = a2.e() + (float)(((double)a5.e() + d8) / d7);
        Vec3 vec34 = AimAssist.mc.player.getEyePosition(1.0f);
        if (a2.f() > a3.f() || a2.f() < a4.f()) {
            float f7;
            float f8;
            float f9 = a2.f() + (float)((double)a6.f() / d7);
            float f10 = a2.f() + (float)((double)a5.f() / d7);
            float f11 = Math.abs(a2.f() - f9);
            if (f11 > (f8 = Math.abs(a2.f() - f10))) {
                f7 = f10;
                this.s = vec34.subtract(new Vec3(0.0, 0.21, 0.0)).subtract(vec33);
            } else {
                f7 = f9;
                this.s = vec34.subtract(new Vec3(0.0, 0.23, 0.0)).subtract((Vec3)player3);
            }
            this.u = true;
            this.a(new a(f6, f7 + (float)lf21.utils.b.a.a(-d5, d5)));
        } else {
            this.s = vec34.subtract(vec32);
            this.u = false;
            this.a(new a(f6, a2.f() + (float)lf21.utils.b.a.a(-d5, d5)));
        }
    }

    private a a() {
        return new a(AimAssist.mc.player.getRotationVector().y, AimAssist.mc.player.getRotationVector().x);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6B);
        }
        return cArray;
    }

    public AimAssist() {
        String[] stringArray = v;
        super("AimAssist", Category.MISC);
        this.randomYawOffset = new NumberSetting("Random Yaw Offset", 2, 0, 10, 0.01);
        this.randomPitchOffset = new NumberSetting("Random Pitch Offset", Float.valueOf(0.075f), 0, 1, 0.01);
        this.range = new NumberSetting("Range", 5, 3, 30, 0.1);
        this.fov = new NumberSetting("Fov", 120, 1, 360, 1);
        this.mouseDown = new BooleanSetting("Mouse down", true);
        this.adaptive = new BooleanSetting("Adaptive", true);
        this.adaptiveOffset = new NumberSetting("Adaptive Offset", 3, Float.valueOf(0.1f), Float.valueOf(15.0f), 0.01);
        this.smoothAmount = new NumberSetting("Smooth amount", 15, Float.valueOf(1.0f), Float.valueOf(90.0f), 0.1);
        this.breakBlock = new BooleanSetting("Break Block", true);
        this.u = false;
    }
}

