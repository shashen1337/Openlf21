/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lf21.event.EventTarget;
import lf21.event.impl.GlRenderEvent;
import lf21.gui.IntroAnimation;
import lf21.gui.l;
import lf21.manager.b;
import lf21.modules.Module;
import lf21.modules.impl.render.Interface;
import lf21.render.C;
import lf21.render.D;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.Minecraft;

public class NotificationManager {
    private static final long a;
    private static final float b;
    private static final float c;
    private static final float d;
    private static final float e;
    private static final float f;
    private static final float g;
    private static final float h;
    private static final int i;
    private static final int j;
    private final List<b> k = new CopyOnWriteArrayList<b>();
    private final u_0 l = t_0.a(15.6f);
    private final D m = new D();
    private final D n = new D();
    private final D o = new D();
    private final C p = new C();
    private long q;
    private static final String[] r;

    public void a(Module module, boolean bl) {
        String string;
        if (module == null) {
            return;
        }
        String string2 = module.getName();
        if (bl) {
            String[] stringArray = r;
            string = "Enabled";
        } else {
            string = "Disabled";
        }
        this.k.add(new b(string2, string, bl ? 7784011 : 14048087, 3000L));
    }

    public void a(String string, String string2, int n2) {
        if (string == null || string.isBlank() || string2 == null || string2.isBlank()) {
            return;
        }
        this.k.add(new b(string, string2, n2 & 0xFFFFFF, 3000L));
    }

    @EventTarget(a=4)
    public void a(GlRenderEvent glRenderEvent) {
        if (Minecraft.getInstance().screen instanceof l) {
            return;
        }
        this.b(glRenderEvent);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(GlRenderEvent glRenderEvent) {
        if (IntroAnimation.a() || this.k.isEmpty()) {
            return;
        }
        long l2 = System.nanoTime();
        float f2 = this.q == 0L ? 0.016666668f : Math.min(0.05f, (float)(l2 - this.q) / 1.0E9f);
        this.q = l2;
        long l3 = System.currentTimeMillis();
        float f3 = 7.0f;
        float f4 = glRenderEvent.a().guiWidth();
        float f5 = glRenderEvent.a().guiHeight();
        boolean bl = Interface.j != null && Interface.j.isEnabled() && (Boolean)Interface.j.hUDGlow.getValue() != false && this.p.a(glRenderEvent.a());
        try {
            for (b b2 : this.k) {
                float f6 = this.a(b2);
                f3 += 30.0f;
                if (l3 - b2.e > b2.d) {
                    b2.f.a = 0.0f;
                    b2.g.a = 0.0f;
                    if (b2.f.a()) {
                        this.k.remove(b2);
                        continue;
                    }
                } else {
                    b2.f.a = f6;
                    b2.g.a = f3;
                }
                b2.f.a(f2);
                b2.g.a(f2);
                float f7 = f4 - 10.0f - b2.f.b - 2.0f;
                float f8 = f5 - b2.g.b;
                this.a(glRenderEvent, b2, f7, f8, f6);
            }
            if (bl) {
                Interface interface_ = Interface.j;
                this.p.a(glRenderEvent.a(), ((Number)interface_.glowRadius.getValue()).floatValue(), ((Number)interface_.glowCompression.getValue()).floatValue(), ((Number)interface_.glowIntensity.getValue()).floatValue());
            }
        }
        catch (Throwable throwable) {
            if (bl) {
                Interface interface_ = Interface.j;
                this.p.a(glRenderEvent.a(), ((Number)interface_.glowRadius.getValue()).floatValue(), ((Number)interface_.glowCompression.getValue()).floatValue(), ((Number)interface_.glowIntensity.getValue()).floatValue());
            }
            throw throwable;
        }
    }

    private float a(b b2) {
        float f2 = y_0.a(b2.a, this.l);
        float f3 = y_0.a(b2.b, this.l);
        return f2 + f3 + 28.0f;
    }

    private void a(GlRenderEvent glRenderEvent, b b2, float f2, float f3, float f4) {
        float f5 = f2 + 2.0f;
        float f6 = f3 + 4.0f;
        C.a(glRenderEvent.b(), f5, f6, f4, 26.0f, 9.0f, 1.0f);
        RenderUtil.a(glRenderEvent.b(), f5, f6, f4, 26.0f, 9.0f, 26.0f, 1.0f, 0);
        this.m.a(-1291845632);
        y_0.a(f5, f6, f4, 26.0f, 9.0f, this.m);
        this.n.a(-789001);
        this.o.a(0xFF000000 | b2.c);
        float f7 = f5 + 10.0f;
        float f8 = f6 + (26.0f - this.l.d().f()) * 0.5f + 1.0f;
        y_0.a(b2.a, f7, f8, this.l, this.n, false);
        y_0.a(b2.b, f7 + y_0.a(b2.a, this.l) + 8.0f, f8, this.l, this.o, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        j = -789001;
        i = -1291845632;
        h = 10.0f;
        g = 26.0f;
        f = 9.0f;
        e = 30.0f;
        d = 26.0f;
        c = 7.0f;
        b = 0.4f;
        a = 3000L;
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "j\u0018LC_+\f\bk\u001f^@Q\"\rK";
        int n3 = "j\u0018LC_+\f\bk\u001f^@Q\"\rK".length();
        int n4 = 7;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = NotificationManager.a(NotificationManager.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                r = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x68);
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
                    case 0 -> 47;
                    case 1 -> 118;
                    case 2 -> 45;
                    case 3 -> 33;
                    case 4 -> 51;
                    case 5 -> 78;
                    default -> 104;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

