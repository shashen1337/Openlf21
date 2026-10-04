/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render.a;

import java.awt.Color;
import java.util.Set;
import lf21.modules.impl.render.a.d;
import net.minecraft.world.entity.Entity;

public class a
implements d {
    private final Color a;
    private final Set<Class<?>> b;

    @Override
    public Color a(Object object) {
        return this.a;
    }

    @Override
    public boolean a(Entity entity) {
        for (Class<?> clazz : this.b) {
            if (!clazz.isInstance(entity)) continue;
            return true;
        }
        return false;
    }

    public a(Set<Class<?>> set, Color color) {
        this.b = set;
        this.a = color;
    }

    public a(Set<Class<?>> set) {
        this(set, new Color(255, 255, 255));
    }
}

