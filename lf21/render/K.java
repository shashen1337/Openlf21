/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import net.minecraft.resources.ResourceLocation;

public class K {
    private static final String[] c;
    final ResourceLocation a;
    private static final String b;

    public K(String string) {
        this.a = ResourceLocation.tryParse((String)("heipixel:" + this.a(string)));
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u0011d8\u0011;6L\u0015;\t\u0011d8\u0011;6L\u0015;";
        int n3 = "\u0011d8\u0011;6L\u0015;\t\u0011d8\u0011;6L\u0015;".length();
        int n4 = 9;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = K.a(K.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                c = stringArray;
                b = c[0];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    String a(String string) {
        if (ResourceLocation.isValidResourceLocation((String)string)) {
            return string;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c2 : string.toLowerCase().toCharArray()) {
            if (!ResourceLocation.isAllowedInResourceLocation((char)c2)) continue;
            stringBuilder.append(c2);
        }
        return stringBuilder.toString();
    }

    public K(ResourceLocation resourceLocation) {
        this.a = ResourceLocation.tryParse((String)(resourceLocation.getNamespace() + ":" + resourceLocation.getPath()));
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
                    case 0 -> 121;
                    case 1 -> 1;
                    case 2 -> 81;
                    case 3 -> 97;
                    case 4 -> 82;
                    case 5 -> 78;
                    default -> 41;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x29);
        }
        return cArray;
    }

    public ResourceLocation a() {
        return this.a;
    }
}

