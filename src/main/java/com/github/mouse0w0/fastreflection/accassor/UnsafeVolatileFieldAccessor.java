package com.github.mouse0w0.fastreflection.accassor;

import static com.github.mouse0w0.fastreflection.util.UnsafeUtils.getUnsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.github.mouse0w0.fastreflection.FieldAccessor;

public class UnsafeVolatileFieldAccessor implements FieldAccessor {

	private final Field field;
	private final long offest;

	public UnsafeVolatileFieldAccessor(Field field) {
		this.field = field;
		this.offest = Modifier.isStatic(field.getModifiers()) ? getUnsafe().staticFieldOffset(field) : getUnsafe().objectFieldOffset(field);
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public Object get(Object obj) throws Exception {
		return getUnsafe().getObjectVolatile(obj, offest);
	}

	@Override
	public byte getByte(Object obj) throws Exception {
		return getUnsafe().getByteVolatile(obj, offest);
	}

	@Override
	public short getShort(Object obj) throws Exception {
		return getUnsafe().getShortVolatile(obj, offest);
	}

	@Override
	public int getInt(Object obj) throws Exception {
		return getUnsafe().getIntVolatile(obj, offest);
	}

	@Override
	public long getLong(Object obj) throws Exception {
		return getUnsafe().getLongVolatile(obj, offest);
	}

	@Override
	public float getFloat(Object obj) throws Exception {
		return getUnsafe().getFloatVolatile(obj, offest);
	}

	@Override
	public double getDouble(Object obj) throws Exception {
		return getUnsafe().getDoubleVolatile(obj, offest);
	}

	@Override
	public boolean getBoolean(Object obj) throws Exception {
		return getUnsafe().getBooleanVolatile(obj, offest);
	}

	@Override
	public char getChar(Object obj) throws Exception {
		return getUnsafe().getCharVolatile(obj, offest);
	}

	@Override
	public void set(Object obj, Object value) throws Exception {
		getUnsafe().putObjectVolatile(obj, offest, value);
	}

	@Override
	public void set(Object obj, byte value) throws Exception {
		getUnsafe().putByteVolatile(obj, offest, value);
	}

	@Override
	public void set(Object obj, short value) throws Exception {
		getUnsafe().putShortVolatile(obj, offest, value);
	}

	@Override
	public void set(Object obj, int value) throws Exception {
		getUnsafe().putIntVolatile(obj, offest, value);
	}

	@Override
	public void set(Object obj, long value) throws Exception {
		getUnsafe().putLongVolatile(obj, offest, value);
	}

	@Override
	public void set(Object obj, float value) throws Exception {
		getUnsafe().putFloatVolatile(obj, offest, value);
	}

	@Override
	public void set(Object obj, double value) throws Exception {
		getUnsafe().putDoubleVolatile(obj, offest, value);
	}

	@Override
	public void set(Object obj, boolean value) throws Exception {
		getUnsafe().putBooleanVolatile(obj, offest, value);
	}

	@Override
	public void set(Object obj, char value) throws Exception {
		getUnsafe().putCharVolatile(obj, offest, value);
	}
}
