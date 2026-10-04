/*
 * Decompiled with CFR 0.152.
 */
package lf21.hwid;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import lf21.hwid.a;

public final class HwidProfileBridge {
    private static final long a;
    private static volatile long b;
    private static volatile a c;
    private static final String[] d;

    private HwidProfileBridge() {
    }

    public static String cpuDisplay() {
        a a2 = HwidProfileBridge.a();
        String string = a2 == null ? null : a2.c + "x " + a2.a;
        return string;
    }

    public static String cpuName() {
        a a2 = HwidProfileBridge.a();
        String string = a2 == null ? null : a2.a;
        return string;
    }

    public static String cpuVendor() {
        a a2 = HwidProfileBridge.a();
        if (a2 == null) {
            return null;
        }
        String string = a2.a.toLowerCase(Locale.ROOT).contains("amd") ? "AuthenticAMD" : "GenuineIntel";
        return string;
    }

    public static String processorId() {
        a a2 = HwidProfileBridge.a();
        String string = a2 == null ? null : a2.d;
        return string;
    }

    public static String renderer() {
        a a2 = HwidProfileBridge.a();
        String string = a2 == null ? null : a2.b + "/PCIe/SSE2";
        return string;
    }

    public static String graphicsVendor() {
        block12: {
            String string;
            block11: {
                a a2 = HwidProfileBridge.a();
                if (a2 == null) {
                    return null;
                }
                string = a2.b.toLowerCase(Locale.ROOT);
                if (!string.contains("nvidia") && !string.contains("geforce")) break block11;
                return "NVIDIA Corporation";
            }
            if (!string.contains("amd") && !string.contains("radeon")) break block12;
            return "ATI Technologies Inc.";
        }
        return "Intel";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static a a() {
        long l2 = System.currentTimeMillis();
        if (l2 < b) {
            return c;
        }
        Class<HwidProfileBridge> clazz = HwidProfileBridge.class;
        synchronized (HwidProfileBridge.class) {
            if (l2 < b) {
                // ** MonitorExit[var2_1] (shouldn't be in output)
                return c;
            }
            b = l2 + 1000L;
            c = HwidProfileBridge.b();
            // ** MonitorExit[var2_1] (shouldn't be in output)
            return c;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static a b() {
        int n2;
        String string2;
        Object object;
        Object object2;
        String[] stringArray = d;
        String string3 = System.getProperty("java.home", "");
        if (string3.isEmpty()) {
            return null;
        }
        stringArray = d;
        Path path = Path.of(string3, new String[0]).resolve("bin");
        Path path3 = path.resolve("lf21-hwid-spoofer.marker");
        Path path4 = path.resolve("lf21_hwid.dll");
        Path path2 = path.resolve("winmm.dll");
        if (!Files.isRegularFile(path3, new LinkOption[0])) return null;
        if (!Files.isRegularFile(path4, new LinkOption[0]) && !Files.isRegularFile(path2, new LinkOption[0])) {
            return null;
        }
        Object object3 = null;
        stringArray = d;
        String string4 = System.getenv("LOCALAPPDATA");
        if (string4 != null && !string4.isBlank()) {
            String[] stringArray2 = new String[2];
            stringArray = d;
            stringArray2[0] = "LF21";
            stringArray2[1] = "lf21_hwid_profile_" + ProcessHandle.current().pid() + ".ini";
            object2 = Path.of(string4, stringArray2);
            if (Files.isRegularFile((Path)object2, new LinkOption[0])) {
                object3 = object2;
            }
        }
        if (object3 == null) {
            stringArray = d;
            object2 = path.resolve("lf21_hwid_profile.ini");
            if (Files.isRegularFile((Path)object2, new LinkOption[0])) {
                object3 = object2;
            }
        }
        if (object3 == null) {
            return null;
        }
        object2 = new HashMap();
        try {
            object = Files.newBufferedReader(object3, StandardCharsets.UTF_8);
            try {
                while ((string2 = ((BufferedReader)object).readLine()) != null) {
                    int n3;
                    if ((string2 = string2.trim()).isEmpty() || string2.startsWith("[") || string2.startsWith(";") || string2.startsWith("#") || (n3 = string2.indexOf(61)) <= 0) continue;
                    object2.put(string2.substring(0, n3).trim().toLowerCase(Locale.ROOT), string2.substring(n3 + 1).trim());
                }
            }
            finally {
                if (object != null) {
                    ((BufferedReader)object).close();
                }
            }
        }
        catch (IOException iOException) {
            return null;
        }
        stringArray = d;
        object = (String)object2.get("cpu");
        string2 = (String)object2.get("gpu");
        String string = object2.getOrDefault("seed", "0000000000000000").replaceAll("[^0-9A-Fa-f]", "").toUpperCase(Locale.ROOT);
        if (object == null) return null;
        if (((String)object).isEmpty()) return null;
        if (string2 == null) return null;
        if (string2.isEmpty()) {
            return null;
        }
        try {
            n2 = Integer.parseInt(object2.getOrDefault("threads", "8"));
            return new a((String)object, string2, Math.max(1, n2), string);
        }
        catch (NumberFormatException numberFormatException) {
            n2 = 8;
        }
        return new a((String)object, string2, Math.max(1, n2), string);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                HwidProfileBridge.a = 1000L;
                var5 = new String[28];
                var3_1 = 0;
                var2_2 = "\u0000_d=bQK\u0000_d=bQK\u0000_\tZ\u000e\"l|\t\u0014]\n\fk1d k Vv\u000eyk\u000f\u0004\u001e\u0006:d\fw\n:x;\u000f\u001ey\u0001 h>\f| \u0017L\u001e +`+\u0015Y\u0013\u0003R\u0006:\fq\u001a e7\u000f\u000fY\f\u0015@\u0016\r\\\tf<\r\t\fY\u000bzi>\r\u0005y\u0001 h>\u0015q;\u001d-\u0006\u0004\u0018X\u0001;a=\u0006\u0012U\u001ctD<\u0002U\tG\u0006:`?O\u001f\\\u0003\u0003Q\u00020\u0006^\u0019=i;\u0000\u0007D\u0007&h3\u0005\b\n\u001f?\u0017D7N(c*f\u0003W\u001f!\u0004|)f<\u0012\\\tf<\r\t\fY\u000b\u000b} \u000e\u001dY\u00031R\u0018\\\tf<\u007f\t\fY\u000by~\"\u000e\u0014V\n&#?\u0000\t[\n&\u0002HO\u0015\\\tf<\r\t\fY\u000b\u000b} \u000e\u001dY\u00031#;\u000f\u0012\u0007W\n2b \u0002\u001e\u0004C\n1i\u0003Q\u00020\u0003S\u001f!";
                var4_3 = "\u0000_d=bQK\u0000_d=bQK\u0000_\tZ\u000e\"l|\t\u0014]\n\fk1d k Vv\u000eyk\u000f\u0004\u001e\u0006:d\fw\n:x;\u000f\u001ey\u0001 h>\f| \u0017L\u001e +`+\u0015Y\u0013\u0003R\u0006:\fq\u001a e7\u000f\u000fY\f\u0015@\u0016\r\\\tf<\r\t\fY\u000bzi>\r\u0005y\u0001 h>\u0015q;\u001d-\u0006\u0004\u0018X\u0001;a=\u0006\u0012U\u001ctD<\u0002U\tG\u0006:`?O\u001f\\\u0003\u0003Q\u00020\u0006^\u0019=i;\u0000\u0007D\u0007&h3\u0005\b\n\u001f?\u0017D7N(c*f\u0003W\u001f!\u0004|)f<\u0012\\\tf<\r\t\fY\u000b\u000b} \u000e\u001dY\u00031R\u0018\\\tf<\u007f\t\fY\u000by~\"\u000e\u0014V\n&#?\u0000\t[\n&\u0002HO\u0015\\\tf<\r\t\fY\u000b\u000b} \u000e\u001dY\u00031#;\u000f\u0012\u0007W\n2b \u0002\u001e\u0004C\n1i\u0003Q\u00020\u0003S\u001f!".length();
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
                    var5[var3_1++] = HwidProfileBridge.a(HwidProfileBridge.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "B\u000e0h=\u000f\u0012~9\u001dI\u001b [s\u0000&}=\u0013\u001aD\u0006;c";
                    var4_3 = "B\u000e0h=\u000f\u0012~9\u001dI\u001b [s\u0000&}=\u0013\u001aD\u0006;c".length();
                    var1_4 = 6;
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
                    var5[var3_1++] = HwidProfileBridge.a(HwidProfileBridge.a(v1));
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
        HwidProfileBridge.d = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x7B);
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
                    case 0 -> 48;
                    case 1 -> 111;
                    case 2 -> 84;
                    case 3 -> 13;
                    case 4 -> 82;
                    case 5 -> 97;
                    default -> 123;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

