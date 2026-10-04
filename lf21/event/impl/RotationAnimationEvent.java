/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lombok.Generated;

public class RotationAnimationEvent
implements EventMarker {
    private float a;
    private float b;
    private float c;
    private float d;
    private static final String[] e;

    @Generated
    public String toString() {
        float f2 = this.getLastPitch();
        float f3 = this.getPitch();
        float f4 = this.getLastYaw();
        float f5 = this.getYaw();
        String[] stringArray = e;
        return "RotationAnimationEvent(yaw=" + f5 + ", lastYaw=" + f4 + ", pitch=" + f3 + ", lastPitch=" + f2 + ")";
    }

    @Generated
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof RotationAnimationEvent)) {
            return false;
        }
        RotationAnimationEvent rotationAnimationEvent = (RotationAnimationEvent)object;
        if (!rotationAnimationEvent.a(this)) {
            return false;
        }
        if (Float.compare(this.getYaw(), rotationAnimationEvent.getYaw()) != 0) {
            return false;
        }
        if (Float.compare(this.getLastYaw(), rotationAnimationEvent.getLastYaw()) != 0) {
            return false;
        }
        if (Float.compare(this.getPitch(), rotationAnimationEvent.getPitch()) != 0) {
            return false;
        }
        return Float.compare(this.getLastPitch(), rotationAnimationEvent.getLastPitch()) == 0;
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof RotationAnimationEvent;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + Float.floatToIntBits(this.getYaw());
        n3 = n3 * 59 + Float.floatToIntBits(this.getLastYaw());
        n3 = n3 * 59 + Float.floatToIntBits(this.getPitch());
        n3 = n3 * 59 + Float.floatToIntBits(this.getLastPitch());
        return n3;
    }

    @Generated
    public RotationAnimationEvent(float f2, float f3, float f4, float f5) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.d = f5;
    }

    @Generated
    public float getYaw() {
        return this.a;
    }

    @Generated
    public float getLastYaw() {
        return this.b;
    }

    @Generated
    public void a(float f2) {
        this.a = f2;
    }

    @Generated
    public void b(float f2) {
        this.b = f2;
    }

    @Generated
    public float getPitch() {
        return this.c;
    }

    @Generated
    public float getLastPitch() {
        return this.d;
    }

    @Generated
    public void c(float f2) {
        this.c = f2;
    }

    @Generated
    public void d(float f2) {
        this.d = f2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "c\u001eofE]\u0011&J`o\u000b\bc\u001esnBJ)r";
                var4_3 = "c\u001eofE]\u0011&J`o\u000b\bc\u001esnBJ)r".length();
                var1_4 = 12;
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
                    var5[var3_1++] = RotationAnimationEvent.a(RotationAnimationEvent.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "c\u001eofE]\u0018.I>\u001b\u001dQwfB@.!\u007fmn[H5&QmB@L/;\u0016zfA\u0014";
                    var4_3 = "c\u001eofE]\u0018.I>\u001b\u001dQwfB@.!\u007fmn[H5&QmB@L/;\u0016zfA\u0014".length();
                    var1_4 = 10;
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
                    var5[var3_1++] = RotationAnimationEvent.a(RotationAnimationEvent.a(v1));
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
        RotationAnimationEvent.e = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x41);
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
                    case 0 -> 79;
                    case 1 -> 62;
                    case 2 -> 3;
                    case 3 -> 7;
                    case 4 -> 54;
                    case 5 -> 41;
                    default -> 65;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

