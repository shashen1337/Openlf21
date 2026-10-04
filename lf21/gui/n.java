/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

import java.awt.Color;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nonnull;
import lf21.LF21Client;
import lf21.gui.c.d;
import lf21.gui.c.e;
import lf21.gui.c.f;
import lf21.gui.c.g;
import lf21.gui.c.h;
import lf21.gui.c.i;
import lf21.gui.j;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.render.Renderer;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.RenderUtil;
import lombok.Generated;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class n
extends Screen {
    public static n a = new n();
    private boolean b = false;
    private boolean c = false;
    private String d = "";
    private long e = 0L;
    private final List<lf21.gui.i> f = new CopyOnWriteArrayList<lf21.gui.i>();
    private lf21.gui.d g = lf21.gui.d.CLOSED;
    private float h = 0.0f;
    private float i = 1.0f;
    private final g j;
    private final d k;
    private final f l;
    private final i m;
    public final e n = new e();
    private final h o = new h();
    private lf21.gui.e p = lf21.gui.e.IDLE;
    private long q = 0L;
    private float r = 1.0f;
    private float s = 1.0f;

    protected n() {
        super(Component.nullToEmpty((String)"New Click GUI"));
        this.j = new g(this::a);
        this.k = new d();
        this.l = new f();
        this.m = new i();
    }

    public void a(float f2) {
        if (this.p == lf21.gui.e.IDLE && f2 != this.i) {
            this.r = f2;
            this.p = lf21.gui.e.FADING_OUT;
            this.o.a(this.i, f2);
        }
    }

    public void init() {
        super.init();
        lf21.utils.b.g.a();
        if (this.g == lf21.gui.d.CLOSED) {
            this.h = 0.0f;
        }
        this.g = lf21.gui.d.OPENING;
    }

    private void a() {
        if (this.g == lf21.gui.d.OPENING) {
            this.h = lf21.utils.b.g.a(this.h, 1.0f, 0.08f);
            if (this.h >= 1.0f) {
                this.g = lf21.gui.d.OPEN;
            }
        } else if (this.g == lf21.gui.d.CLOSING) {
            this.h = lf21.utils.b.g.a(this.h, 0.0f, 0.1f);
            if (this.h <= 0.0f) {
                this.g = lf21.gui.d.CLOSED;
                if (LF21Client.isReady()) {
                    LF21Client.instance.getConfigManager().saveAll();
                }
                this.minecraft.setScreen(null);
            }
        }
    }

    private float b(float f2) {
        return (float)(1.0 - Math.pow(1.0f - f2, 3.0));
    }

    public void render(@Nonnull GuiGraphics guiGraphics, int n2, int n3, float f2) {
        lf21.utils.b.g.b();
        this.a();
        if (this.g == lf21.gui.d.CLOSED && this.h <= 0.0f) {
            return;
        }
        float f3 = this.b(this.h);
        float f4 = 0.98f + 0.02f * f3;
        guiGraphics.fill(0, 0, this.width, this.height, new Color(0, 0, 0, (int)(80.0f * f3)).getRGB());
        this.b();
        int n4 = this.width / 2;
        int n5 = this.height / 2;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((float)n4, (float)n5, 0.0f);
        guiGraphics.pose().scale(f4, f4, 1.0f);
        guiGraphics.pose().translate((float)(-n4), (float)(-n5), 0.0f);
        float f5 = this.p == lf21.gui.e.FADING_OUT || this.p == lf21.gui.e.WAITING ? this.r : this.i;
        int n6 = (int)(600.0f * this.i);
        int n7 = (int)(400.0f * this.i);
        int n8 = n4 - n6 / 2;
        int n9 = n5 - n7 / 2;
        float f6 = this.s * f3;
        if (f6 > 0.005f) {
            this.a(guiGraphics, n8, n9, f2, f6);
            this.k.a(guiGraphics, n8, n9, n2, n3, this.i, f6);
            this.l.a(guiGraphics, n8, n9, n2, n3, this.c(), this.i, f6);
            this.m.a(guiGraphics, n8, n9, n2, n3, this.l.b(), this.i, f6);
            this.j.a(guiGraphics, n8, n9, n2, n3, this.i, f6);
            this.a(guiGraphics, n8, n9, n2, n3, f6);
            this.a(guiGraphics, n8, n9, f6);
        }
        super.render(guiGraphics, n2, n3, f2);
        guiGraphics.pose().popPose();
        this.n.a(guiGraphics, this.width, this.height, f5);
        this.o.a(guiGraphics, this.width, this.height, f5);
        String string = lf21.gui.j.a();
        int n10 = this.width - this.font.width(string) - 8;
        Objects.requireNonNull(this.font);
        guiGraphics.drawString(this.font, string, n10, this.height - 9 - 8, -5197648, false);
    }

    private void b() {
        switch (lf21.gui.h.a[this.p.ordinal()]) {
            case 1: {
                this.s = lf21.utils.b.g.a(this.s, 0.0f, 0.08f);
                if (!(this.s <= 0.0f)) break;
                this.p = lf21.gui.e.WAITING;
                this.q = System.currentTimeMillis();
                break;
            }
            case 2: {
                if (System.currentTimeMillis() - this.q <= 3000L) break;
                this.i = this.r;
                this.p = lf21.gui.e.FADING_IN;
                this.o.a();
                break;
            }
            case 3: {
                this.s = lf21.utils.b.g.a(this.s, 1.0f, 0.08f);
                if (!(this.s >= 1.0f) || !this.o.d()) break;
                this.p = lf21.gui.e.IDLE;
            }
        }
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3) {
        int n4 = (int)(600.0f * this.i);
        int n5 = (int)(400.0f * this.i);
        T.a(guiGraphics.pose(), (float)n2, (float)n3, n4, (float)n5, 12.0f * this.i, f3);
    }

    public boolean mouseClicked(double d2, double d3, int n2) {
        boolean bl;
        if (this.h < 1.0f) {
            return true;
        }
        if (this.n.b()) {
            if (n2 == 0) {
                this.n.a();
            }
            return true;
        }
        int n3 = this.width / 2;
        int n4 = this.height / 2;
        int n5 = (int)(600.0f * this.i);
        int n6 = (int)(400.0f * this.i);
        int n7 = n3 - n5 / 2;
        int n8 = n4 - n6 / 2;
        if (this.j.e() && this.j.b(n7, n8, (int)d2, (int)d3, this.i)) {
            return true;
        }
        int n9 = (int)(200.0f * this.i);
        int n10 = (int)(20.0f * this.i);
        int n11 = n7 + (n5 - n9) / 2;
        int n12 = n8 + n6 + (int)(15.0f * this.i);
        boolean bl2 = bl = d2 >= (double)n11 && d2 <= (double)(n11 + n9) && d3 >= (double)n12 && d3 <= (double)(n12 + n10);
        if (bl) {
            if (!this.b) {
                this.b = true;
                this.d = "";
                this.l.a("");
            }
            this.c = true;
            this.e = System.currentTimeMillis();
            return true;
        }
        this.c = false;
        boolean bl3 = false;
        if (n2 == 0 || n2 == 1 || n2 == 2) {
            if (n2 == 0 && this.k.a(n7, n8, (int)d2, (int)d3, this.i)) {
                if (this.b) {
                    this.b = false;
                    this.c = false;
                    this.d = "";
                    this.l.a((String)null);
                }
                bl3 = true;
            }
            if (!bl3 && this.l.a(n7, n8, (int)d2, (int)d3, this.c(), n2, this.i)) {
                bl3 = true;
            }
            if (!bl3 && this.j.b(n7, n8, (int)d2, (int)d3, this.i)) {
                bl3 = true;
            }
            if (!bl3 && (n2 == 0 || n2 == 1) && this.m.a(n7, n8, (int)d2, (int)d3, n2, this.i)) {
                bl3 = true;
            }
        }
        if (!bl3) {
            lf21.gui.c.a.d.a();
        }
        return bl3 || super.mouseClicked(d2, d3, n2);
    }

    public boolean mouseReleased(double d2, double d3, int n2) {
        if (this.h < 1.0f) {
            return true;
        }
        this.l.a();
        this.m.a(d2, d3, n2);
        this.j.d();
        return super.mouseReleased(d2, d3, n2);
    }

    public boolean mouseScrolled(double d2, double d3, double d4) {
        if (this.h < 1.0f) {
            return true;
        }
        int n2 = this.width / 2;
        int n3 = n2 - (int)(600.0f * this.i) / 2;
        int n4 = this.height / 2;
        int n5 = n4 - (int)(400.0f * this.i) / 2;
        if (this.l.a(n3, n5, (int)d2, (int)d3, this.i)) {
            this.l.a(d4, this.i);
            return true;
        }
        if (this.m.a(n3, n5, (int)d2, (int)d3, this.i)) {
            this.m.a(d4, this.i);
            return true;
        }
        return super.mouseScrolled(d2, d3, d4);
    }

    public boolean mouseDragged(double d2, double d3, int n2, double d4, double d5) {
        if (this.h < 1.0f) {
            return true;
        }
        this.l.a(d2, d3, this.i);
        this.m.a(d2, d3, this.i);
        this.j.a((int)d2, (int)d3);
        return super.mouseDragged(d2, d3, n2, d4, d5);
    }

    public boolean keyPressed(int n2, int n3, int n4) {
        if (this.n.a(n2, n3, n4)) {
            return true;
        }
        if (this.b) {
            if (n2 == 256) {
                this.b = false;
                this.c = false;
                this.d = "";
                this.l.a((String)null);
                return true;
            }
            if (this.c) {
                this.e = System.currentTimeMillis();
                if (n2 == 259) {
                    if (!this.d.isEmpty()) {
                        this.d = this.d.substring(0, this.d.length() - 1);
                        this.l.a(this.d);
                    }
                    return true;
                }
            }
        }
        if (lf21.gui.c.a.d.a(n2, n3, n4)) {
            return true;
        }
        if (n2 == 256 && !this.n.b() && !this.b) {
            this.onClose();
            return true;
        }
        return super.keyPressed(n2, n3, n4);
    }

    public boolean charTyped(char c2, int n2) {
        if (this.b && this.c) {
            this.e = System.currentTimeMillis();
            this.d = this.d + c2;
            this.l.a(this.d);
            return true;
        }
        if (lf21.gui.c.a.d.a(c2)) {
            return true;
        }
        return super.charTyped(c2, n2);
    }

    public boolean isPauseScreen() {
        return false;
    }

    public void onClose() {
        if (this.g != lf21.gui.d.CLOSING) {
            this.g = lf21.gui.d.CLOSING;
        }
    }

    public Category c() {
        return this.k.a();
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, int n4, int n5, float f2) {
        try {
            int n6 = (int)(600.0f * this.i);
            int n7 = (int)(400.0f * this.i);
            int n8 = (int)(200.0f * this.i);
            int n9 = (int)(20.0f * this.i);
            int n10 = n2 + (n6 - n8) / 2;
            int n11 = n3 + n7 + (int)(15.0f * this.i);
            T.a(guiGraphics.pose(), (float)n10, (float)n11, n8, (float)n9, 8.0f * this.i, f2);
            Renderer.a(drawContext -> {
                u_0 u_02 = t_0.j(20.0f * this.i);
                String string = "\ue8b6";
                float f3 = (float)n10 + 12.0f * this.i;
                float f4 = (float)n11 + (float)n9 / 2.0f + u_02.d().f() / 2.0f - 12.0f * this.i + 10.0f * this.i;
                int n6 = new Color(255, 255, 255, (int)(180.0f * f2)).getRGB();
                T.a(string, f3, f4, u_02, -2236963, n6, 12.0f * this.i);
                u_0 u_03 = t_0.l(16.0f * this.i);
                if (!this.b && this.d.isEmpty()) {
                    u_0 u_04 = t_0.k(14.0f * this.i);
                    String string2 = "search";
                    float f5 = y_0.a(string2, u_04);
                    float f6 = (float)n10 + ((float)n8 - f5) / 2.0f;
                    float f7 = (float)n11 + (float)n9 / 2.0f + u_04.d().f() / 2.0f - 8.0f * this.i + 5.0f * this.i;
                    T.a(string2, f6, f7, u_04, -3355444, new Color(255, 255, 255, (int)(130.0f * f2)).getRGB(), 10.0f * this.i);
                } else {
                    float f8 = (float)n10 + 35.0f * this.i;
                    float f9 = (float)n11 + (float)n9 / 2.0f + u_03.d().f() / 2.0f - 9.0f * this.i + 6.0f * this.i;
                    int n7 = new Color(255, 255, 255, (int)(120.0f * f2)).getRGB();
                    T.a(this.d, f8, f9, u_03, -1, n7, 8.0f * this.i);
                    if (this.c) {
                        long l2 = System.currentTimeMillis() - this.e;
                        float f10 = (float)(Math.sin((double)l2 / 200.0) * 0.5 + 0.5);
                        int n8 = (int)(f10 * f2 * 255.0f) << 24 | 0xFFFFFF;
                        float f11 = f8 + y_0.a(this.d, u_03) + 2.0f * this.i;
                        float f12 = u_03.d().f();
                        RenderUtil.a(guiGraphics.pose(), f11, f9 - f12 + 3.0f * this.i, this.i, f12 + 2.0f * this.i, n8);
                    }
                }
            });
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void a(String string) {
        for (lf21.gui.i i2 : this.f) {
            i2.d -= 20.0f * this.i;
        }
        this.f.add(new lf21.gui.i(string));
    }

    public void a(Module module) {
        this.n.a(module);
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, float f2) {
        if (this.f.isEmpty()) {
            return;
        }
        try {
            Renderer.a(drawContext -> {
                u_0 u_02 = t_0.k(18.0f * this.i);
                for (lf21.gui.i i2 : this.f) {
                    long l2 = System.currentTimeMillis() - i2.b;
                    i2.c = lf21.utils.b.g.b(i2.c, i2.d, 0.2f);
                    float f3 = 0.0f;
                    if (l2 < 2000L) {
                        f3 = 1.0f;
                    } else if (l2 < 2500L) {
                        long l3 = l2 - 2000L;
                        f3 = 1.0f - (float)l3 / 500.0f;
                    }
                    i2.e = lf21.utils.b.g.b(i2.e, f3, 0.25f);
                    if (i2.e < 0.01f && l2 > 2000L) {
                        this.f.remove(i2);
                        continue;
                    }
                    float f4 = y_0.a(i2.a, u_02);
                    int n4 = (int)(600.0f * this.i);
                    float f5 = (float)n2 + ((float)n4 - f4) / 2.0f;
                    float f6 = (float)(n3 - 25) + i2.c;
                    int n5 = (int)(255.0f * i2.e * f2);
                    int n6 = n5 << 24 | 0xFFFFFF;
                    int n7 = (int)(120.0f * i2.e * f2);
                    int n8 = n7 << 24 | 0xFFFFFF;
                    T.a(i2.a, f5, f6 + 6.0f * this.i, u_02, n6, n8, 8.0f * this.i);
                }
            });
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Generated
    public d d() {
        return this.k;
    }

    @Generated
    public f e() {
        return this.l;
    }

    @Generated
    public i f() {
        return this.m;
    }
}

