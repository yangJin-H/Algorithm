package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println(Integer.MAX_VALUE/2 + Integer.MAX_VALUE/2);
	}
	
	public static int gcd(int x, int y) {
		if(y == 0) return x;
		return gcd(y, x % y);
	}
}
