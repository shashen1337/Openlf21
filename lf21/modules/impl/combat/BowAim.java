/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.Comparator;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.e;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.AntiBots;
import lf21.modules.impl.combat.w;
import lf21.modules.impl.world.Teams;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.g;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class BowAim
extends Module {
    private LivingEntity x;
    private int z;
    public final BooleanSetting throughWalls;
    public static BowAim k;
    private a y;
    public final NumberSetting range;
    private static final double j;
    public final BooleanSetting invisible;
    private static final String[] A;
    public final NumberSetting maxPredictionTicks;
    public final NumberSetting predictionScale;
    public final NumberSetting aimSpeed;
    public final BooleanSetting players;
    public final BooleanSetting animals;
    public final BooleanSetting movementPrediction;
    public final BooleanSetting silentRotations;
    public final NumberSetting foV;
    public final BooleanSetting mobs;

    @Override
    protected void onEnable() {
        this.l();
    }

    private a b(LivingEntity livingEntity, double d2) {
        Vec3 vec3 = BowAim.mc.player.getEyePosition();
        AABB aABB = livingEntity.getBoundingBox();
        Vec3 vec32 = new Vec3((aABB.minX + aABB.maxX) * 0.5, aABB.minY + (aABB.maxY - aABB.minY) * 0.42, (aABB.minZ + aABB.maxZ) * 0.5);
        double d3 = Math.min(((Number)this.maxPredictionTicks.getValue()).doubleValue(), Math.max(0.5, vec3.distanceTo(vec32) / Math.max(0.1, d2)));
        if (((Boolean)this.movementPrediction.getValue()).booleanValue()) {
            vec32 = vec32.add(this.a((Entity)livingEntity).scale(d3 * ((Number)this.predictionScale.getValue()).doubleValue()));
        }
        double d4 = (1.0 - Math.pow(0.99, d3)) / 0.01;
        vec32 = vec32.subtract(this.k().scale(d4));
        return g.a(vec3, vec32).a(Float.valueOf(((Double)BowAim.mc.options.sensitivity().get()).floatValue()));
    }

    private Vec3 a(Vec3 vec3) {
        double d2 = Math.hypot(vec3.x, vec3.z);
        double d3 = d2 > 1.5 ? 1.5 / d2 : 1.0;
        return new Vec3(vec3.x * d3, Mth.clamp((double)vec3.y, (double)-1.5, (double)1.5), vec3.z * d3);
    }

    private Vec3 k() {
        Vec3 vec3 = BowAim.mc.player.getDeltaMovement();
        Vec3 vec32 = new Vec3(BowAim.mc.player.getX() - BowAim.mc.player.xo, BowAim.mc.player.getY() - BowAim.mc.player.yo, BowAim.mc.player.getZ() - BowAim.mc.player.zo);
        if (vec32.lengthSqr() > 1.0E-6 && vec32.lengthSqr() < 16.0) {
            vec3 = vec32;
        }
        return this.a(new Vec3(vec3.x, BowAim.mc.player.onGround() ? 0.0 : vec3.y, vec3.z));
    }

    private Vec3 a(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        Vec3 vec32 = new Vec3(entity.getX() - entity.xo, entity.getY() - entity.yo, entity.getZ() - entity.zo);
        if (vec32.lengthSqr() > 1.0E-6 && vec32.lengthSqr() < 16.0) {
            vec3 = vec32;
        }
        return this.a(vec3);
    }

    private a a(LivingEntity livingEntity, double d2) {
        double d3;
        Vec3 vec3 = BowAim.mc.player.getEyePosition();
        AABB aABB = livingEntity.getBoundingBox();
        Vec3 vec32 = new Vec3((aABB.minX + aABB.maxX) * 0.5, aABB.minY + (aABB.maxY - aABB.minY) * 0.42, (aABB.minZ + aABB.maxZ) * 0.5);
        Vec3 vec33 = (Boolean)this.movementPrediction.getValue() != false ? this.a((Entity)livingEntity).scale(((Number)this.predictionScale.getValue()).doubleValue()) : Vec3.ZERO;
        Vec3 vec34 = this.k();
        double d4 = vec32.distanceTo(vec3);
        double d5 = Math.min(((Number)this.maxPredictionTicks.getValue()).doubleValue(), Math.max(2.0, d4 / Math.max(0.1, d2) * 1.9));
        double d6 = 1.0;
        double d7 = Double.POSITIVE_INFINITY;
        int n2 = 72;
        for (int i2 = 1; i2 <= 72; ++i2) {
            double d8 = 0.5 + (d5 - 0.5) * (double)i2 / 72.0;
            Vec3 vec35 = this.a(vec3, vec32.add(vec33.scale(d8)), d8, d2, vec34);
            d3 = Math.abs(vec35.length() - 1.0);
            if (!(d3 < d7)) continue;
            d7 = d3;
            d6 = d8;
        }
        double d9 = Math.max(0.25, d5 / 72.0 * 2.0);
        double d10 = Math.max(0.35, d6 - d9);
        d3 = Math.min(d5, d6 + d9);
        for (int i3 = 0; i3 < 28; ++i3) {
            double d11;
            double d12 = d10 + (d3 - d10) / 3.0;
            double d13 = d3 - (d3 - d10) / 3.0;
            double d14 = Math.abs(this.a(vec3, vec32.add(vec33.scale(d12)), d12, d2, vec34).length() - 1.0);
            if (d14 <= (d11 = Math.abs(this.a(vec3, vec32.add(vec33.scale(d13)), d13, d2, vec34).length() - 1.0))) {
                d3 = d13;
                continue;
            }
            d10 = d12;
        }
        d6 = (d10 + d3) * 0.5;
        Vec3 vec36 = this.a(vec3, vec32.add(vec33.scale(d6)), d6, d2, vec34);
        d7 = Math.abs(vec36.length() - 1.0);
        if (!Double.isFinite(d7) || d7 > 0.18 || vec36.lengthSqr() < 1.0E-7) {
            return this.a(vec3, vec32.add(vec33.scale(Math.max(1.0, d4 / d2))), d2, vec34);
        }
        vec36 = vec36.normalize();
        return g.a(Vec3.ZERO, vec36).a(Float.valueOf(((Double)BowAim.mc.options.sensitivity().get()).floatValue()));
    }

    private a a(Vec3 vec3, Vec3 vec32, double d2, Vec3 vec33) {
        Vec3 vec34 = vec32.subtract(vec3);
        double d3 = Math.max(0.25, vec34.length() / Math.max(0.1, d2));
        vec34 = vec34.subtract(vec33.scale(d3));
        double d4 = Math.hypot(vec34.x, vec34.z);
        if (d4 < 1.0E-4) {
            return g.a(vec3, vec32);
        }
        double d5 = d2 * d2;
        double d6 = 0.05;
        double d7 = d5 * d5 - d6 * (d6 * d4 * d4 + 2.0 * vec34.y * d5);
        if (d7 < 0.0) {
            return null;
        }
        double d8 = -Math.atan((d5 - Math.sqrt(d7)) / (d6 * d4));
        float f2 = (float)Math.toDegrees(Math.atan2(vec34.z, vec34.x)) - 90.0f;
        return new a(f2, (float)Math.toDegrees(d8)).a(Float.valueOf(((Double)BowAim.mc.options.sensitivity().get()).floatValue()));
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
                    case 0 -> 52;
                    case 1 -> 34;
                    case 2 -> 126;
                    case 3 -> 48;
                    case 4 -> 53;
                    case 5 -> 80;
                    default -> 104;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public BowAim() {
        String[] stringArray = A;
        super("BowAim", Category.COMBAT);
        this.range = new NumberSetting("Range", 64.0, 8.0, 128.0, 1.0);
        this.foV = new NumberSetting("FoV", 120.0, 10.0, 360.0, 1.0);
        this.aimSpeed = new NumberSetting("Aim Speed", 180.0, 10.0, 180.0, 1.0);
        this.movementPrediction = new BooleanSetting("Movement Prediction", true);
        this.predictionScale = new NumberSetting("Prediction Scale", 1.0, 0.1, 2.0, 0.05, () -> (Boolean)this.movementPrediction.getValue());
        this.maxPredictionTicks = new NumberSetting("Max Prediction Ticks", 40.0, 5.0, 80.0, 1.0, () -> (Boolean)this.movementPrediction.getValue());
        this.silentRotations = new BooleanSetting("Silent Rotations", false);
        this.players = new BooleanSetting("Players", true);
        this.mobs = new BooleanSetting("Mobs", true);
        this.animals = new BooleanSetting("Animals", false);
        this.invisible = new BooleanSetting("Invisible", false);
        this.throughWalls = new BooleanSetting("Through Walls", false);
        this.z = Integer.MIN_VALUE;
        k = this;
    }

    @Override
    protected void onDisable() {
        this.l();
    }

    public a h() {
        return this.a() ? this.y : null;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x68);
        }
        return cArray;
    }

    @EventTarget
    public void a(e e2) {
        this.l();
    }

    private void l() {
        this.x = null;
        this.y = null;
        this.z = Integer.MIN_VALUE;
    }

    public boolean a() {
        return this.x != null && this.y != null && this.x.isAlive() && BowAim.mc.player != null && BowAim.mc.player.isUsingItem() && BowAim.mc.player.getUseItem().getItem() instanceof BowItem;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                BowAim.j = 0.42;
                var5 = new String[13];
                var3_1 = 0;
                var2_2 = "gK\u0012U[$HfM\nQA9\u0007ZQ\u0007dN\u001fIP\"\u001b\u0004yM\u001cC\u0014yC\u0006\u0010e\"\rPK\u001dD\\?\u0006\u0014v\u0017S^#\u0006vM\tq\\=\u0007uL\u0017]T<\u001b\r`J\f_@7\u0000\u0014u\u001f\\Y#\u0013yM\bUX5\u0006@\u0002.BP4\u0001WV\u0017_[\tuK\u0013\u0010f \rQF\u0003rM(\u0010dP\u001bT\\3\u001c]M\u0010\u0010f3\tXG";
                var4_3 = "gK\u0012U[$HfM\nQA9\u0007ZQ\u0007dN\u001fIP\"\u001b\u0004yM\u001cC\u0014yC\u0006\u0010e\"\rPK\u001dD\\?\u0006\u0014v\u0017S^#\u0006vM\tq\\=\u0007uL\u0017]T<\u001b\r`J\f_@7\u0000\u0014u\u001f\\Y#\u0013yM\bUX5\u0006@\u0002.BP4\u0001WV\u0017_[\tuK\u0013\u0010f \rQF\u0003rM(\u0010dP\u001bT\\3\u001c]M\u0010\u0010f3\tXG".length();
                var1_4 = 16;
                var0_5 = -1;
lbl8:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl13:
                // 1 sources

                while (true) {
                    var5[var3_1++] = BowAim.c(BowAim.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "}L\bYF9\nXG\u0005fC\u0010WP";
                    var4_3 = "}L\bYF9\nXG\u0005fC\u0010WP".length();
                    var1_4 = 9;
                    var0_5 = -1;
lbl22:
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
lbl27:
                // 1 sources

                while (true) {
                    var5[var3_1++] = BowAim.c(BowAim.a(v1));
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
lbl38:
                // 1 sources

                ** continue;
            }
        }
        BowAim.A = var5;
    }

    private Vec3 a(Vec3 vec3, Vec3 vec32, double d2, double d3, Vec3 vec33) {
        double d4 = 0.99;
        double d5 = 0.05;
        double d6 = Math.pow(0.99, d2);
        double d7 = d3 * (d6 - 1.0);
        if (Math.abs(d7) < 1.0E-8) {
            return new Vec3(0.0, 10.0, 0.0);
        }
        double d8 = -0.010000000000000009 / d7;
        double d9 = 0.05 * (d6 - 0.99 * d2 + d2 - 1.0) / (d3 * -0.010000000000000009 * (d6 - 1.0));
        Vec3 vec34 = vec32.subtract(vec3);
        Vec3 vec35 = new Vec3(vec34.x * d8, vec34.y * d8 + d9, vec34.z * d8);
        return vec35.subtract(vec33.scale(1.0 / d3));
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (!LF21Client.isReady() || BowAim.mc.player == null || BowAim.mc.level == null || BowAim.mc.screen != null) {
            this.l();
            return;
        }
        if (BowAim.mc.player.tickCount == this.z) {
            return;
        }
        this.z = BowAim.mc.player.tickCount;
        ItemStack itemStack = BowAim.mc.player.getUseItem();
        if (!BowAim.mc.player.isUsingItem() || !(itemStack.getItem() instanceof BowItem)) {
            this.l();
            return;
        }
        int n2 = Math.max(0, itemStack.getUseDuration() - BowAim.mc.player.getUseItemRemainingTicks());
        float f2 = BowItem.getPowerForTime((int)n2);
        LivingEntity livingEntity = this.j();
        if (livingEntity == null) {
            this.l();
            return;
        }
        this.x = livingEntity;
        double d2 = Math.max(0.1, (double)f2 * 3.0);
        a a2 = this.a(livingEntity, d2);
        if (a2 == null) {
            a2 = this.b(livingEntity, d2);
        }
        if (a2 == null || !Float.isFinite(a2.e()) || !Float.isFinite(a2.f())) {
            this.l();
            return;
        }
        a a3 = this.y != null ? this.y : (RotationHandler.b != null ? RotationHandler.b : new a(BowAim.mc.player.getYRot(), BowAim.mc.player.getXRot()));
        this.y = g.a(a3, a2, ((Number)this.aimSpeed.getValue()).doubleValue());
        if (!((Boolean)this.silentRotations.getValue()).booleanValue() && this.y != null) {
            BowAim.mc.player.setYRot(this.y.e());
            BowAim.mc.player.setXRot(Mth.clamp((float)this.y.f(), (float)-90.0f, (float)90.0f));
            BowAim.mc.player.yRotO = BowAim.mc.player.getYRot();
            BowAim.mc.player.xRotO = BowAim.mc.player.getXRot();
        }
    }

    private LivingEntity j() {
        Vec3 vec3 = BowAim.mc.player.getEyePosition();
        a a2 = new a(BowAim.mc.player.getYRot(), BowAim.mc.player.getXRot());
        double d2 = ((Number)this.range.getValue()).doubleValue() * ((Number)this.range.getValue()).doubleValue();
        double d3 = ((Number)this.foV.getValue()).doubleValue() * 0.5;
        ArrayList<w> arrayList = new ArrayList<w>();
        for (Entity object : BowAim.mc.level.entitiesForRendering()) {
            LivingEntity livingEntity;
            if (!(object instanceof LivingEntity) || !this.a(livingEntity = (LivingEntity)object)) continue;
            AABB aABB = livingEntity.getBoundingBox();
            Vec3 vec32 = new Vec3((aABB.minX + aABB.maxX) * 0.5, aABB.minY + (aABB.maxY - aABB.minY) * 0.42, (aABB.minZ + aABB.maxZ) * 0.5);
            if (vec3.distanceToSqr(vec32) > d2) continue;
            a a3 = g.a(vec3, vec32);
            double d4 = Math.abs(Mth.wrapDegrees((float)(a3.e() - a2.e())));
            double d5 = Math.abs(a3.f() - a2.f());
            double d6 = Math.hypot(d4, d5);
            if (d3 < 180.0 && d6 > d3) continue;
            arrayList.add(new w(livingEntity, d6));
        }
        arrayList.sort(Comparator.comparingDouble(w::b));
        for (w w2 : arrayList) {
            if (this.a(w2.a(), 3.0) == null) continue;
            return w2.a();
        }
        return null;
    }

    public LivingEntity i() {
        return this.x;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (this.x == null || !this.a() || BowAim.mc.gameRenderer == null) {
            return;
        }
        PoseStack poseStack = renderEvent.a();
        poseStack.pushPose();
        try {
            Camera camera = BowAim.mc.gameRenderer.getMainCamera();
            Vec3 vec3 = camera.getPosition();
            poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
            RenderUtil.a(poseStack, (Entity)this.x, renderEvent.b());
        }
        finally {
            poseStack.popPose();
        }
    }

    private boolean a(LivingEntity livingEntity) {
        if (livingEntity == BowAim.mc.player || !livingEntity.isAlive() || livingEntity.isDeadOrDying() || livingEntity.getHealth() <= 0.0f || livingEntity instanceof ArmorStand || livingEntity.isInvisible() && !((Boolean)this.invisible.getValue()).booleanValue()) {
            return false;
        }
        if (AntiBots.j != null && AntiBots.j.isEnabled() && (AntiBots.b((Entity)livingEntity) || AntiBots.a((Entity)livingEntity))) {
            return false;
        }
        if (Teams.a((Entity)livingEntity)) {
            return false;
        }
        if (!((Boolean)this.throughWalls.getValue()).booleanValue() && !BowAim.mc.player.hasLineOfSight((Entity)livingEntity)) {
            return false;
        }
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            return (Boolean)this.players.getValue() != false && !player.isSpectator() && !player.isSleeping();
        }
        if (livingEntity instanceof Animal || livingEntity instanceof Squid || livingEntity instanceof Villager) {
            return (Boolean)this.animals.getValue();
        }
        return (Boolean)this.mobs.getValue() != false && (livingEntity instanceof Mob || livingEntity instanceof Slime || livingEntity instanceof Bat || livingEntity instanceof AbstractGolem);
    }
}

