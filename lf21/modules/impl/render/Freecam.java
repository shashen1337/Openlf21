/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import com.mojang.blaze3d.platform.InputConstants;
import lf21.asm.Invocation;
import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.e;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.g;
import lf21.utils.game.m;
import lf21.utils.misc.ReflectionUtil;
import lf21.utils.rotation.a;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class Freecam
extends Module {
    public final BooleanSetting C;
    public static Freecam INSTANCE;
    public final BooleanSetting A;
    public final NumberSetting horizontalSpeed;
    public final BooleanSetting ignoreAllCollision;
    private a O;
    private m V;
    public final BooleanSetting showSubmersionFog;
    public final BooleanSetting freezePlayer;
    private HitResult T;
    private a P;
    public final ModeSetting flightMode;
    private CameraType R;
    public final BooleanSetting ignoreTransparentBlocks;
    private Entity U;
    public final ModeSetting initialPerspective;
    private static final double k;
    public final BooleanSetting fullBrightness;
    public final BooleanSetting playerControl;
    private static final int j;
    public final BooleanSetting alwaysCheckInitialCollision;
    private float Q;
    private Vec3 J;
    public final BooleanSetting D;
    public final BooleanSetting F;
    public final BooleanSetting G;
    public final NumberSetting verticalSpeed;
    public final BooleanSetting showHand;
    private boolean S;
    public final BooleanSetting renderBody;
    private Vec3 M;
    public final BooleanSetting ignoreOpenableBlocks;
    private Vec3 I;
    private Vec3 L;
    public final BooleanSetting E;
    public final BooleanSetting H;
    public final BooleanSetting cancelOnDamage;
    private a N;
    private Vec3 K;
    private static final String[] W;
    public final ModeSetting B;

    private HitResult a(Vec3 vec3, a a2, double d2, boolean bl) {
        AABB aABB;
        EntityHitResult entityHitResult;
        double d3;
        BlockHitResult blockHitResult;
        if (Freecam.mc.player == null || Freecam.mc.level == null) {
            return null;
        }
        Vec3 vec32 = g.b(a2);
        Vec3 vec33 = vec3.add(vec32.scale(d2));
        BlockHitResult blockHitResult2 = blockHitResult = Freecam.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)Freecam.mc.player));
        double d4 = d3 = blockHitResult.getType() == HitResult.Type.MISS ? d2 * d2 : vec3.distanceToSqr(blockHitResult.getLocation());
        if (bl && (entityHitResult = ProjectileUtil.getEntityHitResult((Entity)Freecam.mc.player, (Vec3)vec3, (Vec3)vec33, (AABB)(aABB = new AABB(vec3, vec33).inflate(1.0)), entity -> entity != Freecam.mc.player && entity != this.V && !entity.isSpectator() && entity.isPickable(), (double)d3)) != null) {
            blockHitResult2 = entityHitResult;
        }
        return blockHitResult2;
    }

    public static boolean shouldSuppressPlayerInput() {
        return Freecam.k() && (Boolean)Freecam.INSTANCE.playerControl.getValue() == false;
    }

    private void a() {
        String[] stringArray = W;
        if (this.initialPerspective.is("Inside")) {
            return;
        }
        stringArray = W;
        if (this.initialPerspective.is("First Person")) {
            this.a(0.4);
            return;
        }
        if (this.initialPerspective.is("Third Person Mirror")) {
            this.N = new a(this.N.e() + 180.0f, -this.N.f());
        }
        this.a(-4.0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean captureMouseTurn(Invocation<Entity, Void> invocation) {
        Freecam freecam = INSTANCE;
        if (!Freecam.k() || ((Boolean)freecam.playerControl.getValue()).booleanValue() || invocation == null) {
            return false;
        }
        try {
            if (invocation.args().size() < 2) {
                return false;
            }
            double d2 = ((Number)invocation.args().get(0)).doubleValue();
            double d3 = ((Number)invocation.args().get(1)).doubleValue();
            freecam.O = freecam.N.a();
            freecam.N = new a(freecam.N.e() + (float)(d2 * 0.15), Mth.clamp((float)(freecam.N.f() + (float)(d3 * 0.15)), (float)-90.0f, (float)90.0f));
            return true;
        }
        catch (Throwable throwable) {
            return false;
        }
    }

    public static boolean shouldHideSubmersionFog() {
        return Freecam.k() && (Boolean)Freecam.INSTANCE.showSubmersionFog.getValue() == false;
    }

    private void a(double d2) {
        double d3;
        boolean bl = (Boolean)this.alwaysCheckInitialCollision.getValue() != false || (Boolean)this.ignoreAllCollision.getValue() == false;
        Vec3 vec3 = g.b(this.N).scale(Math.signum(d2));
        for (double d4 = Math.abs(d2); d4 > 1.0E-6; d4 -= d3) {
            d3 = Math.min(0.1, d4);
            Vec3 vec32 = vec3.scale(d3);
            if (bl && this.b(this.I.add(vec32))) break;
            this.I = this.I.add(vec32);
        }
    }

    @Override
    protected void onDisable() {
        this.j();
        Freecam.finishCameraInteraction();
        this.S = false;
        this.I = Vec3.ZERO;
        this.J = Vec3.ZERO;
        this.K = Vec3.ZERO;
        this.L = Vec3.ZERO;
        this.N = new a();
        this.O = new a();
        this.P = new a();
        if (this.R != null) {
            Freecam.mc.options.setCameraType(this.R);
            this.R = null;
        }
    }

    public static boolean shouldKeepSneaking() {
        return Freecam.k() && (Boolean)Freecam.INSTANCE.H.getValue() != false;
    }

    private static double a(Vec3 vec3, Vec3 vec32) {
        double d2 = vec3.x - vec32.x;
        double d3 = vec3.z - vec32.z;
        return d2 * d2 + d3 * d3;
    }

    public static void updateCrosshairTarget(float f2) {
        Entity entity;
        HitResult hitResult;
        a a2;
        Freecam freecam = INSTANCE;
        if (!Freecam.k() || Freecam.mc.player == null || Freecam.mc.level == null || Freecam.mc.gameMode == null) {
            return;
        }
        if (((Boolean)freecam.playerControl.getValue()).booleanValue() || freecam.B.is("Player")) {
            a2 = new a(Freecam.mc.player.getYRot(), Freecam.mc.player.getXRot());
            hitResult = freecam.a(Freecam.mc.player.getEyePosition(f2), a2);
        } else {
            float f3 = Mth.clamp((float)f2, (float)0.0f, (float)1.0f);
            Vec3 vec3 = freecam.J.lerp(freecam.I, (double)f3);
            a a3 = new a(Freecam.a(f3, freecam.O.e(), freecam.N.e()), Mth.lerp((float)f3, (float)freecam.O.f(), (float)freecam.N.f()));
            hitResult = freecam.a(vec3, a3, Freecam.mc.gameMode.getPickRange(), true);
        }
        if (hitResult == null) {
            return;
        }
        Freecam.mc.hitResult = hitResult;
        if (hitResult instanceof EntityHitResult) {
            a2 = (EntityHitResult)hitResult;
            entity = a2.getEntity();
        } else {
            entity = null;
        }
        Freecam.mc.crosshairPickEntity = entity;
    }

    @EventTarget
    public void a(e e2) {
        this.S = false;
        this.setEnabled(false);
    }

    public static boolean shouldUseFullBrightness() {
        return Freecam.k() && (Boolean)Freecam.INSTANCE.fullBrightness.getValue() != false;
    }

    public static void applyCameraTransform(Camera camera, Entity entity, float f2) {
        Freecam freecam = INSTANCE;
        if (!Freecam.k() || !freecam.S || entity != Freecam.mc.player) {
            return;
        }
        float f3 = Mth.clamp((float)f2, (float)0.0f, (float)1.0f);
        Vec3 vec3 = freecam.J.lerp(freecam.I, (double)f3);
        float f4 = Freecam.a(f3, freecam.O.e(), freecam.N.e());
        float f5 = Mth.lerp((float)f3, (float)freecam.O.f(), (float)freecam.N.f());
        ReflectionUtil.a(camera, vec3, f4, f5);
    }

    public static void prepareCameraInteraction() {
        HitResult hitResult;
        a a2;
        Freecam freecam = INSTANCE;
        if (!Freecam.k() || !((Boolean)freecam.A.getValue()).booleanValue() || Freecam.mc.player == null || Freecam.mc.level == null || Freecam.mc.gameMode == null) {
            return;
        }
        if (freecam.T != null) {
            return;
        }
        freecam.T = Freecam.mc.hitResult;
        freecam.U = Freecam.mc.crosshairPickEntity;
        if (((Boolean)freecam.playerControl.getValue()).booleanValue() || freecam.B.is("Player")) {
            a2 = new a(Freecam.mc.player.getYRot(), Freecam.mc.player.getXRot());
            hitResult = freecam.a(Freecam.mc.player.getEyePosition(1.0f), a2);
        } else {
            hitResult = freecam.a(freecam.I, freecam.N, Freecam.mc.gameMode.getPickRange(), true);
        }
        if (hitResult != null) {
            Entity entity;
            Freecam.mc.hitResult = hitResult;
            if (hitResult instanceof EntityHitResult) {
                a2 = (EntityHitResult)hitResult;
                entity = a2.getEntity();
            } else {
                entity = null;
            }
            Freecam.mc.crosshairPickEntity = entity;
        }
    }

    private static boolean k() {
        return INSTANCE != null && INSTANCE.isEnabled() && Freecam.INSTANCE.S;
    }

    @EventTarget(a=4)
    public void a(PacketEvent packetEvent) {
        if (packetEvent.a() && packetEvent.c() instanceof ClientboundPlayerPositionPacket && ((Boolean)this.C.getValue()).booleanValue()) {
            this.setEnabled(false);
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        Vec3 vec3;
        LocalPlayer localPlayer = Freecam.mc.player;
        if (localPlayer == null || Freecam.mc.level == null) {
            this.S = false;
            this.setEnabled(false);
            return;
        }
        if (((Boolean)this.cancelOnDamage.getValue()).booleanValue() && !localPlayer.isCreative() && localPlayer.getHealth() < this.Q) {
            this.setEnabled(false);
            return;
        }
        if (((Boolean)this.E.getValue()).booleanValue() && localPlayer.isInWaterOrBubble()) {
            this.setEnabled(false);
            return;
        }
        Vec3 vec32 = localPlayer.position();
        if (((Boolean)this.D.getValue()).booleanValue() && !((Boolean)this.playerControl.getValue()).booleanValue() && Freecam.a(vec32, this.M) > 1.0E-10) {
            this.setEnabled(false);
            return;
        }
        if (((Boolean)this.freezePlayer.getValue()).booleanValue() && !((Boolean)this.playerControl.getValue()).booleanValue()) {
            localPlayer.setDeltaMovement(Vec3.ZERO);
            localPlayer.absMoveTo(this.L.x, this.L.y, this.L.z, localPlayer.getYRot(), localPlayer.getXRot());
            vec32 = this.L;
        } else {
            this.L = vec32;
        }
        this.P = new a(localPlayer.getYRot(), localPlayer.getXRot());
        this.Q = localPlayer.getHealth();
        this.M = vec32;
        if (((Boolean)this.renderBody.getValue()).booleanValue()) {
            if (this.V == null) {
                this.i();
            }
            this.a(localPlayer);
        } else if (this.V != null) {
            this.j();
        }
        if (Freecam.mc.options.getCameraType() != CameraType.VANILLA_NAME) {
            Freecam.mc.options.setCameraType(CameraType.VANILLA_NAME);
        }
        this.J = this.I;
        this.O = this.N.a();
        if (((Boolean)this.playerControl.getValue()).booleanValue()) {
            this.K = Vec3.ZERO;
            return;
        }
        if (((Boolean)this.F.getValue()).booleanValue() && InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)341) && (vec3 = this.a(this.I, this.N, 200.0, false)) != null && vec3.getType() != HitResult.Type.MISS) {
            Vec3 vec33 = vec3.getLocation().subtract(this.I);
            if (vec33.lengthSqr() > 0.0025) {
                this.a(vec33.normalize().scale(Math.min(((Number)this.horizontalSpeed.getValue()).doubleValue(), vec33.length())));
            }
            return;
        }
        vec3 = this.h();
        if (this.flightMode.is("Creative")) {
            double d2 = vec3.lengthSqr() > 1.0E-8 ? 0.45 : 0.25;
            this.K = this.K.lerp(vec3, d2);
            if (vec3.lengthSqr() <= 1.0E-8 && this.K.lengthSqr() < 1.0E-6) {
                this.K = Vec3.ZERO;
            }
        } else {
            this.K = vec3;
        }
        this.a(this.K);
    }

    @Override
    protected void onEnable() {
        LocalPlayer localPlayer = Freecam.mc.player;
        if (localPlayer == null || Freecam.mc.level == null) {
            this.setEnabled(false);
            return;
        }
        this.J = this.I = localPlayer.getEyePosition(1.0f);
        this.K = Vec3.ZERO;
        this.N = new a(localPlayer.getYRot(), localPlayer.getXRot());
        this.O = this.N.a();
        this.P = this.N.a();
        this.M = this.L = localPlayer.position();
        this.Q = localPlayer.getHealth();
        this.R = Freecam.mc.options.getCameraType();
        Freecam.mc.options.setCameraType(CameraType.VANILLA_NAME);
        this.S = true;
        this.a();
        this.J = this.I;
        this.O = this.N.a();
        if (((Boolean)this.renderBody.getValue()).booleanValue()) {
            this.i();
        }
    }

    public Freecam() {
        String[] stringArray = W;
        super("Freecam", Category.RENDER);
        this.flightMode = new ModeSetting("Flight Mode", "Default", "Creative").withDefault("Default");
        this.horizontalSpeed = new NumberSetting("Horizontal Speed", 1.0, 0.1, 10.0, 0.1);
        this.verticalSpeed = new NumberSetting("Vertical Speed", 1.0, 0.1, 10.0, 0.1);
        this.ignoreTransparentBlocks = new BooleanSetting("Ignore Transparent Blocks", true);
        this.ignoreOpenableBlocks = new BooleanSetting("Ignore Openable Blocks", true);
        this.ignoreAllCollision = new BooleanSetting("Ignore All Collision", true);
        this.alwaysCheckInitialCollision = new BooleanSetting("Always Check Initial Collision", false);
        this.initialPerspective = new ModeSetting("Initial Perspective", "Inside", "First Person", "Third Person", "Third Person Mirror").withDefault("Inside");
        this.renderBody = new BooleanSetting("Render Body", true);
        this.showHand = new BooleanSetting("Show Hand", false);
        this.fullBrightness = new BooleanSetting("Full Brightness", false);
        this.showSubmersionFog = new BooleanSetting("Show Submersion Fog", false);
        this.cancelOnDamage = new BooleanSetting("Cancel On Damage", true);
        this.freezePlayer = new BooleanSetting("Freeze Player", false);
        this.playerControl = new BooleanSetting("Player Control", false);
        this.A = new BooleanSetting("Allow Interaction", false);
        this.B = new ModeSetting("Interaction Mode", "Camera", "Player").withDefault("Camera");
        this.C = new BooleanSetting("Cancel On Teleport", false);
        this.D = new BooleanSetting("Cancel On Move", false);
        this.E = new BooleanSetting("Cancel On Liquid", false);
        this.F = new BooleanSetting("Navigation", false);
        this.G = new BooleanSetting("Mid Click Camera Teleport", false);
        this.H = new BooleanSetting("Keep Sneaking", false);
        this.I = Vec3.ZERO;
        this.J = Vec3.ZERO;
        this.K = Vec3.ZERO;
        this.L = Vec3.ZERO;
        this.M = Vec3.ZERO;
        this.N = new a();
        this.O = new a();
        this.P = new a();
        INSTANCE = this;
    }

    private void i() {
        this.j();
        if (Freecam.mc.player == null || Freecam.mc.level == null) {
            return;
        }
        this.V = new m((AbstractClientPlayer)Freecam.mc.player);
        this.a(Freecam.mc.player);
        Freecam.mc.level.addPlayer(-7319, (AbstractClientPlayer)this.V);
    }

    public static boolean shouldRenderHand() {
        return !Freecam.k() || (Boolean)Freecam.INSTANCE.showHand.getValue() != false;
    }

    public static boolean shouldBlockInteraction() {
        return Freecam.k() && (Boolean)Freecam.INSTANCE.playerControl.getValue() == false && (Boolean)Freecam.INSTANCE.A.getValue() == false;
    }

    private Vec3 h() {
        float f2;
        float f3 = (Freecam.mc.options.keyUp.isDown() ? 1.0f : 0.0f) - (Freecam.mc.options.keyDown.isDown() ? 1.0f : 0.0f);
        double d2 = Math.hypot(f3, f2 = (Freecam.mc.options.keyLeft.isDown() ? 1.0f : 0.0f) - (Freecam.mc.options.keyRight.isDown() ? 1.0f : 0.0f));
        if (d2 > 1.0) {
            f3 /= (float)d2;
            f2 /= (float)d2;
        }
        double d3 = Freecam.mc.options.keySprint.isDown() ? (this.flightMode.is("Creative") ? 2.0 : 1.5) : 1.0;
        double d4 = ((Number)this.horizontalSpeed.getValue()).doubleValue() * d3;
        double d5 = ((Number)this.verticalSpeed.getValue()).doubleValue();
        double d6 = Math.toRadians(this.N.e());
        double d7 = (-Math.sin(d6) * (double)f3 + Math.cos(d6) * (double)f2) * d4;
        double d8 = (Math.cos(d6) * (double)f3 + Math.sin(d6) * (double)f2) * d4;
        double d9 = (Freecam.mc.options.keyJump.isDown() ? d5 : 0.0) - (Freecam.mc.options.keyShift.isDown() ? d5 : 0.0);
        return new Vec3(d7, d9, d8);
    }

    private boolean b(Vec3 vec3) {
        if (Freecam.mc.level == null) {
            return false;
        }
        AABB aABB = new AABB(vec3.x - 0.3, vec3.y - 0.3, vec3.z - 0.3, vec3.x + 0.3, vec3.y + 0.3, vec3.z + 0.3);
        BlockPos blockPos = BlockPos.containing((double)aABB.minX, (double)aABB.minY, (double)aABB.minZ);
        BlockPos blockPos2 = BlockPos.containing((double)aABB.maxX, (double)aABB.maxY, (double)aABB.maxZ);
        for (BlockPos blockPos3 : BlockPos.betweenClosed((BlockPos)blockPos, (BlockPos)blockPos2)) {
            VoxelShape voxelShape;
            BlockState blockState = Freecam.mc.level.getBlockState(blockPos3);
            if (blockState.isAir() || this.a(blockState.getBlock()) || (voxelShape = blockState.getCollisionShape((BlockGetter)Freecam.mc.level, blockPos3)).isEmpty()) continue;
            for (AABB aABB2 : voxelShape.toAabbs()) {
                if (!aABB2.move(blockPos3).intersects(aABB)) continue;
                return true;
            }
        }
        return false;
    }

    private static float a(float f2, float f3, float f4) {
        return f3 + Mth.wrapDegrees((float)(f4 - f3)) * f2;
    }

    public static boolean shouldPrepareInteraction() {
        return Freecam.k() && (Boolean)Freecam.INSTANCE.playerControl.getValue() == false && (Boolean)Freecam.INSTANCE.A.getValue() != false;
    }

    private void a(Vec3 vec3) {
        Vec3 vec32;
        Vec3 vec33;
        if (vec3.lengthSqr() <= 1.0E-12) {
            return;
        }
        if (((Boolean)this.ignoreAllCollision.getValue()).booleanValue()) {
            this.I = this.I.add(vec3);
            return;
        }
        Vec3 vec34 = this.I;
        Vec3 vec35 = new Vec3(vec3.x, 0.0, 0.0);
        if (!this.b(vec34.add(vec35))) {
            vec34 = vec34.add(vec35);
        }
        if (!this.b(vec34.add(vec33 = new Vec3(0.0, vec3.y, 0.0)))) {
            vec34 = vec34.add(vec33);
        }
        if (!this.b(vec34.add(vec32 = new Vec3(0.0, 0.0, vec3.z)))) {
            vec34 = vec34.add(vec32);
        }
        this.I = vec34;
    }

    private boolean a(Block block) {
        if (((Boolean)this.ignoreTransparentBlocks.getValue()).booleanValue() && (block instanceof AbstractGlassBlock || block instanceof IronBarsBlock || block instanceof BarrierBlock)) {
            return true;
        }
        return (Boolean)this.ignoreOpenableBlocks.getValue() != false && (block instanceof FenceGateBlock || block instanceof DoorBlock || block instanceof TrapDoorBlock);
    }

    public static void finishCameraInteraction() {
        Freecam freecam = INSTANCE;
        if (freecam == null || freecam.T == null) {
            return;
        }
        Freecam.mc.hitResult = freecam.T;
        Freecam.mc.crosshairPickEntity = freecam.U;
        freecam.T = null;
        freecam.U = null;
    }

    private void j() {
        if (this.V != null && Freecam.mc.level != null) {
            Freecam.mc.level.removeEntity(this.V.getId(), Entity.RemovalReason.DISCARDED);
        }
        this.V = null;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                Freecam.k = 0.3;
                Freecam.j = -7319;
                var5 = new String[39];
                var3_1 = 0;
                var2_2 = "{<\u0014U\u000fB2F0\u0006a:\bKKo\u001ei8\fCVysk<\u001eAD*\u001aF=\u000fKNfsk;\u0017NFy:G:\u000ek5\u0015AJfsg:[o@|6\u000bn8\u0012EG~se;\u001fG\u0010k5\u0015AJfsg:[nF{&A0\rn&\u001eGUosx8\u001a[Jx\u000fn!\u0017N\u000fH!A3\u0013VAo [\u0006k5\u0016G]k\bk&\u001eC[c%M\u0011i8\u0017MX*\u001aF \u001ePNi'A;\u0015\u0006a:\bKKo\u0013|<\u0012PK*\u0003M&\bMA*\u001eA&\tM]\u000bz1\u0015FJxsj;\u001f[\u000ex8\u001a[Jxsk;\u0015V]e?\u0007l1\u001dCZf'\u0019e=\u001f\u0002lf:K?[aNg6Z5[vJf6X;\tV\u0016a3\u0015M]osg$\u001eLNh?Mt9N@i8[\bk&\u001eC[c%M\u0013|<\u0012PK*\u0003M&\bMA*\u001eA&\tM]\f|<\u0012PK*\u0003M&\bMA\u0006x8\u001a[Jx\u0013{<\u0014U\u000fY&J9\u001eP\\c<Ft=MH\u0007l1\u001dCZf'\u0012k5\u0015AJfsg:[vJf6X;\tV\u0010k5\u0015AJfsg:[fNg2O1\u0014a3\u0015M]osi8\u0017\u0002le?D=\bK@d\fn=\tQ[*\u0003M&\bMA\nf5\rKHk'A;\u0015\u0013a:\u0012VFk?\b\u0004\u001eP\\z6K \u0012TJ\fn=\tQ[*\u0003M&\bMA\u0006k5\u0016G]k\u0010a:\u000fG]k0\\=\u0014L\u000fG<L1\u0019a3\u0015M]os|&\u001aL\\z2Z1\u0015V\u000fH?G7\u0010Q\u0010`;\tKUe=\\5\u0017\u0002|z6M0\u0006x8\u001a[Jx\u000e~1\tVFi2Dt(RJo7";
                var4_3 = "{<\u0014U\u000fB2F0\u0006a:\bKKo\u001ei8\fCVysk<\u001eAD*\u001aF=\u000fKNfsk;\u0017NFy:G:\u000ek5\u0015AJfsg:[o@|6\u000bn8\u0012EG~se;\u001fG\u0010k5\u0015AJfsg:[nF{&A0\rn&\u001eGUosx8\u001a[Jx\u000fn!\u0017N\u000fH!A3\u0013VAo [\u0006k5\u0016G]k\bk&\u001eC[c%M\u0011i8\u0017MX*\u001aF \u001ePNi'A;\u0015\u0006a:\bKKo\u0013|<\u0012PK*\u0003M&\bMA*\u001eA&\tM]\u000bz1\u0015FJxsj;\u001f[\u000ex8\u001a[Jxsk;\u0015V]e?\u0007l1\u001dCZf'\u0019e=\u001f\u0002lf:K?[aNg6Z5[vJf6X;\tV\u0016a3\u0015M]osg$\u001eLNh?Mt9N@i8[\bk&\u001eC[c%M\u0013|<\u0012PK*\u0003M&\bMA*\u001eA&\tM]\f|<\u0012PK*\u0003M&\bMA\u0006x8\u001a[Jx\u0013{<\u0014U\u000fY&J9\u001eP\\c<Ft=MH\u0007l1\u001dCZf'\u0012k5\u0015AJfsg:[vJf6X;\tV\u0010k5\u0015AJfsg:[fNg2O1\u0014a3\u0015M]osi8\u0017\u0002le?D=\bK@d\fn=\tQ[*\u0003M&\bMA\nf5\rKHk'A;\u0015\u0013a:\u0012VFk?\b\u0004\u001eP\\z6K \u0012TJ\fn=\tQ[*\u0003M&\bMA\u0006k5\u0016G]k\u0010a:\u000fG]k0\\=\u0014L\u000fG<L1\u0019a3\u0015M]os|&\u001aL\\z2Z1\u0015V\u000fH?G7\u0010Q\u0010`;\tKUe=\\5\u0017\u0002|z6M0\u0006x8\u001a[Jx\u000e~1\tVFi2Dt(RJo7".length();
                var1_4 = 9;
                var0_5 = -1;
lbl9:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl14:
                // 1 sources

                while (true) {
                    var5[var3_1++] = Freecam.c(Freecam.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "n&\u001eGLk>\rc1\u001eR\u000fY=M5\u0010KAm";
                    var4_3 = "n&\u001eGLk>\rc1\u001eR\u000fY=M5\u0010KAm".length();
                    var1_4 = 7;
                    var0_5 = -1;
lbl23:
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
lbl28:
                // 1 sources

                while (true) {
                    var5[var3_1++] = Freecam.c(Freecam.a(v1));
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
lbl39:
                // 1 sources

                ** continue;
            }
        }
        Freecam.W = var5;
    }

    private void a(LocalPlayer localPlayer) {
        if (this.V == null || localPlayer == null) {
            return;
        }
        this.V.absMoveTo(localPlayer.getX(), localPlayer.getY(), localPlayer.getZ(), localPlayer.getYRot(), localPlayer.getXRot());
        this.V.yRotO = localPlayer.yRotO;
        this.V.xRotO = localPlayer.xRotO;
        this.V.yHeadRot = localPlayer.yHeadRot;
        this.V.yHeadRotO = localPlayer.yHeadRotO;
        this.V.yBodyRot = localPlayer.yBodyRot;
        this.V.yBodyRotO = localPlayer.yBodyRotO;
        this.V.setPose(localPlayer.getPose());
        this.V.setSprinting(localPlayer.isSprinting());
        this.V.setShiftKeyDown(localPlayer.isShiftKeyDown());
        this.V.setHealth(localPlayer.getHealth());
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            this.V.setItemSlot(equipmentSlot, localPlayer.getItemBySlot(equipmentSlot).copy());
        }
    }

    public void onPhysicalMouse(int n2, int n3) {
        if (!(this.isEnabled() && this.S && ((Boolean)this.G.getValue()).booleanValue() && n2 == 2 && n3 == 1)) {
            return;
        }
        HitResult hitResult = this.a(this.I, this.N, 200.0, false);
        if (hitResult != null && hitResult.getType() != HitResult.Type.MISS) {
            this.J = this.I;
            this.I = this.I.lerp(hitResult.getLocation(), 0.9);
        }
    }

    private HitResult a(Vec3 vec3, a a2) {
        EntityHitResult entityHitResult;
        if (Freecam.mc.player == null || Freecam.mc.level == null || Freecam.mc.gameMode == null) {
            return null;
        }
        Vec3 vec32 = g.b(a2);
        double d2 = Freecam.mc.gameMode.getPickRange();
        Vec3 vec33 = vec3.add(vec32.scale(d2));
        BlockHitResult blockHitResult = Freecam.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)Freecam.mc.player));
        double d3 = Freecam.mc.gameMode.hasFarPickRange() ? 6.0 : 3.0;
        Vec3 vec34 = vec3.add(vec32.scale(d3));
        double d4 = d3 * d3;
        if (blockHitResult.getType() != HitResult.Type.MISS) {
            d4 = Math.min(d4, vec3.distanceToSqr(blockHitResult.getLocation()));
        }
        return (entityHitResult = ProjectileUtil.getEntityHitResult((Entity)Freecam.mc.player, (Vec3)vec3, (Vec3)vec34, (AABB)new AABB(vec3, vec34).inflate(1.0), entity -> entity != Freecam.mc.player && entity != this.V && !entity.isSpectator() && entity.isPickable(), (double)d4)) != null ? entityHitResult : blockHitResult;
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
                    case 0 -> 40;
                    case 1 -> 84;
                    case 2 -> 123;
                    case 3 -> 34;
                    case 4 -> 47;
                    case 5 -> 10;
                    default -> 83;
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
            cArray[0] = (char)(cArray[0] ^ 0x53);
        }
        return cArray;
    }
}

