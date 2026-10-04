/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.a;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.gui.a.c;
import lf21.settings.Setting;

public class d {
    public Setting<?> a;
    public c b;
    public int c;

    public d(Setting<?> setting, c c2, int n2) {
        this.a = setting;
        this.b = c2;
        this.c = n2;
    }

    public void a(PoseStack poseStack, int n2, int n3, float f2) {
        this.a(poseStack, n2, n3, f2, 1.0f);
    }

    public void a(PoseStack poseStack, int n2, int n3, float f2, float f3) {
    }

    public void a(double d2, double d3, int n2) {
    }

    public void b(double d2, double d3, int n2) {
    }

    public boolean a(double d2, double d3) {
        int n2 = this.b.b.b + this.b.c + this.b.b.f + this.c;
        int n3 = this.b.b.f;
        return d2 >= (double)this.b.b.a && d2 <= (double)(this.b.b.a + this.b.b.e) && d3 >= (double)n2 && d3 <= (double)(n2 + n3);
    }
}

