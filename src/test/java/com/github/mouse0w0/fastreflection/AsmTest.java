package com.github.mouse0w0.fastreflection;

import static org.junit.Assert.*;

import org.junit.Test;

public class AsmTest {

	@Test
	public void test() throws Exception {
		ConstructorAccessor<ExampleClass> constructorAccessor = FastReflection
				.create(ExampleClass.class.getConstructor(String.class, int.class, int[].class, String[].class));
		ExampleClass obj = constructorAccessor.newInstance("Init", 1, new int[]{1}, new String[] {"Test"});
		
		FieldAccessor fieldAccessor = FastReflection.create(ExampleClass.class.getField("s"));
		assertEquals((String)fieldAccessor.get(obj), "Init");
		
		fieldAccessor = FastReflection.create(ExampleClass.class.getField("i"));
		assertEquals((int)fieldAccessor.get(obj), 1);
		
		fieldAccessor = FastReflection.create(ExampleClass.class.getField("is"));
		fieldAccessor.get(obj);
		fieldAccessor = FastReflection.create(ExampleClass.class.getField("ss"));
		fieldAccessor.get(obj);
		
		MethodAccessor methodAccessor = FastReflection.create(ExampleClass.class.getMethod("test", String.class, int.class, int[].class, String[].class));
		assertEquals((String)methodAccessor.invoke(obj, "Test", 1, new int[]{1}, new String[] {"Test"}), "Test1");
	}
	
	public static class ExampleClass {
		
		public String s;
		public int i;
		public int[] is;
		public String[] ss;
		
		public ExampleClass(String s, int i, int[] is, String[] ss) {
			this.s = s;
			this.i = i;
			this.is = is;
			this.ss = ss;
		}
		
		public String test(String s, int i, int[] is, String[] ss) {
			return s + i;
		}
	}

}
