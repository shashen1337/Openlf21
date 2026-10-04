/*
 * Decompiled with CFR 0.152.
 */
package lf21.dll;

import asm.patchify.loader.PatchAgent;
import lf21.LF21Client;
import lf21.asm.Bootstrap;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DllBootstrap {
    private static final Logger LOGGER = LogManager.getLogger(DllBootstrap.class);
    private static volatile boolean started = false;

    private DllBootstrap() {
    }

    public static synchronized void start(String string) {
        if (started) {
            LOGGER.info("bootstrap.start ignored (already started)");
            return;
        }
        started = true;
        try {
            LOGGER.debug("bootstrap jar={} loader={} clientLoader={} minecraftLoader={} inst={}", (Object)string, (Object)DllBootstrap.class.getClassLoader(), (Object)LF21Client.class.getClassLoader(), (Object)Minecraft.class.getClassLoader(), (Object)PatchAgent.getInstrumentation());
            Bootstrap.init();
            LF21Client.registerPatches();
            PatchAgent.installPatchesAndRetransform();
            LOGGER.debug("bootstrap done");
        }
        catch (Throwable throwable) {
            LOGGER.error("bootstrap.start failed", throwable);
        }
    }
}

