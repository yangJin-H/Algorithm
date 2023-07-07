package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_16958_텔레포트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] cities = new int[N][3];
		int[] toTeleport = new int[N];
		ArrayList<Integer> special = new ArrayList<Integer>();
		for(int i = 0; i < N; i++) {
			toTeleport[i] = 20000;
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				cities[i][j] = Integer.parseInt(st.nextToken());
			}
			if(cities[i][0] == 1) {
				toTeleport[i] = 0;
				special.add(i);
			}
		}
		for(int k = 0; k < N; k++) {
			if(cities[k][0] == 1) continue;
			for(int i : special) {
				int dist = Math.abs(cities[i][1] - cities[k][1]) + Math.abs(cities[i][2] - cities[k][2]);
				toTeleport[k] = toTeleport[k] < dist ? toTeleport[k] : dist;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			int dist = Math.abs(cities[from][1] - cities[to][1]) + Math.abs(cities[from][2] - cities[to][2]);
			int t_dist = toTeleport[from] + toTeleport[to] + T;
			dist = dist < t_dist ? dist : t_dist;
			sb.append(dist).append("\n");
		}
		System.out.println(sb);
	}
}
