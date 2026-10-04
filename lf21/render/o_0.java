/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexSorting;
import lf21.render.DrawContext;
import lf21.render.n_0;
import lf21.render.p_0;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

/*
 * Renamed from lf21.render.o
 */
public final class o_0 {
    private static final n_0 a = new n_0();
    private static final n_0 b = new n_0();
    private static final p_0 c = new p_0();
    private static boolean d = false;

    public static void a() {
        if (d) {
            return;
        }
        c.a();
        d = true;
    }

    private static void a(DrawContext drawContext, int n2, float f2, float f3, float f4, float f5) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture((int)0, (int)n2);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableCull();
        Matrix4f matrix4f = drawContext.b().last().pose();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).uv(0.0f, 1.0f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3 + f5, 0.0f).uv(0.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, 0.0f).uv(1.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3, 0.0f).uv(1.0f, 1.0f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        tesselator.end();
        RenderSystem.defaultBlendFunc();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(DrawContext drawContext, float f2, float f3, float f4, float f5, float f6, Runnable runnable) {
        if (f4 <= 0.0f || f5 <= 0.0f || f6 <= 0.001f) {
            runnable.run();
            return;
        }
        o_0.a();
        float f7 = Math.max(4.0f, f6 * 3.0f);
        float f8 = f2 - f7;
        float f9 = f3 - f7;
        float f10 = f4 + 2.0f * f7;
        float f11 = f5 + 2.0f * f7;
        float f12 = (float)Minecraft.getInstance().getWindow().getGuiScale();
        int n2 = Math.max(4, Math.min(2048, (int)Math.ceil(f10 * f12)));
        int n3 = Math.max(4, Math.min(2048, (int)Math.ceil(f11 * f12)));
        a.a(n2, n3);
        b.a(n2, n3);
        int n4 = GL11.glGetInteger((int)36006);
        int[] nArray = new int[4];
        GL11.glGetIntegerv((int)2978, (int[])nArray);
        Matrix4f matrix4f = RenderSystem.getProjectionMatrix();
        VertexSorting vertexSorting = RenderSystem.getVertexSorting();
        Matrix4f matrix4f2 = new Matrix4f().setOrtho(f8, f8 + f10, f9 + f11, f9, 1000.0f, 3000.0f);
        a.a();
        a.b();
        RenderSystem.setProjectionMatrix((Matrix4f)matrix4f2, (VertexSorting)VertexSorting.ORTHOGRAPHIC_Z);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        try {
            runnable.run();
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        float f13 = Math.max(1.0f, f6 * f12);
        GlStateManager._disableBlend();
        b.a();
        b.b();
        c.a(a.c(), 1.0f, 0.0f, n2, n3, f13);
        a.a();
        a.b();
        c.a(b.c(), 0.0f, 1.0f, n2, n3, f13);
        GL30.glBindFramebuffer((int)36160, (int)n4);
        GL11.glViewport((int)nArray[0], (int)nArray[1], (int)nArray[2], (int)nArray[3]);
        RenderSystem.setProjectionMatrix((Matrix4f)matrix4f, (VertexSorting)vertexSorting);
        o_0.a(drawContext, a.c(), f8, f9, f10, f11);
    }

    public static void b() {
        a.g();
        b.g();
        c.b();
        d = false;
    }
}

