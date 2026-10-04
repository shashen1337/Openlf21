/*
 * Decompiled with CFR 0.152.
 */
package lf21.asm;

import java.util.HashMap;
import java.util.Map;
import lf21.asm.ILocals;

public final class Locals
implements ILocals {
    private final Map<Integer, Object> locals = new HashMap<Integer, Object>();

    private Locals() {
    }

    public static Locals create() {
        return new Locals();
    }

    @Override
    public Object get(int n2) {
        if (!this.locals.containsKey(n2)) {
            throw new IllegalArgumentException("Index " + n2 + " is not defined in the local list");
        }
        return this.locals.get(n2);
    }

    @Override
    public Locals set(int n2, Object object) {
        this.locals.put(n2, object);
        return this;
    }
}

