/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import java.util.concurrent.ThreadLocalRandom;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.settings.impl.NumberSetting;

public final class FakeFPS
extends Module {
    public static FakeFPS INSTANCE;
    public final NumberSetting minFps = new NumberSetting("Min FPS", 60, 1, 1000, 1);
    public final NumberSetting maxFps = new NumberSetting("Max FPS", 120, 1, 1000, 1);
    private static final long MIN_SAMPLE_INTERVAL_NANOS;
    private static final long MAX_SAMPLE_INTERVAL_NANOS;
    private static final int MAX_SUPPORTED_FPS;
    private volatile int sampledFps = 60;
    private volatile long nextSampleNanos;

    public FakeFPS() {
        super("FakeFPS", Category.RENDER);
        INSTANCE = this;
        this.sampledFps = this.getLowerBound();
        this.nextSampleNanos = 0L;
    }

    @Override
    protected void onEnable() {
        this.sampleNow(System.nanoTime());
    }

    @Override
    protected void onDisable() {
        this.nextSampleNanos = 0L;
    }

    public int getDisplayedFps(int n2) {
        if (!this.isEnabled()) {
            return n2;
        }
        long l2 = System.nanoTime();
        if (l2 >= this.nextSampleNanos) {
            this.sampleNow(l2);
        }
        return this.sampledFps;
    }

    public static int hookFpsValue(int n2) {
        FakeFPS fakeFPS = INSTANCE;
        return fakeFPS == null ? n2 : fakeFPS.getDisplayedFps(n2);
    }

    private synchronized void sampleNow(long l2) {
        int n2 = this.getLowerBound();
        int n3 = this.getUpperBound(n2);
        this.sampledFps = ThreadLocalRandom.current().nextInt(n2, n3 + 1);
        long l3 = ThreadLocalRandom.current().nextLong(350000000L, 900000001L);
        this.nextSampleNanos = l2 + l3;
    }

    private int getLowerBound() {
        return this.clamp(((Number)this.minFps.getValue()).intValue());
    }

    private int getUpperBound(int n2) {
        return Math.max(n2, this.clamp(((Number)this.maxFps.getValue()).intValue()));
    }

    private int clamp(int n2) {
        return Math.max(1, Math.min(1000, n2));
    }

    static {
        MAX_SUPPORTED_FPS = 1000;
        MAX_SAMPLE_INTERVAL_NANOS = 900000000L;
        MIN_SAMPLE_INTERVAL_NANOS = 350000000L;
    }
}

