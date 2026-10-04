/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import java.util.concurrent.ThreadLocalRandom;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.PacketUtil;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public final class Timer
extends Module {
    public final NumberSetting maxTimer;
    public final NumberSetting compensateSpeed;
    public final BooleanSetting compensateLowTimer;
    public final NumberSetting minTimer;
    public static Timer j;
    public final NumberSetting balanceMultiplier;
    private static final String[] u;
    public final BooleanSetting watchdog;
    private double r;
    public final BooleanSetting randomize;
    private boolean s;
    private float t;

    @Override
    public void onDisable() {
        LF21Client.serverTickRate = 1.0f;
        this.t = 1.0f;
        this.s = false;
    }

    public float a() {
        return this.t;
    }

    @EventTarget
    public void a(c c2) {
        if (c2.a() instanceof ClientboundPlayerPositionPacket) {
            LF21Client.serverTickRate = 1.0f;
            this.t = 1.0f;
            this.s = false;
        }
    }

    @Override
    public void onEnable() {
        this.r = 0.0;
        this.s = false;
        this.t = 1.0f;
    }

    public Timer() {
        String[] stringArray = u;
        super("Timer", Category.MOVEMENT);
        this.minTimer = new NumberSetting("Min Timer", 1.0, 0.1, 20.0, 0.05);
        this.maxTimer = new NumberSetting("Max Timer", 2.0, 0.1, 20.0, 0.05);
        this.randomize = new BooleanSetting("Randomize", true);
        this.watchdog = new BooleanSetting("Watchdog", false);
        this.compensateLowTimer = new BooleanSetting("Compensate Low Timer", false);
        this.compensateSpeed = new NumberSetting("Compensate Speed", 1.5, 1.0, 5.0, 0.05, () -> (Boolean)this.compensateLowTimer.getValue());
        this.balanceMultiplier = new NumberSetting("Balance Multiplier", 1.0, 0.1, 10.0, 0.05, () -> (Boolean)this.compensateLowTimer.getValue());
        this.t = 1.0f;
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
                    case 0 -> 12;
                    case 1 -> 66;
                    case 2 -> 58;
                    case 3 -> 31;
                    case 4 -> 4;
                    case 5 -> 110;
                    default -> 30;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @EventTarget(a=2)
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPre() && ((Boolean)this.watchdog.getValue()).booleanValue()) {
            motionEvent.setOnGround(true);
        }
    }

    @EventTarget(a=4)
    public void a(TickEvent tickEvent) {
        float f2;
        if (Timer.mc.player == null || Timer.mc.level == null) {
            LF21Client.serverTickRate = 1.0f;
            return;
        }
        float f3 = Math.min(((Number)this.minTimer.getValue()).floatValue(), ((Number)this.maxTimer.getValue()).floatValue());
        float f4 = Math.max(((Number)this.minTimer.getValue()).floatValue(), ((Number)this.maxTimer.getValue()).floatValue());
        float f5 = f2 = (Boolean)this.randomize.getValue() != false ? (float)ThreadLocalRandom.current().nextDouble(f3, Math.nextUp((double)f4)) : f4;
        if (((Boolean)this.compensateLowTimer.getValue()).booleanValue()) {
            if (!this.s && f2 < 1.0f) {
                this.r += (1.0 - (double)f2) * ((Number)this.balanceMultiplier.getValue()).doubleValue();
            } else if (!this.s && f2 >= 1.0f && this.r > 0.0) {
                this.s = true;
            }
            if (this.s) {
                f2 = ((Number)this.compensateSpeed.getValue()).floatValue();
                this.r -= Math.max(0.05, (double)f2 - 1.0);
                if (this.r <= 0.0) {
                    this.r = 0.0;
                    this.s = false;
                }
            }
        }
        this.t = f2;
        LF21Client.serverTickRate = f2;
        if (((Boolean)this.watchdog.getValue()).booleanValue() && Timer.mc.player.onGround() && mc.getConnection() != null) {
            double d2 = Timer.mc.player.getX();
            double d3 = Timer.mc.player.getY();
            double d4 = Timer.mc.player.getZ();
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(d2, d3, d4, true));
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(d2, d3 - 1.0E-16, d4, true));
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(d2, d3 + 0.07, d4, true));
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(d2, d3, d4, true));
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[8];
                var3_1 = 0;
                var2_2 = "^#T{k\u0003wv'\tA#B?P\u0007si0\b[#N|l\nqk\u0012N#V~j\r{,\u000fOsp\u0007n`+_m\u0010O-Woa\u0000mm6_?W\u001e{i&\u0005X+Wzv";
                var4_3 = "^#T{k\u0003wv'\tA#B?P\u0007si0\b[#N|l\nqk\u0012N#V~j\r{,\u000fOsp\u0007n`+_m\u0010O-Woa\u0000mm6_?W\u001e{i&\u0005X+Wzv".length();
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
                    var5[var3_1++] = Timer.c(Timer.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "O-Woa\u0000mm6_?H\u0001i,\u0016Sra\u001c\tA+T?P\u0007si0";
                    var4_3 = "O-Woa\u0000mm6_?H\u0001i,\u0016Sra\u001c\tA+T?P\u0007si0".length();
                    var1_4 = 20;
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
                    var5[var3_1++] = Timer.c(Timer.a(v1));
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
        Timer.u = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x1E);
        }
        return cArray;
    }
}

