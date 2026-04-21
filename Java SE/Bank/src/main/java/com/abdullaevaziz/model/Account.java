package com.abdullaevaziz.model;

import com.abdullaevaziz.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    /**
     * • Номер аккаунта
     * • ФИО собственника
     * • Баланс
     */
    private String numberAccount;
    private String FIO;
    private BigDecimal balance;

    public Account() {
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account(String numberAccount, String FIO, BigDecimal balance) {
        this.numberAccount = numberAccount;
        this.FIO = FIO;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(numberAccount, account.numberAccount) && Objects.equals(FIO, account.FIO) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberAccount, FIO, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "numberAccount='" + numberAccount + '\'' +
                ", FIO='" + FIO + '\'' +
                ", balance=" + balance +
                '}';
    }

    /**
     * • Увеличение баланса аккаунта на заданную величину
     * • Уменьшение баланса аккаунта на заданную величину
     * • Перевода суммы другому лицу
     */
    public BigDecimal balanceIncrease(BigDecimal balance) {
        this.balance = this.balance.add(balance);
        return this.balance;
    }

    public BigDecimal decreaseBalance(BigDecimal balance) throws InsufficientFundsException {
        if (getBalance().compareTo(balance) < 0) {
            throw new InsufficientFundsException("Недостаточно средств на счете");
        }
        this.balance = this.balance.subtract(balance);
        return this.balance;
    }

    public void transfer(Account account, BigDecimal sum) throws InsufficientFundsException {
        if (this.getBalance().compareTo(sum) < 0) {
            throw new InsufficientFundsException("Недостаточно средств на счете для перевода");
        }
        this.decreaseBalance(sum);
        account.balanceIncrease(sum);
    }
}
