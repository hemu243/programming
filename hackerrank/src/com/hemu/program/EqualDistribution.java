package com.hemu.program;

/**
 * Problem - https://www.hackerrank.com/challenges/equal
 * <p>
 * <p>
 * Christy is interning at HackerRank. One day she has to distribute some chocolates to her colleagues. She is biased towards her friends and may have distributed the chocolates unequally. One of the program managers gets to know this and orders Christy to make sure everyone gets equal number of chocolates.
 * <p>
 * But to make things difficult for the intern, she is ordered to equalize the number of chocolates for every colleague in the following manner,
 * <p>
 * For every operation, she can choose one of her colleagues and can do one of the three things.
 * <p>
 * She can give one chocolate to every colleague other than chosen one.
 * She can give two chocolates to every colleague other than chosen one.
 * She can give five chocolates to every colleague other than chosen one.
 * Calculate minimum number of such operations needed to ensure that every colleague has the same number of chocolates.
 * <p>
 * <p>
 * Input Format
 * <p>
 * First line contains an integer  denoting the number of testcases.  testcases follow.
 * Each testcase has  lines. First line of each testcase contains an integer  denoting the number of
 * colleagues. Second line contains N space separated integers denoting the current number of
 * chocolates each colleague has.
 * <p>
 * Constraints
 * <p>
 * <p>
 * <p>
 * Number of initial chocolates each colleague has <
 * <p>
 * Output Format
 * <p>
 * lines, each containing the minimum number of operations needed to make sure all colleagues have the same number of chocolates.
 * <p>
 * Sample Input
 * <p>
 * 1
 * 4
 * 2 2 3 7
 * Sample Output
 * <p>
 * 2
 */


import java.util.Scanner;

public class EqualDistribution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner(System.in);
        int testcase = input.nextInt();
        EqualDistribution equalDistribution = new EqualDistribution();
        for (int i = 0; i < testcase; i++) {
            int member = input.nextInt();
            int[] values = new int[member];
            for (int j = 0; j < member; j++) {
                values[j] = input.nextInt();
            }
            System.out.println(equalDistribution.getMinimumSteps(values));
        }
    }

    private int getMin(int[] input) {
        int min = input[0];
        for (int i = 1; i < input.length; i++) {
            if (min > input[i]) {
                min = input[i];
            }
        }
        return min;
    }

    private int getMinStep(int value) {
        int step = value / 5;
        value = value % 5;
        step += value / 2;
        value = value % 2;
        return step + value;
    }

    private int getStepsBasedUponMin(int[] input, int min) {
        int steps = 0;
        int diff;
        for (int i = 0; i < input.length; i++) {
            diff = input[i] - min;
            steps += this.getMinStep(diff);
        }
        return steps;
    }

    public int getMinimumSteps(int[] input) {
        if (input == null || input.length == 0) {
            return 0; // No operation
        }
        int min = this.getMin(input);
        int minSteps = this.getStepsBasedUponMin(input, min);
        minSteps = Math.min(minSteps, this.getStepsBasedUponMin(input, min - 1));
        minSteps = Math.min(minSteps, this.getStepsBasedUponMin(input, min - 2));
        minSteps = Math.min(minSteps, this.getStepsBasedUponMin(input, min - 3));
        minSteps = Math.min(minSteps, this.getStepsBasedUponMin(input, min - 4));
        minSteps = Math.min(minSteps, this.getStepsBasedUponMin(input, min - 5));
        return minSteps;
    }
}
