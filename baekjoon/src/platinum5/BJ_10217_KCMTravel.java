package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_10217_KCMTravel {
	
	static class Node implements Comparable<Node> {
		int index, distance, cost;
		
		public Node(int index, int distance, int cost) {
			this.index = index;
			this.distance = distance;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}

		@Override
		public String toString() {
			return "Node [index=" + index + ", distance=" + distance + ", cost=" + cost + "]";
		}
	}
	
	static int N, M, K;
	static ArrayList<ArrayList<Node>> graph;
	static int[][] distance;
	
	static final int INF = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 공항수
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			graph = new ArrayList<ArrayList<Node>>();
			for(int i = 0; i <= N; i++) {
				graph.add(new ArrayList<Node>());
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				graph.get(u).add(new Node(v, d, c));
			}
			
			distance = new int[N+1][M+1];
			for(int i = 1; i <= N; i++) {
				for(int j = 0; j <= M; j++) {
					distance[i][j] = INF;
				}
			}
			
			dijkstra(1);
			
			int min = INF;
			for(int i = 0; i <= M; i++) {
				min = min < distance[N][i] ? min : distance[N][i];
			}
			sb.append(min==INF?"Poor KCM":min).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		queue.offer(new Node(start, 0, 0));
		distance[start][0] = 0;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int now = node.index;
			int cost = node.cost;
			int dist = node.distance;
			
			if(distance[now][cost] < dist) continue;
			
			for(int i = 0; i < graph.get(now).size(); i++) {
				int ndist = distance[now][cost] + graph.get(now).get(i).distance;
				int nidx = graph.get(now).get(i).index;
				if(ndist < distance[nidx][cost] && cost + graph.get(now).get(i).cost < M) {
					int ncost = cost + graph.get(now).get(i).cost;
					if(distance[nidx][ncost] < ndist) continue;
					distance[nidx][ncost] = ndist;
					queue.offer(new Node(nidx, ndist, ncost));
					while(++ncost <= M) {
						if(distance[nidx][ncost] < ndist) continue;
						distance[nidx][ncost] = ndist;
					}
				}
			}
		}
	}
}
