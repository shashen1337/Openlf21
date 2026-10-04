/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.k;
import lf21.utils.misc.m;

public class FullBright
extends Module
implements k {
    private static final String[] j;
    public static FullBright INSTANCE;
    public final NumberSetting brightnessSetting;

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "KQYmxL;lPC\nOV\\fRJ<nKD";
        int n3 = "KQYmxL;lPC\nOV\\fRJ<nKD".length();
        int n4 = 10;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = FullBright.c(FullBright.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                j = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
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
                    case 0 -> 9;
                    case 1 -> 35;
                    case 2 -> 48;
                    case 3 -> 10;
                    case 4 -> 16;
                    case 5 -> 56;
                    default -> 85;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x55);
        }
        return cArray;
    }

    @Override
    public m a() {
        if (this.isEnabled()) {
            return new m<String, String, Boolean>(this.getName(), String.valueOf(((Number)this.brightnessSetting.getValue()).intValue()), true);
        }
        return null;
    }

    public FullBright() {
        String[] stringArray = j;
        super("FullBright", Category.RENDER);
        this.brightnessSetting = new NumberSetting("Brightness", Float.valueOf(100.0f), Float.valueOf(0.0f), Float.valueOf(100.0f), Float.valueOf(1.0f));
        INSTANCE = this;
    }
}

