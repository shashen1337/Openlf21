/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.gui.l;
import lf21.utils.a.e;
import lombok.Generated;
import net.minecraft.client.gui.GuiGraphics;

public abstract class c {
    protected float a;
    protected float b;
    protected float c;
    protected float d;
    protected final e e = new e();

    public abstract void a(l var1, GuiGraphics var2, PoseStack var3, int var4, int var5, float var6, float var7);

    public void a() {
    }

    public boolean a(double d2, double d3, int n2) {
        return false;
    }

    public boolean b(double d2, double d3, int n2) {
        return false;
    }

    public boolean a(double d2, double d3, double d4) {
        return false;
    }

    public float b() {
        return this.f();
    }

    @Generated
    public float c() {
        return this.a;
    }

    @Generated
    public void a(float f2) {
        this.a = f2;
    }

    @Generated
    public float d() {
        return this.b;
    }

    @Generated
    public void b(float f2) {
        this.b = f2;
    }

    @Generated
    public float e() {
        return this.c;
    }

    @Generated
    public void c(float f2) {
        this.c = f2;
    }

    @Generated
    public float f() {
        return this.d;
    }

    @Generated
    public void d(float f2) {
        this.d = f2;
    }

    @Generated
    public e g() {
        return this.e;
    }
}

