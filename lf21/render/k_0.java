/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.vertex.VertexConsumer;

/*
 * Renamed from lf21.render.k
 */
final class k_0
extends Enum<k_0>
implements VertexConsumer {
    public static final /* enum */ k_0 a;
    private static final k_0[] b;

    public void defaultColor(int n2, int n3, int n4, int n5) {
    }

    public VertexConsumer overlayCoords(int n2, int n3) {
        return this;
    }

    public VertexConsumer color(int n2, int n3, int n4, int n5) {
        return this;
    }

    public static k_0[] a() {
        return (k_0[])b.clone();
    }

    public static k_0 a(String string) {
        return Enum.valueOf(k_0.class, string);
    }

    public VertexConsumer vertex(double d2, double d3, double d4) {
        return this;
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
                    case 0 -> 45;
                    case 1 -> 81;
                    case 2 -> 73;
                    case 3 -> 44;
                    case 4 -> 96;
                    case 5 -> 113;
                    default -> 15;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public void endVertex() {
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string = k_0.a(k_0.b("d\u001f\u001ax!?Lh"));
        a = new k_0();
        b = k_0.b();
    }

    public VertexConsumer normal(float f2, float f3, float f4) {
        return this;
    }

    public VertexConsumer uv(float f2, float f3) {
        return this;
    }

    public void unsetDefaultColor() {
    }

    public VertexConsumer uv2(int n2, int n3) {
        return this;
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xF);
        }
        return cArray;
    }

    private static k_0[] b() {
        return new k_0[]{a};
    }
}

