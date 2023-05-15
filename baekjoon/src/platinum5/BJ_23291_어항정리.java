package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_23291_어항정리 {
	
	static int N, K, diff, time;
	static int[][] fishbowl;
	static int[] heights;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		fishbowl = new int[N][N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			fishbowl[0][i] = Integer.parseInt(st.nextToken());
			if(min > fishbowl[0][i]) min = fishbowl[0][i];
			else if(max < fishbowl[0][i]) max = fishbowl[0][i];
		}
		heights = new int[N];
		Arrays.fill(heights, 1);
		diff = max - min;
		while(diff > K) {
			arrange();
		}
		
		System.out.println(time);
	}

	private static void arrange() {
		time++;
		add();
		stacking();
	}
	
	private static void add() {
		int min = Integer.MAX_VALUE;
		ArrayList<Integer> minidx = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			if(fishbowl[0][i] > min) continue;
			if(fishbowl[0][i] < min) {
				min = fishbowl[0][i];
				minidx.clear();
			}
			minidx.add(i);
		}
		for(int idx : minidx) fishbowl[0][idx]++;
	}
	
	private static void stacking() {
		
	}
}
