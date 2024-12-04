package main.model;

public class Client extends User {
    private final String AM; // Το μοναδικό ΑΜ του πελάτη
    private PhoneNumber phoneNumber; // Ο αριθμός τηλεφώνου του πελάτη

    public Client(int id, String username, String name, String surname, String email, Role role, String AM,
            PhoneNumber phoneNumber) {
        super(id, username, name, surname, email, role);
        this.AM = AM;
        this.phoneNumber = phoneNumber;
    }

    public void showBill() {
        System.out.println("[+] Showing bill for Client " + getUsername());
        // Υλοποιήστε τη λογική για την προβολή του λογαριασμού
    }

    public void showHistory() {
        System.out.println("[+] Showing history for Client " + getUsername());
        // Υλοποιήστε τη λογική για την προβολή του ιστορικού
    }

    public void payBill() {
        System.out.println("[+] Payment successful for Client " + getUsername());
        // Υλοποιήστε τη λογική για την πληρωμή του λογαριασμού
    }

    // Επιστρέφει το μοναδικό ΑΜ του πελάτη
    public String getAM() {
        return AM;
    }

    // Επιστρέφει τον αριθμό τηλεφώνου του πελάτη
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    // Επιστρέφει τον αριθμό τηλεφώνου του πελάτη ως συμβολοσειρά
    public String getPhoneNumberString() {
        return phoneNumber.getPhone();
    }

    // Ορίζει τον αριθμό τηλεφώνου του πελάτη
    public void setPhoneNumber(PhoneNumber newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
}
