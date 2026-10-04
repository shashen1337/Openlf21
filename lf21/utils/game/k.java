/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import lf21.ClientBase;
import lf21.utils.game.f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class k {
    public double a;
    public double b;
    public double c;
    private double d;
    private double e;
    private double f;
    private final float g;
    private final float h;
    private final float i;
    private float j;

    public k(double d2, double d3, double d4, double d5, double d6, double d7, float f2, float f3, float f4) {
        this.a = d2;
        this.b = d3;
        this.c = d4;
        this.d = d5;
        this.e = d6;
        this.f = d7;
        this.g = f2;
        this.h = f3;
        this.i = f4;
    }

    public k(Player player) {
        this(player.getX(), player.getY(), player.getZ(), player.getDeltaMovement().x, player.getDeltaMovement().y, player.getDeltaMovement().z, player.getYRot(), player.xxa, player.zza);
        float f2;
        float f3 = player.level().getBlockState(player.blockPosition()).getBlock().getJumpFactor();
        float f4 = player.level().getBlockState(player.getOnPos()).getBlock().getJumpFactor();
        this.j = f2 = 0.42f * ((double)f3 == 1.0 ? f4 : f3) + player.getJumpBoostPower();
    }

    private void a() {
        float f2 = this.h;
        float f3 = this.i;
        float f4 = f2 * f2 + f3 * f3;
        if (f4 >= 1.0E-4f) {
            float f5;
            f4 = Mth.sqrt((float)f4);
            if (f5 < 1.0f) {
                f4 = 1.0f;
            }
            float f6 = this.j;
            if (ClientBase.mc.player.isSprinting()) {
                f6 *= 1.3f;
            }
            f4 = f6 / f4;
            float f7 = Mth.sin((float)(this.g * (float)Math.PI / 180.0f));
            float f8 = Mth.cos((float)(this.g * (float)Math.PI / 180.0f));
            this.d += (double)((f2 *= f4) * f8 - (f3 *= f4) * f7);
            this.f += (double)(f3 * f8 + f2 * f7);
        }
        this.e -= 0.08;
        this.e *= (double)0.98f;
        this.a += this.d;
        this.b += this.e;
        this.c += this.f;
    }

    private void b() {
        float f2 = this.h * 0.98f;
        float f3 = this.i * 0.98f;
        float f4 = f2 * f2 + f3 * f3;
        if (f4 >= 1.0E-4f) {
            float f5;
            f4 = Mth.sqrt((float)f4);
            if (f5 < 1.0f) {
                f4 = 1.0f;
            }
            float f6 = this.j;
            if (ClientBase.mc.player.isSprinting()) {
                f6 *= 1.3f;
            }
            f4 = f6 / f4;
            float f7 = Mth.sin((float)(this.g * (float)Math.PI / 180.0f));
            float f8 = Mth.cos((float)(this.g * (float)Math.PI / 180.0f));
            this.d += (double)((f2 *= f4) * f8 - (f3 *= f4) * f7);
            this.f += (double)(f3 * f8 + f2 * f7);
        }
        this.e -= 0.08;
        this.e *= (double)0.98f;
        this.a += this.d;
        this.b += this.e;
        this.c += this.f;
        this.d *= 0.91;
        this.f *= 0.91;
    }

    public BlockPos a(int n2) {
        for (int i2 = 0; i2 < n2; ++i2) {
            Vec3 vec3 = new Vec3(this.a, this.b, this.c);
            this.b();
            Vec3 vec32 = new Vec3(this.a, this.b, this.c);
            float f2 = ClientBase.mc.player.getBbWidth() / 2.0f;
            BlockPos blockPos = this.a(vec3, vec32);
            if (blockPos != null) {
                return blockPos;
            }
            blockPos = this.a(vec3.add((double)f2, 0.0, (double)f2), vec32);
            if (blockPos != null) {
                return blockPos;
            }
            blockPos = this.a(vec3.add((double)(-f2), 0.0, (double)f2), vec32);
            if (blockPos != null) {
                return blockPos;
            }
            blockPos = this.a(vec3.add((double)f2, 0.0, (double)(-f2)), vec32);
            if (blockPos != null) {
                return blockPos;
            }
            blockPos = this.a(vec3.add((double)(-f2), 0.0, (double)(-f2)), vec32);
            if (blockPos != null) {
                return blockPos;
            }
            blockPos = this.a(vec3.add((double)f2, 0.0, (double)(f2 / 2.0f)), vec32);
            if (blockPos != null) {
                return blockPos;
            }
            blockPos = this.a(vec3.add((double)(-f2), 0.0, (double)(f2 / 2.0f)), vec32);
            if (blockPos != null) {
                return blockPos;
            }
            blockPos = this.a(vec3.add((double)(f2 / 2.0f), 0.0, (double)f2), vec32);
            if (blockPos != null) {
                return blockPos;
            }
            blockPos = this.a(vec3.add((double)(f2 / 2.0f), 0.0, (double)(-f2)), vec32);
            if (blockPos == null) continue;
            return blockPos;
        }
        return null;
    }

    private BlockPos a(Vec3 vec3, Vec3 vec32) {
        BlockHitResult blockHitResult;
        HitResult hitResult = lf21.utils.game.f.a(vec3, vec32, false, false, false, (Entity)ClientBase.mc.player);
        if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK && hitResult instanceof BlockHitResult && (blockHitResult = (BlockHitResult)hitResult).getDirection() == Direction.UP) {
            return blockHitResult.getBlockPos();
        }
        return null;
    }

    public void b(int n2) {
        for (int i2 = 0; i2 < n2; ++i2) {
            this.a();
        }
    }

    public void c(int n2) {
        for (int i2 = 0; i2 < n2; ++i2) {
            this.b();
        }
    }
}

