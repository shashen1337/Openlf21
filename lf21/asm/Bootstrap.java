/*
 * Decompiled with CFR 0.152.
 */
package lf21.asm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lf21.LF21Client;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Bootstrap {
    private static final Logger LOGGER;
    private static final Map<String, String> METHOD_MOJ_TO_SRG;
    private static final Map<String, String> FIELD_MOJ_TO_SRG;
    private static final Map<String, String> METHOD_FALLBACK;
    private static final Map<String, String> FIELD_FALLBACK;
    private static final String AMBIGUOUS;
    private static volatile boolean initialized;
    private static volatile boolean runtimeIsSrg;

    private Bootstrap() {
    }

    public static synchronized void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        long l2 = System.nanoTime();
        runtimeIsSrg = Bootstrap.detectSrgRuntime();
        if (runtimeIsSrg) {
            Bootstrap.loadMappings();
        }
        LF21Client.isMCPMapped = runtimeIsSrg;
        long l3 = (System.nanoTime() - l2) / 1000000L;
        LOGGER.info("Runtime mapping = {} (methods={}, fields={}, loaded in {} ms)", (Object)(runtimeIsSrg ? "SRG" : "mojmap"), (Object)METHOD_MOJ_TO_SRG.size(), (Object)FIELD_MOJ_TO_SRG.size(), (Object)l3);
    }

    private static boolean detectSrgRuntime() {
        try {
            Minecraft.class.getDeclaredMethod("tick", new Class[0]);
            return false;
        }
        catch (NoSuchMethodException noSuchMethodException) {
            return true;
        }
        catch (Throwable throwable) {
            LOGGER.warn("Could not probe Minecraft.tick for mapping detection: {}", (Object)throwable.toString());
            return true;
        }
    }

    private static InputStream openMappingStream() {
        File file;
        InputStream inputStream = Bootstrap.class.getResourceAsStream("/mapping.srg");
        if (inputStream != null) {
            return inputStream;
        }
        String string = System.getProperty("lf21.resources");
        if (string != null && (file = new File(string, "mapping.srg")).isFile()) {
            try {
                return new FileInputStream(file);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return null;
    }

    private static void loadMappings() {
        try (InputStream inputStream = Bootstrap.openMappingStream();){
            if (inputStream == null) {
                LOGGER.warn("mapping.srg not found (classpath nor lf21.resources) \u2014 runtime remap disabled");
                return;
            }
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));){
                String string;
                while ((string = bufferedReader.readLine()) != null) {
                    if (string.length() < 4) continue;
                    char c2 = string.charAt(0);
                    char c3 = string.charAt(1);
                    if (c2 != 'M' && c2 != 'F' || c3 != 'D' || string.charAt(2) != ':') continue;
                    Bootstrap.parseLine(string);
                }
            }
        }
        catch (Exception exception) {
            LOGGER.error("Failed to load mapping.srg", (Throwable)exception);
        }
    }

    private static void parseLine(String string3) {
        String[] stringArray = string3.split(" ");
        if (string3.charAt(0) == 'M') {
            String string4;
            if (stringArray.length < 5) {
                return;
            }
            String string5 = stringArray[1];
            String string6 = stringArray[3];
            String string7 = stringArray[4];
            int n2 = string5.lastIndexOf(47);
            int n3 = string6.lastIndexOf(47);
            if (n2 < 0 || n3 < 0) {
                return;
            }
            String string8 = string6.substring(0, n3);
            String string9 = string6.substring(n3 + 1);
            if (string9.equals(string4 = string5.substring(n2 + 1))) {
                return;
            }
            METHOD_MOJ_TO_SRG.put(string8 + "/" + string9 + string7, string4);
            String string10 = string9 + string7;
            METHOD_FALLBACK.merge(string10, string4, (string, string2) -> string.equals(string2) ? string : "\u0000AMBIGUOUS\u0000");
        } else {
            String string11;
            if (stringArray.length < 3) {
                return;
            }
            String string12 = stringArray[1];
            String string13 = stringArray[2];
            int n4 = string12.lastIndexOf(47);
            int n5 = string13.lastIndexOf(47);
            if (n4 < 0 || n5 < 0) {
                return;
            }
            String string14 = string13.substring(0, n5);
            String string15 = string13.substring(n5 + 1);
            if (string15.equals(string11 = string12.substring(n4 + 1))) {
                return;
            }
            FIELD_MOJ_TO_SRG.put(string14 + "/" + string15, string11);
            FIELD_FALLBACK.merge(string15, string11, (string, string2) -> string.equals(string2) ? string : "\u0000AMBIGUOUS\u0000");
        }
    }

    public static String remapMethod(String string, String string2, String string3) {
        if (!runtimeIsSrg) {
            return string2;
        }
        String string4 = METHOD_MOJ_TO_SRG.get(string + "/" + string2 + string3);
        if (string4 != null) {
            return string4;
        }
        String string5 = METHOD_FALLBACK.get(string2 + string3);
        if (string5 != null && string5 != "\u0000AMBIGUOUS\u0000") {
            return string5;
        }
        return string2;
    }

    public static String remapField(String string, String string2) {
        if (!runtimeIsSrg) {
            return string2;
        }
        String string3 = FIELD_MOJ_TO_SRG.get(string + "/" + string2);
        if (string3 != null) {
            return string3;
        }
        String string4 = FIELD_FALLBACK.get(string2);
        if (string4 != null && string4 != "\u0000AMBIGUOUS\u0000") {
            return string4;
        }
        return string2;
    }

    public static boolean isRuntimeSrg() {
        return runtimeIsSrg;
    }

    static {
        AMBIGUOUS = "\u0000AMBIGUOUS\u0000";
        LOGGER = LogManager.getLogger(Bootstrap.class);
        METHOD_MOJ_TO_SRG = new HashMap<String, String>();
        FIELD_MOJ_TO_SRG = new HashMap<String, String>();
        METHOD_FALLBACK = new HashMap<String, String>();
        FIELD_FALLBACK = new HashMap<String, String>();
        initialized = false;
        runtimeIsSrg = false;
    }
}

