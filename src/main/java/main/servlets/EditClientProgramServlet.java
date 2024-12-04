package main.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.dao.ClientDao;
import main.dao.PhoneNumberDao;
import main.model.Client;
import main.model.PhoneNumber;

@WebServlet("/edit-client-program")
public class EditClientProgramServlet extends HttpServlet {
	private static final long serialVersionUID = -2785102612786892472L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Έλεγχος αν υπάρχει το cookie SIGNEDIN
		boolean signedIn = false;
		jakarta.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (jakarta.servlet.http.Cookie cookie : cookies) {
				if (cookie.getName().equals("SIGNEDIN")) {
					signedIn = true;
					break;
				}
			}
		}

		if (!signedIn) {
			// Ανακατεύθυνση στη σελίδα σύνδεσης αν δεν έχει γίνει είσοδος
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// Λήψη του αναγνωριστικού πελάτη από το αίτημα
		int clientId = Integer.parseInt(request.getParameter("id"));

		// Χρήση του αναγνωριστικού πελάτη για να ανακτήσουμε τα στοιχεία του πελάτη
		ClientDao clientDao = new ClientDao();
		Client client = clientDao.getClientById(clientId);

		// Ορισμός των στοιχείων του πελάτη στο αίτημα
		request.setAttribute("client", client);

		String address = "/pages/edit-user-program.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ορισμός κωδικοποίησης για αιτήματα και απαντήσεις
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// Λήψη παραμέτρων από το αίτημα POST
		int phoneId = Integer.parseInt(request.getParameter("phoneId"));
		int programId = Integer.parseInt(request.getParameter("programId"));

		// Ενημέρωση του αναγνωριστικού προγράμματος για τον αριθμό τηλεφώνου
		PhoneNumberDao phoneNumberDao = new PhoneNumberDao();
		PhoneNumber phoneNumber = phoneNumberDao.getPhoneNumberById(phoneId);
		phoneNumber.setProgramId(programId);
		phoneNumberDao.updatePhoneNumber(phoneNumber);

		// Ανακατεύθυνση στη σελίδα πελατών
		response.sendRedirect(request.getContextPath() + "/clients");
	}
}
