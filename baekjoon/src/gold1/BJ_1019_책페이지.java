package gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_1019_책페이지 {
	
	static long[] counts;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		counts = new long[16];
		counts[1] = 1;
		for(int i = 2; i <= 15; i++) {
			counts[i] = (long) Math.pow(10, i-1) + counts[i-1] * 10;
		}
			
		long s = Integer.parseInt(br.readLine());
		long[] arr = new long[10];
		arr = calc(s, (int)Math.log10(s), new long[10], Long.toString(s));
		
		for(long e : arr) {
			System.out.print(e+" ");
		}
		System.out.println();
	}

	private static long[] calc(long n, int digit, long arr[], String s) {
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i <= digit; i++)
			arr[0] -= Math.pow(10, i);
		makestack(n, stack);
		while(!stack.isEmpty()) {
			int k = stack.pop();
			for(int i = 0; i < k; i++) {
				arr[i] += Math.pow(10, digit);
				for(int j = 0; j <= 9; j++) {
					arr[j] += counts[digit];
				}
			}
			s = s.substring(1);
			if(s.length() >= 1) {
				arr[k] += Long.valueOf(s)+1;
			} else {
				arr[k]++;
			}
			digit--;
		}
		return arr;
	}

	private static void makestack(long n, Stack<Integer> stack) {
		while(n > 0) {
			stack.add((int) (n % 10));
			n /= 10;
		}
	}
}
