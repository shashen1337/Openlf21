/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import net.minecraft.network.protocol.Packet;

public final class t {
    public final Packet<?> a;
    public final long b;

    public t(Packet<?> packet) {
        this.a = packet;
        this.b = System.currentTimeMillis();
    }
}

