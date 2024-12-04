package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import main.model.User;
import main.model.Role;
import main.util.Database;
import main.util.Encryption;

public class UserDao {
	Connection connection;
	static String err = "n/a";

	public UserDao() {
		connection = Database.getConnection();
	}

	/**
	 * Βρίσκει το επόμενο διαθέσιμο ID για τη βάση δεδομένων
	 * 
	 * @return το επόμενο διαθέσιμο ID ή -1 σε περίπτωση σφάλματος
	 */
	public int findNextID() {
		int id_to_return = 1;
		try {
			Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery("SELECT MAX(id) FROM \"user\";");
			if (result_set.next()) {
				id_to_return = result_set.getInt(1) + 1; // Διόρθωση του index και αύξηση της τιμής
			}
			result_set.close();
			statement.close();
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return -1;
		}
		return id_to_return;
	}

	/**
	 * Προσθέτει έναν χρήστη στη βάση δεδομένων
	 * 
	 * @param username το όνομα χρήστη
	 * @param password ο κωδικός πρόσβασης
	 * @param name     το όνομα
	 * @param surname  το επώνυμο
	 * @param email    το email
	 * @param role     ο ρόλος του χρήστη
	 * @return το ID του προστιθέμενου χρήστη ή -1 σε περίπτωση σφάλματος
	 */
	public int addUser(String username, String password, String name, String surname, String email, Role role) {
		int userId = -1;
		try {
			String hashed_password = Encryption.getHashMD5(password, "hermes");

			String query = "INSERT INTO \"user\" (id, username, password, name, surname, email, role) values (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, findNextID());
			statement.setString(2, username);
			statement.setString(3, hashed_password);
			statement.setString(4, name);
			statement.setString(5, surname);
			statement.setString(6, email);
			statement.setString(7, role.toLowercase());

			statement.executeUpdate();

			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				userId = generatedKeys.getInt(1);
			}

			generatedKeys.close();
			statement.close();
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
		}
		return userId;
	}

	/**
	 * Βρίσκει έναν χρήστη στη βάση δεδομένων με βάση το όνομα χρήστη και τον κωδικό
	 * πρόσβασης
	 * 
	 * @param username το όνομα χρήστη
	 * @param password ο κωδικός πρόσβασης
	 * @return το ID του χρήστη αν βρέθηκε, διαφορετικά 0
	 */
	public int findUser(String username, String password) {
		try {

			String query = "SELECT * FROM \"user\" WHERE username = ? AND password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet result_set = statement.executeQuery();
			if (result_set.next()) {
				int userId = result_set.getInt("id");
				System.out.println("User found with ID: " + userId);
				// Επιστροφή του ID του χρήστη
				return userId;
			} else {
				// Ο χρήστης δεν βρέθηκε
				System.out.println("User not found");
				return 0;
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return 0;
	}

	/**
	 * Ανακτά έναν χρήστη από τη βάση δεδομένων με βάση το ID του
	 * 
	 * @param id το ID του χρήστη
	 * @return ο χρήστης με το συγκεκριμένο ID ή ένας χρήστης με ψεύτικα δεδομένα αν
	 *         δεν βρέθηκε
	 */
	public User getUser(int id) {
		try {

			String query = "SELECT * FROM \"user\" WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result_set = statement.executeQuery();
			if (result_set.next()) {
				String roleString = result_set.getString("role");
				Role role = Role.valueOf(roleString.toUpperCase());
				User user_to_return = new User(result_set.getInt("id"), result_set.getString("username"),
						result_set.getString("name"), result_set.getString("surname"), result_set.getString("email"),
						role);
				return user_to_return;
			} else {
				return new User(0, "jdoe", "John", "Doe", "johndoe@gmail.com", Role.CLIENT);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		// Επιστροφή ψεύτικου χρήστη αν δεν βρεθει κάτι
		return new User(0, "jdoe", "John", "Doe", "johndoe@gmail.com", Role.CLIENT);
	}
}