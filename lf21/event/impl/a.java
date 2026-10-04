/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import java.util.Objects;
import lf21.event.Event;
import lombok.Generated;

public class a
extends Event {
    private final String b;
    private static final String c;
    private static final String[] d;

    @Generated
    public boolean equals(Object object) {
        String string;
        if (object == this) {
            return true;
        }
        if (!(object instanceof a)) {
            return false;
        }
        a a2 = (a)object;
        if (!a2.a(this)) {
            return false;
        }
        String string2 = this.a();
        return Objects.equals(string2, string = a2.a());
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof a;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        String string = this.a();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        return n3;
    }

    @Generated
    public String toString() {
        return "ChatEvent(message=" + this.a() + ")";
    }

    @Generated
    public a(String string) {
        this.b = string;
    }

    @Generated
    public String a() {
        return this.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "O\b\u0007;%L`b\u0014N\"\u0005Ivm\u0007\u0003r\u0012O\b\u0007;%L`b\u0014N\"\u0005Ivm\u0007\u0003r";
        int n3 = "O\b\u0007;%L`b\u0014N\"\u0005Ivm\u0007\u0003r\u0012O\b\u0007;%L`b\u0014N\"\u0005Ivm\u0007\u0003r".length();
        int n4 = 18;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.event.impl.a.a(lf21.event.impl.a.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                d = stringArray;
                c = d[1];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 5);
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
                    case 0 -> 12;
                    case 1 -> 96;
                    case 2 -> 102;
                    case 3 -> 79;
                    case 4 -> 96;
                    case 5 -> 58;
                    default -> 5;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

