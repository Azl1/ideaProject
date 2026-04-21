package com.abdullaevaziz.uril;

public class Participant implements Comparable<Participant>{

    private String fio;
    private int ball;

    public Participant() {
    }

    public Participant(String fio, int ball) {
        this.fio = fio;
        this.ball = ball;
    }

    public Participant(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    @Override
    public String toString() {
        return fio;
    }

    @Override
    public int compareTo(Participant o) {
        return -Integer.compare(this.ball, o.ball);
    }
}
