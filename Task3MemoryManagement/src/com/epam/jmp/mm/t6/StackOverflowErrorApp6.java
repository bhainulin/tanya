package com.epam.jmp.mm.t6;

public class StackOverflowErrorApp6 {
	
	public static void main(String[] args) {
		A obj = new A();
		System.out.println(obj.toString());
	}

}

class A {
	private int aValue;
	private B bInstance = null;
	
	public A() {
		aValue = 0;
		bInstance = new B();
	}
	
	@Override
	public String toString() {
		return "";
	}
}

class B {
	private int bValue;
	private A aInstance = null;
	
	public B() {
		bValue = 10;
		aInstance = new A();
	}
	
	@Override
	public String toString() {
		return "";
	}
}
