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
@Target(value={ElementType.METHOD})
public @interface ModifyLocals {
    public String method();

    public String desc();

    public int[] indexes();

    public Class<?>[] types();

    public At at() default @At(value=At.Type.HEAD);
}

