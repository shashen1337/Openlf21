/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.Event;
import lombok.Generated;
import net.minecraft.network.protocol.Packet;

public class PrePacketEvent
extends Event {
    private final Packet<?> b;

    @Generated
    public PrePacketEvent(Packet<?> packet) {
        this.b = packet;
    }

    @Generated
    public Packet<?> getPacket() {
        return this.b;
    }
}

