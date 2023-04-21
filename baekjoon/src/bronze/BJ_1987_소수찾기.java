package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1987_소수찾기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		boolean[] isNotPrime = new boolean[1001];
		isNotPrime[1] = true;
		for(int i = 2; i <= 1000; i++) {
			if(isNotPrime[i]) continue;
			for(int k = i*2; k <= 1000; k += i) isNotPrime[k] = true;
		}
		
		int ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			if(!isNotPrime[Integer.parseInt(st.nextToken())]) ans++;
		}
		
		System.out.println(ans);
	}
}
