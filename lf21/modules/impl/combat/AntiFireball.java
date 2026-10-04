/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lf21.event.EventTarget;
import lf21.event.impl.MotionEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.utils.misc.ChatUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;

public class AntiFireball
extends Module {
    public AntiFireball() {
        super("AntiFireball", Category.COMBAT);
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (!motionEvent.isPost()) {
            return;
        }
        if (AntiFireball.mc.player == null || AntiFireball.mc.level == null || AntiFireball.mc.gameMode == null) {
            return;
        }
        Stream<Entity> stream = StreamSupport.stream(AntiFireball.mc.level.entitiesForRendering().spliterator(), false);
        Optional<Fireball> optional = stream.filter(entity -> entity instanceof Fireball && (double)AntiFireball.mc.player.distanceTo(entity) < 6.0).map(entity -> (Fireball)entity).findFirst();
        if (!optional.isPresent()) {
            return;
        }
        Fireball fireball = optional.get();
        ChatUtil.print("\u00a7c[AntiFireball] Attacking fireball...");
        AntiFireball.mc.gameMode.attack((Player)AntiFireball.mc.player, (Entity)fireball);
        AntiFireball.mc.player.swing(InteractionHand.MAIN_HAND);
    }
}

