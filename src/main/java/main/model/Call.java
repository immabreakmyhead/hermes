package main.model;

import java.sql.Date;

public class Call {
    // Μοναδικό αναγνωριστικό κλήσης
    private final int callID;
    // Ο αριθμός τηλεφώνου του καλούντος
    private final PhoneNumber callerNumber;
    // Ο αριθμός τηλεφώνου του παραλήπτη
    private final PhoneNumber receiverNumber;
    // Διάρκεια της κλήσης σε δευτερόλεπτα
    private int duration;
    // Η ημερομηνία της κλήσης
    private Date date;

    public Call(int callID, PhoneNumber callerNumber, PhoneNumber receiverNumber, int duration, Date date) {
        this.callID = callID;
        this.callerNumber = callerNumber;
        this.receiverNumber = receiverNumber;
        this.duration = duration;
        this.date = date;
    }

    // Επιστρέφει το ID της κλήσης
    public int getCallID() {
        return callID;
    }

    // Επιστρέφει τον αριθμό του καλούντος
    public PhoneNumber getCallerNumber() {
        return callerNumber;
    }

    // Επιστρέφει τον αριθμό του παραλήπτη
    public PhoneNumber getReceiverNumber() {
        return receiverNumber;
    }

    // Επιστρέφει τη διάρκεια της κλήσης
    public int getDuration() {
        return duration;
    }

    // Επιστρέφει την ημερομηνία της κλήσης
    public Date getDate() {
        return date;
    }

    // Ορίζει τη διάρκεια της κλήσης
    public void setDuration(int newDuration) {
        this.duration = newDuration;
    }

    // Εμφανίζει τις πληροφορίες της κλήσης
    public void displayCallInfo() {
        System.out.println("[i] INFO: CALL ID IS " + callID);
        System.out.println("[i] INFO: CALLER NUMBER IS " + callerNumber);
        System.out.println("[i] INFO: RECEIVER NUMBER IS " + receiverNumber);
        System.out.println("[i] INFO: DURATION " + duration);
    }
}
