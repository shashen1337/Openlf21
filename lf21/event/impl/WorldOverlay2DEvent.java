/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.event.EventMarker;
import net.minecraft.client.gui.GuiGraphics;

public record WorldOverlay2DEvent(PoseStack a, GuiGraphics b, float c) implements EventMarker
{
}

