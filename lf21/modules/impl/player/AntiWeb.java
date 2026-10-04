/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.player.Helper;
import lf21.modules.impl.player.b_0;
import lf21.modules.impl.player.t;
import lf21.utils.a.g;
import lf21.utils.game.PlayerUtil;
import lf21.utils.game.d;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.a;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AntiWeb
extends Module {
    private final g o;
    public static AntiWeb j;
    private static final String[] x;
    private int r = -1;
    public static a k;
    private int w;
    private BlockPos p;
    private boolean s = false;
    private final g n;
    private int q = -1;
    private int v;
    private int u;
    public static b_0 l;
    private final g m = new g();
    private BlockPos t = null;

    private void a(int n2) {
        this.j();
        this.w = Math.max(this.w, n2);
        k = null;
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (AntiWeb.mc.player == null || AntiWeb.mc.level == null || AntiWeb.mc.gameMode == null) {
            return;
        }
        if (!motionEvent.isPost()) {
            return;
        }
        if (this.w > 0) {
            --this.w;
            l = b_0.IDLE;
            k = null;
            return;
        }
        if (l == b_0.PLACING && this.p != null && !AntiWeb.mc.level.getBlockState(this.p).is(Blocks.COBWEB)) {
            ++this.u;
            this.t = this.a(this.p);
            if (this.u >= 3 && this.t != null) {
                Helper.a(this.t);
                l = b_0.RECYCLING;
                this.s = false;
                this.v = 0;
                this.n.b();
                this.o.b();
            }
        } else if (l == b_0.PLACING) {
            this.u = 0;
        }
        switch (lf21.modules.impl.player.t.a[l.ordinal()]) {
            case 1: {
                if (AntiWeb.mc.player.isInWater() || !this.m.a(5) || !this.h()) {
                    return;
                }
                this.r = d.a(Items.WATER_BUCKET);
                if (this.r == -1 || this.r > 8) {
                    return;
                }
                l = b_0.PLACING;
                break;
            }
            case 2: {
                if (!this.s && !this.h()) {
                    this.j();
                    return;
                }
                if (this.s) break;
                if (this.q == -1) {
                    this.q = AntiWeb.mc.player.getInventory().selected;
                }
                AntiWeb.mc.player.getInventory().selected = this.r;
                PlayerUtil.a();
                Vec3 vec3 = AntiWeb.mc.player.getEyePosition();
                Vec3 vec32 = Vec3.atCenterOf((Vec3i)this.p).add(0.0, 0.5, 0.0);
                k = lf21.utils.game.g.d(vec3, vec32);
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Rot(k.e(), k.f(), AntiWeb.mc.player.onGround()));
                PacketUtil.a(n2 -> new ServerboundUseItemPacket(InteractionHand.MAIN_HAND, n2));
                this.s = true;
                break;
            }
            case 3: {
                if (++this.v < 6) {
                    return;
                }
                if (this.o.a(30)) {
                    String[] stringArray = x;
                    ChatUtil.print("\u00a7cAntiWeb: timeout");
                    this.a(40);
                    return;
                }
                if (AntiWeb.mc.player.getMainHandItem().getItem() == Items.WATER_BUCKET) {
                    this.j();
                    return;
                }
                if (this.t == null || !AntiWeb.mc.level.getBlockState(this.t).is(Blocks.WATER)) {
                    ChatUtil.print("\u00a7cAntiWeb: recycle failed");
                    this.a(20);
                    return;
                }
                if (AntiWeb.mc.player.getMainHandItem().getItem() != Items.BUCKET) {
                    AntiWeb.mc.player.getInventory().selected = this.r;
                    PlayerUtil.a();
                    k = null;
                    return;
                }
                k = lf21.utils.game.g.a(this.t, 0.0f);
                if (this.s && this.n.a(10)) {
                    this.s = false;
                    this.n.b();
                }
                if (this.s && !this.n.a(5)) break;
                PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Rot(k.e(), k.f(), AntiWeb.mc.player.onGround()));
                PacketUtil.a(n2 -> new ServerboundUseItemPacket(InteractionHand.MAIN_HAND, n2));
                this.s = true;
                this.n.b();
            }
        }
    }

    private boolean h() {
        if (AntiWeb.mc.player == null || AntiWeb.mc.level == null) {
            return false;
        }
        AABB aABB = AntiWeb.mc.player.getBoundingBox().inflate(0.1);
        for (int i2 = Mth.floor((double)aABB.minX); i2 <= Mth.floor((double)aABB.maxX); ++i2) {
            for (int i3 = Mth.floor((double)aABB.minY); i3 <= Mth.floor((double)aABB.maxY); ++i3) {
                for (int i4 = Mth.floor((double)aABB.minZ); i4 <= Mth.floor((double)aABB.maxZ); ++i4) {
                    BlockPos blockPos = new BlockPos(i2, i3, i4);
                    if (!AntiWeb.mc.level.getBlockState(blockPos).is(Blocks.COBWEB)) continue;
                    this.p = blockPos;
                    return true;
                }
            }
        }
        return false;
    }

    public AntiWeb() {
        super("AntiWeb", Category.PLAYER);
        this.n = new g();
        this.o = new g();
        j = this;
    }

    public b_0 a() {
        return l;
    }

    @Override
    public void onDisable() {
        this.w = 0;
        k = null;
        if (mc != null && AntiWeb.mc.options.keyUse.isDown()) {
            AntiWeb.mc.options.keyUse.setDown(false);
        }
        if (this.q != -1 && AntiWeb.mc.player != null) {
            AntiWeb.mc.player.getInventory().selected = this.q;
            PlayerUtil.a();
        }
        this.j();
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
                    case 0 -> 114;
                    case 1 -> 75;
                    case 2 -> 24;
                    case 3 -> 37;
                    case 4 -> 98;
                    case 5 -> 21;
                    default -> 4;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void j() {
        if (AntiWeb.mc.options.keyUse.isDown()) {
            AntiWeb.mc.options.keyUse.setDown(false);
        }
        if (this.q != -1 && AntiWeb.mc.player != null) {
            AntiWeb.mc.player.getInventory().selected = this.q;
            PlayerUtil.a();
            this.q = -1;
        }
        l = b_0.IDLE;
        k = null;
        this.p = null;
        this.t = null;
        this.s = false;
        this.u = 0;
        this.v = 0;
        this.m.b();
        this.n.b();
        this.o.b();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "3%lL5pf\u0012\u00d5(YK\u0016|S\u0017)\"\u0005\u0016|i\u0017$mQ\u0019\u00d5(YK\u0016|S\u0017)\"\u0005\u0010pg\u000b(t@Bse\u001b'}A";
        int n3 = "3%lL5pf\u0012\u00d5(YK\u0016|S\u0017)\"\u0005\u0016|i\u0017$mQ\u0019\u00d5(YK\u0016|S\u0017)\"\u0005\u0010pg\u000b(t@Bse\u001b'}A".length();
        int n4 = 7;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = AntiWeb.c(AntiWeb.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                x = stringArray;
                l = b_0.IDLE;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @Override
    public void onEnable() {
        this.w = 0;
        this.j();
    }

    public static boolean i() {
        return j != null && j.isEnabled() && l != b_0.IDLE;
    }

    private BlockPos a(BlockPos blockPos) {
        BlockPos blockPos2;
        int n2;
        int n3;
        int n4;
        if (AntiWeb.mc.level == null) {
            return null;
        }
        if (AntiWeb.mc.level.getBlockState(blockPos).is(Blocks.WATER) && AntiWeb.mc.level.getFluidState(blockPos).getAmount() == 8) {
            return blockPos;
        }
        for (n4 = -1; n4 <= 1; ++n4) {
            for (n3 = -1; n3 <= 1; ++n3) {
                for (n2 = -1; n2 <= 1; ++n2) {
                    blockPos2 = blockPos.offset(n4, n3, n2);
                    if (!AntiWeb.mc.level.getBlockState(blockPos2).is(Blocks.WATER) || AntiWeb.mc.level.getFluidState(blockPos2).getAmount() != 8) continue;
                    return blockPos2;
                }
            }
        }
        for (n4 = -1; n4 <= 1; ++n4) {
            for (n3 = -1; n3 <= 1; ++n3) {
                for (n2 = -1; n2 <= 1; ++n2) {
                    blockPos2 = blockPos.offset(n4, n3, n2);
                    if (!AntiWeb.mc.level.getBlockState(blockPos2).is(Blocks.WATER)) continue;
                    return blockPos2;
                }
            }
        }
        return null;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        Color color;
        AABB aABB;
        if (l == b_0.IDLE || AntiWeb.mc.gameRenderer == null) {
            return;
        }
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = AntiWeb.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        if (this.p != null) {
            aABB = new AABB(this.p);
            color = new Color(0, 150, 255);
            RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.25f);
            RenderUtil.a(aABB, poseStack);
            RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.75f);
            RenderUtil.b(aABB, poseStack);
        }
        if (this.t != null && l == b_0.RECYCLING) {
            aABB = new AABB(this.t);
            color = new Color(0, 255, 0);
            RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.25f);
            RenderUtil.a(aABB, poseStack);
            RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.75f);
            RenderUtil.b(aABB, poseStack);
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        poseStack.popPose();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 4);
        }
        return cArray;
    }
}

