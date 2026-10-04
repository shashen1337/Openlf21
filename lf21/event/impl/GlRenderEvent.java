/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Objects;
import lf21.event.EventMarker;
import lf21.render.DrawContext;
import lombok.Generated;
import net.minecraft.client.gui.GuiGraphics;

public record GlRenderEvent(GuiGraphics a, PoseStack b, DrawContext c) implements EventMarker
{
    private static final String[] d;

    @Override
    @Generated
    public boolean equals(Object object) {
        DrawContext drawContext;
        PoseStack poseStack;
        GuiGraphics guiGraphics;
        if (object == this) {
            return true;
        }
        if (!(object instanceof GlRenderEvent)) {
            return false;
        }
        GlRenderEvent glRenderEvent = (GlRenderEvent)object;
        if (!glRenderEvent.a(this)) {
            return false;
        }
        GuiGraphics guiGraphics2 = this.a();
        if (!Objects.equals(guiGraphics2, guiGraphics = glRenderEvent.a())) {
            return false;
        }
        PoseStack poseStack2 = this.b();
        if (!Objects.equals(poseStack2, poseStack = glRenderEvent.b())) {
            return false;
        }
        DrawContext drawContext2 = this.c();
        return Objects.equals(drawContext2, drawContext = glRenderEvent.c());
    }

    @Generated
    protected boolean a(Object object) {
        return object instanceof GlRenderEvent;
    }

    @Override
    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        GuiGraphics guiGraphics = this.a();
        n3 = n3 * 59 + (guiGraphics == null ? 43 : guiGraphics.hashCode());
        PoseStack poseStack = this.b();
        n3 = n3 * 59 + (poseStack == null ? 43 : poseStack.hashCode());
        DrawContext drawContext = this.c();
        n3 = n3 * 59 + (drawContext == null ? 43 : drawContext.hashCode());
        return n3;
    }

    @Override
    @Generated
    public String toString() {
        String string = String.valueOf(this.c());
        String string2 = String.valueOf(this.b());
        String string3 = String.valueOf(this.a());
        String string4 = string;
        String string5 = string2;
        String string6 = string3;
        String[] stringArray = d;
        return "GlRenderEvent(guiGraphics=" + string6 + ", stack=" + string5 + ", context=" + string4 + ")";
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "\r]TU\u000f\u0004\u001b8tpU\u000f\u0014V-Dow\u0013\u0001\u000e\"XeC\\\bf\u0011uD\u0000\u0003\u0015w\nf\u0011e_\u000f\u0014\u001b2E;";
        int n3 = "\r]TU\u000f\u0004\u001b8tpU\u000f\u0014V-Dow\u0013\u0001\u000e\"XeC\\\bf\u0011uD\u0000\u0003\u0015w\nf\u0011e_\u000f\u0014\u001b2E;".length();
        int n4 = 26;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = GlRenderEvent.a(GlRenderEvent.a(string.substring(n6, n6 + n4)));
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
            cArray[0] = (char)(cArray[0] ^ 0x7E);
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
                    case 0 -> 74;
                    case 1 -> 49;
                    case 2 -> 6;
                    case 3 -> 48;
                    case 4 -> 97;
                    case 5 -> 96;
                    default -> 126;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

