/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.HashMap;
import lf21.event.EventTarget;
import lf21.event.impl.RotationEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.GuiMove;
import lf21.modules.impl.player.InventoryManager;
import net.minecraft.client.KeyMapping;

public class m
extends Module {
    private final HashMap<String, String> j = new HashMap();
    private static final String k = m.c(m.a("\u001c@I];\u0003"));

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
                    case 0 -> 79;
                    case 1 -> 48;
                    case 2 -> 59;
                    case 3 -> 52;
                    case 4 -> 85;
                    case 5 -> 119;
                    default -> 2;
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
            cArray[0] = (char)(cArray[0] ^ 2);
        }
        return cArray;
    }

    @EventTarget
    public void a(RotationEvent rotationEvent) {
        if (GuiMove.j.isEnabled() && InventoryManager.O) {
            return;
        }
        m.mc.options.toggleSprint().set((Object)false);
        KeyMapping.set((InputConstants.Key)m.mc.options.keySprint.getKey(), (boolean)true);
    }

    public m() {
        super(k, Category.MOVEMENT);
        this.setEnabled(true);
    }
}

