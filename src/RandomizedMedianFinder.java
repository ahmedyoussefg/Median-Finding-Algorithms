import java.util.Random;

public class RandomizedMedianFinder extends MedianFinder {
    private Random rand;
    public RandomizedMedianFinder(int[] dataPoints) {
        super(dataPoints);
        this.rand = new Random();
    }
    @Override
    public int select(int l, int r, int k) {
        if (l == r) {
            return this.dataPoints[l];
        }
        int m = randomizedPartition(l, r);
        int p = m - l;
        if (k == p) {
            return this.dataPoints[m];
        }
        else if (k < p) {
            return select(l, m - 1 , k);
        }
        else {
            return select(m + 1, r, k - p - 1);
        }
    }

    private int randomizedPartition(int l, int r) {
        int pivotIndex = this.rand.nextInt(r - l) + l;
        this.swap(pivotIndex, l);
        return this.partition(l, r);
    }
}
