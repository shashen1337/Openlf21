/*
 * Decompiled with CFR 0.152.
 */
package lf21.dll;

import asm.patchify.loader.PatchAgent;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameLoaderBridge {
    private static final Logger LOGGER;
    public static final String RESOURCES_PROP;

    private GameLoaderBridge() {
    }

    public static void load(String string, ClassLoader classLoader) throws Throwable {
        Instrumentation instrumentation = PatchAgent.getInstrumentation();
        if (instrumentation == null) {
            throw new IllegalStateException("agent instrumentation returned null");
        }
        GameLoaderBridge.load(string, classLoader, instrumentation);
    }

    public static void load(String string, ClassLoader classLoader, Instrumentation instrumentation) throws Throwable {
        Object object;
        Object object2;
        LOGGER.debug("bridge.load jar={} gameLoader={}", (Object)string, (Object)classLoader);
        long l2 = System.nanoTime();
        GameLoaderBridge.openJavaBaseToSelf(instrumentation);
        Method method = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE);
        method.setAccessible(true);
        Path path = Paths.get(System.getProperty("java.io.tmpdir"), "lf21-resources-" + ProcessHandle.current().pid());
        Files.createDirectories(path, new FileAttribute[0]);
        LinkedHashMap<String, byte[]> linkedHashMap = new LinkedHashMap<String, byte[]>();
        int n2 = 0;
        try (ZipFile zipFile = new ZipFile(string);){
            Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry object3 = enumeration.nextElement();
                if (object3.isDirectory() || ((String)(object2 = object3.getName())).startsWith("META-INF/")) continue;
                try (Object object4 = zipFile.getInputStream(object3);){
                    object = ((InputStream)object4).readAllBytes();
                }
                if (((String)object2).endsWith(".class")) {
                    object4 = ((String)object2).substring(0, ((String)object2).length() - 6).replace('/', '.');
                    if (((String)object4).equals(GameLoaderBridge.class.getName())) continue;
                    linkedHashMap.put((String)object4, (byte[])object);
                    continue;
                }
                object4 = path.resolve((String)object2);
                Path path2 = object4.getParent();
                if (path2 != null) {
                    Files.createDirectories(path2, new FileAttribute[0]);
                }
                Files.write((Path)object4, (byte[])object, new OpenOption[0]);
                ++n2;
            }
        }
        int n3 = GameLoaderBridge.definePassUntilFixedPoint(method, classLoader, linkedHashMap);
        if (!linkedHashMap.isEmpty()) {
            LOGGER.warn("{} class(es) could not be defined on gameLoader after fixed-point retry:", (Object)linkedHashMap.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                try {
                    method.invoke(classLoader, entry.getKey(), entry.getValue(), 0, ((byte[])entry.getValue()).length);
                }
                catch (Throwable throwable) {
                    object = throwable.getCause() != null ? throwable.getCause() : throwable;
                    LOGGER.warn("  {} -> {}", entry.getKey(), (Object)((Throwable)object).toString());
                }
            }
        }
        System.setProperty("lf21.resources", path.toAbsolutePath().toString());
        long l3 = (System.nanoTime() - l2) / 1000000L;
        LOGGER.debug("Defined {} classes, extracted {} resources to {} ({} ms)", (Object)n3, (Object)n2, (Object)path, (Object)l3);
        object2 = Class.forName("lf21.dll.DllBootstrap", true, classLoader);
        LOGGER.debug("bootstrap loader={}", (Object)((Class)object2).getClassLoader());
        object = ((Class)object2).getMethod("start", String.class);
        ((Method)object).invoke(null, string);
    }

    private static int definePassUntilFixedPoint(Method method, ClassLoader classLoader, LinkedHashMap<String, byte[]> linkedHashMap) {
        int n2 = 0;
        boolean bl = true;
        while (bl && !linkedHashMap.isEmpty()) {
            bl = false;
            Iterator<Map.Entry<String, byte[]>> iterator = linkedHashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, byte[]> entry = iterator.next();
                try {
                    method.invoke(classLoader, entry.getKey(), entry.getValue(), 0, entry.getValue().length);
                    iterator.remove();
                    bl = true;
                    ++n2;
                }
                catch (Throwable throwable) {}
            }
        }
        return n2;
    }

    private static void openJavaBaseToSelf(Instrumentation instrumentation) {
        Module module = ClassLoader.class.getModule();
        Module module2 = GameLoaderBridge.class.getModule();
        try {
            instrumentation.redefineModule(module, Set.of(), Map.of(), Map.of("java.lang", Set.of(module2)), Set.of(), Map.of());
        }
        catch (Throwable throwable) {
            LOGGER.warn("redefineModule for java.base/java.lang failed (may be already open): {}", (Object)throwable.toString());
        }
    }

    static {
        RESOURCES_PROP = "lf21.resources";
        LOGGER = LogManager.getLogger(GameLoaderBridge.class);
    }
}

