/*12
   Пузырьковая сортировка
   Сортировка одномерного массива по возрастанию
   Подсчет колличества проходов и колличества обменов
   
 */
package sorting;

import java.util.Arrays;

/**
 *
 * @author vvm64
 */
public class BubbleSort {

    public static int nPasses = 0; // колличество проходов
    public static int nSwaps = 0; // колличество обменов

    public static void main(String[] args) {
        //  int[] ar = {7, 7, 7, 7, 7}; // тут будет один проход и ноль обменов
        int[] ar = {7, -7, 1, 7, 0};
        bubbleSort(ar);
        System.out.print("array = " + Arrays.toString(ar));
        System.out.println();
        System.out.println("колличество проходов = " + nPasses);
        System.out.println("колличество обменов = " + nSwaps);
    }

    public static void bubbleSort(int[] ar) {
        boolean swapped = true;
        int last = ar.length;

        while (swapped) {
            swapped = false; // в начале обмена не было

            for (int i = 0; i < last - 1; i++) {
                if (ar[i] > ar[i + 1]) { // если пара не по порядку, то обмениваем её
                    swap(ar, i, i + 1);
                    swapped = true; //обмен произошел
                    BubbleSort.nSwaps++; //увеличиваем счетчик обмена 

                }
            }
            last--;
            BubbleSort.nPasses++;//увеличиваем счетчик проходов
        }
    }

    private static void swap(int[] arr, int from, int to) { //обмен
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }
}
