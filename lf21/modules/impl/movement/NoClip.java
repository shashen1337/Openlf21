/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import lf21.event.EventTarget;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.utils.misc.PacketUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class NoClip
extends Module {
    private static final String[] l;
    public static NoClip j;
    public final BooleanSetting block;

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
                    case 0 -> 81;
                    case 1 -> 34;
                    case 2 -> 53;
                    case 3 -> 82;
                    case 4 -> 55;
                    case 5 -> 91;
                    default -> 45;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u001fMv>^+\u0005\u0013NZ1\\";
        int n3 = "\u001fMv>^+\u0005\u0013NZ1\\".length();
        int n4 = 6;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = NoClip.c(NoClip.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                l = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @Override
    protected void onDisable() {
        if (NoClip.mc.player != null) {
            NoClip.mc.player.noPhysics = false;
        }
    }

    public NoClip() {
        String[] stringArray = l;
        super("NoClip", Category.MOVEMENT);
        this.block = new BooleanSetting("Block", false);
        j = this;
    }

    @Override
    protected void onEnable() {
        if (NoClip.mc.player != null) {
            NoClip.mc.player.noPhysics = true;
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (NoClip.mc.player == null || NoClip.mc.level == null) {
            return;
        }
        NoClip.mc.player.noPhysics = true;
        if (!((Boolean)this.block.getValue()).booleanValue() || !(NoClip.mc.player.getMainHandItem().getItem() instanceof BlockItem)) {
            return;
        }
        BlockPos blockPos = NoClip.mc.player.blockPosition().below();
        if (NoClip.mc.level.getBlockState(blockPos).isAir()) {
            return;
        }
        Vec3 vec3 = Vec3.atCenterOf((Vec3i)blockPos).add(0.0, 0.5, 0.0);
        BlockHitResult blockHitResult = new BlockHitResult(vec3, Direction.UP, blockPos, false);
        PacketUtil.b(n2 -> new ServerboundUseItemOnPacket(InteractionHand.MAIN_HAND, blockHitResult, n2));
        NoClip.mc.player.swing(InteractionHand.MAIN_HAND);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x2D);
        }
        return cArray;
    }
}

