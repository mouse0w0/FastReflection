package com.github.mouse0w0.fastreflection.accassor;

import java.lang.reflect.Field;

import com.github.mouse0w0.fastreflection.FieldAccessor;

public class ReflectFieldAccessor implements FieldAccessor {

	private final Field field;

	public ReflectFieldAccessor(Field field){
		field.setAccessible(true);
		this.field = field;
	}

	@Override
	public Field getField() {
		return field;
	}

	@Override
	public Object get(Object obj) throws Exception {
		return field.get(obj);
	}

	@Override
	public byte getByte(Object obj) throws Exception {
		return field.getByte(obj);
	}

	@Override
	public short getShort(Object obj) throws Exception {
		return field.getShort(obj);
	}

	@Override
	public int getInt(Object obj) throws Exception {
		return field.getInt(obj);
	}

	@Override
	public long getLong(Object obj) throws Exception {
		return field.getLong(obj);
	}

	@Override
	public float getFloat(Object obj) throws Exception {
		return field.getFloat(obj);
	}

	@Override
	public double getDouble(Object obj) throws Exception {
		return field.getDouble(obj);
	}

	@Override
	public boolean getBoolean(Object obj) throws Exception {
		return field.getBoolean(obj);
	}

	@Override
	public char getChar(Object obj) throws Exception {
		return field.getChar(obj);
	}

	@Override
	public void set(Object obj, Object value) throws Exception {
		field.set(obj, value);
	}

	@Override
	public void set(Object obj, byte value) throws Exception {
		field.setByte(obj, value);
	}

	@Override
	public void set(Object obj, short value) throws Exception {
		field.setShort(obj, value);
	}

	@Override
	public void set(Object obj, int value) throws Exception {
		field.setInt(obj, value);
	}

	@Override
	public void set(Object obj, long value) throws Exception {
		field.setLong(obj, value);
	}

	@Override
	public void set(Object obj, float value) throws Exception {
		field.setFloat(obj, value);
	}

	@Override
	public void set(Object obj, double value) throws Exception {
		field.setDouble(obj, value);
	}

	@Override
	public void set(Object obj, boolean value) throws Exception {
		field.setBoolean(obj, value);
	}

	@Override
	public void set(Object obj, char value) throws Exception {
		field.setChar(obj, value);
	}
}
