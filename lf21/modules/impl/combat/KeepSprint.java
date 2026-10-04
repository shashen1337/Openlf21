/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public final class KeepSprint
extends Module {
    public final BooleanSetting normalHitSprint;
    public static KeepSprint j;
    public final NumberSetting hitSlowDownNormal;
    public final BooleanSetting velocityHitSprint;
    private boolean v;
    public final BooleanSetting onlyInAir;
    public final NumberSetting bufferDecrease;
    public final BooleanSetting bufferAbuse;
    private static final String[] w;
    private Vec3 s;
    private double u;
    public final NumberSetting maxBuffer;
    public final NumberSetting hitSlowDownDuringVelocity;
    private boolean t;

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
                    case 0 -> 122;
                    case 1 -> 38;
                    case 2 -> 27;
                    case 3 -> 68;
                    case 4 -> 79;
                    case 5 -> 81;
                    default -> 111;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[9];
                var3_1 = 0;
                var2_2 = "8S}\"*#O>Cx6*0\u001c\u001f\n7Gcd\r$\t\u001cCi\u000b5Hw=o\u0018\u0001Zgr6\u0013,Cw+,8\u001b\u0003\u0006S-;q<\nTr*;\u001d2Ood\u001c=\u0000\r\u0006_+8?O>Si-!6O,Cw+,8\u001b\u0003\u00114Ii).=O2Ood\u001c!\u001d\u0013Ho\n1C~4\u001c!\u001d\u0013Ho";
                var4_3 = "8S}\"*#O>Cx6*0\u001c\u001f\n7Gcd\r$\t\u001cCi\u000b5Hw=o\u0018\u0001Zgr6\u0013,Cw+,8\u001b\u0003\u0006S-;q<\nTr*;\u001d2Ood\u001c=\u0000\r\u0006_+8?O>Si-!6O,Cw+,8\u001b\u0003\u00114Ii).=O2Ood\u001c!\u001d\u0013Ho\n1C~4\u001c!\u001d\u0013Ho".length();
                var1_4 = 15;
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
                    var5[var3_1++] = KeepSprint.c(KeepSprint.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "8S}\"*#O;Dn7*\u00142Ood\u001c=\u0000\r\u0006_+8?O4Ii).=";
                    var4_3 = "8S}\"*#O;Dn7*\u00142Ood\u001c=\u0000\r\u0006_+8?O4Ii).=".length();
                    var1_4 = 12;
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
                    var5[var3_1++] = KeepSprint.c(KeepSprint.a(v1));
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
        KeepSprint.w = var5;
    }

    public static void afterAttack(Player player) {
        double d2;
        KeepSprint keepSprint = j;
        if (keepSprint == null || !keepSprint.isEnabled() || player == null || player != KeepSprint.mc.player) {
            return;
        }
        if (((Boolean)keepSprint.onlyInAir.getValue()).booleanValue() && player.onGround()) {
            return;
        }
        boolean bl = player.hurtTime > 0;
        double d3 = d2 = bl ? ((Number)keepSprint.hitSlowDownDuringVelocity.getValue()).doubleValue() : ((Number)keepSprint.hitSlowDownNormal.getValue()).doubleValue();
        if (((Boolean)keepSprint.bufferAbuse.getValue()).booleanValue()) {
            if (keepSprint.u < ((Number)keepSprint.maxBuffer.getValue()).doubleValue() && !keepSprint.v) {
                keepSprint.u += 1.0;
            } else {
                if (keepSprint.u > 0.0) {
                    keepSprint.u = Math.max(0.0, keepSprint.u - ((Number)keepSprint.bufferDecrease.getValue()).doubleValue());
                    keepSprint.v = true;
                    return;
                }
                keepSprint.v = false;
            }
        } else {
            keepSprint.u = 0.0;
            keepSprint.v = false;
        }
        Vec3 vec3 = player.getDeltaMovement();
        player.setDeltaMovement(keepSprint.s.x * d2, vec3.y, keepSprint.s.z * d2);
        boolean bl2 = bl ? (Boolean)keepSprint.velocityHitSprint.getValue() : (Boolean)keepSprint.normalHitSprint.getValue();
        if (bl2 && keepSprint.t) {
            player.setSprinting(true);
        }
    }

    public KeepSprint() {
        String[] stringArray = w;
        super("KeepSprint", Category.COMBAT);
        this.hitSlowDownDuringVelocity = new NumberSetting("Hit Slow Down During Velocity", 0.6, 0.0, 1.0, 0.05);
        this.hitSlowDownNormal = new NumberSetting("Hit Slow Down Normal", 0.6, 0.0, 1.0, 0.05);
        this.velocityHitSprint = new BooleanSetting("Velocity Hit Sprint", false);
        this.normalHitSprint = new BooleanSetting("Normal Hit Sprint", true);
        this.bufferAbuse = new BooleanSetting("Buffer Abuse", false);
        this.bufferDecrease = new NumberSetting("Buffer Decrease", 1.0, 0.1, 10.0, 0.1, () -> (Boolean)this.bufferAbuse.getValue());
        this.maxBuffer = new NumberSetting("Max Buffer", 5.0, 1.0, 10.0, 1.0, () -> (Boolean)this.bufferAbuse.getValue());
        this.onlyInAir = new BooleanSetting("Only In Air", false);
        this.s = Vec3.ZERO;
        j = this;
    }

    public static void beforeAttack(Player player) {
        KeepSprint keepSprint = j;
        if (keepSprint == null || !keepSprint.isEnabled() || player == null || player != KeepSprint.mc.player) {
            return;
        }
        keepSprint.s = player.getDeltaMovement();
        keepSprint.t = player.isSprinting();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6F);
        }
        return cArray;
    }
}

