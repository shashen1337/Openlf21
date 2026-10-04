/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.e;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.AntiVoid;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.PacketUtil;
import lf21.utils.misc.ReflectionUtil;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundPongPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.BowlFoodItem;

public class Stuck
extends Module {
    private final NumberSetting pulseTicks;
    public static Stuck j;
    private final BooleanSetting rotate;
    private final BooleanSetting test;
    private boolean v;
    private float r;
    private Packet<?> p;
    private int o;
    private static final String[] w;
    private boolean s;
    private int u;
    private float q;
    private final ModeSetting mode;
    private final Queue<ServerboundPongPacket> t;

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x54);
        }
        return cArray;
    }

    @Override
    public void onEnable() {
        this.o = 0;
        this.p = null;
        this.q = RotationHandler.a.e();
        this.r = RotationHandler.a.f();
        this.s = false;
        this.u = 0;
        this.v = false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[11];
                var3_1 = 0;
                var2_2 = "/\u0006dx#\u0006,\u0013rp-\u000f\u0006,\u0013rp-\u000f\u0004.\u001bb~\u0006.\u001dez<\u001e\u000b,\u0007}h-[\u0000\u0015\u0011zh\u0004(\u0017bo\u00058\u0017}z1\u0004.\u001bb~";
                var4_3 = "/\u0006dx#\u0006,\u0013rp-\u000f\u0006,\u0013rp-\u000f\u0004.\u001bb~\u0006.\u001dez<\u001e\u000b,\u0007}h-[\u0000\u0015\u0011zh\u0004(\u0017bo\u00058\u0017}z1\u0004.\u001bb~".length();
                var1_4 = 5;
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
                    var5[var3_1++] = Stuck.c(Stuck.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "8\u0017}z1\u00041\u001du~";
                    var4_3 = "8\u0017}z1\u00041\u001du~".length();
                    var1_4 = 5;
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
                    var5[var3_1++] = Stuck.c(Stuck.a(v1));
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
        Stuck.w = var5;
    }

    @Override
    public void setEnabled(boolean bl) {
        if (Stuck.mc.player == null) {
            return;
        }
        if (bl) {
            super.setEnabled(true);
        } else if (this.mode.is("Delay")) {
            if (this.o == 3) {
                super.setEnabled(false);
            } else {
                this.s = true;
            }
        } else {
            super.setEnabled(false);
        }
    }

    public Stuck() {
        String[] stringArray = w;
        super("Stuck", Category.PLAYER);
        this.mode = new ModeSetting("Mode", "Rise", "Delay", "Packet").withDefault("Rise");
        this.rotate = new BooleanSetting("Rotate", false, () -> this.mode.is("Rise"));
        this.test = new BooleanSetting("Test", false, () -> this.mode.is("Rise"));
        this.pulseTicks = new NumberSetting("Pulse Ticks", 0, 0, 30, 1, () -> this.mode.is("Rise"));
        this.o = 0;
        this.s = false;
        this.t = new ConcurrentLinkedQueue<ServerboundPongPacket>();
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
                    case 0 -> 124;
                    case 1 -> 114;
                    case 2 -> 17;
                    case 3 -> 27;
                    case 4 -> 72;
                    case 5 -> 123;
                    default -> 84;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean h() {
        Packet<?> packet = this.p;
        if (packet instanceof ServerboundUseItemPacket) {
            ServerboundUseItemPacket serverboundUseItemPacket = (ServerboundUseItemPacket)packet;
            return !((packet = Stuck.mc.player.getItemInHand(serverboundUseItemPacket.getHand())).getItem() instanceof BowlFoodItem) && !(packet.getItem() instanceof BowItem);
        }
        packet = this.p;
        if (packet instanceof ServerboundPlayerActionPacket) {
            ServerboundPlayerActionPacket serverboundPlayerActionPacket = (ServerboundPlayerActionPacket)packet;
            return serverboundPlayerActionPacket.getAction() == ServerboundPlayerActionPacket.Action.RELEASE_USE_ITEM && Stuck.mc.player.getUseItem().getItem() instanceof BowItem;
        }
        return false;
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        String[] stringArray = w;
        if (this.mode.is("Rise")) {
            if (!this.v && Stuck.mc.player != null) {
                Stuck.mc.player.setDeltaMovement(0.0, 0.0, 0.0);
            }
            return;
        }
        Scaffold scaffold = Scaffold.INSTANCE;
        if (!this.a() && scaffold.isEnabled()) {
            scaffold.setEnabled(false);
            return;
        }
        if (Stuck.mc.player == null) {
            return;
        }
        if (motionEvent.isPost()) {
            Stuck.mc.player.setDeltaMovement(0.0, 0.0, 0.0);
            if (this.o == 1) {
                this.o = 2;
                float f2 = Stuck.mc.player.getYRot();
                float f3 = Stuck.mc.player.getXRot();
                if (this.h() && (this.q != f2 || this.r != f3)) {
                    PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Rot(f2, f3, Stuck.mc.player.onGround()));
                    while (!this.t.isEmpty()) {
                        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)((Packet)this.t.poll()));
                    }
                    this.q = f2;
                    this.r = f3;
                }
                PacketUtil.sendQueued(this.p);
            } else if (!this.a()) {
                stringArray = w;
                if (this.mode.is("Packet") && Stuck.mc.player.tickCount % 10 == 0) {
                    while (!this.t.isEmpty()) {
                        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)((Packet)this.t.poll()));
                    }
                }
            }
            if (this.s) {
                stringArray = w;
                if (this.mode.is("Delay")) {
                    PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(Stuck.mc.player.getX() + 1337.0, Stuck.mc.player.getY(), Stuck.mc.player.getZ() + 1337.0, Stuck.mc.player.onGround()));
                } else {
                    PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerCommandPacket((Entity)Stuck.mc.player, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
                }
                while (!this.t.isEmpty()) {
                    PacketUtil.sendQueued((Packet<ServerGamePacketListener>)((Packet)this.t.poll()));
                }
                if (this.mode.is("Packet")) {
                    for (int i2 = 1; i2 <= 4; ++i2) {
                        ClientBase.delayPackets.add(() -> {});
                    }
                }
                this.o = 3;
                this.s = false;
            }
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        String[] stringArray = w;
        if (this.mode.is("Rise")) {
            int n2 = ((Number)this.pulseTicks.getValue()).intValue();
            if (n2 > 0 && ++this.u >= n2) {
                this.v = true;
                this.u = 0;
            } else if (this.v) {
                this.v = false;
            }
            if (!this.v && Stuck.mc.player != null) {
                Stuck.mc.player.setDeltaMovement(0.0, 0.0, 0.0);
            }
            return;
        }
        if (!this.mode.is("Packet")) {
            return;
        }
        Scaffold scaffold = Scaffold.INSTANCE;
        if (scaffold.isEnabled()) {
            scaffold.setEnabled(false);
            return;
        }
        if (Stuck.mc.player == null) {
            return;
        }
        if (!this.a()) {
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerCommandPacket((Entity)Stuck.mc.player, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
        }
    }

    @EventTarget
    public void a(e e2) {
        this.o = 3;
        this.p = null;
        this.setEnabled(false);
    }

    private boolean a() {
        return LF21Client.isReady() && AntiVoid.j != null && AntiVoid.j.isEnabled() && !Stuck.mc.player.onGround() && AntiVoid.j.n;
    }

    @EventTarget
    public void a(StrafeEvent strafeEvent) {
        strafeEvent.a(0.0f);
        strafeEvent.b(0.0f);
        strafeEvent.a(false);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventTarget(a=1)
    public void a(PacketEvent packetEvent) {
        if (Stuck.mc.player == null) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        String[] stringArray = w;
        if (this.mode.is("Rise")) {
            if (((Boolean)this.test.getValue()).booleanValue() && !this.v && (packet instanceof ServerboundInteractPacket || packet instanceof ServerboundUseItemPacket || packet instanceof ServerboundPlayerActionPacket)) {
                this.v = true;
                return;
            }
            if (!this.v && packet instanceof ServerboundMovePlayerPacket) {
                ServerboundMovePlayerPacket serverboundMovePlayerPacket = (ServerboundMovePlayerPacket)packet;
                if (!((Boolean)this.rotate.getValue()).booleanValue() || !serverboundMovePlayerPacket.hasRotation()) {
                    packetEvent.a(true);
                }
            }
            return;
        }
        if (packet instanceof ServerboundMovePlayerPacket) {
            ServerboundMovePlayerPacket serverboundMovePlayerPacket = (ServerboundMovePlayerPacket)packet;
            if (this.o != 1) {
                stringArray = w;
                if (this.mode.is("Packet")) {
                    a a2 = new a(Stuck.mc.player.getYRot() + (float)(Math.random() - 0.5), Stuck.mc.player.getXRot());
                    ReflectionUtil.b(serverboundMovePlayerPacket, a2.f());
                    ReflectionUtil.a(serverboundMovePlayerPacket, a2.e());
                }
            }
            packetEvent.a(true);
        } else if (packetEvent.c() instanceof ServerboundPongPacket) {
            this.t.offer((ServerboundPongPacket)packetEvent.c());
            packetEvent.a(true);
        } else if (packetEvent.c() instanceof ServerboundUseItemPacket || packetEvent.c() instanceof ServerboundPlayerActionPacket) {
            this.p = packetEvent.c();
            this.o = 1;
            packetEvent.a(true);
        } else if (packetEvent.c() instanceof ClientboundPlayerPositionPacket && this.mode.is("Delay")) {
            while (!this.t.isEmpty()) {
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)((Packet)this.t.poll()));
            }
            this.o = 3;
            this.setEnabled(false);
        }
    }
}

