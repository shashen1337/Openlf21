/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class f {
    private static final Logger a;
    private static final String b;

    public static Optional<byte[]> a(String string) {
        Optional<byte[]> optional;
        InputStream inputStream = f.b(string);
        try {
            optional = Optional.of(inputStream.readAllBytes());
        }
        catch (Throwable throwable) {
            try {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                }
                throw throwable;
            }
            catch (IOException iOException) {
                a.log(Level.SEVERE, b + string, iOException);
                return Optional.empty();
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return optional;
    }

    public static InputStream b(String string) throws FileNotFoundException {
        return new FileInputStream(string);
    }

    public static InputStream c(String string) throws IOException {
        Path path = Paths.get(string, new String[0]);
        return Files.newInputStream(path, new OpenOption[0]);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string = f.a(f.d("jnuGA\u0004bX`<YA\u0001&\fme_A\u0013bJ}sF\u0004\u0012'_`iYG\u0005x\f"));
        b = string;
        a = Logger.getLogger(f.class.getName());
    }

    private static char[] d(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x42);
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
                    case 0 -> 44;
                    case 1 -> 15;
                    case 2 -> 28;
                    case 3 -> 43;
                    case 4 -> 36;
                    case 5 -> 96;
                    default -> 66;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

