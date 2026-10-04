/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Kernel;

/*
 * Renamed from lf21.render.x
 */
public class x_0 {
    protected float a;
    protected Kernel b;
    private static final String c;
    private static final String[] d;

    public BufferedImage a(BufferedImage bufferedImage, BufferedImage bufferedImage2) {
        int n2 = bufferedImage.getWidth();
        int n3 = bufferedImage.getHeight();
        if (bufferedImage2 == null) {
            bufferedImage2 = this.a(bufferedImage, (ColorModel)null);
        }
        int[] nArray = new int[n2 * n3];
        int[] nArray2 = new int[n2 * n3];
        bufferedImage.getRGB(0, 0, n2, n3, nArray, 0, n2);
        if (this.a > 0.0f) {
            x_0.a(this.b, nArray, nArray2, n2, n3, true, true, false, 1);
            x_0.a(this.b, nArray2, nArray, n3, n2, true, false, true, 1);
        }
        bufferedImage2.setRGB(0, 0, n2, n3, nArray, 0, n2);
        return bufferedImage2;
    }

    public x_0(float f2) {
        this.b(f2);
    }

    public BufferedImage a(BufferedImage bufferedImage, ColorModel colorModel) {
        if (colorModel == null) {
            colorModel = bufferedImage.getColorModel();
        }
        return new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(bufferedImage.getWidth(), bufferedImage.getHeight()), colorModel.isAlphaPremultiplied(), null);
    }

    public String toString() {
        return "Blur/Gaussian Blur...";
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
                    case 0 -> 53;
                    case 1 -> 111;
                    case 2 -> 113;
                    case 3 -> 114;
                    case 4 -> 30;
                    case 5 -> 25;
                    default -> 37;
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
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "w\u0003\u0004\u00001^D@\u001c\u0002\u001b\u007fw\u0005w\u0003\u0004\u000007\u000b\u0015w\u0003\u0004\u00001^D@\u001c\u0002\u001b\u007fw\u0005w\u0003\u0004\u000007\u000b";
        int n3 = "w\u0003\u0004\u00001^D@\u001c\u0002\u001b\u007fw\u0005w\u0003\u0004\u000007\u000b\u0015w\u0003\u0004\u00001^D@\u001c\u0002\u001b\u007fw\u0005w\u0003\u0004\u000007\u000b".length();
        int n4 = 21;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = x_0.a(x_0.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                d = stringArray;
                c = d[1];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public void b(float f2) {
        this.a = f2;
        this.b = x_0.a(f2);
    }

    public static Kernel a(float f2) {
        int n2;
        int n3 = (int)Math.ceil(f2);
        int n4 = n3 * 2 + 1;
        float[] fArray = new float[n4];
        float f3 = f2 / 3.0f;
        float f4 = 2.0f * f3 * f3;
        float f5 = (float)Math.PI * 2 * f3;
        float f6 = (float)Math.sqrt(f5);
        float f7 = f2 * f2;
        float f8 = 0.0f;
        int n5 = 0;
        for (n2 = -n3; n2 <= n3; ++n2) {
            float f9 = n2 * n2;
            fArray[n5] = f9 > f7 ? 0.0f : (float)Math.exp(-f9 / f4) / f6;
            f8 += fArray[n5];
            ++n5;
        }
        for (n2 = 0; n2 < n4; ++n2) {
            fArray[n2] = fArray[n2] / f8;
        }
        return new Kernel(n4, 1, fArray);
    }

    public static void a(Kernel kernel, int[] nArray, int[] nArray2, int n2, int n3, boolean bl, boolean bl2, boolean bl3, int n4) {
        float[] fArray = kernel.getKernelData(null);
        int n5 = kernel.getWidth();
        int n6 = n5 / 2;
        for (int i2 = 0; i2 < n3; ++i2) {
            int n7 = i2;
            int n8 = i2 * n2;
            for (int i3 = 0; i3 < n2; ++i3) {
                int n9;
                int n10;
                float f2;
                int n11;
                float f3 = 0.0f;
                float f4 = 0.0f;
                float f5 = 0.0f;
                float f6 = 0.0f;
                int n12 = n6;
                for (n11 = -n6; n11 <= n6; ++n11) {
                    f2 = fArray[n12 + n11];
                    if (f2 == 0.0f) continue;
                    n10 = i3 + n11;
                    if (n10 < 0) {
                        if (n4 == 1) {
                            n10 = 0;
                        } else if (n4 == 2) {
                            n10 = (i3 + n2) % n2;
                        }
                    } else if (n10 >= n2) {
                        if (n4 == 1) {
                            n10 = n2 - 1;
                        } else if (n4 == 2) {
                            n10 = (i3 + n2) % n2;
                        }
                    }
                    n9 = nArray[n8 + n10];
                    int n13 = n9 >> 24 & 0xFF;
                    int n14 = n9 >> 16 & 0xFF;
                    int n15 = n9 >> 8 & 0xFF;
                    int n16 = n9 & 0xFF;
                    if (bl2) {
                        float f7 = (float)n13 * 0.003921569f;
                        n14 = (int)((float)n14 * f7);
                        n15 = (int)((float)n15 * f7);
                        n16 = (int)((float)n16 * f7);
                    }
                    f6 += f2 * (float)n13;
                    f3 += f2 * (float)n14;
                    f4 += f2 * (float)n15;
                    f5 += f2 * (float)n16;
                }
                if (bl3 && f6 != 0.0f && f6 != 255.0f) {
                    f2 = 255.0f / f6;
                    f3 *= f2;
                    f4 *= f2;
                    f5 *= f2;
                }
                n11 = bl ? x_0.a((int)((double)f6 + 0.5)) : 255;
                int n17 = x_0.a((int)((double)f3 + 0.5));
                n10 = x_0.a((int)((double)f4 + 0.5));
                n9 = x_0.a((int)((double)f5 + 0.5));
                nArray2[n7] = n11 << 24 | n17 << 16 | n10 << 8 | n9;
                n7 += n3;
            }
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x25);
        }
        return cArray;
    }

    public static int a(int n2) {
        if (n2 < 0) {
            return 0;
        }
        return Math.min(n2, 255);
    }
}

