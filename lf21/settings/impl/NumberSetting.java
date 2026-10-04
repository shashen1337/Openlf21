/*
 * Decompiled with CFR 0.152.
 */
package lf21.settings.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lf21.settings.Setting;
import lf21.settings.SettingVisibility;
import lombok.Generated;

public class NumberSetting
extends Setting<Number> {
    private final Number min;
    private final Number max;
    private final Number step;

    public NumberSetting(String string, Number number, Number number2, Number number3, Number number4) {
        super(string, number);
        this.min = number2;
        this.max = number3;
        this.step = number4;
    }

    public NumberSetting(String string, Number number, Number number2, Number number3, Number number4, SettingVisibility settingVisibility) {
        super(string, number, settingVisibility);
        this.min = number2;
        this.max = number3;
        this.step = number4;
    }

    @Override
    public void save(JsonObject jsonObject) {
        jsonObject.addProperty(this.getName(), (Number)this.getValue());
    }

    @Override
    public void load(JsonElement jsonElement) {
        this.setValue(jsonElement.getAsNumber());
    }

    @Generated
    public Number getMin() {
        return this.min;
    }

    @Generated
    public Number getMax() {
        return this.max;
    }

    @Generated
    public Number getStep() {
        return this.step;
    }
}

