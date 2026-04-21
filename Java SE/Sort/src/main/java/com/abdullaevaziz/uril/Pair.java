package com.abdullaevaziz.uril;

public class Pair implements Comparable<Pair>{

    private int a;
    private int b;

    public Pair() {
    }

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
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

    @Override
    public String toString() {
        return "(" +
                 + a + ";" +
                " " + b +
                ')';
    }

    @Override
    public int compareTo(Pair o) {
        //TODO по задаче
        if(this.getA() == o.getA()){
            return Integer.compare(this.getB(), o.getB());
        }
        return Integer.compare(this.getA(), o.getA());
    }
}
