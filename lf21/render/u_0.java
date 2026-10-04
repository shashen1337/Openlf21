/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import lf21.render.G;
import lf21.render.X;
import lf21.render.ab;
import lf21.render.d_0;
import lf21.render.q_0;
import lf21.render.w_0;
import lombok.Generated;

/*
 * Renamed from lf21.render.u
 */
public final class u_0 {
    private q_0 c;
    private final String a;
    private final float b;

    public short[] c(String string) {
        return new short[0];
    }

    public u_0 b(boolean bl) {
        return this;
    }

    public int c() {
        return this.b();
    }

    public u_0(String string, float f2) {
        this.a = string;
        this.b = f2;
    }

    public u_0(q_0 q_02, float f2) {
        this.a = null;
        this.b = f2;
        this.c = q_02;
    }

    public int b() {
        q_0 q_02 = this.a();
        return q_02 == null ? 0 : System.identityHashCode(q_02);
    }

    public float b(String string) {
        if (string != null && !string.isEmpty()) {
            q_0 q_02 = this.a();
            return q_02 == null ? 0.0f : q_02.b(string);
        }
        return 0.0f;
    }

    @Generated
    public String e() {
        return this.a;
    }

    public q_0 a() {
        if (this.c == null && this.a != null) {
            this.c = w_0.b(this.a, this.b);
        }
        return this.c;
    }

    public ab a(String string) {
        if (string != null && !string.isEmpty()) {
            q_0 q_02 = this.a();
            if (q_02 == null) {
                return ab.a(0.0f, 0.0f, 0.0f, 0.0f);
            }
            float f2 = q_02.b(string);
            float f3 = q_02.c(string);
            return ab.a(0.0f, 0.0f, f2, f3);
        }
        return ab.a(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public u_0 a(boolean bl) {
        return this;
    }

    public G a(short s2) {
        return null;
    }

    public u_0 a(Object object) {
        return this;
    }

    @Generated
    public float f() {
        return this.b;
    }

    public X d() {
        q_0 q_02 = this.a();
        if (q_02 == null) {
            return new X(-this.b * 0.8f, this.b * 0.2f, this.b * 1.2f, this.b * 0.7f);
        }
        d_0 d_02 = q_02.d();
        int n2 = q_02.e();
        if (n2 <= 0) {
            n2 = 1;
        }
        float f2 = -d_02.getAscent() / n2;
        float f3 = (float)d_02.getDescent() / (float)n2;
        float f4 = (float)d_02.getLeading() / (float)n2;
        float f5 = (float)d_02.getHeight() / (float)n2;
        float f6 = (float)d_02.getAscent() * 0.7f / (float)n2;
        return new X(f2, f3, f5, f6);
    }
}

