package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_19237_어른상어 {
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 상어 정보
	static class Shark extends Point implements Comparable<Shark> {
		int num, dir;
		int[][] priority;
		
		public Shark(int num, int x, int y) {
			super(x, y);
			this.num = num;
			this.priority = new int[5][4];
		}

		@Override
		public int compareTo(Shark o) {
			return this.num - o.num;
		}
	}
	
	// 냄새 정보
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
	
	static int N, M, k; // 격자 크기, 상어 수, 냄새 유효기간
	static int[][] map; // 상어 지도
	static LinkedList<Shark> sharks; // 상어 정보 배열
	static Stink[][] stinkMap; // 냄새 지도
	static PriorityQueue<Integer> delete; // 이동 후 쫓겨날 상어 큐
	
	static int[] dx = {0, -1, 1, 0, 0}; // 1: 위, 2: 아래, 3: 왼쪽, 4: 오른쪽
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
		
		// 맵 입력 받기
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] > 0) { // 상어는 상어 정보 추가, 냄새 추가
					sharks.add(new Shark(map[r][c], r, c));
					stinkMap[r][c] = new Stink(map[r][c]);
				} else {
					stinkMap[r][c] = new Stink(0);
				}
			}
		}
		Collections.sort(sharks); // 상어 번호 순 정렬
		// 상어 초기 방향 입력
		st = new StringTokenizer(br.readLine());
		for(Shark s : sharks) s.dir = Integer.parseInt(st.nextToken());
		
		// 상어 이동 우선 순위 입력
		for(Shark s : sharks) {
			int[][] priority = s.priority;
			for(int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 4; k++) {
					priority[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int time = 0; // 시간
		delete = new PriorityQueue<Integer>();
		while(sharks.size() > 1 && ++time <= 1000) {
			// 상어 움직이기
			for(Shark s : sharks) {
				int x = s.x;
				int y = s.y;
				int[] f = null; // 자기 냄새가 있는 칸 정보 (좌표, 방향)
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[s.priority[s.dir][i]];
					int ny = y + dy[s.priority[s.dir][i]];
					
					// 격자 밖이거나 다른 상어의 냄새가 있는 칸은 패스
					if(nx < 0 || nx >= N || ny < 0 || ny >= N || (stinkMap[nx][ny].num != 0 && stinkMap[nx][ny].num != s.num)) continue;
					if(stinkMap[nx][ny].num == s.num) { // 가장 처음 만나는 자기 냄새가 있는 칸 기억
						if(f == null) f = new int[] {nx, ny, i};
						continue;
					}
					map[x][y] = 0;
					move(nx, ny, s, i);
					break;
				}
				
				// 움직이지 않았고 자기 냄새가 있는 칸이 있다면 이동
				if(map[x][y] != 0 && f != null) {
					map[x][y] = 0;
					move(f[0], f[1], s, f[2]);
				}
			}
			
			int k = 0; // 검사할 상어 번호
			// 지울 상어 지우기
			while(!delete.isEmpty()) {
				int num = delete.poll();
				for(;k < sharks.size(); k++) {
					if(sharks.get(k).num == num) break;
				}
				sharks.remove(k--);
			}
			
			// 냄새 감소
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					stinkMap[i][j].reduce();
				}
			}
			
			// 냄새 남기기
			for(Shark s : sharks) {
				stinkMap[s.x][s.y] = new Stink(s.num);
			}
		}
		if(time == 1001) time = -1;
		System.out.println(time); // 출력
	}
	
	// 움직이기
	static private void move(int nx, int ny, Shark s, int i) {
		// 움직일 칸에 자기보다 다른 상어가 있다면 작은 번호를 가지는 상어 delete 큐에 넣기
		if(map[nx][ny] != 0 && map[nx][ny] < s.num) {
			delete.add(s.num);
			return;
		} else if(map[nx][ny] > s.num) {
			delete.add(map[nx][ny]);
		}
		
		// 움직이기
		s.dir = s.priority[s.dir][i];
		s.x = nx;
		s.y = ny;
		map[nx][ny] = s.num;
		
		return;
	}
}
