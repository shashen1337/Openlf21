/*
 * Decompiled with CFR 0.152.
 */
package lf21.network;

import lf21.LF21Client;
import lf21.event.impl.c;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.RunningOnDifferentThreadException;
import net.minecraft.util.thread.BlockableEventLoop;
import org.slf4j.Logger;

public class PacketHandlerUtil {
    public static <T extends PacketListener> void processPacket(Logger logger, Packet<T> packet, T t2, BlockableEventLoop<?> blockableEventLoop) throws RunningOnDifferentThreadException {
        block7: {
            block8: {
                if (!blockableEventLoop.isSameThread()) break block7;
                if (t2.isAcceptingMessages()) break block8;
                logger.debug("Ignoring packet due to disconnection: {}", packet);
                return;
            }
            c c2 = new c(packet);
            if (LF21Client.isReady()) {
                LF21Client.instance.getEventBus().call(c2);
            }
            return;
        }
        Runnable runnable = () -> {
            if (!t2.isAcceptingMessages()) {
                logger.debug("Ignoring packet due to disconnection: {}", (Object)packet);
                return;
            }
            try {
                c c2 = new c((Packet<ClientGamePacketListener>)packet);
                if (blockableEventLoop.isSameThread() && LF21Client.isReady()) {
                    LF21Client.instance.getEventBus().call(c2);
                    if (c2.isCancelled()) {
                        return;
                    }
                }
                packet.handle(t2);
                return;
            }
            catch (Exception exception) {
                if (t2.shouldPropagateHandlingExceptions()) {
                    throw exception;
                }
                logger.error("Failed to handle packet {}, suppressing error", (Object)packet, (Object)exception);
            }
        };
        blockableEventLoop.executeIfPossible(runnable);
        throw RunningOnDifferentThreadException.RUNNING_ON_DIFFERENT_THREAD;
    }
}

