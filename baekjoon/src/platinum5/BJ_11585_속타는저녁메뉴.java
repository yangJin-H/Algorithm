package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_11585_속타는저녁메뉴 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String find = br.readLine().replaceAll(" ", "");
		String str = br.readLine().replaceAll(" ", "");
		str += str;
		
		int[] pi = new int[find.length()];
		for(int i=1, j=0; i < find.length(); i++) {
			while(j > 0 && find.charAt(i) != find.charAt(j)) j = pi[j-1];
			
			if(find.charAt(i) == find.charAt(j)) pi[i] = ++j;
		}
		
		int count = 0;
		int len = str.length();
		for(int i=0, j=0; i < len-1; i++) {
			while(j > 0 && str.charAt(i) != find.charAt(j)) j = pi[j-1];
			
			if(str.charAt(i) == find.charAt(j)) {
				if(j == find.length()-1) {
					count++;
					j = pi[j];
				}
				else j++;
			}
		}
		int total = N;
		int gcdd = gcd(total, count);
		total /= gcdd;
		count /= gcdd;
		System.out.println(count+"/"+total);
	}
	
	public static int gcd(int p, int q) {
		if(q == 0) return p;
		return gcd(q, p % q);
	}
}
