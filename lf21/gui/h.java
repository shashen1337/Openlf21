/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

import lf21.gui.e;

class h {
    static final int[] a = new int[e.a().length];

    static {
        try {
            h.a[e.FADING_OUT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            h.a[e.WAITING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            h.a[e.FADING_IN.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

