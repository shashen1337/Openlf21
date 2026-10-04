/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.player.A;
import lf21.modules.impl.player.AntiTNT;
import lf21.modules.impl.player.AntiWeb;
import lf21.modules.impl.player.Helper;
import lf21.modules.impl.player.MidPearl;
import lf21.modules.impl.player.b_0;
import lf21.modules.impl.player.c_0;
import lf21.modules.impl.player.d;
import lf21.modules.impl.player.e;
import lf21.modules.impl.player.u;
import lf21.modules.impl.player.v;
import lf21.modules.impl.player.y;
import lf21.modules.impl.player.z;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.PlayerUtil;
import lf21.utils.game.g;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.a;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AutoWeb
extends Module {
    private int B;
    private c_0 I;
    public final BooleanSetting render;
    private d v;
    private Vec3 O;
    public final MultiSelectSetting webPositions;
    public final NumberSetting range;
    private BlockPos J;
    private int E;
    private e x;
    public final NumberSetting predictionTicks;
    private Direction N;
    private int R;
    private v w;
    private BlockPos z;
    private int P;
    public final BooleanSetting movementPrediction;
    public final NumberSetting delay;
    public static a k;
    public static AutoWeb j;
    private boolean F;
    private BlockPos M;
    private int K;
    public final BooleanSetting lava;
    public final BooleanSetting debug;
    private int D;
    private boolean L;
    public final BooleanSetting lavaWaitWaterBucket;
    private final lf21.utils.a.g u;
    private int A;
    private y H;
    private int Q;
    private int S;
    private boolean G;
    private Entity y;
    private int C;
    private static final String[] U;
    private final Map<BlockPos, Long> T;

    private boolean y() {
        if (this.y == null || !((Boolean)this.lava.getValue()).booleanValue() || this.D() == -1) {
            return false;
        }
        if (!this.a(this.y, true)) {
            return false;
        }
        Optional<v> optional = this.k(this.y);
        if (optional.isEmpty()) {
            String[] stringArray = U;
            this.a("Ground web lava skipped: no lava placement target=" + this.y.getName().getString());
            return false;
        }
        this.D = 0;
        this.a(optional.get());
        this.H = null;
        this.I = c_0.IDLE;
        this.J = null;
        this.K = 0;
        this.L = false;
        this.v = d.PLACE;
        String string = this.a(this.w.c);
        String string2 = String.valueOf(this.w.b);
        String string3 = this.k(this.w.a);
        String string4 = this.k(this.w.d);
        String string5 = this.y.getName().getString();
        String[] stringArray = U;
        this.a("Ground web lava queued target=" + string5 + " place=" + string4 + " support=" + string3 + " face=" + string2 + " aim=" + string);
        return true;
    }

    private Vec3 a(Vec3 vec3, Direction direction) {
        double d2 = 0.01;
        return vec3.add((double)(-direction.getStepX()) * d2, (double)(-direction.getStepY()) * d2, (double)(-direction.getStepZ()) * d2);
    }

    private boolean H() {
        return this.v == d.LAVA_RETRIEVE || this.v == d.GROUND_WEB_BREAK || this.I != c_0.IDLE || this.x == e.GROUND_WEB;
    }

    private Optional<v> h(Entity entity) {
        if (!this.d(entity)) {
            return Optional.empty();
        }
        BlockPos blockPos = BlockPos.containing((double)entity.getX(), (double)entity.getY(), (double)entity.getZ());
        BlockPos blockPos2 = blockPos.below();
        if (AutoWeb.mc.level.getBlockState(blockPos).is(Blocks.COBWEB) || AutoWeb.mc.level.getBlockState(blockPos2).is(Blocks.COBWEB) || this.i(blockPos) || !this.c(blockPos2)) {
            return Optional.empty();
        }
        if (!this.a(blockPos)) {
            return Optional.empty();
        }
        return this.a(blockPos2, blockPos, entity.getX(), entity.getZ(), "lower web");
    }

    private boolean j(Entity entity) {
        if (!this.d(entity)) {
            String[] stringArray = U;
            String string = this.a(entity.getDeltaMovement());
            String string2 = String.format("%.2f", entity.getY());
            String string3 = entity.getName().getString();
            this.a("Skip ground web because target is not grounded: " + string3 + " y=" + string2 + " motion=" + string);
            return false;
        }
        if (this.b(entity)) {
            String[] stringArray = U;
            this.a("Skip ground web because target is moving: " + entity.getName().getString() + " move=" + this.a(this.c(entity)));
            return false;
        }
        return true;
    }

    private void a(List<A> list, int n2, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.above();
        if (AutoWeb.mc.level.getBlockState(blockPos).is(Blocks.COBWEB) && AutoWeb.mc.level.getBlockState(blockPos2).is(Blocks.COBWEB)) {
            return;
        }
        A a2 = new A(n2, blockPos, blockPos2);
        if (!list.contains(a2)) {
            list.add(a2);
        }
    }

    private boolean a(Entity entity) {
        return this.a(entity, false);
    }

    private Optional<y> a(Entity entity, BlockPos blockPos, BlockPos blockPos2, String string) {
        if (this.i(blockPos) || this.i(blockPos2) || !this.a(blockPos) || !this.a(blockPos2)) {
            return Optional.empty();
        }
        Optional<v> optional = this.a(blockPos.below(), blockPos, entity.getX(), entity.getZ(), string + " foot");
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        v v2 = this.a(blockPos, blockPos2);
        return Optional.of(new y(blockPos, blockPos2, optional.get(), v2));
    }

    private VoxelShape b(BlockPos blockPos) {
        BlockState blockState = AutoWeb.mc.level.getBlockState(blockPos);
        VoxelShape voxelShape = blockState.getShape((BlockGetter)AutoWeb.mc.level, blockPos);
        return !voxelShape.isEmpty() ? voxelShape : blockState.getCollisionShape((BlockGetter)AutoWeb.mc.level, blockPos);
    }

    private String a(Vec3 vec3) {
        return String.format("%.2f,%.2f,%.2f", vec3.x, vec3.y, vec3.z);
    }

    private boolean a(BlockPos blockPos) {
        return AutoWeb.mc.level.getBlockState(blockPos).canBeReplaced() && AutoWeb.mc.level.getFluidState(blockPos).isEmpty() && !this.i(blockPos);
    }

    private void a() {
        this.u();
        this.v = d.IDLE;
        k = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = false;
        this.G = false;
        this.H = null;
        this.I = c_0.IDLE;
        this.J = null;
        this.K = 0;
        this.L = false;
        this.M = null;
        this.N = null;
        this.O = null;
    }

    private void a(v v2, e e2) {
        this.w = v2;
        this.x = e2;
        this.z = v2.d;
        k = g.d(AutoWeb.mc.player.getEyePosition(), v2.c);
    }

    private boolean a(a a2) {
        float f2 = AutoWeb.mc.player.getYRot();
        float f3 = AutoWeb.mc.player.getXRot();
        AutoWeb.mc.player.setYRot(a2.e());
        AutoWeb.mc.player.setXRot(a2.f());
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Rot(a2.e(), a2.f(), AutoWeb.mc.player.onGround()));
        InteractionResult interactionResult = AutoWeb.mc.gameMode.useItem((Player)AutoWeb.mc.player, InteractionHand.MAIN_HAND);
        float f4 = a2.f();
        float f5 = a2.e();
        String string = String.valueOf(interactionResult);
        String[] stringArray = U;
        this.a("Bucket use result=" + string + " yaw=" + f5 + " pitch=" + f4);
        if (interactionResult.consumesAction()) {
            AutoWeb.mc.player.swing(InteractionHand.MAIN_HAND);
        }
        AutoWeb.mc.player.setYRot(f2);
        AutoWeb.mc.player.setXRot(f3);
        return interactionResult.consumesAction();
    }

    private String a(boolean bl, boolean bl2, boolean bl3) {
        String string;
        if (bl2) {
            String[] stringArray = U;
            return "flowing";
        }
        if (bl) {
            String[] stringArray = U;
            return "target_water_bucket";
        }
        if (bl3) {
            String[] stringArray = U;
            string = "before_flow";
        } else {
            string = "normal";
        }
        return string;
    }

    private void w() {
        if (AutoWeb.mc.player == null) {
            this.P = -1;
            this.R = -1;
            this.Q = -1;
            this.S = -1;
            return;
        }
        int n2 = this.P >= 0 ? this.P : this.R;
        this.P = -1;
        this.R = -1;
        this.Q = -1;
        this.S = -1;
        if (n2 >= 0 && AutoWeb.mc.player.getInventory().selected != n2) {
            AutoWeb.mc.player.getInventory().selected = n2;
            PlayerUtil.a();
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
                    case 0 -> 50;
                    case 1 -> 49;
                    case 2 -> 44;
                    case 3 -> 29;
                    case 4 -> 11;
                    case 5 -> 1;
                    default -> 112;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private Optional<v> a(BlockPos blockPos, BlockPos blockPos2, double d2, double d3, String string) {
        if (!this.c(blockPos)) {
            return Optional.empty();
        }
        BlockHitResult blockHitResult = null;
        Vec3 vec3 = null;
        for (Vec3 vec32 : this.c(blockPos, d2, d3)) {
            if (AutoWeb.mc.player.getEyePosition().distanceToSqr(vec32) > 25.0) continue;
            Vec3 vec33 = this.a(vec32, Direction.UP);
            BlockHitResult blockHitResult2 = AutoWeb.mc.level.clip(new ClipContext(AutoWeb.mc.player.getEyePosition(), vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)AutoWeb.mc.player));
            if (blockHitResult2.getType() != HitResult.Type.BLOCK) {
                vec3 = vec33;
                continue;
            }
            blockHitResult = blockHitResult2;
            vec3 = vec33;
            if (!blockHitResult2.getBlockPos().equals((Object)blockPos) || blockHitResult2.getDirection() != Direction.UP) continue;
            this.M = blockPos;
            this.N = Direction.UP;
            this.O = blockHitResult2.getLocation();
            return Optional.of(new v(blockPos, Direction.UP, blockHitResult2.getLocation(), blockPos2));
        }
        if (blockHitResult != null) {
            this.M = blockHitResult.getBlockPos();
            this.N = blockHitResult.getDirection();
            this.O = vec3;
            String string2 = this.a(vec3);
            String string3 = String.valueOf(blockHitResult.getDirection());
            String string4 = this.k(blockHitResult.getBlockPos());
            String string5 = this.k(blockPos);
            String string6 = string;
            String[] stringArray = U;
            this.a("Placement fail: " + string6 + " support mismatch expected=" + string5 + " hit=" + string4 + " face=" + string3 + " aim=" + string2);
        } else if (vec3 != null) {
            this.M = blockPos;
            this.N = Direction.UP;
            this.O = vec3;
            String string7 = this.a(vec3);
            String string8 = this.k(blockPos);
            String string9 = string;
            String[] stringArray = U;
            this.a("Placement fail: " + string9 + " ray miss support=" + string8 + " aim=" + string7);
        }
        return Optional.empty();
    }

    private void x() {
        if (AutoWeb.mc.player == null || AutoWeb.mc.level == null || AutoWeb.mc.gameMode == null || this.J == null) {
            this.a();
            return;
        }
        if (!AutoWeb.mc.level.getBlockState(this.J).is(Blocks.COBWEB)) {
            String[] stringArray = U;
            this.a("Ground web break done foot=" + this.k(this.J));
            if (!this.y()) {
                this.a();
            }
            return;
        }
        int n2 = this.F();
        if (n2 == -1) {
            String[] stringArray = U;
            this.a("Ground web break abort: no sword foot=" + this.k(this.J));
            this.a();
            return;
        }
        if (!this.a(n2)) {
            return;
        }
        k = g.d(AutoWeb.mc.player.getEyePosition(), Vec3.atCenterOf((Vec3i)this.J));
        if (!this.L) {
            AutoWeb.mc.gameMode.startDestroyBlock(this.J, Direction.UP);
            this.L = true;
        } else {
            AutoWeb.mc.gameMode.continueDestroyBlock(this.J, Direction.UP);
        }
        AutoWeb.mc.player.swing(InteractionHand.MAIN_HAND);
        if (++this.K >= 24) {
            this.a("Ground web break abort: timeout foot=" + this.k(this.J));
            this.a();
        }
    }

    private boolean i(BlockPos blockPos) {
        return AutoWeb.mc.player != null && AutoWeb.mc.player.getBoundingBox().intersects(new AABB(blockPos));
    }

    private boolean a(AABB aABB, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.above();
        return this.b(aABB, blockPos) || this.b(aABB, blockPos2);
    }

    private Optional<v> a(Entity entity, BlockPos blockPos) {
        if (!AutoWeb.mc.level.getBlockState(blockPos).canBeReplaced()) {
            return Optional.empty();
        }
        if (this.i(blockPos)) {
            return Optional.empty();
        }
        Vec3 vec3 = AutoWeb.mc.player.getEyePosition();
        double d2 = entity.getX() - vec3.x;
        double d3 = entity.getZ() - vec3.z;
        Direction direction = Direction.getNearest((float)((float)d2), (float)0.0f, (float)((float)d3));
        ArrayList<Direction> arrayList = new ArrayList<Direction>();
        arrayList.add(direction);
        for (Direction direction2 : Direction.Plane.HORIZONTAL) {
            if (direction2 == direction) continue;
            arrayList.add(direction2);
        }
        arrayList.add(Direction.DOWN);
        arrayList.add(Direction.UP);
        for (Direction direction2 : arrayList) {
            Direction direction3;
            BlockPos blockPos2 = blockPos.relative(direction2);
            Optional<Vec3> optional = this.b(blockPos2, direction3 = direction2.getOpposite());
            if (optional.isEmpty()) continue;
            return Optional.of(new v(blockPos2, direction3, optional.get(), blockPos));
        }
        return Optional.empty();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x70);
        }
        return cArray;
    }

    private boolean v() {
        if (AutoWeb.mc.player == null || this.R < 0) {
            return false;
        }
        if (AutoWeb.mc.player.tickCount < this.S) {
            return true;
        }
        int n2 = this.R;
        this.R = -1;
        this.S = -1;
        if (AutoWeb.mc.player.getInventory().selected != n2) {
            AutoWeb.mc.player.getInventory().selected = n2;
            PlayerUtil.a();
        }
        return true;
    }

    @Override
    protected void onDisable() {
        this.w();
        this.T.clear();
        this.a();
        super.onDisable();
    }

    private boolean k() {
        if (this.y == null || !((Boolean)this.lava.getValue()).booleanValue() || this.D() == -1) {
            return false;
        }
        if (!this.a(this.y)) {
            return false;
        }
        Optional<v> optional = this.k(this.y);
        if (optional.isEmpty()) {
            String[] stringArray = U;
            this.a("Chain lava skipped: no placement target=" + this.y.getName().getString());
            return false;
        }
        this.D = 0;
        this.a(optional.get());
        String string = this.a(this.w.c);
        String string2 = String.valueOf(this.w.b);
        String string3 = this.k(this.w.a);
        String string4 = this.k(this.w.d);
        String string5 = this.y.getName().getString();
        String[] stringArray = U;
        this.a("Chain lava after cobweb target=" + string5 + " place=" + string4 + " support=" + string3 + " face=" + string2 + " aim=" + string);
        return true;
    }

    private boolean e(BlockPos blockPos) {
        return AutoWeb.mc.level.getBlockState(blockPos).is(Blocks.LAVA) && AutoWeb.mc.level.getFluidState(blockPos).isSource();
    }

    private Optional<v> a(Entity entity, A a2) {
        if (AutoWeb.mc.level.getBlockState(a2.c).is(Blocks.COBWEB)) {
            return Optional.empty();
        }
        return this.a(entity, a2.c);
    }

    private v a(BlockPos blockPos, BlockPos blockPos2) {
        Vec3 vec3 = new Vec3((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5);
        return new v(blockPos, Direction.UP, vec3, blockPos2);
    }

    private int F() {
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack = AutoWeb.mc.player.getInventory().getItem(i2);
            if (itemStack.isEmpty() || !(itemStack.getItem() instanceof SwordItem)) continue;
            return i2;
        }
        return -1;
    }

    private int D() {
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack = AutoWeb.mc.player.getInventory().getItem(i2);
            if (itemStack.isEmpty() || itemStack.getItem() != Items.LAVA_BUCKET) continue;
            return i2;
        }
        return -1;
    }

    private Optional<v> a(BlockPos blockPos, double d2, double d3, String string) {
        Vec3 vec3 = this.b(blockPos, d2, d3);
        return this.a(vec3, blockPos, string);
    }

    private void o() {
        boolean bl;
        if (this.y == null || this.w == null || !((Boolean)this.movementPrediction.getValue()).booleanValue()) {
            return;
        }
        boolean bl2 = bl = this.x == e.WEB_EXIT_PREDICT || this.x == e.GROUND_WEB && this.I == c_0.FOOT_PLACED;
        if (!bl) {
            return;
        }
        int n2 = this.C();
        Optional<z> optional = this.b(this.y, n2);
        if (optional.isEmpty()) {
            return;
        }
        z z2 = optional.get();
        this.w = z2.b;
        this.x = z2.c;
        this.z = z2.b.d;
        if (z2.d != null) {
            this.a(z2.d);
        }
        k = g.d(AutoWeb.mc.player.getEyePosition(), z2.b.c);
    }

    private Optional<Vec3> a(BlockPos blockPos, Direction direction, Vec3 vec3) {
        if (AutoWeb.mc.player == null || AutoWeb.mc.level == null) {
            return Optional.empty();
        }
        Vec3 vec32 = this.a(vec3, direction);
        BlockHitResult blockHitResult = AutoWeb.mc.level.clip(new ClipContext(AutoWeb.mc.player.getEyePosition(), vec32, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)AutoWeb.mc.player));
        if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return Optional.empty();
        }
        return blockHitResult.getBlockPos().equals((Object)blockPos) && blockHitResult.getDirection() == direction ? Optional.of(blockHitResult.getLocation()) : Optional.empty();
    }

    private void h() {
        if (!(this.C() != -1 || ((Boolean)this.lava.getValue()).booleanValue() && this.D() != -1)) {
            return;
        }
        if (!this.u.a(((Number)this.delay.getValue()).intValue())) {
            return;
        }
        Optional<z> optional = this.j();
        if (optional.isEmpty()) {
            return;
        }
        z z2 = optional.get();
        this.y = z2.a;
        this.w = z2.b;
        this.x = z2.c;
        this.z = z2.b.d;
        this.D = 0;
        if (z2.d != null) {
            this.a(z2.d);
        }
        k = g.d(AutoWeb.mc.player.getEyePosition(), this.w.c);
        this.v = d.PLACE;
    }

    private Optional<v> e(Entity entity) {
        for (BlockPos blockPos : this.f(entity)) {
            Optional<v> optional = this.a(entity, blockPos);
            if (!optional.isPresent()) continue;
            return optional;
        }
        return Optional.empty();
    }

    private boolean f(BlockPos blockPos) {
        for (int i2 = -1; i2 <= 1; ++i2) {
            for (int i3 = -1; i3 <= 1; ++i3) {
                for (int i4 = -1; i4 <= 1; ++i4) {
                    if (!this.g(blockPos.offset(i2, i3, i4))) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private Vec3 c(Entity entity) {
        double d2;
        Vec3 vec3 = new Vec3(entity.getX() - entity.xo, entity.getY() - entity.yo, entity.getZ() - entity.zo);
        Vec3 vec32 = entity.getDeltaMovement();
        if (vec3.lengthSqr() > 1.0E-6 && vec3.lengthSqr() < 16.0) {
            vec32 = vec3;
        }
        double d3 = (d2 = Math.hypot(vec32.x, vec32.z)) > 1.25 ? 1.25 / d2 : 1.0;
        return new Vec3(vec32.x * d3, Mth.clamp((double)vec32.y, (double)-1.5, (double)1.5), vec32.z * d3);
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (!((Boolean)this.render.getValue()).booleanValue() || this.z == null && this.M == null || AutoWeb.mc.gameRenderer == null) {
            return;
        }
        Color color = this.x == e.LAVA ? new Color(255, 100, 0) : new Color(255, 255, 255);
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = AutoWeb.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        if (this.z != null) {
            AABB aABB = new AABB(this.z);
            RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.25f);
            RenderUtil.a(aABB, poseStack);
            RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.75f);
            RenderUtil.b(aABB, poseStack);
        }
        if (this.w != null) {
            this.a(poseStack, this.w.a, this.w.b, new Color(255, 230, 0));
            this.a(poseStack, this.w.c, new Color(255, 230, 0));
        } else if (this.M != null && this.N != null) {
            this.a(poseStack, this.M, this.N, new Color(255, 40, 40));
            if (this.O != null) {
                this.a(poseStack, this.O, new Color(255, 40, 40));
            }
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        poseStack.popPose();
    }

    private Optional<v> a(Vec3 vec3, BlockPos blockPos, String string) {
        BlockHitResult blockHitResult = AutoWeb.mc.level.clip(new ClipContext(AutoWeb.mc.player.getEyePosition(), vec3, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)AutoWeb.mc.player));
        if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return Optional.empty();
        }
        if (blockHitResult.getDirection() != Direction.UP) {
            String string2 = this.a(vec3);
            String string3 = this.k(blockHitResult.getBlockPos());
            String string4 = String.valueOf(blockHitResult.getDirection());
            String string5 = string;
            String[] stringArray = U;
            this.a("Lava fail: " + string5 + " face not up face=" + string4 + " support=" + string3 + " aim=" + string2);
            return Optional.empty();
        }
        if (blockPos != null && !blockHitResult.getBlockPos().equals((Object)blockPos)) {
            String string6 = this.a(vec3);
            String string7 = this.k(blockHitResult.getBlockPos());
            String string8 = this.k(blockPos);
            String[] stringArray = U;
            this.a("Lava fail: support ray mismatch expected=" + string8 + " hit=" + string7 + " aim=" + string6);
            return Optional.empty();
        }
        BlockPos blockPos2 = blockHitResult.getBlockPos();
        BlockPos blockPos3 = blockPos2.relative(blockHitResult.getDirection());
        if (this.h(blockPos3)) {
            String[] stringArray = U;
            this.a("Lava fail: " + string + " place is water place=" + this.k(blockPos3));
            return Optional.empty();
        }
        if (!AutoWeb.mc.level.getBlockState(blockPos3).canBeReplaced()) {
            String[] stringArray = U;
            this.a("Lava fail: " + string + " place not replaceable place=" + this.k(blockPos3));
            return Optional.empty();
        }
        if (this.i(blockPos3)) {
            String[] stringArray = U;
            this.a("Lava fail: " + string + " place intersects self place=" + this.k(blockPos3));
            return Optional.empty();
        }
        if (!AutoWeb.mc.level.getFluidState(blockPos3).isEmpty()) {
            String[] stringArray = U;
            this.a("Lava fail: " + string + " place has fluid place=" + this.k(blockPos3));
            return Optional.empty();
        }
        if (!this.c(blockPos2)) {
            String[] stringArray = U;
            this.a("Lava fail: " + string + " support invalid support=" + this.k(blockPos2));
            return Optional.empty();
        }
        if (AutoWeb.mc.player.getEyePosition().distanceToSqr(vec3) > 25.0) {
            String string9 = this.a(vec3);
            String string10 = this.k(blockPos2);
            String string11 = string;
            String[] stringArray = U;
            this.a("Lava fail: " + string11 + " aim too far support=" + string10 + " aim=" + string9);
            return Optional.empty();
        }
        return Optional.of(new v(blockPos2, blockHitResult.getDirection(), vec3, blockPos3));
    }

    private void a(List<Vec3> list, Vec3 vec3) {
        for (Vec3 vec32 : list) {
            if (!(vec32.distanceToSqr(vec3) < 1.0E-6)) continue;
            return;
        }
        list.add(vec3);
    }

    private boolean c(BlockPos blockPos) {
        BlockState blockState = AutoWeb.mc.level.getBlockState(blockPos);
        return !blockState.canBeReplaced() && !this.b(blockPos).isEmpty();
    }

    private String k(BlockPos blockPos) {
        return blockPos.getX() + "," + blockPos.getY() + "," + blockPos.getZ();
    }

    private boolean h(BlockPos blockPos) {
        Fluid fluid = AutoWeb.mc.level.getFluidState(blockPos).getType();
        return AutoWeb.mc.level.getBlockState(blockPos).is(Blocks.WATER) || fluid == Fluids.WATER || fluid == Fluids.FLOWING_WATER;
    }

    private int E() {
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack = AutoWeb.mc.player.getInventory().getItem(i2);
            if (itemStack.isEmpty() || itemStack.getItem() != Items.BUCKET) continue;
            return i2;
        }
        return -1;
    }

    public AutoWeb() {
        String[] stringArray = U;
        super("AutoWeb", Category.PLAYER);
        this.range = new NumberSetting("Range", 4.0, 3.0, 5.0, 0.1);
        this.delay = new NumberSetting("Delay", 4.0, 1.0, 20.0, 1.0);
        this.movementPrediction = new BooleanSetting("Movement Prediction", true);
        this.predictionTicks = new NumberSetting("Prediction Ticks", 2.0, 1.0, 6.0, 1.0, () -> (Boolean)this.movementPrediction.getValue());
        this.render = new BooleanSetting("Render", true);
        this.webPositions = new MultiSelectSetting("Web Positions", "Upper", "Lower").withDefaults("Upper", "Lower");
        this.lava = new BooleanSetting("Lava", false);
        this.lavaWaitWaterBucket = new BooleanSetting("Lava Wait Water Bucket", true);
        this.debug = new BooleanSetting("Debug", false);
        this.u = new lf21.utils.a.g();
        this.v = d.IDLE;
        this.I = c_0.IDLE;
        this.P = -1;
        this.Q = -1;
        this.R = -1;
        this.S = -1;
        this.T = new HashMap<BlockPos, Long>();
        j = this;
    }

    private Optional<z> j() {
        if (AutoWeb.mc.level == null || AutoWeb.mc.player == null) {
            return Optional.empty();
        }
        float f2 = ((Number)this.range.getValue()).floatValue();
        List<Entity> list = StreamSupport.stream(AutoWeb.mc.level.entitiesForRendering().spliterator(), false).filter(entity -> entity instanceof Player && entity != AutoWeb.mc.player).filter(entity -> entity.distanceTo((Entity)AutoWeb.mc.player) <= f2).filter(entity -> KillAura.j != null && KillAura.j.e((Entity)entity)).sorted(Comparator.comparingDouble(entity -> entity.distanceTo((Entity)AutoWeb.mc.player))).toList();
        int n2 = this.C();
        int n3 = (Boolean)this.lava.getValue() != false ? this.D() : -1;
        for (Entity entity2 : list) {
            Optional<v> optional;
            Optional<Object> optional2;
            String[] stringArray;
            Optional<z> optional3;
            boolean bl = this.m(entity2);
            if (bl) {
                optional3 = this.a(entity2, n2);
                if (optional3.isPresent()) {
                    return optional3;
                }
                stringArray = U;
                this.a("Skip cobweb because target stays in cobweb: " + entity2.getName().getString());
            }
            if (!bl && n2 != -1 && (optional3 = this.b(entity2, n2)).isPresent()) {
                return optional3;
            }
            stringArray = U;
            boolean bl2 = this.webPositions.isSelected("Upper");
            boolean bl3 = this.webPositions.isSelected("Lower");
            if (!bl && bl2 && bl3 && n2 != -1 && (optional2 = this.g(entity2)).isPresent()) {
                optional = (y)optional2.get();
                String string = this.k(((y)((Object)optional)).b);
                String string2 = this.k(((y)((Object)optional)).a);
                String string3 = entity2.getName().getString();
                this.a("Ground web target=" + string3 + " foot=" + string2 + " head=" + string);
                return Optional.of(new z(entity2, ((y)((Object)optional)).c, e.GROUND_WEB, (y)((Object)optional)));
            }
            optional2 = Optional.empty();
            if (!bl && n2 != -1) {
                if (bl3 && !bl2) {
                    optional2 = this.h(entity2);
                } else if (bl2 && !bl3) {
                    optional2 = this.i(entity2);
                }
            }
            if (optional2.isPresent()) {
                optional = (v)optional2.get();
                String string = this.a(((v)((Object)optional)).c);
                String string4 = String.valueOf(((v)((Object)optional)).b);
                String string5 = this.k(((v)((Object)optional)).a);
                String string6 = this.k(((v)((Object)optional)).d);
                String string7 = entity2.getName().getString();
                stringArray = U;
                this.a("Cobweb target=" + string7 + " place=" + string6 + " support=" + string5 + " face=" + string4 + " aim=" + string);
                return Optional.of(new z(entity2, (v)((Object)optional), e.COBWEB, null));
            }
            if (n3 == -1 || !this.a(entity2, bl) || !(optional = this.k(entity2)).isPresent()) continue;
            v v2 = optional.get();
            String string = this.a(v2.c);
            String string8 = String.valueOf(v2.b);
            String string9 = this.k(v2.a);
            String string10 = this.k(v2.d);
            String string11 = entity2.getName().getString();
            stringArray = U;
            this.a("Lava target=" + string11 + " place=" + string10 + " support=" + string9 + " face=" + string8 + " aim=" + string);
            return Optional.of(new z(entity2, v2, e.LAVA, null));
        }
        return Optional.empty();
    }

    private boolean d(Entity entity) {
        double d2 = entity.getY() - Math.floor(entity.getY());
        double d3 = Math.min(d2, 1.0 - d2);
        return entity.onGround() || d3 < 0.08 && Math.abs(entity.getDeltaMovement().y) < 0.12;
    }

    private boolean b(Entity entity) {
        Vec3 vec3 = this.c(entity);
        return vec3.x * vec3.x + vec3.z * vec3.z >= 0.01;
    }

    private List<A> a(AABB aABB, int n2) {
        ArrayList<A> arrayList = new ArrayList<A>();
        int n3 = (int)Math.floor(aABB.minX + 1.0E-4);
        int n4 = (int)Math.floor(aABB.maxX - 1.0E-4);
        int n5 = (int)Math.floor(aABB.minY);
        int n6 = (int)Math.floor(aABB.maxY - 1.0E-4);
        int n7 = (int)Math.floor(aABB.minZ + 1.0E-4);
        int n8 = (int)Math.floor(aABB.maxZ - 1.0E-4);
        int n9 = (int)Math.floor(aABB.minY + 0.001);
        double d2 = (aABB.minX + aABB.maxX) * 0.5;
        double d3 = (aABB.minZ + aABB.maxZ) * 0.5;
        BlockPos blockPos = BlockPos.containing((double)d2, (double)aABB.minY, (double)d3);
        if (this.a(aABB, blockPos)) {
            this.a(arrayList, n2, blockPos);
        }
        for (int i2 = n3; i2 <= n4; ++i2) {
            for (int i3 = n5; i3 <= n6; ++i3) {
                for (int i4 = n7; i4 <= n8; ++i4) {
                    BlockPos blockPos2 = new BlockPos(i2, i3, i4);
                    if (!aABB.intersects(new AABB(blockPos2)) || AutoWeb.mc.level.getBlockState(blockPos2).is(Blocks.COBWEB)) continue;
                    int n10 = i3 <= n9 ? n9 : i3 - 1;
                    this.a(arrayList, n2, new BlockPos(i2, n10, i4));
                }
            }
        }
        arrayList.sort(Comparator.comparingDouble(a2 -> Vec3.atCenterOf((Vec3i)a2.b).distanceToSqr(d2, (double)n9 + 0.5, d3)));
        return arrayList;
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (!LF21Client.isReady() || AutoWeb.mc.gameMode == null) {
            return;
        }
        this.s();
        if (this.v()) {
            return;
        }
        if (!this.H() && this.G()) {
            this.a();
            return;
        }
        switch (lf21.modules.impl.player.u.a[this.v.ordinal()]) {
            case 1: {
                this.z();
                break;
            }
            case 2: {
                this.x();
                break;
            }
            case 3: {
                this.h();
                break;
            }
            case 4: {
                this.i();
            }
        }
    }

    private boolean m(Entity entity) {
        return entity != null && AutoWeb.mc.level != null && this.b(entity.getBoundingBox());
    }

    private Optional<v> k(Entity entity) {
        Vec3 vec3 = new Vec3(entity.getX(), entity.getY(), entity.getZ());
        String[] stringArray = U;
        Optional<v> optional = this.a(entity.getX(), entity.getY(), entity.getZ(), "current");
        if (optional.isPresent()) {
            return optional;
        }
        stringArray = U;
        this.a("Lava fail: no lava placement foot=" + this.a(vec3) + " motion=" + this.a(entity.getDeltaMovement()));
        return Optional.empty();
    }

    private void a(y y2) {
        this.H = y2;
        this.I = c_0.FOOT_PLACED;
        this.J = y2.a;
        this.K = 0;
        this.L = false;
    }

    private boolean r() {
        int n2;
        int n3 = n2 = this.x == e.LAVA ? this.D() : this.C();
        if (n2 == -1) {
            String[] stringArray = U;
            this.a("Execute fail: item slot missing type=" + String.valueOf((Object)this.x));
            return false;
        }
        if (this.i(this.w.d)) {
            String[] stringArray = U;
            this.a("Execute wait: placement intersects self place=" + this.k(this.w.d));
            return false;
        }
        if (this.x == e.LAVA) {
            a a2;
            k = a2 = g.d(AutoWeb.mc.player.getEyePosition(), this.w.c);
            String string = this.k(this.w.a);
            String string2 = this.k(this.w.d);
            int n4 = n2;
            String[] stringArray = U;
            this.a("Lava place use bucket slot=" + n4 + " place=" + string2 + " support=" + string);
            this.a(a2);
        } else {
            BlockHitResult blockHitResult = new BlockHitResult(this.w.c, this.w.b, this.w.a, false);
            InteractionResult interactionResult = AutoWeb.mc.gameMode.useItemOn(AutoWeb.mc.player, InteractionHand.MAIN_HAND, blockHitResult);
            if (interactionResult.consumesAction()) {
                this.T.put(this.w.d, System.currentTimeMillis());
                AutoWeb.mc.player.swing(InteractionHand.MAIN_HAND);
            }
        }
        return true;
    }

    private Optional<Vec3> e(BlockPos blockPos, Direction direction) {
        double d2;
        VoxelShape voxelShape = this.b(blockPos);
        if (voxelShape.isEmpty()) {
            return Optional.empty();
        }
        List list = voxelShape.toAabbs();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        AABB aABB = null;
        double d3 = AutoWeb.a(direction) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        for (AABB aABB2 : list) {
            switch (direction) {
                default: {
                    throw new IncompatibleClassChangeError();
                }
                case UP: {
                    double d4 = aABB2.maxY;
                    break;
                }
                case DOWN: {
                    double d4 = aABB2.minY;
                    break;
                }
                case EAST: {
                    double d4 = aABB2.maxX;
                    break;
                }
                case WEST: {
                    double d4 = aABB2.minX;
                    break;
                }
                case SOUTH: {
                    double d4 = aABB2.maxZ;
                    break;
                }
                case NORTH: {
                    double d4 = d2 = aABB2.minZ;
                }
            }
            boolean bl = AutoWeb.a(direction) ? d2 > d3 : d2 < d3;
            boolean bl2 = bl;
            if (!bl2) continue;
            d3 = d2;
            aABB = aABB2;
        }
        if (aABB == null) {
            return Optional.empty();
        }
        double d5 = (double)blockPos.getX() + (aABB.minX + aABB.maxX) * 0.5;
        d2 = (double)blockPos.getY() + (aABB.minY + aABB.maxY) * 0.5;
        double d6 = (double)blockPos.getZ() + (aABB.minZ + aABB.maxZ) * 0.5;
        switch (direction) {
            case UP: {
                d2 = (double)blockPos.getY() + aABB.maxY;
                break;
            }
            case DOWN: {
                d2 = (double)blockPos.getY() + aABB.minY;
                break;
            }
            case EAST: {
                d5 = (double)blockPos.getX() + aABB.maxX;
                break;
            }
            case WEST: {
                d5 = (double)blockPos.getX() + aABB.minX;
                break;
            }
            case SOUTH: {
                d6 = (double)blockPos.getZ() + aABB.maxZ;
                break;
            }
            case NORTH: {
                d6 = (double)blockPos.getZ() + aABB.minZ;
            }
        }
        return Optional.of(new Vec3(d5, d2, d6));
    }

    private Optional<z> a(Entity entity, int n2) {
        if (n2 == -1) {
            return Optional.empty();
        }
        if (!this.webPositions.isSelected("Upper")) {
            return Optional.empty();
        }
        Vec3 vec3 = this.c(entity);
        int n3 = (Boolean)this.movementPrediction.getValue() != false ? Math.max(1, ((Number)this.predictionTicks.getValue()).intValue()) : 0;
        for (int i2 = 0; i2 <= n3; ++i2) {
            Optional<z> optional;
            AABB aABB;
            AABB aABB2 = aABB = i2 == 0 ? entity.getBoundingBox() : this.a(entity, vec3, i2);
            if (this.a(aABB) || !(optional = this.a(entity, this.a(aABB, i2), vec3)).isPresent()) continue;
            return optional;
        }
        return Optional.empty();
    }

    private Optional<v> a(BlockPos blockPos, double d2, double d3) {
        BlockPos blockPos2 = blockPos.below();
        if (AutoWeb.mc.level.getBlockState(blockPos).is(Blocks.COBWEB) || AutoWeb.mc.level.getBlockState(blockPos2).is(Blocks.COBWEB) || this.i(blockPos) || !this.c(blockPos2) || !this.a(blockPos)) {
            return Optional.empty();
        }
        return this.a(blockPos2, blockPos, d2, d3, "predicted lower web");
    }

    private void a(PoseStack poseStack, BlockPos blockPos, Direction direction, Color color) {
        AABB aABB = this.a(blockPos, direction);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.45f);
        RenderUtil.a(aABB, poseStack);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.95f);
        RenderUtil.b(aABB, poseStack);
    }

    private boolean p() {
        if (AutoWeb.mc.player == null || AutoWeb.mc.level == null || AutoWeb.mc.gameMode == null) {
            return false;
        }
        if (this.y == null || !this.y.isAlive() || this.y.isRemoved()) {
            return false;
        }
        if (this.w == null) {
            return false;
        }
        if (this.q() && this.x != e.LAVA && (this.w.b != Direction.UP || !this.w.d.equals((Object)this.w.a.above()) || AutoWeb.mc.level.getBlockState(this.w.a).is(Blocks.COBWEB) || !this.c(this.w.a))) {
            return false;
        }
        if (this.x == e.LAVA) {
            if (!this.a(this.y, this.m(this.y))) {
                return false;
            }
            if (this.i(this.w.d)) {
                return false;
            }
            if (this.h(this.w.d)) {
                return false;
            }
            if (!AutoWeb.mc.level.getBlockState(this.w.d).canBeReplaced()) {
                return false;
            }
            if (!AutoWeb.mc.level.getFluidState(this.w.d).isEmpty()) {
                return false;
            }
            return this.c(this.w.a);
        }
        if (this.x != e.GROUND_WEB && this.x != e.WEB_EXIT_PREDICT && this.m(this.y)) {
            return false;
        }
        if (this.i(this.w.d)) {
            return false;
        }
        if (!AutoWeb.mc.level.getBlockState(this.w.d).canBeReplaced()) {
            return false;
        }
        if (!AutoWeb.mc.level.getFluidState(this.w.d).isEmpty()) {
            return false;
        }
        return this.c(this.w.a);
    }

    private double a(double d2, double d3, double d4) {
        return Math.max(d3, Math.min(d4, d2));
    }

    private void a(String string) {
        if (((Boolean)this.debug.getValue()).booleanValue()) {
            String[] stringArray = U;
            Object object = string.replace("Placement fail: ", "fail ").replace("Lava retrieve ", "lava ").replace("Lava fail: ", "lava fail ").replace("because ", "").replace(" skipped", " skip").replace("Cobweb ", "web ").replace("Predicted web exit ", "predict ").replace("Ground web ", "ground ").replace("target=", "t=").replace("support=", "s=").replace("place=", "p=").replace(" attempt=", " #").replace(" ticks=", " tick=");
            if (((String)object).length() > 72) {
                object = ((String)object).substring(0, 69) + "...";
            }
            ChatUtil.print("[AW] " + (String)object);
        }
    }

    private AABB d(BlockPos blockPos, Direction direction) {
        VoxelShape voxelShape = this.b(blockPos);
        if (voxelShape.isEmpty()) {
            return null;
        }
        AABB aABB = null;
        double d2 = AutoWeb.a(direction) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        for (AABB aABB2 : voxelShape.toAabbs()) {
            double d3;
            switch (direction) {
                default: {
                    throw new IncompatibleClassChangeError();
                }
                case UP: {
                    double d4 = aABB2.maxY;
                    break;
                }
                case DOWN: {
                    double d4 = aABB2.minY;
                    break;
                }
                case EAST: {
                    double d4 = aABB2.maxX;
                    break;
                }
                case WEST: {
                    double d4 = aABB2.minX;
                    break;
                }
                case SOUTH: {
                    double d4 = aABB2.maxZ;
                    break;
                }
                case NORTH: {
                    double d4 = d3 = aABB2.minZ;
                }
            }
            boolean bl = AutoWeb.a(direction) ? d3 > d2 : d3 < d2;
            boolean bl2 = bl;
            if (!bl2) continue;
            d2 = d3;
            aABB = aABB2;
        }
        return aABB;
    }

    private AABB a(BlockPos blockPos, Direction direction) {
        double d2 = blockPos.getX();
        double d3 = blockPos.getY();
        double d4 = blockPos.getZ();
        double d5 = d2 + 1.0;
        double d6 = d3 + 1.0;
        double d7 = d4 + 1.0;
        double d8 = 0.002;
        return switch (direction) {
            default -> throw new IncompatibleClassChangeError();
            case Direction.UP -> new AABB(d2, d6 - d8, d4, d5, d6 + d8, d7);
            case Direction.DOWN -> new AABB(d2, d3 - d8, d4, d5, d3 + d8, d7);
            case Direction.EAST -> new AABB(d5 - d8, d3, d4, d5 + d8, d6, d7);
            case Direction.WEST -> new AABB(d2 - d8, d3, d4, d2 + d8, d6, d7);
            case Direction.SOUTH -> new AABB(d2, d3, d7 - d8, d5, d6, d7 + d8);
            case Direction.NORTH -> new AABB(d2, d3, d4 - d8, d5, d6, d4 + d8);
        };
    }

    private boolean t() {
        int n2 = this.x == e.LAVA ? this.D() : this.C();
        return n2 != -1 && this.a(n2);
    }

    private Optional<v> a(double d2, double d3, double d4, String string) {
        Vec3 vec3 = new Vec3(d2, d3 + 0.2, d4);
        Vec3 vec32 = new Vec3(d2, d3 - 2.2, d4);
        BlockHitResult blockHitResult = AutoWeb.mc.level.clip(new ClipContext(vec3, vec32, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)AutoWeb.mc.player));
        if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return Optional.empty();
        }
        if (blockHitResult.getDirection() != Direction.UP) {
            String string2 = this.a(new Vec3(d2, d3, d4));
            String string3 = this.k(blockHitResult.getBlockPos());
            String string4 = String.valueOf(blockHitResult.getDirection());
            String string5 = string;
            String[] stringArray = U;
            this.a("Lava fail: " + string5 + " ground probe face not up face=" + string4 + " support=" + string3 + " foot=" + string2);
            return Optional.empty();
        }
        return this.a(blockHitResult.getBlockPos(), d2, d4, string);
    }

    private AABB a(Entity entity, Vec3 vec3, int n2) {
        Vec3 vec32 = Vec3.ZERO;
        Vec3 vec33 = vec3;
        boolean bl = entity.onGround();
        for (int i2 = 0; i2 < n2; ++i2) {
            vec32 = vec32.add(vec33);
            vec33 = !bl ? new Vec3(vec33.x * 0.91, (vec33.y - 0.08) * 0.98, vec33.z * 0.91) : new Vec3(vec33.x * 0.91, 0.0, vec33.z * 0.91);
        }
        return entity.getBoundingBox().move(vec32);
    }

    private void s() {
        if (AutoWeb.mc.player == null || AutoWeb.mc.level == null || this.T.isEmpty()) {
            return;
        }
        long l2 = System.currentTimeMillis();
        this.T.entrySet().removeIf(entry -> l2 - (Long)entry.getValue() > 1000L && !AutoWeb.mc.level.getBlockState((BlockPos)entry.getKey()).is(Blocks.COBWEB));
    }

    private List<Vec3> c(BlockPos blockPos, double d2, double d3) {
        double d4 = (double)blockPos.getY() + 1.0;
        ArrayList<Vec3> arrayList = new ArrayList<Vec3>();
        arrayList.add(new Vec3((double)blockPos.getX() + 0.5, d4, (double)blockPos.getZ() + 0.5));
        arrayList.add(new Vec3(this.a(d2, (double)blockPos.getX() + 0.25, (double)blockPos.getX() + 0.75), d4, this.a(d3, (double)blockPos.getZ() + 0.25, (double)blockPos.getZ() + 0.75)));
        arrayList.add(new Vec3((double)blockPos.getX() + 0.35, d4, (double)blockPos.getZ() + 0.35));
        arrayList.add(new Vec3((double)blockPos.getX() + 0.65, d4, (double)blockPos.getZ() + 0.35));
        arrayList.add(new Vec3((double)blockPos.getX() + 0.35, d4, (double)blockPos.getZ() + 0.65));
        arrayList.add(new Vec3((double)blockPos.getX() + 0.65, d4, (double)blockPos.getZ() + 0.65));
        return arrayList;
    }

    private boolean a(Entity entity, boolean bl) {
        boolean bl2;
        if (!((Boolean)this.lava.getValue()).booleanValue()) {
            return false;
        }
        if (entity.isOnFire()) {
            String[] stringArray = U;
            this.a("Skip lava because target is on fire: " + entity.getName().getString());
            return false;
        }
        boolean bl3 = bl2 = bl && this.m(entity);
        if (!bl2 && !this.d(entity)) {
            String[] stringArray = U;
            String string = this.a(entity.getDeltaMovement());
            String string2 = String.format("%.2f", entity.getY());
            String string3 = entity.getName().getString();
            this.a("Skip lava because target is not grounded: " + string3 + " y=" + string2 + " motion=" + string);
            return false;
        }
        if (!bl2 && this.b(entity)) {
            String[] stringArray = U;
            this.a("Skip lava because target is moving: " + entity.getName().getString() + " move=" + this.a(this.c(entity)));
            return false;
        }
        return true;
    }

    private static double b(double d2, double d3, double d4) {
        return d2 + (d3 - d2) * d4;
    }

    private List<Vec3> c(BlockPos blockPos, Direction direction) {
        double[] dArray;
        ArrayList<Vec3> arrayList = new ArrayList<Vec3>();
        this.e(blockPos, direction).ifPresent(arrayList::add);
        AABB aABB = this.d(blockPos, direction);
        if (aABB == null) {
            return arrayList;
        }
        for (double d2 : dArray = new double[]{0.5, 0.25, 0.75}) {
            for (double d3 : dArray) {
                Vec3 vec3 = switch (direction) {
                    default -> throw new IncompatibleClassChangeError();
                    case Direction.UP -> new Vec3((double)blockPos.getX() + AutoWeb.b(aABB.minX, aABB.maxX, d2), (double)blockPos.getY() + aABB.maxY, (double)blockPos.getZ() + AutoWeb.b(aABB.minZ, aABB.maxZ, d3));
                    case Direction.DOWN -> new Vec3((double)blockPos.getX() + AutoWeb.b(aABB.minX, aABB.maxX, d2), (double)blockPos.getY() + aABB.minY, (double)blockPos.getZ() + AutoWeb.b(aABB.minZ, aABB.maxZ, d3));
                    case Direction.EAST -> new Vec3((double)blockPos.getX() + aABB.maxX, (double)blockPos.getY() + AutoWeb.b(aABB.minY, aABB.maxY, d2), (double)blockPos.getZ() + AutoWeb.b(aABB.minZ, aABB.maxZ, d3));
                    case Direction.WEST -> new Vec3((double)blockPos.getX() + aABB.minX, (double)blockPos.getY() + AutoWeb.b(aABB.minY, aABB.maxY, d2), (double)blockPos.getZ() + AutoWeb.b(aABB.minZ, aABB.maxZ, d3));
                    case Direction.SOUTH -> new Vec3((double)blockPos.getX() + AutoWeb.b(aABB.minX, aABB.maxX, d2), (double)blockPos.getY() + AutoWeb.b(aABB.minY, aABB.maxY, d3), (double)blockPos.getZ() + aABB.maxZ);
                    case Direction.NORTH -> new Vec3((double)blockPos.getX() + AutoWeb.b(aABB.minX, aABB.maxX, d2), (double)blockPos.getY() + AutoWeb.b(aABB.minY, aABB.maxY, d3), (double)blockPos.getZ() + aABB.minZ);
                };
                this.a(arrayList, vec3);
            }
        }
        return arrayList;
    }

    private boolean b(AABB aABB) {
        if (AutoWeb.mc.level == null) {
            return false;
        }
        int n2 = (int)Math.floor(aABB.minX);
        int n3 = (int)Math.floor(aABB.maxX);
        int n4 = (int)Math.floor(aABB.minY);
        int n5 = (int)Math.floor(aABB.maxY);
        int n6 = (int)Math.floor(aABB.minZ);
        int n7 = (int)Math.floor(aABB.maxZ);
        for (int i2 = n2; i2 <= n3; ++i2) {
            for (int i3 = n4; i3 <= n5; ++i3) {
                for (int i4 = n6; i4 <= n7; ++i4) {
                    BlockPos blockPos = new BlockPos(i2, i3, i4);
                    if (!AutoWeb.mc.level.getBlockState(blockPos).is(Blocks.COBWEB) || !aABB.intersects(new AABB(blockPos))) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private Optional<v> i(Entity entity) {
        BlockPos blockPos = BlockPos.containing((double)entity.getX(), (double)(entity.getY() + 1.0), (double)entity.getZ());
        return this.a(entity, blockPos);
    }

    private Vec3 b(BlockPos blockPos, double d2, double d3) {
        return new Vec3(this.a(d2, (double)blockPos.getX() + 0.1, (double)blockPos.getX() + 0.9), (double)blockPos.getY() + 0.99, this.a(d3, (double)blockPos.getZ() + 0.1, (double)blockPos.getZ() + 0.9));
    }

    private boolean b(AABB aABB, BlockPos blockPos) {
        return aABB.intersects(new AABB(blockPos)) && !AutoWeb.mc.level.getBlockState(blockPos).is(Blocks.COBWEB);
    }

    public static boolean d(BlockPos blockPos) {
        AutoWeb autoWeb = j;
        return autoWeb != null && autoWeb.isEnabled() && blockPos != null && (blockPos.equals((Object)autoWeb.z) || autoWeb.T.containsKey(blockPos));
    }

    private int B() {
        return AutoWeb.mc.level == null ? 9 : Math.max(1, Fluids.LAVA.getTickDelay((LevelReader)AutoWeb.mc.level) - 1);
    }

    private void a(v v2) {
        this.w = v2;
        this.x = e.LAVA;
        this.z = v2.d;
        k = g.d(AutoWeb.mc.player.getEyePosition(), v2.c);
    }

    private static boolean a(Direction direction) {
        return direction == Direction.UP || direction == Direction.EAST || direction == Direction.SOUTH;
    }

    private void a(List<BlockPos> list, BlockPos blockPos) {
        if (!list.contains(blockPos)) {
            list.add(blockPos);
        }
    }

    private boolean g(BlockPos blockPos) {
        FluidState fluidState = AutoWeb.mc.level.getFluidState(blockPos);
        Fluid fluid = fluidState.getType();
        return (fluid == Fluids.LAVA || fluid == Fluids.FLOWING_LAVA) && !fluidState.isSource();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[135];
                var3_1 = 0;
                var2_2 = "UCCheeP%wII~~u\u0015\u0012WMtg;P[EIp+r\u001c]E\fpbr\u0003[_K=\u007fx\u0000W\f\u000b~PZ|+g\u0011[]\u0016=\u0002B\f\u0005\u0012PEp6\t^^[xy!\u0007WS\u0006\\^^pjm\u0012\u0012WM~n!\u001e]E\fh{!\u0016SRI %bCIybb\u0004WU\fjncPWIEi+f\u0002]DBy+v\u0015P\u0011X|yf\u0015F\f\f~PZ|+u\u0011@VIi6\u0013bCIybb\u0004WU\fjncPWIEi+\u0003\u0012H\u0011\"~PZ|+s\u0015FCEx}dPEPEi+c\u0005QZIi+`\u0004FTAm\u007f<\u001b~PZ|+q\u001cSRI=~r\u0015\u0012SY~`d\u0004\u0012B@r\u007f<\u0005TPEq+\u0006\u0012YI|o<\u0007FP^znuM0~PZ|+s\u0015FCEx}dPV^Bx1!\u0003]D^~n!\u0014[BMm{d\u0011@TH=ny\u0000WRXxo<\n^PZ|+g\u0011[]\f\n\u0012TTmnb\u0004WU\u0011\u0007\u0012EE~`rM\b\u0012BGt{q\u0015V\u000e\u0017\u001f\u001e{'$^\u0000W\u00008%3\u0016\u0005`PBzn\bBCIybb\u0004\u0012\u0006\u0012WM~n<\b\u0012CI|xn\u001e\u000f\u0003\u0012H\u0011\b\u0012BChyb\u0015\u000f\u001fqYMte!\u001cSGM=jg\u0004WC\f~dc\u0007WS\fijs\u0017WE\u0011/~PZ|+s\u0015FCEx}dPSSCo\u007f;PA^YohdP\\^X=mn\u0005\\U\fxsq\u0015QEIy6\u0007\u0012S@rhjM\b\u0012BChyb\u0015\u000f\u0005\u0012BGt{\u0002A\f\t\u0012BYm{n\u0002F\f\u0010b]M~nl\u0015\\E\f{jh\u001c\b\u0011\b\u0012\\Cibn\u001e\u000f\nUCCheePETN\u0006B]M~n<\u0005\u0012WCr\u007f\u0005ip{@+\u0005~^[xy(qYMte!\u001cSGM=xj\u0019BAIy1!\u001e]\u0011\\qjb\u0015_TBi+u\u0011@VIi6\u001b\u0012BYm{n\u0002F\u0011Atxl\u0011FRD=ny\u0000WRXxo<\u0006\u0012WM~n<\"~PZ|+g\u0011[]\u0016=enP^PZ|+q\u001cSRIpno\u0004\u0012WCr\u007f<,~PZ|+s\u0015FCEx}dPSSCo\u007f;P\\^\fxfq\u0004K\u0011Nhhj\u0015F\u0011_r~s\u0013W\f\u0005~^[xy\f\u0012SY~`d\u0004a]Ci6%uCCheePETN=is\u0015SZ\f|in\u0002F\u000b\fibl\u0015]DX=mn\u001fF\f0aZEm+f\u0002]DBy+v\u0015P\u0011Nxh`\u0005AT\fijs\u0017WE\ftx!\u001e]E\fzyn\u0005\\UIy1!\u0012uCCheePETN=\u007f`\u0002UTX /~PZ|+s\u0015FCU={m\u0011QT\f|mu\u0015@\u0011Atxr\u0019\\V\fndt\u0002QT\fxsq\u0015QEIy6\u0002F\f\u0004\u0017\u001f\u001e{!~PZ|+s\u0015FCEx}dPGBI=it\u0013YTX=ju\u0004W\\\\i6\u0005gA\\xy\u0007T]Cjbo\u0017\u0005\u0012PEp6\"uCCheePETN=is\u0015SZ\fn`h\u0000BTH'+o\u001f\u0012B[rye\u0005gA\\xy*aZEm+m\u0011DP\f\u007fnb\u0011GBI=\u007f`\u0002UTX=brP\\^X=ls\u001fG_Hxo;P\u0007\u0012A@|hdM\u0019\u0012BYm{n\u0002F\u0011Es}`\u001c[U\fn~q\u0000]CX \u0003\u001c\u001f\u0002\u0013FP^znu/EPXxy^\u0012GRGx\u007f\u0012BCIybb\u0004WU\fjncPWIEi\t\u0012BYm{n\u0002F\f\u0006\u0012EE~`<\u001f\u0012V^r~o\u0014\u0012A^ridPTPOx+o\u001fF\u0011Ym+g\u0011QT\u0011\u0007\u0012EE~`rM%aZEm+m\u0011DP\f\u007fnb\u0011GBI=\u007f`\u0002UTX=brP]_\f{bs\u0015\b\u0011\bAD\\mds\u0004\u000f\u000e~PZ|+s\u0015FCEx}dP\u000bPTJryd/T]Cj\u0006\u0012\\Ckn<*~PZ|+s\u0015SA\\qr!\u0003YX\\mneJ\u0012_C={m\u0011QTAxeuPFP^znuM\u0015\u0012PEp+u\u001f]\u0011J|y!\u0003GA\\ryuM\u0007sDXr\\d\u0012\u0016~PZ|+V\u0011[E\fJju\u0015@\u0011nhhj\u0015F\"~PZ|+s\u0015FCEx}dPEPEi+r\u001fGCOx+`\u0004FTAm\u007f<\u0005\u0012YEi6)~PZ|+g\u0011[]\u0016=xt\u0000B^^i+s\u0011K\u0011Atxl\u0011FRD=ny\u0000WRXxo<\u0006\u0012YI|o<\u001d\u0012A@|hdP[_Xxyr\u0015QE_=xd\u001cT\u0011\\qjb\u0015\u000f\u0005\u0012YEi6\u0006`TByns&uCCheePETN=is\u0015SZ\f|in\u0002F\u000b\fsd!\u0003E^^y+g\u001f]E\u0011\u000buCCheePETN=,aZEm+b\u001fPFI\u007f+c\u0015QPYnn!\u0004SCKx\u007f!\u0003FPUn+h\u001e\u0012RC\u007f|d\u0012\b\u0011\u0002\u0012\u0012*aZEm+f\u0002]DBy+v\u0015P\u0011Nxh`\u0005AT\fijs\u0017WE\ftx!\u001d]GEsl;P\u001euCCheePETN=g`\u0006S\u0011]hnt\u0015V\u0011X|yf\u0015F\f\u0005vT@|r\u001cuCCheePETN=xj\u0019BAIy1!\u001e]\u0011_jds\u0014\u0016\u0012A@|hdP[B\fjju\u0015@\u0011\\qjb\u0015\u000f\u0004\u0017\u001f\u001e{\u0017\u0012A@|hdPZP_=mm\u0005[U\fmg`\u0013W\f-~PZ|+s\u0015FCEx}dPEPEi+u\u0011@VIi+v\u0011FT^=it\u0013YTX=\u007fh\u0013YB\u0011\u0007q^NjncP)~PZ|+s\u0015FCEx}dPSSCo\u007f;P_PT=ju\u0004W\\\\ix!\u0003]D^~n<\t\u0012PXinl\u0000F\f\u0005vTNhl\u0005^PZ|+\bPTO|~r\u0015\u0012\u0013\u007f^Zxfd\u001eF\u0011|one\u0019QEEre\t\u0012PXinl\u0000F\f\u0007\u0012AEihiM\u0010bCIybb\u0004[^B=_h\u0013YB\b\u0012\\Cibn\u001e\u000f\u001d\u0012A@|hdP\\^X=yd\u0000^POxjc\u001cW\u0011\\qjb\u0015\u000f\u000eq^NjncPFP^znuM\u000b~PZ|+g\u0011[]\u0016=\u0012\u0012CMd+l\u0019AB\fn~q\u0000]CX \u0006\u0012WCr\u007f<\u0007QD^ono\u0004\u001buCCheePETN=is\u0015SZ\fydo\u0015\u0012WCr\u007f<.wII~~u\u0015\u0012FMt\u007f;PB]M~nl\u0015\\E\fteu\u0015@BI~\u007frPAT@{+q\u001cSRI \u0007\u0012A@|hdM\u0012pDOvnuPGBI=yd\u0003G]X \u001abCIybb\u0004WU\fjncPWIEi+u\u0011@VIi6\u0004ETN=\u0010\u0012]MkjC\u0005QZIiXm\u001fF\f\u0006\u0012WCr\u007f<\u0006\u0012\\Ckn<\u0010b]M~nl\u0015\\E\f{jh\u001c\b\u0011\u0014BCIybb\u0004WU\fzyn\u0005\\U\fjnc\reTN=[n\u0003[EErer\u0005\u0012HMj6\u0013BCIybb\u0004WU\fqdv\u0015@\u0011[xi$aZEm+m\u0011DP\f\u007fnb\u0011GBI=\u007f`\u0002UTX=brP_^ZtefJ\u0012\u001b~PZ|+s\u0015SA\\qr!\u0001GTYxo!\u0004SCKx\u007f<";
                var4_3 = "UCCheeP%wII~~u\u0015\u0012WMtg;P[EIp+r\u001c]E\fpbr\u0003[_K=\u007fx\u0000W\f\u000b~PZ|+g\u0011[]\u0016=\u0002B\f\u0005\u0012PEp6\t^^[xy!\u0007WS\u0006\\^^pjm\u0012\u0012WM~n!\u001e]E\fh{!\u0016SRI %bCIybb\u0004WU\fjncPWIEi+f\u0002]DBy+v\u0015P\u0011X|yf\u0015F\f\f~PZ|+u\u0011@VIi6\u0013bCIybb\u0004WU\fjncPWIEi+\u0003\u0012H\u0011\"~PZ|+s\u0015FCEx}dPEPEi+c\u0005QZIi+`\u0004FTAm\u007f<\u001b~PZ|+q\u001cSRI=~r\u0015\u0012SY~`d\u0004\u0012B@r\u007f<\u0005TPEq+\u0006\u0012YI|o<\u0007FP^znuM0~PZ|+s\u0015FCEx}dPV^Bx1!\u0003]D^~n!\u0014[BMm{d\u0011@TH=ny\u0000WRXxo<\n^PZ|+g\u0011[]\f\n\u0012TTmnb\u0004WU\u0011\u0007\u0012EE~`rM\b\u0012BGt{q\u0015V\u000e\u0017\u001f\u001e{'$^\u0000W\u00008%3\u0016\u0005`PBzn\bBCIybb\u0004\u0012\u0006\u0012WM~n<\b\u0012CI|xn\u001e\u000f\u0003\u0012H\u0011\b\u0012BChyb\u0015\u000f\u001fqYMte!\u001cSGM=jg\u0004WC\f~dc\u0007WS\fijs\u0017WE\u0011/~PZ|+s\u0015FCEx}dPSSCo\u007f;PA^YohdP\\^X=mn\u0005\\U\fxsq\u0015QEIy6\u0007\u0012S@rhjM\b\u0012BChyb\u0015\u000f\u0005\u0012BGt{\u0002A\f\t\u0012BYm{n\u0002F\f\u0010b]M~nl\u0015\\E\f{jh\u001c\b\u0011\b\u0012\\Cibn\u001e\u000f\nUCCheePETN\u0006B]M~n<\u0005\u0012WCr\u007f\u0005ip{@+\u0005~^[xy(qYMte!\u001cSGM=xj\u0019BAIy1!\u001e]\u0011\\qjb\u0015_TBi+u\u0011@VIi6\u001b\u0012BYm{n\u0002F\u0011Atxl\u0011FRD=ny\u0000WRXxo<\u0006\u0012WM~n<\"~PZ|+g\u0011[]\u0016=enP^PZ|+q\u001cSRIpno\u0004\u0012WCr\u007f<,~PZ|+s\u0015FCEx}dPSSCo\u007f;P\\^\fxfq\u0004K\u0011Nhhj\u0015F\u0011_r~s\u0013W\f\u0005~^[xy\f\u0012SY~`d\u0004a]Ci6%uCCheePETN=is\u0015SZ\f|in\u0002F\u000b\fibl\u0015]DX=mn\u001fF\f0aZEm+f\u0002]DBy+v\u0015P\u0011Nxh`\u0005AT\fijs\u0017WE\ftx!\u001e]E\fzyn\u0005\\UIy1!\u0012uCCheePETN=\u007f`\u0002UTX /~PZ|+s\u0015FCU={m\u0011QT\f|mu\u0015@\u0011Atxr\u0019\\V\fndt\u0002QT\fxsq\u0015QEIy6\u0002F\f\u0004\u0017\u001f\u001e{!~PZ|+s\u0015FCEx}dPGBI=it\u0013YTX=ju\u0004W\\\\i6\u0005gA\\xy\u0007T]Cjbo\u0017\u0005\u0012PEp6\"uCCheePETN=is\u0015SZ\fn`h\u0000BTH'+o\u001f\u0012B[rye\u0005gA\\xy*aZEm+m\u0011DP\f\u007fnb\u0011GBI=\u007f`\u0002UTX=brP\\^X=ls\u001fG_Hxo;P\u0007\u0012A@|hdM\u0019\u0012BYm{n\u0002F\u0011Es}`\u001c[U\fn~q\u0000]CX \u0003\u001c\u001f\u0002\u0013FP^znu/EPXxy^\u0012GRGx\u007f\u0012BCIybb\u0004WU\fjncPWIEi\t\u0012BYm{n\u0002F\f\u0006\u0012EE~`<\u001f\u0012V^r~o\u0014\u0012A^ridPTPOx+o\u001fF\u0011Ym+g\u0011QT\u0011\u0007\u0012EE~`rM%aZEm+m\u0011DP\f\u007fnb\u0011GBI=\u007f`\u0002UTX=brP]_\f{bs\u0015\b\u0011\bAD\\mds\u0004\u000f\u000e~PZ|+s\u0015FCEx}dP\u000bPTJryd/T]Cj\u0006\u0012\\Ckn<*~PZ|+s\u0015SA\\qr!\u0003YX\\mneJ\u0012_C={m\u0011QTAxeuPFP^znuM\u0015\u0012PEp+u\u001f]\u0011J|y!\u0003GA\\ryuM\u0007sDXr\\d\u0012\u0016~PZ|+V\u0011[E\fJju\u0015@\u0011nhhj\u0015F\"~PZ|+s\u0015FCEx}dPEPEi+r\u001fGCOx+`\u0004FTAm\u007f<\u0005\u0012YEi6)~PZ|+g\u0011[]\u0016=xt\u0000B^^i+s\u0011K\u0011Atxl\u0011FRD=ny\u0000WRXxo<\u0006\u0012YI|o<\u001d\u0012A@|hdP[_Xxyr\u0015QE_=xd\u001cT\u0011\\qjb\u0015\u000f\u0005\u0012YEi6\u0006`TByns&uCCheePETN=is\u0015SZ\f|in\u0002F\u000b\fsd!\u0003E^^y+g\u001f]E\u0011\u000buCCheePETN=,aZEm+b\u001fPFI\u007f+c\u0015QPYnn!\u0004SCKx\u007f!\u0003FPUn+h\u001e\u0012RC\u007f|d\u0012\b\u0011\u0002\u0012\u0012*aZEm+f\u0002]DBy+v\u0015P\u0011Nxh`\u0005AT\fijs\u0017WE\ftx!\u001d]GEsl;P\u001euCCheePETN=g`\u0006S\u0011]hnt\u0015V\u0011X|yf\u0015F\f\u0005vT@|r\u001cuCCheePETN=xj\u0019BAIy1!\u001e]\u0011_jds\u0014\u0016\u0012A@|hdP[B\fjju\u0015@\u0011\\qjb\u0015\u000f\u0004\u0017\u001f\u001e{\u0017\u0012A@|hdPZP_=mm\u0005[U\fmg`\u0013W\f-~PZ|+s\u0015FCEx}dPEPEi+u\u0011@VIi+v\u0011FT^=it\u0013YTX=\u007fh\u0013YB\u0011\u0007q^NjncP)~PZ|+s\u0015FCEx}dPSSCo\u007f;P_PT=ju\u0004W\\\\ix!\u0003]D^~n<\t\u0012PXinl\u0000F\f\u0005vTNhl\u0005^PZ|+\bPTO|~r\u0015\u0012\u0013\u007f^Zxfd\u001eF\u0011|one\u0019QEEre\t\u0012PXinl\u0000F\f\u0007\u0012AEihiM\u0010bCIybb\u0004[^B=_h\u0013YB\b\u0012\\Cibn\u001e\u000f\u001d\u0012A@|hdP\\^X=yd\u0000^POxjc\u001cW\u0011\\qjb\u0015\u000f\u000eq^NjncPFP^znuM\u000b~PZ|+g\u0011[]\u0016=\u0012\u0012CMd+l\u0019AB\fn~q\u0000]CX \u0006\u0012WCr\u007f<\u0007QD^ono\u0004\u001buCCheePETN=is\u0015SZ\fydo\u0015\u0012WCr\u007f<.wII~~u\u0015\u0012FMt\u007f;PB]M~nl\u0015\\E\fteu\u0015@BI~\u007frPAT@{+q\u001cSRI \u0007\u0012A@|hdM\u0012pDOvnuPGBI=yd\u0003G]X \u001abCIybb\u0004WU\fjncPWIEi+u\u0011@VIi6\u0004ETN=\u0010\u0012]MkjC\u0005QZIiXm\u001fF\f\u0006\u0012WCr\u007f<\u0006\u0012\\Ckn<\u0010b]M~nl\u0015\\E\f{jh\u001c\b\u0011\u0014BCIybb\u0004WU\fzyn\u0005\\U\fjnc\reTN=[n\u0003[EErer\u0005\u0012HMj6\u0013BCIybb\u0004WU\fqdv\u0015@\u0011[xi$aZEm+m\u0011DP\f\u007fnb\u0011GBI=\u007f`\u0002UTX=brP_^ZtefJ\u0012\u001b~PZ|+s\u0015SA\\qr!\u0001GTYxo!\u0004SCKx\u007f<".length();
                var1_4 = 7;
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
                    var5[var3_1++] = AutoWeb.c(AutoWeb.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "~PZ|2uCCheePETN=g`\u0006S\u0011_vbq\u0000WU\u0016=enP^PZ|+q\u001cSRIpno\u0004\u0012EMold\u0004\u000f";
                    var4_3 = "~PZ|2uCCheePETN=g`\u0006S\u0011_vbq\u0000WU\u0016=enP^PZ|+q\u001cSRIpno\u0004\u0012EMold\u0004\u000f".length();
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
                    var5[var3_1++] = AutoWeb.c(AutoWeb.b(v1));
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
        AutoWeb.U = var5;
    }

    private boolean a(AABB aABB) {
        if (AutoWeb.mc.level == null) {
            return false;
        }
        boolean bl = false;
        int n2 = (int)Math.floor(aABB.minX);
        int n3 = (int)Math.floor(aABB.maxX);
        int n4 = (int)Math.floor(aABB.minY);
        int n5 = (int)Math.floor(aABB.maxY);
        int n6 = (int)Math.floor(aABB.minZ);
        int n7 = (int)Math.floor(aABB.maxZ);
        for (int i2 = n2; i2 <= n3; ++i2) {
            for (int i3 = n4; i3 <= n5; ++i3) {
                for (int i4 = n6; i4 <= n7; ++i4) {
                    BlockPos blockPos = new BlockPos(i2, i3, i4);
                    if (!aABB.intersects(new AABB(blockPos))) continue;
                    if (!AutoWeb.mc.level.getBlockState(blockPos).is(Blocks.COBWEB)) {
                        return false;
                    }
                    bl = true;
                }
            }
        }
        return bl;
    }

    private boolean A() {
        if (this.y == null || this.y.isRemoved() || !this.y.isAlive()) {
            return false;
        }
        if (this.y.isOnFire()) {
            return false;
        }
        if (this.D >= 1) {
            return false;
        }
        if (this.D() == -1) {
            return false;
        }
        if (!this.a(this.y, true)) {
            return false;
        }
        Optional<v> optional = this.k(this.y);
        if (optional.isEmpty()) {
            String[] stringArray = U;
            this.a("Lava reapply skipped: no placement target=" + this.y.getName().getString());
            return false;
        }
        ++this.D;
        this.a(optional.get());
        this.v = d.PLACE;
        String string = this.a(this.w.c);
        String string2 = String.valueOf(this.w.b);
        String string3 = this.k(this.w.a);
        String string4 = this.k(this.w.d);
        int n2 = this.D;
        String string5 = this.y.getName().getString();
        String[] stringArray = U;
        this.a("Lava reapply queued target=" + string5 + " attempt=" + n2 + " place=" + string4 + " support=" + string3 + " face=" + string2 + " aim=" + string);
        return true;
    }

    private List<BlockPos> f(Entity entity) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        this.a(arrayList, BlockPos.containing((double)entity.getX(), (double)(entity.getY() + 1.0), (double)entity.getZ()));
        AABB aABB = entity.getBoundingBox();
        int n2 = (int)Math.floor(aABB.minX - 0.45);
        int n3 = (int)Math.floor(aABB.maxX + 0.45);
        int n4 = (int)Math.floor(aABB.minZ - 0.45);
        int n5 = (int)Math.floor(aABB.maxZ + 0.45);
        int n6 = (int)Math.floor(entity.getY() + 1.0);
        int n7 = (int)Math.floor(aABB.maxY - 0.05);
        for (int i2 = Math.min(n6, n7); i2 <= Math.max(n6, n7); ++i2) {
            for (int i3 = n2; i3 <= n3; ++i3) {
                for (int i4 = n4; i4 <= n5; ++i4) {
                    this.a(arrayList, new BlockPos(i3, i2, i4));
                }
            }
        }
        return arrayList;
    }

    private void u() {
        if (AutoWeb.mc.player == null || this.P < 0) {
            return;
        }
        this.R = this.P;
        this.S = AutoWeb.mc.player.tickCount + 1;
        this.P = -1;
        this.Q = -1;
    }

    private Optional<y> g(Entity entity) {
        if (!this.j(entity)) {
            return Optional.empty();
        }
        if (((Boolean)this.lava.getValue()).booleanValue() && this.F() == -1) {
            String[] stringArray = U;
            this.a("Ground web skipped: no sword");
            return Optional.empty();
        }
        BlockPos blockPos = BlockPos.containing((double)entity.getX(), (double)entity.getY(), (double)entity.getZ());
        return this.a(entity, blockPos, blockPos.above(), "ground web");
    }

    private void n() {
        if (this.F() == -1) {
            this.a("Ground web break skipped: no sword");
            this.a();
            return;
        }
        this.w = null;
        this.x = null;
        this.z = this.J;
        k = g.d(AutoWeb.mc.player.getEyePosition(), Vec3.atCenterOf((Vec3i)this.J));
        this.K = 0;
        this.L = false;
        this.v = d.GROUND_WEB_BREAK;
    }

    private void z() {
        boolean bl;
        if (AutoWeb.mc.player == null || AutoWeb.mc.level == null || AutoWeb.mc.gameMode == null || this.w == null) {
            this.a();
            return;
        }
        ++this.B;
        BlockPos blockPos = this.w.d;
        BlockPos blockPos2 = this.j(blockPos);
        BlockPos blockPos3 = blockPos2 != null ? blockPos2 : blockPos;
        boolean bl2 = this.f(blockPos3);
        boolean bl3 = this.l(this.y);
        boolean bl4 = this.B >= this.B();
        boolean bl5 = bl3 || bl2 || bl4;
        boolean bl6 = bl = (Boolean)this.lavaWaitWaterBucket.getValue() == false || bl5;
        if (this.A > 0 && !bl5) {
            --this.A;
            return;
        }
        if (blockPos2 != null) {
            this.F = true;
            this.E = 0;
            this.z = blockPos2;
            k = g.a(blockPos2, 0.0f);
            if (!bl) {
                String string = this.k(blockPos2);
                int n2 = this.B();
                int n3 = this.B;
                String[] stringArray = U;
                this.a("Lava retrieve wait target water bucket ticks=" + n3 + "/" + n2 + " source=" + string);
                this.A = 1;
                return;
            }
            int n4 = this.E();
            if (n4 == -1) {
                if (++this.C >= 8) {
                    String[] stringArray = U;
                    this.a("Lava retrieve abort: no empty bucket source=" + this.k(blockPos2));
                    this.a();
                    return;
                }
                int n5 = this.D();
                String string = this.k(blockPos2);
                int n6 = this.C;
                String[] stringArray = U;
                this.a("Lava retrieve wait bucket attempt=" + n6 + " source=" + string + " lavaBucketSlot=" + n5);
                this.A = 1;
                return;
            }
            int n7 = this.C + 1;
            String string = this.a(bl3, bl2, bl4);
            String string2 = this.k(blockPos2);
            int n8 = n4;
            int n9 = n7;
            String[] stringArray = U;
            this.a("Lava retrieve use bucket attempt=" + n9 + " bucketSlot=" + n8 + " source=" + string2 + " reason=" + string);
            if (!this.a(n4)) {
                return;
            }
            this.a(k);
            if (++this.C >= 8) {
                stringArray = U;
                this.a("Lava retrieve abort: max attempts source=" + this.k(blockPos2));
                this.a();
            } else {
                this.A = 2;
            }
            return;
        }
        if (this.F) {
            String[] stringArray = U;
            this.a("Lava retrieve done: source disappeared expected=" + this.k(blockPos));
            if (!this.A()) {
                this.a();
            }
            return;
        }
        if (++this.E >= 8) {
            String[] stringArray = U;
            this.a("Lava retrieve abort: source not found expected=" + this.k(blockPos) + " support=" + this.k(this.w.a));
            this.a();
            return;
        }
        String string = String.valueOf(AutoWeb.mc.level.getBlockState(blockPos).getBlock());
        String string3 = this.k(blockPos);
        int n10 = this.E;
        String[] stringArray = U;
        this.a("Lava retrieve wait source attempt=" + n10 + " expected=" + string3 + " block=" + string);
        if (!this.G && this.E >= 2 && this.D() != -1 && this.p()) {
            this.a("Lava retry place after missing source expected=" + this.k(blockPos));
            if (this.t()) {
                this.G = true;
                this.r();
            }
        }
        this.A = 1;
    }

    private boolean q() {
        String[] stringArray = U;
        return this.webPositions.isSelected("Lower") && !this.webPositions.isSelected("Upper");
    }

    private void i() {
        this.o();
        if (!this.p()) {
            this.a();
            return;
        }
        if (!this.t()) {
            return;
        }
        if (!this.r()) {
            this.a();
            return;
        }
        if (this.l()) {
            if (!this.m()) {
                this.u.b();
            }
            return;
        }
        if (this.x == e.WEB_EXIT_PREDICT) {
            this.u.b();
            this.a();
            return;
        }
        if (this.x == e.LAVA) {
            this.u.b();
            k = g.a(this.w.a, 0.0f);
            this.A = 1;
            this.B = 0;
            this.C = 0;
            this.E = 0;
            this.F = false;
            this.G = false;
            this.v = d.LAVA_RETRIEVE;
            return;
        }
        if (!this.k()) {
            this.u.b();
            this.a();
        } else {
            this.v = d.PLACE;
        }
    }

    private Optional<Vec3> b(BlockPos blockPos, Direction direction) {
        for (Vec3 vec3 : this.c(blockPos, direction)) {
            Optional<Vec3> optional;
            if (AutoWeb.mc.player.getEyePosition().distanceToSqr(vec3) > 25.0 || !(optional = this.a(blockPos, direction, vec3)).isPresent()) continue;
            return optional;
        }
        return Optional.empty();
    }

    private BlockPos j(BlockPos blockPos) {
        if (this.e(blockPos)) {
            return blockPos;
        }
        BlockPos blockPos2 = null;
        double d2 = Double.POSITIVE_INFINITY;
        for (int i2 = -1; i2 <= 1; ++i2) {
            for (int i3 = -1; i3 <= 1; ++i3) {
                for (int i4 = -1; i4 <= 1; ++i4) {
                    double d3;
                    BlockPos blockPos3 = blockPos.offset(i2, i3, i4);
                    if (!this.e(blockPos3) || !((d3 = Vec3.atCenterOf((Vec3i)blockPos).distanceToSqr(Vec3.atCenterOf((Vec3i)blockPos3))) < d2)) continue;
                    blockPos2 = blockPos3;
                    d2 = d3;
                }
            }
        }
        return blockPos2;
    }

    private int C() {
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack = AutoWeb.mc.player.getInventory().getItem(i2);
            if (itemStack.isEmpty() || itemStack.getItem() != Items.COBWEB) continue;
            return i2;
        }
        return -1;
    }

    private boolean l(Entity entity) {
        if (!(entity instanceof Player)) {
            return false;
        }
        Player player = (Player)entity;
        return player.getMainHandItem().getItem() == Items.WATER_BUCKET || player.getOffhandItem().getItem() == Items.WATER_BUCKET;
    }

    private boolean G() {
        if (Helper.j != null && Helper.j.isEnabled() && Helper.k != null) {
            return true;
        }
        if (AntiWeb.j != null && AntiWeb.j.isEnabled() && AntiWeb.l != b_0.IDLE && AntiWeb.k != null) {
            return true;
        }
        if (AntiTNT.j != null && AntiTNT.j.isEnabled() && AntiTNT.p != null) {
            return true;
        }
        return MidPearl.j != null && MidPearl.j.isEnabled() && MidPearl.k != null;
    }

    private boolean l() {
        return this.x == e.GROUND_WEB;
    }

    @Override
    protected void onEnable() {
        this.T.clear();
        this.a();
        super.onEnable();
    }

    private Optional<z> b(Entity entity, int n2) {
        if (!((Boolean)this.movementPrediction.getValue()).booleanValue() || n2 < 0) {
            return Optional.empty();
        }
        Vec3 vec3 = this.c(entity);
        if (vec3.horizontalDistanceSqr() < 2.5E-4 && Math.abs(vec3.y) < 0.035) {
            return Optional.empty();
        }
        int n3 = AutoWeb.mc.player.getInventory().selected == n2 ? 0 : 1;
        int n4 = Mth.clamp((int)(((Number)this.predictionTicks.getValue()).intValue() + n3), (int)1, (int)7);
        AABB aABB = this.a(entity, vec3, n4);
        List<A> list = this.a(aABB, n4);
        String[] stringArray = U;
        boolean bl = this.webPositions.isSelected("Upper");
        boolean bl2 = this.webPositions.isSelected("Lower");
        if (bl && bl2) {
            for (A a2 : list) {
                Optional<y> optional = this.a(entity, a2.b, a2.c, "predicted ground web");
                if (!optional.isPresent()) continue;
                y y2 = optional.get();
                return Optional.of(new z(entity, y2.c, e.GROUND_WEB, y2));
            }
        }
        if (bl2 && !bl) {
            double d2 = (aABB.minX + aABB.maxX) * 0.5;
            double d3 = (aABB.minZ + aABB.maxZ) * 0.5;
            for (A a3 : list) {
                Optional<v> optional = this.a(a3.b, d2, d3);
                if (!optional.isPresent()) continue;
                return Optional.of(new z(entity, optional.get(), e.WEB_EXIT_PREDICT, null));
            }
        }
        if (bl) {
            for (A a2 : list) {
                Optional<v> optional = this.a(entity, a2);
                if (!optional.isPresent()) continue;
                return Optional.of(new z(entity, optional.get(), e.WEB_EXIT_PREDICT, null));
            }
        }
        return Optional.empty();
    }

    private boolean a(int n2) {
        if (AutoWeb.mc.player == null) {
            return false;
        }
        if (this.P == -1) {
            this.P = AutoWeb.mc.player.getInventory().selected;
        }
        if (AutoWeb.mc.player.getInventory().selected != n2) {
            AutoWeb.mc.player.getInventory().selected = n2;
            PlayerUtil.a();
            this.Q = AutoWeb.mc.player.tickCount + 1;
            return false;
        }
        return this.Q < 0 || AutoWeb.mc.player.tickCount >= this.Q;
    }

    private boolean m() {
        if (this.H == null) {
            this.a();
            return false;
        }
        switch (lf21.modules.impl.player.u.c[this.I.ordinal()]) {
            case 1: {
                if (!this.webPositions.isSelected("Upper")) {
                    this.u.b();
                    this.a();
                    return false;
                }
                this.I = c_0.HEAD_PLACED;
                this.a(this.H.d, e.GROUND_WEB);
                return true;
            }
            case 2: {
                if (((Boolean)this.lava.getValue()).booleanValue() && this.F() != -1) {
                    this.n();
                    return true;
                }
                this.u.b();
                this.a();
                return false;
            }
        }
        this.a();
        return false;
    }

    private void a(PoseStack poseStack, Vec3 vec3, Color color) {
        double d2 = 0.04;
        AABB aABB = new AABB(vec3.x - d2, vec3.y - d2, vec3.z - d2, vec3.x + d2, vec3.y + d2, vec3.z + d2);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.85f);
        RenderUtil.a(aABB, poseStack);
    }

    private Optional<z> a(Entity entity, List<A> list, Vec3 vec3) {
        String[] stringArray = U;
        boolean bl = this.webPositions.isSelected("Upper");
        boolean bl2 = this.webPositions.isSelected("Lower");
        for (A a2 : list) {
            Optional<Object> optional;
            if (bl && (optional = this.a(entity, a2)).isPresent()) {
                v v2 = optional.get();
                String string = this.a(vec3);
                String string2 = String.valueOf(v2.b);
                String string3 = this.k(v2.a);
                String string4 = this.k(v2.d);
                int n2 = a2.a;
                String string5 = entity.getName().getString();
                stringArray = U;
                this.a("Predicted web exit target=" + string5 + " ticks=" + n2 + " place=" + string4 + " support=" + string3 + " face=" + string2 + " move=" + string);
                return Optional.of(new z(entity, v2, e.WEB_EXIT_PREDICT, null));
            }
            if (!bl || !bl2) continue;
            stringArray = U;
            optional = this.a(entity, a2.b, a2.c, "predicted web exit");
            if (!optional.isPresent()) continue;
            y y2 = (y)optional.get();
            String string = this.a(vec3);
            String string6 = this.k(y2.b);
            String string7 = this.k(y2.a);
            int n3 = a2.a;
            String string8 = entity.getName().getString();
            this.a("Predicted web exit ground web target=" + string8 + " ticks=" + n3 + " foot=" + string7 + " head=" + string6 + " move=" + string);
            return Optional.of(new z(entity, y2.c, e.GROUND_WEB, y2));
        }
        return Optional.empty();
    }
}

