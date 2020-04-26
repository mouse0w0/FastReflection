package com.github.mouse0w0.fastreflection.accessor;

import com.github.mouse0w0.fastreflection.FieldAccessor;

import java.lang.reflect.Field;

import static com.github.mouse0w0.fastreflection.util.UnsafeUtils.getUnsafe;
import static java.util.Objects.requireNonNull;

public class UnsafeFieldAccessor implements FieldAccessor {

	private final Field field;
	private final long offest;

	public UnsafeFieldAccessor(Field field) {
		this.field = requireNonNull(field);
		this.offest = getUnsafe().objectFieldOffset(field);
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public Object get(Object obj) throws Exception {
		return getUnsafe().getObject(requireNonNull(obj), offest);
	}

	@Override
	public byte getByte(Object obj) throws Exception {
		return getUnsafe().getByte(requireNonNull(obj), offest);
	}

	@Override
	public short getShort(Object obj) throws Exception {
		return getUnsafe().getShort(requireNonNull(obj), offest);
	}

	@Override
	public int getInt(Object obj) throws Exception {
		return getUnsafe().getInt(requireNonNull(obj), offest);
	}

	@Override
	public long getLong(Object obj) throws Exception {
		return getUnsafe().getLong(requireNonNull(obj), offest);
	}

	@Override
	public float getFloat(Object obj) throws Exception {
		return getUnsafe().getFloat(requireNonNull(obj), offest);
	}

	@Override
	public double getDouble(Object obj) throws Exception {
		return getUnsafe().getDouble(requireNonNull(obj), offest);
	}

	@Override
	public boolean getBoolean(Object obj) throws Exception {
		return getUnsafe().getBoolean(requireNonNull(obj), offest);
	}

	@Override
	public char getChar(Object obj) throws Exception {
		return getUnsafe().getChar(requireNonNull(obj), offest);
	}

	@Override
	public void set(Object obj, Object value) throws Exception {
		getUnsafe().putObject(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, byte value) throws Exception {
		getUnsafe().putByte(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, short value) throws Exception {
		getUnsafe().putShort(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, int value) throws Exception {
		getUnsafe().putInt(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, long value) throws Exception {
		getUnsafe().putLong(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, float value) throws Exception {
		getUnsafe().putFloat(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, double value) throws Exception {
		getUnsafe().putDouble(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, boolean value) throws Exception {
		getUnsafe().putBoolean(requireNonNull(obj), offest, value);
	}

	@Override
	public void set(Object obj, char value) throws Exception {
		getUnsafe().putChar(requireNonNull(obj), offest, value);
	}
}
