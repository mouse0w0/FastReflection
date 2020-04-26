package com.github.mouse0w0.fastreflection.accessor;

import com.github.mouse0w0.fastreflection.FieldAccessor;

import java.lang.reflect.Field;

import static com.github.mouse0w0.fastreflection.util.UnsafeUtils.getUnsafe;
import static java.util.Objects.requireNonNull;

public class UnsafeStaticVolatileFieldAccessor implements FieldAccessor {

	private final Field field;
	private final Class<?> declaringClass;
	private final long offest;

	public UnsafeStaticVolatileFieldAccessor(Field field) {
		this.field = requireNonNull(field);
		this.declaringClass = field.getDeclaringClass();
		this.offest = getUnsafe().staticFieldOffset(field);
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public Object get(Object obj) throws Exception {
		return getUnsafe().getObjectVolatile(declaringClass, offest);
	}

	@Override
	public byte getByte(Object obj) throws Exception {
		return getUnsafe().getByteVolatile(declaringClass, offest);
	}

	@Override
	public short getShort(Object obj) throws Exception {
		return getUnsafe().getShortVolatile(declaringClass, offest);
	}

	@Override
	public int getInt(Object obj) throws Exception {
		return getUnsafe().getIntVolatile(declaringClass, offest);
	}

	@Override
	public long getLong(Object obj) throws Exception {
		return getUnsafe().getLongVolatile(declaringClass, offest);
	}

	@Override
	public float getFloat(Object obj) throws Exception {
		return getUnsafe().getFloatVolatile(declaringClass, offest);
	}

	@Override
	public double getDouble(Object obj) throws Exception {
		return getUnsafe().getDoubleVolatile(declaringClass, offest);
	}

	@Override
	public boolean getBoolean(Object obj) throws Exception {
		return getUnsafe().getBooleanVolatile(declaringClass, offest);
	}

	@Override
	public char getChar(Object obj) throws Exception {
		return getUnsafe().getCharVolatile(declaringClass, offest);
	}

	@Override
	public void set(Object obj, Object value) throws Exception {
		getUnsafe().putObjectVolatile(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, byte value) throws Exception {
		getUnsafe().putByteVolatile(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, short value) throws Exception {
		getUnsafe().putShortVolatile(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, int value) throws Exception {
		getUnsafe().putIntVolatile(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, long value) throws Exception {
		getUnsafe().putLongVolatile(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, float value) throws Exception {
		getUnsafe().putFloatVolatile(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, double value) throws Exception {
		getUnsafe().putDoubleVolatile(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, boolean value) throws Exception {
		getUnsafe().putBooleanVolatile(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, char value) throws Exception {
		getUnsafe().putCharVolatile(declaringClass, offest, value);
	}
}
