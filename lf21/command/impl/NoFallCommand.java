/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import lf21.command.Command;
import lf21.modules.impl.player.NoFall;
import lf21.utils.misc.ChatUtil;

public final class NoFallCommand
extends Command {
    public NoFallCommand() {
        super("nofall", new String[]{"nf"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        if (stringArray.length == 2 && stringArray[0].equalsIgnoreCase("config") && stringArray[1].equalsIgnoreCase("reset")) {
            NoFall noFall = NoFall.INSTANCE;
            if (noFall != null && noFall.resetDebuggerLog()) {
                ChatUtil.print(false, "\u00a7b[NoFall] \u00a7fDebugger log reset");
            } else {
                ChatUtil.print(false, "\u00a7c[NoFall] \u00a7fDebugger log reset failed");
            }
            return;
        }
        ChatUtil.print(false, "\u00a7b.nofall config reset \u00a77- clear debugger log");
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return new String[]{"config"};
    }
}

