package com.github.mouse0w0.fastreflection;

import java.lang.reflect.Method;

public interface MethodAccessor {

	/**
	 * Returns the {@code Method} object that the accessor
	 * access to.
	 */
	Method getMethod();

	/**
	 * @param obj  the object the underlying method is invoked from
	 * @param args the arguments used for the method call
	 * @return the result of dispatching the method represented by
	 * this object on {@code obj} with parameters
	 */
	Object invoke(Object obj, Object... args) throws Exception;
	
}
