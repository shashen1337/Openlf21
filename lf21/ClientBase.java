/*
 * Decompiled with CFR 0.152.
 */
package lf21;

import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientBase {
    public static Minecraft mc;
    public static Logger logger;
    public static float yaw;
    public static boolean isLoading;
    public static final ConcurrentLinkedQueue<Runnable> delayPackets;

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string = ClientBase.a(ClientBase.e(">\u0011)\b\u001bR"));
        logger = LogManager.getLogger((String)string);
        delayPackets = new ConcurrentLinkedQueue();
    }

    private static char[] e(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x31);
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
                    case 0 -> 125;
                    case 1 -> 125;
                    case 2 -> 64;
                    case 3 -> 109;
                    case 4 -> 117;
                    case 5 -> 38;
                    default -> 49;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

