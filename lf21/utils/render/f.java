/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.render;

import com.mojang.blaze3d.platform.NativeImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lf21.manager.ConfigManager;
import lombok.Generated;
import net.minecraft.client.renderer.texture.DynamicTexture;

public final class f {
    private static final Map<String, DynamicTexture> a;
    private static final String[] b;

    public static DynamicTexture a(String string) {
        File file = new File(ConfigManager.CONFIG_DIR, string);
        if (!file.exists()) {
            System.out.println("Failed to find target file!");
            return f.a();
        }
        DynamicTexture dynamicTexture = a.get(string);
        if (dynamicTexture == null) {
            try (FileInputStream fileInputStream = new FileInputStream(file);){
                NativeImage nativeImage = NativeImage.read((InputStream)fileInputStream);
                dynamicTexture = new DynamicTexture(nativeImage);
                a.put(string, dynamicTexture);
            }
            catch (IOException iOException) {
                return f.a();
            }
        }
        return dynamicTexture;
    }

    private static DynamicTexture a() {
        String[] stringArray = b;
        DynamicTexture dynamicTexture = a.get("__missing_texture__");
        if (dynamicTexture == null) {
            NativeImage nativeImage = new NativeImage(NativeImage.Format.RGBA, 2, 2, false);
            nativeImage.setPixelRGBA(0, 0, -65281);
            nativeImage.setPixelRGBA(1, 0, -16777216);
            nativeImage.setPixelRGBA(0, 1, -16777216);
            nativeImage.setPixelRGBA(1, 1, -65281);
            dynamicTexture = new DynamicTexture(nativeImage);
            a.put("__missing_texture__", dynamicTexture);
        }
        return dynamicTexture;
    }

    public static HashMap<String, String> b(String string) {
        String[] stringArray;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (string == null) {
            return hashMap;
        }
        for (String string2 : stringArray = string.split("&")) {
            int n2 = string2.indexOf(61);
            if (n2 != -1) {
                String string3 = string2.substring(0, n2);
                String string4 = string2.substring(n2 + 1);
                hashMap.put(URLDecoder.decode(string3, StandardCharsets.UTF_8), URLDecoder.decode(string4, StandardCharsets.UTF_8));
                continue;
            }
            hashMap.put(URLDecoder.decode(string2, StandardCharsets.UTF_8), "");
        }
        return hashMap;
    }

    @Generated
    private f() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static UnsupportedEncodingException a(UnsupportedEncodingException unsupportedEncodingException) {
        return unsupportedEncodingException;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "e\bvF\u0014\u001b%\u0011\u0001?@@\u001b:X\u0014f\u0015W\u001e7B\u0013?TZ\u0016vR\u0001q[[\u0006vS\u0005?\\Z\u0001\"P\u000ek\\U\u00063U\u0013n?r\\G\u0001?_\u0007@AQ\n\"D\u0012zjk";
                var4_3 = "e\bvF\u0014\u001b%\u0011\u0001?@@\u001b:X\u0014f\u0015W\u001e7B\u0013?TZ\u0016vR\u0001q[[\u0006vS\u0005?\\Z\u0001\"P\u000ek\\U\u00063U\u0013n?r\\G\u0001?_\u0007@AQ\n\"D\u0012zjk".length();
                var1_4 = 50;
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
                    var5[var3_1++] = f.a(f.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "w\u0001vYQ\u0016vE\u000f?S]\u001c2\u0011\u0014~GS\u0017\"\u0011\u0006vYQS\u0013n?r\\G\u0001?_\u0007@AQ\n\"D\u0012zjk";
                    var4_3 = "w\u0001vYQ\u0016vE\u000f?S]\u001c2\u0011\u0014~GS\u0017\"\u0011\u0006vYQS\u0013n?r\\G\u0001?_\u0007@AQ\n\"D\u0012zjk".length();
                    var1_4 = 27;
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
                    var5[var3_1++] = f.a(f.c(v1));
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
        f.b = var5;
        f.a = new HashMap<String, DynamicTexture>();
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x56);
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
                    case 0 -> 49;
                    case 1 -> 96;
                    case 2 -> 31;
                    case 3 -> 53;
                    case 4 -> 52;
                    case 5 -> 114;
                    default -> 86;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

