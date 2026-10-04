/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.gui.k;
import lf21.hud.HudElement;
import lf21.modules.impl.world.Teams;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.h_0;
import lf21.settings.impl.NumberSetting;
import lf21.utils.render.RenderUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public final class o
extends HudElement {
    private static final String[] K;
    private final NumberSetting I;
    private static final float J;

    public o() {
        String[] stringArray = K;
        super("Radar");
        this.I = new NumberSetting("Range", 48, 8, 128, 1);
        this.setX(12.0f);
        this.setY(70.0f);
        this.e(116.0f);
        this.f(116.0f);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        J = 116.0f;
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "_Vu?C\u0005_V\u007f9T";
        int n3 = "_Vu?C\u0005_V\u007f9T".length();
        int n4 = 5;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.hud.o.d(lf21.hud.o.c(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                K = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
        if (mc == null) return;
        if (lf21.hud.o.mc.player == null) return;
        if (lf21.hud.o.mc.level == null) return;
        if (lf21.hud.o.mc.options.renderDebug) {
            return;
        }
        this.e(116.0f);
        this.f(116.0f);
        DrawContext drawContext = glRenderEvent.c();
        float f4 = f2 + 58.0f;
        float f5 = f3 + 58.0f;
        float f6 = 49.0f;
        RenderUtil.a(glRenderEvent.b(), f2, f3, 116.0f, 116.0f, 8.0f, 10.0f, 0.58f, 0);
        try (D d2 = new D();){
            int n2;
            d2.a(true);
            d2.a(h_0.FILL);
            d2.a(-1257105635);
            drawContext.a(Q.a(f2, f3, 116.0f, 116.0f, 10.0f), d2);
            d2.a(h_0.STROKE).b(1.15f).a(-1971154789);
            drawContext.a(Q.a(f2 + 0.6f, f3 + 0.6f, 114.8f, 114.8f, 9.4f), d2);
            for (n2 = 1; n2 <= 3; ++n2) {
                float f7 = f6 * (float)n2 / 3.0f;
                drawContext.a(f4 - f7, f5 - f7, f4 + f7, f5 + f7, 0.0f, 360.0f, false, d2);
            }
            d2.a(-2143954480).b(1.35f);
            drawContext.c(f4, f5, f4, f5 - f6, d2);
            d2.a(h_0.FILL).a(-1445646);
            drawContext.a(Q.a(f4 - 2.3f, f5 - 2.3f, 4.6f, 4.6f, 2.3f), d2);
            n2 = 0;
            double d3 = ((Number)this.I.getValue()).doubleValue();
            double d4 = Math.toRadians(lf21.hud.o.mc.player.getYRot());
            double d5 = Math.cos(d4);
            double d6 = Math.sin(d4);
            for (Player player : lf21.hud.o.mc.level.players()) {
                double d7;
                double d8;
                double d9;
                if (player == lf21.hud.o.mc.player || player.isSpectator() || !player.isAlive() || (d9 = Math.sqrt((d8 = player.getX() - lf21.hud.o.mc.player.getX()) * d8 + (d7 = player.getZ() - lf21.hud.o.mc.player.getZ()) * d7)) > d3) continue;
                double d10 = -d8 * d5 - d7 * d6;
                double d11 = d7 * d5 - d8 * d6;
                float f8 = f4 + (float)(d10 / d3 * (double)f6);
                float f9 = f5 - (float)(d11 / d3 * (double)f6);
                boolean bl = lf21.hud.o.mc.player.isAlliedTo((Entity)player) || Teams.j != null && Teams.a((Entity)player);
                d2.a(bl ? -13250449 : -49841);
                drawContext.a(Q.a(f8 - 2.5f, f9 - 2.5f, 5.0f, 5.0f, 2.5f), d2);
                ++n2;
            }
            if (n2 != 0) return;
            if (!(lf21.hud.o.mc.screen instanceof k)) return;
            d2.a(-49841);
            drawContext.a(Q.a(f4 + 24.0f, f5 - 30.0f, 5.0f, 5.0f, 2.5f), d2);
            d2.a(-13250449);
            drawContext.a(Q.a(f4 - 22.0f, f5 + 15.0f, 5.0f, 5.0f, 2.5f), d2);
            return;
        }
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
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
                    case 0 -> 13;
                    case 1 -> 55;
                    case 2 -> 17;
                    case 3 -> 94;
                    case 4 -> 49;
                    case 5 -> 81;
                    default -> 69;
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
            cArray[0] = (char)(cArray[0] ^ 0x45);
        }
        return cArray;
    }

    @Override
    public void a() {
    }
}

