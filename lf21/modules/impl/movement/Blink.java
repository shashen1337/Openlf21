/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import java.awt.Color;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.AntiBots;
import lf21.modules.impl.combat.FakeLag;
import lf21.modules.impl.movement.FireballBlink;
import lf21.modules.impl.world.Teams;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.m;
import lf21.utils.misc.PacketUtil;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ServerboundHelloPacket;
import net.minecraft.network.protocol.login.ServerboundKeyPacket;
import net.minecraft.network.protocol.status.ServerboundPingRequestPacket;
import net.minecraft.network.protocol.status.ServerboundStatusRequestPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class Blink
extends Module {
    private final NumberSetting projectileDistance;
    private int C;
    private final NumberSetting releaseSpeed;
    private static final int k;
    public final BooleanSetting pulse;
    private static final String[] F;
    private final NumberSetting releaseTicksOnDamage;
    private int E;
    private final BooleanSetting renderStatus;
    public static Blink j;
    public final NumberSetting delay;
    private final BooleanSetting renderServerPosition;
    private boolean A;
    private final NumberSetting maxTicks;
    private boolean z;
    private final Queue<Packet<?>> x;
    private static final Set<Class<?>> l;
    private int D;
    private int B;
    private m y;
    private final NumberSetting tNTDistance;
    private final BooleanSetting releaseOnDanger;
    private final NumberSetting playerDistance;

    public Blink() {
        String[] stringArray = F;
        super("Blink", Category.MOVEMENT);
        this.releaseTicksOnDamage = new NumberSetting("Release Ticks on Damage", 20, 0, 50, 1);
        this.releaseSpeed = new NumberSetting("Release Speed", 10, 1, 20, 1);
        this.maxTicks = new NumberSetting("Max Ticks", 200, 10, 500, 1);
        this.playerDistance = new NumberSetting("Player Distance", 4, 0, 10, 0.1);
        this.tNTDistance = new NumberSetting("TNT Distance", 5, 0, 10, 0.1);
        this.projectileDistance = new NumberSetting("Projectile Distance", 0.8, 0, 3, 0.1);
        this.releaseOnDanger = new BooleanSetting("Release On Danger", true);
        this.renderStatus = new BooleanSetting("Render Status", true);
        this.renderServerPosition = new BooleanSetting("Render Server Position", true);
        this.pulse = new BooleanSetting("Pulse", false);
        this.delay = new NumberSetting("Delay", 10, 2, 40, 1, () -> (Boolean)this.pulse.getValue());
        this.x = new ConcurrentLinkedQueue();
        j = this;
    }

    private boolean h() {
        return mc != null && mc.hasSingleplayerServer();
    }

    private void a(ServerboundMovePlayerPacket serverboundMovePlayerPacket) {
        if (this.y == null) {
            return;
        }
        double d2 = serverboundMovePlayerPacket.getX(this.y.getX());
        double d3 = serverboundMovePlayerPacket.getY(this.y.getY());
        double d4 = serverboundMovePlayerPacket.getZ(this.y.getZ());
        float f2 = serverboundMovePlayerPacket.getYRot(this.y.getYRot());
        float f3 = serverboundMovePlayerPacket.getXRot(this.y.getXRot());
        this.y.setPos(d2, d3, d4);
        if (serverboundMovePlayerPacket.hasRotation()) {
            this.y.setYRot(f2);
            this.y.setYHeadRot(f2);
            this.y.setXRot(f3);
        }
    }

    private long a() {
        return this.x.stream().filter(ServerboundMovePlayerPacket.class::isInstance).count();
    }

    private boolean a(Entity entity, double d2) {
        Vec3 vec3 = entity.position();
        Vec3 vec32 = entity.getDeltaMovement();
        AABB aABB = this.y.getBoundingBox().inflate(d2);
        for (int i2 = 0; i2 < 30; ++i2) {
            if (aABB.contains(vec3 = vec3.add(vec32))) {
                return true;
            }
            double d3 = entity.isInWater() ? 0.8 : 0.99;
            vec32 = new Vec3(vec32.x * d3, vec32.y * d3 - 0.05, vec32.z * d3);
        }
        return false;
    }

    private boolean m() {
        if (this.y == null || Blink.mc.level == null) {
            return false;
        }
        double d2 = ((Number)this.playerDistance.getValue()).doubleValue();
        if (d2 > 0.0 && Blink.mc.level.players().stream().anyMatch(abstractClientPlayer -> abstractClientPlayer != Blink.mc.player && abstractClientPlayer != this.y && abstractClientPlayer.isAlive() && !Teams.a((Entity)abstractClientPlayer) && !AntiBots.b((Entity)abstractClientPlayer) && abstractClientPlayer.getBoundingBox().distanceToSqr(this.y.position()) <= d2 * d2)) {
            return true;
        }
        double d3 = ((Number)this.tNTDistance.getValue()).doubleValue();
        double d4 = ((Number)this.projectileDistance.getValue()).doubleValue();
        AABB aABB = this.y.getBoundingBox().inflate(Math.max(d3, 12.0));
        for (Entity entity : Blink.mc.level.getEntities((Entity)this.y, aABB)) {
            if (entity instanceof PrimedTnt && d3 > 0.0 && (double)entity.distanceTo((Entity)this.y) <= d3) {
                return true;
            }
            if (!(entity instanceof AbstractArrow) && !(entity instanceof ThrownEgg) && !(entity instanceof Snowball) || !(d4 > 0.0) || !this.a(entity, d4)) continue;
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                Blink.k = -1337;
                var5 = new String[16];
                var3_1 = 0;
                var2_2 = "\u0015\u0002zq\u001e)a,\u001cp;?#f1\u0011{x\u001e\u0016\u0017\u0015{\u007f\u001e85\u0016\u0015gm\u001e85\u0015\u001ffr\u000f#z+\t\b\u0011m;/#v.\u0003\u0005\u0015\u0005yh\u001e\u0006\u0007\u001c|u\u0010j\r\u0017\u0015y~\u001a9pe#e~\u001e.\u0006e\u0004|x\u00109\re\u0004|x\u001095'\u0015}r\u0015.\u0010\u0007\u001c|u\u0010jg \u001cpz\b#{\"P\r\u0017\u0015{\u007f\u001e85\u0016\u0004to\u000e9\u0017\u0017\u0015y~\u001a9pe$|x\u001095*\u001e5_\u001a't\"\u0015\u0005\u0007\u001c|u\u0010\u000f\u0015\u001ctb\u001e85\u0001\u0019fo\u001a$v \f\u0011>A;?#f1\u0011{x\u001e";
                var4_3 = "\u0015\u0002zq\u001e)a,\u001cp;?#f1\u0011{x\u001e\u0016\u0017\u0015{\u007f\u001e85\u0016\u0015gm\u001e85\u0015\u001ffr\u000f#z+\t\b\u0011m;/#v.\u0003\u0005\u0015\u0005yh\u001e\u0006\u0007\u001c|u\u0010j\r\u0017\u0015y~\u001a9pe#e~\u001e.\u0006e\u0004|x\u00109\re\u0004|x\u001095'\u0015}r\u0015.\u0010\u0007\u001c|u\u0010jg \u001cpz\b#{\"P\r\u0017\u0015{\u007f\u001e85\u0016\u0004to\u000e9\u0017\u0017\u0015y~\u001a9pe$|x\u001095*\u001e5_\u001a't\"\u0015\u0005\u0007\u001c|u\u0010\u000f\u0015\u001ctb\u001e85\u0001\u0019fo\u001a$v \f\u0011>A;?#f1\u0011{x\u001e".length();
                var1_4 = 19;
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
                    var5[var3_1++] = Blink.c(Blink.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0017\u0015y~\u001a9pe?{;?+{\"\u0015g\u0005\u0001\u0015yz\u0002";
                    var4_3 = "\u0017\u0015y~\u001a9pe?{;?+{\"\u0015g\u0005\u0001\u0015yz\u0002".length();
                    var1_4 = 17;
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
                    var5[var3_1++] = Blink.c(Blink.a(v1));
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
        Blink.F = var5;
        Blink.l = Set.of(ClientIntentionPacket.class, ServerboundStatusRequestPacket.class, ServerboundPingRequestPacket.class, ServerboundHelloPacket.class, ServerboundKeyPacket.class);
    }

    @EventTarget(a=4)
    public void a(PacketEvent packetEvent) {
        if (Blink.mc.player == null || Blink.mc.level == null || packetEvent.a() || packetEvent.isCancelled()) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (l.contains(packet.getClass())) {
            return;
        }
        if (this.h() && !(packet instanceof ServerboundMovePlayerPacket)) {
            return;
        }
        packetEvent.a(true);
        this.x.offer(packet);
    }

    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        String string;
        if (!((Boolean)this.renderStatus.getValue()).booleanValue() || Blink.mc.player == null) {
            return;
        }
        long l2 = this.a();
        int n2 = Math.max(1, ((Number)this.maxTicks.getValue()).intValue());
        float f2 = Mth.clamp((float)((float)l2 / (float)n2), (float)0.0f, (float)1.0f);
        if (this.z) {
            var11_5 = F;
            string = "Blink releasing " + l2 + " ticks";
        } else {
            var11_5 = F;
            string = "Blink " + l2 + " ticks behind";
        }
        String string2 = string;
        float f3 = 112.0f;
        float f4 = 18.0f;
        float f5 = (float)mc.getWindow().getGuiScaledWidth() / 2.0f - f3 / 2.0f;
        float f6 = (float)mc.getWindow().getGuiScaledHeight() / 2.0f + 18.0f;
        RenderUtil.b(render2DEvent.a(), f5, f6, f3, f4, 4.0f, new Color(10, 12, 15, 190).getRGB());
        render2DEvent.b().drawString(Blink.mc.font, string2, (int)(f5 + 6.0f), (int)(f6 + 4.0f), -1841173, false);
        RenderUtil.b(render2DEvent.a(), f5 + 4.0f, f6 + f4 - 3.0f, f3 - 8.0f, 1.5f, 0.75f, new Color(58, 75, 82, 180).getRGB());
        if (f2 > 0.0f) {
            RenderUtil.b(render2DEvent.a(), f5 + 4.0f, f6 + f4 - 3.0f, (f3 - 8.0f) * f2, 1.5f, 0.75f, new Color(58, 220, 210, 230).getRGB());
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x15);
        }
        return cArray;
    }

    private void i() {
        Packet<?> packet;
        while ((packet = this.x.poll()) != null) {
            this.a(packet);
            if (!(packet instanceof ServerboundMovePlayerPacket)) continue;
            ServerboundMovePlayerPacket serverboundMovePlayerPacket = (ServerboundMovePlayerPacket)packet;
            this.a(serverboundMovePlayerPacket);
            ++this.C;
            break;
        }
    }

    @Override
    public void setEnabled(boolean bl) {
        if (bl) {
            if (!this.isEnabled()) {
                super.setEnabled(true);
            }
            return;
        }
        if (this.h()) {
            this.j();
            this.A = true;
            super.setEnabled(false);
            return;
        }
        if (this.A || Blink.mc.player == null || Blink.mc.level == null || this.x.isEmpty()) {
            super.setEnabled(false);
        } else {
            this.z = true;
        }
    }

    private void l() {
        if (this.y != null && Blink.mc.level != null) {
            Blink.mc.level.removeEntity(this.y.getId(), Entity.RemovalReason.DISCARDED);
        }
        this.y = null;
    }

    private void j() {
        Packet<?> packet;
        while ((packet = this.x.poll()) != null) {
            this.a(packet);
            if (!(packet instanceof ServerboundMovePlayerPacket)) continue;
            ServerboundMovePlayerPacket serverboundMovePlayerPacket = (ServerboundMovePlayerPacket)packet;
            this.a(serverboundMovePlayerPacket);
        }
    }

    private void a(Packet<?> packet) {
        if (mc.getConnection() == null) {
            return;
        }
        Packet<?> packet2 = packet;
        PacketUtil.a.add(packet2);
        mc.getConnection().send(packet2);
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
                    case 0 -> 69;
                    case 1 -> 112;
                    case 2 -> 21;
                    case 3 -> 27;
                    case 4 -> 123;
                    case 5 -> 74;
                    default -> 21;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (Blink.mc.player == null || Blink.mc.level == null) {
            this.x.clear();
            this.l();
            this.A = true;
            super.setEnabled(false);
            return;
        }
        if (((Boolean)this.renderServerPosition.getValue()).booleanValue() && this.y == null) {
            this.k();
        } else if (!((Boolean)this.renderServerPosition.getValue()).booleanValue() && this.y != null) {
            this.l();
        }
        if (Blink.mc.player.hurtTime > this.D && Blink.mc.player.hurtTime >= 9) {
            this.B += ((Number)this.releaseTicksOnDamage.getValue()).intValue();
        }
        this.D = Blink.mc.player.hurtTime;
        this.C = 0;
        int n2 = ((Number)this.releaseSpeed.getValue()).intValue();
        while (this.C < n2 && this.B > 0 && !this.x.isEmpty()) {
            this.i();
            --this.B;
        }
        if (((Boolean)this.releaseOnDanger.getValue()).booleanValue()) {
            while (this.C < n2 && this.m() && !this.x.isEmpty()) {
                this.i();
            }
        }
        while (this.C < n2 && this.a() >= ((Number)this.maxTicks.getValue()).longValue() && !this.x.isEmpty()) {
            this.i();
        }
        if (this.z) {
            while (this.C < n2 && !this.x.isEmpty()) {
                this.i();
            }
            if (this.x.isEmpty()) {
                this.A = true;
                super.setEnabled(false);
            }
        }
        if (((Boolean)this.pulse.getValue()).booleanValue() && !this.z && Blink.mc.player.tickCount >= this.E) {
            this.j();
            if (((Boolean)this.renderServerPosition.getValue()).booleanValue()) {
                this.k();
            }
            this.E = Blink.mc.player.tickCount + ((Number)this.delay.getValue()).intValue();
        }
    }

    private void k() {
        this.l();
        if (Blink.mc.player == null || Blink.mc.level == null) {
            return;
        }
        this.y = new m((AbstractClientPlayer)Blink.mc.player);
        this.y.setSprinting(Blink.mc.player.isSprinting());
        Blink.mc.level.addPlayer(-1337, (AbstractClientPlayer)this.y);
    }

    @Override
    protected void onDisable() {
        this.j();
        this.l();
        this.z = false;
        this.A = false;
        this.B = 0;
    }

    @Override
    protected void onEnable() {
        this.x.clear();
        this.z = false;
        this.A = false;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        int n2 = this.E = Blink.mc.player == null ? 0 : Blink.mc.player.tickCount + ((Number)this.delay.getValue()).intValue();
        if (FakeLag.j != null && FakeLag.j.isEnabled()) {
            FakeLag.j.setEnabled(false);
        }
        if (FireballBlink.j != null && FireballBlink.j.isEnabled()) {
            FireballBlink.j.setEnabled(false);
        }
        if (Blink.mc.player != null && Blink.mc.level != null && ((Boolean)this.renderServerPosition.getValue()).booleanValue()) {
            this.k();
        }
    }
}

