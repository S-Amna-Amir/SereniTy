package serenity;

import java.time.LocalDateTime;

public class Report {

    private String userId;
    private int goalsAchieved;
    private int exercisesPerformed;
    private int streakCount;
    private LocalDateTime reportGeneratedAt;

    public Report(String userId, int goalsAchieved, int exercisesPerformed, int streakCount) {
        this.userId = userId;
        this.goalsAchieved = goalsAchieved;
        this.exercisesPerformed = exercisesPerformed;
        this.streakCount = streakCount;
        this.reportGeneratedAt = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public int getGoalsAchieved() {
        return goalsAchieved;
    }

    public int getExercisesPerformed() {
        return exercisesPerformed;
    }

    public int getStreakCount() {
        return streakCount;
    }

    public LocalDateTime getReportGeneratedAt() {
        return reportGeneratedAt;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Goals Achieved: " + goalsAchieved + ", Exercises Performed: " + exercisesPerformed +
                ", Streak Count: " + streakCount + ", Report Generated At: " + reportGeneratedAt;
    }

	public String getGeneratedTime() {
		// TODO Auto-generated method stub
		return null;
	}
}
