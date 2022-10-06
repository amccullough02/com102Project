package librarymanager;

public class Item { // Item class used to represent items in the library.
    
    private int barcode;
    private String creator;
    private String title;
    private String type;
    private int year;
    private String isbn;
    
    public Item(int barcode, String creator, String title, String type, int year, String isbn) {
    
        this.barcode = barcode;
        this.creator = creator;
        this.title = title;
        this.type = type;
        this.year = year;
        this.isbn = isbn;
    
    }

    // GETTERS AND SETTERS
    
    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
      
}
