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
        int[] m = randomizedPartition(l, r);
        int rankMax = m[1]-l;
        int rankMin = m[0]-l;
        if (k >= rankMin && k<=rankMax) {
            return this.dataPoints[m[0]];
        }
        else if (k < rankMin) {
            return select(l, m[0] - 1 , k);
        }
        else {
            return select(m[1] + 1, r, k - rankMax - 1);
        }
    }

    private int[] randomizedPartition(int l, int r) {
        int pivotIndex = this.rand.nextInt(r - l + 1) + l;
        this.swap(pivotIndex, l);
        return this.partition(l, r);
    }
}
