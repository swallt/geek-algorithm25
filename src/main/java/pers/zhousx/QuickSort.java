package main.java.pers.zhousx;

import java.util.Arrays;

/**
 * 快速排序
 * 大体上可以分三步：
 * 第一步： 选取基准值（通过一次快排可以把无序数组分为小于基准值、大于基准值的两部分），通常会选取第一个元素
 * 第二步： 从基准值后一个元素开始遍历至待排序数组尾部。该遍历操作我个人采用快慢指针进行理解，快指针每次循环
 *          加一；慢指针只有当快指针值小于基准值时，先将快慢指针所指的值交换，然后加一。直到快指针完成遍历，
 *          完成该次遍历后，将基准值和慢指针前一位所指向的值进行交换，这样慢指针左边都是小于基准值的无序数组，右边
 *          都是大于基准值的无序数组，完成单次快排操作。
 * 第三步： 对两边子无序数组也进行第二步相同的操作，直到子数组元素为1，可认为有序。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 49};
        quickSort(arr, 0, arr.length-1);
        Arrays.stream(arr).forEach(i -> System.out.print(i+","));
    }

    private static void quickSort(int[] arr, int start, int end) {
        int partion = getPartition(arr, start, end);
        if (partion - start > 1) {
            quickSort(arr, start, partion - 1);
        }
        if (end - partion > 1) {
            quickSort(arr, partion + 1, end);
        }
    }

    /**
     * 指定起止位置的子数组进行快速排序，并返回排序完成后基准值所在位置
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int getPartition(int[] arr, int start, int end) {
        //基准值
        int baseValue = arr[start];
        //快慢指针
        int slow = start + 1, fast = start + 1;
        while (fast <= end) {
            if (arr[fast] < baseValue) {
                // 快慢指针值交换
                swapValue(arr, fast, slow);
                // 慢指针加一
                slow++;
            }
            fast++;
        }
        // 交换基准值和慢指针前一位值
        swapValue(arr, start, slow-1);
        return slow-1;
    }

    private static void swapValue(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
