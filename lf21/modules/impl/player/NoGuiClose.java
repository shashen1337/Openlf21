/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import lf21.event.EventTarget;
import lf21.event.impl.PacketEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.network.protocol.game.ClientboundContainerClosePacket;

public final class NoGuiClose
extends Module {
    private static final String[] l;
    public static NoGuiClose j;
    public final BooleanSetting chatOnly;

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "Pjd?\u001f\u0013_\u007f{\n]mB>V\u001f]|q`";
        int n3 = "Pjd?\u001f\u0013_\u007f{\n]mB>V\u001f]|q`".length();
        int n4 = 9;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = NoGuiClose.c(NoGuiClose.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                l = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String c(char[] cArray) {
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
                    case 0 -> 19;
                    case 1 -> 2;
                    case 2 -> 5;
                    case 3 -> 75;
                    case 4 -> 63;
                    case 5 -> 92;
                    default -> 49;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x31);
        }
        return cArray;
    }

    @EventTarget(a=5)
    public void a(PacketEvent packetEvent) {
        if (!packetEvent.a() || !(packetEvent.c() instanceof ClientboundContainerClosePacket)) {
            return;
        }
        if (!((Boolean)this.chatOnly.getValue()).booleanValue() || NoGuiClose.mc.screen instanceof ChatScreen) {
            packetEvent.a(true);
        }
    }

    public NoGuiClose() {
        String[] stringArray = l;
        super("NoGuiClose", Category.PLAYER);
        this.chatOnly = new BooleanSetting("Chat Only", false);
        j = this;
    }
}

