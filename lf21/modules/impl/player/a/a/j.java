/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

import lf21.modules.impl.player.a.a.b;

class j {
    static final int[] a = new int[b.a().length];

    static {
        try {
            j.a[b.WATER.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            j.a[b.WATER_SUPPORT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            j.a[b.NONE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

