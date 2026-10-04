/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.render;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.lang.invoke.LambdaMetafactory;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.imageio.ImageIO;
import lf21.ClientBase;
import lf21.render.C;
import lf21.render.DrawContext;
import lf21.render.G;
import lf21.render.K;
import lf21.render.a.c;
import lf21.render.x_0;
import lf21.utils.a.h;
import lf21.utils.render.a;
import lf21.utils.render.d;
import lf21.utils.render.e;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public final class RenderUtil
extends ClientBase {
    private static final Stack<int[]> a;
    private static final Map<Integer, e> b;
    private static lf21.render.a.d c;
    private static lf21.render.a.d d;
    private static final Supplier<TextureTarget> e;
    private static RenderTarget f;
    private static lf21.render.a.d g;
    private static boolean h;
    private static boolean i;
    private static boolean j;
    private static long k;
    private static float l;
    private static final String[] m;

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, int n2, int n3) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        lf21.utils.render.d.a();
        Matrix4f matrix4f = poseStack.last().pose();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, f2, f3, l).color(n2).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3 + f5, l).color(n3).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, l).color(n3).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3, l).color(n2).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        RenderSystem.disableBlend();
    }

    public static void b(PoseStack poseStack, float f2, float f3, float f4, float f5, int n2, int n3) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        lf21.utils.render.d.a();
        Matrix4f matrix4f = poseStack.last().pose();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, f2, f3, l).color(n2).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3 + f5, l).color(n2).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, l).color(n3).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3, l).color(n3).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        RenderSystem.disableBlend();
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, int n2) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2848);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        Matrix4f matrix4f = poseStack.last().pose();
        float f7 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f8 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f9 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f10 = (float)(n2 & 0xFF) / 255.0f;
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).color(f8, f9, f10, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2 - f4 / f5, f3 + f4, 0.0f).color(f8, f9, f10, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3 + f4 / f6, 0.0f).color(f8, f9, f10, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4 / f5, f3 + f4, 0.0f).color(f8, f9, f10, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).color(f8, f9, f10, f7).endVertex();
        Tesselator.getInstance().end();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)2848);
    }

    public static void b(PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, int n2) {
        RenderUtil.a(poseStack, f2, f3, f4, f5, f6, 1.0f, n2);
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, float f7, int n2) {
        if (g == null) {
            g = new lf21.render.a.d("rounded_rect", "vertex_color", lf21.render.a.c.a);
        }
        if (!g.d()) {
            RenderUtil.a(poseStack, f2, f3, f4, f5, n2);
            return;
        }
        Matrix4f matrix4f = poseStack.last().pose();
        g.a();
        String[] stringArray = m;
        GL20.glUniform2f((int)g.a("Size"), (float)f4, (float)f5);
        GL20.glUniform1f((int)g.a("Radius"), (float)f6);
        GL20.glUniform1f((int)g.a("Smoothness"), (float)f7);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).uv(0.0f, 0.0f).color(n2).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3 + f5, 0.0f).uv(0.0f, 1.0f).color(n2).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, 0.0f).uv(1.0f, 1.0f).color(n2).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3, 0.0f).uv(1.0f, 0.0f).color(n2).endVertex();
        BufferUploader.draw((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        RenderSystem.disableBlend();
        g.b();
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, boolean bl, boolean bl2, boolean bl3, boolean bl4, int n2) {
        block38: {
            block37: {
                block36: {
                    block35: {
                        block34: {
                            block33: {
                                block32: {
                                    block31: {
                                        float f7;
                                        float f8;
                                        block30: {
                                            block29: {
                                                block28: {
                                                    if (f6 <= 0.0f) break block28;
                                                    if (bl) break block29;
                                                    if (bl2) break block29;
                                                    if (bl3) break block29;
                                                    if (bl4) break block29;
                                                }
                                                RenderUtil.a(poseStack, f2, f3, f4, f5, n2);
                                                return;
                                            }
                                            f6 = Math.min(f6, Math.min(f4, f5) / 2.0f);
                                            f8 = f4 - 2.0f * f6;
                                            f7 = f5 - 2.0f * f6;
                                            RenderSystem.enableBlend();
                                            RenderSystem.defaultBlendFunc();
                                            if (!(f8 > 0.0f) || !(f7 > 0.0f)) break block30;
                                            RenderUtil.a(poseStack, f2 + f6, f3 + f6, f8, f7, n2);
                                        }
                                        if (f8 > 0.0f) {
                                            RenderUtil.a(poseStack, f2 + f6, f3, f8, f6, n2);
                                            RenderUtil.a(poseStack, f2 + f6, f3 + f5 - f6, f8, f6, n2);
                                        }
                                        if (f7 > 0.0f) {
                                            RenderUtil.a(poseStack, f2, f3 + f6, f6, f7, n2);
                                            RenderUtil.a(poseStack, f2 + f4 - f6, f3 + f6, f6, f7, n2);
                                        }
                                        if (!bl) break block31;
                                        RenderUtil.a((int)f2, (int)f3, (int)f6, (int)f6);
                                        RenderUtil.b(poseStack, f2, f3, f6 * 2.0f, f6 * 2.0f, f6, n2);
                                        RenderUtil.d();
                                        break block32;
                                    }
                                    RenderUtil.a(poseStack, f2, f3, f6, f6, n2);
                                }
                                if (!bl2) break block33;
                                RenderUtil.a((int)(f2 + f4 - f6), (int)f3, (int)f6, (int)f6);
                                RenderUtil.b(poseStack, f2 + f4 - f6 * 2.0f, f3, f6 * 2.0f, f6 * 2.0f, f6, n2);
                                RenderUtil.d();
                                break block34;
                            }
                            RenderUtil.a(poseStack, f2 + f4 - f6, f3, f6, f6, n2);
                        }
                        if (!bl3) break block35;
                        RenderUtil.a((int)f2, (int)(f3 + f5 - f6), (int)f6, (int)f6);
                        RenderUtil.b(poseStack, f2, f3 + f5 - f6 * 2.0f, f6 * 2.0f, f6 * 2.0f, f6, n2);
                        RenderUtil.d();
                        break block36;
                    }
                    RenderUtil.a(poseStack, f2, f3 + f5 - f6, f6, f6, n2);
                }
                if (!bl4) break block37;
                RenderUtil.a((int)(f2 + f4 - f6), (int)(f3 + f5 - f6), (int)f6, (int)f6);
                RenderUtil.b(poseStack, f2 + f4 - f6 * 2.0f, f3 + f5 - f6 * 2.0f, f6 * 2.0f, f6 * 2.0f, f6, n2);
                RenderUtil.d();
                break block38;
            }
            RenderUtil.a(poseStack, f2 + f4 - f6, f3 + f5 - f6, f6, f6, n2);
        }
        RenderSystem.enableBlend();
    }

    public static void a() {
        RenderUtil.beginBlurFrame(System.nanoTime());
    }

    public static void beginBlurFrame(long l2) {
        if (h) {
            return;
        }
        try {
            if (k != l2) {
                k = l2;
                j = false;
            }
            i = true;
        }
        catch (Exception exception) {
            logger.error("Error while preparing HUD blur", (Throwable)exception);
            h = true;
            i = false;
        }
    }

    public static void endBlurFrame() {
        i = false;
    }

    private static TextureTarget b() {
        if (!j) {
            RenderUtil.c();
            j = true;
        }
        return (TextureTarget)e.get();
    }

    private static void c() {
        TextureTarget textureTarget;
        block7: {
            if (f == null) {
                f = mc.getMainRenderTarget();
            }
            textureTarget = (TextureTarget)e.get();
            if (textureTarget.width == RenderUtil.f.width && textureTarget.height == RenderUtil.f.height) break block7;
            textureTarget.resize(RenderUtil.f.width, RenderUtil.f.height, Minecraft.ON_OSX);
        }
        textureTarget.bindWrite(false);
        lf21.utils.render.d.a(f, new PoseStack(), textureTarget.width, textureTarget.height);
        f.bindWrite(false);
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n2) {
        C.a(poseStack, f2, f3, f4, f5, f6, f8);
        try {
            if (h) {
                return;
            }
            if (c == null) {
                c = new lf21.render.a.d("blur", lf21.render.a.c.a);
            }
            if (!c.d()) {
                h = true;
                return;
            }
            TextureTarget textureTarget = RenderUtil.b();
            Matrix4f matrix4f = poseStack.last().pose();
            int n3 = n2 == 0 ? lf21.utils.render.a.a(255, 255, 255, 255) : n2;
            int n4 = n3;
            n4 = lf21.utils.render.a.a(n4, f8);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            c.a();
            String[] stringArray = m;
            GL20.glUniform1i((int)c.a("Sampler0"), (int)0);
            GlStateManager._activeTexture((int)33984);
            GlStateManager._bindTexture((int)textureTarget.getColorTextureId());
            GL20.glUniform2f((int)c.a("Size"), (float)f4, (float)f5);
            GL20.glUniform4f((int)c.a("Radius"), (float)f6, (float)f6, (float)f6, (float)f6);
            GL20.glUniform1f((int)c.a("Smoothness"), (float)1.0f);
            GL20.glUniform1f((int)c.a("BlurRadius"), (float)f7);
            GL20.glUniform1f((int)c.a("Opacity"), (float)f8);
            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferBuilder = tesselator.getBuilder();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).uv(0.0f, 1.0f).color(n4).endVertex();
            bufferBuilder.vertex(matrix4f, f2, f3 + f5, 0.0f).uv(0.0f, 0.0f).color(n4).endVertex();
            bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, 0.0f).uv(1.0f, 0.0f).color(n4).endVertex();
            bufferBuilder.vertex(matrix4f, f2 + f4, f3, 0.0f).uv(1.0f, 1.0f).color(n4).endVertex();
            BufferUploader.draw((BufferBuilder.RenderedBuffer)bufferBuilder.end());
            GlStateManager._bindTexture((int)0);
            c.b();
            RenderSystem.disableBlend();
        }
        catch (Exception exception) {
            logger.error("Error while rendering blur", (Throwable)exception);
            h = true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(PoseStack poseStack, DrawContext drawContext, G g2, float f2, float f3) {
        try {
            if (h) return;
            if (g2 == null) return;
            if (drawContext == null) return;
            if (f3 <= 0.001f) {
                return;
            }
            if (d == null) {
                d = new lf21.render.a.d("path_blur", lf21.render.a.c.a);
            }
            if (!d.d()) {
                h = true;
                return;
            }
            TextureTarget textureTarget = RenderUtil.b();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            d.a();
            String[] stringArray = m;
            GL20.glUniform1i((int)d.a("Sampler0"), (int)0);
            GlStateManager._activeTexture((int)33984);
            GlStateManager._bindTexture((int)textureTarget.getColorTextureId());
            GL20.glUniform1f((int)d.a("BlurRadius"), (float)f2);
            GL20.glUniform1f((int)d.a("Opacity"), (float)f3);
            drawContext.a(g2, -1);
            GlStateManager._bindTexture((int)0);
            d.b();
            RenderSystem.disableBlend();
            return;
        }
        catch (Exception exception) {
            logger.error("Error while rendering path blur", (Throwable)exception);
            h = true;
        }
    }

    public static void a(int n2, int n3, int n4, int n5) {
        int n6 = (int)mc.getWindow().getGuiScale();
        int n7 = n2 * n6;
        int n8 = mc.getWindow().getHeight() - (n3 + n5) * n6;
        int n9 = n4 * n6;
        int n10 = n5 * n6;
        int[] nArray = new int[4];
        if (!a.isEmpty()) {
            nArray = a.peek();
        } else {
            nArray[0] = 0;
            nArray[1] = 0;
            nArray[2] = mc.getWindow().getWidth();
            nArray[3] = mc.getWindow().getHeight();
        }
        int n11 = Math.max(n7, nArray[0]);
        int n12 = Math.max(n8, nArray[1]);
        int n13 = Math.min(n7 + n9, nArray[0] + nArray[2]) - n11;
        int n14 = Math.min(n8 + n10, nArray[1] + nArray[3]) - n12;
        int[] nArray2 = new int[]{n11, n12, n13, n14};
        a.push(nArray2);
        RenderSystem.enableScissor((int)nArray2[0], (int)nArray2[1], (int)nArray2[2], (int)nArray2[3]);
    }

    public static void d() {
        block6: {
            block5: {
                if (a.isEmpty()) {
                    RenderSystem.disableScissor();
                    return;
                }
                a.pop();
                if (!a.isEmpty()) break block5;
                RenderSystem.disableScissor();
                break block6;
            }
            int[] nArray = a.peek();
            RenderSystem.enableScissor((int)nArray[0], (int)nArray[1], (int)nArray[2], (int)nArray[3]);
        }
    }

    public static void a(PoseStack poseStack, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
        float f2 = (float)n6 / (float)n10;
        float f3 = (float)(n6 + n8) / (float)n10;
        float f4 = (float)n7 / (float)n11;
        float f5 = (float)(n7 + n9) / (float)n11;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        Matrix4f matrix4f = poseStack.last().pose();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f, (float)n2, (float)(n3 + n5), 0.0f).uv(f2, f5).endVertex();
        bufferBuilder.vertex(matrix4f, (float)(n2 + n4), (float)(n3 + n5), 0.0f).uv(f3, f5).endVertex();
        bufferBuilder.vertex(matrix4f, (float)(n2 + n4), (float)n3, 0.0f).uv(f3, f4).endVertex();
        bufferBuilder.vertex(matrix4f, (float)n2, (float)n3, 0.0f).uv(f2, f4).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, int n2) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        lf21.utils.render.d.a(n2);
        RenderUtil.a(poseStack, f2, f3, f4, f5);
        RenderSystem.disableBlend();
        lf21.utils.render.d.a();
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5) {
        Matrix4f matrix4f = poseStack.last().pose();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        bufferBuilder.vertex(matrix4f, f2, f3, l).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3 + f5, l).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, l).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3, l).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    public static void a(AABB aABB, PoseStack poseStack) {
        Matrix4f matrix4f = poseStack.last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    public static void b(AABB aABB, PoseStack poseStack) {
        Matrix4f matrix4f = poseStack.last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        bufferBuilder.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION);
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.minY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.maxX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.maxZ).endVertex();
        bufferBuilder.vertex(matrix4f, (float)aABB.minX, (float)aABB.maxY, (float)aABB.minZ).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(AABB aABB, PoseStack poseStack, float f2, float f3, float f4, float f5) {
        if (aABB == null || poseStack == null || f5 <= 0.001f) {
            return;
        }
        f5 = Mth.clamp((float)f5, (float)0.0f, (float)1.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        try {
            float[] fArray = new float[]{7.0f, 5.0f, 3.0f, 1.5f};
            float[] fArray2 = new float[]{0.06f, 0.1f, 0.17f, 0.42f};
            for (int i2 = 0; i2 < fArray.length; ++i2) {
                RenderSystem.lineWidth((float)fArray[i2]);
                RenderSystem.setShaderColor((float)f2, (float)f3, (float)f4, (float)(fArray2[i2] * f5));
                RenderUtil.b(aABB, poseStack);
            }
            return;
        }
        finally {
            RenderSystem.lineWidth((float)1.0f);
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.depthMask((boolean)true);
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
        }
    }

    public static boolean a(float f2, float f3, float f4, float f5, int n2, int n3) {
        boolean bl;
        block9: {
            block8: {
                if (!((float)n2 >= f2) || !((float)n3 >= f3)) break block8;
                if (!((float)n2 < f2 + f4)) break block8;
                if (!((float)n3 < f3 + f5)) break block8;
                bl = true;
                break block9;
            }
            bl = false;
        }
        return bl;
    }

    public static void a(BufferBuilder bufferBuilder, Matrix4f matrix4f, float f2, float f3, float f4, float f5, Color color) {
        float f6 = (float)color.getRed() / 255.0f;
        float f7 = (float)color.getGreen() / 255.0f;
        float f8 = (float)color.getBlue() / 255.0f;
        float f9 = (float)color.getAlpha() / 255.0f;
        bufferBuilder.vertex(matrix4f, f2, f5, 0.0f).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.vertex(matrix4f, f4, f5, 0.0f).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.vertex(matrix4f, f4, f3, 0.0f).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).color(f6, f7, f8, f9).endVertex();
    }

    public static void a(PoseStack poseStack, Entity entity, float f2, float f3, int n2, int n3, int n4, float f4) {
        RenderUtil.a(poseStack, entity, f2, f3, n2, n3, n4, f4, 1.0f);
    }

    public static void a(PoseStack poseStack, Entity entity, float f2, float f3, int n2, int n3, int n4, float f4, float f5) {
        if (entity == null) {
            return;
        }
        RenderUtil.a(poseStack, lf21.utils.game.c.a(entity, f2), f3, n2, n3, n4, f4, f5);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(PoseStack poseStack, Vec3 vec3, float f2, int n2, int n3, int n4, float f3, float f4) {
        f4 = Mth.clamp((float)f4, (float)0.0f, (float)1.0f);
        if (vec3 == null) return;
        if (f2 <= 0.01f) return;
        if (f4 <= 0.001f) {
            return;
        }
        n2 = Mth.clamp((int)n2, (int)0, (int)255);
        n3 = Mth.clamp((int)n3, (int)0, (int)255);
        n4 = Mth.clamp((int)n4, (int)0, (int)255);
        f3 = Mth.clamp((float)f3, (float)0.5f, (float)6.0f);
        float f5 = (float)vec3.x();
        float f6 = (float)vec3.y() + 0.035f;
        float f7 = (float)vec3.z();
        Matrix4f matrix4f = poseStack.last().pose();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        try {
            float f8 = 0.05f + f3 * 0.04f;
            float f9 = 0.015f + f3 * 0.01f;
            RenderUtil.a(matrix4f, f5, f6, f7, Math.max(0.0f, f2 - f8), f2 + f8, n2, n3, n4, Math.round(10.0f * f4));
            RenderUtil.a(matrix4f, f5, f6 + 0.002f, f7, Math.max(0.0f, f2 - f9), f2 + f9, n2, n3, n4, Math.round(72.0f * f4));
            BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
            bufferBuilder.begin(VertexFormat.Mode.LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);
            int n5 = 128;
            for (int i2 = 0; i2 <= n5; ++i2) {
                double d2 = (double)i2 / (double)n5 * Math.PI * 2.0;
                float f10 = f5 + (float)Math.cos(d2) * f2;
                float f11 = f7 + (float)Math.sin(d2) * f2;
                bufferBuilder.vertex(matrix4f, f10, f6 + 0.005f, f11).color(n2, n3, n4, Math.round(220.0f * f4)).endVertex();
            }
            RenderSystem.lineWidth((float)f3);
            BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
            return;
        }
        finally {
            RenderSystem.lineWidth((float)1.0f);
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.depthMask((boolean)true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
        }
    }

    private static void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, int n2, int n3, int n4, int n5) {
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        int n6 = 128;
        for (int i2 = 0; i2 <= n6; ++i2) {
            double d2 = (double)i2 / (double)n6 * Math.PI * 2.0;
            float f7 = (float)Math.cos(d2);
            float f8 = (float)Math.sin(d2);
            bufferBuilder.vertex(matrix4f, f2 + f7 * f5, f3, f4 + f8 * f5).color(n2, n3, n4, 0).endVertex();
            bufferBuilder.vertex(matrix4f, f2 + f7 * f6, f3, f4 + f8 * f6).color(n2, n3, n4, n5).endVertex();
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(PoseStack poseStack, Entity entity, float f2) {
        if (entity == null) return;
        if (mc == null) return;
        if (RenderUtil.mc.gameRenderer == null) return;
        if (RenderUtil.mc.gameRenderer.getMainCamera() == null) {
            return;
        }
        Vec3 vec3 = lf21.utils.game.c.a(entity, f2);
        float f3 = Math.max(0.9f, entity.getBbHeight());
        float f4 = Math.max(0.45f, entity.getBbWidth());
        long l2 = System.currentTimeMillis();
        float f5 = (float)((double)(l2 % 3800L) / 3800.0 * Math.PI * 2.0);
        float f6 = 1.0f + (float)Math.sin((double)l2 * 0.006) * 0.075f;
        poseStack.pushPose();
        poseStack.translate(vec3.x(), vec3.y() + (double)f3 * 0.52, vec3.z());
        poseStack.mulPose(RenderUtil.mc.gameRenderer.getMainCamera().rotation());
        Matrix4f matrix4f = poseStack.last().pose();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        try {
            RenderUtil.a(matrix4f, Math.max(0.62f, f4 * 1.18f), f3 * 0.61f);
            float f7 = Math.max(0.68f, f4 * 1.22f);
            float f8 = Math.max(0.72f, f3 * 0.49f);
            RenderUtil.a(matrix4f, f5, f7, f8, 1.95f * f6, 18, false, l2);
            RenderUtil.a(matrix4f, f5, f7, f8, 1.48f * f6, 50, false, l2);
            RenderUtil.a(matrix4f, f5, f7, f8, 1.0f * f6, 180, true, l2);
            RenderUtil.a(matrix4f, f5, f7, f8, 0.64f * f6, 205, true, l2);
            return;
        }
        finally {
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)true);
            RenderSystem.enableCull();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            poseStack.popPose();
        }
    }

    private static void a(Matrix4f matrix4f, float f2, float f3) {
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, 0.0f, 0.0f, 0.012f).color(70, 225, 245, 27).endVertex();
        int n2 = 56;
        for (int i2 = 0; i2 <= n2; ++i2) {
            double d2 = (double)i2 / (double)n2 * Math.PI * 2.0;
            bufferBuilder.vertex(matrix4f, (float)Math.cos(d2) * f2, (float)Math.sin(d2) * f3, 0.012f).color(70, 225, 245, 0).endVertex();
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    private static void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, int n2, boolean bl, long l2) {
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        for (int i2 = 0; i2 < 4; ++i2) {
            float f6 = f2 + (float)i2 * 1.5707964f;
            float f7 = (float)Math.cos(f6) * f3;
            float f8 = (float)Math.sin(f6) * f4;
            float f9 = ((float)(l2 % 7000L) / 7000.0f + (float)i2 * 0.18f) % 1.0f;
            int n3 = Color.HSBtoRGB(f9, 0.58f, 1.0f);
            int n4 = n3 >> 16 & 0xFF;
            int n5 = n3 >> 8 & 0xFF;
            int n6 = n3 & 0xFF;
            if (bl) {
                n4 = Math.min(255, (n4 + 255) / 2 + 28);
                n5 = Math.min(255, (n5 + 255) / 2 + 28);
                n6 = Math.min(255, (n6 + 255) / 2 + 28);
            }
            RenderUtil.a(bufferBuilder, matrix4f, f7, f8, f6, 0.46f * f5, 0.36f * f5, 0.105f * f5, n4, n5, n6, n2);
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    private static void a(BufferBuilder bufferBuilder, Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, float f7, int n2, int n3, int n4, int n5) {
        float f8 = f5 * 0.5f;
        float f9 = f6 * 0.5f;
        float[][] fArrayArray = new float[][]{{0.0f, f9}, {0.0f, f9 - f7 * 1.35f}, {-f8 + f7, -f9}, {-f8, -f9}};
        float[][] fArrayArray2 = new float[][]{{f8, -f9}, {f8 - f7, -f9}, {0.0f, f9 - f7 * 1.35f}, {0.0f, f9}};
        RenderUtil.a(bufferBuilder, matrix4f, fArrayArray, f2, f3, f4, n2, n3, n4, n5);
        RenderUtil.a(bufferBuilder, matrix4f, fArrayArray2, f2, f3, f4, n2, n3, n4, n5);
    }

    private static void a(BufferBuilder bufferBuilder, Matrix4f matrix4f, float[][] fArray, float f2, float f3, float f4, int n2, int n3, int n4, int n5) {
        float f5 = (float)Math.cos(f4);
        float f6 = (float)Math.sin(f4);
        float f7 = -f6;
        float f8 = f5;
        for (float[] fArray2 : fArray) {
            float f9 = f2 + f7 * fArray2[0] + f5 * fArray2[1];
            float f10 = f3 + f8 * fArray2[0] + f6 * fArray2[1];
            bufferBuilder.vertex(matrix4f, f9, f10, 0.02f).color(n2, n3, n4, n5).endVertex();
        }
    }

    public static void a(PoseStack poseStack, Entity entity, float f2, int n2, int n3, int n4, float f3) {
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        int n5;
        int n6;
        block20: {
            block19: {
                if (mc == null || RenderUtil.mc.gameRenderer == null) break block19;
                if (RenderUtil.mc.gameRenderer.getMainCamera() != null) break block20;
            }
            return;
        }
        Color color = new Color(Mth.clamp((int)n2, (int)0, (int)255), Mth.clamp((int)n3, (int)0, (int)255), Mth.clamp((int)n4, (int)0, (int)255));
        double d8 = 2000.0 / (double)Mth.clamp((float)f3, (float)0.25f, (float)4.0f);
        double d9 = (double)System.currentTimeMillis() % d8;
        boolean bl = d9 > d8 / 2.0;
        boolean bl2 = bl;
        double d10 = d9 / (d8 / 2.0);
        d10 = bl2 ? (d10 = d10 - 1.0) : 1.0 - d10;
        double d11 = d10 < 0.5 ? 2.0 * d10 * d10 : 1.0 - Math.pow(-2.0 * d10 + 2.0, 2.0) / 2.0;
        d10 = d11;
        Vec3 vec3 = lf21.utils.game.c.a(entity, f2);
        double d12 = vec3.x();
        double d13 = vec3.y();
        double d14 = vec3.z();
        float f4 = entity.getBbHeight();
        double d15 = (double)entity.getBbWidth() * 0.975;
        double d16 = (double)f4 * d10;
        double d17 = (double)f4 / 1.2;
        double d18 = d10 > 0.5 ? 1.0 - d10 : d10;
        double d19 = d17 * d18;
        int n7 = bl2 ? -1 : 1;
        double d20 = d19 * (double)n7;
        double d21 = d13 + d16;
        double d22 = d21 + d20;
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.depthMask((boolean)false);
        RenderSystem.enableDepthTest();
        RenderSystem.disableCull();
        Matrix4f matrix4f = poseStack.last().pose();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        int n8 = 60;
        for (int i2 = 0; i2 <= n8; ++i2) {
            n6 = color.getRGB();
            n5 = lf21.utils.render.a.a(color, 0.004f).getRGB();
            d7 = (double)i2 / (double)n8 * Math.PI * 2.0;
            d6 = Math.cos(d7) * d15;
            d5 = Math.sin(d7) * d15;
            d4 = d12 + d6;
            d3 = d14 + d5;
            d2 = d21;
            double d23 = d22;
            bufferBuilder.vertex(matrix4f, (float)d4, (float)d2, (float)d3).color(n6).endVertex();
            bufferBuilder.vertex(matrix4f, (float)d4, (float)d23, (float)d3).color(n5).endVertex();
        }
        BufferBuilder.RenderedBuffer renderedBuffer = bufferBuilder.end();
        if (renderedBuffer != null) {
            BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)renderedBuffer);
        }
        bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        RenderSystem.lineWidth((float)1.0f);
        for (n6 = 0; n6 <= n8; ++n6) {
            n5 = lf21.utils.render.a.a(color, 0.004f).getRGB();
            d7 = (double)n6 / (double)n8 * Math.PI * 2.0;
            d6 = Math.cos(d7) * d15;
            d5 = Math.sin(d7) * d15;
            d4 = d12 + d6;
            d3 = d14 + d5;
            d2 = d21;
            bufferBuilder.vertex(matrix4f, (float)d4, (float)d2, (float)d3).color(n5).endVertex();
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        RenderSystem.lineWidth((float)1.0f);
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void a(AABB aABB, PoseStack poseStack, Color color, Color color2) {
        Matrix4f matrix4f = poseStack.last().pose();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.lineWidth((float)1.5f);
        bufferBuilder.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);
        float f2 = (float)aABB.minX;
        float f3 = (float)aABB.minY;
        float f4 = (float)aABB.minZ;
        float f5 = (float)aABB.maxX;
        float f6 = (float)aABB.maxY;
        float f7 = (float)aABB.maxZ;
        bufferBuilder.vertex(matrix4f, f2, f3, f4).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f7).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f7).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f4).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f4).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f7).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        RenderSystem.lineWidth((float)1.0f);
        RenderSystem.disableBlend();
    }

    public static void b(AABB aABB, PoseStack poseStack, Color color, Color color2) {
        Matrix4f matrix4f = poseStack.last().pose();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        float f2 = (float)aABB.minX;
        float f3 = (float)aABB.minY;
        float f4 = (float)aABB.minZ;
        float f5 = (float)aABB.maxX;
        float f6 = (float)aABB.maxY;
        float f7 = (float)aABB.maxZ;
        int n2 = color.getRed();
        int n3 = color.getGreen();
        int n4 = color.getBlue();
        int n5 = color.getAlpha();
        int n6 = color2.getRed();
        int n7 = color2.getGreen();
        int n8 = color2.getBlue();
        int n9 = color2.getAlpha();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, f2, f3, f7).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f4).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f4).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f7).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).color(n2, n3, n4, n5).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f4).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).color(n6, n7, n8, n9).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f7).color(n6, n7, n8, n9).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        RenderSystem.disableBlend();
    }

    public static void a(BufferBuilder bufferBuilder, Matrix4f matrix4f, AABB aABB) {
        float f2 = (float)(aABB.minX - RenderUtil.mc.getEntityRenderDispatcher().camera.getPosition().x());
        float f3 = (float)(aABB.minY - RenderUtil.mc.getEntityRenderDispatcher().camera.getPosition().y());
        float f4 = (float)(aABB.minZ - RenderUtil.mc.getEntityRenderDispatcher().camera.getPosition().z());
        float f5 = (float)(aABB.maxX - RenderUtil.mc.getEntityRenderDispatcher().camera.getPosition().x());
        float f6 = (float)(aABB.maxY - RenderUtil.mc.getEntityRenderDispatcher().camera.getPosition().y());
        float f7 = (float)(aABB.maxZ - RenderUtil.mc.getEntityRenderDispatcher().camera.getPosition().z());
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        bufferBuilder.vertex(matrix4f, f2, f3, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f3, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f5, f6, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f4).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f7).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f6, f4).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    public static void e() {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
    }

    public static void f() {
        RenderSystem.disableBlend();
    }

    public static void a(ResourceLocation resourceLocation, PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, int n2) {
        RenderUtil.a(mc.getTextureManager().getTexture(resourceLocation).getId(), poseStack, f2, f3, f4, f5, f6, n2);
    }

    public static void a(int n2, PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, int n3) {
        Matrix4f matrix4f = poseStack.last().pose();
        lf21.utils.render.d.a(lf21.utils.render.a.a(n3, f6));
        if (n2 != -1) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture((int)0, (int)n2);
        }
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).uv(0.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f, f2, f3 + f5, 0.0f).uv(0.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, 0.0f).uv(1.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f, f2 + f4, f3, 0.0f).uv(1.0f, 0.0f).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        lf21.utils.render.d.a();
    }

    public static void c(PoseStack poseStack, float f2, float f3, float f4, float f5, int n2, int n3) {
        int n4;
        f2 -= (float)n2;
        f3 -= (float)n2;
        if (!b.containsKey(n4 = (int)((f4 += (float)(n2 * 2)) * (f5 += (float)(n2 * 2)) + f4 * (float)n2))) {
            BufferedImage bufferedImage = new BufferedImage((int)f4, (int)f5, 2);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(new Color(-1));
            graphics.fillRect(n2, n2, (int)(f4 - (float)(n2 * 2)), (int)(f5 - (float)(n2 * 2)));
            graphics.dispose();
            x_0 x_02 = new x_0(n2);
            BufferedImage bufferedImage2 = x_02.a(bufferedImage, (BufferedImage)null);
            b.put(n4, new e(bufferedImage2));
            return;
        }
        b.get(n4).a();
        RenderUtil.e();
        RenderUtil.a(-1, poseStack, f2, f3, f4, f5, (float)lf21.utils.render.a.a(n3) / 255.0f, n3);
        RenderUtil.f();
    }

    public static void a(K k2, BufferedImage bufferedImage) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage)bufferedImage, "png", byteArrayOutputStream);
            byte[] byArray = byteArrayOutputStream.toByteArray();
            RenderUtil.a(k2, byArray);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static void a(K k2, byte[] byArray) {
        try {
            ByteBuffer byteBuffer = BufferUtils.createByteBuffer((int)byArray.length).put(byArray);
            byteBuffer.flip();
            DynamicTexture dynamicTexture = new DynamicTexture(NativeImage.read((ByteBuffer)byteBuffer));
            mc.execute(() -> mc.getTextureManager().register(k2.a(), (AbstractTexture)dynamicTexture));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static int a(int n2, int n3, float f2) {
        float[] fArray = new float[3];
        float[] fArray2 = new float[3];
        Color.RGBtoHSB(n2 >> 16 & 0xFF, n2 >> 8 & 0xFF, n2 & 0xFF, fArray);
        Color.RGBtoHSB(n3 >> 16 & 0xFF, n3 >> 8 & 0xFF, n3 & 0xFF, fArray2);
        float f3 = fArray[0] + (fArray2[0] - fArray[0]) * f2;
        float f4 = fArray[1] + (fArray2[1] - fArray[1]) * f2;
        float f5 = fArray[2] + (fArray2[2] - fArray[2]) * f2;
        int n4 = (int)((float)(n2 >> 24 & 0xFF) + (float)((n3 >> 24 & 0xFF) - (n2 >> 24 & 0xFF)) * f2);
        return n4 << 24 | Color.HSBtoRGB(f3, f4, f5) & 0xFFFFFF;
    }

    @Generated
    private RenderUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Generated
    public static void a(float f2) {
        l = f2;
    }

    private static /* synthetic */ TextureTarget g() {
        return new TextureTarget(1920, 1024, false, Minecraft.ON_OSX);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[21];
                var3_1 = 0;
                var2_2 = "r\u0000'~]9v_\u001b9t\u000fkdY\u00160cFwf\u0017\u00109d]\u0004d\u001b/t\bd\u00138aC|s\u0007\u0004U\u001e c\u0004d\u001b/t\fA\u0017'eJa^T\u001d9~]\nd\u001f:~[qoR\u0001&\u001fr\u0000'~]9v_\u001b9t\u000fkdY\u00160cFwf\u0017\u00024eG9c[\u0007'\u0006e\u00131xZj\u001er\u0000'~]9v_\u001b9t\u000fisR\u00024cFwf\u0017:\u0000U\u000f{mB\u0000\u0007x\u00024rFmx\u0003G\u001c2\u0006e\u00131xZj\nu\u001e c}xe^\u0007&\nu\u001e c}xe^\u0007&\tG\u0013!yp{mB\u0000\u0007x\u00024rFmx\nd\u001f:~[qoR\u0001&2c\u001a<b\u000fpr\u0017\u0013ud[pm^\u0006,1Lu`D\u0001upA}!T\u0013;\u007f@m!U\u0017uxAjuV\u001c!xNmdS";
                var4_3 = "r\u0000'~]9v_\u001b9t\u000fkdY\u00160cFwf\u0017\u00109d]\u0004d\u001b/t\bd\u00138aC|s\u0007\u0004U\u001e c\u0004d\u001b/t\fA\u0017'eJa^T\u001d9~]\nd\u001f:~[qoR\u0001&\u001fr\u0000'~]9v_\u001b9t\u000fkdY\u00160cFwf\u0017\u00024eG9c[\u0007'\u0006e\u00131xZj\u001er\u0000'~]9v_\u001b9t\u000fisR\u00024cFwf\u0017:\u0000U\u000f{mB\u0000\u0007x\u00024rFmx\u0003G\u001c2\u0006e\u00131xZj\nu\u001e c}xe^\u0007&\nu\u001e c}xe^\u0007&\tG\u0013!yp{mB\u0000\u0007x\u00024rFmx\nd\u001f:~[qoR\u0001&2c\u001a<b\u000fpr\u0017\u0013ud[pm^\u0006,1Lu`D\u0001upA}!T\u0013;\u007f@m!U\u0017uxAjuV\u001c!xNmdS".length();
                var1_4 = 26;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = RenderUtil.b(RenderUtil.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "d\u00138aC|s\u0007\fE\u001d \u007fK|eh\u00000r[";
                    var4_3 = "d\u00138aC|s\u0007\fE\u001d \u007fK|eh\u00000r[".length();
                    var1_4 = 8;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = RenderUtil.b(RenderUtil.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    break block10;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl37:
                // 1 sources

                ** continue;
            }
        }
        RenderUtil.m = var5;
        RenderUtil.a = new Stack<E>();
        RenderUtil.b = new HashMap<Integer, e>();
        RenderUtil.k = -9223372036854775808L;
        RenderUtil.e = Suppliers.memoize((Supplier)(Supplier)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, g(), ()Lcom/mojang/blaze3d/pipeline/TextureTarget;)());
        RenderUtil.f = RenderUtil.mc.getMainRenderTarget();
        new h();
        RenderUtil.h = false;
        RenderUtil.i = false;
        RenderUtil.j = false;
        RenderUtil.k = -9223372036854775808L;
        RenderUtil.l = 0.0f;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ '\u0001');
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 55;
                    case 1 -> 114;
                    case 2 -> 85;
                    case 3 -> 17;
                    case 4 -> 47;
                    case 5 -> 25;
                    default -> 1;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

