/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Optional;
import lf21.render.b_0;
import lf21.render.k_0;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;

final class O
implements MultiBufferSource {
    private int c = 255;
    private int e = 255;
    private int d = 255;
    private int b = 255;
    private final MultiBufferSource.BufferSource a = MultiBufferSource.immediate((BufferBuilder)new BufferBuilder(256));

    public VertexConsumer getBuffer(RenderType renderType) {
        Optional optional;
        Optional optional2 = optional = renderType.isOutline() ? Optional.of(renderType) : renderType.outline();
        if (optional.isEmpty()) {
            return k_0.a;
        }
        return new b_0(this.a.getBuffer((RenderType)optional.get()), this.b, this.c, this.d, this.e);
    }

    public void a() {
        this.a.endBatch();
    }

    public void a(int n2, int n3, int n4, int n5) {
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.e = n5;
    }

    private O() {
    }
}

