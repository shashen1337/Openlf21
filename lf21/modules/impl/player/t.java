/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import lf21.modules.impl.player.b_0;

class t {
    static final int[] a = new int[b_0.a().length];

    static {
        try {
            t.a[b_0.IDLE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            t.a[b_0.PLACING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            t.a[b_0.RECYCLING.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

