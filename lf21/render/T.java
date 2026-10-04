/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.RenderUtil;

public final class T {
    public static float a(String string, float f2, float f3, u_0 u_02, int n2, int n3, float f4) {
        return y_0.a(string, f2, f3, u_02, n2);
    }

    private static int a(float f2) {
        return Math.max(0, Math.min(255, Math.round(f2)));
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, float f7) {
        int n2 = T.a(150.0f * f7);
        int n3 = T.a(35.0f * f7);
        RenderUtil.b(poseStack, f2, f3, f4, f5, f6, new Color(24, 24, 24, n2).getRGB());
        RenderUtil.b(poseStack, f2, f3, f4, f5, f6, new Color(255, 255, 255, n3).getRGB());
    }

    private T() {
    }
}

