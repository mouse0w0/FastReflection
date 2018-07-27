package com.github.mouse0w0.fastreflection.util;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public final class UnsafeUtils {
	
	private UnsafeUtils() {
	}

	/**
	 * Cached {@code Unsafe} object
	 */
	private static final Unsafe UNSAFE;
	static {
		// Getting object of Unsafe
		Unsafe unsafe;
		try {
			Class clazz = Class.forName("sun.misc.Unsafe");
			Field theUnsafe = clazz.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			unsafe = (Unsafe) theUnsafe.get(null);
		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
			unsafe = null;
		} catch (IllegalAccessException e) {
			throw new AssertionError(e);
		}
		UNSAFE = unsafe;
	}

	/**
	 * @return true if success to get {@code Unsafe} object
	 */
	public static boolean isUnsafeSupported() {
		return UNSAFE != null;
	}

	/**
	 * @return the object of {@code Unsafe},
	 * may return null if Unsafe is not supported
	 */
	public static Unsafe getUnsafe() {
		return UNSAFE;
	}
}
