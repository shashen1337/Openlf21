/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.List;
import java.util.Properties;
import lf21.gui.b.d;
import lf21.gui.b.e;
import lf21.gui.b.g;
import lf21.manager.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class b {
    private static final Logger a;
    private static final File b;
    private static final String[] c;

    private b() {
    }

    public static void a(List<d> list, e e2, g g2, int n2, int n3) {
        String[] stringArray;
        Properties properties = new Properties();
        if (b.isFile()) {
            try (BufferedReader bufferedReader = Files.newBufferedReader(b.toPath(), StandardCharsets.UTF_8);){
                properties.load(bufferedReader);
            }
            catch (IOException iOException) {
                stringArray = c;
                a.warn("Failed to read ClickGUI layout", (Throwable)iOException);
            }
        }
        float f2 = Math.max(12.0f, (float)n2 / 2.0f - 380.0f);
        for (int i2 = 0; i2 < list.size(); ++i2) {
            d d2 = list.get(i2);
            stringArray = c;
            String string = "category." + d2.i().name() + ".";
            float f3 = lf21.gui.b.b.a(properties, string + "x", f2 + (float)i2 * 128.0f);
            float f4 = lf21.gui.b.b.a(properties, string + "y", 36.0f);
            d2.a(lf21.gui.b.b.a(f3, 0.0f, Math.max(0.0f, (float)n2 - 120.0f)));
            d2.b(lf21.gui.b.b.a(f4, 0.0f, Math.max(0.0f, (float)n3 - 20.0f)));
        }
        float f5 = Math.max(8.0f, (float)n2 - 120.0f - 12.0f);
        float f6 = Math.max(36.0f, (float)n3 - 278.0f);
        stringArray = c;
        e2.a(lf21.gui.b.b.a(lf21.gui.b.b.a(properties, "config.x", f5), 0.0f, Math.max(0.0f, (float)n2 - 120.0f)));
        e2.b(lf21.gui.b.b.a(lf21.gui.b.b.a(properties, "config.y", f6), 0.0f, Math.max(0.0f, (float)n3 - 20.0f)));
        float f7 = Math.max(8.0f, f5 - 120.0f - 8.0f);
        g2.a(lf21.gui.b.b.a(lf21.gui.b.b.a(properties, "musicbot.x", f7), 0.0f, Math.max(0.0f, (float)n2 - 120.0f)));
        g2.b(lf21.gui.b.b.a(lf21.gui.b.b.a(properties, "musicbot.y", f6), 0.0f, Math.max(0.0f, (float)n3 - 20.0f)));
    }

    public static void a(List<d> list, e e2, g g2) {
        String[] stringArray;
        Properties properties = new Properties();
        for (d d2 : list) {
            stringArray = c;
            String string = "category." + d2.i().name() + ".";
            properties.setProperty(string + "x", Float.toString(d2.c()));
            properties.setProperty(string + "y", Float.toString(d2.d()));
        }
        stringArray = c;
        properties.setProperty("config.x", Float.toString(e2.c()));
        properties.setProperty("config.y", Float.toString(e2.d()));
        properties.setProperty("musicbot.x", Float.toString(g2.c()));
        properties.setProperty("musicbot.y", Float.toString(g2.d()));
        try {
            if (!ConfigManager.CONFIG_DIR.exists()) {
                ConfigManager.CONFIG_DIR.mkdirs();
            }
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(b.toPath(), StandardCharsets.UTF_8, new OpenOption[0]);){
                stringArray = c;
                properties.store(bufferedWriter, "LF21 ClickGUI panel layout");
            }
        }
        catch (IOException iOException) {
            a.warn("Failed to save ClickGUI layout", (Throwable)iOException);
        }
    }

    private static float a(Properties properties, String string, float f2) {
        try {
            return Float.parseFloat(properties.getProperty(string, Float.toString(f2)));
        }
        catch (NumberFormatException numberFormatException) {
            return f2;
        }
    }

    private static float a(float f2, float f3, float f4) {
        return Math.max(f3, Math.min(f4, f2));
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[15];
                var3_1 = 0;
                var2_2 = "0\u001f2u\u0015qD+\u001a\u001f6n\"\\U\u0006:\u00137T)_J#\u00112v\u00106\u00062\t3f\b\b0\u001f2u\u0015qD+\b0\u001f2u\u0015qD*\n>\u0005/z\u001ft\u0005'^%\n>\u0005/z\u001ft\u0005'^$\n>\u0005/z\u001ft\u0005'^$\u00130\u001c5p\u0017q\u001f:]0r\u0005y\u001f'^?u\u001b\t0\u0011(v\u001by\u0018*^\b0\u001f2u\u0015qD*\u001e\u0015\u00115\u007f\u0019rJ'\u001f|`\u001d`\u000fs30z\u001f}-\u00069|\u007f\u001do\u0005&\u0004\t0\u0011(v\u001by\u0018*^\n>\u0005/z\u001ft\u0005'^%";
                var4_3 = "0\u001f2u\u0015qD+\u001a\u001f6n\"\\U\u0006:\u00137T)_J#\u00112v\u00106\u00062\t3f\b\b0\u001f2u\u0015qD+\b0\u001f2u\u0015qD*\n>\u0005/z\u001ft\u0005'^%\n>\u0005/z\u001ft\u0005'^$\n>\u0005/z\u001ft\u0005'^$\u00130\u001c5p\u0017q\u001f:]0r\u0005y\u001f'^?u\u001b\t0\u0011(v\u001by\u0018*^\b0\u001f2u\u0015qD*\u001e\u0015\u00115\u007f\u0019rJ'\u001f|`\u001d`\u000fs30z\u001f}-\u00069|\u007f\u001do\u0005&\u0004\t0\u0011(v\u001by\u0018*^\n>\u0005/z\u001ft\u0005'^%".length();
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
                    var5[var3_1++] = lf21.gui.b.b.a(lf21.gui.b.b.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0015\u00115\u007f\u0019rJ'\u001f|a\u0019w\u000es30z\u001f}-\u00069|\u007f\u001do\u0005&\u0004\u000e\u0010\u001c5p\u0017Q\u001f:<=j\u0013c\u001e";
                    var4_3 = "\u0015\u00115\u007f\u0019rJ'\u001f|a\u0019w\u000es30z\u001f}-\u00069|\u007f\u001do\u0005&\u0004\u000e\u0010\u001c5p\u0017Q\u001f:<=j\u0013c\u001e".length();
                    var1_4 = 30;
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
                    var5[var3_1++] = lf21.gui.b.b.a(lf21.gui.b.b.a(v1));
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
        lf21.gui.b.b.c = var5;
        var0_6 = lf21.gui.b.b.c;
        lf21.gui.b.b.a = LogManager.getLogger((String)"ClickGuiLayout");
        lf21.gui.b.b.b = new File(ConfigManager.CONFIG_DIR, "clickgui-layout.cfg");
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6A);
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
                    case 0 -> 83;
                    case 1 -> 112;
                    case 2 -> 92;
                    case 3 -> 19;
                    case 4 -> 124;
                    case 5 -> 22;
                    default -> 106;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

