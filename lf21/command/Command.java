/*
 * Decompiled with CFR 0.152.
 */
package lf21.command;

import lombok.Generated;

public abstract class Command {
    private final String prefix;
    private final String[] aliases;

    public abstract void onCommand(String[] var1);

    public abstract String[] onTab(String[] var1);

    @Generated
    public Command(String string, String[] stringArray) {
        this.prefix = string;
        this.aliases = stringArray;
    }

    @Generated
    public String getPrefix() {
        return this.prefix;
    }

    @Generated
    public String[] getAliases() {
        return this.aliases;
    }
}

