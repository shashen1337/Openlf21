/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render.a;

import java.awt.Color;
import net.minecraft.world.entity.Entity;

public interface d {
    public Color a(Object var1);

    default public float a() {
        return 0.125f;
    }

    default public float b() {
        return 0.25f;
    }

    public boolean a(Entity var1);

    default public float c() {
        return 0.03f;
    }
}

