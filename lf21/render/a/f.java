/*
 * Decompiled with CFR 0.152.
 */
package lf21.render.a;

import java.nio.FloatBuffer;
import lf21.render.a.b;
import lf21.render.a.e;
import lombok.Generated;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

public class f
extends e<f> {
    private final FloatBuffer f;
    private boolean d;
    private final FloatBuffer e = MemoryUtil.memAllocFloat((int)16);

    @Generated
    public FloatBuffer f() {
        return this.e;
    }

    public void a(boolean bl, FloatBuffer floatBuffer) {
        this.d = bl;
        floatBuffer.mark();
        this.e.clear();
        this.e.put(floatBuffer);
        this.e.rewind();
        floatBuffer.reset();
        int n2 = this.c();
        if (n2 >= 0) {
            GL20.glUniformMatrix4fv((int)n2, (boolean)bl, (FloatBuffer)this.e);
        }
    }

    @Generated
    public FloatBuffer g() {
        return this.f;
    }

    protected void d() {
        b.a(this.e, 0.0f);
    }

    @Generated
    public boolean e() {
        return this.d;
    }

    public f(String string) {
        super(string);
        this.f = MemoryUtil.memAllocFloat((int)16);
    }

    public float a(int n2, int n3) {
        int n4 = this.d ? n3 * 4 + n2 : n2 * 4 + n3;
        return this.e.get(n4);
    }

    public void a(Matrix4f matrix4f) {
        this.f.clear();
        b.a(this.f, matrix4f);
        this.a(false, this.f);
    }
}

