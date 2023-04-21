package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1005_ACMCraft {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			int[] buildTime = new int[N+1];
			int[] takenTime = new int[N+1];
			for(int i = 1; i <= N; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
				takenTime[i] = buildTime[i];
			}
			
			ArrayList<Integer>[] forward = new ArrayList[N+1];
			ArrayList<Integer>[] reverse = new ArrayList[N+1];
			for(int i = 1; i <= N; i++) {
				forward[i] = new ArrayList<Integer>();
				reverse[i] = new ArrayList<Integer>();
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				forward[from].add(to);
				reverse[to].add(from);
			}
			
			int W = Integer.parseInt(br.readLine());
			
			boolean[] necessary = new boolean[N+1];
			necessary[W] = true;
			Queue<Integer> necess = new ArrayDeque<>();
			necess.add(W);
			while(!necess.isEmpty()) {
				int buildid = necess.poll();
				for(int i : reverse[buildid]) {
					if(!necessary[i]) {
						necessary[i] = true;
						necess.add(i);
					}
				}
			}
			Queue<int[]> buildorder = new ArrayDeque<>();
			for(int i = 1; i <= N; i++) {
				if(!necessary[i]) continue;
				if(reverse[i].size() == 0) buildorder.add(new int[] {i, 0, buildTime[i]});
			}
			int level = 0;
			while(!buildorder.isEmpty()) {
				int[] build = buildorder.poll();
				
				if(level != build[1]) {
					level = build[1];
				}

				if(build[0] == W) break;
				
				for(int i : forward[build[0]]) {
					reverse[i].remove(Integer.valueOf(build[0]));
					takenTime[i] = Math.max(takenTime[i], buildTime[i] + build[2]);
					if(necessary[i] && reverse[i].size() == 0) {
						buildorder.offer(new int[] {i, build[1]+1, takenTime[i]});
					}
				}
			}
			System.out.println(Arrays.toString(takenTime));
			sb.append(takenTime[W]).append("\n");
		}
		System.out.println(sb);
	}
}
