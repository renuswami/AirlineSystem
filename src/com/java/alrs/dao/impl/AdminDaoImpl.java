package com.java.alrs.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.java.alrs.dto.AdminDTO;
import com.java.alrs.query.*;

public class AdminDaoImpl extends DB {

	public List<AdminDTO> viewFlightDetails() throws SQLException {

		List<AdminDTO> list = new ArrayList<AdminDTO>();
		Connection con = getConnection();
		PreparedStatement stm = con.prepareStatement(Query.FLIGHT_LIST);
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

			list.add(dto);
		}
		con.close();
		return list;
	}

	public int saveFlight(AdminDTO adminDTO) throws SQLException {

		Connection con = getConnection();
		PreparedStatement psmt = con.prepareStatement(Query.ADD_FLIGHT_DETAIL);
		psmt.setString(1, adminDTO.getAirlineName());
		psmt.setString(2, adminDTO.getDate());
		psmt.setString(3, adminDTO.getSource());
		psmt.setString(4, adminDTO.getDestination());
		psmt.setString(5, adminDTO.getDepartureTime());
		psmt.setString(6, adminDTO.getArrivalTime());
		psmt.setString(7, adminDTO.getPrise());

		int i = psmt.executeUpdate();
		if (i >= 1) {
			return i;
		}

		return 0;
	}

	public int deleteFlight(AdminDTO adminDTO) throws SQLException {
		Connection con = getConnection();
		PreparedStatement stm = con.prepareStatement(Query.DELETE_FLIGHT);
		stm.setInt(1, adminDTO.getFlightId());
		int i = stm.executeUpdate();
		if (i >= 1) {
			return i;
		}
		return 0;
	}

	public AdminDTO getFlightByFlightId(int flightId) throws SQLException {

		List<AdminDTO> airlineList = new ArrayList<AdminDTO>();

		Connection con = getConnection();

		PreparedStatement stm = con.prepareStatement(Query.FLIGHTS_BY_FLIGHTID);
		stm.setInt(1, flightId);
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
		if (!airlineList.isEmpty()) {
			return airlineList.get(0);
		}

		return null;
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
}
