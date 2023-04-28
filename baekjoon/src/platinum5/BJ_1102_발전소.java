package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1102_발전소 {
	
	static int N, P, status;
	static int[][] generator;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		generator = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				generator[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		String s = br.readLine();
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'Y') status |= (1<<i);
		}
		P = Integer.parseInt(br.readLine());
		
		if(status == 0) {
			System.out.println(-1);
			return;
		}
		
		
	}
}
