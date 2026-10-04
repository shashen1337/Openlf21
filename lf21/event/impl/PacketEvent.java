/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.Event;
import lombok.Generated;
import net.minecraft.network.protocol.Packet;

public class PacketEvent
extends Event {
    private Packet<?> b;
    private boolean c;

    public boolean a() {
        return !this.c;
    }

    @Generated
    public boolean b() {
        return this.c;
    }

    @Generated
    public PacketEvent(Packet<?> packet, boolean bl) {
        this.b = packet;
        this.c = bl;
    }

    @Generated
    public Packet<?> c() {
        return this.b;
    }

    @Generated
    public void a(Packet<?> packet) {
        this.b = packet;
    }

    @Generated
    public void b(boolean bl) {
        this.c = bl;
    }
}

