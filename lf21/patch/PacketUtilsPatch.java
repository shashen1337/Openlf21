/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.LF21Client;
import lf21.network.NativeProtocolCompatibility;
import lf21.network.PacketHandlerUtil;
import lf21.patch.CallbackInfo;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.server.RunningOnDifferentThreadException;
import net.minecraft.util.thread.BlockableEventLoop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Patch(value=PacketUtils.class)
public class PacketUtilsPatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacketUtils.class);

    @Inject(method="ensureRunningOnSameThread", desc="(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/util/thread/BlockableEventLoop;)V", at=@At(value=At.Type.HEAD))
    public static <T extends PacketListener> void onEnsureRunningOnSameThread(Packet<T> packet, T t2, BlockableEventLoop<?> blockableEventLoop, CallbackInfo callbackInfo) throws RunningOnDifferentThreadException {
        if (NativeProtocolCompatibility.isProtectedPacket(packet)) {
            return;
        }
        if (LF21Client.isReady()) {
            callbackInfo.cancel();
            PacketHandlerUtil.processPacket(LOGGER, packet, t2, blockableEventLoop);
        }
    }
}

