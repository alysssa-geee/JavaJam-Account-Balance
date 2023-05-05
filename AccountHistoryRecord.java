package com.bryant.billing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
/**
 * AccountHistoryRecord describes one line item of account history for a specific customer
 *
 */
public class AccountHistoryRecord {
    private String acctNumber;
    private String date;
    private String description;
    private String type;
    private String amount;

    public AccountHistoryRecord(String acctNumber, String date, String description, String type, String amount) {
        this.acctNumber = acctNumber;
        this.date = date;
        this.description = description;
        this.type = type;
        this.amount = amount;
    }
    

	//Getters (Accessors)
	//TODO code Getters (Accessors) for all attributes
    public String getAcctNumber() {
        return acctNumber;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

	/**
	 * Returns a String in a pre-determined format:  MM/DD/YYYY  if the argument
	 * passed is a boolean true. If false is passed, then the string returned is  
	 * unformatted 
	 * @param formatted - boolean
	 * @return String
	 */

    public String getDate(boolean formatted) {
        Date date = new Date();
        if (formatted) {
            // Format the date in MM/DD/YYYY format
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            return formatter.format(date);
        } else {
            // Return the date in unformatted format
            return date.toString();
        }
    }


	

	/**
	 * Returns a String in a pre-determined format:  $###,###.00  if the argument
	 * passed is a boolean true. If false is passed, then the string returned is  
	 * unformatted 
	 * @param formatted - boolean
	 * @return String
	 */
    public String getAmount(boolean formatted) {
        if (formatted) {
            // Format the amount as $###,###.00
            double amountDouble = Double.parseDouble(amount);
            DecimalFormat decimalFormat = new DecimalFormat("$###,###.00");
            return decimalFormat.format(amountDouble);
        } else {
            // Return the unformatted amount as a string
            return amount;
        }
    }



	/**
	 * Returns a String in a pre-determined format:  MM/DD/YYYY  
	 * @param String - The string to be formatted as a date
	 * @return String - The formatted date string
	 */

    private String formatDate(String date) {
        // Split the date string into year, month, and day components
        String[] dateComponents = date.split("-");
        String year = dateComponents[0];
        String month = dateComponents[1];
        String day = dateComponents[2];

        // Combine the components into the desired format
        return String.format("%s/%s/%s", month, day, year);
    }

	
	/**
	 * Returns a String in a pre-determined format:  $###,###.00  
	 * @param String - amount to be formatted
	 * @return String - String containing formatted amount
	 */
	private String formatAmount( String amount ) {
		float amt = Float.parseFloat(amount); 
		return new DecimalFormat("$###,###.00").format(amt);
	}


}



