/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.a;

import java.util.ArrayList;
import java.util.List;

public class g {
    private static final List<g> a = new ArrayList<g>();
    private int b = 0;

    public static void a() {
        for (g g2 : a) {
            ++g2.b;
        }
    }

    public g() {
        a.add(this);
    }

    public boolean a(int n2) {
        return this.b >= n2;
    }

    public boolean a(float f2) {
        return (float)this.b >= f2;
    }

    public void b() {
        this.b = 0;
    }
}

