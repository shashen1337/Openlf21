/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c;

import java.awt.Color;
import java.util.function.Consumer;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.gui.c.j;
import lf21.render.Renderer;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.AbstractClientPlayer;

public class g
extends ClientBase {
    private float a = 0.0f;
    private boolean b = false;
    private final j c;
    private static final String[] d;

    public g(Consumer<Float> consumer) {
        this.c = new j(consumer);
    }

    public void a(GuiGraphics guiGraphics, int n2, int n3, int n4, int n5, float f2, float f3) {
        if (g.mc.player == null) {
            return;
        }
        try {
            int n6 = (int)(20.0f * f2);
            int n7 = (int)(20.0f * f2);
            int n8 = (int)(10.0f * f2);
            int n9 = (int)(20.0f * f2);
            float f4 = 6.0f * f2;
            int n10 = n2 + n7;
            int n11 = n3 + n9;
            String string = this.a();
            String string2 = this.b();
            int n12 = n10 + n6 + n8 - (int)(11.0f * f2);
            int n13 = n11 + n6 / 2 - (int)(10.0f * f2);
            int n14 = n12 - n6 - (int)(5.0f * f2);
            int n15 = n13 - (int)(8.0f * f2);
            this.a(n14, n15, n4, n5, n6);
            this.c();
            Renderer.a(drawContext -> {
                if (this.a > 0.01f) {
                    int n7 = new Color(255, 255, 255, (int)(30.0f * this.a * f3)).getRGB();
                    RenderUtil.b(guiGraphics.pose(), (float)(n14 - 2), (float)(n15 - 2), (float)(n6 + 4), (float)(n6 + 4), f4 + 1.0f, n7);
                }
                if (g.mc.player instanceof AbstractClientPlayer) {
                    y_0.a((AbstractClientPlayer)g.mc.player, (float)n14, (float)n15, (float)n6, (float)n6, f3, f4);
                }
                u_0 u_02 = t_0.l(14.0f * f2);
                int n8 = new Color(255, 255, 255, (int)(100.0f * f3)).getRGB();
                T.a(string, (float)n12, (float)n13, u_02, this.a(-1, f3), n8, 8.0f * f2);
                float f5 = y_0.a(string, u_02);
                u_0 u_03 = t_0.k(11.0f * f2);
                float f6 = y_0.a(string2, u_03);
                float f7 = f6 + 8.0f * f2;
                float f8 = 12.0f * f2;
                float f9 = (float)n12 + f5 + 8.0f * f2;
                float f10 = (float)n13 + (u_02.d().f() - f8) / 2.0f;
                Color color = this.a(string2);
                int n9 = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(180.0f * f3)).getRGB();
                RenderUtil.b(guiGraphics.pose(), f9 - 1.0f * f2, f10 - 1.0f * f2, f7 + 2.0f * f2, f8 + 2.0f * f2, 5.0f * f2, this.a(n9, f3 * 0.35f));
                RenderUtil.b(guiGraphics.pose(), f9, f10, f7, f8, 4.0f * f2, this.a(color.getRGB(), f3));
                int n10 = new Color(255, 255, 255, (int)(120.0f * f3)).getRGB();
                float f11 = f9 + (f7 - f6) / 2.0f;
                float f12 = f10 + (f8 - u_03.d().f()) / 2.0f + 0.5f * f2;
                T.a(string2, f11, f12, u_03, this.a(-1, f3), n10, 5.0f * f2);
            });
            this.c.a(guiGraphics, n4, n5, f2, f3);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
    }

    private String a() {
        return LF21Client.username != null && !LF21Client.username.isEmpty() ? LF21Client.username : "Unknown";
    }

    private String b() {
        return "User";
    }

    public boolean a(int n2, int n3, int n4, int n5, float f2) {
        int n6 = (int)(20.0f * f2);
        int n7 = (int)(20.0f * f2);
        int n8 = (int)(10.0f * f2);
        int n9 = (int)(20.0f * f2);
        int n10 = n2 + n7;
        int n11 = n3 + n9;
        int n12 = n10 + n6 + n8 - (int)(11.0f * f2);
        int n13 = n11 + n6 / 2 - (int)(10.0f * f2);
        int n14 = n12 - n6 - (int)(5.0f * f2);
        int n15 = n13 - (int)(8.0f * f2);
        return n4 >= n14 && n4 <= n14 + n6 && n5 >= n15 && n5 <= n15 + n6;
    }

    public boolean b(int n2, int n3, int n4, int n5, float f2) {
        if (this.c.d() && this.c.a(n4, n5, f2)) {
            return true;
        }
        if (this.a(n2, n3, n4, n5, f2)) {
            this.c.e();
            return true;
        }
        return false;
    }

    private Color a(String string) {
        String string2 = string.toLowerCase();
        int n2 = -1;
        switch (string2.hashCode()) {
            case 106164915: {
                String[] stringArray = d;
                if (!string2.equals("owner")) break;
                n2 = 0;
                break;
            }
            case 92668751: {
                String[] stringArray = d;
                if (!string2.equals("admin")) break;
                n2 = 1;
                break;
            }
            case 3020272: {
                String[] stringArray = d;
                if (!string2.equals("beta")) break;
                n2 = 2;
                break;
            }
            case 116765: {
                String[] stringArray = d;
                if (!string2.equals("vip")) break;
                n2 = 3;
                break;
            }
            case -318452137: {
                if (!string2.equals("premium")) break;
                n2 = 4;
            }
        }
        switch (n2) {
            case 0: {
                return new Color(220, 53, 69);
            }
            case 1: {
                return new Color(255, 193, 7);
            }
            case 2: {
                return new Color(108, 117, 225);
            }
            case 3: {
                return new Color(40, 167, 69);
            }
            case 4: {
                return new Color(102, 16, 242);
            }
        }
        return new Color(108, 117, 125);
    }

    private void a(int n2, int n3, int n4, int n5, int n6) {
        this.b = n4 >= n2 && n4 <= n2 + n6 && n5 >= n3 && n5 <= n3 + n6;
    }

    private void c() {
        this.a = this.b ? lf21.utils.b.g.a(this.a, 1.0f, 0.12f) : lf21.utils.b.g.a(this.a, 0.0f, 0.12f);
    }

    public void a(int n2, int n3) {
        this.c.b(n2, n3);
    }

    public void d() {
        this.c.c();
    }

    public boolean e() {
        return this.c.d();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "#WOf\u0007\u0006VOy>6\u0011\u0005\u0017@G}9\u0007#JAz84\u0012\u0005\u0019SDq%";
                var4_3 = "#WOf\u0007\u0006VOy>6\u0011\u0005\u0017@G}9\u0007#JAz84\u0012\u0005\u0019SDq%".length();
                var1_4 = 4;
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
                    var5[var3_1++] = g.b(g.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0014A^u\u0003\u0000MZ";
                    var4_3 = "\u0014A^u\u0003\u0000MZ".length();
                    var1_4 = 4;
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
                    var5[var3_1++] = g.b(g.b(v1));
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
        g.d = var5;
    }

    private static char[] b(String string) {
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
    private static String b(char[] cArray) {
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
                    case 0 -> 118;
                    case 1 -> 36;
                    case 2 -> 42;
                    case 3 -> 20;
                    case 4 -> 87;
                    case 5 -> 67;
                    default -> 124;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

