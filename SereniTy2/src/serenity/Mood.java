package serenity;

import java.util.ArrayList;

public class Mood 
{
	
	private ArrayList<Exercise> related;
	
	public Mood(ArrayList<Exercise> related) 
	{
		super();
		this.related = related;
	}

	public ArrayList<Exercise> getRelated() {
		return related;
	}

	public void setRelated(ArrayList<Exercise> related) {
		this.related = related;
	}

	public Mood() 
	{
		related = new ArrayList<>();
	}
	

}

