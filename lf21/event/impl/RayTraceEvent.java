/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lombok.Generated;
import net.minecraft.world.entity.Entity;

public class RayTraceEvent
implements EventMarker {
    public Entity a;
    public float b;
    public float c;
    private static final String[] d;

    @Generated
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof RayTraceEvent)) {
            return false;
        }
        RayTraceEvent rayTraceEvent = (RayTraceEvent)object;
        if (!rayTraceEvent.a(this)) {
            return false;
        }
        if (Float.compare(this.getYaw(), rayTraceEvent.getYaw()) != 0) {
            return false;
        }
        if (Float.compare(this.getPitch(), rayTraceEvent.getPitch()) != 0) {
            return false;
        }
        Entity entity = this.a();
        Entity entity2 = rayTraceEvent.a();
        return !(entity == null ? entity2 != null : !entity.equals(entity2));
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof RayTraceEvent;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + Float.floatToIntBits(this.getYaw());
        n3 = n3 * 59 + Float.floatToIntBits(this.getPitch());
        Entity entity = this.a();
        n3 = n3 * 59 + (entity == null ? 43 : entity.hashCode());
        return n3;
    }

    @Generated
    public String toString() {
        float f2 = this.getPitch();
        float f3 = this.getYaw();
        String string = String.valueOf(this.a());
        String[] stringArray = d;
        return "RayTraceEvent(entity=" + string + ", yaw=" + f3 + ", pitch=" + f2 + ")";
    }

    @Generated
    public RayTraceEvent(Entity entity, float f2, float f3) {
        this.a = entity;
        this.b = f2;
        this.c = f3;
    }

    @Generated
    public Entity a() {
        return this.a;
    }

    @Generated
    public void a(Entity entity) {
        this.a = entity;
    }

    @Generated
    public float getYaw() {
        return this.b;
    }

    @Generated
    public void a(float f2) {
        this.b = f2;
    }

    @Generated
    public float getPitch() {
        return this.c;
    }

    @Generated
    public void b(float f2) {
        this.c = f2;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "=}JI\nC\b=}CA\t\u001db,\u0015C<J|\u000f\u001fit\u0018EM\u0013\n\"t3GA\t\u00077";
        int n3 = "=}JI\nC\b=}CA\t\u001db,\u0015C<J|\u000f\u001fit\u0018EM\u0013\n\"t3GA\t\u00077".length();
        int n4 = 6;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = RayTraceEvent.a(RayTraceEvent.a(string.substring(n6, n6 + n4)));
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
            cArray[0] = (char)(cArray[0] ^ 0xA);
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
                    case 0 -> 17;
                    case 1 -> 93;
                    case 2 -> 51;
                    case 3 -> 40;
                    case 4 -> 125;
                    case 5 -> 126;
                    default -> 10;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

