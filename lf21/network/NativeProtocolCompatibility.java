/*
 * Decompiled with CFR 0.152.
 */
package lf21.network;

import java.lang.reflect.Method;
import java.util.Locale;
import lf21.render.ad;
import net.minecraft.network.protocol.Packet;

public final class NativeProtocolCompatibility {
    private static final String[] a;
    private static volatile int b;
    private static final String[] c;

    private NativeProtocolCompatibility() {
    }

    public static boolean a() {
        int n2 = b;
        if (n2 != 0) {
            return n2 > 0;
        }
        String[] stringArray = c;
        boolean bl = NativeProtocolCompatibility.a(System.getProperty(stringArray[12])) || NativeProtocolCompatibility.a(System.getProperty(stringArray[9]), stringArray[20]) || NativeProtocolCompatibility.a(System.getProperty(stringArray[21]), stringArray[15]) || NativeProtocolCompatibility.b();
        b = bl ? 1 : -1;
        return bl;
    }

    public static boolean isProtectedPacket(Packet<?> packet) {
        String[] stringArray;
        if (packet == null || !NativeProtocolCompatibility.a()) {
            return false;
        }
        String string = packet.getClass().getName();
        if (string.contains((stringArray = c)[3])) {
            return true;
        }
        stringArray = c;
        if (!string.contains(stringArray[6])) {
            return false;
        }
        String string2 = NativeProtocolCompatibility.a(packet);
        if (string2 == null || string2.isBlank()) {
            return true;
        }
        String string3 = string2.toLowerCase(Locale.ROOT);
        return string3.equals((stringArray = c)[10]) || string3.startsWith(stringArray[14]) || string3.startsWith(stringArray[16]) || string3.startsWith(stringArray[2]) || string3.equals(stringArray[7]) || string3.equals(stringArray[5]) || string3.equals(stringArray[0]);
    }

    private static String a(Object object) {
        String[] stringArray;
        String[] stringArray2 = new String[5];
        String[] stringArray3 = c;
        stringArray2[0] = stringArray3[17];
        stringArray2[1] = stringArray3[1];
        stringArray2[2] = stringArray3[4];
        stringArray2[3] = stringArray3[19];
        stringArray2[4] = stringArray3[8];
        for (String string : stringArray = stringArray2) {
            try {
                Object object2;
                Class[] classArray = new Class[]{};
                Class<?> clazz = object.getClass();
                Method method = clazz.getMethod(ad.b(string, clazz, classArray), classArray);
                if (method.getParameterCount() != 0 || (object2 = method.invoke(object, new Object[0])) == null) continue;
                return object2.toString();
            }
            catch (LinkageError | ReflectiveOperationException throwable) {
                // empty catch block
            }
        }
        return null;
    }

