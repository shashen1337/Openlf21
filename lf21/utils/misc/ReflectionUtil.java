/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import com.mojang.blaze3d.pipeline.RenderTarget;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongRBTreeMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectLongImmutablePair;
import it.unimi.dsi.fastutil.objects.ObjectLongPair;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.asm.Bootstrap;
import lf21.render.ad;
import lf21.utils.misc.l;
import lf21.utils.misc.n;
import net.minecraft.client.Camera;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.phys.Vec3;
import sun.misc.Unsafe;

public final class ReflectionUtil {
    private static final MethodHandles.Lookup a;
    private static final Object2ObjectMap<String, MethodHandles.Lookup> b;
    private static final Object2ObjectMap<String, Class<?>> c;
    private static final Object2ObjectMap<String, VarHandle> d;
    private static final Object2LongMap<String> e;
    private static final Object2ObjectMap<String, ObjectLongPair<Object>> f;
    private static final Unsafe g;
    private static volatile Method h;
    private static volatile Method i;
    private static final String[] j;

    public static int a(Class<?> clazz) {
        if (clazz == null) {
            return 0;
        }
        try {
            Field field = Class.class.getDeclaredField("classRedefinedCount");
            l.a(field);
            return field.getInt(clazz);
        }
        catch (Throwable throwable) {
            throw new Error(throwable);
        }
    }

    public static void a(Class<?> clazz, int n2) {
        if (clazz == null) {
            return;
        }
        try {
            Field field = Class.class.getDeclaredField("classRedefinedCount");
            l.a(field);
            field.setInt(clazz, n2);
        }
        catch (Throwable throwable) {
            throw new Error(throwable);
        }
    }

    public static void b(Class<?> clazz, int n2) {
        if (clazz == null) {
            return;
        }
        try {
            Field field = Class.class.getDeclaredField("classRedefinedCount");
            l.a(field);
            field.setInt(clazz, field.getInt(clazz) + n2);
        }
        catch (Throwable throwable) {
            throw new Error(throwable);
        }
    }

    public static void a(int n2) {
        block4: {
            if (ClientBase.mc != null && ClientBase.mc.player != null) break block4;
            return;
        }
        ReflectionUtil.setJumpDelay((LivingEntity)ClientBase.mc.player, n2);
    }

    public static void setJumpDelay(LivingEntity livingEntity, int n2) {
        if (livingEntity == null) {
            return;
        }
        try {
            String[] stringArray = new String[2];
            String[] stringArray2 = j;
            stringArray[0] = "noJumpDelay";
            stringArray[1] = "f_20954_";
            Field field = ReflectionUtil.a(livingEntity.getClass(), stringArray);
            field.setInt(livingEntity, n2);
        }
        catch (Exception exception) {
            ClientBase.logger.error("Failed to set noJumpDelay field", (Throwable)exception);
        }
    }

