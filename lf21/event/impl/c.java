/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.Event;
import lombok.Generated;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;

public class c
extends Event {
    private final Packet<ClientGamePacketListener> b;

    @Generated
    public c(Packet<ClientGamePacketListener> packet) {
        this.b = packet;
    }

    @Generated
    public Packet<ClientGamePacketListener> a() {
        return this.b;
    }
}

