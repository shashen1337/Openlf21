/*
 * Decompiled with CFR 0.152.
 */
package lf21.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import lf21.event.EventMarker;
import lf21.event.EventTarget;
import lf21.event.a;
import lf21.event.c;
import lf21.event.e;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class EventBus {
    private static final Logger a;
    private final Map<Class<? extends EventMarker>, List<e>> b = new HashMap<Class<? extends EventMarker>, List<e>>();
    private static final String[] c;

    public void register(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (!this.a(method)) continue;
            this.a(method, object);
        }
    }

    public void a(Object object, Class<? extends EventMarker> clazz) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (!this.a(method, clazz)) continue;
            this.a(method, object);
        }
    }

    public void unregister(Object object) {
        for (List<e> list : this.b.values()) {
            list.removeIf(e2 -> e2.a().equals(object));
        }
    }

    public void b(Object object, Class<? extends EventMarker> clazz) {
        if (this.b.containsKey(clazz)) {
            this.b.get(clazz).removeIf(e2 -> e2.a().equals(object));
        }
    }

    private void a(Method method, Object object) {
        List list;
        Class<?> clazz2 = method.getParameterTypes()[0];
        e e2 = new e(object, method, method.getAnnotation(EventTarget.class).a());
        if (!e2.b().isAccessible()) {
            e2.b().setAccessible(true);
        }
        if (!(list = this.b.computeIfAbsent(clazz2, clazz -> new CopyOnWriteArrayList())).contains(e2)) {
            list.add(e2);
            this.b(clazz2);
        }
    }

    public void a(Class<? extends EventMarker> clazz) {
        Iterator<Map.Entry<Class<? extends EventMarker>, List<e>>> iterator = this.b.entrySet().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getKey().equals(clazz)) continue;
            iterator.remove();
            break;
        }
    }

    private void b(Class<? extends EventMarker> clazz) {
        List<e> list = this.b.get(clazz);
        CopyOnWriteArrayList<e> copyOnWriteArrayList = new CopyOnWriteArrayList<e>();
        for (byte by : lf21.event.c.f) {
            for (e e2 : list) {
                if (e2.c() != by) continue;
                copyOnWriteArrayList.add(e2);
            }
        }
        this.b.put(clazz, copyOnWriteArrayList);
    }

    private boolean a(Method method) {
        return method.getParameterTypes().length == 1 && method.isAnnotationPresent(EventTarget.class);
    }

    private boolean a(Method method, Class<? extends EventMarker> clazz) {
        return this.a(method) && method.getParameterTypes()[0].equals(clazz);
    }

    public EventMarker call(EventMarker eventMarker) {
        List<e> list = this.b.get(eventMarker.getClass());
        if (list == null) {
            return eventMarker;
        }
        if (eventMarker instanceof a) {
            a a2 = (a)eventMarker;
            for (e e2 : list) {
                this.a(e2, eventMarker);
                if (!a2.b()) continue;
                break;
            }
        } else {
            for (e e3 : list) {
                this.a(e3, eventMarker);
            }
        }
        return eventMarker;
    }

    private void a(e e2, EventMarker eventMarker) {
        try {
            e2.b().invoke(e2.a(), eventMarker);
        }
        catch (InvocationTargetException invocationTargetException) {
            String[] stringArray = c;
            a.error("invocation target {} {} {}", e2.a, (Object)e2.b, (Object)invocationTargetException);
            invocationTargetException.printStackTrace();
        }
        catch (Exception exception) {
            a.error("{} {}", e2.a, (Object)e2.b);
            exception.printStackTrace();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "~ SjgA\u0007~!K%pA\u0001p+Q%\u007f]Sl3\u0005~y\u0005l3\u0005~y";
        int n3 = "~ SjgA\u0007~!K%pA\u0001p+Q%\u007f]Sl3\u0005~y\u0005l3\u0005~y".length();
        int n4 = 26;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = EventBus.a(EventBus.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                c = stringArray;
                a = LogManager.getLogger(EventBus.class);
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x73);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
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
                    case 0 -> 23;
                    case 1 -> 78;
                    case 2 -> 37;
                    case 3 -> 5;
                    case 4 -> 4;
                    case 5 -> 32;
                    default -> 115;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

