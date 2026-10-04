/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.FireballBlink;
import lf21.modules.impl.player.a.a.e;
import lf21.modules.impl.player.a.a.f;
import lf21.modules.impl.player.a.a.g;
import lf21.modules.impl.player.a.a.h;
import lf21.modules.impl.player.a.b;
import lf21.modules.impl.player.x;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class Helper
extends Module {
    private static final Map<BlockPos, Integer> l;
    private final BooleanSetting legit;
    private final MultiSelectSetting mode;
    public static a k;
    public static Helper j;
    private final NumberSetting speed;
    private static final String[] u;
    private static x n;
    private final NumberSetting fOV;
    private static final Map<BlockPos, Integer> m;
    private final List<b> s;
    private a t;

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
                    case 0 -> 91;
                    case 1 -> 69;
                    case 2 -> 98;
                    case 3 -> 83;
                    case 4 -> 4;
                    case 5 -> 82;
                    default -> 32;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static Set<BlockPos> a(BlockPos blockPos, Block block) {
        HashSet<BlockPos> hashSet = new HashSet<BlockPos>();
        if (Helper.mc.level == null || blockPos == null) {
            return hashSet;
        }
        for (int i2 = -6; i2 <= 6; ++i2) {
            for (int i3 = -5; i3 <= 5; ++i3) {
                for (int i4 = -6; i4 <= 6; ++i4) {
                    BlockPos blockPos2 = blockPos.offset(i2, i3, i4);
                    if (!Helper.b(blockPos2, block)) continue;
                    hashSet.add(blockPos2.immutable());
                }
            }
        }
        return hashSet;
    }

    public static boolean e(BlockPos blockPos) {
        return blockPos != null && l.containsKey(blockPos);
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (FireballBlink.j != null && FireballBlink.j.isEnabled()) {
            return;
        }
        k = null;
        this.s.stream().filter(b2 -> this.mode.isSelected(b2.e())).forEach(b2 -> {
            b2.a(motionEvent);
            if (b2.c() && b2.d() != null) {
                k = b2.d();
            }
        });
        if (((Boolean)this.legit.getValue()).booleanValue() && k != null && motionEvent.isPost()) {
            this.t = k;
            a a2 = RotationHandler.b;
            if (a2 != null) {
                k = lf21.utils.game.g.a(a2, k, ((Number)this.speed.getValue()).doubleValue());
            }
        } else if (motionEvent.isPost()) {
            this.t = null;
        }
    }

    public static boolean a(Vec3 vec3) {
        a a2;
        if (j == null || !((Boolean)Helper.j.legit.getValue()).booleanValue()) {
            return true;
        }
        if (Helper.mc.player == null) {
            return true;
        }
        a a3 = new a(Helper.mc.player.getYRot(), Helper.mc.player.getXRot());
        return a3.b(a2 = lf21.utils.game.g.d(Helper.mc.player.getEyePosition(), vec3)) <= ((Number)Helper.j.fOV.getValue()).doubleValue() / 2.0;
    }

    public Helper() {
        String[] stringArray = u;
        super("Helper", Category.PLAYER);
        this.mode = new MultiSelectSetting("Mode", "Self Extinguish", "Extinguish Fire", "Block Lava", "Block Water").withDefaults("Self Extinguish", "Extinguish Fire", "Block Lava", "Block Water");
        this.legit = new BooleanSetting("Legit", false);
        this.speed = new NumberSetting("Speed", 45, 2, 180, 1, this.legit::getValue);
        this.fOV = new NumberSetting("FOV", 90, 30, 180, 1, this.legit::getValue);
        this.s = new ArrayList<b>();
        j = this;
        this.s.add(new h());
        this.s.add(new g());
        this.s.add(new e());
        this.s.add(new f());
    }

    private static void j() {
        if (n == null || Helper.mc.level == null) {
            return;
        }
        Set<BlockPos> set = Helper.a(Helper.n.b, Helper.n.a);
        set.removeAll(Helper.n.c);
        set.forEach(blockPos -> {
            if (Helper.n.a == Blocks.WATER) {
                Helper.a(blockPos);
            } else {
                Helper.b(blockPos);
            }
        });
        if (!set.isEmpty() || --Helper.n.d <= 0) {
            n = null;
        }
    }

    public static void b(BlockPos blockPos) {
        Helper.a(m, blockPos);
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (FireballBlink.j != null && FireballBlink.j.isEnabled()) {
            return;
        }
        Helper.j();
        Helper.k();
        this.s.stream().filter(b2 -> this.mode.isSelected(b2.e())).forEach(b2 -> b2.a(tickEvent));
    }

    @Override
    protected void onDisable() {
        k = null;
        this.t = null;
        l.clear();
        m.clear();
        n = null;
        this.s.forEach(b::b);
    }

    private static void k() {
        if (Helper.mc.level == null) {
            l.clear();
            m.clear();
            return;
        }
        Helper.a(l, Blocks.WATER);
        Helper.a(m, Blocks.LAVA);
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (FireballBlink.j != null && FireballBlink.j.isEnabled()) {
            return;
        }
        this.s.stream().filter(b2 -> this.mode.isSelected(b2.e())).forEach(b2 -> b2.a(renderEvent));
    }

    private static void a(Map<BlockPos, Integer> map, Block block) {
        map.entrySet().removeIf(entry -> {
            if (Helper.b((BlockPos)entry.getKey(), block)) {
                entry.setValue(0);
                return false;
            }
            int n2 = (Integer)entry.getValue();
            if (n2 <= 0) {
                return true;
            }
            entry.setValue(n2 - 1);
            return false;
        });
    }

    @Override
    protected void onEnable() {
        this.s.forEach(b::a);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[13];
                var3_1 = 0;
                var2_2 = "\u0017 \u0005:p\u000f\b \u000e5$\u0017X/,\f4q;S3\u000f\u001e=\u0016:j5U26\nsB;R>\u0003\u001d\n4\u0005\b5\u00076`\u000f\b \u000e5$\u0017X/,\f4q;S3\n\u0019)\r0orl:3\u0003\u000b\u0019)\r0orw:1\u0007!\u000b\u0019)\r0orw:1\u0007!\u0006\u0013 \u000e#a \u0004\u0016*\u00066";
                var4_3 = "\u0017 \u0005:p\u000f\b \u000e5$\u0017X/,\f4q;S3\u000f\u001e=\u0016:j5U26\nsB;R>\u0003\u001d\n4\u0005\b5\u00076`\u000f\b \u000e5$\u0017X/,\f4q;S3\n\u0019)\r0orl:3\u0003\u000b\u0019)\r0orw:1\u0007!\u000b\u0019)\r0orw:1\u0007!\u0006\u0013 \u000e#a \u0004\u0016*\u00066".length();
                var1_4 = 5;
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
                    var5[var3_1++] = Helper.c(Helper.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u001e=\u0016:j5U26\nsB;R>\n\u0019)\r0orl:3\u0003";
                    var4_3 = "\u001e=\u0016:j5U26\nsB;R>\n\u0019)\r0orl:3\u0003".length();
                    var1_4 = 15;
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
                    var5[var3_1++] = Helper.c(Helper.a(v1));
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
        Helper.u = var5;
        Helper.l = new HashMap<BlockPos, Integer>();
        Helper.m = new HashMap<BlockPos, Integer>();
    }

    @EventTarget
    public void a(PreMotionEvent preMotionEvent) {
        if (FireballBlink.j != null && FireballBlink.j.isEnabled()) {
            return;
        }
        Helper.i();
        this.s.stream().filter(b2 -> this.mode.isSelected(b2.e())).forEach(b2 -> b2.a(preMotionEvent));
    }

    public static void c(BlockPos blockPos) {
        if (blockPos != null) {
            l.remove(blockPos);
        }
    }

    public static boolean f(BlockPos blockPos) {
        return blockPos != null && m.containsKey(blockPos);
    }

    public static boolean h() {
        if (j == null || !((Boolean)Helper.j.legit.getValue()).booleanValue()) {
            return false;
        }
        if (Helper.j.t == null) {
            return false;
        }
        a a2 = RotationHandler.b;
        if (a2 == null) {
            return false;
        }
        return Helper.j.t.b(a2) > 2.0;
    }

    private static void i() {
        if (Helper.mc.player == null || Helper.mc.level == null) {
            return;
        }
        Item item = Helper.mc.player.getMainHandItem().getItem();
        if (item != Items.WATER_BUCKET && item != Items.LAVA_BUCKET) {
            item = Helper.mc.player.getOffhandItem().getItem();
        }
        if (item == Items.WATER_BUCKET || item == Items.LAVA_BUCKET) {
            Block block = item == Items.WATER_BUCKET ? Blocks.WATER : Blocks.LAVA;
            n = new x(block, Helper.mc.player.blockPosition(), Helper.a(Helper.mc.player.blockPosition(), block), 20);
        }
    }

    public boolean a() {
        if (FireballBlink.j != null && FireballBlink.j.isEnabled()) {
            return false;
        }
        return k != null;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x20);
        }
        return cArray;
    }

    private static boolean b(BlockPos blockPos, Block block) {
        return Helper.mc.level.getBlockState(blockPos).is(block) && Helper.mc.level.getFluidState(blockPos).isSource();
    }

    private static void a(Map<BlockPos, Integer> map, BlockPos blockPos) {
        if (blockPos != null) {
            map.put(blockPos.immutable(), 20);
        }
    }

    public static void d(BlockPos blockPos) {
        if (blockPos != null) {
            m.remove(blockPos);
        }
    }

    public static void a(BlockPos blockPos) {
        Helper.a(l, blockPos);
    }
}

