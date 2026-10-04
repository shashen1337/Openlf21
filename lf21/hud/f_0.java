/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.ClientBase;
import lf21.hud.F;
import lf21.hud.v;
import lf21.modules.impl.movement.Scaffold;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.t_0;
import lf21.render.u_0;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.Mth;

/*
 * Renamed from lf21.hud.f
 */
public class f_0
extends ClientBase
implements v {
    private float q;
    private static final String[] t;
    private static final String a;
    private static final int d;
    private float o;
    private static final float m;
    private static final float f;
    private float p;
    private static final u_0 c;
    private String r;
    private static final int e;
    private static final float l;
    private int n = -1;
    private static final float i;
    private static final float h;
    private String s;
    private static final float g;
    private static final u_0 b;
    private static final float j;
    private static final float k;

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
                    case 0 -> 102;
                    case 1 -> 116;
                    case 2 -> 47;
                    case 3 -> 113;
                    case 4 -> 80;
                    case 5 -> 79;
                    default -> 107;
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
        if (f_0.mc.player == null) return;
        if (f6 <= 0.01f) {
            return;
        }
        this.e();
        float f7 = k + this.o;
        float f8 = f2 + (f4 - f7) / 2.0f - 1.0f;
        float f9 = f3 + f5 / 2.0f + 1.0f;
        int n2 = this.a(Color.WHITE.getRGB(), f6);
        int n3 = this.a(d, f6);
        int n4 = this.a(e, f6);
        try (D d2 = new D();){
            String[] stringArray = t;
            this.a(drawContext, d2, "lf21", f8, f9 + 1.0f, b, l, n2, n4, true);
            f8 += f + 12.0f;
            this.a(drawContext, d2, "|", (f8 += 12.0f) - 13.0f, f9, c, m, n3, n4, true);
            float f10 = (f8 += g + 12.0f) + (j - h) / 2.0f - 13.0f;
            float f11 = f8 + (j - i) / 2.0f - 13.0f;
            this.a(drawContext, d2, "beta", f10, f9 - 2.0f, c, 0.0f, n2, n4, false);
            this.a(drawContext, d2, "b1", f11, f9 + 7.0f, c, 0.0f, n3, n4, false);
            f8 += j;
            this.a(drawContext, d2, "|", (f8 += 12.0f) - 13.0f, f9, c, m, n3, n4, true);
            float f12 = (f8 += g + 12.0f) + (this.o - this.p) / 2.0f - 13.0f;
            float f13 = f8 + (this.o - this.q) / 2.0f - 13.0f;
            this.a(drawContext, d2, this.r, f12, f9 - 2.0f, c, 0.0f, n2, n4, false);
            this.a(drawContext, d2, this.s, f13, f9 + 7.0f, c, 0.0f, n3, n4, false);
            return;
        }
    }

    @Override
    public void a(GuiGraphics guiGraphics, PoseStack poseStack, float f2, float f3, float f4, float f5, float f6) {
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public F d() {
        return new F(this.f(), 25.0f);
    }

    private void e() {
        if (mc == null || f_0.mc.player == null || this.n == f_0.mc.player.tickCount) {
            return;
        }
        this.n = f_0.mc.player.tickCount;
        String[] stringArray = this.g();
        this.r = stringArray[0];
        this.s = stringArray[1];
        this.p = c.b(this.r);
        this.q = c.b(this.s);
        this.o = Math.max(this.p, this.q);
    }

    private float f() {
        this.e();
        return k + this.o;
    }

    @Override
    public boolean c() {
        return Scaffold.INSTANCE == null || !Scaffold.INSTANCE.isEnabled();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[10];
                var3_1 = 0;
                var2_2 = "\n\u0012\u001d@\u0002\u000b\u0007\u0004\n\u0012\u001d@\u0004\u0004\u0011[\u0010\u0002\u0004E\u0003W\u0019\\\u0004\u0004\u0011[\u0010\u000b+\u0001C\u00059?\u0007\u0007\rJ\u0003";
                var4_3 = "\n\u0012\u001d@\u0002\u000b\u0007\u0004\n\u0012\u001d@\u0004\u0004\u0011[\u0010\u0002\u0004E\u0003W\u0019\\\u0004\u0004\u0011[\u0010\u000b+\u0001C\u00059?\u0007\u0007\rJ\u0003".length();
                var1_4 = 4;
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
                    var5[var3_1++] = f_0.b(f_0.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0004E\f5\u001dA\u0016<*\u001b\n\u0015V\u0014\"";
                    var4_3 = "\u0004E\f5\u001dA\u0016<*\u001b\n\u0015V\u0014\"".length();
                    var1_4 = 2;
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
                    var5[var3_1++] = f_0.b(f_0.a(v1));
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
        f_0.t = var5;
        f_0.a = f_0.t[0];
        f_0.b = t_0.m(17.0f);
        f_0.c = t_0.f(12.0f);
        f_0.d = new Color(170, 170, 170).getRGB();
        f_0.e = new Color(0, 0, 0, 100).getRGB();
        var0_6 = f_0.t;
        f_0.f = f_0.b.b("lf21");
        f_0.g = f_0.c.b("|");
        f_0.h = f_0.c.b("beta");
        f_0.i = f_0.c.b("b1");
        f_0.j = Math.max(f_0.h, f_0.i);
        f_0.k = f_0.f + f_0.g * 2.0f + f_0.j + 48.0f;
        f_0.l = f_0.b.d().f();
        f_0.m = f_0.c.d().f();
    }

    private void a(DrawContext drawContext, D d2, String string, float f2, float f3, u_0 u_02, float f4, int n2, int n3, boolean bl) {
        float f5 = f3;
        if (bl) {
            f5 = f3 + f4 / 2.0f;
        }
        d2.a(n3);
        drawContext.a(string, f2 + 0.5f, f5 + 0.5f, u_02, d2);
        d2.a(n2);
        drawContext.a(string, f2, f5, u_02, d2);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6B);
        }
        return cArray;
    }

    private String[] g() {
        PlayerInfo playerInfo;
        String string;
        if (mc.isSingleplayer()) {
            String[] stringArray = new String[2];
            String[] stringArray2 = t;
            stringArray[0] = "Singleplayer";
            stringArray[1] = "1ms";
            return stringArray;
        }
        ServerData serverData = mc.getCurrentServer();
        if (serverData != null) {
            string = serverData.ip;
        } else {
            String[] stringArray = t;
            string = "Multiplayer";
        }
        String string2 = string;
        int n2 = 0;
        if (mc.getConnection() != null && f_0.mc.player != null && (playerInfo = mc.getConnection().getPlayerInfo(f_0.mc.player.getUUID())) != null) {
            n2 = playerInfo.getLatency();
        }
        n2 = Mth.clamp((int)n2, (int)0, (int)9999);
        return new String[]{string2, n2 + "ms"};
    }
}

