/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.loader;

import asm.patchify.annotation.Patch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PatchRegistry {
    private static final Logger LOGGER = LogManager.getLogger(PatchRegistry.class);
    private static final List<Class<?>> PATCHES = new ArrayList();

    private PatchRegistry() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void register(Class<?> clazz) {
        Patch patch = clazz.getAnnotation(Patch.class);
        if (patch == null) {
            throw new IllegalArgumentException(clazz.getName() + " is missing @Patch");
        }
        List<Class<?>> list = PATCHES;
        synchronized (list) {
            if (!PATCHES.contains(clazz)) {
                PATCHES.add(clazz);
                LOGGER.debug("Registered patch {} -> {}", (Object)clazz.getName(), (Object)patch.value().getName());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static List<Class<?>> getPatches() {
        List<Class<?>> list = PATCHES;
        synchronized (list) {
            return Collections.unmodifiableList(new ArrayList(PATCHES));
        }
    }
}

