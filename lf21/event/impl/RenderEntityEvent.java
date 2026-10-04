/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.event.Event;
import lombok.Generated;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;

public class RenderEntityEvent
extends Event {
    private final EntityRenderer<?> b;
    private final Entity c;
    private final PoseStack d;
    private final MultiBufferSource e;
    private final float f;
    private final int g;
    private boolean h;

    @Override
    public boolean isCancelled() {
        return this.h;
    }

    @Override
    public void a(boolean bl) {
        this.h = bl;
    }

    @Generated
    public RenderEntityEvent(EntityRenderer<?> entityRenderer, Entity entity, PoseStack poseStack, MultiBufferSource multiBufferSource, float f2, int n2, boolean bl) {
        this.b = entityRenderer;
        this.c = entity;
        this.d = poseStack;
        this.e = multiBufferSource;
        this.f = f2;
        this.g = n2;
        this.h = bl;
    }

    @Generated
    public EntityRenderer<?> a() {
        return this.b;
    }

    @Generated
    public Entity b() {
        return this.c;
    }

    @Generated
    public PoseStack c() {
        return this.d;
    }

    @Generated
    public MultiBufferSource d() {
        return this.e;
    }

    @Generated
    public float e() {
        return this.f;
    }

    @Generated
    public int f() {
        return this.g;
    }
}

