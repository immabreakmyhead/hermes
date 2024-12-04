package main.model;

public class Program {
    // Το αναγνωριστικό του προγράμματος
    private final int id;
    // Διάρκεια σε μήνες
    private final int duration;
    // Το όνομα του προγράμματος
    private String name;
    // Τα οφέλη του προγράμματος
    private String benefits;
    // Η περιγραφή του προγράμματος
    private String description;
    // Λίστα των κοστών
    private String costs;

    public Program(int id, String name, String description, String benefits, String costs, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.benefits = benefits;
        this.costs = costs;
        this.duration = duration;
    }

    // Επιστρέφει το ID του προγράμματος
    public int getProgramID() {
        return id;
    }

    // Επιστρέφει το όνομα του προγράμματος
    public String getName() {
        return name;
    }

    // Ορίζει το όνομα του προγράμματος
    public void setName(String newName) {
        this.name = newName;
    }

    // Επιστρέφει την περιγραφή του προγράμματος
    public String getDescription() {
        return description;
    }

    // Ορίζει την περιγραφή του προγράμματος
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    // Επιστρέφει τα κόστη του προγράμματος
    public String getCosts() {
        return costs;
    }

    // Ορίζει τα κόστη του προγράμματος
    public void setCosts(String newCosts) {
        this.costs = newCosts;
    }

    // Επιστρέφει τα οφέλη του προγράμματος
    public String getBenefits() {
        return benefits;
    }

    // Ορίζει τα οφέλη του προγράμματος
    public void setBenefits(String newBenefits) {
        this.benefits = newBenefits;
    }

    // Επιστρέφει τη διάρκεια του προγράμματος
    public int getDuration() {
        return duration;
    }

    // Εμφανίζει τις πληροφορίες του προγράμματος
    public void displayProgramInfo() {
        System.out.println("[i] INFO: PROGRAM ID IS " + id);
        System.out.println("[i] INFO: PROGRAM NAME IS " + name);
        System.out.println("[i] INFO: PROGRAM DESCRIPTION IS " + description);
        System.out.println("[i] INFO: DURATION " + duration);
        System.out.println("[i] INFO: COSTS " + costs);
    }
}
