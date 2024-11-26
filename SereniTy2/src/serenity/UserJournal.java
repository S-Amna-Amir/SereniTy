package serenity;

import java.sql.Date;
import java.util.ArrayList;

public class UserJournal 
{
	private ArrayList <JournalEntry> pages;

	private ArrayList <JournalEntry> highlights;
	
	public UserJournal() 
	{
		pages = new ArrayList<>();
		highlights = new ArrayList<>();
	}
	public UserJournal(ArrayList<JournalEntry> pages, ArrayList<JournalEntry> highs) 
	{
		super();
		this.pages = pages;
		addPositiveEntriesToHighlights();
	}
	
	public UserJournal(ArrayList<JournalEntry> pages)
	{
		highlights = new ArrayList<>();
		this.pages = pages;
	}
	
	public void addPositiveEntriesToHighlights() 
	{
	    for (JournalEntry entry : pages) {
	        if (entry.getPositive() && !highlights.contains(entry)) 
	        {
	            highlights.add(entry);
	        }
	    }
	}

	
	public void AddEntry(JournalEntry je)
	{
		pages.add(je);
		if(je.positive)
		{
			highlights.add(je);
		}
		System.out.println("Added entry");
		
		int i = Creator.getJECounter();
		i++;
		Creator.setJECounter(i);
		
	}
	
	public void DeleteEntry(JournalEntry j)
	{
		pages.removeIf(entry -> entry.getJid() == j.getJid());
	    highlights.removeIf(entry -> entry.getJid() == j.getJid());
	}
	
	public void EditEntry(String i, JournalEntry j)
	{
		for(JournalEntry entry: pages)
		{
			if(entry.getJid().equals(i))
			{
				entry.setDate(j.getDate());
				entry.setPositive(j.getPositive());
				entry.setText(j.getText());
			}
		}
		
	}
	
	public void EditEntry(JournalEntry e, JournalEntry j)
	{
		String text = j.getText();
		Date d = j.getDate();
		Boolean b = j.getPositive();
		String i = j.getJid();
		e.setDate(d);
		e.setText(text);
		e.setPositive(b);
		e.setJid(i);
	}
	
	public ArrayList<JournalEntry> getPages() {
		return pages;
	}
	public void setPages(ArrayList<JournalEntry> pages) {
		this.pages = pages;
	}
	public ArrayList<JournalEntry> getHighlights() {
		return highlights;
	}
	public void setHighlights(ArrayList<JournalEntry> highlights) {
		this.highlights = highlights;
	}

}
