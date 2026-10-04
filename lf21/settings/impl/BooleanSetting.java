/*
 * Decompiled with CFR 0.152.
 */
package lf21.settings.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lf21.settings.Setting;
import lf21.settings.SettingVisibility;

public class BooleanSetting
extends Setting<Boolean> {
    public BooleanSetting(String string, Boolean bl) {
        super(string, bl);
    }

    public BooleanSetting(String string, Boolean bl, SettingVisibility settingVisibility) {
        super(string, bl, settingVisibility);
    }

    @Override
    public void save(JsonObject jsonObject) {
        jsonObject.addProperty(this.getName(), (Boolean)this.getValue());
    }

    @Override
    public void load(JsonElement jsonElement) {
        this.setValue(jsonElement.getAsBoolean());
    }
}

