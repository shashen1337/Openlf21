/*
 * Decompiled with CFR 0.152.
 */
package lf21.hwid;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import lf21.hwid.HardwareSnapshot;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

public final class HardwareCollector {
    private static final String a;
    private static final int b;
    private static final String[] c;

    private HardwareCollector() {
    }

    public static HardwareSnapshot collect() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
        CentralProcessor.ProcessorIdentifier processorIdentifier = centralProcessor.getProcessorIdentifier();
        String[] stringArray = c;
        String string = HardwareCollector.a(" | ", processorIdentifier.getVendor(), processorIdentifier.getName());
        String string2 = HardwareCollector.c(processorIdentifier.getIdentifier());
        String string3 = HardwareCollector.c(processorIdentifier.getProcessorID());
        ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
        Firmware firmware = computerSystem.getFirmware();
        Baseboard baseboard = computerSystem.getBaseboard();
        String string4 = HardwareCollector.c(computerSystem.getHardwareUUID());
        String string5 = " | ";
        Object[] objectArray = new Object[7];
        objectArray[0] = computerSystem.getManufacturer();
        objectArray[1] = computerSystem.getModel();
        objectArray[2] = computerSystem.getSerialNumber();
        objectArray[3] = firmware.getManufacturer();
        objectArray[4] = firmware.getName();
        objectArray[5] = firmware.getVersion();
        Object[] objectArray2 = objectArray;
        Object[] objectArray3 = objectArray;
        int n2 = 6;
        String string6 = firmware.getReleaseDate() == null ? "" : firmware.getReleaseDate().toString();
        objectArray2[n2] = string6;
        String string7 = HardwareCollector.a(string5, objectArray3);
        String string8 = HardwareCollector.a(" | ", baseboard.getManufacturer(), baseboard.getModel(), baseboard.getVersion(), baseboard.getSerialNumber());
        String string9 = HardwareCollector.a();
        List<String> list = HardwareCollector.a(hardwareAbstractionLayer);
        List<String> list2 = HardwareCollector.b(hardwareAbstractionLayer);
        List<String> list3 = HardwareCollector.c(hardwareAbstractionLayer);
        String string10 = HardwareCollector.a(string, string2, string3, list, string4, string9, string7, string8, list2, list3);
        String string11 = HardwareCollector.a(string10);
        String string12 = HardwareCollector.b(string11.substring(0, 32));
        return new HardwareSnapshot(string, string2, string3, List.copyOf(list), string4, string9, string7, string8, List.copyOf(list2), List.copyOf(list3), string10, string11, string12);
    }

    private static List<String> a(HardwareAbstractionLayer hardwareAbstractionLayer) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (GraphicsCard graphicsCard : hardwareAbstractionLayer.getGraphicsCards()) {
            String string = " | ";
            Object[] objectArray = new Object[5];
            objectArray[0] = graphicsCard.getVendor();
            objectArray[1] = graphicsCard.getName();
            objectArray[2] = graphicsCard.getDeviceId();
            objectArray[3] = graphicsCard.getVersionInfo();
            Object[] objectArray2 = objectArray;
            Object[] objectArray3 = objectArray;
            int n2 = 4;
            String string2 = graphicsCard.getVRam() > 0L ? Long.toUnsignedString(graphicsCard.getVRam()) : "";
            objectArray2[n2] = string2;
            String string3 = HardwareCollector.a(string, objectArray3);
            HardwareCollector.a(arrayList, string3);
        }
        arrayList.sort(String.CASE_INSENSITIVE_ORDER);
        return HardwareCollector.a(arrayList);
    }

    private static List<String> b(HardwareAbstractionLayer hardwareAbstractionLayer) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (NetworkIF networkIF : hardwareAbstractionLayer.getNetworkIFs()) {
            networkIF.updateAttributes();
            String string = HardwareCollector.f(networkIF.getMacaddr());
            String string2 = HardwareCollector.a(" ", networkIF.getName(), networkIF.getDisplayName());
            if (string.isEmpty()) continue;
            if (HardwareCollector.g(string2)) {
                continue;
            }
            HardwareCollector.a(arrayList, HardwareCollector.c(string2) + " | " + string);
        }
        arrayList.sort(Comparator.comparing(HardwareCollector::h));
        return HardwareCollector.a(arrayList);
    }

    private static List<String> c(HardwareAbstractionLayer hardwareAbstractionLayer) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (HWDiskStore hWDiskStore : hardwareAbstractionLayer.getDiskStores()) {
            String string = " | ";
            Object[] objectArray = new Object[4];
            objectArray[0] = hWDiskStore.getModel();
            objectArray[1] = hWDiskStore.getSerial();
            objectArray[2] = hWDiskStore.getName();
            Object[] objectArray2 = objectArray;
            Object[] objectArray3 = objectArray;
            int n2 = 3;
            String string2 = hWDiskStore.getSize() > 0L ? Long.toUnsignedString(hWDiskStore.getSize()) : "";
            objectArray2[n2] = string2;
            String string3 = HardwareCollector.a(string, objectArray3);
            HardwareCollector.a(arrayList, string3);
        }
        arrayList.sort(String.CASE_INSENSITIVE_ORDER);
        return HardwareCollector.a(arrayList);
    }

    private static String a() {
        try {
            String[] stringArray = c;
            return HardwareCollector.c(Advapi32Util.registryGetStringValue((WinReg.HKEY)WinReg.HKEY_LOCAL_MACHINE, (String)"SOFTWARE\\Microsoft\\Cryptography", (String)"MachineGuid"));
        }
        catch (RuntimeException runtimeException) {
            return "unavailable";
        }
    }

    private static String a(String string, String string2, String string3, List<String> list, String string4, String string5, String string6, String string7, List<String> list2, List<String> list3) {
        String[] stringArray = c;
        StringBuilder stringBuilder = new StringBuilder("hwid-inspector-v1\n");
        HardwareCollector.a(stringBuilder, "cpu", string);
        HardwareCollector.a(stringBuilder, "cpu_identifier", string2);
        HardwareCollector.a(stringBuilder, "processor_id", string3);
        HardwareCollector.a(stringBuilder, "system_uuid", string4);
        HardwareCollector.a(stringBuilder, "machine_guid", string5);
        HardwareCollector.a(stringBuilder, "bios", string6);
        HardwareCollector.a(stringBuilder, "baseboard", string7);
        HardwareCollector.a(stringBuilder, "gpu", list);
        HardwareCollector.a(stringBuilder, "mac", list2);
        HardwareCollector.a(stringBuilder, "disk", list3);
        return stringBuilder.toString();
    }

    private static void a(StringBuilder stringBuilder, String string, String string2) {
        stringBuilder.append(string).append('=').append(HardwareCollector.e(string2)).append('\n');
    }

    private static void a(StringBuilder stringBuilder, String string2, List<String> list) {
        List<String> list2 = list.stream().map(HardwareCollector::e).filter(string -> {
            boolean bl = !string.isEmpty();
            return bl;
        }).sorted().toList();
        for (int i2 = 0; i2 < list2.size(); ++i2) {
            stringBuilder.append(string2).append('[').append(i2).append("]=").append(list2.get(i2)).append('\n');
        }
    }

    private static String a(String string) {
        try {
            String[] stringArray = c;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] byArray = messageDigest.digest(string.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder(byArray.length * 2);
            for (byte by : byArray) {
                stringArray = c;
                stringBuilder.append(String.format(Locale.ROOT, "%02X", by & 0xFF));
            }
            return stringBuilder.toString();
        }
        catch (Exception exception) {
            throw new IllegalStateException("SHA-256 unavailable", exception);
        }
    }

    private static String b(String string) {
        StringJoiner stringJoiner = new StringJoiner("-");
        for (int i2 = 0; i2 < string.length(); i2 += 4) {
            stringJoiner.add(string.substring(i2, Math.min(i2 + 4, string.length())));
        }
        return stringJoiner.toString();
    }

    private static void a(List<String> list, String string) {
        block4: {
            String string2 = HardwareCollector.c(string);
            if (string2.isEmpty()) break block4;
            if (!list.stream().noneMatch(string2::equalsIgnoreCase)) break block4;
            list.add(string2);
        }
    }

    private static List<String> a(List<String> arrayList) {
        ArrayList arrayList2 = arrayList.size() <= 16 ? arrayList : new ArrayList(arrayList.subList(0, 16));
        return arrayList2;
    }

    private static String a(String string, Object ... objectArray) {
        StringJoiner stringJoiner = new StringJoiner(string);
        for (Object object : objectArray) {
            String string2 = object == null ? "" : object.toString();
            String string3 = HardwareCollector.c(string2);
            if (string3.isEmpty() || HardwareCollector.d(string3)) continue;
            stringJoiner.add(string3);
        }
        return stringJoiner.toString();
    }

    private static String c(String string) {
        if (string == null) {
            return "";
        }
        return string.replace('\u0000', ' ').replaceAll("\\s+", " ").trim();
    }

    private static boolean d(String string) {
        boolean bl;
        block14: {
            block13: {
                block12: {
                    String string2 = string.toLowerCase(Locale.ROOT);
                    if (string2.equals("unknown") || string2.equals("none")) break block12;
                    if (string2.equals("null")) break block12;
                    if (string2.equals("default string")) break block12;
                    if (string2.equals("to be filled by o.e.m.")) break block12;
                    if (!string2.equals("n/a")) break block13;
                }
                bl = true;
                break block14;
            }
            bl = false;
        }
        return bl;
    }

    private static String e(String string) {
        String string2 = HardwareCollector.c(string).toLowerCase(Locale.ROOT);
        String string3 = HardwareCollector.d(string2) ? "" : string2;
        return string3;
    }

    private static String f(String string) {
        String string2;
        block4: {
            string2 = HardwareCollector.c(string).toUpperCase(Locale.ROOT).replace('-', ':');
            if (!string2.isEmpty() && !string2.equals("00:00:00:00:00:00")) break block4;
            return "";
        }
        return string2;
    }

    private static boolean g(String string) {
        boolean bl;
        block20: {
            block19: {
                block18: {
                    String string2 = HardwareCollector.c(string).toLowerCase(Locale.ROOT);
                    if (string2.contains("virtual") || string2.contains("vmware")) break block18;
                    if (string2.contains("hyper-v")) break block18;
                    if (string2.contains("vethernet")) break block18;
                    if (string2.contains("loopback")) break block18;
                    if (string2.contains("tunnel")) break block18;
                    if (string2.contains("vpn")) break block18;
                    if (string2.contains("pseudo")) break block18;
                    if (!string2.contains("bluetooth")) break block19;
                }
                bl = true;
                break block20;
            }
            bl = false;
        }
        return bl;
    }

    private static String h(String string) {
        int n2 = string.lastIndexOf(124);
        String string2 = n2 >= 0 ? string.substring(n2 + 1).trim() : string;
        return string2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                HardwareCollector.b = 16;
                var5 = new String[38];
                var3_1 = 0;
                var2_2 = "\fl\u001b\u001a_\u0014\u0000\u001f}\b\u0016f\u0000\u0002X\u0007\r\u0011\u0003\u001dy\u001a\u0007\u0012p\u001f\u0017HK\u0018\u0004\u0018`\u0000\u0001\u0003ZuO\f\u0017h\f\u001aS\b\u000b%n\u001a\u001b^\f\n{\u0000\u0011_\u0015\u001d\u0015{0\u001b^\u0006\nz\n\u0007^\t\u0004\u0014|\u0003\u001e\u0003\u0019y\u001a\u0006\u000e|\u0001\u001c_\n\u0004\u001e`\u001c\u0019\t\u0018h\u001c\u0017X\t\u000f\bm\u0003\fy\u0001\u0003\u0014&\u000e\u0007)A._\bSX\u0012\u0012~\u0006\u0016\u0017\u000f\u0000\ty\n\u0011N\t\u001cW\u007f^x\u0002'4\u0004\u0014f\u0001\u0017\u0011J9UB\n\\^J3_B\u0000V^@9_\u0006\fd\u0018\u0013H\u0003\u000e\u0019y\u001a-S\u0002\u000b\u0014}\u0006\u0014S\u0003\u001c\u000b7h\f\u001aS\b\u000b=|\u0006\u0016\u001f)F)&m'<?U\"\u001bY\u0014\u0001\tf\t\u0006f%\u001c\u0003y\u001b\u001d]\u0014\u000f\na\u0016\t\u0018e\u001a\u0017N\t\u0001\u000ea\u0003&zD\u0007\u000fg\u0004\u001cU\u0011\u0000\u0007\f`\u001d\u0006O\u0007\u0002\u000b\tp\u001c\u0006_\u000b1\u000f|\u0006\u0016\u0003ZuO\u0003\u0017h\f\u001f)F)&m'<?U\"\u001bY\u0014\u0001\tf\t\u0006f%\u001c\u0003y\u001b\u001d]\u0014\u000f\na\u0016\u0016\u000efO\u0010_F\b\u0013e\u0003\u0017^F\f\u0003)\u0000\\_H\u0003T\u000e\u001el\t\u0013O\n\u001aZz\u001b\u0000S\b\t\u000b\u000fg\u000e\u0004[\u000f\u0002\u001bk\u0003\u0017";
                var4_3 = "\fl\u001b\u001a_\u0014\u0000\u001f}\b\u0016f\u0000\u0002X\u0007\r\u0011\u0003\u001dy\u001a\u0007\u0012p\u001f\u0017HK\u0018\u0004\u0018`\u0000\u0001\u0003ZuO\f\u0017h\f\u001aS\b\u000b%n\u001a\u001b^\f\n{\u0000\u0011_\u0015\u001d\u0015{0\u001b^\u0006\nz\n\u0007^\t\u0004\u0014|\u0003\u001e\u0003\u0019y\u001a\u0006\u000e|\u0001\u001c_\n\u0004\u001e`\u001c\u0019\t\u0018h\u001c\u0017X\t\u000f\bm\u0003\fy\u0001\u0003\u0014&\u000e\u0007)A._\bSX\u0012\u0012~\u0006\u0016\u0017\u000f\u0000\ty\n\u0011N\t\u001cW\u007f^x\u0002'4\u0004\u0014f\u0001\u0017\u0011J9UB\n\\^J3_B\u0000V^@9_\u0006\fd\u0018\u0013H\u0003\u000e\u0019y\u001a-S\u0002\u000b\u0014}\u0006\u0014S\u0003\u001c\u000b7h\f\u001aS\b\u000b=|\u0006\u0016\u001f)F)&m'<?U\"\u001bY\u0014\u0001\tf\t\u0006f%\u001c\u0003y\u001b\u001d]\u0014\u000f\na\u0016\t\u0018e\u001a\u0017N\t\u0001\u000ea\u0003&zD\u0007\u000fg\u0004\u001cU\u0011\u0000\u0007\f`\u001d\u0006O\u0007\u0002\u000b\tp\u001c\u0006_\u000b1\u000f|\u0006\u0016\u0003ZuO\u0003\u0017h\f\u001f)F)&m'<?U\"\u001bY\u0014\u0001\tf\t\u0006f%\u001c\u0003y\u001b\u001d]\u0014\u000f\na\u0016\u0016\u000efO\u0010_F\b\u0013e\u0003\u0017^F\f\u0003)\u0000\\_H\u0003T\u000e\u001el\t\u0013O\n\u001aZz\u001b\u0000S\b\t\u000b\u000fg\u000e\u0004[\u000f\u0002\u001bk\u0003\u0017".length();
                var1_4 = 9;
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
                    var5[var3_1++] = HardwareCollector.a(HardwareCollector.j(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = ")A._\bSXZ|\u0001\u0013L\u0007\u0007\u0016h\r\u001e_\u0004_9]*";
                    var4_3 = ")A._\bSXZ|\u0001\u0013L\u0007\u0007\u0016h\r\u001e_\u0004_9]*".length();
                    var1_4 = 19;
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
                    var5[var3_1++] = HardwareCollector.a(HardwareCollector.j(v1));
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
        HardwareCollector.c = var5;
        HardwareCollector.a = HardwareCollector.c[32];
    }

    private static char[] j(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6E);
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
                    case 0 -> 122;
                    case 1 -> 9;
                    case 2 -> 111;
                    case 3 -> 114;
                    case 4 -> 58;
                    case 5 -> 102;
                    default -> 110;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

