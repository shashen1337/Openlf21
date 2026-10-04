/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import com.mojang.blaze3d.platform.InputConstants;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.time.Instant;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import lf21.event.EventTarget;
import lf21.event.impl.KeyEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PacketEvent;
import lf21.event.impl.StrafeEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.AutoMLG;
import lf21.modules.impl.player.f;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.utils.game.d;
import lf21.utils.misc.ChatUtil;
import lf21.utils.misc.PacketUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundAcceptTeleportationPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class NoFall
extends Module {
    private boolean M;
    private int u;
    private int H;
    private int ak;
    private int K;
    private double B;
    private f x;
    private long ab;
    private float am;
    private int N;
    private static final int j;
    private boolean J;
    private float an;
    private final BooleanSetting prediction;
    public boolean r;
    private Path Z;
    private BufferedWriter Y;
    private boolean C;
    private boolean ad;
    private final Object X;
    private long al;
    private int v;
    private static final String[] cb;
    public static NoFall INSTANCE;
    private boolean V;
    private boolean R;
    private int y;
    private int O;
    private int T;
    private boolean P;
    private final ModeSetting mode;
    private static final int k;
    private boolean Q;
    private boolean I;
    private final BooleanSetting debugger;
    public boolean t;
    private final BooleanSetting autoMLGAutoDisable;
    private int U;
    private boolean aa;
    private boolean F;
    private float af;
    private boolean G;
    private float A;
    private long ac;
    private boolean S;
    private boolean E;
    private String z;
    private boolean q;
    private boolean w;
    private boolean D;
    private boolean ae;
    private boolean ah;
    private boolean p;
    private final Queue<Packet<ServerGamePacketListener>> W;
    private float ai;
    private String ao;
    private double ag;
    private int L;
    private boolean aj;
    private boolean s;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventTarget
    public void a(MotionEvent motionEvent) {
        String[] stringArray;
        block45: {
            block44: {
                if (NoFall.mc.player == null) {
                    return;
                }
                if (this.V) {
                    return;
                }
                stringArray = cb;
                this.a("BEFORE", motionEvent);
                try {
                    if (NoFall.mc.level == null || !this.z()) {
                        stringArray = cb;
                        this.a("AFTER", motionEvent);
                        return;
                    }
                    stringArray = cb;
                    if (this.mode.is("Grim Server 1.9+")) {
                        this.b(motionEvent);
                        this.a("AFTER", motionEvent);
                        return;
                    }
                    stringArray = cb;
                    if (!this.mode.is("GrimAC")) break block44;
                    this.c(motionEvent);
                    this.a("AFTER", motionEvent);
                    return;
                }
                catch (Throwable throwable) {
                    this.a("AFTER", motionEvent);
                    throw throwable;
                }
            }
            if (motionEvent.isPre()) break block45;
            stringArray = cb;
            this.a("AFTER", motionEvent);
            return;
        }
        String string = (String)this.mode.getValue();
        int n2 = -1;
        switch (string.hashCode()) {
            case 80099049: {
                if (!string.equals("Spoof")) break;
                n2 = 0;
                break;
            }
            case 1407612102: {
                stringArray = cb;
                if (!string.equals("No Ground")) break;
                n2 = 1;
                break;
            }
            case -1911998296: {
                stringArray = cb;
                if (!string.equals("Packet")) break;
                n2 = 2;
                break;
            }
            case 79151470: {
                stringArray = cb;
                if (!string.equals("Round")) break;
                n2 = 3;
                break;
            }
            case -1721492669: {
                stringArray = cb;
                if (!string.equals("Vulcan")) break;
                n2 = 4;
                break;
            }
            case 2192268: {
                stringArray = cb;
                if (!string.equals("Flag")) break;
                n2 = 5;
                break;
            }
            case -1997372447: {
                stringArray = cb;
                if (!string.equals("Matrix")) break;
                n2 = 6;
                break;
            }
            case 408575737: {
                stringArray = cb;
                if (!string.equals("Chunk Load")) break;
                n2 = 7;
                break;
            }
            case -352307958: {
                stringArray = cb;
                if (!string.equals("Heypixel")) break;
                n2 = 8;
                break;
            }
            case -1069690750: {
                stringArray = cb;
                if (!string.equals("Watchdog Prediction")) break;
                n2 = 9;
                break;
            }
            case -382043255: {
                stringArray = cb;
                if (!string.equals("Watchdog Blink")) break;
                n2 = 10;
                break;
            }
            case 1432028667: {
                stringArray = cb;
                if (!string.equals("Watchdog Packet")) break;
                n2 = 11;
                break;
            }
        }
        switch (n2) {
            case 0: {
                motionEvent.setOnGround(true);
                break;
            }
            case 1: {
                motionEvent.setOnGround(false);
                motionEvent.setY(motionEvent.getY() + Math.random() / 1.0E20);
                break;
            }
            case 2: {
                if (!this.t()) break;
                this.v();
                break;
            }
            case 3: {
                this.d(motionEvent);
                break;
            }
            case 4: {
                this.e(motionEvent);
                break;
            }
            case 5: {
                this.f(motionEvent);
                break;
            }
            case 6: {
                this.g(motionEvent);
                break;
            }
            case 7: {
                this.a(motionEvent, false);
                break;
            }
            case 8: {
                this.a(motionEvent, true);
                break;
            }
            case 9: {
                this.q();
                break;
            }
            case 10: {
                if (!this.G) break;
                motionEvent.setOnGround(true);
                break;
            }
            case 11: {
                this.h(motionEvent);
                break;
            }
        }
        stringArray = cb;
        this.a("AFTER", motionEvent);
    }

    private boolean z() {
        boolean bl;
        block13: {
            block12: {
                if (NoFall.mc.player.getAbilities().flying || NoFall.mc.player.isFallFlying()) break block12;
                if (NoFall.mc.player.isInWaterRainOrBubble()) break block12;
                if (NoFall.mc.player.isInLava()) break block12;
                if (NoFall.mc.player.onClimbable()) break block12;
                if (NoFall.mc.player.isPassenger()) break block12;
                bl = true;
                break block13;
            }
            bl = false;
        }
        return bl;
    }

    private String a(String string, int n2) {
        if (string == null) {
            return "null";
        }
        String string2 = string.replace('\r', ' ').replace('\n', ' ');
        Object object = string2.length() <= n2 ? string2 : string2.substring(0, n2) + "...";
        return object;
    }

    private void g(MotionEvent motionEvent) {
        if (!this.E) {
            return;
        }
        motionEvent.setOnGround(false);
        this.v();
        this.E = false;
    }

    private void e(MotionEvent motionEvent) {
        if (!this.u()) {
            return;
        }
        motionEvent.setOnGround(true);
        NoFall.mc.player.setDeltaMovement(NoFall.mc.player.getDeltaMovement().x, -0.09800000190735147, NoFall.mc.player.getDeltaMovement().z);
        this.w();
    }

    private void l() {
        block7: {
            block6: {
                if (NoFall.mc.options == null || mc.getWindow() == null) break block6;
                if (!this.Q) break block7;
            }
            return;
        }
        this.R = InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)NoFall.mc.options.keyJump.getKey().getValue());
        this.Q = true;
        this.T = 0;
        String[] stringArray = cb;
        this.a("JUMP_ARMED", "wasDown=" + this.R);
        NoFall.mc.options.keyJump.setDown(true);
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        block45: {
            block46: {
                block43: {
                    block44: {
                        block42: {
                            block41: {
                                if (NoFall.mc.player != null && NoFall.mc.level != null) break block41;
                                return;
                            }
                            if (!this.ah || ((Boolean)this.debugger.getValue()).booleanValue()) break block42;
                            this.a("DEBUGGER_DISABLED");
                            this.aj = false;
                            this.ak = 0;
                            this.H();
                        }
                        this.ah = (Boolean)this.debugger.getValue();
                        if (this.U > 0) {
                            --this.U;
                            this.w();
                            this.B = NoFall.mc.player.getY();
                            return;
                        }
                        if (!this.V) break block43;
                        if (!this.B()) break block44;
                        this.V = false;
                        this.C();
                        this.B = NoFall.mc.player.getY();
                        break block43;
                    }
                    this.B = NoFall.mc.player.getY();
                    return;
                }
                if (this.A()) {
                    this.V = true;
                    this.C();
                    this.B = NoFall.mc.player.getY();
                    return;
                }
                String string = (String)this.mode.getValue();
                if (!string.equals(this.z)) {
                    this.i();
                    this.z = string;
                    this.B = NoFall.mc.player.getY();
                }
                NoFall noFall = this;
                int n2 = NoFall.mc.player.onGround() ? 0 : this.v + 1;
                noFall.v = n2;
                this.j();
                this.D();
                this.E();
                if (!this.mode.is("Clutch")) break block45;
                if (AutoMLG.j == null) break block46;
                if (AutoMLG.j.isEnabled()) break block46;
                AutoMLG.j.setEnabled(true);
                this.F = true;
            }
            return;
        }
        if (this.mode.is("Matrix")) {
            this.p();
        }
        if (this.mode.is("Watchdog Blink")) {
            this.r();
        }
        if (this.mode.is("GrimAC")) {
            this.k();
            this.m();
        }
        if (!this.mode.is("Grim Server 1.9+")) {
            return;
        }
        this.o();
    }

    private void a(MotionEvent motionEvent, boolean bl) {
        block18: {
            block17: {
                if (!bl || !this.D) break block17;
                NoFall.mc.options.keyJump.setDown(true);
            }
            if (this.C) {
                NoFall.mc.player.setDeltaMovement(NoFall.mc.player.getDeltaMovement().x, 0.0, NoFall.mc.player.getDeltaMovement().z);
                motionEvent.setOnGround(false);
                motionEvent.setY(motionEvent.getY() - 0.098);
                NoFall.mc.player.setPos(NoFall.mc.player.getX(), motionEvent.getY(), NoFall.mc.player.getZ());
                return;
            }
            if (!(NoFall.mc.player.getDeltaMovement().y > 0.0) && this.u()) break block18;
            return;
        }
        double d2 = motionEvent.getY() + NoFall.mc.player.getDeltaMovement().y;
        if (!this.a(motionEvent.getX(), d2, motionEvent.getZ())) {
            return;
        }
        this.w();
        this.C = true;
        if (bl) {
            this.D = true;
            NoFall.mc.options.keyJump.setDown(true);
        }
    }

    private void a(PacketEvent packetEvent, ServerboundMovePlayerPacket serverboundMovePlayerPacket) {
        double d2 = serverboundMovePlayerPacket.getX(NoFall.mc.player.getX());
        double d3 = serverboundMovePlayerPacket.getY(NoFall.mc.player.getY());
        double d4 = serverboundMovePlayerPacket.getZ(NoFall.mc.player.getZ());
        float f2 = serverboundMovePlayerPacket.getYRot(NoFall.mc.player.getYRot());
        float f3 = serverboundMovePlayerPacket.getXRot(NoFall.mc.player.getXRot());
        packetEvent.a(true);
        this.x = f.IDLE;
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.PosRot(d2, d3, d4, f2, f3, true));
        String[] stringArray = cb;
        float f4 = f3;
        float f5 = f2;
        double d5 = d4;
        double d6 = d3;
        double d7 = d2;
        this.a("LANDING_CONFIRMATION_REPLACED", "x=" + d7 + " y=" + d6 + " z=" + d5 + " yaw=" + f5 + " pitch=" + f4);
        NoFall.mc.player.stopFallFlying();
        NoFall.mc.player.setOnGround(true);
        NoFall.mc.player.resetFallDistance();
        this.s();
    }

    private void m() {
        block22: {
            block21: {
                block20: {
                    block18: {
                        String[] stringArray;
                        block19: {
                            block17: {
                                block16: {
                                    if (!this.Q || NoFall.mc.options == null) break block16;
                                    if (NoFall.mc.player != null) break block17;
                                }
                                return;
                            }
                            ++this.T;
                            stringArray = cb;
                            double d2 = NoFall.mc.player.getDeltaMovement().y;
                            boolean bl = NoFall.mc.player.onGround();
                            int n2 = this.T;
                            this.a("JUMP_TICK", "jumpTicks=" + n2 + " onGround=" + bl + " motionY=" + d2);
                            if (!NoFall.mc.player.onGround()) break block18;
                            if (!this.S) break block19;
                            this.n();
                            return;
                        }
                        this.S = true;
                        this.P = true;
                        stringArray = cb;
                        this.a("AUTO_JUMP", "action=jumpFromGround");
                        NoFall.mc.player.jumpFromGround();
                        this.n();
                        return;
                    }
                    if (this.S || NoFall.mc.player.getDeltaMovement().y > 0.1) break block20;
                    if (this.T <= 8) break block21;
                }
                this.n();
                break block22;
            }
            NoFall.mc.options.keyJump.setDown(true);
        }
    }

    public boolean x() {
        block4: {
            if (this.isEnabled() && NoFall.mc.player != null) break block4;
            return false;
        }
        this.s();
        this.C = false;
        this.E = false;
        this.U = 3;
        this.w();
        this.B = NoFall.mc.player.getY();
        return true;
    }

    private void f(MotionEvent motionEvent) {
        block4: {
            if (!(NoFall.mc.player.fallDistance <= 3.0f) || !(this.A <= 3.0f)) break block4;
            return;
        }
        motionEvent.setY(motionEvent.getY() + Math.random());
        this.A = -999.0f;
        NoFall.mc.player.fallDistance = -999.0f;
    }

    private void p() {
        if ((double)Math.round(NoFall.mc.player.fallDistance) - NoFall.mc.player.getDeltaMovement().y <= 3.0) {
            return;
        }
        Vec3 vec3 = NoFall.mc.player.getDeltaMovement();
        NoFall.mc.player.setDeltaMovement(vec3.x * 0.1, 0.0, vec3.z * 0.1);
        this.w();
        this.E = true;
    }

    private void h(MotionEvent motionEvent) {
        block12: {
            block11: {
                if (Scaffold.INSTANCE == null || !Scaffold.INSTANCE.isEnabled()) break block11;
                return;
            }
            if (!this.t()) {
                return;
            }
            if (!((Boolean)this.prediction.getValue()).booleanValue() || this.a(200)) break block12;
            return;
        }
        motionEvent.setY(motionEvent.getY() + 1.0E-13);
        this.v();
    }

    private void n() {
        block7: {
            if (!this.Q) {
                return;
            }
            this.Q = false;
            this.S = false;
            this.T = 0;
            if (NoFall.mc.options == null || mc.getWindow() == null) break block7;
            NoFall.mc.options.keyJump.setDown(this.R);
        }
    }

    private void v() {
        String[] stringArray = cb;
        this.a("GROUND_PACKET_SENT", "statusOnly=true");
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.StatusOnly(true));
        this.w();
    }

    public NoFall() {
        String[] stringArray = cb;
        super("NoFall", Category.PLAYER);
        this.mode = new ModeSetting("Mode", "Grim Server 1.9+", "Spoof", "Packet", "No Ground", "Round", "Vulcan", "Flag", "Matrix", "Chunk Load", "Heypixel", "Clutch", "Watchdog Prediction", "Watchdog Blink", "Watchdog Packet", "GrimAC").withDefault("Spoof");
        this.prediction = new BooleanSetting("Prediction", false, () -> this.mode.is("Watchdog Packet"));
        this.debugger = new BooleanSetting("Debugger", false);
        this.autoMLGAutoDisable = new BooleanSetting("AutoMLG auto disable", true);
        this.x = f.IDLE;
        this.W = new ConcurrentLinkedQueue<Packet<ServerGamePacketListener>>();
        this.X = new Object();
        INSTANCE = this;
    }

    private void c(MotionEvent motionEvent) {
        boolean bl;
        double d2;
        block42: {
            boolean bl2;
            boolean bl3;
            block41: {
                block40: {
                    block39: {
                        block38: {
                            block37: {
                                block36: {
                                    block34: {
                                        block35: {
                                            if (!motionEvent.isPre()) {
                                                return;
                                            }
                                            if (this.J || this.K > 0) {
                                            }
                                            break block34;
                                            this.a("EPSILON_APPLIED", "offset=1.0E-9 eventYBefore=" + motionEvent.getY());
                                            motionEvent.setY(motionEvent.getY() + 1.0E-9);
                                            if (this.K <= 0) break block35;
                                            --this.K;
                                        }
                                        NoFall noFall = this;
                                        boolean bl4 = this.K > 0;
                                        noFall.J = bl4;
                                        this.I = false;
                                        this.L = 0;
                                        this.M = true;
                                        ++this.N;
                                        this.O = 0;
                                        this.w();
                                        return;
                                    }
                                    if (mc.isSingleplayer() || NoFall.mc.player.onGround()) break block36;
                                    if (NoFall.mc.player.getDeltaMovement().y >= 0.0) break block36;
                                    if (this.z()) break block37;
                                }
                                return;
                            }
                            if (this.I || this.M) break block38;
                            if (!(NoFall.mc.player.fallDistance <= 3.0f)) break block39;
                            if (!(this.A <= 3.0f)) break block39;
                        }
                        return;
                    }
                    d2 = motionEvent.getY() + NoFall.mc.player.getDeltaMovement().y;
                    bl3 = this.a(motionEvent.getX(), d2, motionEvent.getZ());
                    if (bl3 || !this.a(2)) break block40;
                    bl2 = true;
                    break block41;
                }
                bl2 = false;
            }
            bl = bl2;
            if (bl3 || bl) break block42;
            String[] stringArray = cb;
            String string = this.a(BlockPos.containing((double)motionEvent.getX(), (double)d2, (double)motionEvent.getZ()));
            double d3 = d2;
            this.a("GRIMAC_PREDICTION_MISS", "predictedY=" + d3 + " predictedBlock=" + string);
            return;
        }
        this.I = true;
        this.L = 0;
        String[] stringArray = cb;
        String string = this.a(BlockPos.containing((double)motionEvent.getX(), (double)d2, (double)motionEvent.getZ()));
        boolean bl5 = bl;
        double d4 = d2;
        this.a("FLY_PACKET_SENT", "predictedY=" + d4 + " nearGround=" + bl5 + " predictedBlock=" + string);
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundPlayerCommandPacket((Entity)NoFall.mc.player, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
        this.l();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(String string, String string2) {
        if (!((Boolean)this.debugger.getValue()).booleanValue()) {
            return;
        }
        Object object = this.X;
        synchronized (object) {
            try {
                if (this.Y == null) {
                    this.Z = this.G();
                    Files.createDirectories(this.Z.getParent(), new FileAttribute[0]);
                    this.Y = Files.newBufferedWriter(this.Z, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    this.aa = false;
                }
                long l3 = mc != null && NoFall.mc.player != null ? (long)NoFall.mc.player.tickCount : -1L;
                String string4 = this.mode.getValue() == null ? "null" : (String)this.mode.getValue();
                String string5 = string2;
                String string6 = string4;
                long l4 = l3;
                long l5 = this.ac;
                String string7 = string;
                String string8 = Instant.now().toString();
                String[] stringArray = cb;
                this.Y.write("[" + string8 + "] event=" + string7 + " fallId=" + l5 + " tick=" + l4 + " mode=" + string6 + " " + string5);
                this.Y.newLine();
                this.Y.flush();
            }
            catch (IOException | RuntimeException exception) {
                if (!this.aa) {
                    this.aa = true;
                    logger.error("Failed to write NoFall debugger log", (Throwable)exception);
                }
                this.H();
            }
            return;
        }
    }

    private void I() {
        while (true) {
            Packet<ServerGamePacketListener> packet = this.W.poll();
            if (packet == null) break;
            PacketUtil.sendQueued(packet);
        }
    }

    public void y() {
        block4: {
            if (this.isEnabled() && NoFall.mc.player != null) break block4;
            return;
        }
        this.U = Math.max(this.U, 2);
        this.w();
        this.B = NoFall.mc.player.getY();
    }

    @Override
    public void onDisable() {
        String[] stringArray = cb;
        this.a("MODULE", "action=disable");
        this.h();
        this.aj = false;
        this.H();
    }

    private boolean a(int n2) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        int n3 = NoFall.mc.level.getMinBuildHeight();
        int n4 = (int)Math.floor(NoFall.mc.player.getY());
        int n5 = Math.max(n3, n4 - n2);
        int n6 = n4 - 1;
        while (true) {
            block5: {
                if (n6 < n5) break;
                mutableBlockPos.set(NoFall.mc.player.getX(), (double)n6, NoFall.mc.player.getZ());
                if (NoFall.mc.level.getBlockState((BlockPos)mutableBlockPos).getCollisionShape((BlockGetter)NoFall.mc.level, (BlockPos)mutableBlockPos).isEmpty()) break block5;
                return true;
            }
            --n6;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean resetDebuggerLog() {
        Object object = this.X;
        synchronized (object) {
            this.H();
            try {
                Files.deleteIfExists(this.G());
                this.ab = 0L;
                this.ac = 0L;
                this.ad = false;
                this.ae = false;
                this.af = 0.0f;
                this.aj = false;
                this.ak = 0;
                this.al = 0L;
                this.aa = false;
                return true;
            }
            catch (IOException | RuntimeException exception) {
                logger.error("Failed to reset NoFall debugger log", (Throwable)exception);
                return false;
            }
        }
    }

    private boolean t() {
        boolean bl = (double)Math.max(this.A, NoFall.mc.player.fallDistance) > (double)3.1f + NoFall.mc.player.getDeltaMovement().y;
        return bl;
    }

    private void s() {
        String[] stringArray = cb;
        this.a("RESET", "reason=landingCorrectionState");
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 0;
        this.w = false;
        this.x = f.IDLE;
        this.y = 0;
        this.J();
    }

    private void a(String string, MotionEvent motionEvent) {
        boolean bl;
        boolean bl2;
        float f2;
        float f3;
        double d2;
        double d3;
        double d4;
        boolean bl3;
        boolean bl4;
        String string2;
        NoFall noFall;
        block13: {
            block12: {
                block11: {
                    block10: {
                        if (!((Boolean)this.debugger.getValue()).booleanValue() || !this.ad) break block10;
                        if (NoFall.mc.player != null) break block11;
                    }
                    return;
                }
                noFall = this;
                string2 = "MOTION_" + string;
                bl4 = motionEvent.isPre();
                bl3 = motionEvent.isCancelled();
                d4 = motionEvent.getX();
                d3 = motionEvent.getY();
                d2 = motionEvent.getZ();
                f3 = motionEvent.getYaw();
                f2 = motionEvent.getPitch();
                bl2 = motionEvent.isOnGround();
                if (NoFall.mc.level == null || !this.a(motionEvent.getX(), motionEvent.getY() + NoFall.mc.player.getDeltaMovement().y, motionEvent.getZ())) break block12;
                bl = true;
                break block13;
            }
            bl = false;
        }
        String string3 = this.F();
        boolean bl5 = bl;
        boolean bl6 = bl2;
        float f4 = f2;
        float f5 = f3;
        double d5 = d2;
        double d6 = d3;
        double d7 = d4;
        boolean bl7 = bl3;
        boolean bl8 = bl4;
        String[] stringArray = cb;
        noFall.a(string2, "pre=" + bl8 + " cancelled=" + bl7 + " eventPos=(" + d7 + "," + d6 + "," + d5 + ") eventRot=(" + f5 + "," + f4 + ") eventOnGround=" + bl6 + " predictedSolid=" + bl5 + " " + string3);
    }

    private boolean B() {
        boolean bl;
        block16: {
            block15: {
                block14: {
                    block13: {
                        block12: {
                            if (NoFall.mc.player == null || AutoMLG.j == null) break block12;
                            if (AutoMLG.j.isEnabled()) break block13;
                        }
                        return true;
                    }
                    if (NoFall.mc.player.onGround() || NoFall.mc.player.isInWaterRainOrBubble()) break block14;
                    if (!NoFall.mc.player.isInLava()) break block15;
                }
                bl = true;
                break block16;
            }
            bl = false;
        }
        return bl;
    }

    private void C() {
        this.a("AUTOMLG_PAUSE");
        this.I();
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 0;
        this.w = false;
        this.x = f.IDLE;
        this.y = 0;
        this.A = 0.0f;
        this.C = false;
        this.D = false;
        this.E = false;
        this.G = false;
        this.H = 0;
        this.I = false;
        this.J = false;
        this.K = 0;
        this.L = 0;
        this.M = false;
        this.N = 0;
        this.O = 0;
        this.P = false;
        this.S = false;
        this.n();
        this.J();
        if (NoFall.mc.player != null) {
            NoFall.mc.player.fallDistance = 0.0f;
        }
    }

    private String a(Packet<?> packet) {
        if (packet == null) {
            return "packet=null";
        }
        String[] stringArray = cb;
        return "type=" + packet.getClass().getName() + " payload=" + this.a(String.valueOf(packet), 500);
    }

    private boolean A() {
        block26: {
            block25: {
                block24: {
                    block23: {
                        block22: {
                            if (!((Boolean)this.autoMLGAutoDisable.getValue()).booleanValue() || AutoMLG.j == null) break block22;
                            if (!AutoMLG.j.isEnabled()) break block22;
                            if (NoFall.mc.player != null) break block23;
                        }
                        return false;
                    }
                    if (NoFall.mc.player.onGround() || NoFall.mc.player.getDeltaMovement().y >= 0.0) break block24;
                    if (NoFall.mc.player.isInWaterRainOrBubble()) break block24;
                    if (!NoFall.mc.player.isInLava()) break block25;
                }
                return false;
            }
            if (!(NoFall.mc.player.fallDistance <= 0.0f) || !(this.A <= 0.0f)) break block26;
            return false;
        }
        boolean bl = d.a(0, 9, Items.WATER_BUCKET) >= 0;
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void H() {
        Object object = this.X;
        synchronized (object) {
            if (this.Y == null) {
                return;
            }
            try {
                this.Y.close();
            }
            catch (IOException iOException) {
                logger.error("Failed to close NoFall debugger log", (Throwable)iOException);
            }
            finally {
                this.Y = null;
            }
        }
    }

    @EventTarget
    public void a(KeyEvent keyEvent) {
        block12: {
            block11: {
                if (NoFall.mc.player != null && keyEvent.getKeyCode() == 32) break block11;
                return;
            }
            if (this.V) {
                return;
            }
            if (!this.mode.is("Heypixel") || !this.D) break block12;
            keyEvent.a(true);
        }
    }

    private boolean a(double d2) {
        boolean bl = (double)Math.max(this.A, NoFall.mc.player.fallDistance) > 3.0 + d2;
        return bl;
    }

    private void w() {
        this.A = 0.0f;
        NoFall.mc.player.fallDistance = 0.0f;
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
                    case 0 -> 33;
                    case 1 -> 5;
                    case 2 -> 97;
                    case 3 -> 22;
                    case 4 -> 47;
                    case 5 -> 105;
                    default -> 85;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private void q() {
        block7: {
            if (this.u() && this.a(200)) break block7;
            return;
        }
        Vec3 vec3 = NoFall.mc.player.getEyePosition(1.0f);
        Vec3 vec32 = new Vec3(vec3.x, 0.0, vec3.z);
        BlockHitResult blockHitResult = NoFall.mc.level.clip(new ClipContext(vec3, vec32, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)NoFall.mc.player));
        if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return;
        }
        PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.Pos(NoFall.mc.player.getX(), blockHitResult.getLocation().y, NoFall.mc.player.getZ(), true));
        this.w();
    }

    @EventTarget(a=4)
    public void a(PacketEvent packetEvent) {
        block77: {
            block76: {
                Packet<?> packet;
                block75: {
                    block73: {
                        String[] stringArray;
                        block74: {
                            block69: {
                                block70: {
                                    block72: {
                                        block71: {
                                            block66: {
                                                String string;
                                                NoFall noFall;
                                                block68: {
                                                    block67: {
                                                        block65: {
                                                            if (NoFall.mc.player == null) {
                                                                return;
                                                            }
                                                            if (this.V) {
                                                                return;
                                                            }
                                                            if (this.ad || this.I) break block65;
                                                            if (this.M) break block65;
                                                            if (this.q) break block65;
                                                            if (!this.s) break block66;
                                                        }
                                                        noFall = this;
                                                        if (!packetEvent.a()) break block67;
                                                        string = "IN";
                                                        break block68;
                                                    }
                                                    stringArray = cb;
                                                    string = "OUT";
                                                }
                                                stringArray = cb;
                                                noFall.a("PACKET_" + string, "cancelled=" + packetEvent.isCancelled() + " " + this.a(packetEvent.c()));
                                            }
                                            if (this.U > 0) {
                                                return;
                                            }
                                            if (!packetEvent.a()) break block69;
                                            if (!(packetEvent.c() instanceof ClientboundPlayerPositionPacket)) break block70;
                                            this.C = false;
                                            this.D = false;
                                            this.J();
                                            if (!this.mode.is("Grim Server 1.9+")) break block71;
                                            if (this.x != f.WAITING_FOR_CORRECTION) break block71;
                                            if (!this.q) break block71;
                                            this.s = true;
                                            this.y = 0;
                                        }
                                        if (this.G) {
                                            this.I();
                                            this.G = false;
                                            this.H = 0;
                                        }
                                        if (!this.mode.is("GrimAC")) break block70;
                                        if (this.I) break block72;
                                        if (!this.M) break block70;
                                    }
                                    ++this.K;
                                    this.J = true;
                                    this.L = 0;
                                    this.M = true;
                                    this.O = 0;
                                    stringArray = cb;
                                    String string = this.a(packetEvent.c());
                                    int n2 = this.K;
                                    int n3 = this.N + this.K;
                                    this.a("POSITION_CORRECTION_RECEIVED", "action=armEpsilon correction=" + n3 + " pending=" + n2 + " packet=" + string);
                                }
                                return;
                            }
                            if (!this.mode.is("GrimAC")) break block73;
                            if (this.J) break block74;
                            if (this.K <= 0) break block73;
                        }
                        if ((packet = packetEvent.c()) instanceof ServerboundMovePlayerPacket) {
                            ServerboundMovePlayerPacket serverboundMovePlayerPacket = (ServerboundMovePlayerPacket)packet;
                            double d2 = serverboundMovePlayerPacket.getX(NoFall.mc.player.getX());
                            double d3 = serverboundMovePlayerPacket.getY(NoFall.mc.player.getY()) + 1.0E-9;
                            double d4 = serverboundMovePlayerPacket.getZ(NoFall.mc.player.getZ());
                            float f2 = serverboundMovePlayerPacket.getYRot(NoFall.mc.player.getYRot());
                            float f3 = serverboundMovePlayerPacket.getXRot(NoFall.mc.player.getXRot());
                            boolean bl = serverboundMovePlayerPacket.isOnGround();
                            packetEvent.a(true);
                            if (this.K > 0) {
                                --this.K;
                            }
                            NoFall noFall = this;
                            boolean bl2 = this.K > 0;
                            noFall.J = bl2;
                            this.I = false;
                            this.M = true;
                            this.O = 0;
                            ++this.N;
                            PacketUtil.sendQueued((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.PosRot(d2, d3, d4, f2, f3, bl));
                            stringArray = cb;
                            this.a("EPSILON_APPLIED", "path=packet offset=1.0E-9 eventYBefore=" + serverboundMovePlayerPacket.getY(NoFall.mc.player.getY()));
                            return;
                        }
                    }
                    if (!this.mode.is("Grim Server 1.9+") || this.x != f.WAITING_FOR_CORRECTION) break block75;
                    if (!this.s) break block75;
                    if (!(packetEvent.c() instanceof ServerboundAcceptTeleportationPacket)) break block75;
                    this.x = f.REPLACING_CONFIRMATION;
                    this.y = 0;
                    this.a("ACCEPT_TELEPORT", "action=replaceNextMovement");
                    return;
                }
                if (!this.mode.is("Grim Server 1.9+") || this.x != f.REPLACING_CONFIRMATION) break block76;
                packet = packetEvent.c();
                if (packet instanceof ServerboundMovePlayerPacket) {
                    ServerboundMovePlayerPacket serverboundMovePlayerPacket = (ServerboundMovePlayerPacket)packet;
                    this.a(packetEvent, serverboundMovePlayerPacket);
                    return;
                }
            }
            if (!this.mode.is("Watchdog Blink") || !this.G) break block77;
            if (!(packetEvent.c() instanceof ServerboundMovePlayerPacket)) break block77;
            packetEvent.a(true);
            this.W.offer(packetEvent.c());
        }
    }

    private void E() {
        String[] stringArray;
        String string;
        boolean bl;
        boolean bl2;
        float f2;
        block24: {
            block25: {
                block23: {
                    block22: {
                        block21: {
                            block20: {
                                if (!this.aj || !((Boolean)this.debugger.getValue()).booleanValue()) break block20;
                                if (NoFall.mc.player != null) break block21;
                            }
                            return;
                        }
                        f2 = NoFall.mc.player.getHealth();
                        boolean bl3 = f2 < this.an - 0.001f;
                        boolean bl4 = bl3;
                        if (bl4 || ++this.ak >= 12) break block22;
                        return;
                    }
                    boolean bl5 = this.am >= 3.0f;
                    bl2 = bl5;
                    boolean bl6 = f2 >= this.an - 0.001f;
                    bl = bl6;
                    if (bl2) break block23;
                    string = "\u00a77\u672a\u89e6\u53d1";
                    break block24;
                }
                if (!bl) break block25;
                string = "\u00a7a\u6210\u529f";
                break block24;
            }
            stringArray = cb;
            string = "\u00a7c\u5931\u8d25";
        }
        String string2 = string;
        stringArray = cb;
        String string3 = String.format(Locale.ROOT, "%.1f\u2764\u2192%.1f\u2764", Float.valueOf(this.an), Float.valueOf(f2));
        ChatUtil.print(false, String.format(Locale.ROOT, "\u00a7b[NoFall] %s \u00a77| \u00a7f%s \u00a77| \u00a7f\u5cf0\u503c %.1f\u683c \u00a77| \u00a7f%s", string2, this.ao, Float.valueOf(this.am), string3));
        int n2 = this.ak;
        float f3 = f2;
        boolean bl7 = bl;
        boolean bl8 = bl2;
        long l2 = this.al;
        this.a("RESULT", "pendingFallId=" + l2 + " eligible=" + bl8 + " success=" + bl7 + " endHealth=" + f3 + " waitedTicks=" + n2);
        this.aj = false;
        this.ak = 0;
    }

    private void j() {
        double d2;
        block11: {
            block10: {
                block9: {
                    d2 = NoFall.mc.player.getY();
                    if (!this.z() || NoFall.mc.player.onGround()) break block9;
                    if (!(NoFall.mc.player.getDeltaMovement().y > 0.0)) break block10;
                }
                this.A = 0.0f;
                break block11;
            }
            if (d2 < this.B) {
                this.A += (float)(this.B - d2);
            }
        }
        this.B = d2;
    }

    private void b(MotionEvent motionEvent) {
        block23: {
            block22: {
                block21: {
                    block20: {
                        if (!motionEvent.isPre() || mc.isSingleplayer()) break block20;
                        if (!NoFall.mc.player.horizontalCollision) break block21;
                    }
                    return;
                }
                if (NoFall.mc.player.getDeltaMovement().y > 0.1) {
                    this.p = false;
                    this.r = false;
                    this.t = false;
                }
                if (!(NoFall.mc.player.fallDistance > 3.0f) && !(this.A > 3.0f)) break block22;
                this.p = true;
            }
            if (!this.p || !NoFall.mc.player.onGround()) break block23;
            motionEvent.a(true);
            PacketUtil.b((Packet<ServerGamePacketListener>)new ServerboundMovePlayerPacket.StatusOnly(true));
            NoFall.mc.options.keyJump.setDown(false);
            this.r = true;
            this.t = true;
            this.w();
            this.p = false;
        }
        if (this.p) {
            NoFall.mc.options.keyJump.setDown(false);
        }
    }

    private void h() {
        this.i();
        this.v = 0;
    }

    private void r() {
        block23: {
            block21: {
                block22: {
                    if (this.G || this.v <= 0) break block21;
                    if (!(NoFall.mc.player.getDeltaMovement().y < 0.0)) break block21;
                    if (!(this.A > 1.0f)) break block21;
                    if (!this.a(10)) break block21;
                    if (Scaffold.INSTANCE == null) break block22;
                    if (Scaffold.INSTANCE.isEnabled()) break block21;
                }
                this.G = true;
                this.H = 0;
            }
            if (!this.G) {
                return;
            }
            ++this.H;
            if (!NoFall.mc.player.onGround() && this.H <= 40) break block23;
            this.I();
            this.G = false;
            this.H = 0;
            this.w();
        }
    }

    private String a(BlockPos blockPos) {
        if (NoFall.mc.level == null) {
            return String.valueOf(blockPos) + "{level=null}";
        }
        BlockState blockState = NoFall.mc.level.getBlockState(blockPos);
        boolean bl = blockState.getCollisionShape((BlockGetter)NoFall.mc.level, blockPos).isEmpty();
        String[] stringArray = cb;
        return String.valueOf(blockPos) + "{collisionEmpty=" + bl + ",state=" + this.a(String.valueOf(blockState), 180) + "}";
    }

    private boolean u() {
        boolean bl;
        block5: {
            block4: {
                if (!(NoFall.mc.player.fallDistance > 3.0f) && !(this.A > 3.0f)) break block4;
                bl = true;
                break block5;
            }
            bl = false;
        }
        return bl;
    }

    public static boolean a() {
        boolean bl;
        block21: {
            block20: {
                block19: {
                    NoFall noFall;
                    block18: {
                        block17: {
                            noFall = INSTANCE;
                            if (noFall == null || !noFall.isEnabled()) break block17;
                            if (noFall.mode.is("GrimAC")) break block18;
                        }
                        return false;
                    }
                    if (noFall.V) {
                        return false;
                    }
                    if (noFall.I || noFall.M) break block19;
                    if (noFall.J) break block19;
                    if (noFall.K <= 0) break block20;
                }
                bl = true;
                break block21;
            }
            bl = false;
        }
        return bl;
    }

    private void o() {
        block16: {
            block17: {
                block15: {
                    if (this.x == f.IDLE) {
                        return;
                    }
                    ++this.y;
                    if (this.x != f.FALLING || !(NoFall.mc.player.getDeltaMovement().y > 0.1)) break block15;
                    this.s();
                    break block16;
                }
                if (this.x != f.WAITING_FOR_CORRECTION || this.y <= 12) break block17;
                this.s();
                break block16;
            }
            if (this.x != f.REPLACING_CONFIRMATION || this.y <= 4) break block16;
            this.s();
        }
    }

    private Path G() {
        Path path;
        block5: {
            block4: {
                if (mc == null || NoFall.mc.gameDirectory == null) break block4;
                path = NoFall.mc.gameDirectory.toPath();
                break block5;
            }
            path = Path.of(".", new String[0]);
        }
        Path path2 = path;
        String[] stringArray = cb;
        return path2.resolve("logs").resolve("nofall-debug.log");
    }

    private boolean a(double d2, double d3, double d4) {
        BlockPos blockPos = BlockPos.containing((double)d2, (double)d3, (double)d4);
        boolean bl = !NoFall.mc.level.getBlockState(blockPos).getCollisionShape((BlockGetter)NoFall.mc.level, blockPos).isEmpty();
        return bl;
    }

    private void k() {
        block16: {
            block14: {
                block15: {
                    if (!this.I) break block14;
                    if (++this.L <= 12) break block15;
                    this.a("TIMEOUT", "reason=grimAcResponse ticks=" + this.L);
                    this.I = false;
                    this.J = false;
                    this.K = 0;
                    this.L = 0;
                    this.M = false;
                    this.N = 0;
                    this.O = 0;
                    this.P = false;
                    this.n();
                }
                return;
            }
            if (!this.M) {
                return;
            }
            if (!this.J && this.K <= 0) break block16;
            this.O = 0;
            return;
        }
        if (++this.O >= 12) {
            this.a("SETTLE_COMPLETE", "corrections=" + this.N + " settleTicks=" + this.O);
            this.M = false;
            this.N = 0;
            this.K = 0;
            this.O = 0;
            this.P = false;
        }
    }

    @EventTarget
    public void a(StrafeEvent strafeEvent) {
        block10: {
            if (NoFall.mc.player == null) {
                return;
            }
            if (this.V) {
                return;
            }
            if (!this.mode.is("Heypixel") || !this.D) break block10;
            NoFall.mc.options.keyJump.setDown(true);
        }
    }

    @Override
    public void onEnable() {
        this.h();
        this.z = (String)this.mode.getValue();
        NoFall noFall = this;
        double d2 = NoFall.mc.player == null ? 0.0 : NoFall.mc.player.getY();
        noFall.B = d2;
        this.ah = (Boolean)this.debugger.getValue();
        String[] stringArray = cb;
        this.a("MODULE", "action=enable");
    }

    private void D() {
        block47: {
            boolean bl;
            block46: {
                String[] stringArray;
                float f2;
                block43: {
                    block45: {
                        block44: {
                            block42: {
                                float f3;
                                block41: {
                                    boolean bl2;
                                    block40: {
                                        block39: {
                                            block38: {
                                                block37: {
                                                    if (!((Boolean)this.debugger.getValue()).booleanValue() || NoFall.mc.player == null) break block37;
                                                    if (NoFall.mc.level != null) break block38;
                                                }
                                                return;
                                            }
                                            if (NoFall.mc.player.onGround() || !(NoFall.mc.player.getDeltaMovement().y < 0.0)) break block39;
                                            if (!this.z()) break block39;
                                            bl2 = true;
                                            break block40;
                                        }
                                        bl2 = false;
                                    }
                                    bl = bl2;
                                    float f4 = this.ad ? (float)Math.max(0.0, this.ag - NoFall.mc.player.getY()) : 0.0f;
                                    f3 = f4;
                                    f2 = Math.max(Math.max(NoFall.mc.player.fallDistance, this.A), f3);
                                    if (this.ad || !bl) break block41;
                                    this.ad = true;
                                    this.ae = false;
                                    this.ac = ++this.ab;
                                    this.af = f2;
                                    this.ag = NoFall.mc.player.getY();
                                    this.ai = NoFall.mc.player.getHealth();
                                }
                                if (!this.ad) {
                                    return;
                                }
                                f3 = (float)Math.max(0.0, this.ag - NoFall.mc.player.getY());
                                f2 = Math.max(Math.max(NoFall.mc.player.fallDistance, this.A), f3);
                                this.af = Math.max(this.af, f2);
                                if (this.ae || !(this.af >= 4.0f)) break block42;
                                this.ae = true;
                                stringArray = cb;
                                String string = this.F();
                                double d2 = this.ag;
                                float f5 = this.af;
                                this.a("FALL_START", "distance=" + f5 + " startY=" + d2 + " " + string);
                            }
                            if (this.ae) break block43;
                            if (NoFall.mc.player.onGround()) break block44;
                            if (bl) break block45;
                            if (!(NoFall.mc.player.getDeltaMovement().y > 0.1)) break block45;
                        }
                        this.ad = false;
                        this.af = 0.0f;
                    }
                    return;
                }
                stringArray = cb;
                String string = this.F();
                float f6 = this.af;
                float f7 = f2;
                boolean bl3 = bl;
                this.a("TICK", "descending=" + bl3 + " distance=" + f7 + " peak=" + f6 + " " + string);
                if (!NoFall.mc.player.onGround()) break block46;
                this.a("LANDING_DETECTED");
                break block47;
            }
            if (bl || !(NoFall.mc.player.getDeltaMovement().y > 0.1)) break block47;
            this.a("ASCENDING");
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                NoFall.k = 12;
                NoFall.j = 12;
                var5 = new String[177];
                var3_1 = 0;
                var2_2 = "\u0086d\u6271\u5289\u000fdU2_c&\u001b~D1Fc \u0010e\u000bQw\u0004rF\n!Da8+\r@f\u0015\u007f@\u0007hDk\u0000tC\f\u0004Mj\u0006e\r`P5Yb%\u0012~U C|,\u001dS`\u0000e@\u0007hMd\u000frF\u00072bj\u0013dJ\n!Hj\u000fE[\b!D\u0015\u0001m\u000edF\u0013:Oq\u0000zl\u00069Ml\u0012\u007f@\u0007h\u000bQi\u0000oJ\u001bhOp\rz\u0010\u0001u\u0013sK\u00006U`\u0005E@\u0005<E8\u0006\u0001u\u0004wDT\fZi\u0004`J\u0005hOp\rzR\u0006ld\u0015dF\u0011\r\u0001r\u0000\u007f[=<Bn\u0012+\u001e[\u001bNc\u0007eJ\u001dh\u0010+QS\u0002PuDs\u0004x[0\u0017Dc\u000edJT\u0011e@#Ch.\u0010sZ%_|(\u0017m@%\r\u0001r\u0000\u007f[\f1ul\u0002}\\T\u0004gi\u0000q\u000bQw\u0004rF\n!Da8+\fBj\u0013dJ\n!Hj\u000fe\u0012\u0006qd\u0002}J\u001d\u000bQd\u0002}J\u001dhOp\rz\u0018\u0001w\u0004uJ\u0000#Da1y\\\u0000!Hj\u000fFN\n>Dq\\\bi`\u0018fF\u00110M\u000e\u0001c\u0000}J<;Mj\u0000rJ\rh\f\u0001v\u0015w[\f\u0001Hf\ne\u0012\u0013\u0001b\u0013\u007fB(6`u\u0011zV&3Gv\u0004b\u0012\u000b\u0001g\u000ey\\\u001d\u0001Hf\n+\ngD-Zp:\u0001`W5\u0013vd\u0015uG\r:F%1dJ\r<Bq\byA#gd\bzJ\ruUjAuC\u0006&D%/yi\b9M%\u0005sM\u001c2F`\u00136C\u00062\u0004Qw\u0004+\b\b%\rw\\\u001d\f\u001c\u0017\u0001b\u0013\u007fB(6bj\u0013dJ\n!Hj\u000fU@\u001c;U8\u0010fw\b{\u000f:0Ss\u0004d\u000fX{\u0018.\u0007\rv\u0015w[\fh\u0010fw\b{\u000f:0Ss\u0004d\u000fX{\u0018.\nqw\u0004rF\n!Hj\u000f\u0006qd\u0002}J\u001d\n\u0001`\r\u007fH\u00007M`\\\u0007lJ5_`'\n\u0010\u0001b\u0013\u007fB(6r`\u0015bC\u0000;F8\tEl\u0012bN\u00076D8\u0006c@'Y},\u0013\u0001b\u0013\u007fB(6md\u000frF\u00072r`\u0004x\u0012\u0006oj'wC\u0005\t`P5Yp#\u0000lU\u0015\u0001q\u0013wL\u00020EC\u0000zC-<Rq\u0000xL\fh\nbm\u0014xDI\u0019Nd\u0005\nbm\u0014xDI\u0019Nd\u0005\u0007\u0001v\u0015w[\fh\u0010\b%\u0004`J\u0007!nk&d@\u001c;E8\r\u0001g\u000ey\\\u001d\u0014Bq\b`JT\t\u0001v\u0014uL\f&R8\u0006\u0001q\buDT\u0011\u0001b\u0013\u007fB(6kp\ff{\u00006Jv\\\b\u0001v\u0015w]\u001d\f\u001c\nBd\u000fuJ\u00059Da\\\u0006wp\ruN\u0007\u0005\u00862\u674b\u89f0\u53fe\u0012fW.Ca-\nqD\"]j=\nr@/B\u000b\u0001`\u000frg\f4Mq\t+\u0005Qj\u0012+\u0007\u0004Op\rz\n\b%\fy[\u0000:O8I\u0005sj\u0014xK\u000b\u0001f\u0000xL\f9M`\u0005+\u0012\u0001r\u0000bL\u00011Nb#zF\u0007>Hk\u0006+\u000b\u0001`\u0017sA\u001d\u0005Nv\\>\u000b\u0001l\u0012PN\u00059Hk\u0006+\u0003\u0001|\\\u0013vd\u0015uG\r:F%1dJ\r<Bq\byA\u001dmD/Rf'\u0012~F.Xi \u0007lD5_`'\ns@1Zn*\u0010e\n\u0001d\bd{\u00006Jv\\\u0002hK\u0004\u0086f\u5950\u8d33\u000fr@5Bc,\nbJ,Fc,\u0001d\u0006\u0001h\u000erJT\u0005U|\u0011s\u0012\u0005s@2S{\nKp\ff{\u00006Jv\\\u0006s@2Cc=\b|%\u0004`J\u0007!\u001c'Qd\u0015~\u0012\u00194Bn\u0004b\u000f\u00063Gv\u0004b\u0012X{\u0011@L/\u000f\f#Dk\u0015Om\f3Nw\u0004+\be`\u0003cH\u000e0S\u0015@f\u0015\u007f@\u0007hKp\ffi\u001b:LB\u0013yZ\u00071\u0010\u0001u\u0013sK\u00006U`\u0005TC\u00066J8\u0006wp\ruN\u0007\u0007\u0001u\bbL\u0001h\u0004uL\"]\t\u0001u\u0004xK\u0000;F8\b\u0001c\u0000zC 1\u001c\u0003\u000f+O\u0014\u0001r\u0000bL\u00011Nb#zF\u0007>ul\u0002}\\T\u000f\u0001b\u0013\u007fB(6vd\bbF\u00072\u001c\u000b\u0004+Pp\u274b\u21fbp\u000f4\u0007\u2772\u0005`C5S}\t\u0001u\u0000oC\u00064E8\r\u0001m\u000ezK\u0000;FO\u0014{_T\u0013\u0001b\u0013\u007fB(6r`\u0015bC\f\u0001Hf\ne\u0012\t`V\"Sa-\u001coB\u0007\b%\u0013y[T}\u000b\u0001c\u0004s[+9Nf\n+\u000e\u0001c\u0000zC-<Rq\u0000xL\fh\u000fgI8I\u007f(\u0016j@5I|,\u001bu\n\u0001j\u000fQ]\u0006 Oa\\\bi`\u0018fF\u00110M\u0005`C5S}\f\b%\u0004`J\u0007!sj\u0015+\u0007#gd\bzJ\ruUjAa]\u0000!D%/yi\b9M%\u0005sM\u001c2F`\u00136C\u00062\u0003nP5\u0004gi\u0000q\u0010Oj\u0007wC\u0005xE`\u0003cHG9Nb\tkP,Fp=\u001cbN\u000fvd\u0015uG\r:F%1wL\u00020U\u0005Q`\u0000}\u0012\u0010mD/Rf'\u0012~A$Bj*\u0001dA\u000e@f\u0015\u007f@\u0007hEl\u0012wM\u00050\t\u0001h\u000ebF\u0006;x8\n\u0001a\be[\b;B`\\\u0010\u0001u\u0013sK\u00006U`\u0005TC\u00066J8\u0007qD\"]j=\n\u000eQ`\u000frF\u00072gd\rzf\rh\u000f\u0001v\u0004x[/9XU\u0000uD\f!\u001c\u0012\u0001o\u0014{_%4Oa\bxH+:Nv\u0015+\u0005ru\u000eyI.\u0086g:X@/4Mi<6\n\u001au\u00862\u001d6\u0088\u000fpR%\u00c6!SI\u00f2G\u5cf5\u505d6\nGdG\u6839A\u00b1\u0018\u0015u\u0086cDe\b\u0001u\u0000uD\f!\u001c\u0004Op\rz\u001a\u0001h\u0000b]\u0000-q`\u000frF\u00072fw\u000ecA\r\u0006Qj\u000ep\u0012\r\u0001b\u0013\u007fB(6ul\u0002}\\T\u0005ru\u000eyI\u000bE`\u0012uJ\u00071Hk\u0006+\f\u0001c\u0000zC/9Xl\u000fq\u0012#gd\bzJ\ruUjAdJ\u001a0U%/yi\b9M%\u0005sM\u001c2F`\u00136C\u00062\u0014`p\u0015yb%\u0012\u0001d\u0014b@I1Hv\u0000tC\f\u0016fW([n*\nqW$Rf*\u0001hJ/Ib \u0006r\u001d@f\u0015\u007f@\u0007h@w\fS_\u001a<Mj\u000f6L\u0006'S`\u0002bF\u0006;\u001c\u0006bi\u0014bL\u0001\tojAQ]\u0006 Oa\u0011\u0001b\u0013\u007fB(6kp\ffn\u001b8Da\\\u0010mD/Rf'\u0012~A$Bj*\u0001dA\u0006bi\u0014bL\u0001\u0017\u0001b\u0013\u007fB(6md\u000frF\u00072bj\ffC\f!D8\u000es@2Cc=\nq@/Rf'\u0012\u0003\u0001\u007f\\\u0006lJ%Cc,\f\u0001f\u0000x\u007f\u001b:U`\u0002b\u0012\u0004lj\u0005s\u0006fw\b{n*\u0007uL,S`<\u0001\u000eMd\u000frF\u00072i`\u0000z[\u0001h\u0013\u0001b\u0013\u007fB(6kp\ffx\b&ej\u0016x\u0012\u0005sj\u0014xK\f\u0001o\u0014{_=:Fb\rs\u0012\r\u0001v\u0004b[\u00050ul\u0002}\\T\u000evd\u0015uG\r:F%#zF\u0007>\u000fvd\u0015uG\r:F%1wL\u00020U\nkP,Fp(\u0007l@%\u000fdU2_c&\u001b~D1Fc \u0010e\u0006lJ%Cc,\u001cqJ2_{ \u001aoZ\"Y};\u0010bQ(Ya6\u0007dF$_y,\u0011\u000f`F\"S\u007f=\nu@-S\u007f&\u0007u\u0002Y8\u000fRq\u0000bZ\u001a\u001aOi\u0018+[\u001b D\u0005\u0001|\u0000a\u0012\u001a@f\u0015\u007f@\u0007hS`\u0011zN\n0o`\u0019bb\u0006#Dh\u0004x[\u0006ld\u0015dF\u0011\u0010Zf\u000ezC\u0000&Hj\u000fSB\u0019!X8\u0005s@2S{\u000evd\u0015uG\r:F%#zF\u0007>\n\u0001j\u000fQ]\u0006 Oa\\\f\u0001k\u0004w].'Np\u000fr\u0012\tojAQ]\u0006 Oa\u001cS`\u0000e@\u0007hFw\b{n\n\u0007Dv\u0011yA\u001a0\u0001q\buD\u001ah";
                var4_3 = "\u0086d\u6271\u5289\u000fdU2_c&\u001b~D1Fc \u0010e\u000bQw\u0004rF\n!Da8+\r@f\u0015\u007f@\u0007hDk\u0000tC\f\u0004Mj\u0006e\r`P5Yb%\u0012~U C|,\u001dS`\u0000e@\u0007hMd\u000frF\u00072bj\u0013dJ\n!Hj\u000fE[\b!D\u0015\u0001m\u000edF\u0013:Oq\u0000zl\u00069Ml\u0012\u007f@\u0007h\u000bQi\u0000oJ\u001bhOp\rz\u0010\u0001u\u0013sK\u00006U`\u0005E@\u0005<E8\u0006\u0001u\u0004wDT\fZi\u0004`J\u0005hOp\rzR\u0006ld\u0015dF\u0011\r\u0001r\u0000\u007f[=<Bn\u0012+\u001e[\u001bNc\u0007eJ\u001dh\u0010+QS\u0002PuDs\u0004x[0\u0017Dc\u000edJT\u0011e@#Ch.\u0010sZ%_|(\u0017m@%\r\u0001r\u0000\u007f[\f1ul\u0002}\\T\u0004gi\u0000q\u000bQw\u0004rF\n!Da8+\fBj\u0013dJ\n!Hj\u000fe\u0012\u0006qd\u0002}J\u001d\u000bQd\u0002}J\u001dhOp\rz\u0018\u0001w\u0004uJ\u0000#Da1y\\\u0000!Hj\u000fFN\n>Dq\\\bi`\u0018fF\u00110M\u000e\u0001c\u0000}J<;Mj\u0000rJ\rh\f\u0001v\u0015w[\f\u0001Hf\ne\u0012\u0013\u0001b\u0013\u007fB(6`u\u0011zV&3Gv\u0004b\u0012\u000b\u0001g\u000ey\\\u001d\u0001Hf\n+\ngD-Zp:\u0001`W5\u0013vd\u0015uG\r:F%1dJ\r<Bq\byA#gd\bzJ\ruUjAuC\u0006&D%/yi\b9M%\u0005sM\u001c2F`\u00136C\u00062\u0004Qw\u0004+\b\b%\rw\\\u001d\f\u001c\u0017\u0001b\u0013\u007fB(6bj\u0013dJ\n!Hj\u000fU@\u001c;U8\u0010fw\b{\u000f:0Ss\u0004d\u000fX{\u0018.\u0007\rv\u0015w[\fh\u0010fw\b{\u000f:0Ss\u0004d\u000fX{\u0018.\nqw\u0004rF\n!Hj\u000f\u0006qd\u0002}J\u001d\n\u0001`\r\u007fH\u00007M`\\\u0007lJ5_`'\n\u0010\u0001b\u0013\u007fB(6r`\u0015bC\u0000;F8\tEl\u0012bN\u00076D8\u0006c@'Y},\u0013\u0001b\u0013\u007fB(6md\u000frF\u00072r`\u0004x\u0012\u0006oj'wC\u0005\t`P5Yp#\u0000lU\u0015\u0001q\u0013wL\u00020EC\u0000zC-<Rq\u0000xL\fh\nbm\u0014xDI\u0019Nd\u0005\nbm\u0014xDI\u0019Nd\u0005\u0007\u0001v\u0015w[\fh\u0010\b%\u0004`J\u0007!nk&d@\u001c;E8\r\u0001g\u000ey\\\u001d\u0014Bq\b`JT\t\u0001v\u0014uL\f&R8\u0006\u0001q\buDT\u0011\u0001b\u0013\u007fB(6kp\ff{\u00006Jv\\\b\u0001v\u0015w]\u001d\f\u001c\nBd\u000fuJ\u00059Da\\\u0006wp\ruN\u0007\u0005\u00862\u674b\u89f0\u53fe\u0012fW.Ca-\nqD\"]j=\nr@/B\u000b\u0001`\u000frg\f4Mq\t+\u0005Qj\u0012+\u0007\u0004Op\rz\n\b%\fy[\u0000:O8I\u0005sj\u0014xK\u000b\u0001f\u0000xL\f9M`\u0005+\u0012\u0001r\u0000bL\u00011Nb#zF\u0007>Hk\u0006+\u000b\u0001`\u0017sA\u001d\u0005Nv\\>\u000b\u0001l\u0012PN\u00059Hk\u0006+\u0003\u0001|\\\u0013vd\u0015uG\r:F%1dJ\r<Bq\byA\u001dmD/Rf'\u0012~F.Xi \u0007lD5_`'\ns@1Zn*\u0010e\n\u0001d\bd{\u00006Jv\\\u0002hK\u0004\u0086f\u5950\u8d33\u000fr@5Bc,\nbJ,Fc,\u0001d\u0006\u0001h\u000erJT\u0005U|\u0011s\u0012\u0005s@2S{\nKp\ff{\u00006Jv\\\u0006s@2Cc=\b|%\u0004`J\u0007!\u001c'Qd\u0015~\u0012\u00194Bn\u0004b\u000f\u00063Gv\u0004b\u0012X{\u0011@L/\u000f\f#Dk\u0015Om\f3Nw\u0004+\be`\u0003cH\u000e0S\u0015@f\u0015\u007f@\u0007hKp\ffi\u001b:LB\u0013yZ\u00071\u0010\u0001u\u0013sK\u00006U`\u0005TC\u00066J8\u0006wp\ruN\u0007\u0007\u0001u\bbL\u0001h\u0004uL\"]\t\u0001u\u0004xK\u0000;F8\b\u0001c\u0000zC 1\u001c\u0003\u000f+O\u0014\u0001r\u0000bL\u00011Nb#zF\u0007>ul\u0002}\\T\u000f\u0001b\u0013\u007fB(6vd\bbF\u00072\u001c\u000b\u0004+Pp\u274b\u21fbp\u000f4\u0007\u2772\u0005`C5S}\t\u0001u\u0000oC\u00064E8\r\u0001m\u000ezK\u0000;FO\u0014{_T\u0013\u0001b\u0013\u007fB(6r`\u0015bC\f\u0001Hf\ne\u0012\t`V\"Sa-\u001coB\u0007\b%\u0013y[T}\u000b\u0001c\u0004s[+9Nf\n+\u000e\u0001c\u0000zC-<Rq\u0000xL\fh\u000fgI8I\u007f(\u0016j@5I|,\u001bu\n\u0001j\u000fQ]\u0006 Oa\\\bi`\u0018fF\u00110M\u0005`C5S}\f\b%\u0004`J\u0007!sj\u0015+\u0007#gd\bzJ\ruUjAa]\u0000!D%/yi\b9M%\u0005sM\u001c2F`\u00136C\u00062\u0003nP5\u0004gi\u0000q\u0010Oj\u0007wC\u0005xE`\u0003cHG9Nb\tkP,Fp=\u001cbN\u000fvd\u0015uG\r:F%1wL\u00020U\u0005Q`\u0000}\u0012\u0010mD/Rf'\u0012~A$Bj*\u0001dA\u000e@f\u0015\u007f@\u0007hEl\u0012wM\u00050\t\u0001h\u000ebF\u0006;x8\n\u0001a\be[\b;B`\\\u0010\u0001u\u0013sK\u00006U`\u0005TC\u00066J8\u0007qD\"]j=\n\u000eQ`\u000frF\u00072gd\rzf\rh\u000f\u0001v\u0004x[/9XU\u0000uD\f!\u001c\u0012\u0001o\u0014{_%4Oa\bxH+:Nv\u0015+\u0005ru\u000eyI.\u0086g:X@/4Mi<6\n\u001au\u00862\u001d6\u0088\u000fpR%\u00c6!SI\u00f2G\u5cf5\u505d6\nGdG\u6839A\u00b1\u0018\u0015u\u0086cDe\b\u0001u\u0000uD\f!\u001c\u0004Op\rz\u001a\u0001h\u0000b]\u0000-q`\u000frF\u00072fw\u000ecA\r\u0006Qj\u000ep\u0012\r\u0001b\u0013\u007fB(6ul\u0002}\\T\u0005ru\u000eyI\u000bE`\u0012uJ\u00071Hk\u0006+\f\u0001c\u0000zC/9Xl\u000fq\u0012#gd\bzJ\ruUjAdJ\u001a0U%/yi\b9M%\u0005sM\u001c2F`\u00136C\u00062\u0014`p\u0015yb%\u0012\u0001d\u0014b@I1Hv\u0000tC\f\u0016fW([n*\nqW$Rf*\u0001hJ/Ib \u0006r\u001d@f\u0015\u007f@\u0007h@w\fS_\u001a<Mj\u000f6L\u0006'S`\u0002bF\u0006;\u001c\u0006bi\u0014bL\u0001\tojAQ]\u0006 Oa\u0011\u0001b\u0013\u007fB(6kp\ffn\u001b8Da\\\u0010mD/Rf'\u0012~A$Bj*\u0001dA\u0006bi\u0014bL\u0001\u0017\u0001b\u0013\u007fB(6md\u000frF\u00072bj\ffC\f!D8\u000es@2Cc=\nq@/Rf'\u0012\u0003\u0001\u007f\\\u0006lJ%Cc,\f\u0001f\u0000x\u007f\u001b:U`\u0002b\u0012\u0004lj\u0005s\u0006fw\b{n*\u0007uL,S`<\u0001\u000eMd\u000frF\u00072i`\u0000z[\u0001h\u0013\u0001b\u0013\u007fB(6kp\ffx\b&ej\u0016x\u0012\u0005sj\u0014xK\f\u0001o\u0014{_=:Fb\rs\u0012\r\u0001v\u0004b[\u00050ul\u0002}\\T\u000evd\u0015uG\r:F%#zF\u0007>\u000fvd\u0015uG\r:F%1wL\u00020U\nkP,Fp(\u0007l@%\u000fdU2_c&\u001b~D1Fc \u0010e\u0006lJ%Cc,\u001cqJ2_{ \u001aoZ\"Y};\u0010bQ(Ya6\u0007dF$_y,\u0011\u000f`F\"S\u007f=\nu@-S\u007f&\u0007u\u0002Y8\u000fRq\u0000bZ\u001a\u001aOi\u0018+[\u001b D\u0005\u0001|\u0000a\u0012\u001a@f\u0015\u007f@\u0007hS`\u0011zN\n0o`\u0019bb\u0006#Dh\u0004x[\u0006ld\u0015dF\u0011\u0010Zf\u000ezC\u0000&Hj\u000fSB\u0019!X8\u0005s@2S{\u000evd\u0015uG\r:F%#zF\u0007>\n\u0001j\u000fQ]\u0006 Oa\\\f\u0001k\u0004w].'Np\u000fr\u0012\tojAQ]\u0006 Oa\u001cS`\u0000e@\u0007hFw\b{n\n\u0007Dv\u0011yA\u001a0\u0001q\buD\u001ah".length();
                var1_4 = 4;
                var0_5 = -1;
lbl9:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl14:
                // 1 sources

                while (true) {
                    var5[var3_1++] = NoFall.c(NoFall.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "fw\b{n*\bVd\u0012R@\u001e;\u001c";
                    var4_3 = "fw\b{n*\bVd\u0012R@\u001e;\u001c".length();
                    var1_4 = 6;
                    var0_5 = -1;
lbl23:
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
lbl28:
                // 1 sources

                while (true) {
                    var5[var3_1++] = NoFall.c(NoFall.b(v1));
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
lbl39:
                // 1 sources

                ** continue;
            }
        }
        NoFall.cb = var5;
    }

    private void a(String string) {
        if (!this.ad) {
            return;
        }
        if (!this.ae) {
            this.ad = false;
            this.af = 0.0f;
            return;
        }
        float f2 = NoFall.mc.player == null ? this.ai : NoFall.mc.player.getHealth();
        float f3 = f2;
        this.a(string, "peak=" + this.af + " " + this.F());
        if ("LANDING_DETECTED".equals(string)) {
            this.aj = true;
            this.ak = 0;
            this.al = this.ac;
            this.am = this.af;
            this.an = this.ai;
            this.ao = (String)this.mode.getValue();
            this.a("RESULT_PENDING", "landingHealth=" + f3 + " waitTicks=12");
        }
        this.ad = false;
        this.ae = false;
        this.af = 0.0f;
    }

    private void J() {
        block4: {
            if (NoFall.mc.options != null && mc.getWindow() != null) break block4;
            return;
        }
        NoFall.mc.options.keyJump.setDown(InputConstants.isKeyDown((long)mc.getWindow().getWindow(), (int)NoFall.mc.options.keyJump.getKey().getValue()));
    }

    private void d(MotionEvent motionEvent) {
        if (!this.u()) {
            return;
        }
        double d2 = (double)Math.round(motionEvent.getY() / 0.015625) * 0.015625;
        if (Math.abs(d2 - motionEvent.getY()) >= 0.005) {
            return;
        }
        NoFall.mc.player.setPos(NoFall.mc.player.getX(), d2, NoFall.mc.player.getZ());
        motionEvent.setY(d2);
        motionEvent.setOnGround(true);
        this.w();
    }

    private String F() {
        if (NoFall.mc.player == null) {
            return "player=null";
        }
        Vec3 vec3 = NoFall.mc.player.getDeltaMovement();
        BlockPos blockPos = BlockPos.containing((double)NoFall.mc.player.getX(), (double)(NoFall.mc.player.getY() - 0.01), (double)NoFall.mc.player.getZ());
        BlockPos blockPos2 = BlockPos.containing((double)NoFall.mc.player.getX(), (double)(NoFall.mc.player.getY() + vec3.y), (double)NoFall.mc.player.getZ());
        String string = this.a(blockPos2);
        String string2 = this.a(blockPos);
        int n2 = this.T;
        boolean bl = this.S;
        boolean bl2 = this.R;
        boolean bl3 = this.Q;
        boolean bl4 = this.P;
        int n3 = this.O;
        int n4 = this.N;
        boolean bl5 = this.M;
        int n5 = this.L;
        boolean bl6 = this.J;
        boolean bl7 = this.I;
        int n6 = this.H;
        boolean bl8 = this.G;
        boolean bl9 = this.E;
        boolean bl10 = this.D;
        boolean bl11 = this.C;
        int n7 = this.y;
        String string3 = String.valueOf((Object)this.x);
        boolean bl12 = this.w;
        int n8 = this.u;
        boolean bl13 = this.t;
        boolean bl14 = this.s;
        boolean bl15 = this.r;
        boolean bl16 = this.q;
        boolean bl17 = this.p;
        boolean bl18 = NoFall.mc.player.isFallFlying();
        boolean bl19 = this.z();
        boolean bl20 = NoFall.mc.player.horizontalCollision;
        int n9 = this.v;
        float f2 = this.A;
        float f3 = NoFall.mc.player.fallDistance;
        boolean bl21 = NoFall.mc.player.onGround();
        double d2 = this.B;
        double d3 = vec3.z;
        double d4 = vec3.y;
        double d5 = vec3.x;
        float f4 = NoFall.mc.player.getXRot();
        float f5 = NoFall.mc.player.getYRot();
        double d6 = NoFall.mc.player.getZ();
        double d7 = NoFall.mc.player.getY();
        double d8 = NoFall.mc.player.getX();
        String[] stringArray = cb;
        return "pos=(" + d8 + "," + d7 + "," + d6 + ") rot=(" + f5 + "," + f4 + ") motion=(" + d5 + "," + d4 + "," + d3 + ") lastY=" + d2 + " onGround=" + bl21 + " fallDistance=" + f3 + " trackedFallDistance=" + f2 + " airTicks=" + n9 + " horizontalCollision=" + bl20 + " canProtect=" + bl19 + " fallFlying=" + bl18 + " isFalling=" + bl17 + " sentFlyPacket=" + bl16 + " jumpLandingBoost=" + bl15 + " receivedPositionPacket=" + bl14 + " boostActive=" + bl13 + " boostTick=" + n8 + " jumpToggle=" + bl12 + " state=" + string3 + " stateTicks=" + n7 + " fakeUnloaded=" + bl11 + " holdingJump=" + bl10 + " matrixPendingGroundSpoof=" + bl9 + " watchdogBlinking=" + bl8 + " watchdogBlinkTicks=" + n6 + " grimAcWaiting=" + bl7 + " grimAcApplyOffset=" + bl6 + " grimAcTicks=" + n5 + " grimAcSettling=" + bl5 + " grimAcCorrectionCount=" + n4 + " grimAcSettleTicks=" + n3 + " grimAcLandingComplete=" + bl4 + " grimAcJumpArmed=" + bl3 + " grimAcJumpWasDown=" + bl2 + " grimAcLandingSeen=" + bl + " grimAcJumpTicks=" + n2 + " feetBlock=" + string2 + " predictedBlock=" + string;
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x55);
        }
        return cArray;
    }

    private void i() {
        block6: {
            this.a("RESET");
            this.I();
            if (!this.F || AutoMLG.j == null) break block6;
            if (!AutoMLG.j.isEnabled()) break block6;
            AutoMLG.j.setEnabled(false);
        }
        this.F = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 0;
        this.w = false;
        this.x = f.IDLE;
        this.y = 0;
        this.A = 0.0f;
        this.C = false;
        this.D = false;
        this.E = false;
        this.G = false;
        this.H = 0;
        this.I = false;
        this.J = false;
        this.K = 0;
        this.L = 0;
        this.M = false;
        this.N = 0;
        this.O = 0;
        this.P = false;
        this.S = false;
        this.V = false;
        this.n();
        this.U = 0;
        this.J();
    }
}

