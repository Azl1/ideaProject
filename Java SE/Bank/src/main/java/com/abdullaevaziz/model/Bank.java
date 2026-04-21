package com.abdullaevaziz.model;

import com.abdullaevaziz.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.Objects;

public class Bank {
    private int kod;
    private AccountList accountList = new AccountList();

    public Bank() {
    }

    public AccountList getAccountList() {
        return accountList;
    }

    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    public Bank(int kod) {
        this.kod = kod;
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }

    public Account getAccount(String kodAccount) {
        return accountList.getAccountByNumber(kodAccount);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return kod == bank.kod && Objects.equals(accountList, bank.accountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kod, accountList);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "kod=" + kod +
                ", accountList=" + accountList +
                '}';
    }

    /**
     * • Создание нового аккаунта
     */
    public Account createNewAccount(String numberAccount, String FIO, BigDecimal balance) {
        return new Account(numberAccount, FIO, balance);
    }

    /**
     * • Добавление аккаунта в банк
     */
    public boolean addAccountInBank(Account account) {
        return this.accountList.userAdd(account);
    }

    /**
     * • Удаление аккаунта из банка
     */
    public boolean removeAccountInBank(Account account) {
        return this.accountList.removeAccount(account);
    }

    /**
     * • Проверки на существование аккаунта в банке
     */
    public boolean existenceAccount(Account account) {
        return this.accountList.isExistenceAccountList(account);
    }

    /**
     * • Внутрибанковского перевода сумм между счетами
     */
    public Account intraBankTransfer(String fromAccountNumber,
                                     String toAccountNumber,
                                     BigDecimal balance) throws InsufficientFundsException {
        Account fromAccount = accountList.getAccountByNumber(fromAccountNumber);
        Account toAccount = accountList.getAccountByNumber(toAccountNumber);
        if (fromAccount == null || toAccount == null) {
            throw new InsufficientFundsException("Один из счетов не найден.");
        }
        fromAccount.transfer(toAccount, balance);
        return fromAccount;
    }


}
