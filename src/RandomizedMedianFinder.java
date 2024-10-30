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
        // we partition the data points around a random pivot, into 3 groups [..less than..|..equal..|..greater..than]
        // partitioning returns the range of elements equal to the pivot
        int[] m = this.randomizedPartition(l, r);
        // the max rank is the rank of the most right equal element
        int rankMax = m[1]-l;
        // the min rank is the rank of the most left equal element
        int rankMin = m[0]-l;
        if (k >= rankMin && k <= rankMax) {
            // if k is in that range then we just return the pivot
            return this.dataPoints[m[0]];
        }
        else if (k < rankMin) {
            // else we neglect all elements greater than or equal to the pivot
            return select(l, m[0] - 1 , k);
        }
        else {
            // else we neglect all elements smaller than or equal to the pivot
            return select(m[1] + 1, r, k - rankMax - 1);
        }
    }

    private int[] randomizedPartition(int l, int r) {
        int pivotIndex = this.rand.nextInt(r - l + 1) + l;
        this.swapDataPoints(pivotIndex, l);
        return this.partition(l, r);
    }
}
