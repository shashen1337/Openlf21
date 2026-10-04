/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;

public class Interface
extends Module {
    public static Interface j;
    public final BooleanSetting hUDGlow;
    public final NumberSetting glowCompression;
    public final NumberSetting glowRadius;
    private static final String[] o;
    public final NumberSetting glowIntensity;

    public Interface() {
        String[] stringArray = o;
        super("Interface", Category.RENDER);
        this.hUDGlow = new BooleanSetting("HUD Glow", true);
        this.glowRadius = new NumberSetting("Glow Radius", 14.0, 1.0, 30.0, 1.0, () -> (Boolean)this.hUDGlow.getValue());
        this.glowCompression = new NumberSetting("Glow Compression", 2.0, 1.0, 10.0, 0.1, () -> (Boolean)this.hUDGlow.getValue());
        this.glowIntensity = new NumberSetting("Glow Intensity", 1.0, 0.1, 2.0, 0.05, () -> (Boolean)this.hUDGlow.getValue());
        j = this;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "Y<\u007fu(k[s7\u000eW>dgzDTd7ec3yC\bX\u0007O0\u001daUg";
                var4_3 = "Y<\u007fu(k[s7\u000eW>dgzDTd7ec3yC\bX\u0007O0\u001daUg".length();
                var1_4 = 9;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = Interface.c(Interface.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "W>dgz_[t;~c\u0010W>dgzNU}\"yu)~S\u007f<";
                    var4_3 = "W>dgz_[t;~c\u0010W>dgzNU}\"yu)~S\u007f<".length();
                    var1_4 = 11;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = Interface.c(Interface.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    break block10;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl37:
                // 1 sources

                ** continue;
            }
        }
        Interface.o = var5;
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
                    case 0 -> 16;
                    case 1 -> 82;
                    case 2 -> 11;
                    case 3 -> 16;
                    case 4 -> 90;
                    case 5 -> 13;
                    default -> 58;
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
            cArray[0] = (char)(cArray[0] ^ 0x3A);
        }
        return cArray;
    }
}

