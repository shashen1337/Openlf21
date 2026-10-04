/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.Patch;
import asm.patchify.annotation.Transform;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.event.impl.CameraPitchEvent;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

@Patch(value=HumanoidModel.class)
public class HumanoidModelPatch {
    public static CameraPitchEvent onPitchRender(LivingEntity livingEntity, float f2) {
        if (LF21Client.isReady() && livingEntity == ClientBase.mc.player && ClientBase.mc.level != null) {
            return (CameraPitchEvent)LF21Client.instance.getEventBus().call(new CameraPitchEvent(f2));
        }
        return new CameraPitchEvent(f2);
    }

    @Transform(method="setupAnim", desc="(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V")
    public static void transformSetupAnim(MethodNode methodNode) {
        InsnList insnList = new InsnList();
        int n2 = 7;
        insnList.add((AbstractInsnNode)new VarInsnNode(25, 1));
        insnList.add((AbstractInsnNode)new VarInsnNode(23, 6));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(HumanoidModelPatch.class), "onPitchRender", "(Lnet/minecraft/world/entity/LivingEntity;F)L" + CameraPitchEvent.class.getName().replace(".", "/") + ";", false));
        insnList.add((AbstractInsnNode)new VarInsnNode(58, n2));
        for (int i2 = 0; i2 < methodNode.instructions.size(); ++i2) {
            VarInsnNode varInsnNode;
            AbstractInsnNode abstractInsnNode = methodNode.instructions.get(i2);
            if (abstractInsnNode instanceof VarInsnNode) {
                varInsnNode = (VarInsnNode)abstractInsnNode;
                if (varInsnNode.var >= n2) {
                    varInsnNode.var += n2;
                }
            }
            if (!(abstractInsnNode instanceof VarInsnNode)) continue;
            varInsnNode = (VarInsnNode)abstractInsnNode;
            if (varInsnNode.var != 6) continue;
            InsnList insnList2 = new InsnList();
            insnList2.add((AbstractInsnNode)new VarInsnNode(25, n2));
            insnList2.add((AbstractInsnNode)new MethodInsnNode(182, CameraPitchEvent.class.getName().replace(".", "/"), "getPitch", "()F"));
            methodNode.instructions.insert(abstractInsnNode, insnList2);
            methodNode.instructions.remove(abstractInsnNode);
        }
        methodNode.instructions.insert(insnList);
    }
}

