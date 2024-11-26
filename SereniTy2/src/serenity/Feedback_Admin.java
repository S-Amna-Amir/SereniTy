package serenity;
//////////for code and ui phpackage application;

import java.time.LocalDate;

public class Feedback_Admin {
    private static int idCounter = 1;
    private int feedbackId;
    private String feedbackText;
    private LocalDate date;

    public Feedback_Admin(String feedbackText) {
        this.feedbackId = idCounter++;
        this.feedbackText = feedbackText;
        this.date = LocalDate.now();
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Feedback ID: " + feedbackId + ", Date: " + date + ", Feedback: " + feedbackText;
    }
}
