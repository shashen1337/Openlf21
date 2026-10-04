/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import lf21.ClientBase;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.E;
import lf21.render.P;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.U;
import lf21.render.X;
import lf21.render.ab;
import lf21.render.h_0;
import lf21.render.u_0;
import lf21.utils.render.a;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

/*
 * Renamed from lf21.render.y
 */
public final class y_0 {
    private static final Map<u_0, Map<String, Float>> a = new HashMap<u_0, Map<String, Float>>();

    public static float a(String string, float f2, float f3, u_0 u_02, D d2) {
        int n2 = d2.a();
        y_0.a(string, f2 + 0.5f, f3 + 0.5f, u_02, d2.a(lf21.utils.render.a.a(0, 0, 0, (int)((float)lf21.utils.render.a.a(d2.a()) * 0.65f * 255.0f))), true);
        d2.a(n2);
        return y_0.a(string, f2, f3, u_02, d2, false);
    }

    public static float a(String string, float f2, float f3, u_0 u_02, int n2, int n3, float f4) {
        block6: {
            if (string != null && !string.isEmpty()) break block6;
            return f2;
        }
        DrawContext drawContext = y_0.a();
        float f5 = u_02.b(string);
        float f6 = u_02.a() != null ? u_02.a().c() : u_02.f();
        float f7 = f6;
        float f8 = Math.max(0.01f, f4 * 0.5f);
        drawContext.a(f2, f3 - f7, f5, f7 * 2.0f, f8, () -> drawContext.a(string, f2, f3, u_02, y_0.a(n3)));
        return y_0.a(string, f2, f3, u_02, n2);
    }

    public static U a(AbstractTexture abstractTexture) {
        if (abstractTexture == null) {
            return null;
        }
        int n2 = abstractTexture.getId();
        int n3 = GL11.glGetTexLevelParameteri((int)3553, (int)0, (int)4096);
        int n4 = GL11.glGetTexLevelParameteri((int)3553, (int)0, (int)4097);
        if (n3 <= 0) {
            n3 = 64;
        }
        if (n4 <= 0) {
            n4 = 64;
        }
        return new U(n2, n3, n4);
    }

    public static float a(String string, float f2, float f3, u_0 u_02, D d2, boolean bl) {
        block11: {
            if (string != null && !string.isEmpty()) break block11;
            return f2;
        }
        DrawContext drawContext = y_0.a();
        int n2 = d2.a();
        float f4 = f2;
        float f5 = f3 + (float)y_0.a(u_02);
        String[] stringArray = string.split("\u00a7");
        for (int i2 = 0; i2 < stringArray.length; ++i2) {
            String string2;
            block12: {
                block13: {
                    string2 = stringArray[i2];
                    if (i2 <= 0 || string2.isEmpty()) break block12;
                    char c2 = string2.charAt(0);
                    ChatFormatting chatFormatting = ChatFormatting.getByCode((char)c2);
                    if (chatFormatting == null) break block12;
                    if (bl) break block13;
                    if (chatFormatting.getColor() == null) break block13;
                    n2 = lf21.utils.render.a.a((int)chatFormatting.getColor(), (float)(n2 >> 24 & 0xFF) / 255.0f);
                }
                string2 = string2.substring(1);
            }
            drawContext.a(string2, f4, f5, u_02, d2.a(n2));
            f4 += y_0.a(string2, u_02);
        }
        return f4;
    }

    private static int a(Color color) {
        return color.getAlpha() << 24 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
    }

    public static void a(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, D d2) {
        float[] fArray = new float[]{f6, f6, f7, f7, f8, f8, f9, f9};
        y_0.a().a(Q.a(f2, f3, f4, f5, fArray), d2);
    }

    public static void a(float f2, float f3, float f4, float f5, D d2) {
        y_0.a().a(ab.a(f2, f3, f4, f5), d2);
    }

    public static void a(float f2, float f3, float f4, float f5, float f6, int n2) {
        D d2 = y_0.a(n2);
        d2.b(f6);
        d2.a(h_0.STROKE);
        y_0.a().c(f2, f3, f4, f5, d2);
    }

    public static D a(Object object) {
        D d2;
        block6: {
            block5: {
                d2 = new D();
                if (!(object instanceof float[])) break block5;
                d2.a((float[])object);
                break block6;
            }
            if (object instanceof Color) {
                Color color = (Color)object;
                d2.a(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());
            } else if (object instanceof Integer) {
                Integer n2 = (Integer)object;
                d2.a(n2);
            }
        }
        return d2;
    }

    public static void a(float f2, float f3, float f4, float f5, float f6, Color color, float f7, float f8, float f9) {
        y_0.a().a(Q.a(f2, f3, f4, f5, f6), f8, f9, f7, 1.0f, color.getRGB());
    }

