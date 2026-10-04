/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import com.mojang.authlib.GameProfile;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.e;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.ChatUtil;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameType;

public class AntiBots
extends Module {
    private static final Set<Integer> p;
    private final BooleanSetting debug;
    private static final String[] r;
    private static final Map<UUID, Long> q;
    private static final Map<Integer, String> n;
    private static final Map<UUID, Long> o;
    public static AntiBots j;
    private static final Map<UUID, String> m;
    private final NumberSetting respawnTime;

    @EventTarget
    public void a(PacketEvent packetEvent) {
        if (packetEvent.a() && AntiBots.mc.level != null) {
            Packet<?> packet = packetEvent.c();
            if (packet instanceof ClientboundPlayerInfoUpdatePacket) {
                ClientboundPlayerInfoUpdatePacket clientboundPlayerInfoUpdatePacket = (ClientboundPlayerInfoUpdatePacket)packet;
                if (clientboundPlayerInfoUpdatePacket.actions().contains(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER)) {
                    for (ClientboundPlayerInfoUpdatePacket.Entry entry : clientboundPlayerInfoUpdatePacket.entries()) {
                        if (entry.displayName() != null) {
                            String[] stringArray;
                            if (entry.profile() != null) {
                                stringArray = r;
                                if (entry.profile().getName().contains("Sky_Yuanxiao") && ((Boolean)this.debug.getValue()).booleanValue()) {
                                    ChatUtil.print("\u6c64\u5706\u6765\u4e8611");
                                }
                            }
                            stringArray = r;
                            if (entry.displayName().getString().contains("Sky_Yuanxiao") && ((Boolean)this.debug.getValue()).booleanValue()) {
                                ChatUtil.print("\u6c64\u5706\u6765\u4e8611");
                            }
                        }
                        GameProfile gameProfile = entry.profile();
                        UUID uUID = gameProfile.getId();
                        q.put(uUID, System.currentTimeMillis());
                    }
                }
            } else {
                ClientboundAnimatePacket clientboundAnimatePacket;
                Entity entity;
                packet = packetEvent.c();
                if (packet instanceof ClientboundAnimatePacket && (entity = AntiBots.mc.level.getEntity((clientboundAnimatePacket = (ClientboundAnimatePacket)packet).getId())) != null && clientboundAnimatePacket.getAction() == 0) {
                    q.remove(entity.getUUID());
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
                    case 0 -> 86;
                    case 1 -> 34;
                    case 2 -> 78;
                    case 3 -> 4;
                    case 4 -> 75;
                    case 5 -> 60;
                    default -> 59;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @EventTarget
    public void b(PacketEvent packetEvent) {
        if (!packetEvent.a()) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (packet instanceof ClientboundPlayerInfoUpdatePacket) {
            ClientboundPlayerInfoUpdatePacket clientboundPlayerInfoUpdatePacket = (ClientboundPlayerInfoUpdatePacket)packet;
            if (!clientboundPlayerInfoUpdatePacket.actions().contains(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER)) {
                return;
            }
            for (ClientboundPlayerInfoUpdatePacket.Entry entry : clientboundPlayerInfoUpdatePacket.entries()) {
                if (entry.displayName() != null) {
                    String[] stringArray;
                    if (entry.profile() != null) {
                        stringArray = r;
                        if (entry.profile().getName().contains("Sky_Yuanxiao") && ((Boolean)this.debug.getValue()).booleanValue()) {
                            ChatUtil.print("\u6c64\u5706\u6765\u4e861");
                        }
                    }
                    stringArray = r;
                    if (entry.displayName().getString().contains("Sky_Yuanxiao") && ((Boolean)this.debug.getValue()).booleanValue()) {
                        ChatUtil.print("\u6c64\u5706\u6765\u4e861");
                    }
                }
                if (entry.displayName() == null || !entry.displayName().getSiblings().isEmpty() || entry.gameMode() != GameType.SURVIVAL) continue;
                UUID uUID = entry.profile().getId();
                o.put(uUID, System.currentTimeMillis());
                m.put(uUID, entry.displayName().getString());
            }
            return;
        }
        if (packet instanceof ClientboundAddPlayerPacket) {
            ClientboundAddPlayerPacket clientboundAddPlayerPacket = (ClientboundAddPlayerPacket)packet;
            if (!o.containsKey(clientboundAddPlayerPacket.getPlayerId())) {
                return;
            }
            String string = m.get(clientboundAddPlayerPacket.getPlayerId());
            if (((Boolean)this.debug.getValue()).booleanValue()) {
                String[] stringArray = r;
                ChatUtil.print("Bot Detected! (" + string + ")");
            }
            n.put(clientboundAddPlayerPacket.getEntityId(), string);
            o.remove(clientboundAddPlayerPacket.getPlayerId());
            p.add(clientboundAddPlayerPacket.getEntityId());
            return;
        }
        if (packet instanceof ClientboundRemoveEntitiesPacket) {
            ClientboundRemoveEntitiesPacket clientboundRemoveEntitiesPacket = (ClientboundRemoveEntitiesPacket)packet;
            for (Integer n2 : clientboundRemoveEntitiesPacket.getEntityIds()) {
                if (!p.contains(n2)) continue;
                String string = n.get(n2);
                if (((Boolean)this.debug.getValue()).booleanValue()) {
                    ChatUtil.print("Bot Removed! (" + string + ")");
                }
                p.remove(n2);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[11];
                var3_1 = 0;
                var2_2 = "\u0005I7[\u0012IZ8Z'e$\u0006\u6c32\u5724\u672b\u4e82z\r\u000f\u0014M:$\u000fYO3A:a/\u001d\u001b~\u0005\u6c32\u5724\u672b\u4e82z\u0005\u6c32\u5724\u672b\u4e82z\u0006\u6c32\u5724\u672b\u4e82z\r\u0005\u0012G,q,\f\u0005I7[\u0012IZ8Z'e$\u000e\u0014M:$\u0019YV9T+`j\u001c\u0013";
                var4_3 = "\u0005I7[\u0012IZ8Z'e$\u0006\u6c32\u5724\u672b\u4e82z\r\u000f\u0014M:$\u000fYO3A:a/\u001d\u001b~\u0005\u6c32\u5724\u672b\u4e82z\u0005\u6c32\u5724\u672b\u4e82z\u0006\u6c32\u5724\u672b\u4e82z\r\u0005\u0012G,q,\f\u0005I7[\u0012IZ8Z'e$\u000e\u0014M:$\u0019YV9T+`j\u001c\u0013".length();
                var1_4 = 12;
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
                    var5[var3_1++] = AntiBots.c(AntiBots.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0004G=t*KUvv'i.\b\u0017L:m\tSO%";
                    var4_3 = "\u0004G=t*KUvv'i.\b\u0017L:m\tSO%".length();
                    var1_4 = 12;
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
                    var5[var3_1++] = AntiBots.c(AntiBots.a(v1));
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
        AntiBots.r = var5;
        AntiBots.m = new ConcurrentHashMap<UUID, String>();
        AntiBots.n = new ConcurrentHashMap<Integer, String>();
        AntiBots.o = new ConcurrentHashMap<UUID, Long>();
        AntiBots.p = new HashSet<Integer>();
        AntiBots.q = new ConcurrentHashMap<UUID, Long>();
    }

    public static boolean b(Entity entity) {
        return p.contains(entity.getId());
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPost()) {
            for (Map.Entry<UUID, Long> entry : o.entrySet()) {
                if (System.currentTimeMillis() - entry.getValue() <= 500L) continue;
                o.remove(entry.getKey());
            }
        }
    }

    public static boolean a(Entity entity) {
        AntiBots antiBots = j;
        if (entity.getId() >= 1000000000 || entity.getId() <= -1) {
            return true;
        }
        if (entity.getName() == null) {
            return true;
        }
        if (entity.getScoreboardName().isEmpty()) {
            return true;
        }
        if (((Number)antiBots.respawnTime.getValue()).floatValue() < 1.0f) {
            return false;
        }
        if (!q.containsKey(entity.getUUID())) {
            return false;
        }
        return (float)(System.currentTimeMillis() - q.get(entity.getUUID())) < ((Number)antiBots.respawnTime.getValue()).floatValue();
    }

    @EventTarget
    public void a(e e2) {
        m.clear();
        n.clear();
        p.clear();
        o.clear();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x3B);
        }
        return cArray;
    }

    public AntiBots() {
        String[] stringArray = r;
        super("AntiBots", Category.COMBAT);
        this.respawnTime = new NumberSetting("Respawn Time", 2500.0, 0.0, 10000.0, 100.0);
        this.debug = new BooleanSetting("Debug", true);
        j = this;
    }
}

