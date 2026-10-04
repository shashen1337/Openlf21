/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

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
import net.minecraft.client.multiplayer.ClientPacketListener;

public final class m
extends HudElement {
    private static final u_0 I;
    private ClientPacketListener J;
    private long K;
    private static final String[] L;

    @Override
    public void a() {
    }

    public m() {
        String[] stringArray = L;
        super("Playtime");
        this.setX(12.0f);
        this.setY(145.0f);
        this.e(126.0f);
        this.f(28.0f);
        this.a("Text");
        this.setEnabled(true);
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
                    case 0 -> 44;
                    case 1 -> 94;
                    case 2 -> 89;
                    case 3 -> 11;
                    case 4 -> 40;
                    case 5 -> 81;
                    default -> 95;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "\tnko\u0012to\u001e:c.\u0018c;\u0004x;!\u007f\u0004x;!\u007f";
                var4_3 = "\tnko\u0012to\u001e:c.\u0018c;\u0004x;!\u007f\u0004x;!\u007f".length();
                var1_4 = 14;
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
                    var5[var3_1++] = lf21.hud.m.d(lf21.hud.m.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "|28rM5\u007f\f\b|28r\\82I";
                    var4_3 = "|28rM5\u007f\f\b|28r\\82I".length();
                    var1_4 = 8;
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
                    var5[var3_1++] = lf21.hud.m.d(lf21.hud.m.c(v1));
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
        lf21.hud.m.L = var5;
        lf21.hud.m.I = t_0.a(17.55f);
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
    }

    private static String b(long l2) {
        long l3 = l2 / 1000L;
        long l4 = l3 / 3600L;
        long l5 = l3 / 60L % 60L;
        long l6 = l3 % 60L;
        return String.format("%02d:%02d:%02d", l4, l5, l6);
    }

    public void y() {
        if (mc.getCurrentServer() == null || mc.isLocalServer() || mc.getConnection() == null) {
            this.J = null;
            this.K = 0L;
            return;
        }
        ClientPacketListener clientPacketListener = mc.getConnection();
        if (clientPacketListener != this.J) {
            this.J = clientPacketListener;
            this.K = System.currentTimeMillis();
        }
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x5F);
        }
        return cArray;
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        boolean bl = lf21.hud.m.mc.screen instanceof k;
        if (!bl && (lf21.hud.m.mc.player == null || lf21.hud.m.mc.level == null || lf21.hud.m.mc.options.renderDebug)) {
            return;
        }
        if (!bl) {
            this.y();
        }
        long l2 = bl ? 754000L : (this.J == null ? 0L : Math.max(0L, System.currentTimeMillis() - this.K));
        String string = "Played  " + lf21.hud.m.b(l2);
        float f4 = I.b(string) + 20.0f;
        float f5 = this.d(f4);
        float f6 = 28.0f;
        this.e(f5);
        this.f(f6);
        RenderUtil.a(render2DEvent.a(), f2, f3, f5, f6, 9.0f, 26.0f, 1.0f, 0);
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(true);
                d2.a(-1291845632);
                drawContext.a(Q.a(f2, f3, f5, f6, 9.0f), d2);
            }
            this.a("Text", () -> y_0.a(string, f2 + 10.0f, f3 + (f6 - (float)y_0.a(I)) * 0.5f, I, -723208));
        });
    }
}

