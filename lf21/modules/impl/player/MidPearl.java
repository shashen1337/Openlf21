/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import java.util.Comparator;
import java.util.Optional;
import lf21.event.EventTarget;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.misc.AutoRod;
import lf21.modules.impl.movement.FireballBlink;
import lf21.modules.impl.render.Projectiles;
import lf21.modules.impl.render.n;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.utils.a.h;
import lf21.utils.game.d;
import lf21.utils.game.g;
import lf21.utils.misc.PacketUtil;
import lf21.utils.rotation.a;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;

public class MidPearl
extends Module {
    private final BooleanSetting onRelease;
    private int u;
    private final BooleanSetting blockCheck;
    public static MidPearl j;
    private int v;
    private boolean t;
    private boolean s;
    private boolean r;
    private int z;
    public static a k;
    private final ModeSetting interceptButton;
    private int y;
    private final ModeSetting button;
    private final BooleanSetting intercept;
    private boolean x;
    private final h q;
    private int w;
    private static final String[] B;
    private int A;

    @Override
    public void onEnable() {
        this.a();
    }

    private void n() {
        boolean bl;
        if (this.u > 0) {
            --this.u;
            if (this.u == 0) {
                if (MidPearl.mc.player.getMainHandItem().getItem() == Items.ENDER_PEARL) {
                    PacketUtil.a(n2 -> new ServerboundUseItemPacket(InteractionHand.MAIN_HAND, n2));
                    MidPearl.mc.player.swing(InteractionHand.MAIN_HAND);
                }
                this.v = 2;
            }
            return;
        }
        boolean bl2 = bl = GLFW.glfwGetMouseButton((long)mc.getWindow().getWindow(), (int)this.a(this.button)) == 1;
        if (bl) {
            int n3;
            if (!this.t && (n3 = d.a(Items.ENDER_PEARL)) != -1 && n3 < 9) {
                this.t = true;
                this.w = MidPearl.mc.player.getInventory().selected;
                if (KillAura.j != null && KillAura.j.isEnabled()) {
                    KillAura.j.setEnabled(false);
                }
                MidPearl.mc.player.getInventory().selected = n3;
            }
        } else if (this.t) {
            this.t = false;
            this.u = 1;
        }
    }

    private Optional<n> o() {
        if (Projectiles.x.isEmpty()) {
            return Optional.empty();
        }
        return Projectiles.x.entrySet().stream().filter(entry -> {
            if (MidPearl.mc.level == null || MidPearl.mc.player == null) {
                return false;
            }
            Entity entity = MidPearl.mc.level.getEntity(((Integer)entry.getKey()).intValue());
            if (!(entity instanceof ThrownEnderpearl)) {
                return false;
            }
            ThrownEnderpearl thrownEnderpearl = (ThrownEnderpearl)entity;
            Entity entity2 = thrownEnderpearl.getOwner();
            return entity2 != null && !entity2.equals((Object)MidPearl.mc.player);
        }).map(entry -> (n)entry.getValue()).min(Comparator.comparingDouble(n2 -> n2.f().distanceTo(MidPearl.mc.player.position())));
    }

    private Optional<a> l() {
        int n2;
        Optional<n> optional = this.o();
        if (optional.isPresent() && (n2 = d.a(Items.ENDER_PEARL)) != -1 && n2 < 9) {
            Vec3 vec3 = optional.get().f();
            if (((Boolean)this.blockCheck.getValue()).booleanValue() && MidPearl.mc.level.clip(new ClipContext(MidPearl.mc.player.getEyePosition(), vec3, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)MidPearl.mc.player)).getType() != HitResult.Type.MISS) {
                return Optional.empty();
            }
            float[] fArray = g.b(vec3);
            if (fArray != null) {
                return Optional.of(new a(fArray[0], fArray[1]));
            }
        }
        return Optional.empty();
    }

    private void m() {
        if (MidPearl.mc.player.getMainHandItem().getItem() == Items.ENDER_PEARL) {
            PacketUtil.a(n2 -> new ServerboundUseItemPacket(InteractionHand.MAIN_HAND, n2));
            MidPearl.mc.player.swing(InteractionHand.MAIN_HAND);
        }
        this.A = 2;
        this.x = false;
        this.y = -1;
        this.s = true;
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
                    case 0 -> 68;
                    case 1 -> 28;
                    case 2 -> 94;
                    case 3 -> 12;
                    case 4 -> 76;
                    case 5 -> 52;
                    default -> 85;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public MidPearl() {
        String[] stringArray = B;
        super("MidPearl", Category.PLAYER);
        this.button = new ModeSetting("Button", "Middle", "Mouse 4", "Mouse 5").withDefault("Middle");
        this.intercept = new BooleanSetting("Intercept", false);
        this.interceptButton = new ModeSetting("Intercept Button", "Mouse 4", "Mouse 5").withDefault("Mouse 4");
        this.onRelease = new BooleanSetting("On Release", false);
        this.blockCheck = new BooleanSetting("Block Check", true);
        this.q = new h();
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 0;
        this.v = 0;
        this.w = -1;
        this.x = false;
        this.y = 0;
        this.z = -1;
        this.A = 0;
        j = this;
    }

    @Override
    public void onDisable() {
        if (this.w != -1 && MidPearl.mc.player != null) {
            MidPearl.mc.player.getInventory().selected = this.w;
        }
        if (this.z != -1 && MidPearl.mc.player != null) {
            MidPearl.mc.player.getInventory().selected = this.z;
        }
        this.a();
    }

    private boolean i() {
        AutoRod autoRod = AutoRod.j;
        return autoRod != null && autoRod.isEnabled() && autoRod.b(this.a(this.button));
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x55);
        }
        return cArray;
    }

    private void a() {
        this.t = false;
        this.u = 0;
        this.v = 0;
        this.w = -1;
        this.x = false;
        k = null;
        this.y = 0;
        this.r = false;
        this.s = false;
        this.z = -1;
        this.A = 0;
    }

    public boolean a(int n2) {
        return (Boolean)this.intercept.getValue() != false && this.a(this.interceptButton) == n2 && this.k();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[12];
                var3_1 = 0;
                var2_2 = "\tu:h Q\b\tu:\\)U'(\u0007\ts+\u007f)\u0014`\u0007\ts+\u007f)\u0014a\t\rr*i>W04h\n\u000br~^)X0%o;\u000b\u0006p1o'\u0014\u0016,y=g\u0007\ts+\u007f)\u0014a\u0006\tu:h Q\u0007\ts+\u007f)\u0014`";
                var4_3 = "\tu:h Q\b\tu:\\)U'(\u0007\ts+\u007f)\u0014`\u0007\ts+\u007f)\u0014a\t\rr*i>W04h\n\u000br~^)X0%o;\u000b\u0006p1o'\u0014\u0016,y=g\u0007\ts+\u007f)\u0014a\u0006\tu:h Q\u0007\ts+\u007f)\u0014`".length();
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
                    var5[var3_1++] = MidPearl.c(MidPearl.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\rr*i>W04h~N9@!+r\u0006\u0006i*x#Z";
                    var4_3 = "\rr*i>W04h~N9@!+r\u0006\u0006i*x#Z".length();
                    var1_4 = 16;
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
                    var5[var3_1++] = MidPearl.c(MidPearl.a(v1));
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
        MidPearl.B = var5;
    }

    private void b(int n2) {
        if (n2 == -1 || n2 > 8) {
            return;
        }
        if (this.x) {
            return;
        }
        this.x = true;
        this.y = 2;
        this.z = MidPearl.mc.player.getInventory().selected;
        MidPearl.mc.player.getInventory().selected = n2;
    }

    private void h() {
        this.x = false;
        k = null;
        this.y = 0;
        this.r = false;
    }

    private void j() {
        k = this.l().orElse(null);
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (FireballBlink.j != null && FireballBlink.j.isEnabled()) {
            return;
        }
        if (MidPearl.mc.player == null || MidPearl.mc.level == null) {
            if (this.x) {
                this.a();
            }
            return;
        }
        if (this.A > 0) {
            --this.A;
            if (this.A == 0 && this.z != -1) {
                MidPearl.mc.player.getInventory().selected = this.z;
                this.z = -1;
            }
        }
        if (this.v > 0) {
            --this.v;
            if (this.v == 0) {
                if (this.w != -1) {
                    MidPearl.mc.player.getInventory().selected = this.w;
                }
                this.a();
            }
            return;
        }
        if (this.x) {
            if (this.y > 0) {
                --this.y;
            }
            if (this.y == 0) {
                this.m();
            }
            return;
        }
        if (((Boolean)this.intercept.getValue()).booleanValue()) {
            boolean bl;
            boolean bl2;
            boolean bl3 = bl2 = GLFW.glfwGetMouseButton((long)mc.getWindow().getWindow(), (int)this.a(this.interceptButton)) == 1;
            if (bl2) {
                if (this.s) {
                    return;
                }
            } else {
                this.s = false;
            }
            if (((Boolean)this.onRelease.getValue()).booleanValue()) {
                if (bl2) {
                    this.j();
                    this.r = true;
                } else {
                    if (this.r) {
                        if (k != null) {
                            this.b(d.a(Items.ENDER_PEARL));
                        } else {
                            this.h();
                        }
                    }
                    this.r = false;
                }
            } else if (bl2) {
                this.j();
                if (k != null) {
                    this.b(d.a(Items.ENDER_PEARL));
                }
            } else {
                this.h();
            }
            boolean bl4 = bl = this.a(this.interceptButton) == this.a(this.button);
            if (bl && (bl2 || this.r)) {
                return;
            }
        }
        if (this.i()) {
            return;
        }
        this.n();
    }

    private int a(ModeSetting modeSetting) {
        String string = (String)modeSetting.getValue();
        int n2 = -1;
        switch (string.hashCode()) {
            case -1387248807: {
                String[] stringArray = B;
                if (!string.equals("Mouse 4")) break;
                n2 = 0;
                break;
            }
            case -1387248806: {
                if (!string.equals("Mouse 5")) break;
                n2 = 1;
            }
        }
        return switch (n2) {
            case 0 -> 3;
            case 1 -> 4;
            default -> 2;
        };
    }

    private boolean k() {
        return this.l().isPresent();
    }
}

