package application.utils;

public class Global {
    private static Global instance;
    private boolean isAdmin;

    private Global() { } //singleton lol

    public static Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
