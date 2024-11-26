package serenity;

import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDate;

public class Creator 
{
	public static Creator instance;
	public static ExList Exlist;
	public static Mood sad;
	public static Mood anger;
	public static Mood fear;
	public static Mood anxiety;
	public static Mood happy;
	public static Mood grateful;
	public static Mood excited;
	public static Mood hopeful;
	public static UserJournal Journal;
	private static JournalEntry SelectedEntry;
	private static int JECounter;
	private static int instCounter;
	private static ArrayList <Exercise> sadexlist;
	private static ArrayList <Exercise> angerexlist;
	private static ArrayList <Exercise> anxexlist;
	private static ArrayList <Exercise> fearexlist;
	private ArrayList <Exercise> otherlist;
	

	private Creator() 
	{
		JECounter = 0;
		instCounter = 0;
		Exercise sad1 = new Exercise(1, "Gratefulness", "Sadness1.mp4", false );
		Exercise sad2 = new Exercise(2, "Nature", "Sadness2.mp4", false );
		Exercise sad3= new Exercise(3, "Beauty", "Sadness3.mp4", false );
		Exercise anger1 = new Exercise(4, "Breathing", "Anger1.mp4", false );
		Exercise anger2= new Exercise(5, "Progressive Relaxation", "Sadness1.mp4", false );
		Exercise anger3= new Exercise(6, "5 4 3 2 1 Rule", "Sadness1.mp4", false );
		Exercise anxiety1 = new Exercise(7, "Meditation", "Sadness1.mp4", false );
		Exercise anxiety2 = new Exercise(8, "Gratefulness", "Sadness1.mp4", false );
		Exercise anxiety3 = new Exercise(9, "Gratefulness", "Sadness1.mp4", false );
		Exercise fear1 = new Exercise(10, "Gratefulness", "Sadness1.mp4", false );
		Exercise fear2 = new Exercise(11, "Gratefulness", "Sadness1.mp4", false );
		Exercise fear3 = new Exercise(12, "Gratefulness", "Sadness1.mp4", false );
		ArrayList <Exercise> exlist = new ArrayList<>();
		 sadexlist = new ArrayList<>();
		 angerexlist = new ArrayList<>();
		 anxexlist = new ArrayList<>();
		 fearexlist = new ArrayList<>();
		sadexlist.add(sad1);
		sadexlist.add(sad2);
		sadexlist.add(sad3);
		angerexlist.add(anger1);
		angerexlist.add(anger2);
		angerexlist.add(anger3);
		anxexlist.add(anxiety1);
		anxexlist.add(anxiety2);
		anxexlist.add(anxiety3);
		fearexlist.add(fear1);
		fearexlist.add(fear2);
		fearexlist.add(fear3);
		exlist.add(sad1);
		exlist.add(sad2);
		exlist.add(sad3);
		exlist.add(anger1);
		exlist.add(anger2);
		exlist.add(anger3);
		exlist.add(anxiety1);
		exlist.add(anxiety2);
		exlist.add(anxiety3);
		exlist.add(fear1);
		exlist.add(fear2);
		exlist.add(fear3);
		sad = new Mood (sadexlist);
		anger = new Mood (angerexlist);
		anxiety = new Mood (anxexlist);
		fear = new Mood (fearexlist);
		happy = new Mood(null);
		grateful = new Mood(null);
		excited = new Mood(null);
		hopeful = new Mood(null);
		
		Exlist = new ExList(exlist);
		
		//load entries from db
		ArrayList <JournalEntry> entries = new ArrayList<>();
		ArrayList <JournalEntry> highs = new ArrayList<>();
		String i = "1";
		String i2 = "2";
		String t = "Hi";
		Boolean pos = true;
		Date d = Date.valueOf("2023-11-26");
		Date d2 = Date.valueOf(LocalDate.now());
		Date d3 = Date.valueOf("2022-11-26");
		JournalEntry je = new JournalEntry(i, t, d, pos);
		JournalEntry j2 = new JournalEntry(i2, t, d2, pos);
		JournalEntry j3 = new JournalEntry("3", "Hi, this is the third entry", d3, false);
		//iterate over entries and add positive ones to highs
		Journal = new UserJournal();
		System.out.println("Created journal");
		Journal.AddEntry(je);
		Journal.AddEntry(j2);
		Journal.AddEntry(j3);
		
		
	}
	
	public static Creator getInstance()
	{
		if(instCounter == 0)
		{
			System.out.println("Created creator: " + instCounter);
			instance = new Creator();
			instCounter++;
			
		}
		return instance;
	}
	
	public void addEntry(String i, Date d, String t, Boolean b)
	{
		JournalEntry j = new JournalEntry(i, t, d, b);
		Journal.AddEntry(j);
	}
	
	public static ExList getExlist() {
		return Exlist;
	}

	public void setExlist(ExList exlist) {
		Exlist = exlist;
	}

	public Mood getSad() {
		return sad;
	}

	public void setSad(Mood sad) {
		this.sad = sad;
	}

	public Mood getAnger() {
		return anger;
	}

	public void setAnger(Mood anger) {
		this.anger = anger;
	}

	public Mood getFear() {
		return fear;
	}

	public void setFear(Mood fear) {
		this.fear = fear;
	}

	public Mood getAnxiety() {
		return anxiety;
	}

	public void setAnxiety(Mood anxiety) {
		this.anxiety = anxiety;
	}

	public static UserJournal getJournal() {
		return Journal;
	}

	public void setJournal(UserJournal journal) {
		Journal = journal;
	}
	
	public JournalEntry getSelectedEntry() {
		return SelectedEntry;
	}

	public void setSelectedEntry(JournalEntry selectedEntry) {
		SelectedEntry = selectedEntry;
	}

	public static int getJECounter() {
		return JECounter;
	}

	public static void setJECounter(int jECounter) {
		JECounter = jECounter;
	}
	
	


	public static ArrayList<Exercise> getSadexlist() {
		return sadexlist;
	}

	public void setSadexlist(ArrayList<Exercise> sadexlist) {
		this.sadexlist = sadexlist;
	}

	public static ArrayList<Exercise> getAngerexlist() {
		return angerexlist;
	}

	public  void setAngerexlist(ArrayList<Exercise> angerexlist) {
		this.angerexlist = angerexlist;
	}

	public static ArrayList<Exercise> getAnxexlist() {
		return anxexlist;
	}

	public void setAnxexlist(ArrayList<Exercise> anxexlist) {
		this.anxexlist = anxexlist;
	}

	public static ArrayList<Exercise> getFearexlist() {
		return fearexlist;
	}

	public void setFearexlist(ArrayList<Exercise> fearexlist) {
		this.fearexlist = fearexlist;
	}

	public ArrayList<Exercise> getOtherlist() {
		return otherlist;
	}

	public void setOtherlist(ArrayList<Exercise> otherlist) {
		this.otherlist = otherlist;
	}

	public static Role createAccountObject(int accountId) 
	{
	    try {
	        Role role = PersistenceHandler3.getRoleById(accountId);
	        if (role != null) 
	        {
	            return role;
	        }
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    return null;
	}
		    
	


}
