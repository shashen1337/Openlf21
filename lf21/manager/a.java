/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

final class a {
    private float a;
    private float b;

    private a(float f2) {
        this.a = f2;
        this.b = f2;
    }

    private void a(float f2) {
        float f3 = Math.abs(this.b - this.a);
        float f4 = Math.max(10.0f, f3 * 40.0f) * 0.4f;
        float f5 = f4 * f2;
        if (this.b < this.a) {
            this.b = Math.min(this.b + f5, this.a);
        } else if (this.b > this.a) {
            this.b = Math.max(this.b - f5, this.a);
        }
    }

    private boolean a() {
        return this.b == this.a;
    }
}

