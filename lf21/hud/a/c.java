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
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.a.e;
import lf21.utils.b.f;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;

public class c
extends a {
    private final e h = new e();
    private long n = 0L;
    private final e i;
    private static final Color c;
    private final D o = new D();
    private LivingEntity m;
    private boolean l = false;
    private final e k;
    public float s = 2.0f;
    private static final Color d;
    private final u_0 g;
    public float r = -3.5f;
    private static final Color e;
    private final u_0 f;
    private final D q;
    private final e j;
    private final D p = new D();
    private static final String[] u;
    public float t = 3.5f;

    public c() {
        super("Naven");
        this.q = new D();
        this.f = t_0.b(15.0f);
        this.g = t_0.d(14.0f);
        this.i = new e();
        this.i.a(0.0);
        this.j = new e();
        this.j.a(5.0);
        this.k = new e();
        this.k.a(0.0);
    }

    @Override
    public void a(Render2DEvent render2DEvent, LivingEntity livingEntity, e e2, e e3, float f2, float f3, float f4) {
        float f5;
        boolean bl;
        boolean bl2 = livingEntity != null;
        long l2 = System.currentTimeMillis();
        boolean bl3 = false;
        if (bl2) {
            this.n = l2;
            if (this.m != livingEntity) {
                this.m = livingEntity;
                bl3 = true;
            }
        }
        boolean bl4 = bl = bl2 || l2 - this.n < 300L;
        if (bl != this.l || bl && this.i.e() <= 0.01f) {
            this.l = bl;
            if (this.l) {
                this.i.a(1.0, 0.2, lf21.utils.b.f.e);
                this.j.a(5.0);
                this.j.a(0L);
                this.k.a(0.0);
                this.k.a(0L);
            } else {
                this.i.a(0.0, 0.15, lf21.utils.b.f.d);
                this.j.a(5.0, 0.15, lf21.utils.b.f.d);
                this.k.a(0.0, 0.15, lf21.utils.b.f.d);
            }
        } else if (bl3 && this.l) {
            this.i.a(1.0, 0.2, lf21.utils.b.f.e);
            this.j.a(5.0);
            this.j.a(0L);
            this.k.a(0.0);
            this.k.a(0L);
        }
        this.i.a();
        if (this.i.b() && this.l) {
            if (this.i.d() >= 0.08 && this.j.g() == 0L) {
                this.j.a(0.0, 0.3, lf21.utils.b.f.e);
            }
            if (this.i.d() >= 0.15 && this.k.g() == 0L) {
                this.k.a(1.0, 0.4, lf21.utils.b.f.e);
            }
        }
        if (this.j.g() != 0L) {
            this.j.a();
        }
        if (this.k.g() != 0L) {
            this.k.a();
        }
        float f6 = this.i.e();
        if (f5 <= 0.01f) {
            return;
        }
        LivingEntity livingEntity2 = this.m;
        if (livingEntity2 == null) {
            return;
        }
        String string = livingEntity2.getName().getString();
        float f7 = y_0.a(string, this.f);
        float f8 = Math.max(120.0f, f7 + 53.0f);
        float f9 = 56.0f;
        float f10 = this.r;
        float f11 = this.s;
        float f12 = this.t;
        PoseStack poseStack = render2DEvent.b().pose();
        poseStack.pushPose();
        RenderUtil.a(poseStack, f3, f4, f8, f9, 6.0f, 15.0f, 0.95f * f6, 0);
        poseStack.popPose();
        Renderer.a((DrawContext drawContext) -> {
            String string2;
            this.o.a(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)((float)c.getAlpha() * f6)).getRGB());
            y_0.a(f3, f4, f8, f9, 6.0f, this.o);
            if (livingEntity2 instanceof AbstractClientPlayer) {
                AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)livingEntity2;
                y_0.a(abstractClientPlayer, f3 + 9.0f, f4 + 12.0f + f10, 32.0f, 32.0f, f6, 2.0f);
            }
            float f10 = this.j.e();
            String[] stringArray = u;
            String string3 = "Health: " + (float)Math.round(livingEntity2.getHealth() * 10.0f) / 10.0f;
            float f11 = lf21.hud.a.c.mc.player != null ? (float)Math.round(lf21.hud.a.c.mc.player.distanceTo((Entity)livingEntity2) * 10.0f) / 10.0f : 0.0f;
            stringArray = u;
            String string4 = "Distance: " + f11;
            if (livingEntity2.getMainHandItem().getItem() instanceof SwordItem) {
                String string5;
                if (livingEntity2.isBlocking()) {
                    string5 = "Blocking";
                } else {
                    stringArray = u;
                    string5 = "Not Blocking";
                }
                string2 = string5;
            } else {
                String string6;
                if (livingEntity2.isUsingItem()) {
                    stringArray = u;
                    string6 = "Using";
                } else {
                    string6 = "Not Using";
                }
                string2 = string6;
            }
            int n2 = new Color(e.getRed(), e.getGreen(), e.getBlue(), (int)(255.0f * f6)).getRGB();
            int n3 = new Color(255, 255, 255, (int)(255.0f * f6)).getRGB();
            y_0.b(string, f3 + 46.0f, f4 + 7.0f + f12, this.f, n2);
            y_0.b(string3, f3 + 46.0f, f4 + 19.0f + f10 + f11, this.g, n3);
            y_0.b(string4, f3 + 46.0f, f4 + 27.0f + f10 + f11, this.g, n3);
            y_0.b(string2, f3 + 46.0f, f4 + 35.0f + f10 + f11, this.g, n3);
            float f12 = f3 + 8.0f;
            float f13 = f4 + 47.0f;
            float f14 = f8 - 16.0f;
            float f15 = 4.0f;
            this.p.a(new Color(d.getRed(), d.getGreen(), d.getBlue(), (int)((float)d.getAlpha() * f6)).getRGB());
            E e3 = HudElement.a(f12, f13, f14, f15);
            y_0.a(e3.a(), e3.b(), e3.c(), e3.d(), e3.d() * 0.5f, this.p);
            float f16 = this.k.e();
            float f17 = e2.e() * e3.c() * f16;
            this.q.a(new Color(e.getRed(), e.getGreen(), e.getBlue(), (int)(255.0f * f6)).getRGB());
            y_0.a(e3.a(), e3.b(), f17, e3.d(), e3.d() * 0.5f, this.q);
        });
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "I\u0017\u0007b6\u0005R\u0005\u0018i?\nX\r\u001dx0I\u000ey^N\tR\u000b\u001a,\u0004T\u0004r\u0003\bT\u0001\u000f`%OW<";
                var4_3 = "I\u0017\u0007b6\u0005R\u0005\u0018i?\nX\r\u001dx0I\u000ey^N\tR\u000b\u001a,\u0004T\u0004r\u0003\bT\u0001\u000f`%OW<".length();
                var1_4 = 5;
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
                    var5[var3_1++] = lf21.hud.a.c.b(lf21.hud.a.c.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "^\b\u0001o:N\u0003{\fR\u000b\u001a,\u0013K\u0002\u007f\u000f\u0007b6";
                    var4_3 = "^\b\u0001o:N\u0003{\fR\u000b\u001a,\u0013K\u0002\u007f\u000f\u0007b6".length();
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
                    var5[var3_1++] = lf21.hud.a.c.b(lf21.hud.a.c.b(v1));
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
        lf21.hud.a.c.u = var5;
        lf21.hud.a.c.c = new Color(23, 22, 38, 200);
        lf21.hud.a.c.d = new Color(0, 0, 0, 90);
        lf21.hud.a.c.e = new Color(148, 42, 43);
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
                    case 0 -> 28;
                    case 1 -> 100;
                    case 2 -> 110;
                    case 3 -> 12;
                    case 4 -> 81;
                    case 5 -> 39;
                    default -> 109;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6D);
        }
        return cArray;
    }
}

