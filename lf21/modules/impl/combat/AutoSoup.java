/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import lf21.event.EventTarget;
import lf21.event.impl.SprintEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.h;
import lf21.utils.game.PlayerUtil;
import lf21.utils.game.d;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class AutoSoup
extends Module {
    private final NumberSetting switchDelay;
    private static final String[] s;
    private final h p;
    private int q;
    private final NumberSetting delay;
    private final h o;
    private final NumberSetting health;
    private final BooleanSetting drop;
    public boolean r;
    public static AutoSoup j;

    public AutoSoup() {
        String[] stringArray = s;
        super("AutoSoup", Category.COMBAT);
        this.health = new NumberSetting("Health", 15, 0, 20, 1);
        this.delay = new NumberSetting("Delay", 300, 0, 1000, 1);
        this.switchDelay = new NumberSetting("Switch Delay", 100, 0, 1000, 1);
        this.drop = new BooleanSetting("Drop", true);
        this.o = new h();
        this.p = new h();
        this.q = -1;
        j = this;
    }

    @Override
    protected void onDisable() {
        this.q = -1;
        this.r = false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "{az:\u0010^\u0004wvt&\brqo97Y;C";
                var4_3 = "{az:\u0010^\u0004wvt&\brqo97Y;C".length();
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
                    var5[var3_1++] = AutoSoup.c(AutoSoup.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "waw7\u001d\f`sr\"\u0007^nwaw7\u001d";
                    var4_3 = "waw7\u001d\f`sr\"\u0007^nwaw7\u001d".length();
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
                    var5[var3_1++] = AutoSoup.c(AutoSoup.a(v1));
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
        AutoSoup.s = var5;
    }

    @EventTarget
    public void a(SprintEvent sprintEvent) {
        if (AutoSoup.mc.player == null || AutoSoup.mc.level == null || AutoSoup.mc.screen != null) {
            return;
        }
        if (this.o.c(((Number)this.switchDelay.getValue()).longValue()) && this.q != -1) {
            AutoSoup.mc.player.getInventory().selected = this.q;
            PlayerUtil.a();
            this.q = -1;
            this.p.a();
        }
        if (!this.p.c(((Number)this.delay.getValue()).longValue())) {
            return;
        }
        int n2 = d.a(0, 9, Items.MUSHROOM_STEW);
        if (AutoSoup.mc.player.getHealth() <= ((Number)this.health.getValue()).floatValue() && n2 != -1) {
            boolean bl;
            boolean bl2 = bl = AutoSoup.mc.player.getInventory().selected == n2;
            if (!bl) {
                this.q = AutoSoup.mc.player.getInventory().selected;
                AutoSoup.mc.player.getInventory().selected = n2;
                PlayerUtil.a();
                this.o.a();
            }
            AutoSoup.mc.gameMode.useItem((Player)AutoSoup.mc.player, InteractionHand.MAIN_HAND);
            this.r = true;
            if (((Boolean)this.drop.getValue()).booleanValue()) {
                AutoSoup.mc.player.drop(true);
            }
            this.p.a();
        } else {
            this.r = false;
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
                    case 0 -> 51;
                    case 1 -> 4;
                    case 2 -> 27;
                    case 3 -> 86;
                    case 4 -> 100;
                    case 5 -> 54;
                    default -> 78;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x4E);
        }
        return cArray;
    }
}

