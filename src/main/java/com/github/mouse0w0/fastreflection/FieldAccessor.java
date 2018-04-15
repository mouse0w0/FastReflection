package com.github.mouse0w0.fastreflection;

import java.lang.reflect.Field;

public interface FieldAccessor {
	
	Field getField();

	Object get(Object obj) throws Exception;
	byte getByte(Object obj) throws Exception;
	short getShort(Object obj) throws Exception;
	int getInt(Object obj) throws Exception;
	long getLong(Object obj) throws Exception;
	float getFloat(Object obj) throws Exception;
	double getDouble(Object obj) throws Exception;
	boolean getBoolean(Object obj) throws Exception;
	char getChar(Object obj) throws Exception;
	
	void set(Object obj, Object value) throws Exception;
	void set(Object obj, byte value) throws Exception;
	void set(Object obj, short value) throws Exception;
	void set(Object obj, int value) throws Exception;
	void set(Object obj, long value) throws Exception;
	void set(Object obj, float value) throws Exception;
	void set(Object obj, double value) throws Exception;
	void set(Object obj, boolean value) throws Exception;
	void set(Object obj, char value) throws Exception;
	
}
