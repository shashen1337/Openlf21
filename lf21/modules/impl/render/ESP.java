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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.TeleportAura;
import lf21.modules.impl.misc.MurderDetector;
import lf21.modules.impl.render.w;
import lf21.render.D;
import lf21.render.M;
import lf21.render.N;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.b.i;
import lf21.utils.game.c;
import lf21.utils.game.h;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.b;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector4d;

public class ESP
extends Module {
    private final M y;
    private static final String[] B;
    public static ESP INSTANCE;
    private final NumberSetting bloomCompression;
    private final List<Entity> w;
    private final BooleanSetting showHealthBar;
    private final BooleanSetting players;
    private final List<i> x;
    private final NumberSetting bloomRadius;
    private final N z;
    private final BooleanSetting skeleton;
    private final Map<Entity, float[][]> t;
    private final ModeSetting healthBarPosition;
    private final BooleanSetting items;
    private final BooleanSetting animals;
    private boolean A;
    private final BooleanSetting mobs;
    private final ModeSetting mode;
    private final BooleanSetting arrows;
    private final Map<Entity, w<Vector4d, Boolean>> s;

    @EventTarget(a=4)
    public void a(Render2DEvent render2DEvent) {
        if (this.mode.is("Glow")) {
            this.j();
        }
        if (!this.h() || this.s.isEmpty()) {
            return;
        }
        if (!this.a()) {
            this.b(render2DEvent);
            return;
        }
        Matrix4f matrix4f = render2DEvent.b().pose().last().pose();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        for (Map.Entry<Entity, w<Vector4d, Boolean>> entry : this.s.entrySet()) {
            Vector4d vector4d = (Vector4d)entry.getValue().a;
            if (vector4d.z <= 0.0 || vector4d.w <= 0.0) continue;
            Entity entity = entry.getKey();
            Color color = ESP.getEspColor(entity);
            float f2 = (float)vector4d.x();
            float f3 = (float)vector4d.y();
            float f4 = f2 + (float)vector4d.z();
            float f5 = f3 + (float)vector4d.w();
            this.a(bufferBuilder, matrix4f, f2, f3, f4, f5, color);
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            if (!((Boolean)this.showHealthBar.getValue()).booleanValue()) continue;
            this.a(bufferBuilder, matrix4f, livingEntity, vector4d, color);
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        RenderSystem.disableBlend();
        this.b(render2DEvent);
    }

    private boolean h() {
        return this.a() || this.mode.is("Glow") || this.i();
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
                    case 0 -> 48;
                    case 1 -> 79;
                    case 2 -> 51;
                    case 3 -> 120;
                    case 4 -> 9;
                    case 5 -> 104;
                    default -> 21;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    protected void onDisable() {
        this.s.clear();
        this.t.clear();
        this.w.clear();
        this.z.e();
        super.onDisable();
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (ESP.mc.level == null || ESP.mc.player == null) {
            return;
        }
        if (!this.h()) {
            this.s.clear();
            return;
        }
        b.a();
        this.s.clear();
        this.w.clear();
        float f2 = renderEvent.b();
        for (Entity entity : ESP.mc.level.entitiesForRendering()) {
            if (!this.a(entity) || !this.b(entity)) continue;
            this.w.add(entity);
        }
        if (this.mode.is("Glow")) {
            this.b(renderEvent);
        } else {
            this.z.e();
        }
        for (Entity entity : this.w) {
            i i22;
            AABB aABB = c.b(entity, f2);
            Vec3[] vec3Array = new Vec3[]{new Vec3(aABB.minX, aABB.minY, aABB.minZ), new Vec3(aABB.maxX, aABB.minY, aABB.minZ), new Vec3(aABB.maxX, aABB.minY, aABB.maxZ), new Vec3(aABB.minX, aABB.minY, aABB.maxZ), new Vec3(aABB.minX, aABB.maxY, aABB.minZ), new Vec3(aABB.maxX, aABB.maxY, aABB.minZ), new Vec3(aABB.maxX, aABB.maxY, aABB.maxZ), new Vec3(aABB.minX, aABB.maxY, aABB.maxZ)};
            this.x.clear();
            boolean bl = true;
            for (Vec3 vec3 : vec3Array) {
                i i3 = b.a(vec3.x, vec3.y, vec3.z);
                if (i3 == null) {
                    bl = false;
                    break;
                }
                this.x.add(i3);
            }
            if (!bl || this.x.isEmpty()) continue;
            float f3 = Float.MAX_VALUE;
            float f4 = Float.MAX_VALUE;
            float f5 = Float.MIN_VALUE;
            float f6 = Float.MIN_VALUE;
            for (i i22 : this.x) {
                if (i22.a < f3) {
                    f3 = i22.a;
                }
                if (i22.b < f4) {
                    f4 = i22.b;
                }
                if (i22.a > f5) {
                    f5 = i22.a;
                }
                if (!(i22.b > f6)) continue;
                f6 = i22.b;
            }
            int n2 = 3;
            i22 = new Vector4d((double)((int)(f3 - (float)n2)), (double)((int)(f4 - (float)n2)), (double)((int)(f5 - f3 + (float)(n2 * 2))), (double)((int)(f6 - f4 + (float)(n2 * 2))));
            this.s.put(entity, lf21.modules.impl.render.w.a(i22, true));
        }
    }

    private void b(Render2DEvent render2DEvent) {
        for (Map.Entry<Entity, w<Vector4d, Boolean>> entry : this.s.entrySet()) {
            Player player;
            Object object = entry.getKey();
            if (!(object instanceof Player) || (object = h.a(player = (Player)object)).isEmpty()) continue;
            Vector4d vector4d = (Vector4d)entry.getValue().a;
            float f2 = 22.0f;
            float f3 = 8.0f + (float)object.size() * 16.0f;
            float f4 = (float)vector4d.x() - f2 - 5.0f;
            float f5 = (float)(vector4d.y() + Math.max(0.0, (vector4d.w() - (double)f3) * 0.5));
            f4 = Math.max(2.0f, f4);
            f5 = Mth.clamp((float)f5, (float)2.0f, (float)Math.max(2.0f, (float)mc.getWindow().getGuiScaledHeight() - f3 - 2.0f));
            float f6 = f4;
            float f7 = f5;
            RenderUtil.a(render2DEvent.a(), f4, f5, f2, f3, 6.0f, 12.0f, 0.9f, 0);
            Renderer.render(render2DEvent.b(), drawContext -> {
                try (D d2 = new D();){
                    d2.a(true);
                    d2.a(-1191182336);
                    drawContext.a(Q.a(f6, f7, f2, f3, 6.0f), d2);
                }
            });
            int n2 = Math.round(f4 + 3.0f);
            int n3 = Math.round(f5 + 4.0f);
            Iterator iterator = object.iterator();
            while (iterator.hasNext()) {
                ItemStack itemStack = (ItemStack)iterator.next();
                render2DEvent.b().renderItem(itemStack, n2, n3);
                n3 += 16;
            }
        }
    }

    public float l() {
        return ((Number)this.bloomCompression.getValue()).floatValue();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x15);
        }
        return cArray;
    }

    public ESP() {
        String[] stringArray = B;
        super("ESP", Category.RENDER);
        this.mode = new ModeSetting("Mode", "Glow", "Outlined 2D").withDefault("Glow");
        this.bloomRadius = new NumberSetting("Bloom Radius", 14.0, 1.0, 30.0, 1.0, () -> this.mode.is("Glow"));
        this.bloomCompression = new NumberSetting("Bloom Compression", 2.0, 1.0, 10.0, 0.1, () -> this.mode.is("Glow"));
        this.skeleton = new BooleanSetting("Skeleton", false);
        this.players = new BooleanSetting("Players", true);
        this.mobs = new BooleanSetting("Mobs", false);
        this.animals = new BooleanSetting("Animals", false);
        this.items = new BooleanSetting("Items", false);
        this.arrows = new BooleanSetting("Arrows", true);
        this.s = new HashMap<Entity, w<Vector4d, Boolean>>();
        this.t = new HashMap<Entity, float[][]>();
        this.showHealthBar = new BooleanSetting("Show Health Bar", true);
        this.healthBarPosition = new ModeSetting("Health Bar Position", "Bottom", "Top", "Left", "Right").withDefault("Bottom");
        this.w = new ArrayList<Entity>();
        this.x = new ArrayList<i>();
        this.y = new M();
        this.z = new N();
        INSTANCE = this;
    }

    public static Color getEspColor(Entity entity) {
        LivingEntity livingEntity;
        if (entity instanceof LivingEntity) {
            livingEntity = (LivingEntity)entity;
            if (livingEntity.hurtTime > 0) {
                return new Color(255, 0, 0);
            }
        }
        if (TeleportAura.INSTANCE != null && TeleportAura.INSTANCE.isLockedTarget(entity)) {
            return new Color(255, 55, 55);
        }
        if (LF21Client.getInstance() != null && LF21Client.getInstance().getLookForManager() != null && LF21Client.getInstance().getLookForManager().isTarget(entity)) {
            return new Color(70, 255, 100);
        }
        if (MurderDetector.isMurderer(entity)) {
            return new Color(255, 55, 55);
        }
        if (entity instanceof Player && !h.a((Player)(livingEntity = (Player)entity)).isEmpty()) {
            return new Color(255, 195, 55);
        }
        return Color.WHITE;
    }

    private void b(RenderEvent renderEvent) {
        this.A = true;
        try {
            this.z.a(renderEvent.a(), renderEvent.b(), this.w, (Entity entity) -> true, ESP::getEspColor);
        }
        finally {
            this.A = false;
        }
    }

    private void a(BufferBuilder bufferBuilder, Matrix4f matrix4f, LivingEntity livingEntity, Vector4d vector4d, Color color) {
        float f2;
        float f3;
        float f4;
        String[] stringArray;
        float f5;
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            f5 = Mth.clamp((float)(Math.min(player.getHealth(), player.getMaxHealth()) / player.getMaxHealth()), (float)0.0f, (float)1.0f);
        } else {
            f5 = Math.min(livingEntity.getHealth() / livingEntity.getMaxHealth(), 1.0f);
        }
        float f6 = (float)vector4d.x();
        float f7 = (float)vector4d.y();
        float f8 = f6 + (float)vector4d.z();
        float f9 = f7 + (float)vector4d.w();
        String string = (String)this.healthBarPosition.getValue();
        if (string == null) {
            stringArray = B;
            string = "Bottom";
        }
        Object object = string;
        int n2 = -1;
        switch (((String)object).hashCode()) {
            case 84277: {
                stringArray = B;
                if (!((String)object).equals("Top")) break;
                n2 = 0;
                break;
            }
            case 2364455: {
                stringArray = B;
                if (!((String)object).equals("Left")) break;
                n2 = 1;
                break;
            }
            case 78959100: {
                stringArray = B;
                if (!((String)object).equals("Right")) break;
                n2 = 2;
            }
        }
        float f10 = switch (n2) {
            case 0 -> {
                f4 = (float)vector4d.z();
                f3 = 2.0f;
                f2 = f6;
                yield f7 - 4.0f;
            }
            case 1 -> {
                f4 = 2.0f;
                f3 = (float)vector4d.w();
                f2 = f6 - 4.0f;
                yield f7;
            }
            case 2 -> {
                f4 = 2.0f;
                f3 = (float)vector4d.w();
                f2 = f8 + 2.0f;
                yield f7;
            }
            default -> {
                f4 = (float)vector4d.z();
                f3 = 2.0f;
                f2 = f6;
                yield f9 + 2.0f;
            }
        };
        RenderUtil.a(bufferBuilder, matrix4f, f2 - 0.6f, f10 - 0.6f, f2 + f4 + 0.6f, f10 + f3 + 0.6f, Color.BLACK);
        RenderUtil.a(bufferBuilder, matrix4f, f2, f10, f2 + f4, f10 + f3, color.darker().darker());
        object = this.a(f5);
        stringArray = B;
        if (string.equals("Left") || string.equals("Right")) {
            RenderUtil.a(bufferBuilder, matrix4f, f2, f10 + f3 * (1.0f - f5), f2 + f4, f10 + f3, (Color)object);
        } else {
            RenderUtil.a(bufferBuilder, matrix4f, f2, f10, f2 + f4 * f5, f10 + f3, (Color)object);
        }
    }

    public boolean usesModelOutline(Entity entity) {
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[25];
                var3_1 = 0;
                var2_2 = "\u007f:G\u0014`\u0006pTo\u0001<\u0007q!Z\u0015h\u0004f\u0006r G\ff\u0005\u0005y;V\u0015z\u0004} W\u001d\u0004w#\\\u000f\tb&@\u001d)/y_8\u0004} Q\u000b\u0007`#R\u0001l\u001af\u000b\u007f:G\u0014`\u0006pTo\u0001<\u0006q=A\u0017~\u001b\fr#\\\u0017dHGQ+Z\rz\u0003u\u001cc\u0003d C\u000fc'\\\u000f) pQ#G\u0010)*tB\u0011r#\\\u0017dHV_\"C\nl\u001bfY ]\u0013x*R\u0014}\u00005r.AXY\u0007fY;Z\u0017g\u0005b&T\u0010}\u0004w#\\\u000f\u0005b&T\u0010}\u0004|*U\f\u0006r G\ff\u0005\u0003d C";
                var4_3 = "\u007f:G\u0014`\u0006pTo\u0001<\u0007q!Z\u0015h\u0004f\u0006r G\ff\u0005\u0005y;V\u0015z\u0004} W\u001d\u0004w#\\\u000f\tb&@\u001d)/y_8\u0004} Q\u000b\u0007`#R\u0001l\u001af\u000b\u007f:G\u0014`\u0006pTo\u0001<\u0006q=A\u0017~\u001b\fr#\\\u0017dHGQ+Z\rz\u0003u\u001cc\u0003d C\u000fc'\\\u000f) pQ#G\u0010)*tB\u0011r#\\\u0017dHV_\"C\nl\u001bfY ]\u0013x*R\u0014}\u00005r.AXY\u0007fY;Z\u0017g\u0005b&T\u0010}\u0004w#\\\u000f\u0005b&T\u0010}\u0004|*U\f\u0006r G\ff\u0005\u0003d C".length();
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
                    var5[var3_1++] = ESP.c(ESP.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "c$V\u0014l\u001cz^\u0004|*U\f";
                    var4_3 = "c$V\u0014l\u001cz^\u0004|*U\f".length();
                    var1_4 = 8;
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
                    var5[var3_1++] = ESP.c(ESP.a(v1));
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
        ESP.B = var5;
    }

    public float k() {
        return ((Number)this.bloomRadius.getValue()).floatValue();
    }

    private void a(PoseStack poseStack, float f2) {
    }

    private boolean b(Entity entity) {
        double d2 = ESP.mc.player.distanceToSqr(entity);
        return d2 < 10000.0;
    }

    private Color a(float f2) {
        return Color.getHSBColor(Math.max(0.0f, f2) / 3.0f, 1.0f, 1.0f);
    }

    private void a(BufferBuilder bufferBuilder, Matrix4f matrix4f, float f2, float f3, float f4, float f5, Color color) {
        RenderUtil.a(bufferBuilder, matrix4f, f2 - 1.0f, f3, f2 + 0.5f, f5 + 0.5f, Color.BLACK);
        RenderUtil.a(bufferBuilder, matrix4f, f2 - 1.0f, f3 - 0.5f, f4 + 0.5f, f3 + 1.0f, Color.BLACK);
        RenderUtil.a(bufferBuilder, matrix4f, f4 - 0.5f, f3, f4 + 0.5f, f5 + 0.5f, Color.BLACK);
        RenderUtil.a(bufferBuilder, matrix4f, f2 - 1.0f, f5 - 0.5f, f4 + 0.5f, f5 + 0.5f, Color.BLACK);
        RenderUtil.a(bufferBuilder, matrix4f, f2 - 0.5f, f3, f2, f5, color);
        RenderUtil.a(bufferBuilder, matrix4f, f2, f5 - 0.5f, f4, f5, color);
        RenderUtil.a(bufferBuilder, matrix4f, f2, f3, f4, f3 + 0.5f, color);
        RenderUtil.a(bufferBuilder, matrix4f, f4 - 0.5f, f3, f4, f5, color);
    }

    private boolean a() {
        return this.mode.is("Outlined 2D");
    }

    private void j() {
        if (!this.z.a()) {
            return;
        }
        this.y.a(this.z.b(), this.z.c(), this.z.d(), ((Number)this.bloomRadius.getValue()).floatValue(), ((Number)this.bloomCompression.getValue()).floatValue());
    }

    private boolean i() {
        if (ESP.mc.level == null || ESP.mc.player == null) {
            return false;
        }
        for (Player player : ESP.mc.level.players()) {
            if (player == ESP.mc.player || h.a(player).isEmpty()) continue;
            return true;
        }
        return false;
    }

    public boolean isGlowing(Entity entity) {
        if (this.A) {
            return false;
        }
        return TeleportAura.INSTANCE != null && TeleportAura.INSTANCE.isLockedTarget(entity);
    }

    @Override
    protected void onEnable() {
        String[] stringArray = B;
        if ("Rise Glow".equals(this.mode.getValue())) {
            this.mode.setValue("Glow");
        } else if (this.mode.getValue() == null || !List.of(this.mode.getModes()).contains(this.mode.getValue())) {
            this.mode.setValue("Glow");
        }
        super.onEnable();
    }

    private boolean a(Entity entity) {
        if (entity == ESP.mc.player) {
            return false;
        }
        if (TeleportAura.INSTANCE != null && TeleportAura.INSTANCE.isLockedTarget(entity)) {
            return true;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (((Boolean)this.players.getValue()).booleanValue() || !h.a(player).isEmpty()) {
                return true;
            }
        }
        if (entity instanceof Animal && ((Boolean)this.animals.getValue()).booleanValue()) {
            return true;
        }
        if (entity instanceof Mob && ((Boolean)this.mobs.getValue()).booleanValue()) {
            return true;
        }
        if (entity instanceof ItemEntity && ((Boolean)this.items.getValue()).booleanValue()) {
            return true;
        }
        return entity instanceof Arrow && (Boolean)this.arrows.getValue() != false;
    }
}

