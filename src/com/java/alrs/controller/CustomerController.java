package com.java.alrs.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.java.alrs.dto.AdminDTO;
import com.java.alrs.dto.CustomerDTO;
import com.java.alrs.dto.PaymentDTO;
import com.java.alrs.service.impl.AdminServiceImpl;
import com.java.alrs.service.impl.CustomerServiceImpl;
import com.java.alrs.service.impl.PaymentServiceImpl;

public class CustomerController {

	Scanner sc = new Scanner(System.in);
	CustomerDTO customerDTO = new CustomerDTO();
	CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
	AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
	AdminDTO adminDTO = new AdminDTO();
	PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl();
	PaymentDTO paymentDTO = new PaymentDTO();

	public void callCustomerPanal() throws SQLException {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" Press 1 : Register Customer \n Press 2 : Search Flight \n Press 3 : Flight Booking \n "
				+ "Press 4 : Issue ticket with PNR details  \n Press 5 : Request cancel ticket \n"
				+ " Press 6 : Request for flight reschedule \n Press 7 : View flights \n!!Press any other number for ExiT!! \n ");

		while (!sc.hasNextInt()) {
			System.out.println("Please enter currect number !!");
			sc.next();
		}
		int n = sc.nextInt();
		switch (n) {
		case 1:
			System.out.print("Add customer name : ");
			sc.nextLine();
			String customerName = sc.nextLine();
			if (customerName.matches("[a-zA-Z][a-zA-Z ]*")) {
				customerDTO.setCustomerName(customerName);

				System.out.print("mobile number : ");
				String number = sc.nextLine();
				if (number.matches("(0/91)?[7-9][0-9]{9}")) {
					customerDTO.setMobile(number);

					System.out.print("email id : ");
					String email = sc.nextLine();
					if (email.matches("^(.+)@(.+)$")) {
						customerDTO.setEmail(email);

						customerServiceImpl.registerCustomer(customerDTO);
					} else {
						System.out.println("Enter currect email id !!");
					}
				} else {
					System.out.println("Entered mobile number is invalid !!");
				}
			} else {
				System.out.println("Entered name is invalid !!");
			}
			callCustomerPanal();
			break;
		case 2:
			System.out.print("Date please : ");
			SimpleDateFormat sdfrmt = new SimpleDateFormat("DD/MM/yyyy");
			sc.nextLine();
			String date = sc.nextLine();
			try {
				sdfrmt.parse(date);
				adminDTO.setDate(date);
				System.out.print("Source :");
				String source = sc.nextLine();
				if (source.matches("[a-zA-Z][a-zA-Z ]*")) {
					adminDTO.setSource(source);

					System.out.print("Destination :");
					String destination = sc.nextLine();
					if (destination.matches("[a-zA-Z][a-zA-Z ]*")) {
						adminDTO.setDestination(destination);

						adminServiceImpl.searchFlight(adminDTO);
					} else {
						System.out.println("Invalid destination name please insert currect destination name !!");
					}
				} else {
					System.out.println("Invalid source name please insert currect source name !!");
				}
			} catch (ParseException e) {
				System.out.println("Invalid date format !!");
			}
			callCustomerPanal();
			break;
		case 3:
			customerServiceImpl.flightBooking();
			break;
		case 4:
			System.out.println("Please enter pnr number for ticket & boarding pass");
			sc.nextLine();
			customerDTO.setPnrNo(sc.nextLine());
			customerServiceImpl.getCustomerDetailByPnrNo(customerDTO);
			callCustomerPanal();
			break;
		case 5:
			System.out.print("Enter pnrNo :");
			sc.nextLine();
			customerDTO.setPnrNo(sc.nextLine());
			customerServiceImpl.cancleFlightRequest(customerDTO);
			break;
		case 6:
			System.out.print("Enter pnrNo :");
			sc.nextLine();
			customerDTO.setPnrNo(sc.nextLine());
			customerServiceImpl.flightRescheduleRequest(customerDTO);
			break;
		case 7:
			adminServiceImpl.viewFlightDetails();
			break;
		default:
			exit();
			break;
		}
	}

	private void exit() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("ThanK YoU FoR UsinG OnlinE LibrarY SysteM :)");
	}
}
