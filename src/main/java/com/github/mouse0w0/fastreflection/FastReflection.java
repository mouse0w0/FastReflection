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
import com.github.mouse0w0.fastreflection.accassor.UnsafeVolatileFieldAccessor;
import com.github.mouse0w0.fastreflection.util.UnsafeUtils;

public class FastReflection {
	
	public static FieldAccessor create(Field field) throws Exception {
		return create(field, false);
	}
	
	public static FieldAccessor create(Field field, boolean unsafe) throws Exception{
		int modifiers = field.getModifiers();
		
		if(Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers)) {
			return AsmFieldAccessor.create(field);
		}
		
		if(unsafe && UnsafeUtils.isUnsafeSupported()) {
			if(Modifier.isVolatile(modifiers))
				return new UnsafeVolatileFieldAccessor(field);
			else
				return new UnsafeFieldAccessor(field);
		}
		
		return new ReflectFieldAccessor(field, unsafe);
	}
	
	public static MethodAccessor create(Method method) throws Exception {
		int modifiers = method.getModifiers();
		
		if(Modifier.isPublic(modifiers)) {
			return AsmMethodAccessor.create(method);
		}
		
		return new ReflectMethodAccessor(method);
	}
	
	public static <T> ConstructorAccessor<T> create(Constructor<T> constructor) {
		int modifiers = constructor.getModifiers();
		
		if(Modifier.isPublic(modifiers)) {
			return AsmConstructorAccessor.create(constructor);
		}
		
		return new ReflectConstructorAccessor<>(constructor);
	}
}
