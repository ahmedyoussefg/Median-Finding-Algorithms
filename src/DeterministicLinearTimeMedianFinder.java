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
            Arrays.sort(dataPoints, l, r + 1);
            return dataPoints[l + k];
        }
        List<Integer> mediansList = new ArrayList<>();
        for (int i = l; i <= r; i += 5) {
            List<Integer> five = new ArrayList<>();
            for (int j = i; j < i + 5 && j <= r; j++) {
                five.add(this.dataPoints[j]);
            }
            Collections.sort(five);
            mediansList.add ( five.get(five.size() / 2 - 1 + (five.size() % 2)) );
        }
        int[] medians = mediansList.stream().mapToInt(Integer::intValue).toArray();
        int medianOfMedians = new DeterministicLinearTimeMedianFinder(medians).getMedian();

        int pivotIndex = 0;
        for (int i = l; i <= r; i++){
            if (this.dataPoints[i]==medianOfMedians){
                pivotIndex=i;
                break;
            }
        }
        this.swap(pivotIndex, l);
        int[] m = this.partition(l, r);
        int rankMax = m[1]-l;
        int rankMin = m[0]-l;
        if (k >= rankMin && k<= rankMax) {
            return this.dataPoints[m[0]];
        }
        else if (k < rankMin) {
            return select(l, m[0] - 1 , k);
        }
        else {
            return select(m[1] + 1, r, k - rankMax - 1);
        }
    }
}
