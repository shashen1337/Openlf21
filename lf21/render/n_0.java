/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.platform.GlStateManager;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

/*
 * Renamed from lf21.render.n
 */
public final class n_0 {
    private int a = 0;
    private int b = 0;
    private int d = 0;
    private int c = 0;
    private static final String[] f;
    private static final String e;

    public void a() {
        GL30.glBindFramebuffer((int)36160, (int)this.a);
        GL11.glViewport((int)0, (int)0, (int)this.c, (int)this.d);
    }

    public int e() {
        return this.d;
    }

    public int d() {
        return this.c;
    }

    public void a(int n2, int n3) {
        if (n2 <= 0 || n3 <= 0) {
            return;
        }
        if (this.a != 0 && n2 == this.c && n3 == this.d) {
            return;
        }
        this.g();
        this.c = n2;
        this.d = n3;
        this.a = GL30.glGenFramebuffers();
        this.b = GL11.glGenTextures();
        int n4 = GL11.glGetInteger((int)32873);
        int n5 = GL11.glGetInteger((int)36006);
        GL11.glBindTexture((int)3553, (int)this.b);
        GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)n2, (int)n3, (int)0, (int)6408, (int)5121, (ByteBuffer)null);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
        GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
        GL30.glBindFramebuffer((int)36160, (int)this.a);
        GL30.glFramebufferTexture2D((int)36160, (int)36064, (int)3553, (int)this.b, (int)0);
        int n6 = GL30.glCheckFramebufferStatus((int)36160);
        if (n6 != 36053) {
            System.err.println("[BlurFbo] incomplete framebuffer status=0x" + Integer.toHexString(n6));
        }
        GL30.glBindFramebuffer((int)36160, (int)n5);
        GL11.glBindTexture((int)3553, (int)n4);
        GlStateManager._bindTexture((int)n4);
    }

    public int f() {
        return this.a;
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
                    case 0 -> 112;
                    case 1 -> 49;
                    case 2 -> 30;
                    case 3 -> 104;
                    case 4 -> 52;
                    case 5 -> 57;
                    default -> 53;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public int c() {
        return this.b;
    }

    public void g() {
        if (this.a != 0) {
            GL30.glDeleteFramebuffers((int)this.a);
            this.a = 0;
        }
        if (this.b != 0) {
            GL11.glDeleteTextures((int)this.b);
            this.b = 0;
        }
        this.c = 0;
        this.d = 0;
    }

    public void b() {
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glClear((int)16384);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "+sr\u001dF\u007fW\u001fl>\u0001ZZZ\u001dAr\r@\\\u0015\u0016C\u007f\u0005Q[@\u0016W{\u001a\u0014JA\u0011Ek\u001b\t\tM*+sr\u001dF\u007fW\u001fl>\u0001ZZZ\u001dAr\r@\\\u0015\u0016C\u007f\u0005Q[@\u0016W{\u001a\u0014JA\u0011Ek\u001b\t\tM";
        int n3 = "+sr\u001dF\u007fW\u001fl>\u0001ZZZ\u001dAr\r@\\\u0015\u0016C\u007f\u0005Q[@\u0016W{\u001a\u0014JA\u0011Ek\u001b\t\tM*+sr\u001dF\u007fW\u001fl>\u0001ZZZ\u001dAr\r@\\\u0015\u0016C\u007f\u0005Q[@\u0016W{\u001a\u0014JA\u0011Ek\u001b\t\tM".length();
        int n4 = 42;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = n_0.a(n_0.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                f = stringArray;
                e = f[0];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x35);
        }
        return cArray;
    }
}

