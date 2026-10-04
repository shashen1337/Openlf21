/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class i
implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger();
    private static final String b = i.a(i.a("]4\u0018tZz/t\u0011_1\u0018Mz"));

    private i() {
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, b + this.a.getAndIncrement());
        thread.setDaemon(true);
        return thread;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x57);
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
                    case 0 -> 17;
                    case 1 -> 114;
                    case 2 -> 42;
                    case 3 -> 69;
                    case 4 -> 119;
                    case 5 -> 63;
                    default -> 87;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

