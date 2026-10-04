/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import lf21.modules.Category;
import lf21.modules.Module;

public class ShiftDrag
extends Module {
    public static ShiftDrag INSTANCE;
    private static final String j;

    public ShiftDrag() {
        super(j, Category.PLAYER);
        INSTANCE = this;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        j = ShiftDrag.c(ShiftDrag.a("\u0003t.~$Ke1{"));
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x17);
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
                    case 0 -> 80;
                    case 1 -> 28;
                    case 2 -> 71;
                    case 3 -> 24;
                    case 4 -> 80;
                    case 5 -> 15;
                    default -> 23;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

