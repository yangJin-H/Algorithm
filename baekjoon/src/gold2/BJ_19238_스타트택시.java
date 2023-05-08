package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19238_스타트택시 {
	
	static int N, M, K, tx, ty;
	static int[][] map, dest;
	
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 0) map[i][j] = 0;
				else map[i][j] = -1;
			}
		}
		st = new StringTokenizer(br.readLine());
		tx = Integer.parseInt(st.nextToken()) - 1;
		ty = Integer.parseInt(st.nextToken()) - 1;

		dest = new int[M+1][2];
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int cx = Integer.parseInt(st.nextToken()) - 1;
			int cy = Integer.parseInt(st.nextToken()) - 1;
			int cdx = Integer.parseInt(st.nextToken()) - 1;
			int cdy = Integer.parseInt(st.nextToken()) - 1;
		
			map[cx][cy] = i;
			dest[i][0] = cdx;
			dest[i][1] = cdy;
		}
		
		for(int i = 0; i < M; i++) {
			if(K < 0) break;
			drive(find());
		}
		
		System.out.println(K);
	}

	private static void drive(int num) {
		if(num == -1 || K < 0) {
			K = -1;
			return;
		}
		map[tx][ty] = 0;
		if(tx == dest[num][0] && ty == dest[num][1]) {
			return;
		}
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> taxi = new ArrayDeque<>();
		taxi.offer(new int[] {tx, ty, 0});
		while(!taxi.isEmpty()) {
			int[] t = taxi.poll();
			int x = t[0];
			int y = t[1];
			int d = t[2];
			if(K < d) {
				K = -1;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1 || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				if(nx == dest[num][0] && ny == dest[num][1]) {
					tx = nx;
					ty = ny;
					K -= d+1;
					if(K < 0) return;
					K += (d+1)*2;
					return;
				}
				taxi.offer(new int[] {nx, ny, d+1});
			}
		}
		
		K = -1;
	}

	private static int find() {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<int[]> taxi = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] == o2[2]) {
					if(o1[0] == o2[0]) return o1[1] - o2[1];
					return o1[0] - o2[0];
				}
				return o1[2] - o2[2];
			}
		});
		taxi.offer(new int[] {tx, ty, 0});
		while(!taxi.isEmpty()) {
			int[] t = taxi.poll();
			int x = t[0];
			int y = t[1];
			int d = t[2];
			
			if(map[x][y] != 0 || K < d) {
				tx = x;
				ty = y;
				K -= d;
				return map[x][y];
			}
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1 || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				taxi.offer(new int[] {nx, ny, d+1});
			}
		}
		
		return -1;
	}
}
