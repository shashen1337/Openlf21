/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.world.AutoTools;
import lf21.modules.impl.world.Nuker$TargetChangeResult;
import lf21.modules.impl.world.u;
import lf21.render.D;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.h_0;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.b.i;
import lf21.utils.game.g;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.b;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.core.BlockPos;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class Nuker
extends Module {
    private BlockPos y;
    private BlockPos w;
    private BlockPos F;
    public final BooleanSetting throughWalls;
    private boolean M;
    public final BooleanSetting render;
    private int C;
    private BlockPos H;
    private int I;
    private float u;
    private BlockPos G;
    private BlockPos r;
    private final Map<BlockPos, Float> E;
    private BlockPos s;
    private boolean J;
    public a p;
    public final NumberSetting range;
    private int B;
    private Direction t;
    public static Nuker INSTANCE;
    private BlockPos q;
    private int L;
    private BlockHitResult A;
    private boolean K;
    public final NumberSetting switchDelay;
    private final List<BlockPos> D;
    private final LinkedHashSet<Block> N;
    private int z;
    private BlockPos x;
    public final BooleanSetting rotate;
    public final BooleanSetting breakAdjacentFirst;
    private static final String[] O;
    private boolean v;

    private boolean a(BlockPos blockPos) {
        block17: {
            block16: {
                block15: {
                    block14: {
                        if (blockPos == null || Nuker.mc.player == null) break block14;
                        if (Nuker.mc.level != null) break block15;
                    }
                    return false;
                }
                BlockState blockState = Nuker.mc.level.getBlockState(blockPos);
                if (!(blockState.getBlock() instanceof BedBlock) || !this.a(blockState)) break block16;
                if (!(blockState.getDestroySpeed((BlockGetter)Nuker.mc.level, blockPos) < 0.0f)) break block17;
            }
            return false;
        }
        double d2 = ((Number)this.range.getValue()).doubleValue();
        boolean bl = Nuker.mc.player.getEyePosition().distanceToSqr(Vec3.atCenterOf((Vec3i)blockPos)) <= d2 * d2;
        return bl;
    }

    private boolean i(BlockPos blockPos) {
        boolean bl;
        block12: {
            block11: {
                block10: {
                    if (blockPos != null && Nuker.mc.level != null) break block10;
                    return false;
                }
                BlockState blockState = Nuker.mc.level.getBlockState(blockPos);
                if (blockState.isAir() || blockState.is(BlockTags.BEDS)) break block11;
                if (!(blockState.getDestroySpeed((BlockGetter)Nuker.mc.level, blockPos) >= 0.0f)) break block11;
                bl = true;
                break block12;
            }
            bl = false;
        }
        return bl;
    }

    private void b(Render2DEvent render2DEvent) {
        float f2;
        block36: {
            block35: {
                if (!((Boolean)this.render.getValue()).booleanValue() || Nuker.mc.player == null) break block35;
                if (Nuker.mc.level != null) break block36;
            }
            return;
        }
        ArrayList<u> arrayList = new ArrayList<u>();
        float f3 = (float)mc.getWindow().getGuiScaledWidth() * 0.5f;
        float f4 = (float)mc.getWindow().getGuiScaledHeight() * 0.5f;
        u u2 = null;
        float f5 = Float.MAX_VALUE;
        for (BlockPos object : this.D) {
            u u3 = this.b(object, render2DEvent.c());
            if (u3 == null) {
                continue;
            }
            arrayList.add(u3);
            float f6 = u3.b - f3;
            f2 = u3.c - f4;
            float f7 = (float)Math.sqrt(f6 * f6 + f2 * f2);
            if (!(f7 <= u3.d * 0.5f) || !(f7 < f5)) continue;
            u2 = u3;
            f5 = f7;
        }
        Nuker nuker = this;
        BlockPos blockPos = u2 == null ? null : u2.a;
        nuker.F = blockPos;
        for (u u4 : arrayList) {
            boolean bl;
            boolean bl2;
            block43: {
                float f8;
                block45: {
                    block44: {
                        boolean bl3;
                        block42: {
                            block41: {
                                boolean bl4;
                                block40: {
                                    block39: {
                                        boolean bl5;
                                        block38: {
                                            block37: {
                                                if (this.F == null || !this.F.equals((Object)u4.a)) break block37;
                                                bl5 = true;
                                                break block38;
                                            }
                                            bl5 = false;
                                        }
                                        bl2 = bl5;
                                        if (this.G == null || !this.G.equals((Object)u4.a)) break block39;
                                        bl4 = true;
                                        break block40;
                                    }
                                    bl4 = false;
                                }
                                bl = bl4;
                                f2 = this.E.getOrDefault(u4.a, Float.valueOf(0.0f)).floatValue();
                                if (!bl2 && !bl) break block41;
                                bl3 = true;
                                break block42;
                            }
                            bl3 = false;
                        }
                        boolean bl6 = bl3;
                        float f9 = 0.28f;
                        float f10 = f2;
                        float f11 = bl6 ? 1.0f : 0.0f;
                        f2 = Mth.lerp((float)f9, (float)f10, (float)f11);
                        float f12 = f2;
                        float f13 = bl6 ? 1.0f : 0.0f;
                        if (!(Math.abs(f12 - f13) < 0.01f)) break block43;
                        if (!bl6) break block44;
                        f8 = 1.0f;
                        break block45;
                    }
                    f8 = 0.0f;
                }
                f2 = f8;
            }
            this.E.put(u4.a, Float.valueOf(f2));
            this.a(render2DEvent, u4, bl2, bl, f2);
        }
    }

    private void a(Render2DEvent render2DEvent, u u2, boolean bl, boolean bl2, float f2) {
        float f3;
        float f4;
        float f5;
        float f6;
        block5: {
            block4: {
                f6 = u2.d + 20.0f * f2;
                f5 = u2.b - f6 * 0.5f;
                f4 = u2.c - f6 * 0.5f;
                if (!bl2 || !u2.a.equals((Object)this.q)) break block4;
                f3 = Mth.clamp((float)this.u, (float)0.0f, (float)1.0f);
                break block5;
            }
            f3 = 0.0f;
        }
        float f7 = f3;
        render2DEvent.a().pushPose();
        render2DEvent.a().translate(0.0f, 0.0f, 200.0f);
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(true);
                d2.a(-1945499126);
                d2.a(h_0.FILL);
                drawContext.a(f5, f4, f5 + f6, f4 + f6, -90.0f, 360.0f, false, d2);
                d2.a(h_0.STROKE);
                d2.b(Math.max(1.5f, f6 * 0.1f));
                d2.a(bl2 ? -14559141 : (bl ? -16077046 : -1442182646));
                drawContext.a(f5, f4, f5 + f6, f4 + f6, -90.0f, 360.0f, false, d2);
                if (!bl2) return;
                if (!(f7 > 0.0f)) return;
                if (!(f7 < 1.0f)) return;
                d2.b(Math.max(1.8f, f6 * 0.12f));
                d2.a(-13770926);
                drawContext.a(f5, f4, f5 + f6, f4 + f6, -90.0f, -360.0f * f7, false, d2);
                return;
            }
        });
        render2DEvent.a().popPose();
    }

    private BlockPos e(BlockPos blockPos2) {
        return this.g(blockPos2).stream().filter(this::h).min(Comparator.comparingDouble(blockPos -> Nuker.mc.player.getEyePosition().distanceToSqr(Vec3.atCenterOf((Vec3i)blockPos)))).map(BlockPos::immutable).orElse(null);
    }

    @EventTarget(a=4)
    public void a(MotionEvent motionEvent) {
        block59: {
            BlockPos blockPos;
            block63: {
                block62: {
                    boolean bl;
                    block61: {
                        block60: {
                            BlockState blockState;
                            block58: {
                                block57: {
                                    block56: {
                                        block55: {
                                            BlockHitResult blockHitResult;
                                            block54: {
                                                block53: {
                                                    block52: {
                                                        block51: {
                                                            block50: {
                                                                if (!motionEvent.isPost() || this.B > 0) break block50;
                                                                if (Nuker.mc.player == null) break block50;
                                                                if (Nuker.mc.level == null) break block50;
                                                                if (Nuker.mc.gameMode != null) break block51;
                                                            }
                                                            return;
                                                        }
                                                        if (this.b(this.q) && this.d(this.r)) break block52;
                                                        this.l();
                                                        return;
                                                    }
                                                    if (this.z <= 0 || !this.r.equals((Object)this.w)) break block53;
                                                    return;
                                                }
                                                blockHitResult = this.j(this.r);
                                                if (blockHitResult == null) {
                                                    this.l();
                                                    return;
                                                }
                                                if (!((Boolean)this.rotate.getValue()).booleanValue() || this.a()) break block54;
                                                return;
                                            }
                                            if (this.s != null && this.s.equals((Object)this.r)) break block55;
                                            this.l();
                                            this.s = this.r.immutable();
                                            this.t = blockHitResult.getDirection();
                                            this.u = 0.0f;
                                        }
                                        if (AutoTools.j == null || !AutoTools.j.isEnabled()) break block56;
                                        AutoTools.j.b(this.r);
                                    }
                                    if (this.C > 0) {
                                        Nuker.mc.gameMode.continueDestroyBlock(this.s, this.t);
                                        --this.C;
                                        return;
                                    }
                                    blockState = Nuker.mc.level.getBlockState(this.s);
                                    if (this.v) break block57;
                                    this.v = Nuker.mc.gameMode.startDestroyBlock(this.s, this.t);
                                    if (this.v) break block58;
                                    return;
                                }
                                Nuker.mc.gameMode.continueDestroyBlock(this.s, this.t);
                            }
                            this.u += blockState.getDestroyProgress((Player)Nuker.mc.player, (BlockGetter)Nuker.mc.level, this.s);
                            int n2 = Math.min(9, Math.max(0, (int)(this.u * 10.0f)));
                            Nuker.mc.level.destroyBlockProgress(Nuker.mc.player.getId(), this.s, n2);
                            Nuker.mc.player.swing(InteractionHand.MAIN_HAND);
                            if (Nuker.mc.level.getBlockState(this.s).getBlock() == blockState.getBlock()) break block59;
                            if (this.x == null) break block60;
                            if (!this.s.equals((Object)this.x)) break block60;
                            bl = true;
                            break block61;
                        }
                        bl = false;
                    }
                    boolean bl2 = bl;
                    Nuker.mc.level.destroyBlockProgress(Nuker.mc.player.getId(), this.s, -1);
                    this.w = this.s.immutable();
                    this.z = 5;
                    this.C = 5;
                    this.v = false;
                    this.u = 0.0f;
                    this.s = null;
                    if (!bl2) break block59;
                    Nuker nuker = this;
                    if (this.q != null) break block62;
                    blockPos = null;
                    break block63;
                }
                blockPos = this.q.immutable();
            }
            nuker.y = blockPos;
            this.x = null;
        }
    }

    private boolean f(BlockPos blockPos) {
        boolean bl;
        block5: {
            block4: {
                List<BlockPos> list = this.g(blockPos);
                if (list.isEmpty() || !list.stream().allMatch(this::i)) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    private void m() {
        this.l();
        this.q = null;
        this.r = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = 0;
        this.A = null;
        this.p = null;
        this.B = 0;
        this.C = 0;
    }

    private List<BlockPos> g(BlockPos blockPos) {
        BlockPos blockPos2;
        BlockPos blockPos3;
        block9: {
            if (blockPos != null && Nuker.mc.level != null) break block9;
            return List.of();
        }
        BlockState blockState = Nuker.mc.level.getBlockState(blockPos);
        if (!(blockState.getBlock() instanceof BedBlock)) {
            return List.of();
        }
        Direction direction = (Direction)blockState.getValue((Property)BedBlock.FACING);
        if (blockState.getValue((Property)BedBlock.PART) == BedPart.HEAD) {
            blockPos3 = blockPos;
            blockPos2 = blockPos.relative(direction.getOpposite());
        } else {
            blockPos2 = blockPos;
            blockPos3 = blockPos.relative(direction);
        }
        Direction direction2 = direction.getCounterClockWise();
        Direction direction3 = direction.getClockWise();
        LinkedHashSet<BlockPos> linkedHashSet = new LinkedHashSet<BlockPos>();
        linkedHashSet.add(blockPos3.relative(direction));
        linkedHashSet.add(blockPos2.relative(direction.getOpposite()));
        linkedHashSet.add(blockPos3.above());
        linkedHashSet.add(blockPos2.above());
        linkedHashSet.add(blockPos3.relative(direction2));
        linkedHashSet.add(blockPos2.relative(direction2));
        linkedHashSet.add(blockPos3.relative(direction3));
        linkedHashSet.add(blockPos2.relative(direction3));
        return new ArrayList<BlockPos>(linkedHashSet);
    }

    private BlockHitResult a(BlockPos blockPos, Vec3 vec3, Vec3 vec32) {
        AABB aABB;
        VoxelShape voxelShape;
        BlockState blockState = Nuker.mc.level.getBlockState(blockPos);
        try {
            voxelShape = blockState.getShape((BlockGetter)Nuker.mc.level, blockPos);
            AABB aABB2 = voxelShape.isEmpty() ? new AABB(blockPos) : voxelShape.bounds().move(blockPos);
            aABB = aABB2;
        }
        catch (RuntimeException runtimeException) {
            aABB = new AABB(blockPos);
        }
        voxelShape = vec32.subtract(vec3);
        if (voxelShape.lengthSqr() < 1.0E-8) {
            return new BlockHitResult(vec32, Direction.UP, blockPos, true);
        }
        double d2 = Math.max(((Number)this.range.getValue()).doubleValue() + 1.0, voxelShape.length() + 0.5);
        Vec3 vec33 = vec3.add(voxelShape.normalize().scale(d2));
        Optional optional = aABB.clip(vec3, vec33);
        Vec3 vec34 = optional.orElse(vec32);
        Direction direction = this.a(aABB, vec34, vec3, vec32);
        return new BlockHitResult(vec34, direction, blockPos, aABB.contains(vec3));
    }

    private boolean d(BlockPos blockPos) {
        block14: {
            block13: {
                block12: {
                    if (blockPos == null || Nuker.mc.player == null) break block12;
                    if (Nuker.mc.level != null) break block13;
                }
                return false;
            }
            BlockState blockState = Nuker.mc.level.getBlockState(blockPos);
            if (!blockState.isAir() && !(blockState.getDestroySpeed((BlockGetter)Nuker.mc.level, blockPos) < 0.0f)) break block14;
            return false;
        }
        double d2 = ((Number)this.range.getValue()).doubleValue();
        boolean bl = Nuker.mc.player.getEyePosition().distanceToSqr(Vec3.atCenterOf((Vec3i)blockPos)) <= d2 * d2;
        return bl;
    }

    private float h() {
        float f2;
        block14: {
            block13: {
                block12: {
                    if (this.r == null || Nuker.mc.player == null) break block12;
                    if (Nuker.mc.level != null) break block13;
                }
                return 0.0f;
            }
            BlockState blockState = Nuker.mc.level.getBlockState(this.r);
            float f3 = blockState.getDestroyProgress((Player)Nuker.mc.player, (BlockGetter)Nuker.mc.level, this.r);
            if (f3 <= 0.0f) {
                return 99.9f;
            }
            f2 = Math.max(0.0f, 1.0f - this.u) / f3;
            if (this.q == null || this.r.equals((Object)this.q)) break block14;
            BlockState blockState2 = Nuker.mc.level.getBlockState(this.q);
            float f4 = blockState2.getDestroyProgress((Player)Nuker.mc.player, (BlockGetter)Nuker.mc.level, this.q);
            if (f4 > 0.0f) {
                f2 += 1.0f / f4;
            }
        }
        return Math.min(99.9f, f2 / 20.0f);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[18];
                var3_1 = 0;
                var2_2 = "Kv\u0015\u0007'\u0006\u0005Wl\n\u0003!\u0005Kx\u000f\u00016\u0006K|\u000f\u00026\u0011\u000291\n\u65f9\u6551\u76e5\u65df\u5704CQ]#A\u000bwv\u0015F:\r8up\u0012\u0012\fJn\b\u00120\u000b8]|\r\u0007*\u0007}|\r\u0003'\u0006|\u000244\u0007\u6267\u4e14\u5251\u65df\u5704Y8\u0014[k\u0004\u00078CY}s\u0000\u00056\rl9_\b\u0014 \u0017\u0005x}\u0005\u00037\rMq\u0013\t&\u0004p9N\u0000\n?\u0010\u000exu\u0013\u00032\u0007a9|\u0019\u000f \u0017k\u0005<7P\u0000 ";
                var4_3 = "Kv\u0015\u0007'\u0006\u0005Wl\n\u0003!\u0005Kx\u000f\u00016\u0006K|\u000f\u00026\u0011\u000291\n\u65f9\u6551\u76e5\u65df\u5704CQ]#A\u000bwv\u0015F:\r8up\u0012\u0012\fJn\b\u00120\u000b8]|\r\u0007*\u0007}|\r\u0003'\u0006|\u000244\u0007\u6267\u4e14\u5251\u65df\u5704Y8\u0014[k\u0004\u00078CY}s\u0000\u00056\rl9_\b\u0014 \u0017\u0005x}\u0005\u00037\rMq\u0013\t&\u0004p9N\u0000\n?\u0010\u000exu\u0013\u00032\u0007a9|\u0019\u000f \u0017k\u0005<7P\u0000 ".length();
                var1_4 = 6;
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
                    var5[var3_1++] = Nuker.c(Nuker.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "tp\u000f\u00030\u0011y\u007fm[\n\u65a0\u574eA/\u0017C\u4e15\u80e4\u4e23\u7a1b";
                    var4_3 = "tp\u000f\u00030\u0011y\u007fm[\n\u65a0\u574eA/\u0017C\u4e15\u80e4\u4e23\u7a1b".length();
                    var1_4 = 10;
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
                    var5[var3_1++] = Nuker.c(Nuker.a(v1));
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
        Nuker.O = var5;
    }

    private i a(BlockPos blockPos, float f2) {
        float f3 = -3.4028235E38f;
        float f4 = Float.MAX_VALUE;
        float f5 = -3.4028235E38f;
        int n2 = 0;
        for (int i2 = 0; i2 <= 1; ++i2) {
            for (int i3 = 0; i3 <= 1; ++i3) {
                for (int i4 = 0; i4 <= 1; ++i4) {
                    i i5 = b.a(blockPos.getX() + i2, blockPos.getY() + i3, blockPos.getZ() + i4, f2);
                    if (i5 == null || i5.a == Float.MAX_VALUE) {
                        continue;
                    }
                    f3 = Math.max(f3, i5.a);
                    f4 = Math.min(f4, i5.b);
                    f5 = Math.max(f5, i5.b);
                    ++n2;
                }
            }
        }
        i i6 = n2 == 0 ? null : new i(f3, (f4 + f5) * 0.5f);
        return i6;
    }

    private BlockPos c(BlockPos blockPos) {
        block30: {
            block33: {
                block32: {
                    block31: {
                        if (!this.b(blockPos)) {
                            return null;
                        }
                        if (!((Boolean)this.throughWalls.getValue()).booleanValue()) break block30;
                        if (Nuker.mc.level.getBlockState(blockPos).getBlock() instanceof BedBlock) break block31;
                        return blockPos;
                    }
                    if (!((Boolean)this.breakAdjacentFirst.getValue()).booleanValue()) {
                        return blockPos;
                    }
                    if (blockPos.equals((Object)this.y)) {
                        return blockPos;
                    }
                    if (!this.f(blockPos)) {
                        this.x = null;
                        this.y = blockPos.immutable();
                        return blockPos;
                    }
                    if (this.x == null || !this.g(blockPos).contains(this.x)) break block32;
                    if (this.h(this.x)) break block33;
                }
                this.x = this.e(blockPos);
            }
            if (this.x == null) {
                this.y = blockPos.immutable();
                return blockPos;
            }
            return this.x;
        }
        BlockHitResult blockHitResult = Nuker.mc.level.clip(new ClipContext(Nuker.mc.player.getEyePosition(), Vec3.atCenterOf((Vec3i)blockPos), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)Nuker.mc.player));
        if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return null;
        }
        BlockPos blockPos2 = blockHitResult.getBlockPos().immutable();
        Object object = this.d(blockPos2) ? blockPos2 : null;
        return object;
    }

    private boolean h(BlockPos blockPos) {
        boolean bl;
        block5: {
            block4: {
                if (!this.i(blockPos) || !this.d(blockPos)) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    @Override
    protected void onDisable() {
        this.l();
        this.n();
    }

    public List<String> getTargetBlockIds() {
        return this.N.stream().map(arg_0 -> ((DefaultedRegistry)BuiltInRegistries.BLOCK).getKey(arg_0)).filter(Objects::nonNull).map(ResourceLocation::toString).sorted().toList();
    }

    private BlockPos k() {
        int n2 = (int)Math.ceil(((Number)this.range.getValue()).doubleValue());
        BlockPos blockPos2 = Nuker.mc.player.blockPosition();
        return BlockPos.betweenClosedStream((BlockPos)blockPos2.offset(-n2, -n2, -n2), (BlockPos)blockPos2.offset(n2, n2, n2)).map(BlockPos::immutable).filter(this::b).filter(blockPos -> {
            boolean bl;
            block5: {
                block4: {
                    if (!((Boolean)this.throughWalls.getValue()).booleanValue() && this.c((BlockPos)blockPos) == null) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        }).min(Comparator.comparingDouble(blockPos -> Nuker.mc.player.getEyePosition().distanceToSqr(Vec3.atCenterOf((Vec3i)blockPos)))).orElse(null);
    }

    private BlockHitResult j(BlockPos blockPos) {
        BlockHitResult blockHitResult;
        block11: {
            block10: {
                if (!this.d(blockPos)) {
                    return null;
                }
                Vec3 vec3 = Nuker.mc.player.getEyePosition();
                Vec3 vec32 = Vec3.atCenterOf((Vec3i)blockPos);
                if (((Boolean)this.throughWalls.getValue()).booleanValue()) {
                    return this.a(blockPos, vec3, vec32);
                }
                BlockHitResult blockHitResult2 = Nuker.mc.level.clip(new ClipContext(vec3, vec32, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)Nuker.mc.player));
                if (blockHitResult2.getType() != HitResult.Type.BLOCK || !blockHitResult2.getBlockPos().equals((Object)blockPos)) break block10;
                blockHitResult = blockHitResult2;
                break block11;
            }
            blockHitResult = null;
        }
        return blockHitResult;
    }

    private boolean a() {
        boolean bl;
        block11: {
            block10: {
                if (this.p == null) {
                    return true;
                }
                a a2 = RotationHandler.d;
                if (a2 == null) {
                    return false;
                }
                float f2 = Math.abs(Mth.wrapDegrees((float)(a2.e() - this.p.e())));
                float f3 = Math.abs(Mth.wrapDegrees((float)(a2.f() - this.p.f())));
                if (!(f2 <= 4.0f) || !(f3 <= 4.0f)) break block10;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public boolean handleBedNukerClick() {
        return false;
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
                    case 0 -> 25;
                    case 1 -> 25;
                    case 2 -> 97;
                    case 3 -> 102;
                    case 4 -> 83;
                    case 5 -> 99;
                    default -> 24;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public boolean handleBedNukerHold(boolean bl) {
        return false;
    }

    private void j() {
        block9: {
            block10: {
                if (++this.L >= 4 || this.D.isEmpty()) break block9;
                this.D.removeIf(blockPos -> {
                    boolean bl = !this.a((BlockPos)blockPos);
                    return bl;
                });
                if (this.a(this.F)) break block10;
                this.F = null;
            }
            return;
        }
        this.L = 0;
        this.D.clear();
        int n2 = (int)Math.ceil(((Number)this.range.getValue()).doubleValue());
        BlockPos blockPos2 = Nuker.mc.player.blockPosition();
        BlockPos.betweenClosedStream((BlockPos)blockPos2.offset(-n2, -n2, -n2), (BlockPos)blockPos2.offset(n2, n2, n2)).map(BlockPos::immutable).filter(this::a).filter(blockPos -> {
            boolean bl = Nuker.mc.level.getBlockState(blockPos).getValue((Property)BedBlock.PART) == BedPart.HEAD;
            return bl;
        }).sorted(Comparator.comparingDouble(blockPos -> Nuker.mc.player.getEyePosition().distanceToSqr(Vec3.atCenterOf((Vec3i)blockPos)))).limit(32L).forEach(this.D::add);
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>(this.D);
        this.E.keySet().removeIf(blockPos -> {
            boolean bl = !hashSet.contains(blockPos);
            return bl;
        });
        if (!this.a(this.F)) {
            this.F = null;
        }
    }

    private boolean a(BlockState blockState) {
        return this.N.contains(blockState.getBlock());
    }

    private void i() {
        this.H = null;
        this.I = 0;
        this.J = false;
        this.K = false;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x18);
        }
        return cArray;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        block7: {
            block6: {
                if (!((Boolean)this.render.getValue()).booleanValue() || this.r == null) break block6;
                if (Nuker.mc.gameRenderer != null) break block7;
            }
            return;
        }
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = Nuker.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        AABB aABB = new AABB(this.r).inflate(0.002);
        Color color = new Color(255, 75, 92);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.18f);
        RenderUtil.a(aABB, poseStack);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.9f);
        RenderUtil.b(aABB, poseStack);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        poseStack.popPose();
    }

    private void n() {
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = Direction.DOWN;
        this.u = 0.0f;
        this.v = false;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = 0;
        this.A = null;
        this.p = null;
        this.B = 0;
        this.C = 0;
        this.D.clear();
        this.E.clear();
        this.F = null;
        this.G = null;
        this.i();
        this.L = 0;
    }

    private void l() {
        block10: {
            block11: {
                if (this.s == null || Nuker.mc.player == null) break block10;
                if (Nuker.mc.level == null) break block10;
                if (!this.v) break block11;
                if (Nuker.mc.gameMode == null) break block11;
                Nuker.mc.gameMode.stopDestroyBlock();
            }
            Nuker.mc.level.destroyBlockProgress(Nuker.mc.player.getId(), this.s, -1);
        }
        this.s = null;
        this.u = 0.0f;
        this.v = false;
    }

    private u b(BlockPos blockPos, float f2) {
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        float f5 = -3.4028235E38f;
        float f6 = -3.4028235E38f;
        int n2 = 0;
        double[] dArray = new double[]{0.2, 0.8};
        double[] dArray2 = new double[]{0.4, 1.2};
        for (double d2 : dArray) {
            for (double d3 : dArray2) {
                for (double d4 : dArray) {
                    i i2 = b.a((double)blockPos.getX() + d2, (double)blockPos.getY() + d3, (double)blockPos.getZ() + d4, f2);
                    if (i2 == null || i2.a == Float.MAX_VALUE) {
                        continue;
                    }
                    f3 = Math.min(f3, i2.a);
                    f4 = Math.min(f4, i2.b);
                    f5 = Math.max(f5, i2.a);
                    f6 = Math.max(f6, i2.b);
                    ++n2;
                }
            }
        }
        if (n2 == 0) {
            return null;
        }
        float f7 = Math.max(8.0f, Math.min(f5 - f3, f6 - f4));
        return new u(blockPos, (f3 + f5) * 0.5f, (f4 + f6) * 0.5f, f7);
    }

    private Direction a(AABB aABB, Vec3 vec3, Vec3 vec32, Vec3 vec33) {
        double d2 = 1.0E-4;
        if (Math.abs(vec3.x - aABB.minX) < d2) {
            return Direction.WEST;
        }
        if (Math.abs(vec3.x - aABB.maxX) < d2) {
            return Direction.EAST;
        }
        if (Math.abs(vec3.y - aABB.minY) < d2) {
            return Direction.DOWN;
        }
        if (Math.abs(vec3.y - aABB.maxY) < d2) {
            return Direction.UP;
        }
        if (Math.abs(vec3.z - aABB.minZ) < d2) {
            return Direction.NORTH;
        }
        if (Math.abs(vec3.z - aABB.maxZ) < d2) {
            return Direction.SOUTH;
        }
        return Direction.getNearest((double)(vec32.x - vec33.x), (double)(vec32.y - vec33.y), (double)(vec32.z - vec33.z));
    }

    @Override
    protected void onEnable() {
        this.n();
    }

    @EventTarget(a=4)
    public void a(Render2DEvent render2DEvent) {
        i i2;
        block19: {
            block18: {
                block17: {
                    if (!((Boolean)this.render.getValue()).booleanValue() || this.r == null) break block17;
                    if (Nuker.mc.player == null) break block17;
                    if (Nuker.mc.level != null) break block18;
                }
                return;
            }
            i2 = this.a(this.r, render2DEvent.c());
            if (i2 != null && i2.a != Float.MAX_VALUE) break block19;
            return;
        }
        float f2 = Math.min(0.99f, Mth.clamp((float)this.u, (float)0.0f, (float)1.0f));
        float f3 = this.h();
        BlockState blockState = Nuker.mc.level.getBlockState(this.r);
        ItemStack itemStack = blockState.getBlock().asItem().getDefaultInstance();
        String string = blockState.getBlock().getName().getString();
        String string2 = f3 >= 99.9f ? "--" : String.format(Locale.ROOT, "%.1fs", Float.valueOf(f3));
        String string3 = string2;
        String string4 = Math.round(f2 * 100.0f) + "%";
        float f4 = Math.min(210.0f, Math.max(142.0f, 43.0f + (float)Nuker.mc.font.width(string) + (float)Nuker.mc.font.width(string3) + (float)Nuker.mc.font.width(string4)));
        float f5 = 34.0f;
        float f6 = Mth.clamp((float)i2.a, (float)2.0f, (float)((float)mc.getWindow().getGuiScaledWidth() - f4 - 2.0f));
        float f7 = Mth.clamp((float)(i2.b - f5 * 0.5f), (float)2.0f, (float)((float)mc.getWindow().getGuiScaledHeight() - f5 - 2.0f));
        RenderUtil.a(render2DEvent.a(), f6, f7, f4, f5, 6.0f, 15.0f, 0.95f, 0);
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(true);
                d2.a(-938011098);
                drawContext.a(Q.a(f6, f7, f4, f5, 6.0f), d2);
                d2.a(1429790197);
                drawContext.a(Q.a(f6 + 7.0f, f7 + 23.0f, f4 - 14.0f, 4.0f, 2.0f), d2);
                d2.a(-13050379);
                drawContext.a(Q.a(f6 + 7.0f, f7 + 23.0f, (f4 - 14.0f) * f2, 4.0f, 2.0f), d2);
            }
        });
        if (!itemStack.isEmpty()) {
            render2DEvent.b().renderItem(itemStack, Math.round(f6 + 6.0f), Math.round(f7 + 3.0f));
        }
        float f8 = f6 + 25.0f;
        render2DEvent.b().drawString(Nuker.mc.font, string, Math.round(f8), Math.round(f7 + 6.0f), -1, true);
        float f9 = f8 + (float)Nuker.mc.font.width(string) + 5.0f;
        render2DEvent.b().drawString(Nuker.mc.font, string3, Math.round(f9), Math.round(f7 + 6.0f), -4670520, true);
        render2DEvent.b().drawString(Nuker.mc.font, string4, Math.round(f6 + f4 - 7.0f - (float)Nuker.mc.font.width(string4)), Math.round(f7 + 6.0f), -6427659, true);
    }

    public Nuker$TargetChangeResult changeTargetBlock(String string, boolean bl) {
        String string2;
        ResourceLocation resourceLocation;
        block25: {
            String[] stringArray;
            block26: {
                boolean bl2;
                block23: {
                    block24: {
                        Optional optional;
                        block22: {
                            block21: {
                                if (string != null && !string.isBlank()) break block21;
                                return new Nuker$TargetChangeResult(false, "\u65b9\u5757 ID \u4e0d\u80fd\u4e3a\u7a7a");
                            }
                            Object object = string.trim().toLowerCase(Locale.ROOT);
                            if (!((String)object).contains(":")) {
                                stringArray = O;
                                object = "minecraft:" + (String)object;
                            }
                            resourceLocation = ResourceLocation.tryParse((String)object);
                            if (resourceLocation == null) {
                                return new Nuker$TargetChangeResult(false, "\u65e0\u6548\u7684\u65b9\u5757 ID: " + string);
                            }
                            optional = BuiltInRegistries.BLOCK.getOptional(resourceLocation);
                            if (!optional.isEmpty() && optional.get() != Blocks.AIR) break block22;
                            return new Nuker$TargetChangeResult(false, "\u627e\u4e0d\u5230\u65b9\u5757: " + String.valueOf(resourceLocation));
                        }
                        Block block = (Block)optional.get();
                        bl2 = bl ? this.N.add(block) : this.N.remove(block);
                        if (bl2) {
                            this.l();
                            this.n();
                        }
                        if (!bl) break block23;
                        if (!bl2) break block24;
                        string2 = "added";
                        break block25;
                    }
                    stringArray = O;
                    string2 = "already exists";
                    break block25;
                }
                if (!bl2) break block26;
                string2 = "deleted";
                break block25;
            }
            stringArray = O;
            string2 = "not in list";
        }
        String string3 = string2;
        return new Nuker$TargetChangeResult(true, String.valueOf(resourceLocation) + " (" + string3 + ")");
    }

    private boolean b(BlockPos blockPos) {
        block14: {
            block13: {
                block12: {
                    if (blockPos == null || Nuker.mc.player == null) break block12;
                    if (Nuker.mc.level != null) break block13;
                }
                return false;
            }
            BlockState blockState = Nuker.mc.level.getBlockState(blockPos);
            if (this.a(blockState) && !(blockState.getDestroySpeed((BlockGetter)Nuker.mc.level, blockPos) < 0.0f)) break block14;
            return false;
        }
        double d2 = ((Number)this.range.getValue()).doubleValue();
        boolean bl = Nuker.mc.player.getEyePosition().distanceToSqr(Vec3.atCenterOf((Vec3i)blockPos)) <= d2 * d2;
        return bl;
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        block48: {
            block47: {
                block46: {
                    BlockPos blockPos;
                    block45: {
                        block44: {
                            block43: {
                                block42: {
                                    block41: {
                                        if (Nuker.mc.player == null || Nuker.mc.level == null) break block41;
                                        if (Nuker.mc.gameMode != null) break block42;
                                    }
                                    this.n();
                                    return;
                                }
                                if (this.B > 0) {
                                    --this.B;
                                }
                                if (this.z > 0) {
                                    --this.z;
                                }
                                BlockPos blockPos2 = this.q;
                                if (!this.b(this.q)) {
                                    this.q = this.k();
                                }
                                if (blockPos2 == null || blockPos2.equals((Object)this.q)) break block43;
                                this.l();
                                this.r = null;
                                this.x = null;
                                this.y = null;
                                this.B = ((Number)this.switchDelay.getValue()).intValue();
                            }
                            if (((Boolean)this.throughWalls.getValue()).booleanValue() && ((Boolean)this.breakAdjacentFirst.getValue()).booleanValue()) break block44;
                            this.x = null;
                            this.y = null;
                        }
                        BlockPos blockPos3 = this.c(this.q);
                        blockPos = this.r;
                        this.r = blockPos3;
                        if (this.w == null || this.w.equals((Object)this.r)) break block45;
                        this.w = null;
                        this.z = 0;
                    }
                    Nuker nuker = this;
                    BlockHitResult blockHitResult = this.r == null ? null : this.j(this.r);
                    nuker.A = blockHitResult;
                    if (blockPos == null || blockPos.equals((Object)this.r)) break block46;
                    this.l();
                    this.B = ((Number)this.switchDelay.getValue()).intValue();
                }
                if (this.q == null || this.r == null) break block47;
                if (this.A != null) break block48;
            }
            this.p = null;
            this.l();
            return;
        }
        Nuker nuker = this;
        a a2 = (Boolean)this.rotate.getValue() != false ? g.d(Nuker.mc.player.getEyePosition(), this.A.getLocation()) : null;
        nuker.p = a2;
    }

    public Nuker() {
        String[] stringArray = O;
        super("Nuker", Category.WORLD);
        this.range = new NumberSetting("Range", 5.0, 1.0, 6.0, 0.1);
        this.switchDelay = new NumberSetting("Switch Delay", 0, 0, 10, 1);
        this.throughWalls = new BooleanSetting("Through Walls", false);
        this.breakAdjacentFirst = new BooleanSetting("Break Adjacent First", false, () -> (Boolean)this.throughWalls.getValue());
        this.rotate = new BooleanSetting("Rotate", true);
        this.render = new BooleanSetting("Render", true);
        this.t = Direction.DOWN;
        this.D = new ArrayList<BlockPos>();
        this.E = new HashMap<BlockPos, Float>();
        this.N = new LinkedHashSet();
        INSTANCE = this;
        BuiltInRegistries.BLOCK.stream().filter(block -> block instanceof BedBlock).forEach(this.N::add);
    }
}

