/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

import java.awt.Color;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.GlRenderEvent;
import lf21.render.D;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;

public class IntroAnimation
extends ClientBase {
    private static volatile boolean a;
    private long b = -1L;
    private boolean c = false;
    private static final String[] d;

    public IntroAnimation() {
        a = true;
    }

    public static boolean a() {
        return a;
    }

    @EventTarget(a=4)
    public void a(GlRenderEvent glRenderEvent) {
        float f2;
        float f3;
        long l2;
        if (this.c) {
            return;
        }
        if (this.b < 0L) {
            this.b = System.currentTimeMillis();
        }
        long l3 = l2 = System.currentTimeMillis() - this.b;
        float f4 = mc.getWindow().getGuiScaledWidth();
        float f5 = mc.getWindow().getGuiScaledHeight();
        float f6 = f4 / 2.0f;
        float f7 = f5 / 2.0f;
        long l4 = 1100L;
        long l5 = l4 + 900L + 700L + 500L + 300L + 500L + 1300L;
        if (l2 <= 800L) {
            var16_10 = IntroAnimation.b(IntroAnimation.a((float)l2 / 800.0f));
            f3 = 0.6f * var16_10;
        } else if (l2 <= l5) {
            f3 = 0.6f;
        } else if (l2 <= l5 + 700L) {
            var16_10 = 1.0f - IntroAnimation.c(IntroAnimation.a((float)(l2 - l5) / 700.0f));
            f3 = 0.6f * var16_10;
        } else {
            this.b();
            return;
        }
        D d2 = y_0.a((Object)new Color(0, 0, 0, (int)(f3 * 255.0f)));
        y_0.a(0.0f, 0.0f, f4, f5, d2);
        float f8 = 1.0f;
        float f9 = 0.0f;
        if (l2 >= l4) {
            long l6 = l2 - l4;
            if (l6 <= 900L) {
                float f10 = IntroAnimation.b(IntroAnimation.a((float)l6 / 900.0f));
                f8 = IntroAnimation.a(2.0f, 1.0f, f10);
                f9 = f10;
            } else {
                f8 = 1.0f;
                f9 = 1.0f;
            }
        }
        float f11 = 0.0f;
        long l7 = l4 + 900L + 700L;
        if (l2 > l7 && l2 <= l7 + 500L) {
            f11 = IntroAnimation.b((float)(l2 - l7) / 500.0f);
        } else if (l2 > l7 + 500L) {
            f11 = 1.0f;
        }
        u_0 u_02 = t_0.k(64.0f * f8);
        u_0 u_03 = t_0.k(64.0f);
        float f12 = y_0.a("l", u_02);
        String[] stringArray = d;
        float f13 = y_0.a(stringArray[0], u_03);
        float f14 = f6 - f12 / 2.0f;
        float f15 = f6 - (f12 + 0.0f + f13) / 2.0f;
        float f16 = IntroAnimation.a(f14, f15, f11);
        float f17 = f7 - u_02.d().f() / 2.0f;
        float f18 = 0.0f;
        float f19 = 12.0f;
        long l8 = l7 + 500L + 300L;
        if (l2 > l8 && l2 <= l8 + 500L) {
            f18 = f2 = IntroAnimation.b((float)(l2 - l8) / 500.0f);
            f19 = (1.0f - f2) * 12.0f;
        } else if (l2 > l8 + 500L) {
            f18 = 1.0f;
            f19 = 0.0f;
        }
        f2 = 1.0f;
        if (l2 > l5) {
            f2 = 1.0f - IntroAnimation.a((float)(l2 - l5) / 700.0f);
        }
        int n2 = new Color(1.0f, 1.0f, 1.0f, IntroAnimation.a(f9 * f2)).getRGB();
        y_0.a("l", f16, f17, u_02, n2);
        if (f18 > 0.0f) {
            float f20 = f16 + f12 + 0.0f;
            float f21 = f7 - u_03.d().f() / 2.0f + f19;
            int n3 = new Color(1.0f, 1.0f, 1.0f, IntroAnimation.a(f18 * f2)).getRGB();
            y_0.a(d[1], f20, f21, u_03, n3);
        }
    }

    private void b() {
        if (!this.c) {
            this.c = true;
            try {
                LF21Client.instance.getEventBus().unregister(this);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            a = false;
        }
    }

    private static float a(float f2) {
        return f2 < 0.0f ? 0.0f : (f2 > 1.0f ? 1.0f : f2);
    }

    private static float a(float f2, float f3, float f4) {
        return f2 + (f3 - f2) * f4;
    }

    private static float b(float f2) {
        float f3 = IntroAnimation.a(f2);
        f3 = (float)(1.0 - Math.pow(1.0f - f3, 3.0));
        return f3;
    }

    private static float c(float f2) {
        float f3 = IntroAnimation.a(f2);
        f3 = f3 * f3 * f3;
        return f3;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "%fY\u0003%fY";
        int n3 = "%fY\u0003%fY".length();
        int n4 = 3;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = IntroAnimation.b(IntroAnimation.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                d = stringArray;
                a = false;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x61);
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
                    case 0 -> 67;
                    case 1 -> 84;
                    case 2 -> 104;
                    case 3 -> 43;
                    case 4 -> 79;
                    case 5 -> 34;
                    default -> 97;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

