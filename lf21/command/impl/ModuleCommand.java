/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lf21.LF21Client;
import lf21.command.Command;
import lf21.exception.ModuleNotFoundException;
import lf21.modules.Module;
import lf21.settings.Setting;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.misc.ChatUtil;

public final class ModuleCommand
extends Command {
    public ModuleCommand() {
        super("module", new String[]{"m", "set"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        Module module;
        if (stringArray.length < 1) {
            ChatUtil.print("\u7528\u6cd5: .module <\u6a21\u5757> [\u9009\u9879] [\u503c]");
            return;
        }
        try {
            module = LF21Client.getInstance().getModuleManager().getModule(stringArray[0]);
        }
        catch (ModuleNotFoundException moduleNotFoundException) {
            ChatUtil.print("\u6a21\u5757\u4e0d\u5b58\u5728: " + stringArray[0]);
            return;
        }
        if (stringArray.length == 1) {
            ChatUtil.print(module.getName() + " \u7684\u9009\u9879:");
            for (Setting<?> setting : module.getSettings()) {
                ChatUtil.print("- " + setting.getName() + " = " + ModuleCommand.displayValue(setting));
            }
            return;
        }
        String string = ModuleCommand.join(stringArray, 1, stringArray.length);
        Setting<?> setting = ModuleCommand.findSetting(module, string);
        int n2 = -1;
        if (setting == null) {
            for (int i2 = stringArray.length - 1; i2 >= 2; --i2) {
                setting = ModuleCommand.findSetting(module, ModuleCommand.join(stringArray, 1, i2));
                if (setting == null) continue;
                n2 = i2;
                break;
            }
        }
        if (setting == null) {
            ChatUtil.print("\u9009\u9879\u4e0d\u5b58\u5728: " + string);
            return;
        }
        if (n2 < 0) {
            ChatUtil.print(module.getName() + " -> " + setting.getName() + " = " + ModuleCommand.displayValue(setting));
            return;
        }
        String string2 = ModuleCommand.join(stringArray, n2, stringArray.length);
        if (!ModuleCommand.setValue(setting, string2)) {
            return;
        }
        LF21Client.getInstance().getConfigManager().saveAll();
        ChatUtil.print("\u5df2\u8bbe\u7f6e " + module.getName() + " -> " + setting.getName() + " = " + ModuleCommand.displayValue(setting));
    }

    private static Setting<?> findSetting(Module module, String string) {
        String string2 = ModuleCommand.normalize(string);
        return module.getSettings().stream().filter(setting -> ModuleCommand.normalize(setting.getName()).equals(string2)).findFirst().orElse(null);
    }

    private static boolean setValue(Setting<?> setting, String string) {
        if (setting instanceof BooleanSetting) {
            BooleanSetting booleanSetting = (BooleanSetting)setting;
            if (!(string.equalsIgnoreCase("true") || string.equalsIgnoreCase("false") || string.equalsIgnoreCase("on") || string.equalsIgnoreCase("off"))) {
                ChatUtil.print("\u5e03\u5c14\u503c\u5e94\u4e3a true/false \u6216 on/off");
                return false;
            }
            booleanSetting.setValue(string.equalsIgnoreCase("true") || string.equalsIgnoreCase("on"));
            return true;
        }
        if (setting instanceof NumberSetting) {
            NumberSetting numberSetting = (NumberSetting)setting;
            try {
                double d2 = Double.parseDouble(string);
                double d3 = numberSetting.getMin().doubleValue();
                double d4 = numberSetting.getMax().doubleValue();
                double d5 = numberSetting.getStep().doubleValue();
                d2 = Math.max(d3, Math.min(d4, d2));
                if (d5 > 0.0) {
                    d2 = (double)Math.round((d2 - d3) / d5) * d5 + d3;
                }
                numberSetting.setValue(d2);
                return true;
            }
            catch (NumberFormatException numberFormatException) {
                ChatUtil.print("\u6570\u503c\u683c\u5f0f\u9519\u8bef: " + string);
                return false;
            }
        }
        if (setting instanceof ModeSetting) {
            ModeSetting modeSetting = (ModeSetting)setting;
            String string3 = Arrays.stream(modeSetting.getModes()).filter(string2 -> ModuleCommand.normalize(string2).equals(ModuleCommand.normalize(string))).findFirst().orElse(null);
            if (string3 == null) {
                ChatUtil.print("\u53ef\u9009\u6a21\u5f0f: " + String.join((CharSequence)", ", modeSetting.getModes()));
                return false;
            }
            modeSetting.setValue(string3);
            return true;
        }
        if (setting instanceof MultiSelectSetting) {
            MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
            String string4 = multiSelectSetting.getOptions().stream().filter(string2 -> ModuleCommand.normalize(string2).equals(ModuleCommand.normalize(string))).findFirst().orElse(null);
            if (string4 == null) {
                ChatUtil.print("\u53ef\u9009\u9879\u76ee: " + String.join((CharSequence)", ", multiSelectSetting.getOptions()));
                return false;
            }
            ArrayList<String> arrayList = new ArrayList<String>((Collection)multiSelectSetting.getValue());
            if (arrayList.contains(string4)) {
                arrayList.remove(string4);
            } else {
                arrayList.add(string4);
            }
            multiSelectSetting.setValue(arrayList);
            return true;
        }
        ChatUtil.print("\u6b64\u9009\u9879\u7c7b\u578b\u6682\u4e0d\u652f\u6301\u804a\u5929\u547d\u4ee4\u4fee\u6539");
        return false;
    }

    private static String displayValue(Setting<?> setting) {
        if (setting instanceof MultiSelectSetting) {
            MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
            return ((List)multiSelectSetting.getValue()).isEmpty() ? "\u65e0" : String.join((CharSequence)", ", (Iterable)multiSelectSetting.getValue());
        }
        return String.valueOf(setting.getValue());
    }

    private static String normalize(String string) {
        return string.replace(" ", "").replace("_", "").replace("-", "").toLowerCase(Locale.ROOT);
    }

    private static String join(String[] stringArray, int n2, int n3) {
        return Arrays.stream(stringArray, n2, n3).collect(Collectors.joining(" "));
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return new String[0];
    }
}

