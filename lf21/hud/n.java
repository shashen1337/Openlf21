/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lf21.event.EventTarget;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.TickEvent;
import lf21.hud.B;
import lf21.hud.HudElement;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.b.f;
import lf21.utils.render.a;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;

public class n
extends HudElement {
    private final D O;
    private final u_0 K;
    private static final String[] Q;
    private final D P;
    private final D M;
    private final D N;
    private final List<B> I = new ArrayList<B>();
    final u_0 L;
    final u_0 J = t_0.b(16.0f);

    private void a(DrawContext drawContext, float f2, float f3) {
        if (drawContext == null) {
            return;
        }
        this.I.removeIf(B::f);
        if (this.I.isEmpty()) {
            this.e(0.0f);
            this.f(0.0f);
            return;
        }
        float f4 = 2.0f;
        float f5 = 18.0f;
        float f6 = 4.5f;
        float f7 = 100.0f;
        float f8 = f3;
        float f9 = 0.0f;
        for (B b2 : this.I) {
            b2.g();
            float f10 = 20.0f;
            float f11 = 5.0f;
            float f12 = y_0.a(b2.d, this.J);
            float f13 = y_0.a(b2.e, this.L);
            float f14 = Math.max(f7, f10 + f12 + f13 + f11 * 3.0f);
            if (b2.k) {
                b2.k = false;
                b2.g.a(f8);
                b2.a(f5);
            }
            b2.g.a((double)f8, 0.15, f.h);
            float f15 = b2.h.e();
            float f16 = b2.g.e();
            float f17 = b2.i.e();
            if (f14 > f9) {
                f9 = f14;
            }
            if (f15 <= 0.01f) {
                f8 += f17 + (f17 > 0.0f ? f4 : 0.0f);
                continue;
            }
            int n2 = this.a(b2.b.getEffect());
            float f18 = f14 - f10;
            float f19 = f2 + f10;
            this.N.a(a.a(30, 30, 35, (int)(80.0f * f15)));
            drawContext.a(lf21.render.Q.a(f19, f16, f18, f17, new float[]{0.0f, 0.0f, 4.5f, 4.5f, 4.5f, 4.5f, 0.0f, 0.0f}), this.N);
            float f20 = b2.b.isInfiniteDuration() ? 1.0f : (float)b2.b.getDuration() / (float)b2.c;
            this.O.a(a.a(n2 >> 16 & 0xFF, n2 >> 8 & 0xFF, n2 & 0xFF, (int)(140.0f * f15)));
            if (f20 > 0.0f) {
                drawContext.a(lf21.render.Q.a(f19, f16, f18 * f20, f17, new float[]{0.0f, 0.0f, f6 * f20, f6 * f20, f6 * f20, f6 * f20, 0.0f, 0.0f}), this.O);
            }
            int n3 = n2 >> 16 & 0xFF;
            int n4 = n2 >> 8 & 0xFF;
            int n5 = n2 & 0xFF;
            this.M.a(a.a((int)((float)n3 * 0.7f + 76.5f), (int)((float)n4 * 0.7f + 76.5f), (int)((float)n5 * 0.7f + 76.5f), (int)(160.0f * f15)));
            drawContext.a(lf21.render.Q.a(f2, f16, f10, f17, new float[]{4.5f, 4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 4.5f, 4.5f}), this.M);
            this.P.a(a.a(255, 255, 255, (int)(185.0f * f15)));
            float f21 = f16 + (f17 - (float)y_0.a(this.J)) / 2.0f;
            float f22 = y_0.a(b2.f, this.K);
            y_0.a(b2.f, f2 + (f10 - f22) / 2.0f, f21, this.K, this.P, false);
            y_0.a(b2.d, f19 + f11, f21, this.J, this.P, false);
            f13 = y_0.a(b2.e, this.L);
            y_0.a(b2.e, f2 + f14 - f13 - f11, f21 + 1.0f, this.L, this.P, false);
            f8 += f17 + (f17 > 0.0f ? f4 : 0.0f);
        }
        this.e(f9);
        this.f(Math.max(0.0f, f8 - f3 - f4));
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x21);
        }
        return cArray;
    }

    public n() {
        super("Effects");
        this.K = t_0.k(16.0f);
        this.L = t_0.k(14.0f);
        this.M = new D();
        this.N = new D();
        this.O = new D();
        this.P = new D();
        this.setX(10.0f);
        this.setY(50.0f);
        this.setEnabled(true);
        this.M.a(true);
        this.N.a(true);
        this.O.a(true);
        this.P.a(true);
    }

    String a(MobEffectInstance mobEffectInstance) {
        if (mobEffectInstance.isInfiniteDuration() || mobEffectInstance.getDuration() > 72000) {
            return "\u221e";
        }
        return MobEffectUtil.formatDuration((MobEffectInstance)mobEffectInstance, (float)1.0f).getString();
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (lf21.hud.n.mc.player == null) {
            if (this.I != null && !this.I.isEmpty()) {
                this.I.forEach(B::e);
            }
            return;
        }
        Collection collection = lf21.hud.n.mc.player.getActiveEffects();
        this.I.stream().filter(b2 -> collection.stream().noneMatch(mobEffectInstance -> mobEffectInstance.getEffect() == b2.b())).forEach(B::e);
        for (MobEffectInstance mobEffectInstance : collection) {
            Optional<B> optional = this.I.stream().filter(b2 -> !b2.j && b2.b() == mobEffectInstance.getEffect()).findFirst();
            if (optional.isEmpty()) {
                B b4 = new B(this, mobEffectInstance);
                this.I.add(b4);
                continue;
            }
            optional.get().a(mobEffectInstance);
        }
        this.I.sort((b2, b3) -> Float.compare(b3.d(), b2.d()));
    }

    @Override
    public void a() {
    }

    private int a(MobEffect mobEffect) {
        int n2 = mobEffect.getColor();
        return n2 != 0 ? n2 : 3376639;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "pR\u0007|b\u000bt.oR";
                var4_3 = "pR\u0007|b\u000bt.oR".length();
                var1_4 = 2;
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
                    var5[var3_1++] = lf21.hud.n.d(lf21.hud.n.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "pM\u0003pM$";
                    var4_3 = "pM\u0003pM$".length();
                    var1_4 = 2;
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
                    var5[var3_1++] = lf21.hud.n.d(lf21.hud.n.c(v1));
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
        lf21.hud.n.Q = var5;
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
                    case 0 -> 57;
                    case 1 -> 4;
                    case 2 -> 109;
                    case 3 -> 17;
                    case 4 -> 77;
                    case 5 -> 27;
                    default -> 33;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    public void onEnable() {
        if (this.I != null) {
            this.I.clear();
        }
    }

    String a(int n2) {
        return switch (n2) {
            case 1 -> "I";
            case 2 -> {
                String[] var2_2 = Q;
                yield var2_2[2];
            }
            case 3 -> {
                String[] var2_3 = Q;
                yield var2_3[3];
            }
            case 4 -> "IV";
            case 5 -> "V";
            default -> String.valueOf(n2);
        };
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
        if (!this.isEnabled()) {
            return;
        }
        this.a(glRenderEvent.c(), f2, f3);
    }
}

