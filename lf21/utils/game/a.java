/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import java.util.ArrayList;
import java.util.List;
import lf21.ClientBase;
import lf21.utils.game.d;
import lombok.Generated;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class a
extends ClientBase {
    public static final List<Block> a = new ArrayList<Block>();

    public static boolean a(BlockPos blockPos) {
        block4: {
            if (lf21.utils.game.a.mc.level != null && lf21.utils.game.a.mc.player != null) break block4;
            return false;
        }
        Block block = lf21.utils.game.a.mc.level.getBlockState(blockPos).getBlock();
        return block instanceof AirBlock;
    }

    public static boolean b(BlockPos blockPos) {
        boolean bl = lf21.utils.game.a.d(blockPos) != Shapes.empty();
        return bl;
    }

    public static AABB c(BlockPos blockPos) {
        return lf21.utils.game.a.d(blockPos).bounds().move(blockPos);
    }

    private static VoxelShape d(BlockPos blockPos) {
        return lf21.utils.game.a.e(blockPos).getShape((BlockGetter)lf21.utils.game.a.mc.level, blockPos);
    }

    public static BlockState e(BlockPos blockPos) {
        if (lf21.utils.game.a.mc.level == null) {
            return Blocks.AIR.defaultBlockState();
        }
        return lf21.utils.game.a.mc.level.getBlockState(blockPos);
    }

    public static boolean a(BlockState blockState) {
        block4: {
            if (lf21.utils.game.a.mc.level != null && lf21.utils.game.a.mc.player != null) break block4;
            return false;
        }
        return lf21.utils.game.a.a(blockState.getBlock());
    }

    public static boolean f(BlockPos blockPos) {
        block4: {
            if (lf21.utils.game.a.mc.level != null && lf21.utils.game.a.mc.player != null) break block4;
            return false;
        }
        return lf21.utils.game.a.a(lf21.utils.game.a.mc.level.getBlockState(blockPos).getBlock());
    }

    public static boolean a(Block block) {
        boolean bl;
        block20: {
            block19: {
                block18: {
                    if (lf21.utils.game.a.mc.level != null && lf21.utils.game.a.mc.player != null) break block18;
                    return false;
                }
                if (block instanceof LiquidBlock || block instanceof AirBlock) break block19;
                if (block instanceof ChestBlock) break block19;
                if (block instanceof FurnaceBlock) break block19;
                if (block instanceof CraftingTableBlock) break block19;
                if (block instanceof LadderBlock) break block19;
                if (block instanceof TntBlock) break block19;
                bl = true;
                break block20;
            }
            bl = false;
        }
        return bl;
    }

    public static Block g(BlockPos blockPos) {
        block4: {
            if (lf21.utils.game.a.mc.level != null && lf21.utils.game.a.mc.player != null) break block4;
            return null;
        }
        return lf21.utils.game.a.mc.level.getBlockState(blockPos).getBlock();
    }

    public static BlockPos a(double d2, double d3, double d4) {
        return new BlockPos((int)Math.floor(d2), (int)Math.floor(d3), (int)Math.floor(d4));
    }

    public static BlockPos a(BlockPos blockPos, double d2, double d3, double d4) {
        return new BlockPos((int)Math.floor((double)blockPos.getX() + d2), (int)Math.floor((double)blockPos.getY() + d3), (int)Math.floor((double)blockPos.getZ() + d4));
    }

    public static boolean a(ItemStack itemStack) {
        block35: {
            block37: {
                block36: {
                    if (itemStack == null || !(itemStack.getItem() instanceof BlockItem)) break block35;
                    if (itemStack.getCount() <= 1) break block35;
                    if (d.y(itemStack)) break block36;
                    return false;
                }
                String string = itemStack.getDisplayName().getString();
                if (!string.contains("Click") && !string.contains("\u70b9\u51fb")) break block37;
                return false;
            }
            if (itemStack.getItem() instanceof ItemNameBlockItem) {
                return false;
            }
            Block block = ((BlockItem)itemStack.getItem()).getBlock();
            if (block instanceof FlowerBlock) {
                return false;
            }
            if (block instanceof BushBlock) {
                return false;
            }
            if (block instanceof FungusBlock) {
                return false;
            }
            if (block instanceof CropBlock) {
                return false;
            }
            if (block instanceof SlabBlock) {
                return false;
            }
            if (block instanceof WebBlock) {
                return false;
            }
            boolean bl = !a.contains(block);
            return bl;
        }
        return false;
    }

    @Generated
    private a() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

