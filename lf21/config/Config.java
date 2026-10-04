/*
 * Decompiled with CFR 0.152.
 */
package lf21.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import lf21.manager.ConfigManager;
import lombok.Generated;

public abstract class Config {
    private final String name;
    private final File file;

    public Config(String string) {
        this.name = string;
        this.file = new File(ConfigManager.CONFIG_DIR, string);
    }

    public abstract void read(BufferedReader var1) throws IOException;

    public abstract void save(BufferedWriter var1) throws IOException;

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public File getFile() {
        return this.file;
    }
}

