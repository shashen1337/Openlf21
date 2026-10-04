/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.rotation;

import java.util.concurrent.ThreadLocalRandom;
import lf21.ClientBase;
import lf21.utils.b.i;
import lombok.Generated;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.RandomUtils;

public class a {
    public float a;
    public float b;
    public double c;
    public Runnable d;
    public Runnable e;
    static final boolean f;
    private static final String[] g;

    public a() {
        this.a = 0.0f;
        this.b = 0.0f;
    }

    public a(float f2, float f3) {
        this.a = f2;
        this.b = f3;
    }

    public a(i i2) {
        this.a = i2.c();
        this.b = i2.d();
    }

    public a(Vec3 vec3, Vec3 vec32) {
        Vec3 vec33 = vec32.subtract(vec3);
        this.a = Mth.wrapDegrees((float)((float)Math.toDegrees(Math.atan2(vec33.z, vec33.x)) - 90.0f));
        this.b = Mth.wrapDegrees((float)((float)(-Math.toDegrees(Math.atan2(vec33.y, Math.sqrt(vec33.x * vec33.x + vec33.z * vec33.z))))));
    }

    public a a() {
        return new a(this.e(), this.f());
    }

    public i b() {
        return new i(this.a, this.b);
    }

    public a a(a a2) {
        return new a(this.a - a2.a, this.b - a2.b);
    }

    public a c() {
        return new a(-this.a, -this.b);
    }

    public a a(Runnable runnable) {
        this.d = runnable;
        return this;
    }

    public a b(Runnable runnable) {
        this.e = runnable;
        return this;
    }

    public void d() {
        ClientBase.mc.player.setYRot(this.a);
        ClientBase.mc.player.setXRot(this.b);
    }

    public void a(Player player) {
        if (Float.isNaN(this.a) || Float.isNaN(this.b)) {
            return;
        }
        this.a(Float.valueOf(((Double)ClientBase.mc.options.sensitivity().get()).floatValue()));
        player.setYRot(this.a);
        player.setXRot(this.b);
    }

    public a a(Float f2) {
        float f3 = f2.floatValue() * 0.6f + 0.2f;
        float f4 = f3 * f3 * f3 * 1.2f;
        this.a -= this.a % f4;
        this.b -= this.b % f4;
        return this;
    }

    public static float a(float f2, float f3, float f4) {
        float f5 = Mth.wrapDegrees((float)(f3 - f2));
        if (f5 > f4) {
            f5 = f4;
        }
        if (f5 < -f4) {
            f5 = -f4;
        }
        return f2 + f5;
    }

    public double b(a a2) {
        float f2 = Mth.wrapDegrees((float)this.a);
        float f3 = Mth.wrapDegrees((float)a2.a);
        float f4 = Mth.wrapDegrees((float)(f2 - f3));
        float f5 = Mth.wrapDegrees((float)this.b);
        float f6 = Mth.wrapDegrees((float)a2.b);
        float f7 = Mth.wrapDegrees((float)(f5 - f6));
        return Math.sqrt(f4 * f4 + f7 * f7);
    }

    public float b(float f2, float f3, float f4) {
        float f5 = lf21.utils.rotation.a.a(f3, f4, f2 + RandomUtils.nextFloat((float)0.0f, (float)15.0f));
        double d2 = Mth.wrapDegrees((float)(f4 - f3));
        if ((double)(-f2) > d2 || d2 > (double)f2) {
            f5 += (float)((double)RandomUtils.nextFloat((float)1.0f, (float)2.0f) * Math.sin((double)ClientBase.mc.player.getXRot() * Math.PI));
        }
        if (f5 == f3) {
            return f3;
        }
        float f6 = ((Double)ClientBase.mc.options.sensitivity().get()).floatValue();
        if ((double)f6 == 0.5) {
            f6 = 0.47887325f;
        }
        float f7 = f6 * 0.6f + 0.2f;
        float f8 = f7 * f7 * f7 * 8.0f;
        int n2 = (int)((6.667 * (double)f5 - 6.666666666666667 * (double)f3) / (double)f8);
        float f9 = (float)n2 * f8;
        f5 = (float)((double)f3 + (double)f9 * 0.15);
        return f5;
    }

    public float a(float f2, float[] fArray, float f3) {
        float f4 = lf21.utils.rotation.a.a(fArray[0], f3, f2 + RandomUtils.nextFloat((float)0.0f, (float)15.0f));
        if (f4 != f3) {
            f4 += (float)((double)RandomUtils.nextFloat((float)1.0f, (float)2.0f) * Math.sin((double)fArray[1] * Math.PI));
        }
        if (f4 == fArray[0]) {
            return fArray[0];
        }
        float f5 = ((Double)ClientBase.mc.options.sensitivity().get()).floatValue();
        f4 += (float)(ThreadLocalRandom.current().nextGaussian() * 0.2);
        if ((double)f5 == 0.5) {
            f5 = 0.47887325f;
        }
        float f6 = f5 * 0.6f + 0.2f;
        float f7 = f6 * f6 * f6 * 8.0f;
        int n2 = (int)((6.667 * (double)f4 - 6.6666667 * (double)fArray[0]) / (double)f7);
        float f8 = (float)n2 * f7;
        f4 = (float)((double)fArray[0] + (double)f8 * 0.15);
        return f4;
    }

