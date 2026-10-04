/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.awt.Color;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.gui.k;
import lf21.hud.HudElement;
import lf21.render.D;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.RenderUtil;

public final class h
extends HudElement {
    private static final u_0 I;
    private static final String[] J;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "\"\u001c\u001c3\u001bvx#\u001c\u001b5\u0003v\u0002Z\u0011\u001f4\u0015\u000b9SBsG?6\u001bHHr";
                var4_3 = "\"\u001c\u001c3\u001bvx#\u001c\u001b5\u0003v\u0002Z\u0011\u001f4\u0015\u000b9SBsG?6\u001bHHr".length();
                var1_4 = 19;
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
                    var5[var3_1++] = h.d(h.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\"\u001c\b/\u00120xZe\r$\rg>Z\u001cw!\u0006xi\u001c\u000b9SBsG?6\u001bHHr";
                    var4_3 = "\"\u001c\b/\u00120xZe\r$\rg>Z\u001cw!\u0006xi\u001c\u000b9SBsG?6\u001bHHr".length();
                    var1_4 = 22;
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
                    var5[var3_1++] = h.d(h.c(v1));
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
        h.J = var5;
        h.I = t_0.a(15.6f);
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String d(char[] cArray) {
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
                    case 0 -> 122;
                    case 1 -> 60;
                    case 2 -> 45;
                    case 3 -> 1;
                    case 4 -> 35;
                    case 5 -> 86;
                    default -> 88;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x58);
        }
        return cArray;
    }

    public h() {
        String[] stringArray = J;
        super("Coordinates");
        this.setX(12.0f);
        this.setY(164.0f);
        this.e(126.0f);
        this.f(26.0f);
        this.a("Coordinates");
    }

    @Override
    public void a() {
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        String string;
        boolean bl = h.mc.screen instanceof k;
        if (!bl && (h.mc.player == null || h.mc.level == null || h.mc.options.renderDebug)) {
            return;
        }
        if (bl || h.mc.player == null) {
            String[] stringArray = J;
            string = "X 128  Y 64  Z -256";
        } else {
            string = String.format("X %.1f  Y %.1f  Z %.1f", h.mc.player.getX(), h.mc.player.getY(), h.mc.player.getZ());
        }
        String string2 = string;
        float f4 = this.d(I.b(string2) + 20.0f);
        this.e(f4);
        this.f(26.0f);
        RenderUtil.a(render2DEvent.a(), f2, f3, f4, 26.0f, 9.0f, 26.0f, 1.0f, 0);
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(true);
                d2.a(new Color(0, 0, 0, 179).getRGB());
                drawContext.a(Q.a(f2, f3, f4, 26.0f, 9.0f), d2);
            }
            this.a("Coordinates", () -> y_0.a(string2, f2 + 10.0f, f3 + 11.0f, I, -723208));
        });
    }
}

