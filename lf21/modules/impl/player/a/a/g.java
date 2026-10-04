/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.impl.player.Helper;
import lf21.modules.impl.player.a.a.c;
import lf21.modules.impl.player.a.b;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.a;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class g
extends b {
    private boolean f = false;
    private static final String[] i;
    private int g = 0;
    private Direction d = Direction.UP;
    public a b;
    private BlockPos c;
    public c e = lf21.modules.impl.player.a.a.c.NONE;
    private static final String h;

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
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
                    case 0 -> 18;
                    case 1 -> 42;
                    case 2 -> 9;
                    case 3 -> 39;
                    case 4 -> 65;
                    case 5 -> 52;
                    default -> 23;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    public void a(MotionEvent motionEvent) {
        if (lf21.modules.impl.player.a.a.g.mc.player == null || lf21.modules.impl.player.a.a.g.mc.level == null || lf21.modules.impl.player.a.a.g.mc.gameMode == null || lf21.modules.impl.player.a.a.g.mc.player.isUsingItem()) {
            return;
        }
        if (motionEvent.isPost()) {
            if (this.e == lf21.modules.impl.player.a.a.c.FIRE) {
                if (this.c == null || !lf21.modules.impl.player.a.a.g.mc.level.getBlockState(this.c).is(Blocks.FIRE) || lf21.modules.impl.player.a.a.g.mc.player.getEyePosition().distanceToSqr(Vec3.atCenterOf((Vec3i)this.c)) > 25.0) {
                    this.f();
                } else {
                    this.b = lf21.utils.game.g.d(lf21.modules.impl.player.a.a.g.mc.player.getEyePosition(), Vec3.atCenterOf((Vec3i)this.c));
                    if (!this.f) {
                        this.f = true;
                        this.g = 2;
                    }
                }
            } else if (this.e == lf21.modules.impl.player.a.a.c.NONE) {
                this.g();
                if (this.c != null) {
                    this.e = lf21.modules.impl.player.a.a.c.FIRE;
                    this.b = lf21.utils.game.g.d(lf21.modules.impl.player.a.a.g.mc.player.getEyePosition(), Vec3.atCenterOf((Vec3i)this.c));
                    this.f = true;
                    this.g = 2;
                }
            }
            if (this.f && this.e == lf21.modules.impl.player.a.a.c.FIRE && this.c != null) {
                if (this.g > 0) {
                    --this.g;
                    return;
                }
                if (Helper.h()) {
                    return;
                }
                lf21.modules.impl.player.a.a.g.mc.gameMode.startDestroyBlock(this.c, Direction.UP);
                lf21.modules.impl.player.a.a.g.mc.player.swing(InteractionHand.MAIN_HAND);
                this.f = false;
            }
        }
    }

    private void f() {
        this.c = null;
        this.d = Direction.UP;
        this.e = lf21.modules.impl.player.a.a.c.NONE;
        this.b = null;
    }

    public g() {
        super("Extinguish Fire");
    }

    @Override
    public void b() {
        this.f();
    }

    @Override
    public boolean c() {
        return this.b != null;
    }

    @Override
    public void a() {
        this.f();
    }

    private void g() {
        if (lf21.modules.impl.player.a.a.g.mc.player == null || lf21.modules.impl.player.a.a.g.mc.level == null) {
            this.c = null;
            return;
        }
        BlockPos blockPos2 = lf21.modules.impl.player.a.a.g.mc.player.blockPosition();
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        Vec3 vec3 = lf21.modules.impl.player.a.a.g.mc.player.getEyePosition();
        for (int i2 = -8; i2 <= 8; ++i2) {
            for (int i3 = -2; i3 <= 2; ++i3) {
                for (int i4 = -8; i4 <= 8; ++i4) {
                    BlockPos blockPos3 = blockPos2.offset(i2, i3, i4);
                    if (!lf21.modules.impl.player.a.a.g.mc.level.getBlockState(blockPos3).is(Blocks.FIRE) || !(Vec3.atCenterOf((Vec3i)blockPos3).distanceToSqr(vec3) <= 25.0) || !Helper.a(Vec3.atCenterOf((Vec3i)blockPos3))) continue;
                    arrayList.add(blockPos3);
                }
            }
        }
        this.c = arrayList.stream().min(Comparator.comparingDouble(blockPos -> Vec3.atCenterOf((Vec3i)blockPos).distanceToSqr(vec3))).orElse(null);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "WR}N/Sb{Ya\u0007\u0007]ew\u000fWR}N/Sb{Ya\u0007\u0007]ew";
        int n3 = "WR}N/Sb{Ya\u0007\u0007]ew\u000fWR}N/Sb{Ya\u0007\u0007]ew".length();
        int n4 = 15;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.modules.impl.player.a.a.g.b(lf21.modules.impl.player.a.a.g.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                i = stringArray;
                h = i[0];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @Override
    public void a(RenderEvent renderEvent) {
        if (this.e != lf21.modules.impl.player.a.a.c.FIRE || this.c == null || lf21.modules.impl.player.a.a.g.mc.gameRenderer == null) {
            return;
        }
        Color color = new Color(255, 0, 0);
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = lf21.modules.impl.player.a.a.g.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        AABB aABB = new AABB(this.c);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.25f);
        RenderUtil.a(aABB, poseStack);
        RenderSystem.setShaderColor((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.75f);
        RenderUtil.b(aABB, poseStack);
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
            cArray[0] = (char)(cArray[0] ^ 0x17);
        }
        return cArray;
    }

    @Override
    public a d() {
        return this.b;
    }
}