    public float c(float f2, float f3, float f4) {
        float f5;
        float f6 = lf21.utils.rotation.a.a(f3, f4, f2 + RandomUtils.nextFloat((float)0.0f, (float)15.0f));
        if (f6 != f4) {
            f6 += (float)((double)RandomUtils.nextFloat((float)1.0f, (float)2.0f) * Math.sin((double)ClientBase.mc.player.getYRot() * Math.PI));
        }
        if ((double)(f5 = ((Double)ClientBase.mc.options.sensitivity().get()).floatValue()) == 0.5) {
            f5 = 0.47887325f;
        }
        float f7 = f5 * 0.6f + 0.2f;
        float f8 = f7 * f7 * f7 * 8.0f;
        int n2 = (int)((6.667 * (double)f6 - 6.666667 * (double)f3) / (double)f8) * -1;
        float f9 = (float)n2 * f8;
        float f10 = (float)((double)f3 - (double)f9 * 0.15);
        f6 = Mth.clamp((float)f10, (float)-90.0f, (float)90.0f);
        return f6;
    }

    public float b(float f2, float[] fArray, float f3) {
        float f4;
        float f5 = lf21.utils.rotation.a.a(fArray[1], f3, f2 + RandomUtils.nextFloat((float)0.0f, (float)15.0f));
        if (f5 != f3) {
            f5 += (float)((double)RandomUtils.nextFloat((float)1.0f, (float)2.0f) * Math.sin((double)fArray[0] * Math.PI));
        }
        if ((double)(f4 = ((Double)ClientBase.mc.options.sensitivity().get()).floatValue()) == 0.5) {
            f4 = 0.47887325f;
        }
        float f6 = f4 * 0.6f + 0.2f;
        float f7 = f6 * f6 * f6 * 8.0f;
        int n2 = (int)((6.667 * (double)f5 - 6.666667 * (double)fArray[1]) / (double)f7) * -1;
        float f8 = (float)n2 * f7;
        float f9 = (float)((double)fArray[1] - (double)f8 * 0.15);
        f5 = Mth.clamp((float)f9, (float)-90.0f, (float)90.0f);
        return f5;
    }

    public void a(float f2, float f3) {
        this.a = f2;
        this.b = f3;
    }

    @Generated
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof a)) {
            return false;
        }
        a a2 = (a)object;
        if (!a2.a((Object)this)) {
            return false;
        }
        if (Float.compare(this.e(), a2.e()) != 0) {
            return false;
        }
        if (Float.compare(this.f(), a2.f()) != 0) {
            return false;
        }
        if (Double.compare(this.g(), a2.g()) != 0) {
            return false;
        }
        Runnable runnable = this.h();
        Runnable runnable2 = a2.h();
        if (runnable == null ? runnable2 != null : !runnable.equals(runnable2)) {
            return false;
        }
        Runnable runnable3 = this.i();
        Runnable runnable4 = a2.i();
        return !(runnable3 == null ? runnable4 != null : !runnable3.equals(runnable4));
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof a;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + Float.floatToIntBits(this.e());
        n3 = n3 * 59 + Float.floatToIntBits(this.f());
        long l2 = Double.doubleToLongBits(this.g());
        n3 = n3 * 59 + (int)(l2 >>> 32 ^ l2);
        Runnable runnable = this.h();
        n3 = n3 * 59 + (runnable == null ? 43 : runnable.hashCode());
        Runnable runnable2 = this.i();
        n3 = n3 * 59 + (runnable2 == null ? 43 : runnable2.hashCode());
        return n3;
    }

    @Generated
    public String toString() {
        String string = String.valueOf(this.i());
        String string2 = String.valueOf(this.h());
        double d2 = this.g();
        float f2 = this.f();
        float f3 = this.e();
        String[] stringArray = g;
        return "Rotation(yaw=" + f3 + ", pitch=" + f2 + ", distanceSq=" + d2 + ", task=" + string2 + ", postTask=" + string + ")";
    }

    @Generated
    public float e() {
        return this.a;
    }

    @Generated
    public void a(float f2) {
        this.a = f2;
    }

    @Generated
    public float f() {
        return this.b;
    }

    @Generated
    public void b(float f2) {
        this.b = f2;
    }

    @Generated
    public double g() {
        return this.c;
    }

    @Generated
    public void a(double d2) {
        this.c = d2;
    }

    @Generated
    public Runnable h() {
        return this.d;
    }

    @Generated
    public void c(Runnable runnable) {
        this.d = runnable;
    }

    @Generated
    public Runnable i() {
        return this.e;
    }

    @Generated
    public void d(Runnable runnable) {
        this.e = runnable;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                lf21.utils.rotation.a.f = true;
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "\u0011r\u0012\fx\n\rS1\u00136zC\u0007\u0011r\u0002\u0004x\u0015Q\u000b\u0011r\u0006\nx\n8\\!\u001dX";
                var4_3 = "\u0011r\u0012\fx\n\rS1\u00136zC\u0007\u0011r\u0002\u0004x\u0015Q\u000b\u0011r\u0006\nx\n8\\!\u001dX".length();
                var1_4 = 13;
                var0_5 = -1;
lbl8:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl13:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.utils.rotation.a.a(lf21.utils.rotation.a.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "o=\u0002\u0004\u007f\u0017\u0003Sz\u000f\u0004|C\b\u0011r\u0006\f\u007f\u001d\u0004\u0000";
                    var4_3 = "o=\u0002\u0004\u007f\u0017\u0003Sz\u000f\u0004|C\b\u0011r\u0006\f\u007f\u001d\u0004\u0000".length();
                    var1_4 = 13;
                    var0_5 = -1;
lbl22:
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
lbl27:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.utils.rotation.a.a(lf21.utils.rotation.a.a(v1));
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
lbl38:
                // 1 sources

                ** continue;
            }
        }
        lf21.utils.rotation.a.g = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6C);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
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
                    case 0 -> 61;
                    case 1 -> 82;
                    case 2 -> 118;
                    case 3 -> 101;
                    case 4 -> 11;
                    case 5 -> 126;
                    default -> 108;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

