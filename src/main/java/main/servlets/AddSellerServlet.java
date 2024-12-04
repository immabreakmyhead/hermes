package main.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.dao.SellerDao;
import main.dao.UserDao;
import main.model.Role;

@WebServlet("/add-seller")
public class AddSellerServlet extends HttpServlet {
	private static final long serialVersionUID = -2903302612786892472L;

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
		String address = "/pages/add-seller.jsp";
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String am = request.getParameter("am");

		// Προσθήκη χρήστη τύπου Seller μέσω της UserDao
		UserDao userDao = new UserDao();
		int user_id = userDao.addUser(username, password, name, surname, email, Role.SELLER);

		// Προσθήκη πωλητή μέσω της SellerDao
		SellerDao sellerDao = new SellerDao();
		sellerDao.addSeller(user_id, am);

		// Ανακατεύθυνση στη σελίδα με τους πωλητές
		response.sendRedirect(request.getContextPath() + "/sellers");
	}
}
