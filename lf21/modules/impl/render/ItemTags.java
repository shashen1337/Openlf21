/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lf21.event.EventTarget;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.WorldOverlay2DEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.player.InventoryManager;
import lf21.modules.impl.render.m;
import lf21.modules.impl.render.x;
import lf21.modules.impl.render.y;
import lf21.render.D;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.utils.b.i;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.b;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class ItemTags
extends Module {
    private final Map<Integer, m> p;
    private static final int l;
    private static final String[] r;
    private long q;
    private static final float n;
    private final List<y> o = new ArrayList<y>();
    public static ItemTags j;
    private static final u_0 m;
    private static final long k;

    @EventTarget
    public void a(RenderEvent renderEvent) {
        ItemEntity itemEntity;
        if (!this.isEnabled()) {
            return;
        }
        if (ItemTags.mc.level == null || ItemTags.mc.player == null) {
            return;
        }
        long l2 = System.nanoTime();
        if (l2 - this.q < 100000000L) {
            return;
        }
        this.q = l2;
        this.o.clear();
        b.a();
        ArrayList<ItemEntity> arrayList = new ArrayList<ItemEntity>();
        for (Entity entity : ItemTags.mc.level.entitiesForRendering()) {
            if (!(entity instanceof ItemEntity) || (itemEntity = (ItemEntity)entity).getItem().isEmpty() || !(ItemTags.mc.player.distanceToSqr((Entity)itemEntity) <= 4096.0)) continue;
            arrayList.add(itemEntity);
        }
        if (arrayList.size() > 256) {
            arrayList.sort(Comparator.comparingDouble(arg_0 -> ((LocalPlayer)ItemTags.mc.player).distanceToSqr(arg_0)));
            arrayList.subList(256, arrayList.size()).clear();
        }
        Object object = new boolean[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); ++i2) {
            if (object[i2] != false) continue;
            itemEntity = (ItemEntity)arrayList.get(i2);
            object[i2] = true;
            ArrayList<ItemEntity> arrayList2 = new ArrayList<ItemEntity>();
            arrayList2.add(itemEntity);
            for (int i3 = i2 + 1; i3 < arrayList.size(); ++i3) {
                if (object[i3] != false || !(itemEntity.position().distanceToSqr(((ItemEntity)arrayList.get(i3)).position()) <= 2.25)) continue;
                object[i3] = true;
                arrayList2.add((ItemEntity)arrayList.get(i3));
            }
            LinkedHashMap<String, x> linkedHashMap = new LinkedHashMap<String, x>();
            Vec3 vec3 = Vec3.ZERO;
            for (ItemEntity itemEntity2 : arrayList2) {
                vec3 = vec3.add(itemEntity2.position());
                ItemStack itemStack = itemEntity2.getItem();
                String string = itemStack.getHoverName().getString();
                x x3 = (x)linkedHashMap.get(string);
                if (x3 == null) {
                    linkedHashMap.put(string, new x(itemStack.copy(), itemStack.getCount(), InventoryManager.d(itemStack)));
                    continue;
                }
                linkedHashMap.put(string, new x(x3.a, x3.b + itemStack.getCount(), x3.c));
            }
            vec3 = vec3.scale(1.0 / (double)arrayList2.size()).add(0.0, 0.55, 0.0);
            ArrayList arrayList3 = new ArrayList(linkedHashMap.values());
            arrayList3.sort(Comparator.comparing(x2 -> !x2.c).thenComparing(x2 -> x2.a.getHoverName().getString()));
            this.o.add(new y(itemEntity.getId(), vec3, arrayList3, ItemTags.mc.player.distanceToSqr(vec3)));
        }
        this.o.sort(Comparator.comparingDouble(y::d).reversed());
    }

    public ItemTags() {
        super("ItemTags", Category.RENDER);
        this.p = new HashMap<Integer, m>();
        j = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    protected void onDisable() {
        long l2 = System.nanoTime();
        this.p.values().forEach(m2 -> m2.a(false, l2));
        super.onDisable();
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String c(char[] cArray) {
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
                    case 0 -> 96;
                    case 1 -> 42;
                    case 2 -> 111;
                    case 3 -> 35;
                    case 4 -> 43;
                    case 5 -> 29;
                    default -> 95;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        n = 0.78f;
        l = 256;
        k = 100000000L;
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "@R\b)^\nN\u007f|8\u0013\u0002@R";
        int n3 = "@R\b)^\nN\u007f|8\u0013\u0002@R".length();
        int n4 = 2;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = ItemTags.c(ItemTags.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                r = stringArray;
                m = t_0.a(16.25f);
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @Override
    protected boolean e() {
        return true;
    }

    @EventTarget
    public void a(WorldOverlay2DEvent worldOverlay2DEvent) {
        if (ItemTags.mc.player == null) {
            this.p.clear();
            return;
        }
        long l2 = System.nanoTime();
        HashSet<Integer> hashSet = new HashSet<Integer>();
        if (this.isEnabled()) {
            for (y object : this.o) {
                hashSet.add(object.a);
                m m3 = this.p.computeIfAbsent(object.a, n2 -> new m(object, l2));
                m3.c = object;
                m3.a(true, l2);
            }
        }
        this.p.forEach((n2, m2) -> {
            if (!hashSet.contains(n2)) {
                m2.a(false, l2);
            }
        });
        Iterator<Object> iterator = this.p.entrySet().iterator();
        while (iterator.hasNext()) {
            m m4 = (m)((Map.Entry)iterator.next()).getValue();
            float f2 = m4.a(l2);
            if (!m4.d && f2 <= 0.002f) {
                iterator.remove();
                continue;
            }
            y y2 = m4.c;
            i i2 = b.a(y2.b.x, y2.b.y, y2.b.z, worldOverlay2DEvent.c());
            if (i2 == null || i2.a == Float.MAX_VALUE) continue;
            float f3 = 0.0f;
            for (x x2 : y2.c) {
                String[] stringArray = r;
                String string = x2.a.getHoverName().getString() + " x" + x2.b;
                f3 = Math.max(f3, m.b(string));
            }
            float f4 = m4.b == 0L ? 0.016666668f : Math.min(0.08f, (float)(l2 - m4.b) / 1.0E9f);
            m4.b = l2;
            float f5 = 1.0f - (float)Math.exp(-13.5f * f4);
            m4.a += ((f3 += 34.0f) - m4.a) * f5;
            if (Math.abs(m4.a - f3) < 0.05f) {
                m4.a = f3;
            }
            float f6 = m4.a;
            float f7 = 21.0f;
            float f8 = (float)y2.c.size() * f7 + 8.0f;
            float f9 = -f6 * 0.5f;
            float f10 = -f8;
            float f11 = Math.max(0.001f, f2);
            worldOverlay2DEvent.a().pushPose();
            worldOverlay2DEvent.a().translate(i2.a, i2.b, 0.0f);
            worldOverlay2DEvent.a().scale(0.78f * f11, 0.78f * f11, 1.0f);
            RenderUtil.a(worldOverlay2DEvent.a(), f9, f10, f6, f8, 7.0f, 26.0f, 1.0f, 0);
            float f12 = f6;
            float f13 = f8;
            Renderer.render(worldOverlay2DEvent.b(), drawContext -> {
                try (D d2 = new D();){
                    d2.a(true);
                    d2.a(0x40000000);
                    drawContext.a(Q.a(f9, f10, f12, f13, 7.0f), d2);
                }
            });
            float f14 = f10 + 4.0f;
            for (x x3 : y2.c) {
                int n3 = x3.c ? -789001 : -5393478;
                ItemStack itemStack = x3.a.copy();
                itemStack.setCount(1);
                worldOverlay2DEvent.b().renderItem(itemStack, Math.round(f9 + 5.0f), Math.round(f14 + 2.5f));
                String string = x3.a.getHoverName().getString() + " x" + x3.b;
                float f15 = f14 + (f7 + m.d().f()) * 0.5f - 1.0f;
                Renderer.render(worldOverlay2DEvent.b(), drawContext -> {
                    try (D d2 = new D();){
                        d2.a(true);
                        d2.a(n3);
                        drawContext.a(string, f9 + 25.0f, f15, m, d2);
                    }
                });
                f14 += f7;
            }
            worldOverlay2DEvent.a().popPose();
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x5F);
        }
        return cArray;
    }
}

