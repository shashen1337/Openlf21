/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Objects;
import lf21.event.EventMarker;
import lombok.Generated;
import net.minecraft.client.gui.GuiGraphics;

public record Render2DEvent(PoseStack a, GuiGraphics b, float c) implements EventMarker
{
    private static final String[] d;

    @Override
    @Generated
    public boolean equals(Object object) {
        GuiGraphics guiGraphics;
        PoseStack poseStack;
        if (object == this) {
            return true;
        }
        if (!(object instanceof Render2DEvent)) {
            return false;
        }
        Render2DEvent render2DEvent = (Render2DEvent)object;
        if (!render2DEvent.a(this)) {
            return false;
        }
        if (Float.compare(this.c(), render2DEvent.c()) != 0) {
            return false;
        }
        PoseStack poseStack2 = this.a();
        if (!Objects.equals(poseStack2, poseStack = render2DEvent.a())) {
            return false;
        }
        GuiGraphics guiGraphics2 = this.b();
        return Objects.equals(guiGraphics2, guiGraphics = render2DEvent.b());
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof Render2DEvent;
    }

    @Override
    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + Float.floatToIntBits(this.c());
        PoseStack poseStack = this.a();
        n3 = n3 * 59 + (poseStack == null ? 43 : poseStack.hashCode());
        GuiGraphics guiGraphics = this.b();
        n3 = n3 * 59 + (guiGraphics == null ? 43 : guiGraphics.hashCode());
        return n3;
    }

    @Override
    @Generated
    public String toString() {
        float f2 = this.c();
        String string = String.valueOf(this.b());
        String string2 = String.valueOf(this.a());
        float f3 = f2;
        String string3 = string;
        String string4 = string2;
        String[] stringArray = d;
        return "Render2DEvent(stack=" + string4 + ", guiGraphics=" + string3 + ", partialTicks=" + f3 + ")";
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "8w\u0019-YO\u0018u;=%HP\u0002)\u0014F2\u0007(NICP\u0012\u001f)EOYg#\b/@\u0006\u000e8w\u000e9B|\u0003u'\u0001%HHL";
        int n3 = "8w\u0019-YO\u0018u;=%HP\u0002)\u0014F2\u0007(NICP\u0012\u001f)EOYg#\b/@\u0006\u000e8w\u000e9B|\u0003u'\u0001%HHL".length();
        int n4 = 15;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = Render2DEvent.a(Render2DEvent.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                d = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x71);
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
                    case 0 -> 20;
                    case 1 -> 87;
                    case 2 -> 105;
                    case 3 -> 76;
                    case 4 -> 43;
                    case 5 -> 59;
                    default -> 113;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

