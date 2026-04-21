package com.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Objects;

public class AccountList {
    /**
     * • Список аккаунтов (пользователей)
     */
    private ArrayList<Account> accountArrayList = new ArrayList<>();

    public AccountList() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountList that = (AccountList) o;
        return Objects.equals(accountArrayList, that.accountArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountArrayList);
    }

    @Override
    public String toString() {
        return "AccountList{" +
                accountArrayList +
                '}';
    }

    public ArrayList<Account> getAccountArrayList() {
        return accountArrayList;
    }

    public void setAccountArrayList(ArrayList<Account> accountArrayList) {
        this.accountArrayList = accountArrayList;
    }

    /**
     * • Добавление пользователя в список аккаунтов
     */
    public boolean userAdd(Account account) {
        return this.accountArrayList.add(account);
    }

    /**
     *• Удаление аккаунта из списка аккаунтов
     */
    public boolean removeAccount(Account account) {
        return this.accountArrayList.remove(account);
    }

    /**
     * • Получение первого аккаунта
     */
    @JsonIgnore
    public Account gettingFirstAccount() {
        return this.accountArrayList.get(0);
    }

    /**
     * • Получение последнего аккаунта
     */
    @JsonIgnore
    public Account gettingLastAccount() {
        if (!(accountArrayList.isEmpty())) {
            return this.accountArrayList.get(accountArrayList.size() - 1);
        }
        return null;
    }

    /**
     * • Получение аккаунта по индексу
     */
    public int indexAccount(Account account) {
        return this.accountArrayList.indexOf(account);
    }

    /**
     * • Проверки на существование аккаунта в списке
     */
    public boolean isExistenceAccountList(Account account) {
        return this.accountArrayList.contains(account);
    }

    /**
     * • Получение количества аккаунтов
     */
    public int size() {
        return this.accountArrayList.size();
    }

    /**
     * • Получение аккаунта по его номеру
     */
    public Account getAccountByNumber(String accountNumber) {
        return accountArrayList.stream()
                .filter(account -> account.getNumberAccount().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

}
