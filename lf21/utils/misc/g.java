/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import lf21.manager.ConfigManager;
import lf21.utils.misc.a;

public final class g {
    private static final String[] a;

    public static void a(String string, String string2) {
        JOptionPane.showMessageDialog(null, string2, string, 0);
    }

    public static void a(String string, float f2) {
        Thread thread = new Thread(() -> {
            try {
                InputStream inputStream;
                block24: {
                    block25: {
                        inputStream = lf21.utils.misc.a.a(string);
                        try {
                            if (inputStream != null) break block24;
                            String[] stringArray = a;
                            System.out.println("SoundUtil: resource not found - " + string);
                            if (inputStream == null) break block25;
                        }
                        catch (Throwable throwable) {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                }
                                catch (Throwable throwable2) {
                                    throwable.addSuppressed(throwable2);
                                }
                            }
                            throw throwable;
                        }
                        inputStream.close();
                    }
                    return;
                }
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);){
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    try {
                        FloatControl floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                        floatControl.setValue(f2);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        // empty catch block
                    }
                    clip.start();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (Exception exception) {
                String[] stringArray = a;
                System.out.println("SoundUtil: failed to play resource " + string + " - " + exception.getMessage());
            }
        }, "LF21-Sound-" + lf21.utils.b.a.a(0, 100));
        thread.setDaemon(true);
        thread.start();
    }

    public static void b(String string, float f2) {
        File file = new File(ConfigManager.CONFIG_DIR, string);
        if (!file.exists()) {
            System.out.println("Failed to find target file!");
            return;
        }
        new Thread(() -> {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                FloatControl floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                floatControl.setValue(f2);
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException exception) {
                exception.printStackTrace();
            }
        }, "Netty Client IO #" + lf21.utils.b.a.a(0, 100)).start();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "\u0018\u001bY kY\f#\u0007/EQZCm\u0011\u001fBWC\u000b.\u0006Z_WBY+\f\u000f_\\\u0016Tm\u0011vS\r9\u001aZrT_\u001c#\u0017Zxw\u0016Z\u000btpK|N)^MX\u001d`";
                var4_3 = "\u0018\u001bY kY\f#\u0007/EQZCm\u0011\u001fBWC\u000b.\u0006Z_WBY+\f\u000f_\\\u0016Tm\u0011vS\r9\u001aZrT_\u001c#\u0017Zxw\u0016Z\u000btpK|N)^MX\u001d`".length();
                var1_4 = 3;
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
                    var5[var3_1++] = g.a(g.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "kY\f#\u0007/EQZCm\u0005\u001bXTS\u001dm\u0017\u0015\u0011HZ\u00184C\bTKY\f?\u0000\u001f\u0011\u001b~W\u0010!\u0006\u001e\u0011LYY+\n\u0014U\u0018B\u0018?\u0004\u001fE\u0018P\u0010!\u0006[";
                    var4_3 = "kY\f#\u0007/EQZCm\u0005\u001bXTS\u001dm\u0017\u0015\u0011HZ\u00184C\bTKY\f?\u0000\u001f\u0011\u001b~W\u0010!\u0006\u001e\u0011LYY+\n\u0014U\u0018B\u0018?\u0004\u001fE\u0018P\u0010!\u0006[".length();
                    var1_4 = 35;
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
                    var5[var3_1++] = g.a(g.a(v1));
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
        g.a = var5;
    }

    private static char[] a(String string) {
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
                    case 0 -> 56;
                    case 1 -> 54;
                    case 2 -> 121;
                    case 3 -> 77;
                    case 4 -> 99;
                    case 5 -> 122;
                    default -> 49;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

