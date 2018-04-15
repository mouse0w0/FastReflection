package com.github.mouse0w0.fastreflection;

import java.lang.reflect.Method;

public interface MethodAccessor {

	Method getMethod();
	
	Object invoke(Object obj, Object... args) throws Exception;
	
}
