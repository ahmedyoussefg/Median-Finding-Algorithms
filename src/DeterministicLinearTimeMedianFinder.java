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
        return select(this.dataPoints, k);
    }

    public int select(int [] arr, int k) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            return arr[arr.length / 2 - 1 + (arr.length % 2)];
        }
        List<Integer> mediansList = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 5) {
            List<Integer> five = new ArrayList<>();
            for (int j = i; j < i + 5 && j < arr.length; j++) {
                five.add(arr[j]);
            }
            Collections.sort(five);
            mediansList.add( five.get(five.size() / 2 - 1 + (five.size() % 2)) );
        }
        int [] medians = new int[mediansList.size()];
        for (int i = 0; i < medians.length; i++){
            medians[i] = mediansList.get(i);
        }
        int medianOfMedians = select(medians, medians.length / 2 - 1 +( medians.length% 2));
        int pivotIndex = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i]==medianOfMedians){
                pivotIndex=i;
            }
        }
        this.swap(arr, pivotIndex, 0);
        int m = this.partition(arr,0, arr.length);
        if (k == m ){
            return medianOfMedians;
        } else if (k < m) {
            // Search in the left partition
            return select(Arrays.copyOfRange(arr, 0, pivotIndex), k);
        } else {
            // Search in the right partition
            return select(Arrays.copyOfRange(arr, pivotIndex + 1, arr.length), k - m - 1);
        }
    }
}
