package main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	static Connection connection = null;

	static String jdbcUrl = "jdbc:postgresql://localhost:5432/hermes"; // Σύνδεση στη βάση δεδομένων PostgreSQL στο
																		// localhost με την ονομασία "hermes"
	static String username = "postgres"; // Όνομα χρήστη για τη σύνδεση στη βάση δεδομένων
	static String password = "postgres"; // Κωδικός πρόσβασης για τη σύνδεση στη βάση δεδομένων

	public static Connection getConnection() {
		if (connection == null) {
			try {
				// Φόρτωση της κλάσης οδηγού JDBC για PostgreSQL
				Class.forName("org.postgresql.Driver");

				// Λήψη σύνδεσης από τη βάση δεδομένων
				connection = DriverManager.getConnection(jdbcUrl, username, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace(); // Εκτύπωση του stack trace σε περίπτωση που δεν βρεθεί η κλάση οδηγός JDBC
			} catch (SQLException e) {
				e.printStackTrace(); // Εκτύπωση του stack trace σε περίπτωση σφάλματος SQL
			}
		}
		return connection;
	}
}
