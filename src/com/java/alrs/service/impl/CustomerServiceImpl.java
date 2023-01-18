package com.java.alrs.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.java.alrs.dao.impl.AdminDaoImpl;
import com.java.alrs.dao.impl.CustomerDaoImpl;
import com.java.alrs.dao.impl.PaymentDaoImpl;
import com.java.alrs.dto.AdminDTO;
import com.java.alrs.dto.CustomerDTO;
import com.java.alrs.dto.PaymentDTO;

public class CustomerServiceImpl {

	CustomerDTO customerDTO = new CustomerDTO();
	AdminDTO adminDTO = new AdminDTO();
	PaymentDTO paymentDTO = new PaymentDTO();

	CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	PaymentDaoImpl paymentDaoImpl = new PaymentDaoImpl();
	AdminDaoImpl adminDaoImpl = new AdminDaoImpl();

	PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl();
	AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
	Scanner sc = new Scanner(System.in);

	public void registerCustomer(CustomerDTO customerDTO) throws SQLException {
		CustomerDTO cusomerData = customerDaoImpl.getCustomerByEmail(customerDTO);
		if (cusomerData == null) {
			int isIsert = customerDaoImpl.registerCustomer(customerDTO);

			if (isIsert > 0) {
				System.out.println("Customer registred seccussfully....");
			} else {
				System.out.println("Error in registration !!");
			}
		} else {
			System.out.println("you already registered");
		}
	}

