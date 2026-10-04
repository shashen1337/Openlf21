/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.Transform;
import java.util.List;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.impl.ChatReceiveEvent;
import lf21.event.impl.ChatReceiveEvent$MessageType;
import lf21.hud.TabListInfo;
import lf21.patch.CallbackInfo;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

@Patch(value=PlayerTabOverlay.class)
public class PlayerTabOverlayPatch
extends ClientBase {
    public static List<FormattedCharSequence> hookHeader(Font font, FormattedText formattedText, int n2) {
        Component component = (Component)formattedText;
        ChatReceiveEvent chatReceiveEvent = new ChatReceiveEvent(ChatReceiveEvent$MessageType.SYSTEM, component);
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(chatReceiveEvent);
        }
        return font.split((FormattedText)chatReceiveEvent.getComponent(), n2);
    }

    public static List<FormattedCharSequence> hookFooter(Font font, FormattedText formattedText, int n2) {
        Component component = (Component)formattedText;
        ChatReceiveEvent chatReceiveEvent = new ChatReceiveEvent(ChatReceiveEvent$MessageType.CHAT, component);
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(chatReceiveEvent);
        }
        return font.split((FormattedText)chatReceiveEvent.getComponent(), n2);
    }

    public static Component hookName(PlayerTabOverlay playerTabOverlay, PlayerInfo playerInfo) {
        Component component = playerTabOverlay.getNameForDisplay(playerInfo);
        ChatReceiveEvent chatReceiveEvent = new ChatReceiveEvent(ChatReceiveEvent$MessageType.NAME, component);
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(chatReceiveEvent);
        }
        return chatReceiveEvent.getComponent();
    }

    @Transform(method="render", desc="(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V")
    public static void transformRender(MethodNode methodNode) {
        InsnList insnList = methodNode.instructions;
        int n2 = 0;
        for (AbstractInsnNode abstractInsnNode : insnList) {
            String string;
            if (!(abstractInsnNode instanceof MethodInsnNode)) continue;
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            if (methodInsnNode.owner.equals("net/minecraft/client/gui/Font") && methodInsnNode.name.equals(ReflectionUtil.getMappedFieldName(Font.class, "split")) && methodInsnNode.desc.equals("(Lnet/minecraft/network/chat/FormattedText;I)Ljava/util/List;")) {
                String string2 = ++n2 == 1 ? "hookHeader" : (string = n2 == 2 ? "hookFooter" : null);
                if (string == null) continue;
                MethodInsnNode methodInsnNode2 = new MethodInsnNode(184, Type.getInternalName(PlayerTabOverlayPatch.class), string, "(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;I)Ljava/util/List;", false);
                insnList.set((AbstractInsnNode)methodInsnNode, (AbstractInsnNode)methodInsnNode2);
            }
            if (!methodInsnNode.owner.equals("net/minecraft/client/gui/components/PlayerTabOverlay") || !methodInsnNode.name.equals("getNameForDisplay") || !methodInsnNode.desc.equals("(Lnet/minecraft/client/multiplayer/PlayerInfo;)Lnet/minecraft/network/chat/Component;")) continue;
            string = new MethodInsnNode(184, Type.getInternalName(PlayerTabOverlayPatch.class), "hookName", "(Lnet/minecraft/client/gui/components/PlayerTabOverlay;Lnet/minecraft/client/multiplayer/PlayerInfo;)Lnet/minecraft/network/chat/Component;", false);
            insnList.set((AbstractInsnNode)methodInsnNode, (AbstractInsnNode)string);
        }
    }

    @Inject(method="render", desc="(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V", at=@At(value=At.Type.HEAD))
    public static void onRenderPre(PlayerTabOverlay playerTabOverlay, GuiGraphics guiGraphics, int n2, Scoreboard scoreboard, Objective objective, CallbackInfo callbackInfo) {
        try {
            TabListInfo.header = (Component)ReflectionUtil.getStaticField(playerTabOverlay, "header", "net/minecraft/client/gui/components/PlayerTabOverlay");
            TabListInfo.footer = (Component)ReflectionUtil.getStaticField(playerTabOverlay, "footer", "net/minecraft/client/gui/components/PlayerTabOverlay");
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Inject(method="render", desc="(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V", at=@At(value=At.Type.TAIL))
    public static void onRenderPost(PlayerTabOverlay playerTabOverlay, GuiGraphics guiGraphics, int n2, Scoreboard scoreboard, Objective objective, CallbackInfo callbackInfo) {
    }
}

