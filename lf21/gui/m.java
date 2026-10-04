/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

import java.util.ArrayList;
import java.util.List;
import lf21.LF21Client;
import lf21.gui.a.a;
import lf21.gui.a.c;
import lf21.modules.Category;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class m
extends Screen {
    private final List<a> a = new ArrayList<a>();
    private static final String b;
    private static c c;
    private static final String[] d;

    public m() {
        super(Component.nullToEmpty((String)"Click GUI"));
        int n2 = 20;
        for (Category category : Category.a()) {
            this.a.add(new a(n2, 20, 140, 20, category));
            n2 += 160;
        }
    }

    public void a() {
        super.init();
        m.c();
        for (a a2 : this.a) {
            for (c c2 : a2.j) {
                c2.c();
            }
        }
    }

    public void a(GuiGraphics guiGraphics, int n2, int n3, float f2) {
        for (a a2 : this.a) {
            a2.a(guiGraphics, n2, n3, f2);
            a2.b(n2, n3);
        }
        super.render(guiGraphics, n2, n3, f2);
    }

    public boolean a(double d2, double d3, int n2) {
        for (a a2 : this.a) {
            a2.b(d2, d3, n2);
        }
        return super.mouseReleased(d2, d3, n2);
    }

    public boolean b(double d2, double d3, int n2) {
        for (a a2 : this.a) {
            a2.a(d2, d3, n2);
        }
        return true;
    }

    public boolean a(double d2, double d3, double d4) {
        for (a a2 : this.a) {
            a2.a(d2, d3, d4);
        }
        return true;
    }

    public void b() {
        m.c();
        if (LF21Client.isReady()) {
            LF21Client.instance.getConfigManager().saveAll();
        }
        super.onClose();
    }

    public static void a(c c2) {
        m.c();
        c = c2;
        if (c != null) {
            c.a(true);
        }
    }

    private static void c() {
        if (c != null) {
            c.a(false);
        }
        c = null;
    }

    public boolean a(int n2, int n3, int n4) {
        if (c != null) {
            m.c.a.setKey(n2 == 256 ? 0 : n2);
            m.c();
            if (LF21Client.isReady()) {
                LF21Client.getInstance().getConfigManager().saveAll();
            }
            return true;
        }
        if (n2 == 256) {
            this.b();
            return true;
        }
        return super.keyPressed(n2, n3, n4);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u0005@f?p\u0010]\u0013e\t\u0005@f?p\u0010]\u0013e";
        int n3 = "\u0005@f?p\u0010]\u0013e\t\u0005@f?p\u0010]\u0013e".length();
        int n4 = 9;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = m.a(m.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                d = stringArray;
                b = d[1];
                m m2 = new m();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x1A);
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
                    case 0 -> 70;
                    case 1 -> 44;
                    case 2 -> 15;
                    case 3 -> 92;
                    case 4 -> 27;
                    case 5 -> 48;
                    default -> 26;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

