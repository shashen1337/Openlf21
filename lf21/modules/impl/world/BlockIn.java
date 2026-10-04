/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import lf21.event.EventTarget;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.AutoWeb;
import lf21.modules.impl.world.j;
import lf21.modules.impl.world.k;
import lf21.modules.impl.world.l;
import lf21.modules.impl.world.m;
import lf21.modules.impl.world.n;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.g;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropperBlock;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

public final class BlockIn
extends Module {
    private BlockPos B;
    private float I;
    private static final int p;
    public a v;
    private boolean O;
    private static final float q;
    private static final n[] l;
    private float J;
    private float E;
    private boolean x;
    private static final double o;
    private long N;
    private float R;
    private boolean w;
    private int z;
    private static final long P;
    private static final double n;
    private static final double m;
    private static final Direction[] k;
    public static BlockIn j;
    private BlockPos F;
    private float M;
    private float K;
    public final NumberSetting speed;
    public final NumberSetting rotationTolerance;
    private float D;
    private float L;
    public final NumberSetting randomization;
    public final BooleanSetting ignoreBlocks;
    private boolean A;
    private Vec3 H;
    private float Q;
    private float S;
    private Direction G;
    private int y;
    private Direction C;
    private static final String[] U;
    private long T;

    private boolean o() {
        j j2 = this.p();
        if (j2 == null) {
            j2 = this.q();
        }
        if (j2 == null) {
            return false;
        }
        BlockPos blockPos = j2.a.relative(j2.b);
        this.O = this.c(blockPos);
        this.B = j2.a;
        this.C = j2.b;
        this.D = j2.c;
        this.E = j2.d;
        return true;
    }

    private static double c(double d2) {
        return d2 > 0.0 ? (Math.random() * 2.0 - 1.0) * d2 : 0.0;
    }

    private j a(BlockPos blockPos, Vec3 vec3, int n2) {
        a a2 = this.r();
        boolean bl = Math.abs(vec3.y - ((double)blockPos.getY() + 1.0)) < Math.abs(vec3.y - (double)blockPos.getY());
        boolean bl2 = Math.abs(vec3.z - ((double)blockPos.getZ() + 1.0)) < Math.abs(vec3.z - (double)blockPos.getZ());
        boolean bl3 = Math.abs(vec3.x - ((double)blockPos.getX() + 1.0)) < Math.abs(vec3.x - (double)blockPos.getX());
        double d2 = blockPos.getX();
        double d3 = blockPos.getY();
        double d4 = blockPos.getZ();
        double d5 = 0.020000000000000004;
        ArrayList<m> arrayList = new ArrayList<m>((p + 1) * (p + 1) * 3 + 1);
        arrayList.add(new m(0.0, a2.e(), a2.f()));
        for (int i2 = 0; i2 <= p; ++i2) {
            double d6 = BlockIn.b((double)i2 * 0.2 + BlockIn.c(d5));
            for (int i3 = 0; i3 <= p; ++i3) {
                double d7 = BlockIn.b((double)i3 * 0.2 + BlockIn.c(d5));
                this.a(arrayList, a2, vec3, new Vec3(d2 + d7, bl ? d3 + 1.0 - 0.05 : d3 + 0.05, d4 + d6));
                this.a(arrayList, a2, vec3, new Vec3(d2 + d7, d3 + d6, bl2 ? d4 + 1.0 - 0.05 : d4 + 0.05));
                this.a(arrayList, a2, vec3, new Vec3(bl3 ? d2 + 1.0 - 0.05 : d2 + 0.05, d3 + d6, d4 + d7));
            }
        }
        arrayList.sort(Comparator.comparingDouble(m::a));
        for (m m2 : arrayList) {
            BlockHitResult blockHitResult = this.a(4.5, m2.b, m2.c);
            if (blockHitResult == null || !blockHitResult.getBlockPos().equals((Object)blockPos) || blockHitResult.getBlockPos().getY() < n2 || blockHitResult.getDirection() == Direction.DOWN && blockPos.getY() == n2 || !this.b(blockHitResult.getBlockPos().relative(blockHitResult.getDirection()))) continue;
            return new j(blockHitResult.getBlockPos(), blockHitResult.getDirection(), m2.b, m2.c);
        }
        return null;
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
                    case 0 -> 127;
                    case 1 -> 61;
                    case 2 -> 48;
                    case 3 -> 21;
                    case 4 -> 20;
                    case 5 -> 35;
                    default -> 57;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void k() {
        if (!this.w) {
            return;
        }
        if (BlockIn.mc.player != null && this.x && this.y >= 0 && this.y != BlockIn.mc.player.getInventory().selected) {
            BlockIn.mc.player.getInventory().selected = this.y;
        }
        this.w = false;
        this.x = false;
        this.y = -1;
        this.z = -1;
        this.v = null;
        this.A = false;
        this.l();
    }

    public BlockIn() {
        String[] stringArray = U;
        super("BlockIn", Category.WORLD);
        this.speed = new NumberSetting("Speed", 10, 1, 30, 1);
        this.randomization = new NumberSetting("Randomization", 10, 0, 100, 1);
        this.rotationTolerance = new NumberSetting("Rotation Tolerance", 25, 20, 100, 1);
        this.ignoreBlocks = new BooleanSetting("Ignore Blocks", false);
        this.y = -1;
        this.z = -1;
        this.J = -1.0f;
        j = this;
    }

    private a r() {
        return RotationHandler.c != null ? RotationHandler.c : new a(BlockIn.mc.player.getYRot(), BlockIn.mc.player.getXRot());
    }

    @Override
    protected void onDisable() {
        this.k();
        this.t();
        super.onDisable();
    }

    private boolean b(BlockPos blockPos) {
        return blockPos != null && BlockIn.mc.level != null && BlockIn.mc.level.getBlockState(blockPos).canBeReplaced();
    }

    private void j() {
        if (this.w || BlockIn.mc.player == null) {
            return;
        }
        this.w = true;
        this.x = false;
        this.y = BlockIn.mc.player.getInventory().selected;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                BlockIn.P = 180L;
                BlockIn.q = 30.0f;
                BlockIn.o = 0.2;
                BlockIn.n = 0.05;
                BlockIn.m = 4.5;
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "P\u0004\u0012-RDt`JV\u0011\u001ddzxFK\u001eSSp\u0005,MUpp\r-\\^q{NP\u0005\\D|{M";
                var4_3 = "P\u0004\u0012-RDt`JV\u0011\u001ddzxFK\u001eSSp\u0005,MUpp\r-\\^q{NP\u0005\\D|{M".length();
                var1_4 = 2;
                var0_5 = -1;
lbl12:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl17:
                // 1 sources

                while (true) {
                    var5[var3_1++] = BlockIn.c(BlockIn.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "6Z^zfF\u0019=Q_v\u007fP\u0007=Q_v\u007fjW";
                    var4_3 = "6Z^zfF\u0019=Q_v\u007fP\u0007=Q_v\u007fjW".length();
                    var1_4 = 13;
                    var0_5 = -1;
lbl26:
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
lbl31:
                // 1 sources

                while (true) {
                    var5[var3_1++] = BlockIn.c(BlockIn.a(v1));
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
lbl42:
                // 1 sources

                ** continue;
            }
        }
        BlockIn.U = var5;
        BlockIn.k = new Direction[]{Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH};
        BlockIn.l = new n[]{new n(0, 1, 0, Direction.DOWN), new n(0, -1, 0, Direction.UP), new n(0, 0, -1, Direction.NORTH), new n(0, 0, 1, Direction.SOUTH), new n(1, 0, 0, Direction.EAST), new n(-1, 0, 0, Direction.WEST)};
        BlockIn.p = (int)Math.round(5.0);
    }

    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        float f2;
        float f3 = this.b(this.isEnabled() && BlockIn.mc.player != null && BlockIn.mc.level != null && this.I > 0.0f);
        if (f3 <= 0.001f || BlockIn.mc.player == null || BlockIn.mc.level == null) {
            return;
        }
        long l2 = System.currentTimeMillis() - this.N;
        if (l2 < 50L) {
            f2 = (float)l2 / 50.0f;
            this.K = BlockIn.a(this.L, this.M, BlockIn.a(f2));
        } else {
            this.K = this.M;
        }
        f2 = (float)mc.getWindow().getGuiScaledWidth() / 2.0f;
        float f4 = (float)mc.getWindow().getGuiScaledHeight() / 2.0f;
        float f5 = 10.0f;
        float f6 = 3.0f;
        float f7 = 0.72f + 0.28f * f3;
        this.a(render2DEvent, f2, f4, f5 * f7, f6 * f7, this.K, f3);
        String string = Math.round(this.I) + "/9";
        int n2 = Mth.floor((float)(f2 + f5 * f7 + f6 * f7 * 0.5f + 6.0f));
        Objects.requireNonNull(BlockIn.mc.font);
        int n3 = Mth.floor((float)(f4 - 9.0f / 2.0f));
        int n4 = Mth.clamp((int)Math.round(f3 * 255.0f), (int)0, (int)255);
        render2DEvent.b().drawString(BlockIn.mc.font, string, n2, n3, n4 << 24 | 0xFFFFFF, true);
    }

    private float a(long l2) {
        if (this.T == 0L) {
            return this.S;
        }
        float f2 = Mth.clamp((float)((float)(l2 - this.T) / 180.0f), (float)0.0f, (float)1.0f);
        float f3 = this.S > this.R ? 1.0f - (float)Math.pow(1.0f - f2, 3.0) : f2 * f2;
        return BlockIn.a(this.R, this.S, f3);
    }

    private static float a(float f2, float f3, float f4) {
        return f2 + (f3 - f2) * f4;
    }

    private j p() {
        Vec3 vec3 = BlockIn.mc.player.position();
        BlockPos blockPos = new BlockPos(Mth.floor((double)vec3.x), Mth.floor((double)vec3.y) + 2, Mth.floor((double)vec3.z));
        if (!this.b(blockPos) || this.z < 0 || this.z > 8) {
            return null;
        }
        Vec3 vec32 = BlockIn.mc.player.getEyePosition();
        double d2 = 20.25;
        double d3 = 30.25;
        int n2 = Mth.floor((double)vec32.y) + 1;
        int n3 = Mth.floor((double)(vec32.y + 4.5));
        int n4 = Mth.floor((double)(vec32.x - 4.5));
        int n5 = Mth.floor((double)(vec32.x + 4.5));
        int n6 = Mth.floor((double)(vec32.z - 4.5));
        int n7 = Mth.floor((double)(vec32.z + 4.5));
        ArrayList<k> arrayList = new ArrayList<k>();
        for (int i2 = n2; i2 <= n3; ++i2) {
            for (int i3 = n4; i3 <= n5; ++i3) {
                for (int i4 = n6; i4 <= n7; ++i4) {
                    double d4;
                    BlockPos blockPos2;
                    double d5 = (double)i3 + 0.5 - vec32.x;
                    double d6 = (double)i2 + 0.5 - vec32.y;
                    double d7 = (double)i4 + 0.5 - vec32.z;
                    if (d5 * d5 + d6 * d6 + d7 * d7 > d3 || this.b(blockPos2 = new BlockPos(i3, i2, i4)) || this.a(blockPos2) || !((d4 = this.a(vec32, blockPos2)) <= d2)) continue;
                    arrayList.add(new k(d4, blockPos2));
                }
            }
        }
        arrayList.sort(Comparator.comparingDouble(k::a));
        for (k k2 : arrayList) {
            j j2 = this.a(k2.b, vec32, n2);
            if (j2 == null) continue;
            return j2;
        }
        return null;
    }

    private void a(List<m> list, a a2, Vec3 vec3, Vec3 vec32) {
        a a3 = g.d(vec3, vec32);
        double d2 = Math.abs(Mth.wrapDegrees((float)(a3.e() - a2.e()))) + Math.abs(a3.f() - a2.f());
        list.add(new m(d2, a3.e(), a3.f()));
    }

    private j a(List<BlockPos> list, Vec3 vec3) {
        if (list == null || list.isEmpty() || this.z < 0 || this.z > 8) {
            return null;
        }
        a a2 = this.r();
        BlockHitResult blockHitResult = this.a(4.5, a2.e(), a2.f());
        if (blockHitResult != null && !this.b(blockHitResult.getBlockPos())) {
            for (BlockPos blockPos : list) {
                j j2 = this.a(a2.e(), a2.f(), blockHitResult.getBlockPos(), blockHitResult.getDirection(), blockPos);
                if (j2 == null) continue;
                return j2;
            }
        }
        double d2 = 0.020000000000000004;
        double d3 = 0.949;
        double d4 = 0.051000000000000004;
        ArrayList<l> arrayList = new ArrayList<l>(Math.max(16, list.size() * 6 * (p + 1) * (p + 1)));
        for (BlockPos object : list) {
            for (n n2 : l) {
                BlockPos blockPos = object.offset(n2.a, n2.b, n2.c);
                if (this.b(blockPos) || this.a(blockPos)) continue;
                double d5 = blockPos.getX();
                double d6 = blockPos.getY();
                double d7 = blockPos.getZ();
                for (int i2 = 0; i2 <= p; ++i2) {
                    boolean bl = (i2 & 1) == 0;
                    double d8 = BlockIn.b((double)i2 * 0.2 + BlockIn.c(d2));
                    for (int i3 = 0; i3 <= p; ++i3) {
                        double d9;
                        double d10;
                        double d11;
                        double d12;
                        double d13 = BlockIn.b((double)i3 * 0.2 + BlockIn.c(d2));
                        double d14 = d12 = bl ? d13 : 1.0 - d13;
                        if (n2.b != 0) {
                            d11 = d5 + d12;
                            d10 = d7 + d8;
                            d9 = d6 + (n2.b < 0 ? d3 : d4);
                        } else if (n2.c != 0) {
                            d11 = d5 + d12;
                            d9 = d6 + d8;
                            d10 = d7 + (n2.c < 0 ? d3 : d4);
                        } else {
                            d10 = d7 + d12;
                            d9 = d6 + d8;
                            d11 = d5 + (n2.a < 0 ? d3 : d4);
                        }
                        a a3 = g.d(vec3, new Vec3(d11, d9, d10));
                        float f2 = Math.abs(Mth.wrapDegrees((float)(a3.e() - a2.e())));
                        float f3 = Math.abs(a3.f() - a2.f());
                        if (f2 < 0.1f && f3 < 0.1f) continue;
                        arrayList.add(new l(f2 + f3, a3.e(), a3.f(), blockPos, n2.d, object));
                    }
                }
            }
        }
        arrayList.sort(Comparator.comparingDouble(l::a));
        for (l l2 : arrayList) {
            j j2 = this.a(l2.b, l2.c, l2.d, l2.e, l2.f);
            if (j2 == null) continue;
            return j2;
        }
        return null;
    }

    @Override
    protected boolean e() {
        return true;
    }

    /*
     * WARNING - void declaration
     */
    private j q() {
        void var8_15;
        j j2;
        BlockPos blockPos2 = BlockPos.containing((double)BlockIn.mc.player.getX(), (double)BlockIn.mc.player.getY(), (double)BlockIn.mc.player.getZ());
        BlockPos blockPos3 = blockPos2.above();
        Vec3 vec3 = BlockIn.mc.player.getEyePosition();
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>(8);
        for (Direction object22 : k) {
            arrayList.add(blockPos2.relative(object22));
            arrayList.add(blockPos3.relative(object22));
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (BlockPos blockPos4 : arrayList) {
            if (!this.b(blockPos4) || !this.a(blockPos4, blockPos2, blockPos3)) continue;
            arrayList2.add(blockPos4);
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        Vec3 vec32 = this.a(100.0);
        if (vec32 != null) {
            arrayList.sort(Comparator.comparingDouble(blockPos -> Vec3.atCenterOf((Vec3i)blockPos).distanceToSqr(vec32)));
            int n2 = 0;
            for (BlockPos blockPos4 : arrayList) {
                if (n2 >= 3) break;
                if (!this.b(blockPos4) || !this.a(blockPos4, blockPos2, blockPos3)) continue;
                j j3 = this.a(List.of(blockPos4), vec3);
                if (j3 != null) {
                    return j3;
                }
                ++n2;
            }
        }
        if ((j2 = this.a(arrayList2, vec3)) != null) {
            return j2;
        }
        ArrayList arrayList3 = new ArrayList(arrayList2);
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>(arrayList3);
        for (int i2 = 0; i2 < 5 && !var8_15.isEmpty(); ++i2) {
            ArrayList<BlockPos> arrayList4 = new ArrayList<BlockPos>(var8_15.size() * 3);
            Object object = var8_15.iterator();
            while (object.hasNext()) {
                BlockPos blockPos5 = (BlockPos)object.next();
                for (Direction direction : Direction.values()) {
                    BlockPos blockPos6 = blockPos5.relative(direction).immutable();
                    if (!this.b(blockPos6) || !hashSet.add(blockPos6)) continue;
                    arrayList4.add(blockPos6);
                }
            }
            if (!arrayList4.isEmpty() && (object = this.a(arrayList4, vec3)) != null) {
                return object;
            }
            ArrayList<BlockPos> arrayList5 = arrayList4;
        }
        return null;
    }

    private void i() {
        if (this.F == null || this.G == null || this.H == null || this.z < 0 || this.z > 8) {
            return;
        }
        this.n();
        BlockHitResult blockHitResult = new BlockHitResult(this.H, this.G, this.F, false);
        InteractionResult interactionResult = BlockIn.mc.gameMode.useItemOn(BlockIn.mc.player, InteractionHand.MAIN_HAND, blockHitResult);
        if (interactionResult.consumesAction()) {
            BlockIn.mc.player.swing(InteractionHand.MAIN_HAND);
        }
    }

    private float a(Block block) {
        float f2 = block.defaultDestroyTime();
        if (f2 < 0.0f) {
            return Float.MAX_VALUE;
        }
        if (f2 == 0.0f) {
            return 0.0f;
        }
        return f2 * 100.0f;
    }

    private void s() {
        BlockPos blockPos = BlockPos.containing((double)BlockIn.mc.player.getX(), (double)BlockIn.mc.player.getY(), (double)BlockIn.mc.player.getZ());
        this.I = 0.0f;
        if (!this.b(blockPos.above(2))) {
            this.I += 1.0f;
        }
        for (Direction direction : k) {
            BlockPos blockPos2 = blockPos.relative(direction);
            if (!this.b(blockPos2)) {
                this.I += 1.0f;
            }
            if (this.b(blockPos2.above())) continue;
            this.I += 1.0f;
        }
        if (this.I != this.J) {
            this.L = this.K;
            this.M = Mth.clamp((float)(this.I / 9.0f), (float)0.0f, (float)1.0f);
            this.N = System.currentTimeMillis();
            this.J = this.I;
        }
    }

    public boolean a() {
        return this.w;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x39);
        }
        return cArray;
    }

    private static double b(double d2) {
        return d2 < 0.0 ? 0.0 : Math.min(d2, 1.0);
    }

    private boolean c(BlockPos blockPos) {
        BlockPos blockPos2 = BlockPos.containing((double)BlockIn.mc.player.getX(), (double)BlockIn.mc.player.getY(), (double)BlockIn.mc.player.getZ());
        int n2 = blockPos.getX() - blockPos2.getX();
        int n3 = blockPos.getY() - blockPos2.getY();
        int n4 = blockPos.getZ() - blockPos2.getZ();
        if (n2 == 0 && n4 == 0 && n3 == 2) {
            return true;
        }
        return (n3 == 0 || n3 == 1) && (Math.abs(n2) == 1 && n4 == 0 || Math.abs(n4) == 1 && n2 == 0);
    }

    private void m() {
        this.B = null;
        this.C = null;
        this.v = null;
    }

    private BlockHitResult a(double d2, float f2, float f3) {
        Vec3 vec3;
        if (BlockIn.mc.player == null || BlockIn.mc.level == null) {
            return null;
        }
        Vec3 vec32 = BlockIn.mc.player.getEyePosition();
        BlockHitResult blockHitResult = BlockIn.mc.level.clip(new ClipContext(vec32, vec3 = vec32.add(Vec3.directionFromRotation((float)f3, (float)f2).scale(d2)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)BlockIn.mc.player));
        return blockHitResult.getType() == HitResult.Type.BLOCK ? blockHitResult : null;
    }

    private a a(a a2, a a3) {
        float f2;
        int n2 = ((Number)this.speed.getValue()).intValue();
        if (n2 >= 30) {
            return a3;
        }
        float f3 = Mth.wrapDegrees((float)(a3.e() - a2.e()));
        float f4 = Mth.sqrt((float)(f3 * f3 + (f2 = a3.f() - a2.f()) * f2));
        if (f4 < 0.001f) {
            return a3;
        }
        float f5 = (float)n2 / 30.0f;
        float f6 = f5 * f5 * 180.0f;
        float f7 = ((Number)this.randomization.getValue()).floatValue() / 100.0f;
        float f8 = 0.6f * f7;
        if (f8 > 0.001f) {
            f6 *= 1.0f - f8 / 2.0f + (float)Math.random() * f8;
        }
        float f9 = Math.min(1.0f, f4 / 30.0f);
        f9 = (float)Math.pow(f9, 0.7);
        float f10 = Math.min(f6 *= Math.max(0.8f, 1.0f - f7 * (1.0f - f9)), f4) / f4;
        return new a(a2.e() + f3 * f10, Mth.clamp((float)(a2.f() + f2 * f10), (float)-90.0f, (float)90.0f));
    }

    private void a(Matrix4f matrix4f, float f2, float f3, float f4, float f5, float f6, float f7, int n2, int n3, int n4, int n5) {
        if (f7 <= f6) {
            return;
        }
        int n6 = Math.max(1, (int)Math.ceil((f7 - f6) * 100.0f));
        float f8 = f4 + f5 * 0.5f;
        float f9 = Math.max(0.0f, f4 - f5 * 0.5f);
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_STRIP, DefaultVertexFormat.POSITION_COLOR);
        for (int i2 = 0; i2 <= n6; ++i2) {
            float f10 = f6 + (f7 - f6) * (float)i2 / (float)n6;
            double d2 = Math.toRadians(90.0 + (double)f10 * 360.0);
            float f11 = (float)Math.cos(d2);
            float f12 = (float)Math.sin(d2);
            bufferBuilder.vertex(matrix4f, f2 + f11 * f8, f3 + f12 * f8, 0.0f).color(n2, n3, n4, n5).endVertex();
            bufferBuilder.vertex(matrix4f, f2 + f11 * f9, f3 + f12 * f9, 0.0f).color(n2, n3, n4, n5).endVertex();
        }
        BufferUploader.drawWithShader((BufferBuilder.RenderedBuffer)bufferBuilder.end());
    }

    private int a(boolean bl) {
        int n2 = -1;
        float f2 = bl ? -1.0f : Float.MAX_VALUE;
        for (int i2 = 8; i2 >= 0; --i2) {
            ItemStack itemStack = BlockIn.mc.player.getInventory().getItem(i2);
            if (!this.a(itemStack)) continue;
            float f3 = this.a(((BlockItem)itemStack.getItem()).getBlock());
            if (!(bl ? f3 > f2 : f3 < f2)) continue;
            f2 = f3;
            n2 = i2;
        }
        return n2;
    }

    private void a(Render2DEvent render2DEvent, float f2, float f3, float f4, float f5, float f6, float f7) {
        Matrix4f matrix4f = render2DEvent.a().last().pose();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        int n2 = Mth.clamp((int)Math.round(f7 * 128.0f), (int)0, (int)128);
        int n3 = Mth.clamp((int)Math.round(f7 * 255.0f), (int)0, (int)255);
        this.a(matrix4f, f2, f3, f4, f5, 0.0f, 1.0f, 0, 0, 0, n2);
        float f8 = Mth.clamp((float)f6, (float)0.0f, (float)1.0f);
        int n4 = Math.round((1.0f - f8) * 255.0f);
        int n5 = Math.round(f8 * 255.0f);
        this.a(matrix4f, f2, f3, f4, f5, 0.0f, f8, n4, n5, 0, n3);
        RenderSystem.disableBlend();
    }

    private j a(float f2, float f3, BlockPos blockPos, Direction direction, BlockPos blockPos2) {
        BlockHitResult blockHitResult = this.a(4.5, f2, f3);
        if (blockHitResult == null || !blockHitResult.getBlockPos().equals((Object)blockPos) || blockHitResult.getDirection() != direction || !blockHitResult.getBlockPos().relative(blockHitResult.getDirection()).equals((Object)blockPos2)) {
            return null;
        }
        return new j(blockHitResult.getBlockPos(), blockHitResult.getDirection(), f2, f3);
    }

    private static float a(float f2) {
        return f2 < 0.5f ? 2.0f * f2 * f2 : -1.0f + (4.0f - 2.0f * f2) * f2;
    }

    @Override
    protected void onEnable() {
        this.t();
        if (Scaffold.INSTANCE != null && Scaffold.INSTANCE.isEnabled()) {
            Scaffold.INSTANCE.setEnabled(false);
        }
        if (AutoWeb.j != null && AutoWeb.j.isEnabled()) {
            AutoWeb.j.setEnabled(false);
        }
        super.onEnable();
    }

    private void l() {
        if (BlockIn.mc.options == null || mc.getWindow() == null || BlockIn.mc.screen != null) {
            return;
        }
        long l2 = mc.getWindow().getWindow();
        boolean bl = GLFW.glfwGetMouseButton((long)l2, (int)0) == 1;
        boolean bl2 = GLFW.glfwGetMouseButton((long)l2, (int)1) == 1;
        KeyMapping.set((InputConstants.Key)BlockIn.mc.options.keyAttack.getKey(), (boolean)bl);
        KeyMapping.set((InputConstants.Key)BlockIn.mc.options.keyUse.getKey(), (boolean)bl2);
        BlockIn.mc.options.keyAttack.setDown(bl);
        BlockIn.mc.options.keyUse.setDown(bl2);
    }

    private void n() {
        if (BlockIn.mc.player == null) {
            return;
        }
        int n2 = BlockIn.mc.player.getInventory().selected;
        if (this.z != -1 && this.z != n2) {
            BlockIn.mc.player.getInventory().selected = this.z;
            this.x = true;
        }
    }

    private boolean a(BlockPos blockPos, BlockPos ... blockPosArray) {
        for (Direction direction : Direction.values()) {
            BlockPos blockPos2 = blockPos.relative(direction);
            if (!this.b(blockPos2)) continue;
            boolean bl = false;
            for (BlockPos blockPos3 : blockPosArray) {
                if (!blockPos2.equals((Object)blockPos3)) continue;
                bl = true;
                break;
            }
            if (bl) continue;
            return true;
        }
        return false;
    }

    private void t() {
        this.w = false;
        this.x = false;
        this.y = -1;
        this.z = -1;
        this.A = false;
        this.v = null;
        this.B = null;
        this.C = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.O = false;
    }

    private float b(boolean bl) {
        float f2 = bl ? 1.0f : 0.0f;
        long l2 = System.currentTimeMillis();
        this.Q = this.a(l2);
        if (f2 != this.S) {
            this.R = this.Q;
            this.S = f2;
            this.T = l2;
        }
        this.Q = this.a(l2);
        return this.Q;
    }

    private double a(Vec3 vec3, BlockPos blockPos) {
        AABB aABB = new AABB(blockPos);
        double d2 = Mth.clamp((double)vec3.x, (double)aABB.minX, (double)aABB.maxX);
        double d3 = Mth.clamp((double)vec3.y, (double)aABB.minY, (double)aABB.maxY);
        double d4 = Mth.clamp((double)vec3.z, (double)aABB.minZ, (double)aABB.maxZ);
        return vec3.distanceToSqr(d2, d3, d4);
    }

    private Vec3 a(double d2) {
        Player player = null;
        double d3 = d2;
        for (Player player2 : BlockIn.mc.level.players()) {
            double d4;
            if (player2 == BlockIn.mc.player || (d4 = player2.distanceToSqr((Entity)BlockIn.mc.player)) >= d3) continue;
            d3 = d4;
            player = player2;
        }
        return player == null ? null : player.position();
    }

    @EventTarget(a=2)
    public void a(TickEvent tickEvent) {
        if (!this.isEnabled()) {
            return;
        }
        if (BlockIn.mc.player == null || BlockIn.mc.level == null || BlockIn.mc.gameMode == null || BlockIn.mc.screen != null) {
            this.k();
            this.m();
            return;
        }
        if (this.A) {
            this.A = false;
            this.i();
        }
        this.h();
        this.s();
    }

    private void h() {
        this.m();
        int n2 = this.a(true);
        int n3 = this.a(false);
        if (n2 == -1 && n3 == -1) {
            this.k();
            return;
        }
        int n4 = this.z = n2 != -1 ? n2 : n3;
        if (!this.o()) {
            this.k();
            return;
        }
        int n5 = this.O ? (n2 != -1 ? n2 : n3) : (this.z = n3 != -1 ? n3 : n2);
        if (!this.w) {
            this.j();
        }
        if (BlockIn.mc.options.keyAttack.isDown() || BlockIn.mc.options.keyUse.isDown()) {
            this.m();
        }
        KeyMapping.set((InputConstants.Key)BlockIn.mc.options.keyAttack.getKey(), (boolean)false);
        KeyMapping.set((InputConstants.Key)BlockIn.mc.options.keyUse.getKey(), (boolean)false);
        BlockIn.mc.options.keyAttack.setDown(false);
        BlockIn.mc.options.keyUse.setDown(false);
        this.n();
        if (this.B == null || this.C == null) {
            return;
        }
        a a2 = this.r();
        this.v = this.a(a2, new a(this.D, this.E));
        RotationHandler.a(this.v);
        BlockHitResult blockHitResult = this.a(4.5, this.v.e(), this.v.f());
        if (blockHitResult == null || !blockHitResult.getBlockPos().equals((Object)this.B) || blockHitResult.getDirection() != this.C) {
            return;
        }
        double d2 = ((Number)this.rotationTolerance.getValue()).doubleValue();
        a a3 = RotationHandler.c;
        if (a3 == null || (double)Math.abs(Mth.wrapDegrees((float)(this.v.e() - a3.e()))) > d2 || (double)Math.abs(this.v.f() - a3.f()) > d2) {
            return;
        }
        this.F = blockHitResult.getBlockPos();
        this.G = blockHitResult.getDirection();
        this.H = blockHitResult.getLocation();
        this.A = true;
    }

    private boolean a(BlockPos blockPos) {
        BlockState blockState = BlockIn.mc.level.getBlockState(blockPos);
        if (blockState.canBeReplaced() || blockState.getShape((BlockGetter)BlockIn.mc.level, blockPos).isEmpty()) {
            return true;
        }
        Block block = blockState.getBlock();
        return block instanceof TrapDoorBlock || block instanceof DoorBlock || block instanceof BaseEntityBlock || block instanceof JukeboxBlock || block instanceof FenceGateBlock || block instanceof ChestBlock || block instanceof EnchantmentTableBlock || block instanceof BrewingStandBlock || block instanceof BedBlock || block instanceof DropperBlock || block instanceof DispenserBlock || block instanceof HopperBlock || block instanceof AnvilBlock || block instanceof NoteBlock || block instanceof CraftingTableBlock || block instanceof FenceBlock || block instanceof WallBlock;
    }

    private boolean a(ItemStack itemStack) {
        Item item;
        if (itemStack == null || itemStack.isEmpty() || !((item = itemStack.getItem()) instanceof BlockItem)) {
            return false;
        }
        BlockItem blockItem = (BlockItem)item;
        return (Boolean)this.ignoreBlocks.getValue() == false || !lf21.utils.game.a.a.contains(blockItem.getBlock());
    }
}

