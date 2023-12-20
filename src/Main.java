interface SortingStrategy {
    void sort(int[] array);
}

class BubbleSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        // Implement Bubble Sort logic
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

// MergeSort implementation
class MergeSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {

        if (array.length <= 1) {
            return;
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        sort(left);
        sort(right);

        merge(array, left, right);
    }

    private void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}

class InsertionSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}

interface SearchingStrategy {
    int search(int[] array, int target);
}



class BinarySearch implements SearchingStrategy {
    @Override
    public int search(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }
}


interface FilteringStrategy {
    void filter(int[] array);
}

class GreaterThanFilter implements FilteringStrategy {
    private int threshold;

    public GreaterThanFilter(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void filter(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= threshold) {
                array[i] = 0;     }
        }
    }
}

class LessThanFilter implements FilteringStrategy {
    private int threshold;

    public LessThanFilter(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void filter(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= threshold) {
                array[i] = 0;
            }
        }
    }
}

// ArrayProcessor class
class ArrayProcessor {
    private SortingStrategy sortingStrategy;
    private SearchingStrategy searchingStrategy;
    private FilteringStrategy filteringStrategy;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void setSearchingStrategy(SearchingStrategy searchingStrategy) {
        this.searchingStrategy = searchingStrategy;
    }

    public void setFilteringStrategy(FilteringStrategy filteringStrategy) {
        this.filteringStrategy = filteringStrategy;
    }

    public void processArray(int[] array) {
        if (sortingStrategy != null) {
            sortingStrategy.sort(array);
        }

        if (searchingStrategy != null) {
            int target = 42;
            int result = searchingStrategy.search(array, target);
            System.out.println("Search result: " + result);
        }

        if (filteringStrategy != null) {
            filteringStrategy.filter(array);
        }

        System.out.print("Processed array: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array1 = {69,17,3,2,7};
        int[] array2 = {3,3,4,8,666};
        int[] array3 = {59,7,1,2,4};

        ArrayProcessor processor1 = new ArrayProcessor();
        processor1.setSortingStrategy(new BubbleSort());
        processor1.setSearchingStrategy(new BinarySearch());
        processor1.processArray(array1);

        ArrayProcessor processor2 = new ArrayProcessor();
        processor2.setSortingStrategy(new MergeSort());
        processor2.setSearchingStrategy(new BinarySearch());
        processor2.processArray(array2);

        ArrayProcessor processor3 = new ArrayProcessor();
        processor3.setSortingStrategy(new InsertionSort());
        processor3.setFilteringStrategy(new GreaterThanFilter(5));
        processor3.processArray(array3);
    }
}
