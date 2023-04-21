package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2902_KMP는왜KMP일까 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] arr = br.readLine().toCharArray();
		StringBuilder sb = new StringBuilder();
		for(char c : arr)
			if('A' <= c && c <= 'Z') sb.append(c);
		System.out.println(sb);
	}
}
