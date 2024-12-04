package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.model.Role;
import main.model.Seller;
import main.util.Database;

public class SellerDao {
	Connection connection;
	static String err = "n/a";

	public SellerDao() {
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
			ResultSet result_set = statement.executeQuery("SELECT MAX(id) FROM client;");
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
	 * Προσθέτει έναν πωλητή στη βάση δεδομένων
	 * 
	 * @param user_id το ID του χρήστη
	 * @param am      το AM του πωλητή
	 * @return 0 αν η προσθήκη ήταν επιτυχής, 1 σε περίπτωση σφάλματος
	 */
	public int addSeller(int user_id, String am) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO seller (id, user_id, am) values (?, ?, ?);");
			statement.setInt(1, findNextID());
			statement.setInt(2, user_id);
			statement.setString(3, am);
			statement.executeUpdate();
			statement.close();
		} catch (Exception sqle) {
			err = sqle.toString();
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * Ανακτά όλους τους πωλητές από τη βάση δεδομένων
	 * 
	 * @return λίστα με όλους τους πωλητές
	 */
	public List<Seller> getAllSellers() {
		List<Seller> sellers = new ArrayList<Seller>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery(
					"SELECT u.id, u.username, u.password, u.name, u.surname, u.email, u.role, s.AM\r\n"
							+ "FROM \"user\" u\r\n" + "JOIN seller s ON u.id = s.user_id;");
			while (result_set.next()) {
				String roleString = result_set.getString("role");
				Role role = Role.valueOf(roleString.toUpperCase());
				Seller seller = new Seller(result_set.getInt("id"), result_set.getString("username"),
						result_set.getString("name"), result_set.getString("surname"), result_set.getString("email"),
						role, result_set.getString("AM"));
				sellers.add(seller);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return sellers;
	}
}
