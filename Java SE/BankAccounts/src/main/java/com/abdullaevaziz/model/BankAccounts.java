package com.abdullaevaziz.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Класс счет в банке содержит поля:
 * номер счета,
 * код счета,
 * фамилия владельца,
 * сумма на счете,
 * дата открытия счета (тип данных Date),
 * годовой процент начисления
 */
public class BankAccounts implements Comparable<BankAccounts>{
    private int numberAccounts;
    private int kodAccounts;
    private String fio;
    private int accounts;
    private Date dataDay;
    private int annualInterestAccrual;

    public BankAccounts(){
    }

    public BankAccounts(int numberAccounts, int kodAccounts, String fio, int accounts, Date dataDay, int annualInterestAccrual) {
        this.numberAccounts = numberAccounts;
        this.kodAccounts = kodAccounts;
        this.fio = fio;
        this.accounts = accounts;
        this.dataDay = dataDay;
        this.annualInterestAccrual = annualInterestAccrual;
    }

    public int getNumberAccounts() {
        return numberAccounts;
    }

    public void setNumberAccounts(int numberAccounts) {
        this.numberAccounts = numberAccounts;
    }

    public int getKodAccounts() {
        return kodAccounts;
    }

    public void setKodAccounts(int kodAccounts) {
        this.kodAccounts = kodAccounts;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAccounts() {
        return accounts;
    }

    public void setAccounts(int accounts) {
        this.accounts = accounts;
    }

    public Date getDataDay() {
        return dataDay;
    }

    public void setDataDay(Date dataDay) {
        this.dataDay = dataDay;
    }

    public int getAnnualInterestAccrual() {
        return annualInterestAccrual;
    }

    public void setAnnualInterestAccrual(int annualInterestAccrual) {
        this.annualInterestAccrual = annualInterestAccrual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccounts that = (BankAccounts) o;
        return numberAccounts == that.numberAccounts && kodAccounts == that.kodAccounts && accounts == that.accounts && annualInterestAccrual == that.annualInterestAccrual && Objects.equals(fio, that.fio) && Objects.equals(dataDay, that.dataDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberAccounts, kodAccounts, fio, accounts, dataDay, annualInterestAccrual);
    }

    @Override
    public String toString() {
        return "BankAccounts{" +
                "numberAccounts=" + numberAccounts +
                ", kodAccounts=" + kodAccounts +
                ", fio='" + fio + '\'' +
                ", accounts=" + accounts +
                ", dataDay=" + dataDay +
                ", annualInterestAccrual=" + annualInterestAccrual +
                '}';
    }

    /**
     * Компаратор дата открытия счета
     * @param o the object to be compared.
     */

    @Override
    public int compareTo(BankAccounts o) {
        return this.dataDay.compareTo(o.dataDay);
    }
}
