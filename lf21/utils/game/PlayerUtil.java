/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import com.mojang.blaze3d.platform.InputConstants;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import lf21.ClientBase;
import lf21.render.ad;
import lf21.utils.game.g;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.ReflectionUtil;
import lf21.utils.rotation.a;
import lombok.Generated;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class PlayerUtil
extends ClientBase {
    private static final String[] a;

    public static void updateWalkAnim() {
        if (PlayerUtil.mc.player == null) {
            return;
        }
        PlayerUtil.mc.player.walkAnimation.setSpeed(PlayerUtil.mc.player.walkAnimation.speed());
        double d2 = PlayerUtil.mc.player.getX() - PlayerUtil.mc.player.xo;
        double d3 = PlayerUtil.mc.player.getZ() - PlayerUtil.mc.player.zo;
        float f2 = Mth.sqrt((float)((float)(d2 * d2 + d3 * d3))) * 4.0f;
        f2 = Mth.clamp((float)f2, (float)0.0f, (float)1.0f);
        PlayerUtil.mc.player.walkAnimation.update(f2, 0.4f);
    }

    public static void a() {
        try {
            String[] stringArray = a;
            Class[] classArray = new Class[]{};
            Class<?> clazz = PlayerUtil.mc.gameMode.getClass();
            Method method = clazz.getDeclaredMethod(ad.b(ReflectionUtil.getMappedMethodName(PlayerUtil.mc.gameMode.getClass(), "ensureHasSentCarriedItem", "()V"), clazz, classArray), classArray);
            method.setAccessible(true);
            method.invoke(PlayerUtil.mc.gameMode, new Object[0]);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            ChatUtil.print("Failed to set item!");
        }
    }

    public static boolean a(double d2) {
        int n2 = 0;
        while ((double)n2 < d2) {
            AABB aABB = PlayerUtil.mc.player.getBoundingBox().move(0.0, (double)(-n2), 0.0);
            if (!PlayerUtil.a((Entity)PlayerUtil.mc.player, aABB)) {
                return true;
            }
            n2 += 2;
        }
        return false;
    }

    public static boolean a(@Nullable Entity entity, AABB aABB) {
        for (VoxelShape voxelShape : PlayerUtil.mc.level.getBlockCollisions(entity, aABB)) {
            if (voxelShape.isEmpty()) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void a(int n2, boolean bl) {
        InputConstants.Key key = n2 == 0 ? PlayerUtil.mc.options.keyAttack.getKey() : PlayerUtil.mc.options.keyUse.getKey();
        InputConstants.Key key2 = key;
        KeyMapping.set((InputConstants.Key)key2, (boolean)bl);
        if (bl) {
            KeyMapping.click((InputConstants.Key)key2);
        }
    }

    public static int a(LivingEntity livingEntity) {
        int n2 = 0;
        for (ItemStack itemStack : livingEntity.getArmorSlots()) {
            Item item = itemStack.getItem();
            if (!(item instanceof ArmorItem)) continue;
            ArmorItem armorItem = (ArmorItem)item;
            n2 += armorItem.getDefense();
        }
        return n2;
    }

    public static Block a(double d2, double d3, double d4) {
        return PlayerUtil.a(BlockPos.containing((double)d2, (double)d3, (double)d4));
    }

    public static Block a(BlockPos blockPos) {
        block4: {
            if (PlayerUtil.mc.level != null && PlayerUtil.mc.player != null) break block4;
            return null;
        }
        return PlayerUtil.mc.level.getBlockState(blockPos).getBlock();
    }

    public static Block a(Player player) {
        return PlayerUtil.a(BlockPos.containing((double)player.getX(), (double)(player.getY() - 1.0), (double)player.getZ()));
    }

    public static HitResult a(double d2, float f2, float f3) {
        block4: {
            if (PlayerUtil.mc.player != null && PlayerUtil.mc.level != null) break block4;
            return null;
        }
        Vec3 vec3 = PlayerUtil.mc.player.getEyePosition(1.0f);
        Vec3 vec32 = g.b(new a(f2, f3));
        Vec3 vec33 = vec3.add(vec32.x * d2, vec32.y * d2, vec32.z * d2);
        return PlayerUtil.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)PlayerUtil.mc.player));
    }

    public static boolean b() {
        block21: {
            boolean bl;
            block24: {
                block23: {
                    BlockPos blockPos;
                    block22: {
                        block20: {
                            if (PlayerUtil.mc.player != null && PlayerUtil.mc.level != null) break block20;
                            return true;
                        }
                        Vec3 vec3 = PlayerUtil.mc.player.position();
                        BlockPos blockPos2 = new BlockPos((int)Math.floor(vec3.x + Math.sin(Math.toRadians(PlayerUtil.mc.player.getYRot())) * 0.8), (int)Math.floor(vec3.y), (int)Math.floor(vec3.z + -Math.cos(Math.toRadians(PlayerUtil.mc.player.getYRot())) * 0.8));
                        if (PlayerUtil.mc.level.getBlockState(blockPos2).is(Blocks.LAVA)) {
                            return false;
                        }
                        blockPos = blockPos2.below();
                        BlockState blockState = PlayerUtil.mc.level.getBlockState(blockPos);
                        if (blockState.is(Blocks.LAVA)) {
                            return false;
                        }
                        if (!blockState.isAir()) break block21;
                        if (blockPos.getY() >= PlayerUtil.mc.level.getMinBuildHeight()) break block22;
                        return false;
                    }
                    if (!PlayerUtil.mc.level.getBlockState(blockPos.below()).isAir()) break block21;
                    if (blockPos.below().getY() < PlayerUtil.mc.level.getMinBuildHeight()) break block23;
                    if (PlayerUtil.mc.level.getBlockState(blockPos.below().below()).isAir()) break block23;
                    bl = true;
                    break block24;
                }
                bl = false;
            }
            return bl;
        }
        return true;
    }

    @Generated
    private PlayerUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "Dx]\u0018\t?x)'|\u0001\r\"X9;m\n\r#y50}\u0000\u00184f";
                var4_3 = "Dx]\u0018\t?x)'|\u0001\r\"X9;m\n\r#y50}\u0000\u00184f".length();
                var1_4 = 3;
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
                    var5[var3_1++] = PlayerUtil.b(PlayerUtil.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "89b/up:L0+)!p%\u0005%r|6u(\u001f\"+=;}i\u000f0e2:mi\u000e4+5;j=\r?\u007f54m,\b\u0013*0b00}i\u0018>+/0mi\u0005%n1t";
                    var4_3 = "89b/up:L0+)!p%\u0005%r|6u(\u001f\"+=;}i\u000f0e2:mi\u000e4+5;j=\r?\u007f54m,\b\u0013*0b00}i\u0018>+/0mi\u0005%n1t".length();
                    var1_4 = 50;
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
                    var5[var3_1++] = PlayerUtil.b(PlayerUtil.a(v1));
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
        PlayerUtil.a = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x49);
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
                    case 0 -> 108;
                    case 1 -> 81;
                    case 2 -> 11;
                    case 3 -> 92;
                    case 4 -> 85;
                    case 5 -> 25;
                    default -> 73;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

