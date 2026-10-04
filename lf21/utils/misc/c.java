/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import java.util.HashMap;
import java.util.Map;
import lf21.ClientBase;
import org.lwjgl.glfw.GLFW;

public class c {
    private static final Map<Integer, Long> a = new HashMap<Integer, Long>();
    static long b = ClientBase.mc.getWindow().getWindow();

    public static boolean a(float f2, float f3, float f4, float f5, float f6, float f7) {
        return f2 > f4 && f3 > f5 && f2 < f4 + f6 && f3 < f5 + f7;
    }

    public static void a(int n2) {
        long l2 = a.computeIfAbsent(n2, GLFW::glfwCreateStandardCursor);
        GLFW.glfwSetCursor((long)b, (long)l2);
    }

    public static void a() {
        c.a(221185);
    }

    public static void b() {
        a.values().forEach(GLFW::glfwDestroyCursor);
    }
}

