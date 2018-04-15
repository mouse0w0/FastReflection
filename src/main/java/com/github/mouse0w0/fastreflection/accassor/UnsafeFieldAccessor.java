package com.github.mouse0w0.fastreflection.accassor;

import static com.github.mouse0w0.fastreflection.util.UnsafeUtils.getUnsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.github.mouse0w0.fastreflection.FieldAccessor;

public class UnsafeFieldAccessor implements FieldAccessor {

	private final Field field;
	private final long offest;

	public UnsafeFieldAccessor(Field field) {
		this.field = field;
		this.offest = Modifier.isStatic(field.getModifiers()) ? getUnsafe().staticFieldOffset(field) : getUnsafe().objectFieldOffset(field);
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public Object get(Object obj) throws Exception {
		return getUnsafe().getObject(obj, offest);
	}

	@Override
	public byte getByte(Object obj) throws Exception {
		return getUnsafe().getByte(obj, offest);
	}

	@Override
	public short getShort(Object obj) throws Exception {
		return getUnsafe().getShort(obj, offest);
	}

	@Override
	public int getInt(Object obj) throws Exception {
		return getUnsafe().getInt(obj, offest);
	}

	@Override
	public long getLong(Object obj) throws Exception {
		return getUnsafe().getLong(obj, offest);
	}

	@Override
	public float getFloat(Object obj) throws Exception {
		return getUnsafe().getFloat(obj, offest);
	}

	@Override
	public double getDouble(Object obj) throws Exception {
		return getUnsafe().getDouble(obj, offest);
	}

	@Override
	public boolean getBoolean(Object obj) throws Exception {
		return getUnsafe().getBoolean(obj, offest);
	}

	@Override
	public char getChar(Object obj) throws Exception {
		return getUnsafe().getChar(obj, offest);
	}

	@Override
	public void set(Object obj, Object value) throws Exception {
		getUnsafe().putObject(obj, offest, value);
	}

	@Override
	public void set(Object obj, byte value) throws Exception {
		getUnsafe().putByte(obj, offest, value);
	}

	@Override
	public void set(Object obj, short value) throws Exception {
		getUnsafe().putShort(obj, offest, value);
	}

	@Override
	public void set(Object obj, int value) throws Exception {
		getUnsafe().putInt(obj, offest, value);
	}

	@Override
	public void set(Object obj, long value) throws Exception {
		getUnsafe().putLong(obj, offest, value);
	}

	@Override
	public void set(Object obj, float value) throws Exception {
		getUnsafe().putFloat(obj, offest, value);
	}

	@Override
	public void set(Object obj, double value) throws Exception {
		getUnsafe().putDouble(obj, offest, value);
	}

	@Override
	public void set(Object obj, boolean value) throws Exception {
		getUnsafe().putBoolean(obj, offest, value);
	}

	@Override
	public void set(Object obj, char value) throws Exception {
		getUnsafe().putChar(obj, offest, value);
	}
}
