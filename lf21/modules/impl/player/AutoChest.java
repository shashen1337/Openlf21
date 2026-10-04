/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import lf21.event.EventTarget;
import lf21.event.impl.GameTickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.NumberSetting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class AutoChest
extends Module {
    private Item o;
    private final NumberSetting delay;
    private int p;
    private int q;
    private static final String[] t;
    private boolean r;
    private int n;
    private int m;
    private long k;
    private boolean s;
    private int l;

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u0015E\u0017\u001e}\t\u0010U\u000f\u0010GJ,\"T";
        int n3 = "\u0015E\u0017\u001e}\t\u0010U\u000f\u0010GJ,\"T".length();
        int n4 = 5;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = AutoChest.c(AutoChest.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                t = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        Screen screen;
        if (AutoChest.mc.player == null || AutoChest.mc.gameMode == null || !((screen = AutoChest.mc.screen) instanceof ContainerScreen)) {
            this.a();
            return;
        }
        ContainerScreen containerScreen = (ContainerScreen)screen;
        screen = (ChestMenu)containerScreen.getMenu();
        if (this.l != screen.containerId) {
            this.a((ChestMenu)screen);
            return;
        }
        ++this.q;
        int n2 = AutoChest.mc.player.getInventory().selected;
        int n3 = screen.getRowCount() * 9 + 27 + n2;
        if (n3 < 0 || n3 >= screen.slots.size()) {
            return;
        }
        if (this.s) {
            ItemStack itemStack = screen.getSlot(n3).getItem();
            if (itemStack.isEmpty() || itemStack.getItem() != this.o || itemStack.getCount() < this.n) {
                this.s = false;
                AutoChest.mc.player.closeContainer();
            }
            return;
        }
        if (this.r || this.q < 2 || n2 != this.m || this.o == null) {
            return;
        }
        ItemStack itemStack = AutoChest.mc.player.getMainHandItem();
        if (!AutoChest.a(itemStack) || itemStack.getItem() != this.o || itemStack.getCount() != this.p) {
            return;
        }
        ItemStack itemStack2 = screen.getSlot(n3).getItem();
        if (itemStack2.isEmpty() || itemStack2.getItem() != itemStack.getItem()) {
            return;
        }
        long l2 = System.currentTimeMillis();
        if (l2 - this.k < ((Number)this.delay.getValue()).longValue()) {
            return;
        }
        AutoChest.mc.gameMode.handleInventoryMouseClick(screen.containerId, n3, 0, ClickType.QUICK_MOVE, (Player)AutoChest.mc.player);
        this.k = l2;
        this.n = itemStack.getCount();
        this.r = true;
        this.s = true;
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
                    case 0 -> 81;
                    case 1 -> 32;
                    case 2 -> 123;
                    case 3 -> 127;
                    case 4 -> 4;
                    case 5 -> 34;
                    default -> 73;
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
            cArray[0] = (char)(cArray[0] ^ 0x49);
        }
        return cArray;
    }

    private void a(ChestMenu chestMenu) {
        this.l = chestMenu.containerId;
        this.m = AutoChest.mc.player.getInventory().selected;
        ItemStack itemStack = AutoChest.mc.player.getMainHandItem();
        this.o = AutoChest.a(itemStack) ? itemStack.getItem() : null;
        this.p = itemStack.getCount();
        this.n = itemStack.getCount();
        this.q = 0;
        this.r = false;
        this.s = false;
    }

    private void a() {
        this.k = 0L;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.o = null;
        this.p = 0;
        this.q = 0;
        this.r = false;
        this.s = false;
    }

    public AutoChest() {
        String[] stringArray = t;
        super("AutoChest", Category.PLAYER);
        this.delay = new NumberSetting("Delay", 150, 0, 1000, 10);
        this.l = -1;
        this.m = -1;
        this.n = -1;
    }

    private static boolean a(ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty()) {
            return false;
        }
        Item item = itemStack.getItem();
        return item == Items.IRON_INGOT || item == Items.GOLD_INGOT || item == Items.EMERALD || item == Items.DIAMOND;
    }

    @Override
    protected void onDisable() {
        this.a();
    }
}

