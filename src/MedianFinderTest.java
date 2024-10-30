import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MedianFinderTest {
    private MedianFinder medianFinder;

    @Test
    public void testEvenSmallUnsortedArray() {
        int[] arr = {5, 12, 255, 23, 1243, 5, 56, 2}; // 2 5 5 12 23 56 255 1243
        testMedian(arr, 12);
    }

    @Test
    public void testOddSmallUnsortedArray() {
        // 2 5 5 7 12 13 19 23 255 997 1243
        int[] arr = {5, 12, 255, 23, 1243, 5, 56, 2, 7, 13, 19, 997};
        testMedian(arr, 13);
    }

    @Test
    public void testEvenSmallSortedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        testMedian(arr, 5);
    }

    @Test
    public void testOddSmallSortedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        testMedian(arr, 7);
    }

    @Test
    public void testEvenLargeSortedArray() {
        int size = 1_000_000;
        int[] arr = getArrayOfSize(size);
        testMedian(arr, arr[size/2 - 1]);
    }

    @Test
    public void testOddLargeSortedArray() {
        int size = 1_000_001;
        int[] arr = getArrayOfSize(size);
        testMedian(arr, arr[size/2]);
    }

    @Test
    public void testSingleElementArray() {
        int[] arr = {42};
        testMedian(arr, 42);
    }

    @Test
    public void testAllEqualElementsArray() {
        int[] arr = {5, 5, 5, 5, 5, 5};
        testMedian(arr, 5);
    }

    @Test
    public void testSmallWithEqualElementsArray() {
        int[] arr = {3, 1, 2, 2, 3, 3}; // 1 2 2 3 3 3
        medianFinder = new RandomizedMedianFinder(arr);
        testMedian(arr, 2);
    }

    @Test
    public void testLargeUnsortedArray() {
        int size = 1_000_000;
        int[] arr = getArrayOfSize(size);
        int median = arr[size / 2 - 1];
        shuffleArray(arr);
        testMedian(arr, median);
    }

    @Test
    public void testNegativeElementsArray() {
        // -255 -23 -12 -5 -1
        int[] arr = {-5, -12, -255, -23, -1};
        testMedian(arr, -12);
    }

    @Test
    public void testArrayWithDuplicates() {
        // 4 4 4 4 6 6 6 6
        int[] arr = {4, 4, 4, 4, 6, 6, 6, 6};
        testMedian(arr, 4);
    }

    @Test
    public void testArrayWithZerosAndNegatives() {
        // -3 -2 -1 0 0 1 2 3
        int[] arr = {-1, 1, -3, 0, 2, -2, 0, 3};
        testMedian(arr, 0);
    }

    @Test
    public void testLargeArrayWithAllSameElements() {
        int[] arr = new int[10000];
        Arrays.fill(arr, 42);
        testMedian(arr, 42);
    }

    @Test
    public void testTwoElementArray() {
        int[] arr = {100, 200};
        testMedian(arr, 100);
    }

    @Test
    public void testThreeElementArray() {
        int[] arr = {300, 100, 200};
        testMedian(arr, 200);
    }

    @Test
    public void testArrayWithMaxIntValues() {
        int[] arr = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        testMedian(arr, 0);
    }

    private void testMedian(int [] arr, int expected) {
        medianFinder = new RandomizedMedianFinder(Arrays.copyOf(arr, arr.length));
        assertEquals(expected, medianFinder.getMedian());
        medianFinder = new NaiveMedianFinder(Arrays.copyOf(arr, arr.length));
        assertEquals(expected, medianFinder.getMedian());
        medianFinder = new DeterministicLinearTimeMedianFinder(Arrays.copyOf(arr, arr.length));
        assertEquals(expected, medianFinder.getMedian());
    }

    private void shuffleArray(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int val : arr) {
            list.add(val);
        }
        Collections.shuffle(list);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
    }
    private int [] getArrayOfSize(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }

}