/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.dash.DataModels;

/**
 *
 * @author asirw
 */
public class AccountsModel {
    private String cardNumber;
    private String accountType;
    private String fname;
    private String lname;
    private double balance;
    

    public AccountsModel(String cardNumber, String accountType, String fname, String lname, double balance){
        this.cardNumber = cardNumber;
        this.accountType = accountType;
        this.fname = fname;
        this.lname = lname;
        this.balance = balance;
        
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }
    
    public String getLName() {
        return lname;
    }

    public void setlName(String lname) {
        this.lname = lname;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
