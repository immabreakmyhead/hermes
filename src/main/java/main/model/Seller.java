package main.model;

public class Seller extends User {
    private final String AM;

    public Seller(int id, String username, String name, String surname, String email, Role role, String AM) {
        super(id, username, name, surname, email, role);
        this.AM = AM;
    }

    // Εκδίδει τον λογαριασμό του πελάτη
    public void issueClientBill(Client client) {
        System.out.println(
                "[+] Έκδοση λογαριασμού για τον πελάτη " + client.getUsername() + " από τον πωλητή " + getUsername());
        // Εδώ υλοποιήστε τη λογική για την έκδοση του λογαριασμού του πελάτη
    }

    // Επιστρέφει το ΑΜ του πωλητή
    public String getAM() {
        return AM;
    }
}
