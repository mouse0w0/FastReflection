package com.github.mouse0w0.fastreflection.accassor;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.GeneratorAdapter;

import static org.objectweb.asm.Opcodes.*;

import com.github.mouse0w0.fastreflection.MethodAccessor;
import com.github.mouse0w0.fastreflection.util.AsmUtils;
import com.github.mouse0w0.fastreflection.util.SafeClassDefiner;

public class AsmMethodAccessor {
	
	private static int id = 0;
	
	public static MethodAccessor create(Method method) throws Exception {
		boolean isStatic = Modifier.isStatic(method.getModifiers());
		Class<?> declaringClass = method.getDeclaringClass();
		Type declaringClassType = Type.getType(declaringClass);
		String declaringClassName = declaringClassType.getInternalName();
		String className = String.format("AsmMethodAccessor_%d_%s_%s", id++, declaringClass.getSimpleName(),
				method.getName());
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		FieldVisitor fv;
		MethodVisitor mv;

		cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, className, null, "java/lang/Object",
				new String[] { "com/github/mouse0w0/fastreflection/MethodAccessor" });

		cw.visitSource(".dynamic", null);

		fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "method", "Ljava/lang/reflect/Method;", null, null);
		fv.visitEnd();

		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/reflect/Method;)V", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitFieldInsn(PUTFIELD, className, "method", "Ljava/lang/reflect/Method;");
			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		{
			mv = cw.visitMethod(ACC_PUBLIC, "getMethod", "()Ljava/lang/reflect/Method;", null, null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, className, "method", "Ljava/lang/reflect/Method;");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		{
			GeneratorAdapter ag = new GeneratorAdapter(
					cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, "invoke",
							"(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null,
							new String[] { "java/lang/Exception" }),
					ACC_PUBLIC + ACC_VARARGS, "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;");
			if (!isStatic) {
				ag.loadArg(0);
				ag.checkCast(declaringClassType);
			}
			Class<?>[] types = method.getParameterTypes();
			for (int i = 0; i < types.length; i++) {
				Class<?> paraClazz = types[i];
				Type paraType = Type.getType(paraClazz);
				ag.loadArg(1);
				ag.push(i);
				ag.arrayLoad(AsmUtils.OBJECT_TYPE);
				if(paraClazz.isPrimitive())
					ag.unbox(paraType);
				else
					ag.checkCast(paraType);
			}
			if (isStatic) {
				ag.visitMethodInsn(INVOKESTATIC, declaringClassName, method.getName(),
						Type.getMethodDescriptor(method), false);
			} else if (method.getDeclaringClass().isInterface()) {
				ag.visitMethodInsn(INVOKEINTERFACE, declaringClassName, method.getName(),
						Type.getMethodDescriptor(method), true);
			} else {
				ag.visitMethodInsn(INVOKEVIRTUAL, declaringClassName, method.getName(),
						Type.getMethodDescriptor(method), false);
			}
			if (method.getReturnType().equals(void.class))
				ag.push((Type)null);
			else
				ag.box(Type.getReturnType(method));
			ag.returnValue();
			ag.visitMaxs(0, 0);
			ag.endMethod();
		}
		cw.visitEnd();

		return (MethodAccessor) SafeClassDefiner.INSTANCE
				.defineClass(declaringClass.getClassLoader(), className, cw.toByteArray()).getConstructor(Method.class)
				.newInstance(method);
	}
}
