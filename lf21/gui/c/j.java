/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import lf21.render.T;
import lf21.render.ab;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.b.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class j
extends ClientBase {
    private boolean a = false;
    private boolean b = false;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private float g = 0.0f;
    private float h = 0.0f;
    private boolean i = false;
    private final Map<String, Boolean> j = new HashMap<String, Boolean>();
    private final Map<String, Float> k = new HashMap<String, Float>();
    private final Map<String, Map<String, Float>> l = new HashMap<String, Map<String, Float>>();
    private static final String[] m;
    private String n;
    private static final String[] o;
    private String p;
    private static final Color q;
    private final Consumer<Float> r;
    private static final String[] s;

    public j(Consumer<Float> consumer) {
        String[] stringArray = s;
        this.n = stringArray[16];
        this.p = stringArray[6];
        this.r = consumer;
        this.j.put(stringArray[5], false);
        this.j.put(stringArray[15], false);
        this.k.put(stringArray[9], Float.valueOf(0.0f));
        this.k.put(stringArray[13], Float.valueOf(0.0f));
        this.l.put(stringArray[9], new HashMap());
        this.l.put(stringArray[13], new HashMap());
    }

    public void a(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3) {
        this.b(n2, n3, f2);
        this.f();
        this.h();
        this.g();
        if (this.g > 0.01f) {
            this.b(f2);
            this.b(guiGraphics, n2, n3, f2, f3);
        }
    }

    private void b(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3) {
        int n4 = (int)(220.0f * f2);
        int n5 = this.a(f2);
        int n6 = mc.getWindow().getGuiScaledWidth();
        int n7 = mc.getWindow().getGuiScaledHeight();
        int n8 = (n6 - n4) / 2 + this.e;
        int n9 = (n7 - (int)(200.0f * f2)) / 2 + this.f;
        float f4 = this.g * f3;
        int n10 = (int)(255.0f * f4);
        T.a(guiGraphics.pose(), (float)n8, (float)n9, n4, (float)n5, 12.0f * f2, f4);
        Renderer.a(drawContext -> this.a((DrawContext)drawContext, guiGraphics, n8, n9, n2, n3, n5, n10, f2, n4));
    }

    private void a(DrawContext drawContext, GuiGraphics guiGraphics, int n2, int n3, int n4, int n5, int n6, int n7, float f2, int n8) {
        int n9 = n7 << 24 | 0xFFFFFF;
        u_0 u_02 = t_0.j(18.0f * f2);
        y_0.a("\ue8b8", (float)n2 + 15.0f * f2, (float)n3 + 16.0f * f2, u_02, n9);
        u_0 u_03 = t_0.i(22.0f * f2);
        String[] stringArray = s;
        String string = stringArray[1];
        float f3 = y_0.a(string, u_03);
        y_0.a(string, (float)n2 + ((float)n8 - f3) / 2.0f, (float)n3 + 35.0f * f2, u_03, n9);
        this.a(n2, n3, u_02, n7, f2, n8);
        u_0 u_04 = t_0.l(13.0f * f2);
        u_0 u_05 = t_0.l(13.0f * f2);
        int n10 = n7 << 24 | 0xAAAAAA;
        int n11 = n7 << 24 | 0xFFFFFF;
        int n12 = (int)(18.0f * f2);
        int n13 = (int)((float)n3 + 65.0f * f2);
        int n14 = (int)((float)(n2 + n8) - 15.0f * f2);
        y_0.a(stringArray[17], (float)n2 + 15.0f * f2, (float)n13, u_04, n10);
        String string2 = this.a();
        float f4 = y_0.a(string2, u_05);
        y_0.a(string2, (float)n14 - f4, (float)n13, u_05, n11);
        y_0.a(stringArray[19], (float)n2 + 15.0f * f2, (float)(n13 += n12), u_04, n10);
        String string3 = this.b();
        float f5 = y_0.a(string3, u_05);
        y_0.a(string3, (float)n14 - f5, (float)n13, u_05, n11);
        y_0.a(stringArray[8], (float)n2 + 15.0f * f2, (float)(n13 += n12), u_04, n10);
        String string4 = stringArray[21];
        float f6 = y_0.a(string4, u_05);
        y_0.a(string4, (float)n14 - f6, (float)n13, u_05, n11);
        n13 += n12;
        n13 = (int)((float)n13 + 8.0f * f2);
        n13 += this.a(drawContext, guiGraphics, stringArray[4], this.n, m, stringArray[9], n2, n13, n4, n5, this.g, f2, n8);
        n13 = (int)((float)n13 + 8.0f * f2);
        this.a(drawContext, guiGraphics, stringArray[22], this.p, o, stringArray[13], n2, n13, n4, n5, this.g, f2, n8);
        u_0 u_06 = t_0.l(12.0f * f2);
        String string5 = stringArray[14];
        float f7 = y_0.a(string5, u_06);
        y_0.a(string5, (float)n2 + ((float)n8 - f7) / 2.0f, (float)(n3 + n6) - 15.0f * f2, u_06, n10);
    }

    private void a(int n2, int n3, u_0 u_02, int n4, float f2, int n5) {
        float f3 = (float)(n2 + n5) - 25.0f * f2;
        float f4 = (float)n3 + 16.0f * f2;
        Color color = new Color(255, 255, 255);
        Color color2 = new Color(255, 255, 255);
        int n6 = (int)((float)color.getRed() + (float)(color2.getRed() - color.getRed()) * this.h);
        int n7 = (int)((float)color.getGreen() + (float)(color2.getGreen() - color.getGreen()) * this.h);
        int n8 = (int)((float)color.getBlue() + (float)(color2.getBlue() - color.getBlue()) * this.h);
        int n9 = n4 << 24 | n6 << 16 | n7 << 8 | n8;
        int n10 = (int)(180.0f * this.h * this.g);
        int n11 = new Color(n6, n7, n8, n10).getRGB();
        T.a("\ue5cd", f3, f4, u_02, n9, n11, 10.0f * f2);
    }

    private int a(DrawContext drawContext, GuiGraphics guiGraphics, String string, String string2, String[] stringArray, String string3, int n2, int n3, int n4, int n5, float f2, float f3, int n6) {
        u_0 u_02 = t_0.l(13.0f * f3);
        u_0 u_03 = t_0.l(13.0f * f3);
        int n7 = this.a(new Color(0xAAAAAA).getRGB(), f2);
        int n8 = this.a(new Color(0xFFFFFF).getRGB(), f2);
        int n9 = (int)(90.0f * f3);
        int n10 = (int)((float)(n2 + n6 - n9) - 15.0f * f3);
        int n11 = (int)(20.0f * f3);
        int n12 = (int)(18.0f * f3);
        float f4 = (float)n3 + ((float)n11 - u_02.d().f()) / 2.0f;
        y_0.a(string, (float)n2 + 15.0f * f3, f4, u_02, n7);
        float f5 = this.k.getOrDefault(string3, Float.valueOf(0.0f)).floatValue();
        String[] stringArray2 = this.a(stringArray, string2);
        int n13 = (int)((float)(stringArray2.length * n12) * f5);
        RenderUtil.b(guiGraphics.pose(), (float)n10, (float)n3, (float)n9, (float)(n11 + n13), 4.0f * f3, this.a(q.getRGB(), f2));
        float f6 = (float)n10 + 8.0f * f3;
        float f7 = (float)n3 + ((float)n11 - u_03.d().f()) / 2.0f;
        y_0.a(string2, f6, f7, u_03, n8);
        u_0 u_04 = t_0.j(18.0f * f3);
        String string4 = "\ue313";
        float f8 = (float)(n10 + n9) - 18.0f * f3;
        float f9 = (float)n3 + ((float)n11 - u_04.d().f()) / 2.0f;
        y_0.a(string4, f8, f9, u_04, n8);
        if (f5 > 0.01f) {
            drawContext.d();
            drawContext.a(ab.a(n10, n3 + n11, n9, n13));
            Map<String, Float> map = this.l.get(string3);
            int n14 = n3 + n11;
            for (String string5 : stringArray2) {
                boolean bl = this.a(n4, n5, n10, n14, n9, n12);
                this.a(map, string5, bl);
                float f10 = map.getOrDefault(string5, Float.valueOf(0.0f)).floatValue();
                float f11 = (float)n10 + 8.0f * f3;
                float f12 = (float)n14 + ((float)n12 - u_03.d().f()) / 2.0f;
                int n15 = this.a(n8, f5);
                float f13 = f10 * f5;
                if (f13 > 0.01f) {
                    int n16 = new Color(1.0f, 1.0f, 1.0f, f13).getRGB();
                    T.a(string5, f11, f12, u_03, n15, n16, 8.0f * f3);
                } else {
                    y_0.a(string5, f11, f12, u_03, n15);
                }
                n14 += n12;
            }
            drawContext.e();
        }
        return n11 + n13;
    }

    private String a() {
        String string;
        block5: {
            block4: {
                if (LF21Client.username == null || LF21Client.username.isEmpty()) break block4;
                string = LF21Client.username;
                break block5;
            }
            string = s[3];
        }
        return string;
    }

    private String b() {
        return s[7];
    }

    public boolean a(int n2, int n3, float f2) {
        boolean bl;
        block16: {
            int n4 = (int)(220.0f * f2);
            int n5 = this.a(f2);
            int n6 = mc.getWindow().getGuiScaledWidth();
            int n7 = (n6 - n4) / 2 + this.e;
            int n8 = mc.getWindow().getGuiScaledHeight();
            int n9 = (n8 - (int)(200.0f * f2)) / 2 + this.f;
            if (this.b(n2, n3, n7, n9, f2, n4)) {
                this.e();
                return true;
            }
            if (this.b) {
                return true;
            }
            if (this.a(n2, n3, n7, n9, f2, n4)) {
                this.a(n2, n3);
                return true;
            }
            int n10 = (int)(90.0f * f2);
            int n11 = (int)((float)(n7 + n4 - n10) - 15.0f * f2);
            int n12 = (int)((float)n9 + 127.0f * f2);
            String[] stringArray = s;
            boolean bl2 = this.a(n2, n3, n11, n12, n10, m, this.n, stringArray[9], string -> {
                this.n = string;
            }, f2);
            float f3 = (float)this.a(m, this.n).length * (18.0f * f2) * this.k.getOrDefault(stringArray[9], Float.valueOf(0.0f)).floatValue();
            int n13 = (int)((float)n12 + 20.0f * f2 + f3 + 8.0f * f2);
            boolean bl3 = this.a(n2, n3, n11, n13, n10, o, this.p, stringArray[13], string -> {
                this.p = string;
                try {
                    float f2 = Float.parseFloat(string.replace("%", "")) / 100.0f;
                    this.r.accept(Float.valueOf(f2));
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }, f2);
            bl = this.a(n2, n3, n7, n9, n4, n5);
            if (!bl2 && !bl3) break block16;
            return true;
        }
        if (bl) {
            this.j.put(s[9], false);
            this.j.put(s[13], false);
            return true;
        }
        return false;
    }

    private boolean a(int n2, int n3, int n4, int n5, int n6, String[] stringArray, String string3, String string4, Consumer<String> consumer, float f2) {
        int n7;
        int n8;
        boolean bl;
        block10: {
            boolean bl2;
            String string5;
            Map<String, Boolean> map;
            block12: {
                block11: {
                    bl = this.j.getOrDefault(string4, false);
                    n8 = (int)(18.0f * f2);
                    n7 = (int)(20.0f * f2);
                    if (!this.a(n2, n3, n4, n5, n6, n7)) break block10;
                    map = this.j;
                    string5 = string4;
                    if (bl) break block11;
                    bl2 = true;
                    break block12;
                }
                bl2 = false;
            }
            map.put(string5, bl2);
            this.j.keySet().stream().filter(string2 -> {
                boolean bl = !string2.equals(string4);
                return bl;
            }).forEach(string -> this.j.put((String)string, false));
            return true;
        }
        if (bl) {
            String[] stringArray2 = this.a(stringArray, string3);
            int n9 = 0;
            while (true) {
                block14: {
                    block13: {
                        if (n9 >= stringArray2.length) break;
                        if (this.a(n2, n3, n4, n5 + n7 + n9 * n8, n6, n8)) break block13;
                        break block14;
                    }
                    consumer.accept(stringArray2[n9]);
                    this.j.put(string4, false);
                    return true;
                }
                ++n9;
            }
        }
        return false;
    }

    private boolean a(int n2, int n3, int n4, int n5, float f2, int n6) {
        boolean bl;
        block17: {
            block16: {
                boolean bl2;
                block15: {
                    block14: {
                        float f3 = (float)(n4 + n6) - 25.0f * f2;
                        if (!((float)n2 >= f3 - 10.0f * f2) || !((float)n2 <= f3 + 15.0f * f2)) break block14;
                        bl2 = true;
                        break block15;
                    }
                    bl2 = false;
                }
                boolean bl3 = bl2;
                if (n2 < n4 || n2 > n4 + n6) break block16;
                if (n3 < n5) break block16;
                if (!((float)n3 <= (float)n5 + 30.0f * f2)) break block16;
                if (bl3) break block16;
                bl = true;
                break block17;
            }
            bl = false;
        }
        return bl;
    }

    private boolean b(int n2, int n3, int n4, int n5, float f2, int n6) {
        boolean bl;
        block9: {
            block8: {
                float f3 = (float)(n4 + n6) - 25.0f * f2;
                float f4 = (float)n5 + 16.0f * f2;
                if (!((float)n2 >= f3 - 10.0f * f2) || !((float)n2 <= f3 + 15.0f * f2)) break block8;
                if (!((float)n3 >= f4 - 10.0f * f2)) break block8;
                if (!((float)n3 <= f4 + 10.0f * f2)) break block8;
                bl = true;
                break block9;
            }
            bl = false;
        }
        return bl;
    }

    private void a(int n2, int n3) {
        this.b = true;
        this.c = n2;
        this.d = n3;
    }

    public void b(int n2, int n3) {
        if (this.b) {
            this.e += n2 - this.c;
            this.f += n3 - this.d;
            this.c = n2;
            this.d = n3;
        }
    }

    public void c() {
        this.b = false;
    }

    public boolean d() {
        return this.a;
    }

    public void e() {
        j j2 = this;
        boolean bl = !this.a;
        j2.a = bl;
    }

    private void f() {
        block6: {
            block5: {
                if (!this.a) break block5;
                this.g = lf21.utils.b.g.a(this.g, 1.0f, 0.1f);
                break block6;
            }
            this.g = lf21.utils.b.g.a(this.g, 0.0f, 0.1f);
            if (this.g < 0.01f) {
                this.j.put(s[9], false);
                this.j.put(s[13], false);
            }
        }
    }

    private void g() {
        for (String string : this.j.keySet()) {
            boolean bl = this.j.getOrDefault(string, false);
            float f2 = this.k.getOrDefault(string, Float.valueOf(0.0f)).floatValue();
            float f3 = bl ? 1.0f : 0.0f;
            float f4 = f3;
            float f5 = Math.abs(f2 - f4) > 0.01f ? lf21.utils.b.g.b(f2, f4, 0.22f) : f4;
            f2 = f5;
            this.k.put(string, Float.valueOf(f2));
        }
    }

    private void b(int n2, int n3, float f2) {
        if (this.a) {
            int n4 = (int)(220.0f * f2);
            int n5 = mc.getWindow().getGuiScaledWidth();
            int n6 = mc.getWindow().getGuiScaledHeight();
            int n7 = (n5 - n4) / 2 + this.e;
            int n8 = (n6 - (int)(200.0f * f2)) / 2 + this.f;
            this.i = this.b(n2, n3, n7, n8, f2, n4);
        } else {
            this.i = false;
        }
    }

    private void h() {
        j j2 = this;
        float f2 = this.i ? lf21.utils.b.g.a(this.h, 1.0f, 0.16f) : lf21.utils.b.g.a(this.h, 0.0f, 0.16f);
        j2.h = f2;
    }

    private void a(Map<String, Float> map, String string, boolean bl) {
        float f2 = map.getOrDefault(string, Float.valueOf(0.0f)).floatValue();
        float f3 = bl ? 1.0f : 0.0f;
        float f4 = f3;
        float f5 = Math.abs(f2 - f4) > 0.01f ? lf21.utils.b.g.b(f2, f4, 0.28f) : f4;
        f2 = f5;
        map.put(string, Float.valueOf(f2));
    }

    private String[] a(String[] stringArray, String string) {
        return (String[])Stream.of((Object[])stringArray).filter(object -> {
            boolean bl = !Objects.equals(object, string);
            return bl;
        }).toArray(String[]::new);
    }

    private boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
        boolean bl;
        block9: {
            block8: {
                if (n2 < n4 || n2 > n4 + n6) break block8;
                if (n3 < n5) break block8;
                if (n3 > n5 + n7) break block8;
                bl = true;
                break block9;
            }
            bl = false;
        }
        return bl;
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
    }

    private int a(float f2) {
        float f3 = 200.0f * f2;
        float f4 = 18.0f * f2;
        String[] stringArray = s;
        float f5 = (float)this.a(m, this.n).length * f4 * this.k.getOrDefault(stringArray[9], Float.valueOf(0.0f)).floatValue();
        float f6 = (float)this.a(o, this.p).length * f4 * this.k.getOrDefault(stringArray[13], Float.valueOf(0.0f)).floatValue();
        return (int)(f3 + f5 + f6);
    }

    private void b(float f2) {
        int n2 = mc.getWindow().getGuiScaledWidth();
        int n3 = mc.getWindow().getGuiScaledHeight();
        int n4 = this.a(f2);
        int n5 = (int)(220.0f * f2);
        int n6 = (n2 - n5) / 2;
        int n7 = -(n2 - n5) / 2;
        int n8 = (n3 - n4) / 2;
        int n9 = -(n3 - (int)(200.0f * f2)) / 2;
        this.e = Math.max(n7, Math.min(this.e, n6));
        this.f = Math.max(n9, Math.min(this.f, n8));
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[23];
                var3_1 = 0;
                var2_2 = "wERlQ\u0016q\u0004XK\t3\u0004\u0005\u001f\u000e'\u0007aCPl[\u0012z\bxLUeA\u0004sQ\bXLUeA\u0004sQ\u0004\u0005\u001d\u000b'\u0004a^^p\ba]_c@\u0000p\u000e\bXLUeA\u0004sQ\u0003\u0003\u0018\u001e\u0004\u0005\u001d\u000b'\u0004\u0005\u0018\u000b'\u0005GNZnQ\u0014\u0003XUiZ\ncZ\r\u0092\"\u0006U&\u0000\u0000\t2\u0006P\u0005GNZnQ\u0007qC\\n]\u0016|\ta^^pZ\u0004yQ\u0017\u0003\u0001\u001d\u001e\u0007v_ZlW\r.\u0007qC\\n]\u0016|";
                var4_3 = "wERlQ\u0016q\u0004XK\t3\u0004\u0005\u001f\u000e'\u0007aCPl[\u0012z\bxLUeA\u0004sQ\bXLUeA\u0004sQ\u0004\u0005\u001d\u000b'\u0004a^^p\ba]_c@\u0000p\u000e\bXLUeA\u0004sQ\u0003\u0003\u0018\u001e\u0004\u0005\u001d\u000b'\u0004\u0005\u0018\u000b'\u0005GNZnQ\u0014\u0003XUiZ\ncZ\r\u0092\"\u0006U&\u0000\u0000\t2\u0006P\u0005GNZnQ\u0007qC\\n]\u0016|\ta^^pZ\u0004yQ\u0017\u0003\u0001\u001d\u001e\u0007v_ZlW\r.\u0007qC\\n]\u0016|".length();
                var1_4 = 7;
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
                    var5[var3_1++] = lf21.gui.c.j.b(lf21.gui.c.j.d(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "uX\\\"\u0000E&\u0004\u001f\u000e\nyHUw\u00146wUA^";
                    var4_3 = "uX\\\"\u0000E&\u0004\u001f\u000e\nyHUw\u00146wUA^".length();
                    var1_4 = 10;
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
                    var5[var3_1++] = lf21.gui.c.j.b(lf21.gui.c.j.d(v1));
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
        lf21.gui.c.j.s = var5;
        v4 = new String[2];
        var0_6 = lf21.gui.c.j.s;
        v4[0] = var0_6[20];
        v4[1] = var0_6[0];
        lf21.gui.c.j.m = v4;
        lf21.gui.c.j.o = new String[]{var0_6[18], var0_6[10], var0_6[11], var0_6[2], var0_6[12]};
        lf21.gui.c.j.q = new Color(20, 20, 24, 230);
    }

    private static char[] d(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x14);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
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
                    case 0 -> 52;
                    case 1 -> 45;
                    case 2 -> 59;
                    case 3 -> 2;
                    case 4 -> 52;
                    case 5 -> 101;
                    default -> 20;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

