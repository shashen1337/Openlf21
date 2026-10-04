/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lf21.event.EventTarget;
import lf21.event.impl.AttackEntityEvent;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.gui.k;
import lf21.hud.G;
import lf21.hud.HudElement;
import lf21.hud.a.a;
import lf21.hud.a.b;
import lf21.hud.a.c;
import lf21.hud.a.d;
import lf21.hud.a.e;
import lf21.hud.a.f;
import lf21.modules.impl.combat.KillAura;
import lf21.render.Renderer;
import lf21.settings.impl.ModeSetting;
import lf21.utils.b.i;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class p
extends HudElement {
    private final lf21.utils.a.e I;
    private final ModeSetting Q;
    private int W;
    private static final long P;
    private boolean Y;
    private LivingEntity aa;
    private final lf21.utils.a.e X;
    private float ab;
    private float M;
    private long O;
    private float ac;
    private final ModeSetting R;
    private final lf21.utils.a.e J;
    private boolean V;
    private G S;
    private final lf21.utils.a.e T;
    private static final String[] db;
    private LivingEntity N;
    private boolean U;
    private float L;
    private boolean Z;
    public static final Map<String, AtomicInteger> K;

    @Override
    public void a() {
    }

    private LivingEntity a(boolean bl) {
        LivingEntity livingEntity;
        if (bl && (lf21.hud.p.mc.screen instanceof k || lf21.hud.p.mc.screen instanceof ChatScreen)) {
            return lf21.hud.p.mc.player;
        }
        Entity entity = KillAura.l;
        if (entity instanceof LivingEntity && (livingEntity = (LivingEntity)entity).isAlive() && !livingEntity.isRemoved()) {
            return livingEntity;
        }
        if (this.N != null && System.currentTimeMillis() <= this.O && this.N.isAlive() && !this.N.isRemoved()) {
            return this.N;
        }
        this.N = null;
        this.O = 0L;
        return null;
    }

    private static char[] d(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x1B);
        }
        return cArray;
    }

    public p() {
        String[] stringArray = db;
        super("TargetHUD");
        this.I = new lf21.utils.a.e();
        this.J = new lf21.utils.a.e();
        this.Q = new ModeSetting("Mode", "Round", "Rise", "Moon", "Simple", "Naven").withDefault("Round");
        this.R = new ModeSetting("Position", "HUD", "Target").withDefault("HUD");
        this.T = new lf21.utils.a.e();
        this.W = Integer.MIN_VALUE;
        this.X = new lf21.utils.a.e();
        this.e(200.0f);
        this.f(60.0f);
        a.b();
        this.setEnabled(true);
    }

    private void a(LivingEntity livingEntity) {
        boolean bl;
        boolean bl2 = bl = livingEntity != null;
        if (!this.Y) {
            float f2 = bl ? 1.0f : 0.0f;
            this.X.a(f2);
            this.X.c(f2);
            this.X.d(f2);
            this.X.b(1.0);
            this.X.a(0L);
            this.X.a(lf21.utils.b.f.f);
            this.Y = true;
            this.Z = bl;
            if (bl) {
                this.aa = livingEntity;
            }
        } else if (bl != this.Z) {
            this.X.a();
            this.Z = bl;
            this.X.a(bl ? 1.0 : 0.0, bl ? 0.18 : 0.24, bl ? lf21.utils.b.f.f : lf21.utils.b.f.d);
        }
        if (bl) {
            this.aa = livingEntity;
        }
        this.X.a();
    }

    private void a(LivingEntity livingEntity, boolean bl) {
        int n2 = livingEntity.getId();
        if (!this.U || this.W != n2) {
            float f2 = bl ? 1.0f : 0.0f;
            this.T.a(f2);
            this.T.c(f2);
            this.T.d(f2);
            this.T.b(1.0);
            this.T.a(0L);
            this.T.a(lf21.utils.b.f.f);
            this.U = true;
            this.W = n2;
            this.V = bl;
            return;
        }
        this.T.a();
        if (bl != this.V) {
            this.V = bl;
            this.T.a(bl ? 1.0 : 0.0, 0.28, lf21.utils.b.f.f);
        }
    }

    @EventTarget
    public void a(AttackEntityEvent attackEntityEvent) {
        LivingEntity livingEntity;
        Entity entity = attackEntityEvent.a();
        if (entity instanceof LivingEntity && (livingEntity = (LivingEntity)entity).isAlive()) {
            this.N = livingEntity;
            this.O = System.currentTimeMillis() + 3000L;
        }
    }

    private void a(a a2) {
        if (a2 instanceof e || a2 instanceof f) {
            this.e(120.0f);
            this.f(38.0f);
        } else if (a2 instanceof b) {
            this.e(200.0f);
            this.f(40.0f);
        } else if (a2 instanceof d) {
            this.e(220.0f);
            this.f(45.0f);
        } else if (a2 instanceof c) {
            this.e(160.0f);
            this.f(48.0f);
        } else {
            this.e(150.0f);
            this.f(36.0f);
        }
    }

    private void y() {
        String[] stringArray = db;
        if (!this.R.is("HUD") && !this.R.is("Target")) {
            this.R.setValue("Target");
        }
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                lf21.hud.p.P = 3000L;
                var5 = new String[15];
                var3_1 = 0;
                var2_2 = "ULN\u0012y\u0004~VEV\u0015\u0006_LC\u0011z$\u0006cHP\u001ak8\bgFQ\u0014z%tY\u0004zFM\u0013\u0003\u007f|f\u0006cHP\u001ak8\u0005eFW\u0013j\u0003\u007f|f\u0004e@Q\u0018\u0006d@O\rb)\u0004zFF\u0018\u0005eFW\u0013j";
                var4_3 = "ULN\u0012y\u0004~VEV\u0015\u0006_LC\u0011z$\u0006cHP\u001ak8\bgFQ\u0014z%tY\u0004zFM\u0013\u0003\u007f|f\u0006cHP\u001ak8\u0005eFW\u0013j\u0003\u007f|f\u0004e@Q\u0018\u0006d@O\rb)\u0004zFF\u0018\u0005eFW\u0013j".length();
                var1_4 = 11;
                var0_5 = -1;
lbl8:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl13:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.hud.p.d(lf21.hud.p.d(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "cHP\u001ak8Sbm\u0005yHT\u0018`";
                    var4_3 = "cHP\u001ak8Sbm\u0005yHT\u0018`".length();
                    var1_4 = 9;
                    var0_5 = -1;
lbl22:
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
lbl27:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.hud.p.d(lf21.hud.p.d(v1));
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
lbl38:
                // 1 sources

                ** continue;
            }
        }
        lf21.hud.p.db = var5;
        lf21.hud.p.K = new HashMap<String, AtomicInteger>();
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String d(char[] cArray) {
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
                    case 0 -> 55;
                    case 1 -> 41;
                    case 2 -> 34;
                    case 3 -> 125;
                    case 4 -> 14;
                    case 5 -> 76;
                    default -> 27;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        Packet<?> packet = packetEvent.c();
        if (packet instanceof ClientboundSetScorePacket) {
            ClientboundSetScorePacket clientboundSetScorePacket = (ClientboundSetScorePacket)packet;
            if (lf21.hud.p.mc.level != null && lf21.hud.p.mc.player != null) {
                String[] stringArray = db;
                if (("belowHealth".equals(clientboundSetScorePacket.getObjectiveName()) || "health".equals(clientboundSetScorePacket.getObjectiveName())) && !clientboundSetScorePacket.getOwner().equals(lf21.hud.p.mc.player.getGameProfile().getName())) {
                    K.computeIfAbsent(clientboundSetScorePacket.getOwner(), string -> new AtomicInteger()).set(clientboundSetScorePacket.getScore());
                }
            }
        }
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        float f4;
        LivingEntity livingEntity;
        if (lf21.hud.p.mc.level == null || lf21.hud.p.mc.player == null) {
            return;
        }
        for (AbstractClientPlayer abstractClientPlayer : lf21.hud.p.mc.level.players()) {
            if (abstractClientPlayer == lf21.hud.p.mc.player || !K.containsKey(abstractClientPlayer.getName().getString())) continue;
            abstractClientPlayer.setHealth((float)Math.max(1, K.get(abstractClientPlayer.getName().getString()).get()));
        }
        LivingEntity livingEntity2 = this.a(true);
        this.a(livingEntity2);
        float f5 = Mth.clamp((float)this.X.e(), (float)0.0f, (float)1.0f);
        LivingEntity livingEntity3 = livingEntity = livingEntity2 != null ? livingEntity2 : this.aa;
        if (livingEntity == null || f5 <= 0.002f) {
            if (!this.Z) {
                this.aa = null;
            }
            return;
        }
        if (livingEntity2 != null) {
            if (!Mth.equal((float)this.L, (float)livingEntity2.getHealth())) {
                this.M = livingEntity2.getHealth() - this.L;
                this.L = livingEntity2.getHealth();
            }
            float f6 = Math.min(livingEntity2.getHealth(), 20.0f);
            f4 = Math.min(livingEntity2.getMaxHealth(), 20.0f);
            float f7 = f4 > 0.0f ? f6 / f4 : 0.0f;
            this.I.a((double)f7, 0.5, lf21.utils.b.f.f);
            this.J.a((double)f7, 1.5, lf21.utils.b.f.g);
        } else {
            this.M = 0.0f;
        }
        this.I.a();
        this.J.a();
        a a2 = a.a((String)this.Q.getValue());
        if (a2 != null) {
            float f8;
            float f9;
            float f10;
            float f11;
            float f12;
            this.y();
            this.a(a2);
            f4 = livingEntity.getMaxHealth() > 0.0f ? Math.min(livingEntity.getHealth(), 20.0f) / Math.min(livingEntity.getMaxHealth(), 20.0f) : 0.0f;
            G g2 = this.S;
            boolean bl = lf21.hud.p.mc.screen instanceof k || lf21.hud.p.mc.screen instanceof ChatScreen;
            boolean bl2 = this.R.is("Target") && !bl;
            boolean bl3 = bl2 && livingEntity2 != null && g2 != null && g2.a == livingEntity2;
            float f13 = f2;
            float f14 = f3;
            if (bl3) {
                float f15 = Math.max(0.01f, this.getScale());
                f12 = this.u() * f15;
                f11 = this.v() * f15;
                f10 = 6.0f;
                f9 = mc.getWindow().getGuiScaledWidth();
                f8 = mc.getWindow().getGuiScaledHeight();
                float f16 = g2.d + f10;
                float f17 = g2.b - f10 - f12;
                boolean bl4 = f16 + f12 > f9 - 2.0f;
                boolean bl5 = f17 >= 2.0f && f17 + f12 <= f9 - 2.0f;
                boolean bl6 = bl4 && bl5;
                this.a(livingEntity2, bl6);
                float f18 = Mth.lerp((float)Mth.clamp((float)this.T.e(), (float)0.0f, (float)1.0f), (float)f16, (float)f17);
                f13 = Mth.clamp((float)f18, (float)2.0f, (float)Math.max(2.0f, f9 - f12 - 2.0f));
                f14 = Mth.clamp((float)((g2.c + g2.e - f11) * 0.5f), (float)2.0f, (float)Math.max(2.0f, f8 - f11 - 2.0f));
            } else {
                this.U = false;
                this.W = Integer.MIN_VALUE;
                if (bl2 && livingEntity2 == null) {
                    f13 = this.ab;
                    f14 = this.ac;
                }
            }
            if (livingEntity2 != null) {
                this.ab = f13;
                this.ac = f14;
            }
            PoseStack poseStack = render2DEvent.a();
            f12 = f13 + this.u() * 0.5f;
            f11 = f14 + this.v() * 0.5f;
            poseStack.pushPose();
            poseStack.translate(f12, f11, 0.0f);
            poseStack.scale(Math.max(0.001f, f5), Math.max(0.001f, f5), 1.0f);
            poseStack.translate(-f12, -f11, 0.0f);
            f10 = f13;
            f9 = f14;
            f8 = f4;
            Renderer.render(render2DEvent.b(), drawContext -> a2.a(render2DEvent, livingEntity, this.I, this.J, f8, f10, f9));
            poseStack.popPose();
        }
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        this.S = null;
        this.y();
        if (this.R.is("HUD") || lf21.hud.p.mc.level == null || lf21.hud.p.mc.player == null) {
            return;
        }
        LivingEntity livingEntity = this.a(false);
        if (livingEntity == null || livingEntity == lf21.hud.p.mc.player) {
            return;
        }
        lf21.utils.render.b.a();
        AABB aABB = lf21.utils.game.c.b((Entity)livingEntity, renderEvent.b());
        Vec3 vec3 = new Vec3[]{new Vec3(aABB.minX, aABB.minY, aABB.minZ), new Vec3(aABB.maxX, aABB.minY, aABB.minZ), new Vec3(aABB.maxX, aABB.minY, aABB.maxZ), new Vec3(aABB.minX, aABB.minY, aABB.maxZ), new Vec3(aABB.minX, aABB.maxY, aABB.minZ), new Vec3(aABB.maxX, aABB.maxY, aABB.minZ), new Vec3(aABB.maxX, aABB.maxY, aABB.maxZ), new Vec3(aABB.minX, aABB.maxY, aABB.maxZ)};
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MAX_VALUE;
        float f4 = -3.4028235E38f;
        float f5 = -3.4028235E38f;
        int n2 = 0;
        for (Vec3 vec32 : vec3) {
            i i2 = lf21.utils.render.b.a(vec32.x, vec32.y, vec32.z);
            if (i2 == null) continue;
            ++n2;
            f2 = Math.min(f2, i2.a);
            f3 = Math.min(f3, i2.b);
            f4 = Math.max(f4, i2.a);
            f5 = Math.max(f5, i2.b);
        }
        if (n2 > 0) {
            this.S = new G(livingEntity, f2, f3, f4, f5);
        } else {
            Vec3 vec33 = aABB.getCenter();
            i i3 = lf21.utils.render.b.a(vec33.x, vec33.y, vec33.z, renderEvent.b());
            this.S = new G(livingEntity, i3.a, i3.b, i3.a, i3.b);
        }
    }
}

