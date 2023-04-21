package D6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA D6 1263. 사람 네트워크 2
 * 
 * @author 양진형
 */
public class D6_1263_사람네트워크2_양진형 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테케
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine()); 
			int N = Integer.parseInt(st.nextToken()); // 사람 수
			
			int[][] dist = new int[N][N]; // 인접 행렬
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) { 
					dist[i][j] = Integer.parseInt(st.nextToken());
					if(i == j) continue; // 자기 자신은 건너뛰기 (0)
					if(dist[i][j] == 0) dist[i][j] = 1000; // 연결되지 않은 다른 사람은 거리 1000으로 초기화
				}
			}
			
			// Floyd-Warshall
			for(int k = 0; k < N; k++) { // k : 중간 노드
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
			
			// CC의 최소값 구하기
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) { // 각 사람마다
				int sum = 0;
				for(int j = 0; j < N; j++) {
					sum += dist[i][j]; // 다른 사람들과의 거리 누적해서 더해주기
				}
				min = Math.min(min, sum); // 갱신
			}
			
			sb.append("#").append(tc).append(" ").append(min).append("\n"); // 출력
		}
		System.out.println(sb);
	}
}
