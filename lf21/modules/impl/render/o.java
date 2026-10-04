/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lombok.Generated;
import net.minecraft.core.BlockPos;

final class o {
    private final int c;
    private final int a;
    private final int b;

    @Generated
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof o)) {
            return false;
        }
        o o2 = (o)object;
        if (this.a != o2.a) {
            return false;
        }
        if (this.b != o2.b) {
            return false;
        }
        return this.c == o2.c;
    }

    @Generated
    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        n3 = n3 * 59 + this.a;
        n3 = n3 * 59 + this.b;
        n3 = n3 * 59 + this.c;
        return n3;
    }

    BlockPos a() {
        return new BlockPos(this.a, this.b, this.c);
    }

    o(BlockPos blockPos) {
        this.a = blockPos.getX();
        this.b = blockPos.getY();
        this.c = blockPos.getZ();
    }
}

