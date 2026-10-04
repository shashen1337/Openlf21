/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import java.io.IOException;
import lf21.LF21Client;
import lf21.command.Command;
import lf21.utils.misc.ChatUtil;

public class ConfigCommand
extends Command {
    private static final long RESET_CONFIRMATION_MILLIS = 30000L;
    private static volatile long resetConfirmationDeadline;

    public ConfigCommand() {
        super("config", new String[]{"cfg"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        if (stringArray.length == 1) {
            switch (stringArray[0]) {
                case "reload": {
                    LF21Client.getInstance().getConfigManager().loadAll();
                    ChatUtil.print("Config reloaded.");
                    break;
                }
                case "reset": {
                    if (LF21Client.getInstance().getConfigManager().resetToDefaults()) {
                        ChatUtil.print("Config reset to defaults.");
                        break;
                    }
                    ChatUtil.print("Config reset failed.");
                    break;
                }
                case "folder": {
                    try {
                        Runtime.getRuntime().exec("explorer " + LF21Client.configDir);
                        break;
                    }
                    catch (IOException iOException) {
                        // empty catch block
                    }
                }
            }
        } else {
            ChatUtil.print("Usage: config reload/reset/folder");
        }
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return new String[]{"folder"};
    }

    public static void confirmReset() {
        if (LF21Client.getInstance().getConfigManager().resetToDefaults()) {
            ChatUtil.print("Config reset to defaults.");
        } else {
            ChatUtil.print("Config reset failed.");
        }
    }
}

