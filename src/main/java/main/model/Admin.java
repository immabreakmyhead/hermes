package main.model;

// Η κλάση που αναπαριστά έναν διαχειριστή στο σύστημα
public final class Admin extends User {
    /**
     * Κατασκευαστής για τη δημιουργία ενός διαχειριστή.
     * 
     * @param id       Το μοναδικό αναγνωριστικό του διαχειριστή
     * @param username Το όνομα χρήστη του διαχειριστή
     * @param name     Το όνομα του διαχειριστή
     * @param surname  Το επώνυμο του διαχειριστή
     * @param email    Το email του διαχειριστή
     * @param role     Ο ρόλος του διαχειριστή στο σύστημα (πρέπει να είναι
     *                 Role.ADMIN)
     */
    public Admin(int id, String username, String name, String surname, String email, Role role) {
        super(id, username, name, surname, email, role);
    }
}
