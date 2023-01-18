package com.java.alrs.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.java.alrs.dto.AdminDTO;
import com.java.alrs.dto.CustomerDTO;
import com.java.alrs.service.impl.AdminServiceImpl;
import com.java.alrs.service.impl.CustomerServiceImpl;

/**
 * 
 * @author Renu Swami(ax1009)
 *
 */

public class AdminController {

	AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
	CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
	AdminDTO adminDTO = new AdminDTO();
	CustomerDTO customerDTO = new CustomerDTO();
	Scanner sc = new Scanner(System.in);

	public void callAdminPanal() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" Press 1 :  View Flight Detail... \n Press 2 : Add Flight... \n"
				+ " Press 3 : Delete Flight...\n Press 4 : View Customer Detail \n Press 5 : Requests for ticket cancellation \n"
				+ " Press 6 : Requests for ticket reschedule \n !!Press Any Number For Exit!! ");

		while (!sc.hasNextInt()) {
			System.out.println("Please enter currect number !!");
			sc.next();
		}
		int n = sc.nextInt();
		switch (n) {
		case 1:
			adminServiceImpl.viewFlightDetails();
			callAdminPanal();
			break;
		case 2:
			System.out.print("Flight Name :");
			sc.nextLine();
			String name = sc.nextLine();
			if (name.matches("[a-zA-Z][a-zA-Z ]*")) {
				adminDTO.setAirlineName(name);

				SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
				System.out.print("Date :");

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

							System.out.print("Departure Time :");
							String DepartureTime = sc.nextLine();
							if (DepartureTime.matches("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)")) {
								adminDTO.setDepartureTime(DepartureTime);

								System.out.print("Arrival Time :");
								String arrivalTime = sc.nextLine();
								if (arrivalTime.matches("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)")) {
									adminDTO.setArrivalTime(arrivalTime);
									System.out.print("Tickit Prise :");
									String prise = sc.nextLine();
									if (prise.matches("[0-9]+")) {

										adminDTO.setPrise(prise);

										adminServiceImpl.saveFlight(adminDTO);
									} else {
										System.out.println("please enter numeric value !!");
									}
								} else {
									System.out.println("Invalid time formet !!");
								}
							} else {
								System.out.println("Invalid time formet !!");
							}
						} else {
							System.out.println("Invalid destination name please insert currect destination name !!");
						}
					} else {
						System.out.println("Invalid source name please insert currect source name !!");
					}
				} catch (ParseException e) {
					System.out.println("Invalid date format !!");
				}

			} else {
				System.out.println("Invalid name please insert currect Name !!");
			}

			callAdminPanal();
			break;
		case 3:
			System.out.println("Enter flightid for This Flight Detail !!");
			adminDTO.setFlightId(sc.nextInt());
			adminServiceImpl.deleteFlight(adminDTO);
			callAdminPanal();
			break;
		case 4:
			customerServiceImpl.viewCustomer();
			callAdminPanal();
			break;
		case 5:
			System.out.println("Ticket cancel list");
			customerServiceImpl.viewCancelRequests();
			break;
		case 6:
			System.out.println("Ticket reschedule list");
			customerServiceImpl.viewRescheduleRequests();
			
			break;
		default:
			exit();
			break;
		}
	}
		private void exit() {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Thank you for using airline system :)");
		}
}
