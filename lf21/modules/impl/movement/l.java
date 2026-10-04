/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import lf21.event.EventTarget;
import lf21.event.impl.SneakEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.FireballBlink;

public class l
extends Module {
    private static final String j = l.c(l.a("\u00068F\u000b)V"));

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
                    case 0 -> 72;
                    case 1 -> 87;
                    case 2 -> 22;
                    case 3 -> 126;
                    case 4 -> 90;
                    case 5 -> 62;
                    default -> 60;
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
            cArray[0] = (char)(cArray[0] ^ 0x3C);
        }
        return cArray;
    }

    @EventTarget
    public void a(SneakEvent sneakEvent) {
        if (!FireballBlink.j.isEnabled()) {
            sneakEvent.a(true);
        }
    }

    public l() {
        super(j, Category.MOVEMENT);
    }
}

