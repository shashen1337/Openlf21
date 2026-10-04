/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import java.util.Objects;
import java.util.stream.Stream;
import lf21.ClientBase;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;

public final class b
extends ClientBase {
    public static Stream<BlockEntity> a() {
        return b.b().flatMap(levelChunk -> levelChunk.getBlockEntities().values().stream());
    }

    public static Stream<LevelChunk> b() {
        block4: {
            if (b.mc.player != null && b.mc.level != null) break block4;
            return Stream.empty();
        }
        int n2 = Math.max(2, b.mc.options.getEffectiveRenderDistance()) + 3;
        int n3 = n2 * 2 + 1;
        ChunkPos chunkPos2 = b.mc.player.chunkPosition();
        ChunkPos chunkPos4 = new ChunkPos(chunkPos2.x - n2, chunkPos2.z - n2);
        ChunkPos chunkPos5 = new ChunkPos(chunkPos2.x + n2, chunkPos2.z + n2);
        return Stream.iterate(chunkPos4, chunkPos3 -> {
            int n2 = chunkPos3.x;
            int n3 = chunkPos3.z;
            if (++n2 > chunkPos.x) {
                n2 = chunkPos2.x;
                ++n3;
            }
            if (n3 > chunkPos.z) {
                throw new IllegalStateException("Stream limit didn't work.");
            }
            return new ChunkPos(n2, n3);
        }).limit((long)n3 * (long)n3).filter(chunkPos -> b.mc.level.hasChunk(chunkPos.x, chunkPos.z)).map(chunkPos -> b.mc.level.getChunk(chunkPos.x, chunkPos.z)).filter(Objects::nonNull);
    }

    private b() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

