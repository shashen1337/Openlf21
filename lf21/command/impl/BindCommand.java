/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import com.mojang.blaze3d.platform.InputConstants;
import lf21.LF21Client;
import lf21.command.Command;
import lf21.event.EventTarget;
import lf21.event.impl.KeyEvent;
import lf21.exception.ModuleNotFoundException;
import lf21.modules.Module;
import lf21.utils.misc.ChatUtil;

public class BindCommand
extends Command {
    public BindCommand() {
        super("bind", new String[]{"b"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        if (stringArray.length == 1) {
            this.handleBindInteractive(stringArray[0]);
        } else if (stringArray.length == 2) {
            this.handleBindExplicit(stringArray[0], stringArray[1]);
        } else {
            ChatUtil.print("Usage: .bind <module> [key]");
        }
    }

    private void handleBindInteractive(String string) {
        try {
            Module module = LF21Client.getInstance().getModuleManager().getModule(string);
            if (module == null) {
                ChatUtil.print("Invalid module.");
                return;
            }
            ChatUtil.print("Press a key to bind " + string + " to.");
            LF21Client.getInstance().getEventBus().register(new EventHandler(this, module, string));
        }
        catch (ModuleNotFoundException moduleNotFoundException) {
            ChatUtil.print("Invalid module.");
        }
    }

    private void handleBindExplicit(String string, String string2) {
        try {
            Module module = LF21Client.getInstance().getModuleManager().getModule(string);
            if (module == null) {
                ChatUtil.print("Invalid module.");
                return;
            }
            if (string2.equalsIgnoreCase("none")) {
                module.setKey(InputConstants.UNKNOWN.getValue());
                ChatUtil.print("Unbound " + string + ".");
                LF21Client.getInstance().getConfigManager().saveAll();
                return;
            }
            InputConstants.Key key = InputConstants.getKey((String)("key.keyboard." + string2.toLowerCase()));
            if (key == InputConstants.UNKNOWN) {
                ChatUtil.print("Invalid key.");
                return;
            }
            module.setKey(key.getValue());
            ChatUtil.print("Bound " + string + " to " + string2.toUpperCase() + ".");
            LF21Client.getInstance().getConfigManager().saveAll();
        }
        catch (ModuleNotFoundException moduleNotFoundException) {
            ChatUtil.print("Invalid module.");
        }
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return (String[])LF21Client.getInstance().getModuleManager().getModules().stream().map(Module::getName).filter(string -> string.toLowerCase().startsWith(stringArray.length == 0 ? "" : stringArray[0].toLowerCase())).toArray(String[]::new);
    }

    public static final class EventHandler {
        private final BindCommand parent;
        private final Module module;
        private final String name;

        public EventHandler(BindCommand bindCommand, Module module, String string) {
            this.parent = bindCommand;
            this.module = module;
            this.name = string;
        }

        @EventTarget
        public void onKey(KeyEvent keyEvent) {
            if (!keyEvent.isPressed()) {
                return;
            }
            int n2 = keyEvent.getKeyCode();
            if (n2 == 256) {
                this.module.setKey(0);
                ChatUtil.print("Unbound " + this.name + ".");
            } else {
                this.module.setKey(n2);
                ChatUtil.print("Bound " + this.name + " to key " + n2 + ".");
            }
            LF21Client.getInstance().getEventBus().unregister(this);
            LF21Client.getInstance().getConfigManager().saveAll();
        }
    }
}

