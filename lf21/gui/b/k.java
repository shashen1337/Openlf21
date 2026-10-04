/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lf21.gui.b.d;
import lf21.gui.b.h;
import lf21.gui.f;
import lf21.gui.l;
import lf21.render.v_0;
import lf21.settings.impl.MultiSelectSetting;
import lf21.utils.a.e;
import lf21.utils.misc.c;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.a;
import lombok.Generated;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class k
extends h<MultiSelectSetting> {
    private final e i = new e();
    private final e j = new e();
    private final e k = new e();
    private final e l = new e();
    private boolean m;
    private boolean n;
    private String o;
    private boolean p;
    private boolean q;
    private static final String r;
    private static final String[] s;

    public k(d d2, MultiSelectSetting multiSelectSetting) {
        super(d2, multiSelectSetting);
    }

    @Override
    public void a(l l2, GuiGraphics guiGraphics, PoseStack poseStack, int n2, int n3, float f2, float f3) {
        String string;
        float f4;
        float f5;
        float f6 = this.b + 18.0f + 2.0f;
        float f7 = 108.0f;
        float f8 = 14.0f;
        this.o = null;
        this.m = lf21.utils.misc.c.a(n2, n3, this.a + 6.0f, f6, f7, f8);
        this.i.a(this.m ? 1.0 : 0.0, 0.22, lf21.utils.b.f.c);
        this.i.a();
        this.h.a(((MultiSelectSetting)this.g).getVisibility().displayable() ? 1.0 : 0.0, 0.2, lf21.utils.b.f.c);
        this.h.a();
        this.j.a(this.n ? 1.0 : 0.0, 0.2, lf21.utils.b.f.c);
        this.j.a();
        if (Mth.equal((float)(f2 *= this.h.e()), (float)0.0f)) {
            return;
        }
        float f9 = this.b + (18.0f - v_0.j.c()) / 2.0f + 1.0f;
        v_0.j.a(poseStack, ((MultiSelectSetting)this.g).getName(), (double)(this.a + 6.0f), (double)f9, lf21.utils.render.a.a(-1, f2 * 0.8f));
        float f10 = this.j.e();
        if (f10 > 0.0f) {
            f5 = f8 + (float)((MultiSelectSetting)this.g).getOptions().size() * f8 * f10;
            RenderUtil.b(poseStack, this.a + 6.0f, f6, f7, f5, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(60, 60, 60), f2 * f10));
            f4 = (f8 - v_0.m.c()) / 2.0f;
            float f11 = f6 + f4 + f8;
            for (String string2 : ((MultiSelectSetting)this.g).getOptions()) {
                if (lf21.utils.misc.c.a(n2, n3, this.a + 6.0f, f11 - f4, f7, f8)) {
                    this.o = string2;
                    this.l.a((double)(f11 - f4), 0.2, lf21.utils.b.f.c);
                }
                if (f6 + f5 > f11 + v_0.m.c()) {
                    v_0.m.d(poseStack, string2, this.a + 60.0f, f11, lf21.utils.render.a.a(-1, f2 * 0.8f * f10));
                    if (((List)((MultiSelectSetting)this.g).getValue()).contains(string2)) {
                        v_0.h.a(poseStack, "\ueb53", (double)(this.a + 6.0f + f7 - v_0.h.b("\ueb53") - 4.0f), (double)(f11 - f4 + (f8 - v_0.h.c()) / 2.0f + 0.5f), lf21.utils.render.a.a(-1, f2 * 0.56f * f10));
                    }
                }
                f11 += f8;
            }
        }
        this.k.a(this.o == null || !this.n ? 0.0 : 1.0, 0.18, lf21.utils.b.f.c);
        this.k.a();
        this.l.a();
        f5 = this.k.e();
        if (f5 > 0.0f) {
            RenderUtil.b(poseStack, this.a + 6.0f, this.l.e(), f7, f8, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(255, 255, 255), f2 * f5 * 1.0f * 0.1f));
        }
        f4 = this.i.e();
        RenderUtil.b(poseStack, this.a + 6.0f, f6, f7, f8, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a((int)(60.0f + 30.0f * f4), (int)(60.0f + 30.0f * f4), (int)(60.0f + 30.0f * f4)), f2));
        if (((List)((MultiSelectSetting)this.g).getValue()).isEmpty()) {
            String[] stringArray = s;
            string = "All";
        } else {
            string = (String)((List)((MultiSelectSetting)this.g).getValue()).get(0);
        }
        Object object = string;
        boolean bl = this.p = ((List)((MultiSelectSetting)this.g).getValue()).size() > 1;
        if (this.p) {
            object = (String)object + "...";
        }
        v_0.m.d(poseStack, (String)object, this.a + 60.0f, f6 + (f8 - v_0.m.c()) / 2.0f, lf21.utils.render.a.a(-1, f2 * 0.8f));
        String string3 = String.valueOf('\ueb5d');
        v_0.g.a(poseStack, string3, (double)(this.a + 6.0f + f7 - v_0.g.b(string3) - 2.0f), (double)(f6 + (f8 - v_0.g.c()) / 2.0f + 0.5f), lf21.utils.render.a.a(-1, f2 * 0.8f));
        if (this.o != null && this.n) {
            this.f.a(lf21.gui.f.b(this.o));
        } else if (lf21.utils.misc.c.a(n2, n3, this.a, this.b, 120.0f, this.f())) {
            this.f.a(lf21.gui.f.a(((MultiSelectSetting)this.g).getName()));
        }
    }

    @Override
    public float f() {
        return 36 + (this.n ? 14 * ((MultiSelectSetting)this.g).getOptions().size() : 0);
    }

    @Override
    public float b() {
        return 36.0f + (float)(14 * ((MultiSelectSetting)this.g).getOptions().size()) * this.j.e();
    }

    @Override
    public boolean a(double d2, double d3, int n2) {
        if (!((MultiSelectSetting)this.g).getVisibility().displayable()) {
            return false;
        }
        if (this.m) {
            this.n = !this.n;
            return true;
        }
        if (this.o != null && this.n) {
            ArrayList<String> arrayList = new ArrayList<String>((Collection)((MultiSelectSetting)this.g).getValue());
            if (arrayList.contains(this.o)) {
                if (arrayList.size() > 1) {
                    arrayList.remove(this.o);
                }
            } else {
                arrayList.add(this.o);
            }
            ((MultiSelectSetting)this.g).setValue(arrayList);
            return true;
        }
        return false;
    }

    @Generated
    public e k() {
        return this.i;
    }

    @Generated
    public e l() {
        return this.j;
    }

    @Generated
    public e m() {
        return this.k;
    }

    @Generated
    public e n() {
        return this.l;
    }

    @Generated
    public boolean o() {
        return this.m;
    }

    @Generated
    public void a(boolean bl) {
        this.m = bl;
    }

    @Generated
    public boolean p() {
        return this.n;
    }

    @Generated
    public void b(boolean bl) {
        this.n = bl;
    }

    @Generated
    public String q() {
        return this.o;
    }

    @Generated
    public void a(String string) {
        this.o = string;
    }

    @Generated
    public boolean r() {
        return this.p;
    }

    @Generated
    public void c(boolean bl) {
        this.p = bl;
    }

    @Generated
    public boolean s() {
        return this.q;
    }

    @Generated
    public void d(boolean bl) {
        this.q = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "ns\t\u0003ns\t\u0003\u00011K";
        int n3 = "ns\t\u0003ns\t\u0003\u00011K".length();
        int n4 = 3;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.gui.b.k.a(lf21.gui.b.k.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                s = stringArray;
                r = s[1];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x45);
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
                    case 0 -> 64;
                    case 1 -> 93;
                    case 2 -> 39;
                    case 3 -> 98;
                    case 4 -> 82;
                    case 5 -> 41;
                    default -> 69;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

