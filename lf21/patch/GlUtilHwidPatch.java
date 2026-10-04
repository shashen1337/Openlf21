/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import com.mojang.blaze3d.platform.GlUtil;
import lf21.hwid.HwidProfileBridge;
import lf21.patch.CallbackInfo;

@Patch(value=GlUtil.class)
public final class GlUtilHwidPatch {
    private GlUtilHwidPatch() {
    }

    @Inject(method="getCpuInfo", desc="()Ljava/lang/String;", at=@At(value=At.Type.HEAD))
    public static void getCpuInfo(CallbackInfo callbackInfo) {
        GlUtilHwidPatch.replace(callbackInfo, HwidProfileBridge.cpuDisplay());
    }

    @Inject(method="getRenderer", desc="()Ljava/lang/String;", at=@At(value=At.Type.HEAD))
    public static void getRenderer(CallbackInfo callbackInfo) {
        GlUtilHwidPatch.replace(callbackInfo, HwidProfileBridge.renderer());
    }

    @Inject(method="getVendor", desc="()Ljava/lang/String;", at=@At(value=At.Type.HEAD))
    public static void getVendor(CallbackInfo callbackInfo) {
        GlUtilHwidPatch.replace(callbackInfo, HwidProfileBridge.graphicsVendor());
    }

    private static void replace(CallbackInfo callbackInfo, String string) {
        if (string == null || string.isEmpty()) {
            return;
        }
        callbackInfo.result = string;
        callbackInfo.cancel();
    }
}

