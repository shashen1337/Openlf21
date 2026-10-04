/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.modules.impl.render.ScoreBoard;
import lf21.patch.CallbackInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;

@Patch(value=VanillaGuiOverlay.class)
public final class VanillaGuiOverlayPatch {
    @Inject(method="lambda$static$23", desc="(Lnet/minecraftforge/client/gui/overlay/ForgeGui;Lnet/minecraft/client/gui/GuiGraphics;FII)V", at=@At(value=At.Type.HEAD))
    public static void onScoreboardOverlay(ForgeGui forgeGui, GuiGraphics guiGraphics, float f2, int n2, int n3, CallbackInfo callbackInfo) {
        if (ScoreBoard.shouldHideVanilla()) {
            callbackInfo.cancel();
        }
    }
}

