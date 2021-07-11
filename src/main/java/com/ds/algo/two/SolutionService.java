package com.ds.algo.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolutionService {

	public static void main(String[] args) {
		SolutionService service = new SolutionService();
		int result = service.countVowelPermutation(144);
		System.out.println("Result: " + result);
	}

	private Map<Character, List<Character>> map = null;

	private Map<String, Long> dp = new HashMap<>();

	public int countVowelPermutation(int n) {
		
		map = new HashMap<>();
		map.put('a', Arrays.asList('e'));
		map.put('e', Arrays.asList('a', 'i'));
		map.put('i', Arrays.asList('a', 'e', 'o', 'u'));
		map.put('o', Arrays.asList('i', 'u'));
		map.put('u', Arrays.asList('a'));

		Set<Character> keys = map.keySet();
		return (int) (constructString(new ArrayList<Character>(keys), 0, n) % 1000000007);
	}

	public long constructString(List<Character> keys, int count, int n) {
		long sum = 0;
		if (count == n - 1) {
			return keys.size();
		}
		for (Character key : keys) {
			long temp = 0;
			if (dp.containsKey(key.toString() + "" + count)) {
				temp = dp.get(key.toString() + "" + count);
				temp = temp % 1000000007;
			} else {
				List<Character> nextKeys = map.get(key);
				temp = constructString(nextKeys, count + 1, n);
				temp = temp % 1000000007;
				dp.put(key.toString() + "" + count, temp);
			}
			sum = sum + temp;
			sum = sum % 1000000007;
		}
		return sum;
	}

}
