/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.Patch;
import asm.patchify.annotation.Transform;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.PrePacketEvent;
import lf21.modules.impl.exploit.Raytrace;
import lf21.network.MatchmakingProbe;
import lf21.network.NativeProtocolCompatibility;
import lf21.utils.misc.PacketUtil;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

@Patch(value=Connection.class)
public class ConnectionPatch
extends ClientBase {
    public static boolean onPacketReceive(Packet<?> packet) {
        MatchmakingProbe.observeIncoming(packet);
        if (mc == null || ConnectionPatch.mc.level == null || ConnectionPatch.mc.player == null || packet == null || !LF21Client.isReady()) {
            return false;
        }
        if (NativeProtocolCompatibility.isProtectedPacket(packet)) {
            return false;
        }
        PrePacketEvent prePacketEvent = new PrePacketEvent(packet);
        LF21Client.getInstance().getEventBus().call(prePacketEvent);
        if (prePacketEvent.isCancelled()) {
            return true;
        }
        PacketEvent packetEvent = new PacketEvent(prePacketEvent.getPacket(), false);
        LF21Client.getInstance().getEventBus().call(packetEvent);
        return packetEvent.isCancelled();
    }

    public static boolean onPacketSend(Packet<?> packet) {
        MatchmakingProbe.observeOutgoing(packet);
        if (mc == null || ConnectionPatch.mc.level == null || ConnectionPatch.mc.player == null || packet == null || !LF21Client.isReady()) {
            return false;
        }
        if (NativeProtocolCompatibility.isProtectedPacket(packet)) {
            return false;
        }
        if (Raytrace.shouldCancelOutgoing(packet)) {
            return true;
        }
        if (PacketUtil.shouldBypass(packet)) {
            return false;
        }
        PacketEvent packetEvent = new PacketEvent(packet, true);
        LF21Client.getInstance().getEventBus().call(packetEvent);
        return packetEvent.isCancelled();
    }

    @Transform(method="channelRead0", desc="(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/protocol/Packet;)V")
    public static void transformReceive(MethodNode methodNode) {
        InsnList insnList = new InsnList();
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 2));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(ConnectionPatch.class), "onPacketReceive", "(Lnet/minecraft/network/protocol/Packet;)Z", false));
        LabelNode labelNode = new LabelNode();
        insnList.add((AbstractInsnNode)new JumpInsnNode(153, labelNode));
        insnList.add((AbstractInsnNode)new InsnNode(177));
        insnList.add((AbstractInsnNode)labelNode);
        methodNode.instructions.insert(insnList);
    }

    @Transform(method="sendPacket", desc="(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketSendListener;)V")
    public static void transformSend(MethodNode methodNode) {
        InsnList insnList = new InsnList();
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 1));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(ConnectionPatch.class), "onPacketSend", "(Lnet/minecraft/network/protocol/Packet;)Z", false));
        LabelNode labelNode = new LabelNode();
        insnList.add((AbstractInsnNode)new JumpInsnNode(153, labelNode));
        insnList.add((AbstractInsnNode)new InsnNode(177));
        insnList.add((AbstractInsnNode)labelNode);
        methodNode.instructions.insert(insnList);
    }
}

