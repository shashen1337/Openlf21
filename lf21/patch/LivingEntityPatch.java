/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Overwrite;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.WrapInvoke;
import java.util.Map;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.asm.Invocation;
import lf21.event.impl.EntityHurtEvent;
import lf21.event.impl.FallFlyingEvent;
import lf21.event.impl.JumpEvent;
import lf21.event.impl.JumpMarkerEvent;
import lf21.event.impl.RotationAnimationEvent;
import lf21.modules.impl.movement.NoDelay;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.render.Freecam;
import lf21.modules.impl.render.FullBright;
import lf21.modules.impl.render.NoBlinded;
import lf21.patch.CallbackInfo;
import lf21.utils.game.PlayerUtil;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

@Patch(value=LivingEntity.class)
public class LivingEntityPatch {
    @Inject(method="aiStep", desc="()V", at=@At(value=At.Type.HEAD))
    public static void onAiStep(LivingEntity livingEntity, CallbackInfo callbackInfo) {
        if (LivingEntityPatch.shouldFastDig(livingEntity)) {
            ReflectionUtil.setJumpDelay(livingEntity, 0);
        }
    }

    private static boolean shouldFastDig(LivingEntity livingEntity) {
        if (!LF21Client.isReady() || livingEntity != ClientBase.mc.player) {
            return false;
        }
        NoDelay noDelay = NoDelay.INSTANCE;
        if (noDelay == null || !noDelay.isEnabled() || !((Boolean)noDelay.fastDig.getValue()).booleanValue()) {
            return false;
        }
        return Scaffold.INSTANCE == null || !Scaffold.INSTANCE.isEnabled();
    }

    @Overwrite(method="hasEffect", desc="(Lnet/minecraft/world/effect/MobEffect;)Z")
    public static boolean overwriteHasEffect(LivingEntity livingEntity, MobEffect mobEffect) throws Exception {
        if (ClientBase.mc != null && livingEntity == ClientBase.mc.player && mobEffect == MobEffects.BLINDNESS && NoBlinded.INSTANCE != null && NoBlinded.INSTANCE.isEnabled()) {
            return false;
        }
        if (ClientBase.mc != null && livingEntity == ClientBase.mc.player && mobEffect == MobEffects.NIGHT_VISION && (Freecam.shouldUseFullBrightness() || FullBright.INSTANCE != null && FullBright.INSTANCE.isEnabled())) {
            return true;
        }
        Map map = (Map)ReflectionUtil.getStaticField(livingEntity, "activeEffects", "net/minecraft/world/entity/LivingEntity");
        return map.containsKey(mobEffect);
    }

    @WrapInvoke(method="tickHeadTurn", desc="(FF)F", target="net/minecraft/world/entity/Entity/getYRot", targetDesc="()F")
    public static float onTickHeadTurn(LivingEntity livingEntity, float f2, float f3, Invocation<LivingEntity, Float> invocation) throws Exception {
        float f4 = ((Float)invocation.call()).floatValue();
        RotationAnimationEvent rotationAnimationEvent = new RotationAnimationEvent(f4, 0.0f, 0.0f, 0.0f);
        if (LF21Client.isReady() && livingEntity == ClientBase.mc.player) {
            LF21Client.getInstance().getEventBus().call(rotationAnimationEvent);
        }
        return rotationAnimationEvent.getYaw();
    }

    @WrapInvoke(method="tick", desc="()V", target="net/minecraft/world/entity/Entity/getYRot", targetDesc="()F")
    public static float onTickGetYRot(LivingEntity livingEntity, Invocation<LivingEntity, Float> invocation) throws Exception {
        return ClientBase.yaw;
    }

    @WrapInvoke(method="jumpFromGround", desc="()V", target="net/minecraft/world/entity/Entity/getYRot", targetDesc="()F")
    public static float onJumpGetYRot(LivingEntity livingEntity, Invocation<LivingEntity, Float> invocation) throws Exception {
        float f2 = ((Float)invocation.call()).floatValue();
        JumpMarkerEvent jumpMarkerEvent = new JumpMarkerEvent(f2);
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(jumpMarkerEvent);
        }
        ClientBase.yaw = jumpMarkerEvent.getYaw();
        return jumpMarkerEvent.getYaw();
    }

    @Inject(method="travel", desc="(Lnet/minecraft/world/phys/Vec3;)V", at=@At(value=At.Type.HEAD))
    public static void onTravel(LivingEntity livingEntity, Vec3 vec3, CallbackInfo callbackInfo) throws Exception {
        if (livingEntity == null || livingEntity != ClientBase.mc.player || !LF21Client.isReady()) {
            return;
        }
        JumpEvent jumpEvent = new JumpEvent();
        LF21Client.getInstance().getEventBus().call(jumpEvent);
        if (jumpEvent.isCancelled()) {
            PlayerUtil.updateWalkAnim();
        }
        callbackInfo.cancelled = jumpEvent.isCancelled();
    }

    @WrapInvoke(method="travel", desc="(Lnet/minecraft/world/phys/Vec3;)V", target="net/minecraft/world/entity/Entity/getXRot", targetDesc="()F")
    public static float onTravelGetXRot(LivingEntity livingEntity, Vec3 vec3, Invocation<LivingEntity, Float> invocation) throws Exception {
        float f2 = ((Float)invocation.call()).floatValue();
        FallFlyingEvent fallFlyingEvent = new FallFlyingEvent(f2);
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(fallFlyingEvent);
        }
        return fallFlyingEvent.getPitch();
    }

    @Inject(method="hurt", desc="(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at=@At(value=At.Type.HEAD))
    public static void onHurt(LivingEntity livingEntity, DamageSource damageSource, float f2, CallbackInfo callbackInfo) {
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(new EntityHurtEvent(livingEntity, damageSource, f2));
        }
    }
}

