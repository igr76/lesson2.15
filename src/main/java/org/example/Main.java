package org.example;

import java.util.Random;
import java.util.function.Consumer;

public class Main {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        System.out.println("Hello world!");
        double timeForBubbleSort = measureTime(10, Main::sortBubble);
        System.out.printf("Среднее время сортировки пузырьком %.2f%n", timeForBubbleSort);
        double timeForSelectionSort = measureTime(10, Main::sortSelection);
        System.out.printf("Среднее время сортировки выбором %.2f%n", timeForSelectionSort);
        double timeForInsertionSort = measureTime(10, Main::sortInsertion);
        System.out.printf("Среднее время сортировки вставками %.2f%n", timeForInsertionSort);

    }

    private static double measureTime(int iteration, Consumer<int[]> sort) {
        long times =0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(1000000);
            long start = System.currentTimeMillis();
            sort.accept(array);
            times = times + (System.currentTimeMillis() - start);
        }
        return  times / (double) iteration;
    }

    private static int[] generateArray(int size) {
        int[] array = new  int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt();
        }
        return array;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int i1 = 0; i1 < arr.length-1-i; i1++) {
                if (arr[i] > arr[i + 1]) {
                    swapElements(arr,i,i+1);
                }
            }
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minElementIndex = i;
            for (int j1 = 0; j1 < arr.length; j1++) {
                if (arr[i] < arr[minElementIndex]) {
                    minElementIndex = i;
                }
            }
            swapElements(arr,i,minElementIndex);
        }
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j= i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j]= temp;
        }
    }
}