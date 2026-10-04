/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.GameTickEvent;
import lf21.modules.impl.combat.KillAura;
import lf21.utils.game.g;
import lombok.Generated;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class TargetManager
extends ClientBase {
    public static TargetManager a;
    private final List<LivingEntity> b = new ArrayList<LivingEntity>();

    public TargetManager() {
        a = this;
    }

    @EventTarget
    public void a(GameTickEvent gameTickEvent) {
        if (TargetManager.mc.level == null) {
            return;
        }
        if (KillAura.j == null) {
            return;
        }
        if (!LF21Client.isReady()) {
            return;
        }
        this.b.clear();
        for (Entity entity : TargetManager.mc.level.entitiesForRendering()) {
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity livingEntity = (LivingEntity)entity;
            if (!KillAura.j.e(entity)) continue;
            this.b.add(livingEntity);
        }
    }

    public Stream<LivingEntity> a(float f2) {
        if (TargetManager.mc.player == null) {
            return Stream.empty();
        }
        return new ArrayList<LivingEntity>(this.b).stream().filter(livingEntity -> {
            Vec3 vec3 = g.a(TargetManager.mc.player.getEyePosition(), livingEntity.getBoundingBox());
            return vec3.distanceTo(TargetManager.mc.player.getEyePosition()) <= (double)f2;
        });
    }

    public List<LivingEntity> b(float f2) {
        return this.a(f2).toList();
    }

    @Generated
    public List<LivingEntity> a() {
        return this.b;
    }
}

