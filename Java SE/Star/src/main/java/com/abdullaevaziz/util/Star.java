package com.abdullaevaziz.util;

import java.util.Arrays;

/**
 * 1. Создать понятие Star, в качестве поля данного класса сделать двумерный массив символов
 */
public class Star {

    private char[][] mass;

    public Star() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return Arrays.equals(mass, star.mass);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(mass);
    }

    /**
     * 2. Конструктор класса должен принимать на вход размер массива n и производить его инициализацию.
     * После инициализации массив должен иметь размер n на n
     * 3. В конструкторе произвести заполнение двумерного массива точками, используя вложенные циклы
     */
    /**
     * 4. Далее, используя только один цикл, произвести заполнение звездочками элементов,
     * которые находятся на главной и побочной диагоналях таблицы
     */
    /**
     * 5. В том же цикле произвести заполнение звездочками элементов по середине горизонтали и вертикали таблицы.
     * А если размер таблицы является четным, то дополнительно заполнить еще строку выше середины и
     * столбец левее середины звездочками
     */
    public Star(int n) {
        this.mass = new char[n][n];
        for (int i = 0; i < this.mass.length; i++) {
            for (int j = 0; j < this.mass[0].length; j++) {
                this.mass[i][j] = '.';
            }
        }

        for (int i = 0; i < this.mass.length; i++) {
            this.mass[i][i] = '*';
            this.mass[i][this.mass.length - 1 - i] = '*';

            this.mass[this.mass.length / 2][i] = '*';
            this.mass[i][this.mass.length / 2] = '*';
        }
    }

    /**
     * 6. В результате “*” в массиве должны образовывать изображение звездочки,
     * которое вы должны получить, преобразовав в методе toString двумерный массив в строковое значение в виде таблицы
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.mass.length; i++) {
            for (int j = 0; j < this.mass[0].length; j++) {
                res += this.mass[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }


}