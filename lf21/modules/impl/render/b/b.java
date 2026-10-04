/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render.b;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.impl.render.NameTags;
import lf21.modules.impl.render.b.a;
import lf21.modules.impl.render.b.d;
import lf21.modules.impl.world.Teams;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.w_0;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.b.i;
import lf21.utils.game.j;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class b
extends a {
    private static final int g;
    private static final String[] w;
    private static final int h;
    private final NumberSetting l;
    private final BooleanSetting n;
    private final Map<String, String> u;
    private final Map<Entity, i> s;
    private static final int c;
    private final NumberSetting k;
    private static final int d;
    private static final int e;
    private static final int f;
    private final D r;
    private final u_0 p;
    public static final Map<String, AtomicInteger> j;
    private final u_0 o;
    private static final int i;
    private final Map<UUID, Long> t;
    private final u_0 q;
    private final Map<String, Long> v;
    private final BooleanSetting m;

    @Override
    public void a(RenderEvent renderEvent) {
        try {
            this.b(renderEvent);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public b() {
        String[] stringArray = w;
        super("Opal");
        this.k = NameTags.INSTANCE.scale;
        this.l = NameTags.INSTANCE.maxDistance;
        this.m = NameTags.INSTANCE.invisibles;
        this.n = NameTags.INSTANCE.showArtifacts;
        this.o = t_0.b(28.0f);
        this.p = w_0.a("AstaSans-Medium.ttf", 28.0f);
        this.q = w_0.a("MaterialIcons-Regular.ttf", 28.0f);
        this.r = new D();
        this.s = new ConcurrentHashMap<Entity, i>();
        this.t = new HashMap<UUID, Long>();
        this.u = new HashMap<String, String>();
        this.v = new HashMap<String, Long>();
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
                    case 0 -> 59;
                    case 1 -> 18;
                    case 2 -> 107;
                    case 3 -> 47;
                    case 4 -> 63;
                    case 5 -> 96;
                    default -> 93;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private String b(String string) {
        long l2 = System.currentTimeMillis();
        Long l3 = this.v.get(string);
        if (l3 != null && l2 - l3 < 1000L) {
            return this.u.get(string);
        }
        this.u.put(string, string);
        this.v.put(string, l2);
        return string;
    }

    @Override
    public String d() {
        return "Opal";
    }

    private static char[] d(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x5D);
        }
        return cArray;
    }

    @Override
    public void a(Render2DEvent render2DEvent) {
        if (this.s.isEmpty() || b.mc.level == null) {
            return;
        }
        float f2 = ((Number)this.k.getValue()).floatValue();
        int n2 = 4;
        int n3 = 4;
        float f3 = 6.0f;
        ArrayList arrayList = new ArrayList();
        Renderer.a((DrawContext drawContext) -> {
            float f4 = this.o.d().c();
            float f5 = this.o.d().b();
            float f6 = this.p.d().b();
            for (Map.Entry<Entity, i> entry : this.s.entrySet()) {
                float f7;
                Object object;
                Object object2;
                Entity entity = entry.getKey();
                i i2 = entry.getValue();
                i2.b(Math.round(i2.a), Math.round(i2.b));
                if (!(entity instanceof AbstractClientPlayer)) continue;
                AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)entity;
                if (((Boolean)this.n.getValue()).booleanValue()) {
                    long l2 = System.currentTimeMillis();
                    UUID uUID = abstractClientPlayer.getUUID();
                    Long l3 = this.t.get(uUID);
                    if (l3 == null || l2 - l3 >= 250L) {
                        lf21.utils.game.j.a((Player)abstractClientPlayer, abstractClientPlayer.getMainHandItem());
                        object2 = abstractClientPlayer.getMainHandItem();
                        if (lf21.utils.game.j.a(object2)) {
                            lf21.utils.game.j.a(abstractClientPlayer, object2);
                        }
                        if (lf21.utils.game.j.a(object = abstractClientPlayer.getOffhandItem())) {
                            lf21.utils.game.j.a(abstractClientPlayer, object);
                        }
                        this.t.put(uUID, l2);
                    }
                }
                Set<ItemStack> set = lf21.utils.game.j.a(abstractClientPlayer);
                int n4 = (Boolean)this.n.getValue() != false && !set.isEmpty() ? set.size() : 0;
                boolean bl = n4 > 0;
                int n5 = (int)b.mc.player.distanceTo(entity);
                object2 = "\ue55c";
                object = n5 + "m";
                String string = "\ue7ff";
                String string2 = entity.getDisplayName().getString();
                String string3 = "";
                int n6 = Math.round(abstractClientPlayer.getHealth());
                String string4 = "\ue87d";
                String string5 = String.valueOf(n6);
                int n7 = Math.round(abstractClientPlayer.getAbsorptionAmount());
                String string6 = n7 > 0 ? String.valueOf(n7) : "";
                float f8 = this.q.a((String)object2).a();
                float f9 = this.p.a((String)object).a();
                float f10 = f8 + 2.0f + f9 + (float)(n2 * 2);
                float f11 = this.q.a(string).a();
                float f12 = this.o.a(string2).a();
                float f13 = string3.isEmpty() ? 0.0f : this.o.a(string3).a();
                float f14 = f11 + 6.0f + f12 + f13 + (float)(n2 * 2);
                float f15 = this.q.a(string4).a();
                float f16 = this.p.a(string5).a();
                float f17 = f15 + 2.0f + f16 + (float)(n2 * 2);
                float f18 = this.q.a(string4).a();
                float f19 = string6.isEmpty() ? 0.0f : this.p.a(string6).a();
                float f20 = string6.isEmpty() ? 0.0f : f18 + 2.0f + f19 + (float)(n2 * 2);
                float f21 = f7 = Math.max(f5, f6) + (float)(n2 * 2) - 4.0f;
                float f22 = f21 * (float)n4 + (float)(n4 > 0 ? n3 * (n4 - 1) : 0);
                int n8 = 2;
                if (!string5.isEmpty()) {
                    ++n8;
                }
                if (!string6.isEmpty()) {
                    ++n8;
                }
                float f23 = f10 + f14 + f17 + f20 + f22 + (float)(n3 * ((n8 += n4) > 1 ? n8 - 1 : 0));
                float f24 = -f23 / 2.0f;
                float f25 = -f7;
                drawContext.d();
                drawContext.a(i2.a, i2.b);
                drawContext.b(f2, f2);
                float f26 = f24;
                float f27 = f25 + (float)n2 - 1.0f + f4 + 28.0f;
                this.r.a(c);
                drawContext.a(Q.a(f26, f25, f10, f7, f3), this.r);
                this.r.a(d);
                drawContext.a((String)object2, f26 + (float)n2, f27 + 1.0f, this.q, this.r);
                drawContext.a((String)object, f26 + (float)n2 + f8 + 2.0f, f27 - 1.0f, this.p, this.r);
                this.r.a(c);
                drawContext.a(Q.a(f26 += f10 + (float)n3, f25, f14 + 2.0f, f7, f3), this.r);
                this.r.a(e);
                drawContext.a(string, f26 + (float)n2, f27, this.q, this.r);
                drawContext.a(string2, f26 + (float)n2 + f11 + 6.0f, f27 - 3.0f, this.o, this.r);
                float f28 = f26 + (float)n2 + f11 + 6.0f + f12;
                if (!string3.isEmpty()) {
                    this.r.a(h);
                    drawContext.a(string3, f28, f27 - 1.0f, this.o, this.r);
                    f28 += f13;
                }
                this.r.a(c);
                drawContext.a(Q.a(f26 += f14 + (float)n3, f25, f17, f7, f3), this.r);
                this.r.a(f);
                drawContext.a(string4, f26 + (float)n2, f27 + 1.0f, this.q, this.r);
                this.r.a(e);
                drawContext.a(string5, f26 + (float)n2 + f15 + 2.5f, f27 - 1.0f, this.p, this.r);
                f26 += f17;
                if (!string6.isEmpty()) {
                    this.r.a(c);
                    drawContext.a(Q.a(f26 += (float)n3, f25, f20, f7, f3), this.r);
                    this.r.a(g);
                    drawContext.a(string4, f26 + (float)n2, f27 + 1.0f, this.q, this.r);
                    this.r.a(e);
                    drawContext.a(string6, f26 + (float)n2 + f18 + 2.5f, f27, this.p, this.r);
                    f26 += f20;
                }
                if (bl) {
                    for (ItemStack itemStack : set) {
                        if (lf21.utils.game.j.a(abstractClientPlayer.getUUID(), itemStack.getItem())) continue;
                        this.r.a(c);
                        drawContext.a(Q.a(f26 += (float)n3, f25, f21, f7, f3), this.r);
                        float f29 = i2.a + f26 * f2;
                        float f30 = i2.b + f25 * f2;
                        float f31 = f7 * f2;
                        float f32 = f29 + f31 / 2.0f;
                        float f33 = f30 + f31 / 2.0f;
                        arrayList.add(new d(itemStack, new i(f32 - 5.0f, f33 - 5.0f)));
                        f26 += f21;
                    }
                }
                drawContext.e();
            }
        });
        for (d d2 : arrayList) {
            PoseStack poseStack = render2DEvent.b().pose();
            poseStack.pushPose();
            poseStack.translate(d2.b.a, d2.b.b, 0.0f);
            poseStack.scale(f2, f2, 1.0f);
            render2DEvent.b().renderItem(d2.a, 0, 0);
            render2DEvent.b().renderItemDecorations(b.mc.font, d2.a, 0, 0);
            poseStack.popPose();
        }
    }

    private void b(RenderEvent renderEvent) {
        if (b.mc.level == null || b.mc.player == null) {
            this.s.clear();
            lf21.utils.game.j.a();
            return;
        }
        lf21.utils.render.b.a();
        double d2 = Math.pow(((Number)this.l.getValue()).doubleValue(), 2.0);
        float f2 = renderEvent.b();
        HashSet<Entity> hashSet = new HashSet<Entity>();
        for (Entity entity2 : b.mc.level.entitiesForRendering()) {
            double d3;
            double d4;
            double d5;
            i i2;
            if (entity2 == b.mc.player || !entity2.isAlive() || entity2.isInvisible() && !((Boolean)this.m.getValue()).booleanValue() || entity2.distanceToSqr((Entity)b.mc.player) > d2 || !NameTags.INSTANCE.handlesEntity(entity2) || entity2.getName().getString().startsWith("CIT-") || ((Boolean)NameTags.INSTANCE.hideTeammates.getValue()).booleanValue() && Teams.a(entity2) || (i2 = lf21.utils.render.b.a(d5 = lf21.utils.b.a.a(f2, entity2.xo, entity2.getX()), d4 = lf21.utils.b.a.a(f2, entity2.yo, entity2.getY()) + (double)entity2.getBbHeight() + 0.5, d3 = lf21.utils.b.a.a(f2, entity2.zo, entity2.getZ()))) == null) continue;
            i2.c(i2.d() - 2.0f);
            this.s.put(entity2, i2);
            hashSet.add(entity2);
        }
        this.s.keySet().removeIf(entity -> !hashSet.contains(entity));
        lf21.utils.game.j.a(hashSet);
    }

    @Override
    public void a(PacketEvent packetEvent) {
        Object object = packetEvent.c();
        if (!(object instanceof ClientboundSetScorePacket)) {
            return;
        }
        ClientboundSetScorePacket clientboundSetScorePacket = (ClientboundSetScorePacket)object;
        if (b.mc.level == null || b.mc.player == null) {
            return;
        }
        object = clientboundSetScorePacket.getObjectiveName();
        String[] stringArray = w;
        if (!"belowHealth".equals(object) && !"health".equals(object)) {
            return;
        }
        if (clientboundSetScorePacket.getOwner().equals(b.mc.player.getGameProfile().getName())) {
            return;
        }
        j.computeIfAbsent(clientboundSetScorePacket.getOwner(), string -> new AtomicInteger()).set(clientboundSetScorePacket.getScore());
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "tb\nC\u0004x[?\u0002\u0004tb\nC\u0019vs\u001fJM\t<W[\b@Q\u0013piw\fZS\u0001/\u0015f\u001fI\u0013za\u001fNl\u00013H?&J[\t(V<\u001f[Y";
                var4_3 = "tb\nC\u0004x[?\u0002\u0004tb\nC\u0019vs\u001fJM\t<W[\b@Q\u0013piw\fZS\u0001/\u0015f\u001fI\u0013za\u001fNl\u00013H?&J[\t(V<\u001f[Y".length();
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
                    var5[var3_1++] = b.b(b.d(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "Yw\u0007@H(8Z~\u001fG\u0006Sw\nCK\b";
                    var4_3 = "Yw\u0007@H(8Z~\u001fG\u0006Sw\nCK\b".length();
                    var1_4 = 11;
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
                    var5[var3_1++] = b.b(b.d(v1));
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
        b.w = var5;
        b.c = new Color(0, 0, 0, 120).getRGB();
        b.d = Color.LIGHT_GRAY.getRGB();
        b.e = Color.WHITE.getRGB();
        b.f = new Color(255, 85, 85).getRGB();
        b.g = new Color(255, 215, 0).getRGB();
        b.h = new Color(85, 255, 85).getRGB();
        b.i = new Color(85, 255, 255).getRGB();
        b.j = new ConcurrentHashMap<String, AtomicInteger>();
    }

    @Override
    public void b() {
        this.s.clear();
        lf21.utils.game.j.a();
    }

    @Override
    public void c() {
        this.b();
    }
}

