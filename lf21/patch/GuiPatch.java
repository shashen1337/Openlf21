/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.WrapInvoke;
import lf21.asm.Invocation;
import lf21.modules.impl.render.ScoreBoard;
import lf21.patch.CallbackInfo;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.scores.Objective;

@Patch(value=Gui.class)
public final class GuiPatch {
    @Inject(method="displayScoreboardSidebar", desc="(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V", at=@At(value=At.Type.HEAD))
    public static void onDisplayScoreboardSidebar(Gui gui, GuiGraphics guiGraphics, Objective objective, CallbackInfo callbackInfo) {
        if (ScoreBoard.shouldHideVanilla()) {
            callbackInfo.cancel();
        }
    }

    @WrapInvoke(method="render", desc="(Lnet/minecraft/client/gui/GuiGraphics;F)V", target="net/minecraft/client/gui/Gui/displayScoreboardSidebar", targetDesc="(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V")
    public static void guardScoreboardRender(Gui gui, GuiGraphics guiGraphics, float f2, Invocation<Gui, Void> invocation) throws Exception {
        if (!ScoreBoard.shouldHideVanilla()) {
            invocation.call();
        }
    }
}

