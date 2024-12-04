<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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


<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="home">
		<div class="sidebar-brand-icon rotate-n-15">
			<img class="sidebar-logo" src="images/logo.png" />
		</div>
		<div class="sidebar-brand-text mx-3">Hermes</div>
	</a>

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item"><a class="nav-link" href="#"
		data-toggle="collapse" data-target="#collapsePages"
		aria-expanded="true" aria-controls="collapsePages"> <i
			class="fas fa-fw fa-folder"></i> <span>Pages</span>
	</a>
		<div id="collapsePages" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<!-- <h6 class="collapse-header">Login Screens:</h6>
			 	<a class="collapse-item" href="login.html">Login</a> 
				<a class="collapse-item" href="register.html">Register</a> 
				<a class="collapse-item" href="forgot-password.html">Forgot Password</a> -->
				<div class="collapse-divider"></div>
				<h6 class="collapse-header">Other Pages:</h6>
				<a class="collapse-item" href="404">404 Page</a> <a
					class="collapse-item" href="blank">Blank Page</a>
				<!-- Add active on a collapse item to make it active -->

			</div>
		</div></li>


	<%
	if (user.getRole() == Role.SELLER) {
	%>
	<!-- Nav Item - Charts -->
	<li class="nav-item"><a class="nav-link" href="clients"> <i
			class="fas fa-fw fa-users"></i> <span>Clients</span>
	</a></li>
	<%
	}
	%>

	<%
	if (user.getRole() == Role.ADMIN) {
	%>
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="sellers"> <i
			class="fas fa-fw fa-table"></i> <span>Sellers</span>
	</a></li>
	<%
	}
	%>

	<%
	if (user.getRole() == Role.SELLER || user.getRole() == Role.ADMIN) {
	%>
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="program"> <i
			class="fas fa-fw fa-table"></i> <span>Programs</span>
	</a></li>
	<%
	}
	%>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>