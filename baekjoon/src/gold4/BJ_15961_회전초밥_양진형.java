package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ 15961. 회전초밥
 * 
 * @author 양진형
 * 23. 4. 5
 */
public class BJ_15961_회전초밥_양진형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹을 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		int max = 1, now = 1; // 최대값, 현재값
		int[] picked = new int[d+1]; // 현재 선택한 초밥 개수 리스트(인덱스 : 번호)
		int[] belt = new int[N]; // 회전 초밥 벨트
		for(int i = 0; i < N; i++) belt[i] = Integer.parseInt(br.readLine()); // 벨트 초기화
		for(int i = 0; i < k; i++) { // 선택한 초밥 접시 초기화
			if(picked[belt[i]] == 0 && belt[i] != c) now++;
			picked[belt[i]]++;
		}
		
		// 슬라이딩 윈도우
		for(int i = k; i < N+k; i++) {
			int j = i - k; // j : 뺄 초밥
			int p = i % N; // p : 더할 초밥
			picked[belt[j]]--; // 초밥 빼기
			if(picked[belt[j]] == 0 && belt[j] != c) { // 초밥 빼고난 후 그 초밥이 선택한 초밥에 없다면
				max = max > now ? max : now; // max 갱신
				now--; // 현재 가진 초밥 종류 빼기
			}
			
			if(picked[belt[p]] == 0 && belt[p] != c) { // 초밥 더하기 전 그 초밥이 선택한 초밥에 없다면
				now++; // 현재 가진 초밥 종류 더하기
			}
			picked[belt[p]]++; // 초밥 더하기
		}
		
		System.out.println(max>now?max:now); // 출력
	}
}
