import java.util.Arrays;

public class NaiveMedianFinder extends MedianFinder {
    public NaiveMedianFinder(int[] dataPoints) {
        super(dataPoints);
    }

    @Override
    public int select(int l, int r, int k) {
        // naively sort the elements then return sorted[k]
        int [] sorted = this.dataPoints;
        Arrays.sort(sorted);
        return sorted[k];
    }
}
