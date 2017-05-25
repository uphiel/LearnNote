package com.wangfei.Arithmetic;

public class ReflectTest {

	public static void main(String[] args) {
		String str = "new String";
		String className = str.getClass().getName();
		System.out.println(str.getClass());
		
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		
	}

}
