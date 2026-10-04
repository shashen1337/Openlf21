/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

import java.util.ArrayDeque;
import java.util.Queue;
import lf21.ClientBase;
import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.TickEvent;
import lombok.Generated;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundKeepAlivePacket;
import org.apache.commons.lang3.tuple.Pair;

public class LagManager {
    public static LagManager a;
    private long b = 500L;
    private boolean c;
    private int d = 0;
    private final Queue<Pair<Packet<?>, Long>> e = new ArrayDeque();

    public LagManager() {
        a = this;
    }

    public void a(boolean bl) {
        boolean bl2;
        if (bl) {
            ++this.d;
        } else if (this.d > 0) {
            --this.d;
        }
        boolean bl3 = bl2 = this.d > 0;
        if (this.c == bl2) {
            return;
        }
        this.c = bl2;
        if (this.c) {
            this.e.clear();
        } else {
            this.a();
        }
    }

    private void a() {
        if (ClientBase.mc.getConnection() == null) {
            this.e.clear();
            return;
        }
        while (!this.e.isEmpty()) {
            ClientBase.mc.getConnection().send((Packet)this.e.poll().getLeft());
        }
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        if (!this.c || ClientBase.mc.getConnection() == null || !packetEvent.b()) {
            return;
        }
        if (packetEvent.c() instanceof ServerboundKeepAlivePacket) {
            this.e.add(Pair.of(packetEvent.c(), (Object)System.currentTimeMillis()));
            packetEvent.a(true);
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (!this.c || ClientBase.mc.getConnection() == null) {
            return;
        }
        long l2 = System.currentTimeMillis();
        while (!this.e.isEmpty() && l2 - (Long)this.e.peek().getRight() >= this.b) {
            ClientBase.mc.getConnection().send((Packet)this.e.poll().getLeft());
        }
    }

    @Generated
    public long b() {
        return this.b;
    }

    @Generated
    public void a(long l2) {
        this.b = l2;
    }

    @Generated
    public boolean c() {
        return this.c;
    }
}

