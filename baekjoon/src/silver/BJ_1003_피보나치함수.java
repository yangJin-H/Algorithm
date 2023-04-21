package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1003_피보나치함수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] memo = new int[41][2];
		memo[0] = new int[] {1, 0};
		memo[1] = new int[] {0, 1};
		
		for(int i = 2; i <= 40; i++) {
			memo[i][0] += memo[i-1][0] + memo[i-2][0]; 
			memo[i][1] += memo[i-1][1] + memo[i-2][1];
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			sb.append(memo[N][0]).append(" ").append(memo[N][1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
