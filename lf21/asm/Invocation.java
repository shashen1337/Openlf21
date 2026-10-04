/*
 * Decompiled with CFR 0.152.
 */
package lf21.asm;

import java.util.List;
import java.util.concurrent.Callable;

public interface Invocation<T, R>
extends Callable<R> {
    public List<Object> args();

    public T instance();
}

