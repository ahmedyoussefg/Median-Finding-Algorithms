import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MedianFinderTest {
    private MedianFinder medianFinder;

    @Test
    public void testEvenSmallUnsortedArray() {
        int[] arr = {5, 12, 255, 23, 1243, 5, 56, 2}; // 2 5 5 12 23 255 1243
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

    private void testMedian(int [] arr, int expected) {
        medianFinder = new RandomizedMedianFinder(arr);
        assertEquals(expected, medianFinder.getMedian());
        medianFinder = new NaiveMedianFinder(arr);
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