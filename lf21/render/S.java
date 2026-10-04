/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.lang.invoke.LambdaMetafactory;
import lf21.ClientBase;
import lf21.render.a.d;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.client.Minecraft;
import org.joml.Matrix4f;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

public class S {
    private static final Supplier<TextureTarget> c;
    private static final String[] e;
    private static final RenderTarget d;
    private static final Supplier<TextureTarget> b;
    private static final d a;

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
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
                    case 0 -> 99;
                    case 1 -> 3;
                    case 2 -> 60;
                    case 3 -> 59;
                    case 4 -> 116;
                    case 5 -> 91;
                    default -> 49;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public static void b(boolean bl) {
        GL11.glStencilFunc((int)(bl ? 514 : 517), (int)1, (int)255);
        GL11.glStencilOp((int)7680, (int)7680, (int)7681);
        GlStateManager._colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GlStateManager._enableBlend();
    }

    public static void a(boolean bl, RenderTarget renderTarget, boolean bl2, boolean bl3) {
        renderTarget.bindWrite(false);
        S.a(renderTarget);
        GL11.glEnable((int)2960);
        if (bl2) {
            GL11.glClearStencil((int)0);
            GL11.glClear((int)1024);
        }
        GL11.glStencilFunc((int)519, (int)(bl3 ? 0 : 1), (int)255);
        GL11.glStencilOp((int)7680, (int)7680, (int)7681);
        if (!bl) {
            GlStateManager._colorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
        }
    }

    public static void a(boolean bl) {
        S.a(bl, ClientBase.mc.getMainRenderTarget(), true, false);
    }

    public static void b(RenderTarget renderTarget) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT((int)renderTarget.getDepthTextureId());
        int n2 = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT((int)36161, (int)n2);
        EXTFramebufferObject.glRenderbufferStorageEXT((int)36161, (int)34041, (int)ClientBase.mc.getMainRenderTarget().width, (int)ClientBase.mc.getMainRenderTarget().height);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36128, (int)36161, (int)n2);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36096, (int)36161, (int)n2);
    }

    public static void a(PoseStack poseStack, Runnable runnable, Runnable runnable2, float f2) {
        if (!a.d()) {
            runnable2.run();
            return;
        }
        Matrix4f matrix4f = poseStack.last().pose();
        RenderTarget renderTarget = (RenderTarget)b.get();
        if (renderTarget.width != S.d.width || renderTarget.height != S.d.height) {
            renderTarget.resize(S.d.width, S.d.height, Minecraft.ON_OSX);
        }
        renderTarget.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        renderTarget.clear(Minecraft.ON_OSX);
        renderTarget.bindWrite(false);
        runnable.run();
        RenderTarget renderTarget2 = (RenderTarget)c.get();
        if (renderTarget2.width != S.d.width || renderTarget2.height != S.d.height) {
            renderTarget2.resize(S.d.width, S.d.height, Minecraft.ON_OSX);
        }
        renderTarget2.clear(Minecraft.ON_OSX);
        renderTarget2.bindWrite(false);
        runnable2.run();
        d.bindWrite(false);
        RenderSystem.disableBlend();
        a.a();
        String[] stringArray = e;
        GL20.glUniform1f((int)a.a("Opacity"), (float)f2);
        GL20.glUniform1i((int)a.a("StencilTexture"), (int)2);
        GL20.glUniform1i((int)a.a("TargetTexture"), (int)0);
        GL13.glActiveTexture((int)33986);
        GlStateManager._bindTexture((int)renderTarget.getColorTextureId());
        GL13.glActiveTexture((int)33984);
        GlStateManager._bindTexture((int)renderTarget2.getColorTextureId());
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f, 0.0f, 0.0f, 0.0f).uv(0.0f, 1.0f).endVertex();
        bufferBuilder.vertex(matrix4f, 0.0f, (float)ClientBase.mc.getWindow().getGuiScaledHeight(), 0.0f).uv(0.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f, (float)ClientBase.mc.getWindow().getGuiScaledWidth(), (float)ClientBase.mc.getWindow().getGuiScaledHeight(), 0.0f).uv(1.0f, 0.0f).endVertex();
        bufferBuilder.vertex(matrix4f, (float)ClientBase.mc.getWindow().getGuiScaledWidth(), 0.0f, 0.0f).uv(1.0f, 1.0f).endVertex();
        BufferUploader.draw((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        a.b();
        RenderSystem.enableBlend();
    }

    public static void a(RenderTarget renderTarget) {
        if (renderTarget != null && renderTarget.getDepthTextureId() > -1) {
            S.b(renderTarget);
            ReflectionUtil.a(renderTarget, -1);
        }
    }

    private static /* synthetic */ TextureTarget c() {
        return new TextureTarget(360, 360, false, Minecraft.ON_OSX);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "7bN\\\u0011/e\u0006{HN\u0006>\u000e0wYU\u00172]7fDO\u0001)T";
                var4_3 = "7bN\\\u0011/e\u0006{HN\u0006>\u000e0wYU\u00172]7fDO\u0001)T".length();
                var1_4 = 13;
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
                    var5[var3_1++] = S.a(S.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0010wYU\u00172]\u0007,s]X\u001d/H";
                    var4_3 = "\u0010wYU\u00172]\u0007,s]X\u001d/H".length();
                    var1_4 = 7;
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
                    var5[var3_1++] = S.a(S.a(v1));
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
        S.e = var5;
        S.a = new d("stencil");
        S.b = Suppliers.memoize((Supplier)(Supplier)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, c(), ()Lcom/mojang/blaze3d/pipeline/TextureTarget;)());
        S.c = Suppliers.memoize((Supplier)(Supplier)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, b(), ()Lcom/mojang/blaze3d/pipeline/TextureTarget;)());
        S.d = ClientBase.mc.getMainRenderTarget();
    }

    public static void a() {
        GL11.glDisable((int)2960);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x31);
        }
        return cArray;
    }

    private static /* synthetic */ TextureTarget b() {
        return new TextureTarget(360, 360, false, Minecraft.ON_OSX);
    }
}

