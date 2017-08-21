
package com.hemu.program;
/**
 * Problem can be found here  - https://www.hackerrank.com/challenges/lilys-homework
 *
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinimumSwapsToSortListed {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int i = 0;
        int[] input = new int[number];
        while (i < number) {
            input[i++] = sc.nextInt();
        }
        System.out.println(getMinSwaps(input));
    }

    private static Map<Integer, Integer> getMap(int[] inputs) {
        Map<Integer, Integer> mValWithIndex = new HashMap<Integer, Integer>();
        for (int i = 0; i < inputs.length; i++) {
            mValWithIndex.put(inputs[i], i);
        }
        return mValWithIndex;
    }

    /**
     * Reverse sort of already sorted array
     **/
    private static void reverseSort(int[] inputs) {
        int i = 0, j = inputs.length - 1;
        int temp;
        while (i <= j && i < inputs.length && j > 0) {
            temp = inputs[i];
            inputs[i] = inputs[j];
            inputs[j] = temp;
            i++;
            j--;
        }
    }

    public static int getMinSwaps(int[] inputs) {
        if (inputs == null || inputs.length <= 1) {
            // no swaps required
            return 0;
        }

        // Copy array so we can sort it using utils
        int[] baseInputs = Arrays.copyOf(inputs, inputs.length);
        // in place sort
        Arrays.sort(inputs);
        // swaps with acending order sort
        int ascSwaps = getMinSwaps(Arrays.copyOf(baseInputs, baseInputs.length), inputs);
        //reverse a array
        reverseSort(inputs);
        // Swap with descending order sort
        int descSwaps = getMinSwaps(Arrays.copyOf(baseInputs, baseInputs.length), inputs);
        return Math.min(ascSwaps, descSwaps);
    }

    private static int getMinSwaps(int[] inputs, int[] sortedList) {
        int swaps = 0;
        int temp;
        Map<Integer, Integer> mValWithIndex = getMap(inputs);
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] != sortedList[i]) {
                // get item which is unsorted list
                swaps++;
                int indexValue = mValWithIndex.get(sortedList[i]);
                mValWithIndex.put(inputs[i], indexValue); // update index in the map
                temp = inputs[indexValue];
                inputs[indexValue] = inputs[i];
                inputs[i] = temp;
            }
        }
        return swaps;
    }
}
