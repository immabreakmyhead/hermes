
<%@ page import="main.dao.ProgramDao"%>
<%@ page import="main.model.Program"%>
<%@ page import="main.model.Client"%>
<%@ page import="java.util.List"%>

<!-- Begin Page Content -->
<div class="container-fluid">
	<h1 class="text-center">CHOOSE A PROGRAM</h1>
	<div class="row">
		<%
		// Fetch the list of programs
		ProgramDao programDAO = new ProgramDao();
		List<Program> programs = programDAO.getAllPrograms();
		Client client = (Client) request.getAttribute("client");
		if (programs != null) {
			for (int i = 0; i < programs.size(); i++) {
				Program program = programs.get(i);
				String iconClass = "";

				// Determine the icon class based on the index
				switch (i) {
				case 0:
			iconClass = "fas fa-star";
			break;
				case 1:
			iconClass = "fas fa-trophy";
			break;
				default:
			iconClass = "fas fa-gem";
			break;
				}
		%>
		<div class="col-md-4 mb-4">
			<div class="card text-center">
				<div class="card-body">
					<i class="<%=iconClass%>"
						style="font-size: 4rem; color: var(--blue);"></i>
					<h5 class="card-title mt-3"><%=program.getName()%></h5>
					<p class="card-text"><%=program.getDescription()%></p>
					<ul class="list-group list-group-flush text-left">
						<li class="list-group-item">Benefits: <%=program.getBenefits()%></li>
						<li class="list-group-item">Cost: <%=program.getCosts()%></li>
						<li class="list-group-item">Duration: <%=program.getDuration()%>
							months
						</li>
					</ul>

					<%
					// Client client = (Client) request.getAttribute("client");
					if (client != null) {
						if (program.getProgramID() == client.getPhoneNumber().getProgramId()) {
					%>
					<button class="btn btn-primary mt-3 disabled">SELECTED</button>
					<%
					} else {
					%>
					<form id="selectProgramForm"
						action="${pageContext.request.contextPath}/edit-client-program"
						method="post">
						<input type="hidden" name="phoneId"
							value="<%=client.getPhoneNumber().getId()%>"> <input
							type="hidden" name="programId"
							value="<%=program.getProgramID()%>">
						<button type="submit" class="btn btn-primary mt-3">SELECT
							THIS PLAN</button>
					</form>
					<%
					}
					}
					%>
				</div>
			</div>
		</div>
		<%
		}
		}
		%>
	</div>
</div>
<!-- /.container-fluid -->