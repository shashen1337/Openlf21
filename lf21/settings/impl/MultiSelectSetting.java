/*
 * Decompiled with CFR 0.152.
 */
package lf21.settings.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lf21.settings.Setting;
import lf21.settings.SettingVisibility;
import lombok.Generated;

public class MultiSelectSetting
extends Setting<List<String>> {
    private final List<String> options;

    public MultiSelectSetting(String string, String ... stringArray) {
        super(string, new ArrayList());
        this.options = new ArrayList<String>(Arrays.asList(stringArray));
    }

    public void setOptions(Collection<String> collection) {
        ArrayList<String> arrayList = new ArrayList<String>(collection);
        if (!this.options.equals(arrayList)) {
            this.options.clear();
            this.options.addAll(arrayList);
        }
        ((List)this.getValue()).removeIf(string -> !this.options.contains(string));
    }

    public MultiSelectSetting withDefaults(String ... stringArray) {
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(stringArray));
        this.setValue(arrayList);
        return this;
    }

    public boolean isSelected(String string) {
        return ((List)this.getValue()).contains(string);
    }

    public MultiSelectSetting withVisibility(SettingVisibility settingVisibility) {
        this.setVisibility(settingVisibility);
        return this;
    }

    @Override
    public void save(JsonObject jsonObject) {
        JsonArray jsonArray = new JsonArray();
        for (String string : (List)this.getValue()) {
            jsonArray.add(string);
        }
        jsonObject.add(this.getName(), (JsonElement)jsonArray);
    }

    @Override
    public void load(JsonElement jsonElement) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (jsonElement.isJsonArray()) {
            for (JsonElement jsonElement2 : jsonElement.getAsJsonArray()) {
                arrayList.add(jsonElement2.getAsString());
            }
        }
        this.setValue(arrayList);
    }

    @Generated
    public List<String> getOptions() {
        return this.options;
    }
}

