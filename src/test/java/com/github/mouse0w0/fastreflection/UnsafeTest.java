package com.github.mouse0w0.fastreflection;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

public class UnsafeTest {
	
	private static final ExampleClass targetField = new ExampleClass("Unchange");

	@Test
	public void test() throws Exception {
		Field target = ExampleClass.class.getDeclaredField("s");
		FieldAccessor accessor = FastReflection.create(target,true);
		
		assertEquals((String)accessor.get(targetField),"Unchange");
		accessor.set(targetField, "Changed");
		assertEquals((String)accessor.get(targetField),"Changed");
		
		target = UnsafeTest.class.getDeclaredField("targetField");
		accessor = FastReflection.create(target,true);
		
		assertEquals(((ExampleClass)accessor.get(UnsafeTest.class)).s,"Changed");
		accessor.set(UnsafeTest.class, new ExampleClass("ObjectChanged"));
		assertEquals(((ExampleClass)accessor.get(UnsafeTest.class)).s,"ObjectChanged");
	}

	public static class ExampleClass {
		
		public final String s;
		
		public ExampleClass(String s) {
			this.s = s;
		}
	}
}
