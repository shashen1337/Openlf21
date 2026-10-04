/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import java.text.SimpleDateFormat;
import java.util.Date;
import lf21.ClientBase;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.render.a;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.multiplayer.ServerData;

public class A {
    private final u_0 d;
    private static final String[] j;
    private final D g;
    private final D h;
    private final u_0 c;
    private final float f = 5.0f;
    private final u_0 b;
    private final SimpleDateFormat e;
    private final D i;
    private final u_0 a = t_0.i(18.0f);

    private float a(DrawContext drawContext, float f2, float f3, String string, u_0 u_02, String string2, float f4, float f5) {
        float f6 = 8.0f;
        float f7 = (float)y_0.a(u_02) + 12.0f;
        float f8 = y_0.a(string, u_02);
        float f9 = y_0.a(string2, this.d);
        float f10 = f6 + f9 + 5.0f + f8 + f6 - 4.0f;
        y_0.a(f2, f3, f10, f7, f4, this.g);
        float f11 = f3 + (f7 - (float)y_0.a(this.d)) / 2.0f + 3.0f;
        y_0.b(string2, f2 + f6 - 1.0f, f11, this.d, this.i.a());
        float f12 = f3 + (f7 - (float)y_0.a(u_02)) / 2.0f + 1.0f;
        y_0.b(string, f2 + f6 + f9 + 5.0f - 3.0f, f12, u_02, this.h.a());
        return f2 + f10 + f5;
    }

    private String d() {
        return "Default Config";
    }

    private float a(String string, u_0 u_02) {
        float f2 = 8.0f;
        return f2 + y_0.a(string, u_02) + f2 - 5.0f;
    }

    public A() {
        this.b = t_0.b(13.0f);
        this.c = t_0.b(13.0f);
        this.d = t_0.j(16.0f);
        this.e = new SimpleDateFormat("HH:mm");
        this.g = new D().a(lf21.utils.render.a.a(36, 36, 36, 120));
        this.h = new D().a(-1);
        this.i = new D().a(lf21.utils.render.a.a(42, 180, 255));
    }

    private String e() {
        if (ClientBase.mc.player == null || ClientBase.mc.player.connection == null) {
            String[] stringArray = j;
            return "0ms";
        }
        PlayerInfo playerInfo = ClientBase.mc.player.connection.getPlayerInfo(ClientBase.mc.player.getUUID());
        if (playerInfo == null) {
            String[] stringArray = j;
            return "0ms";
        }
        return playerInfo.getLatency() + "ms";
    }

    private String c() {
        return ClientBase.mc.player != null ? ClientBase.mc.player.getGameProfile().getName() : "Player";
    }

    private float a(String string, u_0 u_02, String string2) {
        float f2 = 8.0f;
        return f2 + y_0.a(string2, this.d) + 5.0f + y_0.a(string, u_02) + f2 - 4.0f;
    }

    public float b() {
        return (float)y_0.a(this.a) + 10.0f;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[10];
                var3_1 = 0;
                var2_2 = "#F\u00125\u0003)PS\u0006\u001fLA}Va\u0004#F\u00125\u0003\u007fMS\u0005\u0007h\u001ai^\f\u001cINc_vv#AYaA\u0002\"S";
                var4_3 = "#F\u00125\u0003)PS\u0006\u001fLA}Va\u0004#F\u00125\u0003\u007fMS\u0005\u0007h\u001ai^\f\u001cINc_vv#AYaA\u0002\"S".length();
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
                    var5[var3_1++] = A.a(A.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u000bEFeF\u007frocOjUza\u0003\u007fMS";
                    var4_3 = "\u000bEFeF\u007frocOjUza\u0003\u007fMS".length();
                    var1_4 = 14;
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
                    var5[var3_1++] = A.a(A.a(v1));
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
        A.j = var5;
    }

    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        if (ClientBase.mc.options.renderDebug) {
            return;
        }
        float f4 = f2;
        float f5 = f3;
        float f6 = 6.0f;
        float f7 = 4.5f;
        float f8 = 15.0f;
        float f9 = this.a("lf21", this.a);
        f4 += f9 + f6;
        f4 = this.a(render2DEvent.a(), f4, f5, this.c(), this.c, "\ue8a6", f7, f8, f6);
        f4 = this.a(render2DEvent.a(), f4, f5, this.d(), this.b, "\ue8b8", f7, f8, f6);
        f4 = this.a(render2DEvent.a(), f4, f5, this.e(), this.b, "\uebca", f7, f8, f6);
        f4 = this.a(render2DEvent.a(), f4, f5, this.f(), this.b, "\ueb66", f7, f8, f6);
        f4 = this.a(render2DEvent.a(), f4, f5, this.g(), this.b, "\ue0c8", f7, f8, f6);
        this.a(render2DEvent.a(), f4, f5, this.h(), this.b, "\ue192", f7, f8, f6);
    }

