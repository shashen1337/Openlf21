/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.modules.Category;
import lf21.modules.Module;

public final class NoCameraClip
extends Module {
    public static NoCameraClip INSTANCE;
    private static final String j;

    public NoCameraClip() {
        super(j, Category.RENDER);
        INSTANCE = this;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        j = NoCameraClip.c(NoCameraClip.a("FTZy\u0014/\u007fixuq\t"));
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xD);
        }
        return cArray;
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
                    case 0 -> 8;
                    case 1 -> 59;
                    case 2 -> 25;
                    case 3 -> 24;
                    case 4 -> 121;
                    case 5 -> 74;
                    default -> 13;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

