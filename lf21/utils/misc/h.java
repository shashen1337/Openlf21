/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import lf21.utils.misc.i;
import lf21.utils.misc.j;

public class h {
    private static final ScheduledExecutorService a = Executors.newScheduledThreadPool(3, new j());
    public static ExecutorService b = Executors.newCachedThreadPool(new i());

    public static void a(Runnable runnable, long l2, long l3, TimeUnit timeUnit) {
        a.scheduleAtFixedRate(runnable, l2, l3, timeUnit);
    }

    public static ScheduledFuture<?> a(Runnable runnable, long l2, TimeUnit timeUnit) {
        return a.schedule(runnable, l2, timeUnit);
    }

    public static void a(Runnable runnable) {
        b.execute(runnable);
    }
}

