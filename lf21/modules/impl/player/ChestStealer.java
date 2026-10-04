/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lf21.event.EventTarget;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.MotionEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.B;
import lf21.modules.impl.player.InventoryManager;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.h;
import lf21.utils.game.a;
import lf21.utils.game.d;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

public class ChestStealer
extends Module {
    private static final h x;
    private int G;
    private int D;
    private static final String[] M;
    private boolean L;
    private final BooleanSetting enderChest;
    private final BooleanSetting pickTrash;
    private final List<B> J;
    private final BooleanSetting randomClick;
    private final Random z;
    private int C;
    public static ChestStealer j;
    private final NumberSetting delay;
    private int I;
    private boolean B;
    private int K;
    private final ModeSetting clickMode;
    private final BooleanSetting noTools;
    private final BooleanSetting furnace;
    private static final h k;
    private final BooleanSetting smartStealing;
    private static final h y;
    private Screen H;
    private final BooleanSetting chest;
    private static long F;
    private final NumberSetting openDelay;
    private AbstractContainerMenu A;
    private final BooleanSetting brewingStand;
    private int E;
    private final BooleanSetting onlyBest;

    private void j() {
        this.B = false;
        this.D = -1;
        this.A = null;
        this.E = 0;
    }

    private Container a(AbstractFurnaceMenu abstractFurnaceMenu) throws Exception {
        Field[] fieldArray;
        for (Field field : fieldArray = AbstractFurnaceMenu.class.getDeclaredFields()) {
            if (!Container.class.isAssignableFrom(field.getType())) {
                continue;
            }
            field.setAccessible(true);
            return (Container)field.get(abstractFurnaceMenu);
        }
        return null;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[53];
                var3_1 = 0;
                var2_2 = "Zr\u0001\u0007Nm\u0000\f\u0015\u007f&\b^o\n\u001c\u000f|%J\nrm\u0000\u0001\\Z/Q|\u001c\u0014^r\u000b\u001b\u001dw$XoK\n\u0012z/O~\r\n\u000fj\fZr\t\u000b\u0019p\u0015\\m\u0015\u0003\u0019\f~u\u0000\u001c\bM>X|\t\n\u000e\u0005Ri\r\n\u000e\u0015^r\u000b\u001b\u001dw$XoK\f\u0014{9IY\n\u001a\u001er/\u0006Ux\t\u0002\u0019j\u0006m|\u0006\u0004\u0019j\trs\t\u0016\\\\/Ni\u0006Nu\n\u0019\u0019r\u0003_r\u0012\n^u\u0000\u001c\bn&\\i\u0000\n^u\u0000\u001c\bn&\\i\u0000\bsrE;\u0013q&N\rjt\u000b\u000b\u0013i9\u001d^\t\u0006\u001fu\u0003Zr\u0001\u0003_r\u0012\u000f^r\u000b\u001b\u001dw$XoK\f\u0014{9I\u0007ts\u0016\u001b\u001dp>\u0005Nj\n\u001d\u0018\u0005~u\u0000\u001c\b\u0007ts\u0016\u001b\u001dp>\u0006Nu\n\u0019\u0019r\rjt\u000b\u000b\u0013i9\u001d^\t\u0006\u001fu\u0007Mt\u0006\u0004\u001df/\u000enp\u0004\u001d\b>\u0019Ix\u0004\u0003\u0015p-\u0005Ri\r\n\u000e\u0007Nm\u0000\f\u0015\u007f&\u0007Hi\f\u0003\u0015j3\u0005~u\u0000\u001c\b\bQx\u0002\b\u0015p-N\f\u007fo\u0000\u0018\u0015p-ni\u0004\u0001\u0018\u000bxs\u0001\n\u000e>\tUx\u0016\u001b\u0007Mt\u0006\u0004\u001df/\tmt\u0006\u0004(l+Nu\bQx\u0002\b\u0015p-N\u0006Ux\t\u0002\u0019j\u0003\\e\u0000\fo|\u000b\u000b\u0013sj~q\f\f\u0017\u0005_r\n\u001b\u000f\u0007{h\u0017\u0001\u001d}/\u0005yx\t\u000e\u0005\u0005_r\n\u001b\u000f\b^o\n\u001c\u000f|%J\u0006m|\u0006\u0004\u0019j\u0003\\e\u0000\u0007Hi\f\u0003\u0015j3\fZr\t\u000b\u0019p\u0015\\m\u0015\u0003\u0019";
                var4_3 = "Zr\u0001\u0007Nm\u0000\f\u0015\u007f&\b^o\n\u001c\u000f|%J\nrm\u0000\u0001\\Z/Q|\u001c\u0014^r\u000b\u001b\u001dw$XoK\n\u0012z/O~\r\n\u000fj\fZr\t\u000b\u0019p\u0015\\m\u0015\u0003\u0019\f~u\u0000\u001c\bM>X|\t\n\u000e\u0005Ri\r\n\u000e\u0015^r\u000b\u001b\u001dw$XoK\f\u0014{9IY\n\u001a\u001er/\u0006Ux\t\u0002\u0019j\u0006m|\u0006\u0004\u0019j\trs\t\u0016\\\\/Ni\u0006Nu\n\u0019\u0019r\u0003_r\u0012\n^u\u0000\u001c\bn&\\i\u0000\n^u\u0000\u001c\bn&\\i\u0000\bsrE;\u0013q&N\rjt\u000b\u000b\u0013i9\u001d^\t\u0006\u001fu\u0003Zr\u0001\u0003_r\u0012\u000f^r\u000b\u001b\u001dw$XoK\f\u0014{9I\u0007ts\u0016\u001b\u001dp>\u0005Nj\n\u001d\u0018\u0005~u\u0000\u001c\b\u0007ts\u0016\u001b\u001dp>\u0006Nu\n\u0019\u0019r\rjt\u000b\u000b\u0013i9\u001d^\t\u0006\u001fu\u0007Mt\u0006\u0004\u001df/\u000enp\u0004\u001d\b>\u0019Ix\u0004\u0003\u0015p-\u0005Ri\r\n\u000e\u0007Nm\u0000\f\u0015\u007f&\u0007Hi\f\u0003\u0015j3\u0005~u\u0000\u001c\b\bQx\u0002\b\u0015p-N\f\u007fo\u0000\u0018\u0015p-ni\u0004\u0001\u0018\u000bxs\u0001\n\u000e>\tUx\u0016\u001b\u0007Mt\u0006\u0004\u001df/\tmt\u0006\u0004(l+Nu\bQx\u0002\b\u0015p-N\u0006Ux\t\u0002\u0019j\u0003\\e\u0000\fo|\u000b\u000b\u0013sj~q\f\f\u0017\u0005_r\n\u001b\u000f\u0007{h\u0017\u0001\u001d}/\u0005yx\t\u000e\u0005\u0005_r\n\u001b\u000f\b^o\n\u001c\u000f|%J\u0006m|\u0006\u0004\u0019j\u0003\\e\u0000\u0007Hi\f\u0003\u0015j3\fZr\t\u000b\u0019p\u0015\\m\u0015\u0003\u0019".length();
                var1_4 = 3;
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
                    var5[var3_1++] = ChestStealer.c(ChestStealer.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "Nj\n\u001d\u0018\n~q\f\f\u0017>\u0007Ry\u0000";
                    var4_3 = "Nj\n\u001d\u0018\n~q\f\f\u0017>\u0007Ry\u0000".length();
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
                    var5[var3_1++] = ChestStealer.c(ChestStealer.b(v1));
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
        ChestStealer.M = var5;
        ChestStealer.k = new h();
        ChestStealer.x = new h();
        ChestStealer.y = new h();
    }

    private int b(ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (d.l(itemStack) || d.q(itemStack) || d.p(itemStack)) {
            return 150;
        }
        if (item instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem)item;
            return switch (armorItem.getEquipmentSlot()) {
                case EquipmentSlot.HEAD -> 100;
                case EquipmentSlot.CHEST -> 99;
                case EquipmentSlot.LEGS -> 98;
                case EquipmentSlot.FEET -> 97;
                default -> 50;
            };
        }
        if (item instanceof SwordItem) {
            return 95;
        }
        if (item instanceof BowItem) {
            return 93;
        }
        if (item instanceof CrossbowItem) {
            return 92;
        }
        if (item == Items.ENCHANTED_GOLDEN_APPLE) {
            return 91;
        }
        if (item == Items.GOLDEN_APPLE) {
            return 90;
        }
        if (item instanceof PickaxeItem) {
            return 89;
        }
        if (item instanceof AxeItem) {
            return 88;
        }
        if (item instanceof ShovelItem) {
            return 87;
        }
        if (item == Items.COMPASS) {
            return 85;
        }
        if (item == Items.WATER_BUCKET || item == Items.LAVA_BUCKET) {
            return 83;
        }
        if (item == Items.ENDER_PEARL) {
            return 80;
        }
        if (item == Items.ARROW) {
            return 75;
        }
        if (item == Items.COBWEB) {
            return 72;
        }
        if (item == Items.SNOWBALL || item == Items.EGG) {
            return 70;
        }
        if (item instanceof FishingRodItem) {
            return 65;
        }
        if (item instanceof BlockItem) {
            return 60;
        }
        return 50;
    }

    private boolean a(ChestMenu chestMenu) {
        if (this.h(chestMenu) && x.c(100L)) {
            ChestStealer.mc.player.closeContainer();
            return false;
        }
        return true;
    }

    public static boolean f(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }
        if (d.l(itemStack) || d.q(itemStack)) {
            return true;
        }
        Item item = itemStack.getItem();
        if (item instanceof ArmorItem) {
            float f2;
            ArmorItem armorItem = (ArmorItem)item;
            float f3 = d.j(itemStack);
            return !(f3 <= (f2 = d.a(armorItem.getEquipmentSlot())));
        }
        if (itemStack.getItem() instanceof SwordItem) {
            float f4;
            float f5 = d.i(itemStack);
            return !(f5 <= (f4 = d.c()));
        }
        if (itemStack.getItem() instanceof PickaxeItem) {
            float f6;
            float f7 = d.g(itemStack);
            return !(f7 <= (f6 = d.o()));
        }
        if (itemStack.getItem() instanceof AxeItem) {
            ItemStack itemStack2 = d.s();
            return itemStack2 == null || d.v(itemStack) > d.v(itemStack2);
        }
        if (itemStack.getItem() instanceof ShovelItem) {
            float f8;
            float f9 = d.g(itemStack);
            return !(f9 <= (f8 = d.u()));
        }
        if (itemStack.getItem() instanceof CrossbowItem) {
            float f10;
            float f11 = d.k(itemStack);
            return !(f11 <= (f10 = d.w()));
        }
        if (itemStack.getItem() instanceof BowItem && d.w(itemStack)) {
            float f12;
            float f13 = d.e(itemStack);
            return !(f13 <= (f12 = d.y()));
        }
        if (itemStack.getItem() instanceof BowItem && d.x(itemStack)) {
            float f14;
            float f15 = d.f(itemStack);
            return !(f15 <= (f14 = d.A()));
        }
        if (itemStack.getItem() == Items.GOLDEN_APPLE || itemStack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
            return true;
        }
        if (itemStack.getItem() == Items.COBWEB) {
            return true;
        }
        if (itemStack.getItem() == Items.TNT) {
            return true;
        }
        if (itemStack.getItem() == Items.COMPASS) {
            return !d.b(itemStack.getItem());
        }
        if (itemStack.getItem() == Items.WATER_BUCKET && d.c(Items.WATER_BUCKET) >= InventoryManager.L()) {
            return false;
        }
        if (itemStack.getItem() == Items.LAVA_BUCKET && d.c(Items.LAVA_BUCKET) >= InventoryManager.M()) {
            return false;
        }
        if (itemStack.getItem() instanceof BlockItem && a.a(itemStack) && d.j() + itemStack.getCount() >= InventoryManager.I()) {
            return false;
        }
        if (itemStack.getItem() == Items.ARROW && d.c(Items.ARROW) + itemStack.getCount() >= InventoryManager.K()) {
            return false;
        }
        if (itemStack.getItem() instanceof FishingRodItem && d.c(Items.FISHING_ROD) >= 1) {
            return false;
        }
        if ((itemStack.getItem() == Items.SNOWBALL || itemStack.getItem() == Items.EGG) && d.c(Items.SNOWBALL) + d.c(Items.EGG) + itemStack.getCount() >= InventoryManager.J()) {
            return false;
        }
        if (itemStack.getItem() instanceof ItemNameBlockItem) {
            return false;
        }
        return d.b(itemStack);
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        if (this.B && this.A != null && this.D >= 0) {
            ++this.E;
            if (this.E >= 1) {
                this.h();
                this.j();
            }
        }
    }

    private boolean e(ItemStack itemStack) {
        if (itemStack.getItem() instanceof SwordItem) {
            float f2;
            float f3 = d.i(itemStack);
            return f3 > (f2 = d.c());
        }
        if (itemStack.getItem() instanceof DiggerItem) {
            if (itemStack.getItem() instanceof PickaxeItem) {
                float f4;
                float f5 = d.g(itemStack);
                return f5 > (f4 = d.o());
            }
            if (itemStack.getItem() instanceof AxeItem) {
                float f6;
                if (d.p(itemStack)) {
                    float f7 = d.h(itemStack);
                    ItemStack itemStack2 = d.t();
                    float f8 = itemStack2 != null ? d.h(itemStack2) : 0.0f;
                    return f7 > f8;
                }
                float f9 = d.g(itemStack);
                return f9 > (f6 = d.q());
            }
            if (itemStack.getItem() instanceof ShovelItem) {
                float f10;
                float f11 = d.g(itemStack);
                return f11 > (f10 = d.u());
            }
        } else {
            Item item = itemStack.getItem();
            if (item instanceof ArmorItem) {
                float f12;
                ArmorItem armorItem = (ArmorItem)item;
                float f13 = d.j(itemStack);
                return f13 > (f12 = d.b(armorItem.getEquipmentSlot())) + 0.1f;
            }
            if (itemStack.getItem() instanceof BowItem) {
                if (d.w(itemStack)) {
                    float f14;
                    float f15 = d.e(itemStack);
                    return f15 > (f14 = d.y());
                }
                if (d.x(itemStack)) {
                    float f16;
                    float f17 = d.f(itemStack);
                    return f17 > (f16 = d.A());
                }
            } else if (itemStack.getItem() instanceof CrossbowItem) {
                float f18;
                float f19 = d.k(itemStack);
                return f19 > (f18 = d.w());
            }
        }
        return true;
    }

    private Map<String, List<B>> b(List<B> list) {
        HashMap<String, List<B>> hashMap = new HashMap<String, List<B>>();
        for (B b2 : list) {
            String string2 = this.a(b2.b);
            hashMap.computeIfAbsent(string2, string -> new ArrayList()).add(b2);
        }
        return hashMap;
    }

    private boolean a(ChestMenu chestMenu, int n2) {
        ItemStack itemStack = chestMenu.getSlot(n2).getItem();
        if ((ChestStealer.f(itemStack) || ((Boolean)this.pickTrash.getValue()).booleanValue()) && this.d(itemStack)) {
            this.a((AbstractContainerMenu)chestMenu, n2);
            return true;
        }
        return false;
    }

    private double c(ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (d.l(itemStack) || d.q(itemStack) || d.p(itemStack)) {
            return 10000.0 + (item instanceof AxeItem ? (double)d.h(itemStack) : 0.0);
        }
        if (item instanceof ArmorItem) {
            return d.j(itemStack);
        }
        if (item instanceof SwordItem) {
            return d.i(itemStack);
        }
        if (item instanceof AxeItem && d.p(itemStack)) {
            return d.h(itemStack);
        }
        if (item instanceof DiggerItem) {
            return d.g(itemStack);
        }
        if (item instanceof BowItem) {
            if (d.w(itemStack)) {
                return d.e(itemStack);
            }
            if (d.x(itemStack)) {
                return d.f(itemStack);
            }
            return 1.0;
        }
        if (item instanceof CrossbowItem) {
            return d.k(itemStack);
        }
        if (item == Items.ENCHANTED_GOLDEN_APPLE) {
            return 50.0 + (double)itemStack.getCount();
        }
        if (item == Items.GOLDEN_APPLE) {
            return 30.0 + (double)itemStack.getCount();
        }
        if (item == Items.ENDER_PEARL) {
            return 10.0 + (double)itemStack.getCount();
        }
        if (item == Items.ARROW) {
            return 5.0 + (double)itemStack.getCount() * 0.1;
        }
        if (item == Items.COBWEB) {
            return 4.0 + (double)itemStack.getCount() * 0.1;
        }
        if (item == Items.SNOWBALL || item == Items.EGG) {
            return 3.0 + (double)itemStack.getCount() * 0.1;
        }
        if (item instanceof FishingRodItem) {
            return d.g(itemStack);
        }
        if (item instanceof BlockItem) {
            return 2.0 + (double)itemStack.getCount() * 0.05;
        }
        return 1.0;
    }

    private void b(AbstractContainerMenu abstractContainerMenu, int n2) {
        ChestStealer.mc.gameMode.handleInventoryMouseClick(abstractContainerMenu.containerId, n2, 0, ClickType.QUICK_MOVE, (Player)ChestStealer.mc.player);
    }

    private void a(AbstractContainerMenu abstractContainerMenu, Container container, List<Integer> list) {
        this.j();
        ArrayList<Integer> arrayList = new ArrayList<Integer>(list);
        if (((Boolean)this.randomClick.getValue()).booleanValue()) {
            Collections.shuffle(arrayList, this.z);
        }
        boolean bl = false;
        for (int n2 : arrayList) {
            ItemStack itemStack = container.getItem(n2);
            if (itemStack.isEmpty() || !this.d(itemStack)) continue;
            this.b(abstractContainerMenu, n2);
            bl = true;
        }
        if (bl) {
            F = 0L;
            y.a();
            x.a();
            k.a();
        }
    }

    public ChestStealer() {
        String[] stringArray = M;
        super("ChestStealer", Category.PLAYER);
        this.delay = new NumberSetting("Delay", 200, 0, 1000, 10, () -> true);
        this.openDelay = new NumberSetting("Open Delay", 2, 0, 10, 1, () -> true);
        this.chest = new BooleanSetting("Chest", true);
        this.enderChest = new BooleanSetting("Ender Chest", false);
        this.furnace = new BooleanSetting("Furnace", true);
        this.brewingStand = new BooleanSetting("BrewingStand", true);
        this.noTools = new BooleanSetting("No Tools", false);
        this.pickTrash = new BooleanSetting("PickTrash", false);
        this.onlyBest = new BooleanSetting("Only Best", true);
        this.randomClick = new BooleanSetting("Random Click", false);
        this.smartStealing = new BooleanSetting("Smart Stealing", true);
        this.clickMode = new ModeSetting("Click Mode", "Windows Click", "Packet", "Instant").withDefault("Windows Click");
        this.z = new Random();
        this.A = null;
        this.B = false;
        this.C = 0;
        this.D = -1;
        this.E = 0;
        this.I = 0;
        this.J = new ArrayList<B>();
        this.K = 0;
        this.L = false;
        j = this;
    }

    private String a(ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (d.l(itemStack) || d.q(itemStack) || d.p(itemStack)) {
            String[] stringArray = M;
            return "god";
        }
        if (item instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem)item;
            return switch (armorItem.getEquipmentSlot()) {
                case EquipmentSlot.HEAD -> {
                    String[] var4_4 = M;
                    yield "helmet";
                }
                case EquipmentSlot.CHEST -> {
                    String[] var4_5 = M;
                    yield "chestplate";
                }
                case EquipmentSlot.LEGS -> {
                    String[] var4_6 = M;
                    yield "leggings";
                }
                case EquipmentSlot.FEET -> {
                    String[] var4_7 = M;
                    yield "boots";
                }
                default -> {
                    String[] var4_8 = M;
                    yield "other";
                }
            };
        }
        if (item instanceof SwordItem) {
            String[] stringArray = M;
            return "sword";
        }
        if (item instanceof BowItem) {
            String[] stringArray = M;
            return "bow";
        }
        if (item instanceof CrossbowItem) {
            String[] stringArray = M;
            return "crossbow";
        }
        if (item instanceof PickaxeItem) {
            String[] stringArray = M;
            return "pickaxe";
        }
        if (item instanceof AxeItem) {
            String[] stringArray = M;
            return "axe";
        }
        if (item instanceof ShovelItem) {
            String[] stringArray = M;
            return "shovel";
        }
        if (item == Items.GOLDEN_APPLE || item == Items.ENCHANTED_GOLDEN_APPLE) {
            String[] stringArray = M;
            return "golden_apple";
        }
        if (item == Items.COMPASS || item == Items.WATER_BUCKET || item == Items.LAVA_BUCKET) {
            String[] stringArray = M;
            return "special";
        }
        if (item == Items.COBWEB) {
            String[] stringArray = M;
            return "utility";
        }
        if (item == Items.ENDER_PEARL || item == Items.SNOWBALL || item == Items.EGG || item == Items.ARROW || item instanceof FishingRodItem || item instanceof BlockItem) {
            String[] stringArray = M;
            return "utility";
        }
        return "other";
    }

    public static boolean a() {
        return !x.c(100L) && !y.c((int)F);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(FurnaceMenu furnaceMenu) {
        ++this.G;
        try {
            Container container = this.a((AbstractFurnaceMenu)furnaceMenu);
            if (container == null) {
                return;
            }
            if (this.b(furnaceMenu) && x.c(100L)) {
                ChestStealer.mc.player.closeContainer();
                return;
            }
            List<Integer> list = this.a(container);
            if (this.clickMode.is("Instant")) {
                this.a((AbstractContainerMenu)furnaceMenu, container, list);
                return;
            }
            if (((Boolean)this.randomClick.getValue()).booleanValue() && !list.isEmpty() && this.G > 1) {
                int n2 = list.get(this.z.nextInt(list.size()));
                this.a((AbstractContainerMenu)furnaceMenu, n2);
                return;
            }
            int i2 = 0;
            while (i2 < container.getContainerSize()) {
                ItemStack itemStack = container.getItem(i2);
                if (!itemStack.isEmpty() && this.G > 1 && this.d(itemStack)) {
                    this.a((AbstractContainerMenu)furnaceMenu, i2);
                    return;
                }
                ++i2;
            }
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean b(BrewingStandMenu brewingStandMenu) {
        Container container = ReflectionUtil.a(brewingStandMenu);
        if (container == null) {
            return true;
        }
        for (int i2 = 0; i2 < container.getContainerSize(); ++i2) {
            ItemStack itemStack = container.getItem(i2);
            if (itemStack.isEmpty() || !this.d(itemStack)) continue;
            return false;
        }
        return true;
    }

    private List<Integer> g(ChestMenu chestMenu) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i2 = 0; i2 < chestMenu.getRowCount() * 9; ++i2) {
            ItemStack itemStack = chestMenu.getSlot(i2).getItem();
            if (itemStack.isEmpty() || !ChestStealer.f(itemStack) && !((Boolean)this.pickTrash.getValue()).booleanValue() || !this.d(itemStack)) continue;
            arrayList.add(i2);
        }
        return arrayList;
    }

    private void e(ChestMenu chestMenu) {
        Object object;
        ArrayList<B> arrayList = new ArrayList<B>();
        for (int i2 = 0; i2 < chestMenu.getRowCount() * 9; ++i2) {
            object = chestMenu.getSlot(i2).getItem();
            if (object.isEmpty() || !this.d((ItemStack)object)) continue;
            int n2 = this.b((ItemStack)object);
            double d2 = this.c((ItemStack)object);
            arrayList.add(new B(i2, (ItemStack)object, n2, d2));
        }
        Map<String, List<B>> map = this.b(arrayList);
        this.J.clear();
        String[] stringArray = new String[15];
        String[] stringArray2 = M;
        stringArray[0] = "god";
        stringArray[1] = "helmet";
        stringArray[2] = "chestplate";
        stringArray[3] = "leggings";
        stringArray[4] = "boots";
        stringArray[5] = "sword";
        stringArray[6] = "bow";
        stringArray[7] = "crossbow";
        stringArray[8] = "golden_apple";
        stringArray[9] = "pickaxe";
        stringArray[10] = "axe";
        stringArray[11] = "shovel";
        stringArray[12] = "special";
        stringArray[13] = "utility";
        stringArray[14] = "other";
        object = Arrays.asList(stringArray);
        Iterator iterator = object.iterator();
        while (iterator.hasNext()) {
            boolean bl;
            String string = (String)iterator.next();
            if (!map.containsKey(string)) continue;
            List<B> list = map.get(string);
            stringArray2 = M;
            boolean bl2 = bl = string.equals("god") || string.equals("helmet") || string.equals("chestplate") || string.equals("leggings") || string.equals("boots") || string.equals("sword") || string.equals("bow") || string.equals("crossbow") || string.equals("pickaxe") || string.equals("axe") || string.equals("shovel");
            if (((Boolean)this.onlyBest.getValue()).booleanValue() && bl) {
                B b4 = list.stream().max(Comparator.comparingDouble(b2 -> b2.d)).orElse(null);
                if (b4 == null) continue;
                this.J.add(b4);
                continue;
            }
            list.sort((b2, b3) -> Double.compare(b3.d, b2.d));
            this.J.addAll(list);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean b(FurnaceMenu furnaceMenu) {
        try {
            Container container = this.a((AbstractFurnaceMenu)furnaceMenu);
            if (container == null) {
                return false;
            }
            for (int i2 = 0; i2 < container.getContainerSize(); ++i2) {
                ItemStack itemStack = container.getItem(i2);
                if (itemStack.isEmpty() || !this.d(itemStack)) continue;
                return false;
            }
            return true;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private void f(ChestMenu chestMenu) {
        List<Integer> list = this.g(chestMenu);
        if (((Boolean)this.randomClick.getValue()).booleanValue() && !list.isEmpty() && this.G > 1) {
            int n2 = list.get(this.z.nextInt(list.size()));
            this.a((AbstractContainerMenu)chestMenu, n2);
        } else {
            for (int i2 = 0; i2 < chestMenu.getRowCount() * 9; ++i2) {
                ItemStack itemStack = chestMenu.getSlot(i2).getItem();
                if (itemStack.isEmpty() || this.G <= 1 || !this.a(chestMenu, i2)) continue;
                return;
            }
        }
    }

    private boolean h(ChestMenu chestMenu) {
        for (int i2 = 0; i2 < chestMenu.getRowCount() * 9; ++i2) {
            ItemStack itemStack = chestMenu.getSlot(i2).getItem();
            if (itemStack.isEmpty() || !ChestStealer.f(itemStack) && !((Boolean)this.pickTrash.getValue()).booleanValue() || !this.d(itemStack)) continue;
            return false;
        }
        return true;
    }

    private void k() {
        this.C = 0;
        for (int i2 = 0; i2 < ChestStealer.mc.player.getInventory().getContainerSize(); ++i2) {
            ItemStack itemStack = ChestStealer.mc.player.getInventory().getItem(i2);
            if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BlockItem)) continue;
            this.C += itemStack.getCount();
        }
    }

    @EventTarget
    public void a(DisconnectEvent disconnectEvent) {
        this.i();
    }

    private void a(BrewingStandMenu brewingStandMenu) {
        ++this.G;
        Container container = ReflectionUtil.a(brewingStandMenu);
        if (container == null) {
            return;
        }
        if (this.b(brewingStandMenu) && x.c(100L)) {
            ChestStealer.mc.player.closeContainer();
            return;
        }
        List<Integer> list = this.a(container);
        if (this.clickMode.is("Instant")) {
            this.a((AbstractContainerMenu)brewingStandMenu, container, list);
            return;
        }
        if (((Boolean)this.randomClick.getValue()).booleanValue() && !list.isEmpty() && this.G > 1) {
            int n2 = list.get(this.z.nextInt(list.size()));
            this.a((AbstractContainerMenu)brewingStandMenu, n2);
        } else {
            for (int i2 = 0; i2 < container.getContainerSize(); ++i2) {
                ItemStack itemStack = container.getItem(i2);
                if (itemStack.isEmpty() || this.G <= 1 || !this.d(itemStack)) continue;
                this.a((AbstractContainerMenu)brewingStandMenu, i2);
                return;
            }
        }
    }

    private List<Integer> a(Container container) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i2 = 0; i2 < container.getContainerSize(); ++i2) {
            ItemStack itemStack = container.getItem(i2);
            if (itemStack.isEmpty() || !this.d(itemStack)) continue;
            arrayList.add(i2);
        }
        return arrayList;
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
                    case 0 -> 61;
                    case 1 -> 29;
                    case 2 -> 101;
                    case 3 -> 111;
                    case 4 -> 124;
                    case 5 -> 30;
                    default -> 74;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void d(ChestMenu chestMenu) {
        if (!this.L) {
            this.e(chestMenu);
            this.L = true;
            this.K = 0;
        }
        if (this.K < this.J.size()) {
            B b2 = this.J.get(this.K);
            if (!chestMenu.getSlot(b2.a).getItem().isEmpty()) {
                this.a((AbstractContainerMenu)chestMenu, b2.a);
                ++this.K;
            } else {
                ++this.K;
            }
        } else if (this.i(chestMenu) && x.c(100L)) {
            ChestStealer.mc.player.closeContainer();
        }
    }

    private boolean i(ChestMenu chestMenu) {
        for (int i2 = 0; i2 < chestMenu.getRowCount() * 9; ++i2) {
            ItemStack itemStack = chestMenu.getSlot(i2).getItem();
            if (itemStack.isEmpty() || !ChestStealer.f(itemStack) && !((Boolean)this.pickTrash.getValue()).booleanValue() || !this.d(itemStack)) continue;
            return false;
        }
        return true;
    }

    private void i() {
        this.j();
        this.I = 0;
    }

    private void h() {
        if (this.A != null && this.D >= 0) {
            F = ((Number)this.delay.getValue()).longValue();
            if (this.clickMode.is("Packet")) {
                int n2 = ChestStealer.mc.player.containerMenu != null ? ChestStealer.mc.player.containerMenu.getStateId() : 0;
                ItemStack itemStack = ChestStealer.mc.player.containerMenu != null ? ChestStealer.mc.player.containerMenu.getCarried().copy() : ItemStack.EMPTY;
                Int2ObjectOpenHashMap int2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
                ChestStealer.mc.player.connection.send((Packet)new ServerboundContainerClickPacket(this.A.containerId, n2, this.D, 0, ClickType.QUICK_MOVE, itemStack, (Int2ObjectMap)int2ObjectOpenHashMap));
            } else {
                ChestStealer.mc.gameMode.handleInventoryMouseClick(this.A.containerId, this.D, 0, ClickType.QUICK_MOVE, (Player)ChestStealer.mc.player);
            }
            y.a();
            x.a();
            k.a();
        }
    }

    private void c(ChestMenu chestMenu) {
        this.j();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        if (((Boolean)this.smartStealing.getValue()).booleanValue()) {
            this.e(chestMenu);
            for (B object2 : this.J) {
                arrayList.add(object2.a());
            }
        } else {
            arrayList.addAll(this.g(chestMenu));
            if (((Boolean)this.randomClick.getValue()).booleanValue()) {
                Collections.shuffle(arrayList, this.z);
            }
        }
        boolean bl = false;
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            int n2 = (Integer)iterator.next();
            ItemStack itemStack = chestMenu.getSlot(n2).getItem();
            if (itemStack.isEmpty() || !this.d(itemStack) || !ChestStealer.f(itemStack) && !((Boolean)this.pickTrash.getValue()).booleanValue()) continue;
            this.b((AbstractContainerMenu)chestMenu, n2);
            bl = true;
        }
        this.L = false;
        this.J.clear();
        this.K = 0;
        if (bl) {
            F = 0L;
            y.a();
            x.a();
            k.a();
        }
    }

    private void a(AbstractContainerMenu abstractContainerMenu, int n2) {
        if (!this.B) {
            this.A = abstractContainerMenu;
            this.D = n2;
            this.B = true;
            this.E = 0;
        }
    }

    private boolean d(ItemStack itemStack) {
        int n2;
        ItemStack itemStack2;
        Item item = itemStack.getItem();
        if (item == Items.TNT) {
            return true;
        }
        if (((Boolean)this.noTools.getValue()).booleanValue() && item instanceof DiggerItem && !d.p(itemStack)) {
            return false;
        }
        if (item instanceof AxeItem && (itemStack2 = d.s()) != null && d.v(itemStack) <= d.v(itemStack2)) {
            return false;
        }
        if (item instanceof FishingRodItem && (n2 = d.c(Items.FISHING_ROD)) > 0) {
            return false;
        }
        if (item instanceof BlockItem && item != Items.COBWEB) {
            n2 = InventoryManager.I();
            if (this.C + itemStack.getCount() > n2) {
                return false;
            }
        }
        if (((Boolean)this.onlyBest.getValue()).booleanValue()) {
            if (d.l(itemStack) || d.q(itemStack)) {
                return true;
            }
            if (item instanceof SwordItem) {
                return this.e(itemStack);
            }
            if (item instanceof DiggerItem) {
                return this.e(itemStack);
            }
            if (item instanceof ArmorItem) {
                return this.e(itemStack);
            }
            if (item instanceof BowItem) {
                return this.e(itemStack);
            }
            if (item instanceof CrossbowItem) {
                return this.e(itemStack);
            }
            if (!(item instanceof SwordItem || item instanceof DiggerItem || item instanceof ArmorItem || item instanceof BowItem || item instanceof CrossbowItem)) {
                return ChestStealer.f(itemStack) || (Boolean)this.pickTrash.getValue() != false;
            }
        }
        return true;
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        ContainerScreen containerScreen;
        if (mc == null || ChestStealer.mc.player == null || ChestStealer.mc.level == null || ChestStealer.mc.gameMode == null || mc.getConnection() == null || KillAura.k != null || Scaffold.INSTANCE.isEnabled()) {
            return;
        }
        if (!y.c((int)F) || !ChestStealer.mc.player.isAlive() || ChestStealer.mc.player.isDeadOrDying() || ChestStealer.mc.player.isSpectator()) {
            return;
        }
        Screen screen = ChestStealer.mc.screen;
        AbstractContainerMenu abstractContainerMenu = ChestStealer.mc.player.containerMenu;
        if (motionEvent.isPre()) {
            return;
        }
        this.k();
        if (screen instanceof ContainerScreen) {
            containerScreen = (ContainerScreen)screen;
            if (screen != this.H) {
                k.a();
                this.I = 0;
                this.L = false;
                this.J.clear();
                this.K = 0;
            } else {
                ++this.I;
                if (this.I < ((Number)this.openDelay.getValue()).intValue()) {
                    return;
                }
                String string = containerScreen.getTitle().getString();
                String[] stringArray = M;
                String string2 = Component.translatable((String)"container.chest").getString();
                String string3 = Component.translatable((String)"container.chestDouble").getString();
                String string4 = Component.translatable((String)"container.enderchest").getString();
                ChestMenu chestMenu = (ChestMenu)containerScreen.getMenu();
                if (((Boolean)this.chest.getValue()).booleanValue() && (string.equals(string2) || string.equals(string3) || string.equals("Chest"))) {
                    if (this.a(chestMenu)) {
                        this.b(chestMenu);
                    }
                } else if (((Boolean)this.enderChest.getValue()).booleanValue() && string.equals(string4) && this.a(chestMenu)) {
                    this.b(chestMenu);
                }
            }
        } else {
            this.I = 0;
            this.L = false;
            this.J.clear();
            this.K = 0;
        }
        if (abstractContainerMenu instanceof FurnaceMenu) {
            containerScreen = (FurnaceMenu)abstractContainerMenu;
            if (((Boolean)this.furnace.getValue()).booleanValue()) {
                this.a((FurnaceMenu)containerScreen);
            }
        }
        if (abstractContainerMenu instanceof BrewingStandMenu) {
            containerScreen = (BrewingStandMenu)abstractContainerMenu;
            if (((Boolean)this.brewingStand.getValue()).booleanValue()) {
                this.a((BrewingStandMenu)containerScreen);
            }
        }
        this.H = screen;
    }

    private void b(ChestMenu chestMenu) {
        ++this.G;
        if (this.clickMode.is("Instant")) {
            this.c(chestMenu);
            return;
        }
        if (((Boolean)this.smartStealing.getValue()).booleanValue() && this.G > 1) {
            this.d(chestMenu);
        } else {
            this.f(chestMenu);
        }
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x4A);
        }
        return cArray;
    }

    @Override
    public void onDisable() {
        this.i();
    }
}

