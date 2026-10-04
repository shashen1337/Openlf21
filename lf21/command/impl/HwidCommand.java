/*
 * Decompiled with CFR 0.152.
 */
package lf21.command.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import lf21.command.Command;
import lf21.hwid.HardwareCollector;
import lf21.hwid.HardwareSnapshot;
import lf21.utils.misc.ChatUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;

public final class HwidCommand
extends Command {
    private static final AtomicBoolean SCANNING = new AtomicBoolean(false);

    public HwidCommand() {
        super("hwid", new String[]{"hardware"});
    }

    @Override
    public void onCommand(String[] stringArray) {
        if (!SCANNING.compareAndSet(false, true)) {
            HwidCommand.send((Component)Component.literal((String)"[HWID] \u786c\u4ef6\u4fe1\u606f\u6b63\u5728\u8bfb\u53d6\u4e2d\u2026\u2026").withStyle(ChatFormatting.YELLOW));
            return;
        }
        HwidCommand.send((Component)Component.literal((String)"[HWID] \u6b63\u5728\u8bfb\u53d6 CPU\u3001GPU\u3001BIOS\u3001\u4e3b\u677f\u3001\u78c1\u76d8\u4e0e\u7f51\u5361\u2026\u2026").withStyle(ChatFormatting.GRAY));
        CompletableFuture.supplyAsync(HardwareCollector::collect).whenComplete((hardwareSnapshot, throwable) -> Minecraft.getInstance().execute(() -> {
            try {
                if (throwable != null) {
                    HwidCommand.send((Component)Component.literal((String)("[HWID] \u8bfb\u53d6\u5931\u8d25\uff1a" + HwidCommand.rootMessage(throwable))).withStyle(ChatFormatting.RED));
                    return;
                }
                HwidCommand.showSnapshot(hardwareSnapshot);
            }
            finally {
                SCANNING.set(false);
            }
        }));
    }

    private static void showSnapshot(HardwareSnapshot hardwareSnapshot) {
        HwidCommand.send((Component)Component.literal((String)"========== lf21 HWID ==========").withStyle(ChatFormatting.DARK_AQUA));
        HwidCommand.sendValue("CPU", hardwareSnapshot.cpu());
        HwidCommand.sendValue("CPU Identifier", hardwareSnapshot.cpuIdentifier());
        HwidCommand.sendValue("Processor ID", hardwareSnapshot.processorId());
        HwidCommand.sendList("GPU", hardwareSnapshot.graphicsCards());
        HwidCommand.sendValue("BIOS/System UUID", hardwareSnapshot.systemUuid());
        HwidCommand.sendValue("MachineGuid", hardwareSnapshot.machineGuid());
        HwidCommand.sendValue("BIOS", hardwareSnapshot.bios());
        HwidCommand.sendValue("Mainboard", hardwareSnapshot.baseboard());
        HwidCommand.sendList("MAC", hardwareSnapshot.macAddresses());
        HwidCommand.sendList("Disk", hardwareSnapshot.disks());
        MutableComponent mutableComponent = Component.literal((String)"Fingerprint-128: ").withStyle(ChatFormatting.GRAY).append((Component)Component.literal((String)hardwareSnapshot.displayHash()).withStyle(style -> style.withColor(ChatFormatting.AQUA).withUnderlined(Boolean.valueOf(true)).withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, hardwareSnapshot.fullHash())).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Component.literal((String)"\u70b9\u51fb\u590d\u5236\u5b8c\u6574 SHA-256")))));
        HwidCommand.send((Component)mutableComponent);
        HwidCommand.sendValue("SHA-256", hardwareSnapshot.fullHash());
    }

    private static void sendValue(String string, String string2) {
        String string3 = string2 == null || string2.isBlank() ? "unavailable" : string2;
        HwidCommand.send((Component)Component.literal((String)(string + ": ")).withStyle(ChatFormatting.GRAY).append((Component)Component.literal((String)string3).withStyle(ChatFormatting.WHITE)));
    }

    private static void sendList(String string, List<String> list) {
        if (list == null || list.isEmpty()) {
            HwidCommand.sendValue(string, "unavailable");
            return;
        }
        for (int i2 = 0; i2 < list.size(); ++i2) {
            HwidCommand.sendValue(string + "[" + i2 + "]", list.get(i2));
        }
    }

    private static void send(Component component) {
        ChatUtil.addMessage(component);
    }

    private static String rootMessage(Throwable throwable) {
        Throwable throwable2 = throwable;
        while (throwable2.getCause() != null) {
            throwable2 = throwable2.getCause();
        }
        String string = throwable2.getMessage();
        return string == null || string.isBlank() ? throwable2.getClass().getSimpleName() : string;
    }

    @Override
    public String[] onTab(String[] stringArray) {
        return new String[0];
    }
}

