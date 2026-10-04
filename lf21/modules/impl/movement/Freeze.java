/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.utils.misc.PacketUtil;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundKeepAlivePacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPongPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;

public final class Freeze
extends Module {
    private static final String[] v;
    public final BooleanSetting cancelPong;
    public final BooleanSetting resetVelocity;
    public static Freeze j;
    public final BooleanSetting disableOnFlag;
    private double q;
    public final ModeSetting mode;
    private final Queue<Packet<?>> p;
    private double s;
    private float t;
    public final BooleanSetting bypassNegativeTimer;
    private float u;
    private double r;

    private void h() {
        Packet<?> packet;
        if (mc.getConnection() == null) {
            this.p.clear();
            return;
        }
        while ((packet = this.p.poll()) != null) {
            Packet<?> packet2 = packet;
            PacketUtil.a.add(packet2);
            mc.getConnection().send(packet2);
        }
    }

    private boolean a(Packet<?> packet) {
        return packet instanceof ServerboundKeepAlivePacket || packet instanceof ServerboundPongPacket;
    }

    @EventTarget(a=4)
    public void a(PacketEvent packetEvent) {
        if (packetEvent.a()) {
            if (packetEvent.c() instanceof ClientboundPlayerPositionPacket && ((Boolean)this.disableOnFlag.getValue()).booleanValue()) {
                this.setEnabled(false);
            }
            return;
        }
        String[] stringArray = v;
        if (this.mode.is("Stationary")) {
            Packet<?> packet = packetEvent.c();
            if (packet instanceof ServerboundMovePlayerPacket) {
                packetEvent.a(true);
                return;
            }
            if (packet instanceof ServerboundPongPacket && ((Boolean)this.cancelPong.getValue()).booleanValue()) {
                packetEvent.a(true);
                return;
            }
            if (packet instanceof ServerboundUseItemPacket || packet instanceof ServerboundUseItemOnPacket || packet instanceof ServerboundInteractPacket) {
                float f2 = this.a(this.t);
                float f3 = this.a(this.u);
                this.t = f2;
                this.u = f3;
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Rot(Freeze.mc.player.getYRot() + f2, Freeze.mc.player.getXRot() + f3, Freeze.mc.player.onGround()));
            }
            return;
        }
        if (this.a(packetEvent.c())) {
            return;
        }
        if (this.mode.is("Queue")) {
            this.p.offer(packetEvent.c());
        }
        packetEvent.a(true);
    }

    public Freeze() {
        String[] stringArray = v;
        super("Freeze", Category.MOVEMENT);
        this.mode = new ModeSetting("Mode", "Stationary", "Queue", "Cancel").withDefault("Stationary");
        this.disableOnFlag = new BooleanSetting("Disable On Flag", true);
        this.resetVelocity = new BooleanSetting("Reset Velocity", true);
        this.cancelPong = new BooleanSetting("Cancel Pong", false, () -> this.mode.is("Stationary"));
        this.bypassNegativeTimer = new BooleanSetting("Bypass Negative Timer", true);
        this.p = new ConcurrentLinkedQueue();
        j = this;
    }

    @Override
    protected void onEnable() {
        this.p.clear();
        if (Freeze.mc.player == null || Freeze.mc.level == null) {
            this.setEnabled(false);
            return;
        }
        this.q = Freeze.mc.player.getX();
        this.r = Freeze.mc.player.getY();
        this.s = Freeze.mc.player.getZ();
        this.t = 0.0f;
        this.u = 0.0f;
        if (((Boolean)this.resetVelocity.getValue()).booleanValue()) {
            Freeze.mc.player.setDeltaMovement(Vec3.ZERO);
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (Freeze.mc.player == null || Freeze.mc.level == null) {
            this.p.clear();
            return;
        }
        Freeze.mc.player.absMoveTo(this.q, this.r, this.s, Freeze.mc.player.getYRot(), Freeze.mc.player.getXRot());
        if (((Boolean)this.resetVelocity.getValue()).booleanValue()) {
            Freeze.mc.player.setDeltaMovement(Vec3.ZERO);
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
                    case 0 -> 93;
                    case 1 -> 40;
                    case 2 -> 118;
                    case 3 -> 63;
                    case 4 -> 27;
                    case 5 -> 67;
                    default -> 104;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean a(ItemStack itemStack) {
        return itemStack != null && !itemStack.isEmpty() && itemStack.getUseAnimation() != UseAnim.NONE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[12];
                var3_1 = 0;
                var2_2 = "\u001bZ\u0013Za&\u000e\u000fM\u0005Zoc>8D\u0019\\r7\u0011\n\u000e\\\u0017Kr,\u0006<Z\u000f\u000b\u001eI\u0018\\~/H\rG\u0018X\u0006\u001eI\u0018\\~/\u0004\u0010G\u0012Z\u0005\f]\u0013J~\u0005\f]\u0013J~\u000f\u0019A\u0005^y/\r}g\u0018\u001f]/\t:\u0015\u001fQ\u0006^h0H\u0013M\u0011^o*\u001e8\b\"Vv&\u001a";
                var4_3 = "\u001bZ\u0013Za&\u000e\u000fM\u0005Zoc>8D\u0019\\r7\u0011\n\u000e\\\u0017Kr,\u0006<Z\u000f\u000b\u001eI\u0018\\~/H\rG\u0018X\u0006\u001eI\u0018\\~/\u0004\u0010G\u0012Z\u0005\f]\u0013J~\u0005\f]\u0013J~\u000f\u0019A\u0005^y/\r}g\u0018\u001f]/\t:\u0015\u001fQ\u0006^h0H\u0013M\u0011^o*\u001e8\b\"Vv&\u001a".length();
                var1_4 = 6;
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
                    var5[var3_1++] = Freeze.c(Freeze.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u000e\\\u0017Kr,\u0006<Z\u000f\u0006\u001eI\u0018\\~/";
                    var4_3 = "\u000e\\\u0017Kr,\u0006<Z\u000f\u0006\u001eI\u0018\\~/".length();
                    var1_4 = 10;
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
                    var5[var3_1++] = Freeze.c(Freeze.a(v1));
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
        Freeze.v = var5;
    }

    @Override
    protected void onDisable() {
        this.h();
        if (Freeze.mc.player != null && ((Boolean)this.resetVelocity.getValue()).booleanValue()) {
            Freeze.mc.player.setDeltaMovement(Vec3.ZERO);
        }
        if (((Boolean)this.bypassNegativeTimer.getValue()).booleanValue()) {
            this.a();
        }
    }

    private void a() {
        if (Freeze.mc.player == null || Freeze.mc.level == null || mc.getConnection() == null) {
            return;
        }
        InteractionHand interactionHand = InteractionHand.OFF_HAND;
        int n3 = Freeze.mc.player.getInventory().selected;
        int n4 = -1;
        if (this.a(Freeze.mc.player.getOffhandItem())) {
            for (int i2 = 0; i2 < 9; ++i2) {
                if (this.a(Freeze.mc.player.getInventory().getItem(i2))) continue;
                interactionHand = InteractionHand.MAIN_HAND;
                if (i2 == n3) break;
                n4 = i2;
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundSetCarriedItemPacket(i2));
                break;
            }
        }
        InteractionHand interactionHand2 = interactionHand;
        PacketUtil.b(n2 -> new ServerboundUseItemPacket(interactionHand2, n2));
        if (n4 != -1) {
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundSetCarriedItemPacket(n3));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean shouldCancelPlayerTick() {
        if (j == null) return false;
        if (!j.isEnabled()) return false;
        String[] stringArray = v;
        if (!Freeze.j.mode.is("Stationary") && !Freeze.j.mode.is("Queue")) {
            if (!Freeze.j.mode.is("Cancel")) return false;
        }
        if (Freeze.mc.player == null) return false;
        if (Freeze.mc.level == null) return false;
        return true;
    }

    private float a(float f2) {
        float f3;
        while (Math.abs((f3 = (float)ThreadLocalRandom.current().nextDouble(0.002, 0.01)) - f2) < 1.0E-6f) {
        }
        return f3;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x68);
        }
        return cArray;
    }
}

