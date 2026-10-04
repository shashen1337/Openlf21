/*
 * Decompiled with CFR 0.152.
 */
package asm.patchify.loader;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Local;
import asm.patchify.annotation.ModifyLocals;
import asm.patchify.annotation.Overwrite;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.Slice;
import asm.patchify.annotation.Transform;
import asm.patchify.annotation.WrapInvoke;
import asm.patchify.loader.ASMHelpers;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import lf21.asm.Bootstrap;
import lf21.asm.ILocals;
import lf21.asm.Invocation;
import lf21.asm.InvocationImpl;
import lf21.asm.Locals;
import lf21.asm.MethodWrapper;
import lf21.patch.CallbackInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public final class PatchTransformer {
    private static final Logger LOGGER = LogManager.getLogger(PatchTransformer.class);
    private static final String CALLBACK_INFO = Type.getInternalName(CallbackInfo.class);
    private static final String CALLBACK_INFO_DESC = Type.getDescriptor(CallbackInfo.class);

    private PatchTransformer() {
    }

    public static void apply(Class<?> clazz, ClassNode classNode) {
        Patch patch = clazz.getAnnotation(Patch.class);
        if (patch == null) {
            throw new IllegalArgumentException(clazz.getName() + " is not @Patch");
        }
        String string = !patch.className().isEmpty() ? patch.className().replace('.', '/') : Type.getInternalName(patch.value());
        HashMap<MethodKey, List<Method>> hashMap = new HashMap<MethodKey, List<Method>>();
        ArrayList<Method> arrayList = new ArrayList<Method>();
        for (Method object : clazz.getDeclaredMethods()) {
            PatchTransformer.collectHandler(clazz, string, object, hashMap, arrayList);
        }
        LOGGER.info("Loading patch {} -> {} ({} handler(s))", (Object)clazz.getName(), (Object)classNode.name, (Object)(hashMap.values().stream().mapToInt(List::size).sum() + arrayList.size()));
        HashSet hashSet = new HashSet();
        HashSet<String> hashSet2 = new HashSet<String>();
        for (MethodNode methodNode : classNode.methods) {
            MethodKey methodKey = new MethodKey(methodNode.name, methodNode.desc);
            List<Method> list = (List)hashMap.get(methodKey);
            if (list == null && !arrayList.isEmpty()) {
                for (Method method : arrayList) {
                    String string2 = PatchTransformer.getHandlerTargetMethodName(method);
                    if (!string2.equals(methodNode.name)) continue;
                    if (list == null) {
                        list = new ArrayList<Method>();
                    }
                    list.add(method);
                    hashSet2.add(methodNode.name);
                }
            }
            if (list == null) continue;
            hashSet.add(methodKey);
            for (Method method : list) {
                if (method.isAnnotationPresent(Inject.class)) {
                    PatchTransformer.applyInject(methodNode, method);
                    continue;
                }
                if (method.isAnnotationPresent(Overwrite.class)) {
                    PatchTransformer.overwriteMethod(methodNode, method);
                    LOGGER.info("@Overwrite     {}/{}{} <- {}#{}", (Object)classNode.name, (Object)methodNode.name, (Object)methodNode.desc, (Object)clazz.getName(), (Object)method.getName());
                    continue;
                }
                if (method.isAnnotationPresent(Transform.class)) {
                    try {
                        method.setAccessible(true);
                        method.invoke(null, methodNode);
                    }
                    catch (Exception exception) {
                        throw new RuntimeException("Failed to apply @Transform " + String.valueOf(method), exception);
                    }
                    LOGGER.info("@Transform     {}/{}{} <- {}#{}", (Object)classNode.name, (Object)methodNode.name, (Object)methodNode.desc, (Object)clazz.getName(), (Object)method.getName());
                    continue;
                }
                if (method.isAnnotationPresent(WrapInvoke.class)) {
                    PatchTransformer.wrapInvoke(methodNode, method);
                    continue;
                }
                if (!method.isAnnotationPresent(ModifyLocals.class)) continue;
                PatchTransformer.modifyLocals(methodNode, method);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (hashSet.contains(entry.getKey())) continue;
            for (List<Method> list : (List)entry.getValue()) {
                LOGGER.warn("Patch handler {}#{}{} targets {}#{}{} but no such method exists on {} \u2014 handler will not run.", (Object)((Method)((Object)list)).getDeclaringClass().getName(), (Object)((Method)((Object)list)).getName(), (Object)Type.getMethodDescriptor((Method)((Object)list)), (Object)classNode.name, (Object)((MethodKey)entry.getKey()).name(), (Object)((MethodKey)entry.getKey()).desc(), (Object)classNode.name);
            }
        }
        for (Method method : arrayList) {
            if (hashSet2.contains(PatchTransformer.getHandlerTargetMethodName(method))) continue;
            LOGGER.warn("Patch handler {}#{} is name-only (empty desc) but no method named \"{}\" exists on {} \u2014 handler will not run.", (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)PatchTransformer.getHandlerTargetMethodName(method), (Object)classNode.name);
        }
    }

    private static String getHandlerTargetMethodName(Method method) {
        Inject inject = method.getAnnotation(Inject.class);
        if (inject != null) {
            return inject.method();
        }
        Overwrite overwrite = method.getAnnotation(Overwrite.class);
        if (overwrite != null) {
            return overwrite.method();
        }
        Transform transform = method.getAnnotation(Transform.class);
        if (transform != null) {
            return transform.method();
        }
        WrapInvoke wrapInvoke = method.getAnnotation(WrapInvoke.class);
        if (wrapInvoke != null) {
            return wrapInvoke.method();
        }
        ModifyLocals modifyLocals = method.getAnnotation(ModifyLocals.class);
        if (modifyLocals != null) {
            return modifyLocals.method();
        }
        return "?";
    }

    private static void collectHandler(Class<?> clazz, String string, Method method, Map<MethodKey, List<Method>> map, List<Method> list) {
        String string2;
        String string3;
        if (!(method.isAnnotationPresent(Inject.class) || method.isAnnotationPresent(Overwrite.class) || method.isAnnotationPresent(Transform.class) || method.isAnnotationPresent(WrapInvoke.class) || method.isAnnotationPresent(ModifyLocals.class))) {
            return;
        }
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException("Handler " + String.valueOf(method) + " must be static");
        }
        if (method.isAnnotationPresent(Inject.class)) {
            Inject inject = method.getAnnotation(Inject.class);
            string3 = inject.method();
            string2 = inject.desc();
            PatchTransformer.validateInjectSignature(clazz, method, inject);
        } else if (method.isAnnotationPresent(Overwrite.class)) {
            Overwrite overwrite = method.getAnnotation(Overwrite.class);
            string3 = overwrite.method();
            string2 = overwrite.desc();
        } else if (method.isAnnotationPresent(Transform.class)) {
            Transform transform = method.getAnnotation(Transform.class);
            string3 = transform.method();
            string2 = transform.desc();
            if (method.getParameterCount() != 1 || method.getParameterTypes()[0] != MethodNode.class) {
                throw new IllegalArgumentException("@Transform " + String.valueOf(method) + " must take a single MethodNode");
            }
        } else if (method.isAnnotationPresent(WrapInvoke.class)) {
            WrapInvoke wrapInvoke = method.getAnnotation(WrapInvoke.class);
            string3 = wrapInvoke.method();
            string2 = wrapInvoke.desc();
            Class<?>[] classArray = method.getParameterTypes();
            if (classArray.length == 0 || !Invocation.class.isAssignableFrom(classArray[classArray.length - 1])) {
                throw new IllegalArgumentException("@WrapInvoke " + String.valueOf(method) + " must take an Invocation as last param");
            }
        } else {
            ModifyLocals modifyLocals = method.getAnnotation(ModifyLocals.class);
            string3 = modifyLocals.method();
            string2 = modifyLocals.desc();
            if (method.getParameterCount() != 1 || !ILocals.class.isAssignableFrom(method.getParameterTypes()[0])) {
                throw new IllegalArgumentException("@ModifyLocals " + String.valueOf(method) + " must take a single ILocals");
            }
        }
        string3 = Bootstrap.remapMethod(string, string3, string2);
        if (string2.isEmpty()) {
            list.add(method);
        } else {
            map.computeIfAbsent(new MethodKey(string3, string2), methodKey -> new ArrayList()).add(method);
        }
    }

    private static void validateInjectSignature(Class<?> clazz, Method method, Inject inject) {
        if (method.getReturnType() != Void.TYPE) {
            throw new IllegalArgumentException("@Inject " + String.valueOf(method) + " must return void");
        }
        Parameter[] parameterArray = method.getParameters();
        if (parameterArray.length == 0 || parameterArray[parameterArray.length - 1].getType() != CallbackInfo.class) {
            throw new IllegalArgumentException("@Inject " + String.valueOf(method) + " must take CallbackInfo as last param");
        }
        if (inject.at().value() == At.Type.HEAD) {
            for (Parameter parameter : parameterArray) {
                if (!parameter.isAnnotationPresent(Local.class)) continue;
                throw new IllegalArgumentException("@Inject " + String.valueOf(method) + " HEAD cannot use @Local");
            }
        }
    }

    private static void applyInject(MethodNode methodNode, Method method) {
        At at = method.getAnnotation(Inject.class).at();
        switch (at.value()) {
            case HEAD: {
                PatchTransformer.injectHead(methodNode, method);
                break;
            }
            case TAIL: {
                PatchTransformer.injectTail(methodNode, method);
                break;
            }
            case BEFORE_INVOKE: 
            case AFTER_INVOKE: {
                String string = at.method();
                String string2 = at.desc();
                if (string.isEmpty() || string2.isEmpty()) {
                    throw new IllegalArgumentException("@At " + String.valueOf(method) + " missing method/desc");
                }
                PatchTransformer.injectAroundInvoke(methodNode, method, string, string2, at.value() == At.Type.BEFORE_INVOKE);
            }
        }
    }

    private static String headHandlerMismatch(MethodNode methodNode, Method method) {
        ArrayList<Type> arrayList = new ArrayList<Type>();
        if (!Modifier.isStatic(methodNode.access)) {
            arrayList.add(Type.getObjectType((String)"java/lang/Object"));
        }
        arrayList.addAll(Arrays.asList(Type.getArgumentTypes((String)methodNode.desc)));
        int n2 = method.getParameterCount() - 1;
        if (arrayList.size() != n2) {
            return "handler takes " + n2 + " forwarded parameter(s) but target supplies " + arrayList.size() + " (receiver + args)";
        }
        Class<?>[] classArray = method.getParameterTypes();
        for (int i2 = 0; i2 < arrayList.size(); ++i2) {
            boolean bl;
            Type type = (Type)arrayList.get(i2);
            Class<?> clazz = classArray[i2];
            boolean bl2 = bl = type.getSort() != 10 && type.getSort() != 9;
            if (bl) {
                if (clazz.isPrimitive() && clazz.getName().equals(type.getClassName())) continue;
                return "parameter " + i2 + " is primitive " + type.getClassName() + " but handler declares " + clazz.getName();
            }
            if (!clazz.isPrimitive()) continue;
            return "parameter " + i2 + " is a reference but handler declares primitive " + clazz.getName();
        }
        return null;
    }

    private static void injectHead(MethodNode methodNode, Method method) {
        String string = PatchTransformer.headHandlerMismatch(methodNode, method);
        if (string != null) {
            LOGGER.warn("@Inject(HEAD) {}#{} is incompatible with matched target {}{} \u2014 {} \u2014 skipping injection to avoid invalid bytecode.", (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)methodNode.name, (Object)methodNode.desc, (Object)string);
            return;
        }
        Type type = Type.getReturnType((String)methodNode.desc);
        String string2 = Type.getInternalName(method.getDeclaringClass());
        String string3 = method.getName();
        String string4 = Type.getMethodDescriptor((Method)method);
        InsnList insnList = new InsnList();
        LabelNode labelNode = new LabelNode();
        int n2 = methodNode.maxLocals++;
        int n3 = 0;
        if (!Modifier.isStatic(methodNode.access)) {
            insnList.add((AbstractInsnNode)new VarInsnNode(25, n3++));
        }
        for (Type type2 : Type.getArgumentTypes((String)methodNode.desc)) {
            insnList.add((AbstractInsnNode)new VarInsnNode(type2.getOpcode(21), n3));
            n3 += type2.getSize();
        }
        insnList.add((AbstractInsnNode)new InsnNode(1));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, CALLBACK_INFO, "create", "(Ljava/lang/Object;)" + CALLBACK_INFO_DESC, false));
        insnList.add((AbstractInsnNode)new InsnNode(89));
        insnList.add((AbstractInsnNode)new VarInsnNode(58, n2));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, string2, string3, string4, false));
        insnList.add((AbstractInsnNode)new VarInsnNode(25, n2));
        insnList.add((AbstractInsnNode)new InsnNode(89));
        insnList.add((AbstractInsnNode)new FieldInsnNode(180, CALLBACK_INFO, "cancelled", "Z"));
        insnList.add((AbstractInsnNode)new JumpInsnNode(153, labelNode));
        if (type == Type.VOID_TYPE) {
            insnList.add((AbstractInsnNode)new InsnNode(87));
            insnList.add((AbstractInsnNode)new InsnNode(177));
        } else {
            insnList.add((AbstractInsnNode)new FieldInsnNode(180, CALLBACK_INFO, "result", "Ljava/lang/Object;"));
            insnList.add(ASMHelpers.unboxFromObject(type));
            insnList.add((AbstractInsnNode)new InsnNode(type.getOpcode(172)));
        }
        insnList.add((AbstractInsnNode)labelNode);
        insnList.add((AbstractInsnNode)new InsnNode(87));
        methodNode.instructions.insert(insnList);
        LOGGER.info("@Inject(HEAD)  {}/{}{} <- {}#{}", (Object)PatchTransformer.targetClassName(method), (Object)methodNode.name, (Object)methodNode.desc, (Object)method.getDeclaringClass().getName(), (Object)method.getName());
    }

    private static void injectTail(MethodNode methodNode, Method method) {
        Type type = Type.getReturnType((String)methodNode.desc);
        int n2 = type.getOpcode(172);
        Slice slice = method.getAnnotation(Inject.class).slice();
        List<AbstractInsnNode> list = PatchTransformer.collectInjectionPoints(methodNode.instructions, slice, abstractInsnNode -> abstractInsnNode.getOpcode() == n2);
        if (list.isEmpty()) {
            LOGGER.warn("@Inject(TAIL) {}#{}{} found no return instruction in target {}{} \u2014 patch handler will not run.", (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)Type.getMethodDescriptor((Method)method), (Object)methodNode.name, (Object)methodNode.desc);
            return;
        }
        String string = Type.getInternalName(method.getDeclaringClass());
        String string2 = method.getName();
        String string3 = Type.getMethodDescriptor((Method)method);
        for (AbstractInsnNode abstractInsnNode2 : list) {
            InsnList insnList = new InsnList();
            int n3 = methodNode.maxLocals++;
            if (type == Type.VOID_TYPE) {
                insnList.add((AbstractInsnNode)new InsnNode(1));
            } else {
                insnList.add(ASMHelpers.boxToObject(type));
            }
            insnList.add((AbstractInsnNode)new MethodInsnNode(184, CALLBACK_INFO, "create", "(Ljava/lang/Object;)" + CALLBACK_INFO_DESC, false));
            int n4 = 0;
            if (!Modifier.isStatic(methodNode.access)) {
                insnList.add((AbstractInsnNode)new VarInsnNode(25, n4++));
                insnList.add((AbstractInsnNode)new InsnNode(95));
            }
            for (Type type2 : Type.getArgumentTypes((String)methodNode.desc)) {
                insnList.add((AbstractInsnNode)new VarInsnNode(type2.getOpcode(21), n4));
                n4 += type2.getSize();
                PatchTransformer.swapForCallback(type2, insnList);
            }
            for (Parameter parameter : method.getParameters()) {
                Local local = parameter.getAnnotation(Local.class);
                if (local == null) continue;
                Type type3 = Type.getType(parameter.getType());
                insnList.add((AbstractInsnNode)new VarInsnNode(type3.getOpcode(21), local.value()));
                PatchTransformer.swapForCallback(type3, insnList);
            }
            insnList.add((AbstractInsnNode)new InsnNode(89));
            insnList.add((AbstractInsnNode)new VarInsnNode(58, n3));
            insnList.add((AbstractInsnNode)new MethodInsnNode(184, string, string2, string3, false));
            insnList.add((AbstractInsnNode)new VarInsnNode(25, n3));
            insnList.add((AbstractInsnNode)new FieldInsnNode(180, CALLBACK_INFO, "result", "Ljava/lang/Object;"));
            if (type == Type.VOID_TYPE) {
                insnList.add((AbstractInsnNode)new InsnNode(87));
            } else {
                insnList.add(ASMHelpers.unboxFromObject(type));
            }
            methodNode.instructions.insertBefore(abstractInsnNode2, insnList);
        }
        LOGGER.info("@Inject(TAIL)  {}/{}{} <- {}#{} ({} return site(s))", (Object)PatchTransformer.targetClassName(method), (Object)methodNode.name, (Object)methodNode.desc, (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)list.size());
    }

    private static void injectAroundInvoke(MethodNode methodNode, Method method, String string, String string2, boolean bl) {
        Type type = Type.getReturnType((String)methodNode.desc);
        String[] stringArray = ASMHelpers.splitOwnerName(string);
        String string3 = stringArray[0];
        String string4 = Bootstrap.remapMethod(stringArray[0], stringArray[1], string2);
        Slice slice = method.getAnnotation(Inject.class).slice();
        List<AbstractInsnNode> list = PatchTransformer.collectInjectionPoints(methodNode.instructions, slice, abstractInsnNode -> {
            if (!(abstractInsnNode instanceof MethodInsnNode)) return false;
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            if (!methodInsnNode.owner.equals(string3)) return false;
            if (!methodInsnNode.name.equals(string4)) return false;
            if (!methodInsnNode.desc.equals(string2)) return false;
            return true;
        });
        if (list.isEmpty()) {
            LOGGER.warn("@Inject({}_INVOKE) {}#{}{} found no call site of {}#{}{} \u2014 patch handler will not run.", (Object)(bl ? "BEFORE" : "AFTER"), (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)Type.getMethodDescriptor((Method)method), (Object)string3, (Object)string4, (Object)string2);
            return;
        }
        Set<Integer> set = PatchTransformer.collectInitializedLocalsBefore(methodNode, list.get(0));
        int n2 = methodNode.maxLocals++;
        String string5 = Type.getInternalName(method.getDeclaringClass());
        String string6 = method.getName();
        String string7 = Type.getMethodDescriptor((Method)method);
        for (AbstractInsnNode abstractInsnNode2 : list) {
            InsnList insnList = new InsnList();
            LabelNode labelNode = new LabelNode();
            int n3 = 0;
            if (!Modifier.isStatic(methodNode.access)) {
                insnList.add((AbstractInsnNode)new VarInsnNode(25, n3++));
            }
            for (Type type2 : Type.getArgumentTypes((String)methodNode.desc)) {
                insnList.add((AbstractInsnNode)new VarInsnNode(type2.getOpcode(21), n3));
                n3 += type2.getSize();
            }
            for (Parameter parameter : method.getParameters()) {
                Local local = parameter.getAnnotation(Local.class);
                if (local == null) continue;
                if (!set.contains(local.value())) {
                    throw new IllegalArgumentException("@Local index " + local.value() + " not initialized before injection point in " + method.getDeclaringClass().getName() + "." + method.getName());
                }
                Type type3 = Type.getType(parameter.getType());
                insnList.add((AbstractInsnNode)new VarInsnNode(type3.getOpcode(21), local.value()));
            }
            insnList.add((AbstractInsnNode)new InsnNode(1));
            insnList.add((AbstractInsnNode)new MethodInsnNode(184, CALLBACK_INFO, "create", "(Ljava/lang/Object;)" + CALLBACK_INFO_DESC, false));
            insnList.add((AbstractInsnNode)new InsnNode(89));
            insnList.add((AbstractInsnNode)new VarInsnNode(58, n2));
            insnList.add((AbstractInsnNode)new MethodInsnNode(184, string5, string6, string7, false));
            insnList.add((AbstractInsnNode)new VarInsnNode(25, n2));
            insnList.add((AbstractInsnNode)new InsnNode(89));
            insnList.add((AbstractInsnNode)new FieldInsnNode(180, CALLBACK_INFO, "cancelled", "Z"));
            insnList.add((AbstractInsnNode)new JumpInsnNode(153, labelNode));
            insnList.add((AbstractInsnNode)new FieldInsnNode(180, CALLBACK_INFO, "result", "Ljava/lang/Object;"));
            if (type == Type.VOID_TYPE) {
                insnList.add((AbstractInsnNode)new InsnNode(87));
                insnList.add((AbstractInsnNode)new InsnNode(177));
            } else {
                insnList.add(ASMHelpers.unboxFromObject(type));
                insnList.add((AbstractInsnNode)new InsnNode(type.getOpcode(172)));
            }
            insnList.add((AbstractInsnNode)labelNode);
            insnList.add((AbstractInsnNode)new InsnNode(87));
            if (bl) {
                methodNode.instructions.insertBefore(abstractInsnNode2, insnList);
                continue;
            }
            methodNode.instructions.insert(abstractInsnNode2, insnList);
        }
        LOGGER.info("@Inject({}) {}/{}{} <- {}#{} around {}#{}{} ({} site(s))", (Object)(bl ? "BEFORE_INVOKE" : "AFTER_INVOKE "), (Object)PatchTransformer.targetClassName(method), (Object)methodNode.name, (Object)methodNode.desc, (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)string3, (Object)string4, (Object)string2, (Object)list.size());
    }

    private static void overwriteMethod(MethodNode methodNode, Method method) {
        int n2 = Type.getArgumentTypes((String)methodNode.desc).length + (Modifier.isStatic(methodNode.access) ? 0 : 1);
        if (method.getParameterCount() != n2) {
            throw new IllegalArgumentException("@Overwrite handler " + String.valueOf(method) + " has " + method.getParameterCount() + " params but target requires " + n2 + " ((this if non-static), then method args). Remove any CallbackInfo param.");
        }
        InsnList insnList = new InsnList();
        int n3 = 0;
        if (!Modifier.isStatic(methodNode.access)) {
            insnList.add((AbstractInsnNode)new VarInsnNode(25, n3++));
        }
        for (Type type : Type.getArgumentTypes((String)methodNode.desc)) {
            insnList.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(21), n3++));
        }
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(method.getDeclaringClass()), method.getName(), Type.getMethodDescriptor((Method)method), false));
        Type type = Type.getReturnType((String)methodNode.desc);
        if (type == Type.VOID_TYPE) {
            insnList.add((AbstractInsnNode)new InsnNode(177));
        } else {
            insnList.add((AbstractInsnNode)new InsnNode(type.getOpcode(172)));
        }
        methodNode.instructions.insert(insnList);
    }

    private static void wrapInvoke(MethodNode methodNode, Method method) {
        boolean bl;
        WrapInvoke wrapInvoke = method.getAnnotation(WrapInvoke.class);
        String string = wrapInvoke.target();
        String string2 = wrapInvoke.targetDesc();
        String[] stringArray = ASMHelpers.splitOwnerName(string);
        String string3 = stringArray[0];
        String string4 = Bootstrap.remapMethod(stringArray[0], stringArray[1], string2);
        List<AbstractInsnNode> list = PatchTransformer.collectInjectionPoints(methodNode.instructions, wrapInvoke.slice(), abstractInsnNode -> {
            if (!(abstractInsnNode instanceof MethodInsnNode)) return false;
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            if (!methodInsnNode.owner.equals(string3)) return false;
            if (!methodInsnNode.name.equals(string4)) return false;
            if (!methodInsnNode.desc.equals(string2)) return false;
            return true;
        });
        if (list.isEmpty()) {
            list = PatchTransformer.collectInjectionPoints(methodNode.instructions, wrapInvoke.slice(), abstractInsnNode -> {
                if (!(abstractInsnNode instanceof MethodInsnNode)) return false;
                MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
                if (!methodInsnNode.owner.equals(string3)) {
                    if (!methodInsnNode.name.equals(string4)) return false;
                }
                if (!methodInsnNode.desc.equals(string2)) return false;
                return true;
            });
        }
        if (list.isEmpty()) {
            LOGGER.warn("@WrapInvoke {}#{}{} found no call site of {}#{}{} \u2014 patch handler will not run.", (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)Type.getMethodDescriptor((Method)method), (Object)string3, (Object)string4, (Object)string2);
            return;
        }
        Set<Integer> set = PatchTransformer.collectInitializedLocalsBefore(methodNode, list.get(0));
        String string5 = Type.getInternalName(method.getDeclaringClass());
        String string6 = method.getName();
        String string7 = Type.getMethodDescriptor((Method)method);
        int n2 = list.get(0).getOpcode();
        if (n2 == 184) {
            bl = true;
        } else if (n2 == 182) {
            bl = false;
        } else {
            throw new IllegalArgumentException("@WrapInvoke unsupported opcode " + n2 + " in " + String.valueOf(method));
        }
        for (AbstractInsnNode abstractInsnNode2 : list) {
            int n3;
            InsnList insnList = new InsnList();
            int n4 = methodNode.maxLocals++;
            insnList.add((AbstractInsnNode)new LdcInsnNode((Object)string3));
            insnList.add((AbstractInsnNode)new LdcInsnNode((Object)string4));
            insnList.add((AbstractInsnNode)new LdcInsnNode((Object)string2));
            insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(MethodWrapper.class), "getInstance", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)" + Type.getDescriptor(MethodWrapper.class), false));
            insnList.add((AbstractInsnNode)new VarInsnNode(58, n4));
            Type[] typeArray = Type.getArgumentTypes((String)string2);
            for (n3 = typeArray.length - 1; n3 >= 0; --n3) {
                insnList.add(ASMHelpers.boxToObject(typeArray[n3]));
                insnList.add((AbstractInsnNode)new VarInsnNode(25, n4));
                insnList.add((AbstractInsnNode)new InsnNode(95));
                insnList.add((AbstractInsnNode)new MethodInsnNode(182, Type.getInternalName(MethodWrapper.class), "addParam", "(Ljava/lang/Object;)" + Type.getDescriptor(MethodWrapper.class), false));
                insnList.add((AbstractInsnNode)new InsnNode(87));
            }
            insnList.add((AbstractInsnNode)new VarInsnNode(25, n4));
            if (bl) {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(InvocationImpl.class), "create", "(" + Type.getDescriptor(MethodWrapper.class) + ")" + Type.getDescriptor(InvocationImpl.class), false));
            } else {
                insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(InvocationImpl.class), "create", "(Ljava/lang/Object;" + Type.getDescriptor(MethodWrapper.class) + ")" + Type.getDescriptor(InvocationImpl.class), false));
            }
            n3 = 0;
            if (!Modifier.isStatic(methodNode.access)) {
                insnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
                insnList.add((AbstractInsnNode)new InsnNode(95));
                ++n3;
            }
            for (Type type : Type.getArgumentTypes((String)methodNode.desc)) {
                insnList.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(21), n3));
                n3 += type.getSize();
                PatchTransformer.swapForCallback(type, insnList);
            }
            for (Parameter parameter : method.getParameters()) {
                Local local = parameter.getAnnotation(Local.class);
                if (local == null) continue;
                if (!set.contains(local.value())) {
                    throw new IllegalArgumentException("@Local index " + local.value() + " not initialized in " + String.valueOf(method));
                }
                Type type = Type.getType(parameter.getType());
                insnList.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(21), local.value()));
                PatchTransformer.swapForCallback(type, insnList);
            }
            insnList.add((AbstractInsnNode)new MethodInsnNode(184, string5, string6, string7, false));
            methodNode.instructions.insertBefore(abstractInsnNode2, insnList);
            methodNode.instructions.remove(abstractInsnNode2);
        }
        LOGGER.info("@WrapInvoke    {}/{}{} <- {}#{} wraps {}#{}{} ({} site(s))", (Object)PatchTransformer.targetClassName(method), (Object)methodNode.name, (Object)methodNode.desc, (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)string3, (Object)string4, (Object)string2, (Object)list.size());
    }

    private static void modifyLocals(MethodNode methodNode, Method method) {
        int n2;
        int n3;
        Class<?>[] classArray;
        ModifyLocals modifyLocals = method.getAnnotation(ModifyLocals.class);
        int[] nArray = modifyLocals.indexes();
        if (nArray.length != (classArray = modifyLocals.types()).length) {
            throw new IllegalArgumentException("@ModifyLocals indexes/types length mismatch in " + String.valueOf(method));
        }
        Type[] typeArray = new Type[classArray.length];
        for (int i2 = 0; i2 < classArray.length; ++i2) {
            typeArray[i2] = Type.getType(classArray[i2]);
        }
        At at = modifyLocals.at();
        Object object = methodNode.instructions.getFirst();
        HashSet<Integer> hashSet = new HashSet<Integer>();
        if (at.value() != At.Type.HEAD) {
            boolean bl = false;
            if (at.value() == At.Type.TAIL) {
                int n4 = Type.getReturnType((String)methodNode.desc).getOpcode(172);
                for (Object object2 : methodNode.instructions) {
                    AbstractInsnNode abstractInsnNode;
                    if (object2.getOpcode() == n4) {
                        object = object2;
                        bl = true;
                        break;
                    }
                    if (!(object2 instanceof VarInsnNode) || (abstractInsnNode = (VarInsnNode)object2).getOpcode() < 54 || abstractInsnNode.getOpcode() > 58) continue;
                    hashSet.add(abstractInsnNode.var);
                }
            } else {
                String[] stringArray = ASMHelpers.splitOwnerName(at.method());
                String string = Bootstrap.remapMethod(stringArray[0], stringArray[1], at.desc());
                for (AbstractInsnNode abstractInsnNode : methodNode.instructions) {
                    MethodInsnNode methodInsnNode;
                    if (abstractInsnNode instanceof MethodInsnNode) {
                        methodInsnNode = (MethodInsnNode)abstractInsnNode;
                        if (methodInsnNode.owner.equals(stringArray[0]) && methodInsnNode.name.equals(string) && methodInsnNode.desc.equals(at.desc())) {
                            object = abstractInsnNode;
                            bl = true;
                            break;
                        }
                    }
                    if (!(abstractInsnNode instanceof VarInsnNode) || (methodInsnNode = (VarInsnNode)abstractInsnNode).getOpcode() < 54 || methodInsnNode.getOpcode() > 58) continue;
                    hashSet.add(methodInsnNode.var);
                }
            }
            if (!bl) {
                LOGGER.warn("@ModifyLocals {}#{}{} found no anchor {} {}{} in target {}{} \u2014 patch handler will not run.", (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)Type.getMethodDescriptor((Method)method), (Object)at.value(), (Object)at.method(), (Object)at.desc(), (Object)methodNode.name, (Object)methodNode.desc);
                return;
            }
            for (int n5 : nArray) {
                if (!hashSet.contains(n5) && at.value() == At.Type.HEAD) continue;
            }
        }
        InsnList insnList = new InsnList();
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(Locals.class), "create", "()" + Type.getDescriptor(Locals.class), false));
        for (n3 = 0; n3 < nArray.length; ++n3) {
            n2 = nArray[n3];
            Type type = typeArray[n3];
            insnList.add((AbstractInsnNode)new LdcInsnNode((Object)n2));
            insnList.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(21), n2));
            insnList.add(ASMHelpers.boxToObject(type));
            insnList.add((AbstractInsnNode)new MethodInsnNode(185, Type.getInternalName(ILocals.class), "set", "(ILjava/lang/Object;)" + Type.getDescriptor(ILocals.class), true));
        }
        insnList.add((AbstractInsnNode)new InsnNode(89));
        insnList.add((AbstractInsnNode)new MethodInsnNode(184, Type.getInternalName(method.getDeclaringClass()), method.getName(), Type.getMethodDescriptor((Method)method), false));
        for (n3 = 0; n3 < nArray.length; ++n3) {
            n2 = nArray[n3];
            Type type = typeArray[n3];
            insnList.add((AbstractInsnNode)new InsnNode(89));
            insnList.add((AbstractInsnNode)new LdcInsnNode((Object)n2));
            insnList.add((AbstractInsnNode)new MethodInsnNode(185, Type.getInternalName(ILocals.class), "get", "(I)Ljava/lang/Object;", true));
            insnList.add(ASMHelpers.unboxFromObject(type));
            insnList.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(54), n2));
        }
        insnList.add((AbstractInsnNode)new InsnNode(87));
        methodNode.instructions.insertBefore(object, insnList);
        LOGGER.info("@ModifyLocals  {}/{}{} <- {}#{} (locals: {})", (Object)PatchTransformer.targetClassName(method), (Object)methodNode.name, (Object)methodNode.desc, (Object)method.getDeclaringClass().getName(), (Object)method.getName(), (Object)Arrays.toString(nArray));
    }

    private static String targetClassName(Method method) {
        Patch patch = method.getDeclaringClass().getAnnotation(Patch.class);
        if (patch == null) {
            return "?";
        }
        if (!patch.className().isEmpty()) {
            return patch.className().replace('.', '/');
        }
        return Type.getInternalName(patch.value());
    }

    /*
     * Unable to fully structure code
     */
    private static List<AbstractInsnNode> collectInjectionPoints(InsnList var0, Slice var1_1, Predicate<AbstractInsnNode> var2_2) {
        var3_3 = var1_1.start().value() == At.Type.HEAD;
        var4_4 = var1_1.end().value() == At.Type.TAIL;
        var5_5 = var1_1.startIndex() != -1 || var1_1.endIndex() != -1;
        var6_6 = new ArrayList<AbstractInsnNode>();
        if (var3_3 && var4_4 && !var5_5) {
            for (AbstractInsnNode var8_10 : var0) {
                if (!var2_2.test(var8_10)) continue;
                var6_6.add(var8_10);
            }
            return var6_6;
        }
        if (var5_5) {
            var7_8 = 0;
            var8_11 = var1_1.endIndex() == -1 ? 0x7FFFFFFF : var1_1.endIndex();
            var9_13 = var1_1.startIndex() == -1 ? 1 : var1_1.startIndex();
            for (AbstractInsnNode var11_17 : var0) {
                if (!var2_2.test(var11_17)) continue;
                if (++var7_8 >= var9_13 && var7_8 <= var8_11) {
                    var6_6.add(var11_17);
                    continue;
                }
                if (var7_8 <= var8_11) continue;
                break;
            }
            return var6_6;
        }
        var7_9 = var1_1.start().method().isEmpty() != false ? null : ASMHelpers.splitOwnerName(var1_1.start().method());
        var8_12 = var1_1.start().desc();
        var9_14 = var7_9 == null ? null : Bootstrap.remapMethod(var7_9[0], var7_9[1], var8_12);
        var10_16 = var1_1.end().method().isEmpty() != false ? null : ASMHelpers.splitOwnerName(var1_1.end().method());
        var11_18 = var1_1.end().desc();
        var12_19 = var10_16 == null ? null : Bootstrap.remapMethod(var10_16[0], var10_16[1], var11_18);
        var13_20 = var3_3;
        for (AbstractInsnNode var15_22 : var0) {
            if (var13_20 || var7_9 == null || !(var15_22 instanceof MethodInsnNode)) ** GOTO lbl-1000
            var16_23 = (MethodInsnNode)var15_22;
            if (var16_23.owner.equals(var7_9[0]) && var16_23.name.equals(var9_14) && var16_23.desc.equals(var8_12)) {
                var13_20 = true;
            } else if (!var4_4 && var10_16 != null && var15_22 instanceof MethodInsnNode) {
                var17_24 = (MethodInsnNode)var15_22;
                if (var17_24.owner.equals(var10_16[0]) && var17_24.name.equals(var12_19) && var17_24.desc.equals(var11_18)) break;
            }
            if (!var13_20) continue;
            var6_6.add(var15_22);
        }
        return var6_6;
    }

    private static Set<Integer> collectInitializedLocalsBefore(MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode abstractInsnNode2;
        HashSet<Integer> hashSet = new HashSet<Integer>();
        ListIterator listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext() && (abstractInsnNode2 = (AbstractInsnNode)listIterator.next()) != abstractInsnNode) {
            VarInsnNode varInsnNode;
            if (!(abstractInsnNode2 instanceof VarInsnNode) || (varInsnNode = (VarInsnNode)abstractInsnNode2).getOpcode() < 54 || varInsnNode.getOpcode() > 58) continue;
            hashSet.add(varInsnNode.var);
        }
        return hashSet;
    }

    private static void swapForCallback(Type type, InsnList insnList) {
        if (type == Type.LONG_TYPE || type == Type.DOUBLE_TYPE) {
            insnList.add((AbstractInsnNode)new InsnNode(93));
            insnList.add((AbstractInsnNode)new InsnNode(88));
        } else {
            insnList.add((AbstractInsnNode)new InsnNode(95));
        }
    }

    private static void keepArraysImport() {
        Arrays.asList(new Object[0]);
    }

    private record MethodKey(String name, String desc) {
        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object object) {
            if (!(object instanceof MethodKey)) return false;
            MethodKey methodKey = (MethodKey)object;
            if (!methodKey.name.equals(this.name)) return false;
            if (!methodKey.desc.equals(this.desc)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() * 31 + this.desc.hashCode();
        }
    }
}

