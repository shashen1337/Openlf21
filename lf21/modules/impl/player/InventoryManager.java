/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.SprintEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.GuiMove;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.C;
import lf21.modules.impl.player.ChestStealer;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.h;
import lf21.utils.game.a;
import lf21.utils.game.d;
import lf21.utils.game.e;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.SlabBlock;
import org.apache.commons.lang3.tuple.Pair;

public class InventoryManager
extends Module {
    private boolean ai;
    private final NumberSetting A;
    public static boolean O;
    private boolean U;
    private long Z;
    private final NumberSetting axeSlot;
    private final NumberSetting G;
    private final BooleanSetting inventoryOnly;
    private final BooleanSetting autoArmor;
    private boolean af;
    private final NumberSetting delay;
    private static final String[] cb;
    private boolean W;
    private int ab;
    private int M;
    private final BooleanSetting fastThrow;
    private final NumberSetting openDelay;
    private final NumberSetting D;
    private boolean Y;
    private int ae;
    private int ag;
    private final ModeSetting bowPriority;
    private final NumberSetting dropDelay;
    private C aa;
    private final NumberSetting H;
    private boolean Q;
    private boolean L;
    private static final h J;
    private final NumberSetting I;
    private int T;
    private final NumberSetting swordSlot;
    private final ModeSetting offhandItems;
    private int N;
    private final NumberSetting F;
    private final NumberSetting maxEggsSnowballsSize;
    private final NumberSetting E;
    private final NumberSetting C;
    private boolean R;
    private int V;
    private boolean X;
    public static InventoryManager j;
    private final Queue<Packet<ServerGamePacketListener>> S;
    private boolean ad;
    private final NumberSetting blockSlot;
    private boolean P;
    private boolean ac;
    public final NumberSetting maxBlockSize;
    private int ah;
    private final NumberSetting B;
    private final BooleanSetting throwItems;
    private final NumberSetting maxRodSize;
    private boolean K;
    private final NumberSetting maxFoodSize;

    private boolean a(int n2) {
        if (InventoryManager.mc.gameMode == null || InventoryManager.mc.player == null) {
            return false;
        }
        ItemStack itemStack = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n2);
        ItemStack itemStack2 = (ItemStack)InventoryManager.mc.player.getInventory().offhand.get(0);
        if (this.b(itemStack) || this.b(itemStack2)) {
            return false;
        }
        int n3 = n2 < 9 ? n2 + 36 : n2;
        this.a(n3, 40, ClickType.SWAP);
        this.K = true;
        J.a();
        return true;
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
                    case 0 -> 82;
                    case 1 -> 18;
                    case 2 -> 88;
                    case 3 -> 62;
                    case 4 -> 50;
                    case 5 -> 102;
                    default -> 12;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean h() {
        return (Boolean)this.throwItems.getValue();
    }

    private int p() {
        return ((Number)this.E.getValue()).intValue();
    }

    private int D() {
        return ((Number)this.delay.getValue()).intValue();
    }

    private int s() {
        return ((Number)this.maxEggsSnowballsSize.getValue()).intValue();
    }

    private int n() {
        return ((Number)this.C.getValue()).intValue();
    }

    private boolean b(ItemStack itemStack) {
        return this.H() && itemStack != null && !itemStack.isEmpty() && itemStack.getItem() instanceof BlockItem;
    }

    private int j() {
        return ((Number)this.blockSlot.getValue()).intValue();
    }

    public boolean c(ItemStack itemStack) {
        BlockItem blockItem;
        if (itemStack.isEmpty()) {
            return false;
        }
        if (d.l(itemStack)) {
            return true;
        }
        if (itemStack.getDisplayName().getString().contains("\u70b9\u51fb\u4f7f\u7528")) {
            return true;
        }
        if (itemStack.getItem() == Items.COBWEB || itemStack.getItem() == Items.TNT) {
            return true;
        }
        Item item = itemStack.getItem();
        if (item instanceof BlockItem && (blockItem = (BlockItem)item).getBlock() instanceof SlabBlock) {
            return false;
        }
        if (itemStack.getItem() == Items.WHEAT_SEEDS || itemStack.getItem() == Items.BEETROOT_SEEDS || itemStack.getItem() == Items.MELON_SEEDS || itemStack.getItem() == Items.PUMPKIN_SEEDS || itemStack.getItem() == Items.TORCHFLOWER_SEEDS || itemStack.getItem() == Items.PITCHER_POD) {
            return false;
        }
        item = itemStack.getItem();
        if (item instanceof ArmorItem) {
            blockItem = (ArmorItem)item;
            float f2 = d.j(itemStack);
            if (d.b(blockItem.getEquipmentSlot()) >= f2) {
                return false;
            }
            return f2 >= d.a(blockItem.getEquipmentSlot());
        }
        if (itemStack.getItem() instanceof SwordItem) {
            return d.d() == itemStack;
        }
        if (itemStack.getItem() instanceof PickaxeItem) {
            return d.p() == itemStack;
        }
        if (itemStack.getItem() instanceof AxeItem) {
            return d.s() == itemStack;
        }
        if (itemStack.getItem() instanceof ShovelItem) {
            return d.v() == itemStack;
        }
        if (itemStack.getItem() instanceof CrossbowItem) {
            return d.x() == itemStack;
        }
        if (itemStack.getItem() instanceof BowItem && d.w(itemStack)) {
            return d.z() == itemStack;
        }
        if (itemStack.getItem() instanceof BowItem && d.x(itemStack)) {
            return d.B() == itemStack;
        }
        if (itemStack.getItem() instanceof BowItem && d.c(Items.BOW) > 1) {
            return false;
        }
        if (itemStack.getItem() == Items.WATER_BUCKET && d.c(Items.WATER_BUCKET) > InventoryManager.L()) {
            return false;
        }
        if (itemStack.getItem() == Items.LAVA_BUCKET && d.c(Items.LAVA_BUCKET) > InventoryManager.M()) {
            return false;
        }
        if (itemStack.getItem() instanceof FishingRodItem && d.c(Items.FISHING_ROD) > 1) {
            return false;
        }
        if (itemStack.getItem() instanceof ItemNameBlockItem) {
            return false;
        }
        return d.b(itemStack);
    }

    private int v() {
        return 1;
    }

    private int w() {
        return ((Number)this.dropDelay.getValue()).intValue();
    }

    private int l() {
        return ((Number)this.A.getValue()).intValue();
    }

    private boolean B() {
        if (InventoryManager.mc.player == null) {
            return false;
        }
        for (ItemStack itemStack : InventoryManager.mc.player.getInventory().items) {
            if (!itemStack.isEmpty()) continue;
            return true;
        }
        return false;
    }

    private boolean E() {
        this.Z = System.currentTimeMillis();
        this.ab = 0;
        if (this.W || InventoryManager.mc.player == null) {
            return false;
        }
        boolean bl = this.ac;
        boolean bl2 = this.ad;
        this.ac = false;
        this.ad = false;
        this.ae = 0;
        this.W = true;
        this.ai = false;
        this.ah = 0;
        this.Y = bl2 || InventoryManager.mc.options.keySprint.isDown();
        this.af = this.X = bl || this.P || InventoryManager.mc.player.isSprinting() || this.Y;
        this.ag = InventoryManager.mc.player.tickCount + 3;
        return this.X;
    }

    public static int J() {
        return j != null ? j.s() : 64;
    }

    private boolean a() {
        return (Boolean)this.autoArmor.getValue();
    }

    public static int I() {
        return j != null ? j.r() : 256;
    }

    private void F() {
        if (!this.W) {
            return;
        }
        long l2 = Math.max(250L, (long)Math.max(this.D(), this.w()) + 250L);
        if (this.ai && System.currentTimeMillis() - this.Z >= l2) {
            if (++this.ab >= 3) {
                this.G();
                return;
            }
        } else {
            this.ab = 0;
        }
        if (InventoryManager.mc.player != null) {
            InventoryManager.mc.options.keySprint.setDown(false);
            InventoryManager.mc.player.setSprinting(false);
        }
    }

    private int m() {
        return ((Number)this.B.getValue()).intValue();
    }

    private int t() {
        return 256;
    }

    private boolean a(ItemStack itemStack) {
        if (InventoryManager.mc.gameMode == null || InventoryManager.mc.player == null) {
            return false;
        }
        if (!this.h() || itemStack == null || itemStack.isEmpty()) {
            return false;
        }
        if (this.b(itemStack)) {
            return false;
        }
        if (!J.c(this.w()) && !((Boolean)this.fastThrow.getValue()).booleanValue()) {
            return false;
        }
        int n2 = d.a(itemStack);
        if (n2 == -1) {
            return false;
        }
        int n3 = n2 < 9 ? n2 + 36 : n2;
        this.a(n3, 1, ClickType.THROW);
        this.K = true;
        J.a();
        return true;
    }

    private boolean C() {
        ItemStack itemStack;
        int n2;
        if (InventoryManager.mc.player == null || InventoryManager.mc.gameMode == null || !J.c(this.D())) {
            return false;
        }
        for (n2 = 9; n2 < InventoryManager.mc.player.getInventory().items.size(); ++n2) {
            itemStack = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n2);
            if (itemStack.isEmpty() || this.b(itemStack) || this.c(itemStack)) continue;
            this.a(n2, 1, ClickType.THROW);
            this.K = true;
            J.a();
            return true;
        }
        for (n2 = 0; n2 < 9; ++n2) {
            itemStack = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n2);
            if (itemStack.isEmpty() || this.b(itemStack) || this.c(itemStack)) continue;
            this.a(n2 + 36, 1, ClickType.THROW);
            this.K = true;
            J.a();
            return true;
        }
        return false;
    }

    private boolean a(int n2, Item item) {
        if (InventoryManager.mc.gameMode == null || InventoryManager.mc.player == null) {
            return false;
        }
        ItemStack itemStack = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n2);
        if (this.b(itemStack)) {
            return false;
        }
        if (!J.c(this.D())) {
            return false;
        }
        int n3 = d.a(item);
        if (n3 == -1) {
            return false;
        }
        ItemStack itemStack2 = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n3);
        if (this.b(itemStack2)) {
            return false;
        }
        if (itemStack.getItem() != item || itemStack.getItem() == item && itemStack.getCount() < itemStack2.getCount()) {
            int n4 = n3 < 9 ? n3 + 36 : n3;
            this.a(n4, n2, ClickType.SWAP);
            this.K = true;
            J.a();
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void b(int n2, int n3, ClickType clickType) {
        this.U = true;
        try {
            InventoryManager.mc.gameMode.handleInventoryMouseClick(InventoryManager.mc.player.inventoryMenu.containerId, n2, n3, clickType, (Player)InventoryManager.mc.player);
            this.Z = System.currentTimeMillis();
            if (this.W) {
                this.ai = true;
                this.ah = 0;
            }
        }
        finally {
            this.U = false;
        }
    }

    private void a(int n2, int n3, ClickType clickType) {
        if (InventoryManager.mc.gameMode == null || InventoryManager.mc.player == null) {
            return;
        }
        boolean bl = (Boolean)this.inventoryOnly.getValue() == false && InventoryManager.mc.screen == null;
        boolean bl2 = InventoryManager.mc.player.isSprinting() || this.P || InventoryManager.mc.options.keySprint.isDown();
        boolean bl3 = true;
        if (bl && bl3 && bl2 && !this.W) {
            this.E();
            this.aa = new C(n2, n3, clickType, this.ag);
            return;
        }
        this.b(n2, n3, clickType);
    }

    public static int K() {
        return j != null ? j.t() : 256;
    }

    private boolean a(Packet<?> packet) {
        return packet instanceof ServerboundContainerClickPacket || packet instanceof ServerboundUseItemOnPacket || packet instanceof ServerboundUseItemPacket || packet instanceof ServerboundInteractPacket || packet instanceof ServerboundPlayerActionPacket;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[45];
                var3_1 = 0;
                var2_2 = "\u0017|<[@F\\7s*R\u00125`=f\f\u0015}4ZW\b,\u0013b(RW\u0015\u0017u?M\u0012@,\u0001|7IP\u0007`>axm^\tx\u0004\u001c}6[\b\u0011`7MA\u0004c%\u0012\u0000w4[S\u0015e<uxnS\u0005g7fb\u001e\f\u0011`!MF\u0007`rA4QF\u0005\u0010~7]Y\u000b\u0014{+V[\bkr@7Z\n\u0010~7]YF_>},\n\u0016`7N\u0012\"i>s!\f\u0010}/\u001eb\u0014e=`1JK\r\u001dt>VS\bhr[,[_\u0015\u000b\u0014{+V[\bkr@7Z\u0005\u0016w4_K\u0005\u0011z=MF\f\u0015}4ZW\b,\u0013b(RW\r\u001fs \u001et\tc62\u000bWH\u0003\b\u0010}/\u001ea\nc&\f\u001fs \u001e`\thrA1DW\u000f\u0001~1SWFN3~4\u001ea\nc&\u000e\u001fs \u001ep\nc1yxm[\u001ci\u0005\u0010~7]Y\u0011\u0015}4ZW\b,\u0013b(RWF_>},\u000f1}6JS\u000fb7`v]Z\u0003\u007f&\u0005\u0002g6]Z\b\u0013j=\u001ea\nc&\u0010\u001b|.[\\\u0012c k\u0015_\\\u0007k7`\u0004\u70eb\u51e9\u4f27\u7516\n\u0014s+J\u00122d }/\u0019\u001fs \u001ew\u0001k!2~\u001ea\bc%p9R^\u0015,\u0001{\"[\n\u0013g,Q\u0012'~?}*\b\u0011`7MA\u0004c%\f\u0002{;US\u001eirA4QF\u0011\u0005s,[@FN'q3[FF_>},\n\u0002`7TW\u0005x;~=\u000e\u001b|.[\\\u0012c kxq\\\nu\t\u0002g6]ZFN=e\u000b\u0011}:IW\u0004,\u0001~7J\n\u0001e7LVF_>},\u000b\u0006z*QEFE&w5M\u00151}6JS\u000fb7`v]Z\u0003\u007f&V7KP\ni\n\u0002`7TW\u0005x;~=";
                var4_3 = "\u0017|<[@F\\7s*R\u00125`=f\f\u0015}4ZW\b,\u0013b(RW\u0015\u0017u?M\u0012@,\u0001|7IP\u0007`>axm^\tx\u0004\u001c}6[\b\u0011`7MA\u0004c%\u0012\u0000w4[S\u0015e<uxnS\u0005g7fb\u001e\f\u0011`!MF\u0007`rA4QF\u0005\u0010~7]Y\u000b\u0014{+V[\bkr@7Z\n\u0010~7]YF_>},\n\u0016`7N\u0012\"i>s!\f\u0010}/\u001eb\u0014e=`1JK\r\u001dt>VS\bhr[,[_\u0015\u000b\u0014{+V[\bkr@7Z\u0005\u0016w4_K\u0005\u0011z=MF\f\u0015}4ZW\b,\u0013b(RW\r\u001fs \u001et\tc62\u000bWH\u0003\b\u0010}/\u001ea\nc&\f\u001fs \u001e`\thrA1DW\u000f\u0001~1SWFN3~4\u001ea\nc&\u000e\u001fs \u001ep\nc1yxm[\u001ci\u0005\u0010~7]Y\u0011\u0015}4ZW\b,\u0013b(RWF_>},\u000f1}6JS\u000fb7`v]Z\u0003\u007f&\u0005\u0002g6]Z\b\u0013j=\u001ea\nc&\u0010\u001b|.[\\\u0012c k\u0015_\\\u0007k7`\u0004\u70eb\u51e9\u4f27\u7516\n\u0014s+J\u00122d }/\u0019\u001fs \u001ew\u0001k!2~\u001ea\bc%p9R^\u0015,\u0001{\"[\n\u0013g,Q\u0012'~?}*\b\u0011`7MA\u0004c%\f\u0002{;US\u001eirA4QF\u0011\u0005s,[@FN'q3[FF_>},\n\u0002`7TW\u0005x;~=\u000e\u001b|.[\\\u0012c kxq\\\nu\t\u0002g6]ZFN=e\u000b\u0011}:IW\u0004,\u0001~7J\n\u0001e7LVF_>},\u000b\u0006z*QEFE&w5M\u00151}6JS\u000fb7`v]Z\u0003\u007f&V7KP\ni\n\u0002`7TW\u0005x;~=".length();
                var1_4 = 16;
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
                    var5[var3_1++] = InventoryManager.c(InventoryManager.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0002g6]ZFN=e\n\u001db=P\u0012\"i>s!";
                    var4_3 = "\u0002g6]ZFN=e\n\u001db=P\u0012\"i>s!".length();
                    var1_4 = 9;
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
                    var5[var3_1++] = InventoryManager.c(InventoryManager.a(v1));
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
        InventoryManager.cb = var5;
        InventoryManager.J = new h();
        InventoryManager.O = false;
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        ServerboundPlayerCommandPacket serverboundPlayerCommandPacket;
        Packet<?> packet = packetEvent.c();
        if (InventoryManager.mc.player == null || mc.getConnection() == null) {
            return;
        }
        if (!packetEvent.b()) {
            if (packet instanceof ClientboundOpenScreenPacket) {
                this.V = Math.max(this.V, 5);
                O = false;
            }
            return;
        }
        if (this.U && packet instanceof ServerboundContainerClickPacket) {
            return;
        }
        if (this.a(packet)) {
            this.V = Math.max(this.V, 4);
            O = false;
            this.G();
        }
        if (packet instanceof ServerboundContainerClosePacket) {
            this.K = false;
            this.N = 0;
        }
        if (packet instanceof ServerboundPlayerCommandPacket) {
            serverboundPlayerCommandPacket = (ServerboundPlayerCommandPacket)packet;
            if (serverboundPlayerCommandPacket.getAction() == ServerboundPlayerCommandPacket.Action.START_SPRINTING) {
                if (this.W) {
                    this.X = true;
                    if (!this.af) {
                        this.P = false;
                        InventoryManager.mc.options.keySprint.setDown(false);
                        InventoryManager.mc.player.setSprinting(false);
                        packetEvent.a(true);
                        return;
                    }
                }
                this.P = true;
            } else if (serverboundPlayerCommandPacket.getAction() == ServerboundPlayerCommandPacket.Action.STOP_SPRINTING) {
                this.P = false;
            }
        }
        if (!GuiMove.j.isEnabled()) {
            return;
        }
        if (O && packet instanceof ServerboundPlayerInputPacket) {
            serverboundPlayerCommandPacket = (ServerboundPlayerInputPacket)packet;
            if (InventoryManager.mc.player.isSprinting()) {
                InventoryManager.mc.player.setSprinting(false);
                packetEvent.a(true);
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerInputPacket(serverboundPlayerCommandPacket.getXxa(), serverboundPlayerCommandPacket.getZza(), serverboundPlayerCommandPacket.isJumping(), serverboundPlayerCommandPacket.isShiftKeyDown()));
                return;
            }
        }
    }

    private int k() {
        return ((Number)this.axeSlot.getValue()).intValue();
    }

    private int r() {
        return ((Number)this.maxBlockSize.getValue()).intValue();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xC);
        }
        return cArray;
    }

    public static boolean y() {
        return j != null && InventoryManager.j.W;
    }

    private int i() {
        return ((Number)this.swordSlot.getValue()).intValue();
    }

    protected void x() {
        this.G();
        if (this.ac && InventoryManager.mc.player != null) {
            InventoryManager.mc.options.keySprint.setDown(this.ad);
        }
        this.ac = false;
        this.ad = false;
        this.ae = 0;
        this.af = false;
        this.T = 0;
        O = false;
        this.Q = false;
        this.R = false;
        this.P = false;
        this.U = false;
        this.V = 0;
        this.Z = 0L;
        super.onDisable();
    }

    public static int L() {
        return j != null ? j.u() : 1;
    }

    @EventTarget
    public void b(MotionEvent motionEvent) {
        boolean bl;
        String string;
        if (!motionEvent.isPost() || InventoryManager.mc.player == null || mc.getConnection() == null || InventoryManager.mc.gameMode == null) {
            return;
        }
        this.F();
        if (this.V > 0) {
            --this.V;
            O = false;
            return;
        }
        if (this.aa != null) {
            if (InventoryManager.mc.screen != null && !(InventoryManager.mc.screen instanceof InventoryScreen)) {
                this.aa = null;
                this.G();
                O = false;
                return;
            }
            if (InventoryManager.mc.player.tickCount < this.aa.d()) {
                O = true;
                return;
            }
            C c2 = this.aa;
            this.aa = null;
            this.b(c2.a(), c2.b(), c2.c());
            this.Z = System.currentTimeMillis();
            O = true;
            return;
        }
        if (!this.z()) {
            O = false;
            this.G();
            this.setEnabled(false);
            this.Q = true;
            return;
        }
        if (d.a()) {
            O = false;
            this.G();
            this.Q = true;
            return;
        }
        this.M = e.g() ? 0 : ++this.M;
        boolean bl2 = false;
        AbstractContainerMenu abstractContainerMenu = InventoryManager.mc.player.containerMenu;
        Object object = InventoryManager.mc.screen;
        if (object instanceof ContainerScreen) {
            ContainerScreen containerScreen = (ContainerScreen)object;
            object = containerScreen.getTitle().getString();
            String[] stringArray = cb;
            string = Component.translatable((String)"container.chest").getString();
            String string2 = Component.translatable((String)"container.chestDouble").getString();
            if (((String)object).equals(string) || ((String)object).equals(string2) || ((String)object).equals("Chest")) {
                bl2 = true;
            }
        }
        if (abstractContainerMenu instanceof FurnaceMenu || abstractContainerMenu instanceof BrewingStandMenu) {
            bl2 = true;
        }
        boolean bl3 = ((Boolean)this.inventoryOnly.getValue()).booleanValue() ? !(InventoryManager.mc.screen instanceof InventoryScreen) : (bl = InventoryManager.mc.screen != null && !(InventoryManager.mc.screen instanceof InventoryScreen));
        if (bl2 || ChestStealer.a() || bl) {
            this.L = false;
            this.N = 0;
            O = false;
            this.G();
            this.Q = true;
            return;
        }
        if (this.W && InventoryManager.mc.player.tickCount < this.ag) {
            InventoryManager.mc.options.keySprint.setDown(false);
            InventoryManager.mc.player.setSprinting(false);
            O = false;
            return;
        }
        string = InventoryManager.mc.screen;
        if (string instanceof AbstractContainerScreen) {
            object = (AbstractContainerScreen)string;
            if (object.getMenu().containerId != InventoryManager.mc.player.inventoryMenu.containerId) {
                return;
            }
        }
        if (((Boolean)this.inventoryOnly.getValue()).booleanValue() && InventoryManager.mc.screen instanceof InventoryScreen) {
            ++this.N;
            if (this.N < ((Number)this.openDelay.getValue()).intValue()) {
                return;
            }
        }
        if (this.A()) {
            O = true;
            this.ah = 0;
        } else {
            O = false;
            this.Q = true;
            if (this.W) {
                ++this.ah;
            }
        }
    }

    @EventTarget
    public void a(SprintEvent sprintEvent) {
        if (InventoryManager.mc.player == null) {
            return;
        }
        if (this.W) {
            this.af = false;
            InventoryManager.mc.options.keySprint.setDown(false);
            InventoryManager.mc.player.setSprinting(false);
            return;
        }
        if (this.ac) {
            if (InventoryManager.mc.player.tickCount < this.ae) {
                InventoryManager.mc.options.keySprint.setDown(false);
                InventoryManager.mc.player.setSprinting(false);
                return;
            }
            boolean bl = this.ad;
            this.ac = false;
            this.ad = false;
            this.ae = 0;
            InventoryManager.mc.options.keySprint.setDown(bl);
            if (InventoryManager.mc.player.input != null && InventoryManager.mc.player.input.forwardImpulse > 0.0f && !InventoryManager.mc.player.isShiftKeyDown()) {
                InventoryManager.mc.player.setSprinting(true);
            }
        }
    }

    private int q() {
        return ((Number)this.F.getValue()).intValue();
    }

    private int u() {
        return 1;
    }

    private void G() {
        if (!this.W) {
            return;
        }
        boolean bl = this.X;
        boolean bl2 = this.Y;
        this.W = false;
        this.X = false;
        this.Y = false;
        this.Z = 0L;
        this.ab = 0;
        this.aa = null;
        this.af = false;
        this.ag = 0;
        this.ah = 0;
        this.ai = false;
        if (bl && InventoryManager.mc.player != null && mc.getConnection() != null) {
            this.ac = true;
            this.ad = bl2;
            this.ae = InventoryManager.mc.player.tickCount + 2;
            InventoryManager.mc.options.keySprint.setDown(false);
            InventoryManager.mc.player.setSprinting(false);
        }
    }

    private boolean A() {
        int n2;
        ArmorItem armorItem;
        ItemStack itemStack;
        if (!J.c(this.D())) {
            return false;
        }
        if (this.a()) {
            int n3;
            for (n3 = 0; n3 < InventoryManager.mc.player.getInventory().armor.size(); ++n3) {
                itemStack = (ItemStack)InventoryManager.mc.player.getInventory().armor.get(n3);
                Item item = itemStack.getItem();
                if (!(item instanceof ArmorItem)) continue;
                armorItem = (ArmorItem)item;
                if (itemStack.isEmpty() || !J.c(this.D()) || !(d.a(armorItem.getEquipmentSlot()) > d.j(itemStack))) continue;
                if (!this.B()) {
                    if (!this.C()) continue;
                    return true;
                }
                this.a(4 + (4 - n3), 0, ClickType.QUICK_MOVE);
                this.K = true;
                J.a();
                return true;
            }
            for (n3 = 0; n3 < InventoryManager.mc.player.getInventory().items.size(); ++n3) {
                boolean bl;
                Item item;
                itemStack = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n3);
                if (itemStack.isEmpty() || !((item = itemStack.getItem()) instanceof ArmorItem)) continue;
                armorItem = (ArmorItem)item;
                float f2 = d.j(itemStack);
                n2 = d.a(armorItem.getEquipmentSlot()) == f2 ? 1 : 0;
                boolean bl2 = bl = d.b(armorItem.getEquipmentSlot()) < f2;
                if (n2 == 0 || !bl || !J.c(this.D())) continue;
                int n4 = n3 < 9 ? n3 + 36 : n3;
                this.a(n4, 0, ClickType.QUICK_MOVE);
                this.K = true;
                J.a();
                return true;
            }
        }
        if (this.L && J.c(this.D())) {
            this.a(45, 0, ClickType.PICKUP);
            this.K = true;
            this.L = false;
            J.a();
            return true;
        }
        String string = (String)this.offhandItems.getValue();
        String[] stringArray = cb;
        if ("Golden Apple".equals(string)) {
            itemStack = (ItemStack)InventoryManager.mc.player.getInventory().offhand.get(0);
            int n5 = d.a(Items.GOLDEN_APPLE);
            if (n5 != -1 && J.c(this.D())) {
                if (itemStack.getItem() != Items.GOLDEN_APPLE && this.a(n5)) {
                    return true;
                }
                ItemStack itemStack2 = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n5);
                if (itemStack.getCount() + itemStack2.getCount() <= 64) {
                    n2 = n5 < 9 ? n5 + 36 : n5;
                    this.a(n2, 0, ClickType.PICKUP);
                    this.K = true;
                    this.L = true;
                    J.a();
                    return true;
                }
            }
        } else {
            stringArray = cb;
            if ("Projectile".equals(string)) {
                itemStack = (ItemStack)InventoryManager.mc.player.getInventory().offhand.get(0);
                armorItem = d.h();
                if (armorItem != null) {
                    int n6 = d.a((ItemStack)armorItem);
                    if (itemStack.getItem() != Items.EGG && itemStack.getItem() != Items.SNOWBALL) {
                        n2 = 1;
                    } else {
                        int n7 = n2 = itemStack.getCount() < armorItem.getCount() ? 1 : 0;
                    }
                    if (n2 != 0 && n6 != -1 && J.c(this.D()) && this.a(n6)) {
                        return true;
                    }
                }
            } else {
                stringArray = cb;
                if ("Fishing Rod".equals(string)) {
                    itemStack = (ItemStack)InventoryManager.mc.player.getInventory().offhand.get(0);
                    int n8 = d.a(Items.FISHING_ROD);
                    if (n8 != -1 && J.c(this.D()) && itemStack.getItem() != Items.FISHING_ROD && this.a(n8)) {
                        return true;
                    }
                } else {
                    stringArray = cb;
                    if ("Block".equals(string)) {
                        itemStack = (ItemStack)InventoryManager.mc.player.getInventory().offhand.get(0);
                        armorItem = d.n();
                        if (armorItem != null) {
                            int n9 = d.a((ItemStack)armorItem);
                            n2 = a.a(itemStack) ? (itemStack.getCount() < armorItem.getCount() ? 1 : 0) : 1;
                            if (n2 != 0 && n9 != -1 && J.c(this.D()) && this.a(n9)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        stringArray = cb;
        if (!"Golden Apple".equals(string) && this.p() != 0 && this.a(this.p() - 1, Items.GOLDEN_APPLE)) {
            return true;
        }
        if (!this.H() && this.j() != 0) {
            int n10 = this.j() - 1;
            ItemStack itemStack3 = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n10);
            ItemStack itemStack4 = d.n();
            if (!(itemStack4 == null || itemStack4.getCount() <= itemStack3.getCount() && a.a(itemStack3))) {
                stringArray = cb;
                if (!"Block".equals(this.offhandItems.getValue()) && this.a(n10, itemStack4)) {
                    return true;
                }
            }
        }
        if (this.i() != 0) {
            ItemStack itemStack5 = d.d();
            int n11 = this.i() - 1;
            ItemStack itemStack6 = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n11);
            if (itemStack5 != null) {
                float f3 = itemStack6.getItem() instanceof SwordItem ? d.i(itemStack6) : 0.0f;
                float f4 = d.i(itemStack5);
                if (f4 > f3 && this.a(n11, itemStack5)) {
                    return true;
                }
            }
        }
        if (this.l() != 0) {
            int n12 = this.l() - 1;
            ItemStack itemStack7 = d.p();
            ItemStack itemStack8 = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n12);
            if (itemStack7 != null && itemStack7.getItem() instanceof PickaxeItem && (d.g(itemStack7) > d.g(itemStack8) || !(itemStack8.getItem() instanceof PickaxeItem)) && this.a(n12, itemStack7)) {
                return true;
            }
        }
        if (this.m() != 0) {
            float f5;
            float f6;
            ItemStack itemStack9;
            int n13 = this.m() - 1;
            ItemStack itemStack10 = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n13);
            String string2 = (String)this.bowPriority.getValue();
            stringArray = cb;
            if ("Crossbow".equals(string2)) {
                itemStack9 = d.x();
                f6 = d.k(itemStack9);
                f5 = d.k(itemStack10);
            } else {
                stringArray = cb;
                if ("Punch Bow".equals(string2) || "Punch".equals(string2)) {
                    itemStack9 = d.B();
                    f6 = d.f(itemStack9);
                    f5 = d.f(itemStack10);
                } else {
                    itemStack9 = d.z();
                    f6 = d.e(itemStack9);
                    f5 = d.e(itemStack10);
                }
            }
            if (itemStack9 == null) {
                itemStack9 = d.x();
                f6 = d.k(itemStack9);
                f5 = d.k(itemStack10);
            }
            if (itemStack9 == null) {
                itemStack9 = d.B();
                f6 = d.f(itemStack9);
                f5 = d.f(itemStack10);
            }
            if (itemStack9 == null) {
                itemStack9 = d.z();
                f6 = d.e(itemStack9);
                f5 = d.e(itemStack10);
            }
            if (itemStack9 != null && f6 > f5 && this.a(n13, itemStack9)) {
                return true;
            }
        }
        if (this.k() != 0) {
            ItemStack itemStack11 = d.s();
            if (this.a(this.k() - 1, itemStack11)) {
                return true;
            }
        }
        if (this.q() != 0 && this.a(this.q() - 1, d.h())) {
            return true;
        }
        if (this.o() != 0 && this.a(this.o() - 1, Items.ENDER_PEARL)) {
            return true;
        }
        if (this.n() != 0 && this.a(this.n() - 1, Items.WATER_BUCKET)) {
            return true;
        }
        if (((Number)this.G.getValue()).intValue() != 0 && this.a(((Number)this.G.getValue()).intValue() - 1, Items.SLIME_BALL)) {
            return true;
        }
        if (((Number)this.H.getValue()).intValue() != 0 && this.a(((Number)this.H.getValue()).intValue() - 1, Items.END_CRYSTAL)) {
            return true;
        }
        if (((Number)this.I.getValue()).intValue() != 0 && this.a(((Number)this.I.getValue()).intValue() - 1, Items.COBWEB)) {
            return true;
        }
        if (!this.H() && d.j() > this.r() && this.a(d.m())) {
            return true;
        }
        if (d.f() > ((Number)this.maxFoodSize.getValue()).intValue() && this.a(d.g())) {
            return true;
        }
        if (d.e() > ((Number)this.maxRodSize.getValue()).intValue() && this.a(d.i())) {
            return true;
        }
        if (d.c(Items.EGG) + d.c(Items.SNOWBALL) > this.s() && this.a(d.k())) {
            return true;
        }
        if (d.c(Items.ARROW) > this.t() && this.a(d.l())) {
            return true;
        }
        List list = IntStream.range(0, InventoryManager.mc.player.getInventory().items.size()).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        for (Integer n14 : list) {
            ItemStack itemStack12 = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n14.intValue());
            if (itemStack12.isEmpty() || this.c(itemStack12) || !this.a(itemStack12)) continue;
            return true;
        }
        return false;
    }

    private boolean H() {
        return (Boolean)this.inventoryOnly.getValue() == false && Scaffold.INSTANCE != null && Scaffold.INSTANCE.isEnabled();
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (!motionEvent.isPost() || ((Boolean)this.inventoryOnly.getValue()).booleanValue() || !GuiMove.j.isEnabled() || InventoryManager.mc.player == null) {
            return;
        }
        if (this.S.isEmpty()) {
            this.T = 0;
            if (this.R) {
                this.R = false;
            }
            return;
        }
        if (this.P || InventoryManager.mc.player.isSprinting()) {
            this.Q = true;
            this.T = 2 + ((Number)this.openDelay.getValue()).intValue();
            return;
        }
        if (this.T > 0) {
            --this.T;
            return;
        }
        while (!this.S.isEmpty()) {
            Packet<ServerGamePacketListener> packet = this.S.poll();
            ChatUtil.print("Releasing Packet: " + packet.getClass().getName());
            PacketUtil.sendQueued(packet);
        }
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundContainerClosePacket(InventoryManager.mc.player.inventoryMenu.containerId));
        this.R = true;
    }

    public static boolean d(ItemStack itemStack) {
        return j != null ? j.c(itemStack) : itemStack != null && !itemStack.isEmpty();
    }

    public static int M() {
        return j != null ? j.v() : 1;
    }

    private boolean z() {
        ArrayList<Pair> arrayList = new ArrayList<Pair>();
        arrayList.add(Pair.of((Object)(((Number)this.swordSlot.getValue()).intValue() != 0 ? 1 : 0), (Object)this.swordSlot));
        arrayList.add(Pair.of((Object)(((Number)this.axeSlot.getValue()).intValue() != 0 ? 1 : 0), (Object)this.axeSlot));
        arrayList.add(Pair.of((Object)(((Number)this.A.getValue()).intValue() != 0 ? 1 : 0), (Object)this.A));
        arrayList.add(Pair.of((Object)(((Number)this.B.getValue()).intValue() != 0 ? 1 : 0), (Object)this.B));
        arrayList.add(Pair.of((Object)(((Number)this.C.getValue()).intValue() != 0 ? 1 : 0), (Object)this.C));
        arrayList.add(Pair.of((Object)(((Number)this.D.getValue()).intValue() != 0 ? 1 : 0), (Object)this.D));
        arrayList.add(Pair.of((Object)(((Number)this.G.getValue()).intValue() != 0 ? 1 : 0), (Object)this.G));
        arrayList.add(Pair.of((Object)(((Number)this.H.getValue()).intValue() != 0 ? 1 : 0), (Object)this.H));
        arrayList.add(Pair.of((Object)(((Number)this.I.getValue()).intValue() != 0 ? 1 : 0), (Object)this.I));
        arrayList.add(Pair.of((Object)(((Number)this.F.getValue()).intValue() != 0 ? 1 : 0), (Object)this.F));
        String[] stringArray = cb;
        if (!"Golden Apple".equals(this.offhandItems.getValue())) {
            arrayList.add(Pair.of((Object)(((Number)this.E.getValue()).intValue() != 0 ? 1 : 0), (Object)this.E));
        }
        if (!"Block".equals(this.offhandItems.getValue())) {
            arrayList.add(Pair.of((Object)(((Number)this.blockSlot.getValue()).intValue() != 0 ? 1 : 0), (Object)this.blockSlot));
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (Pair pair : arrayList) {
            int n2;
            if (!((Boolean)pair.getKey()).booleanValue() || hashSet.add(n2 = ((Number)((NumberSetting)pair.getValue()).getValue()).intValue() - 1)) continue;
            return false;
        }
        return true;
    }

    public InventoryManager() {
        String[] stringArray = cb;
        super("InventoryManager", Category.PLAYER, 66);
        this.delay = new NumberSetting("Delay", 200, 0, 500, 10);
        this.openDelay = new NumberSetting("Open Delay", 2, 0, 10, 1);
        this.dropDelay = new NumberSetting("Drop Delay", 200, 0, 500, 10);
        this.autoArmor = new BooleanSetting("Auto Armor", true);
        this.throwItems = new BooleanSetting("Throw Items", true);
        this.offhandItems = new ModeSetting("Offhand Items", "Golden Apple", "Fishing Rod", "None").withDefault("Projectile");
        this.bowPriority = new ModeSetting("Bow Priority", "Crossbow", "Punch Bow").withDefault("Crossbow");
        this.inventoryOnly = new BooleanSetting("Inventory Only", true);
        this.fastThrow = new BooleanSetting("Fast Throw", false);
        this.maxEggsSnowballsSize = new NumberSetting("Max Eggs & Snowballs Size", 64, 16, 256, 16);
        this.maxBlockSize = new NumberSetting("Max Block Size", 256, 64, 512, 64);
        this.maxFoodSize = new NumberSetting("Max Food Size", 128, 32, 256, 32);
        this.maxRodSize = new NumberSetting("Max Rod Size", 1, 1, 16, 1);
        this.swordSlot = new NumberSetting("Sword Slot", 0, 0, 9, 1);
        this.blockSlot = new NumberSetting("Block Slot", 0, 0, 9, 1);
        this.axeSlot = new NumberSetting("Axe Slot", 0, 0, 9, 1);
        this.A = new NumberSetting("Pickaxe Slot", 0, 0, 9, 1);
        this.B = new NumberSetting("Bow Slot", 0, 0, 9, 1);
        this.C = new NumberSetting("Water Bucket Slot", 0, 0, 9, 1);
        this.D = new NumberSetting("Ender Pearl Slot", 0, 0, 9, 1);
        this.E = new NumberSetting("Golden Apple Slot", 0, 0, 9, 1);
        this.F = new NumberSetting("Eggs & Snowballs Slot", 0, 0, 9, 1);
        this.G = new NumberSetting("Slime Ball Slot", 0, 0, 9, 1);
        this.H = new NumberSetting("Crystal Slot", 0, 0, 9, 1);
        this.I = new NumberSetting("Cobweb Slot", 0, 0, 9, 1);
        this.K = false;
        this.L = false;
        this.M = 0;
        this.N = 0;
        this.P = false;
        this.Q = false;
        this.R = false;
        this.S = new ConcurrentLinkedQueue<Packet<ServerGamePacketListener>>();
        this.T = 0;
        j = this;
    }

    private int o() {
        return ((Number)this.D.getValue()).intValue();
    }

    private boolean a(int n2, ItemStack itemStack) {
        if (InventoryManager.mc.gameMode == null || InventoryManager.mc.player == null) {
            return false;
        }
        ItemStack itemStack2 = (ItemStack)InventoryManager.mc.player.getInventory().items.get(n2);
        if (itemStack == null || itemStack.isEmpty() || itemStack == itemStack2 || this.b(itemStack) || this.b(itemStack2) || !J.c(this.D())) {
            return false;
        }
        int n3 = d.a(itemStack);
        if (n3 == -1) {
            return false;
        }
        int n4 = n3 < 9 ? n3 + 36 : n3;
        this.a(n4, n2, ClickType.SWAP);
        this.K = true;
        J.a();
        return true;
    }
}

