package com.java.alrs.service.impl;

import java.sql.SQLException;

import com.java.alrs.dao.impl.PaymentDaoImpl;
import com.java.alrs.dto.PaymentDTO;

public class PaymentServiceImpl {

	PaymentDaoImpl paymentDaoImpl = new PaymentDaoImpl();

	public void payment(PaymentDTO paymentDTO) throws SQLException {

		int isIsert = paymentDaoImpl.payment(paymentDTO);
		if (isIsert > 0) {
			System.out.println("Payment seccussfully..");
		} else {
			System.out.println("Error in payment!!");
		}

	}

	public void refund(PaymentDTO paymentDTO) throws SQLException {

		PaymentDTO payment = paymentDaoImpl.getPaymentDetailByPnrNo(paymentDTO.getPnrNo());
		if (payment != null) {
			String s = payment.getPayment();
			int amount=Integer.parseInt(s);  	
			amount = (amount*25)/100;
			String deductionAmount = String.valueOf(amount);
			paymentDTO.setPayment(deductionAmount);
			int isIssue = paymentDaoImpl.refund(paymentDTO);
			if (isIssue == 1) {
				System.out.println("your amout refunded after 25% deduction ");
			} else {
				System.out.println("Error in refund !!");
			}
		}
	}
	
	public PaymentDTO getPaymentDetailByPnrNo(String pnrNo) throws SQLException {
	
		
		PaymentDTO isIsert = paymentDaoImpl.getPaymentDetailByPnrNo(pnrNo);
		if (isIsert != null) {
			return isIsert;
		} else {
			System.out.println("Payment id not found!!");
		}
		return null;
	}
	
	public void extracharges(PaymentDTO paymentDTO) throws SQLException {

		PaymentDTO payment = paymentDaoImpl.getPaymentDetailByPnrNo(paymentDTO.getPnrNo());
		if (payment != null) {
			String s = payment.getPayment();
			int amount=Integer.parseInt(s);  	
			amount = (amount+200);
			String deductionAmount = String.valueOf(amount);
			paymentDTO.setPayment(deductionAmount);
			int isIssue = paymentDaoImpl.extracharges(paymentDTO);
			if (isIssue == 1) {
				System.out.println("extra charges 200... ");
			} else {
				System.out.println("Error in refund !!");
			}
		}
	}
}
