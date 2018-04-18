package com.github.mouse0w0.fastreflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.github.mouse0w0.fastreflection.accassor.AsmConstructorAccessor;
import com.github.mouse0w0.fastreflection.accassor.AsmFieldAccessor;
import com.github.mouse0w0.fastreflection.accassor.AsmMethodAccessor;
import com.github.mouse0w0.fastreflection.accassor.ReflectConstructorAccessor;
import com.github.mouse0w0.fastreflection.accassor.ReflectFieldAccessor;
import com.github.mouse0w0.fastreflection.accassor.ReflectMethodAccessor;
import com.github.mouse0w0.fastreflection.accassor.UnsafeFieldAccessor;
import com.github.mouse0w0.fastreflection.accassor.UnsafeStaticFieldAccessor;
import com.github.mouse0w0.fastreflection.accassor.UnsafeStaticVolatileFieldAccessor;
import com.github.mouse0w0.fastreflection.accassor.UnsafeVolatileFieldAccessor;
import com.github.mouse0w0.fastreflection.util.UnsafeReflections;
import com.github.mouse0w0.fastreflection.util.UnsafeUtils;

public class FastReflection {
	
	public static FieldAccessor create(Field field) throws Exception {
		return create(field, false);
	}
	
	public static FieldAccessor create(Field field, boolean unsafe) throws Exception {
		int modifiers = field.getModifiers();
		
		if(Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers)) {
			return AsmFieldAccessor.create(field);
		}
		
		if (unsafe && UnsafeUtils.isUnsafeSupported()) {
			if (Modifier.isVolatile(modifiers))
				return Modifier.isStatic(modifiers) ? new UnsafeStaticVolatileFieldAccessor(field)
						: new UnsafeVolatileFieldAccessor(field);
			else
				return Modifier.isStatic(modifiers) ? new UnsafeStaticFieldAccessor(field)
						: new UnsafeFieldAccessor(field);
		}

		if (unsafe)
			UnsafeReflections.unfinal(field);
		return new ReflectFieldAccessor(field);
	}
	
	public static MethodAccessor create(Method method) throws Exception {
		int modifiers = method.getModifiers();
		
		if(Modifier.isPublic(modifiers)) {
			return AsmMethodAccessor.create(method);
		}
		
		return new ReflectMethodAccessor(method);
	}
	
	public static <T> ConstructorAccessor<T> create(Constructor<T> constructor) throws Exception {
		int modifiers = constructor.getModifiers();
		
		if(Modifier.isPublic(modifiers)) {
			return AsmConstructorAccessor.create(constructor);
		}
		
		return new ReflectConstructorAccessor<>(constructor);
	}
}
