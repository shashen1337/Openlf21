/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.FloatBuffer;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

public final class P {
    private int e = -1;
    private int a = 0;
    private int c = -1;
    private int d = -1;
    private int m = 0;
    private int i = -1;
    private int g = -1;
    private int f = -1;
    private static final String[] n;
    private int j = -1;
    private int l = 0;
    private int h = -1;
    private int b = -1;
    private int k = -1;

    public void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n2, int n3, float f10, float f11, float f12, float f13) {
        this.a(matrix4f, f2, f3, f4, f5, f6, f7, f8, f9, n2, n2, false, 0.0f, n3, f10, f11, f12, f13);
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
                    case 0 -> 103;
                    case 1 -> 88;
                    case 2 -> 105;
                    case 3 -> 119;
                    case 4 -> 114;
                    case 5 -> 75;
                    default -> 82;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static int a(int n2, String string) {
        int n3 = GL20.glCreateShader((int)n2);
        GL20.glShaderSource((int)n3, (CharSequence)string);
        GL20.glCompileShader((int)n3);
        if (GL20.glGetShaderi((int)n3, (int)35713) == 0) {
            String string2 = GL20.glGetShaderInfoLog((int)n3);
            GL20.glDeleteShader((int)n3);
            throw new IllegalStateException("RoundedRectShader shader compile failed: " + string2);
        }
        return n3;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[17];
                var3_1 = 0;
                var2_2 = "4,\u001b\u0018\u0019.\u0005\u000e<\u001d\u001f\n2+\f#\u00173&\u0012*\f\u0462D.\f\u0005\u0001\"=\txXBBA'\t1\u000f\u0018\u0000&r\u0011=\nER\u00033\u000b>:\u001e\b.im-\u0007\u001e\u0014$ \nx\u001f\u0012\u0011\u007fr59\r\u001e\u001bpX\u00126\u0000\u0011\u001d9?G.\f\u0014Fk\u0011\b4\u0006\u0005CpX\u00126\u0000\u0011\u001d9?G.\f\u0014Fk\u0011\b4\u0006\u0005@pX\u00126\u0000\u0011\u001d9?G1\u0007\u0003R\u001e!\u0002\u001f\u001b\u0016\u0016\"7\t,R}\u0007%;\u00017\u001b\u001aR\"<\u0013x<\u0004\u0017\u001f7\u001f,\u001c\u0005\u0017pX\u00126\u0000\u0011\u001d9?G+\b\u001a\u0002'7\u0015j-W!*?\u00174\f\u0005BpX\u00126\u0000\u0011\u001d9?G>\u0005\u0018\u0013?r4,\u001b\u0018\u0019.\u0005\u000e<\u001d\u001fIA;\tx\u001f\u0012\u0011yr\u000b7\n\u0016\u001e\u001b=\u0014cc\u001e\u001ck$\u0002;[W\u0007=\u0011\b7\u001b\u0013IA=\u0012,I\u0001\u0017(fG>\u001b\u0016\u0015\b=\u000b7\u001bLx==\u000e<I\u001a\u0013\"<OqI\fxkrGx\u001f\u0012\u0011yr\u0017xTW\u001e$1\u000649\u0018\u0001pXGxIW\u0014'=\u0006,I\u0005IArGxI\u001e\u0014kz\u0017v\u0011WNkbIh@W\tArGxIWRkr\u0015xTWZ;|\u001exUWBebNxVW *6\u000e1G\u000fRqr59\r\u001e\u001be%\\RIWRk/G=\u0005\u0004\u0017k)mxIWRkrGx\u001bWOkz\u0017v\u0010WNkbIh@WMk\u0000\u0006<\u0000\u001e\\2r]x;\u0016\u0016\";I\"R}RkrG%cWRkr\u0011=\nER:rZx\b\u0015\u0001c\"NxDW:*>\u0001\u000b\u0000\r\u0017kyG*R}RkrG>\u0005\u0018\u0013?r\u0003xTW\u001e.<\u0000,\u0001_\u001f**O)EW\u0004.1UpYYBb{NxBW\u001f\"<O5\b\u000fZ:|\u001ftI\u0006\\2{KxYYBbrJx\u001bLxkrGx\u000f\u001b\u001d*&G9\u0005\u0007\u001a*imxIWR\"4Gp:\u0003\u0000$9\u0002\u000f\u0000\u0013\u0006#rYxYYBbr\u001cRIWRkrGxI\u0011\u001e$3\u0013x\u0001\u0016\u001e-\u0001\u0013*\u0006\u001c\u0017koG\u000b\u001d\u0005\u001d 701\r\u0003\u001akxGhGBIArGxIWRkr\u00064\u0019\u001f\u0013koGiGGRfr\u00145\u0006\u0018\u0006#!\u0013=\u0019_\u001a*>\u0001\u000b\u001d\u0005\u001d 7GuIG\\~~G0\b\u001b\u0014\u0018&\u00157\u0002\u0012R`rWv\\[R*0\u0014p\r^[pXGxIW\u000fk7\u000b+\fW\tArGxIWRkr\u00064\u0019\u001f\u0013koGiGGRfr\u00145\u0006\u0018\u0006#!\u0013=\u0019__{|RtIG\\~~G<@LxkrGx\u0014}RkrG.\f\u0014Fk1\b4R}RkrG1\u000fWZ\u001e!\u0002\f\f\u000f\u0006> \u0002xTJRz{G#cWRkrGxIW\u0011$>GeI\u0003\u00173&\u0012*\f_!*?\u00174\f\u0005Bgr\u0012.*\u0018\u001d96NxCW1$>\b*XLxkrGx\u0014W\u0017'!\u0002x\u0000\u0011Rc\u0007\u0014=.\u0005\u0013/;\u00026\u001dWOvrVqI\fxkrGxIWRk4\u000b7\b\u0003R?rZx\n\u001b\u0013&\"Op\u0019Y\u000bkyG\u0010\b\u001b\u0014\u0018;\u001d=G\u000e[k}G5\b\u000fZy|WxCW:*>\u0001\u000b\u0000\r\u0017e+KxYYB{bVqEWBebKxXYBbimxIWRkrGx\n\u0018\u001ekoG5\u0000\u000fZ\b=\u000b7\u001bF^k\u0011\b4\u0006\u0005@gr\u0013qR}RkrG%I\u0012\u001e87G#cWRkrGxIW\u0011$>GeI4\u001d'=\u0015iR}RkrG%cWRkr\u0001*\b\u00101$>\b*IJR=7\u0004lA\u0014\u001d'|\u0015?\u000b[R(=\u000bv\bWXk3\u000b(\u0001\u0016[pX\u001aR\u00022\u000e)57\u001c\u0019\u0016.65=\n\u0003!#3\u0003=\u001bW\u0001#3\u0003=\u001bW\u0011$?\u00171\u0005\u0012R-3\u000e4\f\u0013Hk\b77\u001a\u001e\u0006\"=\t\b+7\n\u0016\u001e\u001b=\u0014\u0113D.\f\u0005\u0001\"=\txXBBA;\tx\u001f\u0012\u0011yr77\u001a\u001e\u0006\"=\tcc\u001e\u001ck$\u0002;[W>$1\u000649\u0018\u0001pX\u000e6I\u0001\u0017(`G\r?Lx><\u000e>\u0006\u0005\u001fk?\u0006,]W?$6\u00024?\u001e\u0017<\u001f\u0006,R}\u0007%;\u00017\u001b\u001aR&3\u0013lI'\u0000$8*9\u001dLx$'\u0013x\u001f\u0012\u0011yr\u000b7\n\u0016\u001e\u001b=\u0014cc\u0018\u0007?r\u0011=\nER>$$7\u0006\u0005\u0016pX\u00117\u0000\u0013R&3\u000e6A^R0XGxIW\u0015'\r77\u001a\u001e\u0006\"=\txTW\"9=\r\u0015\b\u0003Rar*7\r\u0012\u001e\u001d;\u0002/$\u0016\u0006kxG.\f\u0014Fc\u0002\b+\u0000\u0003\u001b$<KxYYBgrVvY^IArGxI\u001b\u001d(3\u000b\b\u0006\u0004Rvr+7\n\u0016\u001e\u001b=\u0014ccWRkr\u0012.*\u0018\u001d96GeI\"$pX\u001aR\u000b2+\f0\u0000*6\u000e=\u0007\u0003\u00077*\u0006\u001d?*&\b/9\u0005\u0011!\"(\u0002\u001f57\u001c\u0019\u0016.65=\n\u0003!#3\u0003=\u001bW\u001e\"<\fx\u000f\u0016\u001b'7\u0003bI\u000559\r\u001e\u001b\f*7\r\u0012\u001e\u001d;\u0002/$\u0016\u0006\b49\u0004\u0007\u001e. W";
                var4_3 = "4,\u001b\u0018\u0019.\u0005\u000e<\u001d\u001f\n2+\f#\u00173&\u0012*\f\u0462D.\f\u0005\u0001\"=\txXBBA'\t1\u000f\u0018\u0000&r\u0011=\nER\u00033\u000b>:\u001e\b.im-\u0007\u001e\u0014$ \nx\u001f\u0012\u0011\u007fr59\r\u001e\u001bpX\u00126\u0000\u0011\u001d9?G.\f\u0014Fk\u0011\b4\u0006\u0005CpX\u00126\u0000\u0011\u001d9?G.\f\u0014Fk\u0011\b4\u0006\u0005@pX\u00126\u0000\u0011\u001d9?G1\u0007\u0003R\u001e!\u0002\u001f\u001b\u0016\u0016\"7\t,R}\u0007%;\u00017\u001b\u001aR\"<\u0013x<\u0004\u0017\u001f7\u001f,\u001c\u0005\u0017pX\u00126\u0000\u0011\u001d9?G+\b\u001a\u0002'7\u0015j-W!*?\u00174\f\u0005BpX\u00126\u0000\u0011\u001d9?G>\u0005\u0018\u0013?r4,\u001b\u0018\u0019.\u0005\u000e<\u001d\u001fIA;\tx\u001f\u0012\u0011yr\u000b7\n\u0016\u001e\u001b=\u0014cc\u001e\u001ck$\u0002;[W\u0007=\u0011\b7\u001b\u0013IA=\u0012,I\u0001\u0017(fG>\u001b\u0016\u0015\b=\u000b7\u001bLx==\u000e<I\u001a\u0013\"<OqI\fxkrGx\u001f\u0012\u0011yr\u0017xTW\u001e$1\u000649\u0018\u0001pXGxIW\u0014'=\u0006,I\u0005IArGxI\u001e\u0014kz\u0017v\u0011WNkbIh@W\tArGxIWRkr\u0015xTWZ;|\u001exUWBebNxVW *6\u000e1G\u000fRqr59\r\u001e\u001be%\\RIWRk/G=\u0005\u0004\u0017k)mxIWRkrGx\u001bWOkz\u0017v\u0010WNkbIh@WMk\u0000\u0006<\u0000\u001e\\2r]x;\u0016\u0016\";I\"R}RkrG%cWRkr\u0011=\nER:rZx\b\u0015\u0001c\"NxDW:*>\u0001\u000b\u0000\r\u0017kyG*R}RkrG>\u0005\u0018\u0013?r\u0003xTW\u001e.<\u0000,\u0001_\u001f**O)EW\u0004.1UpYYBb{NxBW\u001f\"<O5\b\u000fZ:|\u001ftI\u0006\\2{KxYYBbrJx\u001bLxkrGx\u000f\u001b\u001d*&G9\u0005\u0007\u001a*imxIWR\"4Gp:\u0003\u0000$9\u0002\u000f\u0000\u0013\u0006#rYxYYBbr\u001cRIWRkrGxI\u0011\u001e$3\u0013x\u0001\u0016\u001e-\u0001\u0013*\u0006\u001c\u0017koG\u000b\u001d\u0005\u001d 701\r\u0003\u001akxGhGBIArGxIWRkr\u00064\u0019\u001f\u0013koGiGGRfr\u00145\u0006\u0018\u0006#!\u0013=\u0019_\u001a*>\u0001\u000b\u001d\u0005\u001d 7GuIG\\~~G0\b\u001b\u0014\u0018&\u00157\u0002\u0012R`rWv\\[R*0\u0014p\r^[pXGxIW\u000fk7\u000b+\fW\tArGxIWRkr\u00064\u0019\u001f\u0013koGiGGRfr\u00145\u0006\u0018\u0006#!\u0013=\u0019__{|RtIG\\~~G<@LxkrGx\u0014}RkrG.\f\u0014Fk1\b4R}RkrG1\u000fWZ\u001e!\u0002\f\f\u000f\u0006> \u0002xTJRz{G#cWRkrGxIW\u0011$>GeI\u0003\u00173&\u0012*\f_!*?\u00174\f\u0005Bgr\u0012.*\u0018\u001d96NxCW1$>\b*XLxkrGx\u0014W\u0017'!\u0002x\u0000\u0011Rc\u0007\u0014=.\u0005\u0013/;\u00026\u001dWOvrVqI\fxkrGxIWRk4\u000b7\b\u0003R?rZx\n\u001b\u0013&\"Op\u0019Y\u000bkyG\u0010\b\u001b\u0014\u0018;\u001d=G\u000e[k}G5\b\u000fZy|WxCW:*>\u0001\u000b\u0000\r\u0017e+KxYYB{bVqEWBebKxXYBbimxIWRkrGx\n\u0018\u001ekoG5\u0000\u000fZ\b=\u000b7\u001bF^k\u0011\b4\u0006\u0005@gr\u0013qR}RkrG%I\u0012\u001e87G#cWRkrGxIW\u0011$>GeI4\u001d'=\u0015iR}RkrG%cWRkr\u0001*\b\u00101$>\b*IJR=7\u0004lA\u0014\u001d'|\u0015?\u000b[R(=\u000bv\bWXk3\u000b(\u0001\u0016[pX\u001aR\u00022\u000e)57\u001c\u0019\u0016.65=\n\u0003!#3\u0003=\u001bW\u0001#3\u0003=\u001bW\u0011$?\u00171\u0005\u0012R-3\u000e4\f\u0013Hk\b77\u001a\u001e\u0006\"=\t\b+7\n\u0016\u001e\u001b=\u0014\u0113D.\f\u0005\u0001\"=\txXBBA;\tx\u001f\u0012\u0011yr77\u001a\u001e\u0006\"=\tcc\u001e\u001ck$\u0002;[W>$1\u000649\u0018\u0001pX\u000e6I\u0001\u0017(`G\r?Lx><\u000e>\u0006\u0005\u001fk?\u0006,]W?$6\u00024?\u001e\u0017<\u001f\u0006,R}\u0007%;\u00017\u001b\u001aR&3\u0013lI'\u0000$8*9\u001dLx$'\u0013x\u001f\u0012\u0011yr\u000b7\n\u0016\u001e\u001b=\u0014cc\u0018\u0007?r\u0011=\nER>$$7\u0006\u0005\u0016pX\u00117\u0000\u0013R&3\u000e6A^R0XGxIW\u0015'\r77\u001a\u001e\u0006\"=\txTW\"9=\r\u0015\b\u0003Rar*7\r\u0012\u001e\u001d;\u0002/$\u0016\u0006kxG.\f\u0014Fc\u0002\b+\u0000\u0003\u001b$<KxYYBgrVvY^IArGxI\u001b\u001d(3\u000b\b\u0006\u0004Rvr+7\n\u0016\u001e\u001b=\u0014ccWRkr\u0012.*\u0018\u001d96GeI\"$pX\u001aR\u000b2+\f0\u0000*6\u000e=\u0007\u0003\u00077*\u0006\u001d?*&\b/9\u0005\u0011!\"(\u0002\u001f57\u001c\u0019\u0016.65=\n\u0003!#3\u0003=\u001bW\u001e\"<\fx\u000f\u0016\u001b'7\u0003bI\u000559\r\u001e\u001b\f*7\r\u0012\u001e\u001d;\u0002/$\u0016\u0006\b49\u0004\u0007\u001e. W".length();
                var1_4 = 11;
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
                    var5[var3_1++] = P.a(P.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "$7\u0005\u0018\u0000y\u0006$7\u0005\u0018\u0000z";
                    var4_3 = "$7\u0005\u0018\u0000y\u0006$7\u0005\u0018\u0000z".length();
                    var1_4 = 6;
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
                    var5[var3_1++] = P.a(P.a(v1));
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
        P.n = var5;
    }

    public void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n2, int n3, boolean bl, float f10) {
        this.a(matrix4f, f2, f3, f4, f5, f6, f7, f8, f9, n2, n3, bl, f10, -1, 0.0f, 0.0f, 1.0f, 1.0f);
    }

    public void b() {
        if (this.a != 0) {
            GL20.glDeleteProgram((int)this.a);
            this.a = 0;
        }
        if (this.l != 0) {
            GL15.glDeleteBuffers((int)this.l);
            this.l = 0;
        }
        if (this.m != 0) {
            GL30.glDeleteVertexArrays((int)this.m);
            this.m = 0;
        }
    }

    public void a() {
        if (this.a != 0) {
            return;
        }
        String[] stringArray = n;
        int n2 = P.a(35633, "#version 150\nin vec2 Position;\nin vec2 LocalPos;\nin vec2 UV;\nuniform mat4 ModelViewMat;\nuniform mat4 ProjMat;\nout vec2 localPos;\nout vec2 uvCoord;\nvoid main() {\n    gl_Position = ProjMat * ModelViewMat * vec4(Position, 0.0, 1.0);\n    localPos = LocalPos;\n    uvCoord = UV;\n}\n");
        int n3 = P.a(35632, "#version 150\nuniform vec2 HalfSize;\nuniform vec4 Radii;\nuniform vec4 Color1;\nuniform vec4 Color2;\nuniform int UseGradient;\nuniform int UseTexture;\nuniform sampler2D Sampler0;\nuniform float StrokeWidth;\nin vec2 localPos;\nin vec2 uvCoord;\nout vec4 fragColor;\nvoid main() {\n    vec2 p = localPos;\n    float r;\n    if (p.x < 0.0) {\n        r = (p.y < 0.0) ? Radii.x : Radii.w;\n    } else {\n        r = (p.y < 0.0) ? Radii.y : Radii.z;\n    }\n    vec2 q = abs(p) - HalfSize + r;\n    float d = length(max(q, vec2(0.0))) + min(max(q.x, q.y), 0.0) - r;\n    float alpha;\n    if (StrokeWidth > 0.0) {\n        float halfStroke = StrokeWidth * 0.5;\n        alpha = 1.0 - smoothstep(halfStroke - 0.5, halfStroke + 0.5, abs(d));\n    } else {\n        alpha = 1.0 - smoothstep(-0.5, 0.5, d);\n    }\n    vec4 col;\n    if (UseTexture == 1) {\n        col = texture(Sampler0, uvCoord) * Color1;\n    } else if (UseGradient == 1) {\n        float t = clamp((p.y + HalfSize.y) / max(2.0 * HalfSize.y, 0.0001), 0.0, 1.0);\n        col = mix(Color1, Color2, t);\n    } else {\n        col = Color1;\n    }\n    fragColor = vec4(col.rgb, col.a * alpha);\n}\n");
        int n4 = GL20.glCreateProgram();
        GL20.glAttachShader((int)n4, (int)n2);
        GL20.glAttachShader((int)n4, (int)n3);
        GL20.glBindAttribLocation((int)n4, (int)0, (CharSequence)"Position");
        GL20.glBindAttribLocation((int)n4, (int)1, (CharSequence)"LocalPos");
        GL20.glBindAttribLocation((int)n4, (int)2, (CharSequence)"UV");
        GL20.glLinkProgram((int)n4);
        if (GL20.glGetProgrami((int)n4, (int)35714) == 0) {
            String string = GL20.glGetProgramInfoLog((int)n4);
            GL20.glDeleteProgram((int)n4);
            GL20.glDeleteShader((int)n2);
            GL20.glDeleteShader((int)n3);
            throw new IllegalStateException("RoundedRectShader link failed: " + string);
        }
        GL20.glDeleteShader((int)n2);
        GL20.glDeleteShader((int)n3);
        this.a = n4;
        stringArray = n;
        this.b = GL20.glGetUniformLocation((int)this.a, (CharSequence)"ModelViewMat");
        this.c = GL20.glGetUniformLocation((int)this.a, (CharSequence)"ProjMat");
        this.d = GL20.glGetUniformLocation((int)this.a, (CharSequence)"HalfSize");
        this.e = GL20.glGetUniformLocation((int)this.a, (CharSequence)"Radii");
        this.f = GL20.glGetUniformLocation((int)this.a, (CharSequence)"Color1");
        this.g = GL20.glGetUniformLocation((int)this.a, (CharSequence)"Color2");
        this.h = GL20.glGetUniformLocation((int)this.a, (CharSequence)"UseGradient");
        this.i = GL20.glGetUniformLocation((int)this.a, (CharSequence)"UseTexture");
        this.j = GL20.glGetUniformLocation((int)this.a, (CharSequence)"Sampler0");
        this.k = GL20.glGetUniformLocation((int)this.a, (CharSequence)"StrokeWidth");
        this.m = GL30.glGenVertexArrays();
        this.l = GL15.glGenBuffers();
        int n5 = GL11.glGetInteger((int)34229);
        int n6 = GL11.glGetInteger((int)34964);
        GL30.glBindVertexArray((int)this.m);
        GL15.glBindBuffer((int)34962, (int)this.l);
        GL15.glBufferData((int)34962, (long)144L, (int)35048);
        int n7 = 24;
        GL20.glEnableVertexAttribArray((int)0);
        GL20.glVertexAttribPointer((int)0, (int)2, (int)5126, (boolean)false, (int)n7, (long)0L);
        GL20.glEnableVertexAttribArray((int)1);
        GL20.glVertexAttribPointer((int)1, (int)2, (int)5126, (boolean)false, (int)n7, (long)8L);
        GL20.glEnableVertexAttribArray((int)2);
        GL20.glVertexAttribPointer((int)2, (int)2, (int)5126, (boolean)false, (int)n7, (long)16L);
        GL30.glBindVertexArray((int)n5);
        GL15.glBindBuffer((int)34962, (int)n6);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n2, int n3, boolean bl, float f10, int n4, float f11, float f12, float f13, float f14) {
        int n9;
        this.a();
        float f18 = (f2 + f4) * 0.5f;
        float f17 = (f3 + f5) * 0.5f;
        float f16 = (f4 - f2) * 0.5f;
        float f15 = (f5 - f3) * 0.5f;
        if (f16 <= 0.0f || f15 <= 0.0f) {
            return;
        }
        float f19 = Math.min(f16, f15);
        f6 = Math.min(Math.max(f6, 0.0f), f19);
        f7 = Math.min(Math.max(f7, 0.0f), f19);
        f8 = Math.min(Math.max(f8, 0.0f), f19);
        f9 = Math.min(Math.max(f9, 0.0f), f19);
        float f20 = f16 + 1.0f;
        float f21 = f15 + 1.0f;
        Vector4f vector4f = new Vector4f();
        float[] fArray = new float[36];
        float[][] fArrayArray = new float[][]{{-f20, -f21}, {f20, -f21}, {f20, f21}, {-f20, -f21}, {f20, f21}, {-f20, f21}};
        for (n9 = 0; n9 < 6; ++n9) {
            vector4f.set(f18 + fArrayArray[n9][0], f17 + fArrayArray[n9][1], 0.0f, 1.0f).mul((Matrix4fc)matrix4f);
            float f22 = fArrayArray[n9][0];
            float f23 = fArrayArray[n9][1];
            float f24 = f11 + (f22 + f16) / (2.0f * f16) * (f13 - f11);
            float f25 = f12 + (f23 + f15) / (2.0f * f15) * (f14 - f12);
            int n10 = n9 * 6;
            fArray[n10] = vector4f.x;
            fArray[n10 + 1] = vector4f.y;
            fArray[n10 + 2] = f22;
            fArray[n10 + 3] = f23;
            fArray[n10 + 4] = f24;
            fArray[n10 + 5] = f25;
        }
        n9 = GL11.glGetInteger((int)35725);
        int n8 = GL11.glGetInteger((int)34229);
        int n7 = GL11.glGetInteger((int)34964);
        int n6 = GL11.glGetInteger((int)34016);
        GL13.glActiveTexture((int)33984);
        int n5 = GL11.glGetInteger((int)32873);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        GL20.glUseProgram((int)this.a);
        GL30.glBindVertexArray((int)this.m);
        GL15.glBindBuffer((int)34962, (int)this.l);
        try (MemoryStack memoryStack = MemoryStack.stackPush();){
            FloatBuffer floatBuffer = memoryStack.mallocFloat(fArray.length);
            floatBuffer.put(fArray).flip();
            GL15.glBufferSubData((int)34962, (long)0L, (FloatBuffer)floatBuffer);
            FloatBuffer floatBuffer2 = memoryStack.mallocFloat(16);
            RenderSystem.getModelViewMatrix().get(floatBuffer2);
            GL20.glUniformMatrix4fv((int)this.b, (boolean)false, (FloatBuffer)floatBuffer2);
            FloatBuffer floatBuffer3 = memoryStack.mallocFloat(16);
            RenderSystem.getProjectionMatrix().get(floatBuffer3);
            GL20.glUniformMatrix4fv((int)this.c, (boolean)false, (FloatBuffer)floatBuffer3);
        }
        GL20.glUniform2f((int)this.d, (float)f16, (float)f15);
        GL20.glUniform4f((int)this.e, (float)f6, (float)f7, (float)f8, (float)f9);
        GL20.glUniform4f((int)this.f, (float)((float)(n2 >> 16 & 0xFF) / 255.0f), (float)((float)(n2 >> 8 & 0xFF) / 255.0f), (float)((float)(n2 & 0xFF) / 255.0f), (float)((float)(n2 >>> 24 & 0xFF) / 255.0f));
        GL20.glUniform4f((int)this.g, (float)((float)(n3 >> 16 & 0xFF) / 255.0f), (float)((float)(n3 >> 8 & 0xFF) / 255.0f), (float)((float)(n3 & 0xFF) / 255.0f), (float)((float)(n3 >>> 24 & 0xFF) / 255.0f));
        GL20.glUniform1i((int)this.h, (int)(bl ? 1 : 0));
        GL20.glUniform1f((int)this.k, (float)Math.max(0.0f, f10));
        if (n4 > 0) {
            GL20.glUniform1i((int)this.i, (int)1);
            GL20.glUniform1i((int)this.j, (int)0);
            GL11.glBindTexture((int)3553, (int)n4);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        } else {
            GL20.glUniform1i((int)this.i, (int)0);
        }
        GL11.glDrawArrays((int)4, (int)0, (int)6);
        GL11.glBindTexture((int)3553, (int)n5);
        GL13.glActiveTexture((int)n6);
        GL15.glBindBuffer((int)34962, (int)n7);
        GL30.glBindVertexArray((int)n8);
        GL20.glUseProgram((int)n9);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x52);
        }
        return cArray;
    }
}

