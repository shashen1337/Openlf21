/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

public final class HudElement$InternalPartType
extends Enum<HudElement$InternalPartType> {
    private static final HudElement$InternalPartType[] a;
    public static final /* enum */ HudElement$InternalPartType TEXT;
    public static final /* enum */ HudElement$InternalPartType PROGRESS;

    public static HudElement$InternalPartType[] a() {
        return (HudElement$InternalPartType[])a.clone();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "z?rKY5/y\u0004~(eX";
        int n3 = "z?rKY5/y\u0004~(eX".length();
        int n4 = 8;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = HudElement$InternalPartType.a(HudElement$InternalPartType.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                TEXT = new HudElement$InternalPartType();
                PROGRESS = new HudElement$InternalPartType();
                a = HudElement$InternalPartType.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public static HudElement$InternalPartType a(String string) {
        return Enum.valueOf(HudElement$InternalPartType.class, string);
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
                    case 0 -> 42;
                    case 1 -> 109;
                    case 2 -> 61;
                    case 3 -> 12;
                    case 4 -> 11;
                    case 5 -> 112;
                    default -> 124;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x7C);
        }
        return cArray;
    }

    private static HudElement$InternalPartType[] b() {
        return new HudElement$InternalPartType[]{TEXT, PROGRESS};
    }
}

