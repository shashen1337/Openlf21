/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.loader;

import asm.patchify.annotation.Patch;
import asm.patchify.loader.PatchRegistry;
import asm.patchify.loader.PatchTransformer;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public final class PatchClassFileTransformer
implements ClassFileTransformer {
    private static final Logger LOGGER;
    private static final String DUMP_DIR_PROPERTY;
    private final Map<String, List<Class<?>>> patchesByTarget = new HashMap();

    private static boolean isNativeOwnedClass(String string) {
        return string.startsWith("com/netease/") || string.startsWith("nativeTransformer/") || string.startsWith("com/heypixel/heypixelmod/");
    }

    public PatchClassFileTransformer() {
        this.rebuildIndex();
    }

    public void rebuildIndex() {
        this.patchesByTarget.clear();
        for (Class<?> clazz : PatchRegistry.getPatches()) {
            Patch patch = clazz.getAnnotation(Patch.class);
            if (patch == null) continue;
            String string2 = !patch.className().isEmpty() ? patch.className().replace('.', '/') : patch.value().getName().replace('.', '/');
            this.patchesByTarget.computeIfAbsent(string2, string -> new ArrayList()).add(clazz);
        }
    }

    @Override
    public byte[] transform(ClassLoader classLoader, String string, Class<?> clazz, ProtectionDomain protectionDomain, byte[] byArray) throws IllegalClassFormatException {
        if (string == null) {
            return null;
        }
        if (PatchClassFileTransformer.isNativeOwnedClass(string)) {
            return null;
        }
        List<Class<?>> list = this.patchesByTarget.get(string);
        if (list == null || list.isEmpty()) {
            return null;
        }
        try {
            Object object2;
            ClassReader classReader = new ClassReader(byArray);
            ClassNode classNode = new ClassNode();
            classReader.accept((ClassVisitor)classNode, 0);
            for (Object object2 : list) {
                try {
                    LOGGER.debug("Applying patch {} -> {}", (Object)object2.getName(), (Object)string);
                    PatchTransformer.apply(object2, classNode);
                }
                catch (Throwable throwable) {
                    LOGGER.error("Failed to apply patch {} -> {}", (Object)object2.getName(), (Object)string, (Object)throwable);
                }
            }
            ClassLoader classLoader2 = classLoader != null ? classLoader : Thread.currentThread().getContextClassLoader();
            object2 = new FrameAwareClassWriter(classReader, 2, classLoader2);
            classNode.accept(object2);
            byte[] byArray2 = object2.toByteArray();
            this.dumpIfRequested(string, byArray2);
            return byArray2;
        }
        catch (Throwable throwable) {
            LOGGER.error("Failed to transform {}", (Object)string, (Object)throwable);
            return null;
        }
    }

    private void dumpIfRequested(String string, byte[] byArray) {
        String string2 = System.getProperty("oz.dumpDir");
        if (string2 == null) {
            return;
        }
        try {
            Path path = Path.of(string2, new String[0]).resolve(string + ".class");
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
            Files.write(path, byArray, new OpenOption[0]);
        }
        catch (IOException iOException) {
            LOGGER.warn("Failed to dump transformed class {}", (Object)string, (Object)iOException);
        }
    }

    static {
        DUMP_DIR_PROPERTY = "oz.dumpDir";
        LOGGER = LogManager.getLogger(PatchClassFileTransformer.class);
    }

    private static final class FrameAwareClassWriter
    extends ClassWriter {
        private final ClassLoader loader;

        FrameAwareClassWriter(ClassReader classReader, int n2, ClassLoader classLoader) {
            super(classReader, n2);
            this.loader = classLoader;
        }

        protected ClassLoader getClassLoader() {
            return this.loader;
        }
    }
}

