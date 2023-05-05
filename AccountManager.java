package com.bryant.billing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bryant.util.DBProxy;

public class AccountManager {
	private final String JDBC_URL = "jdbc:derby:C:\\ISA421\\eclipse-workspace\\JavaJam\\billing"; 
	private final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver"; 
	private final String CUST_SQL = "SELECT * FROM customer WHERE pin='"; 
	private final String ACCT_SQL = "SELECT * FROM acctHistory WHERE acctNumber='"; 
	private HttpServletRequest req; 
	private HttpServletResponse res;
	
	public AccountManager(HttpServletRequest request,HttpServletResponse response) {
		//TODO Add code to persist the HTTPServletRequest and HTTPServletResponse objects
        this.req = request;
        this.res = response;
	}
	
	/**
	 * this method is essentially a command pattern which handles the details 
	 * of Account Management on behalf of a Customer. Exceptions which may occur 
	 * are forwarded to the client for resolution.
	 * 
	 * Once the Customer table has been read, a Customer instance is created to 
	 * contain the customer record and bound to the HTTPServletRequest object using
	 * the tag "customer". If the Customer instance contains a valid customer record, 
	 * the Account History table is read by account number to extract the account history 
	 * data. An AccountHistory instance is created to contain the account history information 
	 * and the ArrayList containing the account history detail is bound to the HTTPServletRequest 
	 * object using the tag "acctHist".
	 * 
	 * If the Customer instance does not contain a valid customer record. an error message
	 * is bound to the HTTPServletRequest object using the tag "errorMsg"
	 * @param <AccountHistoryRecord>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void execute() throws SQLException, ClassNotFoundException {
		//TODO develop code necessary to implement the description
		//TODO Research available methods on HTTPServletRequest to 
		//TODO learn how to bind an attribute to the object and how
		//TODO access attributes which are bound to the object(s)
        
        try {
            Class.forName(DRIVER);
            DBProxy proxy = new DBProxy(JDBC_URL, DRIVER); 
            String pin = req.getParameter("user");
            String query = CUST_SQL + pin + "'";
            System.out.println(query);
            ResultSet resultSet = proxy.executeSQL(query);
         
            Customer customer = new Customer(resultSet);
            System.out.println(customer.getFirstName());
            req.setAttribute("customer", customer);
                
            ResultSet result = proxy.executeSQL(ACCT_SQL + customer.getAcctNumber() + "'");
            ArrayList<AccountHistoryRecord> acctHist = new ArrayList<AccountHistoryRecord>();
            while (result.next()) {
            	AccountHistoryRecord record = new AccountHistoryRecord(
            			result.getString("acctNumber"),
            			result.getString("date"),
            			result.getString("description"),
            			result.getString("type"),
            			result.getString("amount"));
            	acctHist.add(record);
            	}
                req.setAttribute("acctHist", acctHist);
                
            proxy.closeConnection();

    } catch (SQLException e) {
        String errorMessage = "An error occurred while executing the account manager: " + e.getMessage();
        req.setAttribute("errorMsg", errorMessage);
        e.printStackTrace();
        throw e;
    } catch (ClassNotFoundException e) {
        String errorMessage = "An error occured: " + e.getMessage();
        req.setAttribute("errorMsg", errorMessage);
        e.printStackTrace();;
        throw e;
    }

    }

}
