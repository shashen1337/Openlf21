/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.gui.k;
import lf21.hud.HudElement;
import lf21.settings.impl.BooleanSetting;
import lf21.utils.render.RenderUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Team;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;

public final class ScoreBoard
extends HudElement {
    public static ScoreBoard I;
    public final BooleanSetting M;
    public final BooleanSetting K;
    private static final int J;
    private static final String[] O;
    public final BooleanSetting N;
    public final BooleanSetting L;

    private Component a(Component component) {
        if (!((Boolean)this.M.getValue()).booleanValue()) {
            return component;
        }
        String string = component.getString().trim().toLowerCase(Locale.ROOT);
        String[] stringArray = O;
        boolean bl = string.contains("hypixel") || string.contains("heypixel") || string.contains("bjd") || string.startsWith("www.") || string.matches(".*[a-z0-9-]+\\.(net|com|cn|org|gg|io).*") || string.matches(".*\\b(?:\\d{1,3}\\.){3}\\d{1,3}.*");
        return bl ? Component.literal((String)"lf21").withStyle(component.getStyle()) : component;
    }

    public ScoreBoard() {
        String[] stringArray = O;
        super("ScoreBoard");
        this.K = new BooleanSetting("Outline", false);
        this.L = new BooleanSetting("Blur color", false);
        this.M = new BooleanSetting("Replace IP with LF21", true);
        this.N = new BooleanSetting("Hide Original", true);
        I = this;
        this.setX(8.0f);
        this.setY(80.0f);
        super.setScale(1.0f);
        this.e(128.0f);
        this.f(53.0f);
        MinecraftForge.EVENT_BUS.addListener(this::a);
    }

    @Override
    public boolean h() {
        return true;
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        Component component;
        Objective objective = this.y();
        ArrayList<Object> arrayList = new ArrayList<Object>();
        if (objective != null) {
            component = this.a(objective.getDisplayName());
            Collection collection = objective.getScoreboard().getPlayerScores(objective);
            List<Score> list = collection.stream().filter(score -> score.getOwner() != null && !score.getOwner().startsWith("#")).sorted(Comparator.comparingInt(Score::getScore).reversed()).limit(15L).toList();
            Iterator<Score> component2 = list.iterator();
            while (component2.hasNext()) {
                Score score2 = component2.next();
                PlayerTeam playerTeam = objective.getScoreboard().getPlayersTeam(score2.getOwner());
                arrayList.add(this.a((Component)PlayerTeam.formatNameForTeam((Team)playerTeam, (Component)Component.literal((String)score2.getOwner()))));
            }
        } else if (ScoreBoard.mc.screen instanceof k) {
            String[] stringArray = O;
            component = Component.literal((String)"LF21 Scoreboard");
            arrayList.add(Component.literal((String)"Time  12:48").withStyle(ChatFormatting.AQUA));
            arrayList.add(Component.literal((String)"Coins  1,280").withStyle(ChatFormatting.GOLD));
            arrayList.add(Component.literal((String)"lf21").withStyle(ChatFormatting.YELLOW));
        } else {
            return;
        }
        int n2 = ScoreBoard.mc.font.width((FormattedText)component);
        for (Component component2 : arrayList) {
            n2 = Math.max(n2, ScoreBoard.mc.font.width((FormattedText)component2));
        }
        float f4 = (float)(n2 += 2) + 12.0f;
        float f5 = (float)(9 * arrayList.size() + 9) + 6.0f;
        this.e(f4);
        this.f(f5);
        int n3 = (Boolean)this.L.getValue() != false ? new Color(25, 95, 105, 60).getRGB() : new Color(0, 0, 0, 100).getRGB();
        RenderUtil.a(render2DEvent.a(), f2, f3, f4, f5, 5.0f, 12.0f, 0.9f, 0);
        RenderUtil.b(render2DEvent.a(), f2, f3, f4, f5, 5.0f, n3);
        if (((Boolean)this.K.getValue()).booleanValue()) {
            RenderUtil.b(render2DEvent.a(), f2 - 1.0f, f3 - 1.0f, f4 + 2.0f, 1.0f, 0.5f, -1721766913);
        }
        int n4 = Math.round(f2 + 6.0f);
        int n5 = Math.round(f3 + 4.0f);
        int n6 = Math.round((float)n4 + (float)n2 * 0.5f - (float)ScoreBoard.mc.font.width((FormattedText)component) * 0.5f);
        render2DEvent.b().drawString(ScoreBoard.mc.font, component, n6, n5, -1, false);
        int n7 = n5 + 9;
        for (Component component3 : arrayList) {
            render2DEvent.b().drawString(ScoreBoard.mc.font, component3, n4, n7, -1, false);
            n7 += 9;
        }
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6E);
        }
        return cArray;
    }

    @Override
    public void a() {
    }

    private void a(RenderGuiOverlayEvent.Pre pre) {
        if (this.isEnabled() && ((Boolean)this.N.getValue()).booleanValue() && pre.getOverlay() != null && VanillaGuiOverlay.SCOREBOARD.id().equals((Object)pre.getOverlay().id())) {
            pre.setCanceled(true);
        }
    }

    private Objective y() {
        Objective objective;
        if (ScoreBoard.mc.level == null) {
            return null;
        }
        Scoreboard scoreboard = ScoreBoard.mc.level.getScoreboard();
        Objective objective2 = scoreboard.getDisplayObjective(1);
        if (ScoreBoard.mc.player == null) {
            return objective2;
        }
        PlayerTeam playerTeam = scoreboard.getPlayersTeam(ScoreBoard.mc.player.getScoreboardName());
        if (playerTeam != null && playerTeam.getColor() != null && playerTeam.getColor().getId() >= 0 && (objective = scoreboard.getDisplayObjective(3 + playerTeam.getColor().getId())) != null) {
            return objective;
        }
        return objective2;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String d(char[] cArray) {
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
                    case 0 -> 61;
                    case 1 -> 14;
                    case 2 -> 113;
                    case 3 -> 62;
                    case 4 -> 120;
                    case 5 -> 38;
                    default -> 110;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    public static boolean shouldHideVanilla() {
        return I != null && I.isEnabled() && (Boolean)ScoreBoard.I.N.getValue() != false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                ScoreBoard.J = 9;
                var5 = new String[16];
                var3_1 = 0;
                var2_2 = "Jy\u0006\u0010\u001d\u0013$-\\P\u0019Taj\n\u000fT\u0015\u0013a XEK[2Yu@\u0012K[@\u0017\u0004QhC\u000f\f~a\u0018P\u000b\u0006N\f\"C\u0006H\u0003_d\u0015\rug\u0015[Xi\u001cTi\u0018P\u0019J\u0007r{\u0005R\u0011H\u000b\u0004QhC\u000f\u000big\u001c[X\u0006_\u000f4E\u0006\u0007Uw\u0001W\u0000C\u0002\u0014ok\u0001R\u0019E\u000b\u001dG!\u001e\u000fO\u001aU.=xJ\u0017\bUk\bN\u0011^\u000bQ\nnm\u001eL\u001dd\u0001\\|\u0015\n\u007fb\u0004LXE\u0001Qa\u0003";
                var4_3 = "Jy\u0006\u0010\u001d\u0013$-\\P\u0019Taj\n\u000fT\u0015\u0013a XEK[2Yu@\u0012K[@\u0017\u0004QhC\u000f\f~a\u0018P\u000b\u0006N\f\"C\u0006H\u0003_d\u0015\rug\u0015[Xi\u001cTi\u0018P\u0019J\u0007r{\u0005R\u0011H\u000b\u0004QhC\u000f\u000big\u001c[X\u0006_\u000f4E\u0006\u0007Uw\u0001W\u0000C\u0002\u0014ok\u0001R\u0019E\u000b\u001dG!\u001e\u000fO\u001aU.=xJ\u0017\bUk\bN\u0011^\u000bQ\nnm\u001eL\u001dd\u0001\\|\u0015\n\u007fb\u0004LXE\u0001Qa\u0003".length();
                var1_4 = 4;
                var0_5 = -1;
lbl8:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl13:
                // 1 sources

                while (true) {
                    var5[var3_1++] = ScoreBoard.d(ScoreBoard.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "qHC\u000fXu\rR|\u0014\\\u0017G\u001cY&\u0013$*_U\\^\u00107\\cSz@\u0015`\u0014J\u0004E\u0001Pr\u0012P\u0004I\u001cZr\u0016Y\u0004O\u0001\u0014 [";
                    var4_3 = "qHC\u000fXu\rR|\u0014\\\u0017G\u001cY&\u0013$*_U\\^\u00107\\cSz@\u0015`\u0014J\u0004E\u0001Pr\u0012P\u0004I\u001cZr\u0016Y\u0004O\u0001\u0014 [".length();
                    var1_4 = 15;
                    var0_5 = -1;
lbl22:
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
lbl27:
                // 1 sources

                while (true) {
                    var5[var3_1++] = ScoreBoard.d(ScoreBoard.c(v1));
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
lbl38:
                // 1 sources

                ** continue;
            }
        }
        ScoreBoard.O = var5;
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
    }

    @Override
    public void setScale(float f2) {
        super.setScale(1.0f);
    }
}

