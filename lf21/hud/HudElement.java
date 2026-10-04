/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.gui.k;
import lf21.hud.D;
import lf21.hud.E;
import lf21.hud.HudElement$InternalPartType;
import lf21.hud.HudElement$PersistedPart;
import lf21.hud.s;
import lf21.hud.t;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.utils.render.RenderUtil;
import lombok.Generated;

public abstract class HudElement
extends Module {
    private static final long A = 280000000L;
    private final Map<String, s> l = new LinkedHashMap<String, s>();
    protected float n;
    private static final ThreadLocal<t> j;
    private float F;
    private long D;
    private float z;
    protected float q = 1.0f;
    private long H;
    protected float m;
    protected float p;
    private float G = Float.NaN;
    private float E;
    private static final String[] cb;
    protected float u = 1.0f;
    private float y;
    private boolean x = false;
    private boolean B;
    protected float o;
    protected float v;
    protected float t;
    protected float w;
    private static final ThreadLocal<String> k;
    private boolean C;
    protected float s;
    protected float r = 1.0f;

    public List<HudElement$PersistedPart> getPersistedInternalParts() {
        ArrayList<HudElement$PersistedPart> arrayList = new ArrayList<HudElement$PersistedPart>();
        this.l.forEach((string, s2) -> arrayList.add(new HudElement$PersistedPart((String)string, s2.a, s2.b, s2.c, s2.d)));
        return arrayList;
    }

    protected float p() {
        return this.m + this.o * 0.5f;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected final void a(String string, Runnable runnable) {
        this.a(string);
        String string2 = k.get();
        k.set(string);
        try {
            runnable.run();
        }
        finally {
            if (string2 == null) {
                k.remove();
            } else {
                k.set(string2);
            }
        }
    }

    public static t o() {
        return j.get();
    }

    @Generated
    public void f(float f2) {
        this.p = f2;
    }

    public abstract void a(Render2DEvent var1, float var2, float var3);

    public boolean a(int n2, int n3) {
        return RenderUtil.a(this.m(), this.n(), this.k(), this.l(), n2, n3);
    }

    public void b(float f2) {
        this.m = f2 - (this.o - this.k()) * 0.5f;
    }

    @Generated
    public float v() {
        return this.p;
    }

    public void a(float f2, float f3) {
        float f4 = Math.max(0.0f, f2 - this.k());
        float f5 = Math.max(0.0f, f3 - this.l());
        this.b(Math.max(0.0f, Math.min(f4, this.m())));
        this.c(Math.max(0.0f, Math.min(f5, this.n())));
    }

    @Generated
    public float getProgressScale() {
        return this.u;
    }

    protected final void b(String string) {
        this.l.putIfAbsent(string, new s(HudElement$InternalPartType.PROGRESS));
    }

    public abstract void a();

    @Generated
    public float x() {
        return this.z;
    }

    private static void a(t t2) {
        if (t2 == null) {
            j.remove();
        } else {
            j.set(t2);
        }
    }

    public static E a(float f2, float f3, float f4, float f5) {
        return HudElement.a(null, f2, f3, f4, f5);
    }

    @Generated
    public float getTextOffsetX() {
        return this.s;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String c(char[] cArray) {
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
                    case 0 -> 26;
                    case 1 -> 81;
                    case 2 -> 43;
                    case 3 -> 86;
                    case 4 -> 4;
                    case 5 -> 79;
                    default -> 11;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Generated
    public void setProgressOffsetX(float f2) {
        this.v = f2;
    }

    public boolean h() {
        return false;
    }

    public float getInternalPartScale(String string, HudElement$InternalPartType hudElement$InternalPartType) {
        s s2 = this.l.get(string);
        if (s2 != null && s2.a == hudElement$InternalPartType) {
            return s2.b;
        }
        return hudElement$InternalPartType == HudElement$InternalPartType.TEXT ? this.r : this.u;
    }

    @Generated
    public void setY(float f2) {
        this.n = f2;
    }

    private float a(long l2) {
        float f2 = Math.max(0.0f, Math.min(1.0f, (float)(l2 - this.D) / 2.8E8f));
        if (this.C) {
            float f3 = f2 - 1.0f;
            float f4 = 1.0f + 2.70158f * f3 * f3 * f3 + 1.70158f * f3 * f3;
            return this.E + (1.0f - this.E) * f4;
        }
        float f5 = f2 * f2 * f2;
        return Math.max(0.0f, this.E * (1.0f - f5));
    }

    @Generated
    public void g(float f2) {
        this.y = f2;
    }

    protected final void a(String string) {
        this.l.putIfAbsent(string, new s(HudElement$InternalPartType.TEXT));
    }

    private void a(PoseStack poseStack, float f2) {
        float f3;
        float f4;
        if (Math.abs(this.q - 1.0f) >= 0.001f) {
            f4 = this.m + this.o * 0.5f;
            f3 = this.n + this.p * 0.5f;
            poseStack.translate(f4, f3, 0.0f);
            poseStack.scale(this.q, this.q, 1.0f);
            poseStack.translate(-f4, -f3, 0.0f);
        }
        if (Math.abs((f4 = Math.max(0.001f, f2)) - 1.0f) < 0.001f) {
            return;
        }
        f3 = this.p();
        float f5 = this.q();
        poseStack.translate(f3, f5, 0.0f);
        poseStack.scale(f4, f4, 1.0f);
        poseStack.translate(-f3, -f5, 0.0f);
    }

    @Generated
    public float w() {
        return this.y;
    }

    private float s() {
        if (HudElement.mc.screen instanceof k) {
            this.B = true;
            this.C = this.isEnabled();
            this.F = 1.0f;
            return 1.0f;
        }
        long l2 = System.nanoTime();
        boolean bl = this.isEnabled();
        if (!this.B) {
            this.B = true;
            this.C = bl;
            this.F = 0.0f;
            this.E = 0.0f;
            this.D = l2;
        } else if (bl != this.C) {
            this.F = this.a(l2);
            this.C = bl;
            this.E = this.F;
            this.D = l2;
        }
        this.F = this.a(l2);
        return this.F;
    }

    @Generated
    public void e(float f2) {
        this.o = f2;
    }

    public void t() {
        this.x = false;
    }

    @Generated
    public void h(float f2) {
        this.z = f2;
    }

    public float getInternalPartOffsetY(String string, HudElement$InternalPartType hudElement$InternalPartType) {
        s s2 = this.l.get(string);
        if (s2 != null && s2.a == hudElement$InternalPartType) {
            return s2.d;
        }
        return hudElement$InternalPartType == HudElement$InternalPartType.TEXT ? this.t : this.w;
    }

    @Generated
    public float u() {
        return this.o;
    }

    public void setProgressScale(float f2) {
        this.u = Math.max(0.5f, Math.min(2.5f, f2));
    }

    public HudElement(String string) {
        super(string, Category.RENDER);
    }

    @Generated
    public float getTextOffsetY() {
        return this.t;
    }

    private static char[] g(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xB);
        }
        return cArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "N4S\"\bJ#D1v*xi";
        int n3 = "N4S\"\bJ#D1v*xi".length();
        int n4 = 4;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = HudElement.c(HudElement.g(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                cb = stringArray;
                j = new ThreadLocal();
                k = new ThreadLocal();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @Generated
    public float getScale() {
        return this.q;
    }

    public boolean mousePressed(int n2, int n3, int n4) {
        if (this.a(n2, n3) && n4 == 0) {
            this.x = true;
            this.y = (float)n2 - this.m();
            this.z = (float)n3 - this.n();
            return true;
        }
        return false;
    }

    @Generated
    public boolean isDragging() {
        return this.x;
    }

    private static float a(float f2) {
        return Math.max(0.5f, Math.min(2.5f, f2));
    }

    public float l() {
        return this.p * this.q;
    }

    public void c(float f2) {
        this.n = f2 - (this.p - this.l()) * 0.5f;
    }

    public abstract void a(GlRenderEvent var1, float var2, float var3);

    public void i() {
        this.r = 1.0f;
        this.s = 0.0f;
        this.t = 0.0f;
        this.u = 1.0f;
        this.v = 0.0f;
        this.w = 0.0f;
        this.l.values().forEach(s::a);
    }

    public float getInternalPartOffsetX(String string, HudElement$InternalPartType hudElement$InternalPartType) {
        s s2 = this.l.get(string);
        if (s2 != null && s2.a == hudElement$InternalPartType) {
            return s2.c;
        }
        return hudElement$InternalPartType == HudElement$InternalPartType.TEXT ? this.s : this.v;
    }

    public float n() {
        return this.n + (this.p - this.l()) * 0.5f;
    }

    @Generated
    public void setTextOffsetY(float f2) {
        this.t = f2;
    }

    public void mouseDragged(int n2, int n3) {
        this.b((float)n2 - this.y);
        this.c((float)n3 - this.z);
    }

    @Generated
    public float getProgressOffsetY() {
        return this.w;
    }

    public List<D> j() {
        if (this.l.isEmpty()) {
            String[] stringArray = cb;
            return List.of(new D("Text", HudElement$InternalPartType.TEXT), new D("Progress", HudElement$InternalPartType.PROGRESS));
        }
        ArrayList<D> arrayList = new ArrayList<D>();
        this.l.forEach((string, s2) -> arrayList.add(new D((String)string, s2.a)));
        return arrayList;
    }

    @Generated
    public void setProgressOffsetY(float f2) {
        this.w = f2;
    }

    protected HudElement(String string, Category category) {
        super(string, category);
    }

    @Generated
    public float getY() {
        return this.n;
    }

    public final boolean r() {
        return this.isEnabled() || this.F > 0.002f;
    }

    public void a(String string, HudElement$InternalPartType hudElement$InternalPartType, float f2, float f3) {
        s s2 = this.l.get(string);
        if (s2 != null && s2.a == hudElement$InternalPartType) {
            s2.c = f2;
            s2.d = f3;
        } else if (hudElement$InternalPartType == HudElement$InternalPartType.TEXT) {
            this.s = f2;
            this.t = f3;
        } else {
            this.v = f2;
            this.w = f3;
        }
    }

    @Generated
    public void setTextOffsetX(float f2) {
        this.s = f2;
    }

    protected float q() {
        return this.n + this.p * 0.5f;
    }

    public float m() {
        return this.m + (this.o - this.k()) * 0.5f;
    }

    protected final float d(float f2) {
        f2 = Math.max(1.0f, f2);
        if (HudElement.mc.screen instanceof k || Float.isNaN(this.G)) {
            this.G = f2;
            this.H = System.nanoTime();
            return f2;
        }
        long l2 = System.nanoTime();
        float f3 = this.H == 0L ? 0.016666668f : Math.min(0.1f, (float)(l2 - this.H) / 1.0E9f);
        this.H = l2;
        float f4 = 1.0f - (float)Math.exp(-13.5f * f3);
        this.G += (f2 - this.G) * f4;
        if (Math.abs(this.G - f2) < 0.05f) {
            this.G = f2;
        }
        return this.G;
    }

    @Generated
    public void setDragging(boolean bl) {
        this.x = bl;
    }

    @Generated
    public float getX() {
        return this.m;
    }

    public void setScale(float f2) {
        this.q = Math.max(0.5f, Math.min(2.0f, f2));
    }

    public void setTextScale(float f2) {
        this.r = Math.max(0.5f, Math.min(2.5f, f2));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void a(Render2DEvent render2DEvent) {
        PoseStack poseStack = render2DEvent.a();
        poseStack.pushPose();
        float f2 = this.s();
        this.a(poseStack, f2);
        try {
            this.a(render2DEvent, this.m, this.n);
        }
        finally {
            poseStack.popPose();
        }
    }

    public static E a(String string, float f2, float f3, float f4, float f5) {
        t t2 = j.get();
        if (t2 == null) {
            return new E(f2, f3, f4, f5);
        }
        HudElement hudElement = t2.a;
        s s2 = string == null ? null : hudElement.l.get(string);
        float f6 = s2 != null && s2.a == HudElement$InternalPartType.PROGRESS ? s2.b : hudElement.u;
        float f7 = s2 != null && s2.a == HudElement$InternalPartType.PROGRESS ? s2.c : hudElement.v;
        float f8 = s2 != null && s2.a == HudElement$InternalPartType.PROGRESS ? s2.d : hudElement.w;
        float f9 = f4 * f6;
        float f10 = f5 * f6;
        return new E(f2 + f7 - (f9 - f4) * 0.5f, f3 + f8 - (f10 - f5) * 0.5f, f9, f10);
    }

    @Generated
    public float getTextScale() {
        return this.r;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void a(GlRenderEvent glRenderEvent) {
        boolean bl;
        PoseStack poseStack = glRenderEvent.b();
        PoseStack poseStack2 = glRenderEvent.c() != null ? glRenderEvent.c().b() : null;
        poseStack.pushPose();
        float f2 = this.s();
        this.a(poseStack, f2);
        boolean bl2 = bl = poseStack2 != null && poseStack2 != poseStack;
        if (bl) {
            poseStack2.pushPose();
            this.a(poseStack2, f2);
        }
        try {
            this.a(glRenderEvent, this.m, this.n);
        }
        finally {
            if (bl) {
                poseStack2.popPose();
            }
            poseStack.popPose();
        }
    }

    @Generated
    public float getProgressOffsetX() {
        return this.v;
    }

    public void a(String string, HudElement$InternalPartType hudElement$InternalPartType, float f2) {
        s s2 = this.l.get(string);
        if (s2 != null && s2.a == hudElement$InternalPartType) {
            s2.b = HudElement.a(f2);
        } else if (hudElement$InternalPartType == HudElement$InternalPartType.TEXT) {
            this.setTextScale(f2);
        } else {
            this.setProgressScale(f2);
        }
    }

    public void restoreInternalPart(String string2, HudElement$InternalPartType hudElement$InternalPartType, float f2, float f3, float f4) {
        s s2 = this.l.computeIfAbsent(string2, string -> new s(hudElement$InternalPartType));
        if (s2.a != hudElement$InternalPartType) {
            return;
        }
        s2.b = HudElement.a(f2);
        s2.c = f3;
        s2.d = f4;
    }

    @Generated
    public void setX(float f2) {
        this.m = f2;
    }

    public float k() {
        return this.o * this.q;
    }
}

