import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch(int[] arr, int number) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == number) {
                return mid;
            } else if (arr[mid] < number) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 10, 12};
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        int number = 10;
        int result = binarySearch(arr, number);

        if (result == -1) {
            System.out.println("Искомый элемент " + number + " отсутствует в массиве: " + result);
        } else {
            System.out.println("Искомый элемент " + number + " найден по индексу: " + result);
        }
    }
}
