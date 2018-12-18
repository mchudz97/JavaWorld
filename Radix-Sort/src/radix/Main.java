package radix;

public class Main {

	public static void main(String[] args) {
		String[] test = { "test", "roznych", "znakow" , "jakies", "dane0", "12332", "hello", "tekst", "a0000", "a0aa", "dane", "a"};
		Radix r= new Radix();
		r.radixSort(test);

		for(int i=0; i<test.length; i++){
			System.out.println(test[i]);
		}
	}

}
