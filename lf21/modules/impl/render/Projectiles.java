/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import lf21.event.EventTarget;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.player.MidPearl;
import lf21.modules.impl.render.a.a;
import lf21.modules.impl.render.a.b;
import lf21.modules.impl.render.a.c;
import lf21.modules.impl.render.a.d;
import lf21.modules.impl.render.n;
import lf21.modules.impl.render.z;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.u_0;
import lf21.render.w_0;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.b.i;
import lf21.utils.game.f;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class Projectiles
extends Module {
    public BooleanSetting showSnowballs;
    public BooleanSetting showArrows;
    private final a n;
    public static Projectiles j;
    private final b k;
    private final a m;
    private static final String[] y;
    public BooleanSetting showEggs;
    private final int w;
    private final c l;
    public BooleanSetting showPotions;
    private final a o;
    private final NumberSetting scale;
    public BooleanSetting showPearls;
    public static final ConcurrentHashMap<Integer, n> x;
    private final u_0 v;

    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        if (x.isEmpty()) {
            return;
        }
        float f2 = ((Number)this.scale.getValue()).floatValue();
        PoseStack poseStack = render2DEvent.a();
        x.forEach((n2, n3) -> {
            if (n3.e() instanceof EnderpearlItem) {
                float f3;
                Vec3 vec3 = n3.f();
                i i2 = lf21.utils.render.b.a(vec3.x, vec3.y, vec3.z);
                if (i2 == null) {
                    return;
                }
                String string = String.format("Thrown by: %s%nLands in: %.1fs%nDistance: %.1fm", n3.a(), n3.b(), n3.c());
                float f4 = 0.0f;
                for (String string2 : string.split("\n")) {
                    f3 = this.v.a(string2).a();
                    if (!(f3 > f4)) continue;
                    f4 = f3;
                }
                float f5 = this.v.d().b() + 2.0f;
                float f6 = f5 * 3.0f - 2.0f;
                float f7 = f4 + 16.0f;
                float f8 = f6 + 8.0f;
                f3 = -f7 / 2.0f;
                float f9 = -f8 / 2.0f - 20.0f;
                poseStack.pushPose();
                poseStack.translate(i2.a, i2.b, 0.0f);
                poseStack.scale(f2, f2, 1.0f);
                RenderUtil.a(poseStack, f3, f9, f7, f8, 4.0f, 8.0f, 1.0f, -1);
                poseStack.popPose();
            }
        });
        Renderer.a((DrawContext drawContext) -> x.forEach((n2, n3) -> {
            if (n3.e() instanceof EnderpearlItem) {
                Vec3 vec3 = n3.f();
                i i2 = lf21.utils.render.b.a(vec3.x, vec3.y, vec3.z);
                if (i2 == null) {
                    return;
                }
                this.a((DrawContext)drawContext, (n)n3, f2, i2);
            }
        }));
    }

    private n a(Entity entity2, d d2) {
        if (Projectiles.mc.player == null) {
            return null;
        }
        double d3 = entity2.getX();
        double d4 = entity2.getY();
        double d5 = entity2.getZ();
        double d6 = entity2.getDeltaMovement().x;
        double d7 = entity2.getDeltaMovement().y;
        double d8 = entity2.getDeltaMovement().z;
        Projectile projectile = (Projectile)entity2;
        for (int i2 = 0; i2 < 1000; ++i2) {
            Object object;
            BlockHitResult blockHitResult;
            Vec3 vec3 = new Vec3(d3, d4, d5);
            Vec3 vec32 = new Vec3(d3 + d6, d4 + d7, d5 + d8);
            BlockHitResult blockHitResult2 = blockHitResult = Projectiles.mc.level.clip(new ClipContext(vec3, vec32, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity2));
            List list = Projectiles.mc.level.getEntities(entity2, entity2.getBoundingBox().expandTowards(d6, d7, d8).inflate(1.0), entity -> entity.canBeCollidedWith() && entity != projectile.getOwner());
            double d9 = vec3.distanceToSqr(blockHitResult.getLocation());
            for (Entity entity3 : list) {
                double d10;
                EntityHitResult entityHitResult;
                if (!(entity3 instanceof LivingEntity) || (entityHitResult = f.a(object = entity3.getBoundingBox().inflate(0.3), vec3, vec32)) == null || !((d10 = vec3.distanceToSqr(entityHitResult.getLocation())) < d9)) continue;
                d9 = d10;
                blockHitResult2 = entityHitResult;
            }
            if (blockHitResult2 != null && blockHitResult2.getType() != HitResult.Type.MISS) {
                Entity entity3;
                Vec3 vec33 = blockHitResult2.getLocation();
                entity3 = projectile.getOwner();
                object = entity3 != null ? entity3.getName().getString() : "Unknown";
                double d11 = (double)i2 / 20.0;
                double d12 = Projectiles.mc.player.getEyePosition().distanceTo(vec33);
                return new n((String)object, d11, d12, System.currentTimeMillis(), Items.ENDER_PEARL, vec33);
            }
            d3 += d6;
            d5 += d8;
            if ((d4 += d7) < (double)(Projectiles.mc.level.getMinBuildHeight() - 10)) break;
            d6 *= 0.99;
            d7 *= 0.99;
            d8 *= 0.99;
            d7 -= (double)d2.c();
        }
        return null;
    }

    public Projectiles() {
        String[] stringArray = y;
        super("Projectiles", Category.RENDER);
        this.k = new b();
        this.l = new c();
        this.m = new a(Collections.singleton(ThrownEnderpearl.class), new Color(173, 12, 255));
        this.n = new a(Collections.singleton(ThrownEgg.class), new Color(255, 238, 154));
        this.o = new a(Collections.singleton(Snowball.class), new Color(255, 255, 255));
        this.showArrows = new BooleanSetting("Show Arrows", true);
        this.showPearls = new BooleanSetting("Show Pearls", true);
        this.showPotions = new BooleanSetting("Show Potions", false);
        this.showEggs = new BooleanSetting("Show Eggs", false);
        this.showSnowballs = new BooleanSetting("Show Snowballs", false);
        this.scale = new NumberSetting("Scale", 0.4, 0.2, 2.0, 0.01);
        this.v = w_0.a("pingfang_sc_regular.ttf", 32.0f);
        this.w = new Color(0, 0, 0, 80).getRGB();
        j = this;
    }

    private static boolean a(Player player, Entity entity) {
        return entity != player && entity instanceof LivingEntity;
    }

    private void a(PoseStack poseStack, Vec3 vec3, Direction direction, float f2, Color color) {
        Matrix4f matrix4f = poseStack.last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        float f3 = (float)color.getRed() / 255.0f;
        float f4 = (float)color.getGreen() / 255.0f;
        float f5 = (float)color.getBlue() / 255.0f;
        float f6 = (float)color.getAlpha() / 255.0f;
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
        Vec3 vec32 = new Vec3((double)direction.getStepX(), (double)direction.getStepY(), (double)direction.getStepZ());
        Vec3 vec33 = Math.abs(vec32.y) > 0.9 ? new Vec3(1.0, 0.0, 0.0) : new Vec3(0.0, 1.0, 0.0);
        Vec3 vec34 = vec32.cross(vec33).normalize();
        Vec3 vec35 = vec32.cross(vec34).normalize();
        bufferBuilder.vertex(matrix4f, (float)vec3.x, (float)vec3.y, (float)vec3.z).color(f3, f4, f5, f6).endVertex();
        int n2 = 40;
        for (int i2 = 0; i2 <= n2; ++i2) {
            double d2 = Math.PI * 2 * (double)i2 / (double)n2;
            double d3 = (double)f2 * Math.cos(d2);
            double d4 = (double)f2 * Math.sin(d2);
            Vec3 vec36 = vec3.add(vec34.scale(d3)).add(vec35.scale(d4));
            bufferBuilder.vertex(matrix4f, (float)vec36.x, (float)vec36.y, (float)vec36.z).color(f3, f4, f5, f6).endVertex();
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x41);
        }
        return cArray;
    }

    private void a(DrawContext drawContext, n n2, float f2, i i2) {
        float f3;
        drawContext.d();
        drawContext.a(i2.a, i2.b);
        drawContext.b(f2, f2);
        String string = String.format("Thrown by: %s%nLands in: %.1fs%nDistance: %.1fm", n2.a(), n2.b(), n2.c());
        String[] stringArray = string.split("\n");
        float f4 = 0.0f;
        for (String string2 : stringArray) {
            f3 = this.v.a(string2).a();
            if (!(f3 > f4)) continue;
            f4 = f3;
        }
        float f5 = this.v.d().b() + 2.0f;
        float f6 = f5 * (float)stringArray.length - 2.0f;
        float f7 = f4 + 16.0f;
        float f8 = f6 + 8.0f;
        f3 = -f7 / 2.0f;
        float f9 = -f8 / 2.0f - 20.0f;
        float f10 = 4.0f;
        try (D d2 = new D();){
            d2.a(this.w);
            drawContext.a(Q.a(f3, f9, f7, f8, f10), d2);
            d2.a(Color.WHITE.getRGB());
            float f11 = f9 + 4.0f - this.v.d().c();
            for (String string3 : stringArray) {
                float f12 = f3 + (f7 - this.v.a(string3).a()) / 2.0f;
                drawContext.a(string3, f12, f11, this.v, d2);
                f11 += f5;
            }
        }
        drawContext.e();
    }

    private void a(PoseStack poseStack, Vec3 vec3, HitResult hitResult, float f2) {
        if (hitResult == null || hitResult.getType() == HitResult.Type.MISS) {
            return;
        }
        poseStack.pushPose();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        if (hitResult instanceof BlockHitResult) {
            BlockHitResult blockHitResult = (BlockHitResult)hitResult;
            Vec3 vec32 = blockHitResult.getLocation();
            Direction direction = blockHitResult.getDirection();
            vec32 = vec32.add((double)direction.getStepX() * 0.005, (double)direction.getStepY() * 0.005, (double)direction.getStepZ() * 0.005);
            this.a(poseStack, vec32, direction, 0.4f, new Color(255, 255, 255, 120));
        } else if (hitResult instanceof EntityHitResult) {
            EntityHitResult entityHitResult = (EntityHitResult)hitResult;
            Entity entity = entityHitResult.getEntity();
            double d2 = Mth.lerp((double)f2, (double)entity.xOld, (double)entity.getX());
            double d3 = Mth.lerp((double)f2, (double)entity.yOld, (double)entity.getY());
            double d4 = Mth.lerp((double)f2, (double)entity.zOld, (double)entity.getZ());
            AABB aABB = entity.getBoundingBox().move(d2 - entity.getX(), d3 - entity.getY(), d4 - entity.getZ());
            RenderUtil.b(aABB, poseStack);
            Vec3 vec33 = new Vec3(aABB.getCenter().x, aABB.minY, aABB.getCenter().z);
            this.a(poseStack, vec33, Direction.UP, (float)((double)entity.getBbWidth() * 0.8), new Color(255, 0, 0, 100));
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        poseStack.popPose();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[11];
                var3_1 = 0;
                var2_2 = "3pr\u0000+9/\u000fo\u007f\u0016g\u00062\t3pr\u0000+/&\u0007k\u00075vv\u0019d\u001d//4po\u0018|\u0004a\u0002a'W.\u0019d\u000eT|\u0019o\u0019a\tv'W.Dp\u0006k8\u0019O\u00032\u0014ys\u0014nPaE6,\u0011f/4po\u0018|\u0004a\u0002a'W.\u0019d\u000eT|\u0019o\u0019a\tv'W.Dp\u0006k8\u0019O\u00032\u0014ys\u0014nPaE6,\u0011f\u00053{|\u001bn\u0017\u0010qs\u0010m\u000b/\u0007Gn\u0014T\u0018$\u0007mq\u0016yD5\u0014~\u000b3pr\u0000++3\u0012wj\u0004\u000b3pr\u0000+:$\u0001jq\u0004";
                var4_3 = "3pr\u0000+9/\u000fo\u007f\u0016g\u00062\t3pr\u0000+/&\u0007k\u00075vv\u0019d\u001d//4po\u0018|\u0004a\u0002a'W.\u0019d\u000eT|\u0019o\u0019a\tv'W.Dp\u0006k8\u0019O\u00032\u0014ys\u0014nPaE6,\u0011f/4po\u0018|\u0004a\u0002a'W.\u0019d\u000eT|\u0019o\u0019a\tv'W.Dp\u0006k8\u0019O\u00032\u0014ys\u0014nPaE6,\u0011f\u00053{|\u001bn\u0017\u0010qs\u0010m\u000b/\u0007Gn\u0014T\u0018$\u0007mq\u0016yD5\u0014~\u000b3pr\u0000++3\u0012wj\u0004\u000b3pr\u0000+:$\u0001jq\u0004".length();
                var1_4 = 14;
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
                    var5[var3_1++] = Projectiles.c(Projectiles.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "0jr\u001dn\t5\ttx\u0004\f3pr\u0000+:.\u0014qr\u0019x";
                    var4_3 = "0jr\u001dn\t5\ttx\u0004\f3pr\u0000+:.\u0014qr\u0019x".length();
                    var1_4 = 11;
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
                    var5[var3_1++] = Projectiles.c(Projectiles.a(v1));
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
        Projectiles.y = var5;
        Projectiles.x = new ConcurrentHashMap<K, V>();
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
                    case 0 -> 96;
                    case 1 -> 24;
                    case 2 -> 29;
                    case 3 -> 119;
                    case 4 -> 11;
                    case 5 -> 106;
                    default -> 65;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean a(Player player, Item item) {
        if (item instanceof BowItem || item instanceof CrossbowItem) {
            return player.isUsingItem();
        }
        return true;
    }

    private List<d> a() {
        ArrayList<d> arrayList = new ArrayList<d>();
        if (((Boolean)this.showArrows.getValue()).booleanValue()) {
            arrayList.add(this.k);
        }
        if (((Boolean)this.showPotions.getValue()).booleanValue()) {
            arrayList.add(this.l);
        }
        if (((Boolean)this.showPearls.getValue()).booleanValue()) {
            arrayList.add(this.m);
        }
        if (((Boolean)this.showEggs.getValue()).booleanValue()) {
            arrayList.add(this.n);
        }
        if (((Boolean)this.showSnowballs.getValue()).booleanValue()) {
            arrayList.add(this.o);
        }
        return arrayList;
    }

    private d a(Entity entity) {
        if (entity.onGround()) {
            return null;
        }
        if (entity.getX() == entity.xOld && entity.getZ() == entity.zOld) {
            return null;
        }
        for (d d2 : this.a()) {
            if (!d2.a(entity)) continue;
            return d2;
        }
        return null;
    }

    private void a(PoseStack poseStack, List<Vec3> list) {
        Matrix4f matrix4f = poseStack.last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        bufferBuilder.begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        for (Vec3 vec3 : list) {
            bufferBuilder.vertex(matrix4f, (float)vec3.x, (float)vec3.y, (float)vec3.z).endVertex();
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    private void a(PoseStack poseStack, Entity entity, d d2) {
        if (entity == null) {
            return;
        }
        LocalPlayer localPlayer = Projectiles.mc.player;
        ClientLevel clientLevel = Projectiles.mc.level;
        Color color = d2.a((Object)entity);
        if (color == null) {
            color = new Color(255, 255, 255);
        }
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        double d3 = entity.getX();
        double d4 = entity.getY();
        double d5 = entity.getZ();
        double d6 = entity.getDeltaMovement().x;
        double d7 = entity.getDeltaMovement().y;
        double d8 = entity.getDeltaMovement().z;
        this.a(color, bufferBuilder, poseStack, d3, d4, d5);
        for (int i2 = 0; i2 < 1000; ++i2) {
            float f2 = d2.a();
            float f3 = d2.b();
            AABB aABB = new AABB(d3 - (double)f2, d4, d5 - (double)f2, d3 + (double)f2, d4 + (double)f3, d5 + (double)f2);
            Vec3 vec3 = new Vec3(d3, d4, d5);
            Vec3 vec32 = new Vec3(d3 + d6, d4 + d7, d5 + d8);
            HitResult hitResult = f.a(vec3, vec32, false, entity instanceof Arrow, false, entity);
            if (hitResult != null && !hitResult.getType().equals((Object)HitResult.Type.MISS)) {
                vec32 = new Vec3(hitResult.getLocation().x(), hitResult.getLocation().y(), hitResult.getLocation().z());
            }
            List list = clientLevel.getEntities((Entity)localPlayer, aABB.contract(d6, d7, d8).expandTowards(1.0, 1.0, 1.0));
            double d9 = 0.0;
            for (Entity entity2 : list) {
                double d10;
                AABB aABB2;
                EntityHitResult entityHitResult;
                if (!(entity2 instanceof LivingEntity) || entity2 instanceof EnderMan || !entity2.canBeCollidedWith() || entity2.equals((Object)localPlayer) || (entityHitResult = f.a(aABB2 = entity2.getBoundingBox().expandTowards(0.3, 0.3, 0.3), vec3, vec32)) == null || !((d10 = vec3.distanceTo(entityHitResult.getLocation())) < d9) && d9 != 0.0) continue;
                d9 = d10;
                hitResult = entityHitResult;
            }
            d3 += d6;
            d4 += d7;
            d5 += d8;
            if (hitResult != null && !hitResult.getType().equals((Object)HitResult.Type.MISS)) {
                d3 = hitResult.getLocation().x();
                d4 = hitResult.getLocation().y();
                d5 = hitResult.getLocation().z();
                break;
            }
            if (d4 < -128.0) break;
            double d11 = entity.isInWater() ? 0.8 : 0.99;
            d7 = d7 * d11 - (double)d2.c();
            d6 *= d11;
            d8 *= d11;
            this.a(color, bufferBuilder, poseStack, d3, d4, d5);
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    private boolean b(Item item) {
        return item instanceof BowItem || item instanceof CrossbowItem || item instanceof SnowballItem || item instanceof EggItem || item instanceof EnderpearlItem || item instanceof SplashPotionItem || item instanceof LingeringPotionItem || item instanceof FishingRodItem || item instanceof TridentItem;
    }

    private void a(Color color, BufferBuilder bufferBuilder, PoseStack poseStack, double d2, double d3, double d4) {
        bufferBuilder.vertex(poseStack.last().pose(), (float)d2, (float)d3, (float)d4).color(color.getRGB()).endVertex();
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        d d2;
        Object object2;
        if (Projectiles.mc.player == null || Projectiles.mc.level == null) {
            return;
        }
        lf21.utils.render.b.a();
        x.keySet().removeIf(n2 -> {
            Entity entity = Projectiles.mc.level.getEntity(n2.intValue());
            return entity == null || !entity.isAlive() || entity.onGround();
        });
        Item item = Projectiles.mc.player.getMainHandItem().getItem();
        Item item2 = Projectiles.mc.player.getOffhandItem().getItem();
        boolean bl = this.b(item) || this.b(item2);
        Item item3 = this.b(item) ? item : item2;
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = Projectiles.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        for (Object object2 : Projectiles.mc.level.entitiesForRendering()) {
            if (!(object2 instanceof Projectile) || !object2.isAlive() || (d2 = this.a((Entity)object2)) == null) continue;
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask((boolean)false);
            RenderSystem.setShader(GameRenderer::getPositionShader);
            Color color = d2.a(object2);
            RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)1.0f);
            this.a(poseStack, (Entity)object2, d2);
            if (object2 instanceof ThrownEnderpearl) {
                n n3 = this.a((Entity)object2, d2);
                if (n3 != null) {
                    x.put(object2.getId(), n3);
                } else {
                    x.remove(object2.getId());
                }
            }
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderSystem.disableBlend();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)true);
        }
        Object object3 = null;
        if (bl && this.a((Player)Projectiles.mc.player, item3)) {
            object3 = this.a(renderEvent.b());
        }
        if (object3 == null) {
            poseStack.popPose();
            return;
        }
        object2 = ((z)object3).a();
        if (object2.size() < 2) {
            poseStack.popPose();
            return;
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader(GameRenderer::getPositionShader);
        object2.remove(0);
        this.a(poseStack, (List<Vec3>)object2);
        if (!object2.isEmpty()) {
            d2 = (Vec3)object2.get(object2.size() - 1);
            this.a(poseStack, (Vec3)d2, ((z)object3).b, renderEvent.b());
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        poseStack.popPose();
    }

    private double a(Item item) {
        if (item instanceof BowItem || item instanceof CrossbowItem) {
            return 0.05;
        }
        if (item instanceof PotionItem) {
            return 0.4;
        }
        if (item instanceof FishingRodItem) {
            return 0.15;
        }
        if (item instanceof TridentItem) {
            return 0.015;
        }
        return 0.03;
    }

    private z a(float f2) {
        double d2;
        double d3;
        double d4;
        LocalPlayer localPlayer = Projectiles.mc.player;
        ArrayList<Vec3> arrayList = new ArrayList<Vec3>();
        ItemStack itemStack = localPlayer.getMainHandItem();
        Item item = itemStack.getItem();
        if (itemStack.isEmpty() || !this.b(item) || !this.a((Player)localPlayer, item)) {
            return null;
        }
        double d5 = localPlayer.xOld + (localPlayer.getX() - localPlayer.xOld) * (double)f2;
        double d6 = localPlayer.yOld + (localPlayer.getY() - localPlayer.yOld) * (double)f2 + (double)localPlayer.getEyeHeight() - 0.1;
        double d7 = localPlayer.zOld + (localPlayer.getZ() - localPlayer.zOld) * (double)f2;
        double d8 = d4 = item instanceof ProjectileWeaponItem ? 1.0 : 0.4;
        if (MidPearl.j != null && MidPearl.j.isEnabled() && MidPearl.k != null) {
            d3 = Math.toRadians(MidPearl.k.e());
            d2 = Math.toRadians(MidPearl.k.f());
        } else {
            d3 = Math.toRadians(Mth.lerp((float)f2, (float)localPlayer.yRotO, (float)localPlayer.getYRot()));
            d2 = Math.toRadians(Mth.lerp((float)f2, (float)localPlayer.xRotO, (float)localPlayer.getXRot()));
        }
        double d9 = -Math.sin(d3) * Math.cos(d2) * d4;
        double d10 = -Math.sin(d2) * d4;
        double d11 = Math.cos(d3) * Math.cos(d2) * d4;
        double d12 = Math.sqrt(d9 * d9 + d10 * d10 + d11 * d11);
        d9 /= d12;
        d10 /= d12;
        d11 /= d12;
        if (item instanceof ProjectileWeaponItem) {
            float f3 = (float)(72000 - localPlayer.getUseItemRemainingTicks()) / 20.0f;
            if ((f3 = (f3 * f3 + f3 * 2.0f) / 3.0f) > 1.0f || f3 <= 0.1f) {
                f3 = 1.0f;
            }
            d9 *= (double)(f3 *= 3.0f);
            d10 *= (double)f3;
            d11 *= (double)f3;
        } else {
            d9 *= 1.5;
            d10 *= 1.5;
            d11 *= 1.5;
        }
        double d13 = this.a(item);
        for (int i2 = 0; i2 < 1000; ++i2) {
            Vec3 vec3 = new Vec3(d5, d6, d7);
            Vec3 vec32 = new Vec3(d5 + d9, d6 + d10, d7 + d11);
            arrayList.add(vec3);
            ClipContext clipContext = new ClipContext(vec3, vec32, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)Projectiles.mc.player);
            BlockHitResult blockHitResult = Projectiles.mc.level.clip(clipContext);
            if (blockHitResult.getType() != HitResult.Type.MISS) {
                return new z(arrayList, (HitResult)blockHitResult);
            }
            Arrow arrow = new Arrow((Level)Projectiles.mc.level, d5, d6, d7);
            EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult((Level)Projectiles.mc.level, (Entity)arrow, (Vec3)vec3, (Vec3)vec32, (AABB)arrow.getBoundingBox().expandTowards(new Vec3(d9, d10, d11)).inflate(1.0), entity -> Projectiles.a((Player)localPlayer, entity));
            if (entityHitResult != null && entityHitResult.getType() == HitResult.Type.ENTITY) {
                return new z(arrayList, (HitResult)entityHitResult);
            }
            d5 += d9;
            d6 += d10;
            d7 += d11;
            d9 *= 0.99;
            d10 *= 0.99;
            d11 *= 0.99;
            d10 -= d13;
        }
        return new z(arrayList, null);
    }
}

