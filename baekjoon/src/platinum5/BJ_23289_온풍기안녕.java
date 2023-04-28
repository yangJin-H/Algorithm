package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_23289_온풍기안녕 {
	
	static int R, C, K;
	static int[][] temp;
	static ArrayList<int[]> heater, target;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		temp = new int[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				temp[i][j] = 0;
			}
		}
		
		heater = new ArrayList<>();
		target = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				int k = Integer.parseInt(st.nextToken());
				if(k == 0) continue;
				else if(k == 5) target.add(new int[] {i, j});
				else heater.add(new int[] {i, j, k});
			}
		}
		
		
	}
}
