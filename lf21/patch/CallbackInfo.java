/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

public final class CallbackInfo {
    public Object result;
    public boolean cancelled;

    public CallbackInfo(Object object, boolean bl) {
        this.result = object;
        this.cancelled = bl;
    }

    public static CallbackInfo create(Object object) {
        return new CallbackInfo(object, false);
    }

    public void cancel() {
        this.cancelled = true;
    }
}

