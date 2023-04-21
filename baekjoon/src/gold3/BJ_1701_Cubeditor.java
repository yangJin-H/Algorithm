package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1701_Cubeditor {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int len = str.length();
		int max = 0;
		for(int k = 0; k < len; k++) {
			if(len-k <= max) break;
			String other = str.substring(k);
			int[] pi = new int[other.length()];
			for(int i=1, j=0; i < other.length(); i++) {
				while(j > 0 && other.charAt(i) != other.charAt(j)) j = pi[j-1];
				
				if(other.charAt(i) == other.charAt(j)) {
					pi[i] = ++j;
					max = max > pi[i] ? max : pi[i];
				} 
			}
		}
		System.out.println(max);
	}
}
