/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.util.Objects;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.ModeSetting;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class Teams
extends Module {
    private static final String[] l;
    public static Teams j;
    public static ModeSetting k;

    public Teams() {
        super("Teams", Category.WORLD);
        j = this;
    }

    public static boolean a(Entity entity) {
        if (!j.isEnabled()) {
            return false;
        }
        if (entity instanceof Player) {
            if (k.is("Color")) {
                Integer n2 = entity.getTeamColor();
                Integer n3 = Teams.mc.player.getTeamColor();
                return n2.equals(n3);
            }
            String string = Teams.b(entity);
            String string2 = Teams.b((Entity)Teams.mc.player);
            return Objects.equals(string, string2);
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
                var2_2 = "\\&?Y^\u0005K,2[_\u0004E,:Q\n[ 1FHmKi1:";
                var4_3 = "\\&?Y^\u0005K,2[_\u0004E,:Q\n[ 1FHmKi1:".length();
                var1_4 = 5;
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
                    var5[var3_1++] = Teams.c(Teams.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "K,2[_\n[ 1FHmKi1:";
                    var4_3 = "K,2[_\n[ 1FHmKi1:".length();
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
                    var5[var3_1++] = Teams.c(Teams.a(v1));
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
        Teams.l = var5;
        var0_6 = Teams.l;
        Teams.k = new ModeSetting("Mode", new String[]{"Color", "Scoreboard"}).withDefault("Scoreboard");
    }

    public static String b(Entity entity) {
        PlayerInfo playerInfo = mc.getConnection().getPlayerInfo(entity.getUUID());
        if (playerInfo == null) {
            return null;
        }
        if (playerInfo.getTeam() != null) {
            return playerInfo.getTeam().getName();
        }
        return null;
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
                    case 0 -> 8;
                    case 1 -> 67;
                    case 2 -> 94;
                    case 3 -> 52;
                    case 4 -> 45;
                    case 5 -> 15;
                    default -> 36;
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
            cArray[0] = (char)(cArray[0] ^ 0x24);
        }
        return cArray;
    }
}

