/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import java.util.Arrays;
import java.util.List;
import lf21.ClientBase;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector4d;
import org.joml.Vector4f;

public class c
extends ClientBase {
    public static boolean a(Entity entity) {
        Frustum frustum = c.mc.levelRenderer.getFrustum();
        return frustum.isVisible(entity.getBoundingBox());
    }

    public static Vec3 a(Entity entity, float f2) {
        double d2 = Mth.lerp((double)f2, (double)entity.xOld, (double)entity.getX());
        double d3 = Mth.lerp((double)f2, (double)entity.yOld, (double)entity.getY());
        double d4 = Mth.lerp((double)f2, (double)entity.zOld, (double)entity.getZ());
        return new Vec3(d2, d3, d4);
    }

    public static AABB b(Entity entity, float f2) {
        Vec3 vec3 = c.a(entity, f2);
        double d2 = (double)entity.getBbWidth() / 2.0;
        return new AABB(vec3.x - d2, vec3.y, vec3.z - d2, vec3.x + d2, vec3.y + (double)entity.getBbHeight(), vec3.z + d2);
    }

    public static Vector4d a(AABB aABB, Matrix4f matrix4f, Matrix4f matrix4f2) {
        Matrix4f matrix4f3 = new Matrix4f((Matrix4fc)matrix4f2).mul((Matrix4fc)matrix4f);
        int[] nArray = new int[]{0, 0, mc.getWindow().getWidth(), mc.getWindow().getHeight()};
        return c.a(nArray, aABB, matrix4f3);
    }

    private static Vector4d a(int[] nArray, AABB aABB, Matrix4f matrix4f) {
        List<Vec3> list = Arrays.asList(new Vec3(aABB.minX, aABB.minY, aABB.minZ), new Vec3(aABB.minX, aABB.maxY, aABB.minZ), new Vec3(aABB.maxX, aABB.minY, aABB.minZ), new Vec3(aABB.maxX, aABB.maxY, aABB.minZ), new Vec3(aABB.minX, aABB.minY, aABB.maxZ), new Vec3(aABB.minX, aABB.maxY, aABB.maxZ), new Vec3(aABB.maxX, aABB.minY, aABB.maxZ), new Vec3(aABB.maxX, aABB.maxY, aABB.maxZ));
        Vector4f vector4f = new Vector4f();
        Vector4d vector4d = null;
        boolean bl = false;
        for (Vec3 vec3 : list) {
            Vector4f vector4f2 = new Vector4f((float)vec3.x(), (float)vec3.y(), (float)vec3.z(), 1.0f);
            matrix4f.transform(vector4f2);
            if (!(vector4f2.w > 0.0f)) continue;
            matrix4f.project((float)vec3.x(), (float)vec3.y(), (float)vec3.z(), nArray, vector4f);
            vector4f.y = (float)nArray[3] - vector4f.y;
            bl = true;
            if (vector4d == null) {
                vector4d = new Vector4d((double)vector4f.x, (double)vector4f.y, (double)vector4f.x, (double)vector4f.y);
                continue;
            }
            vector4d.x = Math.min((double)vector4f.x, vector4d.x);
            vector4d.y = Math.min((double)vector4f.y, vector4d.y);
            vector4d.z = Math.max((double)vector4f.x, vector4d.z);
            vector4d.w = Math.max((double)vector4f.y, vector4d.w);
        }
        return bl ? vector4d : null;
    }

    public static Vector3f c(Entity entity, float f2) {
        if (mc == null || c.mc.gameRenderer == null || c.mc.gameRenderer.getMainCamera() == null) {
            return null;
        }
        Vec3 vec3 = c.a(entity, f2);
        double d2 = vec3.x();
        double d3 = vec3.y();
        double d4 = vec3.z();
        Camera camera = c.mc.gameRenderer.getMainCamera();
        Vec3 vec32 = camera.getPosition();
        Quaternionf quaternionf = camera.rotation();
        Quaternionf quaternionf2 = new Quaternionf((Quaternionfc)quaternionf).conjugate();
        Vector3f vector3f = new Vector3f((float)(d2 - vec32.x), (float)(d3 - vec32.y), (float)(d4 - vec32.z));
        vector3f.rotate((Quaternionfc)quaternionf2);
        return vector3f;
    }
}

