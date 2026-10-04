/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.loader;

import asm.patchify.annotation.Patch;
import asm.patchify.loader.PatchClassFileTransformer;
import asm.patchify.loader.PatchRegistry;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import lf21.dll.GameLoaderBridge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PatchAgent {
    public static final String INSTRUMENTATION_KEY = "oz.instrumentation";
    private static final Logger LOGGER = LogManager.getLogger(PatchAgent.class);
    private static volatile boolean transformerInstalled = false;

    private PatchAgent() {
    }

    public static void premain(String string, Instrumentation instrumentation) {
        PatchAgent.install(instrumentation);
    }

    public static void agentmain(String string, Instrumentation instrumentation) {
        PatchAgent.install(instrumentation);
        Path path = null;
        try {
            if (string == null || string.isBlank()) {
                throw new IllegalArgumentException("missing extracted agent jar path");
            }
            path = Paths.get(string, new String[0]).toAbsolutePath().normalize();
            ClassLoader classLoader = PatchAgent.findGameClassLoader(instrumentation);
            LOGGER.info("Minecraft ClassLoader located by agentmain: {}", (Object)classLoader);
            Thread.currentThread().setContextClassLoader(classLoader);
            GameLoaderBridge.load(path.toString(), classLoader, instrumentation);
        }
        catch (Throwable throwable) {
            try {
                LOGGER.error("dynamic agent bootstrap failed", throwable);
                throw new IllegalStateException("LF21 dynamic agent bootstrap failed", throwable);
            }
            catch (Throwable throwable2) {
                PatchAgent.deleteExtractedAgentJar(path);
                throw throwable2;
            }
        }
        PatchAgent.deleteExtractedAgentJar(path);
    }

    public static synchronized void install(Instrumentation instrumentation) {
        Object object = System.getProperties().get("oz.instrumentation");
        if (object == instrumentation) {
            return;
        }
        System.getProperties().put("oz.instrumentation", instrumentation);
        LOGGER.info("agent attached, retransform supported = {}", (Object)instrumentation.isRetransformClassesSupported());
    }

    public static Instrumentation getInstrumentation() {
        Object object = System.getProperties().get("oz.instrumentation");
        return object instanceof Instrumentation ? (Instrumentation)object : null;
    }

    private static ClassLoader findGameClassLoader(Instrumentation instrumentation) {
        for (Class clazz : instrumentation.getAllLoadedClasses()) {
            ClassLoader classLoader;
            if (!"net.minecraft.client.Minecraft".equals(clazz.getName()) || (classLoader = clazz.getClassLoader()) == null) continue;
            return classLoader;
        }
        throw new IllegalStateException("Minecraft class is not loaded");
    }

    private static void deleteExtractedAgentJar(Path path) {
        if (path == null) {
            return;
        }
        Path path2 = Paths.get(System.getProperty("java.io.tmpdir"), new String[0]).toAbsolutePath().normalize();
        Path path3 = path.getFileName();
        if (!path.startsWith(path2) || path3 == null || !path3.toString().startsWith("lf21-forge-1.20.1-")) {
            LOGGER.warn("Refusing to delete unexpected agent path {}", (Object)path);
            return;
        }
        for (int i2 = 0; i2 < 8; ++i2) {
            try {
                if (Files.deleteIfExists(path)) {
                    LOGGER.info("Removed temporary agent jar {}", (Object)path);
                }
                return;
            }
            catch (Exception exception) {
                if (i2 == 7) {
                    LOGGER.debug("Native cleanup will retry temporary agent jar {}: {}", (Object)path, (Object)exception.toString());
                    return;
                }
                try {
                    Thread.sleep(25L);
                    continue;
                }
                catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public static synchronized void installPatchesAndRetransform() {
        if (transformerInstalled) {
            LOGGER.info("Patches already installed; skipping duplicate retransform request");
            return;
        }
        Instrumentation instrumentation = PatchAgent.getInstrumentation();
        if (instrumentation == null) {
            LOGGER.warn("agent not attached; cannot install patches");
            return;
        }
        PatchClassFileTransformer patchClassFileTransformer = new PatchClassFileTransformer();
        instrumentation.addTransformer(patchClassFileTransformer, true);
        transformerInstalled = true;
        ArrayList arrayList = new ArrayList();
        for (Class<?> clazz : PatchRegistry.getPatches()) {
            Class<?> clazz2;
            Patch object = clazz.getAnnotation(Patch.class);
            if (object == null) continue;
            if (!object.className().isEmpty()) {
                boolean throwable = false;
                for (Class clazz3 : instrumentation.getAllLoadedClasses()) {
                    if (!clazz3.getName().equals(object.className())) continue;
                    LOGGER.debug("Found already-loaded target {} for className-based patch {}", (Object)object.className(), (Object)clazz.getName());
                    if (instrumentation.isModifiableClass(clazz3)) {
                        arrayList.add(clazz3);
                    } else {
                        LOGGER.warn("Cannot retransform unmodifiable target {}", (Object)object.className());
                    }
                    throwable = true;
                    break;
                }
                if (throwable) continue;
                LOGGER.debug("Target {} not yet loaded \u2014 transformer will catch it at class-load time", (Object)object.className());
                continue;
            }
            try {
                clazz2 = object.value();
            }
            catch (Throwable throwable) {
                LOGGER.warn("Patch target unresolved for {}: {}", (Object)clazz.getName(), (Object)throwable.toString());
                continue;
            }
            if (instrumentation.isModifiableClass(clazz2)) {
                arrayList.add(clazz2);
                continue;
            }
            LOGGER.warn("Cannot retransform unmodifiable target {}", (Object)clazz2.getName());
        }
        if (arrayList.isEmpty()) {
            return;
        }
        int n2 = 0;
        for (Class clazz : arrayList) {
            try {
                instrumentation.retransformClasses(clazz);
                ++n2;
            }
            catch (Throwable throwable) {
                LOGGER.error("Retransform failed for {}: {}", (Object)clazz.getName(), (Object)throwable.toString());
            }
        }
        LOGGER.info("Retransformed {} / {} patch target(s)", (Object)n2, (Object)arrayList.size());
    }
}

