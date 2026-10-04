/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render.a;

import java.awt.Color;
import java.util.Collections;
import java.util.HashSet;
import lf21.modules.impl.render.a.a;
import net.minecraft.world.entity.projectile.Arrow;

public class b
extends a {
    @Override
    public float b() {
        return 0.5f;
    }

    @Override
    public float c() {
        return 0.05f;
    }

    @Override
    public float a() {
        return 0.25f;
    }

    public b() {
        super(new HashSet(Collections.singletonList(Arrow.class)), new Color(255, 0, 0));
    }
}

