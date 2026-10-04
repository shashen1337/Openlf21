/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import lf21.modules.impl.combat.c;

class v {
    static final int[] a = new int[c.a().length];

    static {
        try {
            v.a[c.DISPLACING.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            v.a[c.RETURNING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            v.a[c.OUTWARD_SENT_WAIT.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            v.a[c.RESTORE_SENT_WAIT.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

