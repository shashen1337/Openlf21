/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;

public class NoDelay
extends Module {
    private static final String[] j;
    public static NoDelay INSTANCE;
    public final BooleanSetting fastDig;

    public NoDelay() {
        String[] stringArray = j;
        super("NoDelay", Category.MOVEMENT);
        this.fastDig = new BooleanSetting("No Jump Delay", true);
        INSTANCE = this;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u0007\u0005\"v\u000e\u0019\fi.gP\u001a\r\u0007\u0007\u0005FY\u0017\u0015\u0005";
        int n3 = "\u0007\u0005\"v\u000e\u0019\fi.gP\u001a\r\u0007\u0007\u0005FY\u0017\u0015\u0005".length();
        int n4 = 13;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = NoDelay.c(NoDelay.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                j = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x7C);
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
                    case 0 -> 73;
                    case 1 -> 106;
                    case 2 -> 2;
                    case 3 -> 60;
                    case 4 -> 123;
                    case 5 -> 116;
                    default -> 124;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

