package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_19236_청소년상어 {
	
	static class Fish {
		int num, x, y, dir;
		boolean visited;

		public Fish(int num, int x, int y, int dir) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.visited = false;
		}
	}
	
	static int max = 0;
	static Fish shark;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] fmap = new int[4][4];
		Fish[] fishes = new Fish[17];
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				fishes[n] = new Fish(n, i, j, d-1);
				fmap[i][j] = n;
			}
		}

		int sx = 0, sy = 0;
		int sum = fmap[sx][sy];
		fishes[fmap[sx][sy]].visited = true;
		int sd = fishes[fmap[sx][sy]].dir;
		
		dfs(sx, sy, sd, sum, fishes.clone(), fmap);
		
		System.out.println(max);
	}

	private static void dfs(int sx, int sy, int sd, int sum, Fish[] fishes, int[][] ofmap) {
		int[][] fmap = new int[4][4];
		for(int i = 0; i < 4; i++) {
			fmap[i] = ofmap[i].clone();
		}
		
		for(int i = 1; i <= 16; i++) {
			int x = fishes[i].x;
			int y = fishes[i].y;
			if(fishes[fmap[x][y]].visited) continue;
			int d = fishes[i].dir;
			for(int j = 0; j < 8; j++) {
				int nd = (d+j)%8;
				int nx = x + dx[nd];
				int ny = y + dy[nd];
				
				if(isOut(nx, ny) || (nx == sx && ny == sy)) continue;
				
				int onum = fmap[nx][ny];
				fishes[i].dir = nd;
				fishes[i].x = nx;
				fishes[i].y = ny;
				fmap[nx][ny] = i;

				fishes[onum].x = x;
				fishes[onum].y = y;
				fmap[x][y] = onum;
				
				break;
			}
		}
		
		print(fmap);
		while(true) {
			sx = sx+dx[sd];
			sy = sy+dy[sd];
			
			if(isOut(sx, sy) || fishes[fmap[sx][sy]].visited) {
				max = max > sum ? max : sum;
				System.out.println(max);
				return;
			};
			
			fishes[fmap[sx][sy]].visited = true;
			dfs(sx, sy, fishes[fmap[sx][sy]].dir, sum+fmap[sx][sy], fishes.clone(), fmap);
			fishes[fmap[sx][sy]].visited = false;
		}
	}

	private static boolean isOut(int x, int y) {
		return x < 0 || x >= 4 || y < 0 || y >= 4;
	}
	
	public static void print(int[][] fmap) {
		for(int[] row : fmap) {
			for(int e: row) {
				System.out.print(e+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void dprint(int[][] fmap, Fish[] fishes) {
		for(int[] row : fmap) {
			for(int e: row) {
				System.out.print(fishes[e].dir+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void vprint(int[][] fmap, Fish[] fishes) {
		for(int[] row : fmap) {
			for(int e: row) {
				System.out.print(fishes[e].visited?"T ":"F ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
