package serenity;

public class Exercise 
{
	int eid;
	String desc;
	String path;
	Boolean practiced;
	
	public Exercise(int eid, String desc, String path, Boolean practiced) 
	{
		super();
		this.eid = eid;
		this.desc = desc;
		this.path = path;
		this.practiced = practiced;
	}
	
	public int getEid() 
	{
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public Boolean getPracticed() {
		return practiced;
	}

	public void setPracticed(Boolean practiced) {
		this.practiced = practiced;
	}
	

}
