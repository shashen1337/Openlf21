/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.NumberSetting;

public class AspectRatio
extends Module {
    private static final String[] j;
    public static AspectRatio INSTANCE;
    public final NumberSetting ratioSetting;

    public AspectRatio() {
        String[] stringArray = j;
        super("AspectRatio", Category.RENDER);
        this.ratioSetting = new NumberSetting("Ratio", Float.valueOf(1.78f), Float.valueOf(0.1f), Float.valueOf(5.0f), Float.valueOf(0.1f));
        INSTANCE = this;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "O\u0019Pd\t\u000b\\\u000bTh\u0005wk|\fMb";
        int n3 = "O\u0019Pd\t\u000b\\\u000bTh\u0005wk|\fMb".length();
        int n4 = 5;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = AspectRatio.c(AspectRatio.a(string.substring(n6, n6 + n4)));
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
            cArray[0] = (char)(cArray[0] ^ 0x39);
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
                    case 0 -> 29;
                    case 1 -> 120;
                    case 2 -> 36;
                    case 3 -> 13;
                    case 4 -> 102;
                    case 5 -> 3;
                    default -> 57;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

