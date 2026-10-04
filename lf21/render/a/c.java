/*
 * Decompiled with CFR 0.152.
 */
package lf21.render.a;

import io.netty.util.collection.IntObjectHashMap;
import java.lang.invoke.LambdaMetafactory;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class c {
    public static final Supplier<Map<Integer, String>> a;
    public static final Supplier<Map<Integer, String>> b;
    public static final Pattern c;
    private static final String[] d;

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "\u001c-3d>0<\"\\dah2pv\u000ffj\u007f7\u0011\u0007y\u0011>\u001c'a\u0002\rfm\u001d$`\u0005ygm<Q\"py%/0b8-{sxoQ`qlv\u0019\u001e'\u0017%\u000ffi\u001bS`v\u000eeh\u001c'av/\u0010*i'bfinjn'c{/pjn'cgze\u0005\u000f-,b8\u0003\u0019\u0014p\b\u001c-3d>0<\"";
                var4_3 = "\u001c-3d>0<\"\\dah2pv\u000ffj\u007f7\u0011\u0007y\u0011>\u001c'a\u0002\rfm\u001d$`\u0005ygm<Q\"py%/0b8-{sxoQ`qlv\u0019\u001e'\u0017%\u000ffi\u001bS`v\u000eeh\u001c'av/\u0010*i'bfinjn'c{/pjn'cgze\u0005\u000f-,b8\u0003\u0019\u0014p\b\u001c-3d>0<\"".length();
                var1_4 = 8;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.render.a.c.a(lf21.render.a.c.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u000f-,b8\u0003\u0019\u0014p";
                    var4_3 = "\u000f-,b8\u0003\u0019\u0014p".length();
                    var1_4 = 5;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = lf21.render.a.c.a(lf21.render.a.c.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
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
        lf21.render.a.c.d = var5;
        lf21.render.a.c.a = (Supplier<Map>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, c(), ()Ljava/util/Map;)();
        lf21.render.a.c.b = (Supplier<Map>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, b(), ()Ljava/util/Map;)();
        lf21.render.a.c.c = Pattern.compile("(#(?:/\\*(?:[^*]|\\*+[^*/])*\\*+/|\\h)*import(?:/\\*(?:[^*]|\\*+[^*/])*\\*+/|\\h)*(?:\"(.*)\"|<(.*)>))");
    }

    private static /* synthetic */ Map b() {
        IntObjectHashMap intObjectHashMap = new IntObjectHashMap();
        String[] stringArray = d;
        intObjectHashMap.put(0, (Object)"Position");
        intObjectHashMap.put(1, (Object)"UV0");
        return intObjectHashMap;
    }

    private static Map<Integer, String> a() {
        IntObjectHashMap intObjectHashMap = new IntObjectHashMap();
        String[] stringArray = d;
        intObjectHashMap.put(0, (Object)"Position");
        intObjectHashMap.put(1, (Object)"Color");
        return intObjectHashMap;
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
                    case 0 -> 76;
                    case 1 -> 66;
                    case 2 -> 64;
                    case 3 -> 13;
                    case 4 -> 74;
                    case 5 -> 89;
                    default -> 83;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x53);
        }
        return cArray;
    }

    private static /* synthetic */ Map c() {
        IntObjectHashMap intObjectHashMap = new IntObjectHashMap();
        String[] stringArray = d;
        intObjectHashMap.put(0, (Object)"Position");
        intObjectHashMap.put(1, (Object)"UV0");
        intObjectHashMap.put(2, (Object)"Color");
        return intObjectHashMap;
    }
}

