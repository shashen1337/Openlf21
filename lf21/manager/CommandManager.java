/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

import java.util.HashMap;
import java.util.Map;
import lf21.LF21Client;
import lf21.command.Command;
import lf21.command.impl.BindCommand;
import lf21.command.impl.ConfigCommand;
import lf21.command.impl.HelpCommand;
import lf21.command.impl.HwidCommand;
import lf21.command.impl.LanguageCommand;
import lf21.command.impl.LookForCommand;
import lf21.command.impl.ModuleCommand;
import lf21.command.impl.MusicCommand;
import lf21.command.impl.NoFallCommand;
import lf21.command.impl.NukerCommand;
import lf21.command.impl.ToggleCommand;
import lf21.command.impl.YesCommand;
import lf21.event.EventTarget;
import lf21.event.impl.a;
import lf21.utils.misc.ChatUtil;

public class CommandManager {
    public static final String a = ".";
    public final Map<String, Command> b = new HashMap<String, Command>();
    private static final String[] c;

    public CommandManager() {
        LF21Client.getInstance().getEventBus().register(this);
    }

    public void initCommands() {
        this.a(new BindCommand());
        this.a(new ConfigCommand());
        this.a(new HwidCommand());
        this.a(new HelpCommand());
        this.a(new LanguageCommand());
        this.a(new LookForCommand());
        this.a(new MusicCommand());
        this.a(new ModuleCommand());
        this.a(new NoFallCommand());
        this.a(new NukerCommand());
        this.a(new ToggleCommand());
        this.a(new YesCommand());
    }

    private void a(Command command) {
        this.b.put(command.getPrefix().toLowerCase(), command);
        for (String string : command.getAliases()) {
            this.b.put(string.toLowerCase(), command);
        }
    }

    @EventTarget
    public void a(a a2) {
        if (a2.a().startsWith(".")) {
            a2.a(true);
            this.executeClientCommand(a2.a());
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean executeClientCommand(String string) {
        if (string == null) return false;
        if (!string.startsWith(".")) {
            return false;
        }
        String string2 = string.substring(".".length()).trim();
        if (string2.isEmpty()) {
            ChatUtil.print("Unknown command");
            return true;
        }
        String[] stringArray = c;
        String[] stringArray2 = string2.split("\\s+");
        String string3 = stringArray2[0].toLowerCase();
        Command command = this.b.get(string3);
        if (command != null) {
            String[] stringArray3 = new String[stringArray2.length - 1];
            System.arraycopy(stringArray2, 1, stringArray3, 0, stringArray3.length);
            command.onCommand(stringArray3);
            return true;
        }
        try {
            LF21Client.getInstance().getModuleManager().getModule(string3);
            new ModuleCommand().onCommand(stringArray2);
            return true;
        }
        catch (RuntimeException runtimeException) {
            ChatUtil.print("\u672a\u77e5\u547d\u4ee4: " + string3);
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "\\4\u0017\u000b1tr)9\u0013\b3brm\u0003U)W\u0006\u6723\u77bf\u5401\u4e81d#";
        int n3 = "\\4\u0017\u000b1tr)9\u0013\b3brm\u0003U)W\u0006\u6723\u77bf\u5401\u4e81d#".length();
        int n4 = 15;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = CommandManager.a(CommandManager.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                c = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x1C);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 9;
                    case 1 -> 90;
                    case 2 -> 124;
                    case 3 -> 101;
                    case 4 -> 94;
                    case 5 -> 3;
                    default -> 28;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

