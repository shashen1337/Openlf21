/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import lf21.LF21Client;
import lf21.command.Command;
import lf21.utils.misc.ChatUtil;

public final class LookForCommand
extends Command {
    public LookForCommand() {
        super("lookfor", new String[]{"findplayer"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        if (stringArray.length != 1 || stringArray[0].isBlank()) {
            ChatUtil.print("\u7528\u6cd5: .lookfor <name> | .lookfor clear");
            return;
        }
        if ("clear".equalsIgnoreCase(stringArray[0])) {
            LF21Client.getInstance().getLookForManager().clear(true);
            return;
        }
        LF21Client.getInstance().getLookForManager().watch(stringArray[0]);
    }

    @Override
    public String[] onTab(String[] stringArray) {
        String[] stringArray2;
        if (stringArray.length == 1) {
            String[] stringArray3 = new String[1];
            stringArray2 = stringArray3;
            stringArray3[0] = "clear";
        } else {
            stringArray2 = new String[]{};
        }
        return stringArray2;
    }
}

