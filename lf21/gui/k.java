/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lf21.LF21Client;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.hud.HudElement;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class k
extends Screen {
    private boolean a;
    private boolean b;
    private HudElement c;

    public k() {
        super((Component)Component.literal((String)"HUD Editor"));
    }

    protected void init() {
        super.init();
        if (!this.b) {
            this.a = this.minecraft.options.hideGui;
            this.b = true;
        }
        this.minecraft.options.hideGui = true;
    }

    public void tick() {
        this.minecraft.options.hideGui = true;
    }

    public void render(GuiGraphics guiGraphics, int n2, int n3, float f2) {
        int n4;
        guiGraphics.fill(0, 0, this.width, this.height, 0x36000000);
        for (n4 = 20; n4 < this.width; n4 += 20) {
            guiGraphics.fill(n4, 0, n4 + 1, this.height, 0x12FFFFFF);
        }
        for (n4 = 20; n4 < this.height; n4 += 20) {
            guiGraphics.fill(0, n4, this.width, n4 + 1, 0x12FFFFFF);
        }
        guiGraphics.flush();
        List<HudElement> list = this.a();
        Render2DEvent render2DEvent = new Render2DEvent(guiGraphics.pose(), guiGraphics, f2);
        for (HudElement hudElement : list) {
            try {
                hudElement.a(render2DEvent);
            }
            catch (Exception exception) {
                LF21Client.logger.error("Failed to render HUD editor element {}", (Object)hudElement.getName(), (Object)exception);
            }
        }
        Renderer.render(guiGraphics, drawContext -> {
            GlRenderEvent glRenderEvent = new GlRenderEvent(guiGraphics, guiGraphics.pose(), (DrawContext)drawContext);
            for (HudElement hudElement : list) {
                try {
                    hudElement.a(glRenderEvent);
                }
                catch (Exception exception) {
                    LF21Client.logger.error("Failed to render HUD editor canvas element {}", (Object)hudElement.getName(), (Object)exception);
                }
            }
        });
        for (HudElement hudElement : list) {
            this.a(guiGraphics, hudElement, n2, n3);
        }
        super.render(guiGraphics, n2, n3, f2);
    }

    private List<HudElement> a() {
        return LF21Client.getInstance().getHudManager().getHudElements().stream().toList();
    }

    private void a(GuiGraphics guiGraphics, HudElement hudElement, int n2, int n3) {
        int n4 = Math.round(hudElement.m());
        int n5 = Math.round(hudElement.n());
        int n6 = Math.round(hudElement.m() + Math.max(8.0f, hudElement.k()));
        int n7 = Math.round(hudElement.n() + Math.max(8.0f, hudElement.l()));
        boolean bl = hudElement == this.c || hudElement.a(n2, n3);
        int n8 = bl ? -15079730 : -2003787608;
        guiGraphics.fill(n4, n5, n6, n5 + 1, n8);
        guiGraphics.fill(n4, n7 - 1, n6, n7, n8);
        guiGraphics.fill(n4, n5, n4 + 1, n7, n8);
        guiGraphics.fill(n6 - 1, n5, n6, n7, n8);
        if (bl) {
            String string = hudElement.getName() + "  " + Math.round(hudElement.getScale() * 100.0f) + "%";
            int n9 = this.minecraft.font.width(string) + 8;
            int n10 = Math.max(0, n5 - 12);
            guiGraphics.fill(n4, n10, n4 + n9, n10 + 11, -803727336);
            guiGraphics.drawString(this.minecraft.font, string, n4 + 4, n10 + 2, -15079730, false);
        }
    }

    public boolean mouseClicked(double d2, double d3, int n2) {
        block3: {
            ArrayList<HudElement> arrayList;
            block2: {
                arrayList = new ArrayList<HudElement>(this.a());
                Collections.reverse(arrayList);
                if (n2 != 0) break block2;
                for (HudElement hudElement : arrayList) {
                    if (!hudElement.mousePressed((int)d2, (int)d3, n2)) continue;
                    this.c = hudElement;
                    return true;
                }
                break block3;
            }
            if (n2 != 1) break block3;
            for (HudElement hudElement : arrayList) {
                if (!hudElement.a((int)d2, (int)d3)) continue;
                return true;
            }
        }
        return super.mouseClicked(d2, d3, n2);
    }

    public boolean mouseDragged(double d2, double d3, int n2, double d4, double d5) {
        if (n2 == 0 && this.c != null) {
            this.c.mouseDragged((int)d2, (int)d3);
            this.c.a((float)this.width, (float)this.height);
            return true;
        }
        return super.mouseDragged(d2, d3, n2, d4, d5);
    }

    public boolean mouseReleased(double d2, double d3, int n2) {
        if (this.c != null) {
            this.c.t();
            this.c = null;
            LF21Client.getInstance().getConfigManager().saveAll();
            return true;
        }
        return super.mouseReleased(d2, d3, n2);
    }

    public boolean mouseScrolled(double d2, double d3, double d4) {
        ArrayList<HudElement> arrayList = new ArrayList<HudElement>(this.a());
        Collections.reverse(arrayList);
        for (HudElement hudElement : arrayList) {
            if (!hudElement.a((int)d2, (int)d3)) continue;
            if (hudElement.h()) {
                return true;
            }
            hudElement.setScale(hudElement.getScale() + (float)d4 * 0.05f);
            hudElement.a((float)this.width, (float)this.height);
            LF21Client.getInstance().getConfigManager().saveAll();
            return true;
        }
        return super.mouseScrolled(d2, d3, d4);
    }

    public void removed() {
        if (this.b) {
            this.minecraft.options.hideGui = this.a;
            this.b = false;
        }
        for (HudElement hudElement : LF21Client.getInstance().getHudManager().getHudElements()) {
            hudElement.t();
        }
        LF21Client.getInstance().getConfigManager().saveAll();
        super.removed();
    }

    public boolean isPauseScreen() {
        return false;
    }
}

