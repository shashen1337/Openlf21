/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.annotation;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Slice;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Inject {
    public String method();

    public String desc();

    public At at() default @At(value=At.Type.HEAD);

    public Slice slice() default @Slice(start=@At(value=At.Type.HEAD), end=@At(value=At.Type.TAIL));
}

