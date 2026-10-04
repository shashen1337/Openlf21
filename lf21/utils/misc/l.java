/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import lf21.exception.a;
import sun.misc.Unsafe;

public class l {
    private static final Unsafe a;
    public static final MethodHandles.Lookup b;

    public static MethodHandles.Lookup a() {
        return b;
    }

    public static void a(AccessibleObject accessibleObject) {
        l.a(accessibleObject, true);
    }

    public static void a(AccessibleObject accessibleObject, boolean bl) {
        try {
            accessibleObject.setAccessible(bl);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static Field a(Class<?> clazz, String string) {
        for (Field field : l.a(clazz)) {
            if (!string.equals(field.getName())) continue;
            return field;
        }
        return null;
    }

    public static Field[] a(Class<?> clazz) {
        try {
            return clazz.getDeclaredFields();
        }
        catch (Throwable throwable) {
            return new Field[0];
        }
    }

    public static <T> T a(Object object, Method method, Object ... objectArray) {
        try {
            if (Modifier.isStatic(method.getModifiers())) {
                return (T)b.unreflect(method).invokeWithArguments(objectArray);
            }
            return (T)b.unreflect(method).bindTo(object).invokeWithArguments(objectArray);
        }
        catch (Throwable throwable) {
            throw new a();
        }
    }

    public static Class<?> a(String string, byte[] byArray, ClassLoader classLoader) {
        return null;
    }

    public static Unsafe b() {
        return a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static {
        String string = l.a(l.a("9\u0003t\u0012wF\u0014+\u000e"));
        b = MethodHandles.lookup();
        Unsafe unsafe = null;
        try {
            Field field = Unsafe.class.getDeclaredField(string);
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        a = unsafe;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x75);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 77;
                    case 1 -> 107;
                    case 2 -> 17;
                    case 3 -> 71;
                    case 4 -> 25;
                    case 5 -> 53;
                    default -> 117;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

