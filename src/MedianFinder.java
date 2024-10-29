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
    protected int partition(int l, int r) {
        int i = l;
        for (int j = l+1; j <= r ; j++) {
            if (this.dataPoints[j] <= this.dataPoints[l]) {
                i++;
                swap(i, j);
            }
        }
        swap(i, l);
        return i;
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
