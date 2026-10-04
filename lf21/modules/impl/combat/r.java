/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import lf21.modules.impl.combat.b;

class r {
    static final int[] a = new int[b.a().length];

    static {
        try {
            r.a[b.SNOWBALL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            r.a[b.GAPPLE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            r.a[b.BLOCKS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

