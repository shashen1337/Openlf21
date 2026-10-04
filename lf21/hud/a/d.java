/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud.a;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.event.impl.Render2DEvent;
import lf21.hud.E;
import lf21.hud.HudElement;
import lf21.hud.a.a;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.w_0;
import lf21.render.y_0;
import lf21.utils.a.e;
import lf21.utils.b.f;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;

public class d
extends a {
    private static final float m;
    private static final float h;
    private static final float e;
    private static final String[] A;
    private static final float l;
    private LivingEntity w;
    private final e u;
    private final u_0 r;
    private static final int o;
    private final u_0 q;
    private static final float d;
    private final D y;
    private final u_0 p;
    private boolean v;
    private static final float i;
    private final e s;
    private static final float f;
    private long x;
    private static final Color k;
    private final e t;
    private final D z;
    private static final Color j;
    private static final float g;
    private static final int n;
    private static final float c;

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
                    case 0 -> 105;
                    case 1 -> 2;
                    case 2 -> 12;
                    case 3 -> 69;
                    case 4 -> 62;
                    case 5 -> 73;
                    default -> 76;
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
                lf21.hud.a.d.m = 6.0f;
                lf21.hud.a.d.l = 120.0f;
                lf21.hud.a.d.h = 5.0f;
                lf21.hud.a.d.g = 5.0f;
                lf21.hud.a.d.f = 35.0f;
                lf21.hud.a.d.e = 10.0f;
                lf21.hud.a.d.d = 45.0f;
                lf21.hud.a.d.c = 194.0f;
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = ";k\u007f \r\u0018we&U:-\u0007f\"1J/\u0002G2\u0006'ca \u0004i";
                var4_3 = ";k\u007f \r\u0018we&U:-\u0007f\"1J/\u0002G2\u0006'ca \u0004i".length();
                var1_4 = 4;
                var0_5 = -1;
lbl15:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl20:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.hud.a.d.b(lf21.hud.a.d.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "'ca \u0004i\r\u0018we&U:-\u0007f\"1J/";
                    var4_3 = "'ca \u0004i\r\u0018we&U:-\u0007f\"1J/".length();
                    var1_4 = 6;
                    var0_5 = -1;
lbl29:
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
lbl34:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.hud.a.d.b(lf21.hud.a.d.b(v1));
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
lbl45:
                // 1 sources

                ** continue;
            }
        }
        lf21.hud.a.d.A = var5;
        lf21.hud.a.d.i = 45.0f;
        lf21.hud.a.d.j = new Color(0, 0, 0, 45);
        lf21.hud.a.d.k = new Color(0, 0, 0, 100);
        lf21.hud.a.d.n = new Color(70, 130, 255).getRGB();
        lf21.hud.a.d.o = new Color(140, 200, 255).getRGB();
    }

    @Override
    public void a(Render2DEvent render2DEvent, LivingEntity livingEntity, e e2, e e3, float f2, float f3, float f4) {
        float f5;
        boolean bl = livingEntity != null;
        long l2 = System.currentTimeMillis();
        boolean bl2 = false;
        if (bl) {
            this.x = l2;
            if (this.w != livingEntity) {
                this.w = livingEntity;
                bl2 = true;
            }
        }
        boolean bl3 = bl || l2 - this.x < 300L;
        boolean bl4 = bl3;
        if (bl3 != this.v || bl3 && this.s.e() <= 0.01f) {
            this.v = bl3;
            if (this.v) {
                this.s.a(1.0, 0.35, lf21.utils.b.f.e);
                this.t.a(5.0);
                this.t.a(0L);
                this.u.a(0.0);
                this.u.a(0L);
            } else {
                this.s.a(0.0, 0.15, lf21.utils.b.f.d);
                this.t.a(5.0, 0.15, lf21.utils.b.f.d);
                this.u.a(0.0, 0.15, lf21.utils.b.f.d);
            }
        } else if (bl2 && this.v) {
            this.s.a(1.0, 0.35, lf21.utils.b.f.e);
            this.t.a(5.0);
            this.t.a(0L);
            this.u.a(0.0);
            this.u.a(0L);
        }
        this.s.a();
        if (this.s.b() && this.v) {
            if (this.s.d() >= 0.08 && this.t.g() == 0L) {
                this.t.a(0.0, 0.3, lf21.utils.b.f.e);
            }
            if (this.s.d() >= 0.15 && this.u.g() == 0L) {
                this.u.a(1.0, 0.4, lf21.utils.b.f.e);
            }
        }
        if (this.t.g() != 0L) {
            this.t.a();
        }
        if (this.u.g() != 0L) {
            this.u.a();
        }
        float f6 = this.s.e();
        if (f5 <= 0.01f) {
            return;
        }
        LivingEntity livingEntity2 = this.w;
        if (livingEntity2 == null) {
            return;
        }
        String string = livingEntity2.getName().getString();
        String[] stringArray = A;
        String string2 = String.valueOf((int)Math.ceil(livingEntity2.getHealth())) + ".0";
        float f7 = y_0.a("Name: ", this.q);
        float f8 = y_0.a(string2, this.r);
        float f9 = f3 + 5.0f;
        float f10 = f4 + 5.0f;
        float f11 = f3 + i;
        float f12 = f3 + 194.0f - 5.0f;
        float f13 = f12 - f8;
        float f14 = this.t.e();
        float f15 = f4 + 10.0f + f14;
        float f16 = f4 + 28.0f;
        float f17 = 9.0f;
        float f18 = 120.0f;
        float f19 = f17 / 2.0f;
        float f20 = 45.0f;
        float f21 = f16 + (f17 - 18.0f) / 2.0f + 1.0f + 7.0f;
        int n2 = this.a(0);
        int n3 = this.a(4);
        PoseStack poseStack = render2DEvent.b().pose();
        poseStack.pushPose();
        RenderUtil.a(poseStack, f3, f4, 194.0f, 45.0f, 10.0f, 15.0f, 0.95f * f6, 0);
        poseStack.popPose();
        Renderer.a((DrawContext drawContext) -> {
            int n4;
            Object object;
            this.y.a(new Color(0, 0, 0, (int)((float)j.getAlpha() * f6)).getRGB());
            y_0.a(f3, f4, 194.0f, 45.0f, 10.0f, this.y);
            if (livingEntity2 instanceof AbstractClientPlayer) {
                AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)livingEntity2;
                y_0.a(abstractClientPlayer, f9, f10, 35.0f, 35.0f, f6, 10.0f);
            }
            int n5 = new Color(255, 255, 255, (int)(255.0f * f6)).getRGB();
            y_0.b("Name: ", f11, f15 + 1.0f, this.q, n5);
            float f16 = f11 + f7;
            int n6 = 0;
            for (char c2 : string.toCharArray()) {
                object = String.valueOf(c2);
                float f17 = y_0.a((String)object, this.p);
                n4 = lf21.utils.render.a.a(this.a(n6), f6);
                y_0.b((String)object, f16, f15 - 0.5f, this.p, n4);
                f16 += f17;
                ++n6;
            }
            this.z.a(new Color(0, 0, 0, (int)((float)k.getAlpha() * f6)).getRGB());
            Object object2 = HudElement.a(f11, f16, f18, f17);
            y_0.a(((E)object2).a(), ((E)object2).b(), ((E)object2).c(), ((E)object2).d(), ((E)object2).d() * 0.5f, this.z);
            float f18 = this.u.e();
            float f19 = e2.e() * ((E)object2).c() * f18;
            Color color = new Color(lf21.utils.render.a.a(n2, f6), true);
            object = new Color(lf21.utils.render.a.a(n3, f6), true);
            Color color2 = new Color(lf21.utils.render.a.a(n2, f6 * f20 / 255.0f), true);
            y_0.a(((E)object2).a(), ((E)object2).b(), f19, ((E)object2).d(), ((E)object2).d() * 0.5f, color2, 6.0f, 0.0f, 0.0f);
            lf21.hud.a.d.a(((E)object2).a(), ((E)object2).b(), f19, ((E)object2).d(), ((E)object2).d() * 0.5f, color, (Color)object);
            n4 = new Color(255, 255, 255, (int)(255.0f * f6)).getRGB();
            y_0.b(string2, f13, f21, this.r, n4);
        });
    }

    public d() {
        String[] stringArray = A;
        super("Rise");
        this.v = false;
        this.x = 0L;
        this.y = new D();
        this.z = new D();
        this.p = t_0.b(21.0f);
        this.q = w_0.a("quicksand.ttf", 22.0f);
        this.r = w_0.a("quicksand.ttf", 18.0f);
        this.s = new e();
        this.s.a(0.0);
        this.t = new e();
        this.t.a(5.0);
        this.u = new e();
        this.u.a(0.0);
    }

    private int a(int n2) {
        return lf21.utils.render.a.a(10, n2 * 8).getRGB();
    }

    private static void a(float f2, float f3, float f4, float f5, float f6, Color color, Color color2) {
        int n2 = color.getAlpha() << 24 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
        int n3 = color2.getAlpha() << 24 | color2.getRed() << 16 | color2.getGreen() << 8 | color2.getBlue();
        lf21.render.E e2 = new lf21.render.E(f2, f3, f2 + f4, f3, n2, n3);
        D d2 = new D().a(e2);
        y_0.a().a(Q.a(f2, f3, f4, f5, f6), d2);
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x4C);
        }
        return cArray;
    }
}

