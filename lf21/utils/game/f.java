/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import java.util.List;
import java.util.Optional;
import lf21.ClientBase;
import lf21.utils.game.g;
import lf21.utils.rotation.a;
import lombok.Generated;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class f
extends ClientBase {
    private static final String a;
    private static final String[] b;

    public static boolean a(a a2, Direction direction, BlockPos blockPos, boolean bl) {
        boolean bl2;
        block19: {
            block18: {
                boolean bl3;
                boolean bl4;
                block17: {
                    block16: {
                        block15: {
                            if (f.mc.player != null && f.mc.level != null) break block15;
                            return false;
                        }
                        float f2 = a2.e();
                        float f3 = a2.f();
                        Vec3 vec3 = f.mc.player.getEyePosition(1.0f);
                        Vec3 vec32 = Vec3.directionFromRotation((float)f3, (float)f2);
                        Vec3 vec33 = vec3.add(vec32.scale(5.0));
                        BlockHitResult blockHitResult = f.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)f.mc.player));
                        if (blockHitResult.getType() != HitResult.Type.BLOCK) {
                            return false;
                        }
                        bl4 = blockHitResult.getBlockPos().equals((Object)blockPos);
                        if (bl && blockHitResult.getDirection() != direction) break block16;
                        bl3 = true;
                        break block17;
                    }
                    bl3 = false;
                }
                boolean bl5 = bl3;
                if (!bl4 || !bl5) break block18;
                bl2 = true;
                break block19;
            }
            bl2 = false;
        }
        return bl2;
    }

    public static HitResult a(float f2, a a2) {
        HitResult hitResult;
        block2: {
            hitResult = null;
            Entity entity = mc.getCameraEntity();
            if (entity == null || f.mc.level == null) break block2;
            double d2 = f.mc.gameMode.getPickRange();
            hitResult = f.a(d2, f2, true, a2.e(), a2.f());
        }
        return hitResult;
    }

    public static HitResult a(double d2, float f2, boolean bl, a a2) {
        HitResult hitResult;
        block2: {
            hitResult = null;
            Entity entity = mc.getCameraEntity();
            if (entity == null || f.mc.level == null) break block2;
            hitResult = f.a(d2, f2, bl, a2.e(), a2.f());
        }
        return hitResult;
    }

    public static Vec3 a(float f2, float f3) {
        float f4 = f3 * ((float)Math.PI / 180);
        float f5 = -f2 * ((float)Math.PI / 180);
        float f6 = Mth.cos((float)f5);
        float f7 = Mth.sin((float)f5);
        float f8 = Mth.cos((float)f4);
        float f9 = Mth.sin((float)f4);
        return new Vec3((double)(f7 * f8), (double)(-f9), (double)(f6 * f8));
    }

    public static HitResult a(double d2, float f2, boolean bl, float f3, float f4) {
        ClipContext clipContext;
        Vec3 vec3 = new Vec3(f.mc.player.getX(), f.mc.player.getY() + 1.62, f.mc.player.getZ());
        Vec3 vec32 = f.a(f4, f3);
        Vec3 vec33 = vec3.add(vec32.x * d2, vec32.y * d2, vec32.z * d2);
        Level level = f.mc.player.level();
        ClipContext clipContext2 = clipContext;
        ClipContext clipContext3 = clipContext;
        Vec3 vec34 = vec3;
        Vec3 vec35 = vec33;
        ClipContext.Block block = ClipContext.Block.OUTLINE;
        ClipContext.Fluid fluid = bl ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE;
        clipContext2(vec34, vec35, block, fluid, (Entity)f.mc.player);
        return level.clip(clipContext3);
    }

    public static HitResult a(Vec3 vec3, Vec3 vec32, boolean bl, boolean bl2, boolean bl3, Entity entity) {
        ClipContext.Block block;
        block7: {
            block6: {
                if (!bl2) break block6;
                block = ClipContext.Block.COLLIDER;
                break block7;
            }
            block = bl3 ? ClipContext.Block.VISUAL : ClipContext.Block.OUTLINE;
        }
        ClipContext.Block block2 = block;
        ClipContext.Fluid fluid = bl ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE;
        ClipContext.Fluid fluid2 = fluid;
        ClipContext clipContext = new ClipContext(vec3, vec32, block2, fluid2, entity);
        return f.mc.level.clip(clipContext);
    }

    public static EntityHitResult a(AABB aABB, Vec3 vec32, Vec3 vec33) {
        Optional optional = aABB.clip(vec32, vec33);
        return optional.map(vec3 -> new EntityHitResult(null, vec3)).orElse(null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static HitResult a(a a2, double d2, float f2, Entity entity, Entity entity2, boolean bl) {
        if (entity == null || f.mc.level == null) {
            return null;
        }
        float f3 = mc.getFrameTime();
        Vec3 vec3 = entity.getEyePosition(f3);
        Vec3 vec32 = g.b(a2);
        Vec3 vec33 = vec3.add(vec32.x * d2, vec32.y * d2, vec32.z * d2);
        BlockHitResult blockHitResult = null;
        double d3 = d2;
        if (!bl) {
            blockHitResult = f.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity));
            d3 = blockHitResult.getType() == HitResult.Type.BLOCK ? vec3.distanceTo(blockHitResult.getLocation()) : d2;
        }
        double d4 = Math.min(d2, d3) + (double)f2;
        AABB aABB = new AABB(vec3.x - d4, vec3.y - d4, vec3.z - d4, vec3.x + d4, vec3.y + d4, vec3.z + d4);
        List list = f.mc.level.getEntitiesOfClass(Entity.class, aABB, entity3 -> {
            boolean bl;
            block12: {
                block10: {
                    block11: {
                        if (entity3 == entity) break block10;
                        if (entity2 == null) break block11;
                        if (entity3 != entity2) break block10;
                    }
                    if (!EntitySelector.NO_SPECTATORS.test(entity3)) break block10;
                    if (!entity3.isPickable()) break block10;
                    bl = true;
                    break block12;
                }
                bl = false;
            }
            return bl;
        });
        Entity entity4 = null;
        Vec3 vec34 = null;
        double d5 = Math.min(d2, d3);
        d5 *= d5;
        for (Entity entity5 : list) {
            Vec3 vec35;
            BlockHitResult blockHitResult2;
            boolean bl2;
            Vec3 vec36;
            double d6;
            AABB aABB2 = entity5.getBoundingBox().inflate((double)f2);
            Optional optional = aABB2.clip(vec3, vec33);
            if (!optional.isPresent() || !((d6 = vec3.distanceToSqr(vec36 = (Vec3)optional.get())) < d5)) continue;
            boolean bl3 = bl2 = bl || (blockHitResult2 = f.mc.level.clip(new ClipContext(vec3, vec35 = aABB2.getCenter(), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity))).getType() != HitResult.Type.BLOCK || !(vec3.distanceToSqr(blockHitResult2.getLocation()) <= d6);
            if (!bl2) continue;
            d5 = d6;
            entity4 = entity5;
            vec34 = vec36;
        }
        if (entity4 != null) {
            return new EntityHitResult(entity4, vec34);
        }
        if (!bl && blockHitResult != null) {
            return blockHitResult;
        }
        return f.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity));
    }

    @Generated
    private f() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "*ue:z\u0010C^|,<.\u0010\\\u0017iui9\u0015Q\rn,(4\u001d\u0010\u001d|b'5\r\u0010\u001cx, 4\nD\u001fsx ;\rU\u001a2*ue:z\u0010C^|,<.\u0010\\\u0017iui9\u0015Q\rn,(4\u001d\u0010\u001d|b'5\r\u0010\u001cx, 4\nD\u001fsx ;\rU\u001a";
        int n3 = "*ue:z\u0010C^|,<.\u0010\\\u0017iui9\u0015Q\rn,(4\u001d\u0010\u001d|b'5\r\u0010\u001cx, 4\nD\u001fsx ;\rU\u001a2*ue:z\u0010C^|,<.\u0010\\\u0017iui9\u0015Q\rn,(4\u001d\u0010\u001d|b'5\r\u0010\u001cx, 4\nD\u001fsx ;\rU\u001a".length();
        int n4 = 50;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = f.b(f.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                b = stringArray;
                a = b[0];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x30);
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
                    case 0 -> 126;
                    case 1 -> 29;
                    case 2 -> 12;
                    case 3 -> 73;
                    case 4 -> 90;
                    case 5 -> 121;
                    default -> 48;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

