/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render.b;

import java.util.HashMap;
import lf21.ClientBase;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.modules.impl.render.b.b;
import lf21.modules.impl.render.b.c;

public abstract class a
extends ClientBase {
    private final String a;
    private static final HashMap<Class<? extends a>, a> b = new HashMap();

    public abstract String d();

    protected a(String string) {
        this.a = string;
    }

    public abstract void a(RenderEvent var1);

    public abstract void a(Render2DEvent var1);

    public abstract void a(PacketEvent var1);

    public abstract void c();

    public abstract void b();

    public static void a() {
        b.put(b.class, new b());
        b.put(c.class, new c());
    }

    public static a a(String string) {
        return b.values().stream().filter(a2 -> a2.a.equals(string)).findFirst().orElse(null);
    }
}

