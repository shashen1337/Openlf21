/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.render.L;
import lf21.render.M;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.a;
import lf21.utils.game.b;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEventPacket;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class e
extends Module {
    private final NumberSetting glowIntensity;
    private final List<BlockPos> j = new CopyOnWriteArrayList<BlockPos>();
    private final NumberSetting saturation;
    private final L q;
    private static final String[] t;
    private final M r;
    private final NumberSetting glowCompression;
    private final NumberSetting glowRadius;
    private final List<AABB> k = new CopyOnWriteArrayList<AABB>();
    private static final String s;
    private final ModeSetting mode;

    private void a(PoseStack poseStack, List<AABB> list, Color color, boolean bl) {
        if (list.isEmpty()) {
            return;
        }
        this.q.a(poseStack, list, color, bl);
        if (!this.q.a()) {
            return;
        }
        this.r.a(this.q.b(), this.q.c(), this.q.d(), ((Number)this.glowRadius.getValue()).floatValue(), ((Number)this.glowCompression.getValue()).floatValue(), ((Number)this.glowIntensity.getValue()).floatValue());
    }

    private AABB a(ChestBlockEntity chestBlockEntity) {
        BlockPos blockPos;
        BlockState blockState = chestBlockEntity.getBlockState();
        if (!blockState.hasProperty((Property)ChestBlock.TYPE)) {
            return null;
        }
        ChestType chestType = (ChestType)blockState.getValue((Property)ChestBlock.TYPE);
        if (chestType == ChestType.LEFT) {
            return null;
        }
        BlockPos blockPos2 = chestBlockEntity.getBlockPos();
        AABB aABB = a.c(blockPos2);
        if (chestType != ChestType.SINGLE && a.b(blockPos = blockPos2.relative(ChestBlock.getConnectedDirection((BlockState)blockState)))) {
            AABB aABB2 = a.c(blockPos);
            aABB = aABB.minmax(aABB2);
        }
        return aABB;
    }

    public e() {
        super(s, Category.RENDER);
        String[] stringArray = t;
        this.mode = new ModeSetting("Mode", "Box", "Glow").withDefault("Glow");
        this.glowRadius = new NumberSetting("Glow Radius", 14.0, 1.0, 30.0, 1.0, () -> this.mode.is("Glow"));
        this.glowCompression = new NumberSetting("Glow Compression", 2.0, 1.0, 10.0, 0.1, () -> this.mode.is("Glow"));
        this.glowIntensity = new NumberSetting("Glow Intensity", 2.0, 0.1, 2.0, 0.05, () -> this.mode.is("Glow"));
        this.saturation = new NumberSetting("Saturation", 0.55, 0.0, 1.0, 0.05);
        this.q = new L();
        this.r = new M();
    }

    @EventTarget
    public void a(lf21.event.impl.e e2) {
        this.j.clear();
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPost()) {
            ArrayList arrayList = b.a().collect(Collectors.toCollection(ArrayList::new));
            this.k.clear();
            for (BlockEntity blockEntity : arrayList) {
                ChestBlockEntity chestBlockEntity;
                AABB aABB;
                if (!(blockEntity instanceof ChestBlockEntity) || (aABB = this.a(chestBlockEntity = (ChestBlockEntity)blockEntity)) == null) continue;
                this.k.add(aABB);
            }
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
                    case 0 -> 2;
                    case 1 -> 94;
                    case 2 -> 67;
                    case 3 -> 76;
                    case 4 -> 126;
                    case 5 -> 83;
                    default -> 16;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private float[] a(float f2) {
        int n2 = Color.HSBtoRGB(f2, ((Number)this.saturation.getValue()).floatValue(), 1.0f);
        return new float[]{(float)(n2 >> 16 & 0xFF) / 255.0f, (float)(n2 >> 8 & 0xFF) / 255.0f, (float)(n2 & 0xFF) / 255.0f};
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[9];
                var3_1 = 0;
                var2_2 = "E2,;^\u0010\u007fo.1)\r ym0\u0004E2,;\bA6&?\n\u0016CR\u000bE2,;^\u0001qf76?\nQ?79\f2dk1-\u000eE2,;^\u001a~v;-?\u0017'i\u0004E2,;";
                var4_3 = "E2,;^\u0010\u007fo.1)\r ym0\u0004E2,;\bA6&?\n\u0016CR\u000bE2,;^\u0001qf76?\nQ?79\f2dk1-\u000eE2,;^\u001a~v;-?\u0017'i\u0004E2,;".length();
                var1_4 = 16;
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
                    var5[var3_1++] = e.c(e.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "@1;\u0004O1')";
                    var4_3 = "@1;\u0004O1')".length();
                    var1_4 = 3;
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
                    var5[var3_1++] = e.c(e.a(v1));
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
        e.t = var5;
        e.s = "ChestESP";
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        boolean bl;
        ClientboundBlockEventPacket clientboundBlockEventPacket;
        BlockPos blockPos;
        if (!packetEvent.a() || !((blockPos = packetEvent.c()) instanceof ClientboundBlockEventPacket) || (clientboundBlockEventPacket = (ClientboundBlockEventPacket)blockPos).getB0() != 1) {
            return;
        }
        blockPos = clientboundBlockEventPacket.getPos();
        boolean bl2 = clientboundBlockEventPacket.getBlock() == Blocks.CHEST || clientboundBlockEventPacket.getBlock() == Blocks.TRAPPED_CHEST;
        boolean bl3 = bl = e.mc.level != null && e.mc.level.getBlockState(blockPos).getBlock() instanceof ChestBlock;
        if (!bl2 && !bl) {
            return;
        }
        if (clientboundBlockEventPacket.getB1() == 1) {
            this.j.add(blockPos.immutable());
        } else if (clientboundBlockEventPacket.getB1() == 0) {
            this.j.remove(blockPos);
        }
    }

    private Color a(float[] fArray) {
        return new Color(Math.round(fArray[0] * 255.0f), Math.round(fArray[1] * 255.0f), Math.round(fArray[2] * 255.0f), 220);
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        PoseStack poseStack = renderEvent.a();
        poseStack.pushPose();
        Vec3 vec3 = e.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        if (this.mode.is("Glow")) {
            ArrayList<AABB> arrayList = new ArrayList<AABB>();
            ArrayList<AABB> arrayList2 = new ArrayList<AABB>();
            for (AABB aABB : this.k) {
                if (this.a(aABB)) {
                    arrayList2.add(aABB);
                    continue;
                }
                arrayList.add(aABB);
            }
            this.a(poseStack, arrayList, this.a(this.a(0.33333334f)), true);
            this.a(poseStack, arrayList2, this.a(this.a(0.0f)), arrayList.isEmpty());
            poseStack.popPose();
            return;
        }
        this.q.e();
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        Tesselator tesselator = RenderSystem.renderThreadTesselator();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        for (AABB aABB : this.k) {
            float[] fArray = this.a(aABB) ? this.a(0.0f) : this.a(0.33333334f);
            RenderSystem.setShaderColor((float)fArray[0], (float)fArray[1], (float)fArray[2], (float)0.25f);
            RenderUtil.a(bufferBuilder, poseStack.last().pose(), aABB);
        }
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        poseStack.popPose();
    }

    private boolean a(AABB aABB) {
        int n2 = (int)Math.floor(aABB.minX + 1.0E-4);
        int n3 = (int)Math.floor(aABB.minY + 1.0E-4);
        int n4 = (int)Math.floor(aABB.minZ + 1.0E-4);
        int n5 = (int)Math.ceil(aABB.maxX - 1.0E-4) - 1;
        int n6 = (int)Math.ceil(aABB.maxY - 1.0E-4) - 1;
        int n7 = (int)Math.ceil(aABB.maxZ - 1.0E-4) - 1;
        for (int i2 = n2; i2 <= n5; ++i2) {
            for (int i3 = n3; i3 <= n6; ++i3) {
                for (int i4 = n4; i4 <= n7; ++i4) {
                    BlockPos blockPos = new BlockPos(i2, i3, i4);
                    if (this.j.contains(blockPos)) {
                        return true;
                    }
                    if (e.mc.level == null || !(e.mc.level.getBlockEntity(blockPos) instanceof ChestBlockEntity) || ChestBlockEntity.getOpenCount((BlockGetter)e.mc.level, (BlockPos)blockPos) <= 0) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x10);
        }
        return cArray;
    }
}

