/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import lf21.ClientBase;
import lf21.utils.game.a;
import lombok.Generated;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ExperienceBottleItem;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.PlayerHeadItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;

public final class d
extends ClientBase {
    private static final String[] a;

    public static boolean a() {
        return d.b().stream().anyMatch(itemStack -> {
            if (!itemStack.isEmpty()) {
                boolean bl;
                block13: {
                    block12: {
                        block11: {
                            String string = itemStack.getDisplayName().getString();
                            if (string.contains("\u957f\u6309\u70b9\u51fb") || string.contains("\u70b9\u51fb\u4f7f\u7528")) break block11;
                            if (string.contains("\u79bb\u5f00\u6e38\u620f")) break block11;
                            if (string.contains("\u9009\u62e9\u4e00\u4e2a\u961f\u4f0d")) break block11;
                            if (!string.contains(a[16])) break block12;
                        }
                        bl = true;
                        break block13;
                    }
                    bl = false;
                }
                return bl;
            }
            return false;
        });
    }

    public static int a(ItemStack itemStack) {
        block9: {
            if (itemStack != null && d.mc.player != null) break block9;
            return -1;
        }
        int n2 = 0;
        while (true) {
            block11: {
                block10: {
                    if (n2 >= d.mc.player.getInventory().items.size()) break;
                    if (d.mc.player.getInventory().items.get(n2) == itemStack) break block10;
                    break block11;
                }
                return n2;
            }
            ++n2;
        }
        return -1;
    }

    public static int a(Item item) {
        if (d.mc.player == null) {
            return -1;
        }
        for (int i2 = 0; i2 < d.mc.player.getInventory().items.size(); ++i2) {
            ItemStack itemStack = (ItemStack)d.mc.player.getInventory().items.get(i2);
            if (itemStack.getItem() != item) {
                continue;
            }
            return i2;
        }
        return -1;
    }

    public static boolean b(ItemStack itemStack) {
        Item item;
        block29: {
            block28: {
                if (itemStack.isEmpty()) {
                    return true;
                }
                item = itemStack.getItem();
                if (item instanceof BlockItem) {
                    BlockItem blockItem = (BlockItem)item;
                    if (blockItem.getBlock() == Blocks.ENCHANTING_TABLE) {
                        return false;
                    }
                    boolean bl = blockItem.getBlock() != Blocks.COBWEB;
                    return bl;
                }
                if (item instanceof BookItem) {
                    return false;
                }
                if (item instanceof ExperienceBottleItem) {
                    return false;
                }
                if (item instanceof FireworkRocketItem) {
                    return false;
                }
                if (item == Items.WHEAT_SEEDS || item == Items.BEETROOT_SEEDS) break block28;
                if (item == Items.MELON_SEEDS) break block28;
                if (item != Items.PUMPKIN_SEEDS) break block29;
            }
            return false;
        }
        boolean bl = item != Items.FLINT_AND_STEEL;
        return bl;
    }

    public static int c(ItemStack itemStack) {
        return EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.PUNCH_ARROWS, (ItemStack)itemStack);
    }

    public static int d(ItemStack itemStack) {
        return EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.POWER_ARROWS, (ItemStack)itemStack);
    }

    public static List<ItemStack> b() {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>(40);
        if (d.mc.player == null) {
            return arrayList;
        }
        arrayList.addAll((Collection<ItemStack>)d.mc.player.getInventory().items);
        arrayList.addAll((Collection<ItemStack>)d.mc.player.getInventory().armor);
        return arrayList;
    }

    public static float a(EquipmentSlot equipmentSlot) {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block4: {
                block3: {
                    Item item;
                    if (!itemStack.isEmpty() && (item = itemStack.getItem()) instanceof ArmorItem) {
                        ArmorItem armorItem = (ArmorItem)item;
                        if (armorItem.getEquipmentSlot() != equipmentSlot) break block3;
                        bl = true;
                        break block4;
                    }
                }
                bl = false;
            }
            return bl;
        }).map(d::j).max(Float::compareTo).orElse(Float.valueOf(0.0f)).floatValue();
    }

    public static float b(EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.HEAD) {
            return d.j((ItemStack)d.mc.player.getInventory().armor.get(3));
        }
        if (equipmentSlot == EquipmentSlot.CHEST) {
            return d.j((ItemStack)d.mc.player.getInventory().armor.get(2));
        }
        if (equipmentSlot == EquipmentSlot.LEGS) {
            return d.j((ItemStack)d.mc.player.getInventory().armor.get(1));
        }
        if (equipmentSlot == EquipmentSlot.FEET) {
            return d.j((ItemStack)d.mc.player.getInventory().armor.get(0));
        }
        return 0.0f;
    }

    public static float c() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block5: {
                block4: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof SwordItem)) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        }).map(d::i).max(Float::compareTo).orElse(Float.valueOf(0.0f)).floatValue();
    }

    public static ItemStack d() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block5: {
                block4: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof SwordItem)) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(itemStack -> (int)(d.i(itemStack) * 100.0f))).orElse(null);
    }

    public static float e(ItemStack itemStack) {
        if (itemStack == null) {
            return 0.0f;
        }
        if (itemStack.isEmpty()) {
            return 0.0f;
        }
        if (itemStack.getItem() instanceof BowItem) {
            float f2 = 10.0f;
            f2 += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.PUNCH_ARROWS, (ItemStack)itemStack);
            f2 += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.INFINITY_ARROWS, (ItemStack)itemStack);
            f2 += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.FLAMING_ARROWS, (ItemStack)itemStack);
            return (f2 += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.POWER_ARROWS, (ItemStack)itemStack) / 10.0f) + (float)itemStack.getDamageValue() / (float)itemStack.getMaxDamage();
        }
        return 0.0f;
    }

    public static float f(ItemStack itemStack) {
        if (itemStack == null) {
            return 0.0f;
        }
        if (itemStack.isEmpty()) {
            return 0.0f;
        }
        if (itemStack.getItem() instanceof BowItem) {
            float f2 = 10.0f;
            f2 += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.PUNCH_ARROWS, (ItemStack)itemStack) / 10.0f;
            f2 += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.INFINITY_ARROWS, (ItemStack)itemStack);
            f2 += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.FLAMING_ARROWS, (ItemStack)itemStack);
            return (f2 += (float)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.POWER_ARROWS, (ItemStack)itemStack)) + (float)itemStack.getDamageValue() / (float)itemStack.getMaxDamage();
        }
        return 0.0f;
    }

    public static float g(ItemStack itemStack) {
        float f2 = 0.0f;
        if (itemStack == null) {
            return 0.0f;
        }
        if (itemStack.isEmpty()) {
            return 0.0f;
        }
        if (d.l(itemStack)) {
            return 0.0f;
        }
        if (d.p(itemStack)) {
            return 0.0f;
        }
        if (itemStack.getItem() instanceof PickaxeItem) {
            f2 += itemStack.getDestroySpeed(Blocks.STONE.defaultBlockState());
        } else if (itemStack.getItem() instanceof AxeItem) {
            f2 += itemStack.getDestroySpeed(Blocks.OAK_LOG.defaultBlockState());
        } else if (itemStack.getItem() instanceof ShovelItem) {
            f2 += itemStack.getDestroySpeed(Blocks.DIRT.defaultBlockState());
        } else {
            return 0.0f;
        }
        int n2 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.BLOCK_EFFICIENCY, (ItemStack)itemStack);
        if (n2 > 0) {
            f2 += (float)n2 * 0.0075f;
        }
        return f2;
    }

    public static float h(ItemStack itemStack) {
        int n2;
        float f2;
        block15: {
            Item item;
            block19: {
                block18: {
                    block17: {
                        block16: {
                            f2 = 0.0f;
                            if (itemStack == null) {
                                return 0.0f;
                            }
                            if (itemStack.isEmpty()) {
                                return 0.0f;
                            }
                            item = itemStack.getItem();
                            if (!(item instanceof AxeItem)) break block15;
                            if (item == Items.WOODEN_AXE) break block16;
                            if (item != Items.GOLDEN_AXE) break block17;
                        }
                        f2 = 7.0f;
                        break block15;
                    }
                    if (item == Items.STONE_AXE || item == Items.IRON_AXE) break block18;
                    if (item != Items.DIAMOND_AXE) break block19;
                }
                f2 = 9.0f;
                break block15;
            }
            f2 = item == Items.NETHERITE_AXE ? 10.0f : (float)Math.max(1.0, d.m(itemStack) + 1.0);
        }
        if ((n2 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack)) > 0) {
            float f3 = Enchantments.SHARPNESS.getDamageBonus(n2, MobType.UNDEFINED);
            f2 += f3;
        }
        return f2;
    }

    public static float i(ItemStack itemStack) {
        int n2;
        float f2 = 0.0f;
        if (itemStack == null) {
            return 0.0f;
        }
        if (itemStack.isEmpty()) {
            return 0.0f;
        }
        Item item = itemStack.getItem();
        if (item instanceof SwordItem) {
            SwordItem swordItem = (SwordItem)item;
            f2 += swordItem.getDamage() + 1.0f;
        }
        if ((n2 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack)) > 0) {
            float f3 = Enchantments.SHARPNESS.getDamageBonus(n2, MobType.UNDEFINED);
            f2 += f3;
        }
        return f2;
    }

    public static float j(ItemStack itemStack) {
        int n2;
        block20: {
            block18: {
                n2 = 0;
                if (itemStack != null && !itemStack.isEmpty()) break block18;
                return 0.0f;
            }
            Item item = itemStack.getItem();
            if (item instanceof ArmorItem) {
                ArmorMaterial armorMaterial;
                block24: {
                    block23: {
                        block22: {
                            block21: {
                                block19: {
                                    ArmorItem armorItem = (ArmorItem)item;
                                    armorMaterial = armorItem.getMaterial();
                                    if (armorMaterial != ArmorMaterials.LEATHER) break block19;
                                    n2 += 100;
                                    break block20;
                                }
                                if (armorMaterial != ArmorMaterials.CHAIN) break block21;
                                n2 += 200;
                                break block20;
                            }
                            if (armorMaterial != ArmorMaterials.IRON) break block22;
                            n2 += 400;
                            break block20;
                        }
                        if (armorMaterial != ArmorMaterials.GOLD) break block23;
                        n2 += 300;
                        break block20;
                    }
                    if (armorMaterial != ArmorMaterials.DIAMOND) break block24;
                    n2 += 500;
                    break block20;
                }
                if (armorMaterial == ArmorMaterials.NETHERITE) {
                    n2 += 600;
                }
            }
        }
        return n2 + EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.ALL_DAMAGE_PROTECTION, (ItemStack)itemStack);
    }

    public static float k(ItemStack itemStack) {
        int n2 = 0;
        if (itemStack == null) {
            return 0.0f;
        }
        if (itemStack.isEmpty()) {
            return 0.0f;
        }
        if (itemStack.getItem() instanceof CrossbowItem) {
            n2 += EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.QUICK_CHARGE, (ItemStack)itemStack);
            n2 += EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.MULTISHOT, (ItemStack)itemStack);
            n2 += EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.PIERCING, (ItemStack)itemStack);
        }
        return n2;
    }

    public static boolean l(ItemStack itemStack) {
        block19: {
            block18: {
                if (itemStack.isEmpty()) {
                    return false;
                }
                if (!(itemStack.getItem() instanceof AxeItem) || itemStack.getItem() != Items.GOLDEN_AXE) break block18;
                if (EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack) <= 100) break block18;
                return true;
            }
            if (itemStack.getItem() != Items.SLIME_BALL || EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.KNOCKBACK, (ItemStack)itemStack) <= 1) break block19;
            return true;
        }
        if (itemStack.getItem() == Items.TOTEM_OF_UNDYING) {
            return true;
        }
        boolean bl = itemStack.getItem() == Items.END_CRYSTAL;
        return bl;
    }

    public static double m(ItemStack itemStack) {
        double d2 = 0.0;
        Multimap multimap = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);
        for (Attribute attribute : multimap.keySet()) {
            if (!attribute.getDescriptionId().equals("attribute.name.generic.attack_damage")) {
                continue;
            }
            Iterator iterator = multimap.get((Object)attribute).iterator();
            if (!iterator.hasNext()) {
                break;
            }
            d2 += ((AttributeModifier)iterator.next()).getAmount();
            break;
        }
        if (itemStack.hasFoil()) {
            d2 += (double)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.FIRE_ASPECT, (ItemStack)itemStack);
            d2 += (double)EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack) * 1.25;
        }
        return d2;
    }

    public static boolean n(ItemStack itemStack) {
        boolean bl;
        block7: {
            block6: {
                if (itemStack.isEmpty()) {
                    return false;
                }
                Item item = itemStack.getItem();
                if (item instanceof BlockItem) {
                    BlockItem blockItem = (BlockItem)item;
                    if (!(blockItem.getBlock() instanceof SkullBlock)) break block6;
                    bl = true;
                    break block7;
                }
            }
            bl = false;
        }
        return bl;
    }

    public static int e() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || itemStack.getItem() != Items.FISHING_ROD) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).mapToInt(ItemStack::getCount).sum();
    }

    public static int f() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block11: {
                block10: {
                    if (itemStack.isEmpty() || !itemStack.getItem().isEdible()) break block10;
                    if (itemStack.getItem() == Items.GOLDEN_APPLE) break block10;
                    if (itemStack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) break block10;
                    if (!d.y(itemStack)) break block10;
                    bl = true;
                    break block11;
                }
                bl = false;
            }
            return bl;
        }).mapToInt(ItemStack::getCount).sum();
    }

    public static ItemStack g() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block11: {
                block10: {
                    if (itemStack.isEmpty() || !itemStack.getItem().isEdible()) break block10;
                    if (itemStack.getItem() == Items.GOLDEN_APPLE) break block10;
                    if (itemStack.getItem() == Items.ENCHANTED_GOLDEN_APPLE) break block10;
                    if (!d.y(itemStack)) break block10;
                    bl = true;
                    break block11;
                }
                bl = false;
            }
            return bl;
        }).min(Comparator.comparingInt(ItemStack::getCount)).orElse(null);
    }

    public static boolean o(ItemStack itemStack) {
        boolean bl;
        block11: {
            block10: {
                if (itemStack.isEmpty()) {
                    return false;
                }
                if (!(itemStack.getItem() instanceof AxeItem)) {
                    return false;
                }
                int n2 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack);
                if (n2 < 8 || n2 >= 50) break block10;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean p(ItemStack itemStack) {
        boolean bl;
        block16: {
            block15: {
                block14: {
                    block13: {
                        block12: {
                            if (itemStack == null || itemStack.isEmpty()) break block12;
                            if (itemStack.getItem() instanceof AxeItem) break block13;
                        }
                        return false;
                    }
                    int n2 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack);
                    if (n2 >= 4 || d.q(itemStack)) break block14;
                    if (!(d.h(itemStack) >= 12.0f)) break block15;
                }
                bl = true;
                break block16;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean q(ItemStack itemStack) {
        block15: {
            boolean bl;
            block17: {
                block16: {
                    block14: {
                        if (itemStack.isEmpty()) {
                            return false;
                        }
                        String string = itemStack.getDisplayName().getString();
                        if (string.contains(a[17])) {
                            return true;
                        }
                        if (itemStack.getTag() == null || !itemStack.getTag().toString().contains("\u4e00\u5200")) break block14;
                        return true;
                    }
                    if (itemStack.getItem() != Items.GOLDEN_AXE) break block15;
                    if (EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack) <= 100) break block16;
                    bl = true;
                    break block17;
                }
                bl = false;
            }
            return bl;
        }
        return false;
    }

    public static boolean r(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }
        boolean bl = itemStack.getItem() == Items.ENCHANTED_GOLDEN_APPLE;
        return bl;
    }

    public static boolean s(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }
        boolean bl = itemStack.getItem() == Items.END_CRYSTAL;
        return bl;
    }

    public static boolean t(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }
        if (itemStack.getItem() != Items.SLIME_BALL) {
            return false;
        }
        boolean bl = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.KNOCKBACK, (ItemStack)itemStack) > 1;
        return bl;
    }

    public static boolean u(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }
        if (itemStack.getItem() != Items.STICK) {
            return false;
        }
        boolean bl = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.KNOCKBACK, (ItemStack)itemStack) > 1;
        return bl;
    }

    public static ItemStack h() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block10: {
                block8: {
                    block9: {
                        if (itemStack.isEmpty()) break block8;
                        if (itemStack.getItem() == Items.EGG) break block9;
                        if (itemStack.getItem() != Items.SNOWBALL) break block8;
                    }
                    if (!d.y(itemStack)) break block8;
                    bl = true;
                    break block10;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(ItemStack::getCount)).orElse(null);
    }

    public static ItemStack i() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof FishingRodItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).findAny().orElse(null);
    }

    public static int j() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block9: {
                block8: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BlockItem)) break block8;
                    if (!lf21.utils.game.a.a(itemStack)) break block8;
                    if (!d.y(itemStack)) break block8;
                    bl = true;
                    break block9;
                }
                bl = false;
            }
            return bl;
        }).mapToInt(ItemStack::getCount).sum();
    }

    public static ItemStack k() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block8: {
                block6: {
                    block7: {
                        if (itemStack.isEmpty()) break block6;
                        if (itemStack.getItem() == Items.EGG) break block7;
                        if (itemStack.getItem() != Items.SNOWBALL) break block6;
                    }
                    bl = true;
                    break block8;
                }
                bl = false;
            }
            return bl;
        }).min(Comparator.comparingInt(ItemStack::getCount)).orElse(null);
    }

    public static ItemStack l() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof ArrowItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).min(Comparator.comparingInt(ItemStack::getCount)).orElse(null);
    }

    public static ItemStack m() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block11: {
                block10: {
                    if (itemStack.isEmpty() || itemStack.getItem() == Items.TNT) break block10;
                    if (!(itemStack.getItem() instanceof BlockItem)) break block10;
                    if (!lf21.utils.game.a.a(itemStack)) break block10;
                    if (!d.y(itemStack)) break block10;
                    bl = true;
                    break block11;
                }
                bl = false;
            }
            return bl;
        }).min(Comparator.comparingInt(ItemStack::getCount)).orElse(null);
    }

    public static ItemStack n() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block9: {
                block8: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BlockItem)) break block8;
                    if (!lf21.utils.game.a.a(itemStack)) break block8;
                    if (!d.y(itemStack)) break block8;
                    bl = true;
                    break block9;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(ItemStack::getCount)).orElse(null);
    }

    public static float o() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof PickaxeItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).map(d::g).max(Float::compareTo).orElse(Float.valueOf(0.0f)).floatValue();
    }

    public static ItemStack p() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof PickaxeItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(itemStack -> (int)(d.g(itemStack) * 100.0f))).orElse(null);
    }

    public static float q() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block9: {
                block8: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof AxeItem)) break block8;
                    if (d.p(itemStack)) break block8;
                    if (!d.y(itemStack)) break block8;
                    bl = true;
                    break block9;
                }
                bl = false;
            }
            return bl;
        }).map(d::g).max(Float::compareTo).orElse(Float.valueOf(0.0f)).floatValue();
    }

    public static ItemStack r() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block9: {
                block8: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof AxeItem)) break block8;
                    if (d.p(itemStack)) break block8;
                    if (!d.y(itemStack)) break block8;
                    bl = true;
                    break block9;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(itemStack -> (int)(d.g(itemStack) * 100.0f))).orElse(null);
    }

    public static ItemStack s() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof AxeItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingDouble(d::v)).orElse(null);
    }

    public static double v(ItemStack itemStack) {
        double d2;
        block23: {
            block26: {
                block25: {
                    block24: {
                        block22: {
                            boolean bl;
                            block21: {
                                block20: {
                                    String string = itemStack.getHoverName().getString().toLowerCase(Locale.ROOT);
                                    if (!string.contains("mace") && !string.contains("\u91cd\u9524")) break block20;
                                    bl = true;
                                    break block21;
                                }
                                bl = false;
                            }
                            boolean bl2 = bl;
                            int n2 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack);
                            if (bl2) {
                                return 2000000.0 + (double)d.h(itemStack) * 1000.0 + (double)n2;
                            }
                            if (d.p(itemStack)) {
                                return 1000000.0 + (double)d.h(itemStack) * 1000.0 + (double)n2;
                            }
                            if (itemStack.getItem() != Items.NETHERITE_AXE) break block22;
                            d2 = 6.0;
                            break block23;
                        }
                        if (itemStack.getItem() != Items.DIAMOND_AXE) break block24;
                        d2 = 5.0;
                        break block23;
                    }
                    if (itemStack.getItem() != Items.IRON_AXE) break block25;
                    d2 = 4.0;
                    break block23;
                }
                if (itemStack.getItem() != Items.STONE_AXE) break block26;
                d2 = 3.0;
                break block23;
            }
            d2 = itemStack.getItem() == Items.GOLDEN_AXE ? 2.0 : 1.0;
        }
        double d3 = d2;
        return (double)d.h(itemStack) * 10000.0 + d3 * 100.0 + (double)d.g(itemStack);
    }

    public static ItemStack t() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block9: {
                block8: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof AxeItem)) break block8;
                    if (!d.p(itemStack)) break block8;
                    if (!d.y(itemStack)) break block8;
                    bl = true;
                    break block9;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingDouble(d::h)).orElse(null);
    }

    public static float u() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof ShovelItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).map(d::g).max(Float::compareTo).orElse(Float.valueOf(0.0f)).floatValue();
    }

    public static ItemStack v() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof ShovelItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(itemStack -> (int)(d.g(itemStack) * 100.0f))).orElse(null);
    }

    public static float w() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof CrossbowItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).map(d::k).max(Float::compareTo).orElse(Float.valueOf(0.0f)).floatValue();
    }

    public static ItemStack x() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof CrossbowItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(itemStack -> (int)(d.k(itemStack) * 100.0f))).orElse(null);
    }

    public static float y() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BowItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).map(d::e).max(Float::compareTo).orElse(Float.valueOf(0.0f)).floatValue();
    }

    public static ItemStack z() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BowItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(itemStack -> (int)(d.e(itemStack) * 100.0f))).orElse(null);
    }

    public static float A() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BowItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).map(d::f).max(Float::compareTo).orElse(Float.valueOf(0.0f)).floatValue();
    }

    public static ItemStack B() {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block7: {
                block6: {
                    if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BowItem)) break block6;
                    if (!d.y(itemStack)) break block6;
                    bl = true;
                    break block7;
                }
                bl = false;
            }
            return bl;
        }).max(Comparator.comparingInt(itemStack -> (int)(d.f(itemStack) * 100.0f))).orElse(null);
    }

    public static boolean w(ItemStack itemStack) {
        boolean bl;
        block5: {
            block4: {
                if (!(d.e(itemStack) > 10.0f) || !d.y(itemStack)) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean x(ItemStack itemStack) {
        boolean bl;
        block5: {
            block4: {
                if (!(d.f(itemStack) > 10.0f) || !d.y(itemStack)) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean b(Item item) {
        return d.b().stream().anyMatch(itemStack -> {
            boolean bl;
            block5: {
                block4: {
                    if (itemStack.isEmpty() || itemStack.getItem() != item) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        });
    }

    public static int c(Item item) {
        return d.b().stream().filter(itemStack -> {
            boolean bl;
            block5: {
                block4: {
                    if (itemStack.isEmpty() || itemStack.getItem() != item) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        }).mapToInt(ItemStack::getCount).sum();
    }

    public static boolean y(ItemStack itemStack) {
        block24: {
            block25: {
                if (itemStack.isEmpty()) break block24;
                if (!(itemStack.getItem() instanceof PlayerHeadItem)) break block25;
                return false;
            }
            String string = itemStack.getDisplayName().getString();
            if (string.contains("Click")) {
                return false;
            }
            if (string.contains("Right")) {
                return false;
            }
            if (string.contains("\u70b9\u51fb")) {
                return false;
            }
            if (string.contains("Teleport")) {
                return false;
            }
            if (string.contains("\u4f7f\u7528")) {
                return false;
            }
            if (string.contains("\u4f20\u9001")) {
                return false;
            }
            boolean bl = !string.contains("\u518d\u6765");
            return bl;
        }
        return true;
    }

    public static int a(int n2, int n3, Item item) {
        if (d.mc.player == null) {
            return -1;
        }
        for (int i2 = n2; i2 < n3; ++i2) {
            ItemStack itemStack = d.mc.player.getInventory().getItem(i2);
            if (itemStack.isEmpty() || itemStack.getItem() != item) {
                continue;
            }
            return i2;
        }
        return -1;
    }

    @Generated
    private d() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[18];
                var3_1 = 0;
                var2_2 = "\u51b3\u6778\u0002\u4f41\u7535\u0004\u7087\u51e6\u4f04\u755a$_i\u000f\u0000zO:JxU\u001cr@*\u0010z\u001e\u001cv_&]3\u001a\u0006gL,UB\u001f\u0013~L([\u0002\u7087\u51e6\u0004\u7985\u5f1d\u6e43\u627d\u0002\u4e3e\u521d\bjx\u0017\u0017cB=J\u0005}q\u0012\u0011x\u0002\u4f1e\u901c2ju\u0012\u00013D<\u001e|[\u0007gD#Wi\u0002RpA.Mn[\u0013}Io]|\u0015\u001c|Yo\\x[\u001b}^;_s\u000f\u001brY*Z\u0006\u9037\u62f4\u4e7b\u4e58\u960c\u4f20\u0002\u91f3\u9539\u0004\u9541\u6314\u70c2\u5189\u0004S|\u0018\u0017\u0005lt\u001c\u001ag";
                var4_3 = "\u51b3\u6778\u0002\u4f41\u7535\u0004\u7087\u51e6\u4f04\u755a$_i\u000f\u0000zO:JxU\u001cr@*\u0010z\u001e\u001cv_&]3\u001a\u0006gL,UB\u001f\u0013~L([\u0002\u7087\u51e6\u0004\u7985\u5f1d\u6e43\u627d\u0002\u4e3e\u521d\bjx\u0017\u0017cB=J\u0005}q\u0012\u0011x\u0002\u4f1e\u901c2ju\u0012\u00013D<\u001e|[\u0007gD#Wi\u0002RpA.Mn[\u0013}Io]|\u0015\u001c|Yo\\x[\u001b}^;_s\u000f\u001brY*Z\u0006\u9037\u62f4\u4e7b\u4e58\u960c\u4f20\u0002\u91f3\u9539\u0004\u9541\u6314\u70c2\u5189\u0004S|\u0018\u0017\u0005lt\u001c\u001ag".length();
                var1_4 = 2;
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
                    var5[var3_1++] = d.b(d.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u51b3\u6778\u4e7b\u5c32\u0002\u4e3e\u521d";
                    var4_3 = "\u51b3\u6778\u4e7b\u5c32\u0002\u4e3e\u521d".length();
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
                    var5[var3_1++] = d.b(d.a(v1));
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
        d.a = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x4F);
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
                    case 0 -> 62;
                    case 1 -> 29;
                    case 2 -> 123;
                    case 3 -> 114;
                    case 4 -> 19;
                    case 5 -> 45;
                    default -> 79;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

