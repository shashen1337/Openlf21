/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import it.unimi.dsi.fastutil.chars.Char2IntArrayMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.awt.Color;
import java.awt.Font;
import java.io.Closeable;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lf21.ClientBase;
import lf21.render.V;
import lf21.render.W;
import lf21.render.c_0;
import lf21.render.d_0;
import lf21.render.z_0;
import lf21.utils.b.a;
import lombok.Generated;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

/*
 * Renamed from lf21.render.q
 */
public class q_0
implements Closeable {
    private static final Color o;
    private d_0 q;
    private boolean n;
    private static final ExecutorService b;
    private float p = 0.0f;
    private final Object2ObjectMap<ResourceLocation, ObjectList<V>> c = new Object2ObjectOpenHashMap();
    private static final Char2IntArrayMap a;
    private Future<Void> m;
    private final int h;
    private final int g;
    private final ObjectList<z_0> e = new ObjectArrayList();
    private int j = 0;
    private int l = -1;
    private final Char2ObjectArrayMap<W> f = new Char2ObjectArrayMap();
    private final String i;
    private final float d;
    private Font k;

    public d_0 d() {
        return this.q;
    }

    private Future<Void> b() {
        return b.submit(() -> {
            for (char c2 : this.i.toCharArray()) {
                if (Thread.interrupted()) {
                    break;
                }
                this.b(c2);
            }
            return null;
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void close() {
        try {
            if (this.m != null && !this.m.isDone() && !this.m.isCancelled()) {
                this.m.cancel(true);
                this.m.get();
                this.m = null;
            }
            ObjectListIterator objectListIterator = this.e.iterator();
            while (true) {
                if (!objectListIterator.hasNext()) {
                    this.e.clear();
                    this.f.clear();
                    this.n = false;
                    return;
                }
                z_0 z_02 = (z_0)objectListIterator.next();
                z_02.a();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public q_0(Font font, float f2) {
        this(font, f2, 256, 5, null);
    }

    public int e() {
        return this.j;
    }

    public float c(String string) {
        char[] cArray = q_0.a(string).toCharArray();
        if (cArray.length == 0) {
            cArray = new char[]{' '};
        }
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (char c2 : cArray) {
            block10: {
                block11: {
                    float f4;
                    block13: {
                        block12: {
                            if (c2 != '\n') break block10;
                            if (f2 != 0.0f) break block11;
                            if (this.b(' ') != null) break block12;
                            f4 = 0.0f;
                            break block13;
                        }
                        f4 = (float)Objects.requireNonNull(this.b(' ')).d() / (float)this.j;
                    }
                    f2 = f4;
                }
                f3 += f2;
                f2 = 0.0f;
                continue;
            }
            W w2 = this.b(c2);
            float f5 = w2 == null ? 0.0f : (float)w2.d() / (float)this.j;
            f2 = Math.max(f5, f2);
        }
        return f2 + f3;
    }

    public void b(PoseStack poseStack, String string, float f2, float f3, int n2) {
        this.a(poseStack, string, f2 - this.b(string) / 2.0f, f3, n2);
    }

    public void d(PoseStack poseStack, String string, double d2, double d3, int n2) {
        float f2 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n2 & 0xFF) / 255.0f;
        float f5 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        this.a(poseStack, string, (float)(d2 - (double)(this.b(string) / 2.0f)), (float)d3, f2, f3, f4, f5);
    }

    private void a(Font font, float f2) {
        block7: {
            if (this.n) {
                throw new IllegalStateException("Double call to init()");
            }
            this.n = true;
            this.l = (int)ClientBase.mc.getWindow().getGuiScale();
            this.j = Math.max(2, this.l * 2);
            this.k = font.deriveFont(f2 * (float)this.j);
            this.q = new d_0(this.k);
            if (this.i == null || this.i.isEmpty()) break block7;
            this.m = this.b();
        }
    }

    @Generated
    public void a(float f2) {
        this.p = f2;
    }

    private static int a(int n2, int n3) {
        return n3 * (int)Math.floor((double)n2 / (double)n3);
    }

    public void c(PoseStack poseStack, String string, double d2, double d3, int n2) {
        this.a(poseStack, string, (double)((float)d2) + 0.5, (double)((float)d3) + 0.5, o);
        this.a(poseStack, string, (double)((float)d2), (double)((float)d3), n2);
    }

    public void a(PoseStack poseStack, String string, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(poseStack, string, f2, f3, f4, f5, f6, f7, false, 0);
    }

    public static int @NotNull [] a(int n2) {
        int n3 = n2 >> 16 & 0xFF;
        int n4 = n2 >> 8 & 0xFF;
        int n5 = n2 & 0xFF;
        return new int[]{n3, n4, n5};
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(PoseStack poseStack, String string, float f2, float f3, float f4, float f5, float f6, float f7, boolean bl, int n2) {
        block21: {
            if (this.m != null) {
                if (!this.m.isDone()) {
                }
                break block21;
                this.m.get();
                catch (IllegalStateException illegalStateException) {
                    throw illegalStateException;
                }
                catch (InterruptedException | ExecutionException exception) {
                    // empty catch block
                }
            }
        }
        this.a();
        float f8 = f4;
        float f9 = f5;
        float f10 = f6;
        poseStack.pushPose();
        poseStack.translate(lf21.utils.b.a.a((double)f2, 1), lf21.utils.b.a.a((double)(f3 -= 1.0f), 1), 0.0);
        poseStack.scale(1.0f / (float)this.j, 1.0f / (float)this.j, 1.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        Matrix4f matrix4f = poseStack.last().pose();
        char[] cArray = string.toCharArray();
        float f11 = 0.0f;
        float f12 = 0.0f;
        boolean bl2 = false;
        int n3 = 0;
        Object2ObjectMap<ResourceLocation, ObjectList<V>> object2ObjectMap = this.c;
        synchronized (object2ObjectMap) {
            Object object2;
            for (int i2 = 0; i2 < cArray.length; ++i2) {
                char c2 = cArray[i2];
                if (bl2) {
                    bl2 = false;
                    char c3 = Character.toUpperCase(c2);
                    if (a.containsKey(c3)) {
                        int n4 = a.get(c3);
                        object2 = q_0.a(n4);
                        f8 = (float)object2[0] / 255.0f;
                        f9 = (float)object2[1] / 255.0f;
                        f10 = (float)object2[2] / 255.0f;
                        continue;
                    }
                    if (c3 != 'R') continue;
                    f8 = f4;
                    f9 = f5;
                    f10 = f6;
                    continue;
                }
                if (c2 == '\u00a7') {
                    bl2 = true;
                    continue;
                }
                if (c2 == '\n') {
                    f12 += this.c(string.substring(n3, i2)) * (float)this.j;
                    f11 = 0.0f;
                    n3 = i2 + 1;
                    continue;
                }
                W w2 = this.b(c2);
                if (w2 == null) continue;
                if (w2.e() != ' ') {
                    ResourceLocation resourceLocation = w2.f().d;
                    object2 = new V(f11, f12, f8, f9, f10, w2);
                    ((ObjectList)this.c.computeIfAbsent((Object)resourceLocation, object -> new ObjectArrayList())).add(object2);
                }
                f11 += (float)w2.c() + this.p;
            }
            ObjectIterator objectIterator = this.c.keySet().iterator();
            while (true) {
                if (!objectIterator.hasNext()) {
                    this.c.clear();
                    // MONITOREXIT @DISABLED, blocks:[6, 11, 13] lbl74 : MonitorExitStatement: MONITOREXIT : var20_21
                    poseStack.popPose();
                    RenderSystem.disableBlend();
                    return;
                }
                ResourceLocation resourceLocation = (ResourceLocation)objectIterator.next();
                RenderSystem.setShaderTexture((int)0, (ResourceLocation)resourceLocation);
                List list = (List)this.c.get((Object)resourceLocation);
                Tesselator tesselator = Tesselator.getInstance();
                object2 = tesselator.getBuilder();
                object2.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
                for (V v2 : list) {
                    float f13 = v2.a;
                    float f14 = v2.b;
                    float f15 = v2.c;
                    float f16 = v2.d;
                    float f17 = v2.e;
                    W w3 = v2.f;
                    z_0 z_02 = w3.f();
                    float f18 = w3.c();
                    float f19 = w3.d();
                    float f20 = (float)w3.a() / (float)z_02.g;
                    float f21 = (float)w3.b() / (float)z_02.h;
                    float f22 = (float)(w3.a() + w3.c()) / (float)z_02.g;
                    float f23 = (float)(w3.b() + w3.d()) / (float)z_02.h;
                    object2.vertex(matrix4f, f13 + 0.0f, f14 + f19, 0.0f).uv(f20, f23).color(f15, f16, f17, f7).endVertex();
                    object2.vertex(matrix4f, f13 + f18, f14 + f19, 0.0f).uv(f22, f23).color(f15, f16, f17, f7).endVertex();
                    object2.vertex(matrix4f, f13 + f18, f14 + 0.0f, 0.0f).uv(f22, f21).color(f15, f16, f17, f7).endVertex();
                    object2.vertex(matrix4f, f13 + 0.0f, f14 + 0.0f, 0.0f).uv(f20, f21).color(f15, f16, f17, f7).endVertex();
                }
                tesselator.end();
            }
        }
    }

    public void a(PoseStack poseStack, String string, double d2, double d3, int n2) {
        float f2 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n2 & 0xFF) / 255.0f;
        float f5 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        this.a(poseStack, string, (float)d2, (float)d3, f2, f3, f4, f5);
    }

    public q_0(Font font, float f2, int n2, int n3, @Nullable String string) {
        this.d = f2;
        this.g = n2;
        this.h = n3;
        this.i = string;
        this.q = new d_0(font);
        this.a(font, f2);
    }

    public void a(PoseStack poseStack, String string, float f2, float f3, int n2) {
        this.a(poseStack, string, f2, f3, 255.0f, 255.0f, 255.0f, 255.0f, true, n2);
    }

    public static String a(String string) {
        char[] cArray = string.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < cArray.length; ++i2) {
            char c2 = cArray[i2];
            if (c2 == '\u00a7') {
                ++i2;
                continue;
            }
            stringBuilder.append(c2);
        }
        return stringBuilder.toString();
    }

    public void h() {
        this.p = 0.0f;
    }

    public void b(PoseStack poseStack, String string, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(poseStack, string, f2 - this.b(string) / 2.0f, f3, f4, f5, f6, f7);
    }

    static {
        a = new c_0();
        b = Executors.newCachedThreadPool();
        o = new Color(26, 26, 26, 160);
    }

    private static String g() {
        return IntStream.range(0, 32).mapToObj(n2 -> String.valueOf((char)new Random().nextInt(97, 123))).collect(Collectors.joining());
    }

    private W a(char c2) {
        z_0 z_022;
        for (z_0 z_022 : this.e) {
            if (!z_022.b(c2)) {
                continue;
            }
            return z_022.a(c2);
        }
        int n2 = q_0.a(c2, this.g);
        z_022 = this.a((char)n2, (char)(n2 + this.g));
        return z_022.a(c2);
    }

    private z_0 a(char c2, char c3) {
        z_0 z_02 = new z_0(c2, c3, this.k, q_0.f(), this.h);
        this.e.add((Object)z_02);
        return z_02;
    }

    public void a(PoseStack poseStack, String string, double d2, double d3, Color color) {
        this.a(poseStack, string, (float)d2, (float)d3, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, color.getAlpha());
    }

    public float c() {
        return (float)(this.q.getLeading() + this.q.getAscent() + this.q.getDescent()) / (float)this.j;
    }

    public void b(PoseStack poseStack, String string, double d2, double d3, int n2) {
        float f2 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n2 & 0xFF) / 255.0f;
        float f5 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        this.a(poseStack, string, (float)d2, (float)d3, f2, f3, f4, f5);
    }

    public float d(String string) {
        return this.c(string);
    }

    @Contract(value="-> new", pure=true)
    @NotNull
    public static ResourceLocation f() {
        return ResourceLocation.tryParse((String)("lf21:temp/" + q_0.g()));
    }

    public float b(String string) {
        char[] cArray = q_0.a(string).toCharArray();
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (char c2 : cArray) {
            if (c2 == '\n') {
                f3 = Math.max(f2, f3);
                f2 = 0.0f;
                continue;
            }
            W w2 = this.b(c2);
            float f4 = f2;
            float f5 = w2 == null ? 0.0f : (float)w2.c() / (float)this.j;
            f2 = f4 + (f5 + this.p);
        }
        return Math.max(f2, f3);
    }

    private void a() {
        int n2 = (int)ClientBase.mc.getWindow().getGuiScale();
        if (n2 != this.l) {
            this.close();
            this.a(this.k, this.d);
        }
    }

    public void b(PoseStack poseStack, String string, double d2, double d3, Color color) {
        this.a(poseStack, string, (float)(d2 - (double)(this.b(string) / 2.0f)), (float)d3, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
    }

    @Nullable
    private W b(char c2) {
        return (W)this.f.computeIfAbsent(c2, this::a);
    }
}

