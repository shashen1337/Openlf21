/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.modules.impl.render.XRay;
import lf21.patch.CallbackInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@Patch(value=Block.class)
public class BlockPatch {
    @Inject(method="shouldRenderFace", desc="(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;)Z", at=@At(value=At.Type.HEAD))
    public static void onShouldRenderFace(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction, BlockPos blockPos2, CallbackInfo callbackInfo) {
        XRay xRay = XRay.INSTANCE;
        if (xRay == null || !xRay.isEnabled()) {
            return;
        }
        callbackInfo.result = xRay.isXrayVisible(blockState.getBlock()) ? Boolean.TRUE : Boolean.FALSE;
        callbackInfo.cancel();
    }
}

