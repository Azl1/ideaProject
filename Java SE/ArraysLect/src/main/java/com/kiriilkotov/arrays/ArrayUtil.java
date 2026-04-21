package com.kiriilkotov.arrays;

/**
 * @author Kotov Kirill
 */
public class ArrayUtil {
    /**
     * Sum numbers of array
     *
     * @param mass array of integer numbers
     * @return sum of array
     */
    public static int sum(int[] mass) {
        int res = 0;
        for (int val : mass) {
            res += val;
        }
        return res;
    }

    /**
     * Get random number in [a, b]
     *
     * @param a start range
     * @param b end range
     * @return random integer number
     */
    private static int getRandomNumber(int a, int b) {
        return a + (int) (Math.random() * (b - a + 1));
    }

    /**
     * Get array of random numbers
     *
     * @param n Length of array
     * @return array of random integer numbers
     */
    public static int[] getRandomArray(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = getRandomNumber(1, 1000);
        }
        return res;
    }

    /**
     * Get all occurrences of key in array
     *
     * @param arr integer array
     * @param key integer number
     * @return array of all occurrences
     */
    public static int[] search(int[] arr, int key) {
        int count = 0;
        for (int val : arr) {
            if (val == key) {
                count++;
            }
        }
        int[] res = new int[count];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                res[j++] = i;
            }
        }
        return res;
    }

    /**
     * Get sum and average of array
     *
     * @param mass arrays of integer numbers
     * @return array of double sum and average
     */
    public static double[] sumAvg(int[] mass) {
        int sum = sum(mass);
        return new double[]{sum, (double)sum / mass.length};
    }
}
