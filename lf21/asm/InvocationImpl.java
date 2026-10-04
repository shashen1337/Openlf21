/*
 * Decompiled with CFR 0.152.
 */
package lf21.asm;

import java.util.List;
import lf21.asm.Invocation;
import lf21.asm.MethodWrapper;

public final class InvocationImpl
implements Invocation {
    private final Object instance;
    private final MethodWrapper wrapper;

    private InvocationImpl(Object object, MethodWrapper methodWrapper) {
        this.instance = object;
        this.wrapper = methodWrapper;
    }

    public static InvocationImpl create(Object object, MethodWrapper methodWrapper) {
        return new InvocationImpl(object, methodWrapper);
    }

    public static InvocationImpl create(MethodWrapper methodWrapper) {
        return new InvocationImpl(null, methodWrapper);
    }

    @Override
    public List<Object> args() {
        return this.wrapper.getMethodParams();
    }

    public Object instance() {
        if (this.instance == null) {
            throw new IllegalStateException("static method invocation");
        }
        return this.instance;
    }

    @Override
    public Object call() throws Exception {
        try {
            return this.wrapper.call(this.instance);
        }
        catch (Exception exception) {
            throw exception;
        }
        catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}

