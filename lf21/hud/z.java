/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import lf21.modules.Module;
import lf21.utils.a.e;
import lf21.utils.b.f;

final class z {
    private final Module a;
    private final e b = new e();
    private final e c = new e();
    private boolean d;

    private z(Module module, boolean bl) {
        this.a = module;
        if (bl) {
            this.b.a(0.0);
            this.c.a(0.0);
            this.b.a(1.0, 0.3, f.e);
            this.c.a(1.0, 0.42, f.a);
        } else {
            this.b.a(1.0);
            this.b.d(1.0);
            this.c.a(1.0);
            this.c.d(1.0);
        }
    }

    private void a() {
        this.d = false;
        this.b.a(1.0, 0.3, f.e);
        this.c.a(1.0, 0.42, f.a);
    }

    private void c() {
        this.b.a();
        this.c.a();
    }

    private void b() {
        this.d = true;
        this.b.a(0.0, 0.25, f.d);
        this.c.a(0.0, 0.22, f.d);
    }

    private boolean d() {
        return this.d && this.b.c() && this.b.e() <= 0.001f;
    }
}

