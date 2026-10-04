/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import lf21.hud.HudElement;
import lf21.hud.t;
import lf21.render.C;
import lf21.render.D;
import lf21.render.E;
import lf21.render.G;
import lf21.render.H;
import lf21.render.P;
import lf21.render.Q;
import lf21.render.U;
import lf21.render.X;
import lf21.render.ab;
import lf21.render.h_0;
import lf21.render.o_0;
import lf21.render.q_0;
import lf21.render.r_0;
import lf21.render.s_0;
import lf21.render.u_0;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;

public class DrawContext {
    private static final P a = new P();
    private final PoseStack b;
    private final GuiGraphics c;
    private final Deque<Boolean> d = new ArrayDeque<Boolean>();

    public PoseStack b() {
        return this.b;
    }

    public GuiGraphics c() {
        return this.c;
    }

    private static float[] a(E e2, float f2, float f3, Q q2, int n2) {
        if (e2 == null) {
            return DrawContext.a(n2);
        }
        float f4 = q2.b();
        if (f4 <= 0.0f) {
            return DrawContext.a(e2.e);
        }
        float f5 = Math.max(0.0f, Math.min(1.0f, (f3 - q2.b) / f4));
        float[] fArray = DrawContext.a(e2.e);
        float[] fArray2 = DrawContext.a(e2.f);
        return new float[]{fArray[0] + (fArray2[0] - fArray[0]) * f5, fArray[1] + (fArray2[1] - fArray[1]) * f5, fArray[2] + (fArray2[2] - fArray[2]) * f5, fArray[3] + (fArray2[3] - fArray[3]) * f5};
    }

