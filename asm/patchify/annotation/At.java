/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE_USE})
public @interface At {
    public Type value() default Type.HEAD;

    public String method() default "";

    public String remapped() default "";

    public String desc() default "";

    public static enum Type {
        BEFORE_INVOKE,
        AFTER_INVOKE,
        HEAD,
        TAIL;

    }
}

