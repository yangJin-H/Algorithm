package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_23291_어항정리 {
	
	static int N, diff, height, next, colstart;
	static int[][] fishbowl;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		fishbowl = new int[N][N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			fishbowl[0][i] = Integer.parseInt(st.nextToken());
			if(min > fishbowl[0][i]) min = fishbowl[0][i];
			else if(max < fishbowl[0][i]) max = fishbowl[0][i];
		}
		int time = 0;
		diff = max - min;
		while(diff > K) {
			time++;
			System.out.println("Time: "+time);
			arrange();
			System.out.println(Arrays.toString(fishbowl[0]));
			System.out.println();
		}
		
		System.out.println(time);
	}

	private static void arrange() {
		add();
		stacking();
		adjust();
		System.out.println(Arrays.toString(fishbowl[0]));
		stacking2();
		adjust();
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			if(min > fishbowl[0][i]) min = fishbowl[0][i];
			else if(max < fishbowl[0][i]) max = fishbowl[0][i];
		}
		diff = max - min;
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
		int len = N-2;
		height = 2;
		fishbowl[1][1] = fishbowl[0][0];
		fishbowl[0][0] = 0;
		
		next = 2;
		boolean getHigh = false;
		ArrayDeque<Integer> stacked = new ArrayDeque<>();
		stacked.add(1);
		while(len >= height) {
			colstart = next;
			int K = 0;
			while(!stacked.isEmpty()) {
				K++;
				int idx = stacked.pop();
				for(int i = 0; i < height; i++) {
					fishbowl[K][next+i] = fishbowl[i][idx];
					fishbowl[i][idx] = 0;
				}
			}
			for(int i = next; i < next+height; i++) stacked.push(i);
			next += height;
			len -= height;
			if(getHigh) height++;
			getHigh = !getHigh;
		}
	}
	
	private static void adjust() {
		boolean[][] visited = new boolean[N][N];
		int[][] adjvalue = new int[N][N];
		for(int r = 0; r < height; r++) {
			for(int c = colstart; c < N; c++) {
				if(visited[r][c] || fishbowl[r][c] == 0) continue;
				visited[r][c] = true;
				
				for(int i = 0; i < 4; i++) {
					int nr = r + dx[i];
					int nc = c + dy[i];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || fishbowl[nr][nc] == 0) continue;
					int value = Math.abs(fishbowl[r][c] - fishbowl[nr][nc]) / 5;
					if(value == 0) continue;
					if(fishbowl[r][c] > fishbowl[nr][nc]) {
						adjvalue[r][c] -= value;
						adjvalue[nr][nc] += value;
					} else {
						adjvalue[r][c] += value;
						adjvalue[nr][nc] -= value;
					}
				}
			}
		}
		
		for(int r = 0; r < height; r++) {
			for(int c = colstart; c < N; c++) {
				fishbowl[r][c] += adjvalue[r][c];
			}
		}
		
		int k = 0;
		for(int c = colstart; c < next; c++) {
			for(int r = 0; r < height; r++) {
				fishbowl[0][k++] = fishbowl[r][c];
				fishbowl[r][c] = 0;
			}
		}
	}
	
	private static void stacking2() {
		next = N;
		height = 4;
		colstart = N - N / 4;
		for(int i = 0; i < N/4; i++) {
			fishbowl[1][colstart+i] = fishbowl[0][N/4-1-i];
			fishbowl[0][N/4-1-i] = 0;
			fishbowl[2][colstart+i] = fishbowl[0][N/4+i];
			fishbowl[0][N/4+i] = 0;
			fishbowl[3][colstart+i] = fishbowl[0][colstart-1-i];
			fishbowl[0][colstart-1-i] = 0;
		}
	}
}
