package main.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/program")
public class ProgramServlet extends HttpServlet {
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

        // Ορισμός της διεύθυνσης της σελίδας προγραμμάτων που θα εμφανιστεί
        String address = "/pages/program.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
