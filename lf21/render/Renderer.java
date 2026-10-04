/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import java.util.function.Consumer;
import lf21.ClientBase;
import lf21.render.DrawContext;
import net.minecraft.client.gui.GuiGraphics;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL33;

public class Renderer
extends ClientBase {
    private static DrawContext c;
    private static float a;
    private static boolean b;

    public static float b() {
        return a;
    }

    public static void e() {
        RenderSystem.assertOnRenderThread();
        RenderSystem.pixelStore((int)3314, (int)0);
        RenderSystem.pixelStore((int)3316, (int)0);
        RenderSystem.pixelStore((int)3315, (int)0);
        RenderSystem.pixelStore((int)3317, (int)4);
    }

    public static void a(float f2) {
        RenderSystem.assertOnRenderThread();
        a = f2;
        Renderer.c();
    }

    public static DrawContext a() {
        return c;
    }

    public static void f() {
        RenderSystem.assertOnRenderThread();
        BufferUploader.reset();
        GL33.glBindSampler((int)0, (int)0);
        RenderSystem.disableBlend();
        GL11.glDisable((int)3042);
        RenderSystem.blendFunc((int)770, (int)771);
        GL11.glBlendFunc((int)770, (int)771);
        RenderSystem.blendEquation((int)32774);
        GL14.glBlendEquation((int)32774);
        RenderSystem.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        RenderSystem.depthMask((boolean)true);
        GL11.glDepthMask((boolean)true);
        RenderSystem.disableScissor();
        RenderSystem.disableDepthTest();
        GL11.glDisable((int)2929);
        GL13.glActiveTexture((int)33984);
        RenderSystem.activeTexture((int)33984);
        RenderSystem.disableCull();
    }

    static {
        a = 1.0f;
        b = false;
    }

    public static void d() {
        Renderer.a((float)mc.getWindow().getGuiScale());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void render(GuiGraphics guiGraphics, Consumer<DrawContext> consumer) {
        if (!b) {
            Renderer.c();
            if (!b) {
                return;
            }
        }
        if (c != null) {
            consumer.accept(c);
            return;
        }
        Renderer.e();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        DrawContext drawContext = new DrawContext(guiGraphics, guiGraphics != null ? guiGraphics.pose() : null);
        DrawContext drawContext2 = c;
        c = drawContext;
        try {
            consumer.accept(drawContext);
        }
        finally {
            c = drawContext2;
            drawContext.h();
        }
        Renderer.f();
    }

    public static void c() {
        b = true;
    }

    public static void a(Consumer<DrawContext> consumer) {
        if (c != null) {
            consumer.accept(c);
            return;
        }
        Renderer.render(null, consumer);
    }

    public static void setGuiScaleVerified(float f2) {
        Renderer.a(f2);
    }
}

