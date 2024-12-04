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

<title>Hermes - Edit Program</title>

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
<link href="css/index.css" rel="stylesheet">


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

	<!-- Page Wrapper -->
	<div id="wrapper">

		<jsp:include page="../components/sidebar.jsp" />

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">


				<jsp:include page="../components/navbar.jsp" />

				<%@ page import="main.dao.ProgramDao"%>
				<%@ page import="main.model.Program"%>

				<%
				int program_id = (int) request.getAttribute("program_id");

				// Fetch the specific program
				ProgramDao programDAO = new ProgramDao();
				Program program = programDAO.getProgramById(program_id);

				String id = String.valueOf(program.getProgramID());
				String name = program.getName();
				String description = program.getDescription();
				String benefits = program.getBenefits();
		        int costs = Integer.parseInt(program.getCosts().replace("€", ""));
				%>

				<div class="container mt-5">
					<h2>Update Program</h2>
					<form action="edit-program" method="post">
						<input type="hidden" name="id" value="<%=id%>">
						<div class="form-group">
							<label for="name">Name:</label> <input type="text"
								class="form-control" id="name" name="name" value=<%=name%>
								required>
						</div>
						<div class="form-group">
							<label for="description">Description:</label>
							<textarea class="form-control" id="description" name="description" rows="3" required><%=description%></textarea>
						</div>
						<div class="form-group">
							<label for="benefits">Benefits:</label> <input type="text"
								class="form-control" id="benefits" name="benefits"
								value="<%=benefits%>" required>
						</div>
						<div class="form-group">
							<label for="costs">Costs (€):</label> <input type="number"
								class="form-control" id="costs" name="costs"
								value="<%=costs%>" step="1" required>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
			<!-- End of Content Wrapper -->

			<jsp:include page="../components/footer.jsp" />

		</div>
		<!-- End of Page Wrapper -->

		<!-- Logout Modal-->
		<jsp:include page="../components/logout-model.jsp" />

		<!-- Bootstrap core JavaScript-->
		<script src="js/jquery.js"></script>
		<script src="js/bootstrap.bundle.js"></script>

		<!-- Core plugin JavaScript-->
		<script src="js/jquery.js"></script>

		<!-- Custom scripts for all pages-->
		<script src="js/main.js"></script>

		<!-- Form validation script -->
		<script>
			(function() {
				'use strict';
				window
						.addEventListener(
								'load',
								function() {
									var forms = document
											.getElementsByClassName('needs-validation');
									var validation = Array.prototype.filter
											.call(
													forms,
													function(form) {
														form
																.addEventListener(
																		'submit',
																		function(
																				event) {
																			if (form
																					.checkValidity() === false) {
																				event
																						.preventDefault();
																				event
																						.stopPropagation();
																			}
																			form.classList
																					.add('was-validated');
																		},
																		false);
													});
								}, false);
			})();
		</script>
</body>

</html>