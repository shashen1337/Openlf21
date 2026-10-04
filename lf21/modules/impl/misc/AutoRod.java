/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.misc;

import lf21.event.EventTarget;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.player.MidPearl;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.PlayerUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

public class AutoRod
extends Module {
    private boolean n;
    private static final String[] q;
    private int o;
    private final NumberSetting delay;
    public static AutoRod j;
    private final ModeSetting button;
    private boolean m;
    private int p;

    public AutoRod() {
        String[] stringArray = q;
        super("AutoRod", Category.MISC);
        this.button = new ModeSetting("Button", "Middle", "Mouse 4", "Mouse 5").withDefault("Mouse 4");
        this.delay = new NumberSetting("Delay", 2, 0, 20, 1);
        this.m = false;
        this.n = false;
        this.o = -1;
        this.p = 0;
        j = this;
    }

    private boolean i() {
        return GLFW.glfwGetMouseButton((long)mc.getWindow().getWindow(), (int)this.j()) == 1;
    }

    private void m() {
        if (AutoRod.mc.gameMode == null || !this.b(AutoRod.mc.player.getMainHandItem().getItem())) {
            return;
        }
        AutoRod.mc.gameMode.useItem((Player)AutoRod.mc.player, InteractionHand.MAIN_HAND);
        AutoRod.mc.player.swing(InteractionHand.MAIN_HAND);
    }

    private boolean h() {
        MidPearl midPearl = MidPearl.j;
        return midPearl != null && midPearl.isEnabled() && midPearl.a(this.j());
    }

    private boolean c(Item item) {
        return item == Items.EGG || item == Items.SNOWBALL;
    }

    private void o() {
        this.m = false;
        this.n = false;
        this.o = -1;
        this.p = 0;
    }

    private boolean b(Item item) {
        return item == Items.FISHING_ROD || this.c(item);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[9];
                var3_1 = 0;
                var2_2 = "K/D 4\f\u0003\u0007G5E<\u0003CS\u0007G5E<\u0003CS\u0007G5E<\u0003CR\u0007G5E<\u0003CR\u0006G3T+\n\u0006\u0005N?\\.\u001f";
                var4_3 = "K/D 4\f\u0003\u0007G5E<\u0003CS\u0007G5E<\u0003CS\u0007G5E<\u0003CR\u0007G5E<\u0003CR\u0006G3T+\n\u0006\u0005N?\\.\u001f".length();
                var1_4 = 7;
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
                    var5[var3_1++] = AutoRod.c(AutoRod.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "G3T+\n\u0006\u0006H/D;\t\r";
                    var4_3 = "G3T+\n\u0006\u0006H/D;\t\r".length();
                    var1_4 = 6;
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
                    var5[var3_1++] = AutoRod.c(AutoRod.a(v1));
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
        AutoRod.q = var5;
    }

    public boolean a(int n2) {
        return this.j() == n2;
    }

    public boolean b(int n2) {
        return this.a(n2) && (this.a() || this.k() != -1);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x67);
        }
        return cArray;
    }

    private int j() {
        String string = (String)this.button.getValue();
        int n2 = -1;
        switch (string.hashCode()) {
            case -1387248806: {
                String[] stringArray = q;
                if (!string.equals("Mouse 5")) break;
                n2 = 0;
                break;
            }
            case -1990474315: {
                if (!string.equals("Middle")) break;
                n2 = 1;
            }
        }
        return switch (n2) {
            case 0 -> 4;
            case 1 -> 2;
            default -> 3;
        };
    }

    @Override
    public void onEnable() {
        this.o();
    }

    private void c(int n2) {
        AutoRod.mc.player.getInventory().selected = n2;
        PlayerUtil.a();
    }

    private int k() {
        int n2 = this.a(Items.FISHING_ROD);
        return n2 != -1 ? n2 : this.l();
    }

    private int a(Item item) {
        if (AutoRod.mc.player == null) {
            return -1;
        }
        int n2 = Math.min(9, AutoRod.mc.player.getInventory().items.size());
        for (int i2 = 0; i2 < n2; ++i2) {
            if (AutoRod.mc.player.getInventory().getItem(i2).getItem() != item) continue;
            return i2;
        }
        return -1;
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
                    case 0 -> 10;
                    case 1 -> 90;
                    case 2 -> 48;
                    case 3 -> 79;
                    case 4 -> 102;
                    case 5 -> 99;
                    default -> 103;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public boolean a() {
        return this.m || this.p > 0;
    }

    private int l() {
        if (AutoRod.mc.player == null) {
            return -1;
        }
        int n2 = Math.min(9, AutoRod.mc.player.getInventory().items.size());
        for (int i2 = 0; i2 < n2; ++i2) {
            if (!this.c(AutoRod.mc.player.getInventory().getItem(i2).getItem())) continue;
            return i2;
        }
        return -1;
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (AutoRod.mc.player == null || AutoRod.mc.level == null) {
            this.o();
            return;
        }
        if (this.p > 0) {
            --this.p;
            if (this.p == 0) {
                this.n();
                this.m = false;
            }
            return;
        }
        boolean bl = this.i();
        if (!bl) {
            this.n = false;
            return;
        }
        if (this.n || this.h()) {
            return;
        }
        this.n = true;
        int n2 = this.k();
        if (n2 == -1) {
            return;
        }
        this.o = AutoRod.mc.player.getInventory().selected;
        boolean bl2 = AutoRod.mc.player.getInventory().getItem(n2).getItem() == Items.FISHING_ROD;
        this.m = true;
        this.c(n2);
        this.m();
        if (bl2) {
            this.p = ((Number)this.delay.getValue()).intValue();
        }
        if (!bl2 || this.p <= 0) {
            this.n();
            this.m = false;
        }
    }

    private void n() {
        if (AutoRod.mc.player != null && this.o != -1) {
            AutoRod.mc.player.getInventory().selected = this.o;
            PlayerUtil.a();
            this.o = -1;
        }
    }

    @Override
    public void onDisable() {
        this.n();
        this.o();
    }
}

