/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.world.entity.Entity;

public class AntiStaff
extends Module {
    private static final String[] l;
    public static AntiStaff j;
    private static final String k;

    public AntiStaff() {
        super("AntiStaff", Category.WORLD);
        j = this;
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        String string;
        Entity entity;
        ClientboundAddPlayerPacket clientboundAddPlayerPacket;
        String string2 = new String(Base64.getDecoder().decode("QuermeaQnOaXoOmHj+Wfn+mbqizkuInlm73mnYAs56yZ5qmZLE1lbmdDaGVuMzg4NCxBbmRyZXdrcmlzdCxGaWE5LOaeq+iQp+ael+eEtiznu7/osYbkuYPjgZXjgpMs5oqW6Z+z5Li25bCP5YyqLOaKlumfs19hd2Hpqazljp8sTW5hbUxlb18s5Lit5LqM5bCR5bm0REws5p6V5LiK5Lmm5Li25aGR5pyb5pyILElhbU1vbGluY2VuXywsQ29GdV9fLOaWl+aImOiDnOS9myzlj6rnjqnmlqXlgJks5p6V5LiK5Lmm5Li26Zuq5aScLGFpeXVraSxDYW5keUFwb3N0bGUsY2h1bnlpMSzmtYHlvbHlj6rkvJrlmKTlmKTlmKQscXRlc2RmXzY3NCxxeHRtbGM5OSxTa3lmb3ks56We5Z2R5LmL6YCXLOWco+S4iuiNo+iAgDIzMyzlsI/lhpvlkJvkuLblpKnkvb/kuYvnv7ws5p6V5LiK5Lmm5Li25YKy5a+SLF93aW5uZXJfLFNreV9ZdWFueGlhbw=="), StandardCharsets.UTF_8);
        List<String> list = Arrays.asList(string2.split(","));
        Packet<?> packet = packetEvent.c();
        if (packet instanceof ClientboundPlayerInfoUpdatePacket) {
            ClientboundPlayerInfoUpdatePacket clientboundPlayerInfoUpdatePacket = (ClientboundPlayerInfoUpdatePacket)packet;
            if (clientboundPlayerInfoUpdatePacket.actions().contains(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER)) {
                for (ClientboundPlayerInfoUpdatePacket.Entry entry : clientboundPlayerInfoUpdatePacket.entries()) {
                    String string3;
                    if (entry.profile() != null && (string3 = entry.profile().getName()) != null && !string3.isEmpty() && list.contains(string3)) {
                        this.a();
                    }
                    if (entry.displayName() == null || (string3 = entry.displayName().getString()).isEmpty() || !list.contains(string3)) continue;
                    this.a();
                }
            }
        } else if (packet instanceof ClientboundAddPlayerPacket && (clientboundAddPlayerPacket = (ClientboundAddPlayerPacket)packet).getPlayerId() != null && AntiStaff.mc.level != null && (entity = AntiStaff.mc.level.getEntity(clientboundAddPlayerPacket.getEntityId())) != null && !(string = entity.getName().getString()).isEmpty() && list.contains(string)) {
            this.a();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "C\u001f\u0006\tj\u0004\u00105\u0001-6M\f";
                var4_3 = "C\u001f\u0006\tj\u0004\u00105\u0001-6M\f".length();
                var1_4 = 3;
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
                    var5[var3_1++] = AntiStaff.c(AntiStaff.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "z\u001f\u0001.?<6z\u0004+=\n6\u0018F\"\u000ew\u0005?9\u0000\u0007\u0006-;#<^#\n0?ndF\u0004=\u001d!laR0Q-?\u0003\u001bn[\b>?=\u0013J-2)\u001f#0\u001f$'$\u0010;:y\u0013>\u00046+4F\u0006\u001e8\u0011!\u0010J=!i\u001e\u00166N\u001bO5\u0003)|J\u000f\bw7\u001c#B\u0010\n)ev8X3\u00067'\u0000\u0007A\r>\u00048>'f\u0019Q3#\u000eaqA\u001ei\u001e0e\u001e\b'\fg\u0000.Z&+=\u00195\"F\f\u0017mk13\u0019\"\u0014-3#;A\u001a\\/\u0006\u000ebC\b1$>;f\u0013\u0019Q\u0010;-bg\u001b)i0\u001a\u0005\u001e\b\tl\u0000\u001c X_\u0014j\u0004l\u001bB!Q\u0010?4bg\u0003Vi3\u001e\u0005\u001e\u001a\u001d>g).b&!0:;\u0002\u001a\u001c\u0006\u001b>,\u000e\u0019<\u0011\u0004+.$zX]\u001b6\u000fnM&+=\u00055|J#\t\u0013;\u001d9d9]1+#;A\\\u001628(9F\u0006\u0015\u0004>>\u001d@\u0019Q,d\u000fbg\u0003/i\u001e4:\u001e&\rnd\u0003\"Z_\u0005\u000f1\u0015\u0010m\u001a\u0001\u0004\u0004+6x\u0012 \u0005\u0005l<N?\"+0j\u0019\u001b\b#\t!\u0000eC[\u00062>)\u001ax\u0010\t(\u000b\u0011;]\b,08o%@\u001c..>4\u001c\u007f\u0006\t\u0017\u00065:`;\u0017?\n\u000b;HX61\n#\u000e\u0018$'$*<\u001fy\u001e\u0006\u001b\u001fl\u0018x\u00120=a5:IY\u000f/go\u0000N_>n\u0000l\u001bF&R\u0005\u0011\u0001\u001bd=\u00073y\ncB\u001f\r\u0012=r>j\r \u0015(\u0014.Q\u0006\u0017\u0015}5?[\u001c\b7\u0018/<^&\u00060\"\u00129@\u001c\u0006s9,\u000e]\u0004\u0012k%*b[\\2i\u001e0\u001c\u001e&\t1g\u0015>\u0019_=\u0017+l6\u00009(\u001akj6|_\u0011\u0006\n\u00131g,*.7\u000fnq\u000e3\u001a'<\u0010G\u0002\u0006+od\u0228z\u001f\u0001.?<6z\u0004+=\n6\u0018F\"\u000ew\u0005?9\u0000\u0007\u0006-;#<^#\n0?ndF\u0004=\u001d!laR0Q-?\u0003\u001bn[\b>?=\u0013J-2)\u001f#0\u001f$'$\u0010;:y\u0013>\u00046+4F\u0006\u001e8\u0011!\u0010J=!i\u001e\u00166N\u001bO5\u0003)|J\u000f\bw7\u001c#B\u0010\n)ev8X3\u00067'\u0000\u0007A\r>\u00048>'f\u0019Q3#\u000eaqA\u001ei\u001e0e\u001e\b'\fg\u0000.Z&+=\u00195\"F\f\u0017mk13\u0019\"\u0014-3#;A\u001a\\/\u0006\u000ebC\b1$>;f\u0013\u0019Q\u0010;-bg\u001b)i0\u001a\u0005\u001e\b\tl\u0000\u001c X_\u0014j\u0004l\u001bB!Q\u0010?4bg\u0003Vi3\u001e\u0005\u001e\u001a\u001d>g).b&!0:;\u0002\u001a\u001c\u0006\u001b>,\u000e\u0019<\u0011\u0004+.$zX]\u001b6\u000fnM&+=\u00055|J#\t\u0013;\u001d9d9]1+#;A\\\u001628(9F\u0006\u0015\u0004>>\u001d@\u0019Q,d\u000fbg\u0003/i\u001e4:\u001e&\rnd\u0003\"Z_\u0005\u000f1\u0015\u0010m\u001a\u0001\u0004\u0004+6x\u0012 \u0005\u0005l<N?\"+0j\u0019\u001b\b#\t!\u0000eC[\u00062>)\u001ax\u0010\t(\u000b\u0011;]\b,08o%@\u001c..>4\u001c\u007f\u0006\t\u0017\u00065:`;\u0017?\n\u000b;HX61\n#\u000e\u0018$'$*<\u001fy\u001e\u0006\u001b\u001fl\u0018x\u00120=a5:IY\u000f/go\u0000N_>n\u0000l\u001bF&R\u0005\u0011\u0001\u001bd=\u00073y\ncB\u001f\r\u0012=r>j\r \u0015(\u0014.Q\u0006\u0017\u0015}5?[\u001c\b7\u0018/<^&\u00060\"\u00129@\u001c\u0006s9,\u000e]\u0004\u0012k%*b[\\2i\u001e0\u001c\u001e&\t1g\u0015>\u0019_=\u0017+l6\u00009(\u001akj6|_\u0011\u0006\n\u00131g,*.7\u000fnq\u000e3\u001a'<\u0010G\u0002\u0006+od";
                    var4_3 = "z\u001f\u0001.?<6z\u0004+=\n6\u0018F\"\u000ew\u0005?9\u0000\u0007\u0006-;#<^#\n0?ndF\u0004=\u001d!laR0Q-?\u0003\u001bn[\b>?=\u0013J-2)\u001f#0\u001f$'$\u0010;:y\u0013>\u00046+4F\u0006\u001e8\u0011!\u0010J=!i\u001e\u00166N\u001bO5\u0003)|J\u000f\bw7\u001c#B\u0010\n)ev8X3\u00067'\u0000\u0007A\r>\u00048>'f\u0019Q3#\u000eaqA\u001ei\u001e0e\u001e\b'\fg\u0000.Z&+=\u00195\"F\f\u0017mk13\u0019\"\u0014-3#;A\u001a\\/\u0006\u000ebC\b1$>;f\u0013\u0019Q\u0010;-bg\u001b)i0\u001a\u0005\u001e\b\tl\u0000\u001c X_\u0014j\u0004l\u001bB!Q\u0010?4bg\u0003Vi3\u001e\u0005\u001e\u001a\u001d>g).b&!0:;\u0002\u001a\u001c\u0006\u001b>,\u000e\u0019<\u0011\u0004+.$zX]\u001b6\u000fnM&+=\u00055|J#\t\u0013;\u001d9d9]1+#;A\\\u001628(9F\u0006\u0015\u0004>>\u001d@\u0019Q,d\u000fbg\u0003/i\u001e4:\u001e&\rnd\u0003\"Z_\u0005\u000f1\u0015\u0010m\u001a\u0001\u0004\u0004+6x\u0012 \u0005\u0005l<N?\"+0j\u0019\u001b\b#\t!\u0000eC[\u00062>)\u001ax\u0010\t(\u000b\u0011;]\b,08o%@\u001c..>4\u001c\u007f\u0006\t\u0017\u00065:`;\u0017?\n\u000b;HX61\n#\u000e\u0018$'$*<\u001fy\u001e\u0006\u001b\u001fl\u0018x\u00120=a5:IY\u000f/go\u0000N_>n\u0000l\u001bF&R\u0005\u0011\u0001\u001bd=\u00073y\ncB\u001f\r\u0012=r>j\r \u0015(\u0014.Q\u0006\u0017\u0015}5?[\u001c\b7\u0018/<^&\u00060\"\u00129@\u001c\u0006s9,\u000e]\u0004\u0012k%*b[\\2i\u001e0\u001c\u001e&\t1g\u0015>\u0019_=\u0017+l6\u00009(\u001akj6|_\u0011\u0006\n\u00131g,*.7\u000fnq\u000e3\u001a'<\u0010G\u0002\u0006+od\u0228z\u001f\u0001.?<6z\u0004+=\n6\u0018F\"\u000ew\u0005?9\u0000\u0007\u0006-;#<^#\n0?ndF\u0004=\u001d!laR0Q-?\u0003\u001bn[\b>?=\u0013J-2)\u001f#0\u001f$'$\u0010;:y\u0013>\u00046+4F\u0006\u001e8\u0011!\u0010J=!i\u001e\u00166N\u001bO5\u0003)|J\u000f\bw7\u001c#B\u0010\n)ev8X3\u00067'\u0000\u0007A\r>\u00048>'f\u0019Q3#\u000eaqA\u001ei\u001e0e\u001e\b'\fg\u0000.Z&+=\u00195\"F\f\u0017mk13\u0019\"\u0014-3#;A\u001a\\/\u0006\u000ebC\b1$>;f\u0013\u0019Q\u0010;-bg\u001b)i0\u001a\u0005\u001e\b\tl\u0000\u001c X_\u0014j\u0004l\u001bB!Q\u0010?4bg\u0003Vi3\u001e\u0005\u001e\u001a\u001d>g).b&!0:;\u0002\u001a\u001c\u0006\u001b>,\u000e\u0019<\u0011\u0004+.$zX]\u001b6\u000fnM&+=\u00055|J#\t\u0013;\u001d9d9]1+#;A\\\u001628(9F\u0006\u0015\u0004>>\u001d@\u0019Q,d\u000fbg\u0003/i\u001e4:\u001e&\rnd\u0003\"Z_\u0005\u000f1\u0015\u0010m\u001a\u0001\u0004\u0004+6x\u0012 \u0005\u0005l<N?\"+0j\u0019\u001b\b#\t!\u0000eC[\u00062>)\u001ax\u0010\t(\u000b\u0011;]\b,08o%@\u001c..>4\u001c\u007f\u0006\t\u0017\u00065:`;\u0017?\n\u000b;HX61\n#\u000e\u0018$'$*<\u001fy\u001e\u0006\u001b\u001fl\u0018x\u00120=a5:IY\u000f/go\u0000N_>n\u0000l\u001bF&R\u0005\u0011\u0001\u001bd=\u00073y\ncB\u001f\r\u0012=r>j\r \u0015(\u0014.Q\u0006\u0017\u0015}5?[\u001c\b7\u0018/<^&\u00060\"\u00129@\u001c\u0006s9,\u000e]\u0004\u0012k%*b[\\2i\u001e0\u001c\u001e&\t1g\u0015>\u0019_=\u0017+l6\u00009(\u001akj6|_\u0011\u0006\n\u00131g,*.7\u000fnq\u000e3\u001a'<\u0010G\u0002\u0006+od".length();
                    var1_4 = 552;
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
                    var5[var3_1++] = AntiStaff.c(AntiStaff.a(v1));
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
        AntiStaff.l = var5;
        AntiStaff.k = AntiStaff.l[3];
    }

    private void a() {
        if (AntiStaff.mc.player != null && AntiStaff.mc.player.connection != null) {
            AntiStaff.mc.player.connection.sendCommand("hub");
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
                    case 0 -> 43;
                    case 1 -> 106;
                    case 2 -> 100;
                    case 3 -> 92;
                    case 4 -> 82;
                    case 5 -> 89;
                    default -> 87;
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
            cArray[0] = (char)(cArray[0] ^ 0x57);
        }
        return cArray;
    }
}

