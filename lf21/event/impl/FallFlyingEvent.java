/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lombok.Generated;

public class FallFlyingEvent
implements EventMarker {
    private float a;
    private static final String b = FallFlyingEvent.a(FallFlyingEvent.a("Y;Ie|^$v4BLLW3krU`NQ5\""));

    @Generated
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof FallFlyingEvent)) {
            return false;
        }
        FallFlyingEvent fallFlyingEvent = (FallFlyingEvent)object;
        if (!fallFlyingEvent.a(this)) {
            return false;
        }
        return Float.compare(this.getPitch(), fallFlyingEvent.getPitch()) == 0;
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof FallFlyingEvent;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + Float.floatToIntBits(this.getPitch());
        return n3;
    }

    @Generated
    public String toString() {
        return b + this.getPitch() + ")";
    }

    @Generated
    public FallFlyingEvent(float f2) {
        this.a = f2;
    }

    @Generated
    public float getPitch() {
        return this.a;
    }

    @Generated
    public void a(float f2) {
        this.a = f2;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x5D);
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
                    case 0 -> 31;
                    case 1 -> 90;
                    case 2 -> 37;
                    case 3 -> 9;
                    case 4 -> 58;
                    case 5 -> 50;
                    default -> 93;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

