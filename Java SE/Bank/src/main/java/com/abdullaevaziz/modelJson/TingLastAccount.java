
package com.abdullaevaziz.modelJson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "numberAccount",
    "balance",
    "fio"
})
public class TingLastAccount {

    @JsonProperty("numberAccount")
    private String numberAccount;
    @JsonProperty("balance")
    private int balance;
    @JsonProperty("fio")
    private String fio;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TingLastAccount() {
    }

    /**
     * 
     * @param balance
     * @param numberAccount
     * @param fio
     */
    public TingLastAccount(String numberAccount, int balance, String fio) {
        super();
        this.numberAccount = numberAccount;
        this.balance = balance;
        this.fio = fio;
    }

    @JsonProperty("numberAccount")
    public String getNumberAccount() {
        return numberAccount;
    }

    @JsonProperty("numberAccount")
    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    @JsonProperty("balance")
    public int getBalance() {
        return balance;
    }

    @JsonProperty("balance")
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @JsonProperty("fio")
    public String getFio() {
        return fio;
    }

    @JsonProperty("fio")
    public void setFio(String fio) {
        this.fio = fio;
    }

}
