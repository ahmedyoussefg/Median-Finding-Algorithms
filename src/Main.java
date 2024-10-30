import static java.lang.System.currentTimeMillis;
import static java.lang.System.exit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // We will analyze the runtime duration and performance of each algorithm
        MedianFinderFactory factory = new MedianFinderFactory();
        Random random = new Random();
        String [] algorithms = {"Naive", "MedianOfMedians", "Randomized"};
        int [] sizes = {1, 10, 50, 100, 500, 1000, 2500, 5000, 7500, 10000, 50000, 75000, 100_000, 500_000, 750_000,
                1_000_000, 2_500_000, 5_000_000, 7_500_000, 10_000_000};
        int numberOfSamples = 100;
        Set<Integer> localMedianResults = new HashSet<>();
        long [] localDuration = new long[algorithms.length];

        for (int size : sizes) {

            System.out.println("Testing Random Arrays with Size " + size + " with " + numberOfSamples + " samples.");
            for (int i = 0; i < numberOfSamples; i++) {
                int [] array = getRandomArrayOfSize(size, random);
                for (int algorithmIndex = 0;algorithmIndex < algorithms.length; algorithmIndex++) {
                    MedianFinder finder = factory.getMedianFinder(algorithms[algorithmIndex],
                                                                    Arrays.copyOf(array, array.length));
                    long startTime=System.currentTimeMillis();
                    localMedianResults.add(finder.getMedian());
                    localDuration[algorithmIndex] += (System.currentTimeMillis() - startTime);
                }
                if (localMedianResults.size() != 1) {
                    System.out.println("ERROR! Medians don't match!");
                    System.out.println(Arrays.toString(array));
                    exit(-1);
                }
                localMedianResults.clear();
            }
            for (int algorithmIndex = 0; algorithmIndex < algorithms.length; algorithmIndex++) {
                System.out.println("Tested algorithm \""+algorithms[algorithmIndex]+"\" took an average of "
                        + localDuration[algorithmIndex]/(double)numberOfSamples + " ms. over "
                        + numberOfSamples + " samples with size " + size + ".");
                localDuration[algorithmIndex] = 0;
            }
            System.out.println();
        }
    }
    protected static int [] getRandomArrayOfSize(int size, Random random) {
        int [] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] =  random.nextInt();
        }
        return array;
    }
}