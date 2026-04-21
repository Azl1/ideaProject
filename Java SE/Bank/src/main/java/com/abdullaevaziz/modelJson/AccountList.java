
package com.abdullaevaziz.modelJson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tingFirstAccount",
    "tingLastAccount",
    "sizeOfAccounts"
})
public class AccountList {

    @JsonProperty("tingFirstAccount")
    private TingFirstAccount tingFirstAccount;
    @JsonProperty("tingLastAccount")
    private TingLastAccount tingLastAccount;
    @JsonProperty("sizeOfAccounts")
    private int sizeOfAccounts;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccountList() {
    }

    /**
     * 
     * @param sizeOfAccounts
     * @param tingFirstAccount
     * @param tingLastAccount
     */
    public AccountList(TingFirstAccount tingFirstAccount, TingLastAccount tingLastAccount, int sizeOfAccounts) {
        super();
        this.tingFirstAccount = tingFirstAccount;
        this.tingLastAccount = tingLastAccount;
        this.sizeOfAccounts = sizeOfAccounts;
    }

    @JsonProperty("tingFirstAccount")
    public TingFirstAccount getTingFirstAccount() {
        return tingFirstAccount;
    }

    @JsonProperty("tingFirstAccount")
    public void setTingFirstAccount(TingFirstAccount tingFirstAccount) {
        this.tingFirstAccount = tingFirstAccount;
    }

    @JsonProperty("tingLastAccount")
    public TingLastAccount getTingLastAccount() {
        return tingLastAccount;
    }

    @JsonProperty("tingLastAccount")
    public void setTingLastAccount(TingLastAccount tingLastAccount) {
        this.tingLastAccount = tingLastAccount;
    }

    @JsonProperty("sizeOfAccounts")
    public int getSizeOfAccounts() {
        return sizeOfAccounts;
    }

    @JsonProperty("sizeOfAccounts")
    public void setSizeOfAccounts(int sizeOfAccounts) {
        this.sizeOfAccounts = sizeOfAccounts;
    }

}
