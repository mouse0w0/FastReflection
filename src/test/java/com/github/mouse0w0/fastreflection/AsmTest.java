package com.github.mouse0w0.fastreflection;

import static org.junit.Assert.*;

import org.junit.Test;

public class AsmTest {

	@Test
	public void test() throws Exception {
		ConstructorAccessor<ExampleClass> constructorAccessor = FastReflection
				.create(ExampleClass.class.getConstructor(String.class));
		ExampleClass obj = constructorAccessor.newInstance("Init");
		
		FieldAccessor fieldAccessor = FastReflection.create(ExampleClass.class.getField("s"));
		assertEquals((String)fieldAccessor.get(obj), "Init");
		
		MethodAccessor methodAccessor = FastReflection.create(ExampleClass.class.getMethod("test", String.class, int.class, int[].class));
		assertEquals((String)methodAccessor.invoke(obj, "Test", 1, new int[]{1}), "Test1");
	}
	
	public static class ExampleClass {
		
		public String s;
		
		public ExampleClass(String s) {
			this.s = s;
		}
		
		public String test(String s, int i, int[] is) {
			return s + i;
		}
	}

}
