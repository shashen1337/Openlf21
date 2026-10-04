/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public final class u {
    public Vec3 c;
    public final Player a;
    public Vec3 b;

    public u(Player player, Vec3 vec3) {
        this.a = player;
        this.b = vec3;
        this.c = vec3;
    }

    public Vec3 a(short s2, short s3, short s4) {
        double d2 = (double)s2 / 4096.0;
        double d3 = (double)s3 / 4096.0;
        double d4 = (double)s4 / 4096.0;
        return this.b.add(d2, d3, d4);
    }

    public void a() {
        this.c = this.b;
    }

    public void a(Vec3 vec3) {
        this.c = this.b;
        this.b = vec3;
    }

    public Vec3 a(float f2) {
        return new Vec3(this.c.x + (this.b.x - this.c.x) * (double)f2, this.c.y + (this.b.y - this.c.y) * (double)f2, this.c.z + (this.b.z - this.c.z) * (double)f2);
    }
}

