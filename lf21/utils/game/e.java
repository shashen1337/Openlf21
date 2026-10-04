/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import lf21.ClientBase;
import lf21.event.impl.StrafeEvent;
import lf21.modules.impl.movement.TargetStrafe;
import lf21.utils.game.g;
import lf21.utils.rotation.a;
import lombok.Generated;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class e
extends ClientBase {
    private static final String a;
    private static final String[] b;

    public static boolean a() {
        boolean bl;
        block10: {
            block9: {
                block8: {
                    if (e.mc.options.keyUp.isDown() || e.mc.options.keyDown.isDown()) break block8;
                    if (e.mc.options.keyLeft.isDown()) break block8;
                    if (!e.mc.options.keyRight.isDown()) break block9;
                }
                bl = true;
                break block10;
            }
            bl = false;
        }
        return bl;
    }

    public static void a(double d2, double d3) {
        if (!e.g()) {
            return;
        }
        Vec3 vec3 = e.mc.player.getDeltaMovement();
        e.mc.player.setDeltaMovement(vec3.x + (double)(-Mth.sin((float)((float)d2))) * d3, vec3.y, vec3.z + (double)Mth.cos((float)((float)d2)) * d3);
    }

    public static void a(double d2) {
        if (!e.g()) {
            return;
        }
        double d3 = e.b();
        Vec3 vec3 = e.mc.player.getDeltaMovement();
        e.mc.player.setDeltaMovement(vec3.x + (double)(-Mth.sin((float)((float)d3))) * d2, vec3.y, vec3.z + (double)Mth.cos((float)((float)d3)) * d2);
    }

    public static double b() {
        float f2 = e.mc.player.getYRot();
        if (e.mc.player.zza < 0.0f) {
            f2 += 180.0f;
        }
        float f3 = 1.0f;
        if (e.mc.player.zza < 0.0f) {
            f3 = -0.5f;
        } else if (e.mc.player.zza > 0.0f) {
            f3 = 0.5f;
        }
        if (e.mc.player.xxa > 0.0f) {
            f2 -= 90.0f * f3;
        } else if (e.mc.player.xxa < 0.0f) {
            f2 += 90.0f * f3;
        }
        return Math.toRadians(f2);
    }

    public static double c() {
        double d2 = 0.2875;
        if (e.mc.player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
            int n2 = e.mc.player.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier();
            d2 *= 1.0 + 0.2 * (double)(n2 + 1);
        }
        return d2;
    }

    public static double b(double d2, double d3) {
        return Math.sqrt(d2 * d2 + d3 * d3);
    }

    public static double d() {
        return e.b(e.mc.player.getDeltaMovement().x, e.mc.player.getDeltaMovement().z);
    }

    public static void b(double d2) {
        float f2;
        float f3;
        float f4;
        block7: {
            block6: {
                f4 = e.mc.player.input.forwardImpulse;
                f3 = e.mc.player.input.leftImpulse;
                f2 = e.mc.player.getYRot();
                if (f4 != 0.0f || f3 != 0.0f) break block6;
                e.mc.player.setDeltaMovement(0.0, e.mc.player.getDeltaMovement().y, 0.0);
                return;
            }
            if (f4 == 0.0f || f3 == 0.0f) break block7;
            f4 = (float)((double)f4 * Math.sin(0.7853981633974483));
            f3 = (float)((double)f3 * Math.cos(0.7853981633974483));
        }
        double d3 = (double)f4 * d2 * -Math.sin(Math.toRadians(f2)) + (double)f3 * d2 * Math.cos(Math.toRadians(f2));
        double d4 = (double)f4 * d2 * Math.cos(Math.toRadians(f2)) - (double)f3 * d2 * -Math.sin(Math.toRadians(f2));
        e.mc.player.setDeltaMovement(d3, e.mc.player.getDeltaMovement().y, d4);
    }

    public static boolean a(double d2, double d3, double d4) {
        while (d3 > 0.0) {
            block5: {
                Vec3 vec3 = new Vec3(d2, d3, d4);
                Vec3 vec32 = new Vec3(d2, d3 - 1.0, d4);
                BlockHitResult blockHitResult = e.mc.level.clip(new ClipContext(vec3, vec32, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)e.mc.player));
                if (blockHitResult == null || blockHitResult.getType() == HitResult.Type.MISS) break block5;
                return false;
            }
            d3 -= 1.0;
        }
        return true;
    }

    public static void e() {
        e.mc.player.setDeltaMovement(0.0, e.mc.player.getDeltaMovement().y, 0.0);
    }

    public static double f() {
        return Math.hypot(e.mc.player.getDeltaMovement().x, e.mc.player.getDeltaMovement().z);
    }

    public static void a(StrafeEvent strafeEvent, float f2) {
        double d2;
        block21: {
            float f3;
            float f4;
            float f5;
            block19: {
                block20: {
                    f5 = strafeEvent.getForward();
                    f4 = strafeEvent.getStrafe();
                    f3 = e.mc.player.getYRot();
                    if (TargetStrafe.p == null || !TargetStrafe.j.isEnabled()) break block19;
                    if (!TargetStrafe.h()) break block20;
                    if (!e.mc.options.keyJump.isDown()) break block19;
                }
                float f6 = (float)(e.c() / ((double)TargetStrafe.a() * Math.PI * 2.0) * 360.0) * (float)TargetStrafe.o;
                a a2 = g.b(new Vec3(TargetStrafe.p.getX(), TargetStrafe.p.getY(), TargetStrafe.p.getZ()), new Vec3(e.mc.player.getX(), e.mc.player.getY(), e.mc.player.getZ()));
                a2.a(a2.e() + f6);
                float f7 = a2.e() * ((float)Math.PI / 180);
                double d3 = TargetStrafe.p.getX() - Math.sin(f7) * (double)TargetStrafe.a();
                double d4 = TargetStrafe.p.getZ() + Math.cos(f7) * (double)TargetStrafe.a();
                f3 = (float)Math.toDegrees(g.b(new Vec3(e.mc.player.getX(), e.mc.player.getY(), e.mc.player.getZ()), new Vec3(d3, TargetStrafe.p.getY(), d4)).e() * ((float)Math.PI / 180));
            }
            d2 = Mth.wrapDegrees((double)Math.toDegrees(e.a(f3, (double)f5, (double)f4)));
            if (f5 != 0.0f || f4 != 0.0f) break block21;
            return;
        }
        int n2 = 0;
        int n3 = 0;
        float f8 = Float.MAX_VALUE;
        for (int i2 = -1; i2 <= 1; ++i2) {
            for (int i3 = -1; i3 <= 1; ++i3) {
                double d5 = Mth.wrapDegrees((double)Math.toDegrees(e.a(f2, (double)i2, (double)i3)));
                double d6 = Math.abs(Mth.wrapDegrees((double)(d2 - d5)));
                if (i3 == 0 && i2 == 0) continue;
                if (!(d6 < (double)f8)) {
                    continue;
                }
                f8 = (float)d6;
                n2 = i2;
                n3 = i3;
            }
        }
        strafeEvent.a(n2);
        strafeEvent.b(n3);
    }

    public static boolean g() {
        boolean bl;
        block10: {
            block8: {
                block9: {
                    if (e.mc.player == null || e.mc.level == null) break block8;
                    if ((double)e.mc.player.input.forwardImpulse != 0.0) break block9;
                    if ((double)e.mc.player.input.leftImpulse == 0.0) break block8;
                }
                bl = true;
                break block10;
            }
            bl = false;
        }
        return bl;
    }

    public static double a(float f2, double d2, double d3) {
        if (d2 < 0.0) {
            f2 += 180.0f;
        }
        float f3 = 1.0f;
        if (d2 < 0.0) {
            f3 = -0.5f;
        } else if (d2 > 0.0) {
            f3 = 0.5f;
        }
        if (d3 > 0.0) {
            f2 -= 90.0f * f3;
        }
        if (d3 < 0.0) {
            f2 += 90.0f * f3;
        }
        return Math.toRadians(f2);
    }

    private static float a(float f2, float f3) {
        float f4;
        block51: {
            boolean bl;
            block57: {
                boolean bl2;
                block56: {
                    boolean bl3;
                    boolean bl4;
                    block55: {
                        block54: {
                            boolean bl5;
                            block53: {
                                block52: {
                                    boolean bl6;
                                    boolean bl7;
                                    block50: {
                                        block49: {
                                            boolean bl8;
                                            block48: {
                                                block47: {
                                                    f4 = e.mc.player.getYRot();
                                                    boolean bl9 = f2 > 0.0f;
                                                    bl5 = bl9;
                                                    boolean bl10 = f2 < 0.0f;
                                                    bl = bl10;
                                                    boolean bl11 = f3 > 0.0f;
                                                    bl4 = bl11;
                                                    boolean bl12 = f3 < 0.0f;
                                                    bl2 = bl12;
                                                    if (!bl4 && !bl2) break block47;
                                                    bl8 = true;
                                                    break block48;
                                                }
                                                bl8 = false;
                                            }
                                            bl7 = bl8;
                                            if (!bl5 && !bl) break block49;
                                            bl6 = true;
                                            break block50;
                                        }
                                        bl6 = false;
                                    }
                                    bl3 = bl6;
                                    boolean bl13 = bl6;
                                    if (f2 == 0.0f && f3 == 0.0f) break block51;
                                    if (!bl) break block52;
                                    if (bl7) break block52;
                                    return f4 + 180.0f;
                                }
                                if (!bl5 || !bl2) break block53;
                                return f4 + 45.0f;
                            }
                            if (!bl5 || !bl4) break block54;
                            return f4 - 45.0f;
                        }
                        if (bl3 || !bl2) break block55;
                        return f4 + 90.0f;
                    }
                    if (bl3 || !bl4) break block56;
                    return f4 - 90.0f;
                }
                if (!bl || !bl2) break block57;
                return f4 + 135.0f;
            }
            if (bl) {
                return f4 - 135.0f;
            }
        }
        return f4;
    }

    public static double a(Entity entity) {
        return Math.hypot(entity.getX() - entity.xo, entity.getZ() - entity.zo);
    }

    public static double h() {
        return e.a((Entity)e.mc.player) * 20.0 * 1.0;
    }

    @Generated
    private e() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "pDZ\u0016R1F\u0004M\u0013\u0010\u00061YMXJE\u00114TW_\u0013\u0004\u001c<\u0015GM]\u000b\u001d,\u0015FI\u0013\f\u001c+AEBG\f\u0013,P@2pDZ\u0016R1F\u0004M\u0013\u0010\u00061YMXJE\u00114TW_\u0013\u0004\u001c<\u0015GM]\u000b\u001d,\u0015FI\u0013\f\u001c+AEBG\f\u0013,P@";
        int n3 = "pDZ\u0016R1F\u0004M\u0013\u0010\u00061YMXJE\u00114TW_\u0013\u0004\u001c<\u0015GM]\u000b\u001d,\u0015FI\u0013\f\u001c+AEBG\f\u0013,P@2pDZ\u0016R1F\u0004M\u0013\u0010\u00061YMXJE\u00114TW_\u0013\u0004\u001c<\u0015GM]\u000b\u001d,\u0015FI\u0013\f\u001c+AEBG\f\u0013,P@".length();
        int n4 = 50;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = e.b(e.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                b = stringArray;
                a = b[1];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x35);
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
                    case 0 -> 36;
                    case 1 -> 44;
                    case 2 -> 51;
                    case 3 -> 101;
                    case 4 -> 114;
                    case 5 -> 88;
                    default -> 53;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

