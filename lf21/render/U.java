/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import net.minecraft.resources.ResourceLocation;

public final class U {
    private final int a;
    private final int b;
    private final int c;
    private final ResourceLocation d;

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public U(int n2, ResourceLocation resourceLocation, int n3, int n4) {
        this.a = n2;
        this.b = n3;
        this.c = n4;
        this.d = resourceLocation;
    }

    public U(int n2, int n3, int n4) {
        this.a = n2;
        this.b = n3;
        this.c = n4;
        this.d = null;
    }

    public U(ResourceLocation resourceLocation, int n2, int n3) {
        this.a = 0;
        this.b = n2;
        this.c = n3;
        this.d = resourceLocation;
    }

    public int a() {
        return this.a;
    }

    public ResourceLocation d() {
        return this.d;
    }
}

