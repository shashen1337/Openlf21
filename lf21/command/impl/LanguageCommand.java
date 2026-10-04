/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import lf21.LF21Client;
import lf21.command.Command;

public class LanguageCommand
extends Command {
    public LanguageCommand() {
        super("language", new String[]{"lang"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        LF21Client.getInstance().getEventBus().register(new EventHandler(this));
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return new String[0];
    }

    public static final class EventHandler {
        private final LanguageCommand parent;

        public EventHandler(LanguageCommand languageCommand) {
            this.parent = languageCommand;
        }
    }
}

