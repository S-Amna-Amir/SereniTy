package serenity;

import java.sql.Date;

public class JournalEntry 
{
	String Jid;
	String text;
	Date date;
	public JournalEntry(String jid, String text, Date date, Boolean positive) 
	{
		super();
		Jid = jid;
		this.text = text;
		this.date = date;
		this.positive = positive;
	}

	Boolean positive;
	
	public String getJid() {
		return Jid;
	}

	public void setJid(String jid) {
		Jid = jid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getPositive() {
		return positive;
	}

	public void setPositive(Boolean positive) {
		this.positive = positive;
	}

	public String getStringDate() {
        return date.toString();
    }

    public String getShortText() {
        return text.length() > 30 ? text.substring(0, 30) + "..." : text; // Return part of the text
    }

}
