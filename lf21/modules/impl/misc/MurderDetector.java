/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.misc;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lf21.event.EventTarget;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.e;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.utils.misc.ChatUtil;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Blocks;

public class MurderDetector
extends Module {
    public static MurderDetector j;
    private static final Set<Item> k;
    private final BooleanSetting chatAlert;
    private final BooleanSetting glow;
    private static final String[] o;
    private final Set<UUID> n;

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
                    case 0 -> 109;
                    case 1 -> 78;
                    case 2 -> 48;
                    case 3 -> 18;
                    case 4 -> 105;
                    case 5 -> 60;
                    default -> 83;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    protected void onDisable() {
        this.a();
    }

    @EventTarget
    public void a(e e2) {
        this.a();
    }

    public MurderDetector() {
        String[] stringArray = o;
        super("MurderDetector", Category.MISC);
        this.glow = new BooleanSetting("Glow", true);
        this.chatAlert = new BooleanSetting("Chat Alert", true);
        this.n = new HashSet<UUID>();
        j = this;
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        if (MurderDetector.mc.level == null) {
            return;
        }
        for (Entity entity : MurderDetector.mc.level.entitiesForRendering()) {
            AbstractClientPlayer abstractClientPlayer;
            if (!(entity instanceof AbstractClientPlayer) || (abstractClientPlayer = (AbstractClientPlayer)entity) == MurderDetector.mc.player) continue;
            if (MurderDetector.a(abstractClientPlayer.getMainHandItem()) || MurderDetector.a(abstractClientPlayer.getOffhandItem())) {
                this.a(abstractClientPlayer);
            }
            if (!this.n.contains(abstractClientPlayer.getUUID())) continue;
            abstractClientPlayer.setGlowingTag(((Boolean)this.glow.getValue()).booleanValue());
        }
    }

    public static boolean isMurderer(Entity entity) {
        MurderDetector murderDetector = j;
        return murderDetector != null && murderDetector.isEnabled() && entity != null && murderDetector.n.contains(entity.getUUID());
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        AbstractClientPlayer abstractClientPlayer;
        ClientboundSetEquipmentPacket clientboundSetEquipmentPacket;
        block6: {
            block5: {
                if (!packetEvent.a() || MurderDetector.mc.level == null) {
                    return;
                }
                Packet<?> packet = packetEvent.c();
                if (!(packet instanceof ClientboundSetEquipmentPacket)) {
                    return;
                }
                clientboundSetEquipmentPacket = (ClientboundSetEquipmentPacket)packet;
                Entity entity = MurderDetector.mc.level.getEntity(clientboundSetEquipmentPacket.getEntity());
                if (!(entity instanceof AbstractClientPlayer)) break block5;
                abstractClientPlayer = (AbstractClientPlayer)entity;
                if (entity != MurderDetector.mc.player) break block6;
            }
            return;
        }
        clientboundSetEquipmentPacket.getSlots().forEach(pair -> {
            ItemStack itemStack = (ItemStack)pair.getSecond();
            if (!itemStack.isEmpty() && MurderDetector.a(itemStack)) {
                this.a(abstractClientPlayer);
            }
        });
    }

    private void a() {
        if (MurderDetector.mc.level != null) {
            for (Entity entity : MurderDetector.mc.level.entitiesForRendering()) {
                if (!this.n.contains(entity.getUUID())) continue;
                entity.setGlowingTag(false);
            }
        }
        this.n.clear();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = " ;Bv\fN\u0017\b:Uq\u001dS!\u0004*\"_e";
                var4_3 = " ;Bv\fN\u0017\b:Uq\u001dS!\u0004*\"_e".length();
                var1_4 = 14;
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
                    var5[var3_1++] = MurderDetector.c(MurderDetector.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = ".&QfI}?\b<D\u0013 ;Bv\fN6\u001fnTw\u001dY0\u0019+T(I";
                    var4_3 = ".&QfI}?\b<D\u0013 ;Bv\fN6\u001fnTw\u001dY0\u0019+T(I".length();
                    var1_4 = 10;
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
                    var5[var3_1++] = MurderDetector.c(MurderDetector.a(v1));
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
        MurderDetector.o = var5;
        MurderDetector.k = Set.of(new Item[]{Items.GOLDEN_CARROT, Items.CARROT, Items.CARROT_ON_A_STICK, Items.BONE, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.SALMON, Items.BLAZE_ROD, Items.PUMPKIN_PIE, Items.NAME_TAG, Items.APPLE, Items.FEATHER, Items.COOKIE, Items.SHEARS, Items.COOKED_SALMON, Items.STICK, Items.QUARTZ, Items.ROSE_BUSH, Items.ICE, Items.COOKED_BEEF, Items.NETHER_BRICK, Items.COOKED_CHICKEN, Items.RED_DYE, Items.OAK_BOAT, Items.BOOK, Items.GLISTERING_MELON_SLICE, Items.JUNGLE_SAPLING, Items.PRISMARINE_SHARD, Items.CHARCOAL, Items.SUGAR_CANE, Items.FLINT, Items.BREAD, Items.LAPIS_LAZULI, Items.LEATHER});
    }

    private void a(AbstractClientPlayer abstractClientPlayer) {
        if (!this.n.add(abstractClientPlayer.getUUID())) {
            return;
        }
        abstractClientPlayer.setGlowingTag(((Boolean)this.glow.getValue()).booleanValue());
        if (((Boolean)this.chatAlert.getValue()).booleanValue()) {
            ChatUtil.print("Murderer detected: " + abstractClientPlayer.getGameProfile().getName());
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x53);
        }
        return cArray;
    }

    private static boolean a(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (item == Items.WOODEN_SHOVEL || item == Items.GOLDEN_SHOVEL) {
            return false;
        }
        if (k.contains(item)) {
            return true;
        }
        if (item instanceof SwordItem || item instanceof PickaxeItem || item instanceof ShovelItem || item instanceof AxeItem || item instanceof HoeItem || item instanceof BoatItem) {
            return true;
        }
        if (item instanceof BlockItem) {
            BlockItem blockItem = (BlockItem)item;
            return blockItem.getBlock() == Blocks.SPONGE || blockItem.getBlock() == Blocks.DEAD_BUSH || blockItem.getBlock() == Blocks.REDSTONE_TORCH || blockItem.getBlock() == Blocks.CHORUS_PLANT;
        }
        return false;
    }
}

