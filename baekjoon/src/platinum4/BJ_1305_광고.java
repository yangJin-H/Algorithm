package platinum4;

import java.util.Scanner;

public class BJ_1305_광고 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		String str = sc.next();
		int[] pi = new int[L];
		
		for(int i=1,j=0; i < L; i++) {
			while(j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j-1];
			
			if(str.charAt(i) == str.charAt(j)) pi[i] = ++j;
		}
		System.out.println(L - pi[L-1]);
		sc.close();
	}
}
