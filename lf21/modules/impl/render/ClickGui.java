/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.gui.l;
import lf21.gui.m;
import lf21.gui.n;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.ModeSetting;
import net.minecraft.client.gui.screens.Screen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClickGui
extends Module {
    private static final String[] l;
    public static final Logger j;
    public final ModeSetting mode;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[10];
                var3_1 = 0;
                var2_2 = "\r\u001b#N[Ec\u0007W%]UlS*W9XSaS=\u0004,X\\nO\u0004\u0003\u0018.H\u0003\u0000\u0012=\u0003\u0001\u001b.\u0005\u001e\u0016$H\\\u0003\u0000\u0012=\b\r\u001b#N[EC'\u0003\u0001\u001b.";
                var4_3 = "\r\u001b#N[Ec\u0007W%]UlS*W9XSaS=\u0004,X\\nO\u0004\u0003\u0018.H\u0003\u0000\u0012=\u0003\u0001\u001b.\u0005\u001e\u0016$H\\\u0003\u0000\u0012=\b\r\u001b#N[EC'\u0003\u0001\u001b.".length();
                var1_4 = 28;
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
                    var5[var3_1++] = ClickGui.c(ClickGui.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u001e\u0016$H\\\u0016\u000b\u00058BB\"Y>\u0012$D^e\u0016\r\u001b#N[Ec\u0007";
                    var4_3 = "\u001e\u0016$H\\\u0016\u000b\u00058BB\"Y>\u0012$D^e\u0016\r\u001b#N[Ec\u0007".length();
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
                    var5[var3_1++] = ClickGui.c(ClickGui.a(v1));
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
        ClickGui.l = var5;
        ClickGui.j = LogManager.getLogger(ClickGui.class);
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
                    case 0 -> 78;
                    case 1 -> 119;
                    case 2 -> 74;
                    case 3 -> 45;
                    case 4 -> 48;
                    case 5 -> 2;
                    default -> 54;
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
            cArray[0] = (char)(cArray[0] ^ 0x36);
        }
        return cArray;
    }

    @Override
    protected void onEnable() {
        try {
            block10: {
                block11: {
                    block9: {
                        if (!this.mode.is("Old")) break block9;
                        mc.setScreen((Screen)new m());
                        break block10;
                    }
                    if (!this.mode.is("Panel")) break block11;
                    mc.setScreen((Screen)n.a);
                    break block10;
                }
                mc.setScreen((Screen)new l());
            }
            String[] stringArray = l;
            j.info("ClickGUI opened successfully");
        }
        catch (Exception exception) {
            j.error("Error opening ClickGUI", (Throwable)exception);
        }
        finally {
            this.setEnabled(false);
        }
    }

    public ClickGui() {
        String[] stringArray = l;
        super("ClickGui", Category.RENDER, 344);
        this.mode = new ModeSetting("Mode", "Old", "Panel", "New").withDefault("New");
    }
}

