package com.java.alrs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.java.alrs.dto.PaymentDTO;
import com.java.alrs.query.Query;

public class PaymentDaoImpl extends DB {

	public int payment(PaymentDTO paymentDTO) throws SQLException {

		Connection con = getConnection();
		PreparedStatement psmt = con.prepareStatement(Query.PAYMENT);
		psmt.setString(1, paymentDTO.getPayment());
		psmt.setString(2, paymentDTO.getPnrNo());

		int i = psmt.executeUpdate();
		if (i >= 1) {
			return i;
		}
		return 0;
	}
	
	public int extracharges(PaymentDTO paymentDTO) throws SQLException {

		Connection con = getConnection();
		PreparedStatement psmt = con.prepareStatement(Query.REFUND_PAYMENT);
		psmt.setString(1, paymentDTO.getPayment());
		psmt.setString(2, paymentDTO.getPnrNo());

		int i = psmt.executeUpdate();
		if (i >= 1) {
			return i;
		}
		return 0;
	}
	
	public PaymentDTO getPaymentDetailByPnrNo(String pnrNo) throws SQLException {
		List<PaymentDTO> PaymentList = new ArrayList<PaymentDTO>();

		Connection con = getConnection();
		PreparedStatement stm = con.prepareStatement(Query.PAYMENT_DETAIL_BY_PNRNO);
		stm.setString(1, pnrNo);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			PaymentDTO dto = new PaymentDTO();
			dto.setId(rs.getInt(1));
			dto.setPayment(rs.getString(2));
			dto.setPnrNo(rs.getString(3));

			PaymentList.add(dto);
			 if (!PaymentList.isEmpty()) {
			 	  return PaymentList.get(0);
			   }
	} 
	return null;
	}
	
	public int refund(PaymentDTO paymentDTO) throws SQLException {

		Connection con = getConnection();
		PreparedStatement psmt = con.prepareStatement(Query.REFUND_PAYMENT);
		psmt.setString(1, paymentDTO.getPayment());
		psmt.setString(2, paymentDTO.getPnrNo());

		int i = psmt.executeUpdate();
		if (i >= 1) {
			return i;
		}
		return 0;
	}
}
