/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.gui.k;
import lf21.hud.E;
import lf21.hud.HudElement;
import lf21.modules.Category;
import lf21.modules.impl.world.a;
import lf21.modules.impl.world.h;
import lf21.modules.impl.world.i;
import lf21.modules.impl.world.o;
import lf21.modules.impl.world.p;
import lf21.modules.impl.world.q;
import lf21.modules.impl.world.r;
import lf21.modules.impl.world.s;
import lf21.modules.impl.world.t;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.g;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.RotationHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class MusicBot
extends HudElement {
    public static MusicBot I;
    private static final u_0 K;
    private final List<BlockPos> ad;
    private int au;
    public static final File SONG_DIR;
    private int ax;
    private static final String[] db;
    private boolean al;
    private final List<BlockPos> ae;
    private static final u_0 J;
    private boolean ak;
    private long am;
    public lf21.utils.rotation.a ag;
    public final BooleanSetting M;
    private boolean an;
    private final Map<q, BlockPos> Y;
    private boolean aj;
    private final Set<BlockPos> ab;
    public final BooleanSetting W;
    public final BooleanSetting R;
    private int ay;
    private int as;
    public final BooleanSetting X;
    public final NumberSetting P;
    private int at;
    private int aw;
    public final ModeSetting L;
    private final List<BlockPos> af;
    private final Set<q> Z;
    public final BooleanSetting U;
    private float ar;
    private long ao;
    public final NumberSetting O;
    public final BooleanSetting V;
    private String aq;
    private int av;
    private i ah;
    private final Map<BlockPos, Integer> aa;
    private String ap;
    public final BooleanSetting T;
    private final Set<BlockPos> ac;
    public final BooleanSetting S;
    public final NumberSetting Q;
    private a ai;
    private int az;
    public final NumberSetting N;

    private o a(i i2, Set<q> set) {
        HashSet<q> hashSet;
        HashSet<q> hashSet2;
        int n2;
        int n3;
        block11: {
            n3 = 0;
            n2 = 0;
            hashSet2 = new HashSet<q>();
            hashSet = new HashSet<q>();
            for (List<q> list : i2.a.values()) {
                for (q q2 : list) {
                    q q3 = this.a(q2);
                    if (q3 == null) {
                        continue;
                    }
                    ++n3;
                    hashSet2.add(q3);
                    if (!set.contains(q3)) continue;
                    ++n2;
                    hashSet.add(q3);
                }
            }
            if (n3 != 0 && !hashSet2.isEmpty()) break block11;
            return new o(0.0, 0, n3, 0, hashSet2.size());
        }
        double d2 = (double)n2 / (double)n3;
        double d3 = (double)hashSet.size() / (double)hashSet2.size();
        return new o(d2 * 0.8 + d3 * 0.2, n2, n3, hashSet.size(), hashSet2.size());
    }

    public void a(String string, boolean bl) {
        this.loadSong(new File(SONG_DIR, string), bl);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        float f4;
        boolean bl;
        boolean bl2 = MusicBot.mc.screen instanceof k;
        boolean bl3 = bl = bl2 || (Boolean)this.X.getValue() != false && this.ah != null && this.ai == a.PLAYING;
        if (!bl) {
            if (!this.al) {
                return;
            }
            if (!this.an) {
                this.an = true;
                this.ao = System.nanoTime();
            }
        } else {
            this.an = false;
        }
        if (!this.al) {
            this.al = true;
            this.am = System.nanoTime();
        }
        if (bl) {
            Object object;
            String string;
            int n2 = bl2 && this.ah == null ? 840 : Math.max(0, Math.min(this.az, this.ah.d));
            int n3 = bl2 && this.ah == null ? 4000 : Math.max(1, this.ah.d);
            int n4 = bl2 && this.ah == null ? 21 : this.ax;
            int n5 = bl2 && this.ah == null ? 100 : Math.max(1, this.ay);
            this.ar = Math.min(1.0f, (float)n4 / (float)n5);
            this.aq = MusicBot.b(n2) + " / " + MusicBot.b(n3);
            String string2 = string = this.ah == null ? "MusicBot" : this.ah.b;
            if (this.aj || bl2) {
                object = string;
            } else {
                String[] stringArray = db;
                object = string + "  Paused";
            }
            this.ap = object;
        }
        float f5 = this.ar;
        String string = this.aq;
        String string3 = this.ap;
        float f6 = y_0.a(string, J);
        float f7 = 214.0f;
        float f8 = 20.0f + y_0.a(string3, K) + 16.0f + y_0.a(string, K);
        float f9 = Math.min(f7, Math.max(168.0f, f8));
        float f10 = f9 - f6 - 34.0f;
        String string4 = this.a(string3, f10);
        float f11 = this.d(f9);
        float f12 = 38.0f;
        this.e(f11);
        this.f(f12);
        long l2 = System.nanoTime();
        if (bl2) {
            f4 = 1.0f;
        } else if (this.an) {
            var20_25 = Math.min(1.0f, (float)(l2 - this.ao) / 2.8E8f);
            f4 = Math.max(0.001f, 1.0f - var20_25 * var20_25 * var20_25);
            if (var20_25 >= 1.0f) {
                this.al = false;
                this.an = false;
                return;
            }
        } else {
            var20_25 = Math.min(1.0f, (float)(l2 - this.am) / 2.8E8f);
            float f13 = var20_25 - 1.0f;
            f4 = 1.0f + 2.70158f * f13 * f13 * f13 + 1.70158f * f13 * f13;
        }
        render2DEvent.a().pushPose();
        render2DEvent.a().translate(f2 + f11 * 0.5f, f3 + f12 * 0.5f, 0.0f);
        render2DEvent.a().scale(f4, f4, 1.0f);
        render2DEvent.a().translate(-(f2 + f11 * 0.5f), -(f3 + f12 * 0.5f), 0.0f);
        RenderUtil.a(render2DEvent.a(), f2, f3, f11, f12, 9.0f, 24.0f, 1.0f, 0);
        RenderUtil.b(render2DEvent.a(), f2, f3, f11, f12, 9.0f, new Color(0, 0, 0, 179).getRGB());
        Renderer.render(render2DEvent.b(), drawContext -> {
            String[] stringArray = db;
            this.a("Title", () -> y_0.a(string4, f2 + 9.0f, f3 + 8.0f, J, -789001));
            this.a("Time", () -> y_0.a(string, f2 + f11 - 9.0f - f6, f3 + 8.0f, J, -5393478));
        });
        E e2 = HudElement.a("Progress", f2 + 9.0f, f3 + 27.0f, f11 - 18.0f, 4.0f);
        RenderUtil.b(render2DEvent.a(), e2.a(), e2.b(), e2.c(), e2.d(), 2.0f, new Color(245, 246, 247, 220).getRGB());
        if (f5 > 0.0f) {
            RenderUtil.b(render2DEvent.a(), e2.a(), e2.b(), e2.c() * f5, e2.d(), 2.0f, new Color(61, 199, 232, 245).getRGB());
        }
        render2DEvent.a().popPose();
    }

    private void M() {
        this.ad.clear();
        this.ad.addAll(this.B());
    }

    private void a(BlockPos blockPos) {
        block4: {
            this.av = 0;
            if (((Boolean)this.S.getValue()).booleanValue() && MusicBot.mc.player != null) break block4;
            this.ag = null;
            return;
        }
        this.ag = g.a(MusicBot.mc.player.getEyePosition(), Vec3.atCenterOf((Vec3i)blockPos));
        RotationHandler.a(this.ag);
    }

    private static double a(String string, String string2) {
        block25: {
            if (!string.isEmpty() && !string2.isEmpty()) break block25;
            return 0.0;
        }
        if (string2.equals(string)) {
            return 1.0;
        }
        if (string2.startsWith(string)) {
            return 0.94 + 0.06 * (double)string.length() / (double)string2.length();
        }
        if (string2.contains(string)) {
            return 0.88 + 0.08 * (double)string.length() / (double)string2.length();
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        while (true) {
            block27: {
                block26: {
                    if (n4 >= string2.length() || n2 >= string.length()) break;
                    if (string2.charAt(n4) != string.charAt(n2)) break block26;
                    ++n2;
                    break block27;
                }
                if (n2 > 0) {
                    ++n3;
                }
            }
            ++n4;
        }
        double d2 = n2 == string.length() ? 0.72 + 0.22 * (double)string.length() / (double)string2.length() - Math.min(0.12, (double)n3 * 0.015) : 0.0;
        double d3 = d2;
        int n5 = MusicBot.b(string, string2);
        double d4 = 1.0 - (double)n5 / (double)Math.max(string.length(), string2.length());
        return Math.max(d3, d4 * 0.88);
    }

    private boolean I() {
        block12: {
            block11: {
                if (((Boolean)this.S.getValue()).booleanValue() && this.ag != null) break block11;
                return true;
            }
            RotationHandler.a(this.ag);
            ++this.av;
            lf21.utils.rotation.a a2 = RotationHandler.c;
            if (a2 != null) {
                float f2 = Math.abs(Mth.wrapDegrees((float)(a2.e() - this.ag.e())));
                float f3 = Math.abs(a2.f() - this.ag.f());
                if (!(f2 <= 3.0f) || !(f3 <= 3.0f)) break block12;
                return true;
            }
        }
        boolean bl = this.av >= 3;
        return bl;
    }

    private void D() {
        block30: {
            block31: {
                if (this.ae.isEmpty()) break block30;
                if (this.I()) break block31;
                return;
            }
            for (BlockPos blockPos : new ArrayList<BlockPos>(this.ae)) {
                Integer n2;
                block32: {
                    if (!MusicBot.mc.level.getBlockState(blockPos).is(Blocks.NOTE_BLOCK)) {
                        continue;
                    }
                    this.b(blockPos);
                    if (((Boolean)this.V.getValue()).booleanValue()) {
                        MusicBot.mc.player.swing(InteractionHand.MAIN_HAND);
                    }
                    this.ab.add(blockPos);
                    n2 = this.aa.get(blockPos);
                    if (n2 != null && n2 > 1) break block32;
                    this.aa.remove(blockPos);
                    continue;
                }
                this.aa.put(blockPos, n2 - 1);
            }
            this.J();
            if (this.aa.isEmpty()) {
                this.au = ((Number)this.Q.getValue()).intValue();
                this.ai = a.VERIFY;
                ChatUtil.print("MusicBot: checking tuned note blocks");
            }
            return;
        }
        if (++this.at < ((Number)this.O.getValue()).intValue()) {
            return;
        }
        this.at = 0;
        int n3 = ((Number)this.P.getValue()).intValue();
        Iterator<Map.Entry<BlockPos, Integer>> iterator = this.aa.entrySet().iterator();
        while (true) {
            if (!iterator.hasNext() || this.ae.size() >= n3) break;
            Map.Entry<BlockPos, Integer> entry = iterator.next();
            BlockPos blockPos = entry.getKey();
            if (!MusicBot.mc.level.getBlockState(blockPos).is(Blocks.NOTE_BLOCK)) {
                iterator.remove();
                continue;
            }
            this.ae.add(blockPos.immutable());
        }
        if (!this.ae.isEmpty()) {
            this.a(this.ae.get(0));
        }
    }

    private Set<q> b(List<BlockPos> list) {
        HashSet<q> hashSet = new HashSet<q>();
        if (MusicBot.mc.level == null) {
            return hashSet;
        }
        for (BlockPos blockPos : list) {
            BlockState blockState = MusicBot.mc.level.getBlockState(blockPos);
            if (!blockState.is(Blocks.NOTE_BLOCK)) {
                continue;
            }
            q q2 = this.a(new q((NoteBlockInstrument)blockState.getValue((Property)NoteBlock.INSTRUMENT), (Integer)blockState.getValue((Property)NoteBlock.NOTE)));
            if (q2 == null) continue;
            hashSet.add(q2);
        }
        return hashSet;
    }

    private void C() {
        this.aa.clear();
        for (Map.Entry<q, BlockPos> entry : this.Y.entrySet()) {
            if (this.Z.contains(entry.getKey())) {
                continue;
            }
            BlockState blockState = MusicBot.mc.level.getBlockState(entry.getValue());
            if (!blockState.is(Blocks.NOTE_BLOCK)) {
                continue;
            }
            int n2 = (Integer)blockState.getValue((Property)NoteBlock.NOTE);
            int n3 = entry.getKey().b;
            int n4 = Math.floorMod(n3 - n2, 25);
            if (n4 == 0) continue;
            this.aa.put(entry.getValue(), n4);
        }
    }

    private int d(List<BlockPos> list) {
        int n2;
        block8: {
            n2 = 0;
            for (BlockPos blockPos : list) {
                if (!MusicBot.mc.level.getBlockState(blockPos).is(Blocks.NOTE_BLOCK)) {
                    continue;
                }
                this.a(ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK, blockPos);
                this.ac.add(blockPos);
                this.ab.add(blockPos);
                ++n2;
            }
            if (n2 <= 0 || !((Boolean)this.V.getValue()).booleanValue()) break block8;
            MusicBot.mc.player.swing(InteractionHand.MAIN_HAND);
        }
        return n2;
    }

    private void b(BlockPos blockPos) {
        BlockHitResult blockHitResult = new BlockHitResult(Vec3.atCenterOf((Vec3i)blockPos).add(0.0, 0.5, 0.0), Direction.UP, blockPos, false);
        PacketUtil.b(n2 -> new ServerboundUseItemOnPacket(InteractionHand.MAIN_HAND, blockHitResult, n2));
    }

    private static void a(File file, i i2, double d2) {
        String string = file.getName();
        String string2 = MusicBot.d(string);
        String[] stringArray = db;
        String string3 = ".music load " + string;
        String string4 = ".music preview " + string;
        MutableComponent mutableComponent = Component.literal((String)String.format(Locale.ROOT, "%.1f%% - ", d2 * 100.0)).withStyle(ChatFormatting.AQUA).append((Component)Component.literal((String)string2).withStyle(style -> style.withColor(ChatFormatting.WHITE).withUnderlined(Boolean.valueOf(true)).withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, string)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Component.literal((String)("Copy " + string)))))).append((Component)Component.literal((String)(" - " + MusicBot.b(i2.d) + " --- ")).withStyle(ChatFormatting.GRAY)).append((Component)Component.literal((String)"load").withStyle(style -> style.withColor(ChatFormatting.GREEN).withUnderlined(Boolean.valueOf(true)).withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, string3)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Component.literal((String)("Run " + string3)))))).append((Component)Component.literal((String)" - ").withStyle(ChatFormatting.DARK_GRAY)).append((Component)Component.literal((String)"preview").withStyle(style -> style.withColor(ChatFormatting.YELLOW).withUnderlined(Boolean.valueOf(true)).withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, string4)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Component.literal((String)("Run " + string4))))));
        ChatUtil.addMessage((Component)mutableComponent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventTarget
    public void a(TickEvent tickEvent) {
        if (MusicBot.mc.player == null) return;
        if (MusicBot.mc.level == null) return;
        if (MusicBot.mc.gameMode == null) {
            return;
        }
        this.L();
        this.ab.clear();
        if (MusicBot.mc.player.tickCount % 8 == 0 || this.ad.isEmpty()) {
            this.M();
        }
        if (this.ah == null) {
            return;
        }
        if (((Boolean)this.M.getValue()).booleanValue() && (this.ai == a.TUNING || this.ai == a.VERIFY)) {
            this.aa.clear();
            this.J();
            this.H();
            return;
        }
        switch (h.a[this.ai.ordinal()]) {
            case 1: {
                this.A();
                return;
            }
            case 2: {
                this.D();
                return;
            }
            case 3: {
                this.E();
                return;
            }
            case 4: {
                this.F();
                return;
            }
        }
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        block27: {
            block26: {
                if (!((Boolean)this.W.getValue()).booleanValue() || this.ad.isEmpty()) break block26;
                if (MusicBot.mc.level == null) break block26;
                if (MusicBot.mc.gameRenderer != null) break block27;
            }
            return;
        }
        PoseStack poseStack = renderEvent.a();
        Vec3 vec3 = MusicBot.mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-vec3.x, -vec3.y, -vec3.z);
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        for (BlockPos blockPos : this.ad) {
            Object object;
            block35: {
                int n2;
                Integer n3;
                block34: {
                    block31: {
                        block33: {
                            block32: {
                                boolean bl;
                                block30: {
                                    boolean bl2;
                                    block29: {
                                        block28: {
                                            BlockState blockState = MusicBot.mc.level.getBlockState(blockPos);
                                            if (!blockState.is(Blocks.NOTE_BLOCK)) {
                                                continue;
                                            }
                                            n3 = this.c(blockPos);
                                            n2 = (Integer)blockState.getValue((Property)NoteBlock.NOTE);
                                            if (n3 == null || n2 != n3) break block28;
                                            bl2 = true;
                                            break block29;
                                        }
                                        bl2 = false;
                                    }
                                    bl = bl2;
                                    if (!this.ab.contains(blockPos)) break block30;
                                    RenderSystem.setShaderColor((float)1.0f, (float)0.65f, (float)0.1f, (float)0.95f);
                                    break block31;
                                }
                                if (!bl) break block32;
                                RenderSystem.setShaderColor((float)0.1f, (float)1.0f, (float)0.35f, (float)0.85f);
                                break block31;
                            }
                            if (n3 == null) break block33;
                            RenderSystem.setShaderColor((float)1.0f, (float)0.15f, (float)0.15f, (float)0.85f);
                            break block31;
                        }
                        RenderSystem.setShaderColor((float)0.2f, (float)0.82f, (float)1.0f, (float)0.8f);
                    }
                    RenderUtil.b(new AABB(blockPos).inflate(0.002), poseStack);
                    if (n3 != null && n3 != n2) break block34;
                    object = Integer.toString(n2);
                    break block35;
                }
                object = n2 + " -> " + n3;
            }
            String string = object;
            poseStack.pushPose();
            poseStack.translate((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.18, (double)blockPos.getZ() + 0.5);
            poseStack.mulPose(MusicBot.mc.gameRenderer.getMainCamera().rotation());
            poseStack.scale(-0.022f, -0.022f, 0.022f);
            float f2 = MusicBot.mc.font.width(string);
            MusicBot.mc.font.drawInBatch(string, -f2 / 2.0f, -4.0f, -1, false, poseStack.last().pose(), (MultiBufferSource)mc.renderBuffers().bufferSource(), Font.DisplayMode.NORMAL, 0x66000000, 0xF000F0);
            poseStack.popPose();
        }
        mc.renderBuffers().bufferSource().endBatch();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        poseStack.popPose();
    }

    private static String d(String string) {
        int n2 = string.lastIndexOf(46);
        String string2 = n2 <= 0 ? string : string.substring(0, n2);
        return string2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[96];
                var3_1 = 0;
                var2_2 = "3$CG%P?R]\u001fg\u0019ip\u0001Q\u0012Q\u0013oj@\u0014\u000fJ\u0018zjOU\u0011@Vi%\u0001G\u0019D\u0004~\"%=$N@\u0019\u0005\u0014q%B_TV_=8D^\u0019F\u0002x.\u0001V\u0005\u0005\u0002u/\u0001G\u0019W\u0000x8\u0005I#UX\u00196P?R]\u001fg\u0019ip\u0001Z\u0013\u0005\u0004x+B\\\u001dG\u001axjO[\b@V\u007f&NW\u0017VVj#U\\\\D\u001foj@V\u0013S\u0013=>IQ\u0011\u001fP?R]\u001fg\u0019ip\u0001Z\u0013\u0005\u0005t'HX\u001dWVn%OS\u000f\u0005\u0010r8\u0001\u0004=g\u001f\u0014\u0005O+OS\u0019\u0007H$JZ\u0013R\u0018\u0005^%QM\\\t&jLU\fU\u0013yj\u0004O?O\u0014\u0007m8DB\u0015@\u0001\u000e=$N@\u0019\u0005\u0014q%B_TV_\u0011P?R]\u001fg\u0019ip\u0001D\u0010D\u000ft$F\bM8NS\u000e@\u0005n\u0013\u007f+E\u0014\b@\u000eijR[\u0012BVq#OQ\\\u0004I#LQ\u0004O?O\u0014\u001dn?QD\u0013W\u0002x.\u0001R\u0013W\u001b|>R\u000e\\\u000b\u0018\u007f9\r\u0014RQ\u000ei\u001eP?R]\u001fg\u0019ip\u0001G\u0013K\u0011=,NX\u0018@\u0004=#R\u0014\u0019H\u0006i3\u0004q%@P\b='@@\u001fM\u0013n n%OS\\F\u0019s>@]\u0012VVs%\u0001G\tU\u0006r8UQ\u0018\u0005\u0018r>DG\u0007o/RA\u0011@\u0012\nP?R]\u001fg\u0019ip\u0001\u0007=$N@\u0019V_\u0011P?R]\u001fg\u0019ip\u0001G\bJ\u0006m/E\u0007H$JZ\u0013R\u0018\u001eP?R]\u001fg\u0019ip\u0001G\u0013K\u0011=,NX\u0018@\u0004=#R\u0014\u0019H\u0006i3.P?R]\u001fg\u0019ip\u0001Z\u0013Q\u0013=(M[\u001fN\u0005=+SQ\\V\u0002t&M\u0014\u001e@\u001fs-\u0001D\u000e@\u0006|8DP$P?R]\u001fg\u0019ip\u0001W\u0014@\u0015v#OS\\Q\u0003s/E\u0014\u0012J\u0002xjCX\u0013F\u001dn\u0012i8TZ\u001fD\u0002x.\u0001z>vV{#MQ\u000b^\"DW\u0017\u00052x&@M\t8z\u0013PF\u0000F/.\u0011P?R]\u001fg\u0019ip\u0001D\u0010D\u000ft$F\tN=HZ\u001b\u00057o'\u0007s%UQ\u001eJ\u0002\u00033d\u000f\u0002&j\u00043>Y@3P?R]\u001fg\u0019ip\u0001X\u0013D\u0012=+\u0001G\u0013K\u0011=,HF\u000fQVj#U\\\\\u000b\u001bh9HW\\I\u0019|.\u0001\b\u001aL\u001axt\u0004P%EQ\t2e\u0001z\u001dH\u0013'j\u00043>Y@(=$N@\u0019\r\u00054j@F\u0019\u0005\u001bt9R]\u0012BV|$E\u0014\u000bL\u001aqjCQ\\V\u001dt:QQ\u0018\u00022e\u000eS%\u0001G\u0013K\u0011=&NU\u0018@\u0012\u001d\u007f+E\u0014\u0012P\u001b\u007f/S\u0014\u0013KVi/Y@\\V\u0019s-\u0001X\u0015K\u0013=\bP?R]\u001fg\u0019i\u0011X2@W\b\u0005?s9UF\tH\u0013s>R\u0012P?R]\u001fg\u0019ip\u0001R\u0015K\u001fn\"DP\u0012P?R]\u001fg\u0019ip\u0001G\u0017L\u0006m/E\u0014\u0007&jU]\u001fNV\u001dP?R]\u001fg\u0019ip\u0001Z\u0013\u0005O(o\f\u0005L\u0015S='@@\u001fM\u0013n\u000e=9DU\u000eF\u001e=8DG\tI\u0002\u0003=g\u0001\r-z\u001b\u0004L\u0005Y=z\u0011\u000eL\u0015\u00043$CG\u0003=g\u0001\u0004I#LQ\u000bN!HD\\q\u0003s#OS\rt.MQG\u0005\u0010r&EQ\u000e\u0018\u0010{#MQ\\K\u0019ijG[\tK\u0012'j\u000b2e\u0001u\tQ\u001er8\u001b\u0014\bP?R]\u001fg\u0019i!P?R]\u001fg\u0019ip\u0001Z\u0013\u0005\u0004x+EU\u001eI\u0013=9NZ\u001bVV{%TZ\u0018\u0011P?R]\u001fg\u0019ip\u0001@\tK\u001fs-\u0001\fO/OP\u0019WV_%YQ\u000f\nM%MM\fM\u0019s#B\u000b\\?U[\\w\u0019i+UQ7P?R]\u001fg\u0019ip\u0001Z\u0013\u0005\u0004x+B\\\u001dG\u001axjO[\b@V\u007f&NW\u0017VVt$\u0001@\u0014@V~?SF\u0019K\u0002=8@Z\u001b@\u001bt$WU\u0010L\u0012=\u0004cg\\V\u0002o#OS\\I\u0013s-U\\F\u00058P?R]\u001fg\u0019ip\u0001^\u0013L\u0018=+\u0001C\u0013W\u001ayjCQ\u001aJ\u0004xjSQ\rP\u0013n>HZ\u001b\u0005\u0004x)NY\u0011@\u0018y+U]\u0013K\u00053P?R]\u001fg\u0019ip\u0001Z\u0019D\u0004\u007f3\u0001Z\u0013Q\u0013=(M[\u001fN\u0005=.N\u0014\u0012J\u0002='@@\u001fMVi\"HG\\V\u0019s-\u0011X2@W\b\u0005?s9UF\tH\u0013s>R\t8d\u0010RY\u0000V0j\b=jqU\tV\u0013y\u000e\\$X\u00145K\u0005i8TY\u0019K\u0002\u000f3'TG\u0015FVm8DB\u0015@\u0001=\u0002=b\nI#B_\\a\u0013q+X\t\\?U[\\u\u001a|3\bM8NS\u000e@\u0005n\u0016P?R]\u001fg\u0019ijM[\u001dAV{+HX\u0019AL=\u000e\\$X\u00145K\u0005i8TY\u0019K\u0002\rO/OP\u0019WVN>@@\tV\u0003=e\u0001\u0011P?R]\u001fg\u0019ijM[\u001dA\u0013yp\u0001\u0005=g\f\u0019\\\u000f^%OW\tW\u0004x$U\u0014(P\u0018x\nP?R]\u001fg\u0019ip\u0001\f3'TG\u0015FVq%@P\\\u0012O%TZ\u0018\u00059h>\u0001{\u001a\u0005$|$FQ";
                var4_3 = "3$CG%P?R]\u001fg\u0019ip\u0001Q\u0012Q\u0013oj@\u0014\u000fJ\u0018zjOU\u0011@Vi%\u0001G\u0019D\u0004~\"%=$N@\u0019\u0005\u0014q%B_TV_=8D^\u0019F\u0002x.\u0001V\u0005\u0005\u0002u/\u0001G\u0019W\u0000x8\u0005I#UX\u00196P?R]\u001fg\u0019ip\u0001Z\u0013\u0005\u0004x+B\\\u001dG\u001axjO[\b@V\u007f&NW\u0017VVj#U\\\\D\u001foj@V\u0013S\u0013=>IQ\u0011\u001fP?R]\u001fg\u0019ip\u0001Z\u0013\u0005\u0005t'HX\u001dWVn%OS\u000f\u0005\u0010r8\u0001\u0004=g\u001f\u0014\u0005O+OS\u0019\u0007H$JZ\u0013R\u0018\u0005^%QM\\\t&jLU\fU\u0013yj\u0004O?O\u0014\u0007m8DB\u0015@\u0001\u000e=$N@\u0019\u0005\u0014q%B_TV_\u0011P?R]\u001fg\u0019ip\u0001D\u0010D\u000ft$F\bM8NS\u000e@\u0005n\u0013\u007f+E\u0014\b@\u000eijR[\u0012BVq#OQ\\\u0004I#LQ\u0004O?O\u0014\u001dn?QD\u0013W\u0002x.\u0001R\u0013W\u001b|>R\u000e\\\u000b\u0018\u007f9\r\u0014RQ\u000ei\u001eP?R]\u001fg\u0019ip\u0001G\u0013K\u0011=,NX\u0018@\u0004=#R\u0014\u0019H\u0006i3\u0004q%@P\b='@@\u001fM\u0013n n%OS\\F\u0019s>@]\u0012VVs%\u0001G\tU\u0006r8UQ\u0018\u0005\u0018r>DG\u0007o/RA\u0011@\u0012\nP?R]\u001fg\u0019ip\u0001\u0007=$N@\u0019V_\u0011P?R]\u001fg\u0019ip\u0001G\bJ\u0006m/E\u0007H$JZ\u0013R\u0018\u001eP?R]\u001fg\u0019ip\u0001G\u0013K\u0011=,NX\u0018@\u0004=#R\u0014\u0019H\u0006i3.P?R]\u001fg\u0019ip\u0001Z\u0013Q\u0013=(M[\u001fN\u0005=+SQ\\V\u0002t&M\u0014\u001e@\u001fs-\u0001D\u000e@\u0006|8DP$P?R]\u001fg\u0019ip\u0001W\u0014@\u0015v#OS\\Q\u0003s/E\u0014\u0012J\u0002xjCX\u0013F\u001dn\u0012i8TZ\u001fD\u0002x.\u0001z>vV{#MQ\u000b^\"DW\u0017\u00052x&@M\t8z\u0013PF\u0000F/.\u0011P?R]\u001fg\u0019ip\u0001D\u0010D\u000ft$F\tN=HZ\u001b\u00057o'\u0007s%UQ\u001eJ\u0002\u00033d\u000f\u0002&j\u00043>Y@3P?R]\u001fg\u0019ip\u0001X\u0013D\u0012=+\u0001G\u0013K\u0011=,HF\u000fQVj#U\\\\\u000b\u001bh9HW\\I\u0019|.\u0001\b\u001aL\u001axt\u0004P%EQ\t2e\u0001z\u001dH\u0013'j\u00043>Y@(=$N@\u0019\r\u00054j@F\u0019\u0005\u001bt9R]\u0012BV|$E\u0014\u000bL\u001aqjCQ\\V\u001dt:QQ\u0018\u00022e\u000eS%\u0001G\u0013K\u0011=&NU\u0018@\u0012\u001d\u007f+E\u0014\u0012P\u001b\u007f/S\u0014\u0013KVi/Y@\\V\u0019s-\u0001X\u0015K\u0013=\bP?R]\u001fg\u0019i\u0011X2@W\b\u0005?s9UF\tH\u0013s>R\u0012P?R]\u001fg\u0019ip\u0001R\u0015K\u001fn\"DP\u0012P?R]\u001fg\u0019ip\u0001G\u0017L\u0006m/E\u0014\u0007&jU]\u001fNV\u001dP?R]\u001fg\u0019ip\u0001Z\u0013\u0005O(o\f\u0005L\u0015S='@@\u001fM\u0013n\u000e=9DU\u000eF\u001e=8DG\tI\u0002\u0003=g\u0001\r-z\u001b\u0004L\u0005Y=z\u0011\u000eL\u0015\u00043$CG\u0003=g\u0001\u0004I#LQ\u000bN!HD\\q\u0003s#OS\rt.MQG\u0005\u0010r&EQ\u000e\u0018\u0010{#MQ\\K\u0019ijG[\tK\u0012'j\u000b2e\u0001u\tQ\u001er8\u001b\u0014\bP?R]\u001fg\u0019i!P?R]\u001fg\u0019ip\u0001Z\u0013\u0005\u0004x+EU\u001eI\u0013=9NZ\u001bVV{%TZ\u0018\u0011P?R]\u001fg\u0019ip\u0001@\tK\u001fs-\u0001\fO/OP\u0019WV_%YQ\u000f\nM%MM\fM\u0019s#B\u000b\\?U[\\w\u0019i+UQ7P?R]\u001fg\u0019ip\u0001Z\u0013\u0005\u0004x+B\\\u001dG\u001axjO[\b@V\u007f&NW\u0017VVt$\u0001@\u0014@V~?SF\u0019K\u0002=8@Z\u001b@\u001bt$WU\u0010L\u0012=\u0004cg\\V\u0002o#OS\\I\u0013s-U\\F\u00058P?R]\u001fg\u0019ip\u0001^\u0013L\u0018=+\u0001C\u0013W\u001ayjCQ\u001aJ\u0004xjSQ\rP\u0013n>HZ\u001b\u0005\u0004x)NY\u0011@\u0018y+U]\u0013K\u00053P?R]\u001fg\u0019ip\u0001Z\u0019D\u0004\u007f3\u0001Z\u0013Q\u0013=(M[\u001fN\u0005=.N\u0014\u0012J\u0002='@@\u001fMVi\"HG\\V\u0019s-\u0011X2@W\b\u0005?s9UF\tH\u0013s>R\t8d\u0010RY\u0000V0j\b=jqU\tV\u0013y\u000e\\$X\u00145K\u0005i8TY\u0019K\u0002\u000f3'TG\u0015FVm8DB\u0015@\u0001=\u0002=b\nI#B_\\a\u0013q+X\t\\?U[\\u\u001a|3\bM8NS\u000e@\u0005n\u0016P?R]\u001fg\u0019ijM[\u001dAV{+HX\u0019AL=\u000e\\$X\u00145K\u0005i8TY\u0019K\u0002\rO/OP\u0019WVN>@@\tV\u0003=e\u0001\u0011P?R]\u001fg\u0019ijM[\u001dA\u0013yp\u0001\u0005=g\f\u0019\\\u000f^%OW\tW\u0004x$U\u0014(P\u0018x\nP?R]\u001fg\u0019ip\u0001\f3'TG\u0015FVq%@P\\\u0012O%TZ\u0018\u00059h>\u0001{\u001a\u0005$|$FQ".length();
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
                    var5[var3_1++] = MusicBot.d(MusicBot.h(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "I#UX\u0019\u0006m+TG\u0019A";
                    var4_3 = "I#UX\u0019\u0006m+TG\u0019A".length();
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
                    var5[var3_1++] = MusicBot.d(MusicBot.h(v1));
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
        MusicBot.db = var5;
        MusicBot.SONG_DIR = new File(LF21Client.configDir, "notebot");
        MusicBot.J = t_0.a(21.0f);
        MusicBot.K = t_0.a(10.5f);
    }

    private static int a(DataInputStream dataInputStream) throws IOException {
        return dataInputStream.readUnsignedByte() | dataInputStream.readUnsignedByte() << 8;
    }

    private static int b(DataInputStream dataInputStream) throws IOException {
        return dataInputStream.readUnsignedByte() | dataInputStream.readUnsignedByte() << 8 | dataInputStream.readUnsignedByte() << 16 | dataInputStream.readUnsignedByte() << 24;
    }

    public MusicBot() {
        String[] stringArray = db;
        super("MusicBot", Category.WORLD);
        this.L = new ModeSetting("Mode", "Exact Instruments", "Any Instrument").withDefault("Exact Instruments");
        this.M = new BooleanSetting("Skip Tuning", false);
        this.N = new NumberSetting("Range", 5.0, 3.0, 6.0, 0.5);
        this.O = new NumberSetting("Tick Delay", 1.0, 1.0, 20.0, 1.0);
        this.P = new NumberSetting("Concurrent Tune", 1.0, 1.0, 20.0, 1.0);
        this.Q = new NumberSetting("Check Delay", 10.0, 1.0, 40.0, 1.0);
        this.R = new BooleanSetting("Polyphonic", true);
        this.S = new BooleanSetting("Auto Rotate", true);
        this.T = new BooleanSetting("Auto Play", false);
        this.U = new BooleanSetting("Round Out Of Range", false);
        this.V = new BooleanSetting("Swing Arm", true);
        this.W = new BooleanSetting("Render Boxes", true);
        this.X = new BooleanSetting("Render Status", true);
        this.Y = new LinkedHashMap<q, BlockPos>();
        this.Z = new HashSet<q>();
        this.aa = new LinkedHashMap<BlockPos, Integer>();
        this.ab = new HashSet<BlockPos>();
        this.ac = new HashSet<BlockPos>();
        this.ad = new ArrayList<BlockPos>();
        this.ae = new ArrayList<BlockPos>();
        this.af = new ArrayList<BlockPos>();
        this.ai = a.IDLE;
        this.ap = "MusicBot";
        this.aq = "00:00 / 00:00";
        I = this;
        this.setX(300.0f);
        this.setY(300.0f);
        this.e(168.0f);
        this.f(34.0f);
        this.a("Title");
        this.a("Time");
        this.b("Progress");
        MusicBot.N();
    }

    private void G() {
        block3: {
            this.aj = false;
            this.J();
            if (!((Boolean)this.T.getValue()).booleanValue() || this.ak) break block3;
            List<String> list = this.listSongs();
            if (!list.isEmpty()) {
                String string = list.get(ThreadLocalRandom.current().nextInt(list.size()));
                this.loadSong(new File(SONG_DIR, string), false);
                return;
            }
        }
        this.as = 0;
        this.ai = a.READY;
        ChatUtil.print("MusicBot: finished");
    }

    private static i d(File file) throws IOException {
        LinkedHashMap<Integer, List<q>> linkedHashMap = new LinkedHashMap<Integer, List<q>>();
        String string = MusicBot.d(file.getName());
        String[] stringArray = db;
        String string2 = "Unknown";
        int n3 = 0;
        for (String string3 : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
            ++n3;
            String string4 = string3.trim();
            stringArray = db;
            if (string4.startsWith("// Name: ")) {
                string = string4.substring(9).trim();
                continue;
            }
            stringArray = db;
            if (string4.startsWith("// Author: ")) {
                string2 = string4.substring(11).trim();
                continue;
            }
            if (string4.isEmpty() || string4.startsWith("#")) continue;
            if (string4.startsWith("//")) {
                continue;
            }
            String[] stringArray2 = string4.split(":");
            if (stringArray2.length < 2) {
                throw new IOException("bad text song line " + n3);
            }
            try {
                int n4 = Integer.parseInt(stringArray2[0].trim());
                int n5 = Integer.parseInt(stringArray2[1].trim());
                int n6 = stringArray2.length >= 3 ? Integer.parseInt(stringArray2[2].trim()) : 0;
                int n7 = n6;
                NoteBlockInstrument noteBlockInstrument = MusicBot.c(n7);
                if (noteBlockInstrument == null) continue;
                linkedHashMap.computeIfAbsent(n4, n2 -> new ArrayList()).add(new q(noteBlockInstrument, n5));
            }
            catch (NumberFormatException numberFormatException) {
                throw new IOException("bad number on text song line " + n3, numberFormatException);
            }
        }
        return new i(linkedHashMap, string, string2);
    }

    private void A() {
        block44: {
            block43: {
                block42: {
                    block41: {
                        List<BlockPos> list = this.B();
                        if (list.isEmpty()) {
                            ChatUtil.print("MusicBot: no reachable note blocks with air above them");
                            this.ai = a.READY;
                            return;
                        }
                        this.Y.clear();
                        this.Z.clear();
                        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>(list);
                        LinkedHashSet<q> linkedHashSet = new LinkedHashSet<q>();
                        for (Collection object : this.ah.a.values()) {
                            for (q q2 : object) {
                                q q3 = this.a(q2);
                                if (q3 == null) continue;
                                linkedHashSet.add(q3);
                            }
                        }
                        for (q q4 : linkedHashSet) {
                            BlockPos blockPos;
                            block37: {
                                block40: {
                                    List<BlockPos> list2;
                                    MusicBot musicBot;
                                    block39: {
                                        block38: {
                                            block36: {
                                                blockPos = this.a(arrayList, q4, true);
                                                if (blockPos != null || ((Boolean)this.M.getValue()).booleanValue()) break block36;
                                                blockPos = this.a(arrayList, q4, false);
                                            }
                                            if (blockPos != null) break block37;
                                            musicBot = this;
                                            if (!arrayList.isEmpty()) break block38;
                                            list2 = list;
                                            break block39;
                                        }
                                        list2 = arrayList;
                                    }
                                    blockPos = musicBot.a(list2, q4);
                                    if (blockPos != null || arrayList.isEmpty()) break block40;
                                    blockPos = this.a(list, q4);
                                }
                                if (blockPos != null) {
                                    this.Z.add(q4);
                                }
                            }
                            if (blockPos == null) continue;
                            this.Y.put(q4, blockPos.immutable());
                            arrayList.remove(blockPos);
                        }
                        if (this.Y.isEmpty()) {
                            ChatUtil.print("MusicBot: nearby note blocks do not match this song");
                            this.ai = a.READY;
                            return;
                        }
                        int n2 = linkedHashSet.size() - this.Y.size();
                        if (n2 > 0) {
                            ChatUtil.print("MusicBot: " + n2 + " note(s) are missing and will be skipped");
                        }
                        this.ay = this.K();
                        if (!((Boolean)this.M.getValue()).booleanValue()) break block41;
                        this.aa.clear();
                        this.H();
                        break block42;
                    }
                    this.C();
                }
                if (((Boolean)this.M.getValue()).booleanValue() || !this.aa.isEmpty()) break block43;
                this.H();
                break block44;
            }
            if (!((Boolean)this.M.getValue()).booleanValue()) {
                this.ai = a.TUNING;
                this.aj = false;
                ChatUtil.print("MusicBot: tuning " + this.aa.size() + " note block(s)");
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public List<p> a(String string, int n2) {
        MusicBot.N();
        String string2 = MusicBot.c(string);
        if (string2.isEmpty()) return List.of();
        if (n2 <= 0) {
            return List.of();
        }
        File[] fileArray = SONG_DIR.listFiles(file -> {
            boolean bl;
            block5: {
                block4: {
                    if (!file.isFile() || !MusicBot.b(file)) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        });
        if (fileArray == null) return List.of();
        if (fileArray.length == 0) {
            return List.of();
        }
        Set<q> set = MusicBot.mc.player == null || MusicBot.mc.level == null ? Set.of() : this.b(this.B());
        ArrayList<t> arrayList = new ArrayList<t>();
        for (File file2 : fileArray) {
            double d2 = MusicBot.a(string2, MusicBot.c(MusicBot.d(file2.getName())));
            if (d2 < 0.42) continue;
            try {
                arrayList.add(new t(file2, MusicBot.c(file2), d2));
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        arrayList.sort(Comparator.comparingDouble(t::c).reversed().thenComparing(t2 -> t2.a().getName(), String.CASE_INSENSITIVE_ORDER));
        ArrayList arrayList2 = new ArrayList();
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            t t3 = (t)iterator.next();
            if (arrayList2.size() >= n2) {
                return arrayList2;
            }
            arrayList2.add(new p(t3.a().getName(), MusicBot.d(t3.a().getName()), this.a(t3.b(), set).a(), t3.b().d));
        }
        return arrayList2;
    }

    private void L() {
        block8: {
            block7: {
                if (this.ac.isEmpty() || MusicBot.mc.player == null) break block7;
                if (MusicBot.mc.level != null) break block8;
            }
            return;
        }
        for (BlockPos blockPos : new ArrayList<BlockPos>(this.ac)) {
            this.a(ServerboundPlayerActionPacket.Action.ABORT_DESTROY_BLOCK, blockPos);
        }
        this.ac.clear();
    }

    public void pause() {
        String string;
        block8: {
            block7: {
                if (this.ai != a.PLAYING) {
                    return;
                }
                MusicBot musicBot = this;
                boolean bl = !this.aj;
                musicBot.aj = bl;
                if (!this.aj) break block7;
                string = "resumed";
                break block8;
            }
            String[] stringArray = db;
            string = "paused";
        }
        ChatUtil.print("MusicBot: " + string);
    }

    public r y() {
        boolean bl;
        boolean bl2;
        int n2;
        int n3;
        String string;
        r r2;
        r r3;
        block6: {
            block5: {
                r r4;
                if (this.ah == null) {
                    return new r("No song loaded", 0, 0, false, false);
                }
                r3 = r4;
                r2 = r4;
                string = this.ah.b;
                n3 = Math.max(0, Math.min(this.as, this.ah.d));
                n2 = Math.max(1, this.ah.d);
                bl2 = this.aj;
                if (this.ai != a.PLAYING && this.ai != a.READY) break block5;
                bl = true;
                break block6;
            }
            bl = false;
        }
        r3(string, n3, n2, bl2, bl);
        return r2;
    }

    private int a(int n2) {
        if (this.ah == null) {
            return 0;
        }
        int n3 = 0;
        for (Map.Entry<Integer, List<q>> entry : this.ah.a.entrySet()) {
            if (entry.getKey() > n2) {
                continue;
            }
            int n4 = 0;
            for (q q2 : entry.getValue()) {
                block15: {
                    q q3 = this.a(q2);
                    if (this.ak) break block15;
                    if (q3 == null) continue;
                    if (!this.Y.containsKey(q3)) {
                        continue;
                    }
                }
                ++n4;
                if (((Boolean)this.R.getValue()).booleanValue()) continue;
                break;
            }
            n3 += n4;
        }
        return n3;
    }

    private BlockPos b(List<BlockPos> list, q q2, boolean bl) {
        BlockPos blockPos = null;
        int n2 = Integer.MAX_VALUE;
        for (BlockPos blockPos2 : list) {
            BlockState blockState = MusicBot.mc.level.getBlockState(blockPos2);
            if (!blockState.is(Blocks.NOTE_BLOCK)) {
                continue;
            }
            if (bl && blockState.getValue((Property)NoteBlock.INSTRUMENT) != q2.a) {
                continue;
            }
            int n3 = Math.abs(this.a(blockPos2, blockState) - q2.b);
            if (n3 >= n2) continue;
            blockPos = blockPos2;
            n2 = n3;
        }
        return blockPos;
    }

    private void a(ServerboundPlayerActionPacket.Action action, BlockPos blockPos) {
        PacketUtil.b(n2 -> new ServerboundPlayerActionPacket(action, blockPos, Direction.UP, n2));
    }

    private int e(List<q> list) {
        int n2 = 0;
        for (q q2 : list) {
            block6: {
                float f2;
                block5: {
                    f2 = (float)Math.pow(2.0, (double)(q2.b - 12) / 12.0);
                    if (q2.a != null) break block5;
                    MusicBot.mc.player.playSound((SoundEvent)SoundEvents.NOTE_BLOCK_HARP.get(), 2.0f, f2);
                    break block6;
                }
                MusicBot.mc.player.playSound((SoundEvent)q2.a.getSoundEvent().value(), 2.0f, f2);
            }
            ++n2;
            if (((Boolean)this.R.getValue()).booleanValue()) continue;
            break;
        }
        return n2;
    }

    private Integer c(BlockPos blockPos) {
        for (Map.Entry<q, BlockPos> entry : this.Y.entrySet()) {
            if (!entry.getValue().equals((Object)blockPos)) continue;
            return entry.getKey().b;
        }
        return null;
    }

    public void a(double d2) {
        block7: {
            block6: {
                if (this.ah == null) break block6;
                if (this.ai == a.PLAYING) break block7;
                if (this.ai == a.READY) break block7;
            }
            return;
        }
        int n2 = (int)Math.round(Math.max(0.0, Math.min(1.0, d2)) * (double)this.ah.d);
        this.L();
        this.J();
        this.as = n2;
        this.az = n2;
        this.ax = this.a(n2);
    }

    private void J() {
        this.ae.clear();
        this.af.clear();
        this.av = 0;
        this.ag = null;
    }

    private int a(BlockPos blockPos, BlockState blockState) {
        if (!((Boolean)this.M.getValue()).booleanValue()) {
            for (Map.Entry<q, BlockPos> entry : this.Y.entrySet()) {
                if (!entry.getValue().equals((Object)blockPos) || this.Z.contains(entry.getKey())) continue;
                return entry.getKey().b;
            }
        }
        return (Integer)blockState.getValue((Property)NoteBlock.NOTE);
    }

    public String status() {
        if (this.ah == null) {
            return "idle; folder=" + SONG_DIR.getAbsolutePath();
        }
        int n2 = this.Y.size();
        int n3 = this.ah.d;
        int n4 = this.as;
        String string = this.ah.b;
        String[] stringArray = db;
        return this.ai.name().toLowerCase(Locale.ROOT) + "; " + string + "; tick " + n4 + "/" + n3 + "; mapped " + n2;
    }

    private BlockPos a(List<BlockPos> list, q q2) {
        BlockPos blockPos = this.b(list, q2, true);
        BlockPos blockPos2 = blockPos != null ? blockPos : this.b(list, q2, false);
        return blockPos2;
    }

    private static int b(String string, String string2) {
        int n2;
        int[] nArray = new int[string2.length() + 1];
        int[] nArray2 = new int[string2.length() + 1];
        for (n2 = 0; n2 <= string2.length(); ++n2) {
            nArray[n2] = n2;
        }
        for (n2 = 1; n2 <= string.length(); ++n2) {
            nArray2[0] = n2;
            int n3 = 1;
            while (true) {
                int n4;
                block10: {
                    block9: {
                        if (n3 > string2.length()) break;
                        if (string.charAt(n2 - 1) != string2.charAt(n3 - 1)) break block9;
                        n4 = 0;
                        break block10;
                    }
                    n4 = 1;
                }
                int n5 = n4;
                nArray2[n3] = Math.min(Math.min(nArray2[n3 - 1] + 1, nArray[n3] + 1), nArray[n3 - 1] + n5);
                ++n3;
            }
            int[] nArray3 = nArray;
            nArray = nArray2;
            nArray2 = nArray3;
        }
        return nArray[string2.length()];
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void searchSongs(String string) {
        MusicBot.N();
        String string2 = MusicBot.c(string);
        if (string2.isEmpty()) {
            ChatUtil.print("MusicBot: enter a song name to search");
            return;
        }
        File[] fileArray = SONG_DIR.listFiles(file -> {
            boolean bl;
            block5: {
                block4: {
                    if (!file.isFile() || !MusicBot.b(file)) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        });
        if (fileArray == null || fileArray.length == 0) {
            ChatUtil.print("MusicBot: song folder is empty");
            return;
        }
        List<p> list = this.a(string, 20);
        if (list.isEmpty()) {
            ChatUtil.print("MusicBot: no similar songs for " + string);
            return;
        }
        int n2 = list.size();
        String string3 = n2 == 1 ? "" : "s";
        String[] stringArray = db;
        ChatUtil.print("MusicBot: " + n2 + " search result" + string3);
        int n3 = 0;
        while (n3 < n2) {
            p p2 = list.get(n3);
            try {
                MusicBot.a(new File(SONG_DIR, p2.a()), MusicBot.c(new File(SONG_DIR, p2.a())), p2.c());
            }
            catch (IOException iOException) {
                // empty catch block
            }
            ++n3;
        }
        return;
    }

    private static String c(String string) {
        if (string == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        string.toLowerCase(Locale.ROOT).codePoints().filter(Character::isLetterOrDigit).forEach(stringBuilder::appendCodePoint);
        return stringBuilder.toString();
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
    }

    private BlockPos a(List<BlockPos> list, q q2, boolean bl) {
        for (BlockPos blockPos : list) {
            BlockState blockState = MusicBot.mc.level.getBlockState(blockPos);
            if (!blockState.is(Blocks.NOTE_BLOCK)) {
                continue;
            }
            if (this.L.is("Exact Instruments") && blockState.getValue((Property)NoteBlock.INSTRUMENT) != q2.a) {
                continue;
            }
            if (bl && (Integer)blockState.getValue((Property)NoteBlock.NOTE) != q2.b) {
                continue;
            }
            return blockPos;
        }
        return null;
    }

    public void stopPlayback() {
        this.L();
        this.J();
        this.aj = false;
        this.as = 0;
        this.ax = 0;
        this.az = 0;
        MusicBot musicBot = this;
        a a2 = this.ah == null ? a.IDLE : a.READY;
        musicBot.ai = a2;
        ChatUtil.print("MusicBot: stopped");
    }

    private static File a(File file) {
        if (file.isAbsolute()) {
            return file;
        }
        return new File(SONG_DIR, file.getPath());
    }

    private q a(q q2) {
        q q3;
        int n2;
        block8: {
            block9: {
                n2 = q2.b;
                if (n2 >= 0 && n2 <= 24) break block8;
                if (((Boolean)this.U.getValue()).booleanValue()) break block9;
                return null;
            }
            n2 = Math.max(0, Math.min(24, n2));
        }
        q q4 = q3;
        q q5 = q3;
        NoteBlockInstrument noteBlockInstrument = this.L.is("Any Instrument") ? null : q2.a;
        q4(noteBlockInstrument, n2);
        return q5;
    }

    private void E() {
        block7: {
            block6: {
                if (--this.au > 0) {
                    return;
                }
                this.C();
                if (!this.aa.isEmpty()) break block6;
                this.H();
                break block7;
            }
            ++this.aw;
            if (this.aw >= 4) {
                HashSet<BlockPos> hashSet = new HashSet<BlockPos>(this.aa.keySet());
                this.Y.entrySet().removeIf(entry -> hashSet.contains(entry.getValue()));
                this.aa.clear();
                this.ay = this.K();
                String[] stringArray = db;
                ChatUtil.print("MusicBot: skipped " + hashSet.size() + " note block(s) rejected by the server");
                this.H();
                return;
            }
            this.ai = a.TUNING;
        }
    }

    private static i c(File file) throws IOException {
        if (!file.isFile()) {
            throw new IOException("file not found: " + file.getAbsolutePath());
        }
        String string = file.getName().toLowerCase(Locale.ROOT);
        if (string.endsWith(".nbs")) {
            return MusicBot.e(file);
        }
        if (string.endsWith(".txt")) {
            return MusicBot.d(file);
        }
        throw new IOException("supported formats: .nbs, .txt");
    }

    private List<BlockPos> c(List<q> list) {
        LinkedHashSet<BlockPos> linkedHashSet = new LinkedHashSet<BlockPos>();
        for (q q2 : list) {
            q q3 = this.a(q2);
            if (q3 == null) {
                continue;
            }
            BlockPos blockPos = this.Y.get(q3);
            if (blockPos == null) continue;
            if (!MusicBot.mc.level.getBlockState(blockPos).is(Blocks.NOTE_BLOCK)) {
                continue;
            }
            linkedHashSet.add(blockPos);
            if (((Boolean)this.R.getValue()).booleanValue()) continue;
            break;
        }
        return new ArrayList<BlockPos>(linkedHashSet);
    }

    private List<BlockPos> B() {
        int n2 = (int)Math.ceil(((Number)this.N.getValue()).doubleValue());
        BlockPos blockPos2 = MusicBot.mc.player.blockPosition();
        double d2 = ((Number)this.N.getValue()).doubleValue() * ((Number)this.N.getValue()).doubleValue();
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        for (int i2 = -n2; i2 <= n2; ++i2) {
            for (int i3 = -n2; i3 <= n2; ++i3) {
                for (int i4 = -n2; i4 <= n2; ++i4) {
                    BlockPos blockPos3 = blockPos2.offset(i2, i3, i4);
                    if (Vec3.atCenterOf((Vec3i)blockPos3).distanceToSqr(MusicBot.mc.player.getEyePosition()) > d2) {
                        continue;
                    }
                    if (!MusicBot.mc.level.getBlockState(blockPos3).is(Blocks.NOTE_BLOCK)) {
                        continue;
                    }
                    if (!MusicBot.mc.level.getBlockState(blockPos3.above()).isAir()) {
                        continue;
                    }
                    arrayList.add(blockPos3.immutable());
                }
            }
        }
        arrayList.sort(Comparator.comparingDouble(blockPos -> Vec3.atCenterOf((Vec3i)blockPos).distanceToSqr(MusicBot.mc.player.getEyePosition())));
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void recommendSongs() {
        MusicBot.N();
        if (MusicBot.mc.player == null || MusicBot.mc.level == null) {
            ChatUtil.print("MusicBot: join a world before requesting recommendations");
            return;
        }
        List<BlockPos> list = this.B();
        if (list.isEmpty()) {
            ChatUtil.print("MusicBot: no reachable note blocks in the current range");
            return;
        }
        Set<q> set = this.b(list);
        File[] fileArray = SONG_DIR.listFiles(file -> {
            boolean bl;
            block5: {
                block4: {
                    if (!file.isFile() || !file.getName().toLowerCase(Locale.ROOT).endsWith(".nbs")) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        });
        if (fileArray == null || fileArray.length == 0) {
            ChatUtil.print("MusicBot: song folder is empty");
            return;
        }
        ArrayList<s> arrayList = new ArrayList<s>();
        for (File file2 : fileArray) {
            try {
                i i2 = MusicBot.c(file2);
                o o2 = this.a(i2, set);
                if (o2.c() == 0 || o2.e() == 0) continue;
                arrayList.add(new s(file2, i2, o2.a(), o2.b(), o2.c(), o2.d(), o2.e()));
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        arrayList.sort(Comparator.comparingDouble(s::c).reversed().thenComparing(Comparator.comparingInt(s::d).reversed()).thenComparing(s2 -> s2.a().getName(), String.CASE_INSENSITIVE_ORDER));
        if (arrayList.isEmpty()) {
            ChatUtil.print("MusicBot: no readable songs found");
            return;
        }
        List<s> list2 = arrayList.stream().filter(s2 -> {
            boolean bl;
            block5: {
                block4: {
                    if (!(s2.c() >= 0.95) || !(s2.c() <= 1.0)) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        }).toList();
        if (list2.isEmpty()) {
            ChatUtil.print("MusicBot: no 95%-100% matches");
            return;
        }
        String[] stringArray = db;
        ChatUtil.print("MusicBot: " + list2.size() + " matches");
        Iterator iterator = list2.iterator();
        while (iterator.hasNext()) {
            s s3 = (s)iterator.next();
            MusicBot.a(s3.a(), s3.b(), s3.c());
        }
        return;
    }

    private static void N() {
        if (!SONG_DIR.exists()) {
            SONG_DIR.mkdirs();
        }
    }

    private static String c(DataInputStream dataInputStream) throws IOException {
        int n2;
        block7: {
            n2 = MusicBot.b(dataInputStream);
            if (n2 >= 0 && n2 <= 0x100000) break block7;
            throw new IOException("invalid NBS string length: " + n2);
        }
        byte[] byArray = dataInputStream.readNBytes(n2);
        if (byArray.length != n2) {
            throw new EOFException();
        }
        return new String(byArray, StandardCharsets.UTF_8).replace('\r', ' ');
    }

    public void z() {
        block6: {
            block5: {
                if (this.ai != a.PLAYING) break block5;
                this.pause();
                break block6;
            }
            if (this.ai == a.READY) {
                this.play();
            }
        }
    }

    private static boolean b(File file) {
        boolean bl;
        block5: {
            block4: {
                String string = file.getName().toLowerCase(Locale.ROOT);
                if (!string.endsWith(".nbs") && !string.endsWith(".txt")) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    private static i e(File file) throws IOException {
        i i2;
        LinkedHashMap<Integer, List<q>> linkedHashMap = new LinkedHashMap<Integer, List<q>>();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        try {
            int n3 = MusicBot.a(dataInputStream);
            int n4 = 0;
            if (n3 == 0) {
                n4 = dataInputStream.readUnsignedByte();
                dataInputStream.readUnsignedByte();
                if (n4 >= 3) {
                    n3 = MusicBot.a(dataInputStream);
                }
            }
            MusicBot.a(dataInputStream);
            String string = MusicBot.c(dataInputStream);
            String string2 = MusicBot.c(dataInputStream);
            MusicBot.c(dataInputStream);
            MusicBot.c(dataInputStream);
            float f2 = (float)MusicBot.a(dataInputStream) / 100.0f;
            if (f2 <= 0.0f) {
                f2 = 10.0f;
            }
            dataInputStream.readUnsignedByte();
            dataInputStream.readUnsignedByte();
            dataInputStream.readUnsignedByte();
            MusicBot.b(dataInputStream);
            MusicBot.b(dataInputStream);
            MusicBot.b(dataInputStream);
            MusicBot.b(dataInputStream);
            MusicBot.b(dataInputStream);
            MusicBot.c(dataInputStream);
            if (n4 >= 4) {
                dataInputStream.readUnsignedByte();
                dataInputStream.readUnsignedByte();
                MusicBot.a(dataInputStream);
            }
            double d2 = -1.0;
            block17: while (true) {
                int n5 = MusicBot.a(dataInputStream);
                if (n5 == 0) {
                    break;
                }
                d2 += (double)n5 * (20.0 / (double)f2);
                while (true) {
                    block28: {
                        int n6 = MusicBot.a(dataInputStream);
                        if (n6 != 0) break block28;
                        continue block17;
                    }
                    int n7 = dataInputStream.readUnsignedByte();
                    int n8 = dataInputStream.readUnsignedByte();
                    if (n4 >= 4) {
                        dataInputStream.readUnsignedByte();
                        dataInputStream.readUnsignedByte();
                        MusicBot.a(dataInputStream);
                    }
                    NoteBlockInstrument noteBlockInstrument = MusicBot.c(n7);
                    if (noteBlockInstrument == null) {
                        continue;
                    }
                    int n9 = (int)Math.round(d2);
                    linkedHashMap.computeIfAbsent(n9, n2 -> new ArrayList()).add(new q(noteBlockInstrument, n8 - 33));
                }
                break;
            }
            if (string.isBlank()) {
                string = MusicBot.d(file.getName());
            }
            if (string2.isBlank()) {
                String[] stringArray = db;
                string2 = "Unknown";
            }
            i2 = new i(linkedHashMap, string, string2);
        }
        catch (Throwable throwable) {
            try {
                try {
                    dataInputStream.close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (EOFException eOFException) {
                throw new IOException("truncated NBS file", eOFException);
            }
        }
        dataInputStream.close();
        return i2;
    }

    public void loadSong(File file, boolean bl) {
        try {
            this.L();
            File file2 = MusicBot.a(file);
            i i2 = MusicBot.c(file2);
            if (i2.a.isEmpty()) {
                throw new IOException("song contains no supported notes");
            }
            this.ah = i2;
            this.ak = bl;
            this.as = 0;
            this.at = 0;
            this.au = 0;
            this.aw = 0;
            this.aj = false;
            this.ax = 0;
            this.az = 0;
            MusicBot musicBot = this;
            int n2 = bl ? this.a(i2) : 0;
            musicBot.ay = n2;
            this.J();
            this.Y.clear();
            this.Z.clear();
            this.aa.clear();
            this.ab.clear();
            if (!this.isEnabled()) {
                this.setEnabled(true);
            }
            MusicBot musicBot2 = this;
            a a2 = bl ? a.PLAYING : a.SETUP;
            musicBot2.ai = a2;
            this.aj = bl;
            int n3 = i2.a();
            String string = i2.c;
            String string2 = i2.b;
            String[] stringArray = db;
            ChatUtil.print("MusicBot loaded: " + string2 + " - " + string + " (" + n3 + " notes)");
        }
        catch (Exception exception) {
            ChatUtil.print("MusicBot load failed: " + exception.getMessage());
        }
    }

    @Override
    protected void onEnable() {
        MusicBot.N();
    }

    public void play() {
        block8: {
            block7: {
                if (this.ah == null) {
                    ChatUtil.print("MusicBot: load a song first with .music load <file>");
                    return;
                }
                if (this.ai != a.READY && this.ai != a.PLAYING) break block7;
                this.ai = a.PLAYING;
                this.aj = true;
                ChatUtil.print("MusicBot: playing");
                break block8;
            }
            ChatUtil.print("MusicBot: note blocks are still being prepared");
        }
    }

    private int a(i i2) {
        if (i2 == null) {
            return 0;
        }
        if (((Boolean)this.R.getValue()).booleanValue()) {
            return i2.a();
        }
        return (int)i2.a.values().stream().filter(list -> {
            boolean bl = !list.isEmpty();
            return bl;
        }).count();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static NoteBlockInstrument c(int n2) {
        switch (n2) {
            case 0: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.HARP;
                return noteBlockInstrument;
            }
            case 1: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.BASS;
                return noteBlockInstrument;
            }
            case 2: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.BASEDRUM;
                return noteBlockInstrument;
            }
            case 3: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.SNARE;
                return noteBlockInstrument;
            }
            case 4: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.HAT;
                return noteBlockInstrument;
            }
            case 5: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.GUITAR;
                return noteBlockInstrument;
            }
            case 6: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.FLUTE;
                return noteBlockInstrument;
            }
            case 7: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.BELL;
                return noteBlockInstrument;
            }
            case 8: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.CHIME;
                return noteBlockInstrument;
            }
            case 9: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.XYLOPHONE;
                return noteBlockInstrument;
            }
            case 10: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.IRON_XYLOPHONE;
                return noteBlockInstrument;
            }
            case 11: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.COW_BELL;
                return noteBlockInstrument;
            }
            case 12: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.DIDGERIDOO;
                return noteBlockInstrument;
            }
            case 13: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.BIT;
                return noteBlockInstrument;
            }
            case 14: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.BANJO;
                return noteBlockInstrument;
            }
            case 15: {
                NoteBlockInstrument noteBlockInstrument = NoteBlockInstrument.PLING;
                return noteBlockInstrument;
            }
        }
        return null;
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
                    case 0 -> 29;
                    case 1 -> 74;
                    case 2 -> 33;
                    case 3 -> 52;
                    case 4 -> 124;
                    case 5 -> 37;
                    default -> 118;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    public void a() {
    }

    private String a(String string, float f2) {
        if (y_0.a(string, J) <= f2) {
            return string;
        }
        String string2 = "...";
        int n2 = string.length();
        while (true) {
            if (n2 <= 0 || !(y_0.a(string.substring(0, n2) + string2, J) > f2)) break;
            --n2;
        }
        return string.substring(0, Math.max(0, n2)) + string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void F() {
        if (!this.aj) {
            return;
        }
        if (!this.af.isEmpty()) {
            if (!this.I()) {
                return;
            }
            int n2 = this.d(this.af);
            if (n2 > 0) {
                this.ax += n2;
                this.az = this.as;
            }
            this.J();
            ++this.as;
            return;
        }
        if (this.as > this.ah.d) {
            this.G();
            return;
        }
        List<q> list = this.ah.a.get(this.as);
        if (list != null && !list.isEmpty()) {
            if (this.ak) {
                int n3 = this.e(list);
                if (n3 > 0) {
                    this.ax += n3;
                    this.az = this.as;
                }
            } else {
                List<BlockPos> list2 = this.c(list);
                if (!list2.isEmpty()) {
                    this.af.addAll(list2);
                    this.a(list2.get(0));
                    if (((Boolean)this.S.getValue()).booleanValue()) {
                        return;
                    }
                    int n4 = this.d(this.af);
                    if (n4 > 0) {
                        this.ax += n4;
                        this.az = this.as;
                    }
                    this.J();
                }
            }
        }
        ++this.as;
    }

    private static String b(int n2) {
        int n3 = Math.max(0, n2 / 20);
        return String.format(Locale.ROOT, "%02d:%02d", n3 / 60, n3 % 60);
    }

    @Override
    protected void onDisable() {
        this.L();
        this.aj = false;
        this.J();
        this.ab.clear();
        this.ad.clear();
    }

    private void H() {
        this.J();
        this.as = 0;
        this.ax = 0;
        this.az = 0;
        this.aw = 0;
        this.ay = this.ak ? this.a(this.ah) : this.K();
        this.ai = a.PLAYING;
        this.aj = true;
        ChatUtil.print("MusicBot: playing");
    }

    private int K() {
        if (this.ah == null) {
            return 0;
        }
        int n2 = 0;
        for (List<q> list : this.ah.a.values()) {
            int n3 = 0;
            for (q q2 : list) {
                q q3 = this.a(q2);
                if (q3 == null) continue;
                if (!this.Y.containsKey(q3)) {
                    continue;
                }
                ++n3;
                if (((Boolean)this.R.getValue()).booleanValue()) continue;
                break;
            }
            n2 += n3;
        }
        return n2;
    }

    public void clearSong() {
        this.L();
        this.J();
        this.ah = null;
        this.aj = false;
        this.ak = false;
        this.ai = a.IDLE;
        this.as = 0;
        this.ax = 0;
        this.ay = 0;
        this.az = 0;
        this.Y.clear();
        this.Z.clear();
        this.aa.clear();
        this.ab.clear();
    }

    public List<String> listSongs() {
        MusicBot.N();
        File[] fileArray = SONG_DIR.listFiles(file -> {
            boolean bl;
            block5: {
                block4: {
                    if (!file.isFile() || !MusicBot.b(file)) break block4;
                    bl = true;
                    break block5;
                }
                bl = false;
            }
            return bl;
        });
        if (fileArray == null) {
            return List.of();
        }
        return Arrays.stream(fileArray).sorted(Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER)).map(File::getName).toList();
    }

    private static char[] h(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x76);
        }
        return cArray;
    }
}

