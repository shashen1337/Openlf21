/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.loader;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public final class ASMHelpers {
    private ASMHelpers() {
    }

    public static InsnList unboxFromObject(Type type) {
        InsnList insnList = new InsnList();
        switch (type.getSort()) {
            case 5: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Integer"));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/Integer", "intValue", "()I", false));
                break;
            }
            case 1: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Boolean"));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/Boolean", "booleanValue", "()Z", false));
                break;
            }
            case 2: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Character"));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/Character", "charValue", "()C", false));
                break;
            }
            case 3: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Byte"));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/Byte", "byteValue", "()B", false));
                break;
            }
            case 4: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Short"));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/Short", "shortValue", "()S", false));
                break;
            }
            case 7: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Long"));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/Long", "longValue", "()J", false));
                break;
            }
            case 6: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Float"));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/Float", "floatValue", "()F", false));
                break;
            }
            case 8: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Double"));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/Double", "doubleValue", "()D", false));
                break;
            }
            default: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, type.getInternalName()));
            }
        }
        return insnList;
    }

    public static InsnList boxToObject(Type type) {
        InsnList insnList = new InsnList();
        switch (type.getSort()) {
            case 5: {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false));
                break;
            }
            case 1: {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false));
                break;
            }
            case 2: {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false));
                break;
            }
            case 3: {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false));
                break;
            }
            case 4: {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false));
                break;
            }
            case 7: {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false));
                break;
            }
            case 6: {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false));
                break;
            }
            case 8: {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false));
                break;
            }
            default: {
                insnList.add((AbstractInsnNode)new TypeInsnNode(192, "java/lang/Object"));
            }
        }
        return insnList;
    }

    public static String[] splitOwnerName(String string) {
        int n2 = string.lastIndexOf(47);
        if (n2 < 0) {
            return new String[]{"", string};
        }
        return new String[]{string.substring(0, n2), string.substring(n2 + 1)};
    }
}

