/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.b;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lf21.utils.b.b;
import lf21.utils.b.h;

public class c
extends b {
    private final h c;
    private final h d;
    private final List<h> e = new ArrayList<h>();
    private int f = 0;

    public c() {
        this.c = new h(0.0, 0.0);
        this.d = new h(1.0, 1.0);
        this.a(30);
    }

    public c(int n2) {
        this();
        this.a(n2);
    }

    public c(h h2, h h3) {
        this.c = h2;
        this.d = h3;
        this.a(30);
    }

    public c(h h2, h h3, int n2) {
        this(h2, h3);
        this.a(n2);
    }

    public c(c c2) {
        this.c = c2.b();
        this.d = c2.c();
        this.a(30);
    }

    public c(c c2, int n2) {
        this(c2);
        this.a(n2);
    }

    public c(String string) {
        String[] stringArray = string.replace(" ", "").split(",");
        if (stringArray.length != 4) {
            throw new IllegalArgumentException("Couldn't parse " + string + ", please follow this format: x1,y1,x2,y2");
        }
        this.c = new h(stringArray[0] + "," + stringArray[1]);
        this.d = new h(stringArray[2] + "," + stringArray[3]);
        this.a(30);
    }

    public c(String string, int n2) {
        this(string);
        this.a(n2);
    }

    private void a() {
        block5: {
            if (this.c != null && this.d != null) break block5;
            return;
        }
        this.e.clear();
        double d2 = 0.03333333333333333;
        for (double d3 = 0.0; d3 <= 1.0; d3 += d2) {
            h h2 = this.b(d3);
            this.e.add(new h(h2.a, 1.0).c(0.0, h2.b));
        }
        this.e.add(new h(1.0, 0.0));
    }

    private h b(double d2) {
        block4: {
            if (this.c != null && this.d != null) break block4;
            throw new NullPointerException("firstPoint or secondPoint is null");
        }
        h h2 = this.c.a();
        h h3 = this.d.a();
        double d3 = 1.0 - d2;
        return new h(this.a.a * Math.pow(d3, 3.0) + 3.0 * h2.a * d2 * Math.pow(d3, 2.0) + 3.0 * h3.a * Math.pow(d2, 2.0) * d3 + this.b.a * Math.pow(d2, 3.0), this.a.b * Math.pow(d3, 3.0) + 3.0 * h2.b * d2 * Math.pow(d3, 2.0) + 3.0 * h3.b * Math.pow(d2, 2.0) * d3 + this.b.b * Math.pow(d2, 3.0));
    }

    private Map.Entry<h, h> c(double d2) {
        if (this.e.isEmpty()) {
            return new AbstractMap.SimpleEntry<h, h>(new h(0.0, 0.0), new h(0.0, 0.0));
        }
        h h2 = this.e.get(0);
        h h3 = this.e.get(this.e.size() - 1);
        for (h h4 : this.e) {
            if (h4.a < d2) {
                h2 = h4;
                continue;
            }
            if (!(h4.a > d2)) continue;
            if (!(h3.a >= h4.a)) {
                continue;
            }
            h3 = h4;
            break;
        }
        if (h3.a < d2) {
            h3 = h2;
        }
        if (h2.a > d2) {
            h2 = h3;
        }
        return new AbstractMap.SimpleEntry<h, h>(h2, h3);
    }

    @Override
    public double a(double d2) {
        block7: {
            if (this.c != null && this.d != null) break block7;
            return 0.0;
        }
        Map.Entry<h, h> entry = this.c(d2);
        h h2 = entry.getKey();
        h h3 = entry.getValue();
        if (h2.equals(h3)) {
            return 1.0 - h2.b;
        }
        double d3 = (h3.b - h2.b) / (h3.a - h2.a) * (d2 - h2.a) + h2.b;
        return 1.0 - d3;
    }

    public h b() {
        return this.c.a();
    }

    public h c() {
        return this.d.a();
    }

    public List<h> d() {
        return Collections.unmodifiableList(this.e);
    }

    public int e() {
        return this.f;
    }

    public void a(int n2) {
        if (this.f == n2) {
            return;
        }
        this.f = n2;
        this.a();
    }

    public c f() {
        return new c(this);
    }
}

