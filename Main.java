import java.util.Arrays;

public class Main {
    public static class BubbleSort {
        public static void bubbleSort(int[] array) {
            //冒泡排序：在无序区间，通过相邻数的比较，
            //将最大的数冒泡到无序区间的最后，持续这个过程，直到数组整体有序
            //时间复杂度：O(N ^ 2) 空间复杂度：O(1)
            //稳定性：稳定排序
            //升序排序为例：
            //每次找最小元素，从后往前遍历
            //每次找最大元素：从前往后遍历

            //找最大元素
            for (int bound = 0; bound < array.length; bound++) {
                //[0, bound) 已排序区间 [bound, size) 未排序区间
                for (int cur = array.length - 1; cur > bound; cur--) {
                    if (array[cur - 1] > array[cur]) {
                        swap(array, cur - 1, cur);
                    }
                }
            }
        }
        public static void swap(int[] array, int i, int j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        public static void main(String[] args) {
            int[] array = {4, 7, 9, 1, 5, 6, 3, 8, 2};
            bubbleSort(array);
            System.out.println(Arrays.toString(array));
        }
    }
}

class HeapSort {
    //堆排序
    //时间复杂度：O(NlogN) [比 O(N^2) 快] 空间复杂度：O(1)
    //稳定性：不稳定排序

    //排升序要建大堆；排降序要建小堆

    public static void heapSort(int[] array) {
        //1.先建立堆
        creatHeap(array);
        int heapSize = array.length;
        for (int i = 0; i < array.length; i++) {
            swap(array, 0, heapSize - 1);
            //交换堆顶元素和最后一个元素
            heapSize --;
            //删除堆中最后一个元素
            shiftDown(array, heapSize, 0);
            //重新向下调整
        }
    }
    private static void creatHeap(int[] array) {
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            shiftDown(array, array.length, i);
        }
    }
    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    private static void shiftDown(int[] array, int size, int index) {
        int parent = index;
        int child = 2 * parent + 1;
        while (child < size) {
            if (child + 1 < size && array[child + 1] > array[child]) {
                child = child + 1;
            }
            if (array[child] > array[parent]) {
                swap(array, child, parent);
            }else {
                break;
            }
            parent = child;
            child = 2 * parent + 1;
        }
    }
    public static void main(String[] args) {
        int[] array = {4, 7, 9, 1, 5, 6, 3, 8, 2};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}

class ImportentQuickSort {
    public static void quickSort(int[] array) {
        //快速排序：需要用到递归
        //1.从待排序区间选择一个数(第一个或最后一个)，作为基准值(pivot)；
        //2.核心 Partition: 遍历整个待排序区间，将比基准值小的（可以包含相等的）放到基准值的左边，
        // 将比基准值大的（可以包含相等的）放到基准值的右边；
        //3.采用分治思想，对左右两个小区间按照同样的方式处理，直到小区间的长度 == 1，代表已经有序，
        // 或者小区间的长度 == 0，代表没有数据

        //时间复杂度：
        //最坏情况下 => O(N ^ 2)(每次的基准值正好是最大值或最小值)
        //平均情况下 => O(NlogN)
        //空间复杂度：
        //最坏情况下 => O(N) 平均情况下 => O(logN)
        //稳定性：不稳定排序

        //设基准值为最后一个元素（基准值为首元素则操作相反）
        quickSortHelper(array, 0, array.length - 1);
    }
    private static void quickSortHelper(int[] array, int left, int right) {
        if(left >= right) {
            //区间中有 0 或 1 个元素，不需排序
            return;
        }
        int index = partition(array, left, right);
        //返回值表示整理后 基准值 所在位置
        //[left, ingex - 1]  [index + 1, right]
        quickSortHelper(array, left, index -1);
        quickSortHelper(array, index + 1, right);
    }
    private static int partition(int[] array, int left, int right) {
        int baseValue = array[right];//设基准值为最后一个元素
        int i = left;
        int j = right;
        while (i < j) {
            //从左往右找到一个 大于基准值 的元素
            while (i < j && array[i] <= baseValue) {
                i++;
            }
            //再从右往左找到一个 小于基准值 的元素
            while (i < j && array[j] >= baseValue) {
                j--;
            }

            //此时 j 指向的元素要么和 i 重合，要么小于基准值
            //交换 i 和 j 的值
            if(i <j) {
                swap(array, i, j);
            }
        }
        //循环结束， i、j 重合,再与基准值交换
        //重合元素一定大于基准值元素
        swap(array, i, right);
        return i;
    }
    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {4, 9, 1, 5, 6, 3, 8, 2};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
