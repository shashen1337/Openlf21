/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.b;

import lf21.utils.b.d;

public class f {
    public static final d a = d2 -> 1.0 + 2.70158 * Math.pow(d2 - 1.0, 3.0) + 1.70158 * Math.pow(d2 - 1.0, 2.0);
    public static final d b = d2 -> 1.0 - (d2 - 1.0) * (d2 - 1.0);
    public static final d c = lf21.utils.b.f.b(2);
    public static final d d = lf21.utils.b.f.a(3);
    public static final d e = lf21.utils.b.f.b(3);
    public static final d f = lf21.utils.b.f.b(4);
    public static final d g = lf21.utils.b.f.b(5);
    public static final d h = d2 -> Math.sin(d2 * Math.PI / 2.0);
    public static final d i = d2 -> {
        if (d2 == 0.0 || d2 == 1.0) {
            return d2;
        }
        return Math.pow(2.0, -10.0 * d2) * Math.sin((d2 * 10.0 - 0.75) * 2.0943951023931953) + 1.0;
    };
    public static final d j = d2 -> {
        double d3 = 7.5625;
        double d4 = 2.75;
        if (d2 < 1.0 / d4) {
            return d3 * Math.pow(d2, 2.0);
        }
        if (d2 < 2.0 / d4) {
            return d3 * Math.pow(d2 - 1.5 / d4, 2.0) + 0.75;
        }
        if (d2 < 2.5 / d4) {
            return d3 * Math.pow(d2 - 2.25 / d4, 2.0) + 0.9375;
        }
        return d3 * Math.pow(d2 - 2.625 / d4, 2.0) + 0.984375;
    };

    private f() {
    }

    public static d a(double d2) {
        return d3 -> Math.pow(d3, d2);
    }

    public static d a(int n2) {
        return lf21.utils.b.f.a((double)n2);
    }

    public static d b(double d2) {
        return d3 -> 1.0 - Math.pow(1.0 - d3, d2);
    }

    public static d b(int n2) {
        return lf21.utils.b.f.b((double)n2);
    }

    public static d c(double d2) {
        return d3 -> {
            if (d3 < 0.5) {
                return Math.pow(2.0, d2 - 1.0) * Math.pow(d3, d2);
            }
            return 1.0 - Math.pow(-2.0 * d3 + 2.0, d2) / 2.0;
        };
    }

    private static double d(double d2) {
        if (d2 < 0.5) {
            return (1.0 - j.a(1.0 - 2.0 * d2)) / 2.0;
        }
        return (1.0 + j.a(2.0 * d2 - 1.0)) / 2.0;
    }

    private static double e(double d2) {
        return 1.0 - j.a(1.0 - d2);
    }

    private static double f(double d2) {
        if (d2 == 0.0 || d2 == 1.0) {
            return d2;
        }
        if (d2 < 0.5) {
            return Math.pow(2.0, 20.0 * d2 - 10.0) / 2.0;
        }
        return (2.0 - Math.pow(2.0, -20.0 * d2 + 10.0)) / 2.0;
    }

    private static double g(double d2) {
        if (d2 != 1.0) {
            return 1.0 - Math.pow(2.0, -10.0 * d2);
        }
        return d2;
    }

    private static double h(double d2) {
        if (d2 != 0.0) {
            return Math.pow(2.0, 10.0 * d2 - 10.0);
        }
        return d2;
    }

    private static double i(double d2) {
        if (d2 == 0.0 || d2 == 1.0) {
            return d2;
        }
        if (d2 < 0.5) {
            return -(Math.pow(2.0, 20.0 * d2 - 10.0) * Math.sin((20.0 * d2 - 11.125) * 1.3962634015954636)) / 2.0;
        }
        return Math.pow(2.0, -20.0 * d2 + 10.0) * Math.sin((20.0 * d2 - 11.125) * 1.3962634015954636) / 2.0 + 1.0;
    }

    private static double j(double d2) {
        if (d2 == 0.0 || d2 == 1.0) {
            return d2;
        }
        return Math.pow(-2.0, 10.0 * d2 - 10.0) * Math.sin((d2 * 10.0 - 10.75) * 2.0943951023931953);
    }

    private static double k(double d2) {
        if (d2 < 0.5) {
            return (1.0 - Math.sqrt(1.0 - Math.pow(2.0 * d2, 2.0))) / 2.0;
        }
        return (Math.sqrt(1.0 - Math.pow(-2.0 * d2 + 2.0, 2.0)) + 1.0) / 2.0;
    }

    private static double l(double d2) {
        return Math.sqrt(1.0 - Math.pow(d2 - 1.0, 2.0));
    }

    private static double m(double d2) {
        return 1.0 - Math.sqrt(1.0 - Math.pow(d2, 2.0));
    }

    private static double n(double d2) {
        return -(Math.cos(Math.PI * d2) - 1.0) / 2.0;
    }

    private static double o(double d2) {
        return 1.0 - Math.cos(d2 * Math.PI / 2.0);
    }

    private static double p(double d2) {
        return 2.70158 * Math.pow(d2, 3.0) - 1.70158 * Math.pow(d2, 2.0);
    }

    private static double q(double d2) {
        if (d2 < 0.5) {
            return Math.pow(2.0 * d2, 2.0) * (7.189819 * d2 - 2.5949095) / 2.0;
        }
        return (Math.pow(2.0 * d2 - 2.0, 2.0) * (3.5949095 * (d2 * 2.0 - 2.0) + 2.5949095) + 2.0) / 2.0;
    }
}

