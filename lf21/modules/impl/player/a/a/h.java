/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.impl.player.Helper;
import lf21.modules.impl.player.a.a.d;
import lf21.modules.impl.player.a.a.m;
import lf21.modules.impl.player.a.b;
import lf21.utils.game.PlayerUtil;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import lf21.utils.misc.ReflectionUtil;
import lf21.utils.rotation.a;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public class h
extends b {
    private int g = -1;
    public a b;
    private static final String[] p;
    private int j = 0;
    private BlockPos c;
    private int o = 0;
    private int h = -1;
    private BlockPos k;
    private final m f = null;
    private int i = -1;
    private BlockPos n;
    private boolean m = false;
    private BlockPos d;
    public d e = lf21.modules.impl.player.a.a.d.NONE;
    private boolean l = false;

    @Override
    public void a(TickEvent tickEvent) {
        if (lf21.modules.impl.player.a.a.h.mc.player == null) {
            return;
        }
        if (lf21.modules.impl.player.a.a.h.mc.player.isOnFire()) {
            if (this.l && lf21.modules.impl.player.a.a.h.a(lf21.modules.impl.player.a.a.h.mc.player.getDeltaMovement().y)) {
                this.m = true;
            } else if (lf21.modules.impl.player.a.a.h.mc.player.onGround()) {
                this.i = lf21.utils.game.d.a(Items.WATER_BUCKET);
                if (this.i != -1 && this.i < 9) {
                    this.h = lf21.modules.impl.player.a.a.h.mc.player.getInventory().selected;
                    lf21.modules.impl.player.a.a.h.mc.player.getInventory().selected = this.i;
                    this.l = true;
                    this.b = new a(lf21.modules.impl.player.a.a.h.mc.player.getYRot(), 90.0f);
                    this.o = 5;
                }
            }
        }
        if (--this.o == 0 && this.l) {
            this.l = false;
            this.b = null;
            if (this.h != -1) {
                lf21.modules.impl.player.a.a.h.mc.player.getInventory().selected = this.h;
            }
        }
    }

    public h() {
        super("Self Extinguish");
    }

    @Override
    public void b() {
        this.f();
    }

    private void f() {
        this.c = null;
        this.d = null;
        this.k = null;
        this.e = lf21.modules.impl.player.a.a.d.NONE;
        this.b = null;
        if (this.h != -1 && lf21.modules.impl.player.a.a.h.mc.player != null) {
            lf21.modules.impl.player.a.a.h.mc.player.getInventory().selected = this.h;
        }
        this.h = -1;
        this.g = -1;
        this.i = -1;
        this.j = 0;
        this.l = false;
        this.m = false;
        this.n = null;
        this.o = 0;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x7D);
        }
        return cArray;
    }

    public static boolean a(double d2) {
        if (lf21.modules.impl.player.a.a.h.mc.level == null || lf21.modules.impl.player.a.a.h.mc.player == null) {
            return false;
        }
        Iterable iterable = lf21.modules.impl.player.a.a.h.mc.level.getBlockCollisions((Entity)lf21.modules.impl.player.a.a.h.mc.player, lf21.modules.impl.player.a.a.h.mc.player.getBoundingBox().move(0.0, d2, 0.0));
        return iterable.iterator().hasNext();
    }

    @Override
    public boolean c() {
        return this.l && this.b != null;
    }

    public InteractionResult a(Player player, Level level, InteractionHand interactionHand) {
        String[] stringArray = p;
        if (ReflectionUtil.getStaticField(lf21.modules.impl.player.a.a.h.mc.gameMode, "localPlayerMode", "net/minecraft/client/multiplayer/MultiPlayerGameMode") == GameType.SPECTATOR) {
            return InteractionResult.PASS;
        }
        PlayerUtil.a();
        PacketUtil.a(n2 -> new ServerboundUseItemPacket(interactionHand, n2));
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (player.getCooldowns().isOnCooldown(itemStack.getItem())) {
            return InteractionResult.PASS;
        }
        InteractionResult interactionResult = ForgeHooks.onItemRightClick((Player)player, (InteractionHand)interactionHand);
        if (interactionResult != null) {
            return interactionResult;
        }
        InteractionResultHolder interactionResultHolder = itemStack.use(level, player, interactionHand);
        ItemStack itemStack2 = (ItemStack)interactionResultHolder.getObject();
        if (itemStack2 != itemStack) {
            player.setItemInHand(interactionHand, itemStack2);
            if (itemStack2.isEmpty()) {
                ForgeEventFactory.onPlayerDestroyItem((Player)player, (ItemStack)itemStack, (InteractionHand)interactionHand);
            }
        }
        return interactionResultHolder.getResult();
    }

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
                    case 0 -> 58;
                    case 1 -> 101;
                    case 2 -> 90;
                    case 3 -> 62;
                    case 4 -> 13;
                    case 5 -> 99;
                    default -> 125;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    public void a(PreMotionEvent preMotionEvent) {
        if (lf21.modules.impl.player.a.a.h.mc.player == null) {
            return;
        }
        if (this.m) {
            if (Helper.h()) {
                return;
            }
            this.m = false;
            if (lf21.modules.impl.player.a.a.h.mc.hitResult != null && lf21.modules.impl.player.a.a.h.mc.hitResult.getType() == HitResult.Type.BLOCK && ((BlockHitResult)lf21.modules.impl.player.a.a.h.mc.hitResult).getDirection() == Direction.UP) {
                this.n = ((BlockHitResult)lf21.modules.impl.player.a.a.h.mc.hitResult).getBlockPos().above();
                Helper.a(this.n);
                this.a((Player)lf21.modules.impl.player.a.a.h.mc.player, (Level)lf21.modules.impl.player.a.a.h.mc.level, InteractionHand.MAIN_HAND);
            } else {
                String[] stringArray = p;
                ChatUtil.print("Failed to place water!");
                this.l = false;
                this.b = null;
            }
        } else if (this.n != null) {
            this.l = false;
            this.b = null;
            if (lf21.modules.impl.player.a.a.h.mc.hitResult != null && lf21.modules.impl.player.a.a.h.mc.hitResult.getType() == HitResult.Type.BLOCK && ((BlockHitResult)lf21.modules.impl.player.a.a.h.mc.hitResult).getBlockPos().above().equals((Object)this.n)) {
                int n2 = lf21.utils.game.d.a(Items.BUCKET);
                if (n2 != -1 && n2 < 9) {
                    lf21.modules.impl.player.a.a.h.mc.player.getInventory().selected = n2;
                    this.a((Player)lf21.modules.impl.player.a.a.h.mc.player, (Level)lf21.modules.impl.player.a.a.h.mc.level, InteractionHand.MAIN_HAND);
                }
                Helper.c(this.n);
            } else {
                ChatUtil.print("Failed to recycle water due to moving!");
            }
            if (this.h != -1) {
                lf21.modules.impl.player.a.a.h.mc.player.getInventory().selected = this.h;
            }
            this.n = null;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "|\u00043Rh\u0007]N\nzNa\u0002\u001e_E-_y\u0006\u000f\u001b4T\u0000.\u0011`\n\u0013_\u0006(_k\u0017RY\t3[c\u0017RW\u00106Jd\u0013\u0011[\u001c?L\".\bV\u00113na\u0002\u0004_\u0017\u001d_`\u00060U\u0001?\u000fi\u00006X-&\u0005N\f4Yx\n\u000eR";
                var4_3 = "|\u00043Rh\u0007]N\nzNa\u0002\u001e_E-_y\u0006\u000f\u001b4T\u0000.\u0011`\n\u0013_\u0006(_k\u0017RY\t3[c\u0017RW\u00106Jd\u0013\u0011[\u001c?L\".\bV\u00113na\u0002\u0004_\u0017\u001d_`\u00060U\u0001?\u000fi\u00006X-&\u0005N\f4Yx\n\u000eR".length();
                var1_4 = 22;
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
                    var5[var3_1++] = lf21.modules.impl.player.a.a.h.b(lf21.modules.impl.player.a.a.h.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "|\u00043Rh\u0007]N\nzLh\u0000\u0004Y\t?\u001ez\u0002\t_\u0017zZx\u0006]N\nzSb\u0015\u0014T\u0002{\u000fV\n9_a3\u0011[\u001c?L@\f\u0019_";
                    var4_3 = "|\u00043Rh\u0007]N\nzLh\u0000\u0004Y\t?\u001ez\u0002\t_\u0017zZx\u0006]N\nzSb\u0015\u0014T\u0002{\u000fV\n9_a3\u0011[\u001c?L@\f\u0019_".length();
                    var1_4 = 38;
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
                    var5[var3_1++] = lf21.modules.impl.player.a.a.h.b(lf21.modules.impl.player.a.a.h.a(v1));
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
        lf21.modules.impl.player.a.a.h.p = var5;
    }

    @Override
    public a d() {
        return this.b;
    }

    @Override
    public void a() {
        this.f();
    }
}

