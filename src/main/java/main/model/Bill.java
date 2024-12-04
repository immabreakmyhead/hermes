package main.model;

import java.sql.Date;

public class Bill {
    // Το αναγνωριστικό του τιμολογίου
    private final int id;
    // Ο αριθμός τηλεφώνου που σχετίζεται με το τιμολόγιο
    private PhoneNumber phoneNumber;
    // Η ημερομηνία του τιμολογίου
    private Date date;
    // Το συνολικό ποσό πληρωτέο του τιμολογίου
    private double totalAmount;
    // Εάν το τιμολόγιο έχει πληρωθεί ή όχι (true ή false)
    private boolean isPaid;

    // Κατασκευαστής
    public Bill(int id, PhoneNumber phoneNumber, Date date, double totalAmount, boolean isPaid) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
    }

    // Επιστρέφει το ID του τιμολογίου
    public int getBillID() {
        return id;
    }

    // Επιστρέφει τον αριθμό τηλεφώνου του τιμολογίου
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    // Ορίζει τον αριθμό τηλεφώνου του τιμολογίου
    public void setPhoneNumber(PhoneNumber newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    // Επιστρέφει την ημερομηνία του τιμολογίου
    public Date getDate() {
        return date;
    }

    // Ορίζει την ημερομηνία του τιμολογίου
    public void setDate(Date newDate) {
        this.date = newDate;
    }

    // Επιστρέφει το συνολικό ποσό του τιμολογίου
    public double getTotalAmount() {
        return totalAmount;
    }

    // Επιστρέφει την κατάσταση πληρωμής του τιμολογίου
    public boolean getBillStatus() {
        return isPaid;
    }

    // Εμφανίζει τις πληροφορίες του τιμολογίου
    public void displayBillInfo() {
        System.out.println("[i] INFO: BILL ID IS " + id);
        System.out.println("[i] INFO: BILL PHONE NUMBER IS " + phoneNumber);
        System.out.println("[i] INFO: BILL date " + date);
        System.out.println("[i] INFO: BILL TOTAL AMOUNT " + totalAmount);
        System.out.println("[i] INFO: BILL STATUS " + (isPaid ? "PAID" : "OUTSTANDING"));
    }
}
