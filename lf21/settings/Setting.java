/*
 * Decompiled with CFR 0.152.
 */
package lf21.settings;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lf21.settings.SettingVisibility;
import lombok.Generated;

public abstract class Setting<T> {
    private String name;
    private T value;
    private SettingVisibility visibility;

    public Setting(String string, T t2) {
        this.name = string;
        this.value = t2;
        this.visibility = () -> true;
        this.onInit(t2);
    }

    public void onInit(T t2) {
    }

    public void onChanged(T t2, T t3) {
    }

    public void setValue(T t2) {
        T t3 = this.value;
        this.value = t2;
        this.onChanged(t3, t2);
    }

    public abstract void save(JsonObject var1);

    public abstract void load(JsonElement var1);

    @Generated
    public Setting(String string, T t2, SettingVisibility settingVisibility) {
        this.name = string;
        this.value = t2;
        this.visibility = settingVisibility;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public void setName(String string) {
        this.name = string;
    }

    @Generated
    public T getValue() {
        return this.value;
    }

    @Generated
    public SettingVisibility getVisibility() {
        return this.visibility;
    }

    @Generated
    public void setVisibility(SettingVisibility settingVisibility) {
        this.visibility = settingVisibility;
    }
}

