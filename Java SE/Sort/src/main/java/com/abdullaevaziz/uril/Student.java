package com.abdullaevaziz.uril;

public class Student implements Comparable<Student> {

    private String fio;
    private int form;

    public Student() {
    }

    public Student(String fio, int form) {
        this.fio = fio;
        this.form = form;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    @Override
    public String toString() {
        return form + " " + fio;
    }

    @Override
    public int compareTo(Student o) {
        if (this.form == o.form) {
            return this.fio.compareTo(o.fio);
        }
        return Integer.compare(this.form, o.form);
    }
}
