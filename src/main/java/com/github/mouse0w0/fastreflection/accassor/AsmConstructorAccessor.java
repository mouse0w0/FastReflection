package com.github.mouse0w0.fastreflection.accassor;

import java.lang.reflect.Constructor;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.GeneratorAdapter;

import com.github.mouse0w0.fastreflection.ConstructorAccessor;
import com.github.mouse0w0.fastreflection.util.AsmUtils;
import com.github.mouse0w0.fastreflection.util.SafeClassDefiner;

import static java.util.Objects.requireNonNull;
import static org.objectweb.asm.Opcodes.*;

public final class AsmConstructorAccessor {

	private AsmConstructorAccessor() {
	}

	private static int id = 0;

	@SuppressWarnings("unchecked")
	public static <T> ConstructorAccessor<T> create(Constructor<T> constructor) throws Exception{
		requireNonNull(constructor);
		
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
			mv.visitMaxs(0, 0);
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
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, "newInstance", "([Ljava/lang/Object;)Ljava/lang/Object;",
					null, new String[] { "java/lang/Exception" });
			GeneratorAdapter ag = new GeneratorAdapter(mv, ACC_PUBLIC + ACC_VARARGS, "newInstance",
					"([Ljava/lang/Object;)Ljava/lang/Object;");
			ag.newInstance(declaringClassType);
			ag.dup();
			Class<?>[] types = constructor.getParameterTypes();
			for (int i = 0; i < types.length; i++) {
				Class<?> paraClazz = types[i];
				Type paraType = Type.getType(paraClazz);
				ag.loadArg(0);
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
			ag.visitMaxs(0, 0);
			ag.endMethod();
		}
		cw.visitEnd();
		
		return (ConstructorAccessor<T>) SafeClassDefiner.INSTANCE
				.defineClass(declaringClass.getClassLoader(), className, cw.toByteArray())
				.getConstructor(Constructor.class).newInstance(constructor);
	}

}
