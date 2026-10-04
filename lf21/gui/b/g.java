/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import lf21.gui.b.c;
import lf21.gui.b.d;
import lf21.gui.b.f;
import lf21.gui.b.h;
import lf21.gui.l;
import lf21.modules.Category;
import lf21.modules.impl.world.MusicBot;
import lf21.modules.impl.world.p;
import lf21.modules.impl.world.r;
import lf21.render.S;
import lf21.render.v_0;
import lf21.utils.a.e;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.a;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public final class g
extends c {
    public static final float f;
    private static final float g;
    private static final float h;
    private static final float i;
    private static final float j;
    private static final float k;
    private final d l;
    private final f m;
    private final e n = new e();
    private final e o = new e();
    private List<p> p = Collections.emptyList();
    private boolean q;
    private float r;
    private float s;
    private boolean t;
    private boolean u;
    private String v = "";
    private float w;
    private float x;
    private float y;
    private float z;
    private float A;
    private static final String[] B;

    public g() {
        this.c = 120.0f;
        this.l = new d(Category.WORLD, false);
        this.m = new f(this.l, MusicBot.I);
    }

    @Override
    public void a(l l2, GuiGraphics guiGraphics, PoseStack poseStack, int n2, int n3, float f2, float f3) {
        if (this.q) {
            this.a = (float)n2 + this.r;
            this.b = (float)n3 + this.s;
        }
        float f4 = Math.max(20.0f, this.m.f());
        float f5 = this.p.isEmpty() ? 20.0f : (float)this.p.size() * 40.0f;
        this.w = 7.0f + f4 + 6.0f + 24.0f + 6.0f + f5 + 6.0f + 55.0f + 7.0f;
        this.d = Math.min(260.0f, 20.0f + this.w);
        float f6 = Math.max(0.0f, this.w - (this.d - 20.0f));
        this.x = Mth.clamp((float)this.x, (float)0.0f, (float)f6);
        this.o.a((double)this.x, 0.22, lf21.utils.b.f.c);
        this.o.a();
        float f7 = this.o.e();
        float f8 = this.b + 20.0f + 7.0f - f7;
        this.y = f8 + f4 + 6.0f;
        this.z = this.y + 24.0f + 6.0f;
        this.A = this.z + f5 + 6.0f;
        boolean bl = lf21.utils.misc.c.a(n2, n3, this.a, this.b, this.c, this.d);
        if (bl) {
            lf21.gui.l.d = this.l;
        }
        this.l.d(false);
        this.l.a((h<?>)null);
        RenderUtil.a(poseStack, this.a, this.b, this.c, this.d, 6.0f, 18.0f, 0.75f * f2, 0);
        RenderUtil.b(poseStack, this.a, this.b, this.c, this.d, 6.0f, lf21.utils.render.a.a(lf21.gui.b.d.f, f2 * 0.5f));
        RenderUtil.b(poseStack, this.a, this.b, this.c, 1.0f, lf21.utils.render.a.a(lf21.gui.b.d.h, f2), lf21.utils.render.a.a(lf21.gui.b.d.g, f2));
        float f9 = this.b + (20.0f - v_0.p.c()) / 2.0f + 3.0f;
        v_0.p.a(poseStack, "MusicBot", (double)(this.a + 8.0f), (double)f9, lf21.utils.render.a.a(-1, f2));
        S.a(false);
        RenderUtil.a(poseStack, this.a + 0.5f, this.b + 20.0f, this.c - 1.0f, this.d - 20.0f - 0.5f, -1);
        S.b(true);
        this.m.a(this.a);
        this.m.b(f8);
        this.m.a(l2, guiGraphics, poseStack, n2, n3, f2, f3);
        f4 = Math.max(20.0f, this.m.f());
        this.y = f8 + f4 + 6.0f;
        this.z = this.y + 24.0f + 6.0f;
        this.A = this.z + f5 + 6.0f;
        this.w = 7.0f + f4 + 6.0f + 24.0f + 6.0f + f5 + 6.0f + 55.0f + 7.0f;
        this.l.r().a(this.l.x() ? 1.0 : 0.0, 0.18, lf21.utils.b.f.c);
        this.l.r().a();
        this.a(poseStack, f2);
        this.a(poseStack, n2, n3, f2);
        this.b(poseStack, n2, n3, f2);
        S.a();
        if (this.u) {
            this.e(n2);
        }
    }

    private void a(PoseStack poseStack, float f2) {
        Object object;
        float f3 = this.a + 8.0f;
        float f4 = this.c - 16.0f;
        RenderUtil.a(poseStack, f3, this.y, f4, 24.0f, 3.0f, 18.0f, 0.75f * f2, 0);
        RenderUtil.b(poseStack, f3, this.y, f4, 24.0f, 3.0f, lf21.utils.render.a.a(lf21.gui.b.d.h, f2));
        RenderUtil.b(poseStack, f3 + 1.0f, this.y + 1.0f, f4 - 2.0f, 22.0f, 2.5f, lf21.utils.render.a.a(lf21.utils.render.a.a(25, 25, 25), f2 * 0.5f));
        if (this.v.isEmpty()) {
            String[] stringArray = B;
            v0 = "Search music";
        } else {
            v0 = object = this.v;
        }
        if (this.t && (System.currentTimeMillis() / 450L & 1L) == 0L) {
            object = (String)object + "_";
        }
        object = lf21.gui.b.g.b((String)object, f4 - 33.0f);
        v_0.c.a(poseStack, (String)object, (double)(f3 + 6.0f), (double)(this.y + 7.0f), lf21.utils.render.a.a(this.v.isEmpty() ? lf21.utils.render.a.a(140, 140, 140) : -1, f2));
        RenderUtil.b(poseStack, f3 + f4 - 25.0f, this.y + 3.0f, 21.0f, 18.0f, 3.0f, lf21.utils.render.a.a(lf21.gui.b.d.g, f2));
        v_0.m.d(poseStack, "Go", f3 + f4 - 14.5f, this.y + 8.0f, lf21.utils.render.a.a(-1, f2));
    }

    private void a(PoseStack poseStack, int n2, int n3, float f2) {
        if (this.p.isEmpty()) {
            String string;
            if (this.v.isEmpty()) {
                String[] stringArray = B;
                string = "Search your library";
            } else {
                String[] stringArray = B;
                string = "No results";
            }
            v_0.j.d(poseStack, string, this.a + this.c / 2.0f, this.z + 6.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(145, 145, 145), f2));
            return;
        }
        float f3 = this.z;
        for (int i2 = 0; i2 < this.p.size(); ++i2) {
            p p2 = this.p.get(i2);
            String[] stringArray = B;
            String string = String.format(Locale.ROOT, "%.0f%%", p2.c() * 100.0);
            String string2 = lf21.gui.b.g.a(p2.b(), this.c - 52.0f);
            v_0.c.a(poseStack, string2, (double)(this.a + 9.0f), (double)(f3 + 4.0f), lf21.utils.render.a.a(-1, f2));
            float f4 = v_0.j.b(string);
            v_0.j.a(poseStack, string, (double)(this.a + this.c - 9.0f - f4), (double)(f3 + 4.0f), lf21.utils.render.a.a(lf21.gui.b.d.h, f2));
            this.a(poseStack, "Load", this.a + 9.0f, f3 + 18.0f, 45.0f, n2, n3, f2);
            this.a(poseStack, "Preview", this.a + 58.0f, f3 + 18.0f, 53.0f, n2, n3, f2);
            RenderUtil.a(poseStack, this.a + 8.0f, f3 + 40.0f - 1.0f, this.c - 16.0f, 0.6f, lf21.utils.render.a.a(lf21.utils.render.a.a(62, 62, 62), f2));
            f3 += 40.0f;
        }
    }

    private void a(PoseStack poseStack, String string, float f2, float f3, float f4, int n2, int n3, float f5) {
        if (lf21.utils.misc.c.a(n2, n3, f2, f3, f4, 18.0f)) {
            RenderUtil.b(poseStack, f2, f3, f4, 18.0f, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(42, 67, 66), f5));
        }
        v_0.j.d(poseStack, string, f2 + f4 / 2.0f, f3 + 4.0f, lf21.utils.render.a.a(-1, f5));
    }

    private void b(PoseStack poseStack, int n2, int n3, float f2) {
        String string;
        r r2 = MusicBot.I.y();
        String string2 = lf21.gui.b.g.a(r2.a(), this.c - 18.0f);
        v_0.c.a(poseStack, string2, (double)(this.a + 9.0f), (double)(this.A + 3.0f), lf21.utils.render.a.a(-1, f2));
        String string3 = lf21.gui.b.g.b(r2.b()) + "/" + lf21.gui.b.g.b(r2.c());
        float f3 = v_0.j.b(string3);
        v_0.j.a(poseStack, string3, (double)(this.a + this.c - 9.0f - f3), (double)(this.A + 15.0f), lf21.utils.render.a.a(lf21.utils.render.a.a(158, 222, 230), f2));
        double d2 = r2.c() <= 0 ? 0.0 : (double)r2.b() / (double)r2.c();
        this.n.a(d2, 0.18, lf21.utils.b.f.c);
        this.n.a();
        float f4 = this.a + 9.0f;
        float f5 = this.A + 29.0f;
        float f6 = this.c - 18.0f;
        RenderUtil.b(poseStack, f4, f5, f6, 4.0f, 2.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(54, 66, 72), f2));
        if (this.n.e() > 0.0f) {
            RenderUtil.b(poseStack, f4, f5, f6 * this.n.e(), 4.0f, 2.0f, lf21.utils.render.a.a(lf21.gui.b.d.h, f2));
        }
        if (!r2.e()) {
            var18_13 = B;
            string = "--";
        } else if (r2.d()) {
            var18_13 = B;
            string = "Pause";
        } else {
            var18_13 = B;
            string = "Resume";
        }
        String string4 = string;
        float f7 = this.a + 9.0f;
        float f8 = this.A + 37.0f;
        if (r2.e() && lf21.utils.misc.c.a(n2, n3, f7, f8, 47.0f, 17.0f)) {
            RenderUtil.b(poseStack, f7, f8, 47.0f, 17.0f, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(42, 67, 66), f2));
        }
        v_0.j.d(poseStack, string4, f7 + 23.5f, f8 + 3.0f, lf21.utils.render.a.a(r2.e() ? -1 : lf21.utils.render.a.a(110, 110, 110), f2));
        float f9 = this.a + 64.0f;
        if (r2.e() && lf21.utils.misc.c.a(n2, n3, f9, f8, 47.0f, 17.0f)) {
            RenderUtil.b(poseStack, f9, f8, 47.0f, 17.0f, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(72, 39, 42), f2));
        }
        v_0.j.d(poseStack, "Stop", f9 + 23.5f, f8 + 3.0f, lf21.utils.render.a.a(r2.e() ? lf21.utils.render.a.a(255, 180, 180) : lf21.utils.render.a.a(110, 110, 110), f2));
    }

    @Override
    public boolean a(double d2, double d3, int n2) {
        boolean bl = lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b, this.c, this.d);
        if (!bl) {
            this.t = false;
            return false;
        }
        if (n2 == 0 && lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b, this.c, 20.0f)) {
            this.q = true;
            this.r = this.a - (float)d2;
            this.s = this.b - (float)d3;
            return true;
        }
        if (this.m.a(d2, d3, n2)) {
            return true;
        }
        if (!lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b + 20.0f, this.c, this.d - 20.0f)) {
            return true;
        }
        float f2 = this.a + 8.0f;
        float f3 = this.c - 16.0f;
        if (n2 == 0 && lf21.utils.misc.c.a((float)d2, (float)d3, f2, this.y, f3, 24.0f)) {
            if (d2 >= (double)(f2 + f3 - 27.0f)) {
                this.i();
            } else {
                this.t = true;
            }
            return true;
        }
        if (n2 == 0 && !this.p.isEmpty()) {
            float f4 = this.z;
            for (int i2 = 0; i2 < this.p.size(); ++i2) {
                p p2 = this.p.get(i2);
                if (lf21.utils.misc.c.a((float)d2, (float)d3, this.a + 9.0f, f4 + 18.0f, 45.0f, 18.0f)) {
                    MusicBot.I.a(p2.a(), false);
                    return true;
                }
                if (lf21.utils.misc.c.a((float)d2, (float)d3, this.a + 58.0f, f4 + 18.0f, 53.0f, 18.0f)) {
                    MusicBot.I.a(p2.a(), true);
                    return true;
                }
                f4 += 40.0f;
            }
        }
        r r2 = MusicBot.I.y();
        if (n2 == 0 && r2.e() && lf21.utils.misc.c.a((float)d2, (float)d3, this.a + 7.0f, this.A + 25.0f, this.c - 14.0f, 12.0f)) {
            this.u = true;
            this.e((float)d2);
            return true;
        }
        if (n2 == 0 && r2.e() && lf21.utils.misc.c.a((float)d2, (float)d3, this.a + 9.0f, this.A + 37.0f, 47.0f, 17.0f)) {
            MusicBot.I.z();
            return true;
        }
        if (n2 == 0 && r2.e() && lf21.utils.misc.c.a((float)d2, (float)d3, this.a + 64.0f, this.A + 37.0f, 47.0f, 17.0f)) {
            MusicBot.I.stopPlayback();
            return true;
        }
        this.t = false;
        return true;
    }

    @Override
    public boolean b(double d2, double d3, int n2) {
        boolean bl = this.q || this.u;
        this.q = false;
        this.u = false;
        return bl |= this.m.b(d2, d3, n2);
    }

    @Override
    public boolean a(double d2, double d3, double d4) {
        if (!lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b, this.c, this.d)) {
            return false;
        }
        float f2 = Math.max(0.0f, this.w - (this.d - 20.0f));
        if (f2 <= 0.0f) {
            return false;
        }
        this.x = Mth.clamp((float)(this.x - (float)d4 * 50.0f), (float)0.0f, (float)f2);
        return true;
    }

    public boolean a(int n2) {
        if (!this.t) {
            return false;
        }
        if (n2 == 256) {
            this.t = false;
        } else if (n2 == 259 && !this.v.isEmpty()) {
            this.v = this.v.substring(0, this.v.length() - 1);
        } else if (n2 == 257 || n2 == 335) {
            this.i();
        }
        return true;
    }

    public boolean a(char c2) {
        if (!this.t || Character.isISOControl(c2)) {
            return false;
        }
        if (this.v.length() < 48) {
            this.v = this.v + c2;
        }
        return true;
    }

    public boolean h() {
        return this.t;
    }

    private void i() {
        this.p = MusicBot.I.a(this.v, 50);
        this.x = 0.0f;
        this.t = false;
    }

    private void e(float f2) {
        float f3 = this.a + 9.0f;
        float f4 = this.c - 18.0f;
        MusicBot.I.a((f2 - f3) / f4);
    }

    private static String a(String string, float f2) {
        if (v_0.c.b(string) <= f2) {
            return string;
        }
        String string2 = string;
        while (!string2.isEmpty()) {
            String[] stringArray = B;
            if (!(v_0.c.b(string2 + "...") > f2)) break;
            string2 = string2.substring(0, string2.length() - 1);
        }
        return string2 + "...";
    }

    private static String b(String string, float f2) {
        if (v_0.c.b(string) <= f2) {
            return string;
        }
        String string2 = string;
        while (!string2.isEmpty()) {
            String[] stringArray = B;
            if (!(v_0.c.b("..." + string2) > f2)) break;
            string2 = string2.substring(1);
        }
        return "..." + string2;
    }

    private static String b(int n2) {
        int n3 = Math.max(0, n2 / 20);
        return String.format(Locale.ROOT, "%02d:%02d", n3 / 60, n3 % 60);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                lf21.gui.b.g.k = 260.0f;
                lf21.gui.b.g.j = 55.0f;
                lf21.gui.b.g.i = 40.0f;
                lf21.gui.b.g.h = 24.0f;
                lf21.gui.b.g.g = 20.0f;
                lf21.gui.b.g.f = 120.0f;
                var5 = new String[15];
                var3_1 = 0;
                var2_2 = "8\u0015;)D(\u0005\u0003FIp\u0006MIn9\bh\tMWl;\u0017hBZ\u0003\b%\u0012-6N\u000f\u001d\u001c\u0002EJ\u00058\u0006+,H\u0004;\u00131/\f;\u0002?-N%R\u0005\u0012-6N\u0004$\b?;\n&\b~-H>\u0007\u0004\u0013-\u0002/\b\u0006:\u0002-*@(";
                var4_3 = "8\u0015;)D(\u0005\u0003FIp\u0006MIn9\bh\tMWl;\u0017hBZ\u0003\b%\u0012-6N\u000f\u001d\u001c\u0002EJ\u00058\u0006+,H\u0004;\u00131/\f;\u0002?-N%R\u0005\u0012-6N\u0004$\b?;\n&\b~-H>\u0007\u0004\u0013-\u0002/\b\u0006:\u0002-*@(".length();
                var1_4 = 7;
                var0_5 = -1;
lbl13:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl18:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.gui.b.g.a(lf21.gui.b.g.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "FIp\u0013;\u0002?-N%R\u0011\b+-\r!\u001b\n\u0015?-T";
                    var4_3 = "FIp\u0013;\u0002?-N%R\u0011\b+-\r!\u001b\n\u0015?-T".length();
                    var1_4 = 3;
                    var0_5 = -1;
lbl27:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl32:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.gui.b.g.a(lf21.gui.b.g.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    break block10;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl43:
                // 1 sources

                ** continue;
            }
        }
        lf21.gui.b.g.B = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x72);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 104;
                    case 1 -> 103;
                    case 2 -> 94;
                    case 3 -> 95;
                    case 4 -> 45;
                    case 5 -> 77;
                    default -> 114;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

