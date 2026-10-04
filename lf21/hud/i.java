/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.TickEvent;
import lf21.hud.HudElement;
import lf21.hud.w;
import lf21.hud.x;
import lf21.modules.Module;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.G;
import lf21.render.Q;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.e;
import lf21.utils.b.f;
import lf21.utils.misc.k;
import lf21.utils.misc.m;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.a;

public class i
extends HudElement {
    private float S;
    private final e N;
    final u_0 K;
    private static final String[] db;
    private float R;
    public final NumberSetting cornerRadius;
    private final Map<Module, x> aa;
    private final G[] X;
    private float U;
    private float W;
    private final u_0 J;
    public final BooleanSetting hideIndicators;
    private float V;
    public final NumberSetting moduleSpacing;
    private final D O;
    private final boolean[] Y;
    private final u_0 L;
    public final BooleanSetting hideTitle;
    private final D Q;
    private final Map<Module, w> Z;
    boolean af;
    private final D P;
    private final e M;
    private float T;
    private final List<x> I;

    private void a(DrawContext drawContext, float f2, float f3, float f4, boolean bl, float f5, float f6, float f7, float f8) {
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        if (f5 <= 0.01f) {
            return;
        }
        this.P.a(a.a(0, 0, 0, (int)(158.0f * f5)));
        float f17 = f2;
        float f18 = ((Number)this.cornerRadius.getValue()).floatValue();
        int n2 = a.a(255, 255, 255, (int)(255.0f * f5));
        this.Q.a(n2);
        if (!((Boolean)this.hideTitle.getValue()).booleanValue()) {
            float f19 = Math.min(f18, f6 * 0.5f);
            RenderUtil.a(drawContext.b(), f17, f3, f4, f6, f19, 26.0f, f5, 0);
            y_0.a(f17, f3, f4, f6, f19, this.P);
            float f20 = f3 + (f6 - this.U) * 0.5f;
            f16 = f3 + (f6 - this.W) * 0.5f + 1.5f;
            if (bl) {
                f15 = f17 + f4 - 5.0f - this.R;
                f14 = f15 - 3.0f - this.S;
                String[] stringArray = db;
                y_0.a("Hotkeys", f14, f20, this.J, this.Q);
                y_0.a("\ue1c6", f15, f16, this.L, this.Q);
            } else {
                f15 = f17 + 5.0f;
                y_0.a("\ue1c6", f15, f16, this.L, this.Q);
                y_0.a("Hotkeys", f15 + this.R + 3.0f, f20, this.J, this.Q);
            }
        }
        if (this.I.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.X.length; ++i2) {
            if (!this.Y[i2]) continue;
            this.X[i2].c();
            this.Y[i2] = false;
        }
        for (x x2 : this.I) {
            f13 = x2.w.e();
            if (f13 <= 0.0f) {
                x2.k = 0.0f;
                continue;
            }
            f12 = f5 * f13;
            if (f12 <= 0.0f) {
                x2.k = 0.0f;
                continue;
            }
            x2.k = f12;
            f16 = f2 + x2.u.e();
            f15 = x2.v.e();
            f11 = x2.d() + 10.0f;
            x2.l = f15;
            x2.m = f11;
            int n3 = Math.max(0, Math.min(90, Math.round(90.0f * f12)));
            G g2 = this.X[n3];
            if (g2 == null) {
                this.X[n3] = g2 = new G();
            }
            this.Y[n3] = true;
            if (x2.e && !((Boolean)this.hideIndicators.getValue()).booleanValue()) {
                float f21;
                if (bl) {
                    f10 = f16 + f4 - f7;
                    f21 = f10 - 3.0f - f11;
                    x2.n = f10;
                    x2.o = f21;
                    f9 = Math.min(f18, f7 * 0.5f);
                    g2.a(lf21.render.Q.a(f21, f15, f11, f7, f9));
                    g2.a(lf21.render.Q.a(f10, f15, f7, f7, f9));
                    continue;
                }
                x2.n = f16;
                x2.o = f16 + f7 + 3.0f;
                f21 = Math.min(f18, f7 * 0.5f);
                g2.a(lf21.render.Q.a(f16, f15, f7, f7, f21));
                g2.a(lf21.render.Q.a(x2.o, f15, f11, f7, f21));
                continue;
            }
            if (x2.e) {
                f10 = bl ? f16 + f4 - f11 : f16;
                x2.o = f10;
                x2.n = f10;
                g2.a(lf21.render.Q.a(f10, f15, f11, f7, Math.min(f18, f7 * 0.5f)));
                continue;
            }
            f10 = bl ? f16 + f4 - f11 : f16;
            x2.o = f10;
            x2.n = f10;
            g2.a(lf21.render.Q.a(f10, f15, f11, f7, Math.min(f18, f7 * 0.5f)));
        }
        for (int i3 = 0; i3 < this.X.length; ++i3) {
            if (!this.Y[i3]) continue;
            RenderUtil.a(drawContext.b(), drawContext, this.X[i3], 26.0f, f5 * (float)i3 / 90.0f);
            this.O.a(a.a(0, 0, 0, Math.round(179.0f * f5 * (float)i3 / 90.0f)));
            drawContext.a(this.X[i3], this.O);
        }
        for (x x3 : this.I) {
            if (x3.k <= 0.0f) continue;
            f13 = x3.l;
            f12 = x3.m;
            int n4 = (int)(255.0f * x3.k);
            int n5 = a.a(255, 255, 255, n4);
            f11 = f13 + 5.0f + (f7 - 10.0f - this.V) / 2.0f;
            f14 = f13 + 5.0f + (f7 - 10.0f - this.W) / 2.0f + 2.5f;
            this.Q.a(n5);
            if (x3.e && !((Boolean)this.hideIndicators.getValue()).booleanValue()) {
                float f22 = x3.n;
                f10 = x3.o;
                if (bl) {
                    float f23 = f10 + f12 - 5.0f - x3.d();
                    y_0.a(x3.c, f23, f11, this.K, this.Q);
                } else {
                    y_0.a(x3.c, f10 + 5.0f, f11, this.K, this.Q);
                }
                if (x3.d == null) {
                    float f24 = f22 + (f7 - this.T) / 2.0f - 1.0f;
                    y_0.a("\ue9f6", f24, f14, this.L, this.Q);
                    continue;
                }
                u_0 u_02 = x3.a(this.K, f7 - 5.0f);
                f9 = x3.f();
                float f25 = f22 + (f7 - f9) / 2.0f;
                float f26 = f13 + 5.0f + (f7 - 10.0f - (float)y_0.a(u_02)) / 2.0f;
                y_0.a(x3.d, f25, f26, u_02, this.Q);
                continue;
            }
            if (x3.e) {
                y_0.a(x3.c, bl ? x3.o + f12 - 5.0f - x3.d() : x3.o + 5.0f, f11, this.K, this.Q);
                continue;
            }
            y_0.a(x3.c, x3.o + 5.0f, f11, this.K, this.Q);
        }
    }

    public i() {
        String[] stringArray = db;
        super("KeyBinds");
        this.I = new ArrayList<x>();
        this.J = t_0.f(15.0f);
        this.K = t_0.e(15.0f);
        this.L = t_0.j(18.0f);
        this.M = new e();
        this.N = new e();
        this.O = new D();
        this.P = new D();
        this.Q = new D();
        this.R = -1.0f;
        this.X = new G[91];
        this.Y = new boolean[91];
        this.Z = new IdentityHashMap<Module, w>();
        this.aa = new IdentityHashMap<Module, x>();
        this.moduleSpacing = new NumberSetting("Module Spacing", 3.0, 0.0, 12.0, 1.0);
        this.cornerRadius = new NumberSetting("Corner Radius", 9.0, 0.0, 14.0, 0.5);
        this.hideTitle = new BooleanSetting("Hide Title", false);
        this.hideIndicators = new BooleanSetting("Hide Indicators", false);
        this.af = false;
        this.e(150.0f);
        this.f(100.0f);
        this.setEnabled(true);
        this.N.a(1.0);
    }

    @Override
    public void a() {
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
                    case 0 -> 122;
                    case 1 -> 106;
                    case 2 -> 47;
                    case 3 -> 42;
                    case 4 -> 18;
                    case 5 -> 62;
                    default -> 45;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[9];
                var3_1 = 0;
                var2_2 = "9\u0006FIyyX\u0013\r9\u0005]DwL\r(\u000bKCgM\u000f2\u0003KO2wC\u001e\u0003LKfQ_\t\b1\u000fVh{PI\t\u000e7\u0005K_~[\r)\u001aNI{PJ\u00072\u0005[AwG^\u00072\u0005[AwG^";
                var4_3 = "9\u0006FIyyX\u0013\r9\u0005]DwL\r(\u000bKCgM\u000f2\u0003KO2wC\u001e\u0003LKfQ_\t\b1\u000fVh{PI\t\u000e7\u0005K_~[\r)\u001aNI{PJ\u00072\u0005[AwG^\u00072\u0005[AwG^".length();
                var1_4 = 8;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = i.d(i.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "3\u0004[O`XL\u0019\u000f\n2\u0003KO2jD\u000e\u0006J";
                    var4_3 = "3\u0004[O`XL\u0019\u000f\n2\u0003KO2jD\u000e\u0006J".length();
                    var1_4 = 9;
                    var0_5 = -1;
lbl21:
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
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = i.d(i.c(v1));
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
lbl37:
                // 1 sources

                ** continue;
            }
        }
        i.db = var5;
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
        float f4;
        float f5;
        this.y();
        this.I.removeIf(x::e);
        this.I.forEach(x::c);
        this.M.a();
        this.N.a();
        if (this.I.isEmpty() && this.M.c() && this.N.c()) {
            return;
        }
        boolean bl = f2 > (float)mc.getWindow().getGuiScaledWidth() / 2.0f;
        boolean bl2 = bl;
        if (this.af != bl) {
            this.af = bl;
        }
        float f6 = (Boolean)this.hideTitle.getValue() != false ? 0.0f : Math.max(this.U, this.W) + 8.0f;
        float f7 = (Boolean)this.hideTitle.getValue() != false ? 0.0f : this.R + 3.0f + this.S + 10.0f;
        float f8 = this.V + 10.0f;
        float f9 = f7;
        if (!this.I.isEmpty()) {
            f5 = 0.0f;
            Iterator<x> iterator = this.I.iterator();
            while (iterator.hasNext()) {
                x x2;
                f4 = x2.e && (Boolean)this.hideIndicators.getValue() == false ? f8 + 3.0f : 0.0f;
                float f10 = f4 + ((x2 = iterator.next()).d() + 10.0f);
                if (!(f10 > f5)) continue;
                f5 = f10;
            }
            f9 = Math.max(f7, f5);
        }
        if (this.M.c() && this.I.isEmpty()) {
            this.M.a(f9);
        }
        this.M.a((double)f9, 0.2, f.h);
        if (this.I.isEmpty()) {
            this.N.a(0.0, 0.2, f.d);
        } else {
            this.N.a(1.0, 0.2, f.e);
        }
        f5 = this.M.e();
        float f11 = this.N.e();
        float f12 = ((Number)this.moduleSpacing.getValue()).floatValue();
        f4 = f3 + f6 + (f6 > 0.0f ? f12 : 0.0f);
        for (x x3 : this.I) {
            if (x3.f) {
                x3.f = false;
                x3.v.a(f4);
                x3.u.a(x3.g ? 20.0 : -20.0);
                x3.w.a(0.0);
                x3.x.a(0.0);
                x3.u.a(0.0, 0.2, f.e);
                x3.w.a(1.0, 0.2, f.e);
                x3.x.a((double)(f8 + 3.0f), 0.2, f.e);
            } else if (x3.i) {
                x3.i = false;
                x3.u.a(0.0, 0.2, f.e);
                x3.w.a(1.0, 0.2, f.e);
                x3.x.a((double)(f8 + 3.0f), 0.2, f.e);
            }
            x3.v.a((double)f4, 0.15, f.h);
            f4 += x3.x.e();
            if (!(x3.w.e() > 0.001f)) continue;
            f4 += f12;
        }
        DrawContext drawContext = glRenderEvent.c();
        this.a(drawContext, f2, f3, f5, this.af, f11, f6, f8, f4 - f3);
        this.e(f5);
        this.f(f4 - f3);
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
    }

    private void y() {
        if (this.R >= 0.0f) {
            return;
        }
        this.R = y_0.a("\ue1c6", this.L);
        this.S = y_0.a("Hotkeys", this.J);
        this.T = y_0.a(this.L);
        this.U = y_0.a(this.J);
        this.V = y_0.a(this.K);
        this.W = y_0.a(this.L);
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x2D);
        }
        return cArray;
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        Object object;
        if (i.mc.player == null) {
            this.I.clear();
            this.Z.clear();
            this.aa.clear();
            return;
        }
        this.Z.clear();
        this.aa.clear();
        List<Module> list = LF21Client.getInstance().getModuleManager().getModules();
        for (Module iterator : list) {
            Map.Entry<Module, w> entry = null;
            if (iterator instanceof k) {
                object = (k)((Object)iterator);
                m m2 = object.a();
                if (m2 != null) {
                    entry = new w((String)m2.b(), (String)m2.c(), m2.a(), iterator);
                }
            } else if (iterator.isEnabled() && iterator.getKey() > 0) {
                String[] stringArray = db;
                if (!iterator.getName().equals("Interface") && !iterator.getName().equals("ClickGui")) {
                    entry = new w(iterator.c(), null, true, iterator);
                }
            }
            if (entry == null) continue;
            this.Z.put(iterator, (w)((Object)entry));
        }
        for (x x4 : this.I) {
            this.aa.put(x4.b, x4);
        }
        for (x x5 : this.I) {
            if (this.Z.containsKey(x5.b)) continue;
            x5.a();
        }
        boolean bl = false;
        for (Map.Entry<Module, w> entry : this.Z.entrySet()) {
            object = this.aa.get(entry.getKey());
            if (object != null) {
                if (((x)object).j) {
                    ((x)object).b();
                }
                ((x)object).a((w)entry.getValue());
                continue;
            }
            this.I.add(new x(this, (Module)entry.getKey(), (w)entry.getValue()));
            bl = true;
        }
        if (bl) {
            this.I.sort((x2, x3) -> Double.compare(x3.d(), x2.d()));
        }
    }
}

