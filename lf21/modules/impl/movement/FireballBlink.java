/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.SprintEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.utils.game.e;
import lf21.utils.misc.ChatUtil;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.a;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

public class FireballBlink
extends Module {
    private int o = -1;
    private long u = 0L;
    private boolean p = false;
    public static FireballBlink j;
    private int v = 0;
    private boolean t = false;
    private boolean s = false;
    private boolean r = false;
    private final List<Integer> z = new ArrayList<Integer>();
    public static a k;
    private int n = 0;
    private int y = 0;
    private boolean l = false;
    private boolean m = false;
    private int x = 0;
    private boolean q = false;
    private int w = 0;
    private static final String[] B;
    private final LinkedBlockingQueue<Packet<?>> A = new LinkedBlockingQueue();

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
                    case 0 -> 72;
                    case 1 -> 18;
                    case 2 -> 14;
                    case 3 -> 113;
                    case 4 -> 4;
                    case 5 -> 122;
                    default -> 119;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public FireballBlink() {
        super("FireballBlink", Category.MOVEMENT);
        j = this;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(int n2) {
        if (n2 >= this.z.size()) {
            return;
        }
        int n3 = this.z.get(n2);
        int n4 = 0;
        while (!this.A.isEmpty() && n4 <= n3) {
            try {
                Packet<?> packet = this.A.poll();
                if (packet != null && mc.getConnection() != null) {
                    FireballBlink.a(packet, (PacketListener)mc.getConnection());
                }
                ++n4;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        int i2 = n2 + 1;
        while (i2 < this.z.size()) {
            this.z.set(i2, this.z.get(i2) - (n3 + 1));
            ++i2;
        }
        return;
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (!this.isEnabled() || FireballBlink.mc.player == null) {
            return;
        }
        if (motionEvent.isPost()) {
            if (this.n > 0) {
                String[] stringArray;
                if (this.n == 1) {
                    float f2;
                    float f3;
                    ++this.v;
                    stringArray = B;
                    ChatUtil.print("Preparing fireball #" + this.v);
                    FireballBlink.mc.options.keyJump.setDown(true);
                    if (!this.l) {
                        f3 = FireballBlink.mc.player.getYRot() - 180.0f;
                        f2 = 88.0f;
                    } else {
                        f3 = FireballBlink.mc.player.getYRot();
                        f2 = 90.0f;
                    }
                    k = new a(f3, f2);
                }
                if (this.n >= 2) {
                    this.n = 0;
                    int n2 = this.j();
                    if (n2 != -1) {
                        FireballBlink.mc.player.getInventory().selected = n2;
                        this.x = this.i();
                        FireballBlink.mc.options.keyUse.setDown(true);
                        this.r = true;
                        stringArray = B;
                        ChatUtil.print("Throwing fireball " + this.v + " (charges=" + this.x + ")");
                    } else {
                        this.setEnabled(false);
                    }
                }
                if (this.n != 0) {
                    ++this.n;
                }
            }
        } else if (this.r) {
            int n3 = this.i();
            if (n3 < this.x) {
                FireballBlink.mc.options.keyUse.setDown(false);
                FireballBlink.mc.options.keyJump.setDown(false);
                k = null;
                this.r = false;
                int n4 = n3;
                int n5 = this.x;
                int n6 = this.v;
                String[] stringArray = B;
                ChatUtil.print("Used fireball " + n6 + " (had " + n5 + ", now " + n4 + ")");
            } else if (this.h() == -1) {
                FireballBlink.mc.options.keyUse.setDown(false);
                FireballBlink.mc.options.keyJump.setDown(false);
                k = null;
                this.r = false;
                ChatUtil.print("Out of fire charges");
            }
        }
    }

    private int i() {
        if (FireballBlink.mc.player == null) {
            return 0;
        }
        int n2 = 0;
        for (int i2 = 0; i2 < 36; ++i2) {
            ItemStack itemStack = FireballBlink.mc.player.getInventory().getItem(i2);
            if (itemStack.getItem() != Items.FIRE_CHARGE) continue;
            n2 += itemStack.getCount();
        }
        return n2;
    }

    private static void a(Packet packet, PacketListener packetListener) {
        packet.handle(packetListener);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[21];
                var3_1 = 0;
                var2_2 = "\u0001\u007f~\u0004h\t\u0012h\u0014\u0006}.\u0017m\b\u0012hqf\u0010v\u001d\u0012hta\u0004j\u001e\u0014\u0018`k\u0001e\b\u001e&u.\u0017m\b\u0012*sb\u001d$Y\u0013\u0006}.\u0013h\u0013\u0019#2z\u001e$\b\u0012$wo\u0002a\"\n~g\u001fo\u0013\u0019/(.T`Z\u0007)qe\u0014p\t[h7 @b\t[h7j^!\u001e\u0006h:f\u0010`Z\u0006d2`\u001esZ\nh:m\u0019e\b\u0010-a3\u0015\t~bQm\u0017\u0007=~}\u0014wZ\u0005-~k\u0010w\u001f\u0013\r\n~g\u001foZ\u0004<s|\u0005a\u001e\u000e\u001dak\u0015$\u001c\u001e:wl\u0010h\u0016W\u0013\u000e{|\u0014f\u001b\u001b$Pb\u0018j\u0011W:wo\u0015}\r\u000e{|\u0014f\u001b\u001b$Pb\u0018j\u0011\u0011\u000e{|\u0014f\u001b\u001b$2g\u001ct\u000f\u001b;w.\b\u001awb\u0014e\t\u0012h\u000e\n~g\u001foZ\u0011!|g\u0002l\u001f\u0013\u0007hc{\u0014q\u001f\u0013\u0013\u0007gzQk\u001cW.{|\u0014$\u0019\u001f)`i\u0014w\u0012\u001cz|\u001es\u0013\u0019/2h\u0018v\u001f\u0015)~bQ";
                var4_3 = "\u0001\u007f~\u0004h\t\u0012h\u0014\u0006}.\u0017m\b\u0012hqf\u0010v\u001d\u0012hta\u0004j\u001e\u0014\u0018`k\u0001e\b\u001e&u.\u0017m\b\u0012*sb\u001d$Y\u0013\u0006}.\u0013h\u0013\u0019#2z\u001e$\b\u0012$wo\u0002a\"\n~g\u001fo\u0013\u0019/(.T`Z\u0007)qe\u0014p\t[h7 @b\t[h7j^!\u001e\u0006h:f\u0010`Z\u0006d2`\u001esZ\nh:m\u0019e\b\u0010-a3\u0015\t~bQm\u0017\u0007=~}\u0014wZ\u0005-~k\u0010w\u001f\u0013\r\n~g\u001foZ\u0004<s|\u0005a\u001e\u000e\u001dak\u0015$\u001c\u001e:wl\u0010h\u0016W\u0013\u000e{|\u0014f\u001b\u001b$Pb\u0018j\u0011W:wo\u0015}\r\u000e{|\u0014f\u001b\u001b$Pb\u0018j\u0011\u0011\u000e{|\u0014f\u001b\u001b$2g\u001ct\u000f\u001b;w.\b\u001awb\u0014e\t\u0012h\u000e\n~g\u001foZ\u0011!|g\u0002l\u001f\u0013\u0007hc{\u0014q\u001f\u0013\u0013\u0007gzQk\u001cW.{|\u0014$\u0019\u001f)`i\u0014w\u0012\u001cz|\u001es\u0013\u0019/2h\u0018v\u001f\u0015)~bQ".length();
                var1_4 = 8;
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
                    var5[var3_1++] = FireballBlink.c(FireballBlink.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u001cz|\u001es\u0013\u0019/2h\u0018v\u001f\u0015)~bQ|\u0010\u0019gk\u0004a\u001eW.{|\u0014f\u001b\u001b$2";
                    var4_3 = "\u001cz|\u001es\u0013\u0019/2h\u0018v\u001f\u0015)~bQ|\u0010\u0019gk\u0004a\u001eW.{|\u0014f\u001b\u001b$2".length();
                    var1_4 = 19;
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
                    var5[var3_1++] = FireballBlink.c(FireballBlink.a(v1));
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
        FireballBlink.B = var5;
    }

    private int h() {
        if (FireballBlink.mc.player == null) {
            return -1;
        }
        for (int i2 = 0; i2 < 36; ++i2) {
            ItemStack itemStack = FireballBlink.mc.player.getInventory().getItem(i2);
            if (itemStack.isEmpty() || itemStack.getItem() != Items.FIRE_CHARGE) continue;
            return i2;
        }
        return -1;
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        if (!this.isEnabled() || FireballBlink.mc.level == null || FireballBlink.mc.player == null) {
            if (this.p) {
                mc.execute(() -> {
                    this.a();
                    this.p = false;
                });
            }
            return;
        }
        if (this.p && packetEvent.a()) {
            ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket;
            Packet<?> packet = packetEvent.c();
            if (packet instanceof ClientboundPlayerPositionPacket) {
                this.q = true;
                packetEvent.a(true);
                return;
            }
            if (packet instanceof ClientboundSetEntityMotionPacket && (clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet).getId() == FireballBlink.mc.player.getId()) {
                ++this.w;
                this.z.add(this.A.size());
                mc.execute(() -> {
                    String[] stringArray = B;
                    ChatUtil.print("Impulse " + this.w + " queued");
                });
            }
            packetEvent.a(true);
            this.A.add(packet);
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (packet instanceof ClientboundSetEntityMotionPacket) {
            ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet;
            if (packetEvent.a() && clientboundSetEntityMotionPacket.getId() == FireballBlink.mc.player.getId() && this.v > 0 && !this.p) {
                ++this.w;
                this.z.add(this.A.size());
                mc.execute(() -> ChatUtil.print("Fireball impulse " + this.w));
                packetEvent.a(true);
                this.A.add(packetEvent.c());
                this.p = true;
                this.u = System.currentTimeMillis();
                mc.execute(() -> ChatUtil.print("Blink started"));
            }
        }
    }

    @Override
    protected void onDisable() {
        this.p = false;
        this.a();
        this.z.clear();
        this.A.clear();
        k = null;
        super.onDisable();
    }

    @Override
    protected void onEnable() {
        super.onEnable();
        this.s = false;
        this.w = 0;
        this.v = 0;
        this.y = 0;
        this.z.clear();
        this.A.clear();
        this.l = false;
        this.m = false;
        this.n = 0;
        this.o = -1;
        this.p = false;
        this.q = false;
        this.r = false;
        this.t = false;
        this.u = 0L;
        this.x = 0;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x77);
        }
        return cArray;
    }

    private int j() {
        int n2 = this.h();
        if (n2 == -1) {
            ChatUtil.print("No fire charge found");
            this.setEnabled(false);
        }
        return n2;
    }

    private void a() {
        while (!this.A.isEmpty()) {
            try {
                Packet<?> packet = this.A.poll();
                if (packet == null || mc.getConnection() == null) {
                    continue;
                }
                FireballBlink.a(packet, (PacketListener)mc.getConnection());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @EventTarget
    public void a(SprintEvent sprintEvent) {
        String[] stringArray;
        int n2;
        boolean bl;
        if (!this.isEnabled() || FireballBlink.mc.player == null) {
            return;
        }
        if (this.q) {
            this.setEnabled(false);
            return;
        }
        if (this.m) {
            if (!e.g()) {
                this.l = true;
            }
            this.m = false;
        }
        boolean bl2 = bl = GLFW.glfwGetMouseButton((long)mc.getWindow().getWindow(), (int)3) == 1;
        if (bl && !this.s) {
            this.s = true;
            if (!this.r && this.n == 0 && (n2 = this.j()) != -1) {
                this.o = FireballBlink.mc.player.getInventory().selected;
                FireballBlink.mc.player.getInventory().selected = n2;
                this.n = 1;
                stringArray = B;
                ChatUtil.print("Queued fireball " + (this.v + 1));
            }
        } else if (!bl) {
            this.s = false;
        }
        int n3 = n2 = GLFW.glfwGetMouseButton((long)mc.getWindow().getWindow(), (int)4) == 1 ? 1 : 0;
        if (n2 != 0 && !this.t) {
            this.t = true;
            if (this.p && this.y < this.w) {
                stringArray = B;
                ChatUtil.print("Release " + (this.y + 1) + "/" + this.w);
                this.a(this.y);
                ++this.y;
                if (this.y >= this.w) {
                    ChatUtil.print("Blink finished");
                    this.p = false;
                    this.setEnabled(false);
                }
            } else if (!this.p) {
                stringArray = B;
                ChatUtil.print("No blink to release");
                this.setEnabled(false);
            } else {
                ChatUtil.print("All impulses released");
            }
        } else if (n2 == 0) {
            this.t = false;
        }
    }

    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        Object object;
        if (!this.isEnabled()) {
            return;
        }
        int n2 = mc.getWindow().getGuiScaledWidth();
        int n3 = mc.getWindow().getGuiScaledHeight();
        if (this.p) {
            int n4 = this.A.size();
            long l2 = System.currentTimeMillis() - this.u;
            String[] stringArray = B;
            object = String.format("Blinking: %d packets, %.1fs, %d/%d", n4, Float.valueOf((float)l2 / 1000.0f), this.y, this.w);
        } else if (this.r) {
            String[] stringArray = B;
            object = "Throwing fireball x" + this.v;
        } else {
            object = "FireballBlink ready";
        }
        float f2 = (float)n2 / 2.0f - (float)FireballBlink.mc.font.width((String)object) / 2.0f;
        float f3 = (float)n3 / 2.0f + 20.0f;
        render2DEvent.b().drawString(FireballBlink.mc.font, (String)object, (int)f2, (int)f3, -1);
        if (this.p) {
            float f4 = 180.0f;
            float f5 = 6.0f;
            float f6 = (float)n2 / 2.0f - f4 / 2.0f;
            float f7 = (float)n3 / 2.0f + 35.0f;
            long l3 = System.currentTimeMillis() - this.u;
            float f8 = Math.min(1.0f, (float)l3 / 10000.0f);
            RenderUtil.b(render2DEvent.a(), f6, f7, f4, f5, 2.0f, new Color(0, 0, 0, 128).getRGB());
            if (f8 > 0.0f) {
                Color color = new Color(0, 200, 0, 200);
                RenderUtil.b(render2DEvent.a(), f6, f7, f4 * f8, f5, 2.0f, color.getRGB());
            }
        }
    }
}

