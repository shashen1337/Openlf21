/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import lf21.event.EventMarker;

public record RenderEvent(PoseStack a, float b) implements EventMarker
{
    public final String c() {
        return ObjectMethods.bootstrap("c", new MethodHandle[]{RenderEvent.class, "a;b", "a", "b"}, this);
    }

    public final int d() {
        return (int)ObjectMethods.bootstrap("d", new MethodHandle[]{RenderEvent.class, "a;b", "a", "b"}, this);
    }

    public final boolean a(Object object) {
        return (boolean)ObjectMethods.bootstrap("a", new MethodHandle[]{RenderEvent.class, "a;b", "a", "b"}, this, object);
    }
}

