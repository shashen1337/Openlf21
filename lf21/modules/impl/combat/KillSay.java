/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import lf21.event.EventTarget;
import lf21.event.impl.AttackEntityEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public final class KillSay
extends Module {
    private static final String j;
    private LivingEntity k;
    private int m;
    private int l;
    private static final String[] o;
    private String n;

    private void a() {
        if (KillSay.mc.player != null && KillSay.mc.player.connection != null) {
            String[] stringArray = o;
            this.n = "\u4f60\u88ablf21shit\u51fb\u8d25\u4e86,\u771f\u662f\u6742\u9c7c\u5462";
            KillSay.mc.player.connection.sendChat("\u4f60\u88ablf21shit\u51fb\u8d25\u4e86,\u771f\u662f\u6742\u9c7c\u5462");
        }
    }

    public KillSay() {
        super("KillSay", Category.COMBAT);
    }

    @Override
    protected void onEnable() {
        this.k = null;
        this.m = 0;
    }

    @Override
    protected void onDisable() {
        this.k = null;
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
                    case 0 -> 126;
                    case 1 -> 85;
                    case 2 -> 123;
                    case 3 -> 83;
                    case 4 -> 62;
                    case 5 -> 63;
                    default -> 67;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "\u4f1e\u88fe\u00175\f\u000e0\u0016<\u000f\u51a8\u8d1b\u4eb9o\u7761\u667a\u6739\u9c2f\u545c\u00075<\u0017?m^:\u0013\u4f1e\u88fe\u00175\f\u000e0\u0016<\u000f\u51a8\u8d1b\u4eb9o\u7761\u667a\u6739\u9c2f\u545c";
        int n3 = "\u4f1e\u88fe\u00175\f\u000e0\u0016<\u000f\u51a8\u8d1b\u4eb9o\u7761\u667a\u6739\u9c2f\u545c\u00075<\u0017?m^:\u0013\u4f1e\u88fe\u00175\f\u000e0\u0016<\u000f\u51a8\u8d1b\u4eb9o\u7761\u667a\u6739\u9c2f\u545c".length();
        int n4 = 19;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = KillSay.c(KillSay.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                o = stringArray;
                j = o[0];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        ++this.m;
        if (KillSay.mc.player == null || KillSay.mc.level == null) {
            this.k = null;
            return;
        }
        LivingEntity livingEntity = this.k;
        if (livingEntity == null) {
            return;
        }
        if (!livingEntity.isAlive() || livingEntity.getHealth() <= 0.0f) {
            this.a();
            this.k = null;
            return;
        }
        if (this.m - this.l > 200) {
            this.k = null;
        }
    }

    @EventTarget
    public void a(AttackEntityEvent attackEntityEvent) {
        LivingEntity livingEntity;
        Entity entity = attackEntityEvent.a();
        if (!(entity instanceof LivingEntity) || (livingEntity = (LivingEntity)entity) == KillSay.mc.player || !livingEntity.isAlive()) {
            return;
        }
        this.k = livingEntity;
        this.l = this.m;
    }

    @Override
    public void setEnabled(boolean bl) {
        super.setEnabled(true);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x43);
        }
        return cArray;
    }

    @Override
    public void toggle() {
    }
}

