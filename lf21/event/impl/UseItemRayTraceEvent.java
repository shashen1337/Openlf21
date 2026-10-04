/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lombok.Generated;

public class UseItemRayTraceEvent
implements EventMarker {
    private float a;
    private float b;
    private static final String[] c;

    @Generated
    public UseItemRayTraceEvent(float f2, float f3) {
        this.a = f2;
        this.b = f3;
    }

    @Generated
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof UseItemRayTraceEvent)) {
            return false;
        }
        UseItemRayTraceEvent useItemRayTraceEvent = (UseItemRayTraceEvent)object;
        if (!useItemRayTraceEvent.a(this)) {
            return false;
        }
        if (Float.compare(this.getYaw(), useItemRayTraceEvent.getYaw()) != 0) {
            return false;
        }
        return Float.compare(this.getPitch(), useItemRayTraceEvent.getPitch()) == 0;
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof UseItemRayTraceEvent;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + Float.floatToIntBits(this.getYaw());
        n3 = n3 * 59 + Float.floatToIntBits(this.getPitch());
        return n3;
    }

    @Generated
    public String toString() {
        String[] stringArray = c;
        return "UseItemRayTraceEvent(yaw=" + this.getYaw() + ", pitch=" + this.getPitch() + ")";
    }

    @Generated
    public float getYaw() {
        return this.a;
    }

    @Generated
    public void a(float f2) {
        this.a = f2;
    }

    @Generated
    public float getPitch() {
        return this.b;
    }

    @Generated
    public void b(float f2) {
        this.b = f2;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "~l\u0007J$%\u0003o\u0019\u0007?\u0012j$#\u0006\u0000-\u000ew\"'\b7\t\u0001F>2C+-\u0000\u001e";
        int n3 = "~l\u0007J$%\u0003o\u0019\u0007?\u0012j$#\u0006\u0000-\u000ew\"'\b7\t\u0001F>2C+-\u0000\u001e".length();
        int n4 = 8;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = UseItemRayTraceEvent.a(UseItemRayTraceEvent.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                c = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6B);
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
                    case 0 -> 82;
                    case 1 -> 76;
                    case 2 -> 119;
                    case 3 -> 35;
                    case 4 -> 80;
                    case 5 -> 70;
                    default -> 107;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

