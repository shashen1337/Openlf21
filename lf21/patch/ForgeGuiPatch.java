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
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;

@Patch(value=ForgeGui.class)
public final class ForgeGuiPatch {
    @Inject(method="pre", desc="(Lnet/minecraftforge/client/gui/overlay/NamedGuiOverlay;Lnet/minecraft/client/gui/GuiGraphics;)Z", at=@At(value=At.Type.HEAD))
    public static void onPreOverlay(ForgeGui forgeGui, NamedGuiOverlay namedGuiOverlay, GuiGraphics guiGraphics, CallbackInfo callbackInfo) {
        if (ScoreBoard.shouldHideVanilla() && namedGuiOverlay != null && VanillaGuiOverlay.SCOREBOARD.id().equals((Object)namedGuiOverlay.id())) {
            callbackInfo.result = true;
            callbackInfo.cancel();
        }
    }

    @Inject(method="lambda$render$0", desc="(Lnet/minecraft/client/gui/GuiGraphics;FLnet/minecraftforge/client/gui/overlay/NamedGuiOverlay;)V", at=@At(value=At.Type.HEAD))
    public static void onRenderOverlay(ForgeGui forgeGui, GuiGraphics guiGraphics, float f2, NamedGuiOverlay namedGuiOverlay, CallbackInfo callbackInfo) {
        if (ScoreBoard.shouldHideVanilla() && namedGuiOverlay != null && VanillaGuiOverlay.SCOREBOARD.id().equals((Object)namedGuiOverlay.id())) {
            callbackInfo.cancel();
        }
    }
}

