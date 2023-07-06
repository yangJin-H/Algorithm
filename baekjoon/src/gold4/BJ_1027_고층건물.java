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
		
		int[] count = new int[N];
		for(int i = 0; i < N-1; i++) {
			double angle = Integer.MIN_VALUE;
			for(int j = i+1; j < N; j++) {
				double n_angle = ((double)heights[j] - heights[i]) / (j - i);
				if(n_angle > angle) {
					angle = n_angle;
					count[i]++;
					count[j]++;
				}
			}
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			if(ans < count[i]) ans = count[i];
		}
		System.out.println(ans);
	}
}
