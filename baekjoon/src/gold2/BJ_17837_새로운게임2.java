package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_17837_새로운게임2 {
	
	static int N, K, turn;
	static int[][][] map;
	static int[][] dir;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N][4];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		turn = 0;
		dir = new int[K+1][3];
		for(int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			if(put(i, x, y)) {
				System.out.println(turn);
				return;
			};
			dir[i] = new int[] {d, x, y};
		}
		
		while(turn++ <= 1000) {
			for(int i = 1; i <= K; i++) {
				if(move(i)) {
					System.out.println(turn);
					return;
				}
			}
			System.out.println("===turn: "+turn+"===");
			for(int i = 1; i <= K; i++) {
				System.out.println(Arrays.toString(dir[i]));
			}
			System.out.println();
		}
		
		System.out.println(-1);
	}
	
	public static boolean put(int id, int x, int y) {
		int number = 1;
		while(true) {
			if(map[x][y][number] == 0) {
				map[x][y][number] = id;
				dir[id][1] = x;
				dir[id][2] = y;
				return false;
			}
			
			if(++number == 4) return true;
		}
	}
	
	public static boolean move(int id) {
		int x = dir[id][1];
		int y = dir[id][2];
		int d = dir[id][0];
		
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		if(isOut(nx, ny) || map[nx][ny][0] == 2) {
			if(d % 2 == 0) d = ++dir[id][0];
			else d = --dir[id][0];
			
			nx = x + dx[d];
			ny = y + dy[d];
			
			if(isOut(nx, ny) || map[nx][ny][0] == 2) {
				return false;
			}
		}
		
		for(int i = 1; i <= 3; i++) {
			if(map[x][y][i] != id) continue;
			for(int j = i; j <= 3; j++) {
				if(map[x][y][j] == 0) break;
				if(put(map[x][y][j], nx, ny)) return true;
				map[x][y][j] = 0;
			}
			break;
		}
		
		if(map[nx][ny][0] == 1 && map[nx][ny][2] != 0) {
			int idx = -1;
			Stack<Integer> stack = new Stack<>();
			for(int i = 1; i <= 3; i++) {
				if(map[nx][ny][i] != id) continue;
				idx = i;
				for(int j = i; j <= 3; j++) {
					if(map[nx][ny][j] == 0) break;
					stack.add(map[nx][ny][j]);
				}
				break;
			}
			
			while(!stack.isEmpty()) {
				map[nx][ny][idx++] = stack.pop();
			}
		}
		
		return false;
	}
	
	public static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
