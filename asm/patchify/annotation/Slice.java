/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.annotation;

import asm.patchify.annotation.At;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE_USE})
public @interface Slice {
    public At start() default @At(value=At.Type.HEAD);

    public At end() default @At(value=At.Type.TAIL);

    public int startIndex() default -1;

    public int endIndex() default -1;
}

