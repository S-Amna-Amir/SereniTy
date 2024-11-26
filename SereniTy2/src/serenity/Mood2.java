package serenity;
/* Maha */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Mood2 {
    private String moodName;
    private boolean isNegative;
    private int intensity;
    private PersistenceHandler3 mysql; 
    private ArrayList<Exercise> related;
    private LocalDate date;
    
    public Mood2(int moodId, int userId, LocalDate date, String moodName, int intensity)
    {
            
            this.date = date;
            this.moodName = moodName;
            this.intensity = intensity;
        
    }
    
    public Mood2() 
    {
        moodName = "";
        isNegative = false;
        intensity = 0;
        mysql = new PersistenceHandler3(); 
        related = new ArrayList<>();
    }

    public Mood2(String moodName, int intensity, LocalDate currentDate, boolean isNegative, ArrayList <Exercise> ex) 
    {
		this.moodName = moodName;
		this.intensity = intensity;
		this.isNegative = isNegative;
		mysql = new PersistenceHandler3(); 
		related = ex;
	}

	public Mood2(LocalDate date, String mood, int intensity) 
	{
		this.date = date;
		this.moodName = mood;
		this.intensity = intensity;
		related = new ArrayList<>();
	}

	public boolean check_mood_entry_for_today(int accountId) 
	{
        LocalDate today = LocalDate.now();
        return mysql.hasUserEnteredMoodToday(accountId, today);
    }
	
	public ArrayList<Exercise> getRelated() 
	{
		return related;
	}

	public void setRelated(ArrayList<Exercise> related) 
	{
		this.related = related;
	}
	

    public boolean isNegative() {
        return isNegative;
    }
    public void setNegative(boolean isNegative) {
        this.isNegative = isNegative;
    }
    public String getMoodName() {
        return moodName;
    }
    public void setMoodName(String moodName) {
        this.moodName = moodName;
    }
    public int getIntensity() {
        return intensity;
    }
    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return date;
	}

}

