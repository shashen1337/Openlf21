/*
 * Decompiled with CFR 0.152.
 */
package lf21.render.a;

import lombok.Generated;
import org.lwjgl.opengl.GL20;

public abstract class e<T extends e<?>> {
    private int c;
    private final String a;
    private int b;

    public T a(int n2) {
        this.b = n2;
        this.c = GL20.glGetUniformLocation((int)this.b, (CharSequence)this.a);
        return (T)this;
    }

    @Generated
    public e(String string) {
        this.a = string;
    }

    @Generated
    public int b() {
        return this.b;
    }

    @Generated
    public String a() {
        return this.a;
    }

    @Generated
    public int c() {
        return this.c;
    }
}

