/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import lf21.ClientBase;
import lombok.Generated;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;

public final class ChatUtil
extends ClientBase {
    public static void addMessage(Component component) {
        ChatComponent chatComponent = ChatUtil.mc.gui.getChat();
        chatComponent.addMessage(component);
    }

    public static void print(String string) {
        ChatUtil.print(true, string);
    }

    public static void print(boolean bl, String string) {
        String string2 = bl ? "\u00a77[\u00a7b\u00a77] " : "";
        ChatUtil.addMessage(Component.nullToEmpty((String)(string2 + string)));
    }

    @Generated
    private ChatUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

