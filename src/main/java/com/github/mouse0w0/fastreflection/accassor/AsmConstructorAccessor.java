package com.github.mouse0w0.fastreflection.accassor;

import java.lang.reflect.Constructor;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.GeneratorAdapter;

import com.github.mouse0w0.fastreflection.ConstructorAccessor;
import com.github.mouse0w0.fastreflection.util.AsmUtils;

import static org.objectweb.asm.Opcodes.*;

public class AsmConstructorAccessor {

	private static int id = 0;
	
	public static <T> ConstructorAccessor<T> create(Constructor<T> constructor) {
		Class<?> declaringClass = constructor.getDeclaringClass();
		Type declaringClassType = Type.getType(declaringClass);
		String declaringClassName = declaringClassType.getInternalName();
		String declaringClassDesc = declaringClassType.getDescriptor();
		String className = String.format("AsmConstructorAccessor_%d_%s", id++, declaringClass.getSimpleName());
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		FieldVisitor fv;
		MethodVisitor mv;

		cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, className,
				"Ljava/lang/Object;Lcom/github/mouse0w0/fastreflection/ConstructorAccessor<"+declaringClassDesc+">;",
				"java/lang/Object", new String[] { "com/github/mouse0w0/fastreflection/ConstructorAccessor" });

		cw.visitSource(".dynamic", null);

		{
			fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "constructor", "Ljava/lang/reflect/Constructor;",
					"Ljava/lang/reflect/Constructor<"+declaringClassDesc+">;",
					null);
			fv.visitEnd();
		}

		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/reflect/Constructor;)V",
					"(Ljava/lang/reflect/Constructor<"+declaringClassDesc+">;)V",
					null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitFieldInsn(PUTFIELD, className, "constructor",
					"Ljava/lang/reflect/Constructor;");
			mv.visitInsn(RETURN);
			mv.visitEnd();
		}

		{
			mv = cw.visitMethod(ACC_PUBLIC, "getConstructor", "()Ljava/lang/reflect/Constructor;",
					"()Ljava/lang/reflect/Constructor<"+declaringClassDesc+">;",
					null);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, className, "constructor",
					"Ljava/lang/reflect/Constructor;");
			mv.visitInsn(ARETURN);
			mv.visitEnd();
		}

		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, "newInstance", "([Ljava/lang/Object;)" + declaringClassDesc,
					null, new String[] { "java/lang/Exception" });
			GeneratorAdapter ag = new GeneratorAdapter(mv, ACC_PUBLIC + ACC_VARARGS, "newInstance",
					"([Ljava/lang/Object;)" + declaringClassDesc);
			ag.newInstance(declaringClassType);
			ag.dup();
			Class<?>[] types = constructor.getParameterTypes();
			for (int i = 0; i < types.length; i++) {
				Class<?> paraClazz = types[i];
				Type paraType = Type.getType(paraClazz);
				ag.loadArg(1);
				ag.push(i);
				ag.arrayLoad(AsmUtils.OBJECT_TYPE);
				if (paraClazz.isPrimitive())
					ag.unbox(paraType);
				else
					ag.checkCast(paraType);
			}
			ag.visitMethodInsn(INVOKESPECIAL, declaringClassName, "<init>", Type.getConstructorDescriptor(constructor),
					false);
			ag.returnValue();
			mv.visitEnd();
		}
		cw.visitEnd();
		return null;
	}

}
