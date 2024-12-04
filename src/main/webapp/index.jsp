<%@ page import="jakarta.servlet.http.Cookie" %>
<%
    // Retrieve all cookies from the request
    Cookie[] cookies = request.getCookies();
    boolean signedIn = false;

    // Check if the SIGNEDIN cookie exists
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("SIGNEDIN".equals(cookie.getName())) {
                signedIn = true;
                break;
            }
        }
    }

    // Redirect based on the presence of the SIGNEDIN cookie
    if (signedIn) {
        response.sendRedirect("home");
    } else {
        response.sendRedirect("login");
    }
%>