/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import java.util.LinkedHashSet;
import lf21.event.EventTarget;
import lf21.event.impl.RotationEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.q;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.e;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundPingPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class Fly
extends Module {
    public final BooleanSetting disableOnTeleport;
    public static Fly j;
    private static final String[] x;
    public final BooleanSetting smoothCamera;
    public final ModeSetting mode;
    private final LinkedHashSet<q> w;
    public final BooleanSetting viewBobbing;
    public final BooleanSetting visualDragon;
    public final NumberSetting speed;
    public final BooleanSetting fakeDamage;
    private int v;
    private boolean u;
    public final NumberSetting vanillaSpeed;
    public final NumberSetting vanillaVerticalSpeed;
    private boolean t;

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x60);
        }
        return cArray;
    }

    @Override
    public void onEnable() {
        this.t = false;
        this.u = false;
        this.v = 0;
        this.w.clear();
        if (Fly.mc.player == null || Fly.mc.level == null && !Fly.mc.player.onGround()) {
            this.setEnabled(false);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[34];
                var3_1 = 0;
                var2_2 = "50p`\u0014;0\u0013>5g!8\u0014\u0005Z3z`\u000e\u001d\f\u001f,{2.\u00037\u0011W\u00107=`23\u0000@25s(z2\u0015\u0017,\u000b<=\u007f%z<\u0001\u0017=s%\n6=`%)\f@4\u001fD\u0003<0m\r)1{/.\u0010@9=y%(\u0019\u00067=`23\u0000\u0006=.}-zK\b-=`#2\u001c\u000f\u001d\b;5f`\r\u0019\f\u0011\u000473p%\b-=`#2\u001c\u000f\u001d\u000f=.}-zINCq%nk@NK\u0006=.}-zJ\u00107=`23\u0000@25s(z2\u0015\u0017,\u0006?0m4(\u0019\b;5f`\u0010\r\r\n\n6=`%)\f@4\u001fD\b;5f`\u0010\r\r\n\u0007,=z)6\u0014\u0001\u0016,=z)6\u0014\u0001Z\nq2.\u0011\u0003\u001b04\u0013*\u001d\u0005\u001e\f,5q7z:\u000f\u0018>}.=\u0006?0m4(\u0019\u000750p`\u0014;0\u0005,9f5)\u0005),q%>\b,)x#;\u0016@H\u000580{#1\r,=z)6\u0014\u0001Z\u000fd%?\u001c\r,5g5;\u0014@>.u'5\u0016";
                var4_3 = "50p`\u0014;0\u0013>5g!8\u0014\u0005Z3z`\u000e\u001d\f\u001f,{2.\u00037\u0011W\u00107=`23\u0000@25s(z2\u0015\u0017,\u000b<=\u007f%z<\u0001\u0017=s%\n6=`%)\f@4\u001fD\u0003<0m\r)1{/.\u0010@9=y%(\u0019\u00067=`23\u0000\u0006=.}-zK\b-=`#2\u001c\u000f\u001d\b;5f`\r\u0019\f\u0011\u000473p%\b-=`#2\u001c\u000f\u001d\u000f=.}-zINCq%nk@NK\u0006=.}-zJ\u00107=`23\u0000@25s(z2\u0015\u0017,\u0006?0m4(\u0019\b;5f`\u0010\r\r\n\n6=`%)\f@4\u001fD\b;5f`\u0010\r\r\n\u0007,=z)6\u0014\u0001\u0016,=z)6\u0014\u0001Z\nq2.\u0011\u0003\u001b04\u0013*\u001d\u0005\u001e\f,5q7z:\u000f\u0018>}.=\u0006?0m4(\u0019\u000750p`\u0014;0\u0005,9f5)\u0005),q%>\b,)x#;\u0016@H\u000580{#1\r,=z)6\u0014\u0001Z\u000fd%?\u001c\r,5g5;\u0014@>.u'5\u0016".length();
                var1_4 = 7;
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
                    var5[var3_1++] = Fly.c(Fly.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = ",=z)6\u0014\u0001\u0013-=`#2\u001c\u000f\u001d|D2?\u001c\t\u0019(}/4";
                    var4_3 = ",=z)6\u0014\u0001\u0013-=`#2\u001c\u000f\u001d|D2?\u001c\t\u0019(}/4".length();
                    var1_4 = 7;
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
                    var5[var3_1++] = Fly.c(Fly.a(v1));
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
        Fly.x = var5;
    }

    @Override
    public void onDisable() {
        this.a(true);
        if (Fly.mc.player != null && this.mode.is("Vanilla")) {
            Fly.mc.player.setDeltaMovement(0.0, Fly.mc.player.getDeltaMovement().y, 0.0);
        }
        if (Fly.mc.options != null) {
            Fly.mc.options.smoothCamera = false;
        }
    }

    public Fly() {
        String[] stringArray = x;
        super("Fly", Category.MOVEMENT);
        this.mode = new ModeSetting("Mode", "Elytra", "Vanilla", "Air Walk", "Air Jump", "Old NCP", "Latest NCP", "Verus", "Matrix", "Matrix High Jump", "Grim 1.9-1.18.1", "Grim 2", "Grim 3", "Watchdog", "Watchdog Prediction", "Vulcan 2", "Block", "MMC").withDefault("Elytra");
        this.vanillaSpeed = new NumberSetting("Vanilla Speed", 0.44, 0.1, 10.0, 0.01, () -> this.mode.is("Vanilla"));
        this.vanillaVerticalSpeed = new NumberSetting("Vanilla Vertical Speed", 0.44, 0.1, 10.0, 0.01, () -> this.mode.is("Vanilla"));
        this.speed = new NumberSetting("Speed", 1.0, 0.1, 10.0, 0.05, () -> {
            String[] stringArray = x;
            return !this.mode.is("Elytra") && !this.mode.is("Vanilla");
        });
        this.disableOnTeleport = new BooleanSetting("Disable on Teleport", false);
        this.viewBobbing = new BooleanSetting("View Bobbing", false);
        this.fakeDamage = new BooleanSetting("Fake Damage", false);
        this.smoothCamera = new BooleanSetting("Smooth Camera", false);
        this.visualDragon = new BooleanSetting("Visual Dragon", false);
        this.t = false;
        this.u = false;
        this.v = 0;
        this.w = new LinkedHashSet();
        j = this;
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
                    case 0 -> 122;
                    case 1 -> 92;
                    case 2 -> 20;
                    case 3 -> 64;
                    case 4 -> 90;
                    case 5 -> 120;
                    default -> 96;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void h() {
        double d2 = ((Number)this.vanillaSpeed.getValue()).doubleValue();
        double d3 = ((Number)this.vanillaVerticalSpeed.getValue()).doubleValue();
        e.b(d2);
        double d4 = 0.0;
        if (Fly.mc.options.keyJump.isDown() && !Fly.mc.options.keyShift.isDown()) {
            d4 = d3;
        } else if (Fly.mc.options.keyShift.isDown() && !Fly.mc.options.keyJump.isDown()) {
            d4 = -d3;
        }
        if (Fly.mc.player.tickCount % 40 == 0) {
            d4 = -0.04;
        }
        Vec3 vec3 = Fly.mc.player.getDeltaMovement();
        Fly.mc.player.setDeltaMovement(vec3.x, d4, vec3.z);
    }

    @EventTarget
    public void a(c c2) {
        ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket;
        if (!this.isEnabled() || Fly.mc.player == null) {
            return;
        }
        if (c2.a() instanceof ClientboundPlayerPositionPacket && ((Boolean)this.disableOnTeleport.getValue()).booleanValue()) {
            this.setEnabled(false);
            return;
        }
        if (!this.mode.is("Elytra")) {
            return;
        }
        Packet<ClientGamePacketListener> packet = c2.a();
        if (!this.u) {
            return;
        }
        if (packet instanceof ClientboundSetEntityMotionPacket && (clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet).getId() == Fly.mc.player.getId()) {
            this.v = 0;
        }
        if (packet instanceof ClientboundPingPacket && this.t) {
            c2.a(true);
            this.w.add(new q(packet));
            ++this.v;
        }
        if (packet instanceof ClientboundPlayerPositionPacket) {
            c2.a(true);
            this.w.add(new q(packet));
            this.a(true);
            this.v = 0;
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (!this.isEnabled() || Fly.mc.player == null || Fly.mc.level == null) {
            return;
        }
        String[] stringArray = x;
        if (this.mode.is("Vanilla")) {
            this.h();
            return;
        }
        if (!this.mode.is("Elytra")) {
            this.i();
            return;
        }
        if (!this.u) {
            this.u = true;
            return;
        }
        if (!this.t) {
            if (!Fly.mc.player.onGround() && Fly.mc.player.getDeltaMovement().y > 0.0) {
                this.t = true;
                this.a();
            }
            return;
        }
        if (this.v > 8) {
            this.a();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(boolean bl) {
        LinkedHashSet<q> linkedHashSet = this.w;
        synchronized (linkedHashSet) {
            if (bl) {
                this.w.forEach(q2 -> {
                    if (mc.getConnection() != null) {
                        try {
                            Fly.a(q2.a, (PacketListener)mc.getConnection());
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                });
                this.w.clear();
            }
        }
    }

    private void a() {
        mc.getConnection().send((Packet)new ServerboundPlayerCommandPacket((Entity)Fly.mc.player, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
    }

    private void i() {
        double d2 = ((Number)this.speed.getValue()).doubleValue();
        if (((Boolean)this.smoothCamera.getValue()).booleanValue()) {
            Fly.mc.options.smoothCamera = true;
        }
        if (((Boolean)this.viewBobbing.getValue()).booleanValue()) {
            Fly.mc.player.bob = 0.1f;
            Fly.mc.player.oBob = 0.1f;
        }
        String[] stringArray = x;
        if (this.mode.is("Air Jump")) {
            if (Fly.mc.options.keyJump.isDown()) {
                Fly.mc.player.jumpFromGround();
            }
            return;
        }
        stringArray = x;
        if (this.mode.is("Matrix High Jump")) {
            if (Fly.mc.player.onGround()) {
                Fly.mc.player.setDeltaMovement(Fly.mc.player.getDeltaMovement().x, Math.max(0.42, d2), Fly.mc.player.getDeltaMovement().z);
            }
            return;
        }
        e.b(d2);
        double d3 = 0.0;
        if (Fly.mc.options.keyJump.isDown()) {
            d3 = Math.min(d2, 1.0);
        } else if (Fly.mc.options.keyShift.isDown()) {
            d3 = -Math.min(d2, 1.0);
        } else {
            stringArray = x;
            if (this.mode.is("Old NCP") || this.mode.is("Latest NCP") || this.mode.is("Watchdog")) {
                d3 = Fly.mc.player.tickCount % 20 == 0 ? -0.04 : 0.0;
            }
        }
        Vec3 vec3 = Fly.mc.player.getDeltaMovement();
        Fly.mc.player.setDeltaMovement(vec3.x, d3, vec3.z);
        if (((Boolean)this.fakeDamage.getValue()).booleanValue() && Fly.mc.player.tickCount == 1) {
            Fly.mc.player.hurtTime = 10;
        }
    }

    @EventTarget
    public void a(RotationEvent rotationEvent) {
        if (Fly.mc.player == null) {
            return;
        }
        if (this.mode.is("Vanilla")) {
            return;
        }
        if (Fly.mc.player.onGround() && this.u && !Fly.mc.options.keyJump.isDown()) {
            Fly.mc.player.jumpFromGround();
            this.u = false;
        }
    }

    private static void a(Packet packet, PacketListener packetListener) {
        packet.handle(packetListener);
    }
}

