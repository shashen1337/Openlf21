/*
 * Decompiled with CFR 0.152.
 */
package lf21.settings.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lf21.settings.Setting;
import lf21.settings.SettingVisibility;
import lombok.Generated;

public class ModeSetting
extends Setting<String> {
    private final String[] modes;

    public ModeSetting(String string, String ... stringArray) {
        super(string, null);
        this.modes = stringArray;
    }

    public ModeSetting withVisibility(SettingVisibility settingVisibility) {
        this.setVisibility(settingVisibility);
        return this;
    }

    public ModeSetting withDefault(String string) {
        this.setValue(string);
        return this;
    }

    public boolean is(String string) {
        return ((String)this.getValue()).equals(string);
    }

    @Override
    public void save(JsonObject jsonObject) {
        jsonObject.addProperty(this.getName(), (String)this.getValue());
    }

    @Override
    public void load(JsonElement jsonElement) {
        this.setValue(jsonElement.getAsString());
    }

    @Generated
    public String[] getModes() {
        return this.modes;
    }
}

