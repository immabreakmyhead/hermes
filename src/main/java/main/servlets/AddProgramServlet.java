package main.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.dao.ProgramDao;

@WebServlet("/add-program")
public class AddProgramServlet extends HttpServlet {
	private static final long serialVersionUID = -2903102612786892453L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Έλεγχος αν το cookie SIGNEDIN υπάρχει
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
			// Αν δεν έχει συνδεθεί ο χρήστης, ανακατεύθυνση στη σελίδα σύνδεσης
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		String address = "/pages/add-program.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ορισμός κωδικοποίησης για αιτήσεις και αποκρίσεις
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// Λήψη παραμέτρων από το αίτημα POST
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String benefits = request.getParameter("benefits");
		String costs = request.getParameter("costs");
		int duration = Integer.parseInt(request.getParameter("duration"));

		// Προσθήκη προγράμματος μέσω της ProgramDao
		ProgramDao programDao = new ProgramDao();
		programDao.addProgramme(name, description, benefits, costs, duration);

		// Ανακατεύθυνση στη σελίδα με τα προγράμματα
		response.sendRedirect(request.getContextPath() + "/program");
	}
}
