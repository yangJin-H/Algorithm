package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_4354_문자열제곱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String str = br.readLine();
			if(str.equals(".")) break;
			int len = str.length();
			int[] pi = new int[len];
			for(int i=1, j=0; i < len; i++) {
				while(j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j-1];
				
				if(str.charAt(i) == str.charAt(j)) pi[i] = ++j;
			}
			
			int k = len - pi[len-1];
			if(len % k == 0) sb.append(len/k);
			else sb.append(1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
