/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.render;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import java.awt.image.BufferedImage;
import lf21.ClientBase;
import lf21.utils.render.a;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.joml.Matrix4f;

public final class d {
    public static void a(RenderTarget renderTarget, PoseStack poseStack, int n2, int n3) {
        Matrix4f matrix4f = poseStack.last().pose();
        ShaderInstance shaderInstance = ClientBase.mc.gameRenderer.blitShader;
        shaderInstance.setSampler("DiffuseSampler", (Object)renderTarget.getColorTextureId());
        shaderInstance.apply();
        float f2 = (float)renderTarget.viewWidth / (float)renderTarget.width;
        float f3 = (float)renderTarget.viewHeight / (float)renderTarget.height;
        Tesselator tesselator = RenderSystem.renderThreadTesselator();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferBuilder.vertex(matrix4f, 0.0f, (float)n3, 0.0f).uv(0.0f, 0.0f).color(255, 255, 255, 255).endVertex();
        bufferBuilder.vertex(matrix4f, (float)n2, (float)n3, 0.0f).uv(f2, 0.0f).color(255, 255, 255, 255).endVertex();
        bufferBuilder.vertex(matrix4f, (float)n2, 0.0f, 0.0f).uv(f2, f3).color(255, 255, 255, 255).endVertex();
        bufferBuilder.vertex(matrix4f, 0.0f, 0.0f, 0.0f).uv(0.0f, f3).color(255, 255, 255, 255).endVertex();
        BufferUploader.draw((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        shaderInstance.clear();
    }

    public static void b(RenderTarget renderTarget, PoseStack poseStack, int n2, int n3) {
        RenderSystem.assertOnRenderThread();
        Matrix4f matrix4f = poseStack.last().pose();
        Minecraft minecraft = ClientBase.mc;
        ShaderInstance shaderInstance = minecraft.gameRenderer.blitShader;
        shaderInstance.setSampler("DiffuseSampler", (Object)renderTarget.getColorTextureId());
        shaderInstance.apply();
        float f2 = n2;
        float f3 = n3;
        float f4 = (float)renderTarget.viewWidth / (float)renderTarget.width;
        float f5 = (float)renderTarget.viewHeight / (float)renderTarget.height;
        Tesselator tesselator = RenderSystem.renderThreadTesselator();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferBuilder.vertex(matrix4f, 0.0f, f3, 0.0f).uv(0.0f, 0.0f).color(255, 255, 255, 255).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).uv(f4, 0.0f).color(255, 255, 255, 255).endVertex();
        bufferBuilder.vertex(matrix4f, f2, 0.0f, 0.0f).uv(f4, f5).color(255, 255, 255, 255).endVertex();
        bufferBuilder.vertex(matrix4f, 0.0f, 0.0f, 0.0f).uv(0.0f, f5).color(255, 255, 255, 255).endVertex();
        BufferUploader.draw((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        shaderInstance.clear();
    }

    public static void a(int n2, int n3) {
        RenderSystem.texParameter((int)3553, (int)10241, (int)n2);
        RenderSystem.texParameter((int)3553, (int)10240, (int)n3);
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4) {
        poseStack.pushPose();
        poseStack.translate(f2, f3, 0.0f);
        poseStack.scale(f4, f4, 1.0f);
        poseStack.translate(-f2, -f3, 0.0f);
    }

    public static void a(PoseStack poseStack) {
        poseStack.popPose();
    }

    public static void b(PoseStack poseStack, float f2, float f3, float f4) {
        poseStack.pushPose();
        poseStack.translate(f2, f3, 0.0f);
        poseStack.mulPose(Axis.ZP.rotationDegrees(f4));
        poseStack.translate(-f2, -f3, 0.0f);
    }

    public static void a() {
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void a(int n2, int n3, int n4, int n5) {
        RenderSystem.setShaderColor((float)((float)n2 / 255.0f), (float)((float)n3 / 255.0f), (float)((float)n4 / 255.0f), (float)((float)n5 / 255.0f));
    }

    public static void b(int n2, int n3) {
        RenderSystem.setShaderColor((float)((float)a.b(n2) / 255.0f), (float)((float)a.c(n2) / 255.0f), (float)((float)a.d(n2) / 255.0f), (float)((float)n3 / 255.0f));
    }

    public static void a(int n2) {
        RenderSystem.setShaderColor((float)((float)a.b(n2) / 255.0f), (float)((float)a.c(n2) / 255.0f), (float)((float)a.d(n2) / 255.0f), (float)((float)a.a(n2) / 255.0f));
    }

    public static void a(Runnable runnable) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        runnable.run();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    public static void b(int n2) {
        d.a(a.b(n2), a.c(n2), a.d(n2), a.a(n2));
    }

    public static DynamicTexture a(NativeImage nativeImage, BufferedImage bufferedImage) {
        for (int i2 = 0; i2 < bufferedImage.getWidth(); ++i2) {
            for (int i3 = 0; i3 < bufferedImage.getHeight(); ++i3) {
                nativeImage.setPixelRGBA(i2, i3, bufferedImage.getRGB(i2, i3));
            }
            continue;
        }
        return new DynamicTexture(nativeImage);
    }

    @Generated
    private d() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

