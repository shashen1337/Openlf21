/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.b;

import lombok.Generated;

public final class i {
    public float a;
    public float b;
    private static final String[] c;

    public i(i i2) {
        this(i2.a, i2.b);
    }

    public i a(float f2, float f3) {
        return new i(this.a + f2, this.b + f3);
    }

    public i a(float f2) {
        this.a = f2;
        this.b = f2;
        return this;
    }

    public i b(float f2, float f3) {
        this.a = f2;
        this.b = f3;
        return this;
    }

    public i a(double d2) {
        this.a = (float)d2;
        this.b = (float)d2;
        return this;
    }

    public i a(double d2, double d3) {
        this.a = (float)d2;
        this.b = (float)d3;
        return this;
    }

    public i a(float[] fArray) {
        this.a = fArray[0];
        this.b = fArray[1];
        return this;
    }

    @Generated
    public i(float f2, float f3) {
        this.a = f2;
        this.b = f3;
    }

    @Generated
    public boolean a(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof i)) {
            return false;
        }
        i i2 = (i)object;
        if (Float.compare(this.c(), i2.c()) != 0) {
            return false;
        }
        return Float.compare(this.d(), i2.d()) == 0;
    }

    @Generated
    public int a() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + Float.floatToIntBits(this.c());
        n3 = n3 * 59 + Float.floatToIntBits(this.d());
        return n3;
    }

    @Generated
    public String b() {
        String[] stringArray = c;
        return "Vector2f(x=" + this.c() + ", y=" + this.d() + ")";
    }

    @Generated
    public float c() {
        return this.a;
    }

    @Generated
    public void b(float f2) {
        this.a = f2;
    }

    @Generated
    public float d() {
        return this.b;
    }

    @Generated
    public void c(float f2) {
        this.b = f2;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "Fw\"\u001f_Auv:9V\u0004<28V";
        int n3 = "Fw\"\u001f_Auv:9V\u0004<28V".length();
        int n4 = 11;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = i.a(i.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                c = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x47);
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
                    case 0 -> 16;
                    case 1 -> 18;
                    case 2 -> 65;
                    case 3 -> 107;
                    case 4 -> 48;
                    case 5 -> 51;
                    default -> 71;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

