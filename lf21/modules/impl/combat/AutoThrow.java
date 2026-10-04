/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import java.util.Comparator;
import java.util.Optional;
import lf21.event.EventTarget;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.Stuck;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.h;
import lf21.utils.game.PlayerUtil;
import lf21.utils.game.g;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Team;

public class AutoThrow
extends Module {
    public int p;
    private final NumberSetting maxDistance;
    private static final String[] r;
    private int q;
    private final h n;
    public a o;
    public static AutoThrow j;
    private final NumberSetting delay;
    private final NumberSetting minDistance;

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x40);
        }
        return cArray;
    }

    @Override
    public void onEnable() {
        this.o = null;
        this.p = 0;
        this.q = -1;
        this.n.a();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "F3\u000f\u0001$\fO?\r@\u0019\u00163v7\r\u00038";
                var4_3 = "F3\u000f\u0001$\fO?\r@\u0019\u00163v7\r\u00038".length();
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
                    var5[var3_1++] = AutoThrow.c(AutoThrow.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "C#\u0017\u000f\t\u00172m!\fO7\u001b@\u0019\u00163v7\r\u00038";
                    var4_3 = "C#\u0017\u000f\t\u00172m!\fO7\u001b@\u0019\u00163v7\r\u00038".length();
                    var1_4 = 9;
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
                    var5[var3_1++] = AutoThrow.c(AutoThrow.a(v1));
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
        AutoThrow.r = var5;
    }

    @Override
    public void onDisable() {
        this.o = null;
        this.p = 0;
        this.q = -1;
    }

    public AutoThrow() {
        String[] stringArray = r;
        super("AutoThrow", Category.COMBAT);
        this.minDistance = new NumberSetting("Min Distance", 5, 3, 30, 1);
        this.maxDistance = new NumberSetting("Max Distance", 10, 3, 30, 1);
        this.delay = new NumberSetting("Delay", 500, 50, 2000, 50);
        this.n = new h();
        this.q = -1;
        j = this;
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
                    case 0 -> 2;
                    case 1 -> 86;
                    case 2 -> 99;
                    case 3 -> 96;
                    case 4 -> 93;
                    case 5 -> 127;
                    default -> 64;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private double b(Entity entity) {
        double d2 = AutoThrow.mc.player.getX() - entity.getX();
        double d3 = AutoThrow.mc.player.getZ() - entity.getZ();
        return Math.sqrt(d2 * d2 + d3 * d3);
    }

    private Optional<? extends Player> a() {
        if (AutoThrow.mc.player == null || AutoThrow.mc.level == null) {
            return Optional.empty();
        }
        return AutoThrow.mc.level.players().stream().filter(abstractClientPlayer -> abstractClientPlayer != AutoThrow.mc.player).filter(abstractClientPlayer -> KillAura.j.e((Entity)abstractClientPlayer)).filter(abstractClientPlayer -> {
            double d2 = this.b((Entity)abstractClientPlayer);
            return d2 >= ((Number)this.minDistance.getValue()).doubleValue() && d2 <= ((Number)this.maxDistance.getValue()).doubleValue();
        }).filter(this::c).filter(abstractClientPlayer -> !this.d((Entity)abstractClientPlayer)).min(Comparator.comparingDouble(abstractClientPlayer -> AutoThrow.mc.player.distanceTo((Entity)abstractClientPlayer)));
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (AutoThrow.mc.player == null) {
            return;
        }
        if (this.q != -1) {
            AutoThrow.mc.player.getInventory().selected = this.q;
            this.q = -1;
            RotationHandler.e = false;
        }
    }

    private boolean d(Entity entity) {
        if (!entity.isInvisible()) {
            return false;
        }
        if (AutoThrow.mc.player.isSpectator()) {
            return false;
        }
        Team team = entity.getTeam();
        return team == null || AutoThrow.mc.player.getTeam() != team || !team.isAlliedTo(AutoThrow.mc.player.getTeam());
    }

    private a a(Entity entity) {
        float f2 = 1.5f;
        float f3 = 0.03f;
        double d2 = entity.getX();
        double d3 = entity.getY() + (double)entity.getBbHeight() * 0.8;
        double d4 = entity.getZ();
        double d5 = entity.getX() - entity.xOld;
        double d6 = entity.getY() - entity.yOld;
        double d7 = entity.getZ() - entity.zOld;
        for (int i2 = 0; i2 < 3; ++i2) {
            double d8 = d2 - AutoThrow.mc.player.getX();
            double d9 = d3 - (AutoThrow.mc.player.getY() + (double)AutoThrow.mc.player.getEyeHeight(AutoThrow.mc.player.getPose()));
            double d10 = d4 - AutoThrow.mc.player.getZ();
            double d11 = Math.sqrt(d8 * d8 + d10 * d10);
            float f4 = (float)(d11 / (double)(f2 * 0.4f));
            d2 = entity.getX() + d5 * (double)f4;
            d3 = entity.getY() + (double)entity.getBbHeight() * 0.8 + d6 * (double)f4;
            d4 = entity.getZ() + d7 * (double)f4;
        }
        double d12 = d2 - AutoThrow.mc.player.getX();
        double d13 = d3 - (AutoThrow.mc.player.getY() + (double)AutoThrow.mc.player.getEyeHeight(AutoThrow.mc.player.getPose()));
        double d14 = d4 - AutoThrow.mc.player.getZ();
        double d15 = Math.sqrt(d12 * d12 + d14 * d14);
        float f5 = (float)(Math.atan2(d14, d12) * 180.0 / Math.PI) - 90.0f;
        float f6 = -g.a((float)d15, (float)d13, f2, f3);
        return new a(f5, f6);
    }

    private boolean c(Entity entity) {
        Vec3 vec3;
        if (AutoThrow.mc.player == null || AutoThrow.mc.level == null) {
            return false;
        }
        Vec3 vec32 = new Vec3(AutoThrow.mc.player.getX(), AutoThrow.mc.player.getY() + (double)AutoThrow.mc.player.getEyeHeight(), AutoThrow.mc.player.getZ());
        BlockHitResult blockHitResult = AutoThrow.mc.level.clip(new ClipContext(vec32, vec3 = new Vec3(entity.getX(), entity.getY() + (double)entity.getEyeHeight(), entity.getZ()), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)AutoThrow.mc.player));
        return blockHitResult.getType() == HitResult.Type.MISS;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventTarget
    public void a(SprintEvent sprintEvent) {
        int n2;
        if (AutoThrow.mc.player == null || AutoThrow.mc.level == null || AutoThrow.mc.gameMode == null || mc.getConnection() == null) {
            return;
        }
        if (Scaffold.INSTANCE != null && Scaffold.INSTANCE.isEnabled() || Stuck.j != null && Stuck.j.isEnabled() || AutoThrow.mc.player.isUsingItem() || AutoThrow.mc.screen != null) {
            this.p = 0;
            this.o = null;
            return;
        }
        if (this.p <= 0) {
            this.o = null;
        }
        int n3 = -1;
        for (n2 = 0; n2 < 9; ++n2) {
            ItemStack itemStack = AutoThrow.mc.player.getInventory().getItem(n2);
            if (itemStack.isEmpty() || !(itemStack.getItem() instanceof EggItem) && !(itemStack.getItem() instanceof SnowballItem)) continue;
            n3 = n2;
            break;
        }
        if (AutoThrow.mc.player.isUsingItem() || AutoThrow.mc.player.getMainHandItem().getItem() instanceof BowItem || AutoThrow.mc.player.getMainHandItem().getItem() instanceof CrossbowItem) {
            return;
        }
        if (n3 == -1) {
            return;
        }
        if (--this.p == 0) {
            boolean bl;
            n2 = AutoThrow.mc.player.getInventory().selected;
            boolean bl2 = bl = n2 != n3;
            if (bl) {
                AutoThrow.mc.player.getInventory().selected = n3;
                PlayerUtil.a();
                this.q = n2;
            }
            float f2 = AutoThrow.mc.player.getYRot();
            float f3 = AutoThrow.mc.player.getXRot();
            if (RotationHandler.a != null && RotationHandler.e) {
                AutoThrow.mc.player.setYRot(RotationHandler.a.e());
                AutoThrow.mc.player.setXRot(RotationHandler.a.f());
            }
            try {
                if (!(AutoThrow.mc.player.getMainHandItem().getItem() instanceof EggItem) && !(AutoThrow.mc.player.getMainHandItem().getItem() instanceof SnowballItem)) {
                    return;
                }
                AutoThrow.mc.gameMode.useItem((Player)AutoThrow.mc.player, InteractionHand.MAIN_HAND);
            }
            finally {
                AutoThrow.mc.player.setYRot(f2);
                AutoThrow.mc.player.setXRot(f3);
            }
        } else {
            if (!this.a().isPresent() || !this.n.a((float)((long)((Number)this.delay.getValue()).doubleValue())) || Stuck.j != null && Stuck.j.isEnabled()) {
                return;
            }
            this.o = this.a((Entity)this.a().get());
            if (this.o != null) {
                RotationHandler.a(this.o);
                RotationHandler.e = true;
                this.p = 2;
            }
            this.n.a();
        }
    }
}

