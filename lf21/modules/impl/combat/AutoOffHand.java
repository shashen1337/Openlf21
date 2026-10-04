/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import java.util.concurrent.TimeUnit;
import lf21.event.EventTarget;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.b;
import lf21.modules.impl.combat.r;
import lf21.settings.impl.MultiSelectSetting;
import lf21.utils.misc.PacketUtil;
import lf21.utils.misc.h;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundPickItemPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class AutoOffHand
extends Module {
    public static AutoOffHand j;
    public final MultiSelectSetting items;
    private int l;
    private static final String[] m;

    private int j() {
        if (AutoOffHand.mc.player == null) {
            return -1;
        }
        int n2 = -1;
        int n3 = -1;
        for (int i2 = 0; i2 < AutoOffHand.mc.player.getInventory().items.size(); ++i2) {
            ItemStack itemStack = (ItemStack)AutoOffHand.mc.player.getInventory().items.get(i2);
            if (!this.a(itemStack) || itemStack.getCount() <= n3) continue;
            n2 = i2;
            n3 = itemStack.getCount();
        }
        return n2;
    }

    private boolean a(Entity entity) {
        Vec3 vec3;
        if (AutoOffHand.mc.player == null || AutoOffHand.mc.level == null) {
            return false;
        }
        Vec3 vec32 = AutoOffHand.mc.player.getEyePosition();
        return AutoOffHand.mc.level.clip(new ClipContext(vec32, vec3 = entity.getEyePosition(), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)AutoOffHand.mc.player)).getType() == HitResult.Type.MISS;
    }

    private boolean a() {
        for (int i2 = 0; i2 < 9; ++i2) {
            if (!AutoOffHand.mc.player.getInventory().getItem(i2).isEmpty()) continue;
            return true;
        }
        return false;
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
                    case 0 -> 124;
                    case 1 -> 122;
                    case 2 -> 84;
                    case 3 -> 37;
                    case 4 -> 100;
                    case 5 -> 72;
                    default -> 49;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private int a(b b2) {
        if (b2 == b.BLOCKS) {
            return this.j();
        }
        for (int i2 = 0; i2 < AutoOffHand.mc.player.getInventory().getContainerSize(); ++i2) {
            ItemStack itemStack = AutoOffHand.mc.player.getInventory().getItem(i2);
            if (!this.a(itemStack, b2)) continue;
            return i2;
        }
        return -1;
    }

    private void d(int n2) {
        if (AutoOffHand.mc.player == null || AutoOffHand.mc.gameMode == null || n2 < 0) {
            return;
        }
        int n3 = this.c(n2);
        if (n3 < 0) {
            return;
        }
        AutoOffHand.mc.gameMode.handleInventoryMouseClick(AutoOffHand.mc.player.inventoryMenu.containerId, n3, 40, ClickType.SWAP, (Player)AutoOffHand.mc.player);
        this.l = 3;
    }

    private boolean k() {
        int n2;
        int n3;
        if (AutoOffHand.mc.player == null || AutoOffHand.mc.gameMode == null || !AutoOffHand.mc.player.inventoryMenu.getCarried().isEmpty()) {
            return false;
        }
        int n4 = -1;
        int n5 = -1;
        int n6 = -1;
        for (n3 = 0; n3 <= 36; ++n3) {
            n2 = n3 == 36 ? 40 : n3;
            ItemStack itemStack = this.b(n2);
            if (!this.a(itemStack) || itemStack.getCount() >= itemStack.getMaxStackSize()) continue;
            for (int i2 = n3 + 1; i2 <= 36; ++i2) {
                int n7 = i2 == 36 ? 40 : i2;
                ItemStack itemStack2 = this.b(n7);
                if (!this.a(itemStack2) || !ItemStack.isSameItemSameTags((ItemStack)itemStack, (ItemStack)itemStack2)) continue;
                int n8 = itemStack.getCount() >= itemStack2.getCount() ? n2 : n7;
                int n9 = n8 == n2 ? n7 : n2;
                ItemStack itemStack3 = this.b(n8);
                ItemStack itemStack4 = this.b(n9);
                int n10 = Math.min(itemStack3.getMaxStackSize(), itemStack3.getCount() + itemStack4.getCount());
                if (n10 <= itemStack3.getCount() || n10 <= n6) continue;
                n4 = n9;
                n5 = n8;
                n6 = n10;
            }
        }
        if (n4 < 0 || n5 < 0) {
            return false;
        }
        n3 = this.c(n4);
        n2 = this.c(n5);
        if (n3 < 0 || n2 < 0) {
            return false;
        }
        int n11 = AutoOffHand.mc.player.inventoryMenu.containerId;
        AutoOffHand.mc.gameMode.handleInventoryMouseClick(n11, n3, 0, ClickType.PICKUP, (Player)AutoOffHand.mc.player);
        AutoOffHand.mc.gameMode.handleInventoryMouseClick(n11, n2, 0, ClickType.PICKUP, (Player)AutoOffHand.mc.player);
        if (!AutoOffHand.mc.player.inventoryMenu.getCarried().isEmpty()) {
            AutoOffHand.mc.gameMode.handleInventoryMouseClick(n11, n3, 0, ClickType.PICKUP, (Player)AutoOffHand.mc.player);
        }
        return true;
    }

    private int c(int n2) {
        if (n2 >= 0 && n2 < 9) {
            return n2 + 36;
        }
        if (n2 >= 9 && n2 < 36) {
            return n2;
        }
        if (n2 == 40) {
            return 45;
        }
        return -1;
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (!this.isEnabled() || AutoOffHand.mc.player == null || AutoOffHand.mc.level == null || AutoOffHand.mc.player.isUsingItem()) {
            return;
        }
        if (this.l > 0) {
            --this.l;
            return;
        }
        if (AutoOffHand.mc.screen instanceof InventoryScreen) {
            if (this.items.isSelected("Blocks")) {
                if (this.k()) {
                    this.l = 3;
                    return;
                }
                int n2 = this.j();
                if (this.a(n2)) {
                    this.d(n2);
                }
            }
            return;
        }
        if (AutoOffHand.mc.screen instanceof AbstractContainerScreen) {
            return;
        }
        boolean bl = this.i();
        b b2 = this.a(bl);
        if (b2 == b.NONE) {
            return;
        }
        ItemStack itemStack = AutoOffHand.mc.player.getMainHandItem();
        if (b2 != b.BLOCKS && this.a(itemStack, b2)) {
            return;
        }
        ItemStack itemStack2 = AutoOffHand.mc.player.getOffhandItem();
        if (b2 == b.BLOCKS) {
            return;
        }
        if (this.a(itemStack2, b2)) {
            return;
        }
        int n3 = this.a(b2);
        if (n3 != -1) {
            this.e(n3);
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
                var2_2 = "/\u0014;R&)]\u0010\u0006>\u0016;F\u000f;\u000b=\u000f J+.W4\u001b:A\b/\u0014;R&)]\u0010\u00055\u000e1H\u0017\u0006;\u001b$U\b-";
                var4_3 = "/\u0014;R&)]\u0010\u0006>\u0016;F\u000f;\u000b=\u000f J+.W4\u001b:A\b/\u0014;R&)]\u0010\u00055\u000e1H\u0017\u0006;\u001b$U\b-".length();
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
                    var5[var3_1++] = AutoOffHand.c(AutoOffHand.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = ";\u001b$U\b-\u0006>\u0016;F\u000f;";
                    var4_3 = ";\u001b$U\b-\u0006>\u0016;F\u000f;".length();
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
                    var5[var3_1++] = AutoOffHand.c(AutoOffHand.a(v1));
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
        AutoOffHand.m = var5;
    }

    private ItemStack b(int n2) {
        if (n2 == 40) {
            return AutoOffHand.mc.player.getOffhandItem();
        }
        return n2 >= 0 && n2 < AutoOffHand.mc.player.getInventory().items.size() ? (ItemStack)AutoOffHand.mc.player.getInventory().items.get(n2) : ItemStack.EMPTY;
    }

    private b a(boolean bl) {
        if (AutoOffHand.mc.player.getHealth() < 10.0f) {
            String[] stringArray = m;
            if (this.items.isSelected("Gapple")) {
                return b.GAPPLE;
            }
        } else if (AutoOffHand.mc.player.hasEffect(MobEffects.REGENERATION)) {
            String[] stringArray = m;
            if (this.items.isSelected("SnowBall")) {
                return b.SNOWBALL;
            }
        } else if (bl) {
            String[] stringArray = m;
            if (this.items.isSelected("SnowBall")) {
                return b.SNOWBALL;
            }
        } else {
            String[] stringArray = m;
            if (this.items.isSelected("Gapple")) {
                return b.GAPPLE;
            }
        }
        if (this.items.isSelected("Blocks")) {
            return b.BLOCKS;
        }
        return b.NONE;
    }

    private int l() {
        if (mc.getConnection() == null || mc.getConnection().getPlayerInfo(AutoOffHand.mc.player.getUUID()) == null) {
            return 50;
        }
        return mc.getConnection().getPlayerInfo(AutoOffHand.mc.player.getUUID()).getLatency();
    }

    private int h() {
        for (int i2 = 0; i2 < 9; ++i2) {
            if (!AutoOffHand.mc.player.getInventory().getItem(i2).isEmpty()) continue;
            return i2;
        }
        return -1;
    }

    private boolean a(ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (!(item instanceof BlockItem)) {
            return false;
        }
        BlockItem blockItem = (BlockItem)item;
        if (blockItem.getBlock() instanceof FallingBlock) {
            return false;
        }
        return blockItem.getBlock().defaultBlockState().isCollisionShapeFullBlock((BlockGetter)EmptyBlockGetter.INSTANCE, BlockPos.ZERO);
    }

    private void e(int n2) {
        if (n2 == -1 || this.l > 0) {
            return;
        }
        if (AutoOffHand.mc.screen instanceof AbstractContainerScreen) {
            return;
        }
        if (n2 >= 9 && this.h() == -1) {
            return;
        }
        AutoOffHand.mc.options.keyUse.setDown(false);
        int n3 = AutoOffHand.mc.player.getInventory().selected;
        if (n2 >= 9) {
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPickItemPacket(n2));
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN));
            h.a(() -> mc.execute(() -> {
                AutoOffHand.mc.player.getInventory().selected = n3;
            }), 200L, TimeUnit.MILLISECONDS);
        } else {
            AutoOffHand.mc.player.getInventory().selected = n2;
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundSetCarriedItemPacket(AutoOffHand.mc.player.getInventory().selected));
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN));
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundSetCarriedItemPacket(n3));
            AutoOffHand.mc.player.getInventory().selected = n3;
        }
        h.a(() -> {
            this.l = (int)(3.0f + (float)this.l() / 25.0f);
        });
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x31);
        }
        return cArray;
    }

    private boolean a(ItemStack itemStack, b b2) {
        if (itemStack == null || itemStack.isEmpty()) {
            return false;
        }
        return switch (r.a[b2.ordinal()]) {
            case 1 -> {
                if (itemStack.getItem() == Items.SNOWBALL || itemStack.getItem() == Items.EGG) {
                    yield true;
                }
                yield false;
            }
            case 2 -> {
                if (itemStack.getItem() == Items.GOLDEN_APPLE || itemStack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) {
                    yield true;
                }
                yield false;
            }
            case 3 -> this.a(itemStack);
            default -> false;
        };
    }

    private boolean i() {
        if (AutoOffHand.mc.level == null || AutoOffHand.mc.player == null) {
            return false;
        }
        double d2 = 6.0;
        return AutoOffHand.mc.level.players().stream().anyMatch(abstractClientPlayer -> !abstractClientPlayer.equals((Object)AutoOffHand.mc.player) && !abstractClientPlayer.isRemoved() && AutoOffHand.mc.player.distanceToSqr((Entity)abstractClientPlayer) <= d2 * d2 && this.a((Entity)abstractClientPlayer));
    }

    private boolean a(int n2) {
        if (AutoOffHand.mc.player == null || n2 < 0) {
            return false;
        }
        ItemStack itemStack = (ItemStack)AutoOffHand.mc.player.getInventory().items.get(n2);
        ItemStack itemStack2 = AutoOffHand.mc.player.getOffhandItem();
        return this.a(itemStack) && (!this.a(itemStack2) || itemStack.getCount() > itemStack2.getCount());
    }

    public AutoOffHand() {
        String[] stringArray = m;
        super("AutoOffHand", Category.COMBAT);
        this.items = new MultiSelectSetting("Items", "Gapple", "SnowBall", "Blocks").withDefaults("Gapple", "SnowBall");
        this.l = 0;
        j = this;
    }
}

