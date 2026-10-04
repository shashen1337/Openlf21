/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.event.impl.RenderEntityEvent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;

public class RenderEntityEvent$Post
extends RenderEntityEvent {
    public RenderEntityEvent$Post(EntityRenderer<?> entityRenderer, Entity entity, PoseStack poseStack, MultiBufferSource multiBufferSource, float f2, int n2) {
        super(entityRenderer, entity, poseStack, multiBufferSource, f2, n2, false);
    }
}

