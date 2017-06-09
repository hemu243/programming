package com.hemu.program;

/**
 * Created by hchoudhary on 6/9/17.
 * <p>
 * Program is being stated here  - https://www.hackerrank.com/challenges/chief-hopper
 *  Sample input to the program
 *  5
 *  3 4 3 2 4
 * */
import java.util.Scanner;

public class ChiefHopper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int i = 0;
        int[] height = new int[number];
        while (i < number) {
            height[i++] = sc.nextInt();
        }
        System.out.println(getMinBotEnergy(height));
    }

    /**
     * Get Min Bot Energy
     * @param height - array of heights
     * @return
     */
    public static int getMinBotEnergy(int[] height) {
        // Lets assume that at last building minimum energy could be zero
        int en = 0;
        // Iterate from last building to first building and find out min energy
        for (int i = height.length - 1; i >= 0; i--) {
            en = Math.max((en + height[i]) / 2, (en + height[i] + 1) / 2);
        }
        return en;
    }
}
