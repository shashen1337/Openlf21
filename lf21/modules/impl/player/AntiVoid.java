/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.Scaffold;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.PlayerUtil;
import lf21.utils.game.k;
import lf21.utils.misc.PacketUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

public class AntiVoid
extends Module {
    private final LinkedBlockingDeque<Packet<ServerGamePacketListener>> l;
    private boolean p;
    private boolean o;
    private final NumberSetting fallDistance;
    public static AntiVoid j;
    private boolean q;
    private static final String[] u;
    public boolean n;
    private int r;
    private boolean m;
    private int s;
    private boolean t;

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
                    case 0 -> 78;
                    case 1 -> 85;
                    case 2 -> 17;
                    case 3 -> 76;
                    case 4 -> 98;
                    case 5 -> 109;
                    default -> 41;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (this.p) {
            ++this.r;
            if (this.r > 10) {
                this.p = false;
                this.r = 0;
            }
        }
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (AntiVoid.mc.player == null || AntiVoid.mc.level == null || motionEvent.isPre()) {
            return;
        }
        int n2 = this.s = AntiVoid.mc.player.onGround() ? (this.s = this.s + 1) : 0;
        if (AntiVoid.mc.player.onGround()) {
            if (this.t && this.s > 6 && new k((Player)AntiVoid.mc.player).a(60) != null) {
                Scaffold.INSTANCE.setEnabled(false);
                this.t = false;
            }
            if (this.q) {
                if (Scaffold.INSTANCE.isEnabled()) {
                    this.t = true;
                }
                if (this.p) {
                    this.p = false;
                    AntiVoid.mc.options.keyJump.setDown(false);
                }
                this.a();
                this.q = false;
            }
            if (this.n || this.o) {
                this.a();
            }
            return;
        }
        if (!this.n && !this.o && !AntiVoid.mc.player.onGround() && AntiVoid.mc.player.getDeltaMovement().y < 0.0) {
            boolean bl;
            boolean bl2 = bl = AntiVoid.mc.player.getDeltaMovement().y < 0.1 && this.h() && !PlayerUtil.a(AntiVoid.mc.player.getY() + (double)AntiVoid.mc.player.getEyeHeight());
            if (bl) {
                this.m = Scaffold.INSTANCE.isEnabled();
                this.n = true;
                this.l.clear();
            }
        }
        if (!this.m && this.n && AntiVoid.mc.player.fallDistance > ((Number)this.fallDistance.getValue()).floatValue()) {
            if (this.q && !Scaffold.INSTANCE.isEnabled()) {
                Scaffold.INSTANCE.setEnabled(true);
                this.a();
            }
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerCommandPacket((Entity)AntiVoid.mc.player, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
            this.l.clear();
            this.n = false;
            this.o = true;
        }
    }

    public AntiVoid() {
        String[] stringArray = u;
        super("AntiVoid", Category.PLAYER);
        this.fallDistance = new NumberSetting("Fall Distance", 5.0, 1.0, 10.0, 0.5);
        this.l = new LinkedBlockingDeque();
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.s = 0;
        j = this;
    }

    @Override
    public void onDisable() {
        this.m = false;
        if (!this.l.isEmpty()) {
            this.l.forEach((Consumer<Packet<ServerGamePacketListener>>)((Consumer<Packet>)PacketUtil::sendQueued));
        }
        this.a();
    }

    @EventTarget
    public void a(c c2) {
        if (AntiVoid.mc.player == null) {
            return;
        }
        if (c2.a() instanceof ClientboundPlayerPositionPacket && (this.o || this.n)) {
            this.a();
            this.p = true;
            this.r = 0;
            this.q = true;
        }
    }

    @Override
    public void onEnable() {
        this.m = false;
        this.a();
        this.t = false;
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        if (AntiVoid.mc.player == null) {
            return;
        }
        if (this.n && packetEvent.c() instanceof ServerboundMovePlayerPacket) {
            packetEvent.a(true);
        }
    }

    private boolean h() {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        int n2 = 30;
        for (int i2 = 0; i2 < n2; ++i2) {
            mutableBlockPos.set(AntiVoid.mc.player.getX(), AntiVoid.mc.player.getY() - (double)i2, AntiVoid.mc.player.getZ());
            if (AntiVoid.mc.level.getBlockState((BlockPos)mutableBlockPos).getBlock() == Blocks.AIR) continue;
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u000f;e%4\u0002@*\r\b4} B)@=!p\"\u0001\b";
        int n3 = "\u000f;e%4\u0002@*\r\b4} B)@=!p\"\u0001\b".length();
        int n4 = 8;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = AntiVoid.c(AntiVoid.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                u = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @EventTarget
    public void a(StrafeEvent strafeEvent) {
        if (this.p) {
            AntiVoid.mc.options.keyJump.setDown(true);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x29);
        }
        return cArray;
    }

    private void a() {
        this.n = false;
        this.o = false;
        this.l.clear();
    }
}

