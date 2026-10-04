/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lf21.ClientBase;
import lf21.hud.C;
import lf21.hud.F;
import lf21.hud.b_0;
import lf21.hud.v;
import lf21.modules.impl.render.Projectiles;
import lf21.modules.impl.render.n;
import lf21.render.A;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.G;
import lf21.render.I;
import lf21.render.h_0;
import lf21.render.i_0;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.w_0;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.phys.Vec3;

/*
 * Renamed from lf21.hud.d
 */
public class d_0
extends ClientBase
implements v {
    private static final u_0 d;
    private static final String[] j;
    private final Map<Vec3, Long> g = new ConcurrentHashMap<Vec3, Long>();
    private Vec3 h = null;
    private static final u_0 c;
    private static final float f;
    private static final u_0 b;
    private static final G e;
    private long i = 0L;
    private static final u_0 a;

    private Optional<C> e() {
        if (mc == null || d_0.mc.player == null || Projectiles.x.isEmpty()) {
            return Optional.empty();
        }
        Optional<n> optional = Projectiles.x.entrySet().stream().filter(entry -> {
            if (d_0.mc.level == null) {
                return false;
            }
            Entity entity = d_0.mc.level.getEntity(((Integer)entry.getKey()).intValue());
            if (entity == null || !entity.isAlive() || entity.onGround()) {
                return false;
            }
            if (!(entity instanceof ThrownEnderpearl)) {
                return false;
            }
            Entity entity2 = ((ThrownEnderpearl)entity).getOwner();
            return entity2 != null && !entity2.equals((Object)d_0.mc.player);
        }).map(Map.Entry::getValue).min(Comparator.comparingDouble(n2 -> n2.f().distanceToSqr(d_0.mc.player.position())));
        return optional.map(n2 -> new C(n2.f(), n2.c(), Optional.of(Float.valueOf((float)n2.b())), "Find an ender pearl!", "\ue55e"));
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
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
                    case 0 -> 50;
                    case 1 -> 81;
                    case 2 -> 6;
                    case 3 -> 109;
                    case 4 -> 23;
                    case 5 -> 12;
                    default -> 13;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private Optional<C> f() {
        if (mc == null || d_0.mc.player == null || d_0.mc.level == null) {
            return Optional.empty();
        }
        long l2 = System.currentTimeMillis();
        StreamSupport.stream(d_0.mc.level.entitiesForRendering().spliterator(), false).filter(entity -> entity instanceof LightningBolt && entity.isAlive()).forEach(entity -> this.g.put(entity.position(), l2));
        this.g.entrySet().removeIf(entry -> l2 - (Long)entry.getValue() > 5000L);
        if (this.g.isEmpty()) {
            return Optional.empty();
        }
        return this.g.keySet().stream().filter(vec3 -> d_0.mc.player.position().distanceToSqr(vec3) < 65536.0).min(Comparator.comparingDouble(vec3 -> d_0.mc.player.position().distanceToSqr(vec3))).map(vec3 -> new C((Vec3)vec3, d_0.mc.player.position().distanceTo(vec3), Optional.empty(), "Found a lightning strike!", "\uea0b"));
    }

    @Override
    public boolean b() {
        return true;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xD);
        }
        return cArray;
    }

    private Optional<C> g() {
        return Stream.of(this.e(), this.f()).filter(Optional::isPresent).map(Optional::get).min(Comparator.comparingDouble(c2 -> c2.b));
    }

    @Override
    public b_0 a() {
        return b_0.CENTER;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block20: {
            block19: {
                block18: {
                    var5 = new String[4];
                    var3_1 = 0;
                    var2_2 = "\u007f0r\beel^\u0018e\u0002y\u007f `4a\u0018{m\u007f\u001c%r\u000b\u0019t>s\u0003s,l\u0012=o\n\u007fxc[?aMdx\u007f[:cL";
                    var4_3 = "\u007f0r\beel^\u0018e\u0002y\u007f `4a\u0018{m\u007f\u001c%r\u000b\u0019t>s\u0003s,l\u0012=o\n\u007fxc[?aMdx\u007f[:cL".length();
                    var1_4 = 25;
                    var0_5 = -1;
lbl7:
                    // 2 sources

                    while (true) {
                        v0 = ++var0_5;
                        v1 = var2_2.substring(v0, v0 + var1_4);
                        v2 = -1;
                        break block18;
                        break;
                    }
lbl12:
                    // 1 sources

                    while (true) {
                        var5[var3_1++] = d_0.b(d_0.a(v1));
                        if ((var0_5 += var1_4) < var4_3) {
                            var1_4 = var2_2.charAt(var0_5);
                            ** continue;
                        }
                        var2_2 = "\u007f0r\beel^\u0018e\u0002y\u007f `4a\u0018{m\u007f\u001c%r\u000b\u0014t8h\t7mc\u00124h\tr~-B4g\u001f{-";
                        var4_3 = "\u007f0r\beel^\u0018e\u0002y\u007f `4a\u0018{m\u007f\u001c%r\u000b\u0014t8h\t7mc\u00124h\tr~-B4g\u001f{-".length();
                        var1_4 = 25;
                        var0_5 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var0_5;
                            v1 = var2_2.substring(v3, v3 + var1_4);
                            v2 = 0;
                            break block18;
                            break;
                        }
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var5[var3_1++] = d_0.b(d_0.a(v1));
                        if ((var0_5 += var1_4) < var4_3) {
                            var1_4 = var2_2.charAt(var0_5);
                            ** continue;
                        }
                        break block19;
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
            d_0.j = var5;
            var0_6 = d_0.j;
            d_0.a = w_0.a("MaterialIcons-Regular.ttf", 48.0f);
            d_0.b = w_0.a("MaterialIcons-Regular.ttf", 44.0f);
            d_0.c = t_0.g(8.0f);
            d_0.d = t_0.f(6.0f);
            var6_7 = null;
            var7_8 = 0.0f;
            try {
                var8_9 = d_0.a.c("\uea0b");
                if (var8_9 == null || var8_9.length <= 0) break block20;
                var6_7 = d_0.a.a(var8_9[0]);
                if (var6_7 == null) break block20;
                var9_11 = new I(var6_7);
                try {
                    var7_8 = var9_11.a();
                }
                finally {
                    var9_11.close();
                }
            }
            catch (Exception var8_10) {
                var6_7 = null;
            }
        }
        d_0.e = var6_7;
        d_0.f = var7_8;
    }

    @Override
    public boolean c() {
        return this.g().isPresent();
    }

    @Override
    public void a(DrawContext drawContext, float f2, float f3, float f4, float f5, float f6) {
        if (mc == null || d_0.mc.player == null || f6 <= 0.01f) {
            return;
        }
        this.g().ifPresent(c2 -> {
            float f7 = 12.0f;
            float f8 = f2 + f7;
            float f9 = f3 + f5 / 2.0f;
            boolean bl = "\uea0b".equals(c2.g());
            if (bl) {
                if (this.h == null || !this.h.equals((Object)c2.a())) {
                    this.h = c2.a();
                    this.i = System.currentTimeMillis();
                }
            } else {
                this.h = null;
            }
            try (D d2 = new D();){
                float f10;
                d2.a(this.a(Color.WHITE.getRGB(), f6));
                float f11 = a.b(c2.g());
                float f12 = f9 - (a.d().c() + a.d().d()) / 2.0f - a.d().d();
                if (bl && e != null) {
                    long l2 = System.currentTimeMillis() - this.i;
                    float f13 = Mth.clamp((float)((float)l2 / 1000.0f), (float)0.0f, (float)1.0f);
                    d2.a(h_0.STROKE);
                    d2.b(1.5f);
                    d2.a(i_0.ROUND);
                    if (f13 < 1.0f) {
                        f10 = f;
                        float f14 = f13 * f10;
                        try (lf21.render.F f15 = A.a(new float[]{f14, f10}, 0.0f);){
                            d2.a(f15);
                        }
                    } else {
                        d2.a((lf21.render.F)null);
                    }
                    drawContext.d();
                    drawContext.a(f8, f12);
                    drawContext.a(e, d2);
                    drawContext.e();
                    d2.a(h_0.FILL);
                    d2.a((lf21.render.F)null);
                } else {
                    drawContext.a(c2.g(), f8, f12, a, d2);
                }
                float f16 = f8 + f11 + f7;
                d2.a(this.a(Color.WHITE.getRGB(), f6));
                float f17 = f9 - c.d().b() / 2.0f + 2.0f;
                drawContext.a(c2.f(), f16, f17, c, d2);
                String string = c2.b();
                d2.a(this.a(new Color(170, 170, 170).getRGB(), f6));
                f10 = f9 + d.d().b() / 2.0f + 6.0f;
                drawContext.a(string, f16, f10, d, d2);
                d2.a(this.a(Color.WHITE.getRGB(), f6));
                Vec3 vec3 = d_0.mc.player.getEyePosition();
                Vec3 vec32 = c2.a();
                double d3 = vec32.x - vec3.x;
                double d4 = vec32.z - vec3.z;
                if (Math.sqrt(d3 * d3 + d4 * d4) < 1.0) {
                    String string2 = vec32.y > vec3.y ? "\ue5d8" : "\ue5db";
                    float f18 = b.b(string2);
                    float f19 = f2 + f4 - f7 - f18;
                    float f20 = f9 - (b.d().c() + b.d().d()) / 2.0f - b.d().d();
                    drawContext.a(string2, f19, f20, b, d2);
                    return;
                }
                float f21 = b.b("\ue5d8");
                float f22 = f2 + f4 - f7 - f21;
                float f23 = f9;
                drawContext.d();
                drawContext.a(f22 + f21 / 2.0f, f23);
                float f24 = (float)(Mth.atan2((double)d4, (double)d3) * 57.29577951308232) - 90.0f;
                float f25 = Mth.wrapDegrees((float)d_0.mc.player.getYRot());
                float f26 = Mth.wrapDegrees((float)(f24 - f25));
                drawContext.a(f26);
                float f27 = b.d().c();
                float f28 = b.d().d();
                float f29 = -(f27 + f28) / 2.0f;
                drawContext.a("\ue5d8", -f21 / 2.0f, f29, b, d2);
                drawContext.e();
                return;
            }
        });
    }

    @Override
    public void a(GuiGraphics guiGraphics, PoseStack poseStack, float f2, float f3, float f4, float f5, float f6) {
    }

    @Override
    public F d() {
        return new F(200.0f, 40.0f);
    }
}

