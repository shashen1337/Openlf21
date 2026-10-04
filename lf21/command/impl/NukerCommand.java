/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import java.util.List;
import lf21.command.Command;
import lf21.command.impl.ModuleCommand;
import lf21.modules.impl.world.Nuker;
import lf21.modules.impl.world.Nuker$TargetChangeResult;
import lf21.utils.misc.ChatUtil;

public final class NukerCommand
extends Command {
    public NukerCommand() {
        super("nuker", new String[]{"breaktarget"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        Nuker nuker = Nuker.INSTANCE;
        if (nuker == null) {
            ChatUtil.print("Nuker \u5c1a\u672a\u521d\u59cb\u5316");
            return;
        }
        if (stringArray.length > 0 && ("add".equalsIgnoreCase(stringArray[0]) || "delete".equalsIgnoreCase(stringArray[0]))) {
            if (stringArray.length != 2) {
                ChatUtil.print("\u7528\u6cd5: .nuker " + stringArray[0].toLowerCase() + " <\u65b9\u5757ID>");
                return;
            }
            boolean bl = "add".equalsIgnoreCase(stringArray[0]);
            Nuker$TargetChangeResult nuker$TargetChangeResult = nuker.changeTargetBlock(stringArray[1], bl);
            if (!nuker$TargetChangeResult.success()) {
                ChatUtil.print(nuker$TargetChangeResult.message());
                return;
            }
            ChatUtil.print("Nuker target " + nuker$TargetChangeResult.message());
            return;
        }
        if (stringArray.length == 1 && "list".equalsIgnoreCase(stringArray[0])) {
            List<String> list = nuker.getTargetBlockIds();
            ChatUtil.print("Nuker targets (" + list.size() + "):");
            if (list.isEmpty()) {
                ChatUtil.print(false, "\u00a77(empty)");
            } else {
                list.forEach(string -> ChatUtil.print(false, "\u00a7b- " + string));
            }
            return;
        }
        String[] stringArray2 = new String[stringArray.length + 1];
        stringArray2[0] = "nuker";
        System.arraycopy(stringArray, 0, stringArray2, 1, stringArray.length);
        new ModuleCommand().onCommand(stringArray2);
    }

    @Override
    public String[] onTab(String[] stringArray) {
        if (stringArray.length == 1) {
            return new String[]{"add", "delete", "list"};
        }
        if (stringArray.length == 2 && ("add".equalsIgnoreCase(stringArray[0]) || "delete".equalsIgnoreCase(stringArray[0]))) {
            return new String[]{"minecraft:red_bed", "minecraft:stone"};
        }
        return new String[0];
    }
}

