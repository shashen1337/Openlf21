/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.UpdateHeldItemEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.render.ad;
import lf21.settings.impl.BooleanSetting;
import lf21.utils.game.d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class AutoTools
extends Module {
    private BlockPos p;
    private final BooleanSetting switchBack;
    private static final String[] r;
    private int q;
    public static String[] n;
    private int o;
    public static AutoTools j;
    private final BooleanSetting silent;
    private final BooleanSetting checkSword;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "k|\u0002@?cvFz\u000bia\u0013L\u0000,J]f\u0004K";
                var4_3 = "k|\u0002@?cvFz\u000bia\u0013L\u0000,J]f\u0004K".length();
                var1_4 = 9;
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
                    var5[var3_1++] = AutoTools.c(AutoTools.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "y~\u001f[\bd9hh\u0015D\u0006y`\u001aJ\u0005x";
                    var4_3 = "y~\u001f[\bd9hh\u0015D\u0006y`\u001aJ\u0005x".length();
                    var1_4 = 11;
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
                    var5[var3_1++] = AutoTools.c(AutoTools.a(v1));
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
        AutoTools.r = var5;
    }

    @EventTarget
    public void a(UpdateHeldItemEvent updateHeldItemEvent) {
        if (AutoTools.mc.player == null) {
            return;
        }
        if (((Boolean)this.switchBack.getValue()).booleanValue() && ((Boolean)this.silent.getValue()).booleanValue() && updateHeldItemEvent.a() == InteractionHand.MAIN_HAND && this.o != -1) {
            updateHeldItemEvent.a(AutoTools.mc.player.getInventory().getItem(this.o));
        }
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
                    case 0 -> 42;
                    case 1 -> 9;
                    case 2 -> 118;
                    case 3 -> 47;
                    case 4 -> 107;
                    case 5 -> 12;
                    default -> 25;
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
            cArray[0] = (char)(cArray[0] ^ 0x19);
        }
        return cArray;
    }

    public int a(BlockPos blockPos) {
        if (AutoTools.mc.player == null || AutoTools.mc.level == null) {
            return -1;
        }
        BlockState blockState = AutoTools.mc.level.getBlockState(blockPos);
        Block block = blockState.getBlock();
        int n2 = 0;
        float f2 = 1.0f;
        for (int i2 = 0; i2 < 9; ++i2) {
            int n3;
            ItemStack itemStack = AutoTools.mc.player.getInventory().getItem(i2);
            if (d.l(itemStack) || itemStack.isEmpty() || blockState.isAir() || itemStack.getItem() instanceof SwordItem && !(block instanceof WebBlock)) continue;
            float f3 = itemStack.getItem().getDestroySpeed(itemStack, blockState);
            if (f3 > 1.0f && !(block instanceof DropExperienceBlock) && !(block instanceof RedStoneOreBlock) && (n3 = EnchantmentHelper.getItemEnchantmentLevel((Enchantment)Enchantments.BLOCK_EFFICIENCY, (ItemStack)itemStack)) > 0) {
                f3 += (float)(n3 * n3 + 1);
            }
            if (!(f3 > f2)) continue;
            n2 = i2;
            f2 = f3;
        }
        if (f2 > 1.0f) {
            return n2;
        }
        return -1;
    }

    public void b(BlockPos blockPos) {
        if (AutoTools.mc.player == null || AutoTools.mc.level == null) {
            return;
        }
        this.p = blockPos.immutable();
        this.q = 3;
        int n2 = this.a(blockPos);
        if (n2 != -1 && n2 != AutoTools.mc.player.getInventory().selected) {
            if (this.o == -1) {
                this.o = AutoTools.mc.player.getInventory().selected;
            }
            AutoTools.mc.player.getInventory().selected = n2;
        }
    }

    public AutoTools() {
        String[] stringArray = r;
        super("AutoTools", Category.WORLD);
        this.checkSword = new BooleanSetting("Check Sword", true);
        this.switchBack = new BooleanSetting("Switch Back", true);
        this.silent = new BooleanSetting("Silent", true);
        this.o = -1;
        j = this;
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (AutoTools.mc.player == null || AutoTools.mc.gameMode == null) {
            return;
        }
        if (motionEvent.isPost()) {
            if (AutoTools.mc.gameMode.isDestroying()) {
                int n2;
                BlockPos blockPos;
                ItemStack itemStack;
                boolean bl;
                boolean bl2 = bl = this.p != null && this.q > 0;
                if (!bl && ((Boolean)this.checkSword.getValue()).booleanValue() && (itemStack = AutoTools.mc.player.getMainHandItem()).getItem() instanceof SwordItem) {
                    return;
                }
                BlockPos blockPos2 = blockPos = bl ? this.p : null;
                if (blockPos == null && AutoTools.mc.hitResult != null && AutoTools.mc.hitResult.getType() == HitResult.Type.BLOCK) {
                    blockPos = ((BlockHitResult)AutoTools.mc.hitResult).getBlockPos();
                }
                if (blockPos != null && (n2 = this.a(blockPos)) != -1 && n2 != AutoTools.mc.player.getInventory().selected) {
                    if (this.o == -1) {
                        this.o = AutoTools.mc.player.getInventory().selected;
                    }
                    AutoTools.mc.player.getInventory().selected = n2;
                }
                if (this.q > 0) {
                    --this.q;
                    if (this.q == 0) {
                        this.p = null;
                    }
                }
            }
        } else if (!AutoTools.mc.gameMode.isDestroying() && ((Boolean)this.switchBack.getValue()).booleanValue() && this.o != -1) {
            AutoTools.mc.player.getInventory().selected = this.o;
            this.o = -1;
            this.p = null;
            this.q = 0;
        }
    }

    public static Object a() {
        try {
            Class[] classArray = new Class[]{String.class};
            Class<?> clazz = Class.forName(ad.a(n[0]));
            return clazz.getMethod(ad.b(n[1], clazz, classArray), classArray);
        }
        catch (Exception exception) {
            return null;
        }
    }
}

