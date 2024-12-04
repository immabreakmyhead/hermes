package main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.model.Bill;
import main.model.Call;
import main.model.PhoneNumber;
import main.util.Database;

public class BillDao {
	Connection connection;
	static String err = "n/a";

	public BillDao() {
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
	 * Πληρώνει έναν λογαριασμό
	 * 
	 * @param billId το ID του λογαριασμού
	 * @return 0 αν η λειτουργία ήταν επιτυχής, 1 αν υπήρξε σφάλμα
	 */

	public int payBill(int billId) {
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE bill SET isPaid = true WHERE id = ?;");
			statement.setInt(1, billId);
			statement.executeUpdate();
		} catch (Exception sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * Προσθέτει έναν λογαριασμό στη βάση δεδομένων
	 * 
	 * @param phoneNumber το τηλέφωνο του λογαριασμού
	 * @param date        η ημερομηνία του λογαριασμού
	 * @param totalAmount το συνολικό ποσό του λογαριασμού
	 * @return 0 αν η λειτουργία ήταν επιτυχής, 1 αν υπήρξε σφάλμα
	 */
	public int addBill(String phoneNumber, Date date, double totalAmount) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO bill (id, phoneNumber, date, totalAmount, isPaid ) values (?, ?, ?, ?, ?);");
			statement.setInt(1, findNextID());
			statement.setString(2, phoneNumber);
			statement.setDate(3, date);
			statement.setDouble(4, totalAmount);
			statement.setBoolean(5, false);
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
	 * Ανακτά όλους τους λογαριασμούς από τη βάση δεδομένων
	 * 
	 * @return μια λίστα με όλους τους λογαριασμούς
	 */
	public List<Bill> getAllBills() {
		List<Bill> bills = new ArrayList<Bill>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery("SELECT * FROM bill ORDER BY date DESC;");
			PhoneNumberDao phoneNumberDAO = new PhoneNumberDao();
			while (result_set.next()) {
				PhoneNumber phone = phoneNumberDAO.getPhoneNumberByPhone(result_set.getString("phoneNumber"));
				Bill bill = new Bill(result_set.getInt("id"), phone, result_set.getDate("date"),
						result_set.getDouble("totalAmount"), result_set.getString("isPaid").equals(true));
				bills.add(bill);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return bills;
	}

	/**
	 * Ανακτά όλους τους λογαριασμούς για έναν συγκεκριμένο αριθμό τηλεφώνου
	 * 
	 * @param phoneNumber ο αριθμός τηλεφώνου
	 * @return μια λίστα με όλους τους λογαριασμούς για τον συγκεκριμένο αριθμό
	 */
	public List<Bill> getAllBillsOfNumber(String phoneNumber) {
		List<Bill> bills = new ArrayList<Bill>();
		try {

			String query = "SELECT * FROM bill WHERE phoneNumber = ? ORDER BY date DESC;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, phoneNumber);

			ResultSet result_set = statement.executeQuery();
			PhoneNumberDao phoneNumberDAO = new PhoneNumberDao();
			while (result_set.next()) {
				PhoneNumber phone = phoneNumberDAO.getPhoneNumberByPhone(result_set.getString("phoneNumber"));
				Bill bill = new Bill(result_set.getInt("id"), phone, result_set.getDate("date"),
						result_set.getDouble("totalAmount"), result_set.getBoolean("isPaid"));
				bills.add(bill);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return bills;
	}
}
