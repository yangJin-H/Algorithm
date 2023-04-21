package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1027_고층건물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] heights = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) heights[i] = Integer.parseInt(st.nextToken());
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			int count = 0;
			for(int j = 0; j < N; j++) {
				if(i == j || heights[i] < heights[j]) continue;
				
			}
		}
		
	}
}
