/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat.a;

import java.util.HashMap;
import java.util.Optional;
import lf21.ClientBase;
import lf21.event.impl.DisconnectEvent;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.RotationEvent;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.impl.combat.a.c;
import lf21.modules.impl.combat.a.d;
import lf21.modules.impl.combat.a.e;

public abstract class b
extends ClientBase {
    protected final String a;
    private static final HashMap<Class<? extends b>, b> b = new HashMap();

    public void a(Render2DEvent render2DEvent) {
    }

    public b(String string) {
        this.a = string;
    }

    public abstract void c();

    public abstract void a(MotionEvent var1);

    public abstract void a(TickEvent var1);

    public static Optional<b> a(String string) {
        return b.values().stream().filter(b2 -> b2.a.equals(string)).findFirst();
    }

    public void a(RenderEvent renderEvent) {
    }

    public abstract void b();

    public boolean e() {
        return false;
    }

    public abstract String d();

    public abstract void a(SprintEvent var1);

    public abstract void a(StrafeEvent var1);

    public abstract void a(DisconnectEvent var1);

    public abstract void a(lf21.event.impl.c var1);

    public abstract void a(RotationEvent var1);

    public static void a() {
        b.put(d.class, new d());
        b.put(e.class, new e());
        b.put(c.class, new c());
    }

    public abstract void a(PreMotionEvent var1);

    public abstract void a(GameTickEvent var1);
}

