<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Remove the cookie named "SIGNEDIN"
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("SIGNEDIN")) {
                cookie.setMaxAge(0); // Setting the cookie age to 0 deletes it
                cookie.setPath(request.getContextPath()); // Set the cookie path to root
                response.addCookie(cookie);
                break;
            }
        }
    }
    
    // Redirect to the login page from the root of the website
    String contextPath = request.getContextPath();
    response.sendRedirect(contextPath + "/login"); // This will redirect to /contextPath/login

    // Example: If your context path is "/myapp", it will redirect to "/myapp/login"
%>
