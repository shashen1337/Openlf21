/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.misc;

import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.k;
import lf21.utils.misc.m;

public final class IQBoost
extends Module
implements k {
    public final NumberSetting iQ;
    private static final String[] k;

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "xA\u0007xAY~,VE";
        int n3 = "xA\u0007xAY~,VE".length();
        int n4 = 2;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = IQBoost.c(IQBoost.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                k = stringArray;
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
                    case 0 -> 49;
                    case 1 -> 16;
                    case 2 -> 27;
                    case 3 -> 17;
                    case 4 -> 67;
                    case 5 -> 37;
                    default -> 49;
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
            cArray[0] = (char)(cArray[0] ^ 0x31);
        }
        return cArray;
    }

    @Override
    public m<String, String, Boolean> a() {
        if (!this.isEnabled()) {
            return null;
        }
        return new m<String, String, Boolean>(this.getName(), String.valueOf(((Number)this.iQ.getValue()).intValue()), true);
    }

    public IQBoost() {
        String[] stringArray = k;
        super("IQBoost", Category.MISC);
        this.iQ = new NumberSetting("IQ", 0, -250, 250, 1);
    }
}

