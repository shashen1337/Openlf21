/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.LF21Client;
import lf21.patch.CallbackInfo;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Style;

@Patch(value=Screen.class)
public final class ScreenPatch {
    @Inject(method="handleComponentClicked", desc="(Lnet/minecraft/network/chat/Style;)Z", at=@At(value=At.Type.HEAD))
    public static void onHandleComponentClicked(Screen screen, Style style, CallbackInfo callbackInfo) {
        if (style == null || LF21Client.getInstance() == null || LF21Client.getInstance().getCommandManager() == null) {
            return;
        }
        ClickEvent clickEvent = style.getClickEvent();
        if (clickEvent == null || clickEvent.getAction() != ClickEvent.Action.RUN_COMMAND) {
            return;
        }
        String string = clickEvent.getValue();
        if (string == null || !string.startsWith(".")) {
            return;
        }
        if (LF21Client.getInstance().getCommandManager().executeClientCommand(string)) {
            callbackInfo.result = Boolean.TRUE;
            callbackInfo.cancel();
        }
    }
}

