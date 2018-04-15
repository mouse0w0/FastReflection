package com.github.mouse0w0.fastreflection.accassor;

import java.lang.reflect.Method;

import com.github.mouse0w0.fastreflection.MethodAccessor;

public class ReflectMethodAccessor implements MethodAccessor {
	
	private final Method method;
	
	public ReflectMethodAccessor(Method method) {
		method.setAccessible(true);
		this.method = method;
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public Object invoke(Object obj, Object... args) throws Exception {
		return method.invoke(obj, args);
	}
}
