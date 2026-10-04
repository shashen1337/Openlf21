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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.render.c;
import lf21.modules.impl.render.o;
import lf21.modules.impl.render.p;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.PlayerUtil;
import lf21.utils.misc.ChatUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class XRay
extends Module {
    private final Color R;
    private static final String[] cb;
    private final NumberSetting range;
    private final BooleanSetting enableAlternativeStart;
    private final Color L;
    private final BooleanSetting blindScan;
    private final BooleanSetting patternAnalysis;
    private final Map<o, BlockState> W;
    private final BooleanSetting tracers;
    private final Map<String, c> ac;
    private final BooleanSetting diamond;
    private final BooleanSetting emerald;
    private final BooleanSetting antiFakeOre;
    private final BooleanSetting I;
    private final Color N;
    private static ThreadPoolExecutor Y;
    private final ModeSetting blockType;
    private Level X;
    private final Color O;
    private final NumberSetting minAuthScore;
    private final NumberSetting blindRenderRange;
    private final Map<BlockPos, Block> S;
    private final BooleanSetting J;
    private long U;
    private final Color M;
    private final Color K;
    private volatile long Z;
    private final Color Q;
    private static final Set<Block> ae;
    private final BooleanSetting D;
    private final NumberSetting maxBlocks;
    private final NumberSetting alternativeSearchRadius;
    private static final Set<Block> af;
    private final BooleanSetting B;
    private final NumberSetting scanDelay;
    private final BooleanSetting A;
    private Matrix4f aa;
    private final BooleanSetting G;
    private final BooleanSetting C;
    private final NumberSetting minAirBlocksRequired;
    private final NumberSetting minimumVeinSize;
    private final Color P;
    private final BooleanSetting H;
    private boolean V;
    private final BooleanSetting E;
    private static final Direction[] ad;
    private Matrix4f ab;
    private final BooleanSetting F;
    public static XRay INSTANCE;
    private final BooleanSetting strictHostRockCheck;
    private volatile Map<BlockPos, Block> T;

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x13);
        }
        return cArray;
    }

    private void a(PoseStack poseStack) {
        float f2 = mc.getWindow().getGuiScaledWidth();
        float f3 = mc.getWindow().getGuiScaledHeight();
        float f4 = f2 / 2.0f;
        float f5 = f3 / 2.0f;
        Vec3 vec3 = XRay.mc.gameRenderer.getMainCamera().getPosition();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);
        double d2 = Math.pow(((Number)this.range.getValue()).doubleValue(), 2.0);
        Vec3 vec32 = XRay.mc.player.getEyePosition(1.0f);
        for (Map.Entry<BlockPos, Block> entry : this.S.entrySet()) {
            this.a(entry.getKey(), entry.getValue().defaultBlockState(), vec32, d2, vec3, bufferBuilder, poseStack, f4, f5, f2, f3);
        }
        if (((Boolean)this.blindScan.getValue()).booleanValue()) {
            double d3 = Math.pow(((Number)this.blindRenderRange.getValue()).doubleValue(), 2.0);
            for (Map.Entry<o, BlockState> entry : this.W.entrySet()) {
                if (!this.c(entry.getValue().getBlock())) continue;
                this.a(entry.getKey().a(), entry.getValue(), vec32, d3, vec3, bufferBuilder, poseStack, f4, f5, f2, f3);
            }
        }
        tesselator.end();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }

    private double a(int n2) {
        if (n2 == 1) {
            return 2.0;
        }
        if (n2 >= 2 && n2 <= 4) {
            return 2.5;
        }
        return n2 >= 5 && n2 <= 10 ? 3.5 : 3.0;
    }

    private boolean f(Block block) {
        if (block == null) {
            return false;
        }
        return block.builtInRegistryHolder().key().location().getPath().contains("_ore");
    }

    private void a(PoseStack poseStack, AABB aABB, Color color) {
        float f2 = (float)color.getRed() / 255.0f;
        float f3 = (float)color.getGreen() / 255.0f;
        float f4 = (float)color.getBlue() / 255.0f;
        float f5 = 0.8f;
        float f6 = (float)aABB.minX;
        float f7 = (float)aABB.minY;
        float f8 = (float)aABB.minZ;
        float f9 = (float)aABB.maxX;
        float f10 = (float)aABB.maxY;
        float f11 = (float)aABB.maxZ;
        Matrix4f matrix4f = poseStack.last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, f6, f7, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f7, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f7, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f7, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f7, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f7, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f7, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f7, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f7, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f10, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f7, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f10, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f7, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f10, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f7, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f10, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f10, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f10, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f10, f8).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f10, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f9, f10, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f10, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f10, f11).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.vertex(matrix4f, f6, f10, f8).color(f2, f3, f4, f5).endVertex();
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    private int a(BlockPos blockPos, int n2) {
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        LinkedList<BlockPos> linkedList = new LinkedList<BlockPos>();
        if (this.c(blockPos)) {
            linkedList.add(blockPos);
            hashSet.add(blockPos);
        }
        int n3 = 0;
        while (!linkedList.isEmpty() && n3 < n2) {
            BlockPos blockPos2 = (BlockPos)linkedList.poll();
            ++n3;
            for (Direction direction : ad) {
                BlockPos blockPos3 = blockPos2.relative(direction);
                if (hashSet.contains(blockPos3) || !this.c(blockPos3)) continue;
                hashSet.add(blockPos3);
                linkedList.add(blockPos3);
            }
        }
        return n3;
    }

    private void a(PoseStack poseStack, BlockPos blockPos, BlockState blockState, Vec3 vec3) {
        if (blockState == null || blockState.isAir()) {
            return;
        }
        Color color = this.b(blockState.getBlock());
        if (color == null) {
            return;
        }
        poseStack.pushPose();
        poseStack.translate((double)blockPos.getX() - vec3.x, (double)blockPos.getY() - vec3.y, (double)blockPos.getZ() - vec3.z);
        this.a(poseStack, new AABB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0), color);
        poseStack.popPose();
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        if (!LF21Client.isReady()) {
            return;
        }
        if (XRay.mc.level != this.X) {
            this.j();
        }
        if (this.T != null) {
            this.S.clear();
            this.S.putAll(this.T);
            this.T = null;
        }
        if (System.currentTimeMillis() - this.U > ((Number)this.scanDelay.getValue()).longValue() && !this.V) {
            this.V = true;
            long l2 = this.Z;
            Y.execute(() -> {
                try {
                    ConcurrentHashMap<BlockPos, Block> concurrentHashMap = new ConcurrentHashMap<BlockPos, Block>();
                    this.a(XRay.mc.player.blockPosition(), concurrentHashMap);
                    if (l2 == this.Z) {
                        this.T = concurrentHashMap;
                    }
                }
                finally {
                    this.V = false;
                    this.U = System.currentTimeMillis();
                }
            });
        }
    }

    public void a() {
        this.i();
        this.X = null;
        if (Y == null || Y.isShutdown()) {
            Y = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
        }
        if (XRay.mc.levelRenderer != null) {
            XRay.mc.levelRenderer.allChanged();
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
                    case 0 -> 120;
                    case 1 -> 61;
                    case 2 -> 9;
                    case 3 -> 88;
                    case 4 -> 35;
                    case 5 -> 125;
                    default -> 19;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private boolean a(Block block, Block block2) {
        if (block == block2) {
            return true;
        }
        if (block == null || block2 == null) {
            return false;
        }
        Set<Block> set = this.g(block);
        Set<Block> set2 = this.g(block2);
        return !set.isEmpty() && set.equals(set2);
    }

    private boolean d(Block block) {
        if (block == null) {
            return false;
        }
        String string = block.builtInRegistryHolder().key().location().getPath();
        String[] stringArray = cb;
        return string.contains("_planks") || string.contains("_log") || string.contains("_fence") || string.contains("_stairs") || string.contains("rail") || string.equals("torch") || string.equals("wall_torch") || string.equals("cobweb") || string.equals("ladder") || string.equals("chest") || string.equals("trapped_chest");
    }

    private void a(int n2, Map<BlockPos, Block> map) {
        for (int i2 = -n2; i2 <= n2; ++i2) {
            for (int i3 = -n2; i3 <= n2; ++i3) {
                for (int i4 = -n2; i4 <= n2; ++i4) {
                    BlockPos blockPos = new BlockPos((int)(XRay.mc.player.getX() + (double)i2), (int)(XRay.mc.player.getY() + (double)i3), (int)(XRay.mc.player.getZ() + (double)i4));
                    Block block = PlayerUtil.a(blockPos);
                    if (!this.a(block)) continue;
                    map.put(blockPos, block);
                }
            }
        }
    }

    public void h() {
        this.i();
        if (Y != null && !Y.isShutdown()) {
            Y.shutdownNow();
        }
        if (XRay.mc.levelRenderer != null) {
            XRay.mc.levelRenderer.allChanged();
        }
    }

    private Vector3f a(float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f4 - f2;
        float f9 = f5 - f3;
        if ((double)Math.abs(f8) < 1.0E-6 && (double)Math.abs(f9) < 1.0E-6) {
            return new Vector3f(f2, f3, 0.0f);
        }
        float f10 = Float.MAX_VALUE;
        if (f8 > 0.0f) {
            f10 = Math.min(f10, (f6 - f2) / f8);
        }
        if (f8 < 0.0f) {
            f10 = Math.min(f10, -f2 / f8);
        }
        if (f9 > 0.0f) {
            f10 = Math.min(f10, (f7 - f3) / f9);
        }
        if (f9 < 0.0f) {
            f10 = Math.min(f10, -f3 / f9);
        }
        return new Vector3f(f2 + f10 * f8, f3 + f10 * f9, 0.0f);
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        double d2;
        if (XRay.mc.gameRenderer.getMainCamera().isInitialized()) {
            this.aa = new Matrix4f((Matrix4fc)renderEvent.a().last().pose());
            this.ab = new Matrix4f((Matrix4fc)RenderSystem.getProjectionMatrix());
        }
        if (!LF21Client.isReady()) {
            return;
        }
        Vec3 vec3 = XRay.mc.gameRenderer.getMainCamera().getPosition();
        PoseStack poseStack = renderEvent.a();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        if (!this.S.isEmpty()) {
            d2 = Math.pow(((Number)this.range.getValue()).doubleValue(), 2.0);
            for (Map.Entry<BlockPos, Block> entry : this.S.entrySet()) {
                if (!(entry.getKey().distSqr((Vec3i)XRay.mc.player.blockPosition()) <= d2)) continue;
                this.a(poseStack, entry.getKey(), entry.getValue().defaultBlockState(), vec3);
            }
        }
        if (((Boolean)this.blindScan.getValue()).booleanValue() && !this.W.isEmpty()) {
            d2 = Math.pow(((Number)this.blindRenderRange.getValue()).doubleValue(), 2.0);
            this.W.forEach((o2, blockState) -> {
                BlockPos blockPos;
                if (this.c(blockState.getBlock()) && (blockPos = o2.a()).distSqr((Vec3i)XRay.mc.player.blockPosition()) <= d2) {
                    this.a(poseStack, blockPos, (BlockState)blockState, vec3);
                }
            });
        }
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }

    private void i() {
        this.S.clear();
        this.T = null;
        this.V = false;
        this.W.clear();
        this.aa = null;
        this.ab = null;
        this.ac.clear();
        ++this.Z;
    }

    private Set<BlockPos> b(BlockPos blockPos, int n2) {
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        LinkedList<BlockPos> linkedList = new LinkedList<BlockPos>();
        if (this.c(blockPos)) {
            linkedList.add(blockPos);
            hashSet.add(blockPos);
        }
        while (!linkedList.isEmpty() && hashSet.size() < n2) {
            BlockPos blockPos2 = (BlockPos)linkedList.poll();
            for (Direction direction : ad) {
                BlockPos blockPos3 = blockPos2.relative(direction);
                if (!this.c(blockPos3) || !hashSet.add(blockPos3)) continue;
                linkedList.add(blockPos3);
            }
        }
        return hashSet;
    }

    private double b(BlockPos blockPos, Block block2) {
        if (!LF21Client.isReady()) {
            return 0.0;
        }
        int n2 = blockPos.getX() >> 4;
        int n3 = blockPos.getZ() >> 4;
        Set<Block> set = this.g(block2);
        if (set.isEmpty()) {
            return 0.0;
        }
        Block block3 = set.stream().min(Comparator.comparing(block -> block.builtInRegistryHolder().key().location().toString())).orElse(block2);
        String string = n2 + ":" + n3 + ":" + String.valueOf(block3.builtInRegistryHolder().key().location());
        c c2 = this.ac.get(string);
        if (c2 == c.SUSPICIOUS) {
            return -5.0;
        }
        if (c2 == c.AUTHENTIC) {
            return 1.0;
        }
        BlockPos blockPos2 = XRay.mc.player.blockPosition();
        int n4 = blockPos2.getX() >> 4;
        int n5 = blockPos2.getZ() >> 4;
        int n6 = Math.abs(n4 - n2) + Math.abs(n5 - n3);
        if (n6 <= 3) {
            this.ac.put(string, c.AUTHENTIC);
            return 1.0;
        }
        this.ac.put(string, c.SCANNING);
        int n7 = set.contains(Blocks.DIAMOND_ORE) || set.contains(Blocks.EMERALD_ORE) ? 30 : (!set.contains(Blocks.GOLD_ORE) && !set.contains(Blocks.LAPIS_ORE) ? 200 : 50);
        int n8 = 0;
        int n9 = n2 << 4;
        int n10 = n3 << 4;
        for (int i2 = 0; i2 < 16; ++i2) {
            for (int i3 = 0; i3 < 16; ++i3) {
                for (int i4 = XRay.mc.level.getMinBuildHeight(); i4 < XRay.mc.level.getMaxBuildHeight(); ++i4) {
                    if (!this.a(XRay.mc.level.getBlockState(new BlockPos(n9 + i2, i4, n10 + i3)).getBlock(), block2)) continue;
                    ++n8;
                }
            }
        }
        if (n8 > n7) {
            this.ac.put(string, c.SUSPICIOUS);
            return -5.0;
        }
        this.ac.put(string, c.AUTHENTIC);
        return 0.0;
    }

    private double a(BlockPos blockPos, Block block) {
        Set<Block> set = this.e(block);
        if (set.isEmpty()) {
            return 1.0;
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (Direction direction : ad) {
            BlockPos blockPos2 = blockPos.relative(direction);
            Block block2 = PlayerUtil.a(blockPos2);
            if (set.contains(block2)) {
                ++n2;
                continue;
            }
            if (this.c(blockPos2)) {
                ++n4;
                continue;
            }
            if (this.a(block2, block) || this.f(block2) || this.d(block2)) continue;
            ++n3;
        }
        if (n2 == 0 && n3 >= 4 && n4 <= 1) {
            return -5.0;
        }
        if (n4 < 2) {
            double d2 = 0.0;
            if (n2 >= 4) {
                d2 += 3.0;
            } else if (n2 >= 2) {
                d2 += 1.0;
            } else if (n2 == 1) {
                d2 += 0.5;
            }
            return d2;
        }
        if (n2 >= 1) {
            return 2.0;
        }
        return n3 <= 2 ? 0.5 : -1.0;
    }

    private List<BlockPos> a(BlockPos blockPos, Block block, Set<BlockPos> set) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        LinkedList<BlockPos> linkedList = new LinkedList<BlockPos>();
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        if (set.contains(blockPos)) {
            return arrayList;
        }
        linkedList.add(blockPos);
        hashSet.add(blockPos);
        int n2 = 64;
        while (!linkedList.isEmpty() && arrayList.size() < n2) {
            BlockPos blockPos2 = (BlockPos)linkedList.poll();
            arrayList.add(blockPos2);
            for (Direction direction : ad) {
                BlockPos blockPos3 = blockPos2.relative(direction);
                if (set.contains(blockPos3) || hashSet.contains(blockPos3) || !XRay.mc.level.isLoaded(blockPos3) || !this.a(PlayerUtil.a(blockPos3), block)) continue;
                hashSet.add(blockPos3);
                linkedList.add(blockPos3);
            }
        }
        return arrayList;
    }

    private void a(BlockPos blockPos, Block block, Map<BlockPos, Block> map, Set<BlockPos> set) {
        block5: {
            List<BlockPos> list;
            block4: {
                if (set.contains(blockPos)) {
                    return;
                }
                list = this.a(blockPos, block, set);
                if (list.isEmpty()) {
                    return;
                }
                set.addAll(list);
                if (!((Boolean)this.antiFakeOre.getValue()).booleanValue()) break block4;
                p p2 = this.a(list, block);
                if (!p2.a(((Number)this.minAuthScore.getValue()).doubleValue())) break block5;
                for (BlockPos blockPos2 : list) {
                    map.put(blockPos2, block);
                }
                break block5;
            }
            for (BlockPos blockPos3 : list) {
                map.put(blockPos3, block);
            }
        }
    }

    private double c(BlockPos blockPos, Block block) {
        int n2 = blockPos.getY();
        Set<Block> set = this.g(block);
        if (set.contains(Blocks.DIAMOND_ORE)) {
            if (n2 >= -64 && n2 <= 16) {
                return 2.0;
            }
            return n2 > 16 && n2 <= 32 ? 0.5 : -10.0;
        }
        if (set.contains(Blocks.EMERALD_ORE)) {
            return n2 >= -16 && n2 <= 320 ? 2.0 : -10.0;
        }
        if (set.contains(Blocks.GOLD_ORE)) {
            if (n2 >= -64 && n2 <= 32) {
                return 2.0;
            }
            return n2 > 32 && n2 < 80 ? -1.5 : -5.0;
        }
        if (!set.contains(Blocks.IRON_ORE)) {
            if (set.contains(Blocks.COAL_ORE)) {
                return n2 >= 0 && n2 <= 256 ? 1.0 : 0.0;
            }
            if (set.contains(Blocks.COPPER_ORE)) {
                return n2 >= -16 && n2 <= 112 ? 1.5 : 0.5;
            }
            if (set.contains(Blocks.LAPIS_ORE)) {
                return n2 >= -64 && n2 <= 64 ? 1.5 : 0.0;
            }
            if (set.contains(Blocks.REDSTONE_ORE)) {
                return n2 >= -64 && n2 <= 16 ? 1.5 : 0.0;
            }
            if (set.contains(Blocks.ANCIENT_DEBRIS)) {
                if (n2 >= 8 && n2 <= 22) {
                    return 2.0;
                }
                return n2 < 120 ? 1.0 : -15.0;
            }
            return 0.5;
        }
        return !(n2 >= -24 && n2 <= 72 || n2 > 80 && n2 <= 320) ? 1.0 : 1.5;
    }

    public boolean isXrayVisible(Block block) {
        return block != null && (this.c(block) || this.a(block));
    }

    private void j() {
        this.i();
        this.X = XRay.mc.level;
        if (XRay.mc.levelRenderer != null) {
            XRay.mc.levelRenderer.allChanged();
        }
        ChatUtil.print(String.valueOf(ChatFormatting.GREEN) + "Reset");
    }

    private Set<Block> e(Block block) {
        String string = block.builtInRegistryHolder().key().location().getPath();
        String[] stringArray = cb;
        if (string.startsWith("nether_") || string.equals("ancient_debris")) {
            return af;
        }
        return string.contains("_ore") ? ae : Collections.emptySet();
    }

    private BlockPos b(BlockPos blockPos) {
        int n2 = ((Number)this.alternativeSearchRadius.getValue()).intValue();
        int n3 = ((Number)this.minAirBlocksRequired.getValue()).intValue();
        BlockPos blockPos2 = null;
        int n4 = 0;
        for (int i2 = -n2; i2 <= n2; ++i2) {
            block1: for (int i3 = -n2; i3 <= n2; ++i3) {
                for (int i4 = -n2; i4 <= n2; ++i4) {
                    int n5;
                    BlockPos blockPos3 = blockPos.offset(i2, i3, i4);
                    if (blockPos3.equals((Object)blockPos) || !this.c(blockPos3) || (n5 = this.a(blockPos3, n3)) <= n4) continue;
                    n4 = n5;
                    blockPos2 = blockPos3;
                    if (n5 >= n3) continue block1;
                }
            }
        }
        return n4 >= n3 ? blockPos2 : null;
    }

    private boolean a(Block block) {
        return (Boolean)this.I.getValue() != false && block == Blocks.SPAWNER || (Boolean)this.G.getValue() != false && block == Blocks.NETHER_QUARTZ_ORE || (Boolean)this.H.getValue() != false && block == Blocks.ANCIENT_DEBRIS || (Boolean)this.J.getValue() != false && (block == Blocks.END_PORTAL_FRAME || block == Blocks.END_PORTAL || block == Blocks.END_GATEWAY);
    }

    private Set<BlockPos> a(BlockPos blockPos) {
        BlockPos blockPos2;
        BlockPos blockPos3;
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        LinkedList<BlockPos> linkedList = new LinkedList<BlockPos>();
        int n2 = ((Number)this.maxBlocks.getValue()).intValue();
        if (this.c(blockPos) && hashSet.add(blockPos)) {
            linkedList.add(blockPos);
        }
        if (this.c(blockPos3 = blockPos.above()) && hashSet.add(blockPos3)) {
            linkedList.add(blockPos3);
        }
        if (linkedList.isEmpty()) {
            blockPos2 = ad;
            int n3 = ((Direction[])blockPos2).length;
            for (int i2 = 0; i2 < n3; ++i2) {
                BlockPos blockPos4 = blockPos2[i2];
                BlockPos blockPos5 = blockPos.relative((Direction)blockPos4);
                if (!this.c(blockPos5) || !hashSet.add(blockPos5)) continue;
                linkedList.add(blockPos5);
                break;
            }
        }
        while (!linkedList.isEmpty() && hashSet.size() < n2) {
            blockPos2 = (BlockPos)linkedList.poll();
            for (Direction direction : ad) {
                BlockPos blockPos4 = blockPos2.relative(direction);
                if (!this.c(blockPos4) || !hashSet.add(blockPos4)) continue;
                linkedList.add(blockPos4);
            }
        }
        if (((Boolean)this.enableAlternativeStart.getValue()).booleanValue() && hashSet.size() < ((Number)this.minAirBlocksRequired.getValue()).intValue() && (blockPos2 = this.b(blockPos)) != null) {
            return this.b(blockPos2, n2);
        }
        return hashSet;
    }

    private Vector3f a(Vec3 vec3, Vec3 vec32) {
        if (this.aa == null || this.ab == null) {
            return null;
        }
        Vector4f vector4f = new Vector4f((float)(vec3.x - vec32.x), (float)(vec3.y - vec32.y), (float)(vec3.z - vec32.z), 1.0f);
        vector4f.mul((Matrix4fc)this.aa);
        vector4f.mul((Matrix4fc)this.ab);
        if (vector4f.w() <= 0.0f) {
            return null;
        }
        vector4f.mul(1.0f / vector4f.w());
        float f2 = mc.getWindow().getGuiScaledWidth();
        float f3 = mc.getWindow().getGuiScaledHeight();
        float f4 = (vector4f.x() * 0.5f + 0.5f) * f2;
        float f5 = (-vector4f.y() * 0.5f + 0.5f) * f3;
        boolean bl = f4 >= 0.0f && f4 <= f2 && f5 >= 0.0f && f5 <= f3;
        return new Vector3f(f4, f5, bl ? 1.0f : -1.0f);
    }

    private double c(BlockPos blockPos, int n2) {
        HashMap hashMap = new HashMap();
        for (int i2 = -n2; i2 <= n2; ++i2) {
            for (int i3 = -n2; i3 <= n2; ++i3) {
                for (int i4 = -n2; i4 <= n2; ++i4) {
                    Block block;
                    BlockPos blockPos2 = blockPos.offset(i2, i3, i4);
                    if (!XRay.mc.level.isLoaded(blockPos2) || !this.f(block = PlayerUtil.a(blockPos2))) continue;
                    this.g(block).stream().findFirst().ifPresent(block2 -> hashMap.computeIfAbsent(block2, block -> new ArrayList()).add(blockPos2));
                }
            }
        }
        for (List list : hashMap.values()) {
            if (list.size() < 2 || this.b(list) || !this.c(list)) continue;
            return -10.0;
        }
        return 0.0;
    }

    private boolean c(List<BlockPos> list) {
        int n2;
        if (list.size() < 4) {
            return false;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> hashMap2 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> hashMap3 = new HashMap<Integer, Integer>();
        for (n2 = 0; n2 < list.size(); ++n2) {
            for (int i2 = n2 + 1; i2 < list.size(); ++i2) {
                BlockPos blockPos = list.get(n2);
                BlockPos blockPos2 = list.get(i2);
                int n4 = Math.abs(blockPos.getX() - blockPos2.getX());
                int n5 = Math.abs(blockPos.getY() - blockPos2.getY());
                int n6 = Math.abs(blockPos.getZ() - blockPos2.getZ());
                if (n4 > 1) {
                    hashMap.put(n4, hashMap.getOrDefault(n4, 0) + 1);
                }
                if (n5 > 1) {
                    hashMap2.put(n5, hashMap2.getOrDefault(n5, 0) + 1);
                }
                if (n6 <= 1) continue;
                hashMap3.put(n6, hashMap3.getOrDefault(n6, 0) + 1);
            }
        }
        n2 = list.size() > 5 ? 3 : 2;
        return hashMap.values().stream().anyMatch(n3 -> n3 > n2) || hashMap2.values().stream().anyMatch(n3 -> n3 > n2) || hashMap3.values().stream().anyMatch(n3 -> n3 > n2);
    }

    private Set<Block> g(Block block) {
        if (block == null) {
            return Collections.emptySet();
        }
        if (Set.of(Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE).contains(block)) {
            return Set.of(Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE);
        }
        if (Set.of(Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE).contains(block)) {
            return Set.of(Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE);
        }
        if (Set.of(Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.NETHER_GOLD_ORE).contains(block)) {
            return Set.of(Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.NETHER_GOLD_ORE);
        }
        if (Set.of(Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE).contains(block)) {
            return Set.of(Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE);
        }
        if (Set.of(Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE).contains(block)) {
            return Set.of(Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE);
        }
        if (Set.of(Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE).contains(block)) {
            return Set.of(Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE);
        }
        if (Set.of(Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE).contains(block)) {
            return Set.of(Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE);
        }
        if (Set.of(Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE).contains(block)) {
            return Set.of(Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE);
        }
        if (block == Blocks.ANCIENT_DEBRIS) {
            return Set.of(Blocks.ANCIENT_DEBRIS);
        }
        return block == Blocks.NETHER_QUARTZ_ORE ? Set.of(Blocks.NETHER_QUARTZ_ORE) : Collections.emptySet();
    }

    public XRay() {
        String[] stringArray = cb;
        super("XRay", Category.RENDER);
        this.blockType = new ModeSetting("Block Type", "Ores", "Valuables").withDefault("Ores");
        this.range = new NumberSetting("Range", 48, 16, 100, 4);
        this.scanDelay = new NumberSetting("Scan Delay", 500, 50, 3000, 100);
        this.tracers = new BooleanSetting("Tracers", true);
        this.maxBlocks = new NumberSetting("MaxBlocks", 80000, 10000, 500000, 10000);
        this.antiFakeOre = new BooleanSetting("Anti-Fake Ore", true);
        this.minAuthScore = new NumberSetting("Min Auth Score", 1.0, 1.0, 15.0, 0.5, this.antiFakeOre::getValue);
        this.patternAnalysis = new BooleanSetting("Pattern Analysis", false, this.antiFakeOre::getValue);
        this.strictHostRockCheck = new BooleanSetting("Strict Host Rock Check", true, this.antiFakeOre::getValue);
        this.enableAlternativeStart = new BooleanSetting("Enable Alternative Start", true);
        this.minAirBlocksRequired = new NumberSetting("Min Air Blocks Required", 50, 10, 1000, 50);
        this.alternativeSearchRadius = new NumberSetting("Alternative Search Radius", 5, 1, 10, 1);
        this.blindScan = new BooleanSetting("Blind Scan", true);
        this.blindRenderRange = new NumberSetting("Blind Render Range", 32, 8, 1024, 4, this.blindScan::getValue);
        this.minimumVeinSize = new NumberSetting("Minimum Vein Size", 1, 1, 10, 1, this.antiFakeOre::getValue);
        this.diamond = new BooleanSetting("Diamond", true);
        this.emerald = new BooleanSetting("Emerald", true);
        this.A = new BooleanSetting("Redstone", true);
        this.B = new BooleanSetting("Iron", true);
        this.C = new BooleanSetting("Gold", true);
        this.D = new BooleanSetting("Copper", true);
        this.E = new BooleanSetting("Lapis", true);
        this.F = new BooleanSetting("Coal", true);
        this.G = new BooleanSetting("Quartz", true);
        this.H = new BooleanSetting("Ancient Debris", true);
        this.I = new BooleanSetting("Spawner", true);
        this.J = new BooleanSetting("EndPortalFrame", true);
        this.K = new Color(0, 255, 255);
        this.L = new Color(0, 255, 0);
        this.M = new Color(255, 0, 0);
        this.N = new Color(210, 210, 210);
        this.O = new Color(255, 215, 0);
        this.P = new Color(205, 127, 50);
        this.Q = new Color(0, 0, 255);
        this.R = new Color(50, 50, 50);
        this.S = new ConcurrentHashMap<BlockPos, Block>();
        this.T = null;
        this.U = 0L;
        this.V = false;
        this.W = new ConcurrentHashMap<o, BlockState>();
        this.Z = 0L;
        this.ac = new ConcurrentHashMap<String, c>();
        INSTANCE = this;
    }

    private boolean b(List<BlockPos> list) {
        if (list.size() <= 1) {
            return true;
        }
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>(list);
        HashSet<BlockPos> hashSet2 = new HashSet<BlockPos>();
        LinkedList<BlockPos> linkedList = new LinkedList<BlockPos>();
        BlockPos blockPos = list.get(0);
        linkedList.add(blockPos);
        hashSet2.add(blockPos);
        while (!linkedList.isEmpty()) {
            BlockPos blockPos2 = (BlockPos)linkedList.poll();
            for (Direction direction : Direction.values()) {
                BlockPos blockPos3 = blockPos2.relative(direction);
                if (!hashSet.contains(blockPos3) || hashSet2.contains(blockPos3)) continue;
                hashSet2.add(blockPos3);
                linkedList.add(blockPos3);
            }
        }
        return hashSet2.size() == list.size();
    }

    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        if (LF21Client.isReady() && (!this.S.isEmpty() || !this.W.isEmpty()) && ((Boolean)this.tracers.getValue()).booleanValue() && this.aa != null && this.ab != null) {
            this.a(render2DEvent.a());
        }
    }

    private boolean c(BlockPos blockPos) {
        if (XRay.mc.level == null) {
            return false;
        }
        Block block = PlayerUtil.a(blockPos);
        return block.defaultBlockState().isAir() || block == Blocks.WATER || block == Blocks.LAVA || block == Blocks.CAVE_AIR || block == Blocks.VOID_AIR;
    }

    private void a(BlockPos blockPos, Map<BlockPos, Block> map) {
        if (XRay.mc.level == null) {
            return;
        }
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        for (BlockPos blockPos2 : this.a(blockPos)) {
            for (Direction direction : ad) {
                Block block;
                BlockPos blockPos3 = blockPos2.relative(direction);
                if (this.c(blockPos3) || !this.c(block = PlayerUtil.a(blockPos3))) continue;
                this.a(blockPos3, block, map, hashSet);
            }
        }
        int n2 = ((Number)this.range.getValue()).intValue();
        int n3 = n2 + 40;
        this.a(n3, map);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[48];
                var3_1 = 0;
                var2_2 = "9S}1\u000e;r\u0013X)\u0017Q\u0018\u0007<Th5L\u0013w\b*Xm+W\u0012}\u001d\r\fOh(S\u0018w'^a=P\t\t5\\q\u001aO\u0012p\u0013N\u000e9Sj1F\u0013gXyl:Q\u0014`\n\u000f\\e4|\t|\n^a\u0007=Pl*B\u0011w\u0005\u001bUl+W\u0007,Oh;F\u000f`\u0004?Re<\u0016+I{1@\t30Rz,\u0003/|\u001bV)\u001bK\u0018p\u0013\u0006'[l6@\u0018\u0005\fR{;K\u0006\u0014\\m<F\u000f\t.\\e-B\u001f\u007f\u001dN\u00047Ol+\u0007'Me9M\u0016`\u0006\u001bRk/F\u001f\u0004'R{=\u0007'N}9J\u000f`\t.\\e-B\u001f\u007f\u001dN\u0010(\\},F\u000f}X|g9O\u0004`\u0011N\u00115Tg1N\b~Xkl1M]@\u0011Gl\u00175Tgxb\u0014aX\u007fe7@\u0016`Xol)V\u0014a\u001dY\u0006)Hh*W\u0007\u0004'R{=\u0004 oh!\u00047Ol+\u00199Q}=Q\u0013r\fT\u007f=\u0003.v\u0019Oj0\u0003/r\u001cT|+\u00041Of6\u0004'Qf?\u0018=Sh:O\u001839Q}=Q\u0013r\fT\u007f=\u0003.g\u0019O}\u0004;Rh4\u0012:Q`6G]A\u001dSm=Q]A\u0019Sn=\u000e=Sm\bL\u000fg\u0019QO*B\u0010v\u000e5Tgxb\bg\u0010\u001dZ;L\u000fv\u0006;Ry(F\u000f\u0005*\\g?F\u0005*Xz=W\n:Q`6G]@\u001b\\g\n+^h6\u00039v\u0014\\p\u0007+Mh/M\u0018a\u0007\u0016X}0F\u000fL\u000e\u0019Sj1F\u0013g'Yl:Q\u0014`\u00054\\y1P";
                var4_3 = "9S}1\u000e;r\u0013X)\u0017Q\u0018\u0007<Th5L\u0013w\b*Xm+W\u0012}\u001d\r\fOh(S\u0018w'^a=P\t\t5\\q\u001aO\u0012p\u0013N\u000e9Sj1F\u0013gXyl:Q\u0014`\n\u000f\\e4|\t|\n^a\u0007=Pl*B\u0011w\u0005\u001bUl+W\u0007,Oh;F\u000f`\u0004?Re<\u0016+I{1@\t30Rz,\u0003/|\u001bV)\u001bK\u0018p\u0013\u0006'[l6@\u0018\u0005\fR{;K\u0006\u0014\\m<F\u000f\t.\\e-B\u001f\u007f\u001dN\u00047Ol+\u0007'Me9M\u0016`\u0006\u001bRk/F\u001f\u0004'R{=\u0007'N}9J\u000f`\t.\\e-B\u001f\u007f\u001dN\u0010(\\},F\u000f}X|g9O\u0004`\u0011N\u00115Tg1N\b~Xkl1M]@\u0011Gl\u00175Tgxb\u0014aX\u007fe7@\u0016`Xol)V\u0014a\u001dY\u0006)Hh*W\u0007\u0004'R{=\u0004 oh!\u00047Ol+\u00199Q}=Q\u0013r\fT\u007f=\u0003.v\u0019Oj0\u0003/r\u001cT|+\u00041Of6\u0004'Qf?\u0018=Sh:O\u001839Q}=Q\u0013r\fT\u007f=\u0003.g\u0019O}\u0004;Rh4\u0012:Q`6G]A\u001dSm=Q]A\u0019Sn=\u000e=Sm\bL\u000fg\u0019QO*B\u0010v\u000e5Tgxb\bg\u0010\u001dZ;L\u000fv\u0006;Ry(F\u000f\u0005*\\g?F\u0005*Xz=W\n:Q`6G]@\u001b\\g\n+^h6\u00039v\u0014\\p\u0007+Mh/M\u0018a\u0007\u0016X}0F\u000fL\u000e\u0019Sj1F\u0013g'Yl:Q\u0014`\u00054\\y1P".length();
                var1_4 = 13;
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
                    var5[var3_1++] = XRay.c(XRay.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\n\\`4\n:Qf;H]G\u0001Ml";
                    var4_3 = "\n\\`4\n:Qf;H]G\u0001Ml".length();
                    var1_4 = 4;
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
                    var5[var3_1++] = XRay.c(XRay.a(v1));
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
        XRay.cb = var5;
        XRay.ad = Direction.values();
        XRay.ae = Set.of(new Block[]{Blocks.STONE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRANITE, Blocks.DEEPSLATE, Blocks.TUFF, Blocks.CALCITE, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.MYCELIUM, Blocks.GRAVEL, Blocks.DRIPSTONE_BLOCK, Blocks.SAND, Blocks.SANDSTONE, Blocks.RED_SAND, Blocks.RED_SANDSTONE});
        XRay.af = Set.of(Blocks.NETHERRACK, Blocks.BASALT, Blocks.BLACKSTONE, Blocks.SOUL_SAND, Blocks.SOUL_SOIL);
    }

    private void a(BlockPos blockPos, BlockState blockState, Vec3 vec3, double d2, Vec3 vec32, BufferBuilder bufferBuilder, PoseStack poseStack, float f2, float f3, float f4, float f5) {
        Vec3 vec33 = Vec3.atCenterOf((Vec3i)blockPos);
        if (vec33.subtract(vec3).lengthSqr() > d2) {
            return;
        }
        Vector3f vector3f = this.a(vec33, vec32);
        if (vector3f == null) {
            return;
        }
        float f6 = vector3f.x;
        float f7 = vector3f.y;
        boolean bl = vector3f.z > 0.0f;
        Color color = this.b(blockState.getBlock());
        if (color == null) {
            return;
        }
        float f8 = (float)color.getRed() / 255.0f;
        float f9 = (float)color.getGreen() / 255.0f;
        float f10 = (float)color.getBlue() / 255.0f;
        float f11 = 0.8f;
        if (!bl) {
            Vector3f vector3f2 = this.a(f2, f3, f6, f7, f4, f5);
            f6 = vector3f2.x;
            f7 = vector3f2.y;
        }
        bufferBuilder.vertex(poseStack.last().pose(), f2, f3, 0.0f).color(f8, f9, f10, f11).endVertex();
        bufferBuilder.vertex(poseStack.last().pose(), f6, f7, 0.0f).color(f8, f9, f10, f11).endVertex();
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        ClientboundBlockUpdatePacket clientboundBlockUpdatePacket;
        if (!LF21Client.isReady()) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (packet instanceof ClientboundBlockUpdatePacket) {
            clientboundBlockUpdatePacket = (ClientboundBlockUpdatePacket)packet;
            this.S.remove(clientboundBlockUpdatePacket.getPos());
            if (((Boolean)this.blindScan.getValue()).booleanValue()) {
                this.W.put(new o(clientboundBlockUpdatePacket.getPos()), clientboundBlockUpdatePacket.getBlockState());
            }
        }
        if (packet instanceof ClientboundSectionBlocksUpdatePacket) {
            clientboundBlockUpdatePacket = (ClientboundSectionBlocksUpdatePacket)packet;
            clientboundBlockUpdatePacket.runUpdates((blockPos, blockState) -> {
                this.S.remove(blockPos);
                if (((Boolean)this.blindScan.getValue()).booleanValue()) {
                    this.W.put(new o((BlockPos)blockPos), (BlockState)blockState);
                }
            });
        }
    }

    private boolean c(Block block) {
        if (block == null) {
            return false;
        }
        String string = (String)this.blockType.getValue();
        String[] stringArray = cb;
        if (string.equalsIgnoreCase("Ores")) {
            return (Boolean)this.diamond.getValue() != false && (block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE) || (Boolean)this.emerald.getValue() != false && (block == Blocks.EMERALD_ORE || block == Blocks.DEEPSLATE_EMERALD_ORE) || (Boolean)this.A.getValue() != false && (block == Blocks.REDSTONE_ORE || block == Blocks.DEEPSLATE_REDSTONE_ORE) || (Boolean)this.B.getValue() != false && (block == Blocks.IRON_ORE || block == Blocks.DEEPSLATE_IRON_ORE) || (Boolean)this.C.getValue() != false && (block == Blocks.GOLD_ORE || block == Blocks.DEEPSLATE_GOLD_ORE || block == Blocks.NETHER_GOLD_ORE) || (Boolean)this.D.getValue() != false && (block == Blocks.COPPER_ORE || block == Blocks.DEEPSLATE_COPPER_ORE) || (Boolean)this.E.getValue() != false && (block == Blocks.LAPIS_ORE || block == Blocks.DEEPSLATE_LAPIS_ORE) || (Boolean)this.F.getValue() != false && (block == Blocks.COAL_ORE || block == Blocks.DEEPSLATE_COAL_ORE);
        }
        if (string.equalsIgnoreCase("Valuables")) {
            return block == Blocks.ANCIENT_DEBRIS || block == Blocks.NETHER_GOLD_ORE || block == Blocks.NETHER_QUARTZ_ORE || block == Blocks.OBSIDIAN || block == Blocks.AMETHYST_BLOCK || block == Blocks.BUDDING_AMETHYST;
        }
        return false;
    }

    private p a(List<BlockPos> list, Block block) {
        if (!LF21Client.isReady() || list.isEmpty()) {
            return new p();
        }
        p p2 = new p();
        BlockPos blockPos = list.get(0);
        int n2 = list.size();
        p2.c = this.a(n2);
        if (n2 < ((Number)this.minimumVeinSize.getValue()).intValue()) {
            p2.c = -20.0;
            return p2;
        }
        p2.b = this.b(blockPos, block);
        p2.f = this.c(blockPos, block);
        if (p2.f <= -10.0) {
            return p2;
        }
        if (((Boolean)this.strictHostRockCheck.getValue()).booleanValue()) {
            double d2 = 0.0;
            int n3 = 0;
            for (BlockPos blockPos2 : list) {
                d2 += this.a(blockPos2, block);
                for (Direction direction : ad) {
                    if (!this.c(blockPos2.relative(direction))) continue;
                    ++n3;
                }
            }
            p2.a = d2 / (double)n2;
            double d3 = (double)n3 / (double)n2;
            p2.e = d3 >= 3.0 ? 3.0 : (d3 >= 1.0 ? 1.5 : (d3 > 0.0 ? 0.5 : 0.0));
        }
        if (((Boolean)this.patternAnalysis.getValue()).booleanValue()) {
            p2.d = this.c(blockPos, 8);
        }
        return p2;
    }

    private Color b(Block block) {
        if (block == null) {
            return null;
        }
        if (this.c(block) || this.a(block)) {
            if (block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE) {
                return this.K;
            }
            if (block == Blocks.EMERALD_ORE || block == Blocks.DEEPSLATE_EMERALD_ORE) {
                return this.L;
            }
            if (block == Blocks.REDSTONE_ORE || block == Blocks.DEEPSLATE_REDSTONE_ORE) {
                return this.M;
            }
            if (block == Blocks.IRON_ORE || block == Blocks.DEEPSLATE_IRON_ORE) {
                return this.N;
            }
            if (block == Blocks.GOLD_ORE || block == Blocks.DEEPSLATE_GOLD_ORE || block == Blocks.NETHER_GOLD_ORE) {
                return this.O;
            }
            if (block == Blocks.COPPER_ORE || block == Blocks.DEEPSLATE_COPPER_ORE) {
                return this.P;
            }
            if (block == Blocks.LAPIS_ORE || block == Blocks.DEEPSLATE_LAPIS_ORE) {
                return this.Q;
            }
            if (block == Blocks.COAL_ORE || block == Blocks.DEEPSLATE_COAL_ORE) {
                return this.R;
            }
            if (block == Blocks.NETHER_QUARTZ_ORE) {
                return new Color(255, 255, 255);
            }
            if (block == Blocks.ANCIENT_DEBRIS) {
                return new Color(255, 33, 0);
            }
            if (block == Blocks.SPAWNER) {
                return new Color(0, 85, 255);
            }
            if (block == Blocks.END_PORTAL_FRAME) {
                return new Color(253, 0, 250);
            }
        }
        return null;
    }
}

