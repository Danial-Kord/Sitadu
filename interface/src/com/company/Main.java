package com.company;

public class Main {

    public static void main(String[] args) {
	DBConnection.connect();
	Account account = new Account();
	Address address = new Address();
//	address.addToDataBase();
	account.setUser("danialMain");
	account.setPass("dadada");
//	account.setFirst_name("dani");
//	account.setLast_name("kord");
//	account.setMelli_code("26133");
//	account.signUp();
		account.logIn();
		Factor factor = new Factor();
		factor.addNewFactorNoName();
    }
}
