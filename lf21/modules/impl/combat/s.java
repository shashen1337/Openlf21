/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

class s {
    static final int[] a = new int[AttributeModifier.Operation.values().length];

    static {
        try {
            s.a[AttributeModifier.Operation.ADDITION.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            s.a[AttributeModifier.Operation.MULTIPLY_BASE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            s.a[AttributeModifier.Operation.MULTIPLY_TOTAL.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

