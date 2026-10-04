/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.rotation;

import java.util.concurrent.ThreadLocalRandom;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.CameraPitchEvent;
import lf21.event.impl.FallFlyingEvent;
import lf21.event.impl.JumpMarkerEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.RayTraceEvent;
import lf21.event.impl.RotationAnimationEvent;
import lf21.event.impl.RotationEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.UseItemRayTraceEvent;
import lf21.event.impl.e;
import lf21.modules.impl.combat.AntiKB;
import lf21.modules.impl.combat.AutoThrow;
import lf21.modules.impl.combat.BowAim;
import lf21.modules.impl.combat.CrystalAura;
import lf21.modules.impl.combat.Displace;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.movement.FireballBlink;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.AntiTNT;
import lf21.modules.impl.player.AntiWeb;
import lf21.modules.impl.player.AutoMLG;
import lf21.modules.impl.player.AutoWeb;
import lf21.modules.impl.player.Helper;
import lf21.modules.impl.player.MidPearl;
import lf21.modules.impl.player.b_0;
import lf21.modules.impl.world.BlockIn;
import lf21.modules.impl.world.MusicBot;
import lf21.modules.impl.world.Nuker;
import lf21.utils.a.g;
import lf21.utils.misc.ReflectionUtil;
import lf21.utils.rotation.a;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class RotationHandler
extends ClientBase {
    public static a a;
    public static a b;
    public static a c;
    public static a d;
    public static boolean e;
    private static long f;
    private static long g;
    private static long h;
    private static a i;

    public static void a(a a2) {
        if (a2 == null) {
            a = null;
            return;
        }
        float f2 = (float)ThreadLocalRandom.current().nextDouble(-1.35, 1.35);
        float f3 = (float)ThreadLocalRandom.current().nextDouble(-0.65, 0.65);
        a = new a(a2.e() + f2, a2.f() + f3);
        ClientBase.yaw = a.e();
    }

    public static float a(float f2) {
        return f2 < 360.0f && f2 > -360.0f ? f2 + 720.0f : f2;
    }

    @EventTarget
    public void a(e e2) {
        b = null;
        a = null;
        f = 0L;
        g = 0L;
        h = 0L;
        i = null;
    }

    @EventTarget(a=0)
    public void a(TickEvent tickEvent) {
        lf21.utils.a.g.a();
    }

    @EventTarget(a=0)
    public void a(PacketEvent packetEvent) {
        Packet<?> packet;
        float f2;
        float f3;
        Packet<?> packet2 = packetEvent.c();
        if (packet2 instanceof ServerboundMovePlayerPacket && Float.floatToIntBits(f3 = RotationHandler.a(f2 = (packet = (Packet<?>)packet2).getYRot(0.0f))) != Float.floatToIntBits(f2)) {
            ReflectionUtil.a(packet, f3);
        }
        if ((packet = packetEvent.c()) instanceof ServerboundChatPacket) {
            ServerboundChatPacket serverboundChatPacket = (ServerboundChatPacket)packet;
            lf21.event.impl.a a2 = new lf21.event.impl.a(serverboundChatPacket.message());
            if (LF21Client.isReady()) {
                LF21Client.getInstance().getEventBus().call(a2);
                if (a2.isCancelled()) {
                    packetEvent.a(true);
                }
            }
        }
    }

    @EventTarget(a=4)
    public void b(TickEvent tickEvent) {
        if (RotationHandler.mc.player != null) {
            KillAura killAura = KillAura.j;
            Displace displace = Displace.INSTANCE;
            Scaffold scaffold = Scaffold.INSTANCE;
            CrystalAura crystalAura = CrystalAura.j;
            AutoMLG autoMLG = AutoMLG.j;
            FireballBlink fireballBlink = FireballBlink.j;
            AntiTNT antiTNT = AntiTNT.j;
            Helper helper = Helper.j;
            AntiWeb antiWeb = AntiWeb.j;
            AutoWeb autoWeb = AutoWeb.j;
            AutoThrow autoThrow = AutoThrow.j;
            BowAim bowAim = BowAim.k;
            AntiKB antiKB = AntiKB.j;
            MidPearl midPearl = MidPearl.j;
            Nuker nuker = Nuker.INSTANCE;
            BlockIn blockIn = BlockIn.j;
            MusicBot musicBot = MusicBot.I;
            e = true;
            if (displace != null && displace.isEnabled() && displace.h() && displace.a() != null) {
                RotationHandler.a(displace.a());
            } else if (autoMLG != null && autoMLG.isEnabled() && autoMLG.o != null) {
                RotationHandler.a(autoMLG.o);
                autoMLG.o = null;
            } else if (crystalAura != null && crystalAura.isEnabled() && CrystalAura.k != null) {
                RotationHandler.a(CrystalAura.k);
            } else if (fireballBlink != null && fireballBlink.isEnabled() && FireballBlink.k != null) {
                RotationHandler.a(FireballBlink.k);
            } else if (midPearl != null && midPearl.isEnabled() && MidPearl.k != null) {
                RotationHandler.a(MidPearl.k);
            } else if (antiTNT != null && antiTNT.isEnabled() && AntiTNT.p != null) {
                RotationHandler.a(AntiTNT.p);
            } else if (helper != null && helper.isEnabled() && helper.a() && Helper.k != null) {
                RotationHandler.a(Helper.k);
            } else if (antiWeb != null && antiWeb.isEnabled() && AntiWeb.l != b_0.IDLE && AntiWeb.k != null) {
                RotationHandler.a(AntiWeb.k);
            } else if (autoWeb != null && autoWeb.isEnabled() && AutoWeb.k != null) {
                RotationHandler.a(AutoWeb.k);
            } else if (autoThrow != null && autoThrow.isEnabled() && autoThrow.o != null) {
                RotationHandler.a(autoThrow.o);
            } else if (bowAim != null && bowAim.isEnabled() && bowAim.a() && bowAim.h() != null) {
                RotationHandler.a(bowAim.h());
            } else if (nuker != null && nuker.isEnabled() && nuker.p != null) {
                RotationHandler.a(nuker.p);
            } else if (blockIn != null && blockIn.isEnabled() && blockIn.v != null) {
                RotationHandler.a(blockIn.v);
            } else if (scaffold != null && scaffold.isEnabled() && scaffold.aj != null && (killAura == null || !killAura.m())) {
                RotationHandler.a(scaffold.aj);
            } else if (killAura != null && killAura.isEnabled() && KillAura.k != null && killAura.ai != null) {
                RotationHandler.a(new a(killAura.ai.e(), killAura.ai.f()));
            } else if (scaffold != null && scaffold.isEnabled() && scaffold.aj != null) {
                RotationHandler.a(scaffold.aj);
            } else if (antiKB != null && antiKB.isEnabled() && AntiKB.k != null) {
                RotationHandler.a(AntiKB.k);
            } else if (musicBot != null && musicBot.isEnabled() && musicBot.ag != null) {
                RotationHandler.a(musicBot.ag);
            } else {
                e = false;
            }
            RotationHandler.a(e);
        } else {
            e = false;
            f = 0L;
            g = 0L;
            h = 0L;
            i = null;
        }
    }

    private static void a(boolean bl) {
        if (!bl || mc == null || RotationHandler.mc.player == null) {
            f = 0L;
            g = 0L;
            h = 0L;
            i = null;
            return;
        }
        long l2 = System.nanoTime();
        if (f == 0L) {
            f = l2;
            g = l2 + RotationHandler.a();
        }
        if (h != 0L && l2 >= h) {
            h = 0L;
            i = null;
            g = l2 + RotationHandler.a();
        }
        if (h == 0L && l2 >= g) {
            float f2 = RotationHandler.mc.player.getYRot() + ThreadLocalRandom.current().nextFloat(-180.0f, 180.0f);
            float f3 = Math.max(-89.0f, Math.min(89.0f, RotationHandler.mc.player.getXRot() + ThreadLocalRandom.current().nextFloat(-35.0f, 35.0f)));
            i = new a(f2, f3);
            h = l2 + 2000000000L;
        }
        if (h != 0L && i != null) {
            a = i;
            e = true;
        }
    }

    private static long a() {
        return ThreadLocalRandom.current().nextLong(3000000000L, 5000000001L);
    }

    @EventTarget
    public void a(RotationAnimationEvent rotationAnimationEvent) {
        if (c != null && d != null && RotationHandler.mc.player != null && e) {
            rotationAnimationEvent.a(c.e());
            rotationAnimationEvent.b(d.e());
            rotationAnimationEvent.c(c.f());
            rotationAnimationEvent.d(d.f());
        }
    }

    @EventTarget
    public void a(CameraPitchEvent cameraPitchEvent) {
        if (c != null && d != null) {
            cameraPitchEvent.setPitch(c.f());
        }
    }

    @EventTarget(a=4)
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPost()) {
            if (RotationHandler.mc.player != null && RotationHandler.mc.player.tickCount <= 1 && LF21Client.isReady()) {
                LF21Client.getInstance().getEventBus().call(new e());
            }
            if (RotationHandler.mc.player == null) {
                return;
            }
            if (a == null || b == null) {
                a = b = new a(RotationHandler.mc.player.getYRot(), RotationHandler.mc.player.getXRot());
            }
            d = c;
            float f2 = a.e();
            float f3 = a.f();
            if (!Float.isNaN(f2) && !Float.isNaN(f3) && e) {
                motionEvent.setYaw(f2);
                motionEvent.setPitch(f3);
            }
            ClientBase.yaw = a.e();
            c = new a(motionEvent.getYaw(), motionEvent.getPitch());
            b = new a(motionEvent.getYaw(), motionEvent.getPitch());
        }
    }

    @EventTarget
    public void a(StrafeEvent strafeEvent) {
    }

    @EventTarget
    public void a(RayTraceEvent rayTraceEvent) {
        if (a != null && rayTraceEvent.a == RotationHandler.mc.player && e) {
            rayTraceEvent.a(a.e());
            rayTraceEvent.b(a.f());
        }
    }

    @EventTarget
    public void a(UseItemRayTraceEvent useItemRayTraceEvent) {
        if (a != null && e) {
            useItemRayTraceEvent.a(a.e());
            useItemRayTraceEvent.b(a.f());
        }
    }

    @EventTarget
    public void a(RotationEvent rotationEvent) {
        if (e && a != null) {
            rotationEvent.a(a.e());
        }
    }

    @EventTarget
    public void a(JumpMarkerEvent jumpMarkerEvent) {
        if (e && a != null) {
            jumpMarkerEvent.a(a.e());
        }
    }

    @EventTarget
    public void a(FallFlyingEvent fallFlyingEvent) {
        if (a != null) {
            fallFlyingEvent.a(a.f());
        }
    }

    static {
        e = false;
        f = 0L;
        g = 0L;
        h = 0L;
    }
}

