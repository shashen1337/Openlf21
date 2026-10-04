/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c;

import java.awt.Color;
import java.util.Locale;
import lf21.ClientBase;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.b.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class h
extends ClientBase {
    private static final Color a;
    private boolean b = false;
    private float c = 0.0f;
    private long d = 0L;
    private float e = 1.0f;
    private float f = 1.0f;
    private static final String[] g;

    public void a(float f2, float f3) {
        this.e = f2;
        this.f = f3;
        this.b = true;
        this.d = System.currentTimeMillis();
    }

    public void a() {
        this.b = false;
    }

    public boolean b() {
        return this.b;
    }

    public boolean c() {
        return this.b && this.c >= 1.0f;
    }

    public boolean d() {
        return !this.b && this.c <= 0.0f;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(GuiGraphics guiGraphics, int n2, int n3, float f2) {
        if (!this.b && this.c <= 0.005f) {
            return;
        }
        this.e();
        if (this.c <= 0.005f) {
            return;
        }
        try {
            this.a(guiGraphics, n2, n3);
            float f3 = 400.0f * f2;
            float f4 = 180.0f * f2;
            int n4 = (int)(((float)n2 - f3) / 2.0f);
            int n5 = (int)(((float)n3 - f4) / 2.0f);
            this.a(guiGraphics, n4, n5, f3, f4, f2);
            this.a(guiGraphics, n4, n5, f3, f2);
            return;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void e() {
        this.c = this.b ? lf21.utils.b.g.a(this.c, 1.0f, 0.08f) : lf21.utils.b.g.a(this.c, 0.0f, 0.08f);
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3) {
        Color color = new Color(a.getRed(), a.getGreen(), a.getBlue(), (int)((float)a.getAlpha() * this.c));
        RenderUtil.b(guiGraphics.pose(), 0.0f, 0.0f, (float)n2, (float)n3, 0.0f, color.getRGB());
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3, float f4) {
        T.a(guiGraphics.pose(), (float)n2, (float)n3, f2, f3, 12.0f * f4, this.c);
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3) {
        Renderer.a((DrawContext drawContext) -> {
            int n4 = (int)(255.0f * this.c);
            if (n4 <= 0) {
                return;
            }
            u_0 u_02 = t_0.k(24.0f * f3);
            String[] stringArray = g;
            String string = "Waiting";
            float f4 = y_0.a(string, u_02);
            float f5 = (float)n2 + (f2 - f4) / 2.0f;
            float f6 = (float)n3 + 45.0f * f3;
            int n5 = n4 << 24 | 0xFFFFFF;
            int n6 = n4 << 24 | 0xFFFFFF;
            T.a(string, f5, f6, u_02, n5, n6, 10.0f * f3);
            u_0 u_03 = t_0.l(18.0f * f3);
            String string2 = String.format(Locale.US, "Switching scale from %.0f%% to %.0f%%", Float.valueOf(this.e * 100.0f), Float.valueOf(this.f * 100.0f));
            float f7 = y_0.a(string2, u_03);
            float f8 = (float)n2 + (f2 - f7) / 2.0f;
            float f9 = (float)n3 + 75.0f * f3;
            int n7 = n4 << 24 | 0xCCCCCC;
            y_0.a(string2, f8, f9, u_03, n7);
            this.a(n2, (int)((float)n3 + 115.0f * f3), (int)f2, n4, f3);
        });
    }

    private void a(int n2, int n3, int n4, int n5, float f2) {
        u_0 u_02 = t_0.k(20.0f * f2);
        String string = "\u2022";
        float f3 = y_0.a(string, u_02);
        float f4 = f3 * 3.0f + 20.0f * f2;
        float f5 = (float)n2 + ((float)n4 - f4) / 2.0f;
        int n6 = n5 << 24 | 0xFFFFFF;
        long l2 = System.currentTimeMillis() - this.d;
        long l3 = l2 % 1400L;
        for (int i2 = 0; i2 < 3; ++i2) {
            float f6;
            float f7 = f5 + (float)i2 * (f3 + 10.0f * f2);
            long l4 = (long)i2 * 150L;
            long l5 = l4 + 300L;
            float f8 = 0.0f;
            if (l3 >= l4 && l3 <= l5) {
                f6 = (float)(l3 - l4) / 300.0f;
                float f9 = f6 * (float)Math.PI;
                f8 = (float)(Math.sin(f9) * 6.0 * (double)f2);
            }
            f6 = (float)n3 - f8;
            y_0.a(string, f7, f6, u_02, n6);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u0005zMz\u007f]\u001f8j\u0004}\u007fT\u001a3-B|sXVs#\u0014h9\u0010V\"b\u0004+2\u0005\u0010s(\u0007\u0001lMzu[\u0011";
        int n3 = "\u0005zMz\u007f]\u001f8j\u0004}\u007fT\u001a3-B|sXVs#\u0014h9\u0010V\"b\u0004+2\u0005\u0010s(\u0007\u0001lMzu[\u0011".length();
        int n4 = 37;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = h.b(h.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                g = stringArray;
                a = new Color(124, 124, 124, 13);
                new Color(255, 255, 255, 40);
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x76);
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
                    case 0 -> 86;
                    case 1 -> 13;
                    case 2 -> 36;
                    case 3 -> 14;
                    case 4 -> 28;
                    case 5 -> 53;
                    default -> 118;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

