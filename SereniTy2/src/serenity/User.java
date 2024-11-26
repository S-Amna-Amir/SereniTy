package serenity;
/* Maha */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class User extends Role 
{
	private int currentStreak;
	private int highestStreak;
	private LocalDate joiningDate;
	private UserJournal UJ;
	
	public UserJournal getUJ() {
		return UJ;
	}
	public void setUJ(UserJournal uJ) {
		UJ = uJ;
	}

	private float age;
	//private Journal journal;
	//private ArrayList<Goal> goals;
	private Mood2 moodToday; //check->if already entered today ------
	//private MoodCalendar moodCalendar; ------
	//private ArrayList<Feedback> feedback;
	//private ReportGenerator reportGenerator;
	//private ArrayList<Exercise> exercises;
	private String roleType;
	
	public int getCurrentStreak() {
		return currentStreak;
	}
	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}
	public int getHighestStreak() {
		return highestStreak;
	}
	public void setHighestStreak(int highestStreak) {
		this.highestStreak = highestStreak;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	public float getAge() {
		return age;
	}
	public void setAge(float age) {
		this.age = age;
	}
	
	public User(int accountId, String username, String password, String firstName, String lastName, String email, int currentStreak, int highestStreak, LocalDate joiningDate, float age) 
	{
	    super(accountId, username, password, firstName, lastName, email);
	    this.currentStreak = currentStreak;
	    this.highestStreak = highestStreak;
	    this.joiningDate = joiningDate;
	    this.age = age;
	    setRoleType("User");
	    this.mysql = new PersistenceHandler3();
	}
	
	public User() 
	{
        super();
        moodToday = null;
        this.mysql = new PersistenceHandler3();
    }
	
	public String getRoleType() 
	{
		return roleType;
	}
	public void setRoleType(String roleType) 
	{
		this.roleType = roleType;
	}
	public PersistenceHandler3 getMysql() 
	{
		return this.getMysql();
	}
	public void setMysql(PersistenceHandler3 mysql) 
	{
		this.mysql = mysql;
	}

	public boolean editAccount(String oldPassword, String newPassword) {
        int accountId = this.getAccountId();
        if (accountId > 0) {
            boolean passwordUpdated = mysql.updateUserPassword(accountId, newPassword);

            if (passwordUpdated) {
                this.setPassword(newPassword);
                System.out.println("passoord updated successfully");
                return true;
            } else {
                System.out.println("failed");
            }
        } else {
            System.out.println("acc not found");
        }
        return false;
    }

    // Delete account
    public boolean deleteAccount() {
        int accountId = this.getAccountId();
        if (accountId > 0) {
            boolean accountDeleted = mysql.deleteAccount(accountId);
            if (accountDeleted) {
                System.out.println("deleted");
                return true;
            } else {
                System.out.println("failed");
            }
        } else {
            System.out.println("not found");
        }
        return false;
    }
	//update/edit account------------------done
	//delete account------------------done
	public Mood2 getMoodToday() {
		return moodToday;
	}
	public void setMoodToday(Mood2 moodToday) {
		this.moodToday = moodToday;
	}
	
	
	//add entry
	//delete entry
	//edit entry
	
	//view journal
	//filter journal
	
	//view calendar--------
	public List<Mood2> viewMoods() 
	{
		int userid = mysql.getUserIdByAccountId(getAccountId());
	    return mysql.fetchAllMoods(userid);
	}

	
	//give feedback
	//view feedback
	
	//practice exercise
	//view exercises
	
	//set goal
	
	
	public void enterMood(String moodName, int intensity, boolean isNegative, ArrayList <Exercise> exlist) 
	{
	    if (this.moodToday != null) {
	        System.out.println("Mood already entered for today.");
	        return; 
	    }

	    LocalDate today = LocalDate.now();
	    boolean alreadyEntered = new Mood2().check_mood_entry_for_today(this.getAccountId());

	    if (alreadyEntered) 
	    {
	        System.out.println("Mood entry already exists in the database for today.");
	    } 
	    else 
	    {
	        Mood2 newMood = new Mood2(moodName, intensity, today, isNegative, exlist);

	        //mood to the database
	        boolean saved = mysql.saveMood(retrieveUserId(), moodName, intensity, today, isNegative);
	        if (saved) 
	        {
	            this.moodToday = newMood; 
	            System.out.println("Mood successfully entered for today.");
	            ExList EL = Creator.getExlist();
	            Mood a;
	            
	            if(moodName.equals("Excited"))
	            {
	            	a = Creator.excited;
	            	EL.update(a);
	            }
	            else if(moodName.equals("Grateful"))
	            {
	            	a = Creator.grateful;
	            	EL.update(a);
	            }
	            else if(moodName.equals("Sad"))
	            {
	            	a = Creator.sad;
	            	EL.update(a);
	            }
	            else if(moodName.equals("Angry"))
	            {
	            	a = Creator.anger;
	            	EL.update(a);
	            }
	            else if(moodName.equals("Hopeful"))
	            {
	            	a = Creator.hopeful;
	            	EL.update(a);
	            }
	            else if(moodName.equals("Fearful"))
	            {
	            	a = Creator.fear;
	            	EL.update(a);
	            }
	            else if(moodName.equals("Anxious"))
	            {
	            	a = Creator.anxiety;
	            	EL.update(a);
	            }
	            else if(moodName.equals("Angry"))
	            {
	            	a = Creator.anger;
	            	EL.update(a);
	            }
	            
	            
	        }
	        else {
	            System.out.println("Failed to save mood to the database.");
	        }
	    }
	    System.out.println("mood:"+moodToday.getMoodName());
	      
	}

	public int retrieveUserId() {
	    int accountId = this.getAccountId();
	    if (accountId > 0) {
	        return mysql.getUserIdByAccountId(accountId);
	    } 
	    else {
	        System.out.println("no");
	        return -1;
	    }
	}


	//view highlights
	
	//complete goal
	//view goals
	//update goal
	
	
	
}


/*
public class User 
{
	public UserJournal journal;
	public UserJournal getJournal() 
	{
		return journal;
	}

	public void setJournal(UserJournal journal) 
	{
		this.journal = journal;
	}

	public ExList getExlist() {
		return exlist;
	}

	public void setExlist(ExList exlist) {
		this.exlist = exlist;
	}

	public ExList exlist;

	public User() 
	{
		// TODO Auto-generated constructor stub
	}

}
*/
