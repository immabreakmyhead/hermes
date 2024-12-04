package main.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.dao.ClientDao;
import main.dao.PhoneNumberDao;
import main.dao.ProgramDao;
import main.dao.UserDao;
import main.model.Client;
import main.model.PhoneNumber;
import main.model.Program;
import main.model.Role;

@WebServlet("/add-client")
public class AddClientServlet extends HttpServlet {
    private static final long serialVersionUID = -2903102612786892472L;

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

        String address = "/pages/add-client.jsp";
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
        String phoneNumberStr = request.getParameter("phoneNumber");
        int programId = Integer.parseInt(request.getParameter("program"));

        // Λήψη του προγράμματος από τη βάση δεδομένων
        ProgramDao programDao = new ProgramDao();
        Program program = programDao.getProgramById(programId);
        program.displayProgramInfo();

        // Αποθήκευση του αριθμού τηλεφώνου χρησιμοποιώντας την PhoneNumberDao
        PhoneNumberDao phoneNumberDao = new PhoneNumberDao();
        phoneNumberDao.addPhoneNumber(phoneNumberStr, programId);

        // Αποθήκευση του χρήστη χρησιμοποιώντας την UserDao
        UserDao userDao = new UserDao();
        int user_id = userDao.addUser(username, password, name, surname, email, Role.CLIENT);

        // Αποθήκευση του πελάτη χρησιμοποιώντας την ClientDao
        ClientDao clientDao = new ClientDao();
        clientDao.addClient(user_id, am, phoneNumberStr);

        // Ανακατεύθυνση στη σελίδα των πελατών
        response.sendRedirect(request.getContextPath() + "/clients");
    }
}
