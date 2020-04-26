package com.github.mouse0w0.fastreflection.accessor;

import com.github.mouse0w0.fastreflection.FieldAccessor;

import java.lang.reflect.Field;

import static com.github.mouse0w0.fastreflection.util.UnsafeUtils.getUnsafe;
import static java.util.Objects.requireNonNull;

public class UnsafeVolatileFieldAccessor implements FieldAccessor {

	private final Field field;
	private final long offest;

	public UnsafeVolatileFieldAccessor(Field field) {
		this.field = requireNonNull(field);
		this.offest = getUnsafe().objectFieldOffset(field);
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public Object get(Object obj) throws Exception {
		return getUnsafe().getObjectVolatile(requireNonNull(obj), offest);
	}

	@Override
	public byte getByte(Object obj) throws Exception {
		return getUnsafe().getByteVolatile(requireNonNull(obj), offest);
	}

	@Override
	public short getShort(Object obj) throws Exception {
		return getUnsafe().getShortVolatile(requireNonNull(obj), offest);
	}

	@Override
	public int getInt(Object obj) throws Exception {
		return getUnsafe().getIntVolatile(requireNonNull(obj), offest);
	}

	@Override
	public long getLong(Object obj) throws Exception {
		return getUnsafe().getLongVolatile(requireNonNull(obj), offest);
	}

	@Override
	public float getFloat(Object obj) throws Exception {
		return getUnsafe().getFloatVolatile(requireNonNull(obj), offest);
	}

	@Override
	public double getDouble(Object obj) throws Exception {
		return getUnsafe().getDoubleVolatile(requireNonNull(obj), offest);
	}

	@Override
	public boolean getBoolean(Object obj) throws Exception {
		return getUnsafe().getBooleanVolatile(requireNonNull(obj), offest);
	}

	@Override
	public char getChar(Object obj) throws Exception {
		return getUnsafe().getCharVolatile(requireNonNull(obj), offest);
	}

	@Override
	public void set(Object obj, Object value) throws Exception {
		getUnsafe().putObjectVolatile(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, byte value) throws Exception {
		getUnsafe().putByteVolatile(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, short value) throws Exception {
		getUnsafe().putShortVolatile(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, int value) throws Exception {
		getUnsafe().putIntVolatile(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, long value) throws Exception {
		getUnsafe().putLongVolatile(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, float value) throws Exception {
		getUnsafe().putFloatVolatile(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, double value) throws Exception {
		getUnsafe().putDoubleVolatile(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, boolean value) throws Exception {
		getUnsafe().putBooleanVolatile(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, char value) throws Exception {
		getUnsafe().putCharVolatile(requireNonNull(obj), offest, value);
	}
}
