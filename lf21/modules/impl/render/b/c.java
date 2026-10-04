/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render.b;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.impl.render.NameTags;
import lf21.modules.impl.render.b.a;
import lf21.modules.impl.render.b.e;
import lf21.modules.impl.world.Teams;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.utils.b.i;
import lf21.utils.game.j;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.b;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class c
extends a {
    private final u_0 e;
    private static final String[] g;
    private D f;
    private final List<e> d;
    private final Map<Entity, i> c = new ConcurrentHashMap<Entity, i>();

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
                    case 0 -> 46;
                    case 1 -> 65;
                    case 2 -> 1;
                    case 3 -> 86;
                    case 4 -> 30;
                    case 5 -> 50;
                    default -> 46;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private int a(float f2) {
        if ((f2 = Math.max(0.0f, Math.min(1.0f, f2))) >= 0.6f) {
            float f3 = (f2 - 0.6f) / 0.4f;
            return new Color((int)(255.0f * (1.0f - f3)), 255, 0).getRGB();
        }
        if (f2 >= 0.3f) {
            float f4 = (f2 - 0.3f) / 0.3f;
            return new Color(255, (int)(255.0f * f4), 0).getRGB();
        }
        float f5 = f2 / 0.3f;
        return new Color((int)(128.0f + 127.0f * f5), 0, 0).getRGB();
    }

    @Override
    public void c() {
        this.c.clear();
        j.a();
    }

    public c() {
        super("Simple");
        this.d = new ArrayList<e>();
        this.e = t_0.b(16.0f);
    }

    @Override
    public void b() {
        this.c.clear();
        j.a();
    }

    @Override
    public void a(Render2DEvent render2DEvent) {
        if (this.c.isEmpty() || lf21.modules.impl.render.b.c.mc.level == null || this.e == null) {
            return;
        }
        this.d.clear();
        float f2 = ((Number)NameTags.INSTANCE.scale.getValue()).floatValue();
        float f3 = 6.0f;
        int n2 = 4;
        int n3 = 4;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Renderer.a((DrawContext drawContext) -> {
            for (Map.Entry<Entity, i> entry : this.c.entrySet()) {
                Entity entity = entry.getKey();
                i i2 = entry.getValue();
                if (!(entity instanceof Player)) continue;
                Player player = (Player)entity;
                i2.b(Math.round(i2.a), Math.round(i2.b));
                if (((Boolean)NameTags.INSTANCE.showArtifacts.getValue()).booleanValue()) {
                    ItemStack itemStack;
                    ItemStack itemStack2 = player.getMainHandItem();
                    if (j.a(itemStack2)) {
                        j.a((Object)player, itemStack2);
                    }
                    if (j.a(itemStack = player.getOffhandItem())) {
                        j.a((Object)player, itemStack);
                    }
                }
                float f4 = Math.min(player.getHealth(), 20.0f);
                float f5 = f4 / player.getMaxHealth();
                String[] stringArray = g;
                String string = player.getName().getString() + " | ";
                String string2 = "" + Math.round(f4);
                if (player.getAbsorptionAmount() > 0.0f) {
                    string2 = string2 + "+" + Math.round(player.getAbsorptionAmount());
                }
                string2 = string2 + "HP";
                Set<ItemStack> set = j.a(player);
                int n4 = (Boolean)NameTags.INSTANCE.showArtifacts.getValue() != false && !set.isEmpty() ? set.size() : 0;
                boolean bl = n4 > 0;
                String string3 = string + string2;
                float f6 = this.e.a(string3).a();
                float f7 = this.e.d().b();
                float f8 = f7 + (float)(n2 * 2);
                float f9 = f8 * (float)n4 + (float)(n4 > 0 ? n3 * n4 : 0);
                float f10 = f6 + (float)(n2 * 2) + f9;
                float f11 = f7 + (float)(n2 * 2);
                float f12 = -f10 / 2.0f;
                float f13 = -f11;
                drawContext.d();
                drawContext.a(i2.a, i2.b);
                drawContext.b(f2, f2);
                float f14 = i2.a + f12 * f2;
                float f15 = i2.b + f13 * f2;
                float f16 = f10 * f2;
                float f17 = f11 * f2;
                this.d.add(new e(f14, f15, f16, f17));
                if (this.f == null) {
                    this.f = new D();
                }
                this.f.a(new Color(0, 0, 0, 80).getRGB());
                drawContext.a(Q.a(f12, f13, f10, f11, f3), this.f);
                if (f5 > 0.0f) {
                    this.f.a(new Color(0, 0, 0, 80).getRGB());
                    drawContext.a(Q.a(f12, f13, f10 * f5, f11, f3), this.f);
                }
                int n5 = this.a(f5);
                float f18 = f13 + (float)n2 + this.e.d().c() + 35.0f;
                this.f.a(Color.WHITE.getRGB());
                drawContext.a(string, f12 + (float)n2, f18, this.e, this.f);
                float f19 = this.e.a(string).a();
                this.f.a(n5);
                drawContext.a(string2, f12 + (float)n2 + f19, f18, this.e, this.f);
                if (bl) {
                    float f20 = f12 + f6 + (float)(n2 * 2) + (float)n3;
                    for (ItemStack itemStack : set) {
                        this.f.a(new Color(0, 0, 0, 120).getRGB());
                        drawContext.a(Q.a(f20, f13, f8, f11, f3), this.f);
                        float f21 = i2.a + f20 * f2;
                        float f22 = i2.b + f13 * f2;
                        float f23 = f8 * f2;
                        float f24 = f21 + f23 / 2.0f;
                        float f25 = f22 + f23 / 2.0f;
                        concurrentHashMap.put(itemStack, new i(f24 - 8.0f, f25 - 8.0f));
                        f20 += f8 + (float)n3;
                    }
                }
                drawContext.e();
            }
        });
        for (e object : this.d) {
            RenderUtil.a(render2DEvent.b().pose(), object.a, object.b, object.c, object.d, 8.0f, 2.0f, 0.6f, 0);
        }
        for (Map.Entry entry : concurrentHashMap.entrySet()) {
            ItemStack itemStack = (ItemStack)entry.getKey();
            i i2 = (i)entry.getValue();
            render2DEvent.b().renderItem(itemStack, Math.round(i2.a), Math.round(i2.b));
            render2DEvent.b().renderItemDecorations(lf21.modules.impl.render.b.c.mc.font, itemStack, Math.round(i2.a), Math.round(i2.b));
        }
    }

    @Override
    public String d() {
        return "Simple";
    }

    @Override
    public void a(RenderEvent renderEvent) {
        try {
            this.b(renderEvent.b());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "m\bU{\u0003\u000e=!\u0002f\u0011";
                var4_3 = "m\bU{\u0003\u000e=!\u0002f\u0011".length();
                var1_4 = 4;
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
                    var5[var3_1++] = lf21.modules.impl.render.b.c.b(lf21.modules.impl.render.b.c.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "}(l&rW\u0006}(l&rW";
                    var4_3 = "}(l&rW\u0006}(l&rW".length();
                    var1_4 = 6;
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
                    var5[var3_1++] = lf21.modules.impl.render.b.c.b(lf21.modules.impl.render.b.c.b(v1));
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
        lf21.modules.impl.render.b.c.g = var5;
    }

    private void b(float f2) {
        this.c.clear();
        if (lf21.modules.impl.render.b.c.mc.level == null || lf21.modules.impl.render.b.c.mc.player == null) {
            j.a();
            return;
        }
        double d2 = Math.pow(((Number)NameTags.INSTANCE.maxDistance.getValue()).doubleValue(), 2.0);
        HashSet<Entity> hashSet = new HashSet<Entity>();
        for (Entity entity : lf21.modules.impl.render.b.c.mc.level.entitiesForRendering()) {
            double d3;
            double d4;
            double d5;
            i i2;
            if (entity == lf21.modules.impl.render.b.c.mc.player || !entity.isAlive() || entity.distanceToSqr((Entity)lf21.modules.impl.render.b.c.mc.player) > d2 || !NameTags.INSTANCE.handlesEntity(entity) || entity.getName().getString().startsWith("CIT-") || entity.isInvisible() && !((Boolean)NameTags.INSTANCE.invisibles.getValue()).booleanValue() || ((Boolean)NameTags.INSTANCE.hideTeammates.getValue()).booleanValue() && Teams.a(entity) || (i2 = b.a(d5 = lf21.utils.b.a.a(f2, entity.xo, entity.getX()), d4 = lf21.utils.b.a.a(f2, entity.yo, entity.getY()) + (double)entity.getBbHeight() + 0.5, d3 = lf21.utils.b.a.a(f2, entity.zo, entity.getZ()), f2)) == null) continue;
            i2.c(i2.d() - 2.0f);
            this.c.put(entity, i2);
            hashSet.add(entity);
        }
        j.a(hashSet);
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x2E);
        }
        return cArray;
    }

    @Override
    public void a(PacketEvent packetEvent) {
    }
}

