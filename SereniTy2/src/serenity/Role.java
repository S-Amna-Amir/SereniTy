package serenity;
/* Maha */

public abstract class Role  
{
	protected int accountId;
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected PersistenceHandler3 mysql;
	
	public Role(int accountId, String username, String password, String firstName, String lastName, String email) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mysql = new PersistenceHandler3();
    }

	
	public Role() 
	{
		this.accountId = 0;
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
	}


	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PersistenceHandler3 getMysql() {
        return this.mysql; 
    }

    public void setMysql(PersistenceHandler3 mysql) {
        this.mysql = mysql; 
    }

	
	
}
