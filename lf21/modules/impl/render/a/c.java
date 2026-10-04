/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render.a;

import java.awt.Color;
import java.util.Collections;
import java.util.HashSet;
import lf21.modules.impl.render.a.a;
import net.minecraft.world.entity.projectile.ThrownPotion;

public class c
extends a {
    public c() {
        super(new HashSet(Collections.singleton(ThrownPotion.class)), new Color(255, 66, 249));
    }

    @Override
    public float c() {
        return 0.05f;
    }
}

