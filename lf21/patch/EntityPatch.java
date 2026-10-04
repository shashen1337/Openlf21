/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Overwrite;
import asm.patchify.annotation.Patch;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.impl.PreTickEvent;
import lf21.event.impl.RayTraceEvent;
import lf21.event.impl.RotationEvent;
import lf21.event.impl.SneakEvent;
import lf21.event.impl.StuckInBlockEvent;
import lf21.modules.impl.combat.TeleportAura;
import lf21.modules.impl.misc.MurderDetector;
import lf21.modules.impl.render.ESP;
import lf21.patch.CallbackInfo;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

@Patch(value=Entity.class)
public class EntityPatch {
    @Inject(method="getTeamColor", desc="()I", at=@At(value=At.Type.TAIL))
    public static void onGetTeamColor(Entity entity, CallbackInfo callbackInfo) {
        if (!LF21Client.isReady()) {
            return;
        }
        if (TeleportAura.INSTANCE != null && TeleportAura.INSTANCE.isLockedTarget(entity)) {
            callbackInfo.result = 0xFF3737;
        } else if (LF21Client.getInstance().getLookForManager() != null && LF21Client.getInstance().getLookForManager().isTarget(entity)) {
            callbackInfo.result = 0x46FF64;
        } else if (MurderDetector.isMurderer(entity)) {
            callbackInfo.result = 0xFF3737;
        } else if (ESP.INSTANCE != null && ESP.INSTANCE.usesModelOutline(entity)) {
            callbackInfo.result = ESP.getEspColor(entity).getRGB() & 0xFFFFFF;
        }
    }

    @Inject(method="makeStuckInBlock", desc="(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/phys/Vec3;)V", at=@At(value=At.Type.TAIL))
    public static void onMakeStuckInBlock(Entity entity, BlockState blockState, Vec3 vec3, CallbackInfo callbackInfo) {
        if (ClientBase.mc.player != entity) {
            return;
        }
        StuckInBlockEvent stuckInBlockEvent = new StuckInBlockEvent(blockState, vec3);
        LF21Client.getInstance().getEventBus().call(stuckInBlockEvent);
        if (stuckInBlockEvent.isCancelled()) {
            ReflectionUtil.setInstanceField(entity, Vec3.ZERO, "stuckSpeedMultiplier", "net/minecraft/world/entity/Entity");
            return;
        }
        ReflectionUtil.setInstanceField(entity, stuckInBlockEvent.getMotion(), "stuckSpeedMultiplier", "net/minecraft/world/entity/Entity");
    }

    @Inject(method="push", desc="(Lnet/minecraft/world/entity/Entity;)V", at=@At(value=At.Type.HEAD))
    public static void onPush(Entity entity, CallbackInfo callbackInfo) {
        if (!LF21Client.isReady() || entity != ClientBase.mc.player || entity.isInWater()) {
            return;
        }
        SneakEvent sneakEvent = new SneakEvent();
        LF21Client.getInstance().getEventBus().call(sneakEvent);
        if (sneakEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Overwrite(method="moveRelative", desc="(FLnet/minecraft/world/phys/Vec3;)V")
    public static void overwriteMoveRelative(Entity entity, float f2, Vec3 vec3) throws Exception {
        RotationEvent rotationEvent = new RotationEvent(entity.getYRot(), f2);
        if (LF21Client.isReady() && entity == ClientBase.mc.player) {
            LF21Client.getInstance().getEventBus().call(rotationEvent);
        }
        float f3 = rotationEvent.getYaw();
        Vec3 vec32 = EntityPatch.applyRotation(vec3, f2, f3);
        entity.setDeltaMovement(entity.getDeltaMovement().add(vec32));
        if (LF21Client.isReady() && entity == ClientBase.mc.player) {
            LF21Client.getInstance().getEventBus().call(new PreTickEvent());
        }
    }

    @Overwrite(method="calculateViewVector", desc="(FF)Lnet/minecraft/world/phys/Vec3;")
    public static Vec3 overwriteCalculateViewVector(Entity entity, float f2, float f3) throws Exception {
        RayTraceEvent rayTraceEvent = new RayTraceEvent(entity, f3, f2);
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(rayTraceEvent);
        }
        f3 = rayTraceEvent.getYaw();
        f2 = rayTraceEvent.getPitch();
        float f4 = f2 * ((float)Math.PI / 180);
        float f5 = -f3 * ((float)Math.PI / 180);
        float f6 = Mth.cos((float)f5);
        float f7 = Mth.sin((float)f5);
        float f8 = Mth.cos((float)f4);
        float f9 = Mth.sin((float)f4);
        return new Vec3((double)(f7 * f8), (double)(-f9), (double)(f6 * f8));
    }

    public static Vec3 applyRotation(Vec3 vec3, float f2, float f3) {
        double d2 = vec3.lengthSqr();
        if (d2 < 1.0E-7) {
            return Vec3.ZERO;
        }
        Vec3 vec32 = (d2 > 1.0 ? vec3.normalize() : vec3).scale((double)f2);
        float f4 = Mth.sin((float)(f3 * ((float)Math.PI / 180)));
        float f5 = Mth.cos((float)(f3 * ((float)Math.PI / 180)));
        return new Vec3(vec32.x * (double)f5 - vec32.z * (double)f4, vec32.y, vec32.z * (double)f5 + vec32.x * (double)f4);
    }
}

