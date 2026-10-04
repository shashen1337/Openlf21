/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.misc;

import com.mojang.blaze3d.platform.InputConstants;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import net.minecraft.world.entity.Entity;

public class f
extends Module {
    private static final String j = f.c(f.a("@%;8\u0010hux"));

    public f() {
        super(j, Category.MISC);
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPost() && f.mc.player != null && f.mc.level != null) {
            f.mc.options.keyShift.setDown(f.mc.player.onGround() && f.a(0.3f));
        }
    }

    public static boolean a(float f2) {
        return !f.mc.level.getCollisions((Entity)f.mc.player, f.mc.player.getBoundingBox().move(0.0, -0.5, 0.0).inflate((double)(-f2), 0.0, (double)(-f2))).iterator().hasNext();
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
                    case 0 -> 19;
                    case 1 -> 68;
                    case 2 -> 93;
                    case 3 -> 93;
                    case 4 -> 71;
                    case 5 -> 9;
                    default -> 25;
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
            cArray[0] = (char)(cArray[0] ^ 0x19);
        }
        return cArray;
    }

    @Override
    public void onDisable() {
        boolean bl = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)f.mc.options.keyShift.getKey().getValue());
        f.mc.options.keyShift.setDown(bl);
    }
}

