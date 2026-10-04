/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

public final class ChatReceiveEvent$MessageType
extends Enum<ChatReceiveEvent$MessageType> {
    public static final /* enum */ ChatReceiveEvent$MessageType NAME;
    public static final /* enum */ ChatReceiveEvent$MessageType CHAT;
    public static final /* enum */ ChatReceiveEvent$MessageType SYSTEM;
    private static final ChatReceiveEvent$MessageType[] a;

    public static ChatReceiveEvent$MessageType[] a() {
        return (ChatReceiveEvent$MessageType[])a.clone();
    }

    public static ChatReceiveEvent$MessageType a(String string) {
        return Enum.valueOf(ChatReceiveEvent$MessageType.class, string);
    }

    private static ChatReceiveEvent$MessageType[] b() {
        return new ChatReceiveEvent$MessageType[]{NAME, CHAT, SYSTEM};
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "Z\u001ai J\u0003\u0004G\u0002w1\u0004J\u000b{ ";
        int n3 = "Z\u001ai J\u0003\u0004G\u0002w1\u0004J\u000b{ ".length();
        int n4 = 6;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = ChatReceiveEvent$MessageType.a(ChatReceiveEvent$MessageType.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                NAME = new ChatReceiveEvent$MessageType();
                CHAT = new ChatReceiveEvent$MessageType();
                SYSTEM = new ChatReceiveEvent$MessageType();
                a = ChatReceiveEvent$MessageType.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x4A);
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
                    case 0 -> 9;
                    case 1 -> 67;
                    case 2 -> 58;
                    case 3 -> 116;
                    case 4 -> 15;
                    case 5 -> 78;
                    default -> 74;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

