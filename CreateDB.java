package com.bryant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDB {
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:billing;create=true"; 
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(JDBC_URL);
		System.out.println("Connection Extablished"); 
		
		//Create Account History table
		//connection.createStatement().executeUpdate("DROP TABLE customer");// first
		//connection.createStatement().executeUpdate("DROP TABLE acctHistory"); //second
		//connection.createStatement().executeUpdate("DROP TABLE product"); 
			
		connection.createStatement().execute("CREATE TABLE acctHistory("
				+ "acctNumber VARCHAR(4) NOT NULL, "
				+ "date VARCHAR(8), "
				+ "description VARCHAR(25), "
				+ "type VARCHAR(3), "
				+ "amount VARCHAR(8) )" 
				);
		System.out.println("'acctHistory' table created"); 
	
		//Create Customer table
		connection.createStatement().execute("CREATE TABLE customer("
				+ "pin VARCHAR(4) NOT NULL, "
				+ "acctNumber VARCHAR(4),"
				+ "firstName VARCHAR(10), "
				+ "lastName VARCHAR(10), "
				+ "streetAddress VARCHAR(20), "
				+ "city VARCHAR(20), "
				+ "state VARCHAR(2), "
				+ "zip VARCHAR(5), "
				+ "email VARCHAR(25), "
				+ "cellPhone VARCHAR(10) )"
				);
		System.out.println("'customer' table created"); 
		
		//Create Product table 
		connection.createStatement().execute("CREATE TABLE product("
				+ "prodID VARCHAR(4) NOT NULL, "
				+ "prodName VARCHAR(25), "
				+ "prodDesc VARCHAR(200), "
				+ "price DECIMAL(4,2) )"
				);
		System.out.println("'product' table created"); 
	
		//Populate tables with data
		connection.createStatement().execute("INSERT INTO acctHistory VALUES " +
				"('0001', '04102023', 'JavaJam T-Shirt', 'PUR', '14.95'),"+
				"('0001', '04102023', 'Paid in full', 'PMT', '14.95'),"+
				"('0002', '11302022', 'JavaJam Hot Tumbler', 'PUR', '20.00'),"+
				"('0002', '11302022', 'Paid in full', 'PMT', '20.00'), "+
				"('0003', '12252022', 'JavaJam Baseball Cap', 'PUR', '18.00'), "+
				"('0003', '12252022', 'Paid in full', 'PMT', '18.00'), "+
				"('0004', '02142023', 'JavaJam Hoodie', 'PUR', '32.00'), "+
				"('0004', '02142023', 'Paid in full', 'PMT', '32.00') ");
		System.out.println("Rows inserted into 'acctHistory'"); 
	
		connection.createStatement().execute("INSERT INTO customer VALUES " +
				"('1111', '0001', 'Alexander', 'Bean', '1313 Mockingbird Ln', 'Mockingbird Heights', 'CA', '02112','abean2@bryant.edu','3428675309'),"+
				"('2222', '0002', 'Alyssa','Gee', '1 Main St.', 'Orlando', 'FL', '02113', 'agee@bryant.edu', '4447928000'), "+
				"('3333', '0003', 'Zach', 'Cullison', '1150 Douglas Pike', 'Smithfield', 'RI', '02917', 'zcullison@bryant.edu', '6173456789'), "+
				"('4444', '0004', 'Veronica', 'Wallace', '123 ABC St', 'Providence', 'RI', '02906', 'vwallace@bryant.edu', '7810009675')");
		System.out.println("Rows inserted into 'customer'"); 

		connection.createStatement().execute("INSERT INTO product VALUES " +
				"('0001', 'JavaJam T-Shirt', 'JavaJam shirts are comfortable to wear to school and around town. 100% cotton. XL only.', 14.95),"+
				"('0002', 'JavaJam Mug', 'JavaJam mugs carry a full load of caffeine (12 oz.) to jump-start your morning.', 9.95),"+
				"('0003', 'JavaJam Hot Tumbler', 'JavaJam stainless steel hot tumblers carry a full load of caffeine (16 oz.) and keeps it hot for the rest of your day.', 20.00),"+
				"('0004', 'JavaJam Cold Tumbler', 'JavaJam stainless steel cold tumblers carry a full load of caffeine (24 oz.) and keeps it cold for the rest of your day.', 25.00), "+
				"('0005', 'JavaJam Baseball Cap', 'JavaJam Baseball Cap is a perfect accessory for everyday wear.', 18.00), "+
				"('0006', 'JavaJam Hoodie', 'JavaJam Hoodie will keep you warm on a cold day. 50% Polyester 50% Cotton. XL only.', 32.00), "+
				"('0007', 'JavaJam Hot Cup', 'JavaJam Hot Cups are a great reusable option for those on a budget.', 3.50)");
		System.out.println("Rows inserted into 'product'");


		connection.close();
		
		
		
	}

}
