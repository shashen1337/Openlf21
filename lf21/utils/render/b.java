/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.lang.reflect.Method;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import lf21.ClientBase;
import lf21.render.ad;
import lf21.utils.b.i;
import lf21.utils.misc.ReflectionUtil;
import lombok.Generated;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class b
extends ClientBase {
    private static final Matrix4f a;
    private static final Matrix4f b;
    private static final Vector4f c;
    private static final Vector4f d;
    private static final FloatBuffer e;
    private static final IntBuffer f;
    private static final Vector3f g;
    private static final Quaternionf h;
    private static final String[] i;

    public static void a() {
        e.clear();
        RenderSystem.getProjectionMatrix().get(e);
        b.set(e);
        e.clear();
        RenderSystem.getModelViewMatrix().get(e);
        a.set(e);
        f.clear();
        GL11.glGetIntegerv((int)2978, (IntBuffer)f);
        c.set((float)f.get(0), (float)f.get(1), (float)f.get(2), (float)f.get(3));
    }

    public static i a(double d2, double d3, double d4, float f2) {
        Entity entity;
        Vec3 vec3 = lf21.utils.render.b.mc.getEntityRenderDispatcher().camera.getPosition();
        Quaternionf quaternionf = new Quaternionf((Quaternionfc)mc.getEntityRenderDispatcher().cameraOrientation());
        quaternionf.conjugate();
        Vector3f vector3f = new Vector3f((float)(vec3.x - d2), (float)(vec3.y - d3), (float)(vec3.z - d4));
        vector3f.rotate((Quaternionfc)quaternionf);
        if (((Boolean)lf21.utils.render.b.mc.options.bobView().get()).booleanValue() && (entity = mc.getCameraEntity()) instanceof Player) {
            Player player = (Player)entity;
            lf21.utils.render.b.a(player, vector3f, f2);
        }
        double d5 = 1.2f;
        try {
            String[] stringArray = i;
            Class[] classArray = new Class[]{Camera.class, Float.TYPE, Boolean.TYPE};
            Class<?> clazz = lf21.utils.render.b.mc.gameRenderer.getClass();
            Method method = clazz.getDeclaredMethod(ad.b(ReflectionUtil.getMappedMethodName(lf21.utils.render.b.mc.gameRenderer.getClass(), "getFov", "(Lnet/minecraft/client/Camera;FZ)D"), clazz, classArray), classArray);
            method.setAccessible(true);
            d5 = (Double)method.invoke(lf21.utils.render.b.mc.gameRenderer, lf21.utils.render.b.mc.getEntityRenderDispatcher().camera, Float.valueOf(f2), true);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return lf21.utils.render.b.a(vector3f, d5);
    }

    private static void a(Player player, Vector3f vector3f, float f2) {
        float f3 = player.walkDist;
        float f4 = f3 - player.walkDistO;
        float f5 = -(f3 + f4 * f2);
        float f6 = Mth.lerp((float)f2, (float)player.oBob, (float)player.bob);
        Quaternionf quaternionf = new Quaternionf().rotationX(Math.abs(Mth.cos((float)(f5 * (float)Math.PI - 0.2f)) * f6) * 5.0f * ((float)Math.PI / 180));
        quaternionf.conjugate();
        vector3f.rotate((Quaternionfc)quaternionf);
        Quaternionf quaternionf2 = new Quaternionf().rotationZ(Mth.sin((float)(f5 * (float)Math.PI)) * f6 * 3.0f * ((float)Math.PI / 180));
        quaternionf2.conjugate();
        vector3f.rotate((Quaternionfc)quaternionf2);
        Vector3f vector3f2 = new Vector3f(Mth.sin((float)(f5 * (float)Math.PI)) * f6 * 0.5f, -Math.abs(Mth.cos((float)(f5 * (float)Math.PI)) * f6), 0.0f);
        vector3f2.y = -vector3f2.y;
        vector3f.add((Vector3fc)vector3f2);
    }

    private static i a(Vector3f vector3f, double d2) {
        float f2 = (float)mc.getWindow().getGuiScaledHeight() / 2.0f;
        float f3 = f2 / (vector3f.z() * (float)Math.tan(Math.toRadians(d2 / 2.0)));
        if (vector3f.z() < 0.0f) {
            return new i(-vector3f.x() * f3 + (float)mc.getWindow().getGuiScaledWidth() / 2.0f, (float)mc.getWindow().getGuiScaledHeight() / 2.0f - vector3f.y() * f3);
        }
        return new i(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    public static i a(double d2, double d3, double d4) {
        float f2;
        float f3;
        block17: {
            block16: {
                Camera camera = lf21.utils.render.b.mc.gameRenderer.getMainCamera();
                Vec3 vec3 = camera.getPosition();
                g.set((float)(d2 - vec3.x), (float)(d3 - vec3.y), (float)(d4 - vec3.z));
                h.set((Quaternionfc)camera.rotation()).conjugate();
                g.rotate((Quaternionfc)h);
                lf21.utils.render.b.g.x = -lf21.utils.render.b.g.x;
                d.set(lf21.utils.render.b.g.x, lf21.utils.render.b.g.y, -lf21.utils.render.b.g.z, 1.0f);
                b.transform(d);
                if (lf21.utils.render.b.d.w <= 0.0f) {
                    return null;
                }
                f3 = lf21.utils.render.b.d.x / lf21.utils.render.b.d.w;
                f2 = lf21.utils.render.b.d.y / lf21.utils.render.b.d.w;
                if (Float.isNaN(f3) || Float.isNaN(f2)) break block16;
                if (f3 < -1.2f) break block16;
                if (f3 > 1.2f) break block16;
                if (f2 < -1.2f) break block16;
                if (!(f2 > 1.2f)) break block17;
            }
            return null;
        }
        float f4 = c.x() + (1.0f + f3) * c.z() / 2.0f;
        float f5 = c.y() + (1.0f - f2) * c.w() / 2.0f;
        double d5 = mc.getWindow().getGuiScale();
        if (d5 == 0.0) {
            d5 = 1.0;
        }
        f4 = (float)((double)f4 / d5);
        f5 = (float)((double)f5 / d5);
        return new i(f4, f5);
    }

    @Generated
    private b() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = ",y(j)\u0006|m[#l/Hwp\u001a%c4L\u007fp\u001a\u0005n0Lce\u000e\u0000Utm2P]/|}@b$Tfz)@}mA?/>EpwFfn3M1gT(a2]1fPff3Zee[2f<]t`\u0006cP2I2_";
        int n3 = ",y(j)\u0006|m[#l/Hwp\u001a%c4L\u007fp\u001a\u0005n0Lce\u000e\u0000Utm2P]/|}@b$Tfz)@}mA?/>EpwFfn3M1gT(a2]1fPff3Zee[2f<]t`\u0006cP2I2_".length();
        int n4 = 34;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.utils.render.b.b(lf21.utils.render.b.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                i = stringArray;
                a = new Matrix4f();
                b = new Matrix4f();
                c = new Vector4f();
                d = new Vector4f();
                e = BufferUtils.createFloatBuffer((int)16);
                f = BufferUtils.createIntBuffer((int)16);
                g = new Vector3f();
                h = new Quaternionf();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x11);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
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
                    case 0 -> 4;
                    case 1 -> 53;
                    case 2 -> 70;
                    case 3 -> 15;
                    case 4 -> 93;
                    case 5 -> 41;
                    default -> 17;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

