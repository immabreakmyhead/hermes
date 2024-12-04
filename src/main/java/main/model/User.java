package main.model;

import main.model.Role;

public class User {

    private int id;
    // Το όνομα χρήστη του χρήστη (μοναδικό)
    private String username;
    // Το όνομα του χρήστη
    private String name;
    // Το επώνυμο του χρήστη
    private String surname;
    // Το email του χρήστη
    private String email;
    // Ο ρόλος του χρήστη (ΠΕΛΑΤΗΣ, ΠΩΛΗΤΗΣ, ΔΙΑΧΕΙΡΙΣΤΗΣ)
    private Role role;
    // Συνολικοί χρήστες
    private static int userCounter = 0;

    public User(int id, String username, String name, String surname, String email, Role role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        userCounter++;
    }

    // Επιστρέφει τον αριθμό των χρηστών
    public static int getUserCounter() {
        return userCounter;
    }

    // Επιστρέφει το id του χρήστη
    public int getId() {
        return id;
    }

    // Επιστρέφει το όνομα χρήστη του χρήστη
    public String getUsername() {
        return username;
    }

    // Ορίζει το όνομα χρήστη του χρήστη χρησιμοποιώντας ένα String
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    // Επιστρέφει το όνομα του χρήστη
    public String getName() {
        return name;
    }

    // Ορίζει το όνομα του χρήστη
    public void setName(String newName) {
        this.name = newName;
    }

    // Επιστρέφει το επώνυμο του χρήστη
    public String getSurname() {
        return surname;
    }

    // Ορίζει το επώνυμο του χρήστη
    public void setSurname(String newSurname) {
        this.surname = newSurname;
    }

    // Επιστρέφει το email του χρήστη
    public String getEmail() {
        return email;
    }

    // Ορίζει το email του χρήστη
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    // Επιστρέφει το ρόλο του χρήστη
    public Role getRole() {
        return role;
    }

    // Επιστρέφει το ρόλο του χρήστη ως συμβολοσειρά
    public String getStringRole() {
        return role.toString();
    }

    // Ορίζει το ρόλο του χρήστη
    public void setRole(Role newRole) {
        this.role = newRole;
    }

}
