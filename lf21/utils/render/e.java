/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.image.BufferedImage;
import lf21.render.K;
import lf21.utils.render.RenderUtil;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.RandomStringUtils;

public class e {
    public K a = new K(b + RandomStringUtils.randomAlphanumeric((int)16));
    private static final String b = e.a(e.a("k/ \u000bM:\u000408=\u0012W<\u00040"));

    public e(BufferedImage bufferedImage) {
        RenderUtil.a(this.a, bufferedImage);
    }

    public void a() {
        RenderSystem.setShaderTexture((int)0, (ResourceLocation)this.a.a());
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x61);
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
                    case 0 -> 31;
                    case 1 -> 74;
                    case 2 -> 88;
                    case 3 -> 127;
                    case 4 -> 56;
                    case 5 -> 72;
                    default -> 97;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

