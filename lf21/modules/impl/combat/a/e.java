/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat.a;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.concurrent.LinkedBlockingDeque;
import lf21.event.EventTarget;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.RotationEvent;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.StuckInBlockEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.impl.combat.AntiKB;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.combat.a.b;
import lf21.modules.impl.movement.Scaffold;
import lf21.utils.misc.ChatUtil;
import lf21.utils.rotation.a;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPingPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;

public class e
extends b {
    private int h = 0;
    private boolean c = false;
    private int i = 0;
    private ClientboundSetEntityMotionPacket j;
    private int k = -1;
    private int g = 0;
    private final LinkedBlockingDeque<Packet<ClientGamePacketListener>> f = new LinkedBlockingDeque();
    private boolean d = false;
    private int l = 0;
    private boolean e = false;

    public e() {
        super("Mix");
    }

    @Override
    public void a(c c2) {
        LocalPlayer localPlayer = lf21.modules.impl.combat.a.e.mc.player;
        if (localPlayer == null) {
            return;
        }
        Packet<ClientGamePacketListener> packet = c2.a();
        if (this.h <= 0 || lf21.modules.impl.combat.a.e.mc.player.isInWater() || lf21.modules.impl.combat.a.e.mc.player.isUnderWater()) {
            if (packet instanceof ClientboundSetEntityMotionPacket) {
                ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet;
                if (clientboundSetEntityMotionPacket.getId() != localPlayer.getId()) {
                    return;
                }
                AntiKB.k = null;
                this.j = null;
                if (clientboundSetEntityMotionPacket.getYa() > 0) {
                    this.k = 0;
                    if (KillAura.j.isEnabled() && KillAura.k != null && ((Boolean)AntiKB.j.tryAttack.getValue()).booleanValue()) {
                        this.c = true;
                        this.d = lf21.modules.impl.combat.a.e.mc.player.isSprinting();
                    } else if (((Boolean)AntiKB.j.rotate.getValue()).booleanValue()) {
                        float f2 = (float)((double)clientboundSetEntityMotionPacket.getXa() / 8000.0);
                        float f3 = (float)((double)clientboundSetEntityMotionPacket.getZa() / 8000.0);
                        float f4 = (float)Math.toDegrees(Math.atan2(f2, -f3));
                        AntiKB.k = new a(f4, localPlayer.getXRot());
                    }
                    c2.a(true);
                    this.f.add(c2.a());
                    this.e = true;
                    this.j = clientboundSetEntityMotionPacket;
                }
            }
            if (this.e) {
                if (packet instanceof ClientboundMoveEntityPacket || packet instanceof ClientboundPingPacket || packet instanceof ClientboundTeleportEntityPacket) {
                    this.f.add(c2.a());
                    c2.a(true);
                }
                if (c2.a() instanceof ClientboundPlayerPositionPacket) {
                    ChatUtil.print("?");
                    this.h();
                }
            }
        } else {
            AntiKB.k = null;
            this.j = null;
            this.i();
            this.e = false;
            ChatUtil.print("Ignore: Player in web or liquid!");
        }
    }

    @Override
    public void a(GameTickEvent gameTickEvent) {
        if (this.j != null && !((Boolean)AntiKB.j.movementOverride.getValue()).booleanValue() && lf21.modules.impl.combat.a.e.mc.player != null) {
            if (lf21.modules.impl.combat.a.e.mc.player.hurtTime > 6 && !lf21.modules.impl.combat.a.e.mc.options.keyJump.isDown()) {
                lf21.modules.impl.combat.a.e.mc.options.keyJump.setDown(true);
            } else if (!Scaffold.INSTANCE.isEnabled()) {
                boolean bl = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)lf21.modules.impl.combat.a.e.mc.options.keyJump.getKey().getValue());
                lf21.modules.impl.combat.a.e.mc.options.keyJump.setDown(bl);
            }
        }
    }

    @EventTarget
    public void a(StuckInBlockEvent stuckInBlockEvent) {
        if (stuckInBlockEvent.a().getBlock() == Blocks.COBWEB) {
            ++this.h;
        }
    }

    @Override
    public void a(StrafeEvent strafeEvent) {
    }

    private void i() {
        while (!this.f.isEmpty()) {
            try {
                this.f.poll().handle((PacketListener)mc.getConnection());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void a(TickEvent tickEvent) {
        if (lf21.modules.impl.combat.a.e.mc.player == null) {
            return;
        }
        this.i = lf21.modules.impl.combat.a.e.mc.player.onGround() ? 0 : (this.i = this.i + 1);
    }

    @Override
    public String d() {
        return "";
    }

    @Override
    public void a(MotionEvent motionEvent) {
        if (lf21.modules.impl.combat.a.e.mc.player == null) {
            return;
        }
        if (this.g < lf21.modules.impl.combat.a.e.mc.player.tickCount) {
            this.h = 0;
        }
        if (motionEvent.isPre() && (lf21.modules.impl.combat.a.e.mc.player.onGround() || this.i >= 24)) {
            this.i();
            this.e = false;
            this.c = false;
        }
    }

    @Override
    public void a(DisconnectEvent disconnectEvent) {
        this.h();
    }

    @Override
    public boolean e() {
        return this.e;
    }

    private void g() {
        lf21.modules.impl.combat.a.e.mc.options.keyUp.setDown(InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)lf21.modules.impl.combat.a.e.mc.options.keyUp.getKey().getValue()));
        lf21.modules.impl.combat.a.e.mc.options.keyDown.setDown(InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)lf21.modules.impl.combat.a.e.mc.options.keyDown.getKey().getValue()));
        lf21.modules.impl.combat.a.e.mc.options.keyLeft.setDown(InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)lf21.modules.impl.combat.a.e.mc.options.keyLeft.getKey().getValue()));
        lf21.modules.impl.combat.a.e.mc.options.keyRight.setDown(InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)lf21.modules.impl.combat.a.e.mc.options.keyRight.getKey().getValue()));
    }

    @Override
    public void a(PreMotionEvent preMotionEvent) {
    }

    @Override
    public void a(SprintEvent sprintEvent) {
        if (lf21.modules.impl.combat.a.e.mc.player == null) {
            return;
        }
        if (!this.e) {
            AntiKB.k = null;
        }
        if (this.j != null && this.j.getYa() > 0 && lf21.modules.impl.combat.a.e.mc.player.hurtTime > 0 && KillAura.j.isEnabled() && KillAura.k != null && this.c) {
            this.c = false;
            for (int i2 = 0; i2 < 2; ++i2) {
                lf21.modules.impl.combat.a.e.mc.player.setSprinting(false);
                if (((Boolean)KillAura.j.keepSprint.getValue()).booleanValue() && this.d) {
                    lf21.modules.impl.combat.a.e.mc.player.setDeltaMovement(lf21.modules.impl.combat.a.e.mc.player.getDeltaMovement().multiply((double)0.6f, 1.0, (double)0.6f));
                }
                KillAura.j.a();
            }
        }
        if (this.k >= 0) {
            ++this.k;
        }
        if (((Boolean)AntiKB.j.movementOverride.getValue()).booleanValue() && this.j != null) {
            if (this.k >= 1) {
                if (this.k <= 2 && lf21.modules.impl.combat.a.e.mc.player.onGround()) {
                    lf21.modules.impl.combat.a.e.mc.options.keyJump.setDown(true);
                }
                if (this.k <= 3) {
                    this.f();
                    this.l = 1;
                }
            }
            if (this.k >= 4 && this.k <= 10) {
                lf21.modules.impl.combat.a.e.mc.options.keyJump.setDown(false);
                if (this.l == 1) {
                    this.g();
                    this.l = 0;
                }
            }
            if (this.k >= 10) {
                this.k = -1;
            }
        }
    }

    @Override
    public void a(RotationEvent rotationEvent) {
    }

    private void f() {
        float f2 = (float)((double)this.j.getXa() / 8000.0);
        float f3 = (float)((double)this.j.getZa() / 8000.0);
        float f4 = (float)Math.toDegrees(Math.atan2(f2, -f3));
        float f5 = Mth.wrapDegrees((float)(f4 - lf21.modules.impl.combat.a.e.mc.player.getYRot()));
        this.g();
        double d2 = Math.toRadians(f5);
        double d3 = Math.sin(d2);
        double d4 = Math.cos(d2);
        if (d4 > 0.5) {
            lf21.modules.impl.combat.a.e.mc.options.keyUp.setDown(true);
        } else if (d4 < -0.5) {
            lf21.modules.impl.combat.a.e.mc.options.keyDown.setDown(true);
        }
        if (d3 > 0.5) {
            lf21.modules.impl.combat.a.e.mc.options.keyRight.setDown(true);
        } else if (d3 < -0.5) {
            lf21.modules.impl.combat.a.e.mc.options.keyLeft.setDown(true);
        }
    }

    @Override
    public void c() {
        this.h();
    }

    private void h() {
        this.j = null;
        this.g = 0;
        this.h = 0;
        this.e = false;
        this.i();
        AntiKB.k = null;
        this.g();
    }

    @Override
    public void b() {
        this.h();
    }
}

