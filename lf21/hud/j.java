/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import lf21.LF21Client;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.hud.HudElement;
import lf21.hud.z;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.render.Interface;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.G;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.a;

public class j
extends HudElement {
    private boolean S;
    private final D O;
    private final u_0 M;
    private static final String I;
    private static j J;
    private final u_0 L;
    private static final String[] X;
    private final List<z> R;
    public final NumberSetting V;
    private final u_0 K;
    private final G Q;
    private final D N;
    public final MultiSelectSetting T;
    private final D P;
    public final BooleanSetting W;
    public final NumberSetting U;

    @Override
    public void a() {
    }

    private static void a(G g2, float f2, float f3, float f4, float f5, boolean bl) {
        g2.b(j.a(f2, f3, f4, bl), f5);
    }

    private List<Module> a(u_0 u_02) {
        boolean bl = !((List)this.T.getValue()).isEmpty();
        return LF21Client.getInstance().getModuleManager().getModules().stream().filter(module -> !(module instanceof j) && !(module instanceof Interface)).filter(module -> !module.getName().isEmpty()).filter(Module::isEnabled).filter(module -> !bl || this.T.isSelected(module.f().g)).sorted((module, module2) -> Float.compare(y_0.a(module2.c(), u_02), y_0.a(module.c(), u_02))).collect(Collectors.toList());
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String d(char[] cArray) {
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
                    case 0 -> 108;
                    case 1 -> 85;
                    case 2 -> 113;
                    case 3 -> 93;
                    case 4 -> 124;
                    case 5 -> 117;
                    default -> 81;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
        float f42;
        int n2;
        if (!LF21Client.getInstance().getModuleManager().getModule(Interface.class).isEnabled()) {
            return;
        }
        this.y();
        List<z> list = this.R.stream().filter(z2 -> z2.b.e() > 0.001f).toList();
        String string = "ModuleList";
        float f5 = y_0.a(string, this.K);
        float f6 = y_0.a("\ue8ef", this.M);
        float f7 = y_0.a(this.K);
        float f8 = y_0.a(this.M);
        float f9 = y_0.a(this.L);
        float f10 = (Boolean)this.W.getValue() != false ? 0.0f : Math.max(f7, f8) + 8.0f;
        float f11 = f9 + 5.0f;
        float f12 = (Boolean)this.W.getValue() != false ? 0.0f : f6 + 3.0f + f5 + 10.0f;
        float[] fArray = new float[list.size()];
        float[] fArray2 = new float[list.size()];
        for (n2 = 0; n2 < list.size(); ++n2) {
            fArray[n2] = y_0.a(list.get((int)n2).a.c(), this.L) + 10.0f;
            fArray2[n2] = f11 * Math.max(0.0f, Math.min(1.0f, list.get((int)n2).b.e()));
            f12 = Math.max(f12, fArray[n2]);
        }
        n2 = f2 > (float)mc.getWindow().getGuiScaledWidth() / 2.0f ? 1 : 0;
        float f13 = ((Number)this.U.getValue()).floatValue();
        float f14 = 0.0f;
        for (float f42 : fArray2) {
            f14 += f42;
        }
        float f15 = f10 + (list.isEmpty() ? 0.0f : f13 + f14 + f13 * (float)Math.max(0, list.size() - 1));
        this.N.a(a.a(0, 0, 0, 179));
        this.O.a(a.a(0, 0, 0, 179));
        this.P.a(a.a(255, 255, 255, 255));
        float f16 = f2;
        float f17 = ((Number)this.V.getValue()).floatValue();
        if (!((Boolean)this.W.getValue()).booleanValue()) {
            f42 = Math.min(f17, f10 * 0.5f);
            RenderUtil.a(glRenderEvent.b(), f16, f3, f12, f10, f42, 26.0f, 1.0f, 0);
            y_0.a(f16, f3, f12, f10, f42, this.N);
            float f18 = f3 + (f10 - f7) * 0.5f;
            float f19 = f3 + (f10 - f8) * 0.5f + 1.5f;
            if (n2 != 0) {
                var25_30 = f16 + f12 - 5.0f - f6;
                float f20 = var25_30 - 3.0f - f5;
                y_0.a(string, f20, f18, this.K, this.P);
                y_0.a("\ue8ef", var25_30, f19, this.M, this.P);
            } else {
                var25_30 = f16 + 5.0f;
                y_0.a("\ue8ef", var25_30, f19, this.M, this.P);
                y_0.a(string, var25_30 + f6 + 3.0f, f18, this.K, this.P);
            }
        }
        f42 = f3 + f10 + (f10 > 0.0f ? f13 : 0.0f);
        DrawContext drawContext = glRenderEvent.c();
        for (int i2 = 0; i2 < list.size(); ++i2) {
            z z3 = list.get(i2);
            String string2 = z3.a.c();
            float f21 = y_0.a(string2, this.L);
            float f22 = z3.c.e();
            float f23 = Math.max(0.0f, Math.min(1.0f, f22));
            float f24 = (1.0f - f22) * (n2 != 0 ? 18.0f : -18.0f);
            float f25 = fArray[i2];
            float f26 = fArray2[i2];
            float f27 = n2 != 0 ? f2 + f12 - f25 : f2;
            float f28 = f42;
            float f29 = Math.min(f17, f26 * 0.5f);
            RenderUtil.a(glRenderEvent.b(), f27, f28, f25, f26, f29, 26.0f, 1.0f, 0);
            this.O.a(a.a(0, 0, 0, Math.round(179.0f * f23)));
            y_0.a(f27, f28, f25, f26, f29, this.O);
            float f30 = (n2 != 0 ? f27 + f25 - 5.0f - f21 : f27 + 5.0f) + f24;
            float f31 = f28 + (f26 - f9) * 0.5f;
            this.P.a(a.a(255, 255, 255, Math.round(255.0f * f23)));
            y_0.a(string2, f30, f31, this.L, this.P);
            f42 += f26 + f13;
        }
        this.e(f12);
        this.f(f15);
    }

    public static void a(Module module, boolean bl) {
        boolean bl2;
        j j2 = J;
        if (j2 == null || module == null || module instanceof j || module instanceof Interface || module.getName().isEmpty()) {
            return;
        }
        z z3 = j2.R.stream().filter(z2 -> z2.a == module).findFirst().orElse(null);
        boolean bl3 = bl2 = ((List)j2.T.getValue()).isEmpty() || j2.T.isSelected(module.f().g);
        if (bl && bl2) {
            if (z3 == null) {
                j2.R.add(new z(module, true));
            } else {
                z3.a();
            }
        } else if (z3 != null) {
            z3.b();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                j.I = "\ue8ef";
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "?=\u001e*\u0012U\u0012\r!\u0014:\u0013\u00078\t&\n!:\u0015(\u0010\u0010\u001d\u0005&\u0005\n!:\u0015(\u0010\u0010\u001d\u0005&\u0005\r/:\u00033\u0019\u0007q>4\u00154\t\u0006";
                var4_3 = "?=\u001e*\u0012U\u0012\r!\u0014:\u0013\u00078\t&\n!:\u0015(\u0010\u0010\u001d\u0005&\u0005\n!:\u0015(\u0010\u0010\u001d\u0005&\u0005\r/:\u00033\u0019\u0007q>4\u00154\t\u0006".length();
                var1_4 = 16;
                var0_5 = -1;
lbl8:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl13:
                // 1 sources

                while (true) {
                    var5[var3_1++] = j.d(j.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "$<\u00158\\!8\u00189\u0014\u000e!:\u0015(\u0010\u0010q?%\u0010>\u0015\u001b6";
                    var4_3 = "$<\u00158\\!8\u00189\u0014\u000e!:\u0015(\u0010\u0010q?%\u0010>\u0015\u001b6".length();
                    var1_4 = 10;
                    var0_5 = -1;
lbl22:
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
lbl27:
                // 1 sources

                while (true) {
                    var5[var3_1++] = j.d(j.c(v1));
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
lbl38:
                // 1 sources

                ** continue;
            }
        }
        j.X = var5;
    }

    private static void a(G g2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean bl) {
        int n2 = 5;
        for (int i2 = 1; i2 <= n2; ++i2) {
            float f9 = (float)i2 / (float)n2;
            double d2 = Math.toRadians(f7 + (f8 - f7) * f9);
            float f10 = f4 + (float)Math.cos(d2) * f6;
            float f11 = f5 + (float)Math.sin(d2) * f6;
            j.a(g2, f2, f3, f10, f11, bl);
        }
    }

    private void y() {
        int n2;
        for (int i2 = this.R.size() - 1; i2 >= 0; --i2) {
            Iterator<Object> iterator = this.R.get(i2);
            boolean bl = false;
            for (n2 = 0; n2 < i2; ++n2) {
                if (this.R.get((int)n2).a != ((z)((Object)iterator)).a) continue;
                bl = true;
                break;
            }
            if (!bl) continue;
            this.R.remove(i2);
        }
        List<Module> list = this.a(this.L);
        if (!this.S) {
            for (Module module : list) {
                n2 = this.R.stream().anyMatch(z2 -> z2.a == module) ? 1 : 0;
                if (n2 != 0) continue;
                this.R.add(new z(module, false));
            }
            this.S = true;
        } else {
            for (z z4 : this.R) {
                n2 = list.contains(z4.a) ? 1 : 0;
                if (n2 == 0 && !z4.d) {
                    z4.b();
                    continue;
                }
                if (n2 == 0 || !z4.d) continue;
                z4.a();
            }
            for (Module module : list) {
                n2 = this.R.stream().anyMatch(z2 -> z2.a == module) ? 1 : 0;
                if (n2 != 0) continue;
                this.R.add(new z(module, true));
            }
        }
        this.R.sort((z2, z3) -> Float.compare(y_0.a(z3.a.c(), this.L), y_0.a(z2.a.c(), this.L)));
        this.R.forEach(z::c);
        this.R.removeIf(z::d);
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x51);
        }
        return cArray;
    }

    private void a(float f2, float f3, float f4, float[] fArray, float[] fArray2, float f5, boolean bl) {
        float f6;
        int n2;
        this.Q.c();
        if (fArray.length == 0) {
            return;
        }
        float f7 = 0.0f;
        float[] fArray3 = fArray2;
        int n3 = fArray3.length;
        for (n2 = 0; n2 < n3; ++n2) {
            f6 = fArray3[n2];
            f7 += f6;
        }
        float f8 = Math.min(4.5f, Math.min(f5 * 0.5f, f7 * 0.5f));
        this.Q.a(j.a(f2, f4, f8, bl), f3);
        j.a(this.Q, f2, f4, fArray[0] - f8, f3, bl);
        j.a(this.Q, f2, f4, fArray[0] - f8, f3 + f8, f8, -90.0f, 0.0f, bl);
        float f9 = f3;
        for (n2 = 0; n2 < fArray.length - 1; ++n2) {
            f6 = fArray[n2];
            float f10 = fArray[n2 + 1];
            f9 += fArray2[n2];
            float f11 = f6 - f10;
            if (f11 <= 0.5f) {
                j.a(this.Q, f2, f4, f6, f9, bl);
                continue;
            }
            float f12 = Math.min(fArray2[n2], fArray2[n2 + 1]);
            float f13 = Math.min(f8, Math.min(f11 * 0.5f, f12 * 0.35f));
            if (f13 <= 0.05f) {
                j.a(this.Q, f2, f4, f6, f9, bl);
                j.a(this.Q, f2, f4, f10, f9, bl);
                continue;
            }
            j.a(this.Q, f2, f4, f6, f9 - f13, bl);
            j.a(this.Q, f2, f4, f6 - f13, f9 - f13, f13, 0.0f, 90.0f, bl);
            j.a(this.Q, f2, f4, f10 + f13, f9, bl);
            j.a(this.Q, f2, f4, f10 + f13, f9 + f13, f13, -90.0f, -180.0f, bl);
        }
        float f14 = f3 + f7;
        f6 = fArray[fArray.length - 1];
        j.a(this.Q, f2, f4, f6, f14 - f8, bl);
        j.a(this.Q, f2, f4, f6 - f8, f14 - f8, f8, 0.0f, 90.0f, bl);
        j.a(this.Q, f2, f4, f8, f14, bl);
        j.a(this.Q, f2, f4, f8, f14 - f8, f8, 90.0f, 180.0f, bl);
        j.a(this.Q, f2, f4, 0.0f, f3 + f8, bl);
        j.a(this.Q, f2, f4, f8, f3 + f8, f8, 180.0f, 270.0f, bl);
        this.Q.a();
    }

    private static float a(float f2, float f3, float f4, boolean bl) {
        return bl ? f2 + f3 - f4 : f2 + f4;
    }

    public j() {
        String[] stringArray = X;
        super("ModuleList");
        this.K = t_0.f(15.0f);
        this.L = t_0.e(15.0f);
        this.M = t_0.j(18.0f);
        this.N = new D();
        this.O = new D();
        this.P = new D();
        this.Q = new G();
        this.R = new ArrayList<z>();
        this.T = new MultiSelectSetting("Shown Categories", (String[])Arrays.stream(Category.a()).map(category -> category.g).toArray(String[]::new));
        this.U = new NumberSetting("Module Spacing", 3.0, 0.0, 12.0, 1.0);
        this.V = new NumberSetting("Corner Radius", 9.0, 0.0, 14.0, 0.5);
        this.W = new BooleanSetting("Hide Title", false);
        J = this;
        this.e(120.0f);
        this.f(20.0f);
    }
}

