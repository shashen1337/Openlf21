/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Overwrite;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.WrapInvoke;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import lf21.LF21Client;
import lf21.asm.Invocation;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.WorldOverlay2DEvent;
import lf21.modules.impl.combat.LegitReach;
import lf21.modules.impl.render.AspectRatio;
import lf21.modules.impl.render.Freecam;
import lf21.modules.impl.render.FullBright;
import lf21.modules.impl.render.NoFov;
import lf21.modules.impl.render.NoHurtCam;
import lf21.patch.CallbackInfo;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import lf21.utils.misc.ReflectionUtil;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.Camera;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

@Patch(value=GameRenderer.class)
public class GameRendererPatch {
    @Inject(method="pick", desc="(F)V", at=@At(value=At.Type.TAIL))
    public static void onPick(GameRenderer gameRenderer, float f2, CallbackInfo callbackInfo) {
        if (LF21Client.isReady() && Freecam.INSTANCE != null && Freecam.INSTANCE.isEnabled()) {
            Freecam.updateCrosshairTarget(f2);
            return;
        }
        if (LF21Client.isReady() && LegitReach.INSTANCE != null && LegitReach.INSTANCE.isEnabled()) {
            LegitReach.INSTANCE.updateCrosshairTarget(f2);
        }
    }

    @Overwrite(method="getNightVisionScale", desc="(Lnet/minecraft/world/entity/LivingEntity;F)F")
    public static float overwriteGetNightVisionScale(LivingEntity livingEntity, float f2) {
        if (Freecam.shouldUseFullBrightness()) {
            return 1.0f;
        }
        if (FullBright.INSTANCE != null && FullBright.INSTANCE.isEnabled()) {
            return ((Number)FullBright.INSTANCE.brightnessSetting.getValue()).floatValue() / 100.0f;
        }
        return livingEntity.hasEffect(MobEffects.NIGHT_VISION) ? 1.0f : 0.0f;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Inject(method="render", desc="(FJZ)V", at=@At(value=At.Type.AFTER_INVOKE, method="net/minecraft/client/gui/Gui/render", desc="(Lnet/minecraft/client/gui/GuiGraphics;F)V"))
    public static void onRender(GameRenderer gameRenderer, float f2, long l2, boolean bl, CallbackInfo callbackInfo) {
        GuiGraphics guiGraphics = new GuiGraphics(gameRenderer.getMinecraft(), gameRenderer.getMinecraft().renderBuffers().bufferSource());
        Render2DEvent render2DEvent = new Render2DEvent(guiGraphics.pose(), guiGraphics, f2);
        if (LF21Client.isReady()) {
            RenderUtil.beginBlurFrame(l2);
            try {
                LF21Client.getInstance().getEventBus().call(render2DEvent);
                guiGraphics.pose().pushPose();
                Renderer.render(guiGraphics, drawContext -> {
                    GlRenderEvent glRenderEvent = new GlRenderEvent(guiGraphics, guiGraphics.pose(), (DrawContext)drawContext);
                    LF21Client.getInstance().getEventBus().call(glRenderEvent);
                });
                guiGraphics.pose().popPose();
            }
            finally {
                RenderUtil.endBlurFrame();
            }
        }
    }

    @WrapInvoke(method="getProjectionMatrix", desc="(D)Lorg/joml/Matrix4f;", target="org/joml/Matrix4f/setPerspective", targetDesc="(FFFF)Lorg/joml/Matrix4f;")
    public static Matrix4f onGetProjectionMatrix(GameRenderer gameRenderer, double d2, Invocation<GameRenderer, Matrix4f> invocation) throws Exception {
        if (!LF21Client.isReady() || AspectRatio.INSTANCE == null || !AspectRatio.INSTANCE.isEnabled()) {
            return (Matrix4f)invocation.call();
        }
        PoseStack poseStack = new PoseStack();
        poseStack.last().pose().identity();
        float f2 = ((Float)ReflectionUtil.getStaticField(gameRenderer, "zoom", "net/minecraft/client/renderer/GameRenderer")).floatValue();
        float f3 = ((Float)ReflectionUtil.getStaticField(gameRenderer, "zoomX", "net/minecraft/client/renderer/GameRenderer")).floatValue();
        float f4 = ((Float)ReflectionUtil.getStaticField(gameRenderer, "zoomY", "net/minecraft/client/renderer/GameRenderer")).floatValue();
        if (f2 != 1.0f) {
            poseStack.translate(f3, -f4, 0.0f);
            poseStack.scale(f2, f2, 1.0f);
        }
        poseStack.last().pose().mul((Matrix4fc)new Matrix4f().setPerspective((float)(d2 * 0.01745329238474369), ((Number)AspectRatio.INSTANCE.ratioSetting.getValue()).floatValue(), 0.05f, gameRenderer.getDepthFar()));
        return poseStack.last().pose();
    }

    @Inject(method="bobHurt", desc="(Lcom/mojang/blaze3d/vertex/PoseStack;F)V", at=@At(value=At.Type.HEAD))
    public static void onBobHurt(GameRenderer gameRenderer, PoseStack poseStack, float f2, CallbackInfo callbackInfo) {
        if (LF21Client.isReady() && NoHurtCam.INSTANCE != null && NoHurtCam.INSTANCE.isEnabled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method="getFov", desc="(Lnet/minecraft/client/Camera;FZ)D", at=@At(value=At.Type.TAIL))
    public static void onGetFov(GameRenderer gameRenderer, Camera camera, float f2, boolean bl, CallbackInfo callbackInfo) {
        if (!(bl && LF21Client.isReady() && NoFov.INSTANCE != null && NoFov.INSTANCE.isEnabled())) {
            return;
        }
        if (gameRenderer.getMinecraft().screen == null && InputConstants.isKeyDown((long)gameRenderer.getMinecraft().getWindow().getWindow(), (int)67)) {
            return;
        }
        double d2 = ((Number)callbackInfo.result).doubleValue();
        double d3 = ((Integer)gameRenderer.getMinecraft().options.fov().get()).intValue();
        if (NoFov.INSTANCE.isConstant()) {
            callbackInfo.result = ((Number)NoFov.INSTANCE.fovSetting.getValue()).doubleValue();
            return;
        }
        double d4 = d2 / Math.max(1.0, d3);
        double d5 = ((Number)NoFov.INSTANCE.multiplierSetting.getValue()).doubleValue();
        double d6 = 1.0 + (d4 - 1.0) * d5;
        callbackInfo.result = d3 * Math.max(0.1, d6);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Inject(method="render", desc="(FJZ)V", at=@At(value=At.Type.BEFORE_INVOKE, method="net/minecraft/client/gui/Gui/render", desc="(Lnet/minecraft/client/gui/GuiGraphics;F)V"))
    public static void onRenderWorldOverlays(GameRenderer gameRenderer, float f2, long l2, boolean bl, CallbackInfo callbackInfo) {
        if (!LF21Client.isReady()) {
            return;
        }
        GuiGraphics guiGraphics = new GuiGraphics(gameRenderer.getMinecraft(), gameRenderer.getMinecraft().renderBuffers().bufferSource());
        RenderUtil.beginBlurFrame(l2);
        try {
            LF21Client.getInstance().getEventBus().call(new WorldOverlay2DEvent(guiGraphics.pose(), guiGraphics, f2));
        }
        finally {
            guiGraphics.flush();
            RenderUtil.endBlurFrame();
        }
    }
}

