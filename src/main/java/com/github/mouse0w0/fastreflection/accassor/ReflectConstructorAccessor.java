package com.github.mouse0w0.fastreflection.accassor;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Constructor;
import com.github.mouse0w0.fastreflection.ConstructorAccessor;

public class ReflectConstructorAccessor<T> implements ConstructorAccessor<T> {
	
	private final Constructor<T> constructor;
	
	public ReflectConstructorAccessor(Constructor<T> constructor) {
		this.constructor = requireNonNull(constructor);
		constructor.setAccessible(true);
	}

	@Override
	public Constructor<T> getConstructor() {
		return constructor;
	}

	@Override
	public T newInstance(Object... initargs) throws Exception {
		return constructor.newInstance(initargs);
	}

}
