/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

/*
 * Renamed from lf21.render.p
 */
public final class p_0 {
    private int e = -1;
    private static final String[] h;
    private int f = 0;
    private int c = -1;
    private int a = 0;
    private int b = -1;
    private int d = -1;
    private int g = 0;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[11];
                var3_1 = 0;
                var2_2 = "-JqZ\u001c\u00023`\u001c%\u001d_a)`UrG\u001d\u0006|}]yX\u0003\u000e.<x4{\u000e\u0006,bYf\u0018Ta)`UrG\u001d\u0006|xYw\u001aO)0{NPA\u001dPV{R}N\u0000\u00191.JqK]K\bkDqD<\u0002&k\u0007\u001e]\u0001\u0002:aNy\b\t\u00073oH4z\u000e\u000f5{O/\"\u0006\u0005|xYw\u001aO\u001f9v\u007f{G\u001d\u000fg\u0004Sa\\O\u001d9m\b4N\u001d\n;MSxG\u001dPVxS}LO\u0006=gR<\u0001O\u0010V.\u001c4\b\t\u00073oH4[\u0006\f1o\u001c)\b\u0002\n$&nuL\u0006\u001e/\"\u001c%\u0006_Bg\u0004\u001c4\bO\u00022z\u001c|I\u0003\r\u0017.\u00014A\u0001\u001ftmY}DG\u00185iQu\bEKn \f=\u0001Ta|.\u001c4A\tKtf]xN$Kb.\u000e \u0001O\u0003=bZ_\bRKn:\u0007\u001e\bOK|hP{I\u001bK(ySGA\b\u0006=]M4\u0015OYr>\u001c>\b\u001c\u0002;c]4\u0002O\u00185iQu\u0013eK|.\u001cbM\f_|}Iy\bRK*k_ \u0000_El'\u0007\u001e\bOK|hP{I\u001bK+kUs@\u001b8)c\u001c)\b_El564\bOK:aN4\u0000\u0006\u0005(.U4\u0015OF4oPrcTK5.\u0000)\b\u0007\n0hw/\b\u0006@w'\u001co\"OK|.\u001c4\bO\r0a]`\b\u0018Ka.YlXGF:bSu\\G\u0002|$\u001c}\u0001OD|zK{{\u0006\f1ooe\u0001Ta|.\u001c4\bOK|xYw\u001aO\u0004:hOq\\OV|LPaZ+\u0002..\u00164N\u0003\u0004=z\u0014}\u0001OA|ZYlM\u000385tY/\"OK|.\u001c4\bO\u0018)c\u001c?\u0015O\u001f9vHaZ\nC\u000foQdD\n\u0019l\"\u001c`M\u0017(3aNp\bDK3hZgM\u001bB|$\u001cc\u0013eK|.\u001c4\bOK+kUs@\u001b8)c\u001c?\u0015O\u001cg\u0004\u001c4\bO\u0016V.\u001c4\b\t\u0019=i\u007f{D\u0000\u0019|3\u001cg]\u0002Ks.QuPG\u001c9g[|\\<\u001e1\"\u001c$\u0006_[l?\u0015/\"\u0012a\u0007LPaZ+\u0002.\tZYlM\u000385tY\u0019LPaZO\u00184oXqZO\u00075`W4N\u000e\u00020kX.\b\b]]yX\u0003\u000e.>\u0006\\]pA\u001a\u0018\u0003[j$\u008a-JqZ\u001c\u00023`\u001c%\u001d_a5`\u001cbM\fX|^SgA\u001b\u00023`\u0007\u001eA\u0001K*k_&\b:=l56{]\u001bK*k_&\b\u001b\u000e$MS{Z\u000bPVxS}LO\u0006=gR<\u0001O\u0010V.\u001c4\b\b\u0007\u0003^SgA\u001b\u00023`\u001c)\b\u0019\u000e?:\u0014DG\u001c\u0002(gSz\u0004OZr>\u0015/\"OK|.HqP,\u00043|X4\u0015O>\n>\u0007\u001eUe\fLPaZO\u00184oXqZO";
                var4_3 = "-JqZ\u001c\u00023`\u001c%\u001d_a)`UrG\u001d\u0006|}]yX\u0003\u000e.<x4{\u000e\u0006,bYf\u0018Ta)`UrG\u001d\u0006|xYw\u001aO)0{NPA\u001dPV{R}N\u0000\u00191.JqK]K\bkDqD<\u0002&k\u0007\u001e]\u0001\u0002:aNy\b\t\u00073oH4z\u000e\u000f5{O/\"\u0006\u0005|xYw\u001aO\u001f9v\u007f{G\u001d\u000fg\u0004Sa\\O\u001d9m\b4N\u001d\n;MSxG\u001dPVxS}LO\u0006=gR<\u0001O\u0010V.\u001c4\b\t\u00073oH4[\u0006\f1o\u001c)\b\u0002\n$&nuL\u0006\u001e/\"\u001c%\u0006_Bg\u0004\u001c4\bO\u00022z\u001c|I\u0003\r\u0017.\u00014A\u0001\u001ftmY}DG\u00185iQu\bEKn \f=\u0001Ta|.\u001c4A\tKtf]xN$Kb.\u000e \u0001O\u0003=bZ_\bRKn:\u0007\u001e\bOK|hP{I\u001bK(ySGA\b\u0006=]M4\u0015OYr>\u001c>\b\u001c\u0002;c]4\u0002O\u00185iQu\u0013eK|.\u001cbM\f_|}Iy\bRK*k_ \u0000_El'\u0007\u001e\bOK|hP{I\u001bK+kUs@\u001b8)c\u001c)\b_El564\bOK:aN4\u0000\u0006\u0005(.U4\u0015OF4oPrcTK5.\u0000)\b\u0007\n0hw/\b\u0006@w'\u001co\"OK|.\u001c4\bO\r0a]`\b\u0018Ka.YlXGF:bSu\\G\u0002|$\u001c}\u0001OD|zK{{\u0006\f1ooe\u0001Ta|.\u001c4\bOK|xYw\u001aO\u0004:hOq\\OV|LPaZ+\u0002..\u00164N\u0003\u0004=z\u0014}\u0001OA|ZYlM\u000385tY/\"OK|.\u001c4\bO\u0018)c\u001c?\u0015O\u001f9vHaZ\nC\u000foQdD\n\u0019l\"\u001c`M\u0017(3aNp\bDK3hZgM\u001bB|$\u001cc\u0013eK|.\u001c4\bOK+kUs@\u001b8)c\u001c?\u0015O\u001cg\u0004\u001c4\bO\u0016V.\u001c4\b\t\u0019=i\u007f{D\u0000\u0019|3\u001cg]\u0002Ks.QuPG\u001c9g[|\\<\u001e1\"\u001c$\u0006_[l?\u0015/\"\u0012a\u0007LPaZ+\u0002.\tZYlM\u000385tY\u0019LPaZO\u00184oXqZO\u00075`W4N\u000e\u00020kX.\b\b]]yX\u0003\u000e.>\u0006\\]pA\u001a\u0018\u0003[j$\u008a-JqZ\u001c\u00023`\u001c%\u001d_a5`\u001cbM\fX|^SgA\u001b\u00023`\u0007\u001eA\u0001K*k_&\b:=l56{]\u001bK*k_&\b\u001b\u000e$MS{Z\u000bPVxS}LO\u0006=gR<\u0001O\u0010V.\u001c4\b\b\u0007\u0003^SgA\u001b\u00023`\u001c)\b\u0019\u000e?:\u0014DG\u001c\u0002(gSz\u0004OZr>\u0015/\"OK|.HqP,\u00043|X4\u0015O>\n>\u0007\u001eUe\fLPaZO\u00184oXqZO".length();
                var1_4 = 650;
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
                    var5[var3_1++] = p_0.a(p_0.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "^SgA\u001b\u00023`\u0011._{E\u001f\u00020k\u001crI\u0006\u00079j\u00064";
                    var4_3 = "^SgA\u001b\u00023`\u0011._{E\u001f\u00020k\u001crI\u0006\u00079j\u00064".length();
                    var1_4 = 8;
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
                    var5[var3_1++] = p_0.a(p_0.a(v1));
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
        p_0.h = var5;
    }

