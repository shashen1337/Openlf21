/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.nio.IntBuffer;
import java.util.ArrayList;
import lf21.ClientBase;
import lf21.render.W;
import lf21.render.w_0;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

/*
 * Renamed from lf21.render.z
 */
class z_0 {
    final ResourceLocation d;
    private static final String[] j;
    int g;
    int h;
    final Font c;
    private final Char2ObjectArrayMap<W> f = new Char2ObjectArrayMap();
    final char b;
    final int e;
    boolean i = false;
    final char a;

    public void b() {
        Object object;
        if (this.i) {
            return;
        }
        int n2 = this.b - this.a - 1;
        int n3 = (int)(Math.ceil(Math.sqrt(n2)) * 1.5);
        this.f.clear();
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        ArrayList<W> arrayList = new ArrayList<W>();
        AffineTransform affineTransform = new AffineTransform();
        FontRenderContext fontRenderContext = new FontRenderContext(affineTransform, true, false);
        while (n4 <= n2) {
            char c2 = (char)(this.a + n4);
            object = this.c(c2);
            Rectangle2D rectangle2D = ((Font)object).getStringBounds(String.valueOf(c2), fontRenderContext);
            int n11 = (int)Math.ceil(rectangle2D.getWidth());
            int n12 = (int)Math.ceil(rectangle2D.getHeight());
            ++n4;
            n6 = Math.max(n6, n8 + n11);
            n7 = Math.max(n7, n9 + n12);
            if (n5 >= n3) {
                n8 = 0;
                n9 += n10 + this.e;
                n5 = 0;
                n10 = 0;
            }
            n10 = Math.max(n10, n12);
            arrayList.add(new W(n8, n9, n11, n12, c2, this));
            n8 += n11 + this.e;
            ++n5;
        }
        BufferedImage bufferedImage = new BufferedImage(Math.max(n6 + this.e, 1), Math.max(n7 + this.e, 1), 2);
        this.g = bufferedImage.getWidth();
        this.h = bufferedImage.getHeight();
        object = bufferedImage.createGraphics();
        ((Graphics)object).setColor(new Color(255, 255, 255, 1));
        ((Graphics)object).fillRect(0, 0, this.g, this.h);
        ((Graphics)object).setColor(Color.WHITE);
        ((Graphics2D)object).setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        ((Graphics2D)object).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D)object).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        ((Graphics2D)object).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        ((Graphics2D)object).setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        for (W w2 : arrayList) {
            ((Graphics)object).setFont(this.c(w2.e()));
            FontMetrics fontMetrics = ((Graphics)object).getFontMetrics();
            ((Graphics2D)object).drawString(String.valueOf(w2.e()), w2.a(), w2.b() + fontMetrics.getAscent());
            this.f.put(w2.e(), (Object)w2);
        }
        z_0.a(this.d, bufferedImage);
        this.i = true;
    }

    public z_0(char c2, char c3, Font font, ResourceLocation resourceLocation, int n2) {
        this.a = c2;
        this.b = c3;
        this.c = font;
        this.d = resourceLocation;
        this.e = n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(ResourceLocation resourceLocation, BufferedImage bufferedImage) {
        try {
            int n2 = bufferedImage.getWidth();
            int n3 = bufferedImage.getHeight();
            NativeImage nativeImage = new NativeImage(NativeImage.Format.RGBA, n2, n3, false);
            String[] stringArray = j;
            long l2 = (Long)ReflectionUtil.getStaticField(nativeImage, "pixels", "com/mojang/blaze3d/platform/NativeImage");
            IntBuffer intBuffer = MemoryUtil.memIntBuffer((long)l2, (int)(nativeImage.getWidth() * nativeImage.getHeight()));
            boolean bl = false;
            WritableRaster writableRaster = bufferedImage.getRaster();
            ColorModel colorModel = bufferedImage.getColorModel();
            int n4 = writableRaster.getNumBands();
            int n5 = writableRaster.getDataBuffer().getDataType();
            Object[] objectArray = switch (n5) {
                case 0 -> new byte[n4];
                case 1 -> (Object[])new short[n4];
                case 3 -> (Object[])new int[n4];
                case 4 -> (Object[])new float[n4];
                case 5 -> (Object[])new double[n4];
                default -> throw new IllegalArgumentException("Unknown data buffer type: " + n5);
            };
            for (int i2 = 0; i2 < n3; ++i2) {
                for (int i3 = 0; i3 < n2; ++i3) {
                    writableRaster.getDataElements(i3, i2, objectArray);
                    int n6 = colorModel.getAlpha(objectArray);
                    int n7 = colorModel.getRed(objectArray);
                    int n8 = colorModel.getGreen(objectArray);
                    int n9 = colorModel.getBlue(objectArray);
                    int n10 = n6 << 24 | n9 << 16 | n8 << 8 | n7;
                    intBuffer.put(n10);
                }
            }
            DynamicTexture dynamicTexture = new DynamicTexture(nativeImage);
            dynamicTexture.upload();
            RenderSystem.bindTexture((int)dynamicTexture.getId());
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            if (RenderSystem.isOnRenderThread()) {
                ClientBase.mc.getTextureManager().register(resourceLocation, (AbstractTexture)dynamicTexture);
                return;
            }
            RenderSystem.recordRenderCall(() -> ClientBase.mc.getTextureManager().register(resourceLocation, (AbstractTexture)dynamicTexture));
            return;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

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
                    case 0 -> 17;
                    case 1 -> 116;
                    case 2 -> 69;
                    case 3 -> 32;
                    case 4 -> 95;
                    case 5 -> 96;
                    default -> 99;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "a\u001d=E3\u0013'r\u001b(\u000f2\u000f\tp\u001a\"\u000f=\f\u0002k\u0011vDp\u0010\u000fp\u0000#O-\rL_\u00151I)\u0005*|\u0015\"E\u001aD\u001a.N0\u0017\r1\u0010$T>@\u0001d\u0012#E-@\u0017h\u0004 \u001a\u007f";
        int n3 = "a\u001d=E3\u0013'r\u001b(\u000f2\u000f\tp\u001a\"\u000f=\f\u0002k\u0011vDp\u0010\u000fp\u0000#O-\rL_\u00151I)\u0005*|\u0015\"E\u001aD\u001a.N0\u0017\r1\u0010$T>@\u0001d\u0012#E-@\u0017h\u0004 \u001a\u007f".length();
        int n4 = 6;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = z_0.a(z_0.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                j = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private Font c(char c2) {
        Font font;
        block8: {
            block7: {
                if (this.c.canDisplay(c2)) {
                    return this.c;
                }
                Font font2 = w_0.a(this.c.getSize2D());
                if (font2 == null || !font2.canDisplay(c2)) break block7;
                font = font2;
                break block8;
            }
            font = this.c;
        }
        return font;
    }

    public boolean b(char c2) {
        boolean bl;
        block5: {
            block4: {
                if (c2 < this.a || c2 >= this.b) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public W a(char c2) {
        if (!this.i) {
            this.b();
        }
        return (W)this.f.get(c2);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x63);
        }
        return cArray;
    }

    public void a() {
        ClientBase.mc.getTextureManager().release(this.d);
        this.f.clear();
        this.g = -1;
        this.h = -1;
        this.i = false;
    }
}

