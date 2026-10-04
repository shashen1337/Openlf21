/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import lf21.event.EventTarget;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.player.AntiWeb;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.d;
import lf21.utils.game.g;
import lf21.utils.rotation.a;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class AutoMLG
extends Module {
    private int z;
    private static final String[] C;
    public static AutoMLG j;
    private final BooleanSetting solidCheck;
    private BlockPos x;
    private int v;
    public a o;
    private boolean y;
    private final NumberSetting predictTicks;
    private int A;
    private final BooleanSetting recorvey;
    private int u;
    private Integer w;
    private Integer r;
    private double q;
    private int B;
    private float p;
    private final NumberSetting fallDistance;
    private boolean s;
    private boolean t;

    @EventTarget
    public void a(TickEvent tickEvent) {
        a a2;
        BlockHitResult blockHitResult;
        BlockPos blockPos;
        int n2;
        double d2;
        if (AntiWeb.i()) {
            return;
        }
        if (AutoMLG.mc.player == null || AutoMLG.mc.level == null) {
            return;
        }
        if (AutoMLG.mc.player.isFallFlying()) {
            return;
        }
        if (AutoMLG.mc.player.onGround() || AutoMLG.mc.player.getAbilities().flying || AutoMLG.mc.player.isInWaterRainOrBubble() || AutoMLG.mc.player.isInLava()) {
            this.p = 0.0f;
        } else {
            d2 = AutoMLG.mc.player.getY() - this.q;
            if (d2 < 0.0) {
                this.p -= (float)d2;
            }
        }
        this.q = AutoMLG.mc.player.getY();
        if (this.z > 0) {
            --this.z;
        }
        if (this.A > 0) {
            --this.A;
        }
        if (this.B > 0) {
            --this.B;
        }
        if (this.r != null) {
            AutoMLG.mc.player.getInventory().selected = this.r;
            this.r = null;
        }
        if (AutoMLG.mc.player.onGround() || this.p <= 0.0f) {
            this.s = false;
            this.y = false;
        }
        if (this.t) {
            if (this.u > 0) {
                --this.u;
                return;
            }
            if (this.v-- <= 0) {
                this.t = false;
                return;
            }
            if (this.w == null) {
                this.w = d.a(0, 9, Items.BUCKET);
                if (this.w == null) {
                    this.t = false;
                    return;
                }
            }
            if (((ItemStack)AutoMLG.mc.player.getInventory().items.get(this.w.intValue())).getItem() == Items.WATER_BUCKET) {
                this.t = false;
                this.w = null;
                this.x = null;
                this.z = Math.max(this.z, 1);
                return;
            }
            if (this.x == null || !this.a(this.x)) {
                this.t = false;
                this.w = null;
                this.x = null;
                return;
            }
            a a3 = g.a(this.x, 0.0f);
            BlockHitResult blockHitResult2 = this.b(a3, 4.5);
            if (blockHitResult2.getType() == HitResult.Type.MISS || !blockHitResult2.getBlockPos().equals((Object)this.x)) {
                this.t = false;
                this.w = null;
                this.x = null;
                return;
            }
            this.b(a3);
            this.a(this.w);
            this.a(a3);
            return;
        }
        if (!this.s && !this.t && this.x == null && this.z == 0 && this.A == 0 && this.p <= 0.5f && d.a(0, 9, Items.WATER_BUCKET) < 0 && (n2 = d.a(0, 9, Items.BUCKET)) >= 0 && (blockPos = this.i()) != null && (blockHitResult = this.b(a2 = g.a(blockPos, 0.0f), 4.5)).getType() != HitResult.Type.MISS && blockHitResult.getBlockPos().equals((Object)blockPos)) {
            this.b(a2);
            this.a(n2);
            this.a(a2);
            this.A = 8;
            this.z = Math.max(this.z, 1);
            return;
        }
        if (this.s && !this.y && AutoMLG.mc.player.getDeltaMovement().y < 0.0) {
            double d3;
            d2 = this.a(2.5);
            if (d3 > 0.0 && d2 <= 1.05) {
                this.y = true;
            }
        }
        if (this.s) {
            return;
        }
        if (this.p < ((Number)this.fallDistance.getValue()).floatValue()) {
            return;
        }
        n2 = d.a(0, 9, Items.WATER_BUCKET);
        if (n2 < 0) {
            return;
        }
        int n3 = this.h();
        if (n3 <= ((Number)this.predictTicks.getValue()).intValue()) {
            if (((Boolean)this.solidCheck.getValue()).booleanValue() && !this.b(BlockPos.containing((double)AutoMLG.mc.player.getX(), (double)AutoMLG.mc.player.getY(), (double)AutoMLG.mc.player.getZ()))) {
                return;
            }
            a2 = new a(AutoMLG.mc.player.getYRot(), 90.0f);
            blockHitResult = this.a(a2, 5.0);
            if (blockHitResult.getType() == HitResult.Type.MISS) {
                return;
            }
            this.a(n2, true);
        }
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String c(char[] cArray) {
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
                    case 0 -> 76;
                    case 1 -> 32;
                    case 2 -> 54;
                    case 3 -> 85;
                    case 4 -> 64;
                    case 5 -> 106;
                    default -> 84;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private int h() {
        if (AutoMLG.mc.player.getDeltaMovement().y >= 0.0) {
            return 999;
        }
        double d2 = this.a(30.0);
        if (d2 == Double.POSITIVE_INFINITY) {
            return 999;
        }
        double d3 = 0.0;
        double d4 = AutoMLG.mc.player.getDeltaMovement().y;
        for (int i2 = 1; i2 <= 20; ++i2) {
            if (!(Math.abs(d3 += (d4 = (d4 - 0.08) * 0.98)) >= d2)) continue;
            return i2;
        }
        return 999;
    }

    private void a(int n2) {
        this.r = AutoMLG.mc.player.getInventory().selected;
        AutoMLG.mc.player.getInventory().selected = n2;
    }

    private BlockPos c(a a2) {
        BlockHitResult blockHitResult = this.a(a2, 4.5);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return null;
        }
        return blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "\u001fOZ<$J7$EU>\r\nAZ9`\u000e=?TW;#\u000f\r\u001cRS1)\t lt_6+\u0019";
                var4_3 = "\u001fOZ<$J7$EU>\r\nAZ9`\u000e=?TW;#\u000f\r\u001cRS1)\t lt_6+\u0019".length();
                var1_4 = 11;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = AutoMLG.c(AutoMLG.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\rUB:\r&\u0013\b\u001eEU:2\u001c15";
                    var4_3 = "\rUB:\r&\u0013\b\u001eEU:2\u001c15".length();
                    var1_4 = 7;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = AutoMLG.c(AutoMLG.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    break block10;
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
        AutoMLG.C = var5;
    }

    private boolean c(BlockPos blockPos) {
        BlockState blockState = AutoMLG.mc.level.getBlockState(blockPos);
        boolean bl = !blockState.getCollisionShape((BlockGetter)AutoMLG.mc.level, blockPos).isEmpty();
        boolean bl2 = blockState.getMenuProvider((Level)AutoMLG.mc.level, blockPos) == null;
        return bl && bl2;
    }

    public AutoMLG() {
        String[] stringArray = C;
        super("AutoMLG", Category.PLAYER);
        this.fallDistance = new NumberSetting("Fall distance", Float.valueOf(3.0f), Float.valueOf(1.0f), Float.valueOf(10.0f), Float.valueOf(0.1f));
        this.predictTicks = new NumberSetting("Predict Ticks", Float.valueOf(2.0f), Float.valueOf(1.0f), Float.valueOf(5.0f), Float.valueOf(1.0f));
        this.solidCheck = new BooleanSetting("Solid check", true);
        this.recorvey = new BooleanSetting("Recorvey", true);
        this.o = null;
        j = this;
    }

    private void b(a a2) {
        this.o = a2;
    }

    @Override
    protected void onEnable() {
        this.r = null;
        this.s = false;
        this.t = false;
        this.u = 0;
        this.v = 0;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.p = 0.0f;
        this.q = AutoMLG.mc.player != null ? AutoMLG.mc.player.getY() : 0.0;
        super.onEnable();
    }

    @Override
    protected void onDisable() {
        this.r = null;
        this.s = false;
        this.t = false;
        this.u = 0;
        this.v = 0;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.p = 0.0f;
        super.onDisable();
    }

    public boolean a() {
        return this.z > 0;
    }

    private boolean b(BlockPos blockPos) {
        return this.c(blockPos.below()) || this.c(blockPos.below(2));
    }

    private BlockHitResult b(a a2, double d2) {
        Vec3 vec3 = AutoMLG.mc.player.getEyePosition(1.0f);
        Vec3 vec32 = Vec3.directionFromRotation((float)a2.f(), (float)a2.e());
        Vec3 vec33 = vec3.add(vec32.scale(d2));
        return AutoMLG.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.SOURCE_ONLY, (Entity)AutoMLG.mc.player));
    }

    private BlockPos i() {
        BlockPos blockPos = BlockPos.containing((double)AutoMLG.mc.player.getX(), (double)AutoMLG.mc.player.getY(), (double)AutoMLG.mc.player.getZ());
        BlockPos blockPos2 = null;
        double d2 = Double.POSITIVE_INFINITY;
        int n2 = 4;
        for (int i2 = -1; i2 <= 1; ++i2) {
            for (int i3 = -n2; i3 <= n2; ++i3) {
                for (int i4 = -n2; i4 <= n2; ++i4) {
                    a a2;
                    BlockHitResult blockHitResult;
                    double d3;
                    BlockPos blockPos3 = blockPos.offset(i3, i2, i4);
                    if (!this.a(blockPos3) || (d3 = AutoMLG.mc.player.position().distanceToSqr((double)blockPos3.getX() + 0.5, (double)blockPos3.getY() + 0.5, (double)blockPos3.getZ() + 0.5)) >= d2 || (blockHitResult = this.b(a2 = g.a(blockPos3, 0.0f), 4.5)).getType() == HitResult.Type.MISS || !blockHitResult.getBlockPos().equals((Object)blockPos3)) continue;
                    blockPos2 = blockPos3;
                    d2 = d3;
                }
            }
        }
        return blockPos2;
    }

    private BlockHitResult a(a a2, double d2) {
        Vec3 vec3 = AutoMLG.mc.player.getEyePosition(1.0f);
        Vec3 vec32 = Vec3.directionFromRotation((float)a2.f(), (float)a2.e());
        Vec3 vec33 = vec3.add(vec32.scale(d2));
        return AutoMLG.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)AutoMLG.mc.player));
    }

    private void a(a a2) {
        if (AutoMLG.mc.gameMode != null && AutoMLG.mc.player != null) {
            float f2 = AutoMLG.mc.player.getXRot();
            float f3 = AutoMLG.mc.player.getYRot();
            if (a2 != null) {
                AutoMLG.mc.player.setXRot(a2.f());
                AutoMLG.mc.player.setYRot(a2.e());
            }
            AutoMLG.mc.gameMode.useItem((Player)AutoMLG.mc.player, InteractionHand.MAIN_HAND);
            AutoMLG.mc.player.swing(InteractionHand.MAIN_HAND);
            if (a2 != null) {
                AutoMLG.mc.player.setXRot(f2);
                AutoMLG.mc.player.setYRot(f3);
            }
        }
    }

    private double a(double d2) {
        Vec3 vec3;
        Vec3 vec32 = new Vec3(AutoMLG.mc.player.getX(), AutoMLG.mc.player.getBoundingBox().minY, AutoMLG.mc.player.getZ());
        BlockHitResult blockHitResult = AutoMLG.mc.level.clip(new ClipContext(vec32, vec3 = vec32.add(0.0, -d2, 0.0), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)AutoMLG.mc.player));
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return Double.POSITIVE_INFINITY;
        }
        return vec32.y - blockHitResult.getLocation().y;
    }

    private boolean a(BlockPos blockPos) {
        FluidState fluidState = AutoMLG.mc.level.getFluidState(blockPos);
        return fluidState.getType() == Fluids.WATER && fluidState.isSource();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x54);
        }
        return cArray;
    }

    private void a(int n2, boolean bl) {
        a a2 = new a(AutoMLG.mc.player.getYRot(), 90.0f);
        this.b(a2);
        this.a(n2);
        this.a(a2);
        if (bl) {
            this.s = true;
        }
        this.t = (Boolean)this.recorvey.getValue();
        this.u = 3;
        this.v = this.t ? 2 : 0;
        this.w = null;
        this.x = this.c(a2);
    }
}

