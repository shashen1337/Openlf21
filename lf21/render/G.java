/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.util.ArrayList;
import java.util.List;
import lf21.render.H;
import lf21.render.Q;
import lf21.render.ab;
import lf21.render.j_0;

public class G
implements AutoCloseable {
    private final List<H> a = new ArrayList<H>();

    public G b(float f2, float f3) {
        this.a.add(new H(j_0.LINE_TO, new float[]{f2, f3}, null, null));
        return this;
    }

    public G a(Q q2) {
        this.a.add(new H(j_0.RRECT, null, q2, null));
        return this;
    }

    public G a() {
        this.a.add(new H(j_0.CLOSE, null, null, null));
        return this;
    }

    public G a(float f2, float f3) {
        this.a.add(new H(j_0.MOVE_TO, new float[]{f2, f3}, null, null));
        return this;
    }

    public G a(ab ab2) {
        this.a.add(new H(j_0.RECT, null, null, ab2));
        return this;
    }

    public G a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a.add(new H(j_0.CUBIC_TO, new float[]{f2, f3, f4, f5, f6, f7}, null, null));
        return this;
    }

    public List<H> b() {
        return this.a;
    }

    public G a(float f2, float f3, float f4, float f5) {
        this.a.add(new H(j_0.QUAD_TO, new float[]{f2, f3, f4, f5}, null, null));
        return this;
    }

    public void c() {
        this.a.clear();
    }

    @Override
    public void close() {
    }
}

