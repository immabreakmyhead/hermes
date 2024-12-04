

<%@ page import="java.util.List"%>
<%@ page import="main.model.Seller"%>
<%@ page import="main.dao.UserDao"%>
<%@ page import="main.dao.SellerDao"%>
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
		<h1 class="h3 mb-0 text-gray-800">SELLERS</h1>
		<a class="btn btn-primary" href="add-seller">ADD SELLER</a>
	</div>
	<div class="table-responsive">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>AM</th>
					<th>NAME</th>
					<th>SURNAME</th>
					<th>EMAIL</th>
					<th>AM</th>
				</tr>
			</thead>
			<tbody>
				<%
				// Fetch the list of programs
				SellerDao sellerDao = new SellerDao();
				List<Seller> sellers = sellerDao.getAllSellers();


				// Loop through the list and display each program in a table row
				for (Seller seller : sellers) {
				%>
				<tr>
					<td><%=seller.getAM()%></td>
					<td><%=seller.getName()%></td>
					<td><%=seller.getSurname()%></td>
					<td><%=seller.getEmail()%></td>
					<td><%=seller.getAM()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</div>
<!-- End of table section -->