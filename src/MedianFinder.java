public abstract class MedianFinder {
    protected int[] dataPoints;
    public MedianFinder() {}

    public MedianFinder(int[] dataPoints) {
        this.dataPoints = dataPoints;
    }

    public abstract int select(int l, int r, int k);

    public int getMedian() {
        int n = this.dataPoints.length;
        // if the n is odd then we return the n/2th element else  we return (n/2 -1)th element, 0-based indexing
        return select(0, n - 1, n / 2 - 1 + (n%2));
    }

    protected int[] partition(int l, int r) {
        int pivot = this.dataPoints[l]; // let the pivot be the first element
        int lt = l; // pointer after less than partition
        int gt = r; // pointer before greater than partition
        for (int i = l+1; i <= gt ; ) {
            if (this.dataPoints[i] < pivot) {
                // if current element is less than pivot, we add it to less than partition and increase lt
                swapDataPoints(lt, i);
                lt++;
                i++; // go to next element
            }
            else if (this.dataPoints[i] > pivot) {
                // if current element is greater than pivot, we add it to greater than partition and decrease gt
                swapDataPoints(gt, i);
                gt--;
            }
            else {
                // if current element is equal to the pivot we skip it
                i++;
            }
        }
        // we return range of the equal-to-the-pivot elements
        return new int[]{lt,gt};
    }

    // helper function to swap two elements in the dataPoints array, at index i and index j
    protected void swapDataPoints(int i, int j) {
        int temp = this.dataPoints[i];
        this.dataPoints[i] = this.dataPoints[j];
        this.dataPoints[j] = temp;
    }
}
