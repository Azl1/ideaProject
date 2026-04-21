package com.abdullaevaziz.model;

public class Fraction implements Comparable<Fraction>{
    private int a;
    private int b;

    /**
     * A)Создайте класс Fraction в пакете model, описывающий понятие рациональной дроби.
     * Класс должен иметь два поля: числитель a и знаменатель b.
     * Оба поля должны быть типа int.
     */
    public Fraction() {
        this.b = 1;
    }

    public Fraction(int a, int b) {
        if(b == 0) {
            throw new ArithmeticException("Знаменатель не может быть равен нулю");
        }
        this.a = a;
        this.b = b;
    }

    public Fraction(int a) {
        this.a = a;
        this.b = 1;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    /**
     * C)Переопределите метод toString таким образом, чтобы он возвращал строковое представление объекта в формате:
     * •0, если а равно 0
     * •а, если b равно 1
     * •a/b, в остальных случаях
     */
    @Override
    public String toString() {
        if(this.a == 0) {
            return "0";
        }
        if (this.b == 1){
            return this.a + "";
        }
        return this.a + "/" + this.b;
    }

    /**
     * Реализуйте метод compareTo с реализацией интерфейса Comparable для сравнения двух дробей.
     * Сравнения необходимо реализовать для типов Fraction
     */
    @Override
    public int compareTo(Fraction o) {
        return Integer.compare(this.a * o.b, o.a * this.b);
    }

    /**
     * Определите операции сложения, вычитания, умножения так, чтобы можно было складывать:
     * Две дроби (результатом является Fraction)
     */
    public Fraction sum(Fraction other){
        return new Fraction(this.a * other.b + this.b * other.a, this.b * other.b);
    }
    public Fraction subtraction(Fraction other){
        return new Fraction(this.a * other.b - this.b * other.a, this.b * other.b);
    }
    public Fraction multiplication(Fraction other){
        return new Fraction(this.a * other.a * (this.b * other.b));
    }


    public Fraction sumInt(int value){
        return new Fraction(this.a + value * this.b, this.b);
    }
    public Fraction subtractionInt(int value){
        return new Fraction(this.a - value * this.b, this.b);
    }
    public Fraction multiplicationInt(int value){
        return new Fraction(this.a * value, this.b );
    }

    public double sumDouble(double value){
        return (double) this.a / this.b + value;
    }
    public double subtractionDouble(double value){
        return  (double) this.a / this.b - value;
    }
    public double multiplicationDouble(double value){
        return  (double) this.a / this.b * value;
    }


    /**
     * Определить операцию получения обратной дроби
     */
    public Fraction reciprocalFraction(){
        return new Fraction(this.b, this.a);
    }

    /**
     * Реализуйте метод sum, принимающий на вход массив дробей и производящий их общее сложение
     */
    public Fraction sum(Fraction[] other){
        Fraction res = new Fraction();
        for (int i = 0; i < other.length; i++) {
            res = res.sum(other[i]);
        }
        return res;
    }

    /**
     * Реализуйте метод maxFraction, принимающий на вход массив дробей
     * и находящий максимальную дробь в данном массиве
     */
    public Fraction maxFraction(Fraction[] other){
        Fraction max = new Fraction();
        for (Fraction value : other) {
            if(value.compareTo(max) > 0 ){
                max = value;
            }
        }
        return max;
    }








}
