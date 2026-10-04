/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.TargetStrafe;
import lf21.utils.game.e;
import lf21.utils.game.g;
import lf21.utils.rotation.a;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Team;

public class d
extends Module {
    private static final String j = d.c(d.a(":Y4\u0003DMs\u0016X\u000b\u001fH[~"));

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
                    case 0 -> 121;
                    case 1 -> 54;
                    case 2 -> 88;
                    case 3 -> 111;
                    case 4 -> 45;
                    case 5 -> 62;
                    default -> 26;
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
            cArray[0] = (char)(cArray[0] ^ 0x1A);
        }
        return cArray;
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPost() && e.g() && d.mc.player != null && d.mc.level != null) {
            AABB aABB = d.mc.player.getBoundingBox().expandTowards(0.25, 0.25, 0.25);
            int n2 = 0;
            for (Entity entity : d.mc.level.entitiesForRendering()) {
                if (!(entity instanceof LivingEntity) && !(entity instanceof Boat) && !(entity instanceof Minecart) && !(entity instanceof FishingHook) || entity instanceof ArmorStand || entity.getId() == d.mc.player.getId() || !entity.isAlive() || !aABB.intersects(entity.getBoundingBox()) || entity.getId() == -8 || entity.getId() == -1337 || d.mc.player.getTeam() != null && entity.getTeam() != null && !entity.getTeam().getCollisionRule().equals((Object)Team.CollisionRule.ALWAYS) && (d.mc.player.getTeam().isAlliedTo(entity.getTeam()) ? !entity.getTeam().getCollisionRule().equals((Object)Team.CollisionRule.PUSH_OWN_TEAM) : !entity.getTeam().getCollisionRule().equals((Object)Team.CollisionRule.PUSH_OTHER_TEAMS))) continue;
                ++n2;
            }
            float f2 = d.mc.player.getYRot();
            if (d.mc.options.keyDown.isDown()) {
                f2 += 180.0f;
                if (d.mc.options.keyLeft.isDown()) {
                    f2 += 45.0f;
                } else if (d.mc.options.keyRight.isDown()) {
                    f2 -= 45.0f;
                }
            } else if (d.mc.options.keyUp.isDown()) {
                if (d.mc.options.keyLeft.isDown()) {
                    f2 -= 45.0f;
                } else if (d.mc.options.keyRight.isDown()) {
                    f2 += 45.0f;
                }
            } else if (d.mc.options.keyRight.isDown()) {
                f2 += 90.0f;
            } else if (d.mc.options.keyLeft.isDown()) {
                f2 -= 90.0f;
            }
            double d2 = Math.toRadians(f2);
            double d3 = 0.065f * (float)Math.min(4, n2);
            if (TargetStrafe.p != null && TargetStrafe.j.isEnabled() && (!TargetStrafe.h() || d.mc.options.keyJump.isDown())) {
                float f3 = (float)(d3 / ((double)TargetStrafe.a() * Math.PI * 2.0) * 360.0) * (float)TargetStrafe.o;
                a a2 = g.b(new Vec3(TargetStrafe.p.getX(), TargetStrafe.p.getY(), TargetStrafe.p.getZ()), new Vec3(d.mc.player.getX(), d.mc.player.getY(), d.mc.player.getZ()));
                a2.a(a2.e() + f3);
                float f4 = a2.e() * ((float)Math.PI / 180);
                double d4 = TargetStrafe.p.getX() - Math.sin(f4) * (double)TargetStrafe.a();
                double d5 = TargetStrafe.p.getZ() + Math.cos(f4) * (double)TargetStrafe.a();
                d2 = g.b(new Vec3(d.mc.player.getX(), d.mc.player.getY(), d.mc.player.getZ()), new Vec3(d4, TargetStrafe.p.getY(), d5)).e() * ((float)Math.PI / 180);
            }
            e.a(d2, d3);
            e.a(1.0E-4f);
        }
    }

    public d() {
        super(j, Category.MOVEMENT);
    }
}

