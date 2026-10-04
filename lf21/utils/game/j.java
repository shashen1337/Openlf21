/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import lf21.utils.game.d;
import lf21.utils.misc.ChatUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class j {
    private static final Map<UUID, Integer> a;
    private static final Map<UUID, Set<Item>> b;
    private static final ConcurrentHashMap<Object, Set<ItemStack>> c;
    private static final String d;

    public static boolean a(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }
        return lf21.utils.game.d.q(itemStack) || lf21.utils.game.d.r(itemStack) || lf21.utils.game.d.s(itemStack) || lf21.utils.game.d.t(itemStack) || lf21.utils.game.d.u(itemStack) || lf21.utils.game.d.c(itemStack) > 2 && itemStack.getItem() instanceof BowItem || lf21.utils.game.d.d(itemStack) > 3 && itemStack.getItem() instanceof BowItem;
    }

    public static void a(Player player, ItemStack itemStack) {
        if (!lf21.utils.game.d.q(itemStack)) {
            return;
        }
        int n2 = itemStack.getDamageValue();
        UUID uUID2 = player.getUUID();
        Integer n3 = a.get(uUID2);
        if (n3 != null && n2 > n3) {
            ChatUtil.print(String.format(d, player.getName().getString()));
            b.computeIfAbsent(uUID2, uUID -> new HashSet()).add(itemStack.getItem());
        }
        a.put(uUID2, n2);
    }

    public static void a(Object object2, ItemStack itemStack) {
        if (!j.a(itemStack)) {
            return;
        }
        Set set = c.computeIfAbsent(object2, object -> new CopyOnWriteArraySet());
        if (set.stream().noneMatch(itemStack2 -> ItemStack.matches((ItemStack)itemStack2, (ItemStack)itemStack))) {
            set.add(itemStack);
        }
    }

    public static Set<ItemStack> a(Object object) {
        return c.getOrDefault(object, Collections.emptySet());
    }

    public static boolean a(UUID uUID, Item item) {
        Set set = b.getOrDefault(uUID, Collections.emptySet());
        return set.contains(item);
    }

    public static void a() {
        a.clear();
        b.clear();
        c.clear();
    }

    public static void b(Object object) {
        c.remove(object);
    }

    public static void a(Set<?> set) {
        c.keySet().removeIf(object -> !set.contains(object));
    }

    public static Integer a(UUID uUID) {
        return a.get(uUID);
    }

    public static void a(UUID uUID, int n2) {
        a.put(uUID, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string = j.a(j.a("\u00fc\u0001m/J.&\u000f?\u0016\u00c9`N\u0007{\u0017E\u000bbK\u0015{%Y\n&*\f>C"));
        d = string;
        a = new ConcurrentHashMap<UUID, Integer>();
        b = new ConcurrentHashMap<UUID, Set<Item>>();
        c = new ConcurrentHashMap();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x74);
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
                    case 0 -> 91;
                    case 1 -> 98;
                    case 2 -> 54;
                    case 3 -> 110;
                    case 4 -> 6;
                    case 5 -> 107;
                    default -> 116;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

