package gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 17472. 다리 만들기2
 * 
 * @author 양진형
 * 23. 4. 4.
 */
public class BJ_17472_다리만들기2_양진형 {
	
	static int N, M, index; // 행길이, 열길이, 섬 인덱스(번호 붙이기, 개수 세기용)
	static int[][] map; // 지도
	static Queue<int[]> coast; // 해안가 땅 저장용
	static PriorityQueue<int[]> edgequeue; // 간선 저장용
	static int[] parent; // disjoint set
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // id
			}
		}
		
		coast = new ArrayDeque<>(); // coast : x, y, id, direction
		index = 1; // 번호 입력 받기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 1) continue;
				naming(i ,j, ++index); // 한 섬 같은 번호로 칠하기
			}
		}
		
		// edgequeue : from, to, weight // 가중치 기준 오름차순
		edgequeue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] == o2[2]) {
					if(o1[1] == o1[1]) return o1[0] - o2[0];
					else return o1[1] - o2[1];
				}
				return o1[2] - o2[2];
			}
		});
		makebridge(); // 간선 구하기
		
		parent = new int[index+1]; // 서로소 집합 초기화
		for(int i = 2; i <= index; i++) parent[i] = i;
		
		int distance = 0, linked = 0; // 총 다리 길이 합, 연결한 섬 수
		while(!edgequeue.isEmpty() && linked != index - 2) { // 간선을 다 쓰거나 섬이 다 연결되면 탈출
			int[] edge = edgequeue.poll(); // 간선 꺼내오기
			if(find(edge[0]) == find(edge[1])) continue; // 한 집합이면 패스
			union(edge[0], edge[1]); // 유니온
			linked++; // 연결된 섬 +1
			distance += edge[2]; // 다리 길이 +1
		}
		
		if(linked != index - 2) distance = -1; // 다 연결되지 않았다면 -1
		System.out.println(distance); // 출력
	}

	// 섬에 번호 붙이기 + 해안가 가져오기
	private static void naming(int x, int y, int count) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y});
		map[x][y] = count;
		for(int j = 0; j < 4; j++) {
			int nx = x + dx[j];
			int ny = y + dy[j];
			if(isOut(nx, ny)) continue;
			if(map[nx][ny] == 0) coast.offer(new int[] {x, y, count, j});
		}
		
		// BFS, 섬에 번호 붙이기
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p[0] + dx[i];
				int ny = p[1] + dy[i];
				
				if(isOut(nx, ny) || map[nx][ny] == count) continue;
				if(map[nx][ny] == 0) { // 만약 주변이 바다라면 그 위치에서 출발할 int 배열 큐에 넣기
					coast.offer(new int[] {p[0], p[1], count, i});
					continue;
				}

				map[nx][ny] = count;
				queue.offer(new int[] {nx, ny});
			}
		}
	}

	// 생성되는 다리(간선)을 Priority Queue에 넣기
	private static void makebridge() {
		while(!coast.isEmpty()) {
			int[] p = coast.poll();
			int x = p[0];
			int y = p[1];
			int k = -1; // k : 다리 길이
			while(true) {
				// 1칸씩 전진
				int nx = x + dx[p[3]];
				int ny = y + dy[p[3]];
				k++;
				
				// 맵 밖이거나 도착한 섬이 출발 섬과 같다면 탈출
				if(isOut(nx, ny) || map[nx][ny] == p[2]) break;
				if(map[nx][ny] == 0) { // 바다인 경우 한칸 전진
					x = nx;
					y = ny;
					continue;
				}
				// k : 다리길이가 2 이상인 경우에만 등록
				if(k > 1) edgequeue.add(new int[] {p[2], map[nx][ny], k});
				break;
			}
		}
	}
	
	private static boolean isOut(int x, int y) { // 맵 밖인지?
		return x < 0 || x >= N || y < 0 || y >= M;
	}
	
	private static int find(int x) { // 유니온-파인드의 파인드 담당
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) { // 유니온-파인드의 유니온 담당
		int px = find(x);
		int py = find(y);
		if(px < py) parent[py] = px;
		else parent[px] = py;
	}
}
