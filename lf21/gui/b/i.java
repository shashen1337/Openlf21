/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.gui.b.d;
import lf21.gui.b.h;
import lf21.gui.f;
import lf21.gui.l;
import lf21.render.v_0;
import lf21.settings.impl.BooleanSetting;
import lf21.utils.a.e;
import lf21.utils.misc.c;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.a;
import lombok.Generated;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class i
extends h<BooleanSetting> {
    private boolean i;
    private boolean j;
    private final e k = new e();
    private static final String l;
    private static final String[] m;

    public i(d d2, BooleanSetting booleanSetting) {
        super(d2, booleanSetting);
    }

    @Override
    public void a(l l2, GuiGraphics guiGraphics, PoseStack poseStack, int n2, int n3, float f2, float f3) {
        this.j = lf21.utils.misc.c.a(n2, n3, this.a, this.b, 120.0f, this.f());
        this.h.a(((BooleanSetting)this.g).getVisibility().displayable() ? 1.0 : 0.0, 0.2, lf21.utils.b.f.c);
        this.h.a();
        if (Mth.equal((float)(f2 *= this.h.e()), (float)0.0f)) {
            return;
        }
        Object object = ((BooleanSetting)this.g).getName();
        if (v_0.j.b((String)object) > 90.0f) {
            object = ((String)object).substring(0, 10);
            object = (String)object + m[0];
            this.i = true;
        }
        this.k.a((Boolean)((BooleanSetting)this.g).getValue() != false ? 1.0 : 0.0, 0.2, lf21.utils.b.f.c);
        this.k.a();
        if (Mth.equal((float)f2, (float)0.0f)) {
            return;
        }
        poseStack.pushPose();
        v_0.j.a(poseStack, (String)object, (double)(this.a + 6.0f), (double)(this.b + (this.f() - v_0.j.c()) / 2.0f), lf21.utils.render.a.a(-1, f2 * 0.8f));
        float f4 = this.k.e();
        if (f4 > 0.0f) {
            RenderUtil.c(poseStack, this.a + 120.0f - 20.0f - 6.0f, this.b + (this.f() - 10.0f) / 2.0f, 20.0f, 10.0f, 12, lf21.utils.render.a.a(-13768502, 0.36f * f2 * f4));
        }
        RenderUtil.b(poseStack, this.a + 120.0f - 20.0f - 6.0f, this.b + (this.f() - 10.0f) / 2.0f, 20.0f, 10.0f, 4.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(60, 60, 60), f2));
        if (f4 > 0.0f) {
            RenderUtil.b(poseStack, this.a + 120.0f - 20.0f - 6.0f, this.b + (this.f() - 10.0f) / 2.0f, 10.0f + 10.0f * f4, 10.0f, 4.0f, lf21.utils.render.a.a(lf21.gui.b.d.h, f2 * f4));
        }
        RenderUtil.b(poseStack, this.a + 120.0f - 20.0f - 6.0f + 10.0f * f4, this.b + (this.f() - 10.0f) / 2.0f, 10.0f, 10.0f, 4.8f, lf21.utils.render.a.a(-1, f2));
        poseStack.popPose();
        if (this.j) {
            this.f.a(lf21.gui.f.a(((BooleanSetting)this.g).getName()));
        }
    }

    @Override
    public float f() {
        return 18.0f;
    }

    @Override
    public boolean a(double d2, double d3, int n2) {
        if (!((BooleanSetting)this.g).getVisibility().displayable()) {
            return false;
        }
        if (this.j && lf21.utils.misc.c.a((float)d2, (float)d3, this.a, this.b, 120.0f, this.f())) {
            ((BooleanSetting)this.g).setValue((Boolean)((BooleanSetting)this.g).getValue() == false);
            return true;
        }
        return false;
    }

    @Generated
    public boolean k() {
        return this.i;
    }

    @Generated
    public void a(boolean bl) {
        this.i = bl;
    }

    @Generated
    public boolean l() {
        return this.j;
    }

    @Generated
    public void b(boolean bl) {
        this.j = bl;
    }

    @Generated
    public e m() {
        return this.k;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "R_e\u0003R_e";
        int n3 = "R_e\u0003R_e".length();
        int n4 = 3;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.gui.b.i.a(lf21.gui.b.i.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                m = stringArray;
                l = m[1];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x27);
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
                    case 0 -> 124;
                    case 1 -> 113;
                    case 2 -> 75;
                    case 3 -> 10;
                    case 4 -> 124;
                    case 5 -> 53;
                    default -> 39;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

