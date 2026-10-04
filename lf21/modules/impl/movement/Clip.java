/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import lf21.event.EventTarget;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.Fly;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public final class Clip
extends Module {
    private static final String[] v;
    public final NumberSetting vertical;
    public final NumberSetting horizontal;
    public static Clip j;
    public final NumberSetting clipHeight;
    public final BooleanSetting allowLava;
    public final ModeSetting mode;
    public final BooleanSetting allowWater;
    public final NumberSetting oldVertical;
    public final BooleanSetting resetVelocity;
    public final BooleanSetting requiresStandOn;
    private int u;
    public final NumberSetting oldHorizontal;

    public Clip() {
        String[] stringArray = v;
        super("Clip", Category.MOVEMENT);
        this.mode = new ModeSetting("Mode", "Rise", "Fancy", "Old").withDefault("Rise");
        this.clipHeight = new NumberSetting("Clip Height", 5.0, 1.0, 50.0, 1.0, () -> this.mode.is("Rise"));
        this.horizontal = new NumberSetting("Horizontal", 3.0, 0.0, 10.0, 1.0, () -> this.mode.is("Fancy"));
        this.vertical = new NumberSetting("Vertical", 5.0, 0.0, 10.0, 1.0, () -> this.mode.is("Fancy"));
        this.requiresStandOn = new BooleanSetting("Requires Stand On", true, () -> this.mode.is("Fancy"));
        this.allowWater = new BooleanSetting("Allow Water", false, () -> this.mode.is("Fancy"));
        this.allowLava = new BooleanSetting("Allow Lava", false, () -> this.mode.is("Fancy"));
        this.oldHorizontal = new NumberSetting("Old Horizontal", 0.0, -10.0, 10.0, 0.1, () -> this.mode.is("Old"));
        this.oldVertical = new NumberSetting("Old Vertical", 5.0, -10.0, 10.0, 0.1, () -> this.mode.is("Old"));
        this.resetVelocity = new BooleanSetting("Reset Velocity", true, () -> this.mode.is("Old"));
        j = this;
    }

    private boolean a(BlockPos blockPos, boolean bl) {
        if (bl && this.a(blockPos.below())) {
            return false;
        }
        return this.a(blockPos) && this.a(blockPos.above());
    }

    private BlockPos b(Direction direction, int n2) {
        if (n2 <= 0) {
            return null;
        }
        BlockPos.MutableBlockPos mutableBlockPos = Clip.mc.player.blockPosition().mutable();
        boolean bl = false;
        for (int i2 = 0; i2 < n2; ++i2) {
            boolean bl2;
            mutableBlockPos.move(direction);
            boolean bl3 = bl2 = (Boolean)this.requiresStandOn.getValue() != false && direction != Direction.UP;
            if (this.a((BlockPos)mutableBlockPos, bl2)) {
                if (!bl) continue;
                return mutableBlockPos.immutable();
            }
            bl = true;
        }
        return null;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[17];
                var3_1 = 0;
                var2_2 = "L3oy\u0004|ww6h8>\u0004N0o<\u000b@3b)rQ`j8c-\u000eL3oy\u001avwj%d7&xi\u0004Q6x<\u0003L3o\u000bB3g6%9Rb+n+\nK0y0(vkw>g\u0011Q:z,;k`p\u007fX-3wa#\u0010e\bU:y-;zdo\u0005E>e:+\u0004@3b)\u0004Q6x<\nB3g6%9Ib)j\u000eQ:x<&9Sf3d:;m|";
                var4_3 = "L3oy\u0004|ww6h8>\u0004N0o<\u000b@3b)rQ`j8c-\u000eL3oy\u001avwj%d7&xi\u0004Q6x<\u0003L3o\u000bB3g6%9Rb+n+\nK0y0(vkw>g\u0011Q:z,;k`p\u007fX-3wa#\u0010e\bU:y-;zdo\u0005E>e:+\u0004@3b)\u0004Q6x<\nB3g6%9Ib)j\u000eQ:x<&9Sf3d:;m|".length();
                var1_4 = 12;
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
                    var5[var3_1++] = Clip.c(Clip.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "E>e:+\u0003L3o";
                    var4_3 = "E>e:+\u0003L3o".length();
                    var1_4 = 5;
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
                    var5[var3_1++] = Clip.c(Clip.a(v1));
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
        Clip.v = var5;
    }

    private boolean a(Direction direction, int n2) {
        BlockPos blockPos = this.b(direction, n2);
        if (blockPos == null) {
            return false;
        }
        Clip.mc.player.absMoveTo((double)blockPos.getX() + 0.5, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5, Clip.mc.player.getYRot(), Clip.mc.player.getXRot());
        Clip.mc.player.setDeltaMovement(Vec3.ZERO);
        this.u = 5;
        return true;
    }

    private Direction i() {
        if (Clip.mc.options.keyShift.isDown()) {
            return Direction.DOWN;
        }
        if (Clip.mc.options.keyJump.isDown()) {
            return Direction.UP;
        }
        if (this.mode.is("Rise")) {
            return null;
        }
        if (!Clip.mc.player.horizontalCollision) {
            return null;
        }
        Direction direction = Direction.fromYRot((double)Clip.mc.player.getYRot());
        if (Clip.mc.options.keyUp.isDown()) {
            return direction;
        }
        if (Clip.mc.options.keyDown.isDown()) {
            return direction.getOpposite();
        }
        if (Clip.mc.options.keyLeft.isDown()) {
            return direction.getCounterClockWise();
        }
        if (Clip.mc.options.keyRight.isDown()) {
            return direction.getClockWise();
        }
        return null;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 5);
        }
        return cArray;
    }

    private boolean a(BlockPos blockPos) {
        BlockState blockState = Clip.mc.level.getBlockState(blockPos);
        if (blockState.getCollisionShape((BlockGetter)Clip.mc.level, blockPos).isEmpty()) {
            if (blockState.getFluidState().isEmpty()) {
                return true;
            }
            if (((Boolean)this.allowWater.getValue()).booleanValue() && blockState.getFluidState().is(FluidTags.WATER)) {
                return true;
            }
            return (Boolean)this.allowLava.getValue() != false && blockState.getFluidState().is(FluidTags.LAVA);
        }
        return false;
    }

    @Override
    protected void onEnable() {
        this.u = 0;
        if (Clip.mc.player == null || Clip.mc.level == null) {
            this.setEnabled(false);
            return;
        }
        if (this.mode.is("Old")) {
            this.h();
            this.setEnabled(false);
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
                    case 0 -> 3;
                    case 1 -> 95;
                    case 2 -> 11;
                    case 3 -> 89;
                    case 4 -> 82;
                    case 5 -> 25;
                    default -> 5;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void h() {
        double d2 = Math.toRadians(Clip.mc.player.getYRot());
        double d3 = ((Number)this.oldHorizontal.getValue()).doubleValue();
        double d4 = -Math.sin(d2) * d3;
        double d5 = Math.cos(d2) * d3;
        Clip.mc.player.absMoveTo(Clip.mc.player.getX() + d4, Clip.mc.player.getY() + ((Number)this.oldVertical.getValue()).doubleValue(), Clip.mc.player.getZ() + d5, Clip.mc.player.getYRot(), Clip.mc.player.getXRot());
        if (((Boolean)this.resetVelocity.getValue()).booleanValue()) {
            Clip.mc.player.setDeltaMovement(Vec3.ZERO);
        }
    }

    public boolean a() {
        Direction direction;
        if (Clip.mc.player == null || Clip.mc.level == null) {
            return false;
        }
        this.u = 0;
        String[] stringArray = v;
        if (this.mode.is("Old")) {
            this.h();
            this.setEnabled(false);
            return true;
        }
        if (!this.isEnabled()) {
            this.setEnabled(true);
        }
        if ((direction = this.i()) == null) {
            direction = Direction.UP;
        }
        int n2 = this.mode.is("Rise") ? ((Number)this.clipHeight.getValue()).intValue() : (direction.getAxis().isVertical() ? ((Number)this.vertical.getValue()).intValue() : ((Number)this.horizontal.getValue()).intValue());
        return this.a(direction, n2);
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        block7: {
            block6: {
                if (Clip.mc.player == null || Clip.mc.level == null) break block6;
                String[] stringArray = v;
                if (this.mode.is("Fancy") || this.mode.is("Rise")) break block7;
            }
            return;
        }
        if (Fly.j != null && Fly.j.isEnabled()) {
            return;
        }
        if (this.u > 0) {
            --this.u;
            return;
        }
        Direction direction = this.i();
        if (direction == null) {
            return;
        }
        int n2 = this.mode.is("Rise") ? ((Number)this.clipHeight.getValue()).intValue() : (direction.getAxis().isVertical() ? ((Number)this.vertical.getValue()).intValue() : ((Number)this.horizontal.getValue()).intValue());
        this.a(direction, n2);
    }
}

