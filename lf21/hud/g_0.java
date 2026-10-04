/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.awt.Color;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.gui.k;
import lf21.hud.E;
import lf21.hud.HudElement;
import lf21.modules.impl.combat.AntiKB;
import lf21.modules.impl.combat.a.c;
import lf21.modules.impl.combat.a.f;
import lf21.render.D;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.RenderUtil;

/*
 * Renamed from lf21.hud.g
 */
public final class g_0
extends HudElement {
    private static final u_0 I;
    private static final float J;
    private static final float K;
    private static final String[] L;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                g_0.K = 20.0f;
                g_0.J = 120.0f;
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "MB\tFEr\u0005@o\u0002MB\u0005MB\tFE\u0005MB\tFE\u0005@o\u0002MB";
                var4_3 = "MB\tFEr\u0005@o\u0002MB\u0005MB\tFE\u0005MB\tFE\u0005@o\u0002MB".length();
                var1_4 = 6;
                var0_5 = -1;
lbl9:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl14:
                // 1 sources

                while (true) {
                    var5[var3_1++] = g_0.d(g_0.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\\|\u000fO\\7J\u007f\b\\|\u000fO\\7J\u007f";
                    var4_3 = "\\|\u000fO\\7J\u007f\b\\|\u000fO\\7J\u007f".length();
                    var1_4 = 8;
                    var0_5 = -1;
lbl23:
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
lbl28:
                // 1 sources

                while (true) {
                    var5[var3_1++] = g_0.d(g_0.c(v1));
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
lbl39:
                // 1 sources

                ** continue;
            }
        }
        g_0.L = var5;
        g_0.I = t_0.a(14.0f);
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
                    case 0 -> 12;
                    case 1 -> 14;
                    case 2 -> 96;
                    case 3 -> 40;
                    case 4 -> 46;
                    case 5 -> 82;
                    default -> 57;
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
            cArray[0] = (char)(cArray[0] ^ 0x39);
        }
        return cArray;
    }

    private static /* synthetic */ void a(int n2, float f2, float f3, float f4, float f5, String string) {
        try (D d2 = new D();){
            d2.a(true);
            d2.a(n2 << 24 | 0xFFFFFF);
            y_0.a(f2 + f3 * 0.5f, f4 + f5 * 0.5f + 2.0f, string, I, d2);
        }
    }

    public g_0() {
        String[] stringArray = L;
        super("ALink");
        float f2 = mc.getWindow().getGuiScaledWidth();
        float f3 = mc.getWindow().getGuiScaledHeight();
        this.setX(Math.max(2.0f, f2 * 0.5f - 55.0f));
        this.setY(Math.max(2.0f, f3 * 0.5f + 50.0f));
        this.e(120.0f);
        this.f(20.0f);
        this.a("Label");
        this.b("Progress");
        this.setEnabled(true);
    }

    @Override
    public void a() {
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        boolean bl = g_0.mc.screen instanceof k;
        c c2 = c.c;
        if (c2 == null) {
            return;
        }
        if (!(bl || AntiKB.j != null && AntiKB.j.isEnabled() && !AntiKB.j.j() && AntiKB.j.h())) {
            return;
        }
        f f4 = c2.a(bl);
        f f5 = !bl && (Boolean)AntiKB.j.Q.getValue() != false ? new f(1.0f, f4.b(), f4.c()) : f4;
        float f6 = f5.a();
        if (!bl && f6 < 0.002f) {
            return;
        }
        float f7 = 120.0f;
        float f8 = 20.0f;
        this.e(f7);
        this.f(f8);
        int n2 = Math.max(0, Math.min(255, Math.round(230.0f * f6)));
        RenderUtil.a(render2DEvent.a(), f2, f3, f7, f8, 6.0f, 16.0f, 0.9f * f6, 0);
        Renderer.render(render2DEvent.b(), drawContext -> {
            Object object;
            Object object2;
            block9: {
                String[] stringArray;
                block8: {
                    object2 = new D();
                    try {
                        ((D)object2).a(true);
                        ((D)object2).a(new Color(16, 16, 16, Math.min(185, n2)).getRGB());
                        drawContext.a(Q.a(f2, f3, f7, f8, 6.0f), (D)object2);
                        stringArray = L;
                        E e2 = HudElement.a("Progress", f2 + 3.0f, f3 + 3.0f, f7 - 6.0f, f8 - 6.0f);
                        ((D)object2).a(Math.min(130, n2) << 24 | 0x303438);
                        drawContext.a(Q.a(e2.a(), e2.b(), e2.c(), e2.d(), 4.0f), (D)object2);
                        ((D)object2).a(n2 << 24 | 0xA7ADB2);
                        drawContext.a(Q.a(e2.a(), e2.b(), Math.max(0.0f, e2.c() * f5.b()), e2.d(), 4.0f), (D)object2);
                    }
                    finally {
                        ((D)object2).close();
                    }
                    if (!f5.c()) break block8;
                    object = "ALink " + Math.round(f5.b() * 100.0f) + "%";
                    break block9;
                }
                stringArray = L;
                object = "ALink";
            }
            object2 = object;
            this.a("Label", () -> g_0.a(n2, f2, f7, f3, f8, (String)object2));
        });
    }
}

