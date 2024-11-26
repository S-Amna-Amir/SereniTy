package serenity;

/* Maha */

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenceHandler3 
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/serenity";
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "1234"; 
    
    public int saveRole(String username, String password, String firstName, String lastName, String email, String roleType) {
        String sql = "insert into role (_username, _password, _firstname, _lastname, _email, _roleType) values (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, email);
            statement.setString(6, roleType); //uhhh

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //fails
    }

    public boolean saveUser(int accountId, int currentStreak, int highestStreak, String joiningDate, float age) {
        String sql = "insert into user (_accountid, _currentstreak, _higheststreak, _joiningdate, _age) values (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            statement.setInt(2, currentStreak);
            statement.setInt(3, highestStreak);
            statement.setString(4, joiningDate);
            statement.setFloat(5, age);

            return statement.executeUpdate() > 0;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false; //fails
    }
    
    public static Role getRoleById(int accountId) {
        String sql = "select role.*, user._currentstreak, user._higheststreak, user._joiningdate, user._age " +
                     "from role " +
                     "join user on role._accountid = user._accountid " +
                     "join mood on mood._userid = user._accountid " +
                     "where role._accountid = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String roleType = rs.getString("_roleType");
                    if ("Admin".equalsIgnoreCase(roleType)) { //admin
                        return new Admin(rs.getInt("_accountid"),rs.getString("_username"),rs.getString("_password"), rs.getString("_firstname"), rs.getString("_lastname"), rs.getString("_email"));
                    } 
                    else if ("User".equalsIgnoreCase(roleType)) 
                    { //user
                        User u = new User(rs.getInt("_accountid"),rs.getString("_username"),rs.getString("_password"),rs.getString("_firstname"),rs.getString("_lastname"),rs.getString("_email"),rs.getInt("_currentstreak"),rs.getInt("_higheststreak"),rs.getDate("_joiningdate").toLocalDate(),rs.getFloat("_age") );
                        u.setMoodToday(fetchMood(getUserIdByAccountId(rs.getInt("_accountid"))));
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Mood2 fetchMood(int UserID)
    {
    	  String sql = "select * from mood where _userid = ? order by _date desc limit 1"; // Fetch the most recent mood
          try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
               PreparedStatement stmt = conn.prepareStatement(sql)) 
          {

              stmt.setInt(1, UserID);
              try (ResultSet rs = stmt.executeQuery()) 
              {
            	  if (rs.next()) 
              {
                  return new Mood2( rs.getInt("_moodid"), rs.getInt("_userid"), rs.getDate("_date").toLocalDate(),rs.getString("_moodname"),rs.getInt("_intensity"));
              }
          }
      } 
      catch (SQLException e) {
          e.printStackTrace();
      }
      return null;  
  }
    
    
    public static ResultSet getUserByAccountId(int accountId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "select * from user where _accountid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accountId);
            return stmt.executeQuery(); 
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("LOL2");
        return null;
    }
    
    public static String authenticateUser(String username, String password, boolean isAdmin) {
        String roleType = isAdmin ? "Admin" : "User"; 
        String sql = "select _roleType from role where _username = ? and _password = ? and _roleType = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, roleType);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("_roleType");
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;  
    }



    public boolean deleteAccount(int accountId) {
            String sql = "delete from role where _accountid = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, accountId);
                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0; 
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        return false; //didn't
    }

    public static User getUserByUsername(String username) {
        String sql = "select role.*, user._currentstreak, user._higheststreak, user._joiningdate, user._age " +
                     "from role " +
                     "join user on role._accountid = user._accountid " +
                     "where role._username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username); 
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String roleType = rs.getString("_roleType");
                    if ("User".equalsIgnoreCase(roleType)) {
                        return new User(rs.getInt("_accountid"), rs.getString("_username"), rs.getString("_password"), rs.getString("_firstname"), rs.getString("_lastname"), rs.getString("_email"), rs.getInt("_currentstreak"), rs.getInt("_higheststreak"), rs.getDate("_joiningdate").toLocalDate(), rs.getFloat("_age"));
                    }
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null; //not found
    }

    public static Admin getAdminByUsername(String username) {
        String sql = "select * from role where _username = ? and _roleType = 'Admin'";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Admin(rs.getInt("_accountid"), rs.getString("_username"), rs.getString("_password"), rs.getString("_firstname"), rs.getString("_lastname"), rs.getString("_email"));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    public int getAccountIdByUsername(String username) {
        String query = "select _accountId from Role where _username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("_accountId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }

    public boolean deleteUser(int accountId) {
        String deleteRoleQuery = "delete from Role where _accountId = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement deleteRoleStmt = conn.prepareStatement(deleteRoleQuery)) {
          
            deleteRoleStmt.setInt(1, accountId);
            int roleRowsAffected = deleteRoleStmt.executeUpdate();
            return roleRowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    public List<User> fetchAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "select role._accountid, role._username, role._firstname, role._lastname, role._email, User._age, User._joiningdate from role join user on Role._accountId = User._accountId";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int accountId = rs.getInt("_accountId");
                String username = rs.getString("_username");
                String firstName = rs.getString("_firstname");
                String lastName = rs.getString("_lastname");
                String email = rs.getString("_email");
                float age = rs.getFloat("_age");
                //String regdate = rs.getString("_joiningdate");

                User user = new User();
                user.setAccountId(accountId);
                user.setUsername(username);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setAge(age);
                //user.setJoiningDate(regdate);
                users.add(user);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<Mood2> fetchAllMoods(int userId) {
        List<Mood2> moods = new ArrayList<>();
        String query = "SELECT _date, _moodname, _intensity FROM mood WHERE _userid = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LocalDate date = rs.getDate("_date").toLocalDate();
                    String moodName = rs.getString("_moodname");
                    int intensity = rs.getInt("_intensity");
                    Mood2 mood = new Mood2(date, moodName, intensity);
                    moods.add(mood);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return moods;
    }
    public boolean updateUserPassword(int accountId, String newPassword) {
        String query = "update role  set _password = ? where _accountId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newPassword);
            stmt.setInt(2, accountId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasUserEnteredMoodToday(int accountId, LocalDate todayDate) {
        String query = "select count(*) from mood m join user u on m._userid = u._userid where u._accountid = ? and m._date = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setDate(2, java.sql.Date.valueOf(todayDate));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; //if count > 0, entry exists
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveMood(int userId, String moodName, int intensity, LocalDate date, boolean isNegative) {
        String sql = "insert into mood (_userid, _moodName, _intensity, _date) values (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId); 
            statement.setString(2, moodName);
            statement.setInt(3, intensity);
            statement.setDate(4, java.sql.Date.valueOf(date));
            //statement.setBoolean(5, isNegative);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected); 

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static int getUserIdByAccountId(int accountId) {
        String query = "select _userid from user where _accountid = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("_userid");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }

}

