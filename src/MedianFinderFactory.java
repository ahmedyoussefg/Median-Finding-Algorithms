public class MedianFinderFactory {
    public MedianFinder getMedianFinder(String algorithm_name, int [] array) {
        switch (algorithm_name) {
            case "Naive":
                return new NaiveMedianFinder(array);
            case "MedianOfMedians":
                return new DeterministicLinearTimeMedianFinder(array);
            case "Randomized":
                return new RandomizedMedianFinder(array);
        }
        return new RandomizedMedianFinder(array);
    }
}
