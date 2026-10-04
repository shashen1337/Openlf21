/*
 * Decompiled with CFR 0.152.
 */
package lf21.render.a;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import lf21.render.a.a;
import lf21.render.a.c;
import lf21.render.a.f;
import lombok.Generated;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.slf4j.Logger;

public class d {
    private final Map<String, Integer> d;
    private static final String[] j;
    private Matrix4f g;
    private Matrix4f h;
    private final boolean c;
    private final f f;
    private final int b;
    private final f e;
    private static int i;
    private static final Logger a;

    public d(String string, String string2, Supplier<Map<Integer, String>> supplier, String string3, String string4) {
        this.d = new HashMap<String, Integer>();
        this.b = GL20.glCreateProgram();
        String[] stringArray = j;
        int n2 = lf21.render.a.d.a(lf21.render.a.a.b(string + ".fsh").c(), 35632);
        int n3 = lf21.render.a.d.a(lf21.render.a.a.b(string2 + ".vsh").c(), 35633);
        if (n2 == 0 || n3 == 0) {
            if (n2 != 0) {
                GL20.glDeleteShader((int)n2);
            }
            if (n3 != 0) {
                GL20.glDeleteShader((int)n3);
            }
            stringArray = j;
            a.error("Shader '{}' disabled: compilation failed; the effect will be skipped.", (Object)string);
            this.e = null;
            this.f = null;
            this.c = false;
            return;
        }
        GL20.glAttachShader((int)this.b, (int)n2);
        GL20.glAttachShader((int)this.b, (int)n3);
        for (Map.Entry<Integer, String> entry : supplier.get().entrySet()) {
            GL20.glEnableVertexAttribArray((int)entry.getKey());
            GL20.glBindAttribLocation((int)this.b, (int)entry.getKey(), (CharSequence)entry.getValue());
        }
        GL20.glLinkProgram((int)this.b);
        if (GL20.glGetProgrami((int)this.b, (int)35714) == 0) {
            a.error(GL20.glGetProgramInfoLog((int)this.b, (int)Short.MAX_VALUE));
            a.error("Shader '{}' disabled: program link failed; the effect will be skipped.", (Object)string);
            GL20.glDeleteShader((int)n2);
            GL20.glDeleteShader((int)n3);
            this.e = null;
            this.f = null;
            this.c = false;
            return;
        }
        GL20.glDeleteShader((int)n2);
        GL20.glDeleteShader((int)n3);
        this.e = (f)new f(string3).a(this.b);
        this.f = (f)new f(string4).a(this.b);
        this.c = true;
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
                    case 0 -> 76;
                    case 1 -> 28;
                    case 2 -> 36;
                    case 3 -> 72;
                    case 4 -> 15;
                    case 5 -> 57;
                    default -> 34;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public void a() {
        if (!this.c) {
            return;
        }
        i = GL20.glGetInteger((int)35725);
        GL20.glUseProgram((int)this.b);
        this.a(RenderSystem.getModelViewMatrix());
        this.b(RenderSystem.getProjectionMatrix());
    }

    public int a(String string) {
        if (!this.d.containsKey(string)) {
            this.d.put(string, GL20.glGetUniformLocation((int)this.b, (CharSequence)string));
        }
        return this.d.get(string);
    }

    @Generated
    public int c() {
        return this.b;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[10];
                var3_1 = 0;
                var2_2 = ":yV<jA\u0007\u001cnK\"BXVF\u001ftE,jK\u0002kgYo/]K?}F$j]\u0018llV'hKC!<H!aR\u0002*}M$j]\u0019lhL-/\\D*yG</NK p\u0004*j\u0019Q'uT8j]\f&\n}M$j]\u00028s\u0004+`TR%pAh|QC(yVh'M[<y\u00043r\u0010\u0018FgY\u0007\u001cnK\"BXV\u0004bzW \f\u0001s@-coK)ki){\u0004bjW ";
                var4_3 = ":yV<jA\u0007\u001cnK\"BXVF\u001ftE,jK\u0002kgYo/]K?}F$j]\u0018llV'hKC!<H!aR\u0002*}M$j]\u0019lhL-/\\D*yG</NK p\u0004*j\u0019Q'uT8j]\f&\n}M$j]\u00028s\u0004+`TR%pAh|QC(yVh'M[<y\u00043r\u0010\u0018FgY\u0007\u001cnK\"BXV\u0004bzW \f\u0001s@-coK)ki){\u0004bjW ".length();
                var1_4 = 6;
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
                    var5[var3_1++] = lf21.render.a.d.a(lf21.render.a.d.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0001s@-coK)ki){E\u001ftE,jK\u0002kgYo/]K?}F$j]\u0018l\u007fK%\u007fPN-hM'a\u0019D-uH-k\u0002\u00028tAhj_D)\u007fPhxPN <F-/JI%lT-k\u0017";
                    var4_3 = "\u0001s@-coK)ki){E\u001ftE,jK\u0002kgYo/]K?}F$j]\u0018l\u007fK%\u007fPN-hM'a\u0019D-uH-k\u0002\u00028tAhj_D)\u007fPhxPN <F-/JI%lT-k\u0017".length();
                    var1_4 = 12;
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
                    var5[var3_1++] = lf21.render.a.d.a(lf21.render.a.d.b(v1));
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
        lf21.render.a.d.j = var5;
        lf21.render.a.d.a = LogUtils.getLogger();
    }

    @Generated
    public Matrix4f h() {
        return this.g;
    }

    public d(String string) {
        String[] stringArray = j;
        this(string, "vertex", lf21.render.a.c.b, "ModelViewMat", "ProjMat");
    }

    public void b() {
        GL20.glUseProgram((int)i);
    }

    public d(String string, String string2, Supplier<Map<Integer, String>> supplier) {
        String[] stringArray = j;
        this(string, string2, supplier, "ModelViewMat", "ProjMat");
    }

    public d(String string, Supplier<Map<Integer, String>> supplier) {
        String[] stringArray = j;
        this(string, string, supplier, "ModelViewMat", "ProjMat");
    }

    public d(String string, Supplier<Map<Integer, String>> supplier, String string2, String string3) {
        this(string, string, supplier, string2, string3);
    }

    @Generated
    public f g() {
        return this.f;
    }

    @Generated
    public Map<String, Integer> e() {
        return this.d;
    }

    public void b(Matrix4f matrix4f) {
        if (this.h != matrix4f) {
            this.f.a(matrix4f);
            this.h = matrix4f;
        }
    }

    @Generated
    public boolean d() {
        return this.c;
    }

    public void a(Matrix4f matrix4f) {
        if (this.g != matrix4f) {
            this.e.a(matrix4f);
            this.g = matrix4f;
        }
    }

    @Generated
    public Matrix4f i() {
        return this.h;
    }

    @Generated
    public f f() {
        return this.e;
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x22);
        }
        return cArray;
    }

    private static int a(String string, int n2) {
        int n3 = GL20.glCreateShader((int)n2);
        Matcher matcher = lf21.render.a.c.c.matcher(string);
        while (matcher.find()) {
            boolean bl;
            boolean bl2 = bl = matcher.group(2) == null;
            if (!bl) continue;
            String string2 = matcher.group(3);
            String string3 = lf21.render.a.a.b(string2).c();
            string = string.replaceAll(lf21.render.a.c.c.pattern(), string3);
        }
        GL20.glShaderSource((int)n3, (CharSequence)string);
        GL20.glCompileShader((int)n3);
        if (GL20.glGetShaderi((int)n3, (int)35713) == 0) {
            a.error("Failed to compile shader (type {}):\n{}", (Object)n2, (Object)GL20.glGetShaderInfoLog((int)n3, (int)Short.MAX_VALUE));
            GL20.glDeleteShader((int)n3);
            return 0;
        }
        return n3;
    }
}

