/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import java.util.Optional;
import net.minecraft.world.phys.Vec3;

public final class C
extends Record {
    private final Vec3 a;
    private final double b;
    private final String d;
    private final Optional<Float> c;
    private static final String[] f;
    private final String e;

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{C.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public C(Vec3 vec3, double d2, Optional<Float> optional, String string, String string2) {
        this.a = vec3;
        this.b = d2;
        this.c = optional;
        this.d = string;
        this.e = string2;
    }

    public String b() {
        if (this.c.isPresent()) {
            String[] stringArray = f;
            return String.format("%.1fs \u00b7 %.1fm", this.c.get(), this.b);
        }
        return String.format("%.1fm", this.b);
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{C.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x3A);
        }
        return cArray;
    }

    public Optional<Float> e() {
        return this.c;
    }

    public Vec3 c() {
        return this.a;
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{C.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this, object);
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
                    case 0 -> 7;
                    case 1 -> 71;
                    case 2 -> 121;
                    case 3 -> 11;
                    case 4 -> 9;
                    case 5 -> 87;
                    default -> 58;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public String f() {
        return this.d;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\"iHmd\r\"iHmzw\u008d'bW:o:";
        int n3 = "\"iHmd\r\"iHmzw\u008d'bW:o:".length();
        int n4 = 5;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = C.a(C.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                f = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public double d() {
        return this.b;
    }

    public Vec3 a() {
        return this.a;
    }

    public String g() {
        return this.e;
    }
}

