package librarymanager;

public class User { /* User class used to represent users that can take out 
                    books. */
    
    private String user_id;
    private String fname;
    private String lname;
    private String email;
    
    public User(String user_id, String fname, String lname, String email) {
    
        this.user_id = user_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    
    }
    
    // GETTERS AND SETTERS

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
