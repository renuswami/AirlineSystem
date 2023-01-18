package com.java.alrs.service.impl;

import java.sql.SQLException;
import java.util.List;
import com.java.alrs.dao.impl.AdminDaoImpl;
import com.java.alrs.dto.AdminDTO;

public class AdminServiceImpl {

	AdminDaoImpl adminDaoImpl = new AdminDaoImpl();

	public void viewFlightDetails() throws SQLException {

		List<AdminDTO> list = adminDaoImpl.viewFlightDetails();

		System.out.println(
				"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("FlihghtId" + "  " + "AirlineName" + "  " + "Date" + "  " + "Source" + "  " + "destination"
				+ "  " + "DepartureTime" + "  " + "ArrivalTime" + "  " + "Price");
		System.out.println(
				"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (AdminDTO adminDTO : list) {
			System.out.println(
					"--------------------------------------------------------------------------------------------------------");
			System.out.println("    " + adminDTO.getFlightId() + "   " + adminDTO.getAirlineName() + "   "
					+ adminDTO.getDate() + "	 " + adminDTO.getSource() + "	 " + adminDTO.getDestination() + "    "
					+ adminDTO.getDepartureTime() + "	  " + adminDTO.getArrivalTime() + " 	"
					+ adminDTO.getPrise());
		}
	}

	public void saveFlight(AdminDTO adminDTO) throws SQLException {
		AdminDTO airlineId = adminDaoImpl.getFlightByFlightId(adminDTO.getFlightId());
		if (airlineId == null) {
			int isIsert = adminDaoImpl.saveFlight(adminDTO);
			if (isIsert > 0) {
				System.out.println("Flight add seccussfully....");
			} else {
				System.out.println("Error in add flight!!");
			}
		} else {
			System.out.println("Flight Already ADDED !!");
		}
	}

	public void deleteFlight(AdminDTO adminDTO) throws SQLException {
		AdminDTO flightId = adminDaoImpl.getFlightByFlightId(adminDTO.getFlightId());
		if (flightId != null) {
			int isDelete = adminDaoImpl.deleteFlight(adminDTO);
			if (isDelete > 0) {
				System.out.println("Flight delete Seccussfully...");
			} else {
				System.out.println("Error in delete!!");
			}
		} else {
			System.out.println("FlightId not exist!!");
		}
	}

	public void searchFlight(AdminDTO aDTO) throws SQLException {

		List<AdminDTO> list = adminDaoImpl.searchFlight(aDTO);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Source" + "  " + "destination" + "  " + "Date" + "  " + "DepartureTime" + "  "
				+ "ArrivalTime" + "  " + "Price");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (AdminDTO adminDTO : list) {
			System.out.println(adminDTO.getSource() + "	 " + adminDTO.getDestination() + "	 " + adminDTO.getDate()
					+ "	 " + adminDTO.getDepartureTime() + " 	" + adminDTO.getArrivalTime() + " 	"
					+ adminDTO.getPrise());
		}
	}

}
