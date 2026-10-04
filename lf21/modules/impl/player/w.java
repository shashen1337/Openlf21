/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import net.minecraft.world.entity.EquipmentSlot;

class w {
    static final int[] a = new int[EquipmentSlot.values().length];

    static {
        try {
            w.a[EquipmentSlot.HEAD.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            w.a[EquipmentSlot.CHEST.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            w.a[EquipmentSlot.LEGS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            w.a[EquipmentSlot.FEET.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

