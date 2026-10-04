/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import lf21.event.EventTarget;
import lf21.event.impl.PreMotionEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.utils.game.a;
import lf21.utils.game.b;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class n
extends Module {
    private static final String j = n.c(n.a("i\u001a\u001bI8={@\u0016"));

    private AABB a(ChestBlockEntity chestBlockEntity) {
        BlockPos blockPos;
        BlockState blockState = chestBlockEntity.getBlockState();
        if (!blockState.hasProperty((Property)ChestBlock.TYPE)) {
            return null;
        }
        ChestType chestType = (ChestType)blockState.getValue((Property)ChestBlock.TYPE);
        if (chestType == ChestType.LEFT) {
            return null;
        }
        BlockPos blockPos2 = chestBlockEntity.getBlockPos();
        AABB aABB = a.c(blockPos2);
        if (chestType != ChestType.SINGLE && a.b(blockPos = blockPos2.relative(ChestBlock.getConnectedDirection((BlockState)blockState)))) {
            aABB = aABB.minmax(a.c(blockPos));
        }
        return aABB;
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
                    case 0 -> 46;
                    case 1 -> 114;
                    case 2 -> 116;
                    case 3 -> 58;
                    case 4 -> 76;
                    case 5 -> 117;
                    default -> 26;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        new HashSet();
    }

    public boolean a() {
        if (n.mc.player == null || n.mc.level == null) {
            return false;
        }
        Vec3 vec3 = n.mc.player.getEyePosition(1.0f);
        Vec3 vec32 = n.mc.player.getViewVector(1.0f);
        Vec3 vec33 = vec3.add(vec32.scale(4.5));
        ChestBlockEntity chestBlockEntity = null;
        BlockHitResult blockHitResult = null;
        double d2 = Double.MAX_VALUE;
        ArrayList arrayList = b.a().collect(Collectors.toCollection(ArrayList::new));
        for (BlockEntity blockEntity : arrayList) {
            double d3;
            Optional optional;
            ChestBlockEntity chestBlockEntity2;
            AABB aABB;
            if (!(blockEntity instanceof ChestBlockEntity) || (aABB = this.a(chestBlockEntity2 = (ChestBlockEntity)blockEntity)) == null || !(optional = aABB.clip(vec3, vec33)).isPresent() || !((d3 = ((Vec3)optional.get()).distanceTo(vec3)) < d2)) continue;
            d2 = d3;
            chestBlockEntity = chestBlockEntity2;
            Vec3 vec34 = (Vec3)optional.get();
            BlockPos blockPos = BlockPos.containing((double)vec34.x, (double)vec34.y, (double)vec34.z);
            if (!(n.mc.level.getBlockEntity(blockPos) instanceof ChestBlockEntity)) {
                blockPos = chestBlockEntity2.getBlockPos();
            }
            AABB aABB2 = a.c(blockPos);
            Direction direction = this.a(vec34, aABB2, vec3);
            blockHitResult = new BlockHitResult(vec34, direction, blockPos, false);
        }
        if (chestBlockEntity != null && blockHitResult != null) {
            n.mc.gameMode.useItemOn(n.mc.player, InteractionHand.MAIN_HAND, blockHitResult);
            n.mc.player.swing(InteractionHand.MAIN_HAND);
            return true;
        }
        return false;
    }

    public n() {
        super(j, Category.PLAYER);
    }

    @EventTarget
    public void a(PreMotionEvent preMotionEvent) {
        if (n.mc.options.keyUse.isDown() && this.a()) {
            preMotionEvent.a(true);
        }
    }

    private Direction a(Vec3 vec3, AABB aABB, Vec3 vec32) {
        double d2 = 1.0E-4;
        if (Math.abs(vec3.x - aABB.minX) <= d2) {
            return Direction.WEST;
        }
        if (Math.abs(vec3.x - aABB.maxX) <= d2) {
            return Direction.EAST;
        }
        if (Math.abs(vec3.y - aABB.minY) <= d2) {
            return Direction.DOWN;
        }
        if (Math.abs(vec3.y - aABB.maxY) <= d2) {
            return Direction.UP;
        }
        if (Math.abs(vec3.z - aABB.minZ) <= d2) {
            return Direction.NORTH;
        }
        if (Math.abs(vec3.z - aABB.maxZ) <= d2) {
            return Direction.SOUTH;
        }
        double[] dArray = new double[]{Math.abs(vec3.x - aABB.minX), Math.abs(vec3.x - aABB.maxX), Math.abs(vec3.y - aABB.minY), Math.abs(vec3.y - aABB.maxY), Math.abs(vec3.z - aABB.minZ), Math.abs(vec3.z - aABB.maxZ)};
        Direction[] directionArray = new Direction[]{Direction.WEST, Direction.EAST, Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH};
        int n2 = 0;
        for (int i2 = 1; i2 < dArray.length; ++i2) {
            if (!(dArray[i2] < dArray[n2])) continue;
            n2 = i2;
        }
        return directionArray[n2];
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x1A);
        }
        return cArray;
    }
}

