package com.github.mouse0w0.fastreflection;

import java.lang.reflect.Constructor;

public interface ConstructorAccessor<T> {
	
	Constructor<T> getConstructor();

	T newInstance(Object ... initargs) throws Exception;
}
