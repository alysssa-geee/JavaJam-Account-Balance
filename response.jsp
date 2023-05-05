<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <div id="wrapper">
<header>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta name="authors" content="Alexander Bean, Alyssa Gee, Zach Cullison">
            <meta name="description" content="Functional and informative website for a small business. Includes
                                              menu prices, music clips, job application, and merchandise orders.
                                              Friendly for consumer use.">
            <title>Account History</title>
            <link rel="icon" type="image/x-icon" href="favicon.ico">
            <h1><a href="JJindex.html">JavaJam Coffee Bar</a></h1>
            <link href="javajam.css" rel="stylesheet">
</header>

      <nav>
            <ul>
                <li><a href="JJindex.html">Home</a></li>
                <li><a href="JJmenu.html">Menu</a></li>
                <li><a href="JJmusic.html">Music</a></li>
                <li><a href="JJjobs.html">Jobs</a></li>
                <li><a href="JJgear.html">Gear</a></li>
                <li class="dropdown">
                    <a href="JJorders.html" class="dropbtn">Orders</a>
                    <div class="dropdown-content">
                      <a href="JJlogin.html">Place an Order</a>
                      <a href="JJhistory.html">Search Order History</a>
                    </div>
                  </li>
            </ul>
        </nav>
  
<h1>Account History</h1>
<main>
<div id="herocouch"></div>
	
	
	<%@ page import="com.bryant.billing.*, java.util.*, java.util.ArrayList" %>
	<%
	Customer customer = (Customer)request.getAttribute("customer"); 
	String user = customer.getFirstName()+" "+customer.getLastName(); 
	String addr1 = customer.getStreetAddress(); 
	String addr2 = customer.getCity()+", "+customer.getState()+" "+customer.getZip();
	String acctNumber = customer.getAcctNumber(); 
	String date; 
	String type; 
	String description; 
	String amount; 
	
	ArrayList<AccountHistoryRecord> AcctHistory = (ArrayList<AccountHistoryRecord>)request.getAttribute("acctHist");
	
	
	%>
		<%
	if (customer.isValidCustomer()) {
	%>
	
		<h2>Customer: <%=user%></h2>
		<p><strong>Address:</strong>
		<%=addr1 %> 
		<br><strong style="color: #FEF6C2">Address:</strong><%=addr2 %></br>
		<Br><strong>Phone:</strong> <%=customer.getCellPhone(true) %> 
		<br><strong>EMail:</strong> <%=customer.getEmail()%>
		</p>
		<p>
		<strong>Account:</strong> <%=acctNumber %><br>
		<table border="1">
		<tr id="cartTop">
		<td>Date</td>
		<td id="cartCol"><strong>Type</strong></td>
		<td>Description</td>
		<td id="cartCol"><strong>Amount</strong></td>
		</tr>
		<%for(AccountHistoryRecord record : AcctHistory) { 
			date = record.getDate(true);
			type = record.getType();
			description = record.getDescription();
			amount = record.getAmount(true); %>
		<tr id="cart">
		<td style="font-weight: normal"><%=date %></td>
		<td id="cartCol"><%=type %></td>
		<td style="font-weight: normal"><%=description%></td>
		<td id="cartCol"><%=amount %></td>
		</tr>
		<%}	%>
	<%
	} else {
	%>
	<%=request.getAttribute("errorMsg") %>
	<%}	%>
</main>
       
</div>

</html>