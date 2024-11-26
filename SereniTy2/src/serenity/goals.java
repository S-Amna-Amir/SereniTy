package serenity;

import java.util.Date;

public class goals {
    private int goalId;
    private String userId;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;

    // Constructor
    public goals(int goalId, String userId, String description, Date startDate, Date endDate, String status) {
        this.goalId = goalId;
        this.userId = userId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    public goals(int goalId, String description) {
        this.goalId = goalId;
        this.description = description;
    }
    // Getters and Setters
    public int getGoalId() { return goalId; }
    public void setGoalId(int goalId) { this.goalId = goalId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
