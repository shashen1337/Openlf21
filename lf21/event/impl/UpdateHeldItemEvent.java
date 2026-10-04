/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import java.util.Objects;
import lf21.event.EventMarker;
import lombok.Generated;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class UpdateHeldItemEvent
implements EventMarker {
    private final InteractionHand a;
    private ItemStack b;
    private static final String[] c;

    @Generated
    public UpdateHeldItemEvent(InteractionHand interactionHand, ItemStack itemStack) {
        this.a = interactionHand;
        this.b = itemStack;
    }

    @Generated
    public boolean equals(Object object) {
        ItemStack itemStack;
        InteractionHand interactionHand;
        if (object == this) {
            return true;
        }
        if (!(object instanceof UpdateHeldItemEvent)) {
            return false;
        }
        UpdateHeldItemEvent updateHeldItemEvent = (UpdateHeldItemEvent)object;
        if (!updateHeldItemEvent.a(this)) {
            return false;
        }
        InteractionHand interactionHand2 = this.a();
        if (!Objects.equals(interactionHand2, interactionHand = updateHeldItemEvent.a())) {
            return false;
        }
        ItemStack itemStack2 = this.getItemStack();
        return Objects.equals(itemStack2, itemStack = updateHeldItemEvent.getItemStack());
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof UpdateHeldItemEvent;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        InteractionHand interactionHand = this.a();
        n3 = n3 * 59 + (interactionHand == null ? 43 : interactionHand.hashCode());
        ItemStack itemStack = this.getItemStack();
        n3 = n3 * 59 + (itemStack == null ? 43 : itemStack.hashCode());
        return n3;
    }

    @Generated
    public String toString() {
        String[] stringArray = c;
        return "UpdateHeldItemEvent(hand=" + String.valueOf(this.a()) + ", item=" + String.valueOf(this.getItemStack()) + ")";
    }

    @Generated
    public InteractionHand a() {
        return this.a;
    }

    @Generated
    public ItemStack getItemStack() {
        return this.b;
    }

    @Generated
    public void a(ItemStack itemStack) {
        this.b = itemStack;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "GQv\u0005\u007f62\u0019>\u0001{\u0010n>G\u000e\u001d{8n>b.\u0007z\u001fnsg\n\u001f{L";
        int n3 = "GQv\u0005\u007f62\u0019>\u0001{\u0010n>G\u000e\u001d{8n>b.\u0007z\u001fnsg\n\u001f{L".length();
        int n4 = 7;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = UpdateHeldItemEvent.a(UpdateHeldItemEvent.a(string.substring(n6, n6 + n4)));
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
            cArray[0] = (char)(cArray[0] ^ 0xF);
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
                    case 0 -> 107;
                    case 1 -> 113;
                    case 2 -> 31;
                    case 3 -> 113;
                    case 4 -> 26;
                    case 5 -> 91;
                    default -> 15;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

