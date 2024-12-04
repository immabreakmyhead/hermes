package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.model.Program;
import main.util.Database;

public class ProgramDao {
	Connection connection;
	static String err = "n/a";

	public ProgramDao() {
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
			ResultSet result_set = statement.executeQuery("SELECT MAX(id) FROM program;");
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
	 * Προσθέτει ένα πρόγραμμα στη βάση δεδομένων
	 * 
	 * @param name        το όνομα του προγράμματος
	 * @param description η περιγραφή του προγράμματος
	 * @param benefits    τα οφέλη του προγράμματος
	 * @param costs       τα κόστη του προγράμματος
	 * @param duration    η διάρκεια του προγράμματος
	 * @return 0 αν η προσθήκη ήταν επιτυχής, 1 σε περίπτωση σφάλματος
	 */
	public int addProgramme(String name, String description, String benefits, String costs, int duration) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO program (id, name, description, costs, benefits, duration) values (?, ?, ?, ?, ?, ?);");
			statement.setInt(1, findNextID());
			statement.setString(2, name);
			statement.setString(3, description);
			statement.setString(4, costs);
			statement.setString(5, benefits);
			statement.setInt(6, duration);
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
	 * Ενημερώνει ένα πρόγραμμα στη βάση δεδομένων
	 * 
	 * @param program το πρόγραμμα για ενημέρωση
	 * @return 0 αν η ενημέρωση ήταν επιτυχής, 1 σε περίπτωση σφάλματος
	 */
	public int updateProgram(Program program) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE program SET name = ?, description= ?, costs= ?, benefits = ?, duration = ? WHERE id = ?;");
			statement.setString(1, program.getName());
			statement.setString(2, program.getDescription());
			statement.setString(3, program.getCosts());
			statement.setString(4, program.getBenefits());
			statement.setInt(5, program.getDuration());
			statement.setInt(6, program.getProgramID());
			statement.executeUpdate();
		} catch (Exception sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * Ανακτά όλα τα διαθέσιμα προγράμματα από τη βάση δεδομένων
	 * 
	 * @return λίστα με όλα τα προγράμματα
	 */
	public List<Program> getAllPrograms() {
		List<Program> packs = new ArrayList<Program>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result_set = statement.executeQuery("SELECT * FROM program ORDER BY id;");
			while (result_set.next()) {
				Program program = new Program(
						result_set.getInt("id"),
						result_set.getString("name"),
						result_set.getString("description"),
						result_set.getString("benefits"),
						result_set.getString("costs"),
						result_set.getInt("duration"));
				packs.add(program);
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return packs;
	}

	/**
	 * Ανακτά ένα συγκεκριμένο πρόγραμμα από τη βάση δεδομένων με βάση το ID του
	 * 
	 * @param programId το ID του προγράμματος
	 * @return το πρόγραμμα με το συγκεκριμένο ID ή ένα νέο πρόγραμμα με ψεύτικα
	 *         δεδομένα αν δεν βρέθηκε
	 */
	public Program getProgramById(int programId) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM program WHERE id = ?;");
			statement.setInt(1, programId);
			ResultSet result_set = statement.executeQuery();
			while (result_set.next()) {
				Program program = new Program(
						result_set.getInt("id"),
						result_set.getString("name"),
						result_set.getString("description"),
						result_set.getString("benefits"),
						result_set.getString("costs"),
						result_set.getInt("duration"));
				return program;
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return new Program(0, "Fake Data Plan", "This is a Fake Data Plan", "No Benefits Included",
				"Maximum Cost Applied", 60);
	}

	public String giveErr() {
		return err;
	}
}
