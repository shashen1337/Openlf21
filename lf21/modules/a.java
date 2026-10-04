/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules;

import java.util.HashMap;
import java.util.Map;

public class a {
    private int a;
    private static final Map<Integer, String> b;
    private static final String[] c;

    public a(int n2) {
        this.a = n2;
    }

    public String a() {
        if (this.a == 0) {
            String[] stringArray = c;
            return "None";
        }
        return b.getOrDefault(this.a, "Unknown");
    }

    public void a(int n2) {
        this.a = n2;
    }

    public int b() {
        return this.a;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[63];
                var3_1 = 0;
                var2_2 = "w :\u0015\u0004w :\u0012\u0005n\u0019}Wc\u0004w :\n\u0005o\u0000{\\r\u0004l\u0017^Q\u0006n#rVq7\u0004w :\t\u0003x\u0015v\u0004t\u001fwZ\u0003h\u0011x\u0003u\u001ei\u0002zA\u0002zB\u0004w :\f\u0004r\u001ftZ\u0004w :\u000e\u0003y\u0003y\u0002zC\u0002kA\u0006n#oOr1\u0002zD\u0004w :\r\u0002zF\u0002kB\u0004w :\u0010\bw :zy7qN\u0002zH\u0005l\u0002nlt\u0006p#rVq7\u0004w :\u000b\u0005y\u001enZe\u0006o\u0013hP{/\u0005l\u0011oLr\u0004p1vK\u0004w :\u000f\u0003zA+\u0004q\u0015tJ\u0002i\u0000\u0005n3nM{\u0002zG\u0006p#oOr1\u0004w :\b\u0004~\u001biO\u0004w :\u0011\u0004w :\u0006\u0007i\u001eqQx4z\u0004w :\u0002\u0004\u007f\u0011jL\u0004w :\u0014\u0003zA*\u0004r\u001ftZ\u0003zA(\u0004n1vK\u0003y\u001e~\u0002zE\u0005r\u0005ws|\u0005p3nM{\u0004w :\u0007\u0004p\u0015|K\u0004l\u0017OO";
                var4_3 = "w :\u0015\u0004w :\u0012\u0005n\u0019}Wc\u0004w :\n\u0005o\u0000{\\r\u0004l\u0017^Q\u0006n#rVq7\u0004w :\t\u0003x\u0015v\u0004t\u001fwZ\u0003h\u0011x\u0003u\u001ei\u0002zA\u0002zB\u0004w :\f\u0004r\u001ftZ\u0004w :\u000e\u0003y\u0003y\u0002zC\u0002kA\u0006n#oOr1\u0002zD\u0004w :\r\u0002zF\u0002kB\u0004w :\u0010\bw :zy7qN\u0002zH\u0005l\u0002nlt\u0006p#rVq7\u0004w :\u000b\u0005y\u001enZe\u0006o\u0013hP{/\u0005l\u0011oLr\u0004p1vK\u0004w :\u000f\u0003zA+\u0004q\u0015tJ\u0002i\u0000\u0005n3nM{\u0002zG\u0006p#oOr1\u0004w :\b\u0004~\u001biO\u0004w :\u0011\u0004w :\u0006\u0007i\u001eqQx4z\u0004w :\u0002\u0004\u007f\u0011jL\u0004w :\u0014\u0003zA*\u0004r\u001ftZ\u0003zA(\u0004n1vK\u0003y\u001e~\u0002zE\u0005r\u0005ws|\u0005p3nM{\u0004w :\u0007\u0004p\u0015|K\u0004l\u0017OO".length();
                var1_4 = 4;
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
                    var5[var3_1++] = lf21.modules.a.a(lf21.modules.a.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "x\u001fmQ\u0002zI";
                    var4_3 = "x\u001fmQ\u0002zI".length();
                    var1_4 = 4;
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
                    var5[var3_1++] = lf21.modules.a.a(lf21.modules.a.a(v1));
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
        lf21.modules.a.c = var5;
        lf21.modules.a.b = new HashMap<Integer, String>();
        var0_6 = lf21.modules.a.c;
        lf21.modules.a.b.put(-1, "None");
        lf21.modules.a.b.put(32, "Space");
        lf21.modules.a.b.put(39, "'");
        lf21.modules.a.b.put(44, ",");
        lf21.modules.a.b.put(45, "-");
        lf21.modules.a.b.put(46, ".");
        lf21.modules.a.b.put(47, "/");
        lf21.modules.a.b.put(48, "0");
        lf21.modules.a.b.put(49, "1");
        lf21.modules.a.b.put(50, "2");
        lf21.modules.a.b.put(51, "3");
        lf21.modules.a.b.put(52, "4");
        lf21.modules.a.b.put(53, "5");
        lf21.modules.a.b.put(54, "6");
        lf21.modules.a.b.put(55, "7");
        lf21.modules.a.b.put(56, "8");
        lf21.modules.a.b.put(57, "9");
        lf21.modules.a.b.put(59, ";");
        lf21.modules.a.b.put(61, "=");
        lf21.modules.a.b.put(65, "A");
        lf21.modules.a.b.put(66, "B");
        lf21.modules.a.b.put(67, "C");
        lf21.modules.a.b.put(68, "D");
        lf21.modules.a.b.put(69, "E");
        lf21.modules.a.b.put(70, "F");
        lf21.modules.a.b.put(71, "G");
        lf21.modules.a.b.put(72, "H");
        lf21.modules.a.b.put(73, "I");
        lf21.modules.a.b.put(74, "J");
        lf21.modules.a.b.put(75, "K");
        lf21.modules.a.b.put(76, "L");
        lf21.modules.a.b.put(77, "M");
        lf21.modules.a.b.put(78, "N");
        lf21.modules.a.b.put(79, "O");
        lf21.modules.a.b.put(80, "P");
        lf21.modules.a.b.put(81, "Q");
        lf21.modules.a.b.put(82, "R");
        lf21.modules.a.b.put(83, "S");
        lf21.modules.a.b.put(84, "T");
        lf21.modules.a.b.put(85, "U");
        lf21.modules.a.b.put(86, "V");
        lf21.modules.a.b.put(87, "W");
        lf21.modules.a.b.put(88, "X");
        lf21.modules.a.b.put(89, "Y");
        lf21.modules.a.b.put(90, "Z");
        lf21.modules.a.b.put(91, "[");
        lf21.modules.a.b.put(92, "\\");
        lf21.modules.a.b.put(93, "]");
        lf21.modules.a.b.put(96, "`");
        lf21.modules.a.b.put(161, "W1");
        lf21.modules.a.b.put(162, "W2");
        lf21.modules.a.b.put(256, "Esc");
        lf21.modules.a.b.put(257, "Enter");
        lf21.modules.a.b.put(258, "Tab");
        lf21.modules.a.b.put(259, "Bksp");
        lf21.modules.a.b.put(260, "Ins");
        lf21.modules.a.b.put(261, "Del");
        lf21.modules.a.b.put(262, "Right");
        lf21.modules.a.b.put(263, "Left");
        lf21.modules.a.b.put(264, var0_6[61]);
        lf21.modules.a.b.put(265, "Up");
        lf21.modules.a.b.put(266, "PgUp");
        lf21.modules.a.b.put(267, "PgDn");
        lf21.modules.a.b.put(268, "Home");
        lf21.modules.a.b.put(269, "End");
        lf21.modules.a.b.put(280, "Caps");
        lf21.modules.a.b.put(281, "Scroll");
        lf21.modules.a.b.put(282, "NumLk");
        lf21.modules.a.b.put(283, "PrtSc");
        lf21.modules.a.b.put(284, "Pause");
        lf21.modules.a.b.put(290, "F1");
        lf21.modules.a.b.put(291, "F2");
        lf21.modules.a.b.put(292, "F3");
        lf21.modules.a.b.put(293, "F4");
        lf21.modules.a.b.put(294, "F5");
        lf21.modules.a.b.put(295, "F6");
        lf21.modules.a.b.put(296, "F7");
        lf21.modules.a.b.put(297, "F8");
        lf21.modules.a.b.put(298, var0_6[62]);
        lf21.modules.a.b.put(299, "F10");
        lf21.modules.a.b.put(300, "F11");
        lf21.modules.a.b.put(301, "F12");
        lf21.modules.a.b.put(320, "KP 0");
        lf21.modules.a.b.put(321, "KP 1");
        lf21.modules.a.b.put(322, "KP 2");
        lf21.modules.a.b.put(323, "KP 3");
        lf21.modules.a.b.put(324, "KP 4");
        lf21.modules.a.b.put(325, "KP 5");
        lf21.modules.a.b.put(326, "KP 6");
        lf21.modules.a.b.put(327, "KP 7");
        lf21.modules.a.b.put(328, "KP 8");
        lf21.modules.a.b.put(329, "KP 9");
        lf21.modules.a.b.put(330, "KP .");
        lf21.modules.a.b.put(331, "KP /");
        lf21.modules.a.b.put(332, "KP *");
        lf21.modules.a.b.put(333, "KP -");
        lf21.modules.a.b.put(334, "KP +");
        lf21.modules.a.b.put(335, "KP Enter");
        lf21.modules.a.b.put(336, "KP =");
        lf21.modules.a.b.put(340, "LShift");
        lf21.modules.a.b.put(341, "LCtrl");
        lf21.modules.a.b.put(342, "LAlt");
        lf21.modules.a.b.put(343, "LSuper");
        lf21.modules.a.b.put(344, "RShift");
        lf21.modules.a.b.put(345, "RCtrl");
        lf21.modules.a.b.put(346, "RAlt");
        lf21.modules.a.b.put(347, "RSuper");
        lf21.modules.a.b.put(348, "Menu");
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x14);
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
                    case 0 -> 60;
                    case 1 -> 112;
                    case 2 -> 26;
                    case 3 -> 63;
                    case 4 -> 23;
                    case 5 -> 67;
                    default -> 20;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

