package com.java.alrs.query;

public class Query {

	public static final String FLIGHT_LIST = "select * from flight where isActive=1 ";

	
	public static final String ADD_FLIGHT_DETAIL = "insert into flight set flightName=?, date=?, source=?,"
													+ " destination=?, departureTime=?, arrivalTime=?, prise=?";

	
	public static final String FLIGHTS_BY_FLIGHTID = "select * from flight where isActive=1 and flightId=? ";

	
	public static final String DELETE_FLIGHT = "update flight set isActive = 0 where flightId = ? ";

	
	public static final String RESCHEDULE_FLIGHT = "update customer set isSchedule=0 where isActive = 1 and pnrNo = ? ";

	
	public static final String SEARCH_FLIGHT = "select * from flight where isActive=1 and date=? or source=? and destination=?";

	
	public static final String REGISTER_USER = "insert into customer set customerName=?, mobile=?, email=? ";

	
	public static final String CUSTOMER_LIST = "select * from customer where isActive=1 ";

	
	public static final String FLIGHT_BOOKING = "update customer set pnrNo=?, flightId=?,isSchedule=1 where isActive=1 and email=?";

	
	public static final String CUSTOMER_BY_EMAIL = "select * from customer where isActive=1 and email = ?";

	
	public static final String CUSTOMER_DETAIL_BY_PNRNO = "select * from customer where isActive=1 and pnrNo=?";

	
	public static final String PAYMENT = "insert payment set payment= ?, pnrNo= ?";

	
	public static final String CANCLE_TICKET = "update customer set isTcancel = 0 where isActive=1 and pnrNo = ? ";

	
	public static final String CANCLE_REQUEST = "update customer set isCancel = 0 where isActive=1 and pnrNo = ? ";

	
	public static final String CANCLE_REQUESTS_LIST = "select * from customer where isActive=1 and isCancel=0";
	
	
	public static final String RESCHEDULE_REQUESTS_LIST = "select * from customer where isSchedule=0";

	
	public static final String REFUND_PAYMENT = "update payment set payment=? where isActive=1 and pnrNo=?";
	
	
	public static final String PAYMENT_DETAIL_BY_PNRNO = "select * from payment where isActive=1 and pnrNo=?";
	
	
	public static final String RESCHEDULE_REQUEST = "update customer set isSchedule = 0 where isActive=1 and pnrNo=? ";
	

	public static final String RESCHEDULE_TICKET = "update customer set isSchedule = 1 where isActive=1 and pnrNo = ? ";

}
