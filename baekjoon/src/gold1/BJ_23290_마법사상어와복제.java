package gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_23290_마법사상어와복제 {
	
	static class Board { 
		int[] fish, duplicate, moved;
		int stink, total;
		
		public Board() {
			this.fish = new int[8];
			this.duplicate = new int[8];
			this.moved = new int[8];
			this.stink = 0;
		}
		
		public void add(int dir) {
			fish[dir]++;
		}
		
		public void casting() {
			this.duplicate = this.fish.clone();
		}
		
		public void move() {
			int sum = 0;
			for(int i = 0; i < 8; i++) {
				fish[i] += moved[i];
				sum += fish[i];
				moved[i] = 0;
			}
			this.total = sum;
		}

		public void eaten() {
			this.fish = new int[8];
			stink = 3;
		}
		
		public void duplicate() {
			stink--;
			for(int i = 0; i < 8; i++) fish[i] += duplicate[i];
		}
		
	}
	
	static Board[][] board;
	static int sx, sy;
	static boolean[][] visited;
	static int[] max;
	
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] sdx = {-1, 0, 1, 0};
	static int[] sdy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		board = new Board[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				board[i][j] = new Board();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			board[x][y].add(d);
		}
		
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		
		for(int i = 0; i < S; i++) {
			duplication();
		}
		
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 8; k++) {
					sum += board[i][j].fish[k];
				}
			}
		}
		
		System.out.println(sum);
	}

	private static void duplication() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				board[i][j].casting();
				move(i, j);
			}
		}
		
		visited = new boolean[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				board[i][j].move();
			}
		}
		int[] route = new int[3];
		max = new int[4];
		max[3] = -1;
		dfs(sx, sy, 0, 0, route);
		
		for(int i = 0; i < 3; i++) {
			int d = max[i];
			int nsx = sx + sdx[d];
			int nsy = sy + sdy[d];
			board[nsx][nsy].eaten();
			sx = nsx;
			sy = nsy;
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				board[i][j].duplicate();
			}
		}
	}

	private static void move(int x, int y) {
		for(int d = 0; d < 8; d++) {
			for(int j = 0; j < 8; j++) {
				int nd = (8+d-j)%8;
				int nx = x + dx[nd];
				int ny = y + dy[nd];
				if(isOut(nx, ny) || board[nx][ny].stink > 0
						|| (sx == nx && sy == ny)) continue;

				board[nx][ny].moved[nd] += board[x][y].fish[d];
				board[x][y].fish[d] = 0;
				break;
			}
		}
	}

	private static void dfs(int x, int y, int depth, int sum, int[] route) {
		if(depth == 3) {
			if(visited[x][y]) return;
			visited[x][y] = true;
			if(sum > max[3]) {
				max[3] = sum;
				for(int i = 0; i < 3; i++) max[i] = route[i];
			}
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int nx = x + sdx[d];
			int ny = y + sdy[d];
		
			if(isOut(nx, ny)) continue;
			route[depth] = d;
			dfs(nx, ny, depth+1, sum+board[nx][ny].total, route);
		}
	}

	private static boolean isOut(int nx, int ny) {
		return nx < 0 || nx >= 4 || ny < 0 || ny >= 4;
	}
}
