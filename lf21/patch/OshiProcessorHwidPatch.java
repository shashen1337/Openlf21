/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import lf21.hwid.HwidProfileBridge;
import lf21.patch.CallbackInfo;

public final class OshiProcessorHwidPatch {
    private OshiProcessorHwidPatch() {
    }

    @Inject(method="getName", desc="()Ljava/lang/String;", at=@At(value=At.Type.HEAD))
    public static void getName(Object object, CallbackInfo callbackInfo) {
        OshiProcessorHwidPatch.replace(callbackInfo, HwidProfileBridge.cpuName());
    }

    @Inject(method="getVendor", desc="()Ljava/lang/String;", at=@At(value=At.Type.HEAD))
    public static void getVendor(Object object, CallbackInfo callbackInfo) {
        OshiProcessorHwidPatch.replace(callbackInfo, HwidProfileBridge.cpuVendor());
    }

    @Inject(method="getProcessorID", desc="()Ljava/lang/String;", at=@At(value=At.Type.HEAD))
    public static void getProcessorId(Object object, CallbackInfo callbackInfo) {
        OshiProcessorHwidPatch.replace(callbackInfo, HwidProfileBridge.processorId());
    }

    private static void replace(CallbackInfo callbackInfo, String string) {
        if (string == null || string.isEmpty()) {
            return;
        }
        callbackInfo.result = string;
        callbackInfo.cancel();
    }
}

