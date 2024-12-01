package org.example;


public class Account {

    public String name;
    public String Address;
    public double balance;
}

interface IAccount {

    double calculateInterest(Account account);

}
