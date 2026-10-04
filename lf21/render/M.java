/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.logging.LogUtils;
import java.nio.FloatBuffer;
import lf21.render.n_0;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;

public final class M {
    private int f;
    private int l;
    private int g;
    private static final Logger a;
    private int m;
    private int k;
    private int j;
    private int i;
    private boolean q;
    private static final int b;
    private final n_0 e = new n_0();
    private int p = -1;
    private static final String c;
    private static final String d;
    private int o;
    private int h;
    private int n;
    private static final String[] s;
    private boolean r;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a() {
        if (this.q) {
            return;
        }
        this.q = true;
        String[] stringArray = s;
        int n2 = M.a(35633, "#version 150\nin vec3 Position;\nin vec2 UV0;\nout vec2 TexCoord;\n\nvoid main() {\n    gl_Position = vec4(Position, 1.0);\n    TexCoord = UV0;\n}\n");
        int n3 = M.a(35632, "#version 150\nin vec2 TexCoord;\n\nuniform sampler2D DiffuseSampler;\nuniform sampler2D OriginalSampler;\nuniform vec2 TexelSize;\nuniform vec2 Direction;\nuniform float Radius;\nuniform float Kernel[31];\nuniform float Intensity;\n\nout vec4 FragColor;\n\nvoid main() {\n    float sourceAlpha = texture(OriginalSampler, TexCoord).a;\n    if (Direction.x == 0.0 && sourceAlpha > 0.0) {\n        discard;\n    }\n\n    vec4 kernel = texture(DiffuseSampler, TexCoord);\n    vec4 pixelColor = kernel * Kernel[0];\n    pixelColor.rgb *= pixelColor.a;\n\n    for (int i = 0; i <= 30; ++i) {\n        if (float(i) > Radius) break;\n        vec2 offset = TexelSize * Direction * float(i);\n        vec4 left = texture(DiffuseSampler, TexCoord - offset);\n        vec4 right = texture(DiffuseSampler, TexCoord + offset);\n        left.rgb *= left.a;\n        right.rgb *= right.a;\n        pixelColor += (left + right) * Kernel[i];\n    }\n\n    pixelColor.rgb /= pixelColor.a;\n    if (Direction.x == 0.0) pixelColor.a *= Intensity;\n    FragColor = pixelColor;\n}\n");
        if (n2 == 0 || n3 == 0) {
            if (n2 != 0) {
                GL20.glDeleteShader((int)n2);
            }
            if (n3 != 0) {
                GL20.glDeleteShader((int)n3);
            }
            return;
        }
        this.f = GL20.glCreateProgram();
        GL20.glAttachShader((int)this.f, (int)n2);
        GL20.glAttachShader((int)this.f, (int)n3);
        GL20.glBindAttribLocation((int)this.f, (int)0, (CharSequence)"Position");
        GL20.glBindAttribLocation((int)this.f, (int)1, (CharSequence)"UV0");
        GL20.glLinkProgram((int)this.f);
        GL20.glDeleteShader((int)n2);
        GL20.glDeleteShader((int)n3);
        if (GL20.glGetProgrami((int)this.f, (int)35714) == 0) {
            a.error("Rise bloom shader link failed: {}", (Object)GL20.glGetProgramInfoLog((int)this.f));
            GL20.glDeleteProgram((int)this.f);
            this.f = 0;
            return;
        }
        stringArray = s;
        this.i = GL20.glGetUniformLocation((int)this.f, (CharSequence)"DiffuseSampler");
        this.j = GL20.glGetUniformLocation((int)this.f, (CharSequence)"OriginalSampler");
        this.k = GL20.glGetUniformLocation((int)this.f, (CharSequence)"TexelSize");
        this.l = GL20.glGetUniformLocation((int)this.f, (CharSequence)"Direction");
        this.m = GL20.glGetUniformLocation((int)this.f, (CharSequence)"Radius");
        this.n = GL20.glGetUniformLocation((int)this.f, (CharSequence)"Kernel");
        this.o = GL20.glGetUniformLocation((int)this.f, (CharSequence)"Intensity");
        this.g = GL30.glGenVertexArrays();
        this.h = GL15.glGenBuffers();
        int n4 = GL11.glGetInteger((int)34229);
        int n5 = GL11.glGetInteger((int)34964);
        GL30.glBindVertexArray((int)this.g);
        GL15.glBindBuffer((int)34962, (int)this.h);
        try (MemoryStack memoryStack = MemoryStack.stackPush();){
            FloatBuffer floatBuffer = memoryStack.mallocFloat(30);
            M.a(floatBuffer, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f);
            M.a(floatBuffer, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f);
            M.a(floatBuffer, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f);
            M.a(floatBuffer, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f);
            M.a(floatBuffer, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f);
            M.a(floatBuffer, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f);
            floatBuffer.flip();
            GL15.glBufferData((int)34962, (FloatBuffer)floatBuffer, (int)35044);
        }
        GL20.glEnableVertexAttribArray((int)0);
        GL20.glVertexAttribPointer((int)0, (int)3, (int)5126, (boolean)false, (int)20, (long)0L);
        GL20.glEnableVertexAttribArray((int)1);
        GL20.glVertexAttribPointer((int)1, (int)2, (int)5126, (boolean)false, (int)20, (long)12L);
        GL15.glBindBuffer((int)34962, (int)n5);
        GL30.glBindVertexArray((int)n4);
        this.r = true;
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
                    case 0 -> 114;
                    case 1 -> 83;
                    case 2 -> 116;
                    case 3 -> 72;
                    case 4 -> 29;
                    case 5 -> 115;
                    default -> 65;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static void a(FloatBuffer floatBuffer, float f2, float f3, float f4, float f5, float f6) {
        floatBuffer.put(f2).put(f3).put(f4).put(f5).put(f6);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                M.b = 30;
                var5 = new String[15];
                var3_1 = 0;
                var2_2 = "\"<\u0007!i\u001a.\u001c\u03feQ%\u0011:n\u001a.\u001csE}-y(\u001cs\u0002-~Aa&6\f\u000br\u001c3\u0016h~Bh\u001d(\u0014<\u0006%=\u0000 \u001f#\u0018-oA\u0005R\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000h~=s\u001a'\u001d!\u0019hn\u0012,\u0002?\u0011:/7a=!\u001d/t\u001d \u001e\u0000\u0015%m\u001f$\u0000h~=s\u001a'\u001d!\u0019hk\u0016\"@s -e\u0016-!:\u000e-&y4\u001c:\u0012'o\u001ea\u00046\u0017z=7(\u00006\u0017<t\u001c/IY\u0001&t\u0015.\u0000>T.q\u001c \u0006s&)y\u001a4\u0001h~=s\u001a'\u001d!\u0019h{\u001f.\u0013'T\u0003x\u0001/\u0017?/{,.zx&\u001a!{\u001c3\u001fs\u0012$r\u00125R\u001a\u001a<x\u001d2\u001b'\rs\u0017y.\u0007'T>x\u0010uR\u0015\u0006)z0.\u001e<\u0006s\u0017y7\u001d:\u0010hp\u0012(\u001c{]hfyaRsT.q\u001c \u0006s\u0007'h\u0001\"\u0017\u0012\u00188u\u0012aOs\u0000-e\u00074\u00006\\\u0007o\u001a&\u001b=\u0015$N\u0012,\u0002?\u0011:1S\u0015\u0017+7'r\u0001%[}\u0015s\u0017SaRs\u001d.=[\u0005\u001b!\u0011+i\u001a.\u001c}\fh NaB}Dh;Ua\u0001<\u0001:~\u0016\u0000\u001e#\u001c)=MaB}Da=\bKRsTh=SaR7\u001d;~\u00123\u0016h~h=Sa\u000fY~h=Sa\u00046\u0017|=\u0018$\u0000=\u0011$=Na\u00066\f<h\u0001$Z\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000\u007fT\u001cx\u000b\u0002\u001d<\u0006,4HKRsThk\u0016\"Fs\u0004!e\u0016-1<\u0018'oS|R8\u0011:s\u0016-RyT\u0003x\u0001/\u0017?/x@HKRsThm\u001a9\u0017?7'q\u001c3\\!\u0013*=Y|R#\u001d0x\u001f\u0002\u001d?\u001b:3\u0012zxYTh=S'\u001d!T`t\u001d5R:Tu=CzR:Tt SrBhTc6\u001ahR(~h=SaRsTht\u0015aZ5\u0018'|\u0007i\u001bzTv=! \u0016:\u0001;4S#\u00006\u0015#&yaRsTh=Sa\u00046\u0017z=\u001c'\u0014 \u0011<=Na&6\f-q (\b6Tb=7(\u00006\u0017<t\u001c/RyT.q\u001c \u0006{\u001da&yaRsTh=Sa\u00046\u0017|=\u001f$\u0014'Tu=\u0007$\n'\u0001:x[\u0005\u001b5\u0012=n\u0016\u0012\u0013>\u0004$x\u0001mR\u0007\u00110^\u001c.\u00007Te=\u001c'\u0014 \u0011<4HKRsTh=SaR%\u0011+)S3\u001b4\u001c<=Na\u00066\f<h\u0001$Z\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000\u007fT\u001cx\u000b\u0002\u001d<\u0006,=Xa\u001d5\u0012;x\u0007hIYTh=SaRsT$x\u00155\\!\u0013*=Y|R?\u0011.i] IYTh=SaRsT:t\u0014)\u0006}\u0006/\u007fSkOs\u0006!z\u001b5\\2OB=SaRsTh=\u0003(\n6\u0018\u000br\u001f.\u0000s_u=[-\u00175\u0000h6S3\u001b4\u001c<4SkR\u0018\u0011:s\u0016-):)s\u0017SaRs\tB\u0017SaRs\u0004!e\u0016-1<\u0018'o]3\u00151Tg S1\u001b+\u0011$^\u001c-\u001d!Z)&yaRsT!{Si6:\u0006-~\u0007(\u001d=Z0=N|RcZx4S1\u001b+\u0011$^\u001c-\u001d!Z)=Y|R\u001a\u001a<x\u001d2\u001b'\rs\u0017SaRs2:|\u0014\u0002\u001d?\u001b:=Na\u0002:\f-q0.\u001e<\u0006s\u0017\u000eK\u0003'\u0005D\u0006 2\u0010!h\u0000\u008bQ%\u0011:n\u001a.\u001csE}-y(\u001cs\u0002-~@a\"<\u0007!i\u001a.\u001ch~!sS7\u00170FhH%qIY\u001b=iS7\u00170FhI\u001691<\u001b:yHKx%\u001b!yS,\u0013:\u001a`4S:xsTh=\u0014--\u0003\u001b;t\u0007(\u001d=Tu=\u0005$\u0011g\\\u0018r\u0000(\u0006:\u001b&1Sp\\c]s\u0017SaRs -e0.\u001d!\u0010h S\u0014$cOB`y! :\u0007-=\u0011-\u001d<\u0019hn\u001b \u00166\u0006hq\u001a/\u0019s\u0012)t\u001f$\u0016iT3`( :\u0007-=\u0011-\u001d<\u0019hn\u001b \u00166\u0006h~\u001c,\u0002:\u0018)i\u001a.\u001cs\u0012)t\u001f$\u0016iT3`\t6:\u0006-~\u0007(\u001d=\u000696\u0006&x\u001f\u000f=!\u001d/t\u001d \u001e\u0000\u0015%m\u001f$\u0000\u000e6:\u0012.h\u0000$!2\u00198q\u00163\t&6\f-q (\b6\u03feQ%\u0011:n\u001a.\u001csE}-y(\u001cs\u0002-~Aa&6\f\u000br\u001c3\u0016h~Bh\u001d(\u0014<\u0006%=\u0000 \u001f#\u0018-oA\u0005R\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000h~=s\u001a'\u001d!\u0019hn\u0012,\u0002?\u0011:/7a=!\u001d/t\u001d \u001e\u0000\u0015%m\u001f$\u0000h~=s\u001a'\u001d!\u0019hk\u0016\"@s -e\u0016-!:\u000e-&y4\u001c:\u0012'o\u001ea\u00046\u0017z=7(\u00006\u0017<t\u001c/IY\u0001&t\u0015.\u0000>T.q\u001c \u0006s&)y\u001a4\u0001h~=s\u001a'\u001d!\u0019h{\u001f.\u0013'T\u0003x\u0001/\u0017?/{,.zx&\u001a!{\u001c3\u001fs\u0012$r\u00125R\u001a\u001a<x\u001d2\u001b'\rs\u0017y.\u0007'T>x\u0010uR\u0015\u0006)z0.\u001e<\u0006s\u0017y7\u001d:\u0010hp\u0012(\u001c{]hfyaRsT.q\u001c \u0006s\u0007'h\u0001\"\u0017\u0012\u00188u\u0012aOs\u0000-e\u00074\u00006\\\u0007o\u001a&\u001b=\u0015$N\u0012,\u0002?\u0011:1S\u0015\u0017+7'r\u0001%[}\u0015s\u0017SaRs\u001d.=[\u0005\u001b!\u0011+i\u001a.\u001c}\fh NaB}Dh;Ua\u0001<\u0001:~\u0016\u0000\u001e#\u001c)=MaB}Da=\bKRsTh=SaR7\u001d;~\u00123\u0016h~h=Sa\u000fY~h=Sa\u00046\u0017|=\u0018$\u0000=\u0011$=Na\u00066\f<h\u0001$Z\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000\u007fT\u001cx\u000b\u0002\u001d<\u0006,4HKRsThk\u0016\"Fs\u0004!e\u0016-1<\u0018'oS|R8\u0011:s\u0016-RyT\u0003x\u0001/\u0017?/x@HKRsThm\u001a9\u0017?7'q\u001c3\\!\u0013*=Y|R#\u001d0x\u001f\u0002\u001d?\u001b:3\u0012zxYTh=S'\u001d!T`t\u001d5R:Tu=CzR:Tt SrBhTc6\u001ahR(~h=SaRsTht\u0015aZ5\u0018'|\u0007i\u001bzTv=! \u0016:\u0001;4S#\u00006\u0015#&yaRsTh=Sa\u00046\u0017z=\u001c'\u0014 \u0011<=Na&6\f-q (\b6Tb=7(\u00006\u0017<t\u001c/RyT.q\u001c \u0006{\u001da&yaRsTh=Sa\u00046\u0017|=\u001f$\u0014'Tu=\u0007$\n'\u0001:x[\u0005\u001b5\u0012=n\u0016\u0012\u0013>\u0004$x\u0001mR\u0007\u00110^\u001c.\u00007Te=\u001c'\u0014 \u0011<4HKRsTh=SaR%\u0011+)S3\u001b4\u001c<=Na\u00066\f<h\u0001$Z\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000\u007fT\u001cx\u000b\u0002\u001d<\u0006,=Xa\u001d5\u0012;x\u0007hIYTh=SaRsT$x\u00155\\!\u0013*=Y|R?\u0011.i] IYTh=SaRsT:t\u0014)\u0006}\u0006/\u007fSkOs\u0006!z\u001b5\\2OB=SaRsTh=\u0003(\n6\u0018\u000br\u001f.\u0000s_u=[-\u00175\u0000h6S3\u001b4\u001c<4SkR\u0018\u0011:s\u0016-):)s\u0017SaRs\tB\u0017SaRs\u0004!e\u0016-1<\u0018'o]3\u00151Tg S1\u001b+\u0011$^\u001c-\u001d!Z)&yaRsT!{Si6:\u0006-~\u0007(\u001d=Z0=N|RcZx4S1\u001b+\u0011$^\u001c-\u001d!Z)=Y|R\u001a\u001a<x\u001d2\u001b'\rs\u0017SaRs2:|\u0014\u0002\u001d?\u001b:=Na\u0002:\f-q0.\u001e<\u0006s\u0017\u000eK";
                var4_3 = "\"<\u0007!i\u001a.\u001c\u03feQ%\u0011:n\u001a.\u001csE}-y(\u001cs\u0002-~Aa&6\f\u000br\u001c3\u0016h~Bh\u001d(\u0014<\u0006%=\u0000 \u001f#\u0018-oA\u0005R\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000h~=s\u001a'\u001d!\u0019hn\u0012,\u0002?\u0011:/7a=!\u001d/t\u001d \u001e\u0000\u0015%m\u001f$\u0000h~=s\u001a'\u001d!\u0019hk\u0016\"@s -e\u0016-!:\u000e-&y4\u001c:\u0012'o\u001ea\u00046\u0017z=7(\u00006\u0017<t\u001c/IY\u0001&t\u0015.\u0000>T.q\u001c \u0006s&)y\u001a4\u0001h~=s\u001a'\u001d!\u0019h{\u001f.\u0013'T\u0003x\u0001/\u0017?/{,.zx&\u001a!{\u001c3\u001fs\u0012$r\u00125R\u001a\u001a<x\u001d2\u001b'\rs\u0017y.\u0007'T>x\u0010uR\u0015\u0006)z0.\u001e<\u0006s\u0017y7\u001d:\u0010hp\u0012(\u001c{]hfyaRsT.q\u001c \u0006s\u0007'h\u0001\"\u0017\u0012\u00188u\u0012aOs\u0000-e\u00074\u00006\\\u0007o\u001a&\u001b=\u0015$N\u0012,\u0002?\u0011:1S\u0015\u0017+7'r\u0001%[}\u0015s\u0017SaRs\u001d.=[\u0005\u001b!\u0011+i\u001a.\u001c}\fh NaB}Dh;Ua\u0001<\u0001:~\u0016\u0000\u001e#\u001c)=MaB}Da=\bKRsTh=SaR7\u001d;~\u00123\u0016h~h=Sa\u000fY~h=Sa\u00046\u0017|=\u0018$\u0000=\u0011$=Na\u00066\f<h\u0001$Z\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000\u007fT\u001cx\u000b\u0002\u001d<\u0006,4HKRsThk\u0016\"Fs\u0004!e\u0016-1<\u0018'oS|R8\u0011:s\u0016-RyT\u0003x\u0001/\u0017?/x@HKRsThm\u001a9\u0017?7'q\u001c3\\!\u0013*=Y|R#\u001d0x\u001f\u0002\u001d?\u001b:3\u0012zxYTh=S'\u001d!T`t\u001d5R:Tu=CzR:Tt SrBhTc6\u001ahR(~h=SaRsTht\u0015aZ5\u0018'|\u0007i\u001bzTv=! \u0016:\u0001;4S#\u00006\u0015#&yaRsTh=Sa\u00046\u0017z=\u001c'\u0014 \u0011<=Na&6\f-q (\b6Tb=7(\u00006\u0017<t\u001c/RyT.q\u001c \u0006{\u001da&yaRsTh=Sa\u00046\u0017|=\u001f$\u0014'Tu=\u0007$\n'\u0001:x[\u0005\u001b5\u0012=n\u0016\u0012\u0013>\u0004$x\u0001mR\u0007\u00110^\u001c.\u00007Te=\u001c'\u0014 \u0011<4HKRsTh=SaR%\u0011+)S3\u001b4\u001c<=Na\u00066\f<h\u0001$Z\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000\u007fT\u001cx\u000b\u0002\u001d<\u0006,=Xa\u001d5\u0012;x\u0007hIYTh=SaRsT$x\u00155\\!\u0013*=Y|R?\u0011.i] IYTh=SaRsT:t\u0014)\u0006}\u0006/\u007fSkOs\u0006!z\u001b5\\2OB=SaRsTh=\u0003(\n6\u0018\u000br\u001f.\u0000s_u=[-\u00175\u0000h6S3\u001b4\u001c<4SkR\u0018\u0011:s\u0016-):)s\u0017SaRs\tB\u0017SaRs\u0004!e\u0016-1<\u0018'o]3\u00151Tg S1\u001b+\u0011$^\u001c-\u001d!Z)&yaRsT!{Si6:\u0006-~\u0007(\u001d=Z0=N|RcZx4S1\u001b+\u0011$^\u001c-\u001d!Z)=Y|R\u001a\u001a<x\u001d2\u001b'\rs\u0017SaRs2:|\u0014\u0002\u001d?\u001b:=Na\u0002:\f-q0.\u001e<\u0006s\u0017\u000eK\u0003'\u0005D\u0006 2\u0010!h\u0000\u008bQ%\u0011:n\u001a.\u001csE}-y(\u001cs\u0002-~@a\"<\u0007!i\u001a.\u001ch~!sS7\u00170FhH%qIY\u001b=iS7\u00170FhI\u001691<\u001b:yHKx%\u001b!yS,\u0013:\u001a`4S:xsTh=\u0014--\u0003\u001b;t\u0007(\u001d=Tu=\u0005$\u0011g\\\u0018r\u0000(\u0006:\u001b&1Sp\\c]s\u0017SaRs -e0.\u001d!\u0010h S\u0014$cOB`y! :\u0007-=\u0011-\u001d<\u0019hn\u001b \u00166\u0006hq\u001a/\u0019s\u0012)t\u001f$\u0016iT3`( :\u0007-=\u0011-\u001d<\u0019hn\u001b \u00166\u0006h~\u001c,\u0002:\u0018)i\u001a.\u001cs\u0012)t\u001f$\u0016iT3`\t6:\u0006-~\u0007(\u001d=\u000696\u0006&x\u001f\u000f=!\u001d/t\u001d \u001e\u0000\u0015%m\u001f$\u0000\u000e6:\u0012.h\u0000$!2\u00198q\u00163\t&6\f-q (\b6\u03feQ%\u0011:n\u001a.\u001csE}-y(\u001cs\u0002-~Aa&6\f\u000br\u001c3\u0016h~Bh\u001d(\u0014<\u0006%=\u0000 \u001f#\u0018-oA\u0005R\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000h~=s\u001a'\u001d!\u0019hn\u0012,\u0002?\u0011:/7a=!\u001d/t\u001d \u001e\u0000\u0015%m\u001f$\u0000h~=s\u001a'\u001d!\u0019hk\u0016\"@s -e\u0016-!:\u000e-&y4\u001c:\u0012'o\u001ea\u00046\u0017z=7(\u00006\u0017<t\u001c/IY\u0001&t\u0015.\u0000>T.q\u001c \u0006s&)y\u001a4\u0001h~=s\u001a'\u001d!\u0019h{\u001f.\u0013'T\u0003x\u0001/\u0017?/{,.zx&\u001a!{\u001c3\u001fs\u0012$r\u00125R\u001a\u001a<x\u001d2\u001b'\rs\u0017y.\u0007'T>x\u0010uR\u0015\u0006)z0.\u001e<\u0006s\u0017y7\u001d:\u0010hp\u0012(\u001c{]hfyaRsT.q\u001c \u0006s\u0007'h\u0001\"\u0017\u0012\u00188u\u0012aOs\u0000-e\u00074\u00006\\\u0007o\u001a&\u001b=\u0015$N\u0012,\u0002?\u0011:1S\u0015\u0017+7'r\u0001%[}\u0015s\u0017SaRs\u001d.=[\u0005\u001b!\u0011+i\u001a.\u001c}\fh NaB}Dh;Ua\u0001<\u0001:~\u0016\u0000\u001e#\u001c)=MaB}Da=\bKRsTh=SaR7\u001d;~\u00123\u0016h~h=Sa\u000fY~h=Sa\u00046\u0017|=\u0018$\u0000=\u0011$=Na\u00066\f<h\u0001$Z\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000\u007fT\u001cx\u000b\u0002\u001d<\u0006,4HKRsThk\u0016\"Fs\u0004!e\u0016-1<\u0018'oS|R8\u0011:s\u0016-RyT\u0003x\u0001/\u0017?/x@HKRsThm\u001a9\u0017?7'q\u001c3\\!\u0013*=Y|R#\u001d0x\u001f\u0002\u001d?\u001b:3\u0012zxYTh=S'\u001d!T`t\u001d5R:Tu=CzR:Tt SrBhTc6\u001ahR(~h=SaRsTht\u0015aZ5\u0018'|\u0007i\u001bzTv=! \u0016:\u0001;4S#\u00006\u0015#&yaRsTh=Sa\u00046\u0017z=\u001c'\u0014 \u0011<=Na&6\f-q (\b6Tb=7(\u00006\u0017<t\u001c/RyT.q\u001c \u0006{\u001da&yaRsTh=Sa\u00046\u0017|=\u001f$\u0014'Tu=\u0007$\n'\u0001:x[\u0005\u001b5\u0012=n\u0016\u0012\u0013>\u0004$x\u0001mR\u0007\u00110^\u001c.\u00007Te=\u001c'\u0014 \u0011<4HKRsTh=SaR%\u0011+)S3\u001b4\u001c<=Na\u00066\f<h\u0001$Z\u0017\u001d.{\u00062\u0017\u0000\u0015%m\u001f$\u0000\u007fT\u001cx\u000b\u0002\u001d<\u0006,=Xa\u001d5\u0012;x\u0007hIYTh=SaRsT$x\u00155\\!\u0013*=Y|R?\u0011.i] IYTh=SaRsT:t\u0014)\u0006}\u0006/\u007fSkOs\u0006!z\u001b5\\2OB=SaRsTh=\u0003(\n6\u0018\u000br\u001f.\u0000s_u=[-\u00175\u0000h6S3\u001b4\u001c<4SkR\u0018\u0011:s\u0016-):)s\u0017SaRs\tB\u0017SaRs\u0004!e\u0016-1<\u0018'o]3\u00151Tg S1\u001b+\u0011$^\u001c-\u001d!Z)&yaRsT!{Si6:\u0006-~\u0007(\u001d=Z0=N|RcZx4S1\u001b+\u0011$^\u001c-\u001d!Z)=Y|R\u001a\u001a<x\u001d2\u001b'\rs\u0017SaRs2:|\u0014\u0002\u001d?\u001b:=Na\u0002:\f-q0.\u001e<\u0006s\u0017\u000eK".length();
                var1_4 = 8;
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
                    var5[var3_1++] = M.a(M.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "Q%\u0011:n\u001a.\u001csE}-y(\u001cs\u0002-~@a\"<\u0007!i\u001a.\u001ch~!sS7\u00170FhH%qIY\u001b=iS7\u00170FhI\u001691<\u001b:yHKx%\u001b!yS,\u0013:\u001a`4S:xsTh=\u0014--\u0003\u001b;t\u0007(\u001d=Tu=\u0005$\u0011g\\\u0018r\u0000(\u0006:\u001b&1Sp\\c]s\u0017SaRs -e0.\u001d!\u0010h S\u0014$cOB`y\t;=\u0000-s\u0000(\u0006*";
                    var4_3 = "Q%\u0011:n\u001a.\u001csE}-y(\u001cs\u0002-~@a\"<\u0007!i\u001a.\u001ch~!sS7\u00170FhH%qIY\u001b=iS7\u00170FhI\u001691<\u001b:yHKx%\u001b!yS,\u0013:\u001a`4S:xsTh=\u0014--\u0003\u001b;t\u0007(\u001d=Tu=\u0005$\u0011g\\\u0018r\u0000(\u0006:\u001b&1Sp\\c]s\u0017SaRs -e0.\u001d!\u0010h S\u0014$cOB`y\t;=\u0000-s\u0000(\u0006*".length();
                    var1_4 = 139;
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
                    var5[var3_1++] = M.a(M.a(v1));
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
        M.s = var5;
        M.d = M.s[1];
        M.c = M.s[4];
        M.a = LogUtils.getLogger();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean a(int n2, int n3, int n4, float f2, float f3, float f4) {
        this.a();
        if (!this.r || n2 == 0 || n3 <= 0 || n4 <= 0) {
            return false;
        }
        int n5 = Math.max(1, Math.min(30, Math.round(f2)));
        this.e.a(n3, n4);
        int n6 = GL11.glGetInteger((int)36006);
        int[] nArray = new int[4];
        GL11.glGetIntegerv((int)2978, (int[])nArray);
        int n7 = GL11.glGetInteger((int)35725);
        int n8 = GL11.glGetInteger((int)34229);
        int n9 = GL11.glGetInteger((int)34016);
        boolean bl = GL11.glIsEnabled((int)3042);
        boolean bl2 = GL11.glIsEnabled((int)2929);
        int n10 = GL11.glGetInteger((int)32969);
        int n11 = GL11.glGetInteger((int)32968);
        int n12 = GL11.glGetInteger((int)32971);
        int n13 = GL11.glGetInteger((int)32970);
        GL13.glActiveTexture((int)33984);
        int n14 = GL11.glGetInteger((int)32873);
        GL13.glActiveTexture((int)33985);
        int n15 = GL11.glGetInteger((int)32873);
        try {
            GL20.glUseProgram((int)this.f);
            GL30.glBindVertexArray((int)this.g);
            GL20.glUniform1i((int)this.i, (int)0);
            GL20.glUniform1i((int)this.j, (int)1);
            GL20.glUniform2f((int)this.k, (float)(1.0f / (float)n3), (float)(1.0f / (float)n4));
            GL20.glUniform1f((int)this.m, (float)n5);
            GL20.glUniform1f((int)this.o, (float)Math.max(0.0f, f4));
            this.a(n5);
            GL11.glDisable((int)2929);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)1, (int)770);
            this.e.a();
            this.e.b();
            GL13.glActiveTexture((int)33984);
            GL11.glBindTexture((int)3553, (int)n2);
            GL20.glUniform2f((int)this.l, (float)f3, (float)0.0f);
            GL11.glDrawArrays((int)4, (int)0, (int)6);
            GL30.glBindFramebuffer((int)36160, (int)n6);
            GL11.glViewport((int)nArray[0], (int)nArray[1], (int)nArray[2], (int)nArray[3]);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL13.glActiveTexture((int)33984);
            GL11.glBindTexture((int)3553, (int)this.e.c());
            GL13.glActiveTexture((int)33985);
            GL11.glBindTexture((int)3553, (int)n2);
            GL20.glUniform2f((int)this.l, (float)0.0f, (float)f3);
            GL11.glDrawArrays((int)4, (int)0, (int)6);
            boolean bl3 = true;
            return bl3;
        }
        finally {
            GL13.glActiveTexture((int)33985);
            GL11.glBindTexture((int)3553, (int)n15);
            GL13.glActiveTexture((int)33984);
            GL11.glBindTexture((int)3553, (int)n14);
            GL13.glActiveTexture((int)n9);
            GL14.glBlendFuncSeparate((int)n10, (int)n11, (int)n12, (int)n13);
            if (bl) {
                GL11.glEnable((int)3042);
            } else {
                GL11.glDisable((int)3042);
            }
            if (bl2) {
                GL11.glEnable((int)2929);
            } else {
                GL11.glDisable((int)2929);
            }
            GL30.glBindVertexArray((int)n8);
            GL20.glUseProgram((int)n7);
            GL30.glBindFramebuffer((int)36160, (int)n6);
            GL11.glViewport((int)nArray[0], (int)nArray[1], (int)nArray[2], (int)nArray[3]);
        }
    }

    private static int a(int n2, String string) {
        int n3 = GL20.glCreateShader((int)n2);
        GL20.glShaderSource((int)n3, (CharSequence)string);
        GL20.glCompileShader((int)n3);
        if (GL20.glGetShaderi((int)n3, (int)35713) == 0) {
            a.error("Rise bloom shader compilation failed: {}", (Object)GL20.glGetShaderInfoLog((int)n3));
            GL20.glDeleteShader((int)n3);
            return 0;
        }
        return n3;
    }

    public boolean a(int n2, int n3, int n4, float f2, float f3) {
        return this.a(n2, n3, n4, f2, f3, 1.0f);
    }

    private void a(int n2) {
        int n3;
        if (this.p == n2) {
            return;
        }
        float[] fArray = new float[31];
        float f2 = (float)n2 / 2.0f;
        float f3 = 0.0f;
        for (n3 = 0; n3 < n2; ++n3) {
            float f4 = (float)n3 / f2;
            fArray[n3] = (float)(Math.exp(-0.5f * f4 * f4) / (double)(f2 * 2.5066283f));
            f3 += n3 == 0 ? fArray[n3] : fArray[n3] * 2.0f;
        }
        n3 = 0;
        while (n3 < n2) {
            int n4 = n3++;
            fArray[n4] = fArray[n4] / f3;
        }
        GL20.glUniform1fv((int)this.n, (float[])fArray);
        this.p = n2;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x41);
        }
        return cArray;
    }
}

