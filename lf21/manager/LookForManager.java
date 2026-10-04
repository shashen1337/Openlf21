/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

import java.util.Locale;
import java.util.Objects;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.e;
import lf21.utils.misc.ChatUtil;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public final class LookForManager
extends ClientBase {
    private String a;
    private boolean b;
    private boolean c;
    private boolean d;
    private static final String[] e;

    public LookForManager() {
        LF21Client.getInstance().getEventBus().register(this);
    }

    public boolean watch(String string) {
        this.a = string.trim();
        this.c = this.b = this.b();
        this.d = false;
        if (!this.b) {
            String[] stringArray = e;
            ChatUtil.print("\u76ee\u6807\u4e0d\u5b58\u5728\uff1a\u5f53\u524d\u670d\u52a1\u5668\u73a9\u5bb6\u5217\u8868\u4e2d\u6ca1\u6709 " + this.a);
            this.clear(false);
            return false;
        }
        ChatUtil.print("\u6b63\u5728\u67e5\u627e\u73a9\u5bb6: " + this.a);
        this.a();
        return true;
    }

    public void clear(boolean bl) {
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = false;
        if (bl) {
            ChatUtil.print("\u5df2\u6e05\u9664 LookFor \u76ee\u6807");
        }
    }

    public boolean isTarget(Entity entity) {
        Player player;
        return this.a != null && entity instanceof Player && (player = (Player)entity).getGameProfile().getName().equalsIgnoreCase(this.a);
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        if (this.a == null || LookForManager.mc.level == null || LookForManager.mc.player == null || mc.getConnection() == null) {
            return;
        }
        this.a();
        boolean bl = this.b();
        if (bl) {
            this.c = true;
        }
        if (this.c && this.b && !bl) {
            String[] stringArray = e;
            ChatUtil.print("\u73a9\u5bb6 " + this.a + " \u5df2\u9000\u51fa\u5f53\u524d\u670d\u52a1\u5668");
            this.b = false;
        } else {
            this.b = bl;
        }
    }

    @EventTarget
    public void a(e e2) {
        this.b = false;
        this.c = false;
        this.d = false;
    }

    private void a() {
        if (this.a == null || LookForManager.mc.level == null) {
            return;
        }
        boolean bl = false;
        for (Player player : LookForManager.mc.level.players()) {
            if (player == LookForManager.mc.player || !this.isTarget((Entity)player)) continue;
            bl = true;
            if (this.d) break;
            this.d = true;
            ChatUtil.print("\u5df2\u67e5\u627e\u5230\u76ee\u6807: " + player.getGameProfile().getName());
            break;
        }
        if (!bl) {
            this.d = false;
        }
    }

    private boolean b() {
        if (this.a == null || mc.getConnection() == null) {
            return false;
        }
        String string = this.a.toLowerCase(Locale.ROOT);
        for (PlayerInfo playerInfo : mc.getConnection().getOnlinePlayers()) {
            if (!Objects.equals(playerInfo.getProfile().getName().toLowerCase(Locale.ROOT), string)) continue;
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "\u6b1d\u5765\u6788\u6230\u73c4\u5b9bb^\t^\u5dbf\u906d\u51b4\u5f3e\u5260\u6755\u52df\u5625\u0013\u7690\u684a\u4e60\u5b16\u5745\uff37\u5f0b\u5233\u6740\u52cc\u5626\u73c4\u5b9b\u524f\u8816\u4e60\u6ccc\u6747M\u0003\u73d7\u5bfbM";
                var4_3 = "\u6b1d\u5765\u6788\u6230\u73c4\u5b9bb^\t^\u5dbf\u906d\u51b4\u5f3e\u5260\u6755\u52df\u5625\u0013\u7690\u684a\u4e60\u5b16\u5745\uff37\u5f0b\u5233\u6740\u52cc\u5626\u73c4\u5b9b\u524f\u8816\u4e60\u6ccc\u6747M\u0003\u73d7\u5bfbM".length();
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
                    var5[var3_1++] = LookForManager.b(LookForManager.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u5d8c\u67a8\u6213\u527e\u7683\u682ab^\u000e\u5d8c\u6e48\u9609n!B7\u0015\u000b\u0002<M\u76c3\u685f";
                    var4_3 = "\u5d8c\u67a8\u6213\u527e\u7683\u682ab^\u000e\u5d8c\u6e48\u9609n!B7\u0015\u000b\u0002<M\u76c3\u685f".length();
                    var1_4 = 8;
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
                    var5[var3_1++] = LookForManager.b(LookForManager.a(v1));
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
        LookForManager.e = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x58);
        }
        return cArray;
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
                    case 0 -> 126;
                    case 1 -> 77;
                    case 2 -> 109;
                    case 3 -> 78;
                    case 4 -> 109;
                    case 5 -> 45;
                    default -> 88;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

