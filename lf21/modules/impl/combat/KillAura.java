/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.EventTarget;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.event.impl.e;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.combat.AntiBots;
import lf21.modules.impl.movement.Scaffold;
import lf21.modules.impl.player.AntiTNT;
import lf21.modules.impl.player.AntiWeb;
import lf21.modules.impl.player.AutoWeb;
import lf21.modules.impl.player.Helper;
import lf21.modules.impl.player.MidPearl;
import lf21.modules.impl.player.Stuck;
import lf21.modules.impl.world.Teams;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.game.c;
import lf21.utils.game.d;
import lf21.utils.game.g;
import lf21.utils.game.l;
import lf21.utils.game.m;
import lf21.utils.render.RenderUtil;
import lf21.utils.rotation.RotationHandler;
import lf21.utils.rotation.a;
import net.minecraft.client.Camera;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class KillAura
extends Module {
    public final BooleanSetting preferBaby;
    public final NumberSetting O;
    public final NumberSetting J;
    public final BooleanSetting attackMobs;
    private static final String[] cb;
    public final BooleanSetting noSwing;
    public final BooleanSetting K;
    public final ModeSetting Y;
    private int am;
    public final NumberSetting V;
    public final BooleanSetting attackWhilstScaffolding;
    public final NumberSetting I;
    public static Entity l;
    public static Entity k;
    public final BooleanSetting moreParticles;
    public final NumberSetting F;
    private int aq;
    public final NumberSetting H;
    public final BooleanSetting multiAttack;
    public a ai;
    public final NumberSetting B;
    public final BooleanSetting subtickRaycast;
    public final NumberSetting C;
    public final ModeSetting S;
    public final BooleanSetting attackPlayer;
    public final ModeSetting Z;
    private Entity ak;
    private int ao;
    public final BooleanSetting attackAnimals;
    public final NumberSetting M;
    public final ModeSetting X;
    public final NumberSetting U;
    public static List<Entity> m;
    private int ap;
    public final BooleanSetting throughWalls;
    public final ModeSetting R;
    private int an;
    public final NumberSetting E;
    public final NumberSetting W;
    public final BooleanSetting rangeCircle;
    public final BooleanSetting autoDisable;
    public final BooleanSetting infinitySwitch;
    public static KillAura j;
    public final NumberSetting D;
    public final BooleanSetting keepSprint;
    public final BooleanSetting ignoreSkipTicks;
    public int aj;
    public final NumberSetting P;
    public final BooleanSetting attackInvisible;
    private final lf21.utils.render.c ar;
    public final BooleanSetting fakeAutoBlock;
    public final ModeSetting Q;
    public final NumberSetting A;
    public final NumberSetting G;
    public final BooleanSetting badPacketsCheck;
    public final NumberSetting T;
    public final NumberSetting L;
    public final BooleanSetting test;
    public final NumberSetting N;
    public final BooleanSetting rayCast;
    public final BooleanSetting silentRotations;
    private double al;

    private void a(PoseStack poseStack, Entity entity, float f2, boolean bl) {
        int n2 = ((LivingEntity)entity).hurtTime;
        Color color = n2 == 0 ? new Color(0, 0, 0, 130) : (n2 >= 9 ? new Color(0, 255, 255, 200) : new Color(255, 0, 0, 200));
        AABB aABB = c.b(entity, f2);
        AABB aABB2 = bl ? new AABB(aABB.minX, aABB.minY + (double)entity.getEyeHeight() + 0.11, aABB.minZ, aABB.maxX, aABB.maxY - 0.13, aABB.maxZ) : aABB.inflate(0.175, 0.125, 0.175);
        RenderUtil.b(aABB2, poseStack, color, color);
    }

    public void a(Entity entity) {
        if (entity == null || KillAura.mc.player == null || KillAura.mc.gameMode == null) {
            return;
        }
        if (!((Boolean)this.silentRotations.getValue()).booleanValue() && this.ai != null) {
            KillAura.mc.player.setYRot(this.ai.e());
            KillAura.mc.player.setXRot(this.ai.f());
        }
        KillAura.mc.gameMode.attack((Player)KillAura.mc.player, entity);
        if (!((Boolean)this.noSwing.getValue()).booleanValue()) {
            KillAura.mc.player.swing(InteractionHand.MAIN_HAND);
        }
        if (((Boolean)this.moreParticles.getValue()).booleanValue()) {
            KillAura.mc.player.crit(entity);
            KillAura.mc.player.magicCrit(entity);
        }
        ++this.am;
        String[] stringArray = cb;
        if (this.Q.is("1.9")) {
            this.ao = Math.max(1, (int)KillAura.mc.player.getCurrentItemAttackStrengthDelay());
        }
        if (this.X.is("Switch") && this.am >= ((Number)this.N.getValue()).intValue()) {
            this.am = 0;
            this.an = m.isEmpty() ? 0 : (this.an + 1) % m.size();
        }
    }

    private double o() {
        return Math.min(((Number)this.B.getValue()).doubleValue(), this.p());
    }

    private void a(a a2) {
        a a3;
        if (a2 == null) {
            this.ai = null;
            return;
        }
        a a4 = RotationHandler.b != null ? RotationHandler.b : (a3 = this.ai != null ? this.ai : new a(KillAura.mc.player.getYRot(), KillAura.mc.player.getXRot()));
        if (this.Z.is("Snap")) {
            this.ai = a2;
            return;
        }
        double d2 = Math.max(1.0, ((Number)this.C.getValue()).doubleValue());
        double d3 = Math.min(d2, Math.max(1.0, ((Number)this.D.getValue()).doubleValue()));
        this.ai = g.a(a3, a2, Mth.lerp((double)Math.random(), (double)d3, (double)d2));
        if (this.ai == null || !Float.isFinite(this.ai.e()) || !Float.isFinite(this.ai.f())) {
            this.ai = a2;
        }
    }

    public KillAura() {
        String[] stringArray = cb;
        super("KillAura", Category.COMBAT);
        this.attackPlayer = new BooleanSetting("Attack Player", true);
        this.attackInvisible = new BooleanSetting("Attack Invisible", false);
        this.attackAnimals = new BooleanSetting("Attack Animals", false);
        this.attackMobs = new BooleanSetting("Attack Mobs", true);
        this.multiAttack = new BooleanSetting("Multi Attack", true);
        this.infinitySwitch = new BooleanSetting("Infinity Switch", false);
        this.preferBaby = new BooleanSetting("Prefer Baby", false);
        this.moreParticles = new BooleanSetting("More Particles", false);
        this.keepSprint = new BooleanSetting("Keep Sprint", true);
        this.ignoreSkipTicks = new BooleanSetting("Ignore skip ticks", false);
        this.fakeAutoBlock = new BooleanSetting("Fake AutoBlock", true);
        this.test = new BooleanSetting("Test", false);
        this.rangeCircle = new BooleanSetting("Range Circle", false);
        this.A = new NumberSetting("Aim Range", 4.0, 1.0, 8.0, 0.1);
        this.B = new NumberSetting("Range", 3.0, 1.0, 6.0, 0.1);
        this.C = new NumberSetting("Max Rotation Speed", 90.0, 5.0, 180.0, 1.0);
        this.D = new NumberSetting("Min Rotation Speed", 55.0, 1.0, 180.0, 1.0);
        this.E = new NumberSetting("Prediction", 0.0, 0.0, 2.0, 0.1);
        this.F = new NumberSetting("Range Circle Red", 95, 0, 255, 1, () -> (Boolean)this.rangeCircle.getValue());
        this.G = new NumberSetting("Range Circle Green", 235, 0, 255, 1, () -> (Boolean)this.rangeCircle.getValue());
        this.H = new NumberSetting("Range Circle Blue", 255, 0, 255, 1, () -> (Boolean)this.rangeCircle.getValue());
        this.I = new NumberSetting("Range Circle Thickness", 2.0, 0.5, 6.0, 0.5, () -> (Boolean)this.rangeCircle.getValue());
        this.J = new NumberSetting("Attack Per Second", 10.0, 1.0, 20.0, 0.5);
        this.K = new BooleanSetting("Randomize APS", true);
        this.L = new NumberSetting("Min APS", 8.0, 1.0, 20.0, 0.5, () -> (Boolean)this.K.getValue());
        this.M = new NumberSetting("Switch Size", 3.0, 1.0, 10.0, 1.0, () -> (Boolean)this.infinitySwitch.getValue() == false);
        this.N = new NumberSetting("Switch Delay (Attack Times)", 1.0, 1.0, 10.0, 1.0);
        this.O = new NumberSetting("FoV", 360.0, 10.0, 360.0, 1.0);
        this.P = new NumberSetting("Hurt Time", 10.0, 0.0, 10.0, 1.0);
        this.Q = new ModeSetting("Delay Mode", "1.8", "1.9").withDefault("1.8");
        this.R = new ModeSetting("Priority", "Distance", "FoV", "Health", "Hurt Time", "None").withDefault("Distance");
        this.S = new ModeSetting("Target ESP", "None", "Spiral", "Orbit", "Box", "Tab").withDefault("None");
        this.T = new NumberSetting("Spiral Red", 255, 0, 255, 1, () -> this.S.is("Spiral"));
        this.U = new NumberSetting("Spiral Green", 80, 0, 255, 1, () -> this.S.is("Spiral"));
        this.V = new NumberSetting("Spiral Blue", 255, 0, 255, 1, () -> this.S.is("Spiral"));
        this.W = new NumberSetting("Spiral Speed", 1.0, 0.25, 4.0, 0.05, () -> this.S.is("Spiral"));
        this.X = new ModeSetting("Attack Mode", "Single", "Switch", "Multiple").withDefault("Single");
        this.Y = new ModeSetting("Auto Block", "None", "Fake", "Vanilla", "NCP", "Legit", "Grim", "Intave", "Old Intave", "Imperfect Vanilla", "Vanilla ReBlock", "Watchdog 1.12", "New NCP", "Universal", "Watchdog", "Dual Sword", "Watchdog 1.8").withDefault("Fake");
        this.Z = new ModeSetting("Rotation Mode", "Legit/Normal", "Snap", "NCP", "Grim").withDefault("Legit/Normal");
        this.rayCast = new BooleanSetting("Ray cast", true);
        this.subtickRaycast = new BooleanSetting("Subtick Raycast", true, () -> (Boolean)this.rayCast.getValue());
        this.throughWalls = new BooleanSetting("Through Walls", false);
        this.silentRotations = new BooleanSetting("Silent Rotations", true);
        this.attackWhilstScaffolding = new BooleanSetting("Attack whilst Scaffolding", true);
        this.noSwing = new BooleanSetting("No swing", false);
        this.autoDisable = new BooleanSetting("Auto disable", false);
        this.badPacketsCheck = new BooleanSetting("BadPackets check", true);
        this.ap = Integer.MIN_VALUE;
        this.aq = Integer.MIN_VALUE;
        this.ar = new lf21.utils.render.c();
        j = this;
    }

    public void a(PoseStack poseStack, Entity entity, float f2) {
        if (poseStack == null || entity == null || !this.v()) {
            return;
        }
        String string = (String)this.S.getValue();
        int n2 = -1;
        switch (string.hashCode()) {
            case -1812072687: {
                String[] stringArray = cb;
                if (!string.equals("Spiral")) break;
                n2 = 0;
                break;
            }
            case 76451882: {
                String[] stringArray = cb;
                if (!string.equals("Orbit")) break;
                n2 = 1;
                break;
            }
            case 66987: {
                String[] stringArray = cb;
                if (!string.equals("Box")) break;
                n2 = 2;
                break;
            }
            case 83829: {
                if (!string.equals("Tab")) break;
                n2 = 3;
            }
        }
        switch (n2) {
            case 0: {
                RenderUtil.a(poseStack, entity, f2, ((Number)this.T.getValue()).intValue(), ((Number)this.U.getValue()).intValue(), ((Number)this.V.getValue()).intValue(), ((Number)this.W.getValue()).floatValue());
                break;
            }
            case 1: {
                RenderUtil.a(poseStack, entity, f2);
                break;
            }
            case 2: {
                this.a(poseStack, entity, f2, false);
                break;
            }
            case 3: {
                this.a(poseStack, entity, f2, true);
                break;
            }
        }
    }

    public void j() {
        m = this.n();
    }

    private List<Entity> n() {
        ArrayList<Entity> arrayList;
        block19: {
            block18: {
                String[] stringArray;
                Object object2;
                arrayList = new ArrayList<Entity>();
                if (KillAura.mc.player == null || KillAura.mc.level == null) {
                    return arrayList;
                }
                for (Object object2 : KillAura.mc.level.entitiesForRendering()) {
                    if (!this.f((Entity)object2)) continue;
                    arrayList.add((Entity)object2);
                }
                object2 = (String)this.R.getValue();
                int n2 = -1;
                switch (((String)object2).hashCode()) {
                    case -2137395588: {
                        stringArray = cb;
                        if (!((String)object2).equals("Health")) break;
                        n2 = 0;
                        break;
                    }
                    case -2087977922: {
                        stringArray = cb;
                        if (!((String)object2).equals("Hurt Time")) break;
                        n2 = 1;
                        break;
                    }
                    case 70797: {
                        stringArray = cb;
                        if (!((String)object2).equals("FoV")) break;
                        n2 = 2;
                        break;
                    }
                    case 2433880: {
                        stringArray = cb;
                        if (!((String)object2).equals("None")) break;
                        n2 = 3;
                    }
                }
                Comparator<Entity> comparator = switch (n2) {
                    case 0 -> Comparator.comparingDouble(entity -> ((LivingEntity)entity).getHealth());
                    case 1 -> Comparator.comparingInt(entity -> ((LivingEntity)entity).hurtTime);
                    case 2 -> Comparator.comparingDouble(entity -> g.b(KillAura.mc.player.getYRot(), g.c(entity).e()));
                    case 3 -> Comparator.comparingInt(Entity::getId);
                    default -> Comparator.comparingDouble(entity -> this.a(entity.getBoundingBox()));
                };
                arrayList.sort(comparator);
                if (((Boolean)this.preferBaby.getValue()).booleanValue() && arrayList.stream().anyMatch(entity -> ((LivingEntity)entity).isBaby())) {
                    arrayList.removeIf(entity -> !((LivingEntity)entity).isBaby());
                }
                if (((Boolean)this.infinitySwitch.getValue()).booleanValue()) break block18;
                stringArray = cb;
                if (!this.X.is("Multiple")) break block19;
            }
            return arrayList;
        }
        int n3 = this.X.is("Single") ? 1 : ((Number)this.M.getValue()).intValue();
        return new ArrayList<Entity>(arrayList.subList(0, Math.min(n3, arrayList.size())));
    }

    public boolean v() {
        return !this.S.is("None");
    }

    private boolean a(Entity entity, a a2) {
        BlockHitResult blockHitResult;
        if (a2 == null) {
            return false;
        }
        Vec3 vec3 = KillAura.mc.player.getEyePosition();
        Vec3 vec32 = vec3.add(g.b(a2).scale(this.o() + 0.05));
        Optional optional = entity.getBoundingBox().clip(vec3, vec32);
        if (optional.isEmpty()) {
            return false;
        }
        if (!((Boolean)this.throughWalls.getValue()).booleanValue() && (blockHitResult = KillAura.mc.level.clip(new ClipContext(vec3, (Vec3)optional.get(), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)KillAura.mc.player))).getType() != HitResult.Type.MISS && blockHitResult.getLocation().distanceToSqr(vec3) + 1.0E-5 < ((Vec3)optional.get()).distanceToSqr(vec3)) {
            return false;
        }
        return ((Vec3)optional.get()).distanceTo(vec3) <= this.o() + 0.05;
    }

    public Entity l() {
        return this.k();
    }

    private void t() {
        this.aj = 0;
        this.ao = 0;
        this.am = 0;
        this.an = 0;
        this.al = 0.0;
        this.aq = Integer.MIN_VALUE;
        this.ap = Integer.MIN_VALUE;
        this.u();
    }

    public Entity k() {
        return this.ak != null && this.f(this.ak) ? this.ak : (m.isEmpty() ? null : m.get(0));
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
                    case 0 -> 68;
                    case 1 -> 53;
                    case 2 -> 119;
                    case 3 -> 73;
                    case 4 -> 61;
                    case 5 -> 108;
                    default -> 38;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    @Override
    protected boolean e() {
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventTarget
    public void a(RenderEvent renderEvent) {
        boolean bl;
        float f2 = this.ar.a(this.isEnabled() && (Boolean)this.rangeCircle.getValue() != false);
        boolean bl2 = f2 > 0.0f;
        boolean bl3 = bl = this.isEnabled() && !this.S.is("None");
        if (!bl2 && !bl || KillAura.mc.gameRenderer == null || KillAura.mc.player == null) {
            return;
        }
        PoseStack poseStack = renderEvent.a();
        poseStack.pushPose();
        try {
            Entity entity;
            Camera camera = KillAura.mc.gameRenderer.getMainCamera();
            Vec3 vec3 = camera.getPosition();
            poseStack.translate(-vec3.x(), -vec3.y(), -vec3.z());
            if (bl2) {
                RenderUtil.a(poseStack, (Entity)KillAura.mc.player, renderEvent.b(), (float)this.p() * this.ar.b(), ((Number)this.F.getValue()).intValue(), ((Number)this.G.getValue()).intValue(), ((Number)this.H.getValue()).intValue(), ((Number)this.I.getValue()).floatValue(), this.ar.a());
            }
            if ((entity = l) == null || !bl) {
                return;
            }
            this.a(poseStack, entity, renderEvent.b());
        }
        finally {
            poseStack.popPose();
        }
    }

    private boolean h() {
        return this.a(false);
    }

    public void a() {
        if (this.h() && this.Q.is("1.8")) {
            this.al = Math.max(0.0, this.al - 1.0);
        }
    }

    private boolean s() {
        return AutoWeb.j != null && AutoWeb.j.isEnabled() && AutoWeb.k != null;
    }

    public boolean e(Entity entity) {
        if (!LF21Client.isReady() || entity == null || entity == KillAura.mc.player || entity instanceof m || !(entity instanceof LivingEntity)) {
            return false;
        }
        LivingEntity livingEntity = (LivingEntity)entity;
        if (AntiBots.j != null && AntiBots.j.isEnabled() && (AntiBots.b(entity) || AntiBots.a(entity))) {
            return false;
        }
        if (!livingEntity.isAlive() || livingEntity.isDeadOrDying() || livingEntity.getHealth() <= 0.0f || entity instanceof ArmorStand) {
            return false;
        }
        if (entity.isInvisible() && !((Boolean)this.attackInvisible.getValue()).booleanValue() || Teams.a(entity)) {
            return false;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            return (Boolean)this.attackPlayer.getValue() != false && !player.isSpectator() && !player.isSleeping() && player.getBbWidth() >= 0.5f;
        }
        if (entity instanceof Animal || entity instanceof Squid || entity instanceof Villager) {
            return (Boolean)this.attackAnimals.getValue();
        }
        if (entity instanceof Mob || entity instanceof Slime || entity instanceof Bat || entity instanceof AbstractGolem) {
            return (Boolean)this.attackMobs.getValue();
        }
        return false;
    }

    private void i() {
        if (m.isEmpty()) {
            this.ak = null;
            this.an = 0;
            return;
        }
        if (!this.X.is("Switch")) {
            this.an = 0;
        }
        if (this.an >= m.size()) {
            this.an = 0;
        }
        this.ak = m.get(this.an);
    }

    private boolean c(Entity entity) {
        if (!((Boolean)this.rayCast.getValue()).booleanValue()) {
            return this.a(entity.getBoundingBox()) <= this.o();
        }
        a a2 = RotationHandler.e && RotationHandler.a != null ? RotationHandler.a : this.ai;
        return this.a(entity, a2);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[96];
                var3_1 = 0;
                var2_2 = "\u0005A\u0003(^\u0007\u0006\u0014P\u0005in\tE+[\u0013\u0003u\u001bO\u0006\u0017E\u001e;\\\u0000\u0005\u000bG\u0015 I\u0011\u0016T\u0019.XLe-G\u0014%XLd(@\u0012\u0003\u0002Z!\r\u0013T\u0003*U\bI#\u0015Fg\f^\u0004\u0003G\u001e$\u000e\u0002T\u001c,\u001d-S0Z5%R\u000fM\n\u0010T\u0005.X\u0018\u0006\u0001f'\u0003\u0006Z\u000f\u0003\u0010T\u0015\b\t@\u001b=T\u001cJ!\f\t@\u001b=TLg0A\u0016*V\u0007\nP\u0000is/v\u0003\u0006Z\u000f\t\f@\u0005=\u001d8O)P\u0007\u0012T\u0019 Q\u0000G\u0011\rR\u0019&O\t\u00067^\u001e9\u001d\u0018O'^\u0004\t\f@\u0005=\u001d8O)P\u0003u\u001bN\u0003\u0002Z!\u000b\u000fP\u00129\u001d?V6\\\u0019=\u0012\tT\u000fio\u0003R%A\u001e&SLu4P\u0012-\u0010\u0016T\u0019.XLe-G\u0014%XLt!Q\u001b\u0017B\u001e=^\u0004\u0006\u0000P\u001b(DL\u000e\u0005A\u0003(^\u0007\u0006\u0010\\\u001a,NE\u000b\u0005A\u0003(^\u0007\u0006\tZ\u0015:\b\u0000\\\u0004=\\\u0002E!\u000f\u0017@\u0015=T\u000fMdg\u00160^\rU0\u0003\u0010T\u0015\f\u0005@\u0003&\u001d\bO7T\u0015%X\t\u0011[\u001e?X\u001eU%Y\u000b\u0017B\u001e=^\u0004\u0006\u0017\\\r,\u0003u\u001bN\r\u0016Z\u0003(I\u0005I*\u0015:&Y\t\u0006\fP\u0016%I\u0004\f\u0017E\u001e;\\\u0000\u0006\u0017E\u0012,Y\t\u0005\\\u001aio\rH#P\b\u0013T\u0003*U\bI#\u0019\u0005A\u0003(^\u0007\u00063]\u001e%N\u0018\u0006\u0017V\u0016/[\u0003J \\\u0019.\u0006\fP\u0016%I\u0004\u000b\u0005A\u0003(^\u0007\u0006\tZ\u0013,\n\u000bY\u0013it\u0002R%C\u0012\n\u0017E\u001e;\\\u0000\u0006\u0016P\u0013\u0006\u0017E\u001e;\\\u0000\u0006\r[\u0003(K\t\f\bP\u0010 ICh+G\u001a(Q\u0006\u0017B\u001e=^\u0004\b\u0000\\\u0004=\\\u0002E!\u000f\u0012T\u0019 Q\u0000Gdg\u0012\u000bQ\u0003E/\u0004\u0002T\u001c,\f\u0013T\u0003*U\bI#\u0015Fg\u0005\u000e\tZ\u0005,\u001d<G6A\u001e*Q\tU\u0010\u0017\\\u001b,S\u0018\u0006\u0016Z\u0003(I\u0005I*F\u000b\u0017E\u001e;\\\u0000\u0006\u0006Y\u0002,\r\u0010]\u0005&H\u000bNdb\u0016%Q\u001f\f\bP\u0010 ICh+G\u001a(Q\n\u0000P\u001b(DLk+Q\u0012\u0007\t\\\u0019i|<u\u0004\nZ\u0019,\u0003u\u001bO\u0012\t\\\u0019io\u0003R%A\u001e&SLu4P\u0012-\b\u0014G\u001e&O\u0005R=\u000e\u0005A\u0003(^\u0007\u0006\u0005[\u001e$\\\u0000U\u0005\u0016T\u0019.X\u0003\nv'\b\t@\u001b=T\u001cJ!\u000f\r[\u0011 S\u0005R=\u0015$>T\u0018E,\b\u0016T\u000ei^\rU0\r\u0005A\u0003(^\u0007\u0006\u0014Y\u00160X\u001e\n\u0014G\u0012-T\u000fR-Z\u0019\u0003\nv'\u0006\u0017\\\u0019.Q\t\u0004\u0010P\u0004=\u0011\rX\u0007,O\nC'AW\u001f\\\u0002O(Y\u0016\n\u0005@\u0003&\u001d.J+V\u001c\f\u0016T\u0019.XLe-G\u0014%X\u0004\nZ\u0019,\r\u0016T\u0019-R\u0001O>PW\bm?\u0006\u0017B\u001e=^\u0004\u0010\u0005A\u0003(^\u0007\u0006\r[\u0001 N\u0005D(P\u0016\u0016T\u0019.XLe-G\u0014%XLr,\\\u0014\"S\tU7\u0012\u0016T\u0019.XLe-G\u0014%XLa6P\u0012'\u0010\u0006T\u0013\u0019\\\u000fM!A\u0004i^\u0004C'^\u0004\u0003G\u001e$\b\u000f\\\u001b%|\u0019T%\f\u0017E\u001e;\\\u0000\u0006\u0003G\u0012,S\u000b\u0014G\u0012/X\u001e\u0006\u0006T\u00150\u0005\u000bG\u0015 I\u0006\u0017\\\u0019.Q\t\u0004\u0002T\u001c,\u0004\u0017[\u00169\b\nZW:J\u0005H#\u0005\bP\u0010 I";
                var4_3 = "\u0005A\u0003(^\u0007\u0006\u0014P\u0005in\tE+[\u0013\u0003u\u001bO\u0006\u0017E\u001e;\\\u0000\u0005\u000bG\u0015 I\u0011\u0016T\u0019.XLe-G\u0014%XLd(@\u0012\u0003\u0002Z!\r\u0013T\u0003*U\bI#\u0015Fg\f^\u0004\u0003G\u001e$\u000e\u0002T\u001c,\u001d-S0Z5%R\u000fM\n\u0010T\u0005.X\u0018\u0006\u0001f'\u0003\u0006Z\u000f\u0003\u0010T\u0015\b\t@\u001b=T\u001cJ!\f\t@\u001b=TLg0A\u0016*V\u0007\nP\u0000is/v\u0003\u0006Z\u000f\t\f@\u0005=\u001d8O)P\u0007\u0012T\u0019 Q\u0000G\u0011\rR\u0019&O\t\u00067^\u001e9\u001d\u0018O'^\u0004\t\f@\u0005=\u001d8O)P\u0003u\u001bN\u0003\u0002Z!\u000b\u000fP\u00129\u001d?V6\\\u0019=\u0012\tT\u000fio\u0003R%A\u001e&SLu4P\u0012-\u0010\u0016T\u0019.XLe-G\u0014%XLt!Q\u001b\u0017B\u001e=^\u0004\u0006\u0000P\u001b(DL\u000e\u0005A\u0003(^\u0007\u0006\u0010\\\u001a,NE\u000b\u0005A\u0003(^\u0007\u0006\tZ\u0015:\b\u0000\\\u0004=\\\u0002E!\u000f\u0017@\u0015=T\u000fMdg\u00160^\rU0\u0003\u0010T\u0015\f\u0005@\u0003&\u001d\bO7T\u0015%X\t\u0011[\u001e?X\u001eU%Y\u000b\u0017B\u001e=^\u0004\u0006\u0017\\\r,\u0003u\u001bN\r\u0016Z\u0003(I\u0005I*\u0015:&Y\t\u0006\fP\u0016%I\u0004\f\u0017E\u001e;\\\u0000\u0006\u0017E\u0012,Y\t\u0005\\\u001aio\rH#P\b\u0013T\u0003*U\bI#\u0019\u0005A\u0003(^\u0007\u00063]\u001e%N\u0018\u0006\u0017V\u0016/[\u0003J \\\u0019.\u0006\fP\u0016%I\u0004\u000b\u0005A\u0003(^\u0007\u0006\tZ\u0013,\n\u000bY\u0013it\u0002R%C\u0012\n\u0017E\u001e;\\\u0000\u0006\u0016P\u0013\u0006\u0017E\u001e;\\\u0000\u0006\r[\u0003(K\t\f\bP\u0010 ICh+G\u001a(Q\u0006\u0017B\u001e=^\u0004\b\u0000\\\u0004=\\\u0002E!\u000f\u0012T\u0019 Q\u0000Gdg\u0012\u000bQ\u0003E/\u0004\u0002T\u001c,\f\u0013T\u0003*U\bI#\u0015Fg\u0005\u000e\tZ\u0005,\u001d<G6A\u001e*Q\tU\u0010\u0017\\\u001b,S\u0018\u0006\u0016Z\u0003(I\u0005I*F\u000b\u0017E\u001e;\\\u0000\u0006\u0006Y\u0002,\r\u0010]\u0005&H\u000bNdb\u0016%Q\u001f\f\bP\u0010 ICh+G\u001a(Q\n\u0000P\u001b(DLk+Q\u0012\u0007\t\\\u0019i|<u\u0004\nZ\u0019,\u0003u\u001bO\u0012\t\\\u0019io\u0003R%A\u001e&SLu4P\u0012-\b\u0014G\u001e&O\u0005R=\u000e\u0005A\u0003(^\u0007\u0006\u0005[\u001e$\\\u0000U\u0005\u0016T\u0019.X\u0003\nv'\b\t@\u001b=T\u001cJ!\u000f\r[\u0011 S\u0005R=\u0015$>T\u0018E,\b\u0016T\u000ei^\rU0\r\u0005A\u0003(^\u0007\u0006\u0014Y\u00160X\u001e\n\u0014G\u0012-T\u000fR-Z\u0019\u0003\nv'\u0006\u0017\\\u0019.Q\t\u0004\u0010P\u0004=\u0011\rX\u0007,O\nC'AW\u001f\\\u0002O(Y\u0016\n\u0005@\u0003&\u001d.J+V\u001c\f\u0016T\u0019.XLe-G\u0014%X\u0004\nZ\u0019,\r\u0016T\u0019-R\u0001O>PW\bm?\u0006\u0017B\u001e=^\u0004\u0010\u0005A\u0003(^\u0007\u0006\r[\u0001 N\u0005D(P\u0016\u0016T\u0019.XLe-G\u0014%XLr,\\\u0014\"S\tU7\u0012\u0016T\u0019.XLe-G\u0014%XLa6P\u0012'\u0010\u0006T\u0013\u0019\\\u000fM!A\u0004i^\u0004C'^\u0004\u0003G\u001e$\b\u000f\\\u001b%|\u0019T%\f\u0017E\u001e;\\\u0000\u0006\u0003G\u0012,S\u000b\u0014G\u0012/X\u001e\u0006\u0006T\u00150\u0005\u000bG\u0015 I\u0006\u0017\\\u0019.Q\t\u0004\u0002T\u001c,\u0004\u0017[\u00169\b\nZW:J\u0005H#\u0005\bP\u0010 I".length();
                var1_4 = 17;
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
                    var5[var3_1++] = KillAura.c(KillAura.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0000@\u0016%\u001d?Q+G\u0013\u0004\u0017[\u00169";
                    var4_3 = "\u0000@\u0016%\u001d?Q+G\u0013\u0004\u0017[\u00169".length();
                    var1_4 = 10;
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
                    var5[var3_1++] = KillAura.c(KillAura.a(v1));
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
        KillAura.cb = var5;
        KillAura.m = new ArrayList<Entity>();
    }

    @Override
    protected void onDisable() {
        this.t();
    }

    @EventTarget
    public void a(e e2) {
        this.t();
        this.ar.a(0.0f);
        if (((Boolean)this.autoDisable.getValue()).booleanValue()) {
            this.setEnabled(false);
        }
    }

    @Override
    protected void onEnable() {
        if (this.S.getValue() == null || !List.of(this.S.getModes()).contains(this.S.getValue())) {
            this.S.setValue("None");
        }
        this.t();
    }

    private double p() {
        return Math.max(1.0, ((Number)this.A.getValue()).doubleValue());
    }

    private boolean q() {
        return KillAura.mc.player == null || KillAura.mc.level == null || KillAura.mc.screen instanceof AbstractContainerScreen || d.a() || Stuck.j != null && Stuck.j.isEnabled() || Helper.j != null && Helper.j.isEnabled() && Helper.k != null || AntiWeb.k != null || AntiTNT.p != null || MidPearl.k != null || this.s();
    }

    private void u() {
        this.ak = null;
        l = null;
        k = null;
        this.ai = null;
        m.clear();
    }

    private boolean r() {
        return this.q() || !KillAura.mc.player.getUseItem().isEmpty() || KillAura.mc.screen != null || (Boolean)this.badPacketsCheck.getValue() != false && (Boolean)this.ignoreSkipTicks.getValue() == false && !ClientBase.delayPackets.isEmpty();
    }

    public boolean f(Entity entity) {
        if (KillAura.mc.player == null || !this.e(entity)) {
            return false;
        }
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            if (livingEntity.hurtTime > ((Number)this.P.getValue()).intValue()) {
                return false;
            }
        }
        if (this.a(entity.getBoundingBox()) > this.p()) {
            return false;
        }
        if (!g.a(entity, ((Number)this.O.getValue()).floatValue() * 0.5f)) {
            return false;
        }
        return (Boolean)this.throughWalls.getValue() != false || KillAura.mc.player.hasLineOfSight(entity);
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (!this.isEnabled()) {
            return;
        }
        if (!LF21Client.isReady() || this.q()) {
            this.u();
            return;
        }
        if (KillAura.mc.player.tickCount == this.ap) {
            return;
        }
        this.ap = KillAura.mc.player.tickCount;
        this.j();
        this.i();
        k = l = this.ak;
        if (this.ak == null) {
            this.ai = null;
            this.al = Math.min(this.al, 1.0);
            return;
        }
        this.a(this.b(this.ak));
        if (this.ao > 0) {
            --this.ao;
        }
        String[] stringArray = cb;
        if (this.Q.is("1.8")) {
            double d2 = Math.max(1.0, ((Number)this.J.getValue()).doubleValue());
            double d3 = Math.min(d2, Math.max(1.0, ((Number)this.L.getValue()).doubleValue()));
            double d4 = (Boolean)this.K.getValue() != false ? Mth.lerp((double)Math.random(), (double)d3, (double)d2) : d2;
            this.al = Math.min(2.0, this.al + d4 / 20.0);
        }
        if (this.r() || !this.d(this.ak)) {
            return;
        }
        if (this.Q.is("1.9")) {
            if (this.ao == 0 && KillAura.mc.player.getAttackStrengthScale(0.0f) >= 0.9f) {
                this.a(true);
            }
        } else if (this.al >= 1.0 && this.a(true)) {
            this.al -= 1.0;
        }
    }

    public boolean m() {
        if (!this.isEnabled() || !((Boolean)this.attackWhilstScaffolding.getValue()).booleanValue() || this.ak == null || this.ai == null || this.r() || !this.f(this.ak)) {
            return false;
        }
        if (this.a(this.ak.getBoundingBox()) > this.o() + 0.05) {
            return false;
        }
        return this.Q.is("1.9") ? this.ao == 0 && KillAura.mc.player.getAttackStrengthScale(0.0f) >= 0.9f : this.al >= 1.0;
    }

    private boolean a(boolean bl) {
        if (this.ak == null || KillAura.mc.player == null || KillAura.mc.gameMode == null || this.r()) {
            return false;
        }
        if (KillAura.mc.player.tickCount == this.aq) {
            return false;
        }
        if (!((Boolean)this.attackWhilstScaffolding.getValue()).booleanValue() && Scaffold.INSTANCE != null && Scaffold.INSTANCE.isEnabled()) {
            return false;
        }
        String[] stringArray = cb;
        List<Entity> list = this.X.is("Multiple") ? new ArrayList<Entity>(m) : List.of(this.ak);
        int n2 = this.X.is("Multiple") && (Boolean)this.multiAttack.getValue() != false ? 3 : 1;
        boolean bl2 = false;
        for (Entity entity : list) {
            if (!this.f(entity)) continue;
            if (bl) {
                if (!this.d(entity)) continue;
            } else if (!this.c(entity)) continue;
            this.a(entity);
            bl2 = true;
            if (--n2 > 0) continue;
            break;
        }
        if (bl2) {
            this.aq = KillAura.mc.player.tickCount;
        }
        return bl2;
    }

    private double a(AABB aABB) {
        return g.a(KillAura.mc.player.getEyePosition(), aABB).distanceTo(KillAura.mc.player.getEyePosition());
    }

    private a b(Entity entity) {
        l l2 = g.a(entity, this.p());
        if (l2 != null && l2.d() != null) {
            return l2.d();
        }
        AABB aABB = entity.getBoundingBox().move(entity.getDeltaMovement().scale(((Number)this.E.getValue()).doubleValue()));
        Vec3 vec3 = KillAura.mc.player.getEyePosition();
        return g.a(vec3, g.a(vec3, aABB)).a(Float.valueOf(((Double)KillAura.mc.options.sensitivity().get()).floatValue()));
    }

    private boolean d(Entity entity) {
        if (!((Boolean)this.rayCast.getValue()).booleanValue()) {
            return this.a(entity.getBoundingBox()) <= this.o();
        }
        return this.a(entity, RotationHandler.c);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x26);
        }
        return cArray;
    }
}

