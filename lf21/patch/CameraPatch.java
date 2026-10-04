/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.LF21Client;
import lf21.modules.impl.render.Freecam;
import lf21.modules.impl.render.NoCameraClip;
import lf21.patch.CallbackInfo;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.FogType;

@Patch(value=Camera.class)
public final class CameraPatch {
    @Inject(method="setup", desc="(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/world/entity/Entity;ZZF)V", at=@At(value=At.Type.TAIL))
    public static void onSetup(Camera camera, BlockGetter blockGetter, Entity entity, boolean bl, boolean bl2, float f2, CallbackInfo callbackInfo) {
        if (LF21Client.isReady()) {
            Freecam.applyCameraTransform(camera, entity, f2);
        }
    }

    @Inject(method="getMaxZoom", desc="(D)D", at=@At(value=At.Type.HEAD))
    public static void onGetMaxZoom(Camera camera, double d2, CallbackInfo callbackInfo) {
        if (LF21Client.isReady() && NoCameraClip.INSTANCE != null && NoCameraClip.INSTANCE.isEnabled()) {
            callbackInfo.result = d2;
            callbackInfo.cancel();
        }
    }

    @Inject(method="getFluidInCamera", desc="()Lnet/minecraft/world/level/material/FogType;", at=@At(value=At.Type.HEAD))
    public static void onGetFluidInCamera(Camera camera, CallbackInfo callbackInfo) {
        if (LF21Client.isReady() && Freecam.shouldHideSubmersionFog()) {
            callbackInfo.result = FogType.NONE;
            callbackInfo.cancel();
        }
    }
}

