/*
 * Decompiled with CFR 0.152.
 */
package lf21.hwid;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import java.util.List;

public final class HardwareSnapshot
extends Record {
    private final String a;
    private final String b;
    private final String c;
    private final List<String> d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final List<String> i;
    private final List<String> j;
    private final String k;
    private final String l;
    private final String m;

    public HardwareSnapshot(String string, String string2, String string3, List<String> list, String string4, String string5, String string6, String string7, List<String> list2, List<String> list3, String string8, String string9, String string10) {
        this.a = string;
        this.b = string2;
        this.c = string3;
        this.d = list;
        this.e = string4;
        this.f = string5;
        this.g = string6;
        this.h = string7;
        this.i = list2;
        this.j = list3;
        this.k = string8;
        this.l = string9;
        this.m = string10;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{HardwareSnapshot.class, "a;b;c;d;e;f;g;h;i;j;k;l;m", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"}, this);
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{HardwareSnapshot.class, "a;b;c;d;e;f;g;h;i;j;k;l;m", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{HardwareSnapshot.class, "a;b;c;d;e;f;g;h;i;j;k;l;m", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"}, this, object);
    }

    public String cpu() {
        return this.a;
    }

    public String cpuIdentifier() {
        return this.b;
    }

    public String processorId() {
        return this.c;
    }

    public List<String> graphicsCards() {
        return this.d;
    }

    public String systemUuid() {
        return this.e;
    }

    public String machineGuid() {
        return this.f;
    }

    public String bios() {
        return this.g;
    }

    public String baseboard() {
        return this.h;
    }

    public List<String> macAddresses() {
        return this.i;
    }

    public List<String> disks() {
        return this.j;
    }

    public String a() {
        return this.k;
    }

    public String fullHash() {
        return this.l;
    }

    public String displayHash() {
        return this.m;
    }
}

