/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashSet;
import lf21.event.EventTarget;
import lf21.event.impl.DisconnectEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.utils.misc.ChatUtil;

public class Debugger
extends Module {
    private static final String[] j;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000f\u0003w\u0018P\u0007\u0015\u0002#2FpL";
                var4_3 = "\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000f\u0003w\u0018P\u0007\u0015\u0002#2FpL".length();
                var1_4 = 26;
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
                    var5[var3_1++] = Debugger.c(Debugger.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000f\b}G\u0012\"U-\tK";
                    var4_3 = "\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000fwQ\u0004\u001fMj\u000f\b}G\u0012\"U-\tK".length();
                    var1_4 = 26;
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
                    var5[var3_1++] = Debugger.c(Debugger.a(v1));
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
        Debugger.j = var5;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String c(char[] cArray) {
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
                    case 0 -> 57;
                    case 1 -> 34;
                    case 2 -> 112;
                    case 3 -> 87;
                    case 4 -> 50;
                    case 5 -> 74;
                    default -> 108;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6C);
        }
        return cArray;
    }

    @EventTarget
    public void a(DisconnectEvent disconnectEvent) {
        HashSet<String> hashSet = new HashSet<String>();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        if (threadMXBean == null) {
            return;
        }
        ThreadInfo[] threadInfoArray = threadMXBean.dumpAllThreads(false, false);
        int n2 = 0;
        for (ThreadInfo threadInfo : threadInfoArray) {
            String string = threadInfo.getThreadName();
            StackTraceElement[] stackTraceElementArray = threadInfo.getStackTrace();
            if (string == null || stackTraceElementArray == null) continue;
            for (StackTraceElement stackTraceElement : stackTraceElementArray) {
                String string2 = stackTraceElement.getClassName();
                String string3 = stackTraceElement.getFileName();
                String string4 = stackTraceElement.getModuleName();
                if (string3 != null || string4 != null) continue;
                hashSet.add(string2);
                ++n2;
            }
        }
        String[] stringArray = j;
        ChatUtil.print("N: " + n2 + ", Set: ");
        ChatUtil.print("==========================");
        for (String string : hashSet) {
            ChatUtil.print(string);
        }
        ChatUtil.print("==========================");
    }

    public Debugger() {
        super("Debugger", Category.WORLD);
    }
}

