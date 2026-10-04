/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class LegitReach
extends Module {
    public static LegitReach INSTANCE;
    public final NumberSetting reach;
    public final BooleanSetting playersOnly;
    private static final String[] l;

    public LegitReach() {
        String[] stringArray = l;
        super("LegitReach", Category.COMBAT);
        this.reach = new NumberSetting("Reach", 3.35, 3.0, 6.0, 0.05);
        this.playersOnly = new BooleanSetting("Players Only", true);
        INSTANCE = this;
    }

    public void updateCrosshairTarget(float f2) {
        AABB aABB;
        EntityHitResult entityHitResult;
        if (!this.isEnabled() || LegitReach.mc.player == null || LegitReach.mc.level == null || LegitReach.mc.gameMode == null) {
            return;
        }
        double d2 = ((Number)this.reach.getValue()).doubleValue();
        Vec3 vec3 = LegitReach.mc.player.getEyePosition(f2);
        Vec3 vec32 = LegitReach.mc.player.getViewVector(f2);
        Vec3 vec33 = vec3.add(vec32.scale(d2));
        BlockHitResult blockHitResult = LegitReach.mc.level.clip(new ClipContext(vec3, vec33, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (Entity)LegitReach.mc.player));
        double d3 = d2 * d2;
        if (blockHitResult.getType() != HitResult.Type.MISS) {
            d3 = vec3.distanceToSqr(blockHitResult.getLocation());
        }
        if ((entityHitResult = ProjectileUtil.getEntityHitResult((Entity)LegitReach.mc.player, (Vec3)vec3, (Vec3)vec33, (AABB)(aABB = LegitReach.mc.player.getBoundingBox().expandTowards(vec32.scale(d2)).inflate(1.0)), this::a, (double)d3)) != null) {
            LegitReach.mc.hitResult = entityHitResult;
            LegitReach.mc.crosshairPickEntity = entityHitResult.getEntity();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = ";um\u000e?\u001bVKVb\u001b#\u00059|m\u00142\n'|k\u001e.;@\nzd";
        int n3 = ";um\u000e?\u001bVKVb\u001b#\u00059|m\u00142\n'|k\u001e.;@\nzd".length();
        int n4 = 12;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = LegitReach.c(LegitReach.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                l = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private boolean a(Entity entity) {
        if (entity == LegitReach.mc.player || entity.isSpectator() || !entity.isPickable() || !entity.isAlive()) {
            return false;
        }
        if (((Boolean)this.playersOnly.getValue()).booleanValue()) {
            return entity instanceof Player;
        }
        return entity instanceof LivingEntity;
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
                    case 0 -> 107;
                    case 1 -> 25;
                    case 2 -> 12;
                    case 3 -> 119;
                    case 4 -> 90;
                    case 5 -> 105;
                    default -> 37;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x25);
        }
        return cArray;
    }
}

