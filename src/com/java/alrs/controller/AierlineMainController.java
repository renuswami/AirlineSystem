package com.java.alrs.controller;

import java.sql.SQLException;
import java.util.Scanner;

public class AierlineMainController {

	Scanner sc = new Scanner(System.in);
	AdminController adminController = new AdminController();
	CustomerController customerController = new CustomerController();

	public void call() throws SQLException {

		System.out.println("Enter 1 : ADMIN-PANAL & 2 : CUSTOMER-PANAL :)");
		
		while (!sc.hasNextInt()) {
			System.out.println("Please enter currect number !!");
			sc.next();
		}
		int n = sc.nextInt();
		switch (n) {
		case 1:
			adminController.callAdminPanal();
			break;
		case 2:
			customerController.callCustomerPanal();
			break;
		default:
			System.out.println("Please Choose ADMIN-PANAL or CUSTOMER-PANAL!!");
			call();
			break;
		}
	}

	public static void main(String[] args) throws SQLException {
		AierlineMainController aierlineMainController = new AierlineMainController();
		aierlineMainController.call();
	}
}
