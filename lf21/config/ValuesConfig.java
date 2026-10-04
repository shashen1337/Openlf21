/*
 * Decompiled with CFR 0.152.
 */
package lf21.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import lf21.LF21Client;
import lf21.config.Config;
import lf21.hud.HudElement;
import lf21.hud.HudElement$InternalPartType;
import lf21.hud.HudElement$PersistedPart;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValuesConfig
extends Config {
    private static final Logger LOGGER = LogManager.getLogger(ValuesConfig.class);

    public ValuesConfig() {
        super("values.cfg");
    }

    @Override
    public void read(BufferedReader bufferedReader) throws IOException {
        String string;
        while ((string = bufferedReader.readLine()) != null) {
            try {
                String[] stringArray = string.split(":", 4);
                if (stringArray.length != 4) {
                    LOGGER.error("Failed to read line {}!", (Object)string);
                    continue;
                }
                String string2 = stringArray[0];
                String string3 = stringArray[1];
                String string4 = "AutoWebPlace".equalsIgnoreCase(string3) ? "AutoWeb" : string3;
                String string5 = stringArray[2];
                String string6 = stringArray[3];
                Module module = LF21Client.getInstance().getModuleManager().getModule(string4);
                if (module == null) continue;
                if ("ChestESP".equalsIgnoreCase(string4) && "Unopened Saturation".equalsIgnoreCase(string5)) {
                    string5 = "Saturation";
                }
                if ("AntiKB".equalsIgnoreCase(string4)) {
                    if ("Client Mode".equalsIgnoreCase(string5)) {
                        if (!"EdNaven".equalsIgnoreCase(string6)) continue;
                        string5 = "Mode";
                        string6 = "AttackReduce";
                    } else if ("LF21 Mode".equalsIgnoreCase(string5)) {
                        string5 = "Mode";
                        if ("NoXZ".equalsIgnoreCase(string6)) {
                            string6 = "AttackReduce";
                        }
                    } else if ("EdNaven Mode".equalsIgnoreCase(string5)) {
                        string5 = "Mode";
                        string6 = "AttackReduce";
                    } else if ("Mode".equalsIgnoreCase(string5) && "NoXZ".equalsIgnoreCase(string6)) {
                        string6 = "AttackReduce";
                    }
                }
                String string7 = string5;
                String string8 = string6;
                String string9 = string;
                if (module instanceof HudElement) {
                    HudElement hudElement = (HudElement)module;
                    try {
                        if (string2.equals("P")) {
                            String[] stringArray2;
                            if (string5.equals("X")) {
                                hudElement.setX(Float.parseFloat(string8));
                            }
                            if (string5.equals("Y")) {
                                hudElement.setY(Float.parseFloat(string8));
                            }
                            if (string5.equals("S")) {
                                hudElement.setScale(Float.parseFloat(string8));
                            }
                            if (string5.equals("TS")) {
                                hudElement.setTextScale(Float.parseFloat(string8));
                            }
                            if (string5.equals("TX")) {
                                hudElement.setTextOffsetX(Float.parseFloat(string8));
                            }
                            if (string5.equals("TY")) {
                                hudElement.setTextOffsetY(Float.parseFloat(string8));
                            }
                            if (string5.equals("PS")) {
                                hudElement.setProgressScale(Float.parseFloat(string8));
                            }
                            if (string5.equals("PX")) {
                                hudElement.setProgressOffsetX(Float.parseFloat(string8));
                            }
                            if (string5.equals("PY")) {
                                hudElement.setProgressOffsetY(Float.parseFloat(string8));
                            }
                            if ((string5.startsWith("IT_") || string5.startsWith("IP_")) && (stringArray2 = string5.split("_", 3)).length == 3) {
                                HudElement$InternalPartType hudElement$InternalPartType = stringArray2[0].equals("IT") ? HudElement$InternalPartType.TEXT : HudElement$InternalPartType.PROGRESS;
                                String string10 = stringArray2[1];
                                float f2 = hudElement.getInternalPartScale(string10, hudElement$InternalPartType);
                                float f3 = hudElement.getInternalPartOffsetX(string10, hudElement$InternalPartType);
                                float f4 = hudElement.getInternalPartOffsetY(string10, hudElement$InternalPartType);
                                switch (stringArray2[2]) {
                                    case "S": {
                                        f2 = Float.parseFloat(string8);
                                        break;
                                    }
                                    case "X": {
                                        f3 = Float.parseFloat(string8);
                                        break;
                                    }
                                    case "Y": {
                                        f4 = Float.parseFloat(string8);
                                    }
                                }
                                hudElement.restoreInternalPart(string10, hudElement$InternalPartType, f2, f3, f4);
                            }
                        }
                    }
                    catch (Exception exception) {
                        LOGGER.error("Failed to set position of {}!", (Object)hudElement.getName());
                    }
                }
                module.getSettings().forEach(setting -> {
                    if (!string7.equals(setting.getName())) {
                        return;
                    }
                    switch (string2) {
                        case "B": {
                            ((BooleanSetting)setting).setValue(Boolean.parseBoolean(string8));
                            break;
                        }
                        case "F": {
                            ((NumberSetting)setting).setValue(Float.valueOf(Float.parseFloat(string8)));
                            break;
                        }
                        case "MV": {
                            MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
                            if (string8.isEmpty()) {
                                multiSelectSetting.setValue(new ArrayList());
                                break;
                            }
                            multiSelectSetting.setValue(new ArrayList<String>(Arrays.asList(string8.split(","))));
                            break;
                        }
                        case "M": {
                            ModeSetting modeSetting = (ModeSetting)setting;
                            if (string8.isEmpty()) {
                                LOGGER.error("Failed to read mode value {}!", (Object)string9);
                                break;
                            }
                            modeSetting.setValue(string8);
                            break;
                        }
                        default: {
                            LOGGER.error("Unknown value type of {}!", (Object)string3);
                        }
                    }
                });
            }
            catch (Exception exception) {
                LOGGER.error("Failed to read value {}!: {}", (Object)string, (Object)exception);
            }
        }
    }

    @Override
    public void save(BufferedWriter bufferedWriter) throws IOException {
        for (Module module : LF21Client.getInstance().getModuleManager().getModules()) {
            if (module instanceof HudElement) {
                HudElement hudElement = (HudElement)module;
                bufferedWriter.write(String.format("P:%s:X:%s\n", module.getName(), Float.valueOf(hudElement.getX())));
                bufferedWriter.write(String.format("P:%s:Y:%s\n", module.getName(), Float.valueOf(hudElement.getY())));
                bufferedWriter.write(String.format("P:%s:S:%s\n", module.getName(), Float.valueOf(hudElement.getScale())));
                bufferedWriter.write(String.format("P:%s:TS:%s\n", module.getName(), Float.valueOf(hudElement.getTextScale())));
                bufferedWriter.write(String.format("P:%s:TX:%s\n", module.getName(), Float.valueOf(hudElement.getTextOffsetX())));
                bufferedWriter.write(String.format("P:%s:TY:%s\n", module.getName(), Float.valueOf(hudElement.getTextOffsetY())));
                bufferedWriter.write(String.format("P:%s:PS:%s\n", module.getName(), Float.valueOf(hudElement.getProgressScale())));
                bufferedWriter.write(String.format("P:%s:PX:%s\n", module.getName(), Float.valueOf(hudElement.getProgressOffsetX())));
                bufferedWriter.write(String.format("P:%s:PY:%s\n", module.getName(), Float.valueOf(hudElement.getProgressOffsetY())));
                for (HudElement$PersistedPart hudElement$PersistedPart : hudElement.getPersistedInternalParts()) {
                    String string = hudElement$PersistedPart.type() == HudElement$InternalPartType.TEXT ? "IT" : "IP";
                    bufferedWriter.write(String.format("P:%s:%s_%s_S:%s\n", module.getName(), string, hudElement$PersistedPart.id(), Float.valueOf(hudElement$PersistedPart.scale())));
                    bufferedWriter.write(String.format("P:%s:%s_%s_X:%s\n", module.getName(), string, hudElement$PersistedPart.id(), Float.valueOf(hudElement$PersistedPart.offsetX())));
                    bufferedWriter.write(String.format("P:%s:%s_%s_Y:%s\n", module.getName(), string, hudElement$PersistedPart.id(), Float.valueOf(hudElement$PersistedPart.offsetY())));
                }
            }
            module.getSettings().forEach(setting -> {
                try {
                    if (setting instanceof BooleanSetting) {
                        BooleanSetting booleanSetting = (BooleanSetting)setting;
                        bufferedWriter.write(String.format("B:%s:%s:%s\n", module.getName(), booleanSetting.getName(), booleanSetting.getValue()));
                    } else if (setting instanceof NumberSetting) {
                        NumberSetting numberSetting = (NumberSetting)setting;
                        bufferedWriter.write(String.format("F:%s:%s:%s\n", module.getName(), numberSetting.getName(), numberSetting.getValue()));
                    } else if (setting instanceof ModeSetting) {
                        ModeSetting modeSetting = (ModeSetting)setting;
                        bufferedWriter.write(String.format("M:%s:%s:%s\n", module.getName(), modeSetting.getName(), modeSetting.getValue()));
                    } else if (setting instanceof MultiSelectSetting) {
                        MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
                        bufferedWriter.write(String.format("MV:%s:%s:%s\n", module.getName(), multiSelectSetting.getName(), String.join((CharSequence)",", (Iterable)multiSelectSetting.getValue())));
                    } else {
                        LOGGER.error("Unknown value type of {}!", (Object)setting.getName());
                    }
                }
                catch (Exception exception) {
                    LOGGER.error("Failed to save value {}!", (Object)setting.getName());
                }
            });
        }
    }
}

