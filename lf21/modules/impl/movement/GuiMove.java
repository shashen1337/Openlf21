/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import com.mojang.blaze3d.platform.InputConstants;
import lf21.event.EventTarget;
import lf21.event.impl.StrafeEvent;
import lf21.gui.l;
import lf21.gui.m;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.BooleanSetting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public class GuiMove
extends Module {
    private static final String[] l;
    public static GuiMove j;
    public final BooleanSetting invMove;

    private static float a(boolean bl, boolean bl2) {
        if (bl == bl2) {
            return 0.0f;
        }
        return bl ? 1.0f : -1.0f;
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
                    case 0 -> 29;
                    case 1 -> 102;
                    case 2 -> 125;
                    case 3 -> 89;
                    case 4 -> 109;
                    case 5 -> 32;
                    default -> 115;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "Z\u0013\u0014\u0014\u0002V\u0016\bT\b\u000by O\u0005x";
        int n3 = "Z\u0013\u0014\u0014\u0002V\u0016\bT\b\u000by O\u0005x".length();
        int n4 = 7;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = GuiMove.c(GuiMove.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                l = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private boolean a() {
        if (GuiMove.mc.screen instanceof ChatScreen) {
            return false;
        }
        Screen screen = GuiMove.mc.screen;
        if (screen instanceof l) {
            l l2 = (l)screen;
            return !l2.c();
        }
        if (GuiMove.mc.screen instanceof m) {
            return true;
        }
        return (Boolean)this.invMove.getValue() != false && GuiMove.mc.screen instanceof AbstractContainerScreen;
    }

    public GuiMove() {
        String[] stringArray = l;
        super("GuiMove", Category.MOVEMENT);
        this.invMove = new BooleanSetting("Inv Move", true);
        j = this;
    }

    @EventTarget
    public void a(StrafeEvent strafeEvent) {
        if (GuiMove.mc.player == null || GuiMove.mc.screen == null || !this.a()) {
            return;
        }
        strafeEvent.a(this.a(GuiMove.mc.options.keyJump));
        strafeEvent.a(GuiMove.a(this.a(GuiMove.mc.options.keyUp), this.a(GuiMove.mc.options.keyDown)));
        strafeEvent.b(GuiMove.a(this.a(GuiMove.mc.options.keyLeft), this.a(GuiMove.mc.options.keyRight)));
    }

    private boolean a(KeyMapping keyMapping) {
        return InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)keyMapping.getKey().getValue());
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x73);
        }
        return cArray;
    }
}

