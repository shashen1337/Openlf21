/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.awt.Color;
import java.util.Locale;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.gui.k;
import lf21.hud.HudElement;
import lf21.hud.y;
import lf21.render.D;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;

public final class l
extends HudElement {
    private static final u_0 I;
    private ClientPacketListener M;
    private static final float K;
    private static final u_0 J;
    private static final String[] O;
    private long N;
    private final y L;

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
                    case 0 -> 25;
                    case 1 -> 80;
                    case 2 -> 102;
                    case 3 -> 31;
                    case 4 -> 81;
                    case 5 -> 73;
                    default -> 126;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static String b(long l2) {
        long l3 = l2 / 1000L;
        long l4 = l3 / 3600L;
        long l5 = l3 / 60L % 60L;
        long l6 = l3 % 60L;
        return String.format("%02d:%02d:%02d", l4, l5, l6);
    }

    private String z() {
        String string;
        if (mc.getConnection() != null && (string = mc.getConnection().getPlayerInfo(l.mc.player.getUUID())) != null && string.getProfile() != null) {
            String string2;
            Component component = string.getTabListDisplayName();
            if (component != null && !(string2 = component.getString().trim()).isEmpty()) {
                String string3 = null;
                for (PlayerInfo playerInfo : mc.getConnection().getOnlinePlayers()) {
                    String string4;
                    if (playerInfo.getProfile() == null || (string4 = playerInfo.getProfile().getName()) == null || string4.isBlank() || !string2.toLowerCase(Locale.ROOT).contains(string4.toLowerCase(Locale.ROOT)) || string3 != null && string4.length() <= string3.length()) continue;
                    string3 = string4;
                }
                return string3 != null ? string3 : string2;
            }
            string2 = string.getProfile().getName();
            if (string2 != null && !string2.isBlank()) {
                return string2.trim();
            }
        }
        return (string = l.mc.player.getGameProfile().getName()) == null || string.isBlank() ? "Player" : string.trim();
    }

    public l() {
        String[] stringArray = O;
        super("NameInfo");
        this.L = new y();
        this.setX(12.0f);
        this.setY(112.0f);
        this.e(146.0f);
        this.f(42.0f);
        this.a("Name");
        this.a("Playtime");
    }

    public void y() {
        if (mc.getCurrentServer() == null || mc.isLocalServer() || mc.getConnection() == null) {
            this.M = null;
            this.N = 0L;
            return;
        }
        ClientPacketListener clientPacketListener = mc.getConnection();
        if (clientPacketListener != this.M) {
            this.M = clientPacketListener;
            this.N = System.currentTimeMillis();
        }
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        String string;
        boolean bl = l.mc.screen instanceof k;
        if (!bl && (l.mc.player == null || l.mc.level == null || l.mc.options.renderDebug)) {
            return;
        }
        if (!bl) {
            this.y();
        }
        if (bl || l.mc.player == null) {
            String[] stringArray = O;
            v0 = "Player";
        } else {
            v0 = string = this.z();
        }
        long l2 = bl ? 754000L : (this.M == null ? 0L : Math.max(0L, System.currentTimeMillis() - this.N));
        String string2 = "Played  " + l.b(l2);
        float f4 = Math.max(146.0f, Math.max(I.b(string), J.b(string2)) + 20.0f + 28.0f + 8.0f);
        float f5 = this.d(f4);
        float f6 = 42.0f;
        this.e(f5);
        this.f(f6);
        RenderUtil.a(render2DEvent.a(), f2, f3, f5, f6, 9.0f, 26.0f, 1.0f, 0);
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(true);
                d2.a(new Color(0, 0, 0, 179).getRGB());
                drawContext.a(Q.a(f2, f3, f5, f6, 9.0f), d2);
            }
            String[] stringArray = O;
            this.a("Name", () -> y_0.a(string, f2 + 10.0f, f3 + 11.0f, I, -723208));
            this.a("Playtime", () -> y_0.a(string2, f2 + 10.0f, f3 + 29.0f, J, -5393478));
        });
        this.L.a(render2DEvent, f2 + f5 - 28.0f - 9.0f, f3 + (f6 - 28.0f) * 0.5f, 28.0f);
    }

    @Override
    public void a() {
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                l.K = 28.0f;
                var5 = new String[9];
                var3_1 = 0;
                var2_2 = "I<\u0007f4;\bI<\u0007f% \u0013|\bI<\u0007f% \u0013|\u0004W1\u000bz\u0006I<\u0007f4;\u000e<`T{klN+4\\:a{\u001a\u0004W1\u000bz";
                var4_3 = "I<\u0007f4;\bI<\u0007f% \u0013|\bI<\u0007f% \u0013|\u0004W1\u000bz\u0006I<\u0007f4;\u000e<`T{klN+4\\:a{\u001a\u0004W1\u000bz".length();
                var1_4 = 6;
                var0_5 = -1;
lbl8:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl13:
                // 1 sources

                while (true) {
                    var5[var3_1++] = l.d(l.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "W1\u000bz\u0018'\u0018v\bI<\u0007f4-^9";
                    var4_3 = "W1\u000bz\u0018'\u0018v\bI<\u0007f4-^9".length();
                    var1_4 = 8;
                    var0_5 = -1;
lbl22:
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
lbl27:
                // 1 sources

                while (true) {
                    var5[var3_1++] = l.d(l.c(v1));
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
lbl38:
                // 1 sources

                ** continue;
            }
        }
        l.O = var5;
        l.I = t_0.a(17.55f);
        l.J = t_0.a(15.6f);
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x7E);
        }
        return cArray;
    }
}

