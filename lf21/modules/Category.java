/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules;

public final class Category
extends Enum<Category> {
    public static final /* enum */ Category COMBAT;
    public static final /* enum */ Category MOVEMENT;
    public static final /* enum */ Category PLAYER;
    public static final /* enum */ Category RENDER;
    public static final /* enum */ Category EXPLOIT;
    public static final /* enum */ Category WORLD;
    public static final /* enum */ Category MISC;
    public String g;
    private static final /* synthetic */ Category[] h;

    public static Category[] a() {
        return (Category[])h.clone();
    }

    public static Category a(String string) {
        return Enum.valueOf(Category.class, string);
    }

    private Category(String string2) {
        this.g = string2;
    }

    public static Category b(String string) {
        for (Category category : Category.a()) {
            if (!category.g.equalsIgnoreCase(string)) continue;
            return category;
        }
        return COMBAT;
    }

    private static /* synthetic */ Category[] b() {
        return new Category[]{COMBAT, MOVEMENT, PLAYER, RENDER, EXPLOIT, WORLD, MISC};
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[14];
                var4_1 = 0;
                var3_2 = "k\t\u00110w\u0001\be)*\u0017[0\\|\u0005\u007f).\u001eR\u0005o\u000e\u0013!b\u0006z#2\u0016S'\u0007m>,\u001eY<F\u0006z\u0003\u00126s\u0007\be\t\n7{\u0010|\\\u0005\u007f\t\u000e>r\u0006k)1\u0010W!\u0006x*=\u000bS'\u0004e\u000f\u000f1";
                var5_3 = "k\t\u00110w\u0001\be)*\u0017[0\\|\u0005\u007f).\u001eR\u0005o\u000e\u0013!b\u0006z#2\u0016S'\u0007m>,\u001eY<F\u0006z\u0003\u00126s\u0007\be\t\n7{\u0010|\\\u0005\u007f\t\u000e>r\u0006k)1\u0010W!\u0006x*=\u000bS'\u0004e\u000f\u000f1".length();
                var2_4 = 6;
                var1_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var1_5;
                    v1 = var3_2.substring(v0, v0 + var2_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var0[var4_1++] = Category.a(Category.c(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "x\n\u001d+s\u0007\u0004e//\u0011";
                    var5_3 = "x\n\u001d+s\u0007\u0004e//\u0011".length();
                    var2_4 = 6;
                    var1_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var1_5;
                        v1 = var3_2.substring(v3, v3 + var2_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var0[var4_1++] = Category.a(Category.c(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    break block10;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl37:
                // 1 sources

                ** continue;
            }
        }
        Category.COMBAT = new Category("Combat");
        Category.MOVEMENT = new Category("Movement");
        Category.PLAYER = new Category("Player");
        Category.RENDER = new Category("Render");
        Category.EXPLOIT = new Category("World");
        Category.WORLD = new Category("Misc");
        Category.MISC = new Category("Ghost");
        Category.h = Category.b();
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x12);
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
                    case 0 -> 40;
                    case 1 -> 102;
                    case 2 -> 124;
                    case 3 -> 82;
                    case 4 -> 22;
                    case 5 -> 117;
                    default -> 18;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

