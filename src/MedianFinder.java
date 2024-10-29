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

    protected int partition(int [] arr, int l, int r) {
        int i = l;
        for (int j = l+1; j <= r ; j++) {
            if (arr[j] <= arr[l]) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i, l);
        return i;
    }

    protected void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void setDataPoints(int[] dataPoints) {
        this.dataPoints = dataPoints;
    }
}
