/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lf21.utils.game.d;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public final class h {
    private static final Map<String, List<ItemStack>> a;
    private static Object b;
    private static final String[] c;

    private h() {
    }

    public static List<ItemStack> a(Player player) {
        Object object2;
        Level level = player.level();
        if (b != level) {
            a.clear();
            b = level;
        }
        LinkedHashMap<String, ItemStack> linkedHashMap = new LinkedHashMap<String, ItemStack>();
        h.a(linkedHashMap, player.getMainHandItem());
        h.a(linkedHashMap, player.getOffhandItem());
        for (Object object2 : player.getArmorSlots()) {
            h.a(linkedHashMap, (ItemStack)object2);
        }
        if (player.getInventory() != null) {
            for (Object object2 : player.getInventory().items) {
                h.a(linkedHashMap, (ItemStack)object2);
            }
        }
        String string = player.getGameProfile().getName().toLowerCase(Locale.ROOT);
        if (!linkedHashMap.isEmpty()) {
            object2 = new ArrayList();
            linkedHashMap.values().forEach(arg_0 -> h.b((ArrayList)object2, arg_0));
            a.put(string, (List<ItemStack>)object2);
            return object2;
        }
        object2 = a.get(string);
        if (object2 == null) {
            return new ArrayList<ItemStack>();
        }
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        object2.forEach(itemStack -> arrayList.add(itemStack.copy()));
        return arrayList;
    }

    private static void a(Map<String, ItemStack> map, ItemStack itemStack) {
        if (!h.a(itemStack)) {
            return;
        }
        String string = itemStack.getItem().toString() + "|" + itemStack.getHoverName().getString();
        map.putIfAbsent(string, itemStack.copy());
    }

    public static boolean a(ItemStack itemStack) {
        int n2;
        if (itemStack == null || itemStack.isEmpty()) {
            return false;
        }
        if (itemStack.getItem() == Items.ENCHANTED_GOLDEN_APPLE || itemStack.getItem() == Items.TOTEM_OF_UNDYING) {
            return true;
        }
        int n3 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.KNOCKBACK, (ItemStack)itemStack);
        if ((itemStack.getItem() == Items.STICK || itemStack.getItem() == Items.SLIME_BALL) && n3 > 0) {
            return true;
        }
        if (itemStack.getItem() instanceof BowItem) {
            int n4 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.POWER_ARROWS, (ItemStack)itemStack);
            n2 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.PUNCH_ARROWS, (ItemStack)itemStack);
            if (n4 >= 5 || n2 > 0) {
                return true;
            }
        }
        if (itemStack.getItem() instanceof AxeItem) {
            String string = itemStack.getHoverName().getString().toLowerCase(Locale.ROOT);
            n2 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack);
            String[] stringArray = c;
            return string.contains(stringArray[0]) || string.contains(stringArray[1]) || n2 >= 6 || d.h(itemStack) >= 15.0f;
        }
        return false;
    }

    private static /* synthetic */ void b(ArrayList arrayList, ItemStack itemStack) {
        arrayList.add(itemStack.copy());
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "+ Rm\u0002\u918b\u9565";
        int n3 = "+ Rm\u0002\u918b\u9565".length();
        int n4 = 4;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = h.a(h.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                c = stringArray;
                a = new HashMap<String, List<ItemStack>>();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x5D);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
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
                    case 0 -> 70;
                    case 1 -> 65;
                    case 2 -> 49;
                    case 3 -> 8;
                    case 4 -> 46;
                    case 5 -> 64;
                    default -> 93;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

