/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.ClientBase;
import lf21.hud.F;
import lf21.hud.b_0;
import lf21.hud.v;
import lf21.modules.impl.movement.Scaffold;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.utils.a.f;
import lf21.utils.game.e;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

/*
 * Renamed from lf21.hud.e
 */
public class e_0
extends ClientBase
implements v {
    private final f c = new f(250.0f, 1.0f, 22.0f, 0.0f);
    private static final String[] e;
    private long d = 0L;
    private static final u_0 b;
    private static final u_0 a;

    @Override
    public b_0 a() {
        return b_0.CENTER;
    }

    private void a(float f2) {
        long l2 = System.currentTimeMillis();
        if (this.d == 0L || l2 - this.d > 1000L) {
            this.d = l2;
            this.c.c(f2);
            this.c.d(f2);
            return;
        }
        float f3 = (float)(l2 - this.d) / 1000.0f;
        if (f3 <= 0.0f) {
            return;
        }
        this.d = l2;
        this.c.d(f2);
        this.c.b(f3);
    }

    private ItemStack e() {
        if (e_0.mc.player == null) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = e_0.mc.player.getMainHandItem();
        if (itemStack.getItem() instanceof BlockItem) {
            return itemStack;
        }
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack2 = e_0.mc.player.getInventory().getItem(i2);
            if (!(itemStack2.getItem() instanceof BlockItem)) continue;
            return itemStack2;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public boolean c() {
        return Scaffold.INSTANCE != null && Scaffold.INSTANCE.isEnabled();
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
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
                    case 0 -> 52;
                    case 1 -> 62;
                    case 2 -> 45;
                    case 3 -> 45;
                    case 4 -> 66;
                    case 5 -> 37;
                    default -> 93;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(DrawContext drawContext, float f2, float f3, float f4, float f5, float f6) {
        if (mc == null) return;
        if (e_0.mc.player == null) return;
        if (f6 <= 0.01f) {
            return;
        }
        ItemStack itemStack = this.e();
        if (itemStack.isEmpty()) {
            return;
        }
        float f7 = f5 - 16.0f;
        int n3 = Scaffold.INSTANCE != null ? Scaffold.INSTANCE.I() : itemStack.getCount();
        int n5 = Scaffold.INSTANCE != null ? Scaffold.INSTANCE.R() : itemStack.getCount();
        String[] stringArray = e;
        String string = n5 + "/" + n3 + "blocks";
        double d2 = lf21.utils.game.e.h();
        String string2 = String.format("%.2fb/s", d2);
        float f8 = a.b(string);
        float f9 = b.b(string2);
        float f10 = Math.max(f8, f9);
        float f11 = f4 - f7 - f10 - 32.0f;
        float f12 = 6.0f;
        float f13 = f2 + 8.0f + f7 + 8.0f;
        float f14 = f3 + f5 / 2.0f - f12 / 2.0f;
        float f15 = Math.min(1.0f, (float)n3 / 64.0f);
        this.a(f15);
        try (D d3 = new D();){
            d3.a(this.a(new Color(30, 30, 30).getRGB(), f6));
            drawContext.a(Q.a(f13, f14, f11, f12, f12 / 2.0f), d3);
            if (this.c.a() > 0.0f) {
                d3.a(this.a(new Color(153, 0, 255).getRGB(), f6));
                drawContext.a(Q.a(f13, f14, f11 * this.c.a(), f12, f12 / 2.0f), d3);
            }
        }
        float f16 = f13 + f11 + 8.0f;
        float f17 = f3 + f5 / 2.0f;
        float f18 = f16 + (f10 - f8) / 2.0f;
        float f19 = f16 + (f10 - f9) / 2.0f;
        try (D d4 = new D();){
            d4.a(this.a(Color.WHITE.getRGB(), f6));
            drawContext.a(string, f18, f17 - a.d().f() / 2.0f + 2.0f, a, d4);
            d4.a(this.a(Color.GRAY.getRGB(), f6));
            drawContext.a(string2, f19, f17 + b.d().f() / 2.0f + 8.0f, b, d4);
            return;
        }
    }

    @Override
    public F d() {
        return new F(260.0f, 30.0f);
    }

    @Override
    public void a(GuiGraphics guiGraphics, PoseStack poseStack, float f2, float f3, float f4, float f5, float f6) {
        if (mc == null || e_0.mc.player == null || f6 <= 0.01f) {
            return;
        }
        ItemStack itemStack = this.e();
        if (itemStack.isEmpty()) {
            return;
        }
        float f7 = f5 - 16.0f;
        float f8 = f2 + 8.0f;
        float f9 = f3 + 8.0f;
        if (f6 > 0.1f && f7 - 4.0f > 0.0f) {
            guiGraphics.renderItem(itemStack, (int)f8 + 2, (int)f9 + 2, 0, (int)f7 - 4);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "VRBN)V\u0007\u0011\u0010\u001fK \n.";
        int n3 = "VRBN)V\u0007\u0011\u0010\u001fK \n.".length();
        int n4 = 6;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = e_0.b(e_0.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                e = stringArray;
                a = t_0.g(14.0f);
                b = t_0.f(10.0f);
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x5D);
        }
        return cArray;
    }
}

