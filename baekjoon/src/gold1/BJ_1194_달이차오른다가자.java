package gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 1194. 달이 차오른다, 가자.
 * 
 * @author 양진형
 * 2023. 4. 3.
 */
public class BJ_1194_달이차오른다가자 {
	
	static int N, M, startx, starty, answer;
	static char[][] map;
	static boolean[][][] visited;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {
					startx = i;
					starty = j;
					map[i][j] = '.';
				}
			}
		}
		visited = new boolean[N][M][64];
		answer = -1;
		escape();
		System.out.println(answer);
	}

	private static void escape() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {startx, starty, 0, 0});
		visited[startx][starty][0] = true;
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p[0] + dx[i];
				int ny = p[1] + dy[i];
				int nk = p[3];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == '#' || visited[nx][ny][nk]) continue;
				
				if('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
					nk = nk | 1<<(map[nx][ny] - 'a');
				}
				else if('A' <= map[nx][ny] && map[nx][ny] <= 'F' && (nk & 1<<(map[nx][ny] - 'A')) == 0) continue;
				else if(map[nx][ny] == '1') {
					answer = p[2] + 1;
					return;
				}
				
				visited[nx][ny][nk] = true;
				queue.offer(new int[] {nx, ny, p[2] + 1, nk});
			}
		}
	}
}