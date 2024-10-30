import static java.lang.System.exit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        // We will analyze the runtime duration and performance of each algorithm
        MedianFinderFactory factory = new MedianFinderFactory();
        Random random = new Random();
        // define algorithms names
        String [] algorithms = {"Naive", "MedianOfMedians", "Randomized"};
        // define sizes up to 1e7
        int [] sizes = {1, 10, 50, 100, 500, 1000, 2500, 5000, 7500, 10000, 50000, 75000, 100_000, 500_000, 750_000,
                1_000_000, 2_500_000, 5_000_000, 7_500_000, 10_000_000};
        // number of sample arrays for each size
        int numberOfSamples = 100;
        // we will store here the medians from all algorithms inside one sample run to check if they are the same or not
        Set<Integer> localMedianResults = new HashSet<>();
        // we will store the total amount of time taken by each algorithm in millis during the numberOfSamples iterations
        long [] localDuration = new long[algorithms.length];

        // define the file path of the csv file to user.dir (where the java runs from)
        String path = System.getProperty("user.dir") + "/results.csv";
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("ArraySize,AlgorithmName,Runtime");
            for (int size : sizes) {
                System.out.println("Testing Random Arrays with Size " + size + " with " + numberOfSamples + " samples.");
                for (int i = 0; i < numberOfSamples; i++) {
                    int[] array = getRandomArrayOfSize(size, random);
                    for (int algorithmIndex = 0; algorithmIndex < algorithms.length; algorithmIndex++) {
                        MedianFinder finder = factory.getMedianFinder(algorithms[algorithmIndex],
                                Arrays.copyOf(array, array.length));
                        long startTime = System.currentTimeMillis();
                        localMedianResults.add(finder.getMedian());
                        localDuration[algorithmIndex] += (System.currentTimeMillis() - startTime);
                    }

                    // ensure all outputs are the same
                    if (localMedianResults.size() != 1) {
                        System.out.println("ERROR! Medians don't match!");
                        System.out.println(Arrays.toString(array));
                        pw.close();
                        bw.close();
                        fw.close();
                        exit(-1);
                    }
                    localMedianResults.clear();
                }
                for (int algorithmIndex = 0; algorithmIndex < algorithms.length; algorithmIndex++) {
                    System.out.println("Tested algorithm \"" + algorithms[algorithmIndex] + "\" took an average of "
                            + localDuration[algorithmIndex] / (double) numberOfSamples + " ms. over "
                            + numberOfSamples + " samples with size " + size + ".");
                    pw.println(size + "," + algorithms[algorithmIndex] + "," +
                            localDuration[algorithmIndex] / (double) numberOfSamples);
                    localDuration[algorithmIndex] = 0;
                }
                System.out.println();
                pw.flush();
            }
            pw.close();
            bw.close();
            fw.close();
        } catch(IOException e){
            System.out.println("Couldn't open the file.");
            exit(-1);
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