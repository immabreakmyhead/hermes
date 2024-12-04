

<%@ page import="main.dao.ProgramDao"%>
<%@ page import="main.model.Program"%>
<%@ page import="java.util.List"%>
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
		<h1 class="h3 mb-0 text-gray-800">PROGRAMS</h1>

		<%
		if (user.getRole() == Role.ADMIN) {
		%>
		<a class="btn btn-primary" href="add-program">ADD PROGRAM</a>
		<%
		}
		%>

	</div>
	<div class="table-responsive">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>DESCRIPTION</th>
					<th>BENEFITS</th>
					<th>COSTS</th>
					<th>DURATION</th>
					<%
					if (user.getRole() == Role.ADMIN) {
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
				ProgramDao programDAO = new ProgramDao();
				List<Program> programs = programDAO.getAllPrograms();

				// Loop through the list and display each program in a table row
				for (Program program : programs) {
				%>
				<tr>
					<td><%=program.getProgramID()%></td>
					<td><%=program.getName()%></td>
					<td><%=program.getDescription()%></td>
					<td><%=program.getBenefits()%></td>
					<td><%=program.getCosts()%></td>
					<td><%=program.getDuration()%> months</td>
					<%
					if (user.getRole() == Role.ADMIN) {
					%>
					<td><a class="btn btn-primary"
						href="edit-program?id=<%=program.getProgramID()%>"> EDIT PROGRAM <i
							class="fas fa-fw fa-pen ml-2"></i>
					</a></td>
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