package radix;
import java.util.*; 

public class Radix {

		Radix(){};
    	int getMax(String arr[]) 
    { 
        int mx = arr[0].length(); 
        for (int i = 1; i < arr.length; i++) 
            if (arr[i].length() > mx) 
                mx = arr[i].length(); 
        return mx; 
    } 
     int getCharValue(String s, int n){
    	if(s.length()<=n){
    		return 0;
    	}
    	else{
    	char test= Character.toLowerCase(s.charAt(n));
    	if (test >='0' && test<= '9'){
    		return Character.getNumericValue(test+1);
    	}
    	if (test >='a' && test <='z'){
    		return (int) test - 86;
    	}
    	else return 0;
    	}
    }
   
     void countSort(String arr[], int n) 
    { 	
    	int charRange=37;
        String output[] = new String[arr.length+1]; 
        int count[] = new int[charRange]; 
        Arrays.fill(count,0); 
        for (int j=0; j<arr.length; j++){
        	count[getCharValue(arr[j], n)]++;
        	//System.out.println(j+ " znak : " +getCharValue(arr[j], n)+ " wystepowanie: " +count[getCharValue(arr[j], n)] + " | slowo: " + arr[j]);
        }
        	count[0]--;
        for (int j=1;j<charRange;j++){
        	count[j]=count[j]+count[j-1];
        	
        	//System.out.println(j+ " : " +count[j]);
        }
        
        
        for (int k=arr.length-1; k>=0; k--){
        	//System.out.println(count[getCharValue(arr[k],n)]);
        	output[count[getCharValue(arr[k],n)]]=arr[k];
        	//System.out.println(output[count[getCharValue(arr[k],n)]] + " pozycja " + count[getCharValue(arr[k],n)] + " slowo "+ arr[k]);
        	count[getCharValue(arr[k],n)]--;
        	
        }
        
   
        for (int i = 0; i < arr.length; i++) 
            arr[i] = output[i]; 
    } 
     void radixSort(String arr[]) {
    	 int max=getMax(arr);
    	 for (int i=max-1; i>=0; i--){
    		 countSort(arr,i);
    	 }
     }
  

     
}
