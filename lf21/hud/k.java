/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.util.Locale;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.hud.HudElement;
import lf21.render.D;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;

public final class k
extends HudElement {
    private static final u_0 I;
    private static final String[] J;

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
                    case 0 -> 35;
                    case 1 -> 7;
                    case 2 -> 58;
                    case 3 -> 37;
                    case 4 -> 11;
                    case 5 -> 96;
                    default -> 47;
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
                var2_2 = "wbBQ\u0006sk[\\n\u0012\u0004mfW@";
                var4_3 = "wbBQ\u0006sk[\\n\u0012\u0004mfW@".length();
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
                    var5[var3_1++] = k.d(k.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "sk[\\n\u0012\u0004wbBQ";
                    var4_3 = "sk[\\n\u0012\u0004wbBQ".length();
                    var1_4 = 6;
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
                    var5[var3_1++] = k.d(k.c(v1));
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
        k.J = var5;
        k.I = t_0.a(17.0f);
    }

    private String y() {
        String string;
        if (mc.getConnection() != null && (string = mc.getConnection().getPlayerInfo(k.mc.player.getUUID())) != null && string.getProfile() != null) {
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
        return (string = k.mc.player.getGameProfile().getName()) == null || string.isBlank() ? "Player" : string.trim();
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        boolean bl = k.mc.screen instanceof lf21.gui.k;
        if (!bl && (k.mc.player == null || k.mc.level == null || k.mc.options.renderDebug)) {
            return;
        }
        String string = bl || k.mc.player == null ? "Player" : this.y();
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

    @Override
    public void a() {
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x2F);
        }
        return cArray;
    }

    public k() {
        String[] stringArray = J;
        super("Name");
        this.setX(12.0f);
        this.setY(112.0f);
        this.e(88.0f);
        this.f(28.0f);
        this.a("Text");
        this.setEnabled(true);
    }
}

