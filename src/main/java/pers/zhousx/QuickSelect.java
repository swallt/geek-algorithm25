package main.java.pers.zhousx;

import java.util.Random;

/**
 * 快速选择
 * 有时我们只需要取得排x位的元素，那么就没必要对原数组进行整体排序，按照快速排序的算法，可以根据基准值元素的排名ind与x的
 * 关系：
 *   1、ind刚好等于x，那么说明这个基准值就是我们要找的排x位的元素
 *   2、ind大于x，说明目标值在基准值前，那么只需要在基准值前查找排x位的元素
 *   3、ind小于x，说明目标值在基准值后，问题就转变为在基准值后查找排x-ind位的元素
 *
 * 快速排序的三种常见优化思路：
 *   1、单边递归优化，这个思路是减少函数调用次数（每次函数调用都是一次方法栈帧的入栈），该方式是本层完成按基准值分隔后让
 *   本层继续完成基准值左边的分隔操作，而右边的排序工作交给下一层递归函数处理
 *   2、基准值选取优化，如果基准值选取不合理，快速排序的时间复杂度有可能达到O(n^2)这个两级，也就是退化成和选择排序、插入
 *   排序等一样的时间复杂度。只有当基准值每次都能将排序区间中的数据平分时，时间复杂度才是最好情况下的O(n*logn)。一般采用
 *   三点取中法，即取头、中、尾三点中的中间值作为本轮的基准值。因为一般排序后的子数组只有在大小为3以上时才会进行排序。
 *   3、partition分隔操作优化，仅在找到两个在基准值前后的值时才进行交换，减少了交换操作
 */
public class QuickSelect {

    public static void main(String[] args) {
        int[] arr = new int[10000];
        int[] arrNew = new int[10000];
        Random random = new Random();
        long sum = 0L;
        long sumNew = 0L;
        for (int c = 0; c < 100; c++) {
            for (int i = 0; i < 10000; i++) {
                arr[i] = random.nextInt(10);
                arrNew[i] = arr[i];
            }
            long start = System.currentTimeMillis();
            quickSelect_hg(arr, 0, arr.length - 1);
            long cost = System.currentTimeMillis() - start;
            System.out.println("第" + c + "轮耗时" + cost + "毫秒");
            sum += cost;
            long startNew = System.currentTimeMillis();
            QuickSort.quickSort_hg(arrNew, 0, arr.length - 1);
            long costNew = System.currentTimeMillis() - startNew;
            System.out.println("NEW:第" + c + "轮耗时" + costNew + "毫秒");
            sumNew += costNew;

        }
        System.out.println("总耗时【" + sum + "】,平均耗时【" + (sum / 100) + "】");
        System.out.println("New总耗时【" + sumNew + "】,平均耗时【" + (sumNew / 100) + "】");
    }

    public static void quickSelect_hg(int[] arr, int start, int end) {
        while (start < end) {
            //基准值优化
            int x = start, y = end, z = getMid(arr[start], arr[end], arr[(start + end) >> 1]);
            do {
                //partition分隔操作优化
                while (arr[x] < z) {
                    ++x;
                }
                while (arr[y] > z) {
                    --y;
                }
                if (x <= y) {
                    int tmp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = tmp;
                    x++;
                    y--;
                }
            } while (x <= y);
            // 右边交给下一层递归
            quickSelect_hg(arr, x, end);
            end = y;
        }
    }

    private static int getMid(int a, int b, int c) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (a > c) {
            int tmp = a;
            a = c;
            c = tmp;
        }
        if (b > c) {
            int tmp = b;
            b = c;
            c = tmp;
        }
        return b;
    }
}
