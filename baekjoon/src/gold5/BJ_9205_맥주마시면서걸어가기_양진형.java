package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * BJ 9205. 맥주 마시면서 걸어가기
 * 
 * @author 양진형
 */
public class BJ_9205_맥주마시면서걸어가기_양진형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테케
		long start = System.currentTimeMillis();
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 편의점 개수
			
			ArrayList<int[]> points = new ArrayList<int[]>(N+2); // 편의점들의 좌표
			ArrayList<HashSet<Integer>> reachable = new ArrayList<HashSet<Integer>>(N+2); // 각 편의점마다 갈 수 있는 좌표
			for (int i = 0; i < N + 2; i++) {
				int[] tmp_p = new int[2];
				HashSet<Integer> tmp_r = new HashSet<Integer>();
				tmp_p = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for(int j = 0; j < i; j++) { // 이전에 넣은 좌표들 검사
					if(getDist(points.get(j), tmp_p) <= 1000) { // 현재 편의점과의 거리가 1000 이하라면
						tmp_r.add(j); // 현재 -> j번 위치로 갈 수 있음
						reachable.get(j).add(i); // j -> 현재 위치(i)로 갈 수 있음
					}
				}
				points.add(tmp_p); // 새로만든 점과 reachable 추가
				reachable.add(tmp_r);
			}

			// BFS
			boolean end_flag = false;
			boolean[] visited = new boolean[N+2];
			Queue<Integer> q = new LinkedList<Integer>();
			int v = N + 1; // 가장 끝 좌표부터
			visited[v] = true; // 방문 표시
			q.add(v); // 큐에 넣기
			while(q.size() != 0) { // 큐가 빌때까지
				v = q.poll();
				
				Iterator<Integer> iter = reachable.get(v).iterator(); // 갈수 있는 좌표들 iter로 가져오기
				while(iter.hasNext()) { // 갈수있는 좌표가 있으면
					int w = iter.next(); // 좌표 번호 꺼내오기
					if(w == 0) { // 그 좌표가 출발지라면
						end_flag = true; // 끝(갈수 있음)
						break;
					}
					if(!visited[w]) { // 방문하지 않았다면
						visited[w] = true; // 방문표시
						q.add(w); // 큐에 넣기
					}
				}
				if(end_flag) {
					break;
				}
			}
			
			if(end_flag) { // 결과 출력
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
			
		}
	}
	
	public static int getDist(int[] A, int[] B) { // 맨하튼 거리 계산
		return Math.abs(A[0] - B[0]) + Math.abs(A[1] - B[1]);
	}
}
