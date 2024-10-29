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
        int [] oldDataPoints = Arrays.copyOf(this.dataPoints,this.dataPoints.length);
        int c = 0;
        for (int i = l; i <= r; i += 5) {
            List<Integer> five = new ArrayList<>();
            for (int j = i; j < i + 5 && j <= r; j++) {
                five.add(this.dataPoints[j]);
            }
            Collections.sort(five);
            this.dataPoints[c]=five.get(five.size() / 2 - 1 + (five.size() % 2));
            c++;
        }
        int medianOfMedians = select(l, l+c-1, l+(c/2)-1+(c%2));
        int pivotIndex = 0;
        this.dataPoints=oldDataPoints;
        for (int i = l; i <= r; i++){
            if (this.dataPoints[i]==medianOfMedians){
                pivotIndex=i;
            }
        }
        this.swap(pivotIndex, l);
        int m = this.partition(l, r);
        int p = m - l;
        if (k == p ){
            return medianOfMedians;
        }
        else if (k < p) {
            return select(l, pivotIndex-1, k);
        }
        else {
            return select(pivotIndex+1, r, k-p-1);
        }
    }
}
