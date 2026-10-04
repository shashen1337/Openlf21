/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.KillAura;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Quaternionf;

public class OldHitting
extends Module {
    private final NumberSetting yOffset;
    private static final String[] p;
    private final NumberSetting zOffset;
    private final NumberSetting speed;
    private final ModeSetting animation;
    private final NumberSetting size;
    private final NumberSetting xOffset;
    public static OldHitting INSTANCE;

    public void applyHitAnimation(PoseStack poseStack, float f2, HumanoidArm humanoidArm, float f3) {
        String string;
        float f4 = Mth.clamp((float)(f2 * ((Number)this.speed.getValue()).floatValue()), (float)0.0f, (float)1.0f);
        float f5 = ((Number)this.size.getValue()).floatValue();
        int n2 = humanoidArm == HumanoidArm.RIGHT ? 1 : -1;
        float f6 = Mth.sin((float)(Mth.sqrt((float)f4) * (float)Math.PI));
        poseStack.translate(((Number)this.xOffset.getValue()).floatValue() * (float)n2, ((Number)this.yOffset.getValue()).floatValue(), ((Number)this.zOffset.getValue()).floatValue());
        String string2 = string = (String)this.animation.getValue();
        int n3 = -1;
        switch (string2.hashCode()) {
            case 1897755483: {
                String[] stringArray = p;
                if (!string2.equals("Vanilla")) break;
                n3 = 0;
                break;
            }
            case 79973777: {
                String[] stringArray = p;
                if (!string2.equals("Slide")) break;
                n3 = 1;
                break;
            }
            case 2433880: {
                String[] stringArray = p;
                if (!string2.equals("None")) break;
                n3 = 2;
                break;
            }
            case 48570: {
                String[] stringArray = p;
                if (!string2.equals("1.7")) break;
                n3 = 3;
                break;
            }
            case -1019669830: {
                String[] stringArray = p;
                if (!string2.equals("1.7 Accurate")) break;
                n3 = 4;
                break;
            }
            case 80247031: {
                String[] stringArray = p;
                if (!string2.equals("Sunny")) break;
                n3 = 5;
                break;
            }
            case 73771637: {
                String[] stringArray = p;
                if (!string2.equals("Lucid")) break;
                n3 = 6;
                break;
            }
            case 63569951: {
                String[] stringArray = p;
                if (!string2.equals("Astro")) break;
                n3 = 7;
                break;
            }
            case 83843: {
                String[] stringArray = p;
                if (!string2.equals("Tap")) break;
                n3 = 8;
                break;
            }
            case 86917: {
                String[] stringArray = p;
                if (!string2.equals("XIV")) break;
                n3 = 9;
                break;
            }
            case 1972874617: {
                String[] stringArray = p;
                if (!string2.equals("Avatar")) break;
                n3 = 10;
                break;
            }
            case -1814666802: {
                String[] stringArray = p;
                if (!string2.equals("Smooth")) break;
                n3 = 11;
                break;
            }
            case 2587234: {
                String[] stringArray = p;
                if (!string2.equals("Stab")) break;
                n3 = 12;
                break;
            }
            case 2583650: {
                String[] stringArray = p;
                if (!string2.equals("Spin")) break;
                n3 = 13;
                break;
            }
            case -2022880414: {
                String[] stringArray = p;
                if (!string2.equals("Leaked")) break;
                n3 = 14;
                break;
            }
            case 79367: {
                String[] stringArray = p;
                if (!string2.equals("Old")) break;
                n3 = 15;
                break;
            }
            case -352259601: {
                String[] stringArray = p;
                if (!string2.equals("Exhibition")) break;
                n3 = 16;
                break;
            }
            case -1135074218: {
                String[] stringArray = p;
                if (!string2.equals("Exhibition Old")) break;
                n3 = 17;
                break;
            }
            case -1135075377: {
                String[] stringArray = p;
                if (!string2.equals("Exhibition New")) break;
                n3 = 18;
                break;
            }
            case 80307556: {
                String[] stringArray = p;
                if (!string2.equals("Swong")) break;
                n3 = 19;
                break;
            }
            case -1808503203: {
                String[] stringArray = p;
                if (!string2.equals("Stella")) break;
                n3 = 20;
                break;
            }
            case 63: {
                if (!string2.equals("?")) break;
                n3 = 21;
                break;
            }
            case 2192897: {
                String[] stringArray = p;
                if (!string2.equals("Flup")) break;
                n3 = 22;
                break;
            }
            case 2433928: {
                String[] stringArray = p;
                if (!string2.equals("Noov")) break;
                n3 = 23;
                break;
            }
            case 522034400: {
                String[] stringArray = p;
                if (!string2.equals("Komorebi")) break;
                n3 = 24;
                break;
            }
            case 2546672: {
                String[] stringArray = p;
                if (!string2.equals("Rhys")) break;
                n3 = 25;
                break;
            }
            case 80301790: {
                String[] stringArray = p;
                if (!string2.equals("Swing")) break;
                n3 = 26;
                break;
            }
            case 2066960: {
                String[] stringArray = p;
                if (!string2.equals("Beta")) break;
                n3 = 27;
                break;
            }
            case 1355172906: {
                if (!string2.equals("Dortware")) break;
                n3 = 28;
            }
        }
        switch (n3) {
            case 0: {
                OldHitting.a(poseStack, n2, f3, f4);
                break;
            }
            case 1: {
                OldHitting.a(poseStack, (double)(0.648f * (float)n2), (double)-0.55f, (double)-0.72f);
                OldHitting.a(poseStack, (float)(77 * n2), 0.0f, 1.0f, 0.0f);
                OldHitting.a(poseStack, (float)(-10 * n2), 0.0f, 0.0f, 1.0f);
                OldHitting.a(poseStack, -80.0f, 1.0f, 0.0f, 0.0f);
                OldHitting.a(poseStack, -f6 * 20.0f, 1.0f, 0.0f, 0.0f);
                OldHitting.a(poseStack, 1.2f);
                break;
            }
            case 2: {
                OldHitting.a(poseStack, n2, f3, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 3: {
                OldHitting.a(poseStack, n2, f3, f4);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 4: {
                OldHitting.a(poseStack, n2, f3, f4);
                OldHitting.a(poseStack, n2);
                OldHitting.a(poseStack, (double)(-0.02f * (float)n2), (double)0.02f, 0.0);
                break;
            }
            case 5: {
                OldHitting.a(poseStack, (double)(0.05f * (float)n2), (double)-0.05f, (double)-0.12f);
                OldHitting.a(poseStack, n2, f3 + 0.15f, f4);
                OldHitting.a(poseStack, 0.99f);
                OldHitting.a(poseStack, n2);
                OldHitting.a(poseStack, (double)(-0.5f * (float)n2), (double)0.2f, 0.0);
                break;
            }
            case 6: {
                OldHitting.a(poseStack, n2, f3 - 0.1f, f4);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 7: {
                OldHitting.a(poseStack, 0.0, (double)0.03f, (double)-0.05f);
                OldHitting.a(poseStack, n2, f3 / 2.0f, f4);
                OldHitting.a(poseStack, f6 * 15.0f * (float)n2, -f6, 0.0f, 9.0f);
                OldHitting.a(poseStack, f6 * 40.0f, 1.0f, -f6 / 2.0f, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 8: {
                OldHitting.a(poseStack, 0.0, (double)0.3f, 0.0);
                float f7 = f4 * 0.8f - f4 * f4 * 0.8f;
                OldHitting.a(poseStack, (double)(0.56f * (float)n2), (double)-0.52f, (double)-0.72f);
                OldHitting.a(poseStack, (float)(45 * n2), 0.0f, 1.0f, 0.0f);
                OldHitting.a(poseStack, f7 * -90.0f * (float)n2, 0.0f, 1.0f, 0.0f);
                OldHitting.a(poseStack, 0.37f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 9: {
                OldHitting.a(poseStack, n2, f3, f4);
                OldHitting.a(poseStack, n2);
                float f8 = Mth.sin((float)(f4 * f4 * (float)Math.PI));
                OldHitting.a(poseStack, -f8 * 20.0f * (float)n2, 0.0f, 1.0f, 0.0f);
                OldHitting.a(poseStack, -f6 * 20.0f * (float)n2, 0.0f, 0.0f, 1.0f);
                OldHitting.a(poseStack, -f6 * 80.0f, 1.0f, 0.0f, 0.0f);
                break;
            }
            case 10: {
                OldHitting.a(poseStack, (double)(0.56f * (float)n2), (double)-0.52f, (double)-0.72f);
                OldHitting.a(poseStack, (float)(45 * n2), 0.0f, 1.0f, 0.0f);
                float f9 = Mth.sin((float)(f4 * f4 * (float)Math.PI));
                OldHitting.a(poseStack, f9 * -20.0f * (float)n2, 0.0f, 1.0f, 0.0f);
                OldHitting.a(poseStack, f6 * -20.0f * (float)n2, 0.0f, 0.0f, 1.0f);
                OldHitting.a(poseStack, f6 * -40.0f, 1.0f, 0.0f, 0.0f);
                OldHitting.a(poseStack, n2, -0.1f, -0.1f);
                OldHitting.a(poseStack, 0.4f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 11: {
                OldHitting.a(poseStack, n2, f3, 0.0f);
                float f10 = -f6 * 2.0f;
                OldHitting.a(poseStack, 0.0, (double)(f10 / 10.0f + 0.1f), 0.0);
                OldHitting.a(poseStack, f10 * 10.0f * (float)n2, 0.0f, 1.0f, 0.0f);
                OldHitting.a(poseStack, (float)(250 * n2), 0.2f, 1.0f, -0.6f);
                OldHitting.a(poseStack, -10.0f, 1.0f, 0.5f, 1.0f);
                OldHitting.a(poseStack, -f10 * 20.0f, 1.0f, 0.5f, 1.0f);
                break;
            }
            case 12: {
                OldHitting.a(poseStack, (double)(0.6f * (float)n2), (double)0.3f, (double)(-0.6f - f6 * 0.7f));
                OldHitting.a(poseStack, (float)(6090 * n2), 0.0f, 0.0f, 0.1f);
                OldHitting.a(poseStack, (float)(6085 * n2), 0.0f, 0.1f, 0.0f);
                OldHitting.a(poseStack, 6110.0f, 0.1f, 0.0f, 0.0f);
                OldHitting.a(poseStack, n2, 0.0f, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 13: {
                OldHitting.a(poseStack, n2, f3, 0.0f);
                OldHitting.a(poseStack, 0.0, (double)0.2f, -1.0);
                OldHitting.a(poseStack, (float)(-59 * n2), -1.0f, 0.0f, 3.0f);
                OldHitting.a(poseStack, (float)(-(System.currentTimeMillis() / 2L % 360L)), 1.0f, 0.0f, 0.0f);
                OldHitting.a(poseStack, (float)(60 * n2), 0.0f, 1.0f, 0.0f);
                break;
            }
            case 14: {
                OldHitting.a(poseStack, 0.0, (double)-0.03f, (double)-0.13f);
                OldHitting.a(poseStack, n2, f3 / 3.0f, 0.0f);
                OldHitting.a(poseStack, 0.0, (double)0.1f, 0.0);
                OldHitting.a(poseStack, n2);
                OldHitting.a(poseStack, f6 * 10.0f * (float)n2, 0.0f, 1.0f, 1.5f);
                OldHitting.a(poseStack, -f6 * 50.0f, 1.0f, 0.9f, 0.0f);
                break;
            }
            case 15: {
                OldHitting.a(poseStack, 0.0, (double)0.1f, 0.0);
                OldHitting.a(poseStack, n2, f3 / 2.0f - 0.2f, f4);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 16: {
                OldHitting.a(poseStack, 0.0, (double)-0.05f, 0.0);
                OldHitting.a(poseStack, n2, f3 / 2.0f, 0.0f);
                OldHitting.a(poseStack, 0.0, (double)0.3f, 0.0);
                OldHitting.a(poseStack, -f6 * 31.0f, 1.0f, 0.0f, 2.0f);
                OldHitting.a(poseStack, -f6 * 33.0f, 1.5f, f6 / 1.1f, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 17: {
                OldHitting.a(poseStack, (double)(-0.04f * (float)n2), (double)0.08f, 0.0);
                OldHitting.a(poseStack, n2, f3 / 2.5f, 0.0f);
                OldHitting.a(poseStack, -f6 * 20.0f, f6 / 2.0f, 1.0f, 4.0f);
                OldHitting.a(poseStack, -f6 * 30.0f, 1.0f, f6 / 3.0f, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 18: {
                OldHitting.a(poseStack, 0.0, (double)-0.04f, (double)-0.01f);
                OldHitting.a(poseStack, n2, f3 / 2.0f, 0.0f);
                OldHitting.a(poseStack, 0.0, (double)0.3f, 0.0);
                OldHitting.a(poseStack, -f6 * 30.0f, 1.0f, 0.0f, 2.0f);
                OldHitting.a(poseStack, -f6 * 44.0f, 1.5f, f6 / 1.2f, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 19: {
                OldHitting.a(poseStack, 0.0, (double)0.1f, (double)-0.05f);
                OldHitting.a(poseStack, n2, f3 / 2.0f, f4);
                OldHitting.a(poseStack, f6 * 30.0f, -f6, 0.0f, 9.0f);
                OldHitting.a(poseStack, f6 * 40.0f, 1.0f, -f6, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 20: 
            case 21: {
                OldHitting.a(poseStack, n2, f3, f4);
                OldHitting.a(poseStack, (double)(-0.5f * (float)n2), (double)0.4f, (double)-0.2f);
                OldHitting.a(poseStack, (float)(30 * n2), 0.0f, 1.0f, 0.0f);
                OldHitting.a(poseStack, -70.0f, 1.0f, 0.0f, 0.0f);
                OldHitting.a(poseStack, (float)(40 * n2), 0.0f, 1.0f, 0.0f);
                break;
            }
            case 22: {
                OldHitting.a(poseStack, 0.0, (double)0.1f, (double)-0.05f);
                OldHitting.a(poseStack, n2, f3, 0.0f);
                OldHitting.a(poseStack, n2);
                OldHitting.a(poseStack, (double)(-0.05f * (float)n2), (double)0.2f, 0.0);
                OldHitting.a(poseStack, -f6 * 35.0f, -8.0f, 0.0f, 9.0f);
                OldHitting.a(poseStack, -f6 * 70.0f, 1.0f, -0.4f, 0.0f);
                break;
            }
            case 23: {
                OldHitting.a(poseStack, n2, f3 / 1.5f, 0.0f);
                OldHitting.a(poseStack, n2);
                OldHitting.a(poseStack, (double)(-0.05f * (float)n2), (double)0.3f, (double)0.3f);
                OldHitting.a(poseStack, -f6 * 140.0f, 8.0f, 0.0f, 8.0f);
                OldHitting.a(poseStack, f6 * 180.0f, 8.0f, 0.0f, 8.0f);
                break;
            }
            case 24: {
                OldHitting.a(poseStack, n2, -0.25f, 1.0f + f6 / 10.0f);
                OldHitting.a(poseStack, -f6 * 25.0f, 1.0f, 0.0f, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 25: {
                OldHitting.a(poseStack, (double)(0.41f * (float)n2), -0.25, -0.5555557012557983);
                OldHitting.a(poseStack, (float)(35 * n2), 0.0f, 1.5f, 0.0f);
                OldHitting.a(poseStack, f6 * -12.0f * (float)n2, 0.0f, 0.0f, 1.0f);
                OldHitting.a(poseStack, f6 * -65.0f, 1.0f, 0.0f, 0.0f);
                OldHitting.a(poseStack, n2);
                break;
            }
            case 26: {
                OldHitting.a(poseStack, n2, f3, f4);
                OldHitting.a(poseStack, n2);
                OldHitting.a(poseStack, (double)(-0.3f * (float)n2), (double)-0.1f, 0.0);
                break;
            }
            case 27: {
                OldHitting.a(poseStack, 0.0, (double)0.3f, 0.0);
                float f11 = Mth.sin((float)(f4 * f4 * (float)Math.PI));
                OldHitting.a(poseStack, n2, f3 * 0.5f, 0.0f);
                OldHitting.a(poseStack, -f11 * 27.5f, -8.0f, 0.0f, 9.0f);
                OldHitting.a(poseStack, -f11 * 45.0f, 1.0f, f11 / 2.0f, 0.0f);
                OldHitting.a(poseStack, n2);
                OldHitting.a(poseStack, (double)(0.2f * (float)n2), (double)0.1f, (double)0.7f);
                break;
            }
            case 28: {
                float f12 = Mth.sin((float)(f4 * f4 * (float)Math.PI - 3.0f));
                OldHitting.a(poseStack, n2, f3, 1.0f);
                OldHitting.a(poseStack, -f6 * 10.0f, 0.0f, 15.0f, 200.0f);
                OldHitting.a(poseStack, -f6 * 10.0f, 300.0f, f6 / 2.0f, 1.0f);
                OldHitting.a(poseStack, n2);
                OldHitting.a(poseStack, (double)(0.3f * (float)n2), (double)0.1f, (double)0.6f);
                OldHitting.a(poseStack, f12 * 13.0f, -10.0f, -1.4f, -10.0f);
                break;
            }
            default: {
                OldHitting.a(poseStack, n2, f3, f4);
                OldHitting.a(poseStack, n2);
            }
        }
        OldHitting.a(poseStack, f5 / OldHitting.a(string));
    }

    public OldHitting() {
        String[] stringArray = p;
        super("OldHitting", Category.RENDER);
        this.animation = new ModeSetting("Animation", "Vanilla", "Slide", "None", "1.7", "1.7 Accurate", "Sunny", "Lucid", "Astro", "Smooth", "Spin", "Leaked", "Old", "Exhibition", "Exhibition Old", "Exhibition New", "Swong", "Stella", "Flup", "Noov", "Komorebi", "Rhys", "Swing", "?", "Stab", "Beta", "Dortware", "Avatar", "Tap", "XIV").withDefault("Leaked");
        this.size = new NumberSetting("Size", 1.0, 0.1, 3.0, 0.1);
        this.speed = new NumberSetting("Speed", 1.0, 0.1, 5.0, 0.1);
        this.xOffset = new NumberSetting("X-Offset", 0.0, -2.0, 2.0, 0.05);
        this.yOffset = new NumberSetting("Y-Offset", 0.0, -2.0, 2.0, 0.05);
        this.zOffset = new NumberSetting("Z-Offset", 0.0, -2.0, 2.0, 0.05);
        INSTANCE = this;
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
                    case 0 -> 79;
                    case 1 -> 57;
                    case 2 -> 48;
                    case 3 -> 7;
                    case 4 -> 53;
                    case 5 -> 63;
                    default -> 74;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static void a(PoseStack poseStack, int n2) {
        OldHitting.a(poseStack, (double)((float)n2 * -0.5f), (double)0.2f, 0.0);
        OldHitting.a(poseStack, (float)(n2 * 30), 0.0f, 1.0f, 0.0f);
        OldHitting.a(poseStack, -80.0f, 1.0f, 0.0f, 0.0f);
        OldHitting.a(poseStack, (float)(n2 * 60), 0.0f, 1.0f, 0.0f);
    }

    public boolean isKillAuraAttacking() {
        return KillAura.j != null && KillAura.j.isEnabled() && (Boolean)KillAura.j.fakeAutoBlock.getValue() != false && KillAura.l != null;
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x4A);
        }
        return cArray;
    }

    private static float a(String string) {
        String string2 = string;
        int n2 = -1;
        switch (string2.hashCode()) {
            case 79973777: {
                String[] stringArray = p;
                if (!string2.equals("Slide")) break;
                n2 = 0;
                break;
            }
            case 83843: {
                String[] stringArray = p;
                if (!string2.equals("Tap")) break;
                n2 = 1;
                break;
            }
            case 1972874617: {
                String[] stringArray = p;
                if (!string2.equals("Avatar")) break;
                n2 = 2;
                break;
            }
            case 2546672: {
                String[] stringArray = p;
                if (!string2.equals("Rhys")) break;
                n2 = 3;
                break;
            }
            case 80247031: {
                if (!string2.equals("Sunny")) break;
                n2 = 4;
            }
        }
        return switch (n2) {
            case 0 -> 1.2f;
            case 1 -> 0.37f;
            case 2 -> 0.16f;
            case 3 -> 1.0f;
            case 4 -> 0.396f;
            default -> 0.4f;
        };
    }

    private static void a(PoseStack poseStack, double d2, double d3, double d4) {
        poseStack.translate(d2, d3, d4);
    }

    public static void a(float f2, float f3, float f4, PoseStack poseStack) {
        poseStack.scale(f2, f3, f4);
    }

    private static void a(PoseStack poseStack, int n2, float f2, float f3) {
        OldHitting.a(poseStack, (double)((float)n2 * 0.56f), (double)(-0.52f + f2 * -0.6f), (double)-0.72f);
        OldHitting.a(poseStack, (float)(n2 * 45), 0.0f, 1.0f, 0.0f);
        float f4 = Mth.sin((float)(f3 * f3 * (float)Math.PI));
        float f5 = Mth.sin((float)(Mth.sqrt((float)Math.max(0.0f, f3)) * (float)Math.PI));
        OldHitting.a(poseStack, (float)n2 * f4 * -20.0f, 0.0f, 1.0f, 0.0f);
        OldHitting.a(poseStack, (float)n2 * f5 * -20.0f, 0.0f, 0.0f, 1.0f);
        OldHitting.a(poseStack, f5 * -80.0f, 1.0f, 0.0f, 0.0f);
        OldHitting.a(poseStack, 0.4f);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[63];
                var3_1 = 0;
                var2_2 = "\u0004V]hGZ(&\u0006\u001cT_hAW\u0004\u0001V^b\u0005\u001cUYcP\b\u0015\u0014\u007faSL/;\u0004\r\\Df\n\nAXnWV>&V^\u0006\u0003\\QlP[\u0003\u0017pf\u0006\u000eOQsTM\u0003~\u0017\u0007\u0004\u001cIYi\u0006\u001cMUkY^\u0004\u0001V^b\u0004\u001dQIt\u0003\u0000UT\u000e\nAXnWV>&V^'{Z=\u0004\u001cPJb\u0003\u001bX@\u000e\nAXnWV>&V^'zS.\u0007\u0019X^nYS+\f~\u0017\u0007't\\):KQsP\b\u000bVBsB^8*\u0005\u001cNYiR\u0003~\u0017\u0007\u0004\u001cIYi\u0004\r\\Df\n\nAXnWV>&V^\u0004\tUEw\t\u000eWYjTK# W\u0007\u0019X^nYS+\u0005\u001cN_iR\b\u0004V]hGZ(&\u0005\u001cNYiR\u0005\u000eJDuZ\u0005\u001cL^iL\u0005\u001cN_iR\n\u0000UTO\\K>&WW\u0004\u001cMQe\u000e\nAXnWV>&V^'{Z=\u0005\u001cUYcP\f~\u0017\u0007't\\):KQsP\u0005\u000eJDuZ\u0005\u0003LSnQ\u0006\u001cT_hAW\u000e\nAXnWV>&V^'zS.\u0004\u001dQIt\u0005\u001cIUbQ\b\u0016\u0014\u007faSL/;\u0004\tUEw\u0003\u0000UT\u0003\u0017pf\u0004\u0001V_q\b\u000bVBsB^8*\b\u0017\u0014\u007faSL/;\u0004\u001cMQe\u0005\u0003LSnQ\u0004\u0001V_q\u0003\u001bX@\u0006\u000eOQsTM\u0005\u001cL^iL";
                var4_3 = "\u0004V]hGZ(&\u0006\u001cT_hAW\u0004\u0001V^b\u0005\u001cUYcP\b\u0015\u0014\u007faSL/;\u0004\r\\Df\n\nAXnWV>&V^\u0006\u0003\\QlP[\u0003\u0017pf\u0006\u000eOQsTM\u0003~\u0017\u0007\u0004\u001cIYi\u0006\u001cMUkY^\u0004\u0001V^b\u0004\u001dQIt\u0003\u0000UT\u000e\nAXnWV>&V^'{Z=\u0004\u001cPJb\u0003\u001bX@\u000e\nAXnWV>&V^'zS.\u0007\u0019X^nYS+\f~\u0017\u0007't\\):KQsP\b\u000bVBsB^8*\u0005\u001cNYiR\u0003~\u0017\u0007\u0004\u001cIYi\u0004\r\\Df\n\nAXnWV>&V^\u0004\tUEw\t\u000eWYjTK# W\u0007\u0019X^nYS+\u0005\u001cN_iR\b\u0004V]hGZ(&\u0005\u001cNYiR\u0005\u000eJDuZ\u0005\u001cL^iL\u0005\u001cN_iR\n\u0000UTO\\K>&WW\u0004\u001cMQe\u000e\nAXnWV>&V^'{Z=\u0005\u001cUYcP\f~\u0017\u0007't\\):KQsP\u0005\u000eJDuZ\u0005\u0003LSnQ\u0006\u001cT_hAW\u000e\nAXnWV>&V^'zS.\u0004\u001dQIt\u0005\u001cIUbQ\b\u0016\u0014\u007faSL/;\u0004\tUEw\u0003\u0000UT\u0003\u0017pf\u0004\u0001V_q\b\u000bVBsB^8*\b\u0017\u0014\u007faSL/;\u0004\u001cMQe\u0005\u0003LSnQ\u0004\u0001V_q\u0003\u001bX@\u0006\u000eOQsTM\u0005\u001cL^iL".length();
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
                    var5[var3_1++] = OldHitting.c(OldHitting.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u001cMUkY^\u0006\u0003\\QlP[";
                    var4_3 = "\u001cMUkY^\u0006\u0003\\QlP[".length();
                    var1_4 = 6;
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
                    var5[var3_1++] = OldHitting.c(OldHitting.b(v1));
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
        OldHitting.p = var5;
    }

    private static void a(PoseStack poseStack, float f2) {
        poseStack.scale(f2, f2, f2);
    }

    public static void a(double d2, double d3, double d4, PoseStack poseStack) {
        poseStack.translate(d2, d3, d4);
    }

    public static void a(float f2, float f3, float f4, float f5, PoseStack poseStack) {
        float f6 = Mth.sqrt((float)(f3 * f3 + f4 * f4 + f5 * f5));
        if (f6 < 1.0E-5f || Math.abs(f2) < 1.0E-5f) {
            return;
        }
        poseStack.mulPose(new Quaternionf().rotationAxis(f2 * ((float)Math.PI / 180), f3 / f6, f4 / f6, f5 / f6));
    }

    private static void a(PoseStack poseStack, float f2, float f3, float f4, float f5) {
        OldHitting.a(f2, f3, f4, f5, poseStack);
    }
}

