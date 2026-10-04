/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public final class m
extends RemotePlayer {
    private final AbstractClientPlayer a;
    private static final String b = m.a(m.a("o#v\u0004&~\u000fl)w\u001b7e@R"));

    public m(AbstractClientPlayer abstractClientPlayer) {
        super(Minecraft.getInstance().level, new GameProfile(UUID.randomUUID(), b));
        this.a = abstractClientPlayer;
        this.copyPosition((Entity)abstractClientPlayer);
        this.noPhysics = true;
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
        this.yHeadRot = abstractClientPlayer.yHeadRot;
        this.yBodyRot = abstractClientPlayer.yBodyRot;
        this.yHeadRotO = this.yHeadRot;
        this.yBodyRotO = this.yBodyRot;
        Byte by = (Byte)abstractClientPlayer.getEntityData().get(Player.DATA_PLAYER_MODE_CUSTOMISATION);
        this.entityData.set(Player.DATA_PLAYER_MODE_CUSTOMISATION, (Object)by);
    }

    public boolean isSkinLoaded() {
        return this.a.isSkinLoaded();
    }

    public ResourceLocation getSkinTextureLocation() {
        return this.a.getSkinTextureLocation();
    }

    public boolean isCapeLoaded() {
        return this.a.isCapeLoaded();
    }

    public ResourceLocation getCloakTextureLocation() {
        return this.a.getCloakTextureLocation();
    }

    public void a() {
        float f2 = m.a(this.getYRot(), this.a.getYRot());
        float f3 = m.a(this.yHeadRot, this.a.yHeadRot);
        float f4 = m.a(this.yBodyRot, this.a.yBodyRot);
        this.setYRot(f2);
        this.setXRot(this.a.getXRot());
        this.yRotO = m.a(f2, this.a.yRotO);
        this.xRotO = this.a.xRotO;
        this.yHeadRot = f3;
        this.yHeadRotO = m.a(f3, this.a.yHeadRotO);
        this.yBodyRot = f4;
        this.yBodyRotO = m.a(f4, this.a.yBodyRotO);
        this.setPose(this.a.getPose());
        this.setSprinting(this.a.isSprinting());
        this.setShiftKeyDown(this.a.isShiftKeyDown());
        this.setHealth(this.a.getHealth());
        this.swinging = this.a.swinging;
        this.swingingArm = this.a.swingingArm;
        this.swingTime = this.a.swingTime;
        this.oAttackAnim = this.a.oAttackAnim;
        this.attackAnim = this.a.attackAnim;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            this.setItemSlot(equipmentSlot, this.a.getItemBySlot(equipmentSlot).copy());
        }
    }

    private static float a(float f2, float f3) {
        return f2 + Mth.wrapDegrees((float)(f3 - f2));
    }

    public boolean isPickable() {
        return false;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x2F);
        }
        return cArray;
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
                    case 0 -> 60;
                    case 1 -> 70;
                    case 2 -> 4;
                    case 3 -> 114;
                    case 4 -> 67;
                    case 5 -> 12;
                    default -> 47;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

