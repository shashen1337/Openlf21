/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.event.EventTarget;
import lf21.event.impl.GlRenderEvent;
import lf21.event.impl.RenderEvent;
import lf21.event.impl.TickEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.modules.impl.movement.Scaffold;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.G;
import lf21.render.R;
import lf21.render.h_0;
import lf21.render.l_0;
import lf21.settings.impl.BooleanSetting;
import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Compass
extends Module {
    private float n;
    private static final String[] q;
    private double o;
    private boolean l;
    private final BooleanSetting compassOnly;
    private final BooleanSetting noPlayerOnly;
    private BlockPos m;
    private double p;

    @EventTarget
    public void a(RenderEvent renderEvent) {
        if (Compass.mc.player == null) {
            return;
        }
        this.o = Mth.lerp((double)renderEvent.b(), (double)Compass.mc.player.xOld, (double)Compass.mc.player.getX());
        this.p = Mth.lerp((double)renderEvent.b(), (double)Compass.mc.player.zOld, (double)Compass.mc.player.getZ());
        Camera camera = Compass.mc.gameRenderer != null ? Compass.mc.gameRenderer.getMainCamera() : null;
        this.n = camera != null && camera.isInitialized() ? Mth.wrapDegrees((float)camera.getYRot()) : Mth.rotLerp((float)renderEvent.b(), (float)Compass.mc.player.yRotO, (float)Compass.mc.player.getYRot());
    }

    public Compass() {
        String[] stringArray = q;
        super("Compass", Category.RENDER);
        this.compassOnly = new BooleanSetting("Compass Only", true);
        this.noPlayerOnly = new BooleanSetting("No Player Only", true);
        this.l = false;
    }

    @EventTarget
    public void a(GlRenderEvent glRenderEvent) {
        if (Compass.mc.player == null) {
            return;
        }
        if (Scaffold.INSTANCE.isEnabled()) {
            return;
        }
        this.a(glRenderEvent.c());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(DrawContext drawContext) {
        if (Scaffold.INSTANCE != null && Scaffold.INSTANCE.isEnabled()) {
            return;
        }
        if (!this.l && ((Boolean)this.compassOnly.getValue()).booleanValue()) {
            return;
        }
        if (this.h() && ((Boolean)this.noPlayerOnly.getValue()).booleanValue()) {
            return;
        }
        if (this.m == null) {
            return;
        }
        float f2 = Mth.wrapDegrees((float)((float)(Math.toDegrees(Math.atan2((double)this.m.getZ() - this.p, (double)this.m.getX() - this.o)) - 90.0 - (double)this.n)));
        float f3 = (float)mc.getWindow().getGuiScaledWidth() / 2.0f;
        float f4 = (float)mc.getWindow().getGuiScaledHeight() / 2.0f;
        drawContext.d();
        drawContext.a(f3, f4);
        drawContext.a(f2);
        try (G g2 = new G();){
            g2.a(-6.0f, -30.0f);
            g2.b(0.0f, -36.0f);
            g2.b(6.0f, -30.0f);
            try (D d2 = new D();){
                d2.a(true);
                d2.a(-2130706433);
                d2.a(h_0.STROKE);
                d2.b(4.0f);
                d2.b(R.a(4.0f, 4.0f, (Object)l_0.OUTLINE));
                drawContext.a(g2, d2);
            }
            d2 = new D();
            try {
                d2.a(true);
                d2.a(-1);
                d2.a(h_0.STROKE);
                d2.b(2.0f);
                drawContext.a(g2, d2);
            }
            finally {
                d2.close();
            }
        }
        drawContext.e();
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
                    case 0 -> 71;
                    case 1 -> 82;
                    case 2 -> 2;
                    case 3 -> 28;
                    case 4 -> 48;
                    case 5 -> 117;
                    default -> 36;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "\t=\"L\\\u0014]\" \"S^\u0019]\f\u0004=olQ\u0006Wg\u001dlpI\u0007\u0004=olQ\u0006W";
        int n3 = "\t=\"L\\\u0014]\" \"S^\u0019]\f\u0004=olQ\u0006Wg\u001dlpI\u0007\u0004=olQ\u0006W".length();
        int n4 = 14;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = Compass.c(Compass.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                q = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    @EventTarget
    public void a(TickEvent tickEvent) {
        if (Compass.mc.player == null || Compass.mc.level == null) {
            return;
        }
        this.l = this.i();
        this.m = this.a();
    }

    private boolean i() {
        if (Compass.mc.player == null) {
            return false;
        }
        for (ItemStack itemStack : Compass.mc.player.getInventory().items) {
            if (itemStack.getItem() != Items.COMPASS) continue;
            return true;
        }
        return ((ItemStack)Compass.mc.player.getInventory().offhand.get(0)).getItem() == Items.COMPASS;
    }

    private BlockPos a() {
        if (Compass.mc.level == null) {
            return null;
        }
        return Compass.mc.level.dimensionType().natural() ? Compass.mc.level.getSharedSpawnPos() : null;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x24);
        }
        return cArray;
    }

    private boolean h() {
        if (Compass.mc.level == null) {
            return false;
        }
        for (Entity entity : Compass.mc.level.entitiesForRendering()) {
            if (entity == Compass.mc.player || !(entity instanceof Player)) continue;
            return true;
        }
        return false;
    }
}