	public void viewCustomer() throws SQLException {

		List<CustomerDTO> customerList = customerDaoImpl.viewCustomer();

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" CustomerName  MobileNumer	Mail-Id     PNR-NO");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (CustomerDTO cDTO : customerList) {
			System.out.println("   " + cDTO.getCustomerName() + "    	  " + cDTO.getMobile() + " " + cDTO.getEmail()
					+ "   " + cDTO.getPnrNo());
		}
	}

	public void flightBooking() throws SQLException {

		System.out.print("email id : ");
		String email = sc.nextLine();
		if (email.matches("^(.+)@(.+)$"))
			customerDTO.setEmail(email);
		System.out.print("Flight Id : ");
		int flightId = sc.nextInt();
		customerDTO.setFlightId(flightId);
		adminDTO.setFlightId(flightId);
		int pnrNo = ThreadLocalRandom.current().nextInt();
		customerDTO.setPnrNo(pnrNo + "alr");

		CustomerDTO cusomerData = customerDaoImpl.getCustomerByEmail(customerDTO);

		if (cusomerData != null) {
			AdminDTO adminData = adminDaoImpl.getFlightByFlightId(adminDTO.getFlightId());
			if (adminData != null) {
				customerDaoImpl.flightBooking(customerDTO);
				CustomerDTO cusomerData1 = customerDaoImpl.getCustomerByEmail(customerDTO);

				System.out.println("Enter Amount :");
				sc.nextLine();
				paymentDTO.setPayment(sc.nextLine());
				paymentDTO.setPnrNo(cusomerData1.getPnrNo());
				if (adminData.getPrise().equals(paymentDTO.getPayment())) {

					int isIsert = paymentDaoImpl.payment(paymentDTO);
					if (isIsert > 0) {
						System.out.println("Payment seccussfully..");
						System.out.println("ticket booked");
						System.out.println("PNR-No :" + customerDTO.getPnrNo());
					} else {
						System.out.println("Error in payment!!");
					}
				} else {
					System.out.println("The payment amount not match to ticket amount ");
				}
			} else {
				System.out.println("enter currect flight id !!");
			}
		} else {
			System.out.println("please first registration !!");
		}
	}

	public void getCustomerDetailByPnrNo(CustomerDTO customerDTO) throws SQLException {
		CustomerDTO customerList = customerDaoImpl.getCustomerDetailByPnrNo(customerDTO.getPnrNo());
		if (customerList != null) {
			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("|		AIR TICKET				|	BORDING  PASS   	|");
			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			AdminDTO adminData = adminDaoImpl.getFlightByFlightId(customerList.getFlightId());

			if (adminData != null) {
				System.out.println("| Name of passanger		  Date 			|" + " NAME :"
						+ customerList.getCustomerName() + "			|");
				System.out.println("|							|" + " Source :" + adminData.getSource() + "		|");
				System.out.println("|	" + customerList.getCustomerName() + "			" + adminData.getDate()
						+ "		|" + " Destination :" + adminData.getDestination() + "		|");
				System.out.println("|							|" + " Date :" + adminData.getDate() + "		|");
				System.out.println("|  Class			seat-No		 Time	|" + " Time :"
						+ adminData.getDepartureTime() + "		|");
				System.out.println("|							|" + " Seat : 30 			|");
				System.out.println("|First class	   	 	 5" + "		" + adminData.getDepartureTime() + "|"
						+ " Flight :" + adminData.getAirlineName() + "		|");
				System.out.println("|							|" + " Age : 27 			|");
				System.out.println("|Source		Destination		Flight		|" + " Runway : A 			|");
				System.out.println("|							|				|");
				System.out.println("|" + adminData.getSource() + "		 " + adminData.getDestination() + "			"
						+ adminData.getAirlineName() + "		|				|");
			}

			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		} else {
			System.out.println("please enter currect pnrno!!");
		}
	}

	public void cancleFlight() throws SQLException {

		System.out.println("Enter pnrNo for approve cancel requests...");
		customerDTO.setPnrNo(sc.nextLine());

		CustomerDTO customerList = customerDaoImpl.getCustomerDetailByPnrNo(customerDTO.getPnrNo());
		if (customerList != null) {
			int isCancle = customerDaoImpl.cancleFlight(customerDTO.getPnrNo());
			if (isCancle > 0) {
				System.out.println(
						"Your ticket cancel..\n payment refund in your account after sometime \n Thank You :)");

				PaymentDTO paymentList = paymentServiceImpl.getPaymentDetailByPnrNo(customerList.getPnrNo());
				paymentServiceImpl.refund(paymentList);
			} else {
				System.out.println("Error in payment!!");
			}
		} else {
			System.out.println("pnr number is incurrect!!");
		}
	}

	public void cancleFlightRequest(CustomerDTO customerDTO) throws SQLException {

		CustomerDTO customerList = customerDaoImpl.getCustomerDetailByPnrNo(customerDTO.getPnrNo());
		if (customerList != null) {
			int isCancle = customerDaoImpl.cancleFlightRequest(customerDTO.getPnrNo());
			if (isCancle > 0) {
				System.out.println("Your cancellation request sent..");

			} else {
				System.out.println("Error in cancel request !!");
			}
		} else {
			System.out.println("pnr number is incurrect!!");
		}
	}

	public void viewCancelRequests() throws SQLException {

		List<CustomerDTO> customerList = customerDaoImpl.viewCancelRequests();

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" CustomerName  MobileNumer	Mail-Id     PNR-NO");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (CustomerDTO cDTO : customerList) {
			System.out.println("   " + cDTO.getCustomerName() + "    	  " + cDTO.getMobile() + " " + cDTO.getEmail()
					+ "   " + cDTO.getPnrNo());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		if (customerList != null) {
			cancleFlight();
		}
	}

	public void flightRescheduleRequest(CustomerDTO customerDTO) throws SQLException {

		CustomerDTO customerList = customerDaoImpl.getCustomerDetailByPnrNo(customerDTO.getPnrNo());
		if (customerList != null) {
			int isCancle = customerDaoImpl.flightRescheduleRequest(customerDTO);
			if (isCancle > 0) {
				System.out.println("Your rescheule request sent..");
			} else {
				System.out.println("Error in request !!");
			}
		} else {
			System.out.println("pnr number is incurrect!!");
		}
	}

	public void viewRescheduleRequests() throws SQLException {

		List<CustomerDTO> customerList = customerDaoImpl.flightRescheduleRequest();

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" CustomerName	  MobileNumer	  Mail-Id	 PNR-NO  ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (CustomerDTO cDTO : customerList) {
			System.out.println("   " + cDTO.getCustomerName() + "    	  " + cDTO.getMobile() + "	  "
					+ cDTO.getEmail() + "   " + cDTO.getPnrNo());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		if (customerList != null) {
			System.out.print("Pnr no : ");
			customerDTO.setPnrNo(sc.nextLine());
			rescheduleFlight(customerDTO);
		}
	}

	public void rescheduleFlight(CustomerDTO customerDTO) throws SQLException {

		CustomerDTO customerList = customerDaoImpl.getCustomerDetailByPnrNo(customerDTO.getPnrNo());
		if (customerList != null) {
			
			
			int isCancle = customerDaoImpl.cancleFlight(customerDTO.getPnrNo());
			if (isCancle > 0) {
				System.out.println("Your ticket cancel..\n payment refund in your account after sometime \n Thank You :)");

				PaymentDTO paymentList = paymentServiceImpl.getPaymentDetailByPnrNo(customerList.getPnrNo());
				paymentServiceImpl.refund(paymentList);
				flightBooking();
			} else {
				System.out.println("Error in payment!!");
			}
		} else {
			System.out.println("pnr number is incurrect!!");
		}
	}

}
