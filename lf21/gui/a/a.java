/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.a;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lf21.LF21Client;
import lf21.gui.a.b;
import lf21.gui.a.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.render.v_0;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class a {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public Category g;
    public boolean h;
    public boolean i;
    public List<c> j;
    private float[] k;
    private float[] l;
    private final float m = 0.2f;
    private long n = System.currentTimeMillis();
    private boolean o = false;

    public a(int n2, int n3, int n4, int n5, Category category) {
        this.a = n2;
        this.b = n3;
        this.e = n4;
        this.f = n5;
        this.g = category;
        this.h = false;
        this.i = false;
        this.j = new ArrayList<c>();
        int n6 = n5;
        for (Module module : LF21Client.getInstance().getModuleManager().a(category)) {
            this.j.add(new c(module, this, n6));
            n6 += n5;
        }
        this.a();
    }

    private void a() {
        this.k = new float[this.j.size()];
        this.l = new float[this.j.size()];
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            this.k[i2] = this.f + i2 * this.f;
            this.l[i2] = this.f + i2 * this.f;
        }
    }

    public void b() {
        if (!this.o) {
            return;
        }
        boolean bl = false;
        long l2 = System.currentTimeMillis();
        float f2 = (float)(l2 - this.n) / 1000.0f;
        this.n = l2;
        for (c c2 : this.j) {
            if (!c2.d()) continue;
            bl = true;
        }
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            float f3 = this.k[i2] - this.l[i2];
            if (Math.abs(f3) > 0.5f) {
                int n2 = i2;
                this.l[n2] = this.l[n2] + f3 * 0.2f * (f2 * 60.0f);
                bl = true;
                this.j.get((int)i2).c = (int)this.l[i2];
                continue;
            }
            this.l[i2] = this.k[i2];
            this.j.get((int)i2).c = (int)this.k[i2];
        }
        this.o = bl;
    }

    public void a(GuiGraphics guiGraphics, int n2, int n3, float f2) {
        this.b();
        RenderUtil.a(guiGraphics.pose(), (float)this.a, (float)this.b, (float)this.e, (float)this.f, new Color(16, 16, 20, 245).getRGB());
        String string = String.valueOf(switch (lf21.gui.a.b.a[this.g.ordinal()]) {
            default -> throw new IncompatibleClassChangeError();
            case 1 -> 'a';
            case 2 -> 'b';
            case 3 -> 'c';
            case 4 -> 'd';
            case 5 -> 'e';
            case 6 -> 'f';
            case 7 -> 'g';
        });
        float f3 = (float)this.b + ((float)this.f / 2.0f - v_0.f.c() / 2.0f) + 3.0f;
        v_0.f.c(guiGraphics.pose(), string, this.a + 4, f3, -1);
        float f4 = (float)this.b + ((float)this.f / 2.0f - v_0.b.c() / 2.0f) - 0.5f;
        v_0.b.c(guiGraphics.pose(), this.g.g, this.a + this.f + 4, f4, -1);
        for (c c2 : this.j) {
            c2.a(guiGraphics.pose(), n2, n3, f2);
        }
    }

    public void a(double d2, double d3, int n2) {
        if (this.a(d2, d3)) {
            if (n2 == 0) {
                this.h = true;
                this.c = (int)(d2 - (double)this.a);
                this.d = (int)(d3 - (double)this.b);
            } else if (n2 == 1) {
                this.i = !this.i;
                this.c();
            }
        }
        for (c c2 : this.j) {
            c2.a(d2, d3, n2);
        }
    }

    public void b(double d2, double d3, int n2) {
        for (c c2 : this.j) {
            c2.b(d2, d3, n2);
        }
        if (n2 == 0 && this.h) {
            this.h = false;
        }
    }

    public void a(double d2, double d3, double d4) {
        for (c c2 : this.j) {
            c2.a(d2, d3, d4);
        }
    }

    public boolean a(double d2, double d3) {
        return d2 > (double)this.a && d2 < (double)(this.a + this.e) && d3 > (double)this.b && d3 < (double)(this.b + this.f);
    }

    public void b(double d2, double d3) {
        if (this.h) {
            this.a = (int)(d2 - (double)this.c);
            this.b = (int)(d3 - (double)this.d);
        }
    }

    public void c() {
        int n2 = this.f;
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            c c2 = this.j.get(i2);
            this.k[i2] = n2;
            n2 += c2.a();
        }
        this.o = true;
        this.n = System.currentTimeMillis();
    }
}