    public static void b(int n2) {
        if (ClientBase.mc == null) {
            return;
        }
        try {
            String[] stringArray = new String[2];
            String[] stringArray2 = j;
            stringArray[0] = "rightClickDelay";
            stringArray[1] = "f_91011_";
            Field field = ReflectionUtil.a(ClientBase.mc.getClass(), stringArray);
            field.setInt(ClientBase.mc, n2);
        }
        catch (Exception exception) {
            ClientBase.logger.error("Failed to set rightClickDelay field", (Throwable)exception);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a() {
        if (ClientBase.mc == null) return;
        if (ClientBase.mc.player == null) {
            return;
        }
        try {
            String[] stringArray = new String[2];
            String[] stringArray2 = j;
            stringArray[0] = "yRotLast";
            stringArray[1] = "f_108598_";
            Field field = ReflectionUtil.a(ClientBase.mc.player.getClass(), stringArray);
            Field field2 = ReflectionUtil.a(ClientBase.mc.player.getClass(), "xRotLast", "f_108599_");
            field.setFloat(ClientBase.mc.player, Float.NaN);
            field2.setFloat(ClientBase.mc.player, Float.NaN);
            return;
        }
        catch (Exception exception) {
            ClientBase.logger.error("Failed to force next rotation packet", (Throwable)exception);
        }
    }

    public static float a(ServerboundMovePlayerPacket serverboundMovePlayerPacket) {
        String string;
        String[] stringArray;
        Class<?> clazz;
        block8: {
            block7: {
                if (ClientBase.mc.gameMode == null) {
                    return 0.0f;
                }
                clazz = serverboundMovePlayerPacket.getClass();
                String[] stringArray2 = new String[1];
                String[] stringArray3 = stringArray2;
                stringArray = stringArray2;
                int n2 = 0;
                if (!LF21Client.isMCPMapped) break block7;
                string = "f_134121_";
                break block8;
            }
            String[] stringArray4 = j;
            string = "yRot";
        }
        stringArray3[n2] = string;
        Field field = ReflectionUtil.a(clazz, stringArray);
        try {
            return field.getFloat(serverboundMovePlayerPacket);
        }
        catch (Exception exception) {
            ClientBase.logger.error("Failed to get yrot field", (Throwable)exception);
            return 0.0f;
        }
    }

    public static float b(ServerboundMovePlayerPacket serverboundMovePlayerPacket) {
        String string;
        String[] stringArray;
        Class<?> clazz;
        block8: {
            block7: {
                if (ClientBase.mc.gameMode == null) {
                    return 0.0f;
                }
                clazz = serverboundMovePlayerPacket.getClass();
                String[] stringArray2 = new String[1];
                String[] stringArray3 = stringArray2;
                stringArray = stringArray2;
                int n2 = 0;
                if (!LF21Client.isMCPMapped) break block7;
                string = "f_134122_";
                break block8;
            }
            String[] stringArray4 = j;
            string = "xRot";
        }
        stringArray3[n2] = string;
        Field field = ReflectionUtil.a(clazz, stringArray);
        try {
            return field.getFloat(serverboundMovePlayerPacket);
        }
        catch (Exception exception) {
            ClientBase.logger.error("Failed to get xrot field", (Throwable)exception);
            return 0.0f;
        }
    }

    public static void a(ServerboundMovePlayerPacket serverboundMovePlayerPacket, float f2) {
        String string;
        String[] stringArray;
        Class<?> clazz;
        block8: {
            block7: {
                if (ClientBase.mc.gameMode == null) {
                    return;
                }
                clazz = serverboundMovePlayerPacket.getClass();
                String[] stringArray2 = new String[1];
                String[] stringArray3 = stringArray2;
                stringArray = stringArray2;
                int n2 = 0;
                if (!LF21Client.isMCPMapped) break block7;
                string = "f_134121_";
                break block8;
            }
            String[] stringArray4 = j;
            string = "yRot";
        }
        stringArray3[n2] = string;
        Field field = ReflectionUtil.a(clazz, stringArray);
        try {
            field.setFloat(serverboundMovePlayerPacket, f2);
        }
        catch (Exception exception) {
            ClientBase.logger.error("Failed to set yrot field", (Throwable)exception);
        }
    }

    public static void a(RenderTarget renderTarget, int n2) {
        Class<?> clazz = renderTarget.getClass();
        String[] stringArray = new String[1];
        String[] stringArray2 = stringArray;
        String[] stringArray3 = stringArray;
        int n3 = 0;
        String string = LF21Client.isMCPMapped ? "f_83924_" : "depthBufferId";
        stringArray2[n3] = string;
        Field field = ReflectionUtil.a(clazz, stringArray3);
        try {
            field.setInt(renderTarget, n2);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void b(ServerboundMovePlayerPacket serverboundMovePlayerPacket, float f2) {
        String string;
        String[] stringArray;
        Class<?> clazz;
        block8: {
            block7: {
                if (ClientBase.mc.gameMode == null) {
                    return;
                }
                clazz = serverboundMovePlayerPacket.getClass();
                String[] stringArray2 = new String[1];
                String[] stringArray3 = stringArray2;
                stringArray = stringArray2;
                int n2 = 0;
                if (!LF21Client.isMCPMapped) break block7;
                string = "f_134122_";
                break block8;
            }
            String[] stringArray4 = j;
            string = "xRot";
        }
        stringArray3[n2] = string;
        Field field = ReflectionUtil.a(clazz, stringArray);
        try {
            field.setFloat(serverboundMovePlayerPacket, f2);
        }
        catch (Exception exception) {
            ClientBase.logger.error("Failed to set xrot field", (Throwable)exception);
        }
    }

    public static Container a(BrewingStandMenu brewingStandMenu) {
        if (brewingStandMenu == null) {
            return null;
        }
        Class<?> clazz = brewingStandMenu.getClass();
        String[] stringArray = new String[1];
        String[] stringArray2 = stringArray;
        String[] stringArray3 = stringArray;
        int n2 = 0;
        String string = LF21Client.isMCPMapped ? "f_39086_" : "brewingStand";
        stringArray2[n2] = string;
        Field field = ReflectionUtil.a(clazz, stringArray3);
        try {
            return (Container)field.get(brewingStandMenu);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Field a(Class<?> clazz, String ... stringArray) {
        if (clazz == null || stringArray == null || stringArray.length == 0) {
            throw new IllegalArgumentException("Class and fieldNames must not be null or empty");
        }
        Exception exception = null;
        Class<?> clazz2 = clazz;
        while (true) {
            if (clazz2 == null) {
                throw new n(exception);
            }
            for (String string : stringArray) {
                if (string == null) continue;
                try {
                    Class<?> clazz3 = clazz2;
                    Field field = clazz3.getDeclaredField(ad.c(clazz3, string));
                    field.setAccessible(true);
                    return field;
                }
                catch (Exception exception2) {
                    exception = exception2;
                }
            }
            clazz2 = clazz2.getSuperclass();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Method a(Class<?> clazz, Class<?>[] classArray, String ... stringArray) {
        if (clazz == null || stringArray == null || stringArray.length == 0) {
            throw new IllegalArgumentException("Class and methodNames must not be null or empty");
        }
        Exception exception = null;
        Class<?> clazz2 = clazz;
        while (true) {
            if (clazz2 == null) {
                throw new n(exception);
            }
            for (String string : stringArray) {
                if (string == null) continue;
                try {
                    Class<?> clazz3 = clazz2;
                    Method method = clazz3.getDeclaredMethod(ad.b(string, clazz3, classArray), classArray);
                    method.setAccessible(true);
                    return method;
                }
                catch (Exception exception2) {
                    exception = exception2;
                }
            }
            clazz2 = clazz2.getSuperclass();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(Camera camera, Vec3 vec3, float f2, float f3) {
        if (camera == null) return;
        if (vec3 == null) {
            return;
        }
        try {
            Method method;
            String[] stringArray;
            Method method2 = h;
            if (method2 == null) {
                String[] stringArray2 = new String[3];
                stringArray = j;
                stringArray2[0] = ReflectionUtil.getMappedMethodName(Camera.class, "setPosition", "(DDD)V");
                stringArray2[1] = "setPosition";
                stringArray2[2] = "m_90584_";
                h = method2 = ReflectionUtil.a(Camera.class, new Class[]{Double.TYPE, Double.TYPE, Double.TYPE}, stringArray2);
            }
            if ((method = i) == null) {
                String[] stringArray3 = new String[3];
                stringArray = j;
                stringArray3[0] = ReflectionUtil.getMappedMethodName(Camera.class, "setRotation", "(FF)V");
                stringArray3[1] = "setRotation";
                stringArray3[2] = "m_90572_";
                i = method = ReflectionUtil.a(Camera.class, new Class[]{Float.TYPE, Float.TYPE}, stringArray3);
            }
            method2.invoke(camera, vec3.x, vec3.y, vec3.z);
            method.invoke(camera, Float.valueOf(f2), Float.valueOf(f3));
            return;
        }
        catch (Throwable throwable) {
            ClientBase.logger.error("Failed to apply FreeCam camera transform", throwable);
        }
    }

    public static void c(int n2) {
        String[] stringArray = new String[2];
        String[] stringArray2 = j;
        stringArray[0] = "missTime";
        stringArray[1] = "f_91078_";
        Field field = ReflectionUtil.a(ClientBase.mc.getClass(), stringArray);
        try {
            field.setInt(ClientBase.mc, n2);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String getMappedFieldName(Class<?> clazz, String string) {
        return Bootstrap.remapField(clazz.getName().replace('.', '/'), string);
    }

    public static String getMappedMethodName(Class<?> clazz, String string, String string2) {
        return Bootstrap.remapMethod(clazz.getName().replace('.', '/'), string, string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Field a(Class<?> clazz, String string) throws NoSuchFieldException {
        NoSuchFieldException noSuchFieldException;
        NoSuchFieldException noSuchFieldException2 = null;
        for (Class<?> clazz2 = clazz; clazz2 != null; clazz2 = clazz2.getSuperclass()) {
            String string2 = Bootstrap.remapField(clazz2.getName().replace('.', '/'), string);
            try {
                Class<?> clazz3 = clazz2;
                return clazz3.getDeclaredField(ad.c(clazz3, string2));
            }
            catch (NoSuchFieldException noSuchFieldException22) {
                noSuchFieldException2 = noSuchFieldException22;
                if (string2.equals(string)) continue;
                try {
                    Class<?> clazz4 = clazz2;
                    return clazz4.getDeclaredField(ad.c(clazz4, string));
                }
                catch (NoSuchFieldException noSuchFieldException3) {
                    noSuchFieldException2 = noSuchFieldException3;
                }
                continue;
            }
        }
        if (noSuchFieldException2 != null) {
            noSuchFieldException = noSuchFieldException2;
            throw noSuchFieldException;
        }
        noSuchFieldException = new NoSuchFieldException(string);
        throw noSuchFieldException;
    }

    private static long b(Class<?> clazz, String string) {
        String string2 = clazz.getName() + "/" + string;
        long l2 = e.getLong((Object)string2);
        if (l2 != 0L) {
            return l2;
        }
        try {
            l2 = g.objectFieldOffset(ReflectionUtil.a(clazz, string));
            e.put((Object)string2, l2);
            return l2;
        }
        catch (NoSuchFieldException noSuchFieldException) {
            throw new RuntimeException(noSuchFieldException);
        }
    }

    private static ObjectLongPair<Object> c(Class<?> clazz, String string) {
        String string2 = clazz.getName() + "/" + string;
        ObjectLongPair objectLongPair = (ObjectLongPair)f.get((Object)string2);
        if (objectLongPair != null) {
            return objectLongPair;
        }
        try {
            Field field = ReflectionUtil.a(clazz, string);
            ObjectLongImmutablePair objectLongImmutablePair = new ObjectLongImmutablePair(g.staticFieldBase(field), g.staticFieldOffset(field));
            f.put((Object)string2, (Object)objectLongImmutablePair);
            return objectLongImmutablePair;
        }
        catch (NoSuchFieldException noSuchFieldException) {
            throw new RuntimeException(noSuchFieldException);
        }
    }

    public static Object getStaticField(Object object, String string, String string2) {
        Class<?> clazz = ReflectionUtil.a(string2);
        if (object == null) {
            ObjectLongPair<Object> objectLongPair = ReflectionUtil.c(clazz, string);
            return g.getObject(objectLongPair.first(), objectLongPair.secondLong());
        }
        try {
            Field field = ReflectionUtil.a(object.getClass(), string);
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void setInstanceField(Object object, Object object2, String string, String string2) {
        Class<?> clazz = ReflectionUtil.a(string2);
        g.putObject(object, ReflectionUtil.b(clazz, string), object2);
    }

    public static Object getInstanceField(Object object, String string) {
        if (object == null) {
            return null;
        }
        try {
            Field field = ReflectionUtil.a(object.getClass(), string);
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void a(Object object, String string, String string2) {
        Class<?> clazz = ReflectionUtil.a(string2);
        ObjectLongPair<Object> objectLongPair = ReflectionUtil.c(clazz, string);
        g.putObject(objectLongPair.first(), objectLongPair.secondLong(), object);
    }

    public static void a(Object object, Object object2, String string) {
        Class<?> clazz = object.getClass();
        g.putObject(object, ReflectionUtil.b(clazz, string), object2);
    }

    public static void b(Object object, String string, String string2) {
        ReflectionUtil.a(object, string, string2);
    }

    public static Class<?> a(String string) {
        Class clazz = (Class)c.get((Object)string);
        if (clazz != null) {
            return clazz;
        }
        try {
            Class<?> clazz2 = Class.forName(ad.a(string.replace("/", ".")));
            c.put((Object)string, clazz2);
            return clazz2;
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new RuntimeException(classNotFoundException);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block12: {
            block11: {
                var5 = new String[43];
                var3_1 = 0;
                var2_2 = "iB7m{ hXD3tv\bmo`*+{;T\bfok*'y=T\u0018MQ;vw*/\u007f_riw:/rB=n2(fn\\6\u0004rb=n\tmoc)&\u007f=:o\u000bxU&J}=f\u007fY=t\u0005#v\u00143D\bmoa#\"v9T\u0018MQ;vw*/\u007f_r}w:/sB=n2(fn\\6\bsb=n^/|\u007f\bmok+\"\u007f>T\brb=n^/|\u007f#MQ;vw*/\u007f_riw:/yY5rf\rcbS9^w\"nr\u00104sw\"k\u0018MQ;vw*/\u007f_riw:/sB=n2(fn\\6\u0013h\\3ia\u001cjoU4s|+kH_'tf\bfY!iF'bn\u001fMQ;vw*/\u007f_riw:/e_\u0018o\u007f>Kn\\3c2(fn\\6\t\u007fX7O|=nmU/H\\3ianneTrww:gdT\u001c{\u007f+|+]'ifnadDrxwna~\\>:}</n]\"nk\u0004rb=n\u0004sb=n\u0013h\\3ia\u001cjoU4s|+kH_'tf\u000bxU&H}:n\u007fY=t\bfok*'v;T.H\\3ianneTr|{+co~3ww=/fE!n2 `\u007f\u00100\u007f2 zg\\ru`njf@&c\u0004sb=n\u000bxU&J}=f\u007fY=t\roU\"nz\fzmV7h[*\bmoj)+|;T\u0018MQ;vw*/\u007f_r}w:/rB=n2(fn\\6\u0006#t\u0016^;\u0018\tmoc)&\u007f=9o\tmoc**{63o$MQ;vw*/\u007f_r|}<ln\u0010<\u007fj:/y_&{f'`e\u0010\"{q%j\u007f\u000bxU&H}:n\u007fY=t\u000be_\u0018o\u007f>Kn\\3c\bmok+\"y7T\u000fyY5rf\rcbS9^w\"nr(MQ;vw*/\u007f_r{b>cr\u0010\u0014hw+Lj]rys#jyQrn`/axV=h\u007f\tmoc**{62o";
                var4_3 = "iB7m{ hXD3tv\bmo`*+{;T\bfok*'y=T\u0018MQ;vw*/\u007f_riw:/rB=n2(fn\\6\u0004rb=n\tmoc)&\u007f=:o\u000bxU&J}=f\u007fY=t\u0005#v\u00143D\bmoa#\"v9T\u0018MQ;vw*/\u007f_r}w:/sB=n2(fn\\6\bsb=n^/|\u007f\bmok+\"\u007f>T\brb=n^/|\u007f#MQ;vw*/\u007f_riw:/yY5rf\rcbS9^w\"nr\u00104sw\"k\u0018MQ;vw*/\u007f_riw:/sB=n2(fn\\6\u0013h\\3ia\u001cjoU4s|+kH_'tf\bfY!iF'bn\u001fMQ;vw*/\u007f_riw:/e_\u0018o\u007f>Kn\\3c2(fn\\6\t\u007fX7O|=nmU/H\\3ianneTrww:gdT\u001c{\u007f+|+]'ifnadDrxwna~\\>:}</n]\"nk\u0004rb=n\u0004sb=n\u0013h\\3ia\u001cjoU4s|+kH_'tf\u000bxU&H}:n\u007fY=t\bfok*'v;T.H\\3ianneTr|{+co~3ww=/fE!n2 `\u007f\u00100\u007f2 zg\\ru`njf@&c\u0004sb=n\u000bxU&J}=f\u007fY=t\roU\"nz\fzmV7h[*\bmoj)+|;T\u0018MQ;vw*/\u007f_r}w:/rB=n2(fn\\6\u0006#t\u0016^;\u0018\tmoc)&\u007f=9o\tmoc**{63o$MQ;vw*/\u007f_r|}<ln\u0010<\u007fj:/y_&{f'`e\u0010\"{q%j\u007f\u000bxU&H}:n\u007fY=t\u000be_\u0018o\u007f>Kn\\3c\bmok+\"y7T\u000fyY5rf\rcbS9^w\"nr(MQ;vw*/\u007f_r{b>cr\u0010\u0014hw+Lj]rys#jyQrn`/axV=h\u007f\tmoc**{62o".length();
                var1_4 = 12;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block11;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = ReflectionUtil.a(ReflectionUtil.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "moc)&\u007f=:o\tmoc)&\u007f=9o";
                    var4_3 = "moc)&\u007f=:o\tmoc)&\u007f=9o".length();
                    var1_4 = 9;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block11;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = ReflectionUtil.a(ReflectionUtil.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    break block12;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl37:
                // 1 sources

                ** continue;
            }
        }
        ReflectionUtil.j = var5;
        ReflectionUtil.a = MethodHandles.lookup();
        ReflectionUtil.b = new Object2ObjectOpenHashMap(500);
        ReflectionUtil.c = new Object2ObjectOpenHashMap(500);
        ReflectionUtil.d = new Object2ObjectOpenHashMap(1000);
        ReflectionUtil.e = new Object2LongRBTreeMap();
        ReflectionUtil.f = new Object2ObjectOpenHashMap();
        try {
            var7_6 = Unsafe.class.getDeclaredField("theUnsafe");
            var7_6.setAccessible(true);
            var6_8 = (Unsafe)var7_6.get(null);
        }
        catch (Exception var7_7) {
            throw new RuntimeException(var7_7);
        }
        ReflectionUtil.g = var6_8;
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xF);
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
                    case 0 -> 11;
                    case 1 -> 48;
                    case 2 -> 82;
                    case 3 -> 26;
                    case 4 -> 18;
                    case 5 -> 78;
                    default -> 15;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

