package com.abdullaevaziz.arrays;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * 1. Создать базовый класс NumberCalculator,
 * который имеет поля: массивы целых чисел
 * с названиями соответственно
 * dataA, massPositive и massNegative,
 */
public abstract class NumberCalculator {

    private int[] dataA;
    private int[] massPositive;
    private int[] massNegative;

    /**
     * Конструктор принимает на вход число n – размер массивов.
     * Конструктор должен заполнить случайными числами
     * в диапазоне от -100 до 100 только второй и третий массивы.
     * Второй массив заполняется только положительными числами,
     * третий – отрицательными
     */
    public NumberCalculator(int n) {
        this.dataA = new int[n];
        this.massPositive = new int[n];
        this.massNegative = new int[n];
        for (int i = 0; i < n; i++) {
            this.massPositive[i] = (int) (Math.random() * 100) + 1;
            this.massPositive[i] = -((int) (Math.random() * 100) + 1);
        }
    }

    /**
     * 2. Реализовать метод fill, который принимает на вход переменное количество
     * аргументов типа int и производит заполнение первого массива.
     * Корректно обработать ситуацию,
     * когда размер переданного массива окажется меньше размера исходного
     */
    public void fill(int... mass) {
        if (mass == null) {
            throw new IllegalArgumentException("Ошибка бро!");
        }
        System.arraycopy(mass, 0, dataA, 0, mass.length);
    }

    /**
     * 3. Написать метод, который производит операцию
     * над двумя целыми числами,
     * которая пока что не определена,
     * но вскоре будет реализована в наследниках класса
     */
    public abstract int operation(int a, int b);

    /**
     * 4. Написать метод result, который применяет
     * операцию для всех элементов первого массива
     */
    public int result() {
        int result = dataA[0];
        for (int i = 0; i < dataA.length; i++) {
            result = operation(result, dataA[i]);
        }
        return result;
    }

    /**
     * 5. Написать метод, который возвращает объект такого же типа,
     * как и объект, для которого будет вызван метод,
     * используя динамическую идентификацию типа через getClass()
     * и getConstructor(), подавая в качестве аргумента конструктора
     * длину массива, увеличенную вдвое
     */
    public NumberCalculator createNewInstance() {
        try {
            return this.getClass().getConstructor(int.class)
                    .newInstance(dataA.length * 2);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 6. Написать метод, который будет динамически определять и
     * получать массивы и данные из них,
     * которые сохранены в полях, начинающихся с названия mass
     */
    public void printMassArrays() {
        Field[] fields = NumberCalculator.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getName().startsWith("mass")) {
                    int[] value = (int[]) field.get(this);
                    System.out.println(Arrays.toString(value));
                }
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 8. Реализовать геттеры вашего класса таким образом,
     * чтобы они возвращали точные копии ваших массивов
     */
    public int[] getDataA() {
        return Arrays.copyOf(dataA, dataA.length);
    }

    public int[] getMassPositive() {
        return Arrays.copyOf(massPositive, massPositive.length);
    }

    public int[] getMassNegative() {
        return Arrays.copyOf(massNegative, massNegative.length);
    }

    /**
     * 9. Используя динамическую идентификацию типов,
     * написать метод, который вызывает только геттеры,
     * начинающиеся с названия getMass
     */
    public void invokeMassGetters() {
        Method[] method = this.getClass().getMethods();
        for (Method method1 : method) {
            if (method1.getName().startsWith("getMass")) {
                String get = method1.getName();
                System.out.println(get);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberCalculator that = (NumberCalculator) o;
        return Arrays.equals(dataA, that.dataA) && Arrays.equals(massPositive, that.massPositive) && Arrays.equals(massNegative, that.massNegative);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(dataA);
        result = 31 * result + Arrays.hashCode(massPositive);
        result = 31 * result + Arrays.hashCode(massNegative);
        return result;
    }

    @Override
    public String toString() {
        return "NumberCalculator{" +
                "dataA=" + Arrays.toString(dataA) +
                ", massPositive=" + Arrays.toString(massPositive) +
                ", massNegative=" + Arrays.toString(massNegative) +
                '}';
    }
}
