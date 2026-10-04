/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.modules.impl.render.XRay;
import lf21.patch.CallbackInfo;
import net.minecraft.world.level.block.state.BlockState;

@Patch(className="me.jellysquid.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache")
public class BlockOcclusionCachePatch {
    @Inject(method="shouldDrawSide", desc="", at=@At(value=At.Type.HEAD))
    public static void onShouldDrawSide(Object object, Object object2, Object object3, Object object4, Object object5, CallbackInfo callbackInfo) {
        XRay xRay = XRay.INSTANCE;
        if (xRay == null || !xRay.isEnabled()) {
            return;
        }
        boolean bl = xRay.isXrayVisible(((BlockState)object2).getBlock());
        callbackInfo.result = bl ? Boolean.TRUE : Boolean.FALSE;
        callbackInfo.cancel();
    }
}

