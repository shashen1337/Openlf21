/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import java.util.ArrayList;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.SneakEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.movement.FireballBlink;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.h;
import lf21.utils.game.e;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

public class TargetStrafe
extends Module {
    public static Entity p;
    private final BooleanSetting jumpKeyOnly;
    private static final String[] r;
    private final h q;
    private final NumberSetting switchDelay;
    public static int o;
    public static TargetStrafe j;
    private final NumberSetting range;
    private final h k;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "/xjt1\u0003K\u001c-Hj}1\f1luct<}\u0011\u007ffbt";
                var4_3 = "/xjt1\u0003K\u001c-Hj}1\f1luct<}\u0011\u007ffbt".length();
                var1_4 = 13;
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
                    var5[var3_1++] = TargetStrafe.c(TargetStrafe.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "7lict\f6znpr \u000e!hkeh";
                    var4_3 = "7lict\f6znpr \u000e!hkeh".length();
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
                    var5[var3_1++] = TargetStrafe.c(TargetStrafe.a(v1));
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
        TargetStrafe.r = var5;
        TargetStrafe.o = 1;
    }

    public static boolean h() {
        return (Boolean)TargetStrafe.j.jumpKeyOnly.getValue();
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
                    case 0 -> 101;
                    case 1 -> 13;
                    case 2 -> 7;
                    case 3 -> 4;
                    case 4 -> 17;
                    case 5 -> 72;
                    default -> 46;
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
            cArray[0] = (char)(cArray[0] ^ 0x2E);
        }
        return cArray;
    }

    @EventTarget
    public void a(SneakEvent sneakEvent) {
        if (!sneakEvent.isCancelled() && !FireballBlink.j.isEnabled()) {
            sneakEvent.a(true);
        }
    }

    public TargetStrafe() {
        String[] stringArray = r;
        super("TargetStrafe", Category.MOVEMENT);
        this.k = new h();
        this.jumpKeyOnly = new BooleanSetting("Jump Key Only", true);
        this.range = new NumberSetting("Range", Float.valueOf(0.5f), Float.valueOf(0.1f), Float.valueOf(2.0f), Float.valueOf(0.1f));
        this.switchDelay = new NumberSetting("Switch Delay", 1000, 100, 5000, 100);
        this.q = new h();
        j = this;
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPost() && TargetStrafe.mc.player != null) {
            boolean bl;
            if (KillAura.k == null) {
                p = null;
            } else if (this.q.c(((Number)this.switchDelay.getValue()).intValue()) || p == null) {
                ArrayList<Entity> arrayList = new ArrayList<Entity>(KillAura.m);
                arrayList.sort((entity, entity2) -> {
                    float f2 = TargetStrafe.mc.player.distanceTo(entity);
                    float f3 = TargetStrafe.mc.player.distanceTo(entity2);
                    return Float.compare(f2, f3);
                });
                if (!arrayList.isEmpty()) {
                    p = arrayList.get(0);
                    this.q.a();
                }
            }
            AABB aABB = TargetStrafe.mc.player.getBoundingBox();
            boolean bl2 = bl = e.a(aABB.minX, aABB.minY, aABB.minZ) || e.a(aABB.minX, aABB.minY, aABB.maxZ) || e.a(aABB.maxX, aABB.minY, aABB.minZ) || e.a(aABB.maxX, aABB.minY, aABB.maxZ);
            if ((bl || TargetStrafe.mc.player.horizontalCollision) && this.k.a(500.0, true)) {
                o *= -1;
            }
        }
    }

    public static float a() {
        return ((Number)TargetStrafe.j.range.getValue()).floatValue();
    }
}

