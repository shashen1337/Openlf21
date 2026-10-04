/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import lf21.render.E;

public final class B {
    public static E a(float f2, float f3, float f4, float f5, int[] nArray, float[] fArray, Object object) {
        int n2 = nArray.length > 0 ? nArray[0] : 0;
        int n3 = nArray.length > 1 ? nArray[nArray.length - 1] : n2;
        return new E(f2, f3, f4, f5, n2, n3);
    }

    public static E a(float f2, float f3, float f4, float f5, int[] nArray) {
        return B.a(f2, f3, f4, f5, nArray, null, null);
    }
}

