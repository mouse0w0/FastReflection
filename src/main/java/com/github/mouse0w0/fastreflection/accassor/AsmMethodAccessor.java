package com.github.mouse0w0.fastreflection.accassor;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.GeneratorAdapter;

import static org.objectweb.asm.Opcodes.*;

import com.github.mouse0w0.fastreflection.MethodAccessor;
import com.github.mouse0w0.fastreflection.util.SafeClassDefiner;

public class AsmMethodAccessor {

	public static MethodAccessor create(Method method) throws Exception {
		boolean isStatic = Modifier.isStatic(method.getModifiers());
		Class<?> declaringClass = method.getDeclaringClass();
		String className = getUniqueName(method);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		FieldVisitor fv;
		MethodVisitor mv;

		cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, className, null, "java/lang/Object",
				new String[] { "team/unstudio/udpl/util/reflect/MethodAccessor" });

		cw.visitSource(".dynamic", null);

		fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "method", "Ljava/lang/reflect/Method;", null, null);
		fv.visitEnd();

		if (!isStatic) {
			fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "instance", "Ljava/lang/Object;", null, null);
			fv.visitEnd();
		}

		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>",
					isStatic ? "(Ljava/lang/reflect/Method;)V" : "(Ljava/lang/reflect/Method;Ljava/lang/Object;)V",
					null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitFieldInsn(PUTFIELD, className, "method", "Ljava/lang/reflect/Method;");
			if (!isStatic) {
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitFieldInsn(PUTFIELD, className, "instance", "Ljava/lang/Object;");
			}
			mv.visitInsn(RETURN);
			mv.visitEnd();
		}

		{
			mv = cw.visitMethod(ACC_PUBLIC, "getMethod", "()Ljava/lang/reflect/Method;", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitInsn(ARETURN);
			mv.visitEnd();
		}

		{
			GeneratorAdapter ag = new GeneratorAdapter(
					cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, "invoke",
							"(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null,
							new String[] { "java/lang/Exception" }),
					ACC_PUBLIC + ACC_VARARGS, "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;");
			if (!isStatic) {
				ag.loadArg(1);
			}
			Class<?>[] types = method.getParameterTypes();
			for (int i = 0; i < types.length; i++) {
				Type type = Type.getType(types[i]);
				ag.loadArg(2);
				ag.push(i);
				ag.arrayLoad(type);
				ag.checkCast(type);
			}
			if (isStatic) {
				ag.visitMethodInsn(INVOKESTATIC, Type.getInternalName(declaringClass), method.getName(),
						Type.getMethodDescriptor(method), false);
			} else if (method.getDeclaringClass().isInterface()) {
				ag.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(declaringClass), method.getName(),
						Type.getMethodDescriptor(method), true);
			} else {
				ag.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(declaringClass), method.getName(),
						Type.getMethodDescriptor(method), false);
			}
			ag.box(Type.getReturnType(method));
			ag.returnValue();
			ag.endMethod();
		}
		cw.visitEnd();

		return (MethodAccessor) SafeClassDefiner.INSTANCE
				.defineClass(declaringClass.getClassLoader(), className, cw.toByteArray()).getConstructor(Method.class)
				.newInstance(method);
	}

	private static int id = 0;

	private static String getUniqueName(Method method) {
		return String.format("AsmMethodAccessor_%d_%s_%s", id++, method.getDeclaringClass().getSimpleName(),
				method.getName());
	}

}
