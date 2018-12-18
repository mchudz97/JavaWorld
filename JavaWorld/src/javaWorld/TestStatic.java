package javaWorld;

public class TestStatic {

	public int a= 1;
	static int b=3;
	TestStatic(int i){
		a=a+i;
		b=a+b;
	}
	TestStatic(){
		b=a+3;
		a=b+a;
	}
	void wyswietl(){
		System.out.println("a=" +a + " b=" + b + " "
		+ "twojstarynajebany");
	}
	static{
		b=b+2;
	}


public static void main(String[] args) {
	new TestStatic(2).wyswietl();
	new TestStatic(1).wyswietl();
	new TestStatic().wyswietl();
}

}
