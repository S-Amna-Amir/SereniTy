package serenity;
/* Maha */

import java.util.List;

public class Admin extends Role {
	//private ReportGenerator report;
	private String roleType;
	
	
	public Admin(int accountId, String username, String password, String firstName, String lastName, String email) {
		super(accountId, username, password, firstName, lastName, email);
		setRoleType("Admin");
		this.setMysql(new PersistenceHandler3());
	}
	
	
	//generate report
	//view feedback
	//add account----done
	
	public Admin() {
		super();
		this.setMysql(new PersistenceHandler3());
	}


	//delete account-------done
	//update account---done
	//view accounts--------done
//logout
	public int add_user(String username, String password, String firstName, String lastName, String email, float age) {
        String roleType = "User"; 
        int accountId = mysql.saveRole(username, password, firstName, lastName, email, roleType);
        if (accountId > 0) {
            boolean userSaved = mysql.saveUser(accountId, 0, 0, java.time.LocalDate.now().toString(), age);
            if (userSaved) {
                System.out.println("user added with accountid: " + accountId);
                return accountId;
            } 
            else {
                System.out.println("failed to add user");
            }
        } 
        else {
        	System.out.println("failed to add user");
        }
        return -1; 
    }

	
	public boolean delete_user(String username) {
	    int accountId = mysql.getAccountIdByUsername(username);

	    if (accountId > 0) {
	        boolean userDeleted = mysql.deleteUser(accountId);
	        if (userDeleted) {
	            System.out.println("user deleted");
	            return true;
	        } 
	        else {
	        	System.out.println("failed to del user");
	        }
	    } 
	    else {
            System.out.println("user not found");
	    }
	    return false; 
	}
	
	public List<User> viewAccounts() {
	    return mysql.fetchAllUsers();
	}

	public boolean edit_user_account(String oldUsername, String newUsername, String newPassword) {
	     int accountId = mysql.getAccountIdByUsername(oldUsername); 

	     if (accountId > 0) {
	         boolean updated = mysql.updateUserPassword(accountId, newPassword);

	         if (updated) {
	             System.out.println("user's password updated");
	             return true;
	         } else {
	             System.out.println("user's password not updated");
	         }
	     } 
	     else {
             System.out.println("user not found");
	     }
	     return false; 
	}

	
	public String getRoleType() {
		return roleType;
	}



	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

}

