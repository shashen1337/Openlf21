/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.c;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.Camera;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class Teleport
extends Module {
    public final NumberSetting packetStep;
    private static final String[] s;
    private Vec3 p;
    private Vec3 q;
    public final NumberSetting distance;
    public final BooleanSetting disableOnCorrection;
    public final ModeSetting mode;
    public final BooleanSetting renderTarget;
    private boolean r;
    public static Teleport j;

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (!motionEvent.isPre() || !this.mode.is("Watchdog BedWars") || this.q == null) {
            return;
        }
        motionEvent.setX(this.q.x);
        motionEvent.setY(this.q.y);
        motionEvent.setZ(this.q.z);
    }

    public Teleport() {
        String[] stringArray = s;
        super("Teleport", Category.MOVEMENT);
        this.mode = new ModeSetting("Mode", "MiniBlox", "Watchdog BedWars").withDefault("MiniBlox");
        this.distance = new NumberSetting("Distance", 8.0, 1.0, 64.0, 0.5, () -> this.mode.is("MiniBlox"));
        this.packetStep = new NumberSetting("Packet Step", 3.0, 0.5, 8.0, 0.25, () -> this.mode.is("MiniBlox"));
        this.renderTarget = new BooleanSetting("Render Target", true, () -> this.mode.is("MiniBlox"));
        this.disableOnCorrection = new BooleanSetting("Disable On Correction", true);
        j = this;
    }

    @Override
    public void onDisable() {
        if (Teleport.mc.player != null && this.mode.is("Watchdog BedWars") && this.q != null) {
            this.a(this.q, Teleport.mc.player.position());
        }
        this.p = null;
        this.q = null;
        this.r = false;
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (Teleport.mc.player == null || Teleport.mc.level == null || !this.mode.is("MiniBlox")) {
            return;
        }
        double d2 = ((Number)this.distance.getValue()).doubleValue();
        Vec3 vec3 = Teleport.mc.player.getLookAngle();
        Vec3 vec32 = Teleport.mc.player.position();
        this.p = vec32.add(vec3.x * d2, vec3.y * d2, vec3.z * d2);
        boolean bl = Teleport.mc.options.keyShift.isDown();
        if (bl && !this.r && this.p != null) {
            this.a(vec32, this.p);
            Teleport.mc.player.setPos(this.p.x, this.p.y, this.p.z);
        }
        this.r = bl;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x1C);
        }
        return cArray;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (!this.mode.is("MiniBlox") || !((Boolean)this.renderTarget.getValue()).booleanValue() || this.p == null || Teleport.mc.player == null || Teleport.mc.gameRenderer == null) {
            return;
        }
        PoseStack poseStack = renderEvent.a();
        Camera camera = Teleport.mc.gameRenderer.getMainCamera();
        Vec3 vec3 = camera.getPosition();
        AABB aABB = Teleport.mc.player.getBoundingBox().move(this.p.subtract(Teleport.mc.player.position())).inflate(0.08);
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        RenderUtil.b(aABB, poseStack, new Color(65, 215, 255, 75), new Color(65, 215, 255, 185));
        poseStack.popPose();
    }

    @EventTarget
    public void a(c c2) {
        if (c2.a() instanceof ClientboundPlayerPositionPacket && ((Boolean)this.disableOnCorrection.getValue()).booleanValue()) {
            this.setEnabled(false);
        }
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
                    case 0 -> 70;
                    case 1 -> 42;
                    case 2 -> 16;
                    case 3 -> 70;
                    case 4 -> 45;
                    case 5 -> 77;
                    default -> 28;
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
                var5 = new String[12];
                var3_1 = 0;
                var2_2 = "\u000bC~/o!s>\u0015\u0002Cc'O!yfe~fn\"n4Os2D\"r\u000b\u0016Ks-H9<\u0015^u6\u001f\u0012O|#]\"n2\u00100.B!xfY~#L&<2E0%B#z/X}\b\u0002Cc2L#\u007f#-\u0012O|#]\"n2\u00100+B;yfKv2H?<4Oc6L:rj\nd.H#<2Ew!A(<)Lv\b\u0012O|#]\"n2\b\u000bC~/o!s>\u0010\u0011Kd%E)s!\nR#I\u001a}4Y\r\u0014O~\"H?<\u0012Kb!H9";
                var4_3 = "\u000bC~/o!s>\u0015\u0002Cc'O!yfe~fn\"n4Os2D\"r\u000b\u0016Ks-H9<\u0015^u6\u001f\u0012O|#]\"n2\u00100.B!xfY~#L&<2E0%B#z/X}\b\u0002Cc2L#\u007f#-\u0012O|#]\"n2\u00100+B;yfKv2H?<4Oc6L:rj\nd.H#<2Ew!A(<)Lv\b\u0012O|#]\"n2\b\u000bC~/o!s>\u0010\u0011Kd%E)s!\nR#I\u001a}4Y\r\u0014O~\"H?<\u0012Kb!H9".length();
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
                    var5[var3_1++] = Teleport.c(Teleport.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u000bEt#\u0010\u0011Kd%E)s!\nR#I\u001a}4Y";
                    var4_3 = "\u000bEt#\u0010\u0011Kd%E)s!\nR#I\u001a}4Y".length();
                    var1_4 = 4;
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
                    var5[var3_1++] = Teleport.c(Teleport.a(v1));
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
        Teleport.s = var5;
    }

    private void a(Vec3 vec3, Vec3 vec32) {
        if (mc.getConnection() == null || vec3 == null || vec32 == null) {
            return;
        }
        Vec3 vec33 = vec32.subtract(vec3);
        double d2 = vec33.length();
        int n2 = Math.max(1, (int)Math.ceil(d2 / ((Number)this.packetStep.getValue()).doubleValue()));
        for (int i2 = 1; i2 <= n2; ++i2) {
            double d3 = (double)i2 / (double)n2;
            Vec3 vec34 = vec3.add(vec33.scale(d3));
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(vec34.x, vec34.y, vec34.z, i2 == n2 && Teleport.mc.player.onGround()));
        }
    }

    @Override
    public void onEnable() {
        this.p = null;
        this.r = false;
        if (Teleport.mc.player == null || Teleport.mc.level == null) {
            this.setEnabled(false);
            return;
        }
        this.q = Teleport.mc.player.position();
        String[] stringArray = s;
        ChatUtil.print(this.mode.is("MiniBlox") ? "Teleport: hold sneak to confirm" : "Teleport: move after respawn, then toggle off");
    }
}

