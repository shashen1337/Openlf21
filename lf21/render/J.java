/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import lf21.render.j_0;

class J {
    static final int[] a = new int[j_0.a().length];

    static {
        try {
            J.a[j_0.MOVE_TO.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            J.a[j_0.LINE_TO.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            J.a[j_0.QUAD_TO.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            J.a[j_0.CUBIC_TO.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            J.a[j_0.CLOSE.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

