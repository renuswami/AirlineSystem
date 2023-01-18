package com.java.alrs.dao.impl;

import java.sql.*;
import java.util.*;

import com.java.alrs.dto.AdminDTO;
import com.java.alrs.dto.CustomerDTO;
import com.java.alrs.query.Query;

public class CustomerDaoImpl extends DB {

	public int registerCustomer(CustomerDTO customerDTO) throws SQLException {

		Connection con = getConnection();
		PreparedStatement psmt = con.prepareStatement(Query.REGISTER_USER);
		psmt.setString(1, customerDTO.getCustomerName());
		psmt.setString(2, customerDTO.getMobile());
		psmt.setString(3, customerDTO.getEmail());

		int i = psmt.executeUpdate();
		if (i >= 1) {
			return i;
		}
		return 0;
	}

	public CustomerDTO getCustomerbyId(int id) throws SQLException {

		List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();

		Connection con = getConnection();

		PreparedStatement stm = con.prepareStatement(Query.FLIGHTS_BY_FLIGHTID);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();

		while (rs.next()) {
			CustomerDTO dto = new CustomerDTO();
			dto.setId(rs.getInt(1));
			dto.setCustomerName(rs.getString(2));
			dto.setMobile(rs.getString(3));
			dto.setEmail(rs.getString(4));
			dto.setFlightId(rs.getInt(5));

			customerList.add(dto);
		}
		if (!customerList.isEmpty()) {
			return customerList.get(0);
		}
		return null;
	}

