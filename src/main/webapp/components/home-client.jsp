<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="main.dao.ClientDao"%>
<%@ page import="main.dao.CallDao"%>
<%@ page import="main.dao.BillDao"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.concurrent.TimeUnit"%>
<%@ page import="main.dao.UserDao"%>
<%@ page import="main.model.User"%>
<%@ page import="main.model.Call"%>
<%@ page import="main.model.Bill"%>
<%@ page import="main.model.Client"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="jakarta.servlet.http.Cookie"%>

<%
//Retrieve all cookies from the request
Cookie[] cookies = request.getCookies();
boolean signed_in = false;
int user_id = 0;

// Check if the signed_in cookie exists
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if ("SIGNEDIN".equals(cookie.getName())) {
	signed_in = true;
	user_id = Integer.parseInt(cookie.getValue());
	break;
		}
	}
}
// Create an instance of UserDao
UserDao userDao = new UserDao();

// Retrieve the user information
User user = userDao.getUser(user_id);

ClientDao clientDao = new ClientDao();

Client client = clientDao.getClientById(user_id);

CallDao callDao = new CallDao();

BillDao billDao = new BillDao();

List<Call> calls = callDao.getAllCallsOfNumber(client.getPhoneNumberString());

List<Bill> bills = billDao.getAllBillsOfNumber(client.getPhoneNumberString());
%>

<div class="container">
	<div class="row card-wrapper">
		<!-- Date Card -->
		<div class="col-md-6 col-12 mb-4 mb-md-0">
			<div class="card big-card">
				<div class="card-body p-0">
					<div class="date-row">
						<i class="fas fa-calendar-alt icon p-4"></i>
						<div>
							<div class="day">
								<%
								Date today = Date.valueOf(java.time.LocalDate.now());
								SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
								String day = dayFormat.format(today);
								out.print(day);
								%>
							</div>
							<div class="month-year">
								<%
								SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMMM yyyy");
								String monthYear = monthYearFormat.format(today);
								out.print(monthYear);
								%>
							</div>
						</div>
					</div>
					<div class="next-bill">
						<%
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(today);
						int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

						// Set the calendar to the first day of the next month
						calendar.add(Calendar.MONTH, 1);
						calendar.set(Calendar.DAY_OF_MONTH, 1);

						Date firstOfNextMonth = new Date(calendar.getTimeInMillis());
						long diffInMillies = firstOfNextMonth.getTime() - today.getTime();
						long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
						%>
						<p class="text-primary">
							The next bill will be available in
							<%=diffInDays%>
							days
						</p>
					</div>
				</div>
			</div>
		</div>

		<!-- Profile Card -->
		<div class="col-md-6 col-12">
			<div class="card profile-card">
				<div class="card-body">
					<h5 class="card-title">Your Profile</h5>
					<p class="card-text">
						<strong>Name:</strong>
						<%=user.getName()%>
					</p>
					<p class="card-text">
						<strong>Surname:</strong>
						<%=user.getSurname()%>
					</p>
					<p class="card-text">
						<strong>Email:</strong>
						<%=user.getEmail()%>
					</p>
					<p class="card-text">
						<strong>AM:</strong>
						<%=client.getAM()%>
					</p>
					<p class="card-text">
						<strong>PHONE NUMBER:</strong>
						<%=client.getPhoneNumberString()%>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="container mt-5">
	<div class="d-flex justify-content-center align-items-center mb-2">
		<h1 class="h1 mb-0 text-primary text-center">BILLS</h1>
	</div>
	<div class="row">
		<!-- Loop through the list and display each bill in a card -->
		<%
		for (Bill bill : bills) {

			Date date = bill.getDate();
			SimpleDateFormat billMonthFormat = new SimpleDateFormat("MMMM");
			String billMonth = billMonthFormat.format(date);

			// Calculate the difference in milliseconds
			long diffInMillis = Math.abs(Date.valueOf(LocalDate.now()).getTime() - bill.getDate().getTime());

			// Convert the difference to days
			long differenceInDays = diffInMillis / (1000 * 60 * 60 * 24);

			// Determine the class based on bill status
			String statusClass = "";
			if (bill.getBillStatus()) {
				statusClass = "text-success";
			} else if (diffInDays <= 30) {
				statusClass = "text-warning";
			} else {
				statusClass = "text-error";
			}

			String amount = String.valueOf(bill.getTotalAmount()) + "€";
		%>
		<div class="col-md-3 justify-content-center">
			<div class="bill-card text-center">
				<h3 class="text-primary mb-4"><%=billMonth%> Bill</h3>
				<p class="d-flex justify-content-between">
					<span>ISSUED </span> <span><%=bill.getDate()%></span>
				</p>
				<p class="d-flex justify-content-between">
					<span>STATUS </span> <span class="<%=statusClass%>"><%=bill.getBillStatus() ? "PAID" : "OUTSTANDING"%></span>
				</p>
				<%
				if (bill.getBillStatus()) {
				%>
				<!-- If bill is paid, show disabled button -->
				<button class="btn btn-secondary btn-block" disabled>PAID</button>
				<%
				} else {
				%>
				<!-- If bill is unpaid, show Pay button -->
				<a href="utilities/pay-bill.jsp?billId=<%=bill.getBillID()%>" class="btn btn-primary btn-block">
					PAY
					<%=amount%></a>
				<%
				}
				%>
			</div>
		</div>
		<%
		}
		%>
	</div>
</div>

<div class="container mt-5">
	<div class="d-flex justify-content-center align-items-center mb-2">
		<h1 class="h1 mb-0 text-primary text-center">CALL LOG</h1>
	</div>

	<div class="table-responsive">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Caller Number</th>
					<th>Receiver Number</th>
					<th>DURATION (MIN)</th>
					<th>MONTH</th>
				</tr>
			</thead>
			<tbody>
				<%
				// Loop through the list and display each call in a table row
				for (Call call : calls) {
				%>
				<tr>
					<td class="justify-content-between align-items-center">
						<%
						if (call.getCallerNumber().getPhone().equals(client.getPhoneNumber().getPhone())) {
						%> <i class="fas fa-fw fa-phone-alt mr-3 text-primary"></i> <i
						class="fas fa-arrow-right text-primary"></i> <%
 } else {
 %> <i class="fas fa-fw fa-phone-alt mr-3 text-info"></i> <i
						class="fas fa-arrow-left text-info"></i> <%
 }
 %>
					</td>
					<td><%=call.getCallerNumber().getPhone()%></td>
					<td><%=call.getReceiverNumber().getPhone()%></td>
					<td><%=call.getDuration()%></td>
					<td><%=call.getDate()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</div>