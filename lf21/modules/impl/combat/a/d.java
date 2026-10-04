/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat.a;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.concurrent.LinkedBlockingDeque;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.RotationEvent;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.impl.combat.AntiKB;
import lf21.modules.impl.combat.Backtrack;
import lf21.modules.impl.combat.a.a;
import lf21.modules.impl.combat.a.b;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.NoFall;
import lf21.utils.rotation.RotationHandler;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

public class d
extends b {
    private boolean h = false;
    public static volatile boolean c;
    private int i = 0;
    private a j;
    private static final String[] m;
    private int k = 0;
    private Packet<ClientGamePacketListener> g;
    private final LinkedBlockingDeque<Packet<ClientGamePacketListener>> f = new LinkedBlockingDeque();
    private ClientboundSetEntityMotionPacket d;
    private lf21.utils.rotation.a l = null;
    private int e = 0;

    private void f() {
        this.h = false;
        this.i = 0;
        this.g = null;
        this.f.clear();
        c = false;
        this.j = lf21.modules.impl.combat.a.a.IDLE;
        this.k = 0;
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
                    case 1 -> 41;
                    case 2 -> 30;
                    case 3 -> 73;
                    case 4 -> 84;
                    case 5 -> 99;
                    default -> 7;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean g() {
        return NoFall.INSTANCE != null && NoFall.INSTANCE.isEnabled() && (NoFall.INSTANCE.r || NoFall.INSTANCE.t);
    }

    @Override
    public void a(RotationEvent rotationEvent) {
    }

    @Override
    public void a(SprintEvent sprintEvent) {
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "@\\s9t1byLj\n@\\s9t1byLj";
                var4_3 = "@\\s9t1byLj\n@\\s9t1byLj".length();
                var1_4 = 10;
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
                    var5[var3_1++] = lf21.modules.impl.combat.a.d.b(lf21.modules.impl.combat.a.d.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "G@f\u0003G@f";
                    var4_3 = "G@f\u0003G@f".length();
                    var1_4 = 3;
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
                    var5[var3_1++] = lf21.modules.impl.combat.a.d.b(lf21.modules.impl.combat.a.d.b(v1));
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
        lf21.modules.impl.combat.a.d.m = var5;
        lf21.modules.impl.combat.a.d.c = false;
    }

    @Override
    public void a(GameTickEvent gameTickEvent) {
        LocalPlayer localPlayer = lf21.modules.impl.combat.a.d.mc.player;
        if (localPlayer == null) {
            return;
        }
        String[] stringArray = m;
        if (!AntiKB.l.is("Jump Reset") && !AntiKB.l.is(stringArray[3])) {
            return;
        }
        if (this.i()) {
            if (this.h) {
                this.a(false);
            }
            AntiKB.k = null;
            this.e = 0;
            this.f();
            return;
        }
        if (this.h && this.j == lf21.modules.impl.combat.a.a.GROUND) {
            if (!Scaffold.INSTANCE.isEnabled()) {
                boolean bl = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)lf21.modules.impl.combat.a.d.mc.options.keyJump.getKey().getValue());
                lf21.modules.impl.combat.a.d.mc.options.keyJump.setDown(bl);
            }
            return;
        }
        if (this.k > 0 && !Scaffold.INSTANCE.isEnabled()) {
            lf21.modules.impl.combat.a.d.mc.options.keyJump.setDown(true);
            --this.k;
            return;
        }
        if (!Scaffold.INSTANCE.isEnabled()) {
            boolean bl = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)lf21.modules.impl.combat.a.d.mc.options.keyJump.getKey().getValue());
            lf21.modules.impl.combat.a.d.mc.options.keyJump.setDown(bl);
        }
    }

    public d() {
        super("Jump Reset");
        this.j = lf21.modules.impl.combat.a.a.IDLE;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(boolean bl) {
        Packet<ClientGamePacketListener> packet;
        ClientPacketListener clientPacketListener = mc.getConnection();
        if (clientPacketListener == null) {
            this.f.clear();
            return;
        }
        if (!this.f.isEmpty() && bl && this.f.getFirst() == this.g) {
            this.f.pollFirst();
        }
        while ((packet = this.f.poll()) != null) {
            try {
                packet.handle((PacketListener)clientPacketListener);
            }
            catch (Exception exception) {
                this.f.clear();
                return;
            }
        }
    }

    @Override
    public String d() {
        return "Jump Reset";
    }

    @Override
    public void b() {
        this.d = null;
        AntiKB.k = null;
        this.e = 0;
        this.f();
        c = false;
    }

    @Override
    public void c() {
        this.b();
    }

    @Override
    public void a(DisconnectEvent disconnectEvent) {
        AntiKB.k = null;
        this.d = null;
        this.e = 0;
        this.f();
    }

    @Override
    public void a(StrafeEvent strafeEvent) {
        LocalPlayer localPlayer = lf21.modules.impl.combat.a.d.mc.player;
        if (localPlayer == null) {
            return;
        }
        if (this.i()) {
            if (this.h) {
                this.a(false);
            }
            AntiKB.k = null;
            this.e = 0;
            this.f();
            return;
        }
        String[] stringArray = m;
        if ((AntiKB.l.is("Jump Reset") || AntiKB.l.is(stringArray[3])) && ((Boolean)AntiKB.j.followDirection.getValue()).booleanValue() && AntiKB.k != null) {
            strafeEvent.a(1.0f);
            strafeEvent.b(0.0f);
        }
    }

    private boolean i() {
        return this.g() || this.h();
    }

    @Override
    public void a(PreMotionEvent preMotionEvent) {
    }

    private boolean h() {
        return Backtrack.j != null && Backtrack.j.isEnabled() && (Backtrack.j.i() || Backtrack.j.j());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(TickEvent tickEvent) {
        LocalPlayer localPlayer;
        block32: {
            block33: {
                localPlayer = lf21.modules.impl.combat.a.d.mc.player;
                if (localPlayer == null) {
                    return;
                }
                if (!AntiKB.l.is("Jump Reset") && !AntiKB.l.is(m[2])) {
                    return;
                }
                if (this.i()) {
                    if (this.h) {
                        this.a(false);
                    }
                    AntiKB.k = null;
                    this.e = 0;
                    this.f();
                    return;
                }
                if (!this.h) break block32;
                if (this.j != lf21.modules.impl.combat.a.a.AIR) break block33;
                if (localPlayer.onGround()) {
                    this.a(false);
                    this.f();
                    break block32;
                } else if (this.i > 0) {
                    --this.i;
                    break block32;
                } else {
                    this.a(false);
                    this.f();
                }
                break block32;
            }
            if (this.j == lf21.modules.impl.combat.a.a.GROUND) {
                if (this.i > 0) {
                    --this.i;
                } else {
                    this.a(false);
                    if (this.l != null) {
                        AntiKB.k = this.l;
                        this.e = 0;
                        try {
                            RotationHandler.a(this.l);
                            RotationHandler.e = true;
                        }
                        catch (Throwable throwable) {
                            // empty catch block
                        }
                        this.l = null;
                    }
                    this.f();
                    this.k = 1;
                }
            }
        }
        if (AntiKB.k != null) {
            ++this.e;
        }
        boolean bl2 = localPlayer.hurtTime == 0 || this.e > ((Number)AntiKB.j.rotateTicks.getValue()).intValue() || (Boolean)AntiKB.j.rotate.getValue() == false && (Boolean)AntiKB.j.followDirection.getValue() == false;
        if (!bl2) return;
        AntiKB.k = null;
        this.d = null;
        this.e = 0;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(c c2) {
        LocalPlayer localPlayer = lf21.modules.impl.combat.a.d.mc.player;
        if (localPlayer == null) return;
        if (!AntiKB.l.is("Jump Reset")) {
            return;
        }
        if (this.i()) {
            if (this.h) {
                this.a(false);
            }
            AntiKB.k = null;
            this.e = 0;
            this.f();
            return;
        }
        Packet<ClientGamePacketListener> packet = c2.a();
        if (this.h && !(packet instanceof ClientboundSystemChatPacket) && !(packet instanceof ClientboundSetTimePacket)) {
            c2.a(true);
            this.f.add(packet);
            return;
        }
        if (!(packet instanceof ClientboundSetEntityMotionPacket)) return;
        ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet;
        if (clientboundSetEntityMotionPacket.getId() != localPlayer.getId()) {
            return;
        }
        this.d = clientboundSetEntityMotionPacket;
        boolean bl = (Boolean)AntiKB.j.rotate.getValue() != false || (Boolean)AntiKB.j.followDirection.getValue() != false;
        lf21.utils.rotation.a a2 = null;
        if (bl) {
            float f2 = (float)((double)this.d.getXa() / 8000.0);
            float f3 = (float)((double)this.d.getZa() / 8000.0);
            float f4 = (float)Math.toDegrees(Math.atan2(f2, -f3));
            a2 = new lf21.utils.rotation.a(f4, localPlayer.getXRot());
        }
        if (localPlayer.onGround()) {
            this.l = a2;
            this.h = true;
            this.j = lf21.modules.impl.combat.a.a.GROUND;
            this.i = 10;
            c = true;
            this.g = packet;
            this.f.add(packet);
            c2.a(true);
            return;
        }
        if (a2 != null) {
            AntiKB.k = a2;
            this.e = 0;
            try {
                RotationHandler.a(a2);
                RotationHandler.e = true;
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        this.h = true;
        this.j = lf21.modules.impl.combat.a.a.AIR;
        this.i = 20;
        c = true;
        this.g = packet;
        this.f.add(packet);
        c2.a(true);
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 7);
        }
        return cArray;
    }

    @Override
    public void a(MotionEvent motionEvent) {
    }
}

