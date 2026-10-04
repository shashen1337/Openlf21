/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import lf21.ClientBase;
import lf21.modules.Category;
import lf21.render.Renderer;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.b.g;
import net.minecraft.client.gui.GuiGraphics;

public class d
extends ClientBase {
    private static final Map<Category, String> a = new HashMap<Category, String>();
    private final Map<Category, Float> b = new HashMap<Category, Float>();
    private Category c = Category.COMBAT;

    public void a(GuiGraphics guiGraphics, int n2, int n3, int n4, int n5, float f2, float f3) {
        try {
            int n6 = (int)(20.0f * f2);
            int n7 = (int)(25.0f * f2);
            int n8 = (int)(80.0f * f2);
            float f4 = 24.0f * f2;
            int n9 = (int)(420.0f * f2);
            int n10 = n2 + n9;
            int n11 = n3 + n8 - (int)(60.0f * f2);
            Category[] categoryArray = Category.a();
            Renderer.a(drawContext -> {
                u_0 u_02 = t_0.j(f4);
                for (int i2 = 0; i2 < categoryArray.length; ++i2) {
                    Category category = categoryArray[i2];
                    String string = a.get((Object)category);
                    if (string == null) continue;
                    int n8 = n10 + i2 * n7;
                    int n9 = n11;
                    this.a(category, n8, n9, n4, n5, n6);
                    int n10 = this.a(category);
                    if (category == this.c) {
                        int n11 = new Color(255, 255, 255, (int)(150.0f * f3)).getRGB();
                        T.a(string, (float)n8, (float)n9, u_02, this.a(n10, f3), n11, 8.0f * f2);
                        continue;
                    }
                    y_0.a(string, (float)n8, (float)n9, u_02, this.a(n10, f3));
                }
            });
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
    }

    private void a(Category category, int n2, int n3, int n4, int n5, int n6) {
        float f2 = this.b.getOrDefault((Object)category, Float.valueOf(0.0f)).floatValue();
        boolean bl = this.a(n2, n3, n4, n5, n6);
        this.b.put(category, Float.valueOf(g.a(f2, bl ? 1.0f : 0.0f, 0.12f)));
    }

    private int a(Category category) {
        if (category == this.c) {
            return -1;
        }
        float f2 = this.b.getOrDefault((Object)category, Float.valueOf(0.0f)).floatValue();
        return this.a(-7829368, -3355444, f2);
    }

    private int a(int n2, int n3, float f2) {
        float f3 = 1.0f - f2;
        int n4 = n2 >> 24 & 0xFF;
        int n5 = n2 >> 16 & 0xFF;
        int n6 = n2 >> 8 & 0xFF;
        int n7 = n2 & 0xFF;
        int n8 = n3 >> 24 & 0xFF;
        int n9 = n3 >> 16 & 0xFF;
        int n10 = n3 >> 8 & 0xFF;
        int n11 = n3 & 0xFF;
        int n12 = (int)((float)n4 * f3 + (float)n8 * f2);
        int n13 = (int)((float)n5 * f3 + (float)n9 * f2);
        int n14 = (int)((float)n6 * f3 + (float)n10 * f2);
        int n15 = (int)((float)n7 * f3 + (float)n11 * f2);
        return n12 << 24 | n13 << 16 | n14 << 8 | n15;
    }

    private boolean a(int n2, int n3, int n4, int n5, int n6) {
        int n7 = n6 / 2;
        return n4 >= n2 && n4 <= n2 + n6 && n5 >= n3 - n7 && n5 <= n3 + n7;
    }

    public boolean a(int n2, int n3, int n4, int n5, float f2) {
        int n6 = (int)(20.0f * f2);
        int n7 = (int)(25.0f * f2);
        int n8 = (int)(80.0f * f2);
        int n9 = (int)(420.0f * f2);
        int n10 = n2 + n9;
        int n11 = n3 + n8 - (int)(60.0f * f2);
        Category[] categoryArray = Category.a();
        for (int i2 = 0; i2 < categoryArray.length; ++i2) {
            Category category = categoryArray[i2];
            int n12 = n10 + i2 * n7;
            int n13 = n11;
            if (!this.a(n12, n13, n4, n5, n6)) continue;
            this.c = category;
            return true;
        }
        return false;
    }

    public Category a() {
        return this.c;
    }

    public void b(Category category) {
        this.c = category;
    }

    public boolean b(int n2, int n3, int n4, int n5, float f2) {
        int n6 = (int)(20.0f * f2);
        int n7 = (int)(25.0f * f2);
        int n8 = (int)(80.0f * f2);
        int n9 = (int)(420.0f * f2);
        int n10 = n2 + n9;
        int n11 = n3 + n8 - (int)(60.0f * f2);
        Category[] categoryArray = Category.a();
        for (int i2 = 0; i2 < categoryArray.length; ++i2) {
            int n12 = n10 + i2 * n7;
            int n13 = n11;
            if (!this.a(n12, n13, n4, n5, n6)) continue;
            return true;
        }
        return false;
    }

    public int a(float f2) {
        int n2 = (int)(20.0f * f2);
        int n3 = (int)(25.0f * f2);
        return (Category.a().length - 1) * n3 + n2;
    }

    public int b(float f2) {
        return (int)(20.0f * f2);
    }

    static {
        a.put(Category.COMBAT, "\ue074");
        a.put(Category.MOVEMENT, "\ue511");
        a.put(Category.PLAYER, "\ue7fd");
        a.put(Category.RENDER, "\ue8f4");
        a.put(Category.EXPLOIT, "\ue894");
        a.put(Category.WORLD, "\ue5d3");
        a.put(Category.MISC, "\ue24d");
    }
}

