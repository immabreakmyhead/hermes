<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Hermes - Login</title>

<!-- Custom fonts for this template-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/styles.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">


<link rel="apple-touch-icon" sizes="57x57"
	href="favicons/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="favicons/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="favicons/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="favicons/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="favicons/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="favicons/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="favicons/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="favicons/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180"
	href="favicons/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"
	href="favicons/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="favicons/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="favicons/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="favicons/favicon-16x16.png">
<link rel="manifest" href="favicons/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="favicons/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">


</head>

<body id="page-top">

	<div class="wrapper">
		<div class="logo">
			<img src="images/logo.png" alt="">
		</div>
		<div class="text-center mt-4 name">Hermes</div>
		<form class="p-3 mt-3" action="/hermes/login" method="POST">
			<div class="form-field d-flex align-items-center">
				<span class="far fa-user"></span> <input type="text" name="username"
					id="userName" placeholder="Username" required>
			</div>
			<div class="form-field d-flex align-items-center">
				<span class="fas fa-key"></span> <input type="password"
					name="password" id="pwd" placeholder="Password" required>
			</div>
			<button type="submit" class="btn mt-3">Login</button>
		</form>
		<%
		String loginError = (String) request.getAttribute("loginError");
		if (loginError != null) {
		%>
		<div class="alert alert-danger mt-3" role="alert"><%=loginError%></div>
		<%
		}
		%>
	</div>



	<!-- Bootstrap core JavaScript-->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.bundle.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="js/jquery.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/main.js"></script>

</body>

</html>