    public static float a(String string, float f2, float f3, u_0 u_02, int n2) {
        D d2 = y_0.a(n2);
        return y_0.a(string, f2, f3, u_02, d2, false);
    }

    static {
        new HashMap();
    }

    public static void a(float f2, float f3, float f4, float f5, float f6, D d2) {
        y_0.a().a(Q.a(f2, f3, f4, f5, f6), d2);
    }

    public static void a(float f2, float f3, float f4, float f5, float f6, Color color) {
        y_0.a().a(Q.a(f2, f3, f4, f5, f6), 0.0f, 0.0f, 10.0f, 1.0f, color.getRGB());
    }

    public static void a(AbstractClientPlayer abstractClientPlayer, float f2, float f3, float f4, float f5, float f6, float f7) {
        ResourceLocation resourceLocation;
        block5: {
            resourceLocation = abstractClientPlayer.getSkinTextureLocation();
            if (resourceLocation != null && ClientBase.mc.getTextureManager().getTexture(resourceLocation) != null) break block5;
            return;
        }
        AbstractTexture abstractTexture = ClientBase.mc.getTextureManager().getTexture(resourceLocation);
        int n2 = abstractTexture.getId();
        DrawContext drawContext = y_0.a();
        int n3 = (int)Math.max(0.0f, Math.min(255.0f, f6 * 255.0f)) << 24 | 0xFFFFFF;
        P p2 = DrawContext.a();
        float f8 = 0.125f;
        float f9 = 0.125f;
        float f10 = 0.25f;
        float f11 = 0.25f;
        float f12 = 0.625f;
        float f13 = 0.125f;
        float f14 = 0.75f;
        float f15 = 0.25f;
        Matrix4f matrix4f = drawContext.b().last().pose();
        p2.a(matrix4f, f2, f3, f2 + f4, f3 + f5, f7, f7, f7, f7, n3, n2, f8, f9, f10, f11);
        p2.a(matrix4f, f2, f3, f2 + f4, f3 + f5, f7, f7, f7, f7, n3, n2, f12, f13, f14, f15);
        if (abstractClientPlayer.hurtTime > 0) {
            int n4 = lf21.utils.render.a.a(new Color(255, 0, 0, abstractClientPlayer.hurtTime * 18), f6).getRGB();
            D d2 = new D().a(n4);
            drawContext.a(Q.a(f2, f3, f4, f5, f7), d2);
        }
    }

    public static void a(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Color color) {
        float[] fArray = new float[]{f6, f6, f7, f7, f8, f8, f9, f9};
        y_0.a().a(Q.a(f2, f3, f4, f5, fArray), 0.0f, 0.0f, 10.0f, 1.0f, color.getRGB());
    }

    public static void a(float f2, float f3, float f4, float f5, float f6, Color color, Color color2) {
        E e2 = new E(f2, f3, f2, f3 + f5, y_0.a(color), y_0.a(color2));
        D d2 = new D().a(e2);
        y_0.a().a(Q.a(f2, f3, f4, f5, f6), d2);
    }

    public static float b(String string, float f2, float f3, u_0 u_02, int n2) {
        D d2 = y_0.a(n2);
        float f4 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        y_0.a(string, f2 + 0.5f, f3 + 0.5f, u_02, y_0.a(lf21.utils.render.a.a(0, 0, 0, (int)(f4 * 0.65f * 255.0f))), true);
        return y_0.a(string, f2, f3, u_02, d2, false);
    }

    public static float a(String string2, u_0 u_03) {
        block4: {
            if (string2 != null && !string2.isEmpty()) break block4;
            return 0.0f;
        }
        return a.computeIfAbsent(u_03, u_02 -> new HashMap()).computeIfAbsent(string2, string -> {
            float f2 = string != null ? u_03.b(string.replaceAll("\u00a7.", "")) : 0.0f;
            return Float.valueOf(f2);
        }).floatValue();
    }

    public static float a(float f2, float f3, String string, u_0 u_02, D d2) {
        float f4 = u_02.b(string);
        float f5 = u_02.a() != null ? u_02.a().c() : u_02.f();
        float f6 = f5;
        return y_0.a(string, f2 - f4 / 2.0f, f3 - f6 / 2.0f, u_02, d2, false);
    }

    public static DrawContext a() {
        DrawContext drawContext = Renderer.a();
        if (drawContext == null) {
            throw new IllegalStateException("GlHelper.getCanvas() called outside a GlRenderer.render block");
        }
        return drawContext;
    }

    public static int a(u_0 u_02) {
        X x2 = u_02.d();
        float f2 = (x2.a() - x2.c() - x2.d()) / 2.0f;
        return (int)Math.ceil(f2);
    }

    public static void a(AbstractClientPlayer abstractClientPlayer, float f2, float f3, float f4, float f5, float f6) {
        y_0.a(abstractClientPlayer, f2, f3, f4, f5, f6, 0.0f);
    }
}