    private float a(DrawContext drawContext, float f2, float f3, String string, u_0 u_02, float f4, float f5) {
        float f6 = 8.0f;
        float f7 = (float)y_0.a(this.a) + 12.0f - 2.0f;
        float f8 = y_0.a(string, u_02);
        float f9 = f6 + f8 + f6 - 5.0f;
        y_0.a(f2, f3, f9, f7, f4, this.g);
        float f10 = f3 + (f7 - (float)y_0.a(u_02)) / 2.0f + 3.5f;
        y_0.b(string, f2 + f6 - 2.0f, f10, u_02, this.h.a());
        return f2 + f9 + f5;
    }

    private String h() {
        return this.e.format(new Date());
    }

    private float a(PoseStack poseStack, float f2, float f3, String string, u_0 u_02, String string2, float f4, float f5, float f6) {
        float f7 = this.a(string, u_02, string2);
        return f2 + f7 + f6;
    }

    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
        if (ClientBase.mc.options.renderDebug) {
            return;
        }
        float f4 = f2;
        float f5 = f3;
        float f6 = 6.0f;
        float f7 = 4.5f;
        f4 = this.a(glRenderEvent.c(), f4, f5, "lf21", this.a, f7, f6);
        f4 = this.a(glRenderEvent.c(), f4, f5, this.c(), this.c, "\ue8a6", f7, f6);
        f4 = this.a(glRenderEvent.c(), f4, f5, this.d(), this.b, "\ue8b8", f7, f6);
        f4 = this.a(glRenderEvent.c(), f4, f5, this.e(), this.b, "\uebca", f7, f6);
        f4 = this.a(glRenderEvent.c(), f4, f5, this.f(), this.b, "\ueb66", f7, f6);
        f4 = this.a(glRenderEvent.c(), f4, f5, this.g(), this.b, "\ue0c8", f7, f6);
        this.a(glRenderEvent.c(), f4, f5, this.h(), this.b, "\ue192", f7, f6);
    }

    private String g() {
        ServerData serverData = ClientBase.mc.getCurrentServer();
        return serverData != null ? serverData.ip : "Singleplayer";
    }

    public float a() {
        float f2 = 0.0f;
        float f3 = 6.0f;
        f2 += this.a("lf21", this.a) + f3;
        f2 += this.a(this.c(), this.c, "") + f3;
        f2 += this.a(this.d(), this.b, "") + f3;
        f2 += this.a(this.e(), this.b, "") + f3;
        f2 += this.a(this.f(), this.b, "") + f3;
        f2 += this.a(this.g(), this.b, "") + f3;
        return f2 += this.a(this.h(), this.b, "");
    }

    private String f() {
        return ClientBase.mc.getFps() + "fps";
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
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
                    case 0 -> 79;
                    case 1 -> 32;
                    case 2 -> 32;
                    case 3 -> 4;
                    case 4 -> 51;
                    case 5 -> 19;
                    default -> 6;
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
            cArray[0] = (char)(cArray[0] ^ 6);
        }
        return cArray;
    }
}

