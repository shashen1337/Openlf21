/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import lf21.render.g_0;
import lf21.render.q_0;
import lf21.render.u_0;
import lf21.utils.misc.a;

/*
 * Renamed from lf21.render.w
 */
public final class w_0 {
    private static final Map<String, u_0> a = new HashMap<String, u_0>();
    private static final Map<String, q_0> b = new HashMap<String, q_0>();
    private static final Map<String, Font> c = new HashMap<String, Font>();

    static Font a(float f2) {
        Font font = c.computeIfAbsent("pingfang_sc_regular.ttf", w_0::a);
        return font == null ? null : font.deriveFont(0, f2);
    }

    private static g_0 b(String string) {
        int n2 = string.lastIndexOf(46);
        if (n2 < 0) {
            return g_0.UNKNOWN;
        }
        return g_0.b(string.substring(n2 + 1));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Font a(String string) {
        try (InputStream inputStream = lf21.utils.misc.a.a("/assets/lf21/fonts/" + string);){
            if (inputStream == null) {
                Font font = null;
                return font;
            }
            Font font = Font.createFont(0, inputStream);
            return font;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static synchronized q_0 b(String string, float f2) {
        String string2 = string + "-" + f2;
        q_0 q_02 = b.get(string2);
        if (q_02 != null) {
            return q_02;
        }
        try {
            Font font = c.computeIfAbsent(string, w_0::a);
            if (font == null) {
                return null;
            }
            Font font2 = font.deriveFont(0, f2 / 2.0f);
            q_0 q_03 = new q_0(font2, f2 / 2.0f);
            b.put(string2, q_03);
            return q_03;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static u_0 a(String string, float f2, g_0 g_02) {
        String string3 = string + "-" + f2;
        return a.computeIfAbsent(string3, string2 -> new u_0(string, f2));
    }

    public static u_0 a(String string, float f2) {
        return w_0.a(string, f2, w_0.b(string));
    }
}