    private static boolean b() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String[] stringArray = a;
        int n2 = stringArray.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            String string = "vv:server_details";
            try {
                Class.forName(ad.a(string), false, classLoader);
                return true;
            }
            catch (ClassNotFoundException | LinkageError throwable) {
                continue;
            }
        }
        return false;
    }

    private static boolean a(String string) {
        return string != null && !string.isBlank();
    }

    private static boolean a(String string, String string2) {
        return string != null && string.toLowerCase(Locale.ROOT).contains(string2.toLowerCase(Locale.ROOT));
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[22];
                var3_1 = 0;
                var2_2 = "\u001b_9?#\u0013\u001b\b[\\(#\u0015\f\u0004Ep\n\u0004Mf\"2\b\u000b\u0004Lq\t\u0005Lz</\u0019\b\u0001\u0013\u0011.\\p8)\f<\u0018Lq5\u0016\u0000\u000e\u0006Lw\u0007\nLw\u0002'\f\b\u000f\u0000@m)%\u0013\f\u000b]9.4\u0000\u0003\t\u0013.\\p8)\f=\fPo#'\u0005=\fJh)2\u0012\u0000@m)%\u0013\f\u000b]9>#\u0006\u0004\u001e]f>\u0004\u0019Ps)\u0010\u001e\\mb,\u0000\u001b\f\u0007`#+\f\f\u0003M\u0011\u000bEl#\"\u0006\f\u0019L9\"#\u0015\b\fZf-\u0003Hw%0\u00049\u001fHm? \u000e\u001f\u0000Lqb\u0005\u0014\u001e\u0019Fn\u000b#\u000f\b\u001f@`\n3\u0015\u0018\u001fLO%5\u0015\b\u0003Lq\u0013\u0001Hv\"%\t\b\u001fjl\"2\u0013\u0002\u0001yl>2)\u000eFnb(\u0004\u0019\bHp)h\f\u000eCDl(h\u000f\b\u0019^l>-O\u000e\u0002Dn#(O!\u0004Kq-4\u0018\b\u0003Lw)'\u0012\bW\u0007\u0003Lw)'\u0012\b\n\u000bEl#\"\u0006\f\u0019L9\r\nLw\u0005\"\u0004\u0003\u0019@e%#\u0013,\u000eFnb(\u0004\u0019\bHp)h\f\u000eCDl(h\u0000\u0018\u0019Ao%$O,\u0018]k)(\u0015\u0004\u000eHw%)\u000f.\u001dY\u0002\u0004M";
                var4_3 = "\u001b_9?#\u0013\u001b\b[\\(#\u0015\f\u0004Ep\n\u0004Mf\"2\b\u000b\u0004Lq\t\u0005Lz</\u0019\b\u0001\u0013\u0011.\\p8)\f<\u0018Lq5\u0016\u0000\u000e\u0006Lw\u0007\nLw\u0002'\f\b\u000f\u0000@m)%\u0013\f\u000b]9.4\u0000\u0003\t\u0013.\\p8)\f=\fPo#'\u0005=\fJh)2\u0012\u0000@m)%\u0013\f\u000b]9>#\u0006\u0004\u001e]f>\u0004\u0019Ps)\u0010\u001e\\mb,\u0000\u001b\f\u0007`#+\f\f\u0003M\u0011\u000bEl#\"\u0006\f\u0019L9\"#\u0015\b\fZf-\u0003Hw%0\u00049\u001fHm? \u000e\u001f\u0000Lqb\u0005\u0014\u001e\u0019Fn\u000b#\u000f\b\u001f@`\n3\u0015\u0018\u001fLO%5\u0015\b\u0003Lq\u0013\u0001Hv\"%\t\b\u001fjl\"2\u0013\u0002\u0001yl>2)\u000eFnb(\u0004\u0019\bHp)h\f\u000eCDl(h\u000f\b\u0019^l>-O\u000e\u0002Dn#(O!\u0004Kq-4\u0018\b\u0003Lw)'\u0012\bW\u0007\u0003Lw)'\u0012\b\n\u000bEl#\"\u0006\f\u0019L9\r\nLw\u0005\"\u0004\u0003\u0019@e%#\u0013,\u000eFnb(\u0004\u0019\bHp)h\f\u000eCDl(h\u0000\u0018\u0019Ao%$O,\u0018]k)(\u0015\u0004\u000eHw%)\u000f.\u001dY\u0002\u0004M".length();
                var1_4 = 17;
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
                    var5[var3_1++] = NativeProtocolCompatibility.a(NativeProtocolCompatibility.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "*Hn)\u0012\u0018\u001d\b\u00131\u0010\u001e\\mb,\u0000\u001b\f\u0007`#+\f\f\u0003M";
                    var4_3 = "*Hn)\u0012\u0018\u001d\b\u00131\u0010\u001e\\mb,\u0000\u001b\f\u0007`#+\f\f\u0003M".length();
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
                    var5[var3_1++] = NativeProtocolCompatibility.a(NativeProtocolCompatibility.b(v1));
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
        NativeProtocolCompatibility.c = var5;
        v4 = new String[3];
        var0_6 = NativeProtocolCompatibility.c;
        v4[0] = var0_6[13];
        v4[1] = var0_6[18];
        v4[2] = var0_6[11];
        NativeProtocolCompatibility.a = v4;
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6D);
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
                    case 0 -> 109;
                    case 1 -> 41;
                    case 2 -> 3;
                    case 3 -> 76;
                    case 4 -> 70;
                    case 5 -> 97;
                    default -> 109;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

