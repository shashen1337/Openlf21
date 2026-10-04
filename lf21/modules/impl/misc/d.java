/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.misc;

import lf21.event.EventTarget;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.Clip;
import lf21.modules.impl.movement.Fly;
import lf21.modules.impl.movement.Freeze;

public final class d
extends Module {
    private boolean l;
    private static final String n = d.c(d.a("v\u0005{>\"\u0000zq"));
    private int m;
    private boolean k;
    public static d j;

    private boolean a() {
        return d.mc.player != null && d.mc.player.getAbilities() != null && d.mc.player.getAbilities().flying;
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
                    case 0 -> 20;
                    case 1 -> 111;
                    case 2 -> 31;
                    case 3 -> 78;
                    case 4 -> 74;
                    case 5 -> 97;
                    default -> 9;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    protected void onDisable() {
        this.l = false;
        this.m = 0;
    }

    public d() {
        super(n, Category.WORLD);
        j = this;
    }

    @Override
    protected void onEnable() {
        this.k = this.a();
        this.l = false;
        this.m = 0;
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (d.mc.player == null) {
            return;
        }
        boolean bl = this.a();
        if (bl && !this.k) {
            this.l = true;
            this.m = 0;
        }
        if (!bl && this.k) {
            this.l = false;
            this.m = 0;
        }
        if (this.l) {
            ++this.m;
            if (this.m == 2) {
                if (Clip.j != null) {
                    Clip.j.a();
                }
                if (Fly.j != null) {
                    Fly.j.setEnabled(true);
                }
            }
            if (this.m == 3) {
                if (Freeze.j != null) {
                    Freeze.j.setEnabled(true);
                }
                this.l = false;
            }
        }
        this.k = bl;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 9);
        }
        return cArray;
    }
}

