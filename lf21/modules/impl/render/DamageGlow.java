/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lf21.event.EventTarget;
import lf21.event.impl.EntityHurtEvent;
import lf21.event.impl.EntityRemoveEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.render.v;
import lf21.settings.impl.NumberSetting;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class DamageGlow
extends Module {
    public static DamageGlow j;
    private final NumberSetting colorB;
    private final NumberSetting colorR;
    private final Map<Integer, List<v>> k;
    private static final String[] p;
    private final NumberSetting alpha;
    private final NumberSetting colorG;

    @EventTarget
    public void a(EntityRemoveEvent entityRemoveEvent) {
        if (entityRemoveEvent.a()) {
            return;
        }
        if (entityRemoveEvent.b() instanceof Player && entityRemoveEvent.b() == DamageGlow.mc.player) {
            return;
        }
        Entity entity = entityRemoveEvent.b();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            this.a(livingEntity);
        }
    }

    public DamageGlow() {
        String[] stringArray = p;
        super("DamageGlow", Category.RENDER);
        this.k = new ConcurrentHashMap<Integer, List<v>>();
        this.colorR = new NumberSetting("Color R", 0, 0, 255, 1);
        this.colorG = new NumberSetting("Color G", 0, 0, 255, 1);
        this.colorB = new NumberSetting("Color B", 0, 0, 255, 1);
        this.alpha = new NumberSetting("Alpha", 45.0, 0.0, 255.0, 1.0);
        j = this;
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
                    case 0 -> 21;
                    case 1 -> 49;
                    case 2 -> 95;
                    case 3 -> 52;
                    case 4 -> 110;
                    case 5 -> 34;
                    default -> 64;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private float a(v v2) {
        long l2 = v2.b - System.currentTimeMillis();
        if (l2 <= 0L) {
            return 0.0f;
        }
        return Math.min(1.0f, (float)l2 / 1500.0f);
    }

    @Override
    public void onEnable() {
        this.k.clear();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x40);
        }
        return cArray;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (DamageGlow.mc.level == null) {
            return;
        }
        for (Map.Entry<Integer, List<v>> entry2 : this.k.entrySet()) {
            Entity entity;
            Integer n2 = entry2.getKey();
            List<v> list = entry2.getValue();
            this.b(list);
            if (list.isEmpty() || !((entity = DamageGlow.mc.level.getEntity(n2.intValue())) instanceof LivingEntity)) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            this.a(livingEntity, list, renderEvent);
        }
        this.k.entrySet().removeIf(entry -> ((List)entry.getValue()).isEmpty());
    }

    @EventTarget
    public void a(EntityHurtEvent entityHurtEvent) {
        if (entityHurtEvent.a() instanceof Player && entityHurtEvent.a() == DamageGlow.mc.player) {
            return;
        }
        LivingEntity livingEntity = entityHurtEvent.a();
        this.a(livingEntity);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(LivingEntity livingEntity, List<v> list, RenderEvent renderEvent) {
        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
        for (v v2 : list) {
            EntityRenderer entityRenderer;
            float f2 = this.a(v2);
            if (f2 <= 0.0f || !((entityRenderer = mc.getEntityRenderDispatcher().getRenderer((Entity)livingEntity)) instanceof LivingEntityRenderer)) continue;
            LivingEntityRenderer livingEntityRenderer = (LivingEntityRenderer)entityRenderer;
            EntityModel entityModel = livingEntityRenderer.getModel();
            Vec3 vec3 = DamageGlow.mc.gameRenderer.getMainCamera().getPosition();
            float f3 = f2 * 0.18f;
            if (f3 <= 0.0f) continue;
            int n2 = livingEntity.hurtTime;
            livingEntity.hurtTime = 0;
            try {
                PoseStack poseStack = renderEvent.a();
                poseStack.pushPose();
                poseStack.translate(v2.c - vec3.x, v2.d - vec3.y, v2.e - vec3.z);
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0f - v2.i));
                poseStack.mulPose(Axis.XP.rotationDegrees(v2.j));
                poseStack.scale(-1.0f, -1.0f, 1.0f);
                poseStack.translate(0.0f, -1.501f, 0.0f);
                entityModel.prepareMobModel((Entity)livingEntity, v2.q, v2.r, 0.0f);
                entityModel.attackTime = v2.s;
                entityModel.setupAnim((Entity)livingEntity, v2.q, v2.r, v2.t, v2.u, v2.v);
                RenderType renderType = RenderType.entityTranslucentEmissive((ResourceLocation)entityRenderer.getTextureLocation((Entity)livingEntity));
                VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);
                float f4 = (float)((Number)this.colorR.getValue()).intValue() / 255.0f;
                float f5 = (float)((Number)this.colorG.getValue()).intValue() / 255.0f;
                float f6 = (float)((Number)this.colorB.getValue()).intValue() / 255.0f;
                float f7 = (float)((Number)this.alpha.getValue()).intValue() / 255.0f * f3;
                entityModel.renderToBuffer(poseStack, vertexConsumer, 0xF000F0, LivingEntityRenderer.getOverlayCoords((LivingEntity)livingEntity, (float)0.0f), f4, f5, f6, f7);
                poseStack.popPose();
                bufferSource.endBatch(renderType);
            }
            finally {
                livingEntity.hurtTime = n2;
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "V^3[\u001c\u0002\u0007\u0007V^3[\u001c\u0002\u0012\nQP2U\tG\u0007y^(";
                var4_3 = "V^3[\u001c\u0002\u0007\u0007V^3[\u001c\u0002\u0012\nQP2U\tG\u0007y^(".length();
                var1_4 = 7;
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
                    var5[var3_1++] = DamageGlow.c(DamageGlow.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "T]/\\\u000f\u0007V^3[\u001c\u0002\u0002";
                    var4_3 = "T]/\\\u000f\u0007V^3[\u001c\u0002\u0002".length();
                    var1_4 = 5;
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
                    var5[var3_1++] = DamageGlow.c(DamageGlow.a(v1));
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
        DamageGlow.p = var5;
    }

    private void a(LivingEntity livingEntity) {
        int n3;
        int n4;
        List list = this.k.computeIfAbsent(livingEntity.getId(), n2 -> new CopyOnWriteArrayList());
        this.b(list);
        v v2 = list.isEmpty() ? null : (v)list.get(list.size() - 1);
        int n5 = livingEntity.hurtTime;
        long l2 = System.currentTimeMillis();
        if (n5 <= 0) {
            return;
        }
        if (v2 != null && n5 <= (n4 = Math.max(0, v2.w - (n3 = (int)((l2 - v2.a) / 50L))))) {
            return;
        }
        float f2 = livingEntity.getYHeadRot() - livingEntity.yBodyRot;
        float f3 = livingEntity.getXRot();
        float f4 = (float)livingEntity.tickCount + mc.getFrameTime();
        v v3 = new v(l2, l2 + 1500L, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), livingEntity.xo, livingEntity.yo, livingEntity.zo, livingEntity.getYRot(), livingEntity.getXRot(), livingEntity.getYHeadRot(), livingEntity.getXRot(), livingEntity.yBodyRot, livingEntity.yBodyRotO, livingEntity.yHeadRotO, livingEntity.xRotO, livingEntity.walkAnimation.position(0.0f), livingEntity.walkAnimation.speed(0.0f), livingEntity.attackAnim, f4, f2, f3, n5);
        list.add(v3);
        if (list.size() > 6) {
            list.remove(0);
        }
    }

    private void b(List<v> list) {
        long l2 = System.currentTimeMillis();
        list.removeIf(v2 -> l2 > v2.b);
    }
}

