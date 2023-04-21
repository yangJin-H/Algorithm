package SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW 4014. 활주로
 * 
 * @author 양진형
 * 23. 4. 5.
 */
public class SW_4014_활주로건설_양진형 {
	
	static int N, L, left, before; // 맵 크기, 경사로 길이, 남은 경사로 길이, 이전 지형의 높이
	static int[][] map; // 지도
	static boolean[] slope; // 활주로 놓은 여부
	static boolean asc, desc; // 올라가는 경사로 놓는 중인지/내려가는 경사로 놓는 중인지
	static int total; // 놓을 수 있는 총 활주로
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][N]; // 입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			total = 0; // 총 갯수
			cmove(); // 세로로 활주로를 놓는 경우
			rmove(); // 가로로 활주로를 놓는 경우
			
			sb.append("#").append(tc).append(" ").append(total).append("\n"); // 출력
		}
		System.out.println(sb);
	}
	
	public static void cmove() {
		for(int c = 0; c < N; c++) {
			init(); // 초기화
			int before = map[0][c]; // 이전 지형 높이
			for(int r = 1; r < N; r++) {
				if(Math.abs(before - map[r][c]) > 1) break; // 높이 차이 2 이상인 경우 놓을 수 없음
				
				if(before == map[r][c]) { // 같은 경우
					if(asc || desc) { // 경사로를 놓는 중이라면
						int res = put(r); // 경사로 놓기
						if(res == -1) break; // 이미 경사로가 있는 경우 놓을 수 없음
						else r += res; // 올라가는 경사로를 다 놓은 경우 => 한칸 앞으로
					}
				}
				else { // 1차이 나는 경우
					if(left != L) break; // 이미 경사로를 놓고 있는 중이라면 놓을 수 없음
					if(before > map[r][c]) desc = true; // 이전 높이가 높다면 내려가는 경사로
					else { // 아니라면 올라가는 경사로
						asc = true;
						r -= L; // 경사로를 놓을 위치로 돌아가기
						if(r < 0) break; // 맵 밖이라면 패스
					}
					// 경사로 놓기
					int res = put(r);
					if(res == -1) break;
					else r += res;
				}
				
				before = map[r][c]; // 이전 높이 갱신
				if(r == N - 1 && left == L) total++; // 맵 끝까지 왔고 경사로를 놓는 중이 아니라면 활주로 수 +1
			}
		}
	}
	
	public static void rmove() {
		for(int r = 0; r < N; r++) {
			init();
			int before = map[r][0];
			for(int c = 1; c < N; c++) {
				if(Math.abs(before - map[r][c]) > 1) break;
				
				if(before == map[r][c]) {
					if(asc || desc) {
						int res = put(c);
						if(res == -1) break;
						else c += res;
					}
				}
				else {
					if(left != L) break;
					if(before > map[r][c]) desc = true;
					else {
						asc = true;
						c -= L;
						if(c < 0) break;
					}
					int res = put(c);
					if(res == -1) break;
					else c += res;
				}
				
				before = map[r][c];
				if(c == N - 1 && left == L) total++;
			}
		}
	}
	
	static public void init() {
		slope = new boolean[N]; // 활주로 놓은 여부
		asc = false; // 올라가는 중인지
		desc = false; // 내려가는 중인지
		left = L; // 남은 활주로 길이
	}
	
	static public int put(int x) {
		if(slope[x]) return -1; // 이미 경사로가 있는 경우

		slope[x] = true; // 경사로 놓기
		if(--left == 0) { // 남은 경사로 길이 -1 => 0이라면 다시 초기화
			left = L;
			desc = false;
			if(asc) {
				asc = false;
				return 1;
			}
		}
		return 0;
	}
}
