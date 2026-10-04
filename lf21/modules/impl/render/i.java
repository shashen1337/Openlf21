/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.gui.k;
import lf21.modules.Category;
import lf21.modules.Module;
import net.minecraft.client.gui.screens.Screen;

public class i
extends Module {
    private static final String j = i.c(i.a("R\u007f\u0015`"));

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
                    case 0 -> 23;
                    case 1 -> 27;
                    case 2 -> 124;
                    case 3 -> 20;
                    case 4 -> 29;
                    case 5 -> 91;
                    default -> 87;
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
            cArray[0] = (char)(cArray[0] ^ 0x57);
        }
        return cArray;
    }

    @Override
    protected void onEnable() {
        mc.setScreen((Screen)new k());
        this.setEnabled(false);
    }

    public i() {
        super(j, Category.RENDER);
    }
}

