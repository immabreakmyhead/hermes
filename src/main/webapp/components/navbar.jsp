
<%@ page import="main.dao.UserDao"%>
<%@ page import="main.model.User"%>
<%@ page import="main.model.Role"%>
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
%>


<!-- Navbar -->
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop"
		class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">



		<!-- Nav Item - User Information -->
		<li class="nav-item dropdown no-arrow"><a
			class="nav-link dropdown-toggle" href="#" id="userDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <span
				class="mr-3 d-none d-lg-inline text-gray-600 large"> <%=user.getName()%>
					<%=user.getSurname()%> <br> <span
					class="text-primary user-role"> <%=user.getStringRole()%>
				</span>
			</span> <img class="img-profile rounded-circle" src="images/astronaut.png">

		</a> <!-- Dropdown - User Information -->
			<div
				class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="userDropdown">

				<%
				if (user.getRole() == Role.CLIENT) {
				%>
				<a class="dropdown-item" href="#"> <i
					class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
				</a> 
				<%
				}
				%>
				
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#logoutModal"> <i
					class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
					Logout
				</a>
			</div></li>
	</ul>

</nav>
