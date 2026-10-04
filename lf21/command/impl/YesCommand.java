/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import lf21.command.Command;
import lf21.command.impl.ConfigCommand;

public final class YesCommand
extends Command {
    public YesCommand() {
        super("yes", new String[0]);
    }

    @Override
    public void onCommand(String[] stringArray) {
        ConfigCommand.confirmReset();
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return new String[0];
    }
}

