/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.SlowdownEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.a;
import lf21.utils.misc.PacketUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundBundlePacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.network.protocol.game.ClientboundLightUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerChatPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSoundEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public final class NoSlow
extends Module {
    private boolean p;
    private a l = a.IDLE;
    private static final String[] r;
    private final Queue<Packet<ClientGamePacketListener>> q = new ConcurrentLinkedQueue<Packet<ClientGamePacketListener>>();
    private boolean n;
    private boolean o;
    public static NoSlow j;
    private boolean m;
    public static boolean k;

    public static boolean isBlocking(Minecraft minecraft) {
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventTarget
    public void a(PacketEvent packetEvent) {
        ServerboundPlayerActionPacket serverboundPlayerActionPacket;
        if (NoSlow.mc.player == null) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (packetEvent.a()) {
            if (this.l != a.IDLE && this.l != a.PREPARED && !this.a(packet)) {
                packetEvent.a(true);
                this.q.add(packet);
            }
            return;
        }
        if (this.m) {
            return;
        }
        if (packet instanceof ServerboundMovePlayerPacket) {
            this.h();
            return;
        }
        if (packet instanceof ServerboundPlayerActionPacket && (serverboundPlayerActionPacket = (ServerboundPlayerActionPacket)packet).getAction() == ServerboundPlayerActionPacket.Action.RELEASE_USE_ITEM && this.l == a.USING) {
            k = true;
            this.p = false;
            this.l = a.RELEASED;
            return;
        }
        if (packet instanceof ServerboundUseItemOnPacket) {
            this.o = true;
            if (this.l == a.PREPARED) {
                this.b(false);
            }
            return;
        }
        if (!(packet instanceof ServerboundUseItemPacket)) {
            return;
        }
        serverboundPlayerActionPacket = (ServerboundUseItemPacket)packet;
        ItemStack itemStack = NoSlow.mc.player.getItemInHand(serverboundPlayerActionPacket.getHand());
        if (this.a(itemStack) || this.b(itemStack) || !this.b(itemStack.getUseAnimation()) || this.k()) {
            if (this.l == a.PREPARED) {
                this.b(false);
            }
            return;
        }
        if (NoSlow.mc.crosshairPickEntity != null || itemStack.getUseAnimation() == UseAnim.BOW || itemStack.getUseAnimation() == UseAnim.CROSSBOW || itemStack.getUseAnimation() == UseAnim.SPEAR) {
            this.a(false);
            return;
        }
        packetEvent.a(true);
        this.a(true);
        this.a();
        this.n = true;
        InteractionHand interactionHand = serverboundPlayerActionPacket.getHand() == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        boolean bl = this.m;
        this.m = true;
        try {
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundUseItemPacket(interactionHand, serverboundPlayerActionPacket.getSequence()));
        }
        finally {
            this.m = bl;
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
                    case 0 -> 90;
                    case 1 -> 28;
                    case 2 -> 99;
                    case 3 -> 45;
                    case 4 -> 65;
                    case 5 -> 28;
                    default -> 94;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (NoSlow.mc.player == null || mc.getConnection() == null) {
            return;
        }
        this.o = false;
        if (this.l == a.PREPARED) {
            this.b(false);
            return;
        }
        if (this.l == a.FLUSH) {
            this.j();
            this.i();
            return;
        }
        if (this.l == a.USING && !NoSlow.mc.player.isUsingItem()) {
            this.p = false;
            if (this.n) {
                NoSlow.mc.options.keyUse.setDown(false);
            }
            this.l = this.n ? a.RESTORE_ON_MOVE : a.WAIT_FLUSH;
        }
    }

    private static boolean a(Minecraft minecraft) {
        BlockHitResult blockHitResult;
        HitResult hitResult;
        block6: {
            block5: {
                hitResult = minecraft.hitResult;
                if (!(hitResult instanceof BlockHitResult)) break block5;
                blockHitResult = (BlockHitResult)hitResult;
                if (minecraft.level != null) break block6;
            }
            return false;
        }
        hitResult = minecraft.level.getBlockState(blockHitResult.getBlockPos());
        if (hitResult == null || hitResult.isAir()) {
            return false;
        }
        if (hitResult.getMenuProvider((Level)minecraft.level, blockHitResult.getBlockPos()) != null) {
            return true;
        }
        Block block = hitResult.getBlock();
        return block instanceof AbstractCauldronBlock || block instanceof BedBlock || block instanceof BellBlock || block instanceof ButtonBlock || block instanceof CakeBlock || block instanceof CandleCakeBlock || block instanceof ComposterBlock || block instanceof DoorBlock || block instanceof FenceGateBlock || block instanceof JukeboxBlock || block instanceof LecternBlock || block instanceof LeverBlock || block instanceof NoteBlock || block instanceof RespawnAnchorBlock || block instanceof TrapDoorBlock;
    }

    private void b(boolean bl) {
        if (bl && this.n && NoSlow.mc.player != null && mc.getConnection() != null) {
            this.a();
        }
        this.j();
        this.i();
    }

    @Override
    protected void onDisable() {
        this.b(true);
        k = false;
    }

    private void h() {
        if (this.l == a.RELEASED) {
            this.l = this.n ? a.RESTORE_ON_MOVE : a.FLUSH;
            return;
        }
        if (this.l == a.RESTORE_ON_MOVE) {
            if (this.n) {
                this.a();
            }
            this.n = false;
            this.l = a.FLUSH;
        } else if (this.l == a.WAIT_FLUSH) {
            this.l = a.FLUSH;
        }
    }

    @EventTarget
    public void a(SlowdownEvent slowdownEvent) {
        if (NoSlow.mc.player == null || !NoSlow.mc.player.isUsingItem()) {
            return;
        }
        if (this.l == a.USING && this.p && !this.o && !this.b(NoSlow.mc.player.getUseItem())) {
            slowdownEvent.setSlowDown(false);
        }
    }

    private boolean a(Packet<?> packet) {
        if (packet instanceof ClientboundSetEntityMotionPacket) {
            ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet;
            return NoSlow.mc.player == null || clientboundSetEntityMotionPacket.getId() != NoSlow.mc.player.getId();
        }
        if (packet instanceof ClientboundSetEntityDataPacket) {
            ClientboundSetEntityDataPacket clientboundSetEntityDataPacket = (ClientboundSetEntityDataPacket)packet;
            return NoSlow.mc.player == null || clientboundSetEntityDataPacket.id() != NoSlow.mc.player.getId();
        }
        return packet instanceof ClientboundSetDisplayObjectivePacket || packet instanceof ClientboundSetEquipmentPacket || packet instanceof ClientboundAddEntityPacket || packet instanceof ClientboundRemoveEntitiesPacket || packet instanceof ClientboundSetHealthPacket || packet instanceof ClientboundContainerSetContentPacket || packet instanceof ClientboundEntityEventPacket || packet instanceof ClientboundSetObjectivePacket || packet instanceof ClientboundSetScorePacket || packet instanceof ClientboundSetSubtitleTextPacket || packet instanceof ClientboundSetTitleTextPacket || packet instanceof ClientboundMoveEntityPacket || packet instanceof ClientboundTeleportEntityPacket || packet instanceof ClientboundRotateHeadPacket || packet instanceof ClientboundDamageEventPacket || packet instanceof ClientboundSoundPacket || packet instanceof ClientboundSoundEntityPacket || packet instanceof ClientboundLevelEventPacket || packet instanceof ClientboundLevelParticlesPacket || packet instanceof ClientboundLightUpdatePacket || packet instanceof ClientboundSetTimePacket || packet instanceof ClientboundSystemChatPacket || packet instanceof ClientboundBundlePacket || packet instanceof ClientboundPlayerChatPacket;
    }

    private boolean k() {
        return this.a(NoSlow.mc.player.getMainHandItem().getUseAnimation()) && this.a(NoSlow.mc.player.getOffhandItem().getUseAnimation());
    }

    private void a() {
        if (NoSlow.mc.player == null || mc.getConnection() == null) {
            return;
        }
        boolean bl = this.m;
        this.m = true;
        try {
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN));
        }
        finally {
            this.m = bl;
        }
    }

    private boolean b(ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty()) {
            return false;
        }
        String string = itemStack.getItem().getClass().getSimpleName();
        String[] stringArray = r;
        return string.contains("BowlFood") || string.contains("SuspiciousStew");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean l() {
        if (j == null) return false;
        if (!j.isEnabled()) return false;
        if (NoSlow.mc.player == null) return false;
        if (!NoSlow.mc.player.isUsingItem()) return false;
        return true;
    }

    private void j() {
        if (mc.getConnection() == null) {
            this.q.clear();
            return;
        }
        while (!this.q.isEmpty()) {
            Packet<ClientGamePacketListener> packet = this.q.poll();
            if (packet == null) {
                continue;
            }
            try {
                packet.handle((PacketListener)mc.getConnection());
            }
            catch (Exception exception) {}
        }
    }

    private void a(boolean bl) {
        this.p = true;
        this.n = bl;
        this.l = a.USING;
        this.q.clear();
    }

    private boolean a(ItemStack itemStack) {
        return itemStack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged((ItemStack)itemStack);
    }

    public static boolean shouldCancelEdNavenStartUse(Minecraft minecraft) {
        boolean bl;
        if (j == null || !j.isEnabled() || minecraft.player == null) {
            return false;
        }
        if (NoSlow.j.l != a.IDLE && NoSlow.j.l != a.PREPARED) {
            return true;
        }
        ItemStack itemStack = minecraft.player.getMainHandItem();
        ItemStack itemStack2 = minecraft.player.getOffhandItem();
        boolean bl2 = bl = j.b(itemStack.getUseAnimation()) && !j.b(itemStack) || j.b(itemStack2.getUseAnimation()) && !j.b(itemStack2);
        if (bl) {
            NoSlow.j.l = a.PREPARED;
        }
        return false;
    }

    public NoSlow() {
        super("NoSlow", Category.MOVEMENT);
        j = this;
    }

    private boolean a(UseAnim useAnim) {
        return useAnim == UseAnim.EAT || useAnim == UseAnim.DRINK;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x5E);
        }
        return cArray;
    }

    public static boolean shouldBypassBlockInteraction(Minecraft minecraft) {
        if (j == null || !j.isEnabled() || minecraft.player == null || minecraft.hitResult == null || minecraft.hitResult.getType() != HitResult.Type.BLOCK || NoSlow.a(minecraft)) {
            return false;
        }
        ItemStack itemStack = minecraft.player.getMainHandItem();
        ItemStack itemStack2 = minecraft.player.getOffhandItem();
        if (itemStack.getItem() instanceof BlockItem) {
            return false;
        }
        return j.b(itemStack.getUseAnimation()) && !j.b(itemStack) || j.b(itemStack2.getUseAnimation()) && !j.b(itemStack2);
    }

    private boolean b(UseAnim useAnim) {
        return useAnim == UseAnim.EAT || useAnim == UseAnim.DRINK || useAnim == UseAnim.SPEAR || useAnim == UseAnim.BOW || useAnim == UseAnim.CROSSBOW;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "\u0018s\u0014A\u0007s1>\u000e\ti\u0010](\u007f75i\u0010~5y)\u0006\u0014s0A.k";
        int n3 = "\u0018s\u0014A\u0007s1>\u000e\ti\u0010](\u007f75i\u0010~5y)\u0006\u0014s0A.k".length();
        int n4 = 8;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = NoSlow.c(NoSlow.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                r = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public static boolean m() {
        return j != null && j.isEnabled() && NoSlow.j.l != a.IDLE;
    }

    @Override
    protected void onEnable() {
        this.i();
    }

    private void i() {
        this.l = a.IDLE;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q.clear();
    }
}

