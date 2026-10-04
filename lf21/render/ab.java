/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

public record ab(float a, float b, float c, float d) {
    private final float a;
    private final float b;
    private final float c;
    private final float d;

    public float b() {
        return this.d - this.b;
    }

    public float j() {
        return this.d;
    }

    public float e() {
        return this.c;
    }

    public float f() {
        return this.d;
    }

    public static ab b(float f2, float f3, float f4, float f5) {
        return new ab(f2, f3, f4, f5);
    }

    public float c() {
        return this.a;
    }

    public float g() {
        return this.a;
    }

    public float i() {
        return this.c;
    }

    public static ab a(float f2, float f3, float f4, float f5) {
        return new ab(f2, f3, f2 + f4, f3 + f5);
    }

    public float h() {
        return this.b;
    }

    public float d() {
        return this.b;
    }

    public float a() {
        return this.c - this.a;
    }
}

