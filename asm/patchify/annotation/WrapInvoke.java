/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.annotation;

import asm.patchify.annotation.Slice;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface WrapInvoke {
    public String method();

    public String desc();

    public String target();

    public String targetDesc();

    public Slice slice() default @Slice;
}

