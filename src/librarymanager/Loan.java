package librarymanager;

public class Loan { // Loan class used to represent the properties of loans.
    
    private int barcode;
    private String user_id;
    private String issue_date;
    private String due_date;
    private int num_renews;

    public Loan(int barcode, String user_id, String issue_date, String due_date, int num_renews) {
    
        this.barcode = barcode;
        this.user_id = user_id;
        this.issue_date = issue_date;
        this.due_date = due_date;
        this.num_renews = num_renews;
    
    }

    // GETTERS AND SETTERS
    
    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public int getNum_renews() {
        return num_renews;
    }

    public void setNum_renews(int num_renews) {
        this.num_renews = num_renews;
    }
    
}

