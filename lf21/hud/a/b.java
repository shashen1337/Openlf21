/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud.a;

import com.mojang.blaze3d.systems.RenderSystem;
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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class b
extends a {
    private static final Color g;
    private static final String w;
    private final u_0 h;
    private int l;
    private final D n;
    private LivingEntity u;
    private final e s;
    private static final Color c;
    private LivingEntity k;
    private static final Color d;
    private static final Color e;
    private static final Color f;
    private final e r;
    private final D p;
    private final e j = new e();
    private final D o;
    private final u_0 i;
    private boolean t = false;
    private final e q;
    private long v = 0L;
    private final ItemStack[] m = new ItemStack[4];

    public b() {
        super(w);
        this.n = new D();
        this.o = new D();
        this.p = new D();
        this.h = t_0.b(14.0f);
        this.i = t_0.d(13.0f);
        this.j.a(1.0);
        this.q = new e();
        this.q.a(0.0);
        this.r = new e();
        this.r.a(5.0);
        this.s = new e();
        this.s.a(0.0);
    }

    @Override
    public void a(Render2DEvent render2DEvent, LivingEntity livingEntity, e e2, e e3, float f2, float f3, float f4) {
        float f5;
        for (int i2 = 0; i2 < this.m.length; ++i2) {
            this.m[i2] = ItemStack.EMPTY;
        }
        float f6 = 120.0f;
        float f7 = 43.0f;
        boolean bl = livingEntity != null;
        long l2 = System.currentTimeMillis();
        boolean bl2 = false;
        if (bl) {
            this.v = l2;
            if (this.u != livingEntity) {
                this.u = livingEntity;
                this.k = livingEntity;
                bl2 = true;
            }
        }
        boolean bl3 = bl || l2 - this.v < 300L;
        boolean bl4 = bl3;
        if (bl3 != this.t || bl3 && this.q.e() <= 0.01f) {
            this.t = bl3;
            if (this.t) {
                this.q.a(1.0, 0.35, lf21.utils.b.f.e);
                this.r.a(5.0);
                this.r.a(0L);
                this.s.a(0.0);
                this.s.a(0L);
                this.j.a(1.0);
                this.j.a(1.0, 0.0);
            } else {
                this.q.a(0.0, 0.15, lf21.utils.b.f.d);
                this.r.a(5.0, 0.15, lf21.utils.b.f.d);
                this.s.a(0.0, 0.15, lf21.utils.b.f.d);
            }
        } else if (bl2 && this.t) {
            this.q.a(1.0, 0.35, lf21.utils.b.f.e);
            this.r.a(5.0);
            this.r.a(0L);
            this.s.a(0.0);
            this.s.a(0L);
            this.j.a(1.0);
            this.j.a(1.0, 0.0);
        }
        this.q.a();
        if (this.q.b() && this.t) {
            if (this.q.d() >= 0.08 && this.r.g() == 0L) {
                this.r.a(0.0, 0.3, lf21.utils.b.f.e);
            }
            if (this.q.d() >= 0.15 && this.s.g() == 0L) {
                this.s.a(1.0, 0.4, lf21.utils.b.f.e);
            }
        }
        if (this.r.g() != 0L) {
            this.r.a();
        }
        if (this.s.g() != 0L) {
            this.s.a();
        }
        float f8 = this.q.e();
        if (f5 <= 0.01f) {
            return;
        }
        LivingEntity livingEntity2 = this.u;
        if (livingEntity2 == null) {
            return;
        }
        float f9 = 30.0f;
        float f10 = 4.0f;
        float f11 = f3 + 4.0f + 30.0f + 4.0f;
        float f12 = 120.0f - (f11 - f3) - 3.0f;
        float f13 = f4 + 3.0f + 2.0f;
        float f14 = y_0.a(this.h);
        float f15 = f13 + f14 + 4.0f;
        PoseStack poseStack = render2DEvent.b().pose();
        poseStack.pushPose();
        RenderUtil.a(poseStack, f3, f4 - 2.0f, 120.0f, 45.0f, 5.0f, 15.0f, 0.95f * f8, 0);
        poseStack.popPose();
        Renderer.a((DrawContext drawContext) -> {
            this.n.a(new Color(0, 0, 0, (int)((float)c.getAlpha() * f8)).getRGB());
            y_0.a(f3, f4 - 2.0f, 120.0f, 45.0f, 5.0f, this.n);
            if (bl && livingEntity.hurtTime > this.l) {
                this.j.a(0.7f);
                this.j.a(1.0, 1.5, lf21.utils.b.f.i);
            }
            if (bl) {
                this.l = livingEntity.hurtTime;
            }
            this.j.a();
            float f8 = this.j.e();
            float f9 = (float)Math.max(0.7, (double)f8);
            float f10 = f8 * f9;
            float f11 = 30.0f * f10;
            float f12 = f3 + 4.0f + (30.0f - f11) / 2.0f;
            float f13 = f4 + (43.0f - f11) / 2.0f - 4.0f;
            if (livingEntity2 instanceof AbstractClientPlayer) {
                AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)livingEntity2;
                y_0.a(abstractClientPlayer, f12, f13, f11, f11, f8, 5.0f * f10);
            }
            float f14 = this.r.e();
            String string = livingEntity2.getName().getString();
            y_0.b(string, f11, f13 + 1.0f + f14, this.h, new Color(1.0f, 1.0f, 1.0f, f8).getRGB());
            int n2 = (int)Math.ceil(livingEntity2.getHealth());
            String string2 = String.valueOf(n2);
            float f15 = f11 + f12 - y_0.a(string2, this.h) - 2.0f;
            y_0.b(string2, f15, f13 + 1.0f + f14, this.h, new Color(0, 230, 255, (int)(255.0f * f8)).getRGB());
            float f16 = f3 + 4.0f;
            float f17 = 112.0f;
            float f18 = 4.0f;
            float f19 = f13 + f11 + 3.0f;
            this.o.a(new Color(0, 0, 0, (int)((float)d.getAlpha() * f8)).getRGB());
            E e4 = HudElement.a(f16, f19, f17, f18);
            y_0.a(e4.a(), e4.b(), e4.c(), e4.d(), e4.d() * 0.5f, this.o);
            this.p.a(new Color(99, 99, 99, (int)((float)g.getAlpha() * f8)).getRGB());
            float f20 = e3.e() * e4.c();
            y_0.a(e4.a(), e4.b(), f20, e4.d(), e4.d() * 0.5f, this.p);
            float f21 = this.s.e();
            float f22 = e2.e() * e4.c() * f21;
            Color color = new Color(e.getRed(), e.getGreen(), e.getBlue(), (int)(255.0f * f8));
            Color color2 = new Color(f.getRed(), f.getGreen(), f.getBlue(), (int)(255.0f * f8));
            y_0.a(e4.a(), e4.b(), f22, e4.d(), e4.d() * 0.5f, color, color2);
        });
        if (livingEntity2 != null) {
            this.m[0] = livingEntity2.getItemBySlot(EquipmentSlot.HEAD);
            this.m[1] = livingEntity2.getItemBySlot(EquipmentSlot.CHEST);
            this.m[2] = livingEntity2.getItemBySlot(EquipmentSlot.LEGS);
            this.m[3] = livingEntity2.getItemBySlot(EquipmentSlot.FEET);
        }
        float f16 = f11 - 1.0f;
        float f17 = 0.8f;
        float f18 = 16.0f * f17;
        float f19 = 2.0f;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        for (ItemStack itemStack : this.m) {
            if (itemStack != null && !itemStack.isEmpty()) {
                PoseStack poseStack2 = render2DEvent.b().pose();
                poseStack2.pushPose();
                poseStack2.translate(f16, f15 + 2.0f, 0.0f);
                poseStack2.scale(f17, f17, 1.0f);
                RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)f8);
                render2DEvent.b().renderItem(itemStack, 0, 0);
                poseStack2.popPose();
            }
            f16 += f18 + f19;
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string = b.b(b.b("X]\u0003*"));
        w = string;
        c = new Color(0, 0, 0, 80);
        d = new Color(0, 0, 0, 100);
        e = new Color(0, 190, 255);
        f = new Color(0, 230, 255);
        g = new Color(99, 99, 99, 120);
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
                    case 0 -> 21;
                    case 1 -> 50;
                    case 2 -> 108;
                    case 3 -> 68;
                    case 4 -> 38;
                    case 5 -> 79;
                    default -> 71;
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
            cArray[0] = (char)(cArray[0] ^ 0x47);
        }
        return cArray;
    }
}

