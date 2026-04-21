package com.abdullaevaziz.arrays;

import java.util.Scanner;

public class ArrayUtil {
    /**
     * 1.	fill
     * Дан массив целых чисел. Заполнить данный массив с клавиатуры.
     * Scanner подать как аргумент метода
     */
    public static void fill(int[] mass, Scanner scanner) {
        for (int i = 0; i < mass.length; i++) {
            mass[i] = scanner.nextInt();
        }
    }

    /**
     * 2. fillRandom
     * Дан массив целых чисел. Заполнить данный массив числами в диапазоне от a до b
     * Указание:
     * Для генерации случайного числа в диапазоне от a до b реализовать отдельный метод getRandomNumber
     */
    public static int getRandomNumber(int a, int b) {
        return a + (int) (Math.random() * (b - a + 1));
    }

    public static void fillRandom(int[] mass, int a, int b) {
        for (int i = 0; i < mass.length; i++) {
            mass[i] = getRandomNumber(a, b);
        }
    }

    /**
     * 3. toString
     * Дан массив целых чисел.
     * Преобразовать данный массив в строковое значение в формате JSON
     */
    public static String toString(int[] mass) {
        String res = "[";
        for (int val : mass) {
            if (!res.equals("[")) { //res != "["
                res += ", ";
            }
            res += val;
        }
        return res + "]";
    }

    /**
     * 4. evenIndexes
     * Дан массив целых чисел. Выведите все элементы массива с четными индексами.
     * В программе запрещено использовать условную инструкцию для проверки четности индексов.
     * Решение оформить 2 способами:
     * -возвращать строковое представление массива в формате JSON
     */
    public static String evenIndexes(int[] mass) {
        String res = "[";
        for (int i = 0; i < mass.length; i += 2) {
            if (!res.equals("[")) {
                res += ", ";
            }
            res += mass[i];
        }
        return res + "]";
    }

    /**
     * 5. evenData
     * Дан массив целых чисел. Выведите все четные элементы массива.
     * Указание:
     * Для проверки четности элемента массива реализовать отдельный метод isEven,
     * который будет проверять, является ли переданный ей элемент четным.
     * Решение оформить 2 способами:
     * -возвращать строковое представление массива в формате JSON
     */
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static String evenData(int[] mass) {
        String res = "[";
        for (int var : mass) {
            if (isEven(var)) {
                if (!res.equals("[")) {
                    res += ", ";
                }
                res += var;
            }
        }
        return res + "]";
    }

    /**
     * 6. countPositive
     * Дан массив целых чисел.
     * Определить количество положительных элементов в данном массиве
     */
    public static int countPositive(int[] mass) {
        int counter = 0;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] > 0) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * 7. greatPrev
     * Дан массив целых чисел. Выведите все элементы массива, которые больше предыдущего элемента.
     * Решение оформить 2 способами:
     * -возвращать строковое представление массива в формате JSON
     */
    public static String greatPrev(int[] mass) {
        String res = "[";
        for (int i = 1; i < mass.length; i++) {
            if (mass[i] > mass[i - 1]) {
                if (!res.equals("[")) {
                    res += ", ";
                }
                res += i;
            }
        }
        return res + "]";
    }

    /**
     * 8. sameNeighbours
     * Дан массив целых чисел. Если в нем есть два соседних элемента одного знака, выведите эти числа.
     * Если соседних элементов одного знака нет - не выводите ничего. Если таких пар соседей несколько - выведите первую пару.
     * Указание
     * -Вернуть результат в виде массива
     */
    public static int[] sameNeighbours(int[] mass) {
        for (int i = 0; i < mass.length - 1; i++) {
            if (mass[i] > 0 && mass[i + 1] > 0 || mass[i] < 0 && mass[i + 1] < 0) {
                return new int[]{mass[i], mass[i + 1]};
            }
        }
        return new int[]{};
    }

    /**
     * 9. greaterNeighbours
     * Дан массив целых чисел. Определите,
     * сколько в этом массиве элементов, которые больше двух своих соседей и выведите количество таких элементов.
     */
    public static int greaterNeighbours(int[] mass) {
        int count = 0;
        for (int i = 1; i < mass.length - 1; i++) {
            if (mass[i - 1] < mass[i] && mass[i + 1] < mass[i]) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * 10. max
     * Дан массив целых чисел. Выведите значение наибольшего элемента в массиве
     */
    public static int max(int[] mass) {
        int max = Integer.MIN_VALUE;
        for (int val : mass) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    /**
     * 11. maxValues
     * Дан массив целых чисел.
     * Выведите все индексы наибольшего значения данного массива
     * Указание:
     * -Вернуть результат в виде массива
     */
    public static int[] maxValues(int[] mass) {
        int max = max(mass);
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            if (max == mass[i]) {
                count++;
            }
        }
        int[] res = new int[count];
        int j = 0;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] == max) {
                res[j] = i;
                j++;
            }
        }
        return res;
    }

    /**
     * 12. minPositive
     * Дан массив целых чисел.
     * Выведите значение наименьшего из всех положительных элементов в массиве.
     * Известно, что в массиве есть хотя бы один положительный элемент.
     */
    public static int minPositive(int[] mass) {
        int min = Integer.MAX_VALUE;
        for (int val : mass) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    /**
     * 13. minOdd
     * Дан массив целых чисел. Выведите значение наименьшего нечетного элемента массива,
     * а если в массиве нет нечетных элементов см. способы реализации.
     * Указание:
     * Для проверки нечетности элемента использовать заранее написанную функцию из п.5
     * Решение оформить 2 способами:
     * -Вернуть значение 0 при отсутствии элементов, удовлетворяющих условию задачи
     */
    public static int minOdd(int[] mass) {
        int min = Integer.MAX_VALUE;
        boolean has = false;
        for (int i = 0; i < mass.length; i++) {
            if (!isEven(mass[i]) && mass[i] < min) {
                has = true;
                min = mass[i];
            }
        }
        return has ? min : 0;
    }

    /**
     * 16. countDifferent
     * Дан массив целых чисел, упорядоченный по неубыванию элементов в нем.
     * Определите, сколько в нем различных элементов (количество неравных друг другу элементов).
     */
    public static int countDifferent(int[] mass) {
        int count = 1;
        for (int i = 0; i < mass.length - 1; i++) {
            if (mass[i] != mass[i + 1]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 18. reverseNeighbours
     * Дан массив целых чисел. Переставьте соседние элементы массива.
     * Если элементов нечетное число, то последний элемент остается на своем месте.
     */
    public static void reverseNeighbours(int[] mass) {
        int temp = 0;
        for (int i = 0; i < mass.length - 1; i += 2) {
            if (mass.length != temp) {
                temp = mass[i];
                mass[i] = mass[i + 1];
                mass[i + 1] = temp;
            }
        }
    }

    /**
     * 29. search
     * Дан массив целых чисел и число key.
     * Методом линейного поиска при помощи цикла for найти индекс вхождения числа key в массиве
     */
    public static int search(int[] mass, int key) {
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 29.1 searchIndexes (maxValues) смотреть на maxValues
     * Модернизировать программу таким образом,
     * чтобы она вернула индексы всех вхождений числа key в массиве.
     * Решение оформить 2 способами:
     * -использовать массив для хранения элементов
     */
    public static int[] searchIndexes(int[] mass, int key) {
        int count = 0;
        for (int i = 0; i < mass.length; i++) {
            if (key == mass[i]) {
                count++;
            }
        }

        int[] res = new int[count];
        int j = 0;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] == key) {
                res[j] = i;
                j++;
            }
        }
        return res;
    }

}
