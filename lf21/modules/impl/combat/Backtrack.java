/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import lf21.event.EventTarget;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.EntityRemoveEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.AntiKB;
import lf21.modules.impl.combat.a.b;
import lf21.modules.impl.combat.t;
import lf21.modules.impl.combat.u;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class Backtrack
extends Module {
    private final NumberSetting maxRange;
    private final BooleanSetting render;
    private final BooleanSetting resetOnVelocity;
    private final NumberSetting minRange;
    public static Backtrack j;
    private final ConcurrentLinkedQueue<t> q;
    private static final String[] u;
    private final NumberSetting chance;
    private volatile u r;
    private final NumberSetting delay;
    private volatile boolean s;
    private volatile boolean t;

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (this.t) {
            return;
        }
        if (Backtrack.mc.player == null || Backtrack.mc.level == null) {
            this.r = null;
            this.m();
            return;
        }
        if (this.r == null) {
            this.m();
            return;
        }
        if (!this.r.a.isAlive() || this.r.a.isRemoved()) {
            this.r = null;
            this.m();
            return;
        }
        if (((Boolean)this.resetOnVelocity.getValue()).booleanValue() && this.l()) {
            this.r = null;
            this.m();
            return;
        }
        this.r.a();
        if (this.s) {
            this.a(this.r);
            this.h();
        }
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
                    case 0 -> 22;
                    case 1 -> 102;
                    case 2 -> 29;
                    case 3 -> 64;
                    case 4 -> 104;
                    case 5 -> 12;
                    default -> 13;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @EventTarget
    public void a(EntityRemoveEvent entityRemoveEvent) {
        if (this.t) {
            return;
        }
        if (entityRemoveEvent.a()) {
            return;
        }
        Entity entity = entityRemoveEvent.b();
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (Math.random() <= ((Number)this.chance.getValue()).doubleValue() / 100.0) {
                if (((Boolean)this.resetOnVelocity.getValue()).booleanValue() && this.l()) {
                    return;
                }
                if (this.r == null) {
                    this.m();
                    this.r = new u(player, player.position());
                }
            } else {
                this.r = null;
            }
        }
    }

    private static void a(Packet packet, PacketListener packetListener) {
        packet.handle(packetListener);
    }

    public boolean i() {
        return !this.t && this.s && !this.q.isEmpty();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "T\u0007~+\u001c~lu\r\t[\u0007e`:mcq\u0003\t[\u000fs`:mcq\u0003\u0006D\u0003s$\r~\u0005R\u0003q!\u0011";
                var4_3 = "T\u0007~+\u001c~lu\r\t[\u0007e`:mcq\u0003\t[\u000fs`:mcq\u0003\u0006D\u0003s$\r~\u0005R\u0003q!\u0011".length();
                var1_4 = 9;
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
                    var5[var3_1++] = Backtrack.c(Backtrack.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "D\u0003n%\u001c,BxFK%\u0004cn\u007f\u0012d\u0006U\u000e|.\u000bi";
                    var4_3 = "D\u0003n%\u001c,BxFK%\u0004cn\u007f\u0012d\u0006U\u000e|.\u000bi".length();
                    var1_4 = 17;
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
                    var5[var3_1++] = Backtrack.c(Backtrack.a(v1));
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
        Backtrack.u = var5;
    }

    private void a(u u2) {
        if (u2 == null || Backtrack.mc.player == null) {
            return;
        }
        Vec3 vec3 = Backtrack.mc.player.getEyePosition();
        double d2 = (double)u2.a.getBbWidth() / 2.0;
        AABB aABB = new AABB(u2.b.x - d2, u2.b.y, u2.b.z - d2, u2.b.x + d2, u2.b.y + (double)u2.a.getBbHeight(), u2.b.z + d2);
        Vec3 vec32 = g.a(vec3, aABB);
        Vec3 vec33 = g.a(vec3, u2.a.getBoundingBox());
        double d3 = vec3.distanceTo(vec32);
        double d4 = vec3.distanceTo(vec33);
        if (d4 <= 3.0 && d3 >= ((Number)this.minRange.getValue()).doubleValue() && d3 < ((Number)this.maxRange.getValue()).doubleValue()) {
            this.s = true;
        } else {
            this.r = null;
            this.m();
        }
    }

    public Backtrack() {
        String[] stringArray = u;
        super("Backtrack", Category.COMBAT);
        this.minRange = new NumberSetting("Min Range", 3.0, 1.0, 6.0, 0.1);
        this.maxRange = new NumberSetting("Max Range", 6.0, 1.0, 6.0, 0.1);
        this.delay = new NumberSetting("Delay", 200.0, 0.0, 1000.0, 10.0);
        this.chance = new NumberSetting("Chance", 100.0, 5.0, 100.0, 1.0);
        this.resetOnVelocity = new BooleanSetting("Reset On Velocity", true);
        this.render = new BooleanSetting("Render", true);
        this.q = new ConcurrentLinkedQueue();
        j = this;
    }

    @EventTarget
    public void a(DisconnectEvent disconnectEvent) {
        this.r = null;
        this.m();
    }

    public boolean a() {
        return !this.t && this.s;
    }

    @Override
    public void onEnable() {
        this.r = null;
        this.s = false;
        this.q.clear();
    }

    @Override
    public void onDisable() {
        this.r = null;
        this.m();
    }

    private boolean l() {
        if (AntiKB.j != null && AntiKB.j.isEnabled()) {
            Optional<b> optional = b.a((String)AntiKB.l.getValue());
            return optional.isPresent() && optional.get().e();
        }
        return false;
    }

    public void a(boolean bl) {
        if (this.t == bl) {
            return;
        }
        if (bl) {
            this.r = null;
            this.m();
        }
        this.t = bl;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (this.t) {
            return;
        }
        if (!((Boolean)this.render.getValue()).booleanValue()) {
            return;
        }
        if (!this.s) {
            return;
        }
        u u2 = this.r;
        if (u2 == null) {
            return;
        }
        if (Backtrack.mc.player == null || Backtrack.mc.gameRenderer == null) {
            return;
        }
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = u2.a(renderEvent.b());
        double d2 = (double)u2.a.getBbWidth() / 2.0;
        double d3 = u2.a.getBbHeight();
        poseStack.pushPose();
        Vec3 vec32 = Backtrack.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.translate(vec3.x - vec32.x, vec3.y - vec32.y, vec3.z - vec32.z);
        AABB aABB = new AABB(-d2, 0.0, -d2, d2, d3, d2);
        Color color = new Color(255, 255, 255, 64);
        RenderUtil.b(aABB, poseStack, color, color);
        Color color2 = new Color(255, 255, 255, 204);
        RenderUtil.a(aABB, poseStack, color2, color2);
        poseStack.popPose();
    }

    public boolean j() {
        return !this.t && this.s;
    }

    @EventTarget(a=1)
    public void a(c c2) {
        ClientboundMoveEntityPacket clientboundMoveEntityPacket;
        if (this.t) {
            return;
        }
        u u2 = this.r;
        if (u2 == null) {
            return;
        }
        if (Backtrack.mc.player == null || Backtrack.mc.level == null) {
            return;
        }
        if (c2.isCancelled()) {
            return;
        }
        Packet<ClientGamePacketListener> packet = c2.a();
        if (packet instanceof ClientboundMoveEntityPacket && (clientboundMoveEntityPacket = (ClientboundMoveEntityPacket)packet).hasPosition()) {
            Entity entity = clientboundMoveEntityPacket.getEntity((Level)Backtrack.mc.level);
            if (entity != null && entity.getId() == u2.a.getId()) {
                Vec3 vec3;
                u2.b = vec3 = u2.a(clientboundMoveEntityPacket.getXa(), clientboundMoveEntityPacket.getYa(), clientboundMoveEntityPacket.getZa());
                this.a(u2);
            }
        } else if (packet instanceof ClientboundTeleportEntityPacket) {
            ClientboundTeleportEntityPacket clientboundTeleportEntityPacket = (ClientboundTeleportEntityPacket)packet;
            if (clientboundTeleportEntityPacket.getId() == u2.a.getId()) {
                Vec3 vec3 = new Vec3(clientboundTeleportEntityPacket.getX(), clientboundTeleportEntityPacket.getY(), clientboundTeleportEntityPacket.getZ());
                u2.a(vec3);
                u2.b = vec3;
                this.a(u2);
            }
        } else if (packet instanceof ClientboundRemoveEntitiesPacket) {
            ClientboundRemoveEntitiesPacket clientboundRemoveEntitiesPacket = (ClientboundRemoveEntitiesPacket)packet;
            if (clientboundRemoveEntitiesPacket.getEntityIds().contains(u2.a.getId())) {
                this.r = null;
                this.m();
                return;
            }
        } else if (packet instanceof ClientboundPlayerPositionPacket) {
            this.r = null;
            this.m();
            return;
        }
        if (this.s) {
            if (((Boolean)this.resetOnVelocity.getValue()).booleanValue() && packet instanceof ClientboundSetEntityMotionPacket) {
                clientboundMoveEntityPacket = (ClientboundSetEntityMotionPacket)packet;
                if (Backtrack.mc.player != null && clientboundMoveEntityPacket.getId() == Backtrack.mc.player.getId()) {
                    this.r = null;
                    this.m();
                    return;
                }
            }
            this.q.add(new t(packet));
            c2.a(true);
        }
    }

    private void m() {
        block5: {
            if (!this.s) break block5;
            this.s = false;
            while (!this.q.isEmpty()) {
                t t2 = this.q.poll();
                try {
                    if (mc.getConnection() == null) continue;
                    Backtrack.a(t2.a, (PacketListener)mc.getConnection());
                }
                catch (Exception exception) {}
            }
        }
        this.q.clear();
    }

    public boolean k() {
        return this.t;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xD);
        }
        return cArray;
    }

    private void h() {
        long l2 = System.currentTimeMillis();
        long l3 = ((Number)this.delay.getValue()).longValue();
        while (!this.q.isEmpty()) {
            t t2 = this.q.peek();
            if (l2 - t2.b < l3) {
                break;
            }
            this.q.poll();
            try {
                if (mc.getConnection() == null) continue;
                Backtrack.a(t2.a, (PacketListener)mc.getConnection());
            }
            catch (Exception exception) {}
        }
        if (this.q.isEmpty()) {
            this.r = null;
            this.s = false;
        }
    }
}

