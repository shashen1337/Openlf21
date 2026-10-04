/*
 * Decompiled with CFR 0.152.
 */
package lf21.render.a;

import java.nio.FloatBuffer;
import org.joml.Matrix4f;

public class b {
    public static void a(FloatBuffer floatBuffer, float f2) {
        floatBuffer.clear();
        for (int i2 = 0; i2 < floatBuffer.capacity(); ++i2) {
            floatBuffer.put(i2, f2);
        }
        floatBuffer.clear();
    }

    public static FloatBuffer a(FloatBuffer floatBuffer, Matrix4f matrix4f) {
        return matrix4f.get(floatBuffer);
    }
}

