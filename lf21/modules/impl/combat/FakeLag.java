/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.ArrayList;
import java.util.List;
import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.PacketUtil;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class FakeLag
extends Module {
    private static final String[] v;
    private final ModeSetting mode;
    private final NumberSetting recoilTime;
    public static FakeLag j;
    private final NumberSetting delay;
    private final BooleanSetting flushOnDamage;
    private final NumberSetting range;
    private final BooleanSetting flushOnInteract;
    private final List<Packet<?>> s;
    private long t;
    private final BooleanSetting flushOnAttack;
    private boolean u;
    private final BooleanSetting render;

    @EventTarget
    public void a(c c2) {
        if (FakeLag.mc.player == null || this.s.isEmpty()) {
            return;
        }
        Packet<ClientGamePacketListener> packet = c2.a();
        if (packet instanceof ClientboundPlayerPositionPacket) {
            this.a();
            return;
        }
        if (((Boolean)this.flushOnDamage.getValue()).booleanValue()) {
            ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket;
            if (packet instanceof ClientboundSetEntityMotionPacket && (clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet).getId() == FakeLag.mc.player.getId() && (clientboundSetEntityMotionPacket.getXa() != 0 || clientboundSetEntityMotionPacket.getYa() != 0 || clientboundSetEntityMotionPacket.getZa() != 0)) {
                this.a();
                return;
            }
            if (packet instanceof ClientboundSetHealthPacket && (clientboundSetEntityMotionPacket = (ClientboundSetHealthPacket)packet).getHealth() < FakeLag.mc.player.getHealth()) {
                this.a();
                return;
            }
        }
    }

    private List<Vec3> h() {
        ArrayList<Vec3> arrayList = new ArrayList<Vec3>();
        for (Packet<?> packet : this.s) {
            if (packet instanceof ServerboundMovePlayerPacket.PosRot) {
                ServerboundMovePlayerPacket.PosRot posRot = (ServerboundMovePlayerPacket.PosRot)packet;
                arrayList.add(new Vec3(posRot.getX(0.0), posRot.getY(0.0), posRot.getZ(0.0)));
                continue;
            }
            if (!(packet instanceof ServerboundMovePlayerPacket.Pos)) continue;
            ServerboundMovePlayerPacket.Pos pos = (ServerboundMovePlayerPacket.Pos)packet;
            arrayList.add(new Vec3(pos.getX(0.0), pos.getY(0.0), pos.getZ(0.0)));
        }
        if (!arrayList.isEmpty() && FakeLag.mc.player != null) {
            arrayList.add(FakeLag.mc.player.position());
        }
        return arrayList;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x13);
        }
        return cArray;
    }

    public FakeLag() {
        String[] stringArray = v;
        super("FakeLag", Category.COMBAT);
        this.range = new NumberSetting("Range", 4.5, 1, 10, 0.5);
        this.delay = new NumberSetting("Delay", 160, 50, 1000, 10);
        this.recoilTime = new NumberSetting("Recoil Time", 250, 0, 1000, 10);
        this.mode = new ModeSetting("Mode", "Dynamic", "Constant").withDefault("Dynamic");
        this.flushOnAttack = new BooleanSetting("Flush On Attack", true);
        this.flushOnInteract = new BooleanSetting("Flush On Interact", true);
        this.flushOnDamage = new BooleanSetting("Flush On Damage", true);
        this.render = new BooleanSetting("Render", true);
        this.s = new ArrayList();
        this.t = 0L;
        this.u = false;
        j = this;
    }

    private void a() {
        if (this.s.isEmpty()) {
            return;
        }
        for (Packet<?> packet : this.s) {
            try {
                if (mc.getConnection() == null) continue;
                Packet<?> packet2 = packet;
                PacketUtil.a.add(packet2);
                mc.getConnection().send(packet2);
            }
            catch (Exception exception) {}
        }
        this.s.clear();
        this.t = System.currentTimeMillis();
    }

    private boolean a(Packet<?> packet) {
        return packet instanceof ServerboundInteractPacket || packet instanceof ServerboundSwingPacket;
    }

    @Override
    protected void onDisable() {
        this.a();
    }

    public boolean i() {
        return !this.s.isEmpty();
    }

    private boolean b(Packet<?> packet) {
        return packet instanceof ServerboundUseItemOnPacket || packet instanceof ServerboundPlayerActionPacket;
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        if (FakeLag.mc.player == null || FakeLag.mc.level == null) {
            return;
        }
        if (packetEvent.a()) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (!(packet instanceof ServerboundMovePlayerPacket)) {
            if (((Boolean)this.flushOnAttack.getValue()).booleanValue() && this.a(packet)) {
                this.a();
                return;
            }
            if (((Boolean)this.flushOnInteract.getValue()).booleanValue() && this.b(packet)) {
                this.a();
                return;
            }
            return;
        }
        if ("Dynamic".equals(this.mode.getValue()) && !this.u) {
            if (!this.s.isEmpty()) {
                this.a();
            }
            return;
        }
        if (System.currentTimeMillis() - this.t < ((Number)this.recoilTime.getValue()).longValue()) {
            return;
        }
        packetEvent.a(true);
        this.s.add(packet);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[12];
                var3_1 = 0;
                var2_2 = "vf\u0000w\u0012\u0016\\^*4p\u000eWp[\u000bbo\u0016k\u0013Z3dc\u0018a\u0007ts\u001be\u0017_p\u000fvf\u0000w\u0012\u0016\\^*1e\u0017WtU\u0005to\u0019e\u0003\u0011vf\u0000w\u0012\u0016\\^*<j\u000eSaQi\u0001\u0005bk\u001bc\u001f\u0004}e\u0011a\u0006bo\u001b`\u001fD\u0007ts\u001be\u0017_p";
                var4_3 = "vf\u0000w\u0012\u0016\\^*4p\u000eWp[\u000bbo\u0016k\u0013Z3dc\u0018a\u0007ts\u001be\u0017_p\u000fvf\u0000w\u0012\u0016\\^*1e\u0017WtU\u0005to\u0019e\u0003\u0011vf\u0000w\u0012\u0016\\^*<j\u000eSaQi\u0001\u0005bk\u001bc\u001f\u0004}e\u0011a\u0006bo\u001b`\u001fD\u0007ts\u001be\u0017_p".length();
                var1_4 = 15;
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
                    var5[var3_1++] = FakeLag.c(FakeLag.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "se\u001bw\u000eW}D\u0007vk\u001ea6Wt";
                    var4_3 = "se\u001bw\u000eW}D\u0007vk\u001ea6Wt".length();
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
                    var5[var3_1++] = FakeLag.c(FakeLag.a(v1));
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
        FakeLag.v = var5;
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
                    case 1 -> 10;
                    case 2 -> 117;
                    case 3 -> 4;
                    case 4 -> 122;
                    case 5 -> 54;
                    default -> 19;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    protected void onEnable() {
        this.s.clear();
        this.t = 0L;
        this.u = false;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (!((Boolean)this.render.getValue()).booleanValue()) {
            return;
        }
        if (this.s.isEmpty() || FakeLag.mc.player == null || FakeLag.mc.gameRenderer == null) {
            return;
        }
        List<Vec3> list = this.h();
        if (list.size() < 2) {
            return;
        }
        Vec3 vec3 = FakeLag.mc.gameRenderer.getMainCamera().getPosition();
        PoseStack poseStack = renderEvent.a();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
        RenderSystem.lineWidth((float)2.5f);
        RenderSystem.disableCull();
        Matrix4f matrix4f = poseStack.last().pose();
        Matrix3f matrix3f = poseStack.last().normal();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR_NORMAL);
        float f2 = 0.0f;
        float f3 = 0.9f;
        float f4 = 1.0f;
        float f5 = 0.8f;
        double d2 = 0.5;
        for (int i2 = 0; i2 < list.size() - 1; ++i2) {
            Vec3 vec32 = list.get(i2);
            Vec3 vec33 = list.get(i2 + 1);
            int n2 = i2 + 1;
            while (n2 + 1 < list.size()) {
                Vec3 vec34 = list.get(n2 + 1);
                double d3 = vec33.x - vec32.x;
                double d4 = vec33.y - vec32.y;
                double d5 = vec33.z - vec32.z;
                double d6 = vec34.x - vec33.x;
                double d7 = vec34.y - vec33.y;
                double d8 = vec34.z - vec33.z;
                if (!(Math.abs(d3 - d6) < 0.01) || !(Math.abs(d4 - d7) < 0.01) || !(Math.abs(d5 - d8) < 0.01)) break;
                vec33 = vec34;
                ++n2;
            }
            i2 = n2 - 1;
            float f6 = (float)vec33.x;
            float f7 = (float)vec32.x;
            float f8 = f6 - f7;
            float f9 = (float)(vec33.y + d2);
            float f10 = (float)(vec32.y + d2);
            float f11 = f9 - f10;
            float f12 = (float)vec33.z;
            float f13 = (float)vec32.z;
            float f14 = f12 - f13;
            float f15 = (float)Math.sqrt(f8 * f8 + f11 * f11 + f14 * f14);
            float f16 = f15 > 0.0f ? f8 / f15 : 0.0f;
            float f17 = f15 > 0.0f ? f11 / f15 : 0.0f;
            float f18 = f15 > 0.0f ? f14 / f15 : 0.0f;
            bufferBuilder.vertex(matrix4f, f7, f10, f13).color(f2, f3, f4, f5).normal(matrix3f, f16, f17, f18).endVertex();
            bufferBuilder.vertex(matrix4f, f6, f9, f12).color(f2, f3, f4, f5).normal(matrix3f, f16, f17, f18).endVertex();
        }
        Tesselator.getInstance().end();
        RenderSystem.enableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.disableBlend();
        poseStack.popPose();
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        long l2;
        if (FakeLag.mc.player == null || FakeLag.mc.level == null) {
            return;
        }
        double d2 = ((Number)this.range.getValue()).doubleValue();
        this.u = FakeLag.mc.level.players().stream().filter(abstractClientPlayer -> abstractClientPlayer != FakeLag.mc.player).filter(LivingEntity::isAlive).anyMatch(abstractClientPlayer -> (double)FakeLag.mc.player.distanceTo((Entity)abstractClientPlayer) <= d2);
        if (!this.s.isEmpty() && (l2 = System.currentTimeMillis() - this.t) >= ((Number)this.delay.getValue()).longValue()) {
            this.a();
        }
    }
}

