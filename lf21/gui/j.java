/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class j {
    private static final DateTimeFormatter a = DateTimeFormatter.ofPattern("MMddHH");

    private j() {
    }

    public static String a() {
        return "v1.1 " + a.format(LocalDateTime.now());
    }
}

