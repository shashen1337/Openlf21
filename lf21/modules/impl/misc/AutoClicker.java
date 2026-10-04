/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.misc;

import com.mojang.blaze3d.platform.InputConstants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lf21.event.EventTarget;
import lf21.event.impl.SprintEvent;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.render.ad;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.h;
import lf21.utils.game.i;
import lf21.utils.misc.ReflectionUtil;
import lf21.utils.misc.l;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.ForgeHooksClient;

public class AutoClicker
extends Module {
    private final BooleanSetting breakBlock;
    private static final String[] q;
    private final h o;
    private final ModeSetting clickMode;
    private final NumberSetting cPS;
    private final ModeSetting mode;
    private final ModeSetting cPSMode;
    private final h p;

    private void h() {
        try {
            String[] stringArray = q;
            Class[] classArray = new Class[]{};
            Class<?> clazz = mc.getClass();
            Method method = clazz.getDeclaredMethod(ad.b(ReflectionUtil.getMappedMethodName(mc.getClass(), "startUseItem", "()V"), clazz, classArray), classArray);
            l.a(method);
            method.invoke(mc, new Object[0]);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException reflectiveOperationException) {
            throw new RuntimeException(reflectiveOperationException);
        }
    }

    public AutoClicker() {
        String[] stringArray = q;
        super("AutoClicker", Category.MISC);
        this.cPS = new NumberSetting("CPS", 7, 4, 25, 1);
        this.mode = new ModeSetting("Mode", "Left", "Right", "Both").withDefault("Left");
        this.clickMode = new ModeSetting("Click Mode", "Method", "Key", "Mouse").withDefault("Key");
        this.cPSMode = new ModeSetting("CPS Mode", "Normal", "DBC").withDefault("Normal");
        this.breakBlock = new BooleanSetting("Break Block", true);
        this.o = new h();
        this.p = new h();
    }

    private void i() {
        try {
            String[] stringArray = q;
            Class[] classArray = new Class[]{};
            Class<?> clazz = mc.getClass();
            Method method = clazz.getDeclaredMethod(ad.b(ReflectionUtil.getMappedMethodName(mc.getClass(), "startAttack", "()V"), clazz, classArray), classArray);
            l.a(method);
            method.invoke(mc, new Object[0]);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException reflectiveOperationException) {
            throw new RuntimeException(reflectiveOperationException);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void j() {
        BlockItem blockItem;
        Object object;
        if (AutoClicker.mc.player.isUsingItem() || !(AutoClicker.mc.hitResult instanceof BlockHitResult) || !((object = AutoClicker.mc.player.getMainHandItem().getItem()) instanceof BlockItem) || (blockItem = (BlockItem)object).getBlock() instanceof DoorBlock || !AutoClicker.mc.options.keyUse.isDown() || AutoClicker.mc.screen != null || !this.p.c(i.a((String)this.cPSMode.getValue(), ((Number)this.cPS.getValue()).doubleValue()))) {
            return;
        }
        object = (String)this.clickMode.getValue();
        int n2 = -1;
        switch (((String)object).hashCode()) {
            case -1993687807: {
                if (!((String)object).equals("Method")) break;
                n2 = 0;
                break;
            }
            case 75327: {
                String[] stringArray = q;
                if (!((String)object).equals("Key")) break;
                n2 = 1;
                break;
            }
            case 74534021: {
                if (!((String)object).equals("Mouse")) break;
                n2 = 2;
                break;
            }
        }
        switch (n2) {
            case 0: {
                this.h();
                break;
            }
            case 1: {
                KeyMapping.click((InputConstants.Key)AutoClicker.mc.options.keyUse.getKey());
                break;
            }
            case 2: {
                ForgeHooksClient.onMouseButtonPre((int)AutoClicker.mc.options.keyUse.getKey().getValue(), (int)1, (int)0);
                KeyMapping.click((InputConstants.Key)AutoClicker.mc.options.keyUse.getKey());
                ForgeHooksClient.onMouseButtonPost((int)AutoClicker.mc.options.keyUse.getKey().getValue(), (int)1, (int)0);
                break;
            }
        }
        ReflectionUtil.b(0);
        this.p.a();
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
                    case 0 -> 55;
                    case 1 -> 21;
                    case 2 -> 27;
                    case 3 -> 31;
                    case 4 -> 52;
                    case 5 -> 52;
                    default -> 51;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[24];
                var3_1 = 0;
                var2_2 = "\u001f<M\u0004{p}k\u0005e||w@\fDazm@a@R\\ozY\ntyr|_\u0014~Xq~\u0004zz\u007fz\u0006zpow[P\u0004uzow\u0006yzirUX\u0006zpow[P\u0003\u001f<M\u000bDazm@uGCtxt\u0005e||w@\u0003|pb\u0005zznlQ\u0003tEH\btEH?y[WR\u000bv`opwXZT~~m\u0006yzirUX\u0005zznlQ\u000bug~~_\u0014q[zxt\u0003sWX";
                var4_3 = "\u001f<M\u0004{p}k\u0005e||w@\fDazm@a@R\\ozY\ntyr|_\u0014~Xq~\u0004zz\u007fz\u0006zpow[P\u0004uzow\u0006yzirUX\u0006zpow[P\u0003\u001f<M\u000bDazm@uGCtxt\u0005e||w@\u0003|pb\u0005zznlQ\u0003tEH\btEH?y[WR\u000bv`opwXZT~~m\u0006yzirUX\u0005zznlQ\u000bug~~_\u0014q[zxt\u0003sWX".length();
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
                    var5[var3_1++] = AutoClicker.c(AutoClicker.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "|pb\u0004{p}k";
                    var4_3 = "|pb\u0004{p}k".length();
                    var1_4 = 3;
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
                    var5[var3_1++] = AutoClicker.c(AutoClicker.a(v1));
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
        AutoClicker.q = var5;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a() {
        String string;
        HitResult hitResult;
        if (AutoClicker.mc.player.isUsingItem() || !AutoClicker.mc.options.keyAttack.isDown() || AutoClicker.mc.screen != null || !this.o.c(i.a((String)this.cPSMode.getValue(), ((Number)this.cPS.getValue()).doubleValue()))) {
            return;
        }
        if (((Boolean)this.breakBlock.getValue()).booleanValue() && (hitResult = AutoClicker.mc.hitResult) instanceof BlockHitResult && (string = (BlockHitResult)hitResult).getType() == HitResult.Type.BLOCK) {
            return;
        }
        ReflectionUtil.c(0);
        string = (String)this.clickMode.getValue();
        int n2 = -1;
        switch (string.hashCode()) {
            case -1993687807: {
                if (!string.equals("Method")) break;
                n2 = 0;
                break;
            }
            case 75327: {
                String[] stringArray = q;
                if (!string.equals("Key")) break;
                n2 = 1;
                break;
            }
            case 74534021: {
                if (!string.equals("Mouse")) break;
                n2 = 2;
                break;
            }
        }
        switch (n2) {
            case 0: {
                this.i();
                break;
            }
            case 1: {
                KeyMapping.click((InputConstants.Key)AutoClicker.mc.options.keyAttack.getKey());
                break;
            }
            case 2: {
                ForgeHooksClient.onMouseButtonPre((int)AutoClicker.mc.options.keyAttack.getKey().getValue(), (int)1, (int)0);
                KeyMapping.click((InputConstants.Key)AutoClicker.mc.options.keyAttack.getKey());
                ForgeHooksClient.onMouseButtonPost((int)AutoClicker.mc.options.keyAttack.getKey().getValue(), (int)1, (int)0);
                break;
            }
        }
        this.o.a();
    }

    @EventTarget
    public void a(SprintEvent sprintEvent) {
        block13: {
            block14: {
                block12: {
                    block11: {
                        if (AutoClicker.mc.player != null && AutoClicker.mc.level != null) break block11;
                        return;
                    }
                    if (this.mode.is("Normal")) {
                        this.mode.setValue("Key");
                    }
                    if (!this.mode.is("Left")) break block12;
                    this.a();
                    break block13;
                }
                if (!this.mode.is("Right")) break block14;
                this.j();
                break block13;
            }
            this.a();
            this.j();
        }
    }

    @Override
    public void onEnable() {
        this.o.a();
        this.p.a();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x33);
        }
        return cArray;
    }

    @Override
    public void onDisable() {
    }
}

