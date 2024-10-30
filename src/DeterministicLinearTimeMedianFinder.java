import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeterministicLinearTimeMedianFinder extends MedianFinder {
    // Implementation of the Median of Medians algorithm

    public DeterministicLinearTimeMedianFinder(int[] dataPoints) {
        super(dataPoints);
    }

    @Override
    public int select(int l, int r, int k) {
        if (r-l+1 <= 5) {
            // if we have less than 5 elements, we naively get the kth element
            Arrays.sort(dataPoints, l, r + 1);
            return dataPoints[l + k];
        }
        int numOfMedians = (r - l + 1 + 4) / 5;
        int[] medians = new int[numOfMedians];
        // counter for number of medians
        int cnt = 0;
        for (int i = l; i <= r; i += 5) {
            // we get the 5 elements that include ith element
            int [] five = Arrays.copyOfRange(dataPoints, i, Math.min(i + 5, r + 1));
            Arrays.sort(five);
            // add the median of the five elements
            // (they might be less than five if the array length is not divisible by 5)
            medians[cnt++]= ( five[five.length / 2 - 1 + (five.length % 2)] );
        }
        int[] exactMedians = Arrays.copyOf(medians, cnt);
        // get the median of medians by recursive call with but with new array "medians" which has approx length n/5
        int medianOfMedians = new DeterministicLinearTimeMedianFinder(exactMedians).getMedian();

        // we partition the data points around the median of medians, into 3 groups [..less than..|..equal..|..greater..than]
        int pivotIndex = 0;
        for (int i = l; i <= r; i++){
            if (this.dataPoints[i]==medianOfMedians){
                pivotIndex=i;
                break;
            }
        }
        // we make the pivot at the start
        this.swapDataPoints(pivotIndex, l);
        // partitioning returns the range of elements equal to the pivot
        int[] m = this.partition(l, r);
        // the max rank is the rank of the most right equal element
        int rankMax = m[1]-l;
        // the min rank is the rank of the most left equal element
        int rankMin = m[0]-l;
        if (k >= rankMin && k<= rankMax) {
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
}
