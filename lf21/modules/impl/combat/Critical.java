/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import com.mojang.blaze3d.platform.InputConstants;
import lf21.event.EventTarget;
import lf21.event.impl.AttackEntityEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.SprintEvent$Source;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.PacketUtil;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class Critical
extends Module {
    public final ModeSetting mode;
    private static final String[] s;
    private long p;
    private int q;
    public final NumberSetting hurtTime;
    private boolean o;
    public final BooleanSetting controlSprintKey;
    public final NumberSetting delay;
    private boolean r;
    public static Critical j;

    @EventTarget
    public void a(AttackEntityEvent attackEntityEvent) {
        String[] stringArray;
        block13: {
            block12: {
                if (Critical.mc.player == null || !(attackEntityEvent.a() instanceof LivingEntity)) break block12;
                stringArray = s;
                if (!this.mode.is("Stop Sprint")) break block13;
            }
            return;
        }
        stringArray = s;
        if (this.mode.is("Grim")) {
            if (!Critical.mc.player.onGround() || Critical.mc.player.isInWater() || Critical.mc.player.isInLava() || Critical.mc.player.onClimbable()) {
                return;
            }
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.StatusOnly(false));
            this.r = true;
            return;
        }
        long l2 = System.currentTimeMillis();
        if (l2 - this.p < ((Number)this.delay.getValue()).longValue()) {
            return;
        }
        if (!Critical.mc.player.onGround() || Critical.mc.player.isInWater() || Critical.mc.player.isInLava() || Critical.mc.player.onClimbable()) {
            return;
        }
        this.p = l2;
        stringArray = s;
        if (this.mode.is("Edit") || this.mode.is("Watchdog")) {
            stringArray = s;
            this.q = this.mode.is("Watchdog") ? 2 : 1;
            return;
        }
        stringArray = s;
        if (this.mode.is("Packet") || this.mode.is("Verus")) {
            double[] dArray;
            double[] dArray2;
            if (this.mode.is("Verus")) {
                double[] dArray3 = new double[4];
                dArray3[0] = 0.41999998688698;
                dArray3[1] = 0.33319999363422;
                dArray3[2] = 0.24813599859094;
                dArray2 = dArray3;
                dArray3[3] = 0.16477328182607;
            } else {
                double[] dArray4 = new double[4];
                dArray4[0] = 0.0625;
                dArray4[1] = 0.0;
                dArray4[2] = 1.0E-5;
                dArray2 = dArray4;
                dArray4[3] = 0.0;
            }
            for (double d2 : dArray = dArray2) {
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(Critical.mc.player.getX(), Critical.mc.player.getY() + d2, Critical.mc.player.getZ(), false));
            }
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
                    case 0 -> 58;
                    case 1 -> 103;
                    case 2 -> 102;
                    case 3 -> 79;
                    case 4 -> 69;
                    case 5 -> 126;
                    default -> 56;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[21];
                var3_1 = 0;
                var2_2 = "l\u0012\n,$\u0010\u0006j\u0006\u0005$ \n\u0006l\u0012\n,$\u0010\bm\u0006\u0012,-\u001aW]\tr\u0012\u0014;e*QW\u0002\u0005~\u0002\n.<\tt\bF\b7\u0011MT\u0003\u0012y\b\b;7\u0011T\u001a4\u0016=,\u0010L\u001a,\u00036\tt\bF\b7\u0011MT\u0003\u0005l\u0002\u0014:6\bm\u0006\u0012,-\u001aW]\u0006j\u0006\u0005$ \n\u0005l\u0002\u0014:6\u000bi\u0013\t?e-HH\u000e\b;\u0004}\u0015\u000f\"\by\u0015\u000f;,\u001dYV\u0004\u007f\u0003\u000f;\u0004w\b\u0002*\u000bi\u0013\t?e-HH\u000e\b;";
                var4_3 = "l\u0012\n,$\u0010\u0006j\u0006\u0005$ \n\u0006l\u0012\n,$\u0010\bm\u0006\u0012,-\u001aW]\tr\u0012\u0014;e*QW\u0002\u0005~\u0002\n.<\tt\bF\b7\u0011MT\u0003\u0012y\b\b;7\u0011T\u001a4\u0016=,\u0010L\u001a,\u00036\tt\bF\b7\u0011MT\u0003\u0005l\u0002\u0014:6\bm\u0006\u0012,-\u001aW]\u0006j\u0006\u0005$ \n\u0005l\u0002\u0014:6\u000bi\u0013\t?e-HH\u000e\b;\u0004}\u0015\u000f\"\by\u0015\u000f;,\u001dYV\u0004\u007f\u0003\u000f;\u0004w\b\u0002*\u000bi\u0013\t?e-HH\u000e\b;".length();
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
                    var5[var3_1++] = Critical.c(Critical.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "}\u0015\u000f\"\u0004\u007f\u0003\u000f;";
                    var4_3 = "}\u0015\u000f\"\u0004\u007f\u0003\u000f;".length();
                    var1_4 = 4;
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
                    var5[var3_1++] = Critical.c(Critical.a(v1));
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
        Critical.s = var5;
    }

    @EventTarget
    public void a(SprintEvent sprintEvent) {
        boolean bl;
        if (!this.mode.is("Stop Sprint")) {
            return;
        }
        if (this.o) {
            this.a();
        }
        if (Critical.mc.player == null || KillAura.j == null || !KillAura.j.isEnabled()) {
            return;
        }
        Entity entity = KillAura.k;
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity)entity;
        boolean bl2 = bl = Critical.mc.player.getDeltaMovement().y <= -0.08 && !Critical.mc.player.onGround() && !Critical.mc.player.isInWater() && !Critical.mc.player.isInLava() && !Critical.mc.player.onClimbable() && Critical.mc.player.isSprinting();
        if (bl && livingEntity.hurtTime <= ((Number)this.hurtTime.getValue()).intValue()) {
            if (((Boolean)this.controlSprintKey.getValue()).booleanValue()) {
                Critical.mc.options.keySprint.setDown(false);
                this.o = true;
            } else if (sprintEvent.a() == SprintEvent$Source.INPUT) {
                sprintEvent.a(false);
            }
        }
    }

    private void a() {
        if (!this.o || mc.getWindow() == null) {
            return;
        }
        boolean bl = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)Critical.mc.options.keySprint.getKey().getValue());
        Critical.mc.options.keySprint.setDown(bl);
        this.o = false;
    }

    @Override
    public void onDisable() {
        this.r = false;
        this.a();
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPre() || Critical.mc.player == null) {
            return;
        }
        String[] stringArray = s;
        if (this.mode.is("Grim")) {
            if (this.r && motionEvent.isOnGround()) {
                motionEvent.setOnGround(false);
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.StatusOnly(true));
                this.r = false;
                Critical.mc.player.jumpFromGround();
            }
            return;
        }
        stringArray = s;
        if (this.mode.is("No Ground")) {
            motionEvent.setOnGround(false);
        } else {
            stringArray = s;
            if (this.mode.is("Vulcan") && Critical.mc.player.onGround()) {
                motionEvent.setOnGround(false);
            } else if (this.q > 0) {
                motionEvent.setOnGround(false);
                if (this.mode.is("Watchdog")) {
                    motionEvent.setY(motionEvent.getY() + (this.q == 2 ? 1.0E-6 : 0.03125));
                }
                --this.q;
            }
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x38);
        }
        return cArray;
    }

    public Critical() {
        String[] stringArray = s;
        super("Critical", Category.COMBAT);
        this.controlSprintKey = new BooleanSetting("Control Sprint Key", true);
        this.hurtTime = new NumberSetting("Hurt Time", 2.0, 0.0, 10.0, 1.0);
        this.mode = new ModeSetting("Mode", "Stop Sprint", "Packet", "Edit", "No Ground", "Vulcan", "Watchdog", "Verus", "Grim").withDefault("Stop Sprint");
        this.delay = new NumberSetting("Delay", 500, 0, 1000, 50, () -> {
            String[] stringArray = s;
            return !this.mode.is("No Ground") && !this.mode.is("Vulcan");
        });
        j = this;
    }
}

