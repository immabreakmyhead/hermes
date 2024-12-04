<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<%@ page import="main.dao.BillDao"%>
<%@ page import="main.model.Bill"%>
<%@ page import="main.dao.ClientDao"%>
<%@ page import="main.model.Client"%>
<%@ page import="main.dao.ProgramDao"%>
<%@ page import="main.model.Program"%>
<%@ page import="java.sql.Date"%>

<%
//Get the billId parameter from the request
int clientId = Integer.parseInt(request.getParameter("clientId"));

// Print the billId to the console
System.out.println("ISSUED BILL TO THE CLIENT WITH ID: " + clientId);

ClientDao clientDAO = new ClientDao();
Client client = clientDAO.getClientById(clientId);

ProgramDao programDao = new ProgramDao();
Program program = programDao.getProgramById(client.getPhoneNumber().getProgramId());
System.out.println("CLIENT HAS THE PROGRAM WITH NAME: " + program.getName());

String cleanedPrice = program.getCosts().replace("€", "").trim();
Double billTotalAmount = Double.parseDouble(cleanedPrice);
System.out.println("CLIENT WILL HAVE TO PAY: " + billTotalAmount);

BillDao billDAO = new BillDao();
long millis=System.currentTimeMillis();  
Date currentDate = new Date(millis);  
billDAO.addBill(client.getPhoneNumber().getPhone(), currentDate, billTotalAmount);

// Redirect to the login page from the root of the website
String contextPath = request.getContextPath();
response.sendRedirect(contextPath + "/clients");
%>
