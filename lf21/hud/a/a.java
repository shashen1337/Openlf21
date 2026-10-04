/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud.a;

import java.util.HashMap;
import lf21.ClientBase;
import lf21.event.impl.Render2DEvent;
import lf21.hud.a.b;
import lf21.hud.a.c;
import lf21.hud.a.d;
import lf21.hud.a.e;
import lf21.hud.a.f;
import net.minecraft.world.entity.LivingEntity;

public abstract class a
extends ClientBase {
    private final String a;
    private static final HashMap<Class<? extends a>, a> b = new HashMap();

    protected a(String string) {
        this.a = string;
    }

    public static a a(String string) {
        return b.values().stream().filter(a2 -> a2.a.equals(string)).findFirst().orElse(null);
    }

    public abstract void a(Render2DEvent var1, LivingEntity var2, lf21.utils.a.e var3, lf21.utils.a.e var4, float var5, float var6, float var7);

    public String a() {
        return this.a;
    }

    public static void b() {
        if (!b.isEmpty()) {
            return;
        }
        b.put(e.class, new e());
        b.put(b.class, new b());
        b.put(d.class, new d());
        b.put(f.class, new f());
        b.put(c.class, new c());
    }
}

