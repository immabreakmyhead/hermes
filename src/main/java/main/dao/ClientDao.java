package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.model.Client;
import main.model.PhoneNumber;
import main.model.Program;
import main.model.Role;
import main.util.Database;

public class ClientDao {
	Connection connection;
	static String err = "n/a";

	public ClientDao() {
		connection = Database.getConnection();
	}

	/**
	 * Βρίσκει το επόμενο διαθέσιμο ID για τη βάση δεδομένων
	 * 
	 * @return το επόμενο διαθέσιμο ID, -1 αν υπάρχει σφάλμα
	 */
	public int findNextID() {
		int id_to_return = 1;
		try {
			Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery("SELECT MAX(id) FROM client;");
			if (result_set.next()) {
				id_to_return = result_set.getInt(1) + 1; // Corrected index and incremented value
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
	 * Προσθέτει έναν πελάτη στη βάση δεδομένων
	 * 
	 * @param user_id     το ID του χρήστη
	 * @param am          το AM του πελάτη
	 * @param phonenumber το τηλέφωνο του πελάτη
	 * @return 0 αν η λειτουργία ήταν επιτυχής, 1 αν υπήρξε σφάλμα
	 */
	public int addClient(int user_id, String am, String phonenumber) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO client (id, user_id, am, phonenumber) values (?, ?, ?, ?);");
			statement.setInt(1, findNextID());
			statement.setInt(2, user_id);
			statement.setString(3, am);
			statement.setString(4, phonenumber);
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
	 * Ανακτά όλους τους πελάτες από τη βάση δεδομένων
	 * 
	 * @return μια λίστα με όλους τους πελάτες
	 */
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<Client>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery(
					"SELECT u.id, u.username, u.password, u.name, u.surname, u.email, u.role, c.AM, c.phoneNumber, pn.id as phone_id, pn.status as phone_status, p.id AS program_id\r\n"
							+ "FROM \"user\" u\r\n" + "JOIN client c ON u.id = c.user_id\r\n"
							+ "JOIN phoneNumber pn ON c.phoneNumber = pn.number\r\n"
							+ "JOIN program p ON pn.program_id = p.id;");
			while (result_set.next()) {
				String roleString = result_set.getString("role");
				Role role = Role.valueOf(roleString.toUpperCase());

				String phoneNumberString = result_set.getString("phoneNumber");
				int phoneIdInt = result_set.getInt("phone_id");
				int programIdInt = result_set.getInt("program_id");
				String phoneStatus = result_set.getString("phone_status");
				PhoneNumber phoneNumber = new PhoneNumber(phoneIdInt, phoneNumberString, programIdInt, phoneStatus);
				Client client = new Client(result_set.getInt("id"), result_set.getString("username"),
						result_set.getString("name"), result_set.getString("surname"), result_set.getString("email"),
						role, result_set.getString("AM"), phoneNumber);
				clients.add(client);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return clients;
	}

	/**
	 * Ανακτά έναν πελάτη από τη βάση δεδομένων με βάση το ID του
	 * 
	 * @param clientId το ID του πελάτη
	 * @return ο πελάτης με το συγκεκριμένο ID ή null αν δεν βρέθηκε
	 */
	public Client getClientById(int clientId) {
		Client client = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT u.id, u.username, u.password, u.name, u.surname, u.email, u.role, c.AM, c.phoneNumber, pn.status as phone_status, pn.id as phone_id, p.id AS program_id\r\n"
							+ "FROM \"user\" u\r\n" + "JOIN client c ON u.id = c.user_id\r\n"
							+ "JOIN phoneNumber pn ON c.phoneNumber = pn.number\r\n"
							+ "JOIN program p ON pn.program_id = p.id\r\n" + "WHERE u.id = ?;");
			preparedStatement.setInt(1, clientId);
			ResultSet result_set = preparedStatement.executeQuery();

			if (result_set.next()) {
				String roleString = result_set.getString("role");
				Role role = Role.valueOf(roleString.toUpperCase());

				String phoneNumberString = result_set.getString("phoneNumber");
				int phoneIdInt = result_set.getInt("phone_id");
				int programIdInt = result_set.getInt("program_id");
				String phoneStatus = result_set.getString("phone_status");
				PhoneNumber phoneNumber = new PhoneNumber(phoneIdInt, phoneNumberString, programIdInt, phoneStatus);
				client = new Client(result_set.getInt("id"), result_set.getString("username"),
						result_set.getString("name"), result_set.getString("surname"), result_set.getString("email"),
						role, result_set.getString("AM"), phoneNumber);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return client;
	}
}
