/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import lombok.Generated;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.apache.commons.lang3.StringUtils;

public final class b {
    private static final String a;
    private static final String[] b;

    public static MutableComponent a(Component component, String string, String string2) {
        if (component == null) {
            return Component.empty();
        }
        String string3 = component.getString();
        if (!string3.contains(string)) {
            return component.copy();
        }
        String string4 = StringUtils.replace((String)string3, (String)string, (String)string2);
        return Component.literal((String)string4).withStyle(component.getStyle());
    }

    @Generated
    private b() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "Z\u001e\u0002y\u0015\\6.\u0017K\u007fA\\)g\u0002\u0012*VY$}\u0005Kk[Qem\u0017\u0005dZAel\u0013Kc[F1o\u0018\u001fcTA j2Z\u001e\u0002y\u0015\\6.\u0017K\u007fA\\)g\u0002\u0012*VY$}\u0005Kk[Qem\u0017\u0005dZAel\u0013Kc[F1o\u0018\u001fcTA j";
        int n3 = "Z\u001e\u0002y\u0015\\6.\u0017K\u007fA\\)g\u0002\u0012*VY$}\u0005Kk[Qem\u0017\u0005dZAel\u0013Kc[F1o\u0018\u001fcTA j2Z\u001e\u0002y\u0015\\6.\u0017K\u007fA\\)g\u0002\u0012*VY$}\u0005Kk[Qem\u0017\u0005dZAel\u0013Kc[F1o\u0018\u001fcTA j".length();
        int n4 = 50;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.utils.misc.b.a(lf21.utils.misc.b.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                b = stringArray;
                a = b[0];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x45);
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
                    case 0 -> 14;
                    case 1 -> 118;
                    case 2 -> 107;
                    case 3 -> 10;
                    case 4 -> 53;
                    case 5 -> 53;
                    default -> 69;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

