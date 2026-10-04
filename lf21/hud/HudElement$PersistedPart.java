/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import lf21.hud.HudElement$InternalPartType;

public final class HudElement$PersistedPart
extends Record {
    private final float c;
    private final float e;
    private final float d;
    private final HudElement$InternalPartType b;
    private final String a;

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{HudElement$PersistedPart.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this, object);
    }

    public float scale() {
        return this.c;
    }

    public float offsetX() {
        return this.d;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{HudElement$PersistedPart.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public HudElement$InternalPartType type() {
        return this.b;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{HudElement$PersistedPart.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public String id() {
        return this.a;
    }

    public float offsetY() {
        return this.e;
    }

    public HudElement$PersistedPart(String string, HudElement$InternalPartType hudElement$InternalPartType, float f2, float f3, float f4) {
        this.a = string;
        this.b = hudElement$InternalPartType;
        this.c = f2;
        this.d = f3;
        this.e = f4;
    }
}

