<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<%@ page import="main.dao.BillDao"%>
<%@ page import="main.model.Bill"%>

<%
//Get the billId parameter from the request
String billId = request.getParameter("billId");

// Print the billId to the console
System.out.println("PAYED BILL WITH ID: " + billId);
BillDao billDAO = new BillDao();
billDAO.payBill(Integer.parseInt(billId));

// Redirect to the login page from the root of the website
String contextPath = request.getContextPath();
response.sendRedirect(contextPath + "/home");
%>
