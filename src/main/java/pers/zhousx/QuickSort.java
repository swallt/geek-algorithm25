package main.java.pers.zhousx;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 * 大体上可以分三步：
 * 第一步： 选取基准值（通过一次快排可以把无序数组分为小于基准值、大于基准值的两部分），通常会选取第一个元素
 * 第二步： 从基准值后一个元素开始遍历至待排序数组尾部。该遍历操作我个人采用快慢指针进行理解，快指针每次循环
 * 加一；慢指针只有当快指针值小于基准值时，先将快慢指针所指的值交换，然后加一。直到快指针完成遍历，
 * 完成该次遍历后，将基准值和慢指针前一位所指向的值进行交换，这样慢指针左边都是小于基准值的无序数组，右边
 * 都是大于基准值的无序数组，完成单次快排操作。
 * 第三步： 对两边子无序数组也进行第二步相同的操作，直到子数组元素为1，可认为有序。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[100000];
        int[] arrNew = new int[100000];
        Random random = new Random();
        long sum = 0L;
        long sumNew = 0L;
        for (int c = 0; c < 100; c++) {
            for (int i = 0; i < 100000; i++) {
                arr[i] = random.nextInt(10);
                arrNew[i] = arr[i];
            }
            long start = System.currentTimeMillis();
            quickSort(arr, 0, arr.length - 1);
            long cost = System.currentTimeMillis() - start;
            System.out.println("第" + c + "轮耗时" + cost + "毫秒");
            sum += cost;
//            Arrays.stream(arr).forEach(i -> System.out.print(i+","));
//            System.out.println();

            long startNew = System.currentTimeMillis();
            quickSort_hg(arrNew, 0, arr.length - 1);
            long costNew = System.currentTimeMillis() - startNew;
            System.out.println("NEW:第" + c + "轮耗时" + costNew + "毫秒");
            sumNew += costNew;
//            Arrays.stream(arrNew).forEach(i -> System.out.print(i+","));
//            System.out.println();
        }
        System.out.println("总耗时【" + sum + "】,平均耗时【" + (sum / 100) + "】");
        System.out.println("New总耗时【" + sumNew + "】,平均耗时【" + (sumNew / 100) + "】");

//        Arrays.stream(arr).forEach(i -> System.out.print(i+","));
    }

    /**
     * alicia-ying/jikeshijian 项目的java实现
     * 胡光老师的代码很精简，交换值的思路也很巧妙，双链交换，最后一个值跟基准值交换，隐藏了常见的定义临时变量进行交换
     * 由于操作都在一个方法内，单个方法栈帧大小会大于我的实现，如果测试出现了StackOverflowError，请调整Xss参数
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort_hg(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int x = start, y = end, z = arr[start];
        while (x < y) {
            while (x < y && arr[y] >= z) {
                --y;
            }
            if (x < y) {
                arr[x++] = arr[y];
            }
            while (x < y && arr[x] <= z) {
                ++x;
            }
            if (x < y) {
                arr[y--] = arr[x];
            }
        }
        arr[x] = z;
        quickSort_hg(arr, start, x - 1);
        quickSort_hg(arr, x + 1, end);
    }

    public static void quickSort(int[] arr, int start, int end) {
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
     *
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
        swapValue(arr, start, slow - 1);
        return slow - 1;
    }

    private static void swapValue(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
