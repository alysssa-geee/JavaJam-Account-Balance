package com.bryant.billing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * AccountHistory represents the status of a Customer's account. It maintains a collection
 * of AccountHistoryRecords and essentially serves as a wrapper for the collection
 * @author Alexander Bean, Zach Cullison, Alyssa Gee, Veronica Wallace
 *
 */

public class AccountHistory {
	private ArrayList<AccountHistoryRecord> records = new ArrayList<AccountHistoryRecord>();

	/**
	 * Builds a collection of AccountHistoryRecord objects from a given Result Set. 
	 * 
	 * @param resultSet
	 */
	public AccountHistory( ResultSet resultSet ) {
		try {
			while (resultSet.next()) {
				records.add( new AccountHistoryRecord(
						resultSet.getString("acctNumber"),
						resultSet.getString("date"),
						resultSet.getString("description"),
						resultSet.getString("type"),
						resultSet.getString("amount")
						)); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *
	 * Returns a copy of the collection of AccountHistoryRecord objects
	 * 
	 * @return ArrayList<AccountHistoryRecord>
	 */
	public ArrayList<AccountHistoryRecord> getRecords() {
		return new ArrayList<AccountHistoryRecord>(records); 
	}
	

}
