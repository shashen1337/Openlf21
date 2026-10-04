/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.PreTickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class HighJump
extends Module {
    public static HighJump j;
    public int n;
    public int l;
    public int k;
    public int o;
    public int m;

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPre()) {
            return;
        }
        if (HighJump.mc.player == null) {
            return;
        }
        LF21Client.serverTickRate = 2.0f;
        motionEvent.setPitch((float)((double)motionEvent.getPitch() + (double)((float)Math.random()) * 0.1));
        if (this.k == 0) {
            return;
        }
        HighJump.mc.player.setDeltaMovement(HighJump.mc.player.getDeltaMovement().x, (double)0.42f, HighJump.mc.player.getDeltaMovement().z);
        if (this.m == 1) {
            this.m = 2;
            this.o = 0;
        } else if (this.m == 2) {
            for (int i2 = 0; i2 < 2; ++i2) {
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.StatusOnly(false));
            }
            this.n = 1;
            this.m = 0;
        } else {
            HighJump.mc.player.setDeltaMovement(0.0, 0.0, 0.0);
            this.n = 1;
            ++this.o;
            if (this.o > 20) {
                // empty if block
            }
        }
    }

    @EventTarget
    public void a(PreTickEvent preTickEvent) {
        if (HighJump.mc.player == null) {
            return;
        }
        if (this.l == 0) {
            HighJump.mc.player.jumpFromGround();
        }
        if (this.l == 1) {
            this.k = 1;
            for (int i2 = 0; i2 < 20; ++i2) {
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.StatusOnly(false));
            }
        }
        ++this.l;
    }

    public HighJump() {
        super("HighJump", Category.MOVEMENT);
        j = this;
    }

    @Override
    public void onEnable() {
        this.l = 0;
        this.k = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        if (LF21Client.isReady()) {
            ChatUtil.print("\u4f60\u5fc5\u987b\u5728Bedwars\u5e76\u4e14\u4f60\u7684\u5ef6\u8fdf\u5fc5\u987b\u572820ms\u4ee5\u4e0b\u624d\u80fd\u4f7f\u7528\u8fd9\u4e2a\u6a21\u5757");
        }
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        if (HighJump.mc.player == null) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (packetEvent.a()) {
            ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket;
            if (packet instanceof ClientboundSetEntityMotionPacket && (clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet).getId() == HighJump.mc.player.getId() && (double)clientboundSetEntityMotionPacket.getYa() / 8000.0 < 0.0) {
                this.toggle();
            }
            if (packetEvent.c() instanceof ClientboundPlayerPositionPacket) {
                this.m = 1;
            }
        } else if (packet instanceof ServerboundMovePlayerPacket && this.n == 1) {
            this.n = 0;
            packetEvent.a(true);
        }
    }
}

