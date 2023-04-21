package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1009_분산처리 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int[] pattern = new int[10];
			pattern[0] = a % 10;
			int k = (a * a) % 10;
			int num = 1;
			while(k != pattern[0] && k != 0) {
				pattern[num++] = k;
				k = (k * a) % 10;
			}
			int idx = b%num-1;
			int ans = pattern[idx<0?num-1:idx];
			sb.append(ans==0?10:ans).append("\n");
		}
		System.out.println(sb);
	}
}
