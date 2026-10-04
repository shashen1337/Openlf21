/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

public record X(float a, float b, float c, float d) {
    private final float a;
    private final float b;
    private final float c;
    private final float d;

    public float c() {
        return this.a;
    }

    public float d() {
        return this.b;
    }

    public float e() {
        return this.c;
    }

    public float a() {
        return this.c - (this.b - this.a);
    }

    public float f() {
        return this.d;
    }

    public float b() {
        return this.b - this.a;
    }
}

