package com.github.mouse0w0.fastreflection.accassor;

import java.lang.reflect.Constructor;
import com.github.mouse0w0.fastreflection.ConstructorAccessor;

public class ReflectConstructorAccessor<T> implements ConstructorAccessor<T> {
	
	private final Constructor<T> constructor;
	
	public ReflectConstructorAccessor(Constructor<T> constructor) {
		constructor.setAccessible(true);
		this.constructor = constructor;
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
