/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import com.mojang.blaze3d.platform.InputConstants;
import lf21.ClientBase;
import lf21.modules.impl.player.ShiftDrag;
import lf21.patch.CallbackInfo;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

@Patch(value=AbstractContainerScreen.class)
public class AbstractContainerScreenPatch
extends ClientBase {
    private static AbstractContainerScreen<?> dragScreen;
    private static int lastDraggedSlot;

    @Inject(method="mouseClicked", desc="(DDI)Z", at=@At(value=At.Type.HEAD))
    public static void onMouseClicked(AbstractContainerScreen<?> abstractContainerScreen, double d2, double d3, int n2, CallbackInfo callbackInfo) {
        AbstractContainerScreenPatch.clearIfNotShiftDrag(abstractContainerScreen, n2);
        if (n2 != 0 || !AbstractContainerScreenPatch.isShiftDown() || !AbstractContainerScreenPatch.isEnabled()) {
            return;
        }
        dragScreen = abstractContainerScreen;
        Slot slot = AbstractContainerScreenPatch.findSlot(abstractContainerScreen, d2, d3);
        lastDraggedSlot = slot == null ? -1 : slot.index;
    }

    @Inject(method="mouseDragged", desc="(DDIDD)Z", at=@At(value=At.Type.HEAD))
    public static void onMouseDragged(AbstractContainerScreen<?> abstractContainerScreen, double d2, double d3, int n2, double d4, double d5, CallbackInfo callbackInfo) {
        Slot slot;
        if (n2 != 0 || !AbstractContainerScreenPatch.isShiftDown() || !AbstractContainerScreenPatch.isEnabled()) {
            AbstractContainerScreenPatch.clearDragState();
            return;
        }
        if (dragScreen != abstractContainerScreen) {
            dragScreen = abstractContainerScreen;
            lastDraggedSlot = -1;
        }
        if ((slot = AbstractContainerScreenPatch.findSlot(abstractContainerScreen, d2, d3)) == null || slot.index == lastDraggedSlot || !slot.hasItem() || AbstractContainerScreenPatch.mc.gameMode == null || AbstractContainerScreenPatch.mc.player == null) {
            return;
        }
        AbstractContainerScreenPatch.mc.gameMode.handleInventoryMouseClick(abstractContainerScreen.getMenu().containerId, slot.index, 0, ClickType.QUICK_MOVE, (Player)AbstractContainerScreenPatch.mc.player);
        lastDraggedSlot = slot.index;
    }

    @Inject(method="mouseReleased", desc="(DDI)Z", at=@At(value=At.Type.HEAD))
    public static void onMouseReleased(AbstractContainerScreen<?> abstractContainerScreen, double d2, double d3, int n2, CallbackInfo callbackInfo) {
        if (n2 == 0) {
            AbstractContainerScreenPatch.clearDragState();
        }
    }

    private static boolean isEnabled() {
        ShiftDrag shiftDrag = ShiftDrag.INSTANCE;
        return shiftDrag != null && shiftDrag.isEnabled();
    }

    private static boolean isShiftDown() {
        if (mc == null || mc.getWindow() == null) {
            return false;
        }
        long l2 = mc.getWindow().getWindow();
        return InputConstants.isKeyDown((long)l2, (int)340) || InputConstants.isKeyDown((long)l2, (int)344);
    }

    private static Slot findSlot(AbstractContainerScreen<?> abstractContainerScreen, double d2, double d3) {
        try {
            int n2 = ((Number)ReflectionUtil.getInstanceField(abstractContainerScreen, "leftPos")).intValue();
            int n3 = ((Number)ReflectionUtil.getInstanceField(abstractContainerScreen, "topPos")).intValue();
            for (Slot slot : abstractContainerScreen.getMenu().slots) {
                double d4 = n2 + slot.x;
                double d5 = n3 + slot.y;
                if (!(d2 >= d4 - 1.0) || !(d2 < d4 + 17.0) || !(d3 >= d5 - 1.0) || !(d3 < d5 + 17.0)) continue;
                return slot;
            }
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
        return null;
    }

    private static void clearIfNotShiftDrag(AbstractContainerScreen<?> abstractContainerScreen, int n2) {
        if (n2 != 0 || dragScreen != abstractContainerScreen || !AbstractContainerScreenPatch.isShiftDown() || !AbstractContainerScreenPatch.isEnabled()) {
            AbstractContainerScreenPatch.clearDragState();
        }
    }

    private static void clearDragState() {
        dragScreen = null;
        lastDraggedSlot = -1;
    }

    static {
        lastDraggedSlot = -1;
    }
}

