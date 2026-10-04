/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.vertex.DefaultedVertexConsumer;
import com.mojang.blaze3d.vertex.VertexConsumer;

/*
 * Renamed from lf21.render.b
 */
final class b_0
extends DefaultedVertexConsumer {
    private final int d;
    private float j;
    private double g;
    private double h;
    private final int c;
    private double f;
    private final int b;
    private final int e;
    private float i;
    private final VertexConsumer a;

    public void unsetDefaultColor() {
    }

    public VertexConsumer overlayCoords(int n2, int n3) {
        return this;
    }

    public VertexConsumer uv(float f2, float f3) {
        this.i = f2;
        this.j = f3;
        return this;
    }

    public void defaultColor(int n2, int n3, int n4, int n5) {
    }

    private b_0(VertexConsumer vertexConsumer, int n2, int n3, int n4, int n5) {
        this.a = vertexConsumer;
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.e = n5;
        super.defaultColor(n2, n3, n4, n5);
    }

    public VertexConsumer uv2(int n2, int n3) {
        return this;
    }

    public VertexConsumer color(int n2, int n3, int n4, int n5) {
        return this;
    }

    public VertexConsumer normal(float f2, float f3, float f4) {
        return this;
    }

    public VertexConsumer vertex(double d2, double d3, double d4) {
        this.f = d2;
        this.g = d3;
        this.h = d4;
        return this;
    }

    public void endVertex() {
        this.a.vertex(this.f, this.g, this.h).color(this.b, this.c, this.d, this.e).uv(this.i, this.j).endVertex();
    }

    public void vertex(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int n2, int n3, float f11, float f12, float f13) {
        this.a.vertex((double)f2, (double)f3, (double)f4).color(this.b, this.c, this.d, this.e).uv(f9, f10).endVertex();
    }
}

