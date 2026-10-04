/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.Event;
import lombok.Generated;

public class KeyEvent
extends Event {
    private final int b;
    private final boolean c;
    private static final String[] d;

    @Generated
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof KeyEvent)) {
            return false;
        }
        KeyEvent keyEvent = (KeyEvent)object;
        if (!keyEvent.a(this)) {
            return false;
        }
        if (this.getKeyCode() != keyEvent.getKeyCode()) {
            return false;
        }
        return this.isPressed() == keyEvent.isPressed();
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof KeyEvent;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + this.getKeyCode();
        n3 = n3 * 59 + (this.isPressed() ? 79 : 97);
        return n3;
    }

    @Generated
    public String toString() {
        String[] stringArray = d;
        return "KeyEvent(key=" + this.getKeyCode() + ", state=" + this.isPressed() + ")";
    }

    @Generated
    public KeyEvent(int n2, boolean bl) {
        this.b = n2;
        this.c = bl;
    }

    @Generated
    public int getKeyCode() {
        return this.b;
    }

    @Generated
    public boolean isPressed() {
        return this.c;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u000f\u0016!\u0014OUE\u001e\rhS+%XDNW\u001e9\u0005W\u001c";
        int n3 = "\u000f\u0016!\u0014OUE\u001e\rhS+%XDNW\u001e9\u0005W\u001c".length();
        int n4 = 8;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = KeyEvent.a(KeyEvent.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                d = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x20);
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
                    case 0 -> 35;
                    case 1 -> 54;
                    case 2 -> 82;
                    case 3 -> 96;
                    case 4 -> 46;
                    case 5 -> 33;
                    default -> 32;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

