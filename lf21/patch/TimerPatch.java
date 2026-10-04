/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.Overwrite;
import asm.patchify.annotation.Patch;
import lf21.LF21Client;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.client.Timer;

@Patch(value=Timer.class)
public class TimerPatch {
    @Overwrite(method="advanceTime", desc="(J)I")
    public static int overwriteAdvanceTime(Timer timer, long l2) {
        long l3 = (Long)ReflectionUtil.getStaticField(timer, "lastMs", "net/minecraft/client/Timer");
        float f2 = ((Float)ReflectionUtil.getStaticField(timer, "msPerTick", "net/minecraft/client/Timer")).floatValue();
        timer.tickDelta = LF21Client.serverTickRate == 1.0f ? (float)(l2 - l3) / f2 : (float)(l2 - l3) / f2 * LF21Client.serverTickRate;
        ReflectionUtil.setInstanceField(timer, l2, "lastMs", "net/minecraft/client/Timer");
        timer.partialTick += timer.tickDelta;
        int n2 = (int)timer.partialTick;
        timer.partialTick -= (float)n2;
        return n2;
    }
}

