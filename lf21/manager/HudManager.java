/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.TickEvent;
import lf21.gui.IntroAnimation;
import lf21.gui.k;
import lf21.hud.HudElement;
import lf21.hud.g_0;
import lf21.hud.h;
import lf21.hud.i;
import lf21.hud.j;
import lf21.hud.m;
import lf21.hud.n;
import lf21.hud.o;
import lf21.hud.p;
import lf21.modules.impl.render.Interface;
import lf21.render.C;
import net.minecraft.client.gui.GuiGraphics;

public class HudManager {
    private final Map<Class<? extends HudElement>, HudElement> a = new HashMap<Class<? extends HudElement>, HudElement>();
    private final C b = new C();

    public HudManager() {
        this.a();
    }

    public void a() {
        this.a(new p());
        this.a(new i());
        this.a(new j());
        this.a(new n());
        this.a(new o());
        this.a(new lf21.hud.k());
        this.a(new m());
        this.a(new h());
        this.a(new g_0());
    }

    private void a(HudElement hudElement) {
        if (hudElement == null || "PlayerList".equalsIgnoreCase(hudElement.getName())) {
            return;
        }
        LF21Client.getInstance().getModuleManager().a(hudElement);
        this.a.put(hudElement.getClass(), hudElement);
    }

    public void b(HudElement hudElement) {
        if (hudElement == null || "PlayerList".equalsIgnoreCase(hudElement.getName())) {
            return;
        }
        this.a.put(hudElement.getClass(), hudElement);
    }

    public <T extends HudElement> T a(Class<T> clazz) {
        return (T)((HudElement)clazz.cast(this.a.get(clazz)));
    }

    public HudElement a(String string) {
        return this.a.values().stream().filter(hudElement -> hudElement.getName().equalsIgnoreCase(string)).findFirst().orElse(null);
    }

    public Collection<HudElement> getHudElements() {
        return this.a.values();
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        m m2 = this.a(m.class);
        if (m2 != null) {
            m2.y();
        }
        if (ClientBase.mc.screen == null) {
            try {
                for (HudElement hudElement : LF21Client.getInstance().getHudManager().getHudElements()) {
                    hudElement.t();
                }
            }
            catch (Exception exception) {
                ClientBase.logger.error((Object)exception);
                ClientBase.logger.error(exception.getMessage());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventTarget
    public void a(Render2DEvent render2DEvent) {
        if (!this.b() || IntroAnimation.a() || ClientBase.mc.screen instanceof k) {
            return;
        }
        boolean bl = this.a(render2DEvent.b());
        try {
            for (HudElement hudElement : this.getHudElements()) {
                if (!hudElement.r()) continue;
                hudElement.a(render2DEvent);
            }
        }
        finally {
            if (bl) {
                this.b(render2DEvent.b());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventTarget
    public void a(GlRenderEvent glRenderEvent) {
        if (!this.b() || IntroAnimation.a() || ClientBase.mc.screen instanceof k) {
            return;
        }
        boolean bl = this.a(glRenderEvent.a());
        try {
            for (HudElement hudElement : this.getHudElements()) {
                if (!hudElement.r()) continue;
                hudElement.a(glRenderEvent);
            }
        }
        finally {
            if (bl) {
                this.b(glRenderEvent.a());
            }
        }
    }

    public boolean a(GuiGraphics guiGraphics) {
        if (!this.b() || !((Boolean)Interface.j.hUDGlow.getValue()).booleanValue()) {
            return false;
        }
        return this.b.a(guiGraphics);
    }

    public void b(GuiGraphics guiGraphics) {
        Interface interface_ = Interface.j;
        this.b.a(guiGraphics, ((Number)interface_.glowRadius.getValue()).floatValue(), ((Number)interface_.glowCompression.getValue()).floatValue(), ((Number)interface_.glowIntensity.getValue()).floatValue());
    }

    public boolean b() {
        return Interface.j != null && Interface.j.isEnabled();
    }
}

