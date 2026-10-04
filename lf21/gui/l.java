/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import lf21.LF21Client;
import lf21.event.impl.GlRenderEvent;
import lf21.gui.b.b;
import lf21.gui.b.d;
import lf21.gui.b.e;
import lf21.gui.b.f;
import lf21.gui.b.g;
import lf21.gui.j;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import lombok.Generated;
import lombok.NonNull;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class l
extends Screen {
    private static final List<d> a = new ArrayList<d>();
    private static final e b;
    private static final g c;
    public static d d;
    private static f e;
    private boolean f = false;
    private final lf21.utils.a.e g = new lf21.utils.a.e();

    public l() {
        super((Component)Component.literal((String)"ClickGui"));
    }

    protected void init() {
        block3: {
            block2: {
                d = a.get(0);
                l.b();
                if (ThreadLocalRandom.current().nextInt(100) >= 25) break block2;
                Module.a(LF21Client.getInstance().getModuleManager().getModules());
                break block3;
            }
            Module.d();
        }
        lf21.gui.b.b.a(a, b, c, this.width, this.height);
        b.l();
    }

    public static void a() {
        b.l();
    }

    public void render(@NonNull GuiGraphics guiGraphics, int n2, int n3, float f2) {
        float f3;
        block13: {
            if (guiGraphics == null) {
                throw new NullPointerException("guiGraphics is marked non-null but is null");
            }
            lf21.utils.a.e e2 = this.g;
            double d2 = this.f ? 0.0 : 1.0;
            e2.a(d2, 0.2, lf21.utils.b.f.c);
            this.g.a();
            f3 = this.g.e();
            if (!Mth.equal((float)f3, (float)0.0f) || !this.f) break block13;
            this.f = false;
            super.onClose();
            a.forEach(d::a);
            return;
        }
        for (d d3 : a) {
            d3.a(this, guiGraphics, guiGraphics.pose(), n2, n3, f3, f2);
        }
        b.a(this, guiGraphics, guiGraphics.pose(), n2, n3, f3, f2);
        c.a(this, guiGraphics, guiGraphics.pose(), n2, n3, f3, f2);
        if (d != null) {
            d.a(guiGraphics.pose(), n2, n3, this.width, this.height, f3);
        }
        Renderer.render(guiGraphics, drawContext -> LF21Client.getInstance().getNotificationManager().b(new GlRenderEvent(guiGraphics, guiGraphics.pose(), (DrawContext)drawContext)));
        String string = j.a();
        int n4 = this.width - this.font.width(string) - 8;
        Objects.requireNonNull(this.font);
        guiGraphics.drawString(this.font, string, n4, this.height - 9 - 8, -5197648, false);
    }

    public void onClose() {
        l.b();
        lf21.gui.b.b.a(a, b, c);
        this.f = true;
    }

    public static void a(f f2) {
        l.b();
        e = f2;
        if (e != null) {
            e.c(true);
        }
    }

    private static void b() {
        if (e != null) {
            e.c(false);
        }
        e = null;
    }

    public boolean c() {
        boolean bl;
        block5: {
            block4: {
                if (!c.h() && !b.h()) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public boolean keyPressed(int n2, int n3, int n4) {
        block16: {
            int n5;
            Module module;
            block18: {
                block17: {
                    if (c.a(n2)) {
                        return true;
                    }
                    if (b.a(n2)) {
                        return true;
                    }
                    if (e == null) break block16;
                    module = e.l();
                    if (n2 != 256) break block17;
                    n5 = 0;
                    break block18;
                }
                n5 = n2;
            }
            module.setKey(n5);
            l.b();
            if (LF21Client.isReady()) {
                LF21Client.getInstance().getConfigManager().saveAll();
            }
            return true;
        }
        if (n2 == 256) {
            this.onClose();
            return true;
        }
        return super.keyPressed(n2, n3, n4);
    }

    public boolean charTyped(char c2, int n2) {
        if (c.a(c2)) {
            return true;
        }
        if (b.a(c2)) {
            return true;
        }
        return super.charTyped(c2, n2);
    }

    public boolean mouseClicked(double d2, double d3, int n2) {
        if (c.h()) {
            c.a(d2, d3, n2);
            return true;
        }
        if (b.h()) {
            b.a(d2, d3, n2);
            return true;
        }
        if (c.a(d2, d3, n2)) {
            return true;
        }
        if (b.a(d2, d3, n2)) {
            return true;
        }
        for (d d4 : a) {
            if (!d4.a(d2, d3, n2)) {
                continue;
            }
            d = d4;
            return true;
        }
        return super.mouseClicked(d2, d3, n2);
    }

    public boolean mouseReleased(double d2, double d3, int n2) {
        c.b(d2, d3, n2);
        b.b(d2, d3, n2);
        for (d d4 : a) {
            d4.b(d2, d3, n2);
        }
        lf21.gui.b.b.a(a, b, c);
        return false;
    }

    public boolean isPauseScreen() {
        return false;
    }

    public boolean mouseScrolled(double d2, double d3, double d4) {
        if (c.a(d2, d3, d4)) {
            return true;
        }
        if (b.a(d2, d3, d4)) {
            return true;
        }
        for (d d5 : a) {
            if (!d5.a(d2, d3, d4)) {
                continue;
            }
            return true;
        }
        return false;
    }

    @Generated
    public boolean d() {
        return this.f;
    }

    @Generated
    public lf21.utils.a.e e() {
        return this.g;
    }

    static {
        for (Category category : Category.a()) {
            a.add(new d(category));
        }
        b = new e();
        c = new g();
    }
}

