/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import lf21.event.EventTarget;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.utils.a.g;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.a;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class AntiTNT
extends Module {
    private int n = -1;
    private BlockPos o = null;
    private final List<BlockPos> l;
    public static AntiTNT j;
    private final g k = new g();
    private PrimedTnt m = null;
    public static a p;

    private boolean b(PrimedTnt primedTnt) {
        Vec3 vec3 = AntiTNT.mc.player.position().subtract(primedTnt.position()).normalize();
        return primedTnt.getDeltaMovement().dot(vec3) > 0.05;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = AntiTNT.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader(GameRenderer::getPositionShader);
        if (this.o != null) {
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)0.8f);
            RenderUtil.b(new AABB(this.o), poseStack);
        }
        if (this.m != null && this.m.isAlive()) {
            AABB aABB = this.m.getBoundingBox();
            RenderSystem.setShaderColor((float)1.0f, (float)0.0f, (float)0.0f, (float)0.25f);
            RenderUtil.a(aABB, poseStack);
            RenderSystem.setShaderColor((float)1.0f, (float)0.0f, (float)0.0f, (float)0.8f);
            RenderUtil.b(aABB, poseStack);
            float f2 = (float)this.m.getFuse() / 20.0f;
            if (f2 > 0.0f) {
                String string = String.format("%.1fs", Float.valueOf(f2));
                BlockPos blockPos = this.m.blockPosition();
                poseStack.pushPose();
                poseStack.translate((double)blockPos.getX() + 0.5 - vec3.x, (double)blockPos.getY() + 1.1 - vec3.y, (double)blockPos.getZ() + 0.5 - vec3.z);
                poseStack.mulPose(AntiTNT.mc.gameRenderer.getMainCamera().rotation());
                poseStack.scale(-0.025f, -0.025f, 0.025f);
                float f3 = AntiTNT.mc.font.width(string);
                AntiTNT.mc.font.drawInBatch(string, -f3 / 2.0f, 0.0f, -1, false, poseStack.last().pose(), (MultiBufferSource)mc.renderBuffers().bufferSource(), Font.DisplayMode.NORMAL, 0, 0xF000F0);
                poseStack.popPose();
            }
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        poseStack.popPose();
    }

    @Override
    public void onEnable() {
        this.l.clear();
        this.m = null;
        this.n = -1;
        this.o = null;
        this.k.b();
        p = null;
    }

    private boolean a(BlockPos blockPos) {
        return AntiTNT.mc.level.getBlockState(blockPos).canBeReplaced() && !AntiTNT.mc.player.getBoundingBox().intersects(new AABB(blockPos));
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (AntiTNT.mc.player == null || AntiTNT.mc.level == null || AntiTNT.mc.gameMode == null) {
            return;
        }
        if (this.a()) {
            if (!this.l.isEmpty()) {
                this.l.clear();
                this.k();
            }
            p = null;
            this.m = null;
            return;
        }
        if (this.l.isEmpty()) {
            this.m = this.h();
        }
        if (!this.l.isEmpty()) {
            this.j();
            return;
        }
        if (this.m != null) {
            this.i();
        } else {
            p = null;
        }
    }

    private void j() {
        if (!this.k.a(1)) {
            return;
        }
        if (this.l.isEmpty()) {
            return;
        }
        BlockPos blockPos = this.l.get(0);
        BlockHitResult blockHitResult = this.c(blockPos);
        if (blockHitResult == null) {
            this.l.remove(0);
            return;
        }
        int n2 = this.l();
        if (n2 == -1) {
            this.l.clear();
            this.k();
            return;
        }
        if (this.n == -1) {
            this.n = AntiTNT.mc.player.getInventory().selected;
        }
        AntiTNT.mc.player.getInventory().selected = n2;
        p = lf21.utils.game.g.a(blockHitResult.getBlockPos(), 0.0f);
        InteractionResult interactionResult = AntiTNT.mc.gameMode.useItemOn(AntiTNT.mc.player, InteractionHand.MAIN_HAND, blockHitResult);
        if (interactionResult.consumesAction()) {
            AntiTNT.mc.player.swing(InteractionHand.MAIN_HAND);
        }
        this.o = blockPos;
        this.l.remove(0);
        this.k.b();
        if (this.l.isEmpty()) {
            this.k();
            p = null;
        }
    }

    public AntiTNT() {
        super("AntiTNT", Category.PLAYER);
        this.l = new ArrayList<BlockPos>();
        j = this;
    }

    private int l() {
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack = AntiTNT.mc.player.getInventory().getItem(i2);
            if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BlockItem) || !lf21.utils.game.a.a(itemStack)) continue;
            return i2;
        }
        return -1;
    }

    private void k() {
        if (this.n != -1) {
            AntiTNT.mc.player.getInventory().selected = this.n;
            this.n = -1;
        }
    }

    private boolean a() {
        if (AntiTNT.mc.options == null) {
            return false;
        }
        return AntiTNT.mc.options.keyUp.isDown() || AntiTNT.mc.options.keyDown.isDown() || AntiTNT.mc.options.keyLeft.isDown() || AntiTNT.mc.options.keyRight.isDown() || AntiTNT.mc.player.isSprinting();
    }

    private Vec3 a(BlockPos blockPos, Direction direction) {
        double d2 = (double)blockPos.getX() + 0.5;
        double d3 = (double)blockPos.getY() + 0.5;
        double d4 = (double)blockPos.getZ() + 0.5;
        if (direction == Direction.UP || direction == Direction.DOWN) {
            d2 += lf21.utils.b.a.a(-0.3, 0.3);
            d4 += lf21.utils.b.a.a(-0.3, 0.3);
        } else {
            d3 += lf21.utils.b.a.a(-0.25, 0.25);
        }
        if (direction == Direction.WEST || direction == Direction.EAST) {
            d4 += lf21.utils.b.a.a(-0.3, 0.3);
        }
        if (direction == Direction.SOUTH || direction == Direction.NORTH) {
            d2 += lf21.utils.b.a.a(-0.3, 0.3);
        }
        double d5 = Math.max((double)blockPos.getX(), Math.min((double)(blockPos.getX() + 1), d2));
        double d6 = Math.max((double)blockPos.getY(), Math.min((double)(blockPos.getY() + 1), d3));
        double d7 = Math.max((double)blockPos.getZ(), Math.min((double)(blockPos.getZ() + 1), d4));
        return new Vec3(d5, d6, d7);
    }

    private boolean b(BlockPos blockPos) {
        return AntiTNT.mc.level.getBlockState(blockPos).isSolidRender((BlockGetter)AntiTNT.mc.level, blockPos);
    }

    @Override
    public void onDisable() {
        this.l.clear();
        this.m = null;
        this.k();
        p = null;
    }

    static {
        p = null;
    }

    private BlockHitResult c(BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.below();
        if (this.b(blockPos2)) {
            return new BlockHitResult(this.a(blockPos2, Direction.UP), Direction.UP, blockPos2, false);
        }
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockPos3 = blockPos.relative(direction);
            if (!this.b(blockPos3)) continue;
            Direction direction2 = direction.getOpposite();
            return new BlockHitResult(this.a(blockPos3, direction2), direction2, blockPos3, false);
        }
        return null;
    }

    private boolean a(PrimedTnt primedTnt) {
        Vec3 vec3;
        if (AntiTNT.mc.player == null || AntiTNT.mc.level == null) {
            return false;
        }
        float f2 = 8.0f;
        if (primedTnt.distanceToSqr((Entity)AntiTNT.mc.player) > 64.0) {
            return false;
        }
        Vec3 vec32 = primedTnt.position();
        BlockHitResult blockHitResult = AntiTNT.mc.level.clip(new ClipContext(vec32, vec3 = AntiTNT.mc.player.getEyePosition(), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)AntiTNT.mc.player));
        return blockHitResult.getType() == HitResult.Type.MISS;
    }

    private void i() {
        BlockPos blockPos;
        if (!this.l.isEmpty()) {
            return;
        }
        if (AntiTNT.mc.screen != null) {
            AntiTNT.mc.player.closeContainer();
            mc.setScreen(null);
        }
        BlockPos blockPos2 = AntiTNT.mc.player.blockPosition();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            blockPos = blockPos2.relative(direction);
            if (!this.a(blockPos)) continue;
            this.l.add(blockPos);
        }
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            blockPos = blockPos2.above().relative(direction);
            if (!this.a(blockPos)) continue;
            this.l.add(blockPos);
        }
        Iterator iterator = blockPos2.above(2);
        if (this.a((BlockPos)iterator)) {
            this.l.add((BlockPos)iterator);
        }
        this.k.b();
    }

    private PrimedTnt h() {
        return AntiTNT.mc.level.getEntitiesOfClass(PrimedTnt.class, AntiTNT.mc.player.getBoundingBox().inflate(20.0)).stream().filter(primedTnt -> primedTnt.getFuse() > 0).filter(primedTnt -> this.b((PrimedTnt)primedTnt) || this.a((PrimedTnt)primedTnt)).min(Comparator.comparingDouble(primedTnt -> primedTnt.distanceToSqr((Entity)AntiTNT.mc.player))).orElse(null);
    }
}

