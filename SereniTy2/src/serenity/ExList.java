package serenity;

import java.util.ArrayList;

public class ExList implements MoodObserver  
{
	private ArrayList <Exercise> exercises;
	private ArrayList <Exercise> today;
	private ArrayList <Exercise> practiced;
	
	public ExList(ArrayList<Exercise> exercises) 
	{
		super();
		this.exercises = exercises;
		today = new ArrayList<>();
		practiced = new ArrayList<>();
	}
	
	
	public void AddEx(Exercise e)
	{
		exercises.add(e);
	}

	public ExList() 
	{
		exercises = new ArrayList<>();
		today =new ArrayList<>();
		practiced = new ArrayList<>();
	}

	@Override
	public void update(Mood mood) 
	{
		today = mood.getRelated();
		if(today == null)
		{
			today = exercises;
		}
	}

	public ArrayList<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(ArrayList<Exercise> exercises) {
		this.exercises = exercises;
	}

	public ArrayList<Exercise> getToday() {
		return today;
	}

	public void setToday(ArrayList<Exercise> today) {
		this.today = today;
	}

	public ArrayList<Exercise> getPracticed() {
		return practiced;
	}

	public void setPracticed(ArrayList<Exercise> practiced) {
		this.practiced = practiced;
	}
	
}
