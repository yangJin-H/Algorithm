package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1865_웜홀 {
	
	static class Edge {
		int e, t;

		public Edge(int e, int t) {
			this.e = e;
			this.t = t;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<Edge>> edgelist = new ArrayList<ArrayList<Edge>>();
			for(int i = 0; i <= N; i++) edgelist.add(new ArrayList<Edge>());
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());

				edgelist.get(s).add(new Edge(e, t));
				edgelist.get(e).add(new Edge(s, t));
			}
			
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());

				edgelist.get(s).add(new Edge(e, -t));
			}
			
			int[] distance = new int[N+1];
//			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[1] = 0;
			for(int i = 0; i < N-1; i++) {
				for(int s = 1; s <= N; s++) {
					for(Edge e: edgelist.get(s)) {
						if(distance[s] + e.t < distance[e.e]) {
							distance[e.e] = distance[s] + e.t; 
						}
					}
				}
			}

			int[] cycle = distance.clone();
			for(int s = 1; s <= N; s++) {
				for(Edge e: edgelist.get(s)) {
					if(cycle[s] + e.t < cycle[e.e]) {
						cycle[e.e]= cycle[s] + e.t; 
					}
				}
			}
			
			String ans = "NO";
			for(int i = 1; i <= N; i++) {
				if(cycle[i] != distance[i]) {
					ans = "YES";
					break;
				}
			}
			
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
