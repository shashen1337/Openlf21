/*
 * Decompiled with CFR 0.152.
 */
package lf21.network;

import io.netty.buffer.ByteBuf;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Locale;
import lf21.render.ad;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MatchmakingProbe {
    private static final Logger a;
    private static final String b;
    private static final int c;
    private static volatile Path d;
    private static final String[] e;

    private MatchmakingProbe() {
    }

    public static void observeIncoming(Packet<?> packet) {
        MatchmakingProbe.a("server", packet);
    }

    public static void observeOutgoing(Packet<?> packet) {
        MatchmakingProbe.a("client", packet);
    }

    private static void a(String string, Packet<?> packet) {
        Object object;
        String string2;
        String[] stringArray;
        if (packet == null || !MatchmakingProbe.a() || !MatchmakingProbe.a(packet)) {
            return;
        }
        String string3 = MatchmakingProbe.a(packet);
        Object object2 = MatchmakingProbe.b(packet);
        if (object2 == null) {
            stringArray = e;
            string2 = "none";
        } else {
            string2 = object2.getClass().getName();
        }
        String string4 = string2;
        int n2 = -1;
        String string5 = "";
        if (object2 instanceof ByteBuf) {
            object = (ByteBuf)object2;
            n2 = object.readableBytes();
            string5 = MatchmakingProbe.a(object);
        } else if (object2 instanceof byte[]) {
            byte[] byArray = (byte[])object2;
            n2 = byArray.length;
            string5 = MatchmakingProbe.a(byArray, 0, byArray.length);
        } else if (object2 != null) {
            byte[] byArray = object2.toString().getBytes(StandardCharsets.UTF_8);
            n2 = byArray.length;
            string5 = MatchmakingProbe.a(byArray, 0, byArray.length);
        }
        String string6 = string5;
        int n3 = n2;
        String string7 = string4;
        String string8 = MatchmakingProbe.a(string3);
        String string9 = packet.getClass().getName();
        String string10 = string;
        stringArray = e;
        object = String.valueOf(Instant.now()) + " direction=" + string10 + " class=" + string9 + " identifier=" + string8 + " payloadType=" + string7 + " length=" + n3 + " sha256=" + string6;
        a.info("[MatchmakingProbe] {}", object);
        MatchmakingProbe.b((String)object);
    }

    private static boolean a() {
        String[] stringArray = e;
        return Boolean.parseBoolean(System.getProperty("lf21.matchmakingProbe", "false"));
    }

    private static boolean a(Packet<?> packet) {
        String string = packet.getClass().getName().toLowerCase(Locale.ROOT);
        String[] stringArray = e;
        return string.contains("custompayload") || string.contains("customquery") || string.contains("pluginmessage") || string.contains("serverboundcustom");
    }

    private static String a(Object object) {
        if (object instanceof ClientboundCustomPayloadPacket) {
            ClientboundCustomPayloadPacket clientboundCustomPayloadPacket = (ClientboundCustomPayloadPacket)object;
            return clientboundCustomPayloadPacket.getIdentifier().toString();
        }
        if (object instanceof ServerboundCustomPayloadPacket) {
            ServerboundCustomPayloadPacket serverboundCustomPayloadPacket = (ServerboundCustomPayloadPacket)object;
            return serverboundCustomPayloadPacket.getIdentifier().toString();
        }
        String[] stringArray = new String[5];
        String[] stringArray2 = e;
        stringArray[0] = "getIdentifier";
        stringArray[1] = "identifier";
        stringArray[2] = "getName";
        stringArray[3] = "id";
        stringArray[4] = "type";
        for (String string : stringArray) {
            try {
                Object object2;
                Class[] classArray = new Class[]{};
                Class<?> clazz = object.getClass();
                Method method = clazz.getMethod(ad.b(string, clazz, classArray), classArray);
                if (method.getParameterCount() != 0 || (object2 = method.invoke(object, new Object[0])) == null) continue;
                return object2.toString();
            }
            catch (LinkageError | ReflectiveOperationException throwable) {
                // empty catch block
            }
        }
        return "unknown";
    }

    private static Object b(Object object) {
        if (object instanceof ClientboundCustomPayloadPacket) {
            ClientboundCustomPayloadPacket clientboundCustomPayloadPacket = (ClientboundCustomPayloadPacket)object;
            return clientboundCustomPayloadPacket.getData();
        }
        if (object instanceof ServerboundCustomPayloadPacket) {
            ServerboundCustomPayloadPacket serverboundCustomPayloadPacket = (ServerboundCustomPayloadPacket)object;
            return serverboundCustomPayloadPacket.getData();
        }
        String[] stringArray = new String[5];
        String[] stringArray2 = e;
        stringArray[0] = "getData";
        stringArray[1] = "data";
        stringArray[2] = "getPayload";
        stringArray[3] = "payload";
        stringArray[4] = "getContents";
        for (String string : stringArray) {
            try {
                Object object2;
                Class[] classArray = new Class[]{};
                Class<?> clazz = object.getClass();
                Method method = clazz.getMethod(ad.b(string, clazz, classArray), classArray);
                if (method.getParameterCount() != 0 || (object2 = method.invoke(object, new Object[0])) == null) continue;
                return object2;
            }
            catch (LinkageError | ReflectiveOperationException throwable) {
                // empty catch block
            }
        }
        return null;
    }

    private static String a(ByteBuf byteBuf) {
        int n2 = Math.min(byteBuf.readableBytes(), 4096);
        byte[] byArray = new byte[n2];
        byteBuf.getBytes(byteBuf.readerIndex(), byArray);
        return MatchmakingProbe.a(byArray, 0, byArray.length);
    }

    private static String a(byte[] byArray, int n2, int n3) {
        try {
            String[] stringArray = e;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(byArray, n2, n3);
            byte[] byArray2 = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder(byArray2.length * 2);
            for (byte by : byArray2) {
                stringArray = e;
                stringBuilder.append(String.format("%02x", by));
            }
            return stringBuilder.toString();
        }
        catch (Exception exception) {
            return "error";
        }
    }

    private static String a(String string) {
        return string == null ? "unknown" : string.replace('\r', '_').replace('\n', '_');
    }

    private static void b(String string) {
        try {
            Path path = d;
            if (path == null) {
                String[] stringArray = e;
                String[] stringArray2 = new String[1];
                stringArray2[0] = "lf21-matchmaking-probe-" + ProcessHandle.current().pid() + ".log";
                d = path = Path.of(System.getProperty("java.io.tmpdir"), stringArray2);
            }
            Files.writeString(path, (CharSequence)(string + System.lineSeparator()), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        }
        catch (Exception exception) {
            a.debug("[MatchmakingProbe] unable to persist probe event", (Throwable)exception);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                MatchmakingProbe.c = 4096;
                var5 = new String[36];
                var3_1 = 0;
                var2_2 = "-\u000b\u007f%\\PT0\u0003(u?POW9\u000e}%TwH7\u0007q\u0016\u0013RT9\u0007x.\u0013SUx\u0015q9@NI,Ed9\\E_x\u0000b.]S\u0007(\u0004m'\\F^\u0005=\u0017f$A\fx\fp.]SS>\fq9\u000e\u0015\u0003(u?POW9\u000e}%TwH7\u0007q\u0016\u0013\\G\u0011+\u0000f=VUX7\u0010z/PRI,\ny\u0004v\t{,\rx\u0015u2_H[<1m;V\u001a\u00154\u0003&z\u001dJ[,\u0006|&RLS6\u0002D9\\E_\bx\u0016|*\u0001\u0012\fe\u0007-\u000b\u007f%\\PT\n1\u0001q%GN\\1\u0000f\u000bx\u0001}9VDN1\nzv\u0007?\u0000`\u0005RJ_\n?\u0000`\u001bR^V7\u0004p\u0007?\u0000`\u000fRS[\u0005>\u0004x8V\u00046\nz.\u0004<\u0004`*\r(\ta,ZIW=\u0016g*TB\u0006+\u0000f=VU\u000b?\u0000`\b\\IN=\u000b`8\u000b;\u0010g?\\JK-\u0000f2\r;\u0010g?\\JJ9\u001cx$RC\u00154\u0003&z\u001dJ[,\u0006|&RLS6\u0002D9\\E_\u00174\u0003&z\u001eJ[,\u0006|&RLS6\u00029;AHX=H\u0004}U&3\bx\tq%TSRe\u00021\u0001\u0004,\u001cd.\u000e2\u0004b*\u001dNUv\u0011y;WNH\r?\u0000`\u0002WBT,\fr\"VU\u0007x\u0006x*@T\u0007";
                var4_3 = "-\u000b\u007f%\\PT0\u0003(u?POW9\u000e}%TwH7\u0007q\u0016\u0013RT9\u0007x.\u0013SUx\u0015q9@NI,Ed9\\E_x\u0000b.]S\u0007(\u0004m'\\F^\u0005=\u0017f$A\fx\fp.]SS>\fq9\u000e\u0015\u0003(u?POW9\u000e}%TwH7\u0007q\u0016\u0013\\G\u0011+\u0000f=VUX7\u0010z/PRI,\ny\u0004v\t{,\rx\u0015u2_H[<1m;V\u001a\u00154\u0003&z\u001dJ[,\u0006|&RLS6\u0002D9\\E_\bx\u0016|*\u0001\u0012\fe\u0007-\u000b\u007f%\\PT\n1\u0001q%GN\\1\u0000f\u000bx\u0001}9VDN1\nzv\u0007?\u0000`\u0005RJ_\n?\u0000`\u001bR^V7\u0004p\u0007?\u0000`\u000fRS[\u0005>\u0004x8V\u00046\nz.\u0004<\u0004`*\r(\ta,ZIW=\u0016g*TB\u0006+\u0000f=VU\u000b?\u0000`\b\\IN=\u000b`8\u000b;\u0010g?\\JK-\u0000f2\r;\u0010g?\\JJ9\u001cx$RC\u00154\u0003&z\u001dJ[,\u0006|&RLS6\u0002D9\\E_\u00174\u0003&z\u001eJ[,\u0006|&RLS6\u00029;AHX=H\u0004}U&3\bx\tq%TSRe\u00021\u0001\u0004,\u001cd.\u000e2\u0004b*\u001dNUv\u0011y;WNH\r?\u0000`\u0002WBT,\fr\"VU\u0007x\u0006x*@T\u0007".length();
                var1_4 = 7;
                var0_5 = -1;
lbl8:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl13:
                // 1 sources

                while (true) {
                    var5[var3_1++] = MatchmakingProbe.a(MatchmakingProbe.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u000b-Uf\u0001\u0012\f\u0006;\t}.]S";
                    var4_3 = "\u000b-Uf\u0001\u0012\f\u0006;\t}.]S".length();
                    var1_4 = 7;
                    var0_5 = -1;
lbl22:
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
lbl27:
                // 1 sources

                while (true) {
                    var5[var3_1++] = MatchmakingProbe.a(MatchmakingProbe.c(v1));
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
lbl38:
                // 1 sources

                ** continue;
            }
        }
        MatchmakingProbe.e = var5;
        MatchmakingProbe.b = MatchmakingProbe.e[9];
        MatchmakingProbe.a = LogManager.getLogger(MatchmakingProbe.class);
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x3A);
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
                    case 0 -> 88;
                    case 1 -> 101;
                    case 2 -> 20;
                    case 3 -> 75;
                    case 4 -> 51;
                    case 5 -> 39;
                    default -> 58;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

