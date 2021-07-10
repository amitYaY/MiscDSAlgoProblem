package com.ds.algo.one;

import java.util.HashSet;
import java.util.Set;

public class SolutionService {

	private static int jug1Capacity = 6;
	private static int jug2Capacity = 9;
	private static int targetCapacity = 1;

	public static void main(String[] args) {
		SolutionService service = new SolutionService();
		boolean result = service.canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity);
		System.out.println("Possible: " + result);

	}

	public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
		boolean result = false;

		if (jug1Capacity == targetCapacity || jug2Capacity == targetCapacity
				|| jug1Capacity + jug2Capacity == targetCapacity
				|| Math.abs(jug1Capacity - jug2Capacity) == targetCapacity || targetCapacity == 0) {
			result = true;
		}

		if ((jug1Capacity == jug2Capacity && !result)
				|| (targetCapacity % 2 != 0 && jug1Capacity % 2 == 0 && jug2Capacity % 2 == 0)) {
			return false;
		}

		int maxCapacity = jug1Capacity >= jug2Capacity ? jug1Capacity : jug2Capacity;
		int minCapacity = jug1Capacity < jug2Capacity ? jug1Capacity : jug2Capacity;

		if (targetCapacity % minCapacity == 0 && jug1Capacity + jug2Capacity >= minCapacity) {
			result = true;
		}

//		int mod = maxCapacity % minCapacity;
//		int modDiff = minCapacity - mod;
//
//		if (modDiff + maxCapacity == targetCapacity || modDiff + minCapacity == targetCapacity) {
//			result = true;
//		}
//
//		int modMaxDiff = maxCapacity - modDiff;
//
//		if (modMaxDiff == targetCapacity || modMaxDiff + minCapacity == targetCapacity) {
//			result = true;
//		}

		int tempMin = minCapacity;
		
		Set<Integer> set = new HashSet<>();

		while (!result) {
			int diff = 0;
			if (tempMin < 0) {
				diff = Math.abs(tempMin);
			} else {
				diff = maxCapacity - tempMin;
			}

			if(set.contains(diff)) {
				return false;
			}
			
			set.add(diff);
			
			if (minCapacity + diff == targetCapacity) {
				result = true;
				break;
			}

			if (diff == targetCapacity) {
				result = true;
				break;
			}

			if (diff <= minCapacity && maxCapacity + diff == targetCapacity) {
				result = true;
				break;
			}
			tempMin = minCapacity - diff;
		}

		return result;
	}

}
