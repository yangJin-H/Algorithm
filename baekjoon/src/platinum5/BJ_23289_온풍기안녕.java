package baekjoon.src.platinum5;

import sun.security.util.math.intpoly.IntegerPolynomial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_23289_온풍기안녕 {
	
	static int R, C, K;
	static int[][] temp;
	static ArrayList<int[]> heater, target;

	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		temp = new int[R][C];
		
		heater = new ArrayList<>();
		target = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				int k = Integer.parseInt(st.nextToken());
				if(k != 0) {
					if (k == 5) target.add(new int[]{i, j});
					else heater.add(new int[]{i, j, k});
				}
			}
		}

		int W = Integer.parseInt(br.readLine());
		boolean[][][] wallMap = new boolean[R][C][5];
		for(int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());
			if(t == 0) {
				wallMap[x][y][3] = true;
				if(x > 1) wallMap[x-1][y][4] = true;
			} else {
				wallMap[x][y][t] = true;
				if(y > 1) wallMap[x][y-1][2] = true;
			}
		}

		int[][] heatMap = new int[R][C];
		for(int[] h : heater) {
			int d = h[2];
			ArrayDeque<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] {h[0]+dx[d], h[1]+dy[d], 5});
			boolean[][] visited = new boolean[R][C];
			while(!queue.isEmpty()) {
				int[] e = queue.poll();
				heatMap[e[0]][e[1]] += e[2];
				if(e[2] == 1) continue;

				int nx = e[0] + dx[d];
				int ny = e[1] + dy[d];
				if(nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;
				if(!wallMap[e[0]][e[1]][d]) {
					queue.add(new int[] {nx, ny, e[2]-1});
					visited[nx][ny] = true;
				}
				int[] kDist;
				if(d < 3) kDist = new int[] {3, 4};
				else kDist = new int[] {1, 2};
				for (int k : kDist) {
					nx = e[0] + dx[k];
					ny = e[1] + dy[k];
					if(!wallMap[e[0]][e[1]][k] && ny >= 0 && ny < C) {
						int nnx = nx + dx[d];
						int nny = ny + dy[d];
						if(nnx < 0 || nnx >= R || nny < 0 || nny >= C || visited[nnx][nny]) continue;
						if(!wallMap[nx][ny][d]) {
							queue.add(new int[] {nnx, nny, e[2]-1});
							visited[nnx][nny] = true;
						}
					}
				}
			}
		}

		int choco = 0;
		while(true) {
			// 1. 바람이 나옴
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					temp[i][j] += heatMap[i][j];
				}
			}

			// 2. 온도가 조절됨
			int[][] adjMap = new int[R][C];
			for(int x = 0; x < R; x++) {
				for(int y = 0; y < C; y++) {
					for(int d : new int[] {1, 4}) {
						if(wallMap[x][y][d]) continue;
						int nx = x + dx[d];
						int ny = y + dy[d];
						if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
						if(temp[x][y] == temp[nx][ny]) continue;
						boolean flag = true;
						if(temp[x][y] < temp[nx][ny]) flag = false;
						int diff = Math.abs(temp[x][y] - temp[nx][ny]) / 4;
						if(flag) {
							adjMap[x][y] -= diff;
							adjMap[nx][ny] += diff;
						} else {
							adjMap[x][y] += diff;
							adjMap[nx][ny] -= diff;
						}
					}
				}
			}
			for(int x = 0; x < R; x++) {
				for(int y = 0; y < C; y++) {
					temp[x][y] += adjMap[x][y];
				}
			}

			// 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1 감소
			for(int c = 0; c < C; c++) {
				if(temp[0][c] > 0) temp[0][c]--;
				if(temp[R-1][c] > 0) temp[R-1][c]--;
			}

			for(int r = 1; r < R - 1; r++) {
				if(temp[r][0] > 0) temp[r][0]--;
				if(temp[r][C-1] > 0) temp[r][C-1]--;
			}

			// 4. 초콜렛 먹기
			if(++choco == 101) break;

			// 5. target 칸들의 온도 조사
			boolean flag = true;
			for(int[] t : target) {
				if(temp[t[0]][t[1]] < K) {
					flag = false;
					break;
				}
			}

			if(flag) break;
		}

		for(int[] row : heatMap) {
			for(int t : row) {
				System.out.print(t+" ");
			}
			System.out.println();
		}
		System.out.println(choco);
	}
}
