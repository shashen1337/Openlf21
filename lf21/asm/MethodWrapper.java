/*
 * Decompiled with CFR 0.152.
 */
package lf21.asm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.objectweb.asm.Type;

public final class MethodWrapper {
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final Map<String, MethodHandle> CACHE = new ConcurrentHashMap<String, MethodHandle>();
    private final MethodHandle handle;
    private final List<Object> params = new LinkedList<Object>();

    private MethodWrapper(MethodHandle methodHandle) {
        this.handle = methodHandle;
    }

    public static MethodWrapper getInstance(String string, String string2, String string3) throws Exception {
        String string4 = string + "/" + string2 + string3;
        MethodHandle methodHandle = CACHE.get(string4);
        if (methodHandle != null) {
            return new MethodWrapper(methodHandle);
        }
        Class<?> clazz = Class.forName(string.replace('/', '.'), false, Thread.currentThread().getContextClassLoader());
        MethodHandle methodHandle2 = MethodWrapper.lookup(clazz, string2, string3);
        if (methodHandle2 == null) {
            throw new NoSuchMethodException("Method " + string2 + string3 + " not found on " + string);
        }
        CACHE.put(string4, methodHandle2);
        return new MethodWrapper(methodHandle2);
    }

    private static MethodHandle lookup(Class<?> clazz, String string, String string2) throws Exception {
        for (Class<?> clazz2 = clazz; clazz2 != null; clazz2 = clazz2.getSuperclass()) {
            for (Method method : clazz2.getDeclaredMethods()) {
                if (!method.getName().equals(string) || !Type.getMethodDescriptor((Method)method).equals(string2)) continue;
                method.setAccessible(true);
                return LOOKUP.unreflect(method);
            }
        }
        return null;
    }

    public List<Object> getMethodParams() {
        return this.params;
    }

    public MethodWrapper addParam(Object object) {
        this.params.add(0, object);
        return this;
    }

    public Object call(Object object) throws Throwable {
        if (object == null) {
            if (this.params.isEmpty()) {
                return this.handle.invoke();
            }
            return this.handle.invokeWithArguments(this.params);
        }
        if (this.params.isEmpty()) {
            return this.handle.invoke(object);
        }
        this.params.add(0, object);
        return this.handle.invokeWithArguments(this.params);
    }
}

