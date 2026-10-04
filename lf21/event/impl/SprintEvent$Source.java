/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

public final class SprintEvent$Source
extends Enum<SprintEvent$Source> {
    public static final /* enum */ SprintEvent$Source INPUT;
    private static final SprintEvent$Source[] a;

    public static SprintEvent$Source[] a() {
        return (SprintEvent$Source[])a.clone();
    }

    public static SprintEvent$Source a(String string) {
        return Enum.valueOf(SprintEvent$Source.class, string);
    }

    private static SprintEvent$Source[] b() {
        return new SprintEvent$Source[]{INPUT};
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string = SprintEvent$Source.a(SprintEvent$Source.b("{Z[GW"));
        INPUT = new SprintEvent$Source();
        a = SprintEvent$Source.b();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x48);
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
                    case 0 -> 50;
                    case 1 -> 20;
                    case 2 -> 11;
                    case 3 -> 18;
                    case 4 -> 3;
                    case 5 -> 38;
                    default -> 72;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