	public CustomerDTO getCustomerByEmail(CustomerDTO customerDTO) throws SQLException {
		List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();

		Connection con = getConnection();
		PreparedStatement stm = con.prepareStatement(Query.CUSTOMER_BY_EMAIL);
		stm.setString(1, customerDTO.getEmail());
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			CustomerDTO dto = new CustomerDTO();
			dto.setId(rs.getInt(1));
			dto.setCustomerName(rs.getString(2));
			dto.setMobile(rs.getString(3));
			dto.setEmail(rs.getString(4));
			dto.setPnrNo(rs.getString(5));
			dto.setFlightId(rs.getInt(6));

			customerList.add(dto);
		}
		if (!customerList.isEmpty()) {
			return customerList.get(0);
		}
		return null;
	}

	public List<CustomerDTO> viewCustomer() throws SQLException {

		List<CustomerDTO> list = new ArrayList<CustomerDTO>();

		Connection con = getConnection();
		PreparedStatement stm = con.prepareStatement(Query.CUSTOMER_LIST);

		ResultSet rs = stm.executeQuery();
		while (rs.next()) {

			CustomerDTO dto = new CustomerDTO();
			dto.setId(rs.getInt(1));
			dto.setCustomerName(rs.getString(2));
			dto.setMobile(rs.getString(3));
			dto.setEmail(rs.getString(4));
			dto.setPnrNo(rs.getString(5));
			dto.setFlightId(rs.getInt(6));

			list.add(dto);
		}
		return list;
	}

	public int flightBooking(CustomerDTO customerDTO) throws SQLException {

		Connection con = getConnection();

		PreparedStatement psmt = con.prepareStatement(Query.FLIGHT_BOOKING);

		psmt.setString(1, customerDTO.getPnrNo());
		psmt.setInt(2, customerDTO.getFlightId());
		psmt.setString(3, customerDTO.getEmail());

		int n = psmt.executeUpdate();
		if (n > 0) {
			return n;
		}
		return 0;
	}

	public List<AdminDTO> searchFlight(AdminDTO adminDTO) throws SQLException {

		List<AdminDTO> airlineList = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement stm = con.prepareStatement(Query.SEARCH_FLIGHT);
		stm.setString(1, adminDTO.getDate());
		stm.setString(2, adminDTO.getSource());
		stm.setString(3, adminDTO.getDestination());
		ResultSet rs = stm.executeQuery();

		while (rs.next()) {
			AdminDTO dto = new AdminDTO();
			dto.setFlightId(rs.getInt(1));
			dto.setAirlineName(rs.getString(2));
			dto.setDate(rs.getString(3));
			dto.setSource(rs.getString(4));
			dto.setDestination(rs.getString(5));
			dto.setDepartureTime(rs.getString(6));
			dto.setArrivalTime(rs.getString(7));
			dto.setPrise(rs.getString(8));

			airlineList.add(dto);
		}
		con.close();
		return airlineList;
	}

	public CustomerDTO getCustomerDetailByPnrNo(String pnrNo) throws SQLException {
		List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();

		Connection con = getConnection();
		PreparedStatement stm = con.prepareStatement(Query.CUSTOMER_DETAIL_BY_PNRNO);
		stm.setString(1, pnrNo);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			CustomerDTO dto = new CustomerDTO();
			dto.setId(rs.getInt(1));
			dto.setCustomerName(rs.getString(2));
			dto.setMobile(rs.getString(3));
			dto.setEmail(rs.getString(4));
			dto.setPnrNo(rs.getString(5));
			dto.setFlightId(rs.getInt(6));

			customerList.add(dto);
			 if (!customerList.isEmpty()) {
			 	  return customerList.get(0);
			   }
	} 
	return null;
	}
	
	public int cancleFlight(String pnrNo) throws SQLException {
		Connection con = getConnection();

		PreparedStatement psmt = con.prepareStatement(Query.CANCLE_TICKET);
		psmt.setString(1, pnrNo);
		
		int n = psmt.executeUpdate();
		if (n > 0) {
			return n;
		}
		return 0;
	}
	
	public int cancleFlightRequest(String pnrNo) throws SQLException {

		Connection con = getConnection();

		PreparedStatement psmt = con.prepareStatement(Query.CANCLE_REQUEST);
		
		psmt.setString(1, pnrNo);

		int n = psmt.executeUpdate();
		if (n > 0) {
			return n;
		}
		return 0;
	}
	
	public int flightRescheduleRequest(CustomerDTO customerDTO) throws SQLException {

		Connection con = getConnection();

		PreparedStatement psmt = con.prepareStatement(Query.RESCHEDULE_REQUEST);
		
		psmt.setString(1, customerDTO.getPnrNo());

		int n = psmt.executeUpdate();
		if (n > 0) {
			return n;
		}
		return 0;
	}
	
	public List<CustomerDTO> viewCancelRequests() throws SQLException {

		List<CustomerDTO> list = new ArrayList<CustomerDTO>();

		Connection con = getConnection();
		PreparedStatement stm = con.prepareStatement(Query.CANCLE_REQUESTS_LIST);

		ResultSet rs = stm.executeQuery();
		while (rs.next()) {

			CustomerDTO dto = new CustomerDTO();
			dto.setId(rs.getInt(1));
			dto.setCustomerName(rs.getString(2));
			dto.setMobile(rs.getString(3));
			dto.setEmail(rs.getString(4));
			dto.setPnrNo(rs.getString(5));
			dto.setFlightId(rs.getInt(6));

			list.add(dto);
		}
		return list;
	}
	
	public List<CustomerDTO> flightRescheduleRequest() throws SQLException {

		List<CustomerDTO> list = new ArrayList<CustomerDTO>();

		Connection con = getConnection();
		PreparedStatement stm = con.prepareStatement(Query.RESCHEDULE_REQUESTS_LIST);

		ResultSet rs = stm.executeQuery();
		while (rs.next()) {

			CustomerDTO dto = new CustomerDTO();
			dto.setId(rs.getInt(1));
			dto.setCustomerName(rs.getString(2));
			dto.setMobile(rs.getString(3));
			dto.setEmail(rs.getString(4));
			dto.setPnrNo(rs.getString(5));
			dto.setFlightId(rs.getInt(6));

			list.add(dto);
		}
		return list;
	}
	
	public int rescheduleFlight(CustomerDTO customerDTO) throws SQLException {
		Connection con = getConnection();

		PreparedStatement psmt = con.prepareStatement(Query.RESCHEDULE_TICKET);
		psmt.setString(1, customerDTO.getDate());
		psmt.setString(2, customerDTO.getPnrNo());
		
		int n = psmt.executeUpdate();
		if (n > 0) {
			return n;
		}
		return 0;
	}
}
