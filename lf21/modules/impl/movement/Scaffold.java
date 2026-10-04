/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import lf21.ClientBase;
import lf21.event.EventTarget;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.JumpEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.PreMotionEvent;
import lf21.event.impl.Render2DEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.UpdateHeldItemEvent;
import lf21.gui.k;
import lf21.hud.HudElement;
import lf21.modules.Category;
import lf21.modules.impl.combat.KillAura;
import lf21.modules.impl.movement.r;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.render.ab;
import lf21.render.h_0;
import lf21.render.i_0;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.e;
import lf21.utils.game.f;
import lf21.utils.game.g;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class Scaffold
extends HudElement {
    public final BooleanSetting tellyMovementFix;
    private int at;
    public final NumberSetting tellyPlaceAirTicks;
    public final NumberSetting J;
    public final BooleanSetting tellySwing;
    private float aY;
    private int an;
    public static Scaffold INSTANCE;
    public a aj;
    private boolean az;
    private long aZ;
    private boolean ap;
    private int ar;
    public final NumberSetting tellyAirTicks;
    public final NumberSetting tellyRotationSpeed;
    private static final u_0 aJ;
    private int ao;
    public a ak;
    private boolean aO;
    public final NumberSetting R;
    private int aq;
    public final BooleanSetting Q;
    private int aT;
    public final ModeSetting V;
    private boolean aC;
    private static final u_0 aF;
    public final ModeSetting I;
    private String aB;
    private boolean aA;
    public int al;
    private long aP;
    public final BooleanSetting W;
    public final NumberSetting markAlpha;
    private r as;
    public final BooleanSetting L;
    private boolean aw;
    private static final u_0 aG;
    public final BooleanSetting P;
    public final NumberSetting tellyAirRotationSpeed;
    private int au;
    private static final u_0 aL;
    public final BooleanSetting S;
    private int aN;
    public final BooleanSetting M;
    private float a0;
    private int aR;
    private boolean a6;
    private ItemStack a2;
    private BlockPos ax;
    public final ModeSetting O;
    public final NumberSetting U;
    private long aD;
    private long a5;
    public final BooleanSetting X;
    public final NumberSetting markBlue;
    private float a1;
    public final NumberSetting Y;
    private int aS;
    private float aV;
    private int aU;
    private int aX;
    private final lf21.utils.a.f aM;
    private static final u_0 aH;
    public final NumberSetting K;
    public final NumberSetting T;
    private final CopyOnWriteArrayList<CopyOnWriteArrayList<Packet<?>>> av;
    public final BooleanSetting N;
    private int a4;
    public final NumberSetting Z;
    private static final String[] db;
    public final BooleanSetting tellyEagle;
    private int ay;
    private int aW;
    private static final u_0 aK;
    private float a3;
    public int am;
    private static final long aE;
    private static final u_0 aI;
    private long aQ;

    private void Y() {
        if (!this.aA) {
            return;
        }
        if (this.aB != null) {
            this.I.setValue(this.aB);
        }
        this.Q.setValue(this.aC);
        this.aA = false;
        this.aB = null;
        this.aD = 0L;
    }

    private InteractionHand V() {
        if (Scaffold.mc.player == null) {
            return null;
        }
        if (this.b(Scaffold.mc.player.getOffhandItem())) {
            return InteractionHand.OFF_HAND;
        }
        if (this.b(Scaffold.mc.player.getMainHandItem())) {
            return InteractionHand.MAIN_HAND;
        }
        return null;
    }

    public boolean y() {
        return !this.z();
    }

    private boolean b(ItemStack itemStack) {
        BlockItem blockItem;
        block3: {
            block2: {
                Item item;
                if (itemStack == null || itemStack.isEmpty() || !((item = itemStack.getItem()) instanceof BlockItem)) break block2;
                blockItem = (BlockItem)item;
                if (!itemStack.is(Items.TNT) && lf21.utils.game.a.a(itemStack)) break block3;
            }
            return false;
        }
        return blockItem.getBlock().defaultBlockState().isCollisionShapeFullBlock((BlockGetter)EmptyBlockGetter.INSTANCE, BlockPos.ZERO);
    }

    private a j(float f2) {
        if (this.as == null) {
            return null;
        }
        for (float f3 = 0.0f; f3 < 180.0f; f3 += 1.0f) {
            float f4 = this.aj.f();
            for (float f5 = 0.0f; f5 < 25.0f; f5 += 1.0f) {
                for (int i2 = 0; i2 < 2; ++i2) {
                    float f6 = f4 - f5 * (float)(i2 == 0 ? 1 : -1);
                    a a2 = new a(f2 + f3, f6 = Mth.clamp((float)f6, (float)-90.0f, (float)90.0f));
                    if (this.a(a2)) {
                        return a2;
                    }
                    a a3 = new a(f2 - f3, f6);
                    if (!this.a(a3)) continue;
                    return a3;
                }
            }
        }
        return null;
    }

    private boolean W() {
        if (this.as == null || Scaffold.mc.player == null) {
            return false;
        }
        a a2 = RotationHandler.a;
        if (a2 == null) {
            a2 = this.aj;
        }
        if (a2 == null) {
            return false;
        }
        return f.a(a2, this.as.b, this.as.a, true);
    }

    public int R() {
        if (Scaffold.mc.player == null) {
            return 0;
        }
        int n2 = 0;
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack = Scaffold.mc.player.getInventory().getItem(i2);
            if (!this.b(itemStack)) continue;
            n2 += itemStack.getCount();
        }
        ItemStack itemStack = Scaffold.mc.player.getOffhandItem();
        if (this.b(itemStack)) {
            n2 += itemStack.getCount();
        }
        return n2;
    }

    private boolean B() {
        String[] stringArray = db;
        return this.I.is("Normal") || this.I.is("Diagonal");
    }

    @Override
    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        int n2;
        boolean bl = Scaffold.mc.screen instanceof k;
        if ((Boolean)this.P.getValue() == false && !bl || Scaffold.mc.player == null && !bl || Scaffold.mc.level == null && !bl || Scaffold.mc.options.renderDebug && !bl) {
            return;
        }
        ItemStack itemStack = this.H();
        if (itemStack.isEmpty() && bl) {
            itemStack = new ItemStack((ItemLike)Items.WHITE_WOOL);
        }
        if (itemStack.isEmpty()) {
            return;
        }
        int n3 = bl ? 256 : this.T();
        int n4 = n2 = bl ? 64 : this.R();
        if (!this.a6) {
            this.a6 = true;
            if (this.getX() == 0.0f && this.getY() == 0.0f) {
                this.setX(((float)mc.getWindow().getGuiScaledWidth() - this.u()) * 0.5f);
                this.setY((float)mc.getWindow().getGuiScaledHeight() * 0.5f + 12.0f);
                f2 = this.getX();
                f3 = this.getY();
            }
        }
        this.a(render2DEvent, n3, n2, f2, f3, itemStack);
    }

    private void L() {
        Object object;
        if (Scaffold.mc.player == null || Scaffold.mc.level == null) {
            return;
        }
        Vec3 vec3 = Scaffold.mc.player.getEyePosition();
        if (!this.aw) {
            vec3 = Scaffold.mc.player.getEyePosition().add(Scaffold.mc.player.getDeltaMovement().multiply(2.0, 2.0, 2.0));
        }
        if (this.F() && Scaffold.mc.player.getDeltaMovement().y < 0.01) {
            object = new lf21.utils.game.k((Player)Scaffold.mc.player);
            ((lf21.utils.game.k)object).c(2);
            vec3 = new Vec3(vec3.x, Math.max(((lf21.utils.game.k)object).b + (double)Scaffold.mc.player.getEyeHeight(), vec3.y), vec3.z);
        }
        object = BlockPos.containing((double)vec3.x, (double)((float)this.al + 0.1f), (double)vec3.z);
        int n2 = object.getX();
        int n3 = object.getZ();
        if (Scaffold.mc.level.getBlockState((BlockPos)object).entityCanStandOn((BlockGetter)Scaffold.mc.level, (BlockPos)object, (Entity)Scaffold.mc.player)) {
            return;
        }
        if (this.a(vec3, (BlockPos)object)) {
            return;
        }
        for (int i2 = 1; i2 <= 6; ++i2) {
            if (this.a(vec3, new BlockPos(n2, this.al - i2, n3))) {
                return;
            }
            for (int i3 = 1; i3 <= i2; ++i3) {
                for (int i4 = 0; i4 <= i2 - i3; ++i4) {
                    int n4 = i2 - i3 - i4;
                    for (int i5 = 0; i5 <= 1; ++i5) {
                        for (int i6 = 0; i6 <= 1; ++i6) {
                            BlockPos blockPos = new BlockPos(n2 + (i5 == 0 ? i3 : -i3), this.al - n4, n3 + (i6 == 0 ? i4 : -i4));
                            if (!this.a(vec3, blockPos)) continue;
                            return;
                        }
                    }
                }
            }
        }
    }

    private void X() {
        String[] stringArray;
        boolean bl = this.aa();
        if (!this.y()) {
            this.Y();
            this.az = bl;
            return;
        }
        long l2 = System.currentTimeMillis();
        if (this.aA && l2 >= this.aD) {
            String string;
            this.Y();
            String string2 = (String)this.I.getValue();
            if (((Boolean)this.Q.getValue()).booleanValue()) {
                stringArray = db;
                string = "ON";
            } else {
                stringArray = db;
                string = "OFF";
            }
            stringArray = db;
            ChatUtil.print(true, "[Scaffold] Ctrl mode restored automatically: " + string2 + ", OnTickRot " + string);
        }
        if (bl && !this.az && Scaffold.mc.screen == null) {
            if (this.aA) {
                String string;
                this.Y();
                String string3 = (String)this.I.getValue();
                if (((Boolean)this.Q.getValue()).booleanValue()) {
                    stringArray = db;
                    string = "ON";
                } else {
                    stringArray = db;
                    string = "OFF";
                }
                stringArray = db;
                ChatUtil.print(true, "[Scaffold] Ctrl mode restored: " + string3 + ", OnTickRot " + string);
            } else if (this.A()) {
                String string;
                this.aB = (String)this.I.getValue();
                this.aC = (Boolean)this.Q.getValue();
                stringArray = db;
                this.I.setValue("Normal");
                this.Q.setValue(!this.aC);
                this.aA = true;
                this.aD = l2 + 3000L;
                this.Z();
                if (((Boolean)this.Q.getValue()).booleanValue()) {
                    stringArray = db;
                    string = "ON";
                } else {
                    stringArray = db;
                    string = "OFF";
                }
                stringArray = db;
                ChatUtil.print(true, "[Scaffold] Ctrl mode: Normal, OnTickRot " + string + " (3.0s)");
            }
        }
        this.az = bl;
    }

    private boolean E() {
        return this.y() && (Boolean)this.Q.getValue() != false;
    }

    private void b(Render2DEvent render2DEvent, int n2, float f2) {
        String string = n2 + " blocks";
        float f3 = aH.b(string);
        float f4 = 30.0f;
        float f5 = f4 - 10.0f;
        float f6 = 5.0f + f5 + 8.0f + f3 + 12.0f;
        float f7 = ((float)mc.getWindow().getGuiScaledWidth() - f6) * 0.5f;
        float f8 = (float)mc.getWindow().getGuiScaledHeight() * 0.5f + 12.0f;
        this.a0 = f7 + f6 * 0.5f;
        this.a1 = f8 + f4 * 0.5f;
        float f9 = f7 + 5.0f;
        float f10 = f8 + (f4 - f5) * 0.5f;
        float f11 = f9 + f5 + 8.0f;
        float f12 = f8 + f4 * 0.5f - aH.d().f() * 0.5f + 4.0f;
        float f13 = Mth.clamp((float)f2, (float)0.0f, (float)1.0f);
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(true);
                d2.a(-871361252);
                drawContext.a(lf21.render.Q.a(f7, f8, f6, f4, f4 * 0.5f), d2);
                d2.b(2.25f).a(h_0.STROKE).a(i_0.ROUND).a(1429789183);
                drawContext.a(f9, f10, f9 + f5, f10 + f5, -90.0f, 360.0f, false, d2);
                if (f13 > 0.001f) {
                    d2.a(-14362369);
                    drawContext.a(f9, f10, f9 + f5, f10 + f5, -90.0f, 360.0f * f13, false, d2);
                }
                d2.a(h_0.FILL).a(-1);
                drawContext.a(string, f11, f12, aH, d2);
            }
        });
    }

    private void O() {
        if (this.as == null || Scaffold.mc.player == null || Scaffold.mc.gameMode == null) {
            return;
        }
        Direction direction = this.as.b;
        if (direction == null) {
            return;
        }
        if (direction == Direction.UP && !Scaffold.mc.player.onGround() && e.a() && !Scaffold.mc.options.keyJump.isDown() && !this.B()) {
            return;
        }
        InteractionHand interactionHand = this.V();
        if (interactionHand == null) {
            return;
        }
        Vec3 vec3 = Scaffold.a(this.as.a, direction);
        a a2 = g.d(Scaffold.mc.player.getEyePosition(), vec3);
        if (!f.a(a2, direction, this.as.a, true)) {
            return;
        }
        float f2 = Scaffold.mc.player.getYRot();
        float f3 = Scaffold.mc.player.getXRot();
        mc.getConnection().send((Packet)new ServerboundMovePlayerPacket.PosRot(Scaffold.mc.player.getX(), Scaffold.mc.player.getY(), Scaffold.mc.player.getZ(), a2.e() + this.N(), a2.f() + this.N(), Scaffold.mc.player.onGround()));
        BlockHitResult blockHitResult = new BlockHitResult(Scaffold.b(this.as.a, direction), direction, this.as.a, false);
        InteractionResult interactionResult = Scaffold.mc.gameMode.useItemOn(Scaffold.mc.player, interactionHand, blockHitResult);
        if (interactionResult == InteractionResult.SUCCESS && this.G()) {
            Scaffold.mc.player.swing(interactionHand);
        }
    }

    private boolean A() {
        return this.I.is("Telly Bridge");
    }

    public Scaffold() {
        String[] stringArray = db;
        super("Scaffold", Category.MOVEMENT);
        this.I = new ModeSetting("Mode", "Normal", "Diagonal", "Telly Bridge", "Keep Y").withDefault("Normal");
        this.J = new NumberSetting("AirTicks", 1, 0, 3, 0.1, () -> this.A());
        this.K = new NumberSetting("PlaceDelay", 0, 0, 20, 1, () -> this.A());
        this.L = new BooleanSetting("Eagle", true, () -> this.B());
        this.M = new BooleanSetting("Render Item Spoof", true, () -> true);
        this.N = new BooleanSetting("Clutch", true, () -> !this.A());
        this.O = new ModeSetting("Swing", "Both", "Server").withDefault("Both").withVisibility(() -> !this.A());
        this.P = new BooleanSetting("Block Counter", true);
        this.Q = new BooleanSetting("OnTickRot", false, () -> !this.A());
        this.R = new NumberSetting("Rotation Speed", 180, 0, 360, 5, () -> !this.A() && (Boolean)this.S.getValue() == false);
        this.S = new BooleanSetting("Sync RotSpeed", false, () -> !this.A());
        this.T = new NumberSetting("Turn Speed", 75, 0, 360, 5, () -> !this.A() && (Boolean)this.S.getValue() != false);
        this.U = new NumberSetting("Return Speed", 120, 0, 360, 5, () -> !this.A() && (Boolean)this.S.getValue() != false);
        this.V = new ModeSetting("Switch Mode", "Normal", "Hotbar", "Full").withDefault("Hotbar").withVisibility(() -> !this.A());
        this.W = new BooleanSetting("Log", false, () -> !this.A());
        this.X = new BooleanSetting("Mark", true, () -> !this.A());
        this.Y = new NumberSetting("Mark Red", 255, 0, 255, 1, () -> !this.A() && (Boolean)this.X.getValue() != false);
        this.Z = new NumberSetting("Mark Green", 55, 0, 255, 1, () -> !this.A() && (Boolean)this.X.getValue() != false);
        this.markBlue = new NumberSetting("Mark Blue", 100, 0, 255, 1, () -> !this.A() && (Boolean)this.X.getValue() != false);
        this.markAlpha = new NumberSetting("Mark Alpha", 130, 0, 255, 1, () -> !this.A() && (Boolean)this.X.getValue() != false);
        this.tellyAirTicks = new NumberSetting("Telly AirTicks", 1, 1, 20, 1, () -> this.A());
        this.tellyEagle = new BooleanSetting("Telly Eagle", true, this::A);
        this.tellySwing = new BooleanSetting("Telly Swing", true, this::A);
        this.tellyMovementFix = new BooleanSetting("Telly Movement Fix", true, this::A);
        this.tellyRotationSpeed = new NumberSetting("Telly Rotation Speed", 180, 1, 180, 1, this::A);
        this.tellyAirRotationSpeed = new NumberSetting("Telly Air Rotation Speed", 180, 1, 180, 1, this::A);
        this.tellyPlaceAirTicks = new NumberSetting("Telly Place AirTicks", 1, 0, 5, 1, this::A);
        this.aj = new a();
        this.ak = new a();
        this.al = -1;
        this.am = 0;
        this.ao = -1;
        this.at = 0;
        this.au = 0;
        this.av = new CopyOnWriteArrayList();
        this.aM = new lf21.utils.a.f(300.0f, 1.0f, 25.0f, 1.0f);
        this.aR = -1;
        this.aS = -1;
        this.aT = -1;
        this.aU = -1;
        this.aV = 1.0f;
        this.aW = -1;
        this.aX = -1;
        this.aY = Float.NaN;
        this.a0 = Float.NaN;
        this.a1 = Float.NaN;
        this.a2 = ItemStack.EMPTY;
        this.a4 = 1;
        INSTANCE = this;
        this.e(118.5f);
        this.f(31.5f);
        this.a("BlockName");
        this.a("Count");
        this.b("Progress");
    }

    private int T() {
        return this.I();
    }

    private void c(Render2DEvent render2DEvent) {
        long l2 = Math.max(0L, this.aD - System.currentTimeMillis());
        float f2 = Mth.clamp((float)((float)l2 / 3000.0f), (float)0.0f, (float)1.0f);
        String string = this.S();
        float f3 = 33.0f + aH.b(string) + 12.0f;
        float f4 = ((float)mc.getWindow().getGuiScaledWidth() - f3) / 2.0f;
        float f5 = (float)mc.getWindow().getGuiScaledHeight() / 2.0f + 46.0f;
        float f6 = 4.0f;
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(true);
                d2.a(-871361252);
                drawContext.a(lf21.render.Q.a(f4, f5, f3, f6, 2.0f), d2);
                if (f2 > 0.001f) {
                    d2.a(-13703469);
                    drawContext.a(lf21.render.Q.a(f4, f5, f3 * f2, f6, 2.0f), d2);
                }
            }
        });
    }

    @EventTarget(a=1)
    public void a(TickEvent tickEvent) {
        InteractionHand interactionHand;
        if (Scaffold.mc.player == null) {
            return;
        }
        this.X();
        if (this.ap && this.aq != this.ar) {
            Scaffold.mc.player.getInventory().selected = this.ao;
            this.ap = false;
        }
        ++this.ar;
        this.av.add(new CopyOnWriteArrayList());
        if (this.am > 0) {
            --this.am;
        }
        if (Scaffold.mc.player.onGround() && this.am <= 30) {
            this.am = 0;
        }
        boolean bl = this.b(Scaffold.mc.player.getOffhandItem());
        int n2 = -1;
        if (!bl) {
            for (int i2 = 0; i2 < 9; ++i2) {
                ItemStack itemStack = Scaffold.mc.player.getInventory().getItem(i2);
                if (!this.b(itemStack)) continue;
                n2 = i2;
                break;
            }
        }
        Object object = bl ? InteractionHand.OFF_HAND : (interactionHand = n2 != -1 ? InteractionHand.MAIN_HAND : null);
        if (n2 != -1 && n2 != Scaffold.mc.player.getInventory().selected) {
            if (!this.ap) {
                this.ao = Scaffold.mc.player.getInventory().selected;
            }
            Scaffold.mc.player.getInventory().selected = n2;
            this.ap = true;
            this.aq = this.ar;
        }
        boolean bl2 = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)Scaffold.mc.options.keyJump.getKey().getValue());
        if (this.al == -1 || this.al > (int)Math.floor(Scaffold.mc.player.getY()) - 1 || Scaffold.mc.player.onGround() || !e.a() || bl2 || this.B()) {
            this.al = (int)Math.floor(Scaffold.mc.player.getY()) - 1;
        }
        this.L();
        this.ay = this.as == null ? 0 : ++this.ay;
        this.aw = true;
        if (this.as != null && interactionHand != null && this.F() && Scaffold.mc.player.getDeltaMovement().y < -0.1) {
            lf21.utils.game.k k2 = new lf21.utils.game.k((Player)Scaffold.mc.player);
            k2.c(2);
            if ((double)this.as.a.getY() > k2.b) {
                this.aw = false;
            }
        }
        if (Scaffold.mc.player.onGround()) {
            this.aw = true;
        }
        if (this.as == null) {
            ClientBase.delayPackets.clear();
        } else if (this.F() && (!this.aw || this.am > 0) && this.au <= 8) {
            boolean bl3;
            if (((Boolean)this.W.getValue()).booleanValue()) {
                String[] stringArray = db;
                ChatUtil.print(true, "\u00a76Skipped 1Tick\u00a76.");
            }
            boolean bl4 = bl3 = this.E() || !this.aw;
            if (bl3) {
                this.aj.a(Scaffold.mc.player.getYRot(), Scaffold.mc.player.getXRot());
                RotationHandler.a(this.aj);
                ++this.au;
            } else {
                Vec3 vec3 = Scaffold.a(this.as.a, this.as.b);
                Vec3 vec32 = new Vec3(Scaffold.mc.player.getX() + Scaffold.mc.player.getDeltaMovement().x, Scaffold.mc.player.getY() + (double)Scaffold.mc.player.getEyeHeight() + Scaffold.mc.player.getDeltaMovement().y, Scaffold.mc.player.getZ() + Scaffold.mc.player.getDeltaMovement().z);
                a a2 = g.a(vec3.x, vec3.y, vec3.z, vec32.x, vec32.y, vec32.z);
                a a3 = RotationHandler.a;
                this.aj.a(a2.e(), a2.f());
                RotationHandler.a(this.aj);
                ++this.au;
                ClientBase.delayPackets.add(() -> {});
                ClientBase.delayPackets.add(() -> {
                    RotationHandler.d.a(a2.e(), a2.f());
                    float f2 = a2.e();
                    if (f2 > -360.0f && f2 < 360.0f) {
                        f2 += 720.0f;
                    }
                    if (a3 != null) {
                        if (a3.e() != a2.e()) {
                            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Rot(f2, a2.f(), Scaffold.mc.player.onGround()));
                        }
                    } else {
                        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Rot(f2, a2.f(), Scaffold.mc.player.onGround()));
                    }
                    this.P();
                    this.a(tickEvent);
                });
            }
        } else {
            this.aw = true;
            boolean bl5 = this.au > 0;
            ClientBase.delayPackets.clear();
            this.au = 0;
            if (bl5) {
                mc.getConnection().send((Packet)new ServerboundMovePlayerPacket.PosRot(Scaffold.mc.player.getX(), Scaffold.mc.player.getY(), Scaffold.mc.player.getZ(), Scaffold.mc.player.getYRot() + this.N(), Scaffold.mc.player.getXRot() + this.N(), Scaffold.mc.player.onGround()));
            }
            if (this.E()) {
                this.aj.a(Scaffold.mc.player.getYRot(), Scaffold.mc.player.getXRot());
            } else {
                this.J();
            }
            if (this.A()) {
                Scaffold.mc.options.keyJump.setDown(e.a() || bl2);
                if (this.at < this.C() && e.a()) {
                    this.aj.a(Scaffold.mc.player.getYRot());
                    this.ak.a(this.aj.e(), this.aj.f());
                    return;
                }
            } else if (this.I.is("Keep Y")) {
                Scaffold.mc.options.keyJump.setDown(e.a() || bl2);
            } else if ((this.z() ? (Boolean)this.tellyEagle.getValue() : (Boolean)this.L.getValue()).booleanValue()) {
                Scaffold.mc.options.keyShift.setDown(Scaffold.mc.player.onGround() && Scaffold.k(0.3f));
            }
        }
        this.ak.a(this.aj.e(), this.aj.f());
    }

    private void Z() {
        if (mc == null || mc.getWindow() == null) {
            return;
        }
        boolean bl = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)Scaffold.mc.options.keyJump.getKey().getValue());
        Scaffold.mc.options.keyJump.setDown(bl);
    }

    public static boolean k(float f2) {
        if (Scaffold.mc.level == null || Scaffold.mc.player == null) {
            return false;
        }
        double d2 = (double)Scaffold.mc.player.getBbWidth() / 2.0;
        if ((double)f2 >= d2) {
            return true;
        }
        double d3 = Math.min((double)f2, d2 * 0.99);
        Vec3 vec3 = Scaffold.mc.player.position();
        for (int i2 = -1; i2 <= 1; i2 += 2) {
            for (int i3 = -1; i3 <= 1; i3 += 2) {
                BlockPos blockPos = BlockPos.containing((double)(vec3.x + (double)i2 * d3), (double)(vec3.y - 1.0), (double)(vec3.z + (double)i3 * d3));
                BlockState blockState = Scaffold.mc.level.getBlockState(blockPos);
                if (blockState.entityCanStandOn((BlockGetter)Scaffold.mc.level, blockPos, (Entity)Scaffold.mc.player)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean M() {
        if (Scaffold.mc.player == null || Scaffold.mc.level == null) {
            return false;
        }
        BlockPos blockPos = BlockPos.containing((double)Scaffold.mc.player.getX(), (double)(Scaffold.mc.player.getY() - 0.5), (double)Scaffold.mc.player.getZ());
        return Scaffold.mc.level.isEmptyBlock(blockPos) && this.V() != null;
    }

    @EventTarget
    public void a(PreMotionEvent preMotionEvent) {
        boolean bl;
        if (this.U()) {
            return;
        }
        preMotionEvent.a(true);
        if (Scaffold.mc.screen != null || Scaffold.mc.player == null || this.as == null) {
            return;
        }
        if (this.E() || !this.aw && this.au > 0) {
            if (this.A() && this.at < this.C()) {
                return;
            }
            if (this.A() && this.D() > 0 && this.ay < this.D()) {
                return;
            }
            this.O();
            return;
        }
        if (this.A() && this.at < this.C()) {
            return;
        }
        if (this.A() && this.D() > 0 && this.ay < this.D()) {
            return;
        }
        a a2 = RotationHandler.a != null ? RotationHandler.a : this.aj;
        boolean bl2 = bl = a2 != null && f.a(a2, this.as.b, this.as.a, true);
        if (!this.aw && !this.a(this.as)) {
            return;
        }
        if (this.au <= 0 && !this.A() && !bl) {
            return;
        }
        this.P();
    }

    @EventTarget
    public void b(Render2DEvent render2DEvent) {
        if (Scaffold.mc.player == null || Scaffold.mc.level == null) {
            return;
        }
        if (this.aA) {
            this.c(render2DEvent);
        }
    }

    private boolean U() {
        return Scaffold.mc.player != null && KillAura.j != null && KillAura.j.m();
    }

    private void c(float f2, float f3) {
        float f4;
        float f5;
        a a2;
        float f6;
        if (this.as == null || Scaffold.mc.player == null) {
            return;
        }
        if (this.z()) {
            f6 = this.A() && this.at < this.C() ? ((Number)this.tellyAirRotationSpeed.getValue()).floatValue() : ((Number)this.tellyRotationSpeed.getValue()).floatValue();
        } else if (((Boolean)this.S.getValue()).booleanValue()) {
            a2 = RotationHandler.a;
            if (a2 != null) {
                float f7 = Scaffold.mc.player.getYRot();
                f5 = Math.abs(Mth.wrapDegrees((float)(a2.e() - f7)));
                f4 = Math.abs(Mth.wrapDegrees((float)(f2 - f7)));
                f6 = f4 < f5 ? ((Number)this.U.getValue()).floatValue() : ((Number)this.T.getValue()).floatValue();
            } else {
                f6 = ((Number)this.T.getValue()).floatValue();
            }
        } else {
            f6 = ((Number)this.R.getValue()).floatValue();
        }
        a2 = new a(f2, f3);
        if (f6 < 360.0f) {
            a a3 = RotationHandler.a;
            if (a3 == null) {
                a3 = new a(Scaffold.mc.player.getYRot(), Scaffold.mc.player.getXRot());
            }
            f5 = Mth.wrapDegrees((float)(a2.e() - a3.e()));
            f4 = a2.f() - a3.f();
            float f8 = Mth.clamp((float)f5, (float)(-f6), (float)f6);
            float f9 = Mth.clamp((float)f4, (float)(-f6), (float)f6);
            a2 = new a(a3.e() + f8, a3.f() + f9);
        }
        this.aj.a(a2.e(), a2.f());
        RotationHandler.a(this.aj);
    }

    private int a(ItemStack itemStack) {
        if (itemStack.isEmpty() || Scaffold.mc.player == null) {
            return 0;
        }
        int n2 = 0;
        for (int i2 = 0; i2 < 36; ++i2) {
            ItemStack itemStack2 = Scaffold.mc.player.getInventory().getItem(i2);
            if (itemStack2.isEmpty() || itemStack2.getItem() != itemStack.getItem() || !this.b(itemStack2)) continue;
            n2 += itemStack2.getCount();
        }
        ItemStack itemStack3 = Scaffold.mc.player.getOffhandItem();
        if (!itemStack3.isEmpty() && itemStack3.getItem() == itemStack.getItem() && this.b(itemStack3)) {
            n2 += itemStack3.getCount();
        }
        return n2;
    }

    private boolean a(Vec3 vec3, BlockPos blockPos) {
        if (Scaffold.mc.level == null || Scaffold.mc.player == null) {
            return false;
        }
        if (!(Scaffold.mc.level.getBlockState(blockPos).getBlock() instanceof AirBlock)) {
            return false;
        }
        Vec3 vec32 = new Vec3((double)blockPos.getX() + 0.5, (double)((float)blockPos.getY() + 0.5f), (double)blockPos.getZ() + 0.5);
        for (Direction direction : Direction.values()) {
            Vec3 vec33;
            Vec3 vec34 = vec32.add(new Vec3((double)direction.getNormal().getX() * 0.5, (double)direction.getNormal().getY() * 0.5, (double)direction.getNormal().getZ() * 0.5));
            BlockPos blockPos2 = blockPos.offset(direction.getNormal());
            if (!Scaffold.mc.level.getBlockState(blockPos2).entityCanStandOnFace((BlockGetter)Scaffold.mc.level, blockPos2, (Entity)Scaffold.mc.player, direction) || !((vec33 = vec34.subtract(vec3)).lengthSqr() <= 20.25) || !(vec33.normalize().dot(Vec3.atLowerCornerOf((Vec3i)direction.getNormal()).normalize()) >= 0.0)) continue;
            this.as = new r(new BlockPos(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()), direction.getOpposite());
            return true;
        }
        return false;
    }

    private void K() {
        float[] fArray;
        float f2 = Scaffold.mc.player.input.forwardImpulse;
        float f3 = Scaffold.mc.player.input.leftImpulse;
        float f4 = f2 == 0.0f && f3 == 0.0f ? Scaffold.mc.player.getYRot() : (float)Math.toDegrees(e.a(Scaffold.mc.player.getYRot(), (double)f2, (double)f3));
        float f5 = Mth.wrapDegrees((float)(f4 - 180.0f));
        a a2 = RotationHandler.a != null ? RotationHandler.a : new a(Scaffold.mc.player.getYRot(), Scaffold.mc.player.getXRot());
        a a3 = null;
        double d2 = Double.POSITIVE_INFINITY;
        for (float f6 : fArray = new float[]{0.0f, -45.0f, 45.0f, -90.0f, 90.0f}) {
            float f7 = Mth.wrapDegrees((float)(f5 + f6));
            for (float f8 = 70.0f; f8 <= 89.5f; f8 += 0.25f) {
                double d3;
                a a4 = new a(f7, f8);
                if (!this.a(a4) || !((d3 = (double)Math.abs(Mth.wrapDegrees((float)(f7 - a2.e()))) + (double)Math.abs(f8 - 75.5f) * 0.35) < d2)) continue;
                a3 = a4;
                d2 = d3;
            }
        }
        if (a3 == null) {
            Vec3 vec3 = Scaffold.b(this.as.a, this.as.b);
            a3 = g.d(Scaffold.mc.player.getEyePosition(), vec3);
        }
        this.c(a3.e(), a3.f());
    }

    private boolean a(r r2) {
        if (r2 == null || Scaffold.mc.player == null) {
            return false;
        }
        Vec3 vec3 = new Vec3((double)r2.a.getX() + 0.5, (double)((float)r2.a.getY() + 0.5f), (double)r2.a.getZ() + 0.5);
        Vec3 vec32 = vec3.add(new Vec3((double)r2.b.getNormal().getX() * 0.5, (double)r2.b.getNormal().getY() * 0.5, (double)r2.b.getNormal().getZ() * 0.5));
        a a2 = RotationHandler.a;
        if (a2 == null) {
            return false;
        }
        Vec3 vec33 = Scaffold.mc.player.getEyePosition();
        Vec3 vec34 = vec32.subtract(vec33);
        return vec34.lengthSqr() <= 20.25 && vec34.normalize().dot(Vec3.atLowerCornerOf((Vec3i)r2.b.getNormal().multiply(-1)).normalize()) >= 0.0;
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ '\u0001');
        }
        return cArray;
    }

    public int I() {
        if (Scaffold.mc.player == null) {
            return 0;
        }
        int n2 = 0;
        for (int i2 = 0; i2 < 36; ++i2) {
            ItemStack itemStack = Scaffold.mc.player.getInventory().getItem(i2);
            if (!this.b(itemStack)) continue;
            n2 += itemStack.getCount();
        }
        ItemStack itemStack = Scaffold.mc.player.getOffhandItem();
        if (this.b(itemStack)) {
            n2 += itemStack.getCount();
        }
        return n2;
    }

    @Override
    public void a() {
    }

    @EventTarget
    public void a(PacketEvent packetEvent) {
        double d2;
        ClientboundSetEntityMotionPacket clientboundSetEntityMotionPacket;
        if (Scaffold.mc.player == null || Scaffold.mc.level == null) {
            return;
        }
        Packet<?> packet = packetEvent.c();
        if (packet instanceof ClientboundSetEntityMotionPacket && (clientboundSetEntityMotionPacket = (ClientboundSetEntityMotionPacket)packet).getId() == Scaffold.mc.player.getId() && (d2 = new Vec3((double)clientboundSetEntityMotionPacket.getXa() / 8000.0, 0.0, (double)clientboundSetEntityMotionPacket.getZa() / 8000.0).length()) >= 1.5) {
            this.am = 60;
        }
    }

    private ItemStack Q() {
        if (Scaffold.mc.player == null) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = Scaffold.mc.player.getOffhandItem();
        if (this.b(itemStack)) {
            return itemStack;
        }
        ItemStack itemStack2 = Scaffold.mc.player.getInventory().getSelected();
        if (this.b(itemStack2)) {
            return itemStack2;
        }
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack3 = Scaffold.mc.player.getInventory().getItem(i2);
            if (!this.b(itemStack3)) continue;
            return itemStack3;
        }
        return ItemStack.EMPTY;
    }

    private int D() {
        return this.A() ? ((Number)this.tellyPlaceAirTicks.getValue()).intValue() : ((Number)this.K.getValue()).intValue();
    }

    public static Vec3 b(BlockPos blockPos, Direction direction) {
        double d2 = ThreadLocalRandom.current().nextDouble(0.2, 0.8);
        double d3 = ThreadLocalRandom.current().nextDouble(0.2, 0.8);
        double d4 = (double)blockPos.getX() + 0.5;
        double d5 = (double)blockPos.getY() + 0.5;
        double d6 = (double)blockPos.getZ() + 0.5;
        if (direction.getAxis() == Direction.Axis.X) {
            d4 = (double)blockPos.getX() + (direction.getStepX() > 0 ? 1.0 : 0.0);
            d5 = (double)blockPos.getY() + d2;
            d6 = (double)blockPos.getZ() + d3;
        } else if (direction.getAxis() == Direction.Axis.Y) {
            d5 = (double)blockPos.getY() + (direction.getStepY() > 0 ? 1.0 : 0.0);
            d4 = (double)blockPos.getX() + d2;
            d6 = (double)blockPos.getZ() + d3;
        } else {
            d6 = (double)blockPos.getZ() + (direction.getStepZ() > 0 ? 1.0 : 0.0);
            d4 = (double)blockPos.getX() + d2;
            d5 = (double)blockPos.getY() + d3;
        }
        return new Vec3(d4, d5, d6);
    }

    private Float i(float f2) {
        if (this.as == null) {
            return null;
        }
        float f3 = this.aj.f();
        for (float f4 = Math.max(f3 - 30.0f, -90.0f); f4 <= Math.min(f3 + 20.0f, 90.0f); f4 += 0.3f) {
            a a2 = new a(f2, f4);
            if (!this.a(a2)) continue;
            return Float.valueOf(f4);
        }
        return null;
    }

    private void a(Render2DEvent render2DEvent, int n2, int n3, float f2, float f3, ItemStack itemStack) {
        long l2 = System.nanoTime();
        if (this.aQ == 0L) {
            this.aQ = l2;
        }
        float f4 = Math.min((float)(l2 - this.aQ) / 1.0E9f, 0.05f);
        this.aQ = l2;
        if (this.aT < 0) {
            this.aR = this.aS = n3;
            this.aT = this.aU = n2;
            this.aV = 1.0f;
        } else if (n3 != this.aR || n2 != this.aT) {
            this.aS = this.aR;
            this.aU = this.aT;
            this.aW = n3 >= this.aR ? -1 : 1;
            this.aX = n2 >= this.aT ? -1 : 1;
            this.aR = n3;
            this.aT = n2;
            this.aV = 0.0f;
        }
        this.aV = Math.min(1.0f, this.aV + f4 * 8.5f);
        float f5 = 1.0f - (float)Math.pow(1.0f - this.aV, 3.0);
        String string = Integer.toString(this.aR);
        String string2 = Integer.toString(this.aS);
        String string3 = Integer.toString(this.aT);
        String string4 = Integer.toString(this.aU);
        float f6 = Mth.lerp((float)f5, (float)aH.b(string2), (float)aH.b(string));
        float f7 = Mth.lerp((float)f5, (float)aH.b(string4), (float)aH.b(string3));
        float f8 = aH.b("/");
        float f9 = aH.b("blocks");
        float f10 = 42.0f + f6 + f8 + f7 + f9;
        if (Float.isNaN(this.aY)) {
            this.aY = f10;
        }
        float f11 = 1.0f - (float)Math.exp(-13.5f * f4);
        this.aY += (f10 - this.aY) * f11;
        if (Math.abs(this.aY - f10) < 0.05f) {
            this.aY = f10;
        }
        float f12 = 26.0f;
        float f13 = this.aY;
        this.e(f13);
        this.f(f12);
        RenderUtil.a(render2DEvent.a(), f2, f3, f13, f12, 9.0f, 26.0f, 1.0f, 0);
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(true);
                d2.a(-1291845632);
                drawContext.a(lf21.render.Q.a(f2, f3, f13, f12, 9.0f), d2);
            }
        });
        if (!itemStack.isEmpty()) {
            render2DEvent.b().renderItem(itemStack, Math.round(f2 + 7.0f), Math.round(f3 + 5.0f));
        }
        float f14 = f2 + 33.0f;
        float f15 = f3 + (f12 - aH.d().f()) * 0.5f + 5.0f;
        float f16 = Math.max(12.0f, aH.d().f() + 3.0f);
        float f17 = f5;
        boolean bl = this.aV < 1.0f;
        boolean bl2 = this.aR != this.aS;
        boolean bl3 = this.aT != this.aU;
        float f18 = f14 + f6;
        float f19 = f18 + f8;
        float f20 = f19 + f7;
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(-789001);
                drawContext.d();
                float f17 = f2 + 25.0f;
                drawContext.a(ab.a(f17, f3, f13 - (f17 - f2) - 2.0f, f12));
                this.a((DrawContext)drawContext, d2, string, string2, f14, f15, f17, f16, this.aW, bl && bl2);
                drawContext.a("/", f18, f15, aH, d2);
                this.a((DrawContext)drawContext, d2, string3, string4, f19, f15, f17, f16, this.aX, bl && bl3);
                drawContext.a("blocks", f20, f15, aH, d2);
                drawContext.e();
                return;
            }
        });
    }

    private void b(List<Packet<?>> list) {
        list.forEach(packet -> {
            list.remove(packet);
            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)packet);
        });
    }

    private void a(DrawContext drawContext, D d2, String string, String string2, float f2, float f3, float f4, float f5, int n2, boolean bl) {
        float f6 = f2;
        int n3 = string2.length() - string.length();
        for (int i2 = 0; i2 < string.length(); ++i2) {
            String string3;
            String string4 = string.substring(i2, i2 + 1);
            int n4 = i2 + n3;
            String string5 = string3 = n4 >= 0 && n4 < string2.length() ? string2.substring(n4, n4 + 1) : "";
            if (bl && !string4.equals(string3)) {
                if (!string3.isEmpty()) {
                    drawContext.a(string3, f6, f3 + (float)n2 * f4 * f5, aH, d2);
                }
                drawContext.a(string4, f6, f3 - (float)n2 * (1.0f - f4) * f5, aH, d2);
            } else {
                drawContext.a(string4, f6, f3, aH, d2);
            }
            f6 += aH.b(string4);
        }
    }

    @Override
    protected float q() {
        return super.q();
    }

    private void P() {
        if (this.as == null || Scaffold.mc.player == null || Scaffold.mc.gameMode == null) {
            return;
        }
        InteractionHand interactionHand = this.V();
        if (interactionHand == null) {
            return;
        }
        Direction direction = this.as.b;
        boolean bl = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)Scaffold.mc.options.keyJump.getKey().getValue());
        if (direction == null) {
            return;
        }
        if (direction == Direction.UP && !Scaffold.mc.player.onGround() && e.a() && !bl && !this.B()) {
            return;
        }
        if (!this.M()) {
            return;
        }
        if (!this.W()) {
            return;
        }
        BlockHitResult blockHitResult = new BlockHitResult(Scaffold.b(this.as.a, direction), direction, this.as.a, false);
        InteractionResult interactionResult = Scaffold.mc.gameMode.useItemOn(Scaffold.mc.player, interactionHand, blockHitResult);
        if (interactionResult == InteractionResult.SUCCESS && this.G()) {
            Scaffold.mc.player.swing(interactionHand);
        }
    }

    @EventTarget
    public void a(UpdateHeldItemEvent updateHeldItemEvent) {
        if (updateHeldItemEvent.a() == InteractionHand.MAIN_HAND && Scaffold.mc.player != null) {
            boolean bl;
            boolean bl2 = bl = this.y() && this.ap && !this.V.is("Normal");
            if ((bl || ((Boolean)this.M.getValue()).booleanValue()) && this.ap && this.ao >= 0) {
                updateHeldItemEvent.a(Scaffold.mc.player.getInventory().getItem(this.ao));
            }
        }
    }

    @Override
    public void onEnable() {
        this.aA = false;
        this.aB = null;
        this.aD = 0L;
        this.az = this.aa();
        if (Scaffold.mc.player != null) {
            this.ao = this.an = Scaffold.mc.player.getInventory().selected;
            this.ap = false;
            this.aq = 0;
            this.ar = 0;
            this.aj.a(Scaffold.mc.player.getYRot() - 180.0f, Scaffold.mc.player.getXRot());
            this.ak.a(Scaffold.mc.player.yRotO - 180.0f, Scaffold.mc.player.xRotO);
            this.as = null;
            this.al = 10000;
            this.am = 0;
            this.aw = true;
            this.ax = null;
            this.av.clear();
            this.av.add(new CopyOnWriteArrayList());
        }
        this.aO = Scaffold.mc.player != null;
        this.aN = this.aO ? this.T() : 0;
        this.aM.a(0.0f);
        this.aP = 0L;
        this.aQ = 0L;
        this.aR = -1;
        this.aS = -1;
        this.aT = -1;
        this.aU = -1;
        this.aV = 1.0f;
        this.aY = Float.NaN;
        this.aZ = System.nanoTime();
        super.onEnable();
    }

    private boolean aa() {
        if (mc == null || mc.getWindow() == null) {
            return false;
        }
        long l2 = mc.getWindow().getWindow();
        return InputConstants.isKeyDown((long)l2, (int)341) || InputConstants.isKeyDown((long)l2, (int)345);
    }

    private int C() {
        return this.A() ? ((Number)this.tellyAirTicks.getValue()).intValue() : ((Number)this.J.getValue()).intValue();
    }

    private void J() {
        if (this.as == null || Scaffold.mc.player == null) {
            return;
        }
        if (this.z()) {
            this.K();
            return;
        }
        float f2 = Scaffold.mc.player.getYRot();
        if (Scaffold.mc.options.keyDown.isDown()) {
            f2 += 180.0f;
            if (Scaffold.mc.options.keyLeft.isDown()) {
                f2 += 45.0f;
            } else if (Scaffold.mc.options.keyRight.isDown()) {
                f2 -= 45.0f;
            }
        } else if (Scaffold.mc.options.keyUp.isDown()) {
            if (Scaffold.mc.options.keyLeft.isDown()) {
                f2 -= 45.0f;
            } else if (Scaffold.mc.options.keyRight.isDown()) {
                f2 += 45.0f;
            }
        } else if (Scaffold.mc.options.keyRight.isDown()) {
            f2 += 90.0f;
        } else if (Scaffold.mc.options.keyLeft.isDown()) {
            f2 -= 90.0f;
        }
        float f3 = f2 - 180.0f;
        float f4 = 75.5f;
        if (this.I.is("Diagonal") && e.a()) {
            this.b(f3, f4);
            return;
        }
        a a2 = new a(f3, f4);
        if (this.a(a2)) {
            this.c(f3, f4);
            return;
        }
        Float f5 = this.i(f3);
        if (f5 != null) {
            this.c(f3, f5.floatValue());
            return;
        }
        a a3 = this.j(f3);
        if (a3 != null) {
            this.c(a3.e(), a3.f());
            return;
        }
        this.c(f3, f4);
    }

    @EventTarget
    public void a(MotionEvent motionEvent) {
        if (motionEvent.isPost() && Scaffold.mc.player != null) {
            this.at = Scaffold.mc.player.onGround() ? 0 : ++this.at;
        }
    }

    @Override
    public void a(GlRenderEvent glRenderEvent, float f2, float f3) {
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
                    case 0 -> 41;
                    case 1 -> 68;
                    case 2 -> 73;
                    case 3 -> 45;
                    case 4 -> 60;
                    case 5 -> 55;
                    default -> 1;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void d(Render2DEvent render2DEvent) {
        if (Scaffold.mc.player == null) {
            return;
        }
        if (!this.aO) {
            this.aN = this.T();
            this.aO = true;
            this.aM.a(0.0f);
            this.aP = 0L;
        }
        int n2 = this.T();
        float f2 = Mth.clamp((float)((float)n2 / (float)Math.max(this.aN, 64)), (float)0.0f, (float)1.0f);
        long l2 = System.currentTimeMillis();
        if (this.aP == 0L) {
            this.aP = l2;
        }
        float f3 = Math.min((float)(l2 - this.aP) / 1000.0f, 0.05f);
        this.aP = l2;
        this.aM.d(f2);
        this.aM.b(f3);
        float f4 = Mth.clamp((float)this.aM.a(), (float)0.0f, (float)1.0f);
        this.a(render2DEvent, n2, f4);
    }

    @Override
    protected float p() {
        return super.p();
    }

    public boolean z() {
        return this.I.is("Telly Bridge");
    }

    private boolean a(a a2) {
        if (this.as == null) {
            return false;
        }
        return f.a(a2, this.as.b, this.as.a, true);
    }

    private void a(Render2DEvent render2DEvent, int n2) {
        String string = "Blocks: " + this.S();
        u_0 u_02 = t_0.f(15.0f);
        float f2 = u_02.b(string);
        float f3 = mc.getWindow().getGuiScaledWidth();
        float f4 = mc.getWindow().getGuiScaledHeight();
        float f5 = f2 + 12.0f;
        float f6 = 18.0f;
        float f7 = f3 / 2.0f - f2 / 2.0f - 5.0f;
        float f8 = f4 / 2.0f + 30.0f;
        int n3 = 190;
        float f9 = 13.0f;
        int n4 = n3 << 24 | 0;
        int n5 = n3 << 24 | 0x962D2D;
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(n4);
                drawContext.a(lf21.render.Q.a(f7, f8, f5, f6, 5.0f), d2);
                drawContext.d();
                drawContext.a(ab.a(f7, f8, f5, 3.0f));
                d2.a(n5);
                drawContext.a(lf21.render.Q.a(f7, f8, f5, f6, 5.0f), d2);
                drawContext.e();
                d2.a(-1);
                drawContext.a(string, f7 + 5.0f, f8 + f9, u_02, d2);
            }
        });
    }

    private float N() {
        float f2 = (float)(Math.random() * 0.04 + 0.03);
        return Math.random() > 0.5 ? f2 : -f2;
    }

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (!this.y() || Scaffold.mc.player == null || Scaffold.mc.level == null || !((Boolean)this.X.getValue()).booleanValue() || this.as == null) {
            return;
        }
        PoseStack poseStack = renderEvent.a();
        poseStack.pushPose();
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        Tesselator tesselator = RenderSystem.renderThreadTesselator();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        RenderSystem.setShaderColor((float)(((Number)this.Y.getValue()).floatValue() / 255.0f), (float)(((Number)this.Z.getValue()).floatValue() / 255.0f), (float)(((Number)this.markBlue.getValue()).floatValue() / 255.0f), (float)(((Number)this.markAlpha.getValue()).floatValue() / 255.0f));
        RenderUtil.a(bufferBuilder, poseStack.last().pose(), new AABB(this.as.a.relative(this.as.b)));
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        poseStack.popPose();
    }

    private boolean F() {
        return this.z() || (Boolean)this.N.getValue() != false;
    }

    private void b(float f2, float f3) {
        if (Scaffold.mc.player == null) {
            return;
        }
        double d2 = Math.toRadians(f2);
        boolean bl = Math.floor(Scaffold.mc.player.getX() + Math.cos(d2) * 0.5) != Math.floor(Scaffold.mc.player.getX()) || Math.floor(Scaffold.mc.player.getZ() + Math.sin(d2) * 0.5) != Math.floor(Scaffold.mc.player.getZ());
        float f4 = bl ? 45.0f : -45.0f;
        float f5 = Mth.wrapDegrees((float)(f2 + f4));
        if (this.a(new a(f5, f3))) {
            this.c(f5, f3);
            return;
        }
        float f6 = Mth.wrapDegrees((float)(f2 - f4));
        if (this.a(new a(f6, f3))) {
            this.c(f6, f3);
            return;
        }
        Float f7 = this.i(f5);
        if (f7 != null) {
            this.c(f5, f7.floatValue());
            return;
        }
        a a2 = this.j(f5);
        if (a2 != null) {
            this.c(a2.e(), a2.f());
            return;
        }
        this.c(f5, f3);
    }

    @Override
    public void onDisable() {
        this.Y();
        this.az = false;
        if (mc != null && Scaffold.mc.player != null) {
            this.av.forEach((Consumer<CopyOnWriteArrayList<Packet<?>>>)((Consumer<CopyOnWriteArrayList>)this::b));
            this.av.clear();
            boolean bl = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)Scaffold.mc.options.keyJump.getKey().getValue());
            boolean bl2 = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)Scaffold.mc.options.keyShift.getKey().getValue());
            Scaffold.mc.options.keyJump.setDown(bl);
            Scaffold.mc.options.keyShift.setDown(bl2);
            Scaffold.mc.options.keyUse.setDown(false);
            if (this.ap) {
                Scaffold.mc.player.getInventory().selected = this.ao;
                this.ap = false;
            }
            this.aw = true;
            this.ax = null;
            ClientBase.delayPackets.clear();
        }
        super.onDisable();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                Scaffold.aE = 3000L;
                var5 = new String[63];
                var3_1 = 0;
                var2_2 = "K(&NWD\u000b}!%AE\u0017DH#%H\rz='N\u001cen]\u00179HYS\nd%;F\u001cpsL!'\nd%;F\u001cvmY,(\u0007\t&%B_\\r\ny((NYsdE%0\bm-(JSY`E\tf*\u001dD_\\SF0\u0006g+;@][\bm-(JSY`E\u0007\tlz\u0003\fD(\u0004d+-H\bd%;F\u001cedM\u0006a+=O]E\f\u0005d\u0006Ch^bB\u0016&Y\u001c\u0006g+;@][\u000b}!%AE\u0017R^-'J\u0006b!,]\u001cn\u0003e+.\u0004k+=E\u0014}!%AE\u0017SF0(YUXo\t\u00179HYS\u0006z!;[YE\u0006K(&NWD\u000bz3 Y__!d+-H\u0006z!;[YE\u000e{+=LH^nGd\u001a]YRe\u0005j+<CH\b\fjxK\u001cU.Z\u0002f\n\f}!%AE\u0017C[--JY\by6&JNRrZ\u0011{!'IYE!`0,@\u001cdqF+/\u0018}!%AE\u0017@@6i\u007fSC`]-&C\u001cdqL!-\tk(&NWy`D!\u0012}!%AE\u0017LF2,@YYu\t\u0002 U\rk(&NW\u0017BF1'YYE\u0012\u008er\u001aFUGqL i\u001ch^bB\u00e3\u007f\u0003\u0014}!%AE\u0017QE%*H\u001cvh[\u0010 NWD\f\u0005d\u0006Ch^bB\u0016&Y\u001c\u001fr\u0017*LZQnE \u0014\r\u007fCsEd$BXR![!:YSEdM~i\bz'(KZXmM(r\u0017*LZQnE \u0014\r\u007fCsEd$BXR;\t\n&_QVm\u0005d\u0006Ch^bB\u0016&Y\u001c\bk(&NWD;\t\bh-;yUTjZ\u000e}!%AE\u0017@@6\u001dD_\\r\n}1;C\u001cdqL!-\td%;F\u001cum\\!\u0005z3 C[\f{!=XNY!z4,HX\u0005l%.AY\u0002f\n\u0004o1%A\u0004d%;F\bz'(KZXmM\u0006a+=O]E\u0003f\u0002\u000f\u0003f\u0002\u000f\u0006j(<Y__-r\u0017*LZQnE \u0014\r\u007fCsEd$BXR![!:YSEdMd(XHXlH0 N][mP~i\f}!%AE\u0017C[--JY";
                var4_3 = "K(&NWD\u000b}!%AE\u0017DH#%H\rz='N\u001cen]\u00179HYS\nd%;F\u001cpsL!'\nd%;F\u001cvmY,(\u0007\t&%B_\\r\ny((NYsdE%0\bm-(JSY`E\tf*\u001dD_\\SF0\u0006g+;@][\bm-(JSY`E\u0007\tlz\u0003\fD(\u0004d+-H\bd%;F\u001cedM\u0006a+=O]E\f\u0005d\u0006Ch^bB\u0016&Y\u001c\u0006g+;@][\u000b}!%AE\u0017R^-'J\u0006b!,]\u001cn\u0003e+.\u0004k+=E\u0014}!%AE\u0017SF0(YUXo\t\u00179HYS\u0006z!;[YE\u0006K(&NWD\u000bz3 Y__!d+-H\u0006z!;[YE\u000e{+=LH^nGd\u001a]YRe\u0005j+<CH\b\fjxK\u001cU.Z\u0002f\n\f}!%AE\u0017C[--JY\by6&JNRrZ\u0011{!'IYE!`0,@\u001cdqF+/\u0018}!%AE\u0017@@6i\u007fSC`]-&C\u001cdqL!-\tk(&NWy`D!\u0012}!%AE\u0017LF2,@YYu\t\u0002 U\rk(&NW\u0017BF1'YYE\u0012\u008er\u001aFUGqL i\u001ch^bB\u00e3\u007f\u0003\u0014}!%AE\u0017QE%*H\u001cvh[\u0010 NWD\f\u0005d\u0006Ch^bB\u0016&Y\u001c\u001fr\u0017*LZQnE \u0014\r\u007fCsEd$BXR![!:YSEdM~i\bz'(KZXmM(r\u0017*LZQnE \u0014\r\u007fCsEd$BXR;\t\n&_QVm\u0005d\u0006Ch^bB\u0016&Y\u001c\bk(&NWD;\t\bh-;yUTjZ\u000e}!%AE\u0017@@6\u001dD_\\r\n}1;C\u001cdqL!-\td%;F\u001cum\\!\u0005z3 C[\f{!=XNY!z4,HX\u0005l%.AY\u0002f\n\u0004o1%A\u0004d%;F\bz'(KZXmM\u0006a+=O]E\u0003f\u0002\u000f\u0003f\u0002\u000f\u0006j(<Y__-r\u0017*LZQnE \u0014\r\u007fCsEd$BXR![!:YSEdMd(XHXlH0 N][mP~i\f}!%AE\u0017C[--JY".length();
                var1_4 = 6;
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
                    var5[var3_1++] = Scaffold.d(Scaffold.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "b!,]\u001cn\u0004k+=E";
                    var4_3 = "b!,]\u001cn\u0004k+=E".length();
                    var1_4 = 6;
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
                    var5[var3_1++] = Scaffold.d(Scaffold.c(v1));
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
        Scaffold.db = var5;
        Scaffold.aF = t_0.k(12.0f);
        Scaffold.aG = t_0.k(13.0f);
        Scaffold.aH = t_0.a(15.6f);
        Scaffold.aI = t_0.a(19.0f);
        Scaffold.aJ = t_0.a(18.0f);
        Scaffold.aK = t_0.a(9.5f);
        Scaffold.aL = t_0.a(9.0f);
    }

    private String S() {
        return this.R() + "/" + this.I() + "blocks";
    }

    private void a(Render2DEvent render2DEvent, int n2, float f2) {
        String[] stringArray = db;
        String string = "Scaffold";
        String string2 = String.format("%.1f b/s", e.h());
        String string3 = this.S();
        float f3 = aG.b(string2);
        float f4 = aH.b(string3);
        float f5 = mc.getWindow().getGuiScaledWidth();
        float f6 = mc.getWindow().getGuiScaledHeight();
        float f7 = 115.0f;
        float f8 = 2.0f;
        float f9 = (f5 - f7) / 2.0f;
        float f10 = f6 / 2.0f + 12.0f;
        float f11 = f9 + f7 - f4;
        float f12 = f11 - 10.0f - f3;
        float f13 = f10 - 3.0f;
        Renderer.render(render2DEvent.b(), drawContext -> {
            try (D d2 = new D();){
                d2.a(-1);
                drawContext.a(string, f9, f13 + 1.0f, aF, d2);
                drawContext.a(string3, f11, f13, aH, d2);
                drawContext.a(string2, f12, f13, aG, d2);
                d2.a(-2144128205);
                drawContext.a(lf21.render.Q.a(f9, f10, f7, f8, 2.0f), d2);
                if (f2 > 0.01f) {
                    drawContext.d();
                    drawContext.a(ab.a(f9, f10, f7 * f2, f8));
                    d2.a(-1);
                    drawContext.a(lf21.render.Q.a(f9, f10, f7, f8, 1.0f), d2);
                    drawContext.e();
                }
            }
        });
    }

    private ItemStack H() {
        if (Scaffold.mc.player == null) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = Scaffold.mc.player.getOffhandItem();
        if (this.b(itemStack)) {
            return itemStack.copyWithCount(1);
        }
        ItemStack itemStack2 = Scaffold.mc.player.getInventory().getSelected();
        if (this.b(itemStack2)) {
            return itemStack2.copyWithCount(1);
        }
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack itemStack3 = Scaffold.mc.player.getInventory().getItem(i2);
            if (!this.b(itemStack3)) continue;
            return itemStack3.copyWithCount(1);
        }
        return ItemStack.EMPTY;
    }

    @EventTarget
    public void a(JumpEvent jumpEvent) {
        if (!this.aw && this.as != null && this.au > 0) {
            jumpEvent.a(true);
        }
    }

    private boolean G() {
        return this.z() ? (Boolean)this.tellySwing.getValue() : !this.O.is("Server");
    }

    public static Vec3 a(BlockPos blockPos, Direction direction) {
        return new Vec3((double)blockPos.getX() + 0.5 + (double)direction.getNormal().getX() * 0.5, (double)blockPos.getY() + 0.5 + (double)direction.getNormal().getY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)direction.getNormal().getZ() * 0.5);
    }
}

