/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.LF21Client;
import lf21.event.impl.KeyEvent;
import lf21.patch.CallbackInfo;
import net.minecraft.client.KeyboardHandler;

@Patch(value=KeyboardHandler.class)
public class KeyboardHandlerPatch {
    @Inject(method="keyPress", desc="(JIIII)V")
    public static void onKeyPress(KeyboardHandler keyboardHandler, long l2, int n2, int n3, int n4, int n5, CallbackInfo callbackInfo) {
        if (keyboardHandler == null || !LF21Client.isReady()) {
            return;
        }
        KeyEvent keyEvent = new KeyEvent(n2, n4 != 0);
        LF21Client.getInstance().getEventBus().call(keyEvent);
        if (keyEvent.isCancelled()) {
            callbackInfo.cancelled = true;
        }
    }
}

