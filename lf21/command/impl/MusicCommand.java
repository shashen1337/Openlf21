/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import java.awt.Desktop;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import lf21.LF21Client;
import lf21.command.Command;
import lf21.modules.impl.world.MusicBot;
import lf21.utils.misc.ChatUtil;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public final class MusicCommand
extends Command {
    public MusicCommand() {
        super("music", new String[]{"musicbot", "notebot"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        String string;
        MusicBot musicBot = LF21Client.getInstance().getModuleManager().getModule(MusicBot.class);
        if (stringArray.length == 0) {
            MusicCommand.printUsage();
            return;
        }
        switch (string = stringArray[0].toLowerCase(Locale.ROOT)) {
            case "load": 
            case "preview": {
                if (stringArray.length < 2) {
                    ChatUtil.print("Usage: .music " + string + " <song.nbs|song.txt>");
                    return;
                }
                String string2 = MusicCommand.joinPath(stringArray, 1);
                musicBot.loadSong(new File(string2), string.equals("preview"));
                break;
            }
            case "play": 
            case "resume": {
                musicBot.play();
                break;
            }
            case "pause": {
                musicBot.pause();
                break;
            }
            case "stop": {
                musicBot.stopPlayback();
                break;
            }
            case "clear": {
                musicBot.clearSong();
                ChatUtil.print("MusicBot: song cleared");
                break;
            }
            case "status": {
                ChatUtil.print("MusicBot: " + musicBot.status());
                break;
            }
            case "recommend": 
            case "suggest": {
                musicBot.recommendSongs();
                break;
            }
            case "serach": 
            case "search": {
                if (stringArray.length < 2) {
                    ChatUtil.print("Usage: .music serach <song name>");
                    return;
                }
                musicBot.searchSongs(MusicCommand.joinPath(stringArray, 1));
                break;
            }
            case "list": {
                List<String> list = musicBot.listSongs();
                if (list.isEmpty()) {
                    ChatUtil.print("MusicBot: song folder is empty");
                    break;
                }
                MutableComponent mutableComponent = Component.literal((String)"\u00a77[\u00a7b\u00a77] MusicBot songs: ");
                for (int i2 = 0; i2 < list.size(); ++i2) {
                    if (i2 > 0) {
                        mutableComponent.append((Component)Component.literal((String)", "));
                    }
                    String string3 = list.get(i2);
                    mutableComponent.append((Component)Component.literal((String)string3).withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, string3))));
                }
                ChatUtil.addMessage((Component)mutableComponent);
                break;
            }
            case "folder": 
            case "open": {
                try {
                    MusicBot.SONG_DIR.mkdirs();
                    Desktop.getDesktop().open(MusicBot.SONG_DIR);
                    ChatUtil.print("MusicBot folder: " + MusicBot.SONG_DIR.getAbsolutePath());
                }
                catch (Exception exception) {
                    ChatUtil.print("MusicBot folder: " + MusicBot.SONG_DIR.getAbsolutePath());
                }
                break;
            }
            default: {
                MusicCommand.printUsage();
            }
        }
    }

    private static String joinPath(String[] stringArray, int n2) {
        String string = String.join((CharSequence)" ", Arrays.copyOfRange(stringArray, n2, stringArray.length)).trim();
        if (string.length() >= 2 && string.startsWith("\"") && string.endsWith("\"")) {
            string = string.substring(1, string.length() - 1);
        }
        return string;
    }

    private static void printUsage() {
        ChatUtil.print("MusicBot: .music load <file> | recommend | serach <name> | preview <file> | play | pause | stop | list | folder | status");
    }

    @Override
    public String[] onTab(String[] stringArray) {
        if (stringArray.length == 1) {
            return new String[]{"load", "recommend", "serach", "search", "preview", "play", "pause", "stop", "list", "folder", "status", "clear"};
        }
        if (stringArray.length == 2 && ("load".equalsIgnoreCase(stringArray[0]) || "preview".equalsIgnoreCase(stringArray[0]))) {
            MusicBot musicBot = LF21Client.getInstance().getModuleManager().getModule(MusicBot.class);
            return (String[])musicBot.listSongs().toArray(String[]::new);
        }
        return new String[0];
    }
}

