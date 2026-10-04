/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.misc;

import java.util.HashSet;
import java.util.Set;
import lf21.event.EventTarget;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.e;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.utils.misc.ChatUtil;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.BlockItem;

public class BlockHideSeekDetector
extends Module {
    private final BooleanSetting chatAlert;
    private static final String[] n;
    private final Set<Integer> m;
    private final BooleanSetting glow;
    public static BlockHideSeekDetector j;

    @Override
    protected void onDisable() {
        this.a();
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
                    case 0 -> 72;
                    case 1 -> 127;
                    case 2 -> 91;
                    case 3 -> 99;
                    case 4 -> 1;
                    case 5 -> 77;
                    default -> 38;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void a() {
        if (BlockHideSeekDetector.mc.level != null) {
            for (Entity entity : BlockHideSeekDetector.mc.level.entitiesForRendering()) {
                if (!this.m.contains(entity.getId())) continue;
                entity.setGlowingTag(false);
            }
        }
        this.m.clear();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "\n\u00134\u0000jmB!\f<\u0016h>Ch\u001b>\u0017d.R-\u001b{\u0002um\u0002d_\u0015\n\u00134\u0000j\u0005O,\u001a\b\u0006d&b-\u000b>\u0000u\"T\u0002d_";
                var4_3 = "\n\u00134\u0000jmB!\f<\u0016h>Ch\u001b>\u0017d.R-\u001b{\u0002um\u0002d_\u0015\n\u00134\u0000j\u0005O,\u001a\b\u0006d&b-\u000b>\u0000u\"T\u0002d_".length();
                var1_4 = 27;
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
                    var5[var3_1++] = BlockHideSeekDetector.c(BlockHideSeekDetector.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u000f\u00134\u0014\n\u000b\u0017:\u0017!\fJ-\r/";
                    var4_3 = "\u000f\u00134\u0014\n\u000b\u0017:\u0017!\fJ-\r/".length();
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
                    var5[var3_1++] = BlockHideSeekDetector.c(BlockHideSeekDetector.a(v1));
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
        BlockHideSeekDetector.n = var5;
    }

    @EventTarget
    public void a(e e2) {
        this.a();
    }

    public static boolean shouldGlow(Entity entity) {
        BlockHideSeekDetector blockHideSeekDetector = j;
        return blockHideSeekDetector != null && blockHideSeekDetector.isEnabled() && (Boolean)blockHideSeekDetector.glow.getValue() != false && entity != null && blockHideSeekDetector.m.contains(entity.getId());
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        if (BlockHideSeekDetector.mc.level == null) {
            return;
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (Entity entity : BlockHideSeekDetector.mc.level.entitiesForRendering()) {
            if (!BlockHideSeekDetector.a(entity)) continue;
            hashSet.add(entity.getId());
            entity.setGlowingTag(((Boolean)this.glow.getValue()).booleanValue());
            if (!this.m.add(entity.getId()) || !((Boolean)this.chatAlert.getValue()).booleanValue()) continue;
            int n2 = entity.blockPosition().getZ();
            int n3 = entity.blockPosition().getY();
            int n4 = entity.blockPosition().getX();
            String[] stringArray = n;
            ChatUtil.print("Block disguise detected at " + n4 + ", " + n3 + ", " + n2);
        }
        this.m.retainAll(hashSet);
    }

    private static boolean a(Entity entity) {
        ArmorStand armorStand;
        if (entity instanceof FallingBlockEntity || entity instanceof Display.BlockDisplay) {
            return true;
        }
        if (!(entity instanceof ArmorStand) || !(armorStand = (ArmorStand)entity).isInvisible()) {
            return false;
        }
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (!(armorStand.getItemBySlot(equipmentSlot).getItem() instanceof BlockItem)) continue;
            return true;
        }
        return false;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x26);
        }
        return cArray;
    }

    public BlockHideSeekDetector() {
        String[] stringArray = n;
        super("BlockHideSeekDetector", Category.MISC);
        this.glow = new BooleanSetting("Glow", true);
        this.chatAlert = new BooleanSetting("Chat Alert", false);
        this.m = new HashSet<Integer>();
        j = this;
    }
}

