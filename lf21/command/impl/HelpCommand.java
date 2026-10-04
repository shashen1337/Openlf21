/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import lf21.command.Command;
import lf21.utils.misc.ChatUtil;

public final class HelpCommand
extends Command {
    public HelpCommand() {
        super("help", new String[]{"commands", "?"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        ChatUtil.print("LF21 commands:");
        ChatUtil.print(false, "\u00a7b.help\u00a77 - \u67e5\u770b\u6b64\u547d\u4ee4\u5e2e\u52a9");
        ChatUtil.print(false, "\u00a7b.toggle <module>\u00a77 - \u5f00\u542f\u6216\u5173\u95ed\u6a21\u5757");
        ChatUtil.print(false, "\u00a7b.bind <module> [key]\u00a77 - \u7ed1\u5b9a\u6216\u6e05\u9664\u6a21\u5757\u6309\u952e");
        ChatUtil.print(false, "\u00a7b.module <module> [setting] [value]\u00a77 - \u67e5\u770b\u6216\u4fee\u6539\u6a21\u5757\u9009\u9879");
        ChatUtil.print(false, "\u00a7b.<module> [setting] [value]\u00a77 - \u76f4\u63a5\u4f7f\u7528\u6a21\u5757\u540d\u4fee\u6539\u9009\u9879");
        ChatUtil.print(false, "\u00a7b.config reload/reset/folder\u00a77 - \u91cd\u8f7d\u3001\u91cd\u7f6e\u6216\u6253\u5f00\u914d\u7f6e\u76ee\u5f55");
        ChatUtil.print(false, "\u00a7b.nofall config reset\u00a77 - \u6e05\u7a7a NoFall \u8c03\u8bd5\u65e5\u5fd7");
        ChatUtil.print(false, "\u00a7b.yes\u00a77 - \u786e\u8ba4\u5f85\u6267\u884c\u7684\u914d\u7f6e\u91cd\u7f6e");
        ChatUtil.print(false, "\u00a7b.language <language>\u00a77 - \u5207\u6362\u754c\u9762\u8bed\u8a00");
        ChatUtil.print(false, "\u00a7b.lookfor <name>/clear\u00a77 - \u67e5\u627e\u73a9\u5bb6\u6216\u6e05\u9664\u67e5\u627e\u76ee\u6807");
        ChatUtil.print(false, "\u00a7b.nuker add/delete <\u65b9\u5757ID> | list\u00a77 - \u7ba1\u7406\u81ea\u52a8\u7834\u574f\u76ee\u6807");
        ChatUtil.print(false, "\u00a7b.hwid\u00a77 - \u663e\u793a\u5f53\u524d\u786c\u4ef6\u6307\u7eb9\u4fe1\u606f");
        ChatUtil.print(false, "\u00a7b.music list\u00a77 - \u5217\u51fa\u6b4c\u66f2\uff0c\u70b9\u51fb\u6b4c\u540d\u590d\u5236");
        ChatUtil.print(false, "\u00a7b.music serach <name>\u00a77 - \u6a21\u7cca\u67e5\u8be2\u672c\u5730\u6b4c\u66f2");
        ChatUtil.print(false, "\u00a7b.music recommend\u00a77 - \u6309\u5f53\u524d\u97f3\u7b26\u76d2\u97f3\u57df\u63a8\u8350\u6b4c\u66f2");
        ChatUtil.print(false, "\u00a7b.music load/preview <file>\u00a77 - \u52a0\u8f7d\u6216\u8bd5\u542c\u6b4c\u66f2");
        ChatUtil.print(false, "\u00a7b.music play/pause/stop/clear\u00a77 - \u63a7\u5236\u97f3\u4e50\u64ad\u653e");
        ChatUtil.print(false, "\u00a7b.music folder/status\u00a77 - \u6253\u5f00\u6b4c\u66f2\u76ee\u5f55\u6216\u67e5\u770b\u72b6\u6001");
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return new String[0];
    }
}

