package main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.model.Call;
import main.model.PhoneNumber;
import main.util.Database;

public class CallDao {
	Connection connection;
	static String err = "n/a";

	// Αρχικοποιεί τη σύνδεση με τη βάση δεδομένων
	public CallDao() {
		connection = Database.getConnection();
	}

	/**
	 * Προσθέτει μια κλήση στη βάση δεδομένων
	 * 
	 * @param callId         το ID της κλήσης
	 * @param callerNumber   ο αριθμός τηλεφώνου του καλούντος
	 * @param receiverNumber ο αριθμός τηλεφώνου του αποδέκτη
	 * @param duration       η διάρκεια της κλήσης σε δευτερόλεπτα
	 * @param date           η ημερομηνία της κλήσης
	 * @return 0 αν η λειτουργία ήταν επιτυχής, 1 αν υπήρξε σφάλμα
	 */
	public int addCall(int callId, String callerNumber, String receiverNumber, int duration, Date date) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO call (callId, callerNumber, receiverNumber, duration, billableMonth) values (?, ?, ?, ?, ?);");
			statement.setInt(1, callId);
			statement.setString(2, callerNumber);
			statement.setString(3, receiverNumber);
			statement.setInt(4, duration);
			statement.setDate(5, date);

			// Εκτελεί τη δήλωση
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
	 * Ανακτά όλες τις κλήσεις από τη βάση δεδομένων
	 * 
	 * @return μια λίστα με όλες τις κλήσεις
	 */
	public List<Call> getAllCalls() {
		List<Call> calls = new ArrayList<Call>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery("SELECT * FROM call;");
			PhoneNumberDao phoneNumberDAO = new PhoneNumberDao();
			while (result_set.next()) {
				PhoneNumber callerPhoneNumber = phoneNumberDAO
						.getPhoneNumberByPhone(result_set.getString("callerNumber"));
				PhoneNumber receiverPhoneNumber = phoneNumberDAO
						.getPhoneNumberByPhone(result_set.getString("receiverNumber"));
				Call call = new Call(result_set.getInt("callId"), callerPhoneNumber, receiverPhoneNumber,
						result_set.getInt("duration"), result_set.getDate("date"));
				calls.add(call);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return calls;
	}

	/**
	 * Ανακτά όλες τις κλήσεις για έναν συγκεκριμένο αριθμό τηλεφώνου
	 * 
	 * @param phoneNumber ο αριθμός τηλεφώνου
	 * @return μια λίστα με όλες τις κλήσεις για τον συγκεκριμένο αριθμό
	 */
	public List<Call> getAllCallsOfNumber(String phoneNumber) {
		List<Call> calls = new ArrayList<Call>();
		try {

			String query = "SELECT * FROM call WHERE callerNumber = ? OR receiverNumber = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, phoneNumber);
			statement.setString(2, phoneNumber);

			// Εκτελεί το αποτέλεσμα της ερώτησης
			ResultSet result_set = statement.executeQuery();
			PhoneNumberDao phoneNumberDAO = new PhoneNumberDao();
			// Επεξεργάζεται το αποτέλεσμα
			while (result_set.next()) {
				PhoneNumber callerPhoneNumber = phoneNumberDAO
						.getPhoneNumberByPhone(result_set.getString("callerNumber"));
				PhoneNumber receiverPhoneNumber = phoneNumberDAO
						.getPhoneNumberByPhone(result_set.getString("receiverNumber"));
				Call call = new Call(result_set.getInt("callId"), callerPhoneNumber, receiverPhoneNumber,
						result_set.getInt("duration"), result_set.getDate("date"));
				calls.add(call);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return calls;
	}
}
