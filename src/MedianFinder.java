public abstract class MedianFinder {
    protected int[] dataPoints;
    public MedianFinder() {}

    public MedianFinder(int[] dataPoints) {
        this.dataPoints = dataPoints;
    }

    public abstract int select(int l, int r, int k);

    public int getMedian() {
        int n = this.dataPoints.length;
        return select(0, n - 1, n / 2 - 1 + (n%2));
    }

    protected int[] partition(int l, int r) {
        int pivot = this.dataPoints[l];
        int lt = l; // less than partition pointer
        int gt = r; // greater than partition pointer
        for (int i = l+1; i <= gt ; ) {
            if (this.dataPoints[i] < pivot) {
                swap(lt, i);
                i++;
                lt++;
            }
            else if (this.dataPoints[i] > pivot) {
                swap(gt, i);
                gt--;
            }
            else {
                i++;
            }
        }
        return new int[]{lt,gt};
    }

    protected void swap(int i, int j) {
        int temp = this.dataPoints[i];
        this.dataPoints[i] = this.dataPoints[j];
        this.dataPoints[j] = temp;
    }

    public void setDataPoints(int[] dataPoints) {
        this.dataPoints = dataPoints;
    }
}
