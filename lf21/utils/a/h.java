/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.a;

import net.minecraft.util.Mth;

public class h {
    private long a = System.currentTimeMillis();

    public void a() {
        this.a = System.currentTimeMillis();
    }

    public void a(long l2) {
        this.a = l2;
    }

    public void b(long l2) {
        this.a = l2;
    }

    public boolean a(long l2, boolean bl) {
        if (System.currentTimeMillis() - this.a > l2) {
            if (bl) {
                this.a();
            }
            return true;
        }
        return false;
    }

    public boolean a(float f2) {
        return (float)(System.currentTimeMillis() - this.a) >= f2;
    }

    public boolean a(double d2, boolean bl) {
        boolean bl2;
        boolean bl3 = bl2 = (double)Mth.clamp((float)(System.currentTimeMillis() - this.a), (float)0.0f, (float)((float)d2)) >= d2;
        if (bl2 && bl) {
            this.a();
        }
        return bl2;
    }

    public long b() {
        return System.currentTimeMillis() - this.a;
    }

    public boolean c(long l2) {
        return System.currentTimeMillis() - this.a > l2;
    }

    public boolean d(long l2) {
        return System.currentTimeMillis() - this.a >= l2;
    }
}

