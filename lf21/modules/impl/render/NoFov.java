/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;

public class NoFov
extends Module {
    public final NumberSetting fovSetting;
    private static final String[] k;
    public final NumberSetting multiplierSetting;
    public final ModeSetting mode;
    public static NoFov INSTANCE;

    public NoFov() {
        String[] stringArray = k;
        super("NoFov", Category.RENDER);
        this.mode = new ModeSetting("Mode", "Constant", "Custom").withDefault("Constant");
        this.fovSetting = new NumberSetting("FOV", 90, 1, 179, 1, () -> this.mode.is("Constant"));
        this.multiplierSetting = new NumberSetting("Multiplier", 0.0, 0.0, 1.5, 0.05, () -> this.mode.is("Custom"));
        INSTANCE = this;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[8];
                var3_1 = 0;
                var2_2 = "[v\u007f._W\u0012l\nUl})BF\u0010q|c\b[v\u007f._W\u0012l\u0006[lb)D[\u0004Uvu8\u0006[lb)D[";
                var4_3 = "[v\u007f._W\u0012l\nUl})BF\u0010q|c\b[v\u007f._W\u0012l\u0006[lb)D[\u0004Uvu8\u0006[lb)D[".length();
                var1_4 = 8;
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
                    var5[var3_1++] = NoFov.c(NoFov.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "VvW2]\u0003^VG";
                    var4_3 = "VvW2]\u0003^VG".length();
                    var1_4 = 5;
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
                    var5[var3_1++] = NoFov.c(NoFov.a(v1));
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
        NoFov.k = var5;
    }

    public boolean isConstant() {
        return this.mode.is("Constant");
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
                    case 0 -> 24;
                    case 1 -> 25;
                    case 2 -> 17;
                    case 3 -> 93;
                    case 4 -> 43;
                    case 5 -> 54;
                    default -> 124;
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
            cArray[0] = (char)(cArray[0] ^ 0x7C);
        }
        return cArray;
    }
}

