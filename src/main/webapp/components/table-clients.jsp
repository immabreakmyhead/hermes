

<%@ page import="main.dao.ClientDao"%>
<%@ page import="main.model.Client"%>
<%@ page import="java.util.List"%>
<%@ page import="main.dao.ProgramDao"%>
<%@ page import="main.model.Program"%>
<%@ page import="main.dao.UserDao"%>
<%@ page import="main.model.User"%>
<%@ page import="main.model.Role"%>

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
%>


<!-- Add your table here -->
<div class="container-fluid">
	<div class="d-flex justify-content-between align-items-center mb-4">
		<h1 class="h3 mb-0 text-gray-800">CLIENTS</h1>
		<a class="btn btn-primary" href="add-client">ADD CLIENT</a>
	</div>
	<div class="table-responsive">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>AM</th>
					<th>NAME</th>
					<th>SURNAME</th>
					<th>EMAIL</th>
					<th>PHONENUMBER</th>
					<th>STATUS</th>
					<th>PLAN</th>
					<%
					if (user.getRole() == Role.SELLER) {
					%>
					<th>ACTIONS</th>
					<%
					}
					%>
				</tr>
			</thead>
			<tbody>
				<%
				// Fetch the list of programs
				ClientDao clientDao = new ClientDao();
				List<Client> clients = clientDao.getAllClients();

				// Fetch the list of programs
				ProgramDao programDAO = new ProgramDao();

				// Loop through the list and display each program in a table row
				for (Client client : clients) {
					Program selectedProgram = programDAO.getProgramById(client.getPhoneNumber().getProgramId());
				%>
				<tr>
					<td><%=client.getAM()%></td>
					<td><%=client.getName()%></td>
					<td><%=client.getSurname()%></td>
					<td><%=client.getEmail()%></td>
					<td><%=client.getPhoneNumberString()%></td>
					<td><%=client.getPhoneNumber().getStatus()%></td>
					<td><%=selectedProgram.getName()%></td>
					<%
					if (user.getRole() == Role.SELLER) {
					%>
					<td>
						<a class="btn btn-primary mr-2"
						href="edit-client-program?id=<%=client.getId()%>"> EDIT PLAN <i
							class="fas fa-fw fa-pen ml-2"></i>
						</a>
						<a class="btn btn-primary mr-2"
						href="utilities/issue-bill.jsp?clientId=<%=client.getId()%>"> ISSUE BILL <i class="fas fa-money-bill-wave"></i>
						</a>
					</td>
					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</div>
<!-- End of table section -->