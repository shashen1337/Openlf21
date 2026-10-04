/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import lf21.event.EventTarget;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.ChatUtil;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

public class AutoPlay
extends Module {
    public static AutoPlay j;
    private final NumberSetting delay;
    public boolean m;
    public long l;
    private static final String[] o;
    public long n;

    @EventTarget
    public void a(DisconnectEvent disconnectEvent) {
        this.m = false;
        this.l = -1L;
        this.n = -1L;
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
                    case 0 -> 88;
                    case 1 -> 74;
                    case 2 -> 25;
                    case 3 -> 118;
                    case 4 -> 62;
                    case 5 -> 95;
                    default -> 85;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public NumberSetting a() {
        return this.delay;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "\u0019?m\u0019n34!\r\u00ff\u0011)[\u0007>x>!4\u0019L\u0002\f\u6e60\u6245\u5c1f\u575e\u001enu\u798aj\u5417\u5f76\u59f5\u00059-x\u001fP";
                var4_3 = "\u0019?m\u0019n34!\r\u00ff\u0011)[\u0007>x>!4\u0019L\u0002\f\u6e60\u6245\u5c1f\u575e\u001enu\u798aj\u5417\u5f76\u59f5\u00059-x\u001fP".length();
                var1_4 = 8;
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
                    var5[var3_1++] = AutoPlay.c(AutoPlay.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u5768\u56b4\u8bdd\u5270\u0005\u001c/u\u0017G";
                    var4_3 = "\u5768\u56b4\u8bdd\u5270\u0005\u001c/u\u0017G".length();
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
                    var5[var3_1++] = AutoPlay.c(AutoPlay.a(v1));
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
        AutoPlay.o = var5;
    }

    @Override
    protected void onDisable() {
        this.m = false;
        this.l = -1L;
        this.n = -1L;
        super.onDisable();
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (AutoPlay.mc.player == null || AutoPlay.mc.level == null) {
            return;
        }
        if (this.n != -1L) {
            if (System.currentTimeMillis() - this.n > 1000L) {
                this.l = -1L;
                this.m = false;
                this.n = -1L;
            }
            return;
        }
        if (this.l != -1L) {
            long l2;
            long l3 = System.currentTimeMillis() - this.l;
            if ((double)l2 >= ((Number)this.delay.getValue()).doubleValue() * 1000.0) {
                AutoPlay.mc.player.connection.sendCommand("again");
                this.n = System.currentTimeMillis();
            }
        }
    }

    @Override
    protected void onEnable() {
        this.m = false;
        this.l = -1L;
        this.n = -1L;
        super.onEnable();
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        if (AutoPlay.mc.player == null || AutoPlay.mc.level == null) {
            return;
        }
        Object object = packetEvent.c();
        if (object instanceof ClientboundSystemChatPacket) {
            ClientboundSystemChatPacket clientboundSystemChatPacket = (ClientboundSystemChatPacket)object;
            String[] stringArray = o;
            object = clientboundSystemChatPacket.content().getString().replaceAll("\u00a7[0-9a-fk-or]", "").trim();
            if (((String)object).contains("\u5730\u56fe\u8bc4\u5206")) {
                ChatUtil.print("1");
                if (this.l == -1L) {
                    this.l = System.currentTimeMillis();
                    this.m = true;
                }
            } else if (((String)object).contains("\u6e38\u620f\u5c06\u5728 1 \u79d2 \u540e\u5f00\u59cb")) {
                ChatUtil.print("2");
                this.l = -1L;
                this.m = false;
            }
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x55);
        }
        return cArray;
    }

    public AutoPlay() {
        String[] stringArray = o;
        super("AutoPlay", Category.WORLD);
        this.delay = new NumberSetting("Delay", 2.0, 0.0, 10.0, 0.1);
        this.l = -1L;
        this.m = false;
        this.n = -1L;
        j = this;
    }
}

