/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.util.List;
import java.util.Map;
import lf21.modules.impl.world.q;

final class i {
    private final Map<Integer, List<q>> a;
    private final String b;
    private final String c;
    private final int d;

    private i(Map<Integer, List<q>> map, String string, String string2) {
        this.a = map;
        this.b = string;
        this.c = string2;
        this.d = map.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    private int a() {
        return this.a.values().stream().mapToInt(List::size).sum();
    }
}

