package main.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.dao.ProgramDao;
import main.model.Program;

@WebServlet("/edit-program")
public class EditProgramServlet extends HttpServlet {
	private static final long serialVersionUID = -2903102612786392453L;

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

		// Λήψη του αναγνωριστικού προγράμματος από το αίτημα
		int programId = Integer.parseInt(request.getParameter("id"));

		// Ορισμός του αναγνωριστικού προγράμματος στα γνωρίσματα του αιτήματος
		request.setAttribute("program_id", programId);

		String address = "/pages/edit-program.jsp";
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
		int programId = Integer.parseInt(request.getParameter("programId"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String benefits = request.getParameter("benefits");
		String costs = request.getParameter("costs");

		// Δημιουργία της διεπαφής ProgramDao
		ProgramDao programDao = new ProgramDao();

		// Ανάκτηση του αντικειμένου προγράμματος βάσει του ID
		Program program = programDao.getProgramById(programId);

		// Ενημέρωση του αντικειμένου προγράμματος με τις νέες τιμές
		program.setName(name);
		program.setDescription(description);
		program.setBenefits(benefits);
		program.setCosts(costs + "€");

		// Κλήση της μεθόδου DAO για ενημέρωση του προγράμματος
		programDao.updateProgram(program);

		// Ανακατεύθυνση στη σελίδα προγραμμάτων
		response.sendRedirect(request.getContextPath() + "/program");
	}
}
