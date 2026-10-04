/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lf21.event.EventTarget;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.WorldOverlay2DEvent;
import lf21.event.impl.e;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.render.q;
import lf21.modules.impl.render.r;
import lf21.modules.impl.render.s;
import lf21.modules.impl.render.t;
import lf21.modules.impl.render.u;
import lf21.render.D;
import lf21.render.L;
import lf21.render.M;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.b.i;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.b;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class BedESP
extends Module {
    private boolean A;
    private final NumberSetting range;
    private final NumberSetting maxLayers;
    private static final int j;
    private int F;
    private int D;
    private int z;
    private final ModeSetting mode;
    private final BlockPos.MutableBlockPos H;
    private final NumberSetting vertical;
    private final BooleanSetting surroundings;
    private int G;
    private int E;
    private static final String[] I;
    private final L u;
    private final List<r> y;
    private final List<r> x;
    private BlockPos B;
    private final NumberSetting maxBeds;
    private final M v;
    private final NumberSetting glowIntensity;
    private final BooleanSetting showDistance;
    private static final int k;
    private int C;
    private final NumberSetting glowCompression;
    private final NumberSetting glowRadius;

    @Override
    protected void onEnable() {
        this.z = 20;
        this.A = false;
        this.y.clear();
    }

    @EventTarget
    public void a(e e2) {
        this.x.clear();
        this.y.clear();
        this.A = false;
        this.z = 20;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (this.x.isEmpty()) {
            return;
        }
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = BedESP.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        if (this.mode.is("Glow")) {
            ArrayList<AABB> arrayList = new ArrayList<AABB>();
            for (r r2 : this.x) {
                arrayList.add(this.a(r2.a, r2.b));
            }
            this.u.a(poseStack, arrayList, new Color(51, 230, 255, 220));
            if (this.u.a()) {
                this.v.a(this.u.b(), this.u.c(), this.u.d(), ((Number)this.glowRadius.getValue()).floatValue(), ((Number)this.glowCompression.getValue()).floatValue(), ((Number)this.glowIntensity.getValue()).floatValue());
            }
            poseStack.popPose();
            return;
        }
        this.u.e();
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        for (r r3 : this.x) {
            AABB aABB = this.a(r3.a, r3.b).inflate(0.002);
            RenderSystem.setShaderColor((float)0.2f, (float)0.9f, (float)1.0f, (float)0.08f);
            RenderUtil.a(aABB, poseStack);
            RenderSystem.setShaderColor((float)0.2f, (float)0.9f, (float)1.0f, (float)0.82f);
            RenderUtil.b(aABB, poseStack);
        }
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        poseStack.popPose();
    }

    private boolean b(r r2) {
        double d2;
        if (BedESP.mc.player == null || r2 == null) {
            return false;
        }
        BlockPos blockPos = BedESP.mc.player.blockPosition();
        double d3 = r2.a.getX() - blockPos.getX();
        return d3 * d3 + (d2 = (double)(r2.a.getZ() - blockPos.getZ())) * d2 <= ((Number)this.range.getValue()).doubleValue() * ((Number)this.range.getValue()).doubleValue() && (double)Math.abs(r2.a.getY() - blockPos.getY()) <= ((Number)this.vertical.getValue()).doubleValue();
    }

    private void a() {
        if (BedESP.mc.player == null) {
            return;
        }
        this.y.removeIf(r2 -> !this.a((r)r2));
        if (this.A && this.y.isEmpty()) {
            return;
        }
        this.j();
    }

    private void h() {
        this.x.removeIf(r2 -> !this.a((r)r2) || !this.b((r)r2));
        this.y.removeIf(r2 -> !this.a((r)r2) || !this.b((r)r2));
    }

    public BedESP() {
        String[] stringArray = I;
        super("BedESP", Category.RENDER);
        this.range = new NumberSetting("Range", 48.0, 8.0, 96.0, 4.0);
        this.vertical = new NumberSetting("Vertical", 16.0, 4.0, 64.0, 4.0);
        this.maxLayers = new NumberSetting("Max Layers", 2.0, 1.0, 5.0, 1.0);
        this.maxBeds = new NumberSetting("Max Beds", 8.0, 1.0, 32.0, 1.0);
        this.surroundings = new BooleanSetting("Surroundings", true);
        this.mode = new ModeSetting("Mode", "Box", "Glow").withDefault("Glow");
        this.glowRadius = new NumberSetting("Glow Radius", 14.0, 1.0, 30.0, 1.0, () -> this.mode.is("Glow"));
        this.glowCompression = new NumberSetting("Glow Compression", 2.0, 1.0, 10.0, 0.1, () -> this.mode.is("Glow"));
        this.glowIntensity = new NumberSetting("Glow Intensity", 2.0, 0.1, 2.0, 0.05, () -> this.mode.is("Glow"));
        this.u = new L();
        this.v = new M();
        this.showDistance = new BooleanSetting("Show Distance", true, () -> (Boolean)this.surroundings.getValue());
        this.x = new ArrayList<r>();
        this.y = new ArrayList<r>();
        this.z = 20;
        this.B = BlockPos.ZERO;
        this.H = new BlockPos.MutableBlockPos();
    }

    private void i() {
        this.A = false;
        this.j();
        this.y.clear();
    }

    private AABB a(BlockPos blockPos, BlockPos blockPos2) {
        int n2 = Math.min(blockPos.getX(), blockPos2.getX());
        int n3 = Math.min(blockPos.getY(), blockPos2.getY());
        int n4 = Math.min(blockPos.getZ(), blockPos2.getZ());
        int n5 = Math.max(blockPos.getX(), blockPos2.getX()) + 1;
        int n6 = Math.max(blockPos.getY(), blockPos2.getY()) + 1;
        int n7 = Math.max(blockPos.getZ(), blockPos2.getZ()) + 1;
        return new AABB((double)n2, (double)n3, (double)n4, (double)n5, (double)n6, (double)n7);
    }

    private static int a(int n2) {
        if (n2 == 0) {
            return 0;
        }
        int n3 = (n2 + 1) / 2;
        return (n2 & 1) == 1 ? n3 : -n3;
    }

    private u a(BlockPos blockPos, BlockPos blockPos2, Direction direction) {
        LinkedHashMap<s, Integer> linkedHashMap = new LinkedHashMap<s, Integer>();
        ArrayList<t> arrayList = new ArrayList<t>();
        int n2 = ((Number)this.maxLayers.getValue()).intValue();
        Direction direction2 = direction.getAxis() == Direction.Axis.X ? Direction.SOUTH : Direction.WEST;
        Direction direction3 = direction2.getOpposite();
        this.a(blockPos, direction, direction2, direction3, n2, blockPos, blockPos2, linkedHashMap, arrayList);
        this.a(blockPos2, direction.getOpposite(), direction2, direction3, n2, blockPos, blockPos2, linkedHashMap, arrayList);
        return new u(arrayList, linkedHashMap);
    }

    private List<t> c(r r2) {
        ArrayList<t> arrayList = new ArrayList<t>();
        int n2 = ((Number)this.maxLayers.getValue()).intValue();
        for (int i2 = 1; i2 <= n2; ++i2) {
            int n3 = i2;
            HashMap<Block, Integer> hashMap = new HashMap<Block, Integer>();
            for (t t3 : r2.c) {
                if (t3.c != n3) continue;
                hashMap.merge(t3.b, 1, Integer::sum);
            }
            if (hashMap.isEmpty()) break;
            hashMap.entrySet().stream().sorted(Map.Entry.comparingByValue().reversed()).forEach(entry -> arrayList.add(new t(r2.a, (Block)entry.getKey(), n3)));
        }
        HashSet hashSet = new HashSet();
        arrayList.removeIf(t2 -> !hashSet.add(t2.b));
        return arrayList;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x11);
        }
        return cArray;
    }

    private void a(BlockPos blockPos, int n2, int n3) {
        this.A = true;
        this.z = 0;
        this.B = blockPos.immutable();
        this.C = n2;
        this.D = n3;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.y.clear();
    }

    private void j() {
        if (BedESP.mc.player == null || this.y.isEmpty()) {
            return;
        }
        for (r r4 : this.y) {
            int n2 = -1;
            for (int i2 = 0; i2 < this.x.size(); ++i2) {
                r r5 = this.x.get(i2);
                if (!r5.a.equals((Object)r4.a) && !r5.b.equals((Object)r4.b)) continue;
                n2 = i2;
                break;
            }
            if (n2 >= 0) {
                this.x.set(n2, r4);
                continue;
            }
            this.x.add(r4);
        }
        BlockPos blockPos = BedESP.mc.player.blockPosition();
        this.x.sort((r2, r3) -> Double.compare(r2.a.distSqr((Vec3i)blockPos), r3.a.distSqr((Vec3i)blockPos)));
        int n3 = ((Number)this.maxBeds.getValue()).intValue();
        if (this.x.size() > n3) {
            this.x.subList(n3, this.x.size()).clear();
        }
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        boolean bl;
        if (BedESP.mc.level == null || BedESP.mc.player == null) {
            return;
        }
        this.h();
        int n2 = ((Number)this.range.getValue()).intValue();
        int n3 = ((Number)this.vertical.getValue()).intValue();
        BlockPos blockPos = BedESP.mc.player.blockPosition();
        boolean bl2 = bl = this.A && (n2 != this.C || n3 != this.D || this.B.distSqr((Vec3i)blockPos) > 64.0);
        if (bl) {
            this.a(blockPos, n2, n3);
        }
        if (!this.A) {
            if (++this.z < 4) {
                return;
            }
            this.a(blockPos, n2, n3);
        }
        int n4 = 32768;
        int n5 = this.C * 2 + 1;
        int n6 = this.D * 2 + 1;
        int n7 = this.C * 2 + 1;
        while (this.A && n4-- > 0) {
            BlockState blockState;
            this.H.set(this.B.getX() + BedESP.a(this.E), this.B.getY() + BedESP.a(this.F), this.B.getZ() + BedESP.a(this.G));
            if (BedESP.mc.level.hasChunkAt((BlockPos)this.H) && (blockState = BedESP.mc.level.getBlockState((BlockPos)this.H)).getBlock() instanceof BedBlock && blockState.getValue((Property)BedBlock.PART) == BedPart.HEAD) {
                BlockPos blockPos2 = this.H.immutable();
                BlockPos blockPos3 = blockPos2.relative(((Direction)blockState.getValue((Property)BedBlock.FACING)).getOpposite());
                u u2 = (Boolean)this.surroundings.getValue() != false ? this.a(blockPos2, blockPos3, (Direction)blockState.getValue((Property)BedBlock.FACING)) : new u(new ArrayList<t>(), Map.of());
                this.y.add(new r(blockPos2, blockPos3, u2.a, u2.b));
            }
            if (++this.E < n5) continue;
            this.E = 0;
            if (++this.G < n7) continue;
            this.G = 0;
            if (++this.F < n6) continue;
            this.i();
        }
        this.a();
    }

    private boolean a(r r2) {
        if (r2 == null || BedESP.mc.level == null) {
            return false;
        }
        BlockState blockState = BedESP.mc.level.getBlockState(r2.a);
        BlockState blockState2 = BedESP.mc.level.getBlockState(r2.b);
        return blockState.getBlock() instanceof BedBlock && blockState2.getBlock() instanceof BedBlock && blockState.getValue((Property)BedBlock.PART) == BedPart.HEAD && blockState2.getValue((Property)BedBlock.PART) == BedPart.FOOT;
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
                    case 0 -> 71;
                    case 1 -> 47;
                    case 2 -> 91;
                    case 3 -> 112;
                    case 4 -> 70;
                    case 5 -> 26;
                    default -> 17;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void a(BlockPos blockPos, Direction direction, Direction direction2, Direction direction3, int n2, BlockPos blockPos2, BlockPos blockPos3, Map<s, Integer> map, List<t> list) {
        ArrayList<q> arrayList = new ArrayList<q>();
        HashMap<Long, Integer> hashMap = new HashMap<Long, Integer>();
        arrayList.add(new q(blockPos, 0));
        hashMap.put(blockPos.asLong(), 0);
        for (int i2 = 0; i2 < arrayList.size(); ++i2) {
            q q2 = (q)arrayList.get(i2);
            if (q2.b >= n2) continue;
            for (Direction direction4 : new Direction[]{direction, Direction.UP, direction2, direction3}) {
                BlockState blockState;
                BlockPos blockPos4 = q2.a.relative(direction4);
                int n3 = q2.b + 1;
                Integer n4 = hashMap.putIfAbsent(blockPos4.asLong(), n3);
                if (n4 != null) continue;
                arrayList.add(new q(blockPos4, n3));
                if (blockPos4.equals((Object)blockPos2) || blockPos4.equals((Object)blockPos3) || (blockState = BedESP.mc.level.getBlockState(blockPos4)).isAir()) continue;
                s s2 = new s(blockState.getBlock(), Component.translatable((String)blockState.getBlock().getDescriptionId()).getString());
                map.merge(s2, 1, Integer::sum);
                list.add(new t(blockPos4, blockState.getBlock(), n3));
            }
        }
    }

    @EventTarget
    public void a(WorldOverlay2DEvent worldOverlay2DEvent) {
        for (int i2 = this.x.size() - 1; i2 >= 0; --i2) {
            i i3;
            i i4;
            r r2 = this.x.get(i2);
            if (!((Boolean)this.surroundings.getValue()).booleanValue() || r2.c.isEmpty() || (i4 = b.a((double)(r2.a.getX() + r2.b.getX()) * 0.5 + 0.5, (double)r2.a.getY() + 1.0, (double)(r2.a.getZ() + r2.b.getZ()) * 0.5 + 0.5, worldOverlay2DEvent.c())) == null || i4.a == Float.MAX_VALUE || (i3 = b.a((double)(r2.a.getX() + r2.b.getX()) * 0.5 + 0.5, (double)r2.a.getY() + 2.0, (double)(r2.a.getZ() + r2.b.getZ()) * 0.5 + 0.5, worldOverlay2DEvent.c())) == null || i3.a == Float.MAX_VALUE) continue;
            float f2 = Mth.clamp((float)(Math.abs(i4.b - i3.b) / 48.0f), (float)0.3f, (float)1.35f) * 1.5f;
            List<t> list = this.c(r2);
            if (list.isEmpty()) continue;
            int n2 = 16;
            int n3 = 2;
            int n4 = 4;
            String string = (int)Math.sqrt(BedESP.mc.player.getEyePosition().distanceToSqr((double)(r2.a.getX() + r2.b.getX()) * 0.5 + 0.5, (double)r2.a.getY() + 0.5, (double)(r2.a.getZ() + r2.b.getZ()) * 0.5 + 0.5)) + "m";
            int n5 = list.size() * 16 + Math.max(0, list.size() - 1) * 2 + 8;
            int n6 = (Boolean)this.showDistance.getValue() != false ? Math.max(n5, BedESP.mc.font.width(string) + 10) : n5;
            int n7 = 22 + ((Boolean)this.showDistance.getValue() != false ? 8 : 0);
            float f3 = (float)(-n6) * 0.5f;
            float f4 = -n7;
            worldOverlay2DEvent.a().pushPose();
            worldOverlay2DEvent.a().translate(i4.a, i4.b, 0.0f);
            worldOverlay2DEvent.a().scale(f2, f2, 1.0f);
            RenderUtil.a(worldOverlay2DEvent.a(), f3, f4, (float)n6, (float)n7, 5.0f, 12.0f, 0.9f, 0);
            Renderer.render(worldOverlay2DEvent.b(), drawContext -> {
                try (D d2 = new D();){
                    d2.a(true);
                    d2.a(-1090519040);
                    drawContext.a(Q.a(f3, f4, (float)n6, (float)n7, 5.0f), d2);
                }
            });
            int n8 = Math.round(f4 + (float)n7 - 20.0f);
            int n9 = list.size() * 16 + Math.max(0, list.size() - 1) * 2;
            int n10 = Math.round(f3 + (float)(n6 - n9) * 0.5f);
            int n11 = 0;
            for (t t2 : list) {
                ItemStack itemStack = t2.b.asItem().getDefaultInstance();
                itemStack.setCount(1);
                int n12 = n10 + n11++ * 18;
                worldOverlay2DEvent.b().renderItem(itemStack, n12, n8);
            }
            if (((Boolean)this.showDistance.getValue()).booleanValue()) {
                worldOverlay2DEvent.b().drawString(BedESP.mc.font, string, Math.round(f3 + (float)(n6 - BedESP.mc.font.width(string)) * 0.5f), Math.round(f4 + 2.0f), -1, true);
            }
            worldOverlay2DEvent.a().popPose();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                BedESP.k = 4;
                BedESP.j = 32768;
                var5 = new String[14];
                var3_1 = 0;
                var2_2 = "\u0005J?5\u0015J\r\u0014G4\u0007f^x4[:\u001e%\u007f\b\u0011J)\u0004/yp+\u0004\u0000C4\u0007\n\nN#P\n{h\"](\u0004\u0000C4\u0007\u0010\u0000C4\u0007fY~*_)\u00155ix(A\u000e\u0000C4\u0007fS\u007f3J5\u0003/nh\b\nN#P\u0004\u007fu4\f\u0014Z)\u0002)o\u007f#F5\u00175\u0003\u0005@#\u0004\n@?\u0015";
                var4_3 = "\u0005J?5\u0015J\r\u0014G4\u0007f^x4[:\u001e%\u007f\b\u0011J)\u0004/yp+\u0004\u0000C4\u0007\n\nN#P\n{h\"](\u0004\u0000C4\u0007\u0010\u0000C4\u0007fY~*_)\u00155ix(A\u000e\u0000C4\u0007fS\u007f3J5\u0003/nh\b\nN#P\u0004\u007fu4\f\u0014Z)\u0002)o\u007f#F5\u00175\u0003\u0005@#\u0004\n@?\u0015".length();
                var1_4 = 6;
                var0_5 = -1;
lbl9:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl14:
                // 1 sources

                while (true) {
                    var5[var3_1++] = BedESP.c(BedESP.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0000C4\u0007fHp#F.\u0003\u0005\u0015N5\u0017#";
                    var4_3 = "\u0000C4\u0007fHp#F.\u0003\u0005\u0015N5\u0017#".length();
                    var1_4 = 11;
                    var0_5 = -1;
lbl23:
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
lbl28:
                // 1 sources

                while (true) {
                    var5[var3_1++] = BedESP.c(BedESP.a(v1));
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
lbl39:
                // 1 sources

                ** continue;
            }
        }
        BedESP.I = var5;
    }
}

