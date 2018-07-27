package com.github.mouse0w0.fastreflection;

import java.lang.reflect.Constructor;

public interface ConstructorAccessor<T> {

	/**
	 * Returns the {@code Constructor} object that the accessor
	 * access to.
	 */
	Constructor<T> getConstructor();

	/**
	 * @param initArgs array of objects to be passed as arguments to
	 * the constructor call; values of primitive types are wrapped in
	 * a wrapper object of the appropriate type (e.g. a {@code float}
	 * in a {@link java.lang.Float Float})
	 */
	T newInstance(Object ... initArgs) throws Exception;

}