    private static void a(FloatBuffer floatBuffer, float f2, float f3, float f4, float f5, float f6) {
        floatBuffer.put(f2).put(f3).put(f4).put(f5).put(f6);
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
                    case 0 -> 14;
                    case 1 -> 60;
                    case 2 -> 20;
                    case 3 -> 40;
                    case 4 -> 111;
                    case 5 -> 107;
                    default -> 92;
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
            cArray[0] = (char)(cArray[0] ^ 0x5C);
        }
        return cArray;
    }

    public void b() {
        if (this.a != 0) {
            GL20.glDeleteProgram((int)this.a);
            this.a = 0;
        }
        if (this.f != 0) {
            GL15.glDeleteBuffers((int)this.f);
            this.f = 0;
        }
        if (this.g != 0) {
            GL30.glDeleteVertexArrays((int)this.g);
            this.g = 0;
        }
    }

    private static int a(int n2, String string) {
        int n3 = GL20.glCreateShader((int)n2);
        GL20.glShaderSource((int)n3, (CharSequence)string);
        GL20.glCompileShader((int)n3);
        if (GL20.glGetShaderi((int)n3, (int)35713) == 0) {
            String string2 = GL20.glGetShaderInfoLog((int)n3);
            GL20.glDeleteShader((int)n3);
            String[] stringArray = h;
            throw new IllegalStateException("Blur shader " + n2 + " compile failed: " + string2);
        }
        return n3;
    }

    public void a(int n2, float f2, float f3, int n3, int n4, float f4) {
        int n5 = GL11.glGetInteger((int)35725);
        int n6 = GL11.glGetInteger((int)34229);
        int n7 = GL11.glGetInteger((int)34016);
        GL13.glActiveTexture((int)33984);
        int n8 = GL11.glGetInteger((int)32873);
        GL20.glUseProgram((int)this.a);
        GL20.glUniform2f((int)this.c, (float)f2, (float)f3);
        GL20.glUniform2f((int)this.d, (float)(1.0f / (float)n3), (float)(1.0f / (float)n4));
        GL20.glUniform1f((int)this.e, (float)Math.max(f4, 1.0f));
        GL20.glUniform1i((int)this.b, (int)0);
        GL11.glBindTexture((int)3553, (int)n2);
        GL30.glBindVertexArray((int)this.g);
        GL11.glDrawArrays((int)4, (int)0, (int)6);
        GL11.glBindTexture((int)3553, (int)n8);
        GL13.glActiveTexture((int)n7);
        GL30.glBindVertexArray((int)n6);
        GL20.glUseProgram((int)n5);
    }

    public void a() {
        if (this.a != 0) {
            return;
        }
        String[] stringArray = h;
        int n2 = p_0.a(35633, "#version 150\nin vec3 Position;\nin vec2 UV0;\nout vec2 texCoord;\nvoid main() {\n    gl_Position = vec4(Position, 1.0);\n    texCoord = UV0;\n}\n");
        int n3 = p_0.a(35632, "#version 150\nuniform sampler2D Sampler0;\nuniform vec2 BlurDir;\nuniform vec2 TexelSize;\nuniform float Radius;\nin vec2 texCoord;\nout vec4 fragColor;\nvoid main() {\n    float sigma = max(Radius, 1.0);\n    int halfK = int(ceil(sigma * 2.0));\n    if (halfK > 24) halfK = 24;\n    float twoSigmaSq = 2.0 * sigma * sigma;\n    vec4 sum = vec4(0.0);\n    float weightSum = 0.0;\n    for (int i = -halfK; i <= halfK; i++) {\n        float w = exp(-float(i * i) / twoSigmaSq);\n        vec2 offset = BlurDir * float(i) * TexelSize;\n        sum += texture(Sampler0, texCoord + offset) * w;\n        weightSum += w;\n    }\n    fragColor = sum / max(weightSum, 0.0001);\n}\n");
        int n4 = GL20.glCreateProgram();
        GL20.glAttachShader((int)n4, (int)n2);
        GL20.glAttachShader((int)n4, (int)n3);
        GL20.glBindAttribLocation((int)n4, (int)0, (CharSequence)"Position");
        GL20.glBindAttribLocation((int)n4, (int)1, (CharSequence)"UV0");
        GL20.glLinkProgram((int)n4);
        if (GL20.glGetProgrami((int)n4, (int)35714) == 0) {
            String string = GL20.glGetProgramInfoLog((int)n4);
            GL20.glDeleteProgram((int)n4);
            GL20.glDeleteShader((int)n2);
            GL20.glDeleteShader((int)n3);
            throw new IllegalStateException("Blur shader link failed: " + string);
        }
        GL20.glDeleteShader((int)n2);
        GL20.glDeleteShader((int)n3);
        this.a = n4;
        stringArray = h;
        this.b = GL20.glGetUniformLocation((int)this.a, (CharSequence)"Sampler0");
        this.c = GL20.glGetUniformLocation((int)this.a, (CharSequence)"BlurDir");
        this.d = GL20.glGetUniformLocation((int)this.a, (CharSequence)"TexelSize");
        this.e = GL20.glGetUniformLocation((int)this.a, (CharSequence)"Radius");
        this.g = GL30.glGenVertexArrays();
        this.f = GL15.glGenBuffers();
        int n5 = GL11.glGetInteger((int)34229);
        int n6 = GL11.glGetInteger((int)34964);
        GL30.glBindVertexArray((int)this.g);
        GL15.glBindBuffer((int)34962, (int)this.f);
        try (MemoryStack memoryStack = MemoryStack.stackPush();){
            FloatBuffer floatBuffer = memoryStack.mallocFloat(30);
            p_0.a(floatBuffer, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f);
            p_0.a(floatBuffer, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f);
            p_0.a(floatBuffer, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f);
            p_0.a(floatBuffer, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f);
            p_0.a(floatBuffer, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f);
            p_0.a(floatBuffer, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f);
            floatBuffer.flip();
            GL15.glBufferData((int)34962, (FloatBuffer)floatBuffer, (int)35044);
        }
        GL20.glEnableVertexAttribArray((int)0);
        GL20.glVertexAttribPointer((int)0, (int)3, (int)5126, (boolean)false, (int)20, (long)0L);
        GL20.glEnableVertexAttribArray((int)1);
        GL20.glVertexAttribPointer((int)1, (int)2, (int)5126, (boolean)false, (int)20, (long)12L);
        GL30.glBindVertexArray((int)n5);
        GL15.glBindBuffer((int)34962, (int)n6);
    }
}

