/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lf21.event.impl.ChatReceiveEvent$MessageType;
import lombok.Generated;
import net.minecraft.network.chat.Component;

public class ChatReceiveEvent
implements EventMarker {
    private ChatReceiveEvent$MessageType a;
    private Component b;

    public ChatReceiveEvent(Component component) {
        this.a = ChatReceiveEvent$MessageType.NAME;
        this.b = component;
    }

    @Generated
    public void a(ChatReceiveEvent$MessageType chatReceiveEvent$MessageType) {
        this.a = chatReceiveEvent$MessageType;
    }

    @Generated
    public ChatReceiveEvent(ChatReceiveEvent$MessageType chatReceiveEvent$MessageType, Component component) {
        this.a = chatReceiveEvent$MessageType;
        this.b = component;
    }

    @Generated
    public ChatReceiveEvent$MessageType a() {
        return this.a;
    }

    @Generated
    public Component getComponent() {
        return this.b;
    }

    @Generated
    public void a(Component component) {
        this.b = component;
    }
}

