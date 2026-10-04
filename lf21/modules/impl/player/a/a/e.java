/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.impl.player.Helper;
import lf21.modules.impl.player.a.a.a;
import lf21.modules.impl.player.a.a.i;
import lf21.modules.impl.player.a.a.k;
import lf21.modules.impl.player.a.b;
import lf21.utils.game.d;
import lf21.utils.game.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class e
extends b {
    private int f = -1;
    private static final String[] i;
    private int g = -1;
    public a d = a.NONE;
    public lf21.utils.rotation.a b;
    private BlockPos c;
    private k e = null;
    private static final String h;

    @Override
    public void a(MotionEvent motionEvent) {
        if (lf21.modules.impl.player.a.a.e.mc.player == null || lf21.modules.impl.player.a.a.e.mc.level == null || lf21.modules.impl.player.a.a.e.mc.gameMode == null) {
            return;
        }
        if (lf21.modules.impl.player.a.a.e.mc.player.isOnFire()) {
            this.f();
            return;
        }
        if (lf21.modules.impl.player.a.a.e.mc.player.isInLava()) {
            this.f();
            return;
        }
        if (motionEvent.isPost()) {
            if (this.e != null) {
                if (Helper.h()) {
                    return;
                }
                this.h();
                this.e = null;
                if (this.d != a.LAVA_SUPPORT) {
                    this.f();
                }
                return;
            }
            switch (lf21.modules.impl.player.a.a.i.a[this.d.ordinal()]) {
                case 1: {
                    if (this.c == null || !lf21.modules.impl.player.a.a.e.mc.level.getBlockState(this.c).is(Blocks.LAVA)) {
                        this.f();
                        return;
                    }
                    if (lf21.modules.impl.player.a.a.e.mc.level.getBlockState(this.c.below()).isAir()) {
                        this.d = a.LAVA_SUPPORT;
                        break;
                    }
                    if (this.g()) break;
                    this.d = a.LAVA_SUPPORT;
                    break;
                }
                case 2: {
                    if (this.c == null || !lf21.modules.impl.player.a.a.e.mc.level.getBlockState(this.c).is(Blocks.LAVA)) {
                        this.f();
                        return;
                    }
                    BlockPos blockPos = this.c.below();
                    if (lf21.modules.impl.player.a.a.e.mc.level.getBlockState(blockPos).isSolid()) {
                        this.d = a.LAVA;
                        this.g();
                        return;
                    }
                    this.a(blockPos).ifPresentOrElse(k2 -> {
                        this.b = lf21.utils.game.g.d(lf21.modules.impl.player.a.a.e.mc.player.getEyePosition(), k2.c());
                        this.e = k2;
                    }, () -> this.f());
                    break;
                }
                case 3: {
                    this.i();
                    if (this.c == null) break;
                    this.d = a.LAVA;
                }
            }
        }
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
                    case 0 -> 55;
                    case 1 -> 111;
                    case 2 -> 96;
                    case 3 -> 19;
                    case 4 -> 13;
                    case 5 -> 33;
                    default -> 122;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean g() {
        Optional<k> optional = this.a(this.c);
        if (optional.isPresent()) {
            k k2 = optional.get();
            this.b = lf21.utils.game.g.d(lf21.modules.impl.player.a.a.e.mc.player.getEyePosition(), k2.c());
            this.e = k2;
            return true;
        }
        return false;
    }

    private int j() {
        int n2 = lf21.utils.game.d.a(Items.COBBLESTONE);
        if (n2 != -1 && n2 < 9) {
            return n2;
        }
        for (int i2 = 0; i2 < 9; ++i2) {
            Block block;
            ItemStack itemStack = lf21.modules.impl.player.a.a.e.mc.player.getInventory().getItem(i2);
            if (!(itemStack.getItem() instanceof BlockItem) || !(block = ((BlockItem)itemStack.getItem()).getBlock()).defaultBlockState().isSolid() || block.getSoundType(block.defaultBlockState()).equals(SoundEvents.WOOD_PLACE)) continue;
            return i2;
        }
        return -1;
    }

    private boolean a(Vec3 vec3) {
        if (lf21.modules.impl.player.a.a.e.mc.player == null || lf21.modules.impl.player.a.a.e.mc.level == null) {
            return false;
        }
        Vec3 vec32 = lf21.modules.impl.player.a.a.e.mc.player.getEyePosition();
        return lf21.modules.impl.player.a.a.e.mc.level.clip(new ClipContext(vec32, vec3, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)lf21.modules.impl.player.a.a.e.mc.player)).getType() == HitResult.Type.MISS;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "u\u0003\u000fpf\u00016V\u0019\u0001\nu\u0003\u000fpf\u00016V\u0019\u0001";
        int n3 = "u\u0003\u000fpf\u00016V\u0019\u0001\nu\u0003\u000fpf\u00016V\u0019\u0001".length();
        int n4 = 10;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.modules.impl.player.a.a.e.b(lf21.modules.impl.player.a.a.e.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                i = stringArray;
                h = i[1];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public e() {
        super("Block Lava");
    }

    private void i() {
        if (lf21.modules.impl.player.a.a.e.mc.player == null || lf21.modules.impl.player.a.a.e.mc.level == null) {
            this.c = null;
            return;
        }
        BlockPos blockPos2 = lf21.modules.impl.player.a.a.e.mc.player.blockPosition();
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        for (int i2 = -3; i2 <= 3; ++i2) {
            for (int i3 = -2; i3 <= 2; ++i3) {
                for (int i4 = -3; i4 <= 3; ++i4) {
                    BlockPos blockPos3 = blockPos2.offset(i2, i3, i4);
                    if (Helper.f(blockPos3) || !lf21.modules.impl.player.a.a.e.mc.level.getBlockState(blockPos3).is(Blocks.LAVA) || !lf21.modules.impl.player.a.a.e.mc.level.getFluidState(blockPos3).isSource() || !Helper.a(Vec3.atCenterOf((Vec3i)blockPos3))) continue;
                    arrayList.add(blockPos3);
                }
            }
        }
        Vec3 vec3 = lf21.modules.impl.player.a.a.e.mc.player.getEyePosition();
        this.c = arrayList.stream().min(Comparator.comparingDouble(blockPos -> Vec3.atCenterOf((Vec3i)blockPos).distanceToSqr(vec3))).orElse(null);
    }

    @Override
    public void a() {
        this.f();
    }

    @Override
    public void b() {
        this.f();
    }

    private void f() {
        this.c = null;
        this.d = a.NONE;
        this.b = null;
        if (this.g != -1 && lf21.modules.impl.player.a.a.e.mc.player != null) {
            lf21.modules.impl.player.a.a.e.mc.player.getInventory().selected = this.g;
        }
        this.g = -1;
        this.f = -1;
    }

    @Override
    public lf21.utils.rotation.a d() {
        return this.b;
    }

    @Override
    public void a(RenderEvent renderEvent) {
        if (this.d == a.NONE || lf21.modules.impl.player.a.a.e.mc.gameRenderer == null || this.c == null) {
            return;
        }
        Color color = new Color(255, 165, 0);
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = lf21.modules.impl.player.a.a.e.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        AABB aABB = new AABB(this.c);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.25f);
        RenderUtil.a(aABB, poseStack);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.75f);
        RenderUtil.b(aABB, poseStack);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        poseStack.popPose();
    }

    @Override
    public boolean c() {
        return this.b != null;
    }

    private void h() {
        if (this.e == null) {
            return;
        }
        this.f = this.j();
        if (this.f == -1) {
            this.f();
            return;
        }
        if (this.g == -1) {
            this.g = lf21.modules.impl.player.a.a.e.mc.player.getInventory().selected;
        }
        lf21.modules.impl.player.a.a.e.mc.player.getInventory().selected = this.f;
        BlockHitResult blockHitResult = new BlockHitResult(this.e.c(), this.e.b(), this.e.a(), false);
        lf21.modules.impl.player.a.a.e.mc.gameMode.useItemOn(lf21.modules.impl.player.a.a.e.mc.player, InteractionHand.MAIN_HAND, blockHitResult);
        lf21.modules.impl.player.a.a.e.mc.player.swing(InteractionHand.MAIN_HAND);
    }

    private boolean a(BlockPos blockPos, Direction direction) {
        Vec3 vec3;
        if (lf21.modules.impl.player.a.a.e.mc.player == null || lf21.modules.impl.player.a.a.e.mc.level == null) {
            return false;
        }
        Vec3 vec32 = lf21.modules.impl.player.a.a.e.mc.player.getEyePosition();
        BlockHitResult blockHitResult = lf21.modules.impl.player.a.a.e.mc.level.clip(new ClipContext(vec32, vec3 = Vec3.atCenterOf((Vec3i)blockPos).add(new Vec3((double)direction.getStepX() * 0.49, (double)direction.getStepY() * 0.49, (double)direction.getStepZ() * 0.49)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)lf21.modules.impl.player.a.a.e.mc.player));
        if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return false;
        }
        if (!blockHitResult.getBlockPos().equals((Object)blockPos)) {
            return false;
        }
        return blockHitResult.getLocation().distanceToSqr(vec3) < 0.25;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x7A);
        }
        return cArray;
    }

    private Optional<k> a(BlockPos blockPos) {
        for (Direction direction : Direction.values()) {
            BlockPos blockPos2;
            BlockPos blockPos3 = blockPos.relative(direction);
            if (lf21.modules.impl.player.a.a.e.mc.level.getBlockState(blockPos3).isSolid() && !lf21.modules.impl.player.a.a.e.mc.level.getBlockState(blockPos3).is(Blocks.LAVA)) {
                blockPos2 = Vec3.atCenterOf((Vec3i)blockPos3).add((double)direction.getOpposite().getStepX() * 0.5, (double)direction.getOpposite().getStepY() * 0.5, (double)direction.getOpposite().getStepZ() * 0.5);
                if (lf21.modules.impl.player.a.a.e.mc.player.getEyePosition().distanceToSqr((Vec3)blockPos2) <= 25.0 && this.a(blockPos3, direction.getOpposite())) {
                    return Optional.of(new k(blockPos3, direction.getOpposite(), (Vec3)blockPos2));
                }
            }
            if (direction != Direction.DOWN) continue;
            blockPos2 = blockPos3;
            for (int i2 = 0; i2 < 3 && lf21.modules.impl.player.a.a.e.mc.level.getBlockState(blockPos2).is(Blocks.LAVA); ++i2) {
                blockPos2 = blockPos2.below();
            }
            if (!lf21.modules.impl.player.a.a.e.mc.level.getBlockState(blockPos2).isSolid() || lf21.modules.impl.player.a.a.e.mc.level.getBlockState(blockPos2).is(Blocks.LAVA)) continue;
            Vec3 vec3 = Vec3.atCenterOf((Vec3i)blockPos2).add(new Vec3((double)Direction.UP.getStepX() * 0.5, (double)Direction.UP.getStepY() * 0.5, (double)Direction.UP.getStepZ() * 0.5));
            if (!(lf21.modules.impl.player.a.a.e.mc.player.getEyePosition().distanceToSqr(vec3) <= 25.0) || !this.a(blockPos2, Direction.UP)) continue;
            return Optional.of(new k(blockPos2, Direction.UP, vec3));
        }
        return Optional.empty();
    }
}

