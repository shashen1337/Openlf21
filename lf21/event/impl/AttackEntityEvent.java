/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.Event;
import net.minecraft.world.entity.Entity;

public final class AttackEntityEvent
extends Event {
    private final Entity b;

    public AttackEntityEvent(Entity entity) {
        this.b = entity;
    }

    public Entity a() {
        return this.b;
    }
}

