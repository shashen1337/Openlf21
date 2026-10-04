/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.PoseStack;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import lf21.render.M;
import lf21.render.Y;
import lf21.render.Z;
import lf21.render.n_0;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

public final class C {
    private int s;
    private final n_0 f;
    private static final String[] A;
    private final n_0 e;
    private final int[] u;
    private int j;
    private static C c;
    private final List<Y> i;
    private final Minecraft d = Minecraft.getInstance();
    private int r;
    private int o;
    private final n_0 g;
    private int n;
    private int w;
    private int l;
    private final M h;
    private int m;
    private int v;
    private static final String a;
    private int p;
    private boolean z;
    private int t;
    private int q;
    private boolean y;
    private boolean x;
    private static final String b;
    private int k;

    private void a(n_0 n_02) {
        int n2 = GL11.glGetInteger((int)34016);
        GL13.glActiveTexture((int)33984);
        int n3 = GL11.glGetInteger((int)32873);
        GL11.glBindTexture((int)3553, (int)n_02.c());
        GL11.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)0, (int)0, (int)this.v, (int)this.w);
        GL11.glBindTexture((int)3553, (int)n3);
        GL13.glActiveTexture((int)n2);
    }

    public C() {
        this.e = new n_0();
        this.f = new n_0();
        this.g = new n_0();
        this.h = new M();
        this.i = new ArrayList<Y>();
        this.u = new int[4];
    }

    public boolean a(GuiGraphics guiGraphics) {
        if (this.z || guiGraphics == null || this.d.getMainRenderTarget() == null) {
            return false;
        }
        this.c();
        if (!this.y) {
            return false;
        }
        guiGraphics.flush();
        BufferUploader.reset();
        this.v = Math.max(1, this.d.getWindow().getWidth());
        this.w = Math.max(1, this.d.getWindow().getHeight());
        this.e.a(this.v, this.w);
        this.f.a(this.v, this.w);
        this.g.a(this.v, this.w);
        this.t = GL11.glGetInteger((int)36006);
        GL11.glGetIntegerv((int)2978, (int[])this.u);
        this.a(this.e);
        this.i.clear();
        this.z = true;
        c = this;
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(GuiGraphics guiGraphics, float f2, float f3, float f4) {
        if (!this.z) {
            return;
        }
        try {
            c = null;
            if (guiGraphics != null) {
                guiGraphics.flush();
            }
            BufferUploader.reset();
            GL30.glBindFramebuffer((int)36160, (int)this.t);
            GL11.glViewport((int)this.u[0], (int)this.u[1], (int)this.u[2], (int)this.u[3]);
            this.a(this.f);
            this.a();
            GL30.glBindFramebuffer((int)36160, (int)this.t);
            GL11.glViewport((int)this.u[0], (int)this.u[1], (int)this.u[2], (int)this.u[3]);
            this.h.a(this.g.c(), this.v, this.w, f2, f3, f4);
        }
        finally {
            c = null;
            GL30.glBindFramebuffer((int)36160, (int)this.t);
            GL11.glViewport((int)this.u[0], (int)this.u[1], (int)this.u[2], (int)this.u[3]);
            this.z = false;
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x7B);
        }
        return cArray;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void c() {
        if (this.x) {
            return;
        }
        this.x = true;
        String[] stringArray = A;
        int n3 = C.a(35633, "#version 150\nin vec3 Position;\nin vec2 UV0;\nout vec2 TexCoord;\n\nvoid main() {\n    gl_Position = vec4(Position, 1.0);\n    TexCoord = UV0;\n}\n");
        int n2 = C.a(35632, "#version 150\nin vec2 TexCoord;\n\nuniform sampler2D BeforeSampler;\nuniform sampler2D AfterSampler;\nuniform int DrawMode;\nuniform vec2 Resolution;\nuniform vec4 SurfaceRect;\nuniform float CornerRadius;\nuniform float SurfaceAlpha;\n\nout vec4 FragColor;\n\nvoid main() {\n    if (DrawMode == 0) {\n        vec4 beforeColor = texture(BeforeSampler, TexCoord);\n        vec4 afterColor = texture(AfterSampler, TexCoord);\n        vec4 delta = abs(afterColor - beforeColor);\n        float difference = max(max(delta.r, delta.g), max(delta.b, delta.a));\n        float coverage = smoothstep(0.001, 0.012, difference);\n        if (coverage <= 0.0) discard;\n        FragColor = vec4(1.0, 1.0, 1.0, coverage);\n        return;\n    }\n\n    vec2 halfSize = SurfaceRect.zw * 0.5;\n    vec2 center = SurfaceRect.xy + halfSize;\n    float radius = min(CornerRadius, min(halfSize.x, halfSize.y));\n    vec2 q = abs(TexCoord * Resolution - center) - halfSize + radius;\n    float distance = length(max(q, vec2(0.0)))\n            + min(max(q.x, q.y), 0.0) - radius;\n    float coverage = (1.0 - smoothstep(-1.0, 1.0, distance)) * SurfaceAlpha;\n    if (coverage <= 0.0) discard;\n    FragColor = vec4(1.0, 1.0, 1.0, coverage);\n}\n");
        if (n3 == 0 || n2 == 0) {
            return;
        }
        this.j = GL20.glCreateProgram();
        GL20.glAttachShader((int)this.j, (int)n3);
        GL20.glAttachShader((int)this.j, (int)n2);
        GL20.glBindAttribLocation((int)this.j, (int)0, (CharSequence)"Position");
        GL20.glBindAttribLocation((int)this.j, (int)1, (CharSequence)"UV0");
        GL20.glLinkProgram((int)this.j);
        GL20.glDeleteShader((int)n3);
        GL20.glDeleteShader((int)n2);
        if (GL20.glGetProgrami((int)this.j, (int)35714) == 0) {
            return;
        }
        stringArray = A;
        this.m = GL20.glGetUniformLocation((int)this.j, (CharSequence)"BeforeSampler");
        this.n = GL20.glGetUniformLocation((int)this.j, (CharSequence)"AfterSampler");
        this.o = GL20.glGetUniformLocation((int)this.j, (CharSequence)"DrawMode");
        this.p = GL20.glGetUniformLocation((int)this.j, (CharSequence)"Resolution");
        this.q = GL20.glGetUniformLocation((int)this.j, (CharSequence)"SurfaceRect");
        this.r = GL20.glGetUniformLocation((int)this.j, (CharSequence)"CornerRadius");
        this.s = GL20.glGetUniformLocation((int)this.j, (CharSequence)"SurfaceAlpha");
        this.k = GL30.glGenVertexArrays();
        this.l = GL15.glGenBuffers();
        int n4 = GL11.glGetInteger((int)34229);
        int n5 = GL11.glGetInteger((int)34964);
        GL30.glBindVertexArray((int)this.k);
        GL15.glBindBuffer((int)34962, (int)this.l);
        try (MemoryStack memoryStack = MemoryStack.stackPush();){
            FloatBuffer floatBuffer = memoryStack.mallocFloat(30);
            C.a(floatBuffer, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f);
            C.a(floatBuffer, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f);
            C.a(floatBuffer, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f);
            C.a(floatBuffer, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f);
            C.a(floatBuffer, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f);
            C.a(floatBuffer, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f);
            floatBuffer.flip();
            GL15.glBufferData((int)34962, (FloatBuffer)floatBuffer, (int)35044);
        }
        GL20.glEnableVertexAttribArray((int)0);
        GL20.glVertexAttribPointer((int)0, (int)3, (int)5126, (boolean)false, (int)20, (long)0L);
        GL20.glEnableVertexAttribArray((int)1);
        GL20.glVertexAttribPointer((int)1, (int)2, (int)5126, (boolean)false, (int)20, (long)12L);
        GL15.glBindBuffer((int)34962, (int)n5);
        GL30.glBindVertexArray((int)n4);
        this.y = true;
    }

    private void b() {
        Z z2;
        if (this.i.isEmpty()) {
            return;
        }
        float f2 = (float)this.v / (float)Math.max(1, this.d.getWindow().getGuiScaledWidth());
        float f3 = (float)this.w / (float)Math.max(1, this.d.getWindow().getGuiScaledHeight());
        GL11.glEnable((int)3089);
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        for (Y y2 : this.i) {
            z2 = this.a(y2, f2, f3);
            int n2 = Math.max(0, (int)Math.floor(z2.a) - 2);
            int n3 = Math.max(0, (int)Math.floor(z2.b) - 2);
            int n4 = Math.min(this.v, (int)Math.ceil(z2.a + z2.c) + 2);
            int n5 = Math.min(this.w, (int)Math.ceil(z2.b + z2.d) + 2);
            if (n4 <= n2 || n5 <= n3) continue;
            GL11.glScissor((int)n2, (int)n3, (int)(n4 - n2), (int)(n5 - n3));
            GL11.glClear((int)16384);
        }
        GL11.glDisable((int)3089);
        GL20.glUniform1i((int)this.o, (int)1);
        for (Y y2 : this.i) {
            z2 = this.a(y2, f2, f3);
            GL20.glUniform4f((int)this.q, (float)z2.a, (float)z2.b, (float)z2.c, (float)z2.d);
            GL20.glUniform1f((int)this.r, (float)z2.e);
            GL20.glUniform1f((int)this.s, (float)y2.f);
            GL11.glDrawArrays((int)4, (int)0, (int)6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a() {
        int n2 = GL11.glGetInteger((int)36006);
        int[] nArray = new int[4];
        GL11.glGetIntegerv((int)2978, (int[])nArray);
        int n3 = GL11.glGetInteger((int)35725);
        int n4 = GL11.glGetInteger((int)34229);
        int n5 = GL11.glGetInteger((int)34016);
        boolean bl = GL11.glIsEnabled((int)3042);
        boolean bl2 = GL11.glIsEnabled((int)2929);
        boolean bl3 = GL11.glIsEnabled((int)3089);
        int[] nArray2 = new int[4];
        GL11.glGetIntegerv((int)3088, (int[])nArray2);
        float[] fArray = new float[4];
        GL11.glGetFloatv((int)3106, (float[])fArray);
        GL13.glActiveTexture((int)33984);
        int n6 = GL11.glGetInteger((int)32873);
        GL13.glActiveTexture((int)33985);
        int n7 = GL11.glGetInteger((int)32873);
        try {
            this.g.a();
            this.g.b();
            GL11.glDisable((int)2929);
            GL11.glDisable((int)3042);
            GL20.glUseProgram((int)this.j);
            GL30.glBindVertexArray((int)this.k);
            GL20.glUniform1i((int)this.m, (int)0);
            GL20.glUniform1i((int)this.n, (int)1);
            GL20.glUniform1i((int)this.o, (int)0);
            GL20.glUniform2f((int)this.p, (float)this.v, (float)this.w);
            GL13.glActiveTexture((int)33984);
            GL11.glBindTexture((int)3553, (int)this.e.c());
            GL13.glActiveTexture((int)33985);
            GL11.glBindTexture((int)3553, (int)this.f.c());
            GL11.glDrawArrays((int)4, (int)0, (int)6);
            this.b();
        }
        finally {
            GL11.glClearColor((float)fArray[0], (float)fArray[1], (float)fArray[2], (float)fArray[3]);
            GL11.glScissor((int)nArray2[0], (int)nArray2[1], (int)nArray2[2], (int)nArray2[3]);
            if (bl3) {
                GL11.glEnable((int)3089);
            } else {
                GL11.glDisable((int)3089);
            }
            GL13.glActiveTexture((int)33985);
            GL11.glBindTexture((int)3553, (int)n7);
            GL13.glActiveTexture((int)33984);
            GL11.glBindTexture((int)3553, (int)n6);
            GL13.glActiveTexture((int)n5);
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
            GL30.glBindVertexArray((int)n4);
            GL20.glUseProgram((int)n3);
            GL30.glBindFramebuffer((int)36160, (int)n2);
            GL11.glViewport((int)nArray[0], (int)nArray[1], (int)nArray[2], (int)nArray[3]);
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
                    case 0 -> 37;
                    case 1 -> 120;
                    case 2 -> 20;
                    case 3 -> 109;
                    case 4 -> 118;
                    case 5 -> 35;
                    default -> 123;
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
            GL20.glDeleteShader((int)n3);
            return 0;
        }
        return n3;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[13];
                var3_1 = 0;
                var2_2 = "\u0006\u000eq\u001f\u0005J\u0014KX%XF)\u0012KXb\b\u0015\u0011[q\u001dl.\u0019L\tAC\u001eg\u0003M\u0012C\u0017f\u0000VP\u001aH\bx\b\u0004\u0011?\u0005:q\u000b\u0019Q\u001ev\u0019y\u001d\u001aF\t\u001era\u0003\u001fE\u0014W\u00154\u001e\u0017N\u000bI\u001df_2\u0003:C\fq\u001f%B\u0016U\u0014q\u001fM)\u000eK\u0011r\u0002\u0004N[L\u0016`M2Q\u001aR5{\t\u0013\u0018qP\u0016}\u000b\u0019Q\u0016\u0005\u000eq\u000eD\u0003)@\u000b{\u0001\u0003W\u0012J\u0016/g\u0003M\u0012C\u0017f\u0000VU\u001eFL4>\u0003Q\u001dD\u001bq?\u0013@\u000f\u001era\u0003\u001fE\u0014W\u00154\u000b\u001aL\u001aQXW\u0002\u0004M\u001eW*u\t\u001fV\b\u001era\u0003\u001fE\u0014W\u00154\u000b\u001aL\u001aQXG\u0018\u0004E\u001aF\u001dU\u0001\u0006K\u001a\u001er\u001e\u0002\u0003W[S\u001dwYVe\tD\u001fW\u0002\u001aL\t\u001er\u001e\u001b\u0019J\u001f\u0005\u0015u\u0004\u0018\u000bR\u0005\u0003\u001eMV\u0003[L\u001e4E2Q\u001aR5{\t\u0013\u0003F\u0018X$DVXq\u0005X4MV\u0003[\u0005\u000eq\u000eB\u0003\u0019@\u001e{\u001f\u0013`\u0014I\u0017fMK\u0003\u000f@\u0000`\u0018\u0004FSg\u001dr\u0002\u0004F(D\u0015d\u0001\u0013QW\u0005,q\u00155L\u0014W\u001c=V|\u0003[\u0005X4MV\u0003\r@\u001b M\u0017E\u000f@\nW\u0002\u001aL\t\u0005E4\u0019\u0013[\u000fP\nqE7E\u000f@\nG\f\u001bS\u0017@\n8M\"F\u0003f\u0017{\u001f\u0012\n@/X4MV\u0003[\u0005Xb\b\u0015\u0017[A\u001dx\u0019\u0017\u0003F\u0005\u0019v\u001e^B\u001dQ\u001df.\u0019O\u0014WX9M\u0014F\u001dJ\nq.\u0019O\u0014WQ/gV\u0003[\u0005X4MVE\u0017J\u0019`M\u0012J\u001dC\u001df\b\u0018@\u001e\u0005E4\u0000\u0017[SH\u0019lE\u0012F\u0017Q\u0019:\u001fZ\u0003\u001f@\u0014`\fXDR\tXy\f\u000e\u000b\u001f@\u0014`\fXAW\u0005\u001cq\u0001\u0002BUDQ=V|\u0003[\u0005X4MV\u0003\u001dI\u0017u\u0019V@\u0014S\u001df\f\u0011F[\u0018Xg\u0000\u0019L\u000fM\u000b`\b\u0006\u000bK\u000bH$\\Z\u0003K\u000bH%_Z\u0003\u001fL\u001er\b\u0004F\u0015F\u001d=V|\u0003[\u0005X4MV\u0003\u0012CX<\u000e\u0019U\u001eW\u0019s\bV\u001fF\u0005H:]_\u0003\u001fL\u000bw\f\u0004G@/X4MV\u0003[\u0005XR\u001f\u0017D8J\u0014{\u001fV\u001e[S\u001dwY^\u0012U\u0015T4\\X\u0013W\u0005I:]Z\u0003\u0018J\u000eq\u001f\u0017D\u001e\fC\u001eMV\u0003[\u0005X4M\u0004F\u000fP\nzV|\u0003[\u0005Xig|\u0003[\u0005Xb\b\u0015\u0011[M\u0019x\u000b%J\u0001@X)M%V\tC\u0019w\b$F\u0018QVn\u001aV\t[\u0015V!V|\u0003[\u0005Xb\b\u0015\u0011[F\u001dz\u0019\u0013Q[\u0018XG\u0018\u0004E\u001aF\u001dF\b\u0015WU]\u00014FVK\u001aI\u001eG\u0004\fF@/X4MVE\u0017J\u0019`M\u0004B\u001fL\rgMK\u0003\u0016L\u0016<.\u0019Q\u0015@\nF\f\u0012J\u000eVT4\u0000\u001fMSM\u0019x\u000b%J\u0001@VlAVK\u001aI\u001eG\u0004\fFU\\Q=V|\u0003[\u0005Xb\b\u0015\u0011[TX)M\u0017A\b\r,q\u00155L\u0014W\u001c4GVq\u001eV\u0017x\u0018\u0002J\u0014KX9M\u0015F\u0015Q\u001dfDV\u000e[M\u0019x\u000b%J\u0001@X?M\u0004B\u001fL\rgV|\u0003[\u0005Xr\u0001\u0019B\u000f\u0005\u001c}\u001e\u0002B\u0015F\u001d4PVO\u001eK\u001f`\u0005^N\u001a]PeAVU\u001eFJ<]X\u0013R\fQ\u001eMV\u0003[\u0005X4MV\u0003[\u0005S4\u0000\u001fMSH\u0019lE\u0007\r\u0003\tXeC\u000f\nW\u0005H:]_\u0003V\u0005\nu\t\u001fV\b\u001er4MV\u0003\u001dI\u0017u\u0019V@\u0014S\u001df\f\u0011F[\u0018X<\\X\u0013[\bXg\u0000\u0019L\u000fM\u000b`\b\u0006\u000bV\u0014V$AV\u0012U\u0015T4\t\u001fP\u000fD\u0016w\b_\n[\u000fXG\u0018\u0004E\u001aF\u001dU\u0001\u0006K\u001a\u001er4MV\u0003\u0012CX<\u000e\u0019U\u001eW\u0019s\bV\u001fF\u0005H:]_\u0003\u001fL\u000bw\f\u0004G@/X4MVe\tD\u001fW\u0002\u001aL\t\u0005E4\u001b\u0013@O\rI:]Z\u0003J\u000bH8MG\rK\tXw\u0002\u0000F\tD\u001fqDM)\u0006/\rg\u001dr\u0002\u0004F(D\u0015d\u0001\u0013Q\nw\u001dg\u0002\u001aV\u000fL\u0017z\u04a7\u0006\u000eq\u001f\u0005J\u0014KX%XF)\u0012KXb\b\u0015\u0011[q\u001dl.\u0019L\tAC\u001eg\u0003M\u0012C\u0017f\u0000VP\u001aH\bx\b\u0004\u0011?\u0005:q\u000b\u0019Q\u001ev\u0019y\u001d\u001aF\t\u001era\u0003\u001fE\u0014W\u00154\u001e\u0017N\u000bI\u001df_2\u0003:C\fq\u001f%B\u0016U\u0014q\u001fM)\u000eK\u0011r\u0002\u0004N[L\u0016`M2Q\u001aR5{\t\u0013\u0018qP\u0016}\u000b\u0019Q\u0016\u0005\u000eq\u000eD\u0003)@\u000b{\u0001\u0003W\u0012J\u0016/g\u0003M\u0012C\u0017f\u0000VU\u001eFL4>\u0003Q\u001dD\u001bq?\u0013@\u000f\u001era\u0003\u001fE\u0014W\u00154\u000b\u001aL\u001aQXW\u0002\u0004M\u001eW*u\t\u001fV\b\u001era\u0003\u001fE\u0014W\u00154\u000b\u001aL\u001aQXG\u0018\u0004E\u001aF\u001dU\u0001\u0006K\u001a\u001er\u001e\u0002\u0003W[S\u001dwYVe\tD\u001fW\u0002\u001aL\t\u001er\u001e\u001b\u0019J\u001f\u0005\u0015u\u0004\u0018\u000bR\u0005\u0003\u001eMV\u0003[L\u001e4E2Q\u001aR5{\t\u0013\u0003F\u0018X$DVXq\u0005X4MV\u0003[\u0005\u000eq\u000eB\u0003\u0019@\u001e{\u001f\u0013`\u0014I\u0017fMK\u0003\u000f@\u0000`\u0018\u0004FSg\u001dr\u0002\u0004F(D\u0015d\u0001\u0013QW\u0005,q\u00155L\u0014W\u001c=V|\u0003[\u0005X4MV\u0003\r@\u001b M\u0017E\u000f@\nW\u0002\u001aL\t\u0005E4\u0019\u0013[\u000fP\nqE7E\u000f@\nG\f\u001bS\u0017@\n8M\"F\u0003f\u0017{\u001f\u0012\n@/X4MV\u0003[\u0005Xb\b\u0015\u0017[A\u001dx\u0019\u0017\u0003F\u0005\u0019v\u001e^B\u001dQ\u001df.\u0019O\u0014WX9M\u0014F\u001dJ\nq.\u0019O\u0014WQ/gV\u0003[\u0005X4MVE\u0017J\u0019`M\u0012J\u001dC\u001df\b\u0018@\u001e\u0005E4\u0000\u0017[SH\u0019lE\u0012F\u0017Q\u0019:\u001fZ\u0003\u001f@\u0014`\fXDR\tXy\f\u000e\u000b\u001f@\u0014`\fXAW\u0005\u001cq\u0001\u0002BUDQ=V|\u0003[\u0005X4MV\u0003\u001dI\u0017u\u0019V@\u0014S\u001df\f\u0011F[\u0018Xg\u0000\u0019L\u000fM\u000b`\b\u0006\u000bK\u000bH$\\Z\u0003K\u000bH%_Z\u0003\u001fL\u001er\b\u0004F\u0015F\u001d=V|\u0003[\u0005X4MV\u0003\u0012CX<\u000e\u0019U\u001eW\u0019s\bV\u001fF\u0005H:]_\u0003\u001fL\u000bw\f\u0004G@/X4MV\u0003[\u0005XR\u001f\u0017D8J\u0014{\u001fV\u001e[S\u001dwY^\u0012U\u0015T4\\X\u0013W\u0005I:]Z\u0003\u0018J\u000eq\u001f\u0017D\u001e\fC\u001eMV\u0003[\u0005X4M\u0004F\u000fP\nzV|\u0003[\u0005Xig|\u0003[\u0005Xb\b\u0015\u0011[M\u0019x\u000b%J\u0001@X)M%V\tC\u0019w\b$F\u0018QVn\u001aV\t[\u0015V!V|\u0003[\u0005Xb\b\u0015\u0011[F\u001dz\u0019\u0013Q[\u0018XG\u0018\u0004E\u001aF\u001dF\b\u0015WU]\u00014FVK\u001aI\u001eG\u0004\fF@/X4MVE\u0017J\u0019`M\u0004B\u001fL\rgMK\u0003\u0016L\u0016<.\u0019Q\u0015@\nF\f\u0012J\u000eVT4\u0000\u001fMSM\u0019x\u000b%J\u0001@VlAVK\u001aI\u001eG\u0004\fFU\\Q=V|\u0003[\u0005Xb\b\u0015\u0011[TX)M\u0017A\b\r,q\u00155L\u0014W\u001c4GVq\u001eV\u0017x\u0018\u0002J\u0014KX9M\u0015F\u0015Q\u001dfDV\u000e[M\u0019x\u000b%J\u0001@X?M\u0004B\u001fL\rgV|\u0003[\u0005Xr\u0001\u0019B\u000f\u0005\u001c}\u001e\u0002B\u0015F\u001d4PVO\u001eK\u001f`\u0005^N\u001a]PeAVU\u001eFJ<]X\u0013R\fQ\u001eMV\u0003[\u0005X4MV\u0003[\u0005S4\u0000\u001fMSH\u0019lE\u0007\r\u0003\tXeC\u000f\nW\u0005H:]_\u0003V\u0005\nu\t\u001fV\b\u001er4MV\u0003\u001dI\u0017u\u0019V@\u0014S\u001df\f\u0011F[\u0018X<\\X\u0013[\bXg\u0000\u0019L\u000fM\u000b`\b\u0006\u000bV\u0014V$AV\u0012U\u0015T4\t\u001fP\u000fD\u0016w\b_\n[\u000fXG\u0018\u0004E\u001aF\u001dU\u0001\u0006K\u001a\u001er4MV\u0003\u0012CX<\u000e\u0019U\u001eW\u0019s\bV\u001fF\u0005H:]_\u0003\u001fL\u000bw\f\u0004G@/X4MVe\tD\u001fW\u0002\u001aL\t\u0005E4\u001b\u0013@O\rI:]Z\u0003J\u000bH8MG\rK\tXw\u0002\u0000F\tD\u001fqDM)\u0006/\bu\u0017g\u0004\u0002J\u0014K\u0003p.$\u000bv\rf\u000b\u0017@\u001ew\u001dw\u0019\u008b\u0006\u000eq\u001f\u0005J\u0014KX%XF)\u0012KXb\b\u0015\u0010[u\u0017g\u0004\u0002J\u0014KC\u001e\u0004\u0018\u0003\r@\u001b&M#uK\u001er{\u0018\u0002\u0003\r@\u001b&M\"F\u0003f\u0017{\u001f\u0012\u0018q/\u000e{\u0004\u0012\u0003\u0016D\u0011zE_\u0003\u0000/X4MVD\u0017z({\u001e\u001fW\u0012J\u00164PVU\u001eFL<=\u0019P\u0012Q\u0011{\u0003Z\u0003J\u000bH=V|\u0003[\u0005X@\b\u000e`\u0014J\npMK\u0003.sH/g\u000b)\fv\rf\u000b\u0017@\u001ed\u0014d\u0005\u0017\ba\nu\u001a;L\u001f@\ff\u0017f\u0003\u0013Q)D\u001c}\u0018\u0005";
                var4_3 = "\u0006\u000eq\u001f\u0005J\u0014KX%XF)\u0012KXb\b\u0015\u0011[q\u001dl.\u0019L\tAC\u001eg\u0003M\u0012C\u0017f\u0000VP\u001aH\bx\b\u0004\u0011?\u0005:q\u000b\u0019Q\u001ev\u0019y\u001d\u001aF\t\u001era\u0003\u001fE\u0014W\u00154\u001e\u0017N\u000bI\u001df_2\u0003:C\fq\u001f%B\u0016U\u0014q\u001fM)\u000eK\u0011r\u0002\u0004N[L\u0016`M2Q\u001aR5{\t\u0013\u0018qP\u0016}\u000b\u0019Q\u0016\u0005\u000eq\u000eD\u0003)@\u000b{\u0001\u0003W\u0012J\u0016/g\u0003M\u0012C\u0017f\u0000VU\u001eFL4>\u0003Q\u001dD\u001bq?\u0013@\u000f\u001era\u0003\u001fE\u0014W\u00154\u000b\u001aL\u001aQXW\u0002\u0004M\u001eW*u\t\u001fV\b\u001era\u0003\u001fE\u0014W\u00154\u000b\u001aL\u001aQXG\u0018\u0004E\u001aF\u001dU\u0001\u0006K\u001a\u001er\u001e\u0002\u0003W[S\u001dwYVe\tD\u001fW\u0002\u001aL\t\u001er\u001e\u001b\u0019J\u001f\u0005\u0015u\u0004\u0018\u000bR\u0005\u0003\u001eMV\u0003[L\u001e4E2Q\u001aR5{\t\u0013\u0003F\u0018X$DVXq\u0005X4MV\u0003[\u0005\u000eq\u000eB\u0003\u0019@\u001e{\u001f\u0013`\u0014I\u0017fMK\u0003\u000f@\u0000`\u0018\u0004FSg\u001dr\u0002\u0004F(D\u0015d\u0001\u0013QW\u0005,q\u00155L\u0014W\u001c=V|\u0003[\u0005X4MV\u0003\r@\u001b M\u0017E\u000f@\nW\u0002\u001aL\t\u0005E4\u0019\u0013[\u000fP\nqE7E\u000f@\nG\f\u001bS\u0017@\n8M\"F\u0003f\u0017{\u001f\u0012\n@/X4MV\u0003[\u0005Xb\b\u0015\u0017[A\u001dx\u0019\u0017\u0003F\u0005\u0019v\u001e^B\u001dQ\u001df.\u0019O\u0014WX9M\u0014F\u001dJ\nq.\u0019O\u0014WQ/gV\u0003[\u0005X4MVE\u0017J\u0019`M\u0012J\u001dC\u001df\b\u0018@\u001e\u0005E4\u0000\u0017[SH\u0019lE\u0012F\u0017Q\u0019:\u001fZ\u0003\u001f@\u0014`\fXDR\tXy\f\u000e\u000b\u001f@\u0014`\fXAW\u0005\u001cq\u0001\u0002BUDQ=V|\u0003[\u0005X4MV\u0003\u001dI\u0017u\u0019V@\u0014S\u001df\f\u0011F[\u0018Xg\u0000\u0019L\u000fM\u000b`\b\u0006\u000bK\u000bH$\\Z\u0003K\u000bH%_Z\u0003\u001fL\u001er\b\u0004F\u0015F\u001d=V|\u0003[\u0005X4MV\u0003\u0012CX<\u000e\u0019U\u001eW\u0019s\bV\u001fF\u0005H:]_\u0003\u001fL\u000bw\f\u0004G@/X4MV\u0003[\u0005XR\u001f\u0017D8J\u0014{\u001fV\u001e[S\u001dwY^\u0012U\u0015T4\\X\u0013W\u0005I:]Z\u0003\u0018J\u000eq\u001f\u0017D\u001e\fC\u001eMV\u0003[\u0005X4M\u0004F\u000fP\nzV|\u0003[\u0005Xig|\u0003[\u0005Xb\b\u0015\u0011[M\u0019x\u000b%J\u0001@X)M%V\tC\u0019w\b$F\u0018QVn\u001aV\t[\u0015V!V|\u0003[\u0005Xb\b\u0015\u0011[F\u001dz\u0019\u0013Q[\u0018XG\u0018\u0004E\u001aF\u001dF\b\u0015WU]\u00014FVK\u001aI\u001eG\u0004\fF@/X4MVE\u0017J\u0019`M\u0004B\u001fL\rgMK\u0003\u0016L\u0016<.\u0019Q\u0015@\nF\f\u0012J\u000eVT4\u0000\u001fMSM\u0019x\u000b%J\u0001@VlAVK\u001aI\u001eG\u0004\fFU\\Q=V|\u0003[\u0005Xb\b\u0015\u0011[TX)M\u0017A\b\r,q\u00155L\u0014W\u001c4GVq\u001eV\u0017x\u0018\u0002J\u0014KX9M\u0015F\u0015Q\u001dfDV\u000e[M\u0019x\u000b%J\u0001@X?M\u0004B\u001fL\rgV|\u0003[\u0005Xr\u0001\u0019B\u000f\u0005\u001c}\u001e\u0002B\u0015F\u001d4PVO\u001eK\u001f`\u0005^N\u001a]PeAVU\u001eFJ<]X\u0013R\fQ\u001eMV\u0003[\u0005X4MV\u0003[\u0005S4\u0000\u001fMSH\u0019lE\u0007\r\u0003\tXeC\u000f\nW\u0005H:]_\u0003V\u0005\nu\t\u001fV\b\u001er4MV\u0003\u001dI\u0017u\u0019V@\u0014S\u001df\f\u0011F[\u0018X<\\X\u0013[\bXg\u0000\u0019L\u000fM\u000b`\b\u0006\u000bV\u0014V$AV\u0012U\u0015T4\t\u001fP\u000fD\u0016w\b_\n[\u000fXG\u0018\u0004E\u001aF\u001dU\u0001\u0006K\u001a\u001er4MV\u0003\u0012CX<\u000e\u0019U\u001eW\u0019s\bV\u001fF\u0005H:]_\u0003\u001fL\u000bw\f\u0004G@/X4MVe\tD\u001fW\u0002\u001aL\t\u0005E4\u001b\u0013@O\rI:]Z\u0003J\u000bH8MG\rK\tXw\u0002\u0000F\tD\u001fqDM)\u0006/\rg\u001dr\u0002\u0004F(D\u0015d\u0001\u0013Q\nw\u001dg\u0002\u001aV\u000fL\u0017z\u04a7\u0006\u000eq\u001f\u0005J\u0014KX%XF)\u0012KXb\b\u0015\u0011[q\u001dl.\u0019L\tAC\u001eg\u0003M\u0012C\u0017f\u0000VP\u001aH\bx\b\u0004\u0011?\u0005:q\u000b\u0019Q\u001ev\u0019y\u001d\u001aF\t\u001era\u0003\u001fE\u0014W\u00154\u001e\u0017N\u000bI\u001df_2\u0003:C\fq\u001f%B\u0016U\u0014q\u001fM)\u000eK\u0011r\u0002\u0004N[L\u0016`M2Q\u001aR5{\t\u0013\u0018qP\u0016}\u000b\u0019Q\u0016\u0005\u000eq\u000eD\u0003)@\u000b{\u0001\u0003W\u0012J\u0016/g\u0003M\u0012C\u0017f\u0000VU\u001eFL4>\u0003Q\u001dD\u001bq?\u0013@\u000f\u001era\u0003\u001fE\u0014W\u00154\u000b\u001aL\u001aQXW\u0002\u0004M\u001eW*u\t\u001fV\b\u001era\u0003\u001fE\u0014W\u00154\u000b\u001aL\u001aQXG\u0018\u0004E\u001aF\u001dU\u0001\u0006K\u001a\u001er\u001e\u0002\u0003W[S\u001dwYVe\tD\u001fW\u0002\u001aL\t\u001er\u001e\u001b\u0019J\u001f\u0005\u0015u\u0004\u0018\u000bR\u0005\u0003\u001eMV\u0003[L\u001e4E2Q\u001aR5{\t\u0013\u0003F\u0018X$DVXq\u0005X4MV\u0003[\u0005\u000eq\u000eB\u0003\u0019@\u001e{\u001f\u0013`\u0014I\u0017fMK\u0003\u000f@\u0000`\u0018\u0004FSg\u001dr\u0002\u0004F(D\u0015d\u0001\u0013QW\u0005,q\u00155L\u0014W\u001c=V|\u0003[\u0005X4MV\u0003\r@\u001b M\u0017E\u000f@\nW\u0002\u001aL\t\u0005E4\u0019\u0013[\u000fP\nqE7E\u000f@\nG\f\u001bS\u0017@\n8M\"F\u0003f\u0017{\u001f\u0012\n@/X4MV\u0003[\u0005Xb\b\u0015\u0017[A\u001dx\u0019\u0017\u0003F\u0005\u0019v\u001e^B\u001dQ\u001df.\u0019O\u0014WX9M\u0014F\u001dJ\nq.\u0019O\u0014WQ/gV\u0003[\u0005X4MVE\u0017J\u0019`M\u0012J\u001dC\u001df\b\u0018@\u001e\u0005E4\u0000\u0017[SH\u0019lE\u0012F\u0017Q\u0019:\u001fZ\u0003\u001f@\u0014`\fXDR\tXy\f\u000e\u000b\u001f@\u0014`\fXAW\u0005\u001cq\u0001\u0002BUDQ=V|\u0003[\u0005X4MV\u0003\u001dI\u0017u\u0019V@\u0014S\u001df\f\u0011F[\u0018Xg\u0000\u0019L\u000fM\u000b`\b\u0006\u000bK\u000bH$\\Z\u0003K\u000bH%_Z\u0003\u001fL\u001er\b\u0004F\u0015F\u001d=V|\u0003[\u0005X4MV\u0003\u0012CX<\u000e\u0019U\u001eW\u0019s\bV\u001fF\u0005H:]_\u0003\u001fL\u000bw\f\u0004G@/X4MV\u0003[\u0005XR\u001f\u0017D8J\u0014{\u001fV\u001e[S\u001dwY^\u0012U\u0015T4\\X\u0013W\u0005I:]Z\u0003\u0018J\u000eq\u001f\u0017D\u001e\fC\u001eMV\u0003[\u0005X4M\u0004F\u000fP\nzV|\u0003[\u0005Xig|\u0003[\u0005Xb\b\u0015\u0011[M\u0019x\u000b%J\u0001@X)M%V\tC\u0019w\b$F\u0018QVn\u001aV\t[\u0015V!V|\u0003[\u0005Xb\b\u0015\u0011[F\u001dz\u0019\u0013Q[\u0018XG\u0018\u0004E\u001aF\u001dF\b\u0015WU]\u00014FVK\u001aI\u001eG\u0004\fF@/X4MVE\u0017J\u0019`M\u0004B\u001fL\rgMK\u0003\u0016L\u0016<.\u0019Q\u0015@\nF\f\u0012J\u000eVT4\u0000\u001fMSM\u0019x\u000b%J\u0001@VlAVK\u001aI\u001eG\u0004\fFU\\Q=V|\u0003[\u0005Xb\b\u0015\u0011[TX)M\u0017A\b\r,q\u00155L\u0014W\u001c4GVq\u001eV\u0017x\u0018\u0002J\u0014KX9M\u0015F\u0015Q\u001dfDV\u000e[M\u0019x\u000b%J\u0001@X?M\u0004B\u001fL\rgV|\u0003[\u0005Xr\u0001\u0019B\u000f\u0005\u001c}\u001e\u0002B\u0015F\u001d4PVO\u001eK\u001f`\u0005^N\u001a]PeAVU\u001eFJ<]X\u0013R\fQ\u001eMV\u0003[\u0005X4MV\u0003[\u0005S4\u0000\u001fMSH\u0019lE\u0007\r\u0003\tXeC\u000f\nW\u0005H:]_\u0003V\u0005\nu\t\u001fV\b\u001er4MV\u0003\u001dI\u0017u\u0019V@\u0014S\u001df\f\u0011F[\u0018X<\\X\u0013[\bXg\u0000\u0019L\u000fM\u000b`\b\u0006\u000bV\u0014V$AV\u0012U\u0015T4\t\u001fP\u000fD\u0016w\b_\n[\u000fXG\u0018\u0004E\u001aF\u001dU\u0001\u0006K\u001a\u001er4MV\u0003\u0012CX<\u000e\u0019U\u001eW\u0019s\bV\u001fF\u0005H:]_\u0003\u001fL\u000bw\f\u0004G@/X4MVe\tD\u001fW\u0002\u001aL\t\u0005E4\u001b\u0013@O\rI:]Z\u0003J\u000bH8MG\rK\tXw\u0002\u0000F\tD\u001fqDM)\u0006/\bu\u0017g\u0004\u0002J\u0014K\u0003p.$\u000bv\rf\u000b\u0017@\u001ew\u001dw\u0019\u008b\u0006\u000eq\u001f\u0005J\u0014KX%XF)\u0012KXb\b\u0015\u0010[u\u0017g\u0004\u0002J\u0014KC\u001e\u0004\u0018\u0003\r@\u001b&M#uK\u001er{\u0018\u0002\u0003\r@\u001b&M\"F\u0003f\u0017{\u001f\u0012\u0018q/\u000e{\u0004\u0012\u0003\u0016D\u0011zE_\u0003\u0000/X4MVD\u0017z({\u001e\u001fW\u0012J\u00164PVU\u001eFL<=\u0019P\u0012Q\u0011{\u0003Z\u0003J\u000bH=V|\u0003[\u0005X@\b\u000e`\u0014J\npMK\u0003.sH/g\u000b)\fv\rf\u000b\u0017@\u001ed\u0014d\u0005\u0017\ba\nu\u001a;L\u001f@\ff\u0017f\u0003\u0013Q)D\u001c}\u0018\u0005".length();
                var1_4 = 1191;
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
                    var5[var3_1++] = C.a(C.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0006\u000eq\u001f\u0005J\u0014KX%XF)\u0012KXb\b\u0015\u0010[u\u0017g\u0004\u0002J\u0014KC\u001e\u0004\u0018\u0003\r@\u001b&M#uK\u001er{\u0018\u0002\u0003\r@\u001b&M\"F\u0003f\u0017{\u001f\u0012\u0018q/\u000e{\u0004\u0012\u0003\u0016D\u0011zE_\u0003\u0000/X4MVD\u0017z({\u001e\u001fW\u0012J\u00164PVU\u001eFL<=\u0019P\u0012Q\u0011{\u0003Z\u0003J\u000bH=V|\u0003[\u0005X@\b\u000e`\u0014J\npMK\u0003.sH/g\u000b)\fd\u001e`\b\u0004p\u001aH\bx\b\u0004";
                    var4_3 = "\u0006\u000eq\u001f\u0005J\u0014KX%XF)\u0012KXb\b\u0015\u0010[u\u0017g\u0004\u0002J\u0014KC\u001e\u0004\u0018\u0003\r@\u001b&M#uK\u001er{\u0018\u0002\u0003\r@\u001b&M\"F\u0003f\u0017{\u001f\u0012\u0018q/\u000e{\u0004\u0012\u0003\u0016D\u0011zE_\u0003\u0000/X4MVD\u0017z({\u001e\u001fW\u0012J\u00164PVU\u001eFL<=\u0019P\u0012Q\u0011{\u0003Z\u0003J\u000bH=V|\u0003[\u0005X@\b\u000e`\u0014J\npMK\u0003.sH/g\u000b)\fd\u001e`\b\u0004p\u001aH\bx\b\u0004".length();
                    var1_4 = 139;
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
                    var5[var3_1++] = C.a(C.a(v1));
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
        C.A = var5;
        C.a = C.A[7];
        C.b = C.A[0];
    }

    private Z a(Y y2, float f2, float f3) {
        float f4 = y2.a * f2;
        float f5 = y2.c * f2;
        float f6 = y2.d * f3;
        float f7 = (float)this.w - (y2.b + y2.d) * f3;
        return new Z(f4, f7, f5, f6, y2.e * Math.min(f2, f3));
    }

    public static void a(PoseStack poseStack, float f2, float f3, float f4, float f5, float f6, float f7) {
        C c2 = c;
        if (c2 == null || poseStack == null || f4 <= 0.0f || f5 <= 0.0f || f7 <= 0.001f) {
            return;
        }
        Matrix4f matrix4f = poseStack.last().pose();
        Vector3f vector3f = matrix4f.transformPosition(f2, f3, 0.0f, new Vector3f());
        Vector3f vector3f2 = matrix4f.transformPosition(f2 + f4, f3, 0.0f, new Vector3f());
        Vector3f vector3f3 = matrix4f.transformPosition(f2, f3 + f5, 0.0f, new Vector3f());
        Vector3f vector3f4 = matrix4f.transformPosition(f2 + f4, f3 + f5, 0.0f, new Vector3f());
        float f8 = Math.min(Math.min(vector3f.x, vector3f2.x), Math.min(vector3f3.x, vector3f4.x));
        float f9 = Math.min(Math.min(vector3f.y, vector3f2.y), Math.min(vector3f3.y, vector3f4.y));
        float f10 = Math.max(Math.max(vector3f.x, vector3f2.x), Math.max(vector3f3.x, vector3f4.x));
        float f11 = Math.max(Math.max(vector3f.y, vector3f2.y), Math.max(vector3f3.y, vector3f4.y));
        float f12 = (f10 - f8) / f4;
        float f13 = (f11 - f9) / f5;
        c2.i.add(new Y(f8, f9, f10 - f8, f11 - f9, f6 * Math.min(f12, f13), Math.max(0.0f, Math.min(1.0f, f7))));
    }

    private static void a(FloatBuffer floatBuffer, float f2, float f3, float f4, float f5, float f6) {
        floatBuffer.put(f2).put(f3).put(f4).put(f5).put(f6);
    }
}

