/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import lf21.modules.impl.world.a;

class h {
    static final int[] a = new int[lf21.modules.impl.world.a.a().length];

    static {
        try {
            h.a[lf21.modules.impl.world.a.SETUP.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            h.a[lf21.modules.impl.world.a.TUNING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            h.a[lf21.modules.impl.world.a.VERIFY.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            h.a[lf21.modules.impl.world.a.PLAYING.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

