/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.hud.HudElement;
import lf21.modules.Module;
import lf21.patch.CallbackInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ChatScreen;
import org.lwjgl.glfw.GLFW;

@Patch(value=ChatScreen.class)
public class ChatScreenPatch {
    @Inject(method="render", desc="(Lnet/minecraft/client/gui/GuiGraphics;IIF)V")
    public static void onRender(ChatScreen chatScreen, GuiGraphics guiGraphics, int n2, int n3, float f2, CallbackInfo callbackInfo) {
        try {
            for (HudElement hudElement : LF21Client.getInstance().getHudManager().getHudElements().stream().filter(Module::isEnabled).toList()) {
                if (!hudElement.isDragging()) continue;
                hudElement.mouseDragged(n2, n3);
                boolean bl = GLFW.glfwGetMouseButton((long)ClientBase.mc.getWindow().getWindow(), (int)0) == 1;
                if (bl) continue;
                hudElement.setDragging(false);
            }
        }
        catch (Exception exception) {
            ClientBase.logger.error((Object)exception);
            ClientBase.logger.error(exception.getMessage());
        }
    }

    @Inject(method="mouseClicked", desc="(DDI)Z")
    public static void onMouseClicked(double d2, double d3, int n2, CallbackInfo callbackInfo) {
        try {
            for (HudElement hudElement : LF21Client.getInstance().getHudManager().getHudElements().stream().filter(Module::isEnabled).toList()) {
                if (hudElement.mousePressed((int)d2, (int)d3, n2)) break;
            }
        }
        catch (Exception exception) {
            ClientBase.logger.error((Object)exception);
            ClientBase.logger.error(exception.getMessage());
        }
    }
}

