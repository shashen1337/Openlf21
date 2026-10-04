/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c.a;

import java.util.ArrayList;
import java.util.List;
import lf21.gui.c.a.a;
import lf21.gui.c.a.b;
import lf21.gui.c.a.c;
import lf21.gui.c.a.d;
import lf21.gui.c.a.e;
import lf21.settings.Setting;
import lf21.settings.impl.ModeSetting;
import net.minecraft.client.gui.GuiGraphics;

public class f {
    private static final f a;
    private final List<e> b = new ArrayList<e>();
    private static final String c;

    private f() {
        this.a(new b());
        this.a(new a());
        this.a(new d());
        this.a(new c());
    }

    public static f a() {
        return a;
    }

    public void a(e e2) {
        this.b.add(e2);
    }

    public e a(Setting<?> setting) {
        for (e e2 : this.b) {
            if (!e2.a(setting)) continue;
            return e2;
        }
        return null;
    }

    public int a(GuiGraphics guiGraphics, Setting<?> setting, int n2, int n3, int n4, int n5, int n6, float f2, float f3) {
        e e2 = this.a(setting);
        if (!setting.getName().equals(c) || setting instanceof ModeSetting) {
            // empty if block
        }
        if (e2 != null) {
            return e2.a(guiGraphics, setting, n2, n3, n4, n5, n6, f2, f3);
        }
        return 0;
    }

    public boolean a(Setting<?> setting, int n2, int n3, int n4, int n5, int n6, int n7, float f2) {
        e e2 = this.a(setting);
        if (e2 != null) {
            return e2.a(setting, n2, n3, n4, n5, n6, n7, f2);
        }
        return false;
    }

    public int a(Setting<?> setting, float f2) {
        e e2 = this.a(setting);
        if (e2 != null) {
            return e2.a(setting, f2);
        }
        return 0;
    }

    public int b(Setting<?> setting, float f2) {
        e e2 = this.a(setting);
        if (e2 != null) {
            return e2.a(setting, f2);
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string = f.a(f.a("c\u0015\t\u0011"));
        c = string;
        a = new f();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x75);
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
                    case 0 -> 46;
                    case 1 -> 122;
                    case 2 -> 109;
                    case 3 -> 116;
                    case 4 -> 26;
                    case 5 -> 112;
                    default -> 117;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

