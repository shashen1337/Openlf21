/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.impl.b;
import lf21.render.ad;
import lf21.utils.misc.ReflectionUtil;
import lombok.Generated;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.prediction.BlockStatePredictionHandler;
import net.minecraft.client.multiplayer.prediction.PredictiveAction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ClientboundDisconnectPacket;
import net.minecraft.network.protocol.game.ClientboundForgetLevelChunkPacket;
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacketData;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerChatPacket;
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.login.ClientboundCustomQueryPacket;
import net.minecraft.network.protocol.login.ClientboundGameProfilePacket;
import net.minecraft.network.protocol.status.ClientboundPongResponsePacket;

public final class PacketUtil
extends ClientBase {
    public static final ArrayList<Packet<ServerGamePacketListener>> a = new ArrayList();

    public static boolean shouldBypass(Packet<ServerGamePacketListener> packet) {
        b b2 = new b(packet);
        LF21Client.getInstance().getEventBus().call(b2);
        if (b2.isCancelled()) {
            return true;
        }
        if (a.contains(packet)) {
            a.remove(packet);
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(PredictiveAction predictiveAction) {
        if (mc.getConnection() == null) return;
        if (PacketUtil.mc.level == null) {
            return;
        }
        try {
            String string = ReflectionUtil.getMappedFieldName(ClientLevel.class, "blockStatePredictionHandler");
            if (string == null) {
                return;
            }
            Field field = ClientLevel.class.getDeclaredField(ad.c(ClientLevel.class, string));
            field.setAccessible(true);
            BlockStatePredictionHandler blockStatePredictionHandler = (BlockStatePredictionHandler)field.get(PacketUtil.mc.level);
            try (BlockStatePredictionHandler blockStatePredictionHandler2 = blockStatePredictionHandler.startPredicting();){
                int n2 = blockStatePredictionHandler2.currentSequence();
                mc.getConnection().send(predictiveAction.predict(n2));
                return;
            }
        }
        catch (Exception exception) {
            logger.error((Object)exception);
            exception.printStackTrace();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void b(PredictiveAction predictiveAction) {
        if (mc.getConnection() == null) return;
        if (PacketUtil.mc.level == null) {
            return;
        }
        try {
            String string = ReflectionUtil.getMappedFieldName(ClientLevel.class, "blockStatePredictionHandler");
            if (string == null) {
                return;
            }
            Field field = ClientLevel.class.getDeclaredField(ad.c(ClientLevel.class, string));
            field.setAccessible(true);
            BlockStatePredictionHandler blockStatePredictionHandler = (BlockStatePredictionHandler)field.get(PacketUtil.mc.level);
            try (BlockStatePredictionHandler blockStatePredictionHandler2 = blockStatePredictionHandler.startPredicting();){
                int n2 = blockStatePredictionHandler2.currentSequence();
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)predictiveAction.predict(n2));
                return;
            }
        }
        catch (Exception exception) {
            logger.error((Object)exception);
            exception.printStackTrace();
        }
    }

    public static boolean a(Packet<?> packet) {
        if (PacketUtil.mc.player == null) {
            return true;
        }
        if (PacketUtil.mc.screen instanceof ReceivingLevelScreen) {
            return true;
        }
        if (packet instanceof ClientboundCustomQueryPacket) {
            return true;
        }
        if (packet instanceof ClientboundTabListPacket) {
            return true;
        }
        if (packet instanceof ClientboundDisconnectPacket) {
            return true;
        }
        if (packet instanceof ClientboundLevelChunkPacketData) {
            return true;
        }
        if (packet instanceof ClientboundForgetLevelChunkPacket) {
            return true;
        }
        if (packet instanceof ClientboundPongResponsePacket) {
            return true;
        }
        if (packet instanceof ClientboundLoginPacket) {
            return true;
        }
        if (packet instanceof ClientboundGameProfilePacket) {
            return true;
        }
        if (packet instanceof ClientboundMapItemDataPacket) {
            return true;
        }
        if (packet instanceof ClientboundSetDefaultSpawnPositionPacket) {
            return true;
        }
        if (packet instanceof ClientboundSetEntityMotionPacket) {
            return true;
        }
        if (packet instanceof ClientboundCustomPayloadPacket) {
            return true;
        }
        if (packet instanceof ClientboundPlayerChatPacket) {
            return true;
        }
        if (packet instanceof ClientboundSetTitleTextPacket) {
            return true;
        }
        boolean bl = PacketUtil.mc.player.tickCount <= 60;
        return bl;
    }

    public static void sendQueued(Packet<ServerGamePacketListener> packet) {
        if (PacketUtil.mc.player == null) {
            return;
        }
        a.add(packet);
        PacketUtil.mc.player.connection.send(packet);
    }

    public static void b(Packet<ServerGamePacketListener> packet) {
        if (PacketUtil.mc.player == null) {
            return;
        }
        PacketUtil.mc.player.connection.send(packet);
    }

    @Generated
    private PacketUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

