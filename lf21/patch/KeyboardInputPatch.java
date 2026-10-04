/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import java.util.concurrent.ThreadLocalRandom;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.impl.StrafeEvent;
import lf21.modules.impl.render.Freecam;
import lf21.patch.CallbackInfo;
import lf21.utils.misc.PacketUtil;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.entity.Entity;

@Patch(value=KeyboardInput.class)
public class KeyboardInputPatch
extends Input {
    private static boolean previousUp;
    private static boolean previousDown;
    private static boolean previousLeft;
    private static boolean previousRight;
    private static boolean failedDown;
    private static boolean failedLeft;
    private static boolean failedRight;

    @Inject(method="tick", desc="(ZF)V", at=@At(value=At.Type.TAIL))
    public static void onTick(KeyboardInput keyboardInput, boolean bl, float f2, CallbackInfo callbackInfo) {
        float f3 = keyboardInput.up == keyboardInput.down ? 0.0f : (keyboardInput.forwardImpulse = keyboardInput.up ? 1.0f : -1.0f);
        keyboardInput.leftImpulse = keyboardInput.left == keyboardInput.right ? 0.0f : (keyboardInput.left ? 1.0f : -1.0f);
        StrafeEvent strafeEvent = new StrafeEvent(keyboardInput.forwardImpulse, keyboardInput.leftImpulse, keyboardInput.jumping);
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(strafeEvent);
        }
        double d2 = 0.3;
        keyboardInput.forwardImpulse = strafeEvent.getForward();
        keyboardInput.leftImpulse = strafeEvent.getStrafe();
        keyboardInput.jumping = strafeEvent.isSprinting();
        if (bl) {
            keyboardInput.leftImpulse = (float)((double)keyboardInput.leftImpulse * d2);
            keyboardInput.forwardImpulse = (float)((double)keyboardInput.forwardImpulse * d2);
        }
        failedDown = KeyboardInputPatch.updateFailureState(keyboardInput.down, previousDown, failedDown);
        failedLeft = KeyboardInputPatch.updateFailureState(keyboardInput.left, previousLeft, failedLeft);
        failedRight = KeyboardInputPatch.updateFailureState(keyboardInput.right, previousRight, failedRight);
        boolean bl2 = failedDown;
        boolean bl3 = failedLeft;
        boolean bl4 = failedRight;
        previousUp = keyboardInput.up;
        previousDown = keyboardInput.down;
        previousLeft = keyboardInput.left;
        previousRight = keyboardInput.right;
        if (bl2) {
            KeyboardInputPatch.sendMovementCorrection(0.0f, -1.0f);
        }
        if (bl3) {
            KeyboardInputPatch.sendMovementCorrection(-1.0f, 0.0f);
        }
        if (bl4) {
            KeyboardInputPatch.sendMovementCorrection(1.0f, 0.0f);
        }
        if (Freecam.shouldSuppressPlayerInput()) {
            keyboardInput.forwardImpulse = 0.0f;
            keyboardInput.leftImpulse = 0.0f;
            keyboardInput.jumping = false;
            keyboardInput.shiftKeyDown = Freecam.shouldKeepSneaking();
            keyboardInput.up = false;
            keyboardInput.down = false;
            keyboardInput.left = false;
            keyboardInput.right = false;
        }
    }

    private static boolean updateFailureState(boolean bl, boolean bl2, boolean bl3) {
        if (!bl) {
            return false;
        }
        if (bl3) {
            return true;
        }
        return !bl2 && ThreadLocalRandom.current().nextInt(100) < 25;
    }

    private static void sendMovementCorrection(float f2, float f3) {
        if (!LF21Client.isReady() || ClientBase.mc == null || ClientBase.mc.player == null || ClientBase.mc.getConnection() == null) {
            return;
        }
        float f4 = ClientBase.mc.player.getYRot() * ((float)Math.PI / 180);
        double d2 = (-Math.sin(f4) * (double)f2 + Math.cos(f4) * (double)f3) * 0.2;
        double d3 = (Math.cos(f4) * (double)f2 + Math.sin(f4) * (double)f3) * 0.2;
        float f5 = ClientBase.mc.player.getYRot() + 180.0f;
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.PosRot(ClientBase.mc.player.getX() + d2, ClientBase.mc.player.getY(), ClientBase.mc.player.getZ() + d3, f5, ClientBase.mc.player.getXRot(), ClientBase.mc.player.onGround()));
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerCommandPacket((Entity)ClientBase.mc.player, ServerboundPlayerCommandPacket.Action.START_SPRINTING));
    }
}

