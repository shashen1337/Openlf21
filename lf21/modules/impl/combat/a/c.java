/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat.a;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RotationEvent;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.impl.combat.AntiKB;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.combat.a.b;
import lf21.modules.impl.combat.a.f;
import lf21.modules.impl.movement.NoSlow;
import lf21.modules.impl.movement.Scaffold;
import lf21.utils.game.g;
import lf21.utils.misc.ChatUtil;
import lf21.utils.rotation.a;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.protocol.game.ClientboundContainerClosePacket;
import net.minecraft.network.protocol.game.ClientboundDisconnectPacket;
import net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerChatPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class c
extends b {
    private boolean g;
    private int m;
    private long r;
    public static c c;
    private int j;
    private int k;
    private final Map<Entity, Vec3> e;
    private long n = -1L;
    private int l;
    private boolean h;
    private Entity o;
    private float q;
    private static final String[] s;
    private final Queue<Packet<ClientGamePacketListener>> d = new ConcurrentLinkedQueue<Packet<ClientGamePacketListener>>();
    private float p;
    private boolean i;
    private boolean f;

    private void f() {
        if (this.n != -1L && (System.currentTimeMillis() >= this.n || mc != null && lf21.modules.impl.combat.a.c.mc.options.keyUp.isDown())) {
            this.f = false;
            this.n = -1L;
        }
    }

    private void b(boolean bl) {
        if (bl && !this.d.isEmpty()) {
            this.k();
        } else if (!bl) {
            this.d.clear();
        }
        this.g = false;
        this.f = false;
        this.h = false;
        this.i = false;
        this.e.clear();
        this.n = -1L;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.o = null;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0L;
        AntiKB.k = null;
    }

    public f a(boolean bl) {
        if (bl) {
            return new f(1.0f, 0.55f, true);
        }
        long l2 = System.nanoTime();
        float f2 = this.r == 0L ? 0.016666668f : Math.min(0.1f, (float)(l2 - this.r) / 1.0E9f);
        this.r = l2;
        boolean bl2 = this.g || this.p > 0.001f;
        float f3 = bl2 ? 1.0f : 0.0f;
        float f4 = this.g ? Math.min(1.0f, (float)this.j / Math.max(1.0f, ((Number)AntiKB.j.L.getValue()).floatValue())) : 0.0f;
        this.q += (f3 - this.q) * (1.0f - (float)Math.exp(-9.75f * f2));
        float f5 = f4 > this.p ? 9.75f : 21.4f;
        this.p += (f4 - this.p) * (1.0f - (float)Math.exp(-f5 * f2));
        return new f(Math.max(0.0f, Math.min(1.0f, this.q)), Math.max(0.0f, Math.min(1.0f, this.p)), this.g);
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
                    case 0 -> 10;
                    case 1 -> 123;
                    case 2 -> 25;
                    case 3 -> 64;
                    case 4 -> 95;
                    case 5 -> 23;
                    default -> 48;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x30);
        }
        return cArray;
    }

    @Override
    public void a(DisconnectEvent disconnectEvent) {
        this.b(false);
    }

    @Override
    public void a(MotionEvent motionEvent) {
    }

    private void b(String string) {
        if (((Boolean)AntiKB.j.O.getValue()).booleanValue()) {
            ChatUtil.print("[LF21] " + string);
        }
    }

    @Override
    public void a(TickEvent tickEvent) {
        String[] stringArray;
        block14: {
            block15: {
                if (lf21.modules.impl.combat.a.c.mc.player == null) {
                    return;
                }
                this.f();
                this.o = this.h();
                this.i();
                if (this.h && ++this.k >= 5) {
                    this.h = false;
                }
                if (this.g() || this.l >= 3) {
                    this.b(true);
                    return;
                }
                if (!this.g) break block14;
                this.f = true;
                this.m = 0;
                if (this.j >= ((Number)AntiKB.j.L.getValue()).intValue()) {
                    this.b(true);
                    return;
                }
                ++this.j;
                if (!this.j() || !lf21.modules.impl.combat.a.c.mc.player.isSprinting()) break block14;
                if (lf21.modules.impl.combat.a.c.mc.player.onGround()) break block15;
                stringArray = s;
                if (!AntiKB.j.J.is("Air")) break block14;
            }
            this.m = ((Number)AntiKB.j.K.getValue()).intValue();
            this.l = 0;
            this.g = false;
            this.k();
        }
        if (this.m > 0) {
            if (!this.j()) {
                ++this.l;
                stringArray = s;
                this.b("target");
                return;
            }
            if (!lf21.modules.impl.combat.a.c.mc.player.isSprinting()) {
                this.b("sprint");
                return;
            }
            if (this.o != null && lf21.modules.impl.combat.a.c.mc.gameMode != null) {
                lf21.modules.impl.combat.a.c.mc.gameMode.attack((Player)lf21.modules.impl.combat.a.c.mc.player, this.o);
                lf21.modules.impl.combat.a.c.mc.player.swing(InteractionHand.MAIN_HAND);
                --this.m;
            }
        } else if (this.f && this.n == -1L) {
            this.n = System.currentTimeMillis() + 10L;
        }
    }

    private boolean g() {
        return lf21.modules.impl.combat.a.c.mc.player == null || lf21.modules.impl.combat.a.c.mc.player.isUsingItem() || this.i || mc.getConnection() == null || NoSlow.m() || this.o == null || Scaffold.INSTANCE != null && Scaffold.INSTANCE.isEnabled();
    }

    @Override
    public void a(Render2DEvent render2DEvent) {
    }

    public c() {
        super("EdNaven");
        this.e = new ConcurrentHashMap<Entity, Vec3>();
        c = this;
    }

    @Override
    public boolean e() {
        return this.g;
    }

    private void b(Packet<?> packet) {
        if (!((Boolean)AntiKB.j.N.getValue()).booleanValue()) {
            return;
        }
        for (Entity entity : lf21.modules.impl.combat.a.c.mc.level.entitiesForRendering()) {
            this.e.putIfAbsent(entity, entity.position());
        }
    }

    private void i() {
        AntiKB.k = (Boolean)AntiKB.j.P.getValue() != false && this.o != null && (this.g || this.m > 0) ? lf21.utils.game.g.c(this.o) : null;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "K\u0012k\u0006~\u001ak':c\u0007Q7_rnJ\u0010\u0007O\u001fW!)r^";
                var4_3 = "K\u0012k\u0006~\u001ak':c\u0007Q7_rnJ\u0010\u0007O\u001fW!)r^".length();
                var1_4 = 3;
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
                    var5[var3_1++] = lf21.modules.impl.combat.a.c.b(lf21.modules.impl.combat.a.c.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "O\u001fW!)r^\u0006y\u000bk)1c";
                    var4_3 = "O\u001fW!)r^\u0006y\u000bk)1c".length();
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
                    var5[var3_1++] = lf21.modules.impl.combat.a.c.b(lf21.modules.impl.combat.a.c.c(v1));
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
        lf21.modules.impl.combat.a.c.s = var5;
    }

    @Override
    public void a(SprintEvent sprintEvent) {
        this.f();
    }

    @Override
    public void a(GameTickEvent gameTickEvent) {
    }

    @Override
    public void a(RotationEvent rotationEvent) {
    }

    @Override
    public String d() {
        return "EdNaven";
    }

    @Override
    public void a(StrafeEvent strafeEvent) {
        if (lf21.modules.impl.combat.a.c.mc.player == null) {
            return;
        }
        if (this.f && ((Boolean)AntiKB.j.M.getValue()).booleanValue() && (!this.g || this.j())) {
            strafeEvent.a(1.0f);
            strafeEvent.b(0.0f);
        }
        if (this.h && lf21.modules.impl.combat.a.c.mc.player.isSprinting()) {
            strafeEvent.a(true);
            this.h = false;
        }
    }

    private void a(ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket) {
        if (lf21.modules.impl.combat.a.c.mc.player == null || clientboundSetEntityMotionPacket.getId() != lf21.modules.impl.combat.a.c.mc.player.getId()) {
            return;
        }
        if (((Boolean)AntiKB.j.P.getValue()).booleanValue()) {
            if (this.o == null) {
                float f2 = (float)Math.toDegrees(Math.atan2((double)clientboundSetEntityMotionPacket.getXa() / 8000.0, -((double)clientboundSetEntityMotionPacket.getZa() / 8000.0)));
                AntiKB.k = new a(f2, lf21.modules.impl.combat.a.c.mc.player.getXRot());
            }
            this.f = true;
        }
        this.h = true;
        this.k = 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean a(Packet<?> packet) {
        if (packet instanceof ClientboundSetEntityMotionPacket) return true;
        if (packet instanceof ClientboundSetHealthPacket) return true;
        if (packet instanceof ClientboundPlayerPositionPacket) return true;
        if (packet instanceof ClientboundSoundPacket) return true;
        if (packet instanceof ClientboundPlayerChatPacket) return true;
        if (packet instanceof ClientboundPlayerCombatKillPacket) return true;
        if (packet instanceof ClientboundContainerClosePacket) return true;
        if (packet instanceof ClientboundHurtAnimationPacket) return true;
        if (packet instanceof ClientboundSetTitleTextPacket) return true;
        if (packet instanceof ClientboundSetPlayerTeamPacket) return true;
        if (packet instanceof ClientboundSystemChatPacket) return true;
        if (packet instanceof ClientboundDisconnectPacket) return true;
        if (!(packet instanceof ClientboundAnimatePacket)) return false;
        ClientboundAnimatePacket clientboundAnimatePacket = (ClientboundAnimatePacket)packet;
        if (lf21.modules.impl.combat.a.c.mc.player == null) return false;
        if (clientboundAnimatePacket.getId() == lf21.modules.impl.combat.a.c.mc.player.getId()) return false;
        return true;
    }

    @Override
    public void c() {
        this.b(true);
    }

    private void k() {
        while (!this.d.isEmpty()) {
            Packet<ClientGamePacketListener> packet = this.d.poll();
            if (packet == null) continue;
            if (mc.getConnection() == null) {
                continue;
            }
            try {
                packet.handle((PacketListener)mc.getConnection());
                if (!(packet instanceof ClientboundSetEntityMotionPacket)) continue;
                ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet;
                this.a(clientboundSetEntityMotionPacket);
            }
            catch (Exception exception) {}
        }
    }

    @Override
    public void b() {
        this.b(true);
    }

    private Entity h() {
        LivingEntity livingEntity;
        EntityHitResult entityHitResult;
        if (KillAura.k != null && KillAura.k.isAlive()) {
            return KillAura.k;
        }
        HitResult hitResult = lf21.modules.impl.combat.a.c.mc.hitResult;
        if (hitResult instanceof EntityHitResult && (hitResult = (entityHitResult = (EntityHitResult)hitResult).getEntity()) instanceof LivingEntity && (livingEntity = (LivingEntity)hitResult).isAlive()) {
            return livingEntity;
        }
        return this.o != null && this.o.isAlive() ? this.o : null;
    }

    @Override
    public void a(PreMotionEvent preMotionEvent) {
    }

    private boolean j() {
        EntityHitResult entityHitResult;
        if (lf21.modules.impl.combat.a.c.mc.player == null || this.o == null) {
            return false;
        }
        HitResult hitResult = lf21.modules.impl.combat.a.c.mc.hitResult;
        return hitResult instanceof EntityHitResult && (entityHitResult = (EntityHitResult)hitResult).getEntity() == this.o;
    }

    @Override
    public void a(lf21.event.impl.c c2) {
        ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket;
        if (lf21.modules.impl.combat.a.c.mc.player == null || lf21.modules.impl.combat.a.c.mc.level == null) {
            return;
        }
        Packet<ClientGamePacketListener> packet = c2.a();
        this.o = this.h();
        if (packet instanceof ClientboundPlayerPositionPacket) {
            this.i = true;
            if (this.g) {
                this.k();
            }
            this.b(false);
            return;
        }
        if (this.g()) {
            this.b(true);
            return;
        }
        if (packet instanceof ClientboundSetEntityMotionPacket && (clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet).getId() == lf21.modules.impl.combat.a.c.mc.player.getId()) {
            if (!this.g) {
                this.g = true;
                this.j = 0;
            }
            c2.a(true);
            this.d.add(packet);
            this.i();
            return;
        }
        if (this.a(packet) || !this.g) {
            return;
        }
        this.b(packet);
        c2.a(true);
        this.d.add(packet);
    }
}

