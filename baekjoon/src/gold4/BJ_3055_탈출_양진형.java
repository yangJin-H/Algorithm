package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 3055. 탈출
 * 
 * @author 양진형
 * 23. 4. 5
 */
public class BJ_3055_탈출_양진형 {
	
	static int R, C, ans; // 행길이, 열길이, 정답
	static char[][] map; // 맵
	static Queue<int[]> queue; // 물과 고슴도치 저장할 큐
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행,열 입력
		C = Integer.parseInt(st.nextToken());
		
		int[] start = null; // 고슴도치 시작 위치
		map = new char[R][C];
		queue = new ArrayDeque<>();
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				switch(str.charAt(j)) {
				case 'S': // 고슴도치인경우
					start = new int[] {i, j, 0, 0};
					map[i][j] = '.';
					break;
				case '*': // 물인 경우
					queue.add(new int[] {i, j, 1, 0});
				default: // 그외
					map[i][j] = str.charAt(j);
				}
			}
		}
		ans = 0; // 정답
		queue.add(start); // 고슴도치 위치를 가장 마지막에 넣기
		bfs(); // 탈출
		System.out.println(ans==0?"KAKTUS":ans); // 출력
	}

	private static void bfs() {
		boolean[][] visited = new boolean[R][C]; // 고슴도치 방문 배열
		
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p[0] + dx[i];
				int ny = p[1] + dy[i];
				boolean dochi = p[2] == 0; // 고슴도치인지?
				
				// 맵 밖이거나 물이거나 돌이면 패스
				if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == '*' || map[nx][ny] == 'X') continue;
				if(dochi) { // 고슴도치인 경우
					if(visited[nx][ny]) continue; // 방문했다면 패스
					if(map[nx][ny] == 'D') { // 도착지라면
						ans = p[3] + 1; // 정답 갱신 후 탈출
						return;
					}
					visited[nx][ny] = true; // 방문 표시
					queue.add(new int[] {nx, ny, 0, p[3]+1}); // 큐에 넣기
				}
				else { // 물인 경우
					if(map[nx][ny] == 'D') continue; // 도착지라면 패스
					map[nx][ny] = '*'; // 해당 위치 물로 만들기
					queue.add(new int[] {nx, ny, 1, 0}); // 큐에 넣기
				}
			}
		}
	}
}
