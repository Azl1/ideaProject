
package com.abdullaevaziz.modelJson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "kod",
    "accountList"
})
public class Bank {

    @JsonProperty("kod")
    private int kod;
    @JsonProperty("accountList")
    private AccountList accountList;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Bank() {
    }

    /**
     * 
     * @param kod
     * @param accountList
     */
    public Bank(int kod, AccountList accountList) {
        super();
        this.kod = kod;
        this.accountList = accountList;
    }

    @JsonProperty("kod")
    public int getKod() {
        return kod;
    }

    @JsonProperty("kod")
    public void setKod(int kod) {
        this.kod = kod;
    }

    @JsonProperty("accountList")
    public AccountList getAccountList() {
        return accountList;
    }

    @JsonProperty("accountList")
    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

}
