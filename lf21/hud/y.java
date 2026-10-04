/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.platform.NativeImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import lf21.ClientBase;
import lf21.event.impl.Render2DEvent;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.renderer.texture.DynamicTexture;

public final class y {
    private static final String[] c;
    private static final int a;
    private DynamicTexture b;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                y.a = 38;
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "\u0007\u001c.\u0003\u0010\u0004\u001f\u0007\u0011;BD_\u0018M\u0005)\u0005\u0007\u0015\u001f\u0007\u001a(\u0019Z\u001c\n\u001aL\u0002\u0007\u0014\u0004\tZ\u0010<\u0002\u001e^\u001cF\u001a+I\u000e.\u0015\u0001\u0003CD\u001boAZ\u0004\tP\t(\u0002\u0010\u0003CO\b4_\u0019\u0016^\u0019\"*\u0011\u0001\u0015\u001eE\u001c/\u001b[\u0000\u0002O";
                var4_3 = "\u0007\u001c.\u0003\u0010\u0004\u001f\u0007\u0011;BD_\u0018M\u0005)\u0005\u0007\u0015\u001f\u0007\u001a(\u0019Z\u001c\n\u001aL\u0002\u0007\u0014\u0004\tZ\u0010<\u0002\u001e^\u001cF\u001a+I\u000e.\u0015\u0001\u0003CD\u001boAZ\u0004\tP\t(\u0002\u0010\u0003CO\b4_\u0019\u0016^\u0019\"*\u0011\u0001\u0015\u001eE\u001c/\u001b[\u0000\u0002O".length();
                var1_4 = 44;
                var0_5 = -1;
lbl8:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl13:
                // 1 sources

                while (true) {
                    var5[var3_1++] = y.a(y.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "n\u001c4\u001c\u0010\u0014L\\\u0012}\u001c\u001a\u0011\b\b1\u001bBDP\u001bI\t8\u0002\u0018\u0011\u001eC])\u0015\r\u0004\u0019Z\u0018\u000eD\u001boA[\u0002\t[\u0012(\u0002\u0016\u0015\u001f";
                    var4_3 = "n\u001c4\u001c\u0010\u0014L\\\u0012}\u001c\u001a\u0011\b\b1\u001bBDP\u001bI\t8\u0002\u0018\u0011\u001eC])\u0015\r\u0004\u0019Z\u0018\u000eD\u001boA[\u0002\t[\u0012(\u0002\u0016\u0015\u001f".length();
                    var1_4 = 37;
                    var0_5 = -1;
lbl22:
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
lbl27:
                // 1 sources

                while (true) {
                    var5[var3_1++] = y.a(y.a(v1));
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
lbl38:
                // 1 sources

                ** continue;
            }
        }
        y.c = var5;
    }

    public void a(Render2DEvent render2DEvent, float f2, float f3, float f4) {
        if (ClientBase.mc == null || ClientBase.mc.options.renderDebug) {
            return;
        }
        this.b();
        if (this.b == null) {
            return;
        }
        RenderUtil.a(this.b.getId(), render2DEvent.a(), f2, f3, f4, f4, 1.0f, -1);
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
                    case 0 -> 40;
                    case 1 -> 125;
                    case 2 -> 93;
                    case 3 -> 112;
                    case 4 -> 117;
                    case 5 -> 112;
                    default -> 108;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6C);
        }
        return cArray;
    }

    private void b() {
        if (this.b != null) {
            return;
        }
        try {
            InputStream inputStream;
            block13: {
                inputStream = this.c();
                try {
                    if (inputStream == null) break block13;
                    this.b = new DynamicTexture(NativeImage.read((InputStream)inputStream));
                }
                catch (Throwable throwable) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        catch (Exception exception) {
            ClientBase.logger.error("Failed to load LF21 watermark texture", (Throwable)exception);
        }
    }

    private InputStream c() {
        String[] stringArray = c;
        InputStream inputStream = y.class.getResourceAsStream("/assets/lf21/textures/gui/lf21_watermark.png");
        if (inputStream != null) {
            return inputStream;
        }
        stringArray = c;
        String string = System.getProperty("lf21.resources");
        if (string == null) {
            return null;
        }
        try {
            File file = new File(string, "assets/lf21/textures/gui/lf21_watermark.png");
            FileInputStream fileInputStream = file.isFile() ? new FileInputStream(file) : null;
            return fileInputStream;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public int a() {
        return 38;
    }

    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        this.a(render2DEvent, f2, f3, 38.0f);
    }
}

