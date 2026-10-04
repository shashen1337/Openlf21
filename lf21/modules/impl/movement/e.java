/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.StuckInBlockEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class e
extends Module {
    private static final String l = e.c(e.a("DA\u0017 vj$"));
    private int j = 0;
    private int k = 0;

    public e() {
        super(l, Category.MOVEMENT);
    }

    @EventTarget
    public void a(StrafeEvent strafeEvent) {
        if (this.k > 1) {
            strafeEvent.a(false);
        }
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPre() && e.mc.player != null && this.j < e.mc.player.tickCount) {
            this.k = 0;
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
                    case 0 -> 2;
                    case 1 -> 32;
                    case 2 -> 100;
                    case 3 -> 84;
                    case 4 -> 33;
                    case 5 -> 15;
                    default -> 70;
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
            cArray[0] = (char)(cArray[0] ^ 0x46);
        }
        return cArray;
    }

    @EventTarget
    public void a(StuckInBlockEvent stuckInBlockEvent) {
        if (stuckInBlockEvent.a().getBlock() == Blocks.COBWEB && e.mc.player != null) {
            this.j = e.mc.player.tickCount;
            ++this.k;
            if (this.k > 5) {
                Vec3 vec3 = new Vec3(0.88, 1.88, 0.88);
                stuckInBlockEvent.a(vec3);
            }
        }
    }
}

