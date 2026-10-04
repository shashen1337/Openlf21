/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import com.google.common.collect.Multimap;
import lf21.event.EventTarget;
import lf21.event.impl.AttackEntityEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class i
extends Module {
    private static final String j = i.c(i.a("4\u001fc\u001cjN\u0017\u0007\u000e"));

    private double a(ItemStack itemStack, LivingEntity livingEntity) {
        double d2 = i.mc.player.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
        double d3 = 0.0;
        double d4 = 0.0;
        double d5 = 1.0;
        Multimap multimap = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);
        for (AttributeModifier attributeModifier : multimap.get((Object)Attributes.ATTACK_DAMAGE)) {
            switch (attributeModifier.getOperation()) {
                case ADDITION: {
                    d3 += attributeModifier.getAmount();
                    break;
                }
                case MULTIPLY_BASE: {
                    d4 += attributeModifier.getAmount();
                    break;
                }
                case MULTIPLY_TOTAL: {
                    d5 *= 1.0 + attributeModifier.getAmount();
                }
            }
        }
        double d6 = (d2 + d3) * (1.0 + d4) * d5;
        if (livingEntity != null) {
            d6 += (double)EnchantmentHelper.getDamageBonus((ItemStack)itemStack, (MobType)livingEntity.getMobType());
        }
        return d6;
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
                    case 0 -> 117;
                    case 1 -> 106;
                    case 2 -> 23;
                    case 3 -> 115;
                    case 4 -> 57;
                    case 5 -> 57;
                    default -> 120;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private int a(LivingEntity livingEntity, boolean bl) {
        int n2 = -1;
        double d2 = Double.NEGATIVE_INFINITY;
        for (int i2 = 0; i2 < 9; ++i2) {
            double d3;
            ItemStack itemStack = i.mc.player.getInventory().getItem(i2);
            Item item = itemStack.getItem();
            if ((!bl ? !(item instanceof AxeItem) : !(item instanceof SwordItem)) || !((d3 = this.a(itemStack, livingEntity)) > d2)) continue;
            d2 = d3;
            n2 = i2;
        }
        return n2;
    }

    public i() {
        super(j, Category.COMBAT);
    }

    @EventTarget
    public void a(AttackEntityEvent attackEntityEvent) {
        int n2;
        LivingEntity livingEntity;
        if (i.mc.player == null || attackEntityEvent.a() == null) {
            return;
        }
        Entity entity = attackEntityEvent.a();
        LivingEntity livingEntity2 = entity instanceof LivingEntity ? (livingEntity = (LivingEntity)entity) : null;
        livingEntity = i.mc.player.getMainHandItem().getItem();
        if (livingEntity instanceof SwordItem) {
            n2 = this.a(livingEntity2, true);
        } else if (livingEntity instanceof AxeItem) {
            n2 = this.a(livingEntity2, false);
        } else {
            n2 = this.a(livingEntity2, true);
            if (n2 == -1) {
                n2 = this.a(livingEntity2, false);
            }
        }
        if (n2 == -1) {
            n2 = this.a();
        }
        if (n2 != -1 && n2 != i.mc.player.getInventory().selected) {
            i.mc.player.getInventory().selected = n2;
        }
    }

    private int a() {
        int n2 = -1;
        int n3 = -1;
        for (int i2 = 0; i2 < 9; ++i2) {
            int n4;
            ItemStack itemStack = i.mc.player.getInventory().getItem(i2);
            Item item = itemStack.getItem();
            if (item != Items.STICK && item != Items.SLIME_BALL || !itemStack.isEnchanted() || (n4 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.KNOCKBACK, (ItemStack)itemStack)) <= n3) continue;
            n3 = n4;
            n2 = i2;
        }
        return n2;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x78);
        }
        return cArray;
    }
}

