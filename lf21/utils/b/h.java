/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.b;

public class h {
    public double a;
    public double b;

    public h() {
        this.a = 0.0;
        this.b = 0.0;
    }

    public h(double d2, double d3) {
        this.a = d2;
        this.b = d3;
    }

    public h(h h2) {
        this.a = h2.a;
        this.b = h2.b;
    }

    public h(String string) {
        string = string.replace(" ", "");
        if (!string.contains(",")) {
            return;
        }
        String[] stringArray = string.split(",");
        if (stringArray.length <= 1) {
            return;
        }
        String string2 = stringArray[0];
        String string3 = stringArray[1];
        this.a = Double.parseDouble(string2.trim().replace("\ufeff", ""));
        this.b = Double.parseDouble(string3.trim().replace("\ufeff", ""));
    }

    public h a() {
        return new h(this);
    }

    public h a(double d2, double d3) {
        this.a *= d2;
        this.b *= d3;
        return this;
    }

    public h a(double d2) {
        return this.a(d2, d2);
    }

    public h a(h h2) {
        return this.a(h2.a, h2.b);
    }

    public h b(double d2, double d3) {
        this.a += d2;
        this.b += d3;
        return this;
    }

    public h b(h h2) {
        return this.b(h2.a, h2.b);
    }

    public h b(double d2) {
        return this.b(d2, d2);
    }

    public h c(double d2, double d3) {
        this.a -= d2;
        this.b -= d3;
        return this;
    }

    public h c(h h2) {
        return this.b(h2.a, h2.b);
    }

    public h c(double d2) {
        return this.b(d2, d2);
    }

    public h d(double d2, double d3) {
        this.a /= d2;
        this.b /= d3;
        return this;
    }

    public h d(h h2) {
        return this.d(h2.a, h2.b);
    }

    public h d(double d2) {
        return this.d(d2, d2);
    }

    public h e(double d2, double d3) {
        this.a = d2;
        this.b = d3;
        return this;
    }

    public h e(h h2) {
        return this.e(h2.a, h2.b);
    }
}

