package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.model.PhoneNumber;
import main.model.Program;
import main.util.Database;

public class PhoneNumberDao {
	Connection connection;
	static String err = "n/a";

	public PhoneNumberDao() {
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
			ResultSet result_set = statement.executeQuery("SELECT MAX(id) FROM phoneNumber;");
			if (result_set.next()) {
				id_to_return = result_set.getInt(1) + 1;
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
	 * Προσθέτει έναν αριθμό τηλεφώνου στη βάση δεδομένων
	 * 
	 * @param number    ο αριθμός τηλεφώνου
	 * @param programId το ID του προγράμματος
	 * @return 0 αν η προσθήκη ήταν επιτυχής, 1 σε περίπτωση σφάλματος
	 */
	public int addPhoneNumber(String number, int programId) {
		try {

			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO phoneNumber (id, number, program_id, status) values (?, ?, ?, ?);");
			statement.setInt(1, findNextID());
			statement.setString(2, number);
			statement.setInt(3, programId);
			statement.setString(4, "ACTIVE");
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
	 * Ενημερώνει έναν αριθμό τηλεφώνου στη βάση δεδομένων
	 * 
	 * @param phoneNumber ο αριθμός τηλεφώνου για ενημέρωση
	 * @return 0 αν η ενημέρωση ήταν επιτυχής, 1 σε περίπτωση σφάλματος
	 */
	public int updatePhoneNumber(PhoneNumber phoneNumber) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE phoneNumber SET id = ?, number= ?, program_id = ?, status = ? WHERE id = ?;");
			statement.setInt(1, phoneNumber.getId());
			statement.setString(2, phoneNumber.getPhone());
			statement.setInt(3, phoneNumber.getProgramId());
			statement.setString(4, phoneNumber.getStatus());
			statement.setInt(5, phoneNumber.getId());
			statement.executeUpdate();
		} catch (Exception sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * Ανακτά όλους τους αριθμούς τηλεφώνου από τη βάση δεδομένων
	 * 
	 * @return λίστα με όλους τους αριθμούς τηλεφώνου
	 */
	public List<PhoneNumber> getAllPhoneNumbers() {
		List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery("SELECT * FROM phoneNumber ORDER BY id;");
			while (result_set.next()) {
				PhoneNumber phoneNumber = new PhoneNumber(
						result_set.getInt("id"),
						result_set.getString("number"),
						result_set.getInt("program_id"),
						result_set.getString("status"));
				phoneNumbers.add(phoneNumber);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return phoneNumbers;
	}

	/**
	 * Ανακτά έναν συγκεκριμένο αριθμό τηλεφώνου από τη βάση δεδομένων με βάση το ID
	 * του
	 * 
	 * @param phoneId το ID του αριθμού τηλεφώνου
	 * @return ο αριθμός τηλεφώνου με το συγκεκριμένο ID ή ένας νέος αριθμός
	 *         τηλεφώνου αν δεν βρέθηκε
	 */
	public PhoneNumber getPhoneNumberById(int phoneId) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PhoneNumber WHERE id = ?;");
			statement.setInt(1, phoneId);
			ResultSet result_set = statement.executeQuery();
			while (result_set.next()) {
				PhoneNumber pack = new PhoneNumber(
						result_set.getInt("id"),
						result_set.getString("number"),
						result_set.getInt("program_id"),
						result_set.getString("status"));
				return pack;
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return new PhoneNumber(0, "6935558090", 1, "INACTIVE");
	}

	/**
	 * Ανακτά έναν συγκεκριμένο αριθμό τηλεφώνου από τη βάση δεδομένων με βάση τον
	 * αριθμό
	 * 
	 * @param phoneNumber ο αριθμός τηλεφώνου
	 * @return ο αριθμός τηλεφώνου με τον συγκεκριμένο αριθμό ή ένας νέος αριθμός
	 *         τηλεφώνου αν δεν βρέθηκε
	 */
	public PhoneNumber getPhoneNumberByPhone(String phoneNumber) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PhoneNumber WHERE number = ?;");
			statement.setString(1, phoneNumber);
			ResultSet result_set = statement.executeQuery();
			while (result_set.next()) {
				PhoneNumber pack = new PhoneNumber(
						result_set.getInt("id"),
						result_set.getString("number"),
						result_set.getInt("program_id"),
						result_set.getString("status"));
				return pack;
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return new PhoneNumber(0, "6935558090", 1, "INACTIVE");
	}

	public String giveErr() {
		return err;
	}
}
