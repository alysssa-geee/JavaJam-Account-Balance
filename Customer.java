package com.bryant.billing;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bryant.util.DBProxy;
/**
 * The Customer object acts as a wrapper for all information relating to a specific customer
 * @author Alexander Bean, Zach Cullison, Alyssa Gee, Veronica Wallace
 */
public class Customer {
	private String pin = "";
	private String acctNumber = ""; 
	private String firstName = ""; 
	private String lastName = ""; 
	private String streetAddress = ""; 
	private String city = ""; 
	private String state = ""; 
	private String zip = ""; 
	private String email = ""; 
	private String cellPhone = ""; 
	private boolean validCustomer = false; 


	/**
	 * Constructs a Customer instance from a Result Set containing a customer record and 
	 * sets the internal state to true or false for a valid customer. A valid customer is
	 * the case where there is a matching PIN in the database for the Customer. An invalid
	 * state is set when there was no Customer record in the Result Set. 
	 * 
	 * In the event of an SQLException, the internal state is set as invalid and the 
	 * stack trace is displayed on the console.
	 * @param resultSet
	 * @param pin 
	 */
	public Customer(ResultSet resultSet) {
        try {
            validCustomer = resultSet.next();
            if (validCustomer==true) {
                if (isValidCustomer()) {
                    pin = resultSet.getString("pin");
                    acctNumber = resultSet.getString("acctNumber");
                    firstName = resultSet.getString("firstName");
                    lastName = resultSet.getString("lastName");
                    streetAddress = resultSet.getString("streetAddress");
                    city = resultSet.getString("city");
                    state = resultSet.getString("state");
                    zip = resultSet.getString("zip");
                    email = resultSet.getString("email");
                    cellPhone = resultSet.getString("cellPhone");
                    validCustomer = true;
                } else {
                    System.out.println("Not a valid customer. Try again.");
                }
            } else {
                System.out.println("No customer record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//Getter (Accessor) methods
	//TODO add Getter (Accessor) methods for all attributes in the Customer record
 public String getPin() {
	return this.pin;
	}

public String getAcctNumber() {
	return this.acctNumber;
	}

public String getFirstName() {
	return this.firstName;
	}

public String getLastName() {
	return this.lastName;
}

public String getStreetAddress() {
	return this.streetAddress;
}

public String getCity() {
	return this.city;
}

public String getState() {
	return this.state;
}

public String getZip() {
	return this.zip;
}

public String getEmail() {
	return this.email;
}

public String getCellPhone() {
	return cellPhone;
}
	/**
	 * Returns a String in a pre-determined format:  (NNN) NNN-NNNN  if the argument
	 * passed is a boolean true. If false is passed, then the string returned is  
	 * unformatted. 
	 * 
	 * Note: 
	 * Processing reverts to returning an unformatted phone number
	 * if the internal state of the Customer record is invalid
	 * 
	 * @param boolean
	 * @return String
	 */
	public String getCellPhone( boolean formatted ) {
		//TODO Provide code as described above
		if (formatted == true) {
			return "(" + cellPhone.substring(0, 3) + ") " + cellPhone.substring(3, 6) + "-" + cellPhone.substring(6);
		} else {
			return cellPhone;
		}
	}

	/**
	 * Returns a boolean value indicating the internal state of the Customer instance
	 * true  - indicates the object contains a valid Customer record
	 * false - indicates the object does not contain a valid Customer record
	 * 
	 * @return boolean
	 */
	public boolean isValidCustomer() {
	    // Check if the Customer object contains a valid customer record
	    if (validCustomer) {
	        // Retrieve the PIN value from the Customer object
	        String actualPin = getPin();
	        // Compare the PIN value to the input PIN value
	        if (actualPin.equals(pin)) {
	            return validCustomer = true; // the PIN values match, so the Customer object is valid
	        } else {
	            return validCustomer = false; // the PIN values don't match, so the Customer object is not valid
	        }
	    } else {
	        return validCustomer = false; // the Customer object is not valid
	    }
	}

	/**
	 * Returns a String which will format the phone number passed in as an argument. 
	 * 
	 * The format is as follows: 
	 * 
	 * (NNN) NNN-NNNN
	 * 
	 * @param String - containing phone number to be formatted 
	 * @return String - Formatted phone number
	 */
	@SuppressWarnings("unused")
	private String formatPhone( String phone ) {
		//TODO add code to format the string passed as an argument into the format above
		//TODO return the formatted phone number
		String areaCode = phone.substring(0,3);
		String prefix = phone.substring(3,6);
		String suffix = phone.substring(6);
		return "(" + areaCode + ") " + prefix + "-" + suffix;
	}
	
	
	
}
