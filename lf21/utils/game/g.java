/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import lf21.ClientBase;
import lf21.utils.game.f;
import lf21.utils.game.l;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import lombok.Generated;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.antlr.v4.runtime.misc.OrderedHashSet;

public final class g
extends ClientBase {
    public static a a(a a2) {
        return new a(Mth.wrapDegrees((float)a2.e()), Mth.wrapDegrees((float)a2.f()));
    }

    public static a a(a a2, a a3, double d2) {
        float f2 = a3.e();
        float f3 = a3.f();
        float f4 = a2.e();
        float f5 = a2.f();
        if (d2 != 0.0) {
            double d3;
            double d4;
            double d5;
            float f6;
            block11: {
                f6 = (float)d2;
                d5 = Mth.wrapDegrees((float)(a3.e() - a2.e()));
                d4 = f3 - f5;
                d3 = Math.sqrt(d5 * d5 + d4 * d4);
                if (Double.isFinite(d3) && !(d3 < 1.0E-4)) break block11;
                return new a(f2, Mth.clamp((float)f3, (float)-90.0f, (float)90.0f)).a(Float.valueOf(((Double)g.mc.options.sensitivity().get()).floatValue()));
            }
            double d6 = Math.abs(d5 / d3);
            double d7 = Math.abs(d4 / d3);
            double d8 = (double)f6 * d6;
            double d9 = (double)f6 * d7;
            float f7 = (float)Math.max(Math.min(d5, d8), -d8);
            float f8 = (float)Math.max(Math.min(d4, d9), -d9);
            f2 = f4 + f7;
            f3 = f5 + f8;
        }
        boolean bl = Math.random() > 0.8;
        boolean bl2 = bl;
        int n2 = 1;
        while (true) {
            a a4;
            a a5;
            block12: {
                if (n2 > (int)(2.0 + Math.random() * 2.0)) break;
                if (!bl2) break block12;
                f2 += (float)((Math.random() - 0.5) / 1.0E8);
                f3 -= (float)(Math.random() / 2.0E8);
            }
            if ((a5 = (a4 = new a(f2, f3)).a(Float.valueOf(((Double)g.mc.options.sensitivity().get()).floatValue()))) != null) {
                f2 = a5.e();
                f3 = Mth.clamp((float)a5.f(), (float)-90.0f, (float)90.0f);
            }
            ++n2;
        }
        return new a(f2, f3);
    }

    public static float a(float f2, float f3) {
        if (Math.abs(f2) < f3) {
            return f2;
        }
        if (f2 > 0.0f) {
            return f3;
        }
        if (f2 < 0.0f) {
            return -f3;
        }
        return 0.0f;
    }

    public static a a(Vec3 vec3, Vec3 vec32) {
        double d2 = vec32.x - vec3.x;
        double d3 = vec32.y - vec3.y;
        double d4 = vec32.z - vec3.z;
        double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f2 = g.a(Math.atan2(d4, d2)) - 90.0f;
        float f3 = g.a(-Math.atan2(d3, d5));
        return new a(f2, f3);
    }

    public static float a(double d2) {
        return (float)(d2 * 180.0 / Math.PI);
    }

    public static a b(Vec3 vec3, Vec3 vec32) {
        double d2 = vec32.x - vec3.x;
        double d3 = vec32.y - vec3.y;
        double d4 = vec32.z - vec3.z;
        return g.a(d2, d3, d4);
    }

    public static boolean a(float f2, LivingEntity livingEntity) {
        boolean bl;
        block11: {
            block10: {
                if (g.mc.player == null) {
                    return false;
                }
                Vec3 vec3 = g.mc.player.getEyePosition(1.0f);
                Vec3 vec32 = new Vec3(livingEntity.getX(), livingEntity.getY() + (double)livingEntity.getBbHeight() * 0.5, livingEntity.getZ());
                Vec3 vec33 = g.mc.player.getLookAngle();
                Vec3 vec34 = vec32.subtract(vec3);
                if (vec34.lengthSqr() < 1.0E-7) {
                    return true;
                }
                Vec3 vec35 = vec34.normalize();
                double d2 = vec33.dot(vec35);
                double d3 = Math.toDegrees(Math.acos(d2));
                if (!(f2 >= 180.0f) && !(d3 <= (double)f2)) break block10;
                bl = true;
                break block11;
            }
            bl = false;
        }
        return bl;
    }

    public static a a(Vec3 vec3) {
        if (g.mc.player == null) {
            return null;
        }
        return g.c(g.mc.player.position().add(0.0, (double)g.mc.player.getEyeHeight(), 0.0), vec3);
    }

    public static a c(Vec3 vec3, Vec3 vec32) {
        Vec3 vec33 = vec32.add(0.0, -0.7, 0.0).subtract(vec3);
        double d2 = Math.hypot(vec33.x, vec33.z);
        double d3 = 0.03;
        double d4 = 1.5;
        float f2 = (float)(Mth.atan2((double)vec33.z, (double)vec33.x) * 57.29577951308232) - 90.0f;
        double d5 = d2 / 1.5;
        double d6 = 0.015 * d5 * d5;
        double d7 = vec33.y + d6;
        double d8 = Math.atan2(d7, d2);
        float f3 = (float)(-(d8 * 57.29577951308232));
        return new a(f2, f3);
    }

    public static a a(BlockPos blockPos, float f2) {
        Vec3 vec3 = new Vec3(g.mc.player.getX() + g.mc.player.getDeltaMovement().x * (double)f2, g.mc.player.getY() + (double)g.mc.player.getEyeHeight() + g.mc.player.getDeltaMovement().y() * (double)f2, g.mc.player.getZ() + g.mc.player.getDeltaMovement().z() * (double)f2);
        double d2 = (double)blockPos.getX() - vec3.x + 0.5;
        double d3 = (double)blockPos.getY() - vec3.y + 0.5;
        double d4 = (double)blockPos.getZ() - vec3.z + 0.5;
        return g.a(g.b(d2), g.b(d3), g.b(d4));
    }

    public static a a(double d2, double d3, double d4) {
        double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f2 = (float)Math.toDegrees(Math.atan2(d4, d2)) - 90.0f;
        float f3 = (float)(-Math.toDegrees(Math.atan2(d3, d5)));
        return new a(Mth.wrapDegrees((float)f2), Mth.wrapDegrees((float)f3));
    }

    private static double b(double d2) {
        return d2 + lf21.utils.b.a.a(0.05, 0.08) * (lf21.utils.b.a.a(0.0, 1.0) * 2.0 - 1.0);
    }

    public static double a(Entity entity, Vec3 vec3, a a2) {
        AABB aABB = g.b(entity);
        HitResult hitResult = g.a(aABB, a2, vec3, 6.0);
        if (hitResult != null) {
            Vec3 vec32 = hitResult.getLocation();
            return vec32.distanceTo(vec3);
        }
        return 1000.0;
    }

    public static List<Float> a() {
        return List.of(Float.valueOf(g.mc.player.getEyeHeight()));
    }

    public static double a(Entity entity, a a2) {
        double d2 = Double.MAX_VALUE;
        Iterator<Float> iterator = g.a().iterator();
        while (iterator.hasNext()) {
            double d3 = iterator.next().floatValue();
            Vec3 vec3 = new Vec3(g.mc.player.getX(), g.mc.player.getY(), g.mc.player.getZ());
            Vec3 vec32 = vec3.add(0.0, d3, 0.0);
            d2 = Math.min(d2, g.a(entity, vec32, a2));
        }
        return d2;
    }

    public static HitResult a(AABB aABB, a a2, Vec3 vec3, double d2) {
        Vec3 vec32 = g.c(a2.e(), a2.f());
        Vec3 vec33 = vec3.add(vec32.x * d2, vec32.y * d2, vec32.z * d2);
        return ProjectileUtil.getEntityHitResult((Entity)g.mc.player, (Vec3)vec3, (Vec3)vec33, (AABB)aABB, entity -> {
            boolean bl;
            block5: {
                block4: {
                    if (entity.isSpectator() || !entity.isPickable()) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        }, (double)(d2 * d2));
    }

    public static float a(float f2, float f3, float f4) {
        return g.b(f3, f4, f2);
    }

    public static float b(float f2, float f3, float f4) {
        float f5 = Mth.wrapDegrees((float)(f3 - f2));
        if (f5 > f4) {
            f5 = f4;
        }
        if (f5 < -f4) {
            f5 = -f4;
        }
        return f2 + f5;
    }

    public static float b(float f2, float f3) {
        float f4 = Math.abs(f2 - f3) % 360.0f;
        if (f4 > 180.0f) {
            f4 = 0.0f;
        }
        return f4;
    }

    public static float a(float f2, float f3, float f4, float f5) {
        float f6 = f4 * f4 * f4 * f4 - f5 * (f5 * (f2 * f2) + 2.0f * f3 * (f4 * f4));
        return (float)Math.toDegrees(Math.atan(((double)(f4 * f4) - Math.sqrt(f6)) / (double)(f5 * f2)));
    }

    public static float[] b(Vec3 vec3) {
        double d2;
        float f2;
        double d3;
        double d4;
        double d5;
        Vec3 vec32;
        block24: {
            float f3;
            float[] fArray;
            block26: {
                block25: {
                    block23: {
                        if (g.mc.player != null && g.mc.level != null) break block23;
                        return null;
                    }
                    vec32 = g.mc.player.getEyePosition();
                    d5 = 1.5;
                    d4 = 0.03;
                    d3 = 0.99;
                    double d6 = vec3.x - vec32.x;
                    double d7 = vec3.y - vec32.y;
                    double d8 = vec3.z - vec32.z;
                    f2 = (float)(Math.toDegrees(Math.atan2(d8, d6)) - 90.0);
                    d2 = Math.sqrt(d6 * d6 + d8 * d8);
                    if (d2 != 0.0) break block24;
                    float[] fArray2 = new float[2];
                    fArray2[0] = f2;
                    float[] fArray3 = fArray2;
                    fArray = fArray2;
                    int n2 = 1;
                    if (!(d7 > 0.0)) break block25;
                    f3 = -90.0f;
                    break block26;
                }
                f3 = 90.0f;
            }
            fArray3[n2] = f3;
            return fArray;
        }
        block18: for (float f4 = 90.0f; f4 >= -90.0f; f4 -= 0.5f) {
            double d9 = Math.toRadians(f4);
            double d10 = -Math.sin(Math.toRadians(f2)) * Math.cos(d9);
            double d11 = -Math.sin(d9);
            double d12 = Math.cos(Math.toRadians(f2)) * Math.cos(d9);
            double d13 = Math.sqrt(d10 * d10 + d11 * d11 + d12 * d12);
            d10 = d10 / d13 * d5;
            d11 = d11 / d13 * d5;
            d12 = d12 / d13 * d5;
            Vec3 vec33 = new Vec3(vec32.x, vec32.y, vec32.z);
            Vec3 vec34 = new Vec3(d10, d11, d12);
            for (int i2 = 0; i2 < 300; ++i2) {
                vec33 = vec33.add(vec34);
                vec34 = new Vec3(vec34.x * d3, vec34.y * d3 - d4, vec34.z * d3);
                if (vec33.y < (double)(g.mc.level.getMinBuildHeight() - 10)) {
                    continue block18;
                }
                double d14 = Math.sqrt(Math.pow(vec33.x - vec32.x, 2.0) + Math.pow(vec33.z - vec32.z, 2.0));
                if (!(d14 >= d2)) {
                    continue;
                }
                if (Math.abs(vec33.y - vec3.y) < 1.0) {
                    return new float[]{Mth.wrapDegrees((float)f2), Mth.wrapDegrees((float)f4)};
                }
                if (!(vec34.y < 0.0) || !(vec33.y < vec3.y)) continue;
                continue block18;
            }
        }
        return null;
    }

    public static l a(Entity entity) {
        return g.a(entity, 3.0);
    }

    public static l a(Entity entity, double d2) {
        double d3;
        double d4;
        Vec3 vec3 = new Vec3(g.mc.player.getX(), g.mc.player.getY(), g.mc.player.getZ());
        Vec3 vec32 = vec3.add(0.0, (double)g.mc.player.getEyeHeight(), 0.0);
        AABB aABB = entity.getBoundingBox();
        double d5 = aABB.minX;
        double d6 = aABB.minY;
        double d7 = aABB.minZ;
        double d8 = aABB.maxX;
        double d9 = aABB.maxY;
        double d10 = aABB.maxZ;
        double d11 = 0.1;
        OrderedHashSet orderedHashSet = new OrderedHashSet();
        orderedHashSet.add((Object)g.a(vec32, aABB));
        orderedHashSet.add((Object)new Vec3((d5 + d8) / 2.0, (d6 + d9) / 2.0, (d7 + d10) / 2.0));
        for (d4 = d5; d4 <= d8; d4 += d11) {
            for (d3 = d6; d3 <= d9; d3 += d11) {
                orderedHashSet.add((Object)new Vec3(d4, d3, d7));
                orderedHashSet.add((Object)new Vec3(d4, d3, d10));
            }
        }
        for (d4 = d5; d4 <= d8; d4 += d11) {
            for (d3 = d7; d3 <= d10; d3 += d11) {
                orderedHashSet.add((Object)new Vec3(d4, d6, d3));
                orderedHashSet.add((Object)new Vec3(d4, d9, d3));
            }
        }
        for (d4 = d6; d4 <= d9; d4 += d11) {
            for (d3 = d7; d3 <= d10; d3 += d11) {
                orderedHashSet.add((Object)new Vec3(d5, d4, d3));
                orderedHashSet.add((Object)new Vec3(d8, d4, d3));
            }
        }
        for (Vec3 vec33 : orderedHashSet) {
            a a2 = g.d(vec32, vec33);
            HitResult hitResult = g.a(a2, d2);
            if (hitResult == null) continue;
            if (!g.a(vec32, hitResult, entity, d2)) {
                continue;
            }
            try {
                Vec3 vec34 = hitResult.getLocation();
                a a3 = RotationHandler.b != null ? RotationHandler.b : new a(g.mc.player.getYRot(), g.mc.player.getXRot());
                a a4 = a3;
                return new l(vec32, vec34, vec34.distanceTo(vec32), g.b(a2.e(), a2.f(), a4.a, a4.b));
            }
            catch (Exception exception) {
                logger.error("er here");
                logger.error((Object)exception);
                exception.printStackTrace();
                return null;
            }
        }
        return new l(vec32, vec32, 1000.0, null);
    }

    public static a a(Entity entity, float f2, float f3, float f4) {
        if (entity == null) {
            return null;
        }
        LocalPlayer localPlayer = g.mc.player;
        if (localPlayer == null) {
            return null;
        }
        Random random = new Random();
        double d2 = (random.nextDouble() - 0.5) * (double)entity.getBbWidth() * 0.5 * (double)f2;
        double d3 = (random.nextDouble() - 0.5) * (double)entity.getBbWidth() * 0.5 * (double)f2;
        double d4 = entity.getY();
        d4 = f4 <= 0.1f ? (d4 = d4 + (double)(entity.getBbHeight() * 0.1f)) : (f4 >= 0.9f ? (d4 = d4 + (double)(entity.getEyeHeight() * (0.8f + random.nextFloat() * 0.2f))) : (d4 = d4 + (double)(entity.getBbHeight() * Mth.clamp((float)f4, (float)0.1f, (float)0.9f))));
        double d5 = (random.nextDouble() - 0.5) * (double)entity.getBbHeight() * 0.3 * (double)f3;
        double d6 = entity.getX() + d2;
        double d7 = d4 + d5;
        double d8 = entity.getZ() + d3;
        double d9 = d6 - localPlayer.getX();
        double d10 = d7 - (localPlayer.getY() + (double)localPlayer.getEyeHeight());
        double d11 = d8 - localPlayer.getZ();
        double d12 = Mth.sqrt((float)((float)(d9 * d9 + d11 * d11)));
        float f5 = (float)(Math.atan2(d11, d9) * 180.0 / Math.PI) - 90.0f;
        float f6 = (float)(-(Math.atan2(d10, d12) * 180.0 / Math.PI));
        float f7 = localPlayer.getYRot() + Mth.wrapDegrees((float)(f5 - localPlayer.getYRot()));
        float f8 = localPlayer.getXRot() + Mth.wrapDegrees((float)(f6 - localPlayer.getXRot()));
        f8 = Mth.clamp((float)f8, (float)-90.0f, (float)90.0f);
        return g.b(f7, f8, RotationHandler.b.a, RotationHandler.b.b);
    }

    public static a b(float f2, float f3, float f4, float f5) {
        float f6 = (float)((Double)g.mc.options.sensitivity().get() * (double)0.6f + (double)0.2f);
        float f7 = f6 * f6 * f6 * 1.2f;
        float f8 = f2 - f4;
        float f9 = f3 - f5;
        float f10 = f8 - f8 % f7;
        float f11 = f9 - f9 % f7;
        float f12 = f4 + f10;
        float f13 = f5 + f11;
        return new a(f12, f13);
    }

    private static AABB b(Entity entity) {
        return entity.getBoundingBox().inflate((double)entity.getPickRadius());
    }

    private static boolean a(Vec3 vec3, HitResult hitResult, Entity entity, double d2) {
        block9: {
            boolean bl;
            block11: {
                block10: {
                    if (hitResult == null) {
                        return false;
                    }
                    if (hitResult.getType() != HitResult.Type.ENTITY || ((EntityHitResult)hitResult).getEntity() != entity) break block9;
                    Vec3 vec32 = hitResult.getLocation();
                    if (!g.a(entity.getBoundingBox(), vec3) && !(vec32.distanceTo(vec3) <= d2 + 0.05)) break block10;
                    bl = true;
                    break block11;
                }
                bl = false;
            }
            return bl;
        }
        return false;
    }

    private static HitResult a(a a2, double d2) {
        HitResult hitResult;
        block4: {
            AABB aABB;
            double d3 = Math.max(0.1, d2);
            hitResult = f.a(d3, 1.0f, false, a2);
            Vec3 vec3 = g.mc.player.getEyePosition(1.0f);
            double d4 = d3 * d3;
            if (hitResult != null) {
                d4 = hitResult.getLocation().distanceToSqr(vec3);
            }
            Vec3 vec32 = g.c(a2.e(), a2.f());
            Vec3 vec33 = vec3.add(vec32.x * d3, vec32.y * d3, vec32.z * d3);
            EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult((Entity)g.mc.player, (Vec3)vec3, (Vec3)vec33, (AABB)(aABB = g.mc.player.getBoundingBox().expandTowards(vec32.scale(d3)).inflate(1.0, 1.0, 1.0)), entity -> {
                boolean bl;
                block5: {
                    block4: {
                        if (entity.isSpectator() || !entity.isPickable()) break block4;
                        bl = true;
                        break block5;
                    }
                    bl = false;
                }
                return bl;
            }, (double)d4);
            if (entityHitResult != null) {
                Vec3 vec34 = entityHitResult.getLocation();
                double d5 = vec3.distanceToSqr(vec34);
                if (!(d5 < d4) && hitResult != null) break block4;
                hitResult = entityHitResult;
            }
        }
        return hitResult;
    }

    public static Vec3 c(float f2, float f3) {
        float f4 = Mth.cos((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = Mth.sin((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f6 = -Mth.cos((float)(-f3 * ((float)Math.PI / 180)));
        float f7 = Mth.sin((float)(-f3 * ((float)Math.PI / 180)));
        return new Vec3((double)(f5 * f6), (double)f7, (double)(f4 * f6));
    }

    public static boolean a(AABB aABB, Vec3 vec3) {
        boolean bl;
        block13: {
            block12: {
                if (!(vec3.x > aABB.minX) || !(vec3.x < aABB.maxX)) break block12;
                if (!(vec3.y > aABB.minY)) break block12;
                if (!(vec3.y < aABB.maxY)) break block12;
                if (!(vec3.z > aABB.minZ)) break block12;
                if (!(vec3.z < aABB.maxZ)) break block12;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    public static a d(Vec3 vec3, Vec3 vec32) {
        double d2 = vec32.x - vec3.x;
        double d3 = vec32.y - vec3.y;
        double d4 = vec32.z - vec3.z;
        double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f2 = (float)Math.toDegrees(Math.atan2(d4, d2)) - 90.0f;
        float f3 = (float)(-Math.toDegrees(Math.atan2(d3, d5)));
        return new a(Mth.wrapDegrees((float)f2), Mth.wrapDegrees((float)f3));
    }

    public static Vec3 a(Vec3 vec3, AABB aABB) {
        double d2 = Math.max(aABB.minX, Math.min(vec3.x, aABB.maxX));
        double d3 = Math.max(aABB.minY, Math.min(vec3.y, aABB.maxY));
        double d4 = Math.max(aABB.minZ, Math.min(vec3.z, aABB.maxZ));
        return new Vec3(d2, d3, d4);
    }

    public static a c(Vec3 vec3) {
        return g.b(vec3.x, vec3.y, vec3.z);
    }

    public static a b(double d2, double d3, double d4) {
        if (g.mc.player == null) {
            return new a(0.0f, 0.0f);
        }
        return g.a(d2, d3, d4, g.mc.player.getX(), g.mc.player.getY() + (double)g.mc.player.getEyeHeight(g.mc.player.getPose()), g.mc.player.getZ());
    }

    private static double c(double d2) {
        return ((d2 + 180.0) % 360.0 + 360.0) % 360.0 - 180.0;
    }

    public static double d(float f2, float f3) {
        double d2 = f2 - f3;
        return g.c(d2);
    }

    public static a a(double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = g.b(d2 - d5);
        double d9 = g.b(d3 - d6);
        double d10 = g.b(d4 - d7);
        double d11 = Mth.sqrt((float)((float)(d8 * d8 + d10 * d10)));
        float f2 = (float)(Math.atan2(d10, d8) * 180.0 / Math.PI) - 90.0f;
        float f3 = (float)(-(Math.atan2(d9, d11) * 180.0 / Math.PI));
        return new a(f2, f3);
    }

    public static a c(Entity entity) {
        if (entity == null) {
            return null;
        }
        double d2 = entity.getX() - g.mc.player.getX();
        double d3 = entity.getZ() - g.mc.player.getZ();
        double d4 = entity.getY() + (double)entity.getEyeHeight() - (g.mc.player.getY() + (double)g.mc.player.getEyeHeight());
        return g.c(d2, d4, d3);
    }

    public static a c(double d2, double d3, double d4) {
        double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f2 = (float)Math.toDegrees(Math.atan2(d4, d2)) - 90.0f;
        float f3 = (float)(-Math.toDegrees(Math.atan2(d3, d5)));
        return new a(Mth.wrapDegrees((float)f2), Mth.wrapDegrees((float)f3));
    }

    public static boolean a(Entity entity, float f2) {
        a a2 = g.c(entity);
        float f3 = Math.abs(g.mc.player.getYRot() % 360.0f - a2.e());
        float f4 = Math.abs(Math.min(f3, 360.0f - f3));
        boolean bl = f4 <= f2;
        return bl;
    }

    public static Vec3 b(a a2) {
        float f2 = (float)Math.cos(-a2.e() * ((float)Math.PI / 180) - (float)Math.PI);
        float f3 = (float)Math.sin(-a2.e() * ((float)Math.PI / 180) - (float)Math.PI);
        float f4 = (float)(-Math.cos(-a2.f() * ((float)Math.PI / 180)));
        float f5 = (float)Math.sin(-a2.f() * ((float)Math.PI / 180));
        return new Vec3((double)(f3 * f4), (double)f5, (double)(f2 * f4));
    }

    @Generated
    private g() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

