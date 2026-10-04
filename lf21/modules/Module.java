/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.modules.Category;
import lf21.modules.a;
import lf21.settings.Setting;
import lombok.Generated;
import net.minecraft.sounds.SoundEvents;

public abstract class Module
extends ClientBase {
    private final String a;
    private final Category b;
    private int c;
    private final a d;
    private boolean e;
    private boolean f;
    private final List<Setting<?>> g;
    private static final String h;
    private static final Map<String, String> i;
    private static final String[] bb;

    protected Module(String string, Category category) {
        this.a = string;
        this.b = category;
        this.c = 0;
        this.d = new a(this.c);
        this.g = new ArrayList();
    }

    protected Module(String string, Category category, int n2) {
        this.a = string;
        this.b = category;
        this.c = n2;
        this.d = new a(this.c);
        this.g = new ArrayList();
    }

    public void setKey(int n2) {
        this.c = n2;
        this.d.a(n2);
    }

    public void a(Setting<?> setting) {
        this.g.add(setting);
    }

    public void b() {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object object = field.get(this);
                if (!(object instanceof Setting)) continue;
                this.a((Setting)object);
            }
            catch (IllegalAccessException illegalAccessException) {
                System.out.println("Failed to register value for module " + this.getName() + "!");
            }
        }
        Collections.shuffle(this.g);
    }

    public String c() {
        return i.getOrDefault(this.a, this.a);
    }

    public static void a(List<Module> list) {
        int n2;
        List<String> list2 = list.stream().map(Module::getName).distinct().toList();
        if (list2.size() < 2) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<String>(list2);
        Collections.shuffle(arrayList);
        boolean bl = true;
        for (n2 = 0; n2 < list2.size(); ++n2) {
            if (list2.get(n2).equals(arrayList.get(n2))) continue;
            bl = false;
            break;
        }
        if (bl) {
            Collections.rotate(arrayList, 1);
        }
        i.clear();
        for (n2 = 0; n2 < list2.size(); ++n2) {
            i.put(list2.get(n2), (String)arrayList.get(n2));
        }
    }

    public static void d() {
        i.clear();
    }

    public void setEnabled(boolean bl) {
        if (this.e == bl) {
            return;
        }
        this.e = bl;
        if (bl) {
            if (!this.f) {
                LF21Client.getInstance().getEventBus().register(this);
                this.f = true;
            }
            this.onEnable();
        } else {
            this.onDisable();
            if (!this.e()) {
                LF21Client.getInstance().getEventBus().unregister(this);
                this.f = false;
            }
        }
    }

    protected boolean e() {
        return false;
    }

    public void toggle() {
        boolean bl = this.isEnabled();
        this.setEnabled(!this.isEnabled());
        if (bl != this.isEnabled()) {
            if (mc != null && Module.mc.player != null) {
                Module.mc.player.playSound(this.isEnabled() ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF, 0.5f, this.isEnabled() ? 1.3f : 0.8f);
            }
            if (LF21Client.getInstance().getNotificationManager() != null) {
                LF21Client.getInstance().getNotificationManager().a(this, this.isEnabled());
            }
        }
    }

    protected void onEnable() {
    }

    protected void onDisable() {
    }

    @Generated
    public int getKey() {
        return this.c;
    }

    @Generated
    public String getName() {
        return this.a;
    }

    @Generated
    public Category f() {
        return this.b;
    }

    @Generated
    public a g() {
        return this.d;
    }

    @Generated
    public boolean isEnabled() {
        return this.e;
    }

    @Generated
    public List<Setting<?>> getSettings() {
        return this.g;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "$\u0013\u001b\u0001K!\u001b\u0016\u001dR\u001fK\"R\u0011\u0006\u0017\u001f\u000e3Z\u000e\u0007\u0017MH*IB\u001f\u001d\t[)^B$$\u0013\u001b\u0001K!\u001b\u0016\u001dR\u001fK\"R\u0011\u0006\u0017\u001f\u000e3Z\u000e\u0007\u0017MH*IB\u001f\u001d\t[)^B";
        int n3 = "$\u0013\u001b\u0001K!\u001b\u0016\u001dR\u001fK\"R\u0011\u0006\u0017\u001f\u000e3Z\u000e\u0007\u0017MH*IB\u001f\u001d\t[)^B$$\u0013\u001b\u0001K!\u001b\u0016\u001dR\u001fK\"R\u0011\u0006\u0017\u001f\u000e3Z\u000e\u0007\u0017MH*IB\u001f\u001d\t[)^B".length();
        int n4 = 36;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = Module.b(Module.f(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                bb = stringArray;
                h = bb[1];
                i = new ConcurrentHashMap<String, String>();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] f(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x3B);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 98;
                    case 1 -> 114;
                    case 2 -> 114;
                    case 3 -> 109;
                    case 4 -> 46;
                    case 5 -> 69;
                    default -> 59;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

