/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import net.minecraft.world.entity.Entity;

public record EntityRemoveEvent(boolean a, Entity b) implements EventMarker
{
}