    private void a(BufferBuilder bufferBuilder, Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, int n2, D d2, Q q2) {
        int n3 = d2.a();
        E e2 = d2.f();
        if (f4 <= 0.0f) {
            float[] fArray = DrawContext.a(e2, f2, f3, q2, n3);
            bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            return;
        }
        for (int i2 = 0; i2 <= n2; ++i2) {
            float f7 = (float)i2 / (float)n2;
            double d3 = Math.toRadians(f5 + (f6 - f5) * f7);
            float f8 = f2 + (float)Math.cos(d3) * f4;
            float f9 = f3 + (float)Math.sin(d3) * f4;
            float[] fArray = DrawContext.a(e2, f8, f9, q2, n3);
            bufferBuilder.vertex(matrix4f, f8, f9, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        }
    }

    public void a(ab ab2, boolean bl) {
        if (this.c == null) {
            return;
        }
        Matrix4f matrix4f = this.b.last().pose();
        Vector4f vector4f = new Vector4f(ab2.c(), ab2.d(), 0.0f, 1.0f).mul((Matrix4fc)matrix4f);
        Vector4f vector4f2 = new Vector4f(ab2.e(), ab2.f(), 0.0f, 1.0f).mul((Matrix4fc)matrix4f);
        int n2 = (int)Math.floor(Math.min(vector4f.x, vector4f2.x));
        int n3 = (int)Math.floor(Math.min(vector4f.y, vector4f2.y));
        int n4 = (int)Math.ceil(Math.max(vector4f.x, vector4f2.x));
        int n5 = (int)Math.ceil(Math.max(vector4f.y, vector4f2.y));
        this.c.enableScissor(n2, n3, n4, n5);
        this.g();
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7, boolean bl, D d2) {
        this.i();
        Matrix4f matrix4f = this.b.last().pose();
        float f8 = (f2 + f4) * 0.5f;
        float f9 = (f3 + f5) * 0.5f;
        float f10 = Math.min(f4 - f2, f5 - f3) * 0.5f;
        float[] fArray = DrawContext.a(d2.a());
        int n2 = 32;
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        if (d2.b() == h_0.STROKE) {
            float f11 = Math.max(0.5f, d2.d());
            float f12 = f10 - f11 * 0.5f;
            float f13 = f10 + f11 * 0.5f;
            bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_STRIP, DefaultVertexFormat.POSITION_COLOR);
            for (int i2 = 0; i2 <= n2; ++i2) {
                float f14 = (float)i2 / (float)n2;
                double d3 = Math.toRadians(f6 + f7 * f14);
                float f15 = (float)Math.cos(d3);
                float f16 = (float)Math.sin(d3);
                bufferBuilder.vertex(matrix4f, f8 + f15 * f13, f9 + f16 * f13, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
                bufferBuilder.vertex(matrix4f, f8 + f15 * f12, f9 + f16 * f12, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            }
        } else {
            bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            bufferBuilder.vertex(matrix4f, f8, f9, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            for (int i3 = 0; i3 <= n2; ++i3) {
                float f17 = (float)i3 / (float)n2;
                double d4 = Math.toRadians(f6 + f7 * f17);
                float f18 = f8 + (float)Math.cos(d4) * f10;
                float f19 = f9 + (float)Math.sin(d4) * f10;
                bufferBuilder.vertex(matrix4f, f18, f19, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            }
        }
        tesselator.end();
    }

    public static P a() {
        return a;
    }

    public void f() {
    }

    private void a(List<float[]> list, int n2) {
        ArrayList<float[]> arrayList = DrawContext.b(list);
        ArrayList<int[]> arrayList2 = DrawContext.a(arrayList);
        if (arrayList2.isEmpty()) {
            return;
        }
        Matrix4f matrix4f = this.b.last().pose();
        float[] fArray = DrawContext.a(n2);
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.POSITION_TEX_COLOR);
        for (int[] nArray : arrayList2) {
            for (int n3 : nArray) {
                float[] fArray2 = arrayList.get(n3);
                bufferBuilder.vertex(matrix4f, fArray2[0], fArray2[1], 0.0f).uv(0.0f, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            }
        }
        BufferUploader.draw((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    private void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, float[] fArray, s_0 s_02) {
        if (s_02.a()) {
            this.a(matrix4f, f2, f3, f4, f5, f6, fArray);
        }
        s_02.a((float)Math.hypot(f4 - f2, f5 - f3));
    }

    public void a(Q q2, float f2, float f3, float f4, float f5, int n2) {
        float f6 = q2.a + f2 - f5;
        float f7 = q2.b + f3 - f5;
        float f8 = q2.a() + f5 * 2.0f;
        float f9 = q2.b() + f5 * 2.0f;
        float f10 = Math.max(0.0f, q2.e + f5);
        float f11 = Math.max(0.0f, q2.f + f5);
        float f12 = Math.max(0.0f, q2.g + f5);
        float f13 = Math.max(0.0f, q2.h + f5);
        Q q3 = Q.a(f6, f7, f8, f9, new float[]{f10, f11, f12, f13});
        D d2 = new D().a(n2);
        float f14 = Math.max(0.01f, f4 * 0.5f);
        o_0.a(this, f6, f7, f8, f9, f14, () -> this.a(q3, d2));
    }

    public void b(float f2, float f3) {
        this.b.scale(f2, f3, 1.0f);
    }

    void h() {
        while (!this.d.isEmpty()) {
            boolean bl = this.d.pop();
            if (!bl || this.c == null) continue;
            this.c.disableScissor();
        }
    }

    public void a(ab ab2) {
        this.a(ab2, true);
    }

    public void e() {
        boolean bl;
        if (!this.d.isEmpty() && (bl = this.d.pop().booleanValue()) && this.c != null) {
            this.c.disableScissor();
        }
        this.b.popPose();
    }

    private void b(Q q2, D d2) {
        D d3 = d2.j();
        this.b(q2.a, q2.b, q2.a(), q2.b(), d2);
    }

    public void a(float f2, float f3) {
        this.b.translate(f2, f3, 0.0f);
    }

    public void d() {
        this.b.pushPose();
        this.d.push(Boolean.FALSE);
    }

    static float[] a(int n2) {
        return new float[]{(float)(n2 >> 16 & 0xFF) / 255.0f, (float)(n2 >> 8 & 0xFF) / 255.0f, (float)(n2 & 0xFF) / 255.0f, (float)(n2 >>> 24 & 0xFF) / 255.0f};
    }

    public static AbstractTexture a(ResourceLocation resourceLocation) {
        return Minecraft.getInstance().getTextureManager().getTexture(resourceLocation);
    }

    private void i() {
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
    }

    private static boolean a(float[] fArray, float[] fArray2, float[] fArray3, float[] fArray4, boolean bl) {
        float f2 = DrawContext.a(fArray2, fArray3, fArray);
        float f3 = DrawContext.a(fArray3, fArray4, fArray);
        float f4 = DrawContext.a(fArray4, fArray2, fArray);
        if (bl) {
            return f2 > 1.0E-4f && f3 > 1.0E-4f && f4 > 1.0E-4f;
        }
        return f2 < -1.0E-4f && f3 < -1.0E-4f && f4 < -1.0E-4f;
    }

    private void j() {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
    }

    public void a(Q q2, boolean bl) {
        if (this.c == null) {
            return;
        }
        Matrix4f matrix4f = this.b.last().pose();
        Vector4f vector4f = new Vector4f(q2.a, q2.b, 0.0f, 1.0f).mul((Matrix4fc)matrix4f);
        Vector4f vector4f2 = new Vector4f(q2.c, q2.d, 0.0f, 1.0f).mul((Matrix4fc)matrix4f);
        int n2 = (int)Math.floor(Math.min(vector4f.x, vector4f2.x));
        int n3 = (int)Math.floor(Math.min(vector4f.y, vector4f2.y));
        int n4 = (int)Math.ceil(Math.max(vector4f.x, vector4f2.x));
        int n5 = (int)Math.ceil(Math.max(vector4f.y, vector4f2.y));
        this.c.enableScissor(n2, n3, n4, n5);
        this.g();
    }

    private static ArrayList<int[]> a(List<float[]> list) {
        int n2;
        int n3;
        int n4;
        ArrayList<int[]> arrayList = new ArrayList<int[]>(Math.max(0, list.size() - 2));
        if (list.size() < 3) {
            return arrayList;
        }
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>(list.size());
        for (n4 = 0; n4 < list.size(); n4 += 1) {
            arrayList2.add(n4);
        }
        n4 = DrawContext.c(list) > 0.0f ? 1 : 0;
        int n5 = list.size() * list.size();
        while (arrayList2.size() > 3 && n5-- > 0) {
            n3 = 0;
            for (n2 = 0; n2 < arrayList2.size(); ++n2) {
                int n6 = (Integer)arrayList2.get((n2 - 1 + arrayList2.size()) % arrayList2.size());
                int n7 = (Integer)arrayList2.get(n2);
                int n8 = (Integer)arrayList2.get((n2 + 1) % arrayList2.size());
                float[] fArray = list.get(n6);
                float[] fArray2 = list.get(n7);
                float[] fArray3 = list.get(n8);
                float f2 = DrawContext.a(fArray, fArray2, fArray3);
                if (n4 != 0 ? f2 <= 1.0E-4f : f2 >= -1.0E-4f) continue;
                boolean bl = false;
                Iterator iterator = arrayList2.iterator();
                while (iterator.hasNext()) {
                    int n9 = (Integer)iterator.next();
                    if (n9 == n6 || n9 == n7 || n9 == n8 || !DrawContext.a(list.get(n9), fArray, fArray2, fArray3, n4 != 0)) continue;
                    bl = true;
                    break;
                }
                if (bl) continue;
                arrayList.add(new int[]{n6, n7, n8});
                arrayList2.remove(n2);
                n3 = 1;
                break;
            }
            if (n3 != 0) continue;
            break;
        }
        if (arrayList2.size() == 3) {
            arrayList.add(new int[]{(Integer)arrayList2.get(0), (Integer)arrayList2.get(1), (Integer)arrayList2.get(2)});
        } else if (arrayList2.size() > 3) {
            n3 = (Integer)arrayList2.get(0);
            for (n2 = 1; n2 < arrayList2.size() - 1; ++n2) {
                arrayList.add(new int[]{n3, (Integer)arrayList2.get(n2), (Integer)arrayList2.get(n2 + 1)});
            }
        }
        return arrayList;
    }

    private static float a(float[] fArray, float[] fArray2, float[] fArray3) {
        return (fArray2[0] - fArray[0]) * (fArray3[1] - fArray2[1]) - (fArray2[1] - fArray[1]) * (fArray3[0] - fArray2[0]);
    }

    private void a(ArrayList<float[]> arrayList, int n2) {
        if (!arrayList.isEmpty()) {
            this.a((List<float[]>)arrayList, n2);
            arrayList.clear();
        }
    }

    private static float c(List<float[]> list) {
        float f2 = 0.0f;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            float[] fArray = list.get(i2);
            float[] fArray2 = list.get((i2 + 1) % list.size());
            f2 += fArray[0] * fArray2[1] - fArray2[0] * fArray[1];
        }
        return f2 * 0.5f;
    }

    private void b(G g2, D d2) {
        ArrayList<float[]> arrayList = new ArrayList<float[]>();
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        block7: for (H h2 : g2.b()) {
            switch (r_0.a[h2.a.ordinal()]) {
                case 1: {
                    if (!arrayList.isEmpty()) {
                        this.a(arrayList, d2);
                        arrayList.clear();
                    }
                    f2 = h2.b[0];
                    f3 = h2.b[1];
                    f4 = f2;
                    f5 = f3;
                    arrayList.add(new float[]{f2, f3});
                    continue block7;
                }
                case 2: {
                    f2 = h2.b[0];
                    f3 = h2.b[1];
                    arrayList.add(new float[]{f2, f3});
                    continue block7;
                }
                case 5: {
                    if (!arrayList.isEmpty()) {
                        this.a(arrayList, d2);
                        arrayList.clear();
                    }
                    f2 = f4;
                    f3 = f5;
                    continue block7;
                }
                case 6: {
                    this.a(h2.c, d2);
                    continue block7;
                }
                case 7: {
                    this.a(h2.d, d2);
                    continue block7;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.a(arrayList, d2);
        }
    }

    private void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, float[] fArray) {
        float f7 = f4 - f2;
        float f8 = f5 - f3;
        float f9 = (float)Math.hypot(f7, f8);
        if (f9 < 1.0E-4f) {
            return;
        }
        float f10 = -f8 / f9 * f6 * 0.5f;
        float f11 = f7 / f9 * f6 * 0.5f;
        this.i();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, f2 + f10, f3 + f11, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f2 - f10, f3 - f11, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f4 - f10, f5 - f11, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f4 + f10, f5 + f11, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        tesselator.end();
    }

    public void a(G g2, int n2) {
        if (g2 == null) {
            return;
        }
        ArrayList<float[]> arrayList = new ArrayList<float[]>();
        for (H h2 : g2.b()) {
            switch (r_0.a[h2.a.ordinal()]) {
                case 1: {
                    this.a(arrayList, n2);
                    arrayList.add(new float[]{h2.b[0], h2.b[1]});
                    break;
                }
                case 2: {
                    arrayList.add(new float[]{h2.b[0], h2.b[1]});
                    break;
                }
                case 5: {
                    this.a(arrayList, n2);
                    break;
                }
                case 6: {
                    this.a(arrayList, n2);
                    this.a((List<float[]>)DrawContext.a(h2.c), n2);
                    break;
                }
                case 7: {
                    this.a(arrayList, n2);
                    ab ab2 = h2.d;
                    ArrayList<float[]> arrayList2 = new ArrayList<float[]>();
                    arrayList2.add(new float[]{ab2.c(), ab2.d()});
                    arrayList2.add(new float[]{ab2.e(), ab2.d()});
                    arrayList2.add(new float[]{ab2.e(), ab2.f()});
                    arrayList2.add(new float[]{ab2.c(), ab2.f()});
                    this.a((List<float[]>)arrayList2, n2);
                    break;
                }
            }
        }
        this.a(arrayList, n2);
    }

    public DrawContext(GuiGraphics guiGraphics) {
        this.c = guiGraphics;
        this.b = new PoseStack();
    }

    private static float a(float[] fArray, float[] fArray2) {
        float f2 = fArray[0] - fArray2[0];
        float f3 = fArray[1] - fArray2[1];
        return f2 * f2 + f3 * f3;
    }

    public DrawContext(GuiGraphics guiGraphics, PoseStack poseStack) {
        this.c = guiGraphics;
        this.b = poseStack != null ? poseStack : new PoseStack();
    }

    public void a(float f2, float f3, float f4, float f5, float f6, Runnable runnable) {
        o_0.a(this, f2, f3, f4, f5, f6, runnable);
    }

    private void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float[] fArray, s_0 s_02) {
        int n2 = 24;
        float f11 = f2;
        float f12 = f3;
        for (int i2 = 1; i2 <= n2; ++i2) {
            float f13 = (float)i2 / (float)n2;
            float f14 = 1.0f - f13;
            float f15 = f14 * f14 * f14 * f2 + 3.0f * f14 * f14 * f13 * f4 + 3.0f * f14 * f13 * f13 * f6 + f13 * f13 * f13 * f8;
            float f16 = f14 * f14 * f14 * f3 + 3.0f * f14 * f14 * f13 * f5 + 3.0f * f14 * f13 * f13 * f7 + f13 * f13 * f13 * f9;
            this.a(matrix4f, f11, f12, f15, f16, f10, fArray, s_02);
            f11 = f15;
            f12 = f16;
        }
    }

    private static void a(List<float[]> list, float f2, float f3, float f4, float f5, float f6) {
        if (f4 <= 0.0f) {
            list.add(new float[]{f2, f3});
            return;
        }
        int n2 = 6;
        for (int i2 = 0; i2 <= n2; ++i2) {
            float f7 = (float)i2 / (float)n2;
            double d2 = Math.toRadians(f5 + (f6 - f5) * f7);
            list.add(new float[]{f2 + (float)Math.cos(d2) * f4, f3 + (float)Math.sin(d2) * f4});
        }
    }

    public void a(float f2, float f3, float f4, float f5, D d2) {
        if (d2.b() == h_0.STROKE) {
            this.b(f2, f3, f4, f5, d2);
            return;
        }
        this.i();
        Matrix4f matrix4f = this.b.last().pose();
        float[] fArray = DrawContext.a(d2.a());
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        if (d2.f() != null) {
            E e2 = d2.f();
            float[] fArray2 = DrawContext.a(e2.e);
            float[] fArray3 = DrawContext.a(e2.f);
            boolean bl = Math.abs(e2.d - e2.b) >= Math.abs(e2.c - e2.a);
            boolean bl2 = bl;
            if (bl) {
                bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).color(fArray2[0], fArray2[1], fArray2[2], fArray2[3]).endVertex();
                bufferBuilder.vertex(matrix4f, f2, f3 + f5, 0.0f).color(fArray3[0], fArray3[1], fArray3[2], fArray3[3]).endVertex();
                bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, 0.0f).color(fArray3[0], fArray3[1], fArray3[2], fArray3[3]).endVertex();
                bufferBuilder.vertex(matrix4f, f2 + f4, f3, 0.0f).color(fArray2[0], fArray2[1], fArray2[2], fArray2[3]).endVertex();
            } else {
                bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).color(fArray2[0], fArray2[1], fArray2[2], fArray2[3]).endVertex();
                bufferBuilder.vertex(matrix4f, f2, f3 + f5, 0.0f).color(fArray2[0], fArray2[1], fArray2[2], fArray2[3]).endVertex();
                bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, 0.0f).color(fArray3[0], fArray3[1], fArray3[2], fArray3[3]).endVertex();
                bufferBuilder.vertex(matrix4f, f2 + f4, f3, 0.0f).color(fArray3[0], fArray3[1], fArray3[2], fArray3[3]).endVertex();
            }
        } else {
            bufferBuilder.vertex(matrix4f, f2, f3, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            bufferBuilder.vertex(matrix4f, f2, f3 + f5, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            bufferBuilder.vertex(matrix4f, f2 + f4, f3 + f5, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            bufferBuilder.vertex(matrix4f, f2 + f4, f3, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        }
        tesselator.end();
    }

    public void a(U u2, ab ab2, ab ab3, D d2) {
        if (u2 == null) {
            return;
        }
        this.j();
        if (u2.d() != null) {
            RenderSystem.setShaderTexture((int)0, (ResourceLocation)u2.d());
        } else {
            RenderSystem.setShaderTexture((int)0, (int)u2.a());
        }
        Matrix4f matrix4f = this.b.last().pose();
        float[] fArray = DrawContext.a(d2.a());
        float f2 = ab2.c() / (float)u2.b();
        float f3 = ab2.d() / (float)u2.c();
        float f4 = ab2.e() / (float)u2.b();
        float f5 = ab2.f() / (float)u2.c();
        float f6 = ab3.c();
        float f7 = ab3.d();
        float f8 = ab3.a();
        float f9 = ab3.b();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferBuilder.vertex(matrix4f, f6, f7, 0.0f).uv(f2, f3).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f7 + f9, 0.0f).uv(f2, f5).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f6 + f8, f7 + f9, 0.0f).uv(f4, f5).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f6 + f8, f7, 0.0f).uv(f4, f3).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        tesselator.end();
    }

    public void a(float f2) {
        this.b.mulPose(Axis.ZP.rotationDegrees(f2));
    }

    public void a(Q q2, D d2) {
        int n2;
        C.a(this.b, q2.a, q2.b, q2.a(), q2.b(), Math.max(Math.max(q2.e, q2.f), Math.max(q2.g, q2.h)), (float)(d2.a() >>> 24 & 0xFF) / 255.0f);
        int n3 = n2 = d2.a();
        boolean bl = false;
        E e2 = d2.f();
        if (e2 != null) {
            n3 = e2.e;
            n2 = e2.f;
            bl = true;
        }
        float f2 = d2.b() == h_0.STROKE ? Math.max(0.5f, d2.d()) : 0.0f;
        a.a(this.b.last().pose(), q2.a, q2.b, q2.c, q2.d, q2.e, q2.f, q2.g, q2.h, n3, n2, bl, f2);
    }

    public void a(ab ab2, D d2) {
        this.a(ab2.c(), ab2.d(), ab2.a(), ab2.b(), d2);
    }

    public void a(G g2, D d2) {
        if (g2 == null) {
            return;
        }
        float f2 = Math.max(0.5f, d2.d());
        float[] fArray = DrawContext.a(d2.a());
        s_0 s_02 = new s_0(d2.g());
        Matrix4f matrix4f = this.b.last().pose();
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        boolean bl = false;
        if (d2.b() == h_0.STROKE || d2.b() == h_0.STROKE_AND_FILL) {
            for (H h2 : g2.b()) {
                switch (r_0.a[h2.a.ordinal()]) {
                    case 1: {
                        f3 = h2.b[0];
                        f4 = h2.b[1];
                        f5 = f3;
                        f6 = f4;
                        bl = true;
                        break;
                    }
                    case 2: {
                        if (bl) {
                            this.a(matrix4f, f3, f4, h2.b[0], h2.b[1], f2, fArray, s_02);
                        }
                        f3 = h2.b[0];
                        f4 = h2.b[1];
                        bl = true;
                        break;
                    }
                    case 3: {
                        if (bl) {
                            this.a(matrix4f, f3, f4, (f3 + 2.0f * h2.b[0]) / 3.0f, (f4 + 2.0f * h2.b[1]) / 3.0f, (h2.b[2] + 2.0f * h2.b[0]) / 3.0f, (h2.b[3] + 2.0f * h2.b[1]) / 3.0f, h2.b[2], h2.b[3], f2, fArray, s_02);
                        }
                        f3 = h2.b[2];
                        f4 = h2.b[3];
                        break;
                    }
                    case 4: {
                        if (bl) {
                            this.a(matrix4f, f3, f4, h2.b[0], h2.b[1], h2.b[2], h2.b[3], h2.b[4], h2.b[5], f2, fArray, s_02);
                        }
                        f3 = h2.b[4];
                        f4 = h2.b[5];
                        break;
                    }
                    case 5: {
                        if (bl) {
                            this.a(matrix4f, f3, f4, f5, f6, f2, fArray, s_02);
                        }
                        f3 = f5;
                        f4 = f6;
                        break;
                    }
                    case 6: {
                        this.b(h2.c, d2);
                        break;
                    }
                    case 7: {
                        this.b(h2.d.c(), h2.d.d(), h2.d.a(), h2.d.b(), d2);
                    }
                }
            }
        }
        if (d2.b() == h_0.FILL || d2.b() == h_0.STROKE_AND_FILL) {
            this.b(g2, d2);
        }
    }

    private void a(List<float[]> list, D d2) {
        ArrayList<float[]> arrayList = DrawContext.b(list);
        ArrayList<int[]> arrayList2 = DrawContext.a(arrayList);
        if (arrayList2.isEmpty()) {
            return;
        }
        this.i();
        Matrix4f matrix4f = this.b.last().pose();
        float[] fArray = DrawContext.a(d2.a());
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.POSITION_COLOR);
        for (int[] nArray : arrayList2) {
            for (int n2 : nArray) {
                float[] fArray2 = arrayList.get(n2);
                bufferBuilder.vertex(matrix4f, fArray2[0], fArray2[1], 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
            }
        }
        tesselator.end();
    }

    private void g() {
        if (!this.d.isEmpty()) {
            this.d.pop();
            this.d.push(Boolean.TRUE);
        }
    }

    private static ArrayList<float[]> a(Q q2) {
        ArrayList<float[]> arrayList = new ArrayList<float[]>();
        DrawContext.a(arrayList, q2.a + q2.e, q2.b + q2.e, q2.e, 180.0f, 270.0f);
        DrawContext.a(arrayList, q2.c - q2.f, q2.b + q2.f, q2.f, 270.0f, 360.0f);
        DrawContext.a(arrayList, q2.c - q2.g, q2.d - q2.g, q2.g, 0.0f, 90.0f);
        DrawContext.a(arrayList, q2.a + q2.h, q2.d - q2.h, q2.h, 90.0f, 180.0f);
        return arrayList;
    }

    public void c(float f2, float f3, float f4, float f5, D d2) {
        float f6 = Math.max(0.5f, d2.d());
        float f7 = f4 - f2;
        float f8 = f5 - f3;
        float f9 = (float)Math.hypot(f7, f8);
        if (f9 < 1.0E-4f) {
            return;
        }
        float f10 = -f8 / f9 * f6 * 0.5f;
        float f11 = f7 / f9 * f6 * 0.5f;
        this.i();
        Matrix4f matrix4f = this.b.last().pose();
        float[] fArray = DrawContext.a(d2.a());
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, f2 + f10, f3 + f11, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f2 - f10, f3 - f11, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f4 - f10, f5 - f11, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        bufferBuilder.vertex(matrix4f, f4 + f10, f5 + f11, 0.0f).color(fArray[0], fArray[1], fArray[2], fArray[3]).endVertex();
        tesselator.end();
    }

    public void a(String string, float f2, float f3, u_0 u_02, D d2) {
        if (string == null || string.isEmpty()) {
            return;
        }
        q_0 q_02 = u_02.a();
        if (q_02 == null) {
            return;
        }
        X x2 = u_02.d();
        t t2 = HudElement.o();
        if (t2 != null && t2.a()) {
            float f4 = u_02.b(string);
            float f5 = Math.max(1.0f, u_02.a(string).b());
            float f6 = t2.b();
            float f7 = t2.c();
            float f8 = t2.d();
            float f9 = t2.e();
            float f10 = t2.f();
            float f11 = t2.g();
            float f12 = f2 - f6;
            float f13 = f6 + f8 - (f2 + f4);
            float f14 = f3 - f7;
            float f15 = f7 + f9 - (f3 + f5);
            float f16 = f12 <= f13 ? f6 + f12 : f6 + f8 * f10 - f13 - f4 * f11;
            float f17 = f14 <= f15 ? f7 + f14 : f7 + f9 * f10 - f15 - f5 * f11;
            f16 += t2.h();
            f17 += t2.i();
            this.b.pushPose();
            if (Math.abs(f10 - 1.0f) >= 0.001f) {
                this.b.translate(f6, f7, 0.0f);
                this.b.scale(1.0f / f10, 1.0f / f10, 1.0f);
                this.b.translate(-f6, -f7, 0.0f);
            }
            if (Math.abs(f11 - 1.0f) >= 0.001f) {
                this.b.translate(f16, f17, 0.0f);
                this.b.scale(f11, f11, 1.0f);
                this.b.translate(-f16, -f17, 0.0f);
            }
            q_02.a(this.b, string, (double)f16, (double)(f17 + x2.c()), d2.a());
            this.b.popPose();
            return;
        }
        float f18 = f3 + x2.c();
        q_02.a(this.b, string, (double)f2, (double)f18, d2.a());
    }

    private void b(float f2, float f3, float f4, float f5, D d2) {
        float f6 = Math.max(0.5f, d2.d());
        float f7 = f6 * 0.5f;
        this.a(f2 - f7, f3 - f7, f4 + f6, f6, d2.j());
        this.a(f2 - f7, f3 + f5 - f7, f4 + f6, f6, d2.j());
        this.a(f2 - f7, f3 - f7, f6, f5 + f6, d2.j());
        this.a(f2 + f4 - f7, f3 - f7, f6, f5 + f6, d2.j());
    }

    private static ArrayList<float[]> b(List<float[]> list) {
        ArrayList<float[]> arrayList = new ArrayList<float[]>(list.size());
        for (float[] fArray : list) {
            if (!arrayList.isEmpty() && !(DrawContext.a(arrayList.get(arrayList.size() - 1), fArray) > 1.0E-6f)) continue;
            arrayList.add(fArray);
        }
        if (arrayList.size() > 1 && DrawContext.a((float[])arrayList.get(0), arrayList.get(arrayList.size() - 1)) <= 1.0E-6f) {
            arrayList.remove(arrayList.size() - 1);
        }
        boolean bl = true;
        block1: while (bl && arrayList.size() > 3) {
            bl = false;
            for (int i2 = 0; i2 < arrayList.size(); ++i2) {
                float[] fArray = arrayList.get((i2 - 1 + arrayList.size()) % arrayList.size());
                float[] fArray2 = arrayList.get(i2);
                float[] fArray3 = arrayList.get((i2 + 1) % arrayList.size());
                float f2 = Math.abs(DrawContext.a(fArray, fArray2, fArray3));
                float f3 = (fArray2[0] - fArray[0]) * (fArray3[0] - fArray2[0]) + (fArray2[1] - fArray[1]) * (fArray3[1] - fArray2[1]);
                if (f2 > 1.0E-4f || f3 < 0.0f) continue;
                arrayList.remove(i2);
                bl = true;
                continue block1;
            }
        }
        return arrayList;
    }
}

