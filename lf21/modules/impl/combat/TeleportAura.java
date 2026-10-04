/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lf21.event.EventTarget;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.AntiBots;
import lf21.modules.impl.player.NoFall;
import lf21.modules.impl.world.Teams;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.PacketUtil;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundAcceptTeleportationPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public final class TeleportAura
extends Module {
    public final BooleanSetting render;
    private LivingEntity t;
    private final List<Vec3> o;
    public static TeleportAura INSTANCE;
    private boolean u;
    private LivingEntity s;
    private String r;
    private LivingEntity q;
    private Vec3 y;
    public final NumberSetting range;
    public final BooleanSetting _19Cooldown;
    private long x;
    public final NumberSetting cPS;
    public final NumberSetting holdDelay;
    private long w;
    private long p;
    private boolean v;
    private static final String[] A;
    private int z;

    private LivingEntity h() {
        double d2;
        Vec3 vec3;
        Vec3 vec32;
        if (TeleportAura.mc.player == null || TeleportAura.mc.level == null) {
            return null;
        }
        Vec3 vec33 = TeleportAura.mc.player.getEyePosition(1.0f);
        BlockHitResult blockHitResult = TeleportAura.mc.level.clip(new ClipContext(vec33, vec32 = vec33.add((vec3 = TeleportAura.mc.player.getViewVector(1.0f)).scale(d2 = ((Number)this.range.getValue()).doubleValue())), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)TeleportAura.mc.player));
        double d3 = blockHitResult.getType() == HitResult.Type.MISS ? d2 * d2 : vec33.distanceToSqr(blockHitResult.getLocation());
        AABB aABB = TeleportAura.mc.player.getBoundingBox().expandTowards(vec3.scale(d2)).inflate(1.0);
        LivingEntity livingEntity = null;
        for (Entity entity : TeleportAura.mc.level.getEntities((Entity)TeleportAura.mc.player, aABB, this::b)) {
            Vec3 vec34;
            LivingEntity livingEntity2 = (LivingEntity)entity;
            AABB aABB2 = livingEntity2.getBoundingBox().inflate((double)Math.max(0.1f, livingEntity2.getPickRadius()));
            if (aABB2.contains(vec33)) {
                vec34 = vec33;
            } else {
                Optional optional = aABB2.clip(vec33, vec32);
                if (optional.isEmpty()) continue;
                vec34 = (Vec3)optional.get();
            }
            double d4 = vec33.distanceToSqr(vec34);
            if (d4 > d3) continue;
            d3 = d4;
            livingEntity = livingEntity2;
        }
        return livingEntity;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        List<Vec3> list;
        Vec3 vec3;
        if (!((Boolean)this.render.getValue()).booleanValue() || TeleportAura.mc.gameRenderer == null || TeleportAura.mc.player == null) {
            return;
        }
        if (this.a()) {
            float f2 = renderEvent.b();
            vec3 = TeleportAura.mc.player.getEyePosition(f2);
            double d2 = this.q.xo + (this.q.getX() - this.q.xo) * (double)f2;
            double d3 = this.q.yo + (this.q.getY() - this.q.yo) * (double)f2 + (double)this.q.getBbHeight() * 0.5;
            double d4 = this.q.zo + (this.q.getZ() - this.q.zo) * (double)f2;
            list = List.of(vec3, new Vec3(d2, d3, d4));
        } else {
            if (this.o.size() < 2) {
                return;
            }
            list = this.o;
        }
        PoseStack poseStack = renderEvent.a();
        vec3 = TeleportAura.mc.gameRenderer.getMainCamera();
        Vec3 vec32 = vec3.getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec32.x, -vec32.y, -vec32.z);
        Matrix4f matrix4f = poseStack.last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.lineWidth((float)1.5f);
        bufferBuilder.begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        for (Vec3 vec33 : list) {
            bufferBuilder.vertex(matrix4f, (float)vec33.x, (float)vec33.y, (float)vec33.z).color(90, 235, 255, 220).endVertex();
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
        RenderSystem.lineWidth((float)1.0f);
        poseStack.popPose();
    }

    private boolean a(LivingEntity livingEntity) {
        if (livingEntity == TeleportAura.mc.player || !livingEntity.isAlive() || livingEntity.isRemoved()) {
            return false;
        }
        if ((double)TeleportAura.mc.player.distanceTo((Entity)livingEntity) > ((Number)this.range.getValue()).doubleValue()) {
            return false;
        }
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            return !Teams.a((Entity)player) && !AntiBots.b((Entity)player);
        }
        return livingEntity instanceof Mob || livingEntity instanceof Animal;
    }

    @Override
    protected void onDisable() {
        this.o.clear();
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = false;
        this.v = false;
        this.p = 0L;
        this.x = 0L;
        this.y = null;
        this.z = 0;
    }

    private static String a(Entity entity) {
        return entity.getName().getString();
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
                    case 0 -> 86;
                    case 1 -> 84;
                    case 2 -> 2;
                    case 3 -> 43;
                    case 4 -> 29;
                    case 5 -> 18;
                    default -> 23;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void a(long l2) {
        double d2 = Math.max(1.0, ((Number)this.cPS.getValue()).doubleValue());
        if (((Boolean)this._19Cooldown.getValue()).booleanValue()) {
            d2 = Math.min(d2, 4.0);
        }
        this.p = l2 + Math.round(1000.0 / d2);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x17);
        }
        return cArray;
    }

    private void b(LivingEntity livingEntity) {
        this.q = livingEntity;
        this.r = livingEntity == null ? null : TeleportAura.a((Entity)livingEntity);
    }

    public TeleportAura() {
        String[] stringArray = A;
        super("TeleportAura", Category.COMBAT);
        this.range = new NumberSetting("Range", 32.0, 3.0, 100.0, 0.1);
        this.cPS = new NumberSetting("CPS", 10.0, 1.0, 20.0, 1.0);
        this.holdDelay = new NumberSetting("Hold Delay", 250.0, 100.0, 1000.0, 25.0);
        this._19Cooldown = new BooleanSetting("1.9 Cooldown", false);
        this.render = new BooleanSetting("Render", true);
        this.o = new ArrayList<Vec3>();
        INSTANCE = this;
    }

    private boolean b(Entity entity) {
        LivingEntity livingEntity;
        if (!(entity instanceof LivingEntity) || (livingEntity = (LivingEntity)entity) == TeleportAura.mc.player || !livingEntity.isAlive() || livingEntity.isRemoved()) {
            return false;
        }
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            return !Teams.a((Entity)player) && !AntiBots.b((Entity)player);
        }
        return livingEntity instanceof Mob || livingEntity instanceof Animal;
    }

    private LivingEntity i() {
        if (this.r == null || TeleportAura.mc.level == null) {
            return null;
        }
        if (this.q != null && this.b((Entity)this.q) && this.r.equalsIgnoreCase(TeleportAura.a((Entity)this.q))) {
            return this.q;
        }
        for (Entity entity : TeleportAura.mc.level.entitiesForRendering()) {
            LivingEntity livingEntity;
            if (!(entity instanceof LivingEntity) || !this.b((Entity)(livingEntity = (LivingEntity)entity)) || !this.r.equalsIgnoreCase(TeleportAura.a((Entity)livingEntity))) continue;
            return livingEntity;
        }
        return null;
    }

    private void a(Vec3 vec3, boolean bl) {
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(vec3.x, vec3.y, vec3.z, bl));
    }

    private boolean a() {
        return this.q != null && this.q.level() == TeleportAura.mc.level && this.q.isAlive() && !this.q.isRemoved();
    }

    private Vec3 a(Vec3 vec3, LivingEntity livingEntity) {
        if ((double)TeleportAura.mc.player.distanceTo((Entity)livingEntity) <= 2.8) {
            return vec3;
        }
        Vec3 vec32 = livingEntity.position();
        Vec3 vec33 = new Vec3(vec3.x - vec32.x, 0.0, vec3.z - vec32.z);
        if (vec33.lengthSqr() < 1.0E-6) {
            double d2 = Math.toRadians(TeleportAura.mc.player.getYRot());
            vec33 = new Vec3(-Math.sin(d2), 0.0, Math.cos(d2));
        } else {
            vec33 = vec33.normalize();
        }
        return new Vec3(vec32.x + vec33.x * 2.25, vec32.y, vec32.z + vec33.z * 2.25);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void c(LivingEntity livingEntity) {
        boolean bl;
        NoFall noFall;
        boolean bl2;
        Vec3 vec3;
        block8: {
            this.y = vec3 = TeleportAura.mc.player.position();
            this.z = 20;
            bl2 = TeleportAura.mc.player.onGround();
            Vec3 vec32 = this.a(vec3, livingEntity);
            noFall = NoFall.INSTANCE;
            bl = noFall != null && noFall.x();
            List<Vec3> list = vec3.distanceTo(vec32) < 0.05 ? List.of() : TeleportAura.a(vec3, vec32, 2.75);
            this.o.clear();
            this.o.add(vec3);
            this.o.addAll(list);
            try {
                for (Vec3 vec33 : list) {
                    this.a(vec33, bl);
                }
                TeleportAura.mc.gameMode.attack((Player)TeleportAura.mc.player, (Entity)livingEntity);
                TeleportAura.mc.player.swing(InteractionHand.MAIN_HAND);
                if (list.isEmpty()) break block8;
            }
            catch (Throwable throwable) {
                if (!list.isEmpty()) {
                    for (int i2 = list.size() - 2; i2 >= 0; --i2) {
                        this.a(list.get(i2), bl);
                    }
                }
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.PosRot(vec3.x, vec3.y, vec3.z, TeleportAura.mc.player.getYRot(), TeleportAura.mc.player.getXRot(), bl2 || bl));
                if (bl) {
                    noFall.y();
                }
                throw throwable;
            }
            for (int i3 = list.size() - 2; i3 >= 0; --i3) {
                this.a(list.get(i3), bl);
            }
        }
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.PosRot(vec3.x, vec3.y, vec3.z, TeleportAura.mc.player.getYRot(), TeleportAura.mc.player.getXRot(), bl2 || bl));
        if (bl) {
            noFall.y();
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (TeleportAura.mc.player == null || TeleportAura.mc.level == null || TeleportAura.mc.gameMode == null || TeleportAura.mc.screen != null) {
            return;
        }
        long l2 = System.currentTimeMillis();
        if (this.z > 0) {
            --this.z;
            if (this.z == 0) {
                this.y = null;
            }
        }
        if (this.u && !this.v && this.s != null && l2 - this.w >= ((Number)this.holdDelay.getValue()).longValue()) {
            this.b(this.s);
            this.v = true;
            this.p = 0L;
        }
        if (this.t != null && l2 >= this.p) {
            LivingEntity livingEntity = this.t;
            this.t = null;
            if (this.a(livingEntity)) {
                this.c(livingEntity);
                this.a(l2);
            }
        }
        this.q = this.i();
        if (this.q == null && !this.u) {
            this.o.clear();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "\u00041lOx`\fgz;\u000b^}x:0m\\s\u0003\u0015\u0004Q\f\u00021nNm}e\"\u0015wY|";
                var4_3 = "\u00041lOx`\fgz;\u000b^}x:0m\\s\u0003\u0015\u0004Q\f\u00021nNm}e\"\u0015wY|".length();
                var1_4 = 6;
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
                    var5[var3_1++] = TeleportAura.c(TeleportAura.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u001e;nO=Vr:5{\u0005\u00045lLx";
                    var4_3 = "\u001e;nO=Vr:5{\u0005\u00045lLx".length();
                    var1_4 = 10;
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
                    var5[var3_1++] = TeleportAura.c(TeleportAura.a(v1));
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
        TeleportAura.A = var5;
    }

    private static List<Vec3> a(Vec3 vec3, Vec3 vec32, double d2) {
        double d3 = vec3.distanceTo(vec32);
        int n2 = Math.max(1, (int)Math.ceil(d3 / d2));
        ArrayList<Vec3> arrayList = new ArrayList<Vec3>(n2);
        for (int i2 = 1; i2 <= n2; ++i2) {
            arrayList.add(vec3.lerp(vec32, (double)i2 / (double)n2));
        }
        return arrayList;
    }

    @EventTarget(a=4)
    public void a(c c2) {
        ClientboundPlayerPositionPacket clientboundPlayerPositionPacket;
        block3: {
            block2: {
                Packet<ClientGamePacketListener> packet = c2.a();
                if (!(packet instanceof ClientboundPlayerPositionPacket)) break block2;
                clientboundPlayerPositionPacket = (ClientboundPlayerPositionPacket)packet;
                if (this.z > 0 && this.y != null && TeleportAura.mc.player != null) break block3;
            }
            return;
        }
        c2.a(true);
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundAcceptTeleportationPacket(clientboundPlayerPositionPacket.getId()));
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.PosRot(this.y.x, this.y.y, this.y.z, TeleportAura.mc.player.getYRot(), TeleportAura.mc.player.getXRot(), TeleportAura.mc.player.onGround()));
        this.z = 0;
        this.y = null;
    }

    public boolean shouldInterceptVanillaAttack() {
        return this.isEnabled() && (this.u || System.currentTimeMillis() <= this.x);
    }

    public boolean isLockedTarget(Entity entity) {
        return this.isEnabled() && this.r != null && entity instanceof LivingEntity && this.r.equalsIgnoreCase(TeleportAura.a(entity));
    }

    public void onPhysicalMouse(int n2, int n3) {
        if (!this.isEnabled() || TeleportAura.mc.player == null || TeleportAura.mc.level == null || TeleportAura.mc.screen != null) {
            return;
        }
        long l2 = System.currentTimeMillis();
        if (n2 == 1 && n3 == 1) {
            this.q = null;
            this.r = null;
            this.s = null;
            this.t = null;
            this.o.clear();
            this.p = 0L;
            return;
        }
        if (n2 != 0) {
            return;
        }
        if (n3 == 1) {
            this.u = true;
            this.v = false;
            this.w = l2;
            this.q = this.i();
            if (this.r != null) {
                if (this.q != null) {
                    this.t = this.q;
                }
                this.s = null;
                this.v = true;
                this.x = l2 + 250L;
            } else {
                this.s = this.h();
                if (this.s != null) {
                    this.x = l2 + 750L;
                }
            }
        } else if (n3 == 0) {
            if (this.u && !this.v && this.s != null) {
                this.t = this.s;
            }
            this.u = false;
            this.v = false;
            this.s = null;
        }
    }
}

