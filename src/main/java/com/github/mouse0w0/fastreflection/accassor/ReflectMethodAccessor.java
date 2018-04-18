package com.github.mouse0w0.fastreflection.accassor;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Method;

import com.github.mouse0w0.fastreflection.MethodAccessor;

public class ReflectMethodAccessor implements MethodAccessor {
	
	private final Method method;
	
	public ReflectMethodAccessor(Method method) {
		this.method = requireNonNull(method);
		method.setAccessible(true);
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
