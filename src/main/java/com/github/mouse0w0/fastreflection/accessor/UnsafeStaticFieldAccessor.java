package com.github.mouse0w0.fastreflection.accessor;

import com.github.mouse0w0.fastreflection.FieldAccessor;

import java.lang.reflect.Field;

import static com.github.mouse0w0.fastreflection.util.UnsafeUtils.getUnsafe;
import static java.util.Objects.requireNonNull;

public class UnsafeStaticFieldAccessor implements FieldAccessor {

	private final Field field;
	private final Class<?> declaringClass;
	private final long offest;

	public UnsafeStaticFieldAccessor(Field field) {
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
		return getUnsafe().getObject(declaringClass, offest);
	}

	@Override
	public byte getByte(Object obj) throws Exception {
		return getUnsafe().getByte(declaringClass, offest);
	}

	@Override
	public short getShort(Object obj) throws Exception {
		return getUnsafe().getShort(declaringClass, offest);
	}

	@Override
	public int getInt(Object obj) throws Exception {
		return getUnsafe().getInt(declaringClass, offest);
	}

	@Override
	public long getLong(Object obj) throws Exception {
		return getUnsafe().getLong(declaringClass, offest);
	}

	@Override
	public float getFloat(Object obj) throws Exception {
		return getUnsafe().getFloat(declaringClass, offest);
	}

	@Override
	public double getDouble(Object obj) throws Exception {
		return getUnsafe().getDouble(declaringClass, offest);
	}

	@Override
	public boolean getBoolean(Object obj) throws Exception {
		return getUnsafe().getBoolean(declaringClass, offest);
	}

	@Override
	public char getChar(Object obj) throws Exception {
		return getUnsafe().getChar(declaringClass, offest);
	}

	@Override
	public void set(Object obj, Object value) throws Exception {
		getUnsafe().putObject(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, byte value) throws Exception {
		getUnsafe().putByte(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, short value) throws Exception {
		getUnsafe().putShort(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, int value) throws Exception {
		getUnsafe().putInt(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, long value) throws Exception {
		getUnsafe().putLong(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, float value) throws Exception {
		getUnsafe().putFloat(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, double value) throws Exception {
		getUnsafe().putDouble(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, boolean value) throws Exception {
		getUnsafe().putBoolean(declaringClass, offest, value);
	}

	@Override
	public void set(Object obj, char value) throws Exception {
		getUnsafe().putChar(declaringClass, offest, value);
	}
}
