/* Created and authored by Alex McCullough (B00836551) and 
Conor McGraph (B00844471). */

package librarymanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    private ArrayList<User> users = new ArrayList<>();
    
    private ArrayList<Loan> loans = new ArrayList<>();
    
    private ArrayList<Item> items = new ArrayList<>();
    
    private final Scanner sc = new Scanner(System.in);
    
    private final String loansPath = "src\\librarymanager\\data\\LOANS.csv";
        
    private final String itemsPath = "src\\librarymanager\\data\\ITEMS.csv";
        
    private final String usersPath = "src\\librarymanager\\data\\USERS.csv";

    public static void main(String[] args) {
        Main m = new Main();
        m.run();
    }
    
    private void run() {
        this.initialise();
        this.menu();
    }
    
    /* This method is called at the start of program execution, it will read  
    from the CSV files. */
    
    private void initialise() {
        
        
        BufferedReader reader = null;
        
        ArrayList<String[]> content = new ArrayList<>();
        
        // LOANS
              
        try {
            
            String line = "";         
            reader = new BufferedReader(new FileReader(loansPath));
            while ((line = reader.readLine()) != null) {
        
                content.add(line.split(","));
        
            }
            
            content.remove(0);
  
            for (int i = 0; i < content.size(); i++) {
                
                loans.add(new Loan(Integer.parseInt(content.get(i)[0]), 
                        content.get(i)[1], content.get(i)[2], content.get(i)[3], 
                        Integer.parseInt(content.get(i)[4])));
            
            }
            
        }
        
        catch (IOException | NumberFormatException e){
            e.printStackTrace();
        }
        
        finally {
            
            try {
                reader.close();
                
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        }
        
        content.clear();
        
        // USERS
        
        try {
            
            String line = "";         
            reader = new BufferedReader(new FileReader(usersPath));
            while ((line = reader.readLine()) != null) {
        
                content.add(line.split(","));
        
            }
            
            content.remove(0);
  
            for (int i = 0; i < content.size(); i++) {
                
                users.add(new User(content.get(i)[0], content.get(i)[1], 
                        content.get(i)[2], content.get(i)[3]));
            
            }
            
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
        
        finally {
            
            try {
                reader.close();
                
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        }
        
        content.clear();
        
        // ITEMS
        
        try {
            
            String line = "";         
            reader = new BufferedReader(new FileReader(itemsPath));
            while ((line = reader.readLine()) != null) {
        
                content.add(line.split(","));
        
            }
            
            content.remove(0);
  
            for (int i = 0; i < content.size(); i++) {
                
                items.add(new Item(Integer.parseInt(content.get(i)[0]), 
                        content.get(i)[1], content.get(i)[2], content.get(i)[3], 
                        Integer.parseInt(content.get(i)[4]), 
                        content.get(i)[5]));
            
            }
                      
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
        
        finally {
            
            try {
                reader.close();
                
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        }
     
    }
    
    /* This method prints a list of menu items allowing the user to select
    which facility of the program they want to use. */
    
    private void menu() {
 
        try {

            int choice;
        
            System.out.println("=== Main Menu ===\n1. Issue item/Create a loan."
            + "\n2. Renew item."
            + "\n3. Record return.\n4. View items on loan.\n5. View items held in "
            + "library.\n6. Quit\n\nEnter your selection: ");
        
            choice = Integer.parseInt(sc.nextLine());
            
            switch (choice) {
            case 1:
                System.out.println("Menu option #1 selected.");
                this.IssueItem();
                this.QuitMenu();
                break;
            case 2:
                System.out.println("Menu option #2 selected.");
                this.RenewItem();
                this.QuitMenu();
                break;
            case 3:
                System.out.println("Menu option #3 selected.");
                this.RecordItem();
                this.QuitMenu();
                break;
            case 4:
                System.out.println("Menu option #4 selected.");
                this.ViewLoan();
                this.QuitMenu();
                break;
            case 5:
                System.out.println("Menu option #5 selected.");
                this.ViewLib();
                this.QuitMenu();
                break;
            case 6:
                System.out.println("Program terminating, goodbye.");
                this.WriteToLoans();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input, input must be an integer "
                        + "between 1 & 6.");
                this.QuitMenu();
                break;
                
            }
        
        }
        
        catch(NumberFormatException e) {
        
            System.out.println("Invalid data type, please enter a number "
                    + "between 1 & 6.");
            this.QuitMenu();
    
        }
            
    }
    
    /* This method is displayed to the user after one of the main methods
    of the program has finished, it allows the user to either close the program,
    or return to the main menu. */
    
    private void QuitMenu() {
    
        int choice;
        
        System.out.println("\n=== Quit Menu ===\n1. Return to main menu.\n2. Quit.");
        
        System.out.println("\nEnter selection: ");
        
        choice = Integer.parseInt(sc.nextLine());
        
        if (choice == 1) {
        
            this.menu();
        
        }
        
        else if(choice == 2) {
        
            System.out.println("Program terminating, goodbye.");
            this.WriteToLoans();
            System.exit(0);
        
        }
        
        else {
        
            System.out.println("Invalid input, input must be 1 or 2.\n");
            this.QuitMenu();
        
        }
    
    }
    
    /* This method allows the user to create/issue a loan. */
    
    private void IssueItem() {
                
        String barcode;
        
        boolean bcodeExists = false;
        
        String userID;
        
        boolean IDExists = false;
        
        String type = "";
        
        String issueDate = "";
        
        String dueDate = "";
        
        System.out.println("Enter item barcode: ");
        
        barcode = sc.nextLine();
        
        for (int i=0; i < items.size(); i++) {

            if (Integer.parseInt(barcode) == items.get(i).getBarcode()) {

                System.out.println("Barcode exists.");
                bcodeExists = true;

            }
            
        }
        
        if (bcodeExists == false) {
            
            System.out.println("The barcode entered doesn't exist, "
                        + "loan creation cancelled.");
            this.QuitMenu();
            
        }
        
        System.out.println("Enter user id: ");
        
        userID = sc.nextLine();
        
        for (int i=0; i < users.size(); i++) {
        
            if (userID.equals(users.get(i).getUser_id())) {
            
                System.out.println("User ID exists.");
                IDExists = true;
            
            }
            
        }
            
        if(IDExists == false) {

            System.out.println("The user id entered doesn't exist, "
                    + "loan creation cancelled.");
            this.QuitMenu();

        }
        
        
        for(int i=0; i < items.size(); i++) {
        
            if (Integer.parseInt(barcode) == items.get(i).getBarcode()) {
            
                type = items.get(i).getType();
                
            }
            
        }
        
        LocalDate date = LocalDate.now();
        String[] temp = date.toString().split("-");
        issueDate += temp[2] + "/" + temp[1] + "/" + temp[0];
        
        if ("Book".equals(type)) {
        
            date = date.plusWeeks(4);
            String[] x = date.toString().split("-");
            dueDate += x[2] + "/" + x[1] + "/" + x[0];
            
        }
        
        else if("Multimedia".equals(type)) {
        
            date = date.plusWeeks(1);
            String[] x = date.toString().split("-");
            dueDate += x[2] + "/" + x[1] + "/" + x[0];
        
        }
          
        Loan loan = new Loan(Integer.parseInt(barcode), userID, issueDate, dueDate, 0);
        loans.add(loan);
        
    }
        
    /* This methods allows a loan to be renewed, the number of renewals is noted
    and the due date is modified based on the type of content. */
    
    private void RenewItem() {
    
        String barcode;
        
        boolean exists = false;
        
        String type = "";
                
        String dueDate = "";
        
        System.out.println("Enter item barcode: ");
        
        barcode = sc.nextLine();
        
        for(int i=0; i < items.size(); i++) {
        
            if (Integer.parseInt(barcode) == items.get(i).getBarcode()) {
            
                type = items.get(i).getType();
                
            }
            
        }
        
        for(int i=0; i < loans.size(); i++) {
        
            if (Integer.parseInt(barcode) == loans.get(i).getBarcode()) {
            
                System.out.println("Barcode exists.");
                exists = true;
                
                System.out.println(loans.get(i).getNum_renews());
                
                if (loans.get(i).getNum_renews() < 3 && "Book".equals(type)) {
                
                    loans.get(i).setNum_renews(loans.get(i).getNum_renews() 
                                                                        + 1);
                    
                    String[] tempArray = loans.get(i).getDue_date().split("/");
                    String rawDate = "";
                    rawDate += tempArray[2] + "-" + tempArray[1] + "-" + tempArray[0];
                    LocalDate date = LocalDate.parse(rawDate);
                    date = date.plusWeeks(2);
                    String[] temp = date.toString().split("-");
                    System.out.println(Arrays.toString(temp));
                    dueDate += temp[2] + "/" + temp[1] + "/" + temp[0];
                    loans.get(i).setDue_date(dueDate);
                 
                }
                
                else if(loans.get(i).getNum_renews() >= 3 && "Book".equals(type)){
                
                    System.out.println("Books cannot be renewed more than three"
                            + "times, loan extension cancelled.");
                    
                }
                
               if(loans.get(i).getNum_renews() < 2 && 
                                                "Multimedia".equals(type)) {
                
                    loans.get(i).setNum_renews(loans.get(i).getNum_renews() 
                                                                        + 1);
                    
                    String[] tempArray = loans.get(i).getDue_date().split("/");
                    String rawDate = "";
                    rawDate += tempArray[2] + "-" + tempArray[1] + "-" + tempArray[0];
                    LocalDate date = LocalDate.parse(rawDate);
                    date = date.plusWeeks(1);
                    String[] temp = date.toString().split("-");
                    System.out.println(Arrays.toString(temp));
                    dueDate += temp[2] + "/" + temp[1] + "/" + temp[0];
                    loans.get(i).setDue_date(dueDate);
                }
               
                else if(loans.get(i).getNum_renews() >= 2 && "Multimedia".equals(type)){
                
                    System.out.println("Multimedia items cannot be "
                    + "renewed more than two times, loan extension cancelled.");
                    
                }
                 
            }
            
        }
        
        if(exists == false) {

            System.out.println("This loan doesn't exist, please try again "
            + "with the barcode of a currently active item on loan.");
            exists = true;
            this.QuitMenu();

        }
        
        type = "";
     
    }
    
    /* This allows the program to record the return of an item, the loan is
    removed from the LOANS.CSV file. */
    
    private void RecordItem() { // Record the return of an item.
    
        String barcode;
        
        boolean exists = false;
        
        System.out.println("Enter item barcode: ");
        
        barcode = sc.nextLine();
        
        for(int i=0; i < loans.size(); i++) {
        
            if (Integer.parseInt(barcode) == loans.get(i).getBarcode()) {
                
                exists = true;
            
                System.out.println("Barcode exists.");
                LocalDate currentDate = LocalDate.now();
                String[] x = loans.get(i).getDue_date().split("/");
                String rawData = "";
                rawData += x[2] + "-" + x[1] + "-" + x[0];
                LocalDate dueDate = LocalDate.parse(rawData);
                
                if(dueDate.compareTo(currentDate) >= 0) {
                
                    System.out.println("Item returned, thank you.");
                    loans.remove(i);
                    
                }
                
                else if(dueDate.compareTo(currentDate) < 0) {
                
                    System.out.println("Item returned after due date, please "
                            + "pay the appropriate fine for late return.");
                    loans.remove(i);
                
                }
                
            }
                          
        }
        
       if(exists == false) {
            
                System.out.println("No such loan exists, return cancelled.");
                exists = true;
                this.QuitMenu();
            
            }
        
    }
    
    // Allows the user to view what loans are currently in effect.
    
    private void ViewLoan() {
        
        System.out.println("Barcode   UserId    IssueDate  DueDate    "
        + "NumRenews");
        
        for(int i=0; i < loans.size(); i++) {
        
            System.out.println(loans.get(i).getBarcode() + " " + 
            loans.get(i).getUser_id() + " " + loans.get(i).getIssue_date() +
            " " + loans.get(i).getDue_date() + " " 
            + loans.get(i).getNum_renews());
        
        }
        
    }
    
    // Allows the user to view the inventory of the library.
    
    private void ViewLib() {
    
        System.out.println("Barcode   Author/Artist   Title   Type   Year   "
        + "ISBN");
        
        for(int i=0; i < items.size(); i++) {
            
            System.out.println(items.get(i).getBarcode() + " " + 
                    items.get(i).getCreator() + " " + items.get(i).getTitle() + 
                    " " + items.get(i).getType() + " " + items.get(i).getYear() 
                    + " " + items.get(i).getIsbn());
            
        }
    
    }
    
    /* The method called at the end of program execution; the arraylist of loans
    is converted and written to the LOANS.CSV file. */
    
    private void WriteToLoans() {
    
        try {
        
            FileWriter fw = new FileWriter(loansPath);
            
            fw.write("Barcode,User_id,Issue_Date,Due_Date,numRenews\n");
            
            String temp = "";
            
            for (int i=0; i < loans.size(); i++) {
            
                temp += (loans.get(i).getBarcode() + "," + 
                        loans.get(i).getUser_id() + "," + 
                        loans.get(i).getIssue_date() + "," + 
                        loans.get(i).getDue_date() + "," + 
                        loans.get(i).getNum_renews());
                
                if (i < (loans.size() - 1)) {
                temp += "\n";
                }
                
            fw.write(temp);
                
            temp = "";
            
            }
            
            fw.close();
            
            System.out.println("Successfully wrote to file.");
        
        }
        
        catch(IOException e) {
        
            e.printStackTrace();
        
        }
        
    }
    
}
