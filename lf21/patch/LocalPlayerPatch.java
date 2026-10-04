/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.Transform;
import lf21.LF21Client;
import lf21.event.impl.GameTickEvent;
import lf21.event.impl.MotionEvent;
import lf21.event.impl.PositionPacketSentEvent;
import lf21.event.impl.SlowdownEvent;
import lf21.event.impl.SprintEvent;
import lf21.event.impl.SprintEvent$Source;
import lf21.modules.impl.movement.Freeze;
import lf21.patch.CallbackInfo;
import lf21.utils.misc.ReflectionUtil;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

@Patch(value=LocalPlayer.class)
public class LocalPlayerPatch {
    private static MotionEvent currentMotionEvent;

    @Inject(method="tick", desc="()V", at=@At(value=At.Type.HEAD))
    public static void onFreezeTick(LocalPlayer localPlayer, CallbackInfo callbackInfo) {
        if (LF21Client.isReady() && Freeze.shouldCancelPlayerTick()) {
            callbackInfo.cancel();
        }
    }

    public static MotionEvent onMotion(double d2, double d3, double d4, float f2, float f3, boolean bl, boolean bl2) {
        if (currentMotionEvent == null) {
            currentMotionEvent = new MotionEvent(bl2, d2, d3, d4, f2, f3, bl);
        } else if (currentMotionEvent.isPost() && bl2) {
            currentMotionEvent.setPre(true);
        }
        MotionEvent motionEvent = currentMotionEvent;
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(currentMotionEvent);
            if (bl2) {
                currentMotionEvent = null;
            }
        }
        return motionEvent;
    }

    public static boolean shouldCancelMotion(MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.isCancelled()) {
            currentMotionEvent = null;
            return true;
        }
        return false;
    }

    public static void onPositionPacketSent() {
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(new PositionPacketSentEvent());
        }
    }

    public static SlowdownEvent onSlowDown(boolean bl) {
        if (LF21Client.isReady()) {
            return (SlowdownEvent)LF21Client.instance.getEventBus().call(new SlowdownEvent(bl));
        }
        return new SlowdownEvent(bl);
    }

    @Transform(method="aiStep", desc="()V")
    public static void transformAiStep(MethodNode methodNode) {
        MethodInsnNode methodInsnNode;
        AbstractInsnNode abstractInsnNode3;
        AbstractInsnNode abstractInsnNode2 = null;
        for (AbstractInsnNode abstractInsnNode3 : methodNode.instructions) {
            if (!(abstractInsnNode3 instanceof MethodInsnNode)) continue;
            methodInsnNode = (MethodInsnNode)abstractInsnNode3;
            if (!methodInsnNode.name.equals(ReflectionUtil.getMappedMethodName(LocalPlayer.class, "isUsingItem", "()Z")) || !methodInsnNode.desc.equals("()Z") || !(methodInsnNode.getNext() instanceof JumpInsnNode)) continue;
            abstractInsnNode2 = abstractInsnNode3;
            break;
        }
        int n2 = methodNode.maxLocals++;
        abstractInsnNode3 = new InsnList();
        abstractInsnNode3.add((AbstractInsnNode)new VarInsnNode(25, 0));
        abstractInsnNode3.add((AbstractInsnNode)new MethodInsnNode(182, "net/minecraft/client/player/LocalPlayer", ReflectionUtil.getMappedMethodName(LocalPlayer.class, "isUsingItem", "()Z"), "()Z", false));
        abstractInsnNode3.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(LocalPlayerPatch.class), "onSlowDown", "(Z)L" + SlowdownEvent.class.getName().replace(".", "/") + ";", false));
        abstractInsnNode3.add((AbstractInsnNode)new VarInsnNode(58, n2));
        methodNode.instructions.insert((InsnList)abstractInsnNode3);
        methodInsnNode = new InsnList();
        methodInsnNode.add((AbstractInsnNode)new VarInsnNode(25, n2));
        methodInsnNode.add((AbstractInsnNode)new MethodInsnNode(182, SlowdownEvent.class.getName().replace(".", "/"), "isSlowDown", "()Z", false));
        methodNode.instructions.insertBefore(abstractInsnNode2, (InsnList)methodInsnNode);
        methodNode.instructions.remove(abstractInsnNode2);
    }

    @Inject(method="tick", desc="()V", at=@At(value=At.Type.BEFORE_INVOKE, method="net/minecraft/client/player/AbstractClientPlayer/tick", desc="()V"))
    public static void onTick(LocalPlayer localPlayer, CallbackInfo callbackInfo) throws Throwable {
        if (LF21Client.isReady()) {
            SprintEvent sprintEvent = new SprintEvent(localPlayer.isSprinting(), SprintEvent$Source.INPUT);
            LF21Client.getInstance().getEventBus().call(sprintEvent);
            if (!sprintEvent.isSprint() && localPlayer.isSprinting()) {
                localPlayer.setSprinting(false);
            }
        }
    }

    @Inject(method="aiStep", desc="()V")
    public static void onAiStep(LocalPlayer localPlayer, CallbackInfo callbackInfo) throws Throwable {
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(new GameTickEvent());
        }
    }

    @Transform(method="sendPosition", desc="()V")
    public static void transformSendPosition(MethodNode methodNode) throws Throwable {
        InsnList insnList = new InsnList();
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getX", "()D"));
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getY", "()D"));
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getZ", "()D"));
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getYRot", "()F"));
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getXRot", "()F"));
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "onGround", "()Z"));
        insnList.add((AbstractInsnNode)new InsnNode(3));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(LocalPlayerPatch.class), "onMotion", "(DDDFFZZ)L" + MotionEvent.class.getName().replace(".", "/") + ";"));
        insnList.add((AbstractInsnNode)new VarInsnNode(58, 1));
        LabelNode labelNode = new LabelNode();
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 1));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(LocalPlayerPatch.class), "shouldCancelMotion", "(L" + MotionEvent.class.getName().replace(".", "/") + ";)Z", false));
        insnList.add((AbstractInsnNode)new JumpInsnNode(153, labelNode));
        insnList.add((AbstractInsnNode)new InsnNode(177));
        insnList.add((AbstractInsnNode)labelNode);
        InsnList insnList2 = new InsnList();
        insnList2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList2.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getX", "()D"));
        insnList2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList2.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getY", "()D"));
        insnList2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList2.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getZ", "()D"));
        insnList2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList2.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getYRot", "()F"));
        insnList2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList2.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "getXRot", "()F"));
        insnList2.add((AbstractInsnNode)new VarInsnNode(25, 0));
        insnList2.add((AbstractInsnNode)LocalPlayerPatch.invokeGetter(Entity.class, "onGround", "()Z"));
        insnList2.add((AbstractInsnNode)new InsnNode(4));
        insnList2.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(LocalPlayerPatch.class), "onMotion", "(DDDFFZZ)L" + MotionEvent.class.getName().replace(".", "/") + ";"));
        insnList2.add((AbstractInsnNode)new InsnNode(87));
        insnList2.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(LocalPlayerPatch.class), "onPositionPacketSent", "()V", false));
        int n2 = 1;
        for (int i2 = 0; i2 < methodNode.instructions.size(); ++i2) {
            VarInsnNode varInsnNode;
            AbstractInsnNode abstractInsnNode = methodNode.instructions.get(i2);
            if (abstractInsnNode instanceof VarInsnNode) {
                varInsnNode = (VarInsnNode)abstractInsnNode;
                if (varInsnNode.var >= n2) {
                    varInsnNode.var += n2;
                }
            }
            if (!(abstractInsnNode instanceof MethodInsnNode)) continue;
            varInsnNode = (MethodInsnNode)abstractInsnNode;
            String string = varInsnNode.name;
            if (string.equals(ReflectionUtil.getMappedMethodName(Entity.class, "onGround", "()Z"))) {
                LocalPlayerPatch.redirectToEventField(methodNode, abstractInsnNode, i2, "onGround", "Z");
                continue;
            }
            if (string.equals(ReflectionUtil.getMappedMethodName(Entity.class, "getYRot", "()F"))) {
                LocalPlayerPatch.redirectToEventField(methodNode, abstractInsnNode, i2, "yaw", "F");
                continue;
            }
            if (string.equals(ReflectionUtil.getMappedMethodName(Entity.class, "getXRot", "()F"))) {
                LocalPlayerPatch.redirectToEventField(methodNode, abstractInsnNode, i2, "pitch", "F");
                continue;
            }
            if (string.equals(ReflectionUtil.getMappedMethodName(Entity.class, "getX", "()D"))) {
                LocalPlayerPatch.redirectToEventField(methodNode, abstractInsnNode, i2, "x", "D");
                continue;
            }
            if (string.equals(ReflectionUtil.getMappedMethodName(Entity.class, "getY", "()D"))) {
                LocalPlayerPatch.redirectToEventField(methodNode, abstractInsnNode, i2, "y", "D");
                continue;
            }
            if (!string.equals(ReflectionUtil.getMappedMethodName(Entity.class, "getZ", "()D"))) continue;
            LocalPlayerPatch.redirectToEventField(methodNode, abstractInsnNode, i2, "z", "D");
        }
        methodNode.instructions.insert(insnList);
        methodNode.instructions.insertBefore(methodNode.instructions.getLast().getPrevious(), insnList2);
    }

    private static MethodInsnNode invokeGetter(Class<?> clazz, String string, String string2) {
        return new MethodInsnNode(182, "net/minecraft/world/entity/Entity", ReflectionUtil.getMappedMethodName(clazz, string, string2), string2);
    }

    private static void redirectToEventField(MethodNode methodNode, AbstractInsnNode abstractInsnNode, int n2, String string, String string2) {
        AbstractInsnNode abstractInsnNode2 = methodNode.instructions.get(n2 - 1);
        if (!(abstractInsnNode2 instanceof VarInsnNode)) {
            return;
        }
        methodNode.instructions.insert(abstractInsnNode2, (AbstractInsnNode)new VarInsnNode(25, 1));
        methodNode.instructions.remove(abstractInsnNode2);
        methodNode.instructions.insert(abstractInsnNode, (AbstractInsnNode)new FieldInsnNode(180, MotionEvent.class.getName().replace(".", "/"), string, string2));
        methodNode.instructions.remove(abstractInsnNode);
    }
}

