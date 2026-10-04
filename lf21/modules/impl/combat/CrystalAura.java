/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import java.util.Optional;
import java.util.stream.StreamSupport;
import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.utils.game.g;
import lf21.utils.misc.PacketUtil;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.Level;

public class CrystalAura
extends Module {
    private Entity l;
    private static final String[] n;
    public final BooleanSetting attackOnPacketDanger;
    public static a k;
    public static CrystalAura j;

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "Ck\\]M 8mq\blO(sgk\b\u0014j*vezZ\u0015\u000bAmQOZ*tCjZ]";
        int n3 = "Ck\\]M 8mq\blO(sgk\b\u0014j*vezZ\u0015\u000bAmQOZ*tCjZ]".length();
        int n4 = 25;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = CrystalAura.c(CrystalAura.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                n = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (CrystalAura.mc.player != null && CrystalAura.mc.level != null) {
            Optional<Entity> optional = StreamSupport.stream(CrystalAura.mc.level.entitiesForRendering().spliterator(), true).filter(entity -> entity instanceof EndCrystal).findAny();
            k = null;
            if (optional.isPresent()) {
                double d2;
                Entity entity2 = optional.get();
                a a2 = g.c(entity2);
                double d3 = g.a(entity2, a2);
                if (d2 <= 3.0) {
                    k = a2;
                    this.l = entity2;
                }
            }
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
                    case 0 -> 2;
                    case 1 -> 31;
                    case 2 -> 40;
                    case 3 -> 60;
                    case 4 -> 46;
                    case 5 -> 75;
                    default -> 24;
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
            cArray[0] = (char)(cArray[0] ^ 0x18);
        }
        return cArray;
    }

    public CrystalAura() {
        String[] stringArray = n;
        super("CrystalAura", Category.COMBAT);
        this.attackOnPacketDanger = new BooleanSetting("Attack on Packet (Danger)", false);
        j = this;
    }

    @EventTarget
    public void a(PreMotionEvent preMotionEvent) {
        if (this.l != null && k != null && CrystalAura.mc.player != null && mc.getConnection() != null) {
            float f2 = CrystalAura.mc.player.getYRot();
            float f3 = CrystalAura.mc.player.getXRot();
            CrystalAura.mc.player.setYRot(k.e());
            CrystalAura.mc.player.setXRot(k.f());
            mc.getConnection().send((Packet)ServerboundInteractPacket.createAttackPacket((Entity)this.l, (boolean)false));
            CrystalAura.mc.player.swing(InteractionHand.MAIN_HAND);
            CrystalAura.mc.player.setYRot(f2);
            CrystalAura.mc.player.setXRot(f3);
            this.l = null;
        }
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        if (CrystalAura.mc.player == null || CrystalAura.mc.level == null) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (packet instanceof ClientboundAddEntityPacket) {
            ClientboundAddEntityPacket clientboundAddEntityPacket = (ClientboundAddEntityPacket)packet;
            if (((Boolean)this.attackOnPacketDanger.getValue()).booleanValue() && clientboundAddEntityPacket.getType() == EntityType.END_CRYSTAL) {
                EndCrystal endCrystal = new EndCrystal((Level)CrystalAura.mc.level, clientboundAddEntityPacket.getX(), clientboundAddEntityPacket.getY(), clientboundAddEntityPacket.getZ());
                endCrystal.setId(clientboundAddEntityPacket.getId());
                if (CrystalAura.mc.player.distanceTo((Entity)endCrystal) <= 4.0f) {
                    a a2 = g.c((Entity)endCrystal);
                    mc.getConnection().send((Packet)new ServerboundMovePlayerPacket.PosRot(CrystalAura.mc.player.getX(), CrystalAura.mc.player.getY(), CrystalAura.mc.player.getZ(), a2.e(), a2.f(), CrystalAura.mc.player.onGround()));
                    PacketUtil.a(n2 -> new ServerboundUseItemPacket(InteractionHand.MAIN_HAND, n2));
                    float f2 = CrystalAura.mc.player.getYRot();
                    float f3 = CrystalAura.mc.player.getXRot();
                    CrystalAura.mc.player.setYRot(RotationHandler.a.e());
                    CrystalAura.mc.player.setXRot(RotationHandler.a.f());
                    mc.getConnection().send((Packet)ServerboundInteractPacket.createAttackPacket((Entity)endCrystal, (boolean)false));
                    CrystalAura.mc.player.swing(InteractionHand.MAIN_HAND);
                    CrystalAura.mc.player.setYRot(f2);
                    CrystalAura.mc.player.setXRot(f3);
                }
            }
        }
    }
}

