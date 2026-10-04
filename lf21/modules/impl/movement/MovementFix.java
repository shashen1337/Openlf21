/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import lf21.event.EventTarget;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.StrafeEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.Scaffold;
import lf21.settings.impl.ModeSetting;
import lf21.utils.game.e;
import lf21.utils.rotation.RotationHandler;

public final class MovementFix
extends Module {
    private static final String[] l;
    public static MovementFix j;
    private final ModeSetting mode;

    public MovementFix() {
        String[] stringArray = l;
        super("MovementFix", Category.MOVEMENT);
        this.mode = new ModeSetting("Mode", "Normal", "Sprint Only").withDefault("Normal");
        j = this;
        this.setEnabled(true);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "\u001ed)c\u0010$\u0004\u001dd?k\u000b\u001dd-k\u001c-!$M2v\u000b\u0003{)g\u001f<o\u001fe7w";
                var4_3 = "\u001ed)c\u0010$\u0004\u001dd?k\u000b\u001dd-k\u001c-!$M2v\u000b\u0003{)g\u001f<o\u001fe7w".length();
                var1_4 = 6;
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
                    var5[var3_1++] = MovementFix.c(MovementFix.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u001ed)c\u0010$\u000b\u0003{)g\u001f<o\u001fe7w";
                    var4_3 = "\u001ed)c\u0010$\u000b\u0003{)g\u001f<o\u001fe7w".length();
                    var1_4 = 6;
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
                    var5[var3_1++] = MovementFix.c(MovementFix.a(v1));
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
        MovementFix.l = var5;
    }

    @EventTarget(a=1)
    public void a(SprintEvent sprintEvent) {
        if (!this.mode.is("Sprint Only") || !MovementFix.a() || MovementFix.mc.player == null) {
            return;
        }
        if (MovementFix.mc.player.input != null && MovementFix.mc.player.input.forwardImpulse > 0.0f && !MovementFix.mc.player.isShiftKeyDown()) {
            MovementFix.mc.player.setSprinting(true);
        }
    }

    @EventTarget(a=1)
    public void a(StrafeEvent strafeEvent) {
        if (!this.mode.is("Normal") || !MovementFix.a()) {
            return;
        }
        Scaffold scaffold = Scaffold.INSTANCE;
        if (scaffold != null && scaffold.isEnabled() && scaffold.z() && !((Boolean)scaffold.tellyMovementFix.getValue()).booleanValue()) {
            return;
        }
        float f2 = RotationHandler.a.e();
        if (!Float.isFinite(f2)) {
            return;
        }
        e.a(strafeEvent, f2);
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
                    case 0 -> 80;
                    case 1 -> 11;
                    case 2 -> 91;
                    case 3 -> 14;
                    case 4 -> 113;
                    case 5 -> 72;
                    default -> 79;
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
            cArray[0] = (char)(cArray[0] ^ 0x4F);
        }
        return cArray;
    }

    private static boolean a() {
        return RotationHandler.e && RotationHandler.a != null;
    }
}

