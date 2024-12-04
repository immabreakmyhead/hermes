package main.servlets;

import java.io.IOException;
import main.dao.UserDao;
import main.util.Encryption;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1076048530718946589L;

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

		if (signedIn) {
			// Ανακατεύθυνση στην αρχική σελίδα αν ο χρήστης έχει ήδη συνδεθεί
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		// Προώθηση στη σελίδα σύνδεσης
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ορισμός κωδικοποίησης για αιτήματα και απαντήσεις
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// Λήψη παραμέτρων από το αίτημα POST
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassword = Encryption.getHashMD5(password, "hermes");

		// Εκτέλεση πιστοποίησης χρήστη χρησιμοποιώντας την UserDao
		UserDao userDao = new UserDao();
		int userId = userDao.findUser(username, hashedPassword);

		if (userId != 0) {
			// Επιτυχής πιστοποίηση, ορισμός του cookie SIGNEDIN
			Cookie cookie = new Cookie("SIGNEDIN", String.valueOf(userId));
			cookie.setMaxAge(24 * 60 * 60); // 1 ημέρα σε δευτερόλεπτα
			response.addCookie(cookie);

			// Ανακατεύθυνση στην αρχική σελίδα
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			// Αποτυχία πιστοποίησης, ορισμός μηνύματος σφάλματος
			request.setAttribute("loginError", "Λανθασμένο Όνομα Χρήστη ή Κωδικός!");

			// Προώθηση πίσω στη σελίδα σύνδεσης
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
