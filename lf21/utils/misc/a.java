/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class a {
    private static final String a = lf21.utils.misc.a.a(lf21.utils.misc.a.b("\u0014\u0010?[\u00135U\u000b\u0019x\u0018^\"C"));

    private a() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static InputStream a(String string) {
        String string4;
        File file;
        InputStream inputStream = a.class.getResourceAsStream(string);
        if (inputStream != null) {
            return inputStream;
        }
        String string3 = System.getProperty(a);
        if (string3 != null && (file = new File(string3, string4 = string.startsWith("/") ? string.substring(1) : string)).isFile()) {
            try {
                return new FileInputStream(file);
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        return null;
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x30);
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
                    case 0 -> 120;
                    case 1 -> 118;
                    case 2 -> 13;
                    case 3 -> 106;
                    case 4 -> 61;
                    case 5 -> 71;
                    default -> 48;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

