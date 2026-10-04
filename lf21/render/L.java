/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.AABB;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public final class L {
    private boolean c;
    private final Minecraft a = Minecraft.getInstance();
    private TextureTarget b;

    public boolean a() {
        return this.c && this.b != null;
    }

    public int d() {
        return this.b == null ? 0 : this.b.height;
    }

    public void e() {
        this.c = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int a(PoseStack poseStack, Iterable<AABB> iterable, Color color, boolean bl) {
        if (poseStack == null || iterable == null || color == null) {
            this.c = false;
            return 0;
        }
        int n2 = Math.max(1, this.a.getWindow().getWidth());
        int n3 = Math.max(1, this.a.getWindow().getHeight());
        this.a(n2, n3);
        int n4 = GL11.glGetInteger((int)36006);
        int[] nArray = new int[4];
        GL11.glGetIntegerv((int)2978, (int[])nArray);
        boolean bl2 = GL11.glIsEnabled((int)2929);
        boolean bl3 = GL11.glIsEnabled((int)3042);
        int n5 = 0;
        if (bl) {
            this.b.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            this.b.clear(Minecraft.ON_OSX);
        }
        this.b.bindWrite(false);
        try {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask((boolean)false);
            float f2 = (float)color.getRed() / 255.0f;
            float f3 = (float)color.getGreen() / 255.0f;
            float f4 = (float)color.getBlue() / 255.0f;
            for (AABB aABB : iterable) {
                if (aABB == null) continue;
                AABB aABB2 = aABB.inflate(0.006);
                RenderSystem.setShaderColor((float)f2, (float)f3, (float)f4, (float)0.92f);
                RenderUtil.a(aABB2, poseStack);
                RenderSystem.setShaderColor((float)f2, (float)f3, (float)f4, (float)1.0f);
                RenderUtil.b(aABB2, poseStack);
                ++n5;
            }
            this.c = this.c || n5 > 0;
            int n6 = n5;
            return n6;
        }
        finally {
            RenderSystem.lineWidth((float)1.0f);
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.depthMask((boolean)true);
            if (bl2) {
                RenderSystem.enableDepthTest();
            } else {
                RenderSystem.disableDepthTest();
            }
            if (bl3) {
                RenderSystem.enableBlend();
            } else {
                RenderSystem.disableBlend();
            }
            GL30.glBindFramebuffer((int)36160, (int)n4);
            GL11.glViewport((int)nArray[0], (int)nArray[1], (int)nArray[2], (int)nArray[3]);
        }
    }

    public int c() {
        return this.b == null ? 0 : this.b.width;
    }

    public int a(PoseStack poseStack, Iterable<AABB> iterable, Color color) {
        return this.a(poseStack, iterable, color, true);
    }

    public int b() {
        return this.b == null ? 0 : this.b.getColorTextureId();
    }

    private void a(int n2, int n3) {
        if (this.b == null) {
            this.b = new TextureTarget(n2, n3, true, Minecraft.ON_OSX);
        } else if (this.b.width != n2 || this.b.height != n3) {
            this.b.resize(n2, n3, Minecraft.ON_OSX);
        }
    }
}

