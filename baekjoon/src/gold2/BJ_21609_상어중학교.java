package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_21609_상어중학교 {
	
	static int N, M, bx, by, score;
	static int[][] map;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자의 크기
		M = Integer.parseInt(st.nextToken()); // 색상의 개수
		map = new int[N][N]; // 전체 격자
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) { // 숫자 입력 받기
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 블록 그룹이 있는 동안
		while(find()) {
			autoplay(); // 오토 플레이
		}
		
		System.out.println(score); // 출력
	}

	// 블록 그룹 찾기
	private static boolean find() {
		boolean[][] visited = new boolean[N][N];
		bx = -1; // 기준 블록의 x좌표
		by = -1; // 기준 블록의 y좌표
		int maxr = 0; // 블록 그룹의 가장 큰 
		int maxi = 0; // 
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] <= 0 || visited[r][c]) continue;
				boolean[][] rainbowvisited = new boolean[N][N];
				Queue<int[]> queue = new ArrayDeque<>();
				queue.offer(new int[] {r, c});
				visited[r][c] = true;
				int bc = map[r][c];
				int chunk = 1;
				int rainbow = 0;
				while(!queue.isEmpty()) {
					int[] p = queue.poll();
					int x = p[0];
					int y = p[1];
					
					for(int i = 0; i < 4; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						
						if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] <= -1 || (map[nx][ny] > 0 && map[nx][ny] != bc)
								|| (map[nx][ny] == bc && visited[nx][ny]) || (map[nx][ny] == 0 && rainbowvisited[nx][ny])) continue;
						
						if(map[nx][ny] == 0) {
							rainbow++;
							rainbowvisited[nx][ny] = true;
						}
						else visited[nx][ny] = true;
						chunk++;
						queue.offer(new int[] {nx, ny});
					}
				}
				
				if(maxi <= chunk) {
					if(maxi == chunk && maxr > rainbow) continue;
					bx = r;
					by = c;
					maxi = chunk;
					maxr = rainbow;
				}
			}
		}
		
		if(maxi < 2) return false;
		score += maxi * maxi;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {bx, by});
		int bc = map[bx][by];
		map[bx][by] = -2;
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			int x = p[0];
			int y = p[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] <= -1 || (map[nx][ny] != bc && map[nx][ny] != 0)) continue;
				map[nx][ny] = -2;
				queue.offer(new int[] {nx, ny});
			}
		}
		
		return true;
	}

	private static void autoplay() {
		gravity();
		
		int[][] tmp = new int[N][N];
		for(int r = 0; r < N; r++) {
			tmp[r] = map[r].clone();
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				map[r][c] = tmp[c][N-1-r];
			}
		}
		
		gravity();
	}
	
	private static void gravity() {
		for(int c = 0; c < N; c++) {
			int r = -1;
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			while(r < N) {
				r++;
				if(r == N || map[r][c] == -1) {
					int k = r - 1;
					while(!stack.isEmpty()) {
						map[k--][c] = stack.pop();
					}
				}
				else if(map[r][c] == -2) continue;
				else {
					stack.push(map[r][c]);
					map[r][c] = -2;
				}
			}
		}
	}
}
