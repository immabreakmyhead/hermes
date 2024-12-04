<style>
.btn-custom {
	width: 100%;
	padding: 20px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.btn-custom i {
	font-size: 40px; /* Adjust this value as needed */
	margin-bottom: 10px;
}
</style>

<div class="container mt-5">
	<div class="row">
		<div class="col-md-6 mb-4">
			<a href="clients" class="btn btn-primary btn-custom"> <i
				class="fas fa-user"></i> SHOW CLIENTS
			</a>
		</div>

		<div class="col-md-6 mb-4">
			<a href="program" class="btn btn-warning btn-custom"> <i
				class="fas fa-list"></i> SHOW ALL PROGRAMS
			</a>
		</div>
	</div>
	<div class="row">

		<div class="col-md-6 mb-4">
			<a href="add-client" class="btn btn-success btn-custom"> <i
				class="fas fa-user-plus"></i> CREATE A NEW CLIENT
			</a>
		</div>
		<div class="col-md-6 mb-4">
			<a href="clients" class="btn btn-info btn-custom"> <i class="fas fa-file-invoice"></i> ISSUE MOBILE BILL
			</a>
		</div>
	</div>
</div>
