package main.model;

public class PhoneNumber {
    // Το αναγνωριστικό του τηλεφώνου
    private final int id;
    // Ο αριθμός του τηλεφώνου
    private String number;
    // Η κατάσταση του τηλεφώνου (ΕΝΕΡΓΟ, ΑΝΕΝΕΡΓΟ)
    private String status;
    // Το id του προγράμματος του τηλεφώνου
    private int programId;

    public PhoneNumber(int id, String number, int programId, String status) {
        this.id = id;
        this.number = number;
        this.programId = programId;
        this.status = status;
    }

    // Επιστρέφει το αναγνωριστικό του τηλεφώνου
    public int getId() {
        return this.id;
    }

    // Επιστρέφει τον αριθμό του τηλεφώνου
    public String getPhone() {
        return number;
    }

    // Ορίζει τον αριθμό του τηλεφώνου
    public void setPhone(String newNumber) {
        this.number = newNumber;
    }

    // Επιστρέφει το id του προγράμματος του τηλεφώνου
    public int getProgramId() {
        return programId;
    }

    // Ορίζει το id του προγράμματος του τηλεφώνου
    public void setProgramId(int newProgramId) {
        this.programId = newProgramId;
    }

    // Ενεργοποιεί τον αριθμό του τηλεφώνου
    public void activate() {
        this.status = "ACTIVE";
    }

    // Απενεργοποιεί τον αριθμό του τηλεφώνου
    public void deactivate() {
        this.status = "INACTIVE";
    }

    // Επιστρέφει την κατάσταση του τηλεφώνου
    public String getStatus() {
        return this.status;
    }

    // Εμφανίζει τις πληροφορίες του τηλεφώνου
    public void displayPhoneNumberInfo() {
        System.out.println("[i] INFO: PHONE ID IS " + id);
        System.out.println("[i] INFO: PHONE NUMBER IS " + number);
        System.out.println("[i] INFO: PHONE programId IS " + programId);
        System.out.println("[i] INFO: PHONE STATUS " + status);
    }
}
