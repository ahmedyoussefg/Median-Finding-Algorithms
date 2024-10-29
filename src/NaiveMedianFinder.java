import java.util.Arrays;

public class NaiveMedianFinder extends MedianFinder {
    public NaiveMedianFinder(int[] dataPoints) {
        super(dataPoints);
    }

    @Override
    public int select(int l, int r, int k) {
        int [] sorted = this.dataPoints;
        // sort then return kth element (k is zero based)
        Arrays.sort(sorted);
        return sorted[k];
    }
}
