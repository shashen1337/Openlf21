/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import lf21.LF21Client;
import lf21.gui.b.a;
import lf21.gui.b.c;
import lf21.gui.b.d;
import lf21.gui.l;
import lf21.manager.ConfigManager;
import lf21.render.S;
import lf21.render.v_0;
import lf21.utils.b.f;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public final class e
extends c {
    public static final float f;
    private static final float g;
    private static final float h;
    private static final float i;
    private static final float j;
    private List<String> k = Collections.emptyList();
    private boolean l;
    private float m;
    private float n;
    private boolean o;
    private String p = "";
    private String q = "";
    private long r;
    private float s;
    private float t;
    private final lf21.utils.a.e u = new lf21.utils.a.e();
    private a v = lf21.gui.b.a.NONE;
    private String w = "";
    private static final String[] x;

    public e() {
        this.c = 120.0f;
        this.m();
    }

    @Override
    public void a(l l2, GuiGraphics guiGraphics, PoseStack poseStack, int n2, int n3, float f2, float f3) {
        Object object;
        if (this.l) {
            this.a = (float)n2 + this.m;
            this.b = (float)n3 + this.n;
        }
        this.s = 38.0f + Math.max(20.0f, (float)this.k.size() * 49.0f) + 8.0f;
        this.d = Math.min(260.0f, 20.0f + this.s);
        float f4 = Math.max(0.0f, this.s - (this.d - 20.0f));
        this.t = Mth.clamp((float)this.t, (float)0.0f, (float)f4);
        this.u.a((double)this.t, 0.22, lf21.utils.b.f.c);
        this.u.a();
        boolean bl = lf21.utils.misc.c.a(n2, n3, this.a, this.b, this.c, this.d);
        if (bl) {
            lf21.gui.l.d = null;
        }
        RenderUtil.a(poseStack, this.a, this.b, this.c, this.d, 6.0f, 18.0f, 0.75f * f2, 0);
        RenderUtil.b(poseStack, this.a, this.b, this.c, this.d, 6.0f, lf21.utils.render.a.a(lf21.gui.b.d.f, f2 * 0.5f));
        RenderUtil.b(poseStack, this.a, this.b, this.c, 1.0f, lf21.utils.render.a.a(lf21.gui.b.d.h, f2), lf21.utils.render.a.a(lf21.gui.b.d.g, f2));
        float f5 = this.b + (20.0f - v_0.p.c()) / 2.0f + 3.0f;
        String[] stringArray = x;
        v_0.p.a(poseStack, "Config", (double)(this.a + 8.0f), (double)f5, lf21.utils.render.a.a(-1, f2));
        S.a(false);
        RenderUtil.a(poseStack, this.a + 0.5f, this.b + 20.0f, this.c - 1.0f, this.d - 20.0f - 0.5f, -1);
        S.b(true);
        float f6 = this.b + 20.0f + 8.0f - this.u.e();
        RenderUtil.a(poseStack, this.a + 8.0f, f6, this.c - 16.0f, 24.0f, 3.0f, 18.0f, 0.75f * f2, 0);
        lf21.gui.b.e.a(poseStack, this.a + 8.0f, f6, this.c - 16.0f, 24.0f, lf21.gui.b.d.h, f2);
        lf21.gui.b.e.a(poseStack, this.a + 9.0f, f6 + 1.0f, this.c - 18.0f, 22.0f, this.o ? lf21.utils.render.a.a(25, 42, 42) : lf21.gui.b.d.f, f2 * 0.5f);
        if (this.o) {
            boolean bl2;
            boolean bl3 = bl2 = (System.currentTimeMillis() / 450L & 1L) == 0L;
            if (this.p.isEmpty()) {
                stringArray = x;
                v1 = "Config name";
            } else {
                v1 = object = this.p;
            }
            if (bl2) {
                object = (String)object + "_";
            }
        } else {
            stringArray = x;
            object = "+  Add";
        }
        object = lf21.gui.b.e.b((String)object, this.c - 28.0f);
        v_0.q.d(poseStack, (String)object, this.a + this.c / 2.0f, f6 + 6.0f, lf21.utils.render.a.a(this.o && this.p.isEmpty() ? lf21.utils.render.a.a(145, 145, 145) : -1, f2));
        float f7 = f6 + 24.0f + 6.0f;
        if (this.k.isEmpty()) {
            v_0.j.d(poseStack, "No saved configs", this.a + this.c / 2.0f, f7 + 14.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(145, 145, 145), f2));
        } else {
            for (int i2 = 0; i2 < this.k.size(); ++i2) {
                this.a(poseStack, this.k.get(i2), f7, n2, n3, f2);
                f7 += 49.0f;
            }
        }
        S.a();
        if (!this.q.isEmpty() && System.currentTimeMillis() < this.r) {
            v_0.j.d(poseStack, this.q, this.a + this.c / 2.0f, this.b + this.d - 8.0f, lf21.utils.render.a.a(lf21.gui.b.d.h, f2));
        }
        if (this.v != lf21.gui.b.a.NONE) {
            this.a(poseStack, f2);
        }
    }

    private void a(PoseStack poseStack, String string, float f2, int n2, int n3, float f3) {
        String string2 = lf21.gui.b.e.a(string, this.c - 20.0f);
        v_0.m.a(poseStack, string2, (double)(this.a + 10.0f), (double)(f2 + 6.0f), lf21.utils.render.a.a(-1, f3));
        float f4 = this.a + 13.0f;
        float f5 = f2 + 19.0f;
        String[] stringArray = x;
        this.a(poseStack, "Load", f4, f5, 27.0f, n2, n3, f3, false);
        this.a(poseStack, "Save", f4 + 30.0f, f5, 27.0f, n2, n3, f3, false);
        this.a(poseStack, "Delete", f4 + 60.0f, f5, 35.0f, n2, n3, f3, true);
        RenderUtil.a(poseStack, this.a + 8.0f, f2 + 44.0f - 1.0f, this.c - 16.0f, 0.6f, lf21.utils.render.a.a(lf21.utils.render.a.a(62, 62, 62), f3));
    }

    private void a(PoseStack poseStack, String string, float f2, float f3, float f4, int n2, int n3, float f5, boolean bl) {
        boolean bl2 = lf21.utils.misc.c.a(n2, n3, f2, f3, f4, 22.0f);
        if (bl2) {
            int n4 = bl ? lf21.utils.render.a.a(78, 35, 38) : lf21.utils.render.a.a(42, 67, 66);
            lf21.gui.b.e.a(poseStack, f2, f3, f4, 18.0f, n4, f5);
        }
        v_0.j.d(poseStack, string, f2 + f4 / 2.0f, f3 + 4.0f, lf21.utils.render.a.a(bl ? lf21.utils.render.a.a(255, 170, 170) : -1, f5));
    }

    private void a(PoseStack poseStack, float f2) {
        String string;
        String[] stringArray;
        RenderUtil.b(poseStack, this.a + 3.0f, this.b + 22.0f, this.c - 6.0f, this.d - 25.0f, 5.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(9, 9, 9), f2 * 0.94f));
        float f3 = this.a + 6.0f;
        float f4 = this.b + this.d / 2.0f - 46.0f;
        float f5 = this.c - 12.0f;
        RenderUtil.b(poseStack, f3, f4, f5, 92.0f, 5.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(34, 34, 34), f2));
        if (this.v == lf21.gui.b.a.DELETE) {
            stringArray = x;
            string = "Delete permanently?";
        } else {
            stringArray = x;
            string = "Overwrite this config?";
        }
        String string2 = string;
        v_0.q.d(poseStack, string2, this.a + this.c / 2.0f, f4 + 14.0f, lf21.utils.render.a.a(-1, f2));
        v_0.j.d(poseStack, lf21.gui.b.e.a(this.w, f5 - 24.0f), this.a + this.c / 2.0f, f4 + 34.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(165, 165, 165), f2));
        lf21.gui.b.e.a(poseStack, f3 + 7.0f, f4 + 61.0f, 43.0f, 20.0f, this.v == lf21.gui.b.a.DELETE ? lf21.utils.render.a.a(92, 38, 42) : lf21.utils.render.a.a(31, 79, 75), f2);
        lf21.gui.b.e.a(poseStack, f3 + f5 - 50.0f, f4 + 61.0f, 43.0f, 20.0f, lf21.utils.render.a.a(52, 52, 52), f2);
        stringArray = x;
        v_0.j.d(poseStack, "Confirm", f3 + 28.5f, f4 + 66.0f, lf21.utils.render.a.a(-1, f2));
        v_0.j.d(poseStack, "Cancel", f3 + f5 - 28.5f, f4 + 66.0f, lf21.utils.render.a.a(-1, f2));
    }

    private static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, int n2, float f6) {
        RenderUtil.b(poseStack, f2, f3, f4, f5, 3.0f, lf21.utils.render.a.a(n2, f6));
    }

    @Override
    public boolean a(double d2, double d3, int n2) {
        if (n2 != 0 || !lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b, this.c, this.d)) {
            return false;
        }
        if (this.v != lf21.gui.b.a.NONE) {
            return this.a((float)d2, (float)d3);
        }
        if (lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b, this.c, 20.0f)) {
            this.l = true;
            this.m = this.a - (float)d2;
            this.n = this.b - (float)d3;
            return true;
        }
        if (!lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b + 20.0f, this.c, this.d - 20.0f)) {
            return true;
        }
        float f2 = this.b + 20.0f + 8.0f - this.u.e();
        if (lf21.utils.misc.c.a((float)d2, (float)d3, this.a + 8.0f, f2, this.c - 16.0f, 24.0f)) {
            this.o = true;
            this.p = "";
            return true;
        }
        float f3 = f2 + 24.0f + 6.0f;
        for (int i2 = 0; i2 < this.k.size(); ++i2) {
            String string = this.k.get(i2);
            float f4 = this.a + 13.0f;
            float f5 = f3 + 19.0f;
            if (lf21.utils.misc.c.a((float)d2, (float)d3, f4, f5, 27.0f, 18.0f)) {
                this.a(string);
                return true;
            }
            if (lf21.utils.misc.c.a((float)d2, (float)d3, f4 + 30.0f, f5, 27.0f, 18.0f)) {
                this.v = lf21.gui.b.a.SAVE;
                this.w = string;
                return true;
            }
            if (lf21.utils.misc.c.a((float)d2, (float)d3, f4 + 60.0f, f5, 35.0f, 18.0f)) {
                this.v = lf21.gui.b.a.DELETE;
                this.w = string;
                return true;
            }
            f3 += 49.0f;
        }
        return true;
    }

    private boolean a(float f2, float f3) {
        float f4 = this.a + 6.0f;
        float f5 = this.b + this.d / 2.0f - 46.0f;
        float f6 = this.c - 12.0f;
        if (lf21.utils.misc.c.a(f2, f3, f4 + 7.0f, f5 + 61.0f, 43.0f, 20.0f)) {
            this.j();
        } else if (lf21.utils.misc.c.a(f2, f3, f4 + f6 - 50.0f, f5 + 61.0f, 43.0f, 20.0f)) {
            this.k();
        }
        return true;
    }

    @Override
    public boolean b(double d2, double d3, int n2) {
        boolean bl = this.l;
        this.l = false;
        return bl;
    }

    @Override
    public boolean a(double d2, double d3, double d4) {
        if (!lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b, this.c, this.d)) {
            return false;
        }
        float f2 = Math.max(0.0f, this.s - (this.d - 20.0f));
        if (f2 <= 0.0f) {
            return false;
        }
        this.t = Mth.clamp((float)(this.t - (float)d4 * 50.0f), (float)0.0f, (float)f2);
        return true;
    }

    public boolean a(int n2) {
        if (this.v != lf21.gui.b.a.NONE) {
            if (n2 == 256) {
                this.k();
            }
            return true;
        }
        if (!this.o) {
            return false;
        }
        if (n2 == 256) {
            this.o = false;
            this.p = "";
        } else if (n2 == 259 && !this.p.isEmpty()) {
            this.p = this.p.substring(0, this.p.length() - 1);
        } else if (n2 == 257 || n2 == 335) {
            this.i();
        }
        return true;
    }

    public boolean a(char c2) {
        if (!this.o || Character.isISOControl(c2)) {
            return false;
        }
        if (this.p.length() < 24) {
            this.p = this.p + c2;
        }
        return true;
    }

    public boolean h() {
        return this.o || this.v != lf21.gui.b.a.NONE;
    }

    private void i() {
        try {
            String string = ConfigManager.f(this.p);
            if (!LF21Client.getInstance().getConfigManager().a(string)) {
                String[] stringArray = x;
                this.b("Name already exists");
                return;
            }
            this.o = false;
            this.p = "";
            String[] stringArray = x;
            this.b("Config added");
            this.m();
        }
        catch (Exception exception) {
            this.b("Check the config name");
        }
    }

    private void a(String string) {
        try {
            LF21Client.getInstance().getConfigManager().c(string);
            String[] stringArray = x;
            this.b("Loaded " + string);
        }
        catch (IOException iOException) {
            this.b("Load failed");
        }
    }

    private void j() {
        try {
            block11: {
                block10: {
                    if (this.v != lf21.gui.b.a.SAVE) break block10;
                    LF21Client.getInstance().getConfigManager().b(this.w);
                    this.b("Saved " + this.w);
                    break block11;
                }
                if (this.v == lf21.gui.b.a.DELETE) {
                    LF21Client.getInstance().getConfigManager().d(this.w);
                    this.b("Deleted " + this.w);
                }
            }
            this.m();
        }
        catch (IOException iOException) {
            this.b("Operation failed");
        }
        finally {
            this.k();
        }
    }

    private void k() {
        this.v = lf21.gui.b.a.NONE;
        this.w = "";
    }

    public void l() {
        if (LF21Client.isReady()) {
            this.k = LF21Client.getInstance().getConfigManager().f();
        }
        float f2 = Math.max(0.0f, this.s - (this.d - 20.0f));
        this.t = Mth.clamp((float)this.t, (float)0.0f, (float)f2);
    }

    private void m() {
        this.l();
    }

    private void b(String string) {
        this.q = string;
        this.r = System.currentTimeMillis() + 2200L;
    }

    private static String a(String string, float f2) {
        if (v_0.m.b(string) <= f2) {
            return string;
        }
        String string2 = string;
        while (!string2.isEmpty()) {
            String[] stringArray = x;
            if (!(v_0.m.b(string2 + "...") > f2)) break;
            string2 = string2.substring(0, string2.length() - 1);
        }
        return string2 + "...";
    }

    private static String b(String string, float f2) {
        if (v_0.q.b(string) <= f2) {
            return string;
        }
        String string2 = string;
        while (!string2.isEmpty()) {
            String[] stringArray = x;
            if (!(v_0.q.b("..." + string2) > f2)) break;
            string2 = string2.substring(1);
        }
        return "..." + string2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                lf21.gui.b.e.j = 260.0f;
                lf21.gui.b.e.i = 44.0f;
                lf21.gui.b.e.h = 24.0f;
                lf21.gui.b.e.g = 20.0f;
                lf21.gui.b.e.f = 120.0f;
                var5 = new String[21];
                var3_1 = 0;
                var2_2 = "%~qI\u0003X1)\f5piJeI8\u0017{cIh\u00108p'_mX}\u0012?dCbHq\u0011l\u00138~jI,Ot\u0004zfHu\u000e}\u000evtX\u007f\u0003X1)\u00062zkIxK\u00065piJeI\u0006%~qIh\u000e\u0006]?'mhJ\u0004:pfH\u00132zkIxK8\u0006zuAm@}\u0018kkU3\u00169ib^{\\q\u0002z'XdGkV|hBjG\u007fI\u00109ob^mZq\u0019q'JmGt\u0013{\u00065~iOiB\u00075piJe\\u\u0007:pfHiJ8\b2zkIxK|V\u000b:pfH,Hy\u001fsbH";
                var4_3 = "%~qI\u0003X1)\f5piJeI8\u0017{cIh\u00108p'_mX}\u0012?dCbHq\u0011l\u00138~jI,Ot\u0004zfHu\u000e}\u000evtX\u007f\u0003X1)\u00062zkIxK\u00065piJeI\u0006%~qIh\u000e\u0006]?'mhJ\u0004:pfH\u00132zkIxK8\u0006zuAm@}\u0018kkU3\u00169ib^{\\q\u0002z'XdGkV|hBjG\u007fI\u00109ob^mZq\u0019q'JmGt\u0013{\u00065~iOiB\u00075piJe\\u\u0007:pfHiJ8\b2zkIxK|V\u000b:pfH,Hy\u001fsbH".length();
                var1_4 = 4;
                var0_5 = -1;
lbl12:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl17:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.gui.b.e.a(lf21.gui.b.e.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "5wbOg\u000el\u001ez'Oc@~\u001fx'BmC}\u000b5piJeI8\u0018~jI";
                    var4_3 = "5wbOg\u000el\u001ez'Oc@~\u001fx'BmC}\u000b5piJeI8\u0018~jI".length();
                    var1_4 = 21;
                    var0_5 = -1;
lbl26:
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
lbl31:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.gui.b.e.a(lf21.gui.b.e.c(v1));
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
lbl42:
                // 1 sources

                ** continue;
            }
        }
        lf21.gui.b.e.x = var5;
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x18);
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
                    case 0 -> 118;
                    case 1 -> 31;
                    case 2 -> 7;
                    case 3 -> 44;
                    case 4 -> 12;
                    case 5 -> 46;
                    default -> 24;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

