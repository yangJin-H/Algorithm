package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19237_어른상어 {
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Shark extends Point {
		int num, dir;
		int[][] priority;
		
		public Shark(int num, int x, int y) {
			super(x, y);
			this.num = num;
			this.priority = new int[5][4];
		}
	}
	
	static class Stink {
		int num, left;

		public Stink(int num) {
			this.num = num;
			this.left = k;
		}

		public void reduce() {
			if(--this.left == 0) this.num = 0;
		}
	}
	
	static int N, M, k, num;
	static int[][] map;
	static LinkedList<Shark> sharks;
	static Stink[][] stinkMap;
	
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		sharks = new LinkedList<Shark>();
		stinkMap = new Stink[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] > 0) {
					sharks.add(new Shark(num, r, c));
					stinkMap[r][c] = new Stink(num);
				} else {
					stinkMap[r][c] = new Stink(0);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(Shark s : sharks) s.dir = Integer.parseInt(st.nextToken());
		
		for(Shark s : sharks) {
			int[][] priority = s.priority;
			for(int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 4; k++) {
					priority[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int time = 0;
		Queue<Integer> delete = new ArrayDeque<Integer>();
		while(sharks.size() > 1 && ++time < 1000) {
			for(Shark s : sharks) {
				int x = s.x;
				int y = s.y;
				
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[s.priority[s.dir][i]];
					int ny = y + dy[s.priority[s.dir][i]];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N || stinkMap[nx][ny].num > 0) continue;
					map[x][y] = 0;
					if(map[nx][ny] > s.num) {
						delete.add(s.num);
						break;
					} else if(map[nx][ny] < s.num && map[nx][ny] != 0) {
						delete.add(map[nx][ny]);
					}
					
					s.dir = s.priority[s.dir][i];
					s.x = nx;
					s.y = ny;
					map[nx][ny] = s.num;
					
					break;
				}
			}
		}
	}
}